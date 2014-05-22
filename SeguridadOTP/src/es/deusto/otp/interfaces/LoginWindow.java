package es.deusto.otp.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;


public class LoginWindow extends JFrame implements ActionListener{

	private JTextField login;
	private JLabel Llogin;
	private JTextField contrasena;
	private JLabel Lcontraseña;
	private JButton aceptar;
	private JButton cancelar;

	
	public LoginWindow(){
		login = new JTextField(12);
		contrasena = new JTextField(12);
		aceptar = new JButton("OK");
		cancelar = new JButton("Cancel");
		aceptar.addActionListener(this);
	    cancelar.addActionListener(this);
	
		
		login.setText("user");
		login.setForeground(Color.LIGHT_GRAY);
		contrasena.setText("password");
		contrasena.setForeground(Color.LIGHT_GRAY);

		this.setTitle("Welcome to the OTP ");
		this.setSize(new Dimension(300, 170));
		 this.setResizable(false);

		JPanel centro = new JPanel();
		JPanel bots = new JPanel();
		bots.add(aceptar);
		bots.add(cancelar);

		posicionaLinea(centro, "           Login:  ", login);
		posicionaLinea(centro, "Contraseña:  ", contrasena);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(centro, "Center");
		getContentPane().add(bots, "South");

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
		
		if(b==aceptar){
			
		}
		
		else if(b==cancelar){
			
		}
	}
}

