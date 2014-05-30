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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import es.deusto.otp.client.socket.SocketFacade;

public class CodeWindow extends JFrame implements ActionListener {
	private SocketFacade socket;
	
	private static final long serialVersionUID = 1L;
	private JTextField codeJTextField;
	private JButton ok;
	private JButton cancel;
	
	public CodeWindow(SocketFacade socket){
		codeJTextField = new JTextField(12);
		ok = new JButton("OK");
		cancel = new JButton("Cancel");
		ok.addActionListener(this);
	    cancel.addActionListener(this);
	
		
		codeJTextField.setText("");
		codeJTextField.setForeground(Color.LIGHT_GRAY);
		

		this.setTitle("Welcome to the OTP ");
		this.setSize(new Dimension(300, 170));
		this.setResizable(false);

		JPanel centro = new JPanel();
		JPanel bots = new JPanel();
		bots.add(ok);
		bots.add(cancel);

		posicionaLinea(centro, "  Introduce code here:  ", codeJTextField);
	

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(centro, "Center");
		getContentPane().add(bots, "South");

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setVisible(true);
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dimen = this.getSize();
		this.setLocation((pantalla.width - dimen.width) / 2,
				(pantalla.height - dimen.height) / 2);
		
		this.socket = socket;
	}

	public static void main(String[] args) {
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
		JButton b = (JButton) a.getSource();

		if (b == ok) {
				if (codeJTextField.getText() != null && !codeJTextField.getText().isEmpty()) {
					
					this.socket.write("OTP " + codeJTextField.getText());
					
					String code;
					
					try {
						code = this.socket.read();
						
						if (!code.split(" ")[0].equals("203")) {
							JOptionPane.showMessageDialog(null, "OTP invalido", "Error", JOptionPane.ERROR_MESSAGE);
							
							return;
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(null, "Enhorabuena a entrado en el servidor", "Entrando", JOptionPane.INFORMATION_MESSAGE);
				}
		}
		else if (b == cancel) {
			System.exit(0);
		}
		
	}
}