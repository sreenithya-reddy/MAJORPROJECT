package majorproject;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class exitPage {

	private JFrame frmExitPage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					exitPage window = new exitPage();
					window.frmExitPage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public exitPage() {
		initialize();
		frmExitPage.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmExitPage = new JFrame();
		frmExitPage.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\DC\\OneDrive\\Pictures\\Saved Pictures\\MeloBot.jpg"));
		frmExitPage.setTitle("exit page");
		frmExitPage.setBounds(100, 100, 641, 452);
		frmExitPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmExitPage.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Do you want to exit?");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(207, 80, 231, 65);
		frmExitPage.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Yes");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmExitPage.dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(159, 167, 85, 21);
		frmExitPage.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("No");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmojiSongSearchApp eb=new EmojiSongSearchApp();
				frmExitPage.dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(354, 167, 85, 21);
		frmExitPage.getContentPane().add(btnNewButton_1);
	}
}
