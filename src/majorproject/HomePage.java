package majorproject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomePage {

	private JFrame frmHomepage;
	private String name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage window = new HomePage();
					window.frmHomepage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HomePage() {
		initialize();
	}
	public HomePage(String name) {
		this.name = name;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHomepage = new JFrame();
		frmHomepage.setTitle("HomePage");
		frmHomepage.setBounds(100, 100, 511, 432);
		frmHomepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHomepage.getContentPane().setLayout(null);
		frmHomepage.setVisible(true);

		JLabel lblWish = new JLabel("");
		lblWish.setHorizontalAlignment(SwingConstants.CENTER);
		lblWish.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblWish.setBounds(22, 11, 416, 35);
		frmHomepage.getContentPane().add(lblWish);
		System.out.println(name);
		lblWish.setText("WELCOME TO MELOBOT");
		
		JButton btnNewButton = new JButton("Login ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage1 lp=new LoginPage1();
				frmHomepage.dispose();
			}
		});
		btnNewButton.setForeground(new Color(64, 0, 64));
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton.setBounds(176, 328, 107, 23);
		frmHomepage.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\DC\\OneDrive\\Pictures\\Saved Pictures\\MeloBot.jpg"));
		lblNewLabel.setBounds(0, 0, 495, 393);
		frmHomepage.getContentPane().add(lblNewLabel);
	}
}

