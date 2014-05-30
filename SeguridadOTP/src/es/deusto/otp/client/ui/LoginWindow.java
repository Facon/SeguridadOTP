package es.deusto.otp.client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.deusto.otp.client.socket.SocketFacade;

public class LoginWindow extends JFrame implements ActionListener {
	Logger logger = LoggerFactory.getLogger(LoginWindow.class);
	
	private SocketFacade socket;
	
	private JTextField loginJTextField;
	private JLabel loginJLabel;
	private JTextField passwordJTextField;
	private JLabel passwordJLabel;
	private JButton ok;
	private JButton cancel;

	public LoginWindow() {
		loginJTextField = new JTextField(12);
		passwordJTextField = new JTextField(12);
		ok = new JButton("OK");
		cancel = new JButton("Cancel");
		ok.addActionListener(this);
		cancel.addActionListener(this);

		loginJTextField.setText("");
		loginJTextField.setForeground(Color.LIGHT_GRAY);
		passwordJTextField.setText("");
		passwordJTextField.setForeground(Color.LIGHT_GRAY);

		this.setTitle("Welcome to the OTP ");
		this.setSize(new Dimension(300, 170));
		this.setResizable(false);

		JPanel centro = new JPanel();
		JPanel bots = new JPanel();
		bots.add(ok);
		bots.add(cancel);

		posicionaLinea(centro, "            Login:  ", loginJTextField);
		posicionaLinea(centro, "Contraseña:  ", passwordJTextField);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(centro, "Center");
		getContentPane().add(bots, "South");

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		try {
			socket = new SocketFacade("127.0.0.1", 4445);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Servidor no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public static void main(String[] args) {

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {

		}

		LoginWindow vi = new LoginWindow();
		vi.setVisible(true);
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dimen = vi.getSize();
		vi.setLocation((pantalla.width - dimen.width) / 2,
				(pantalla.height - dimen.height) / 2);
	}

	protected void posicionaLinea(Container p, String etiqueta, Component campo) {
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		tempPanel.add(new JLabel(etiqueta));
		tempPanel.add(campo);
		p.add(tempPanel);
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		// TODO Auto-generated method stub
		JButton b = (JButton) a.getSource();

		if (b == ok) {
			if (loginJTextField.getText() != null && !loginJTextField.getText().isEmpty()) {
				if (passwordJTextField.getText() != null && !passwordJTextField.getText().isEmpty()) {
					
					this.socket.write("USER " + loginJTextField.getText());
					
					String code;
					
					try {
						code = this.socket.read();
						
						if (!code.split(" ")[0].equals("201")) {
							JOptionPane.showMessageDialog(null, "Usuario no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
							
							return;
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					this.socket.write("PASS " + passwordJTextField.getText());
					
					try {
						code = this.socket.read();
						
						if (!code.split(" ")[0].equals("202")) {
							JOptionPane.showMessageDialog(null, "Usuario/Contraseña no válido.", "Error", JOptionPane.ERROR_MESSAGE);
							
							return;
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					this.setVisible(false);
					
					new CodeWindow(socket).setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "Campo contraseña vacio.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Campo usuario vacio.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}

		else if (b == cancel) {
			System.exit(0);
		}
	}
}
