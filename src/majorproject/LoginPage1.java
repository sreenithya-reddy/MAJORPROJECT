package majorproject;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Desktop;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; // Make sure to import this
import java.net.URI;
import java.net.URISyntaxException; // Also import this for URI handling
import java.io.IOException; // Import IOException if you are handling it
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;
import java.net.URI; 

public class LoginPage1 {

    private JFrame frmLogin;
    private JTextField textField;
    private JTextField textField_1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginPage1 window = new LoginPage1();
                window.frmLogin.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LoginPage1() {
        initialize();
        frmLogin.setVisible(true);
    }

    private void initialize() {
        frmLogin = new JFrame();
        frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\DC\\OneDrive\\Pictures\\Saved Pictures\\MeloBot.jpg"));
        frmLogin.getContentPane().setBackground(new Color(240, 240, 240));
        frmLogin.setTitle("Login");
        frmLogin.setBounds(100, 100, 785, 504);
        frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmLogin.getContentPane().setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("Email :");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(54, 39, 168, 13);
        frmLogin.getContentPane().add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Password:");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        lblNewLabel_2.setBounds(55, 90, 109, 13);
        frmLogin.getContentPane().add(lblNewLabel_2);
        
        textField = new JTextField();
        textField.setBounds(186, 36, 394, 19);
        frmLogin.getContentPane().add(textField);
        textField.setColumns(10);
        
        textField_1 = new JTextField();
        textField_1.setBounds(186, 87, 394, 19);
        frmLogin.getContentPane().add(textField_1);
        textField_1.setColumns(10);
        
        JButton btnNewButton_1 = new JButton("LOG IN");
        btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sr", "root", "");
					String uname = textField.getText();
			        String passwordChars = textField_1.getText();
					String qry = "SELECT * FROM Signuppage WHERE email = ? AND password = ?";
					PreparedStatement statement = con.prepareStatement(qry);
					statement.setString(1,uname);
					statement.setString(2, passwordChars);
					ResultSet rs = statement.executeQuery();
					if(rs.next()) {
						EmojiSongSearchApp ep=new EmojiSongSearchApp();
						frmLogin.dispose();
						JOptionPane.showMessageDialog(btnNewButton_1, "Login succesfull");
						  EventQueue.invokeLater(() -> {
					            try {
					                EmojiSongSearchApp window = new EmojiSongSearchApp();
					                window.frame.setVisible(true);
					            } catch (Exception ex) {
					                ex.printStackTrace();
					            }
					        });
						} else {
							JOptionPane.showMessageDialog(btnNewButton_1, "Login failed");
						}
				}
				catch(Exception ex) {ex.printStackTrace(); 
				}
				}
			
		});
		btnNewButton_1.setBounds(228, 144, 224, 21);
		frmLogin.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("CONTINUE WITH FACEBOOK");
		btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String loginDialogURL = "https://www.facebook.com";
				try {
		            Desktop.getDesktop().browse(new URI(loginDialogURL));
		        } 
		        catch (IOException | URISyntaxException ex) {
		            ex.printStackTrace();
		        }
			}
		});
		btnNewButton_2.setBounds(120, 202, 460, 21);
		frmLogin.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("CONTINUE WITH GOOGLE");
		btnNewButton_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String authUri = "https://accounts.google.com";
				 try {
					 Desktop.getDesktop().browse(new URI(authUri));
				}
				 catch (IOException | URISyntaxException ex) {
					 ex.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(120, 269, 460, 21);
		frmLogin.getContentPane().add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("Don't have an account?");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel.setBounds(202, 320, 168, 13);
		frmLogin.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_4 = new JButton("SIGN UP");
		btnNewButton_4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Signuppage sp=new Signuppage();
				frmLogin.dispose();
			}
		});
		btnNewButton_4.setBounds(367, 316, 109, 21);
		frmLogin.getContentPane().add(btnNewButton_4);
	}
}