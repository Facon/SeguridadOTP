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

public class CodeWindow extends JFrame implements ActionListener {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField codigo;
	private JButton ok;
	private JButton cancel;
	
	public CodeWindow(){
		codigo = new JTextField(12);
		ok = new JButton("OK");
		cancel = new JButton("Cancel");
		ok.addActionListener(this);
	    cancel.addActionListener(this);
	
		
		codigo.setText("code");
		codigo.setForeground(Color.LIGHT_GRAY);
		

		this.setTitle("Welcome to the OTP ");
		this.setSize(new Dimension(300, 170));
		this.setResizable(false);

		JPanel centro = new JPanel();
		JPanel bots = new JPanel();
		bots.add(ok);
		bots.add(cancel);

		posicionaLinea(centro, "  Introduce code here:  ", codigo);
	

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(centro, "Center");
		getContentPane().add(bots, "South");

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		System.out.println("la cangreja");

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {

		}
		System.out.println("la cangreja");

		CodeWindow vi = new CodeWindow();
		vi.setVisible(true);
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dimen = vi.getSize();
		vi.setLocation((pantalla.width - dimen.width) / 2,
				(pantalla.height - dimen.height) / 2);
		System.out.println("tu vieja");
	}

	protected void posicionaLinea(Container p, String etiqueta, Component campo) {
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		tempPanel.add(new JLabel(etiqueta));
		tempPanel.add(campo);
		p.add(tempPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}