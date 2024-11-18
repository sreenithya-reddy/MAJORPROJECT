package majorproject;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Signuppage {

	private JFrame frmSignuppage;
	private JTextField txtEmail;
	private JTextField txtPassword;
	private JTextField txtAge;
	private JLabel label_1;
	private JLabel label_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signuppage window = new Signuppage();
					window.frmSignuppage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Signuppage() {
		initialize();
		frmSignuppage.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSignuppage = new JFrame();
		frmSignuppage.setTitle("SignUpPage");
		frmSignuppage.setBounds(100, 100, 798, 521);
		frmSignuppage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSignuppage.getContentPane().setLayout(null);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtEmail.setBounds(159, 46, 559, 20);
		frmSignuppage.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtPassword.setBounds(159, 87, 559, 20);
		frmSignuppage.getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		txtAge = new JTextField();
		txtAge.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtAge.setBounds(159, 138, 559, 20);
		frmSignuppage.getContentPane().add(txtAge);
		txtAge.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Gender :");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel.setBounds(54, 187, 64, 14);
		frmSignuppage.getContentPane().add(lblNewLabel);
		
		JRadioButton rdbtnMale = new JRadioButton("Male ");
		rdbtnMale.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		rdbtnMale.setBounds(174, 183, 57, 23);
		frmSignuppage.getContentPane().add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		rdbtnFemale.setBounds(288, 183, 109, 23);
		frmSignuppage.getContentPane().add(rdbtnFemale);
		ButtonGroup genderGroup = new ButtonGroup();
	    genderGroup.add(rdbtnMale);
	    genderGroup.add(rdbtnFemale);

		
		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sr", "root", "");
                    String email = txtEmail.getText();
                    String password = txtPassword.getText();
                    String age = txtAge.getText();
                    String gender = rdbtnMale.isSelected() ? "Male" : "Female"; // Check the selected gender
                    if (email.isEmpty() || password.isEmpty() || age.isEmpty() || gender.isEmpty()) {
		                JOptionPane.showMessageDialog(null, "Please fill in all fields.");
		                }
                    else {

                    String sql = "INSERT INTO Signuppage (email,password,age,gender) VALUES (?,?,?,?)";
                    PreparedStatement stmt = con.prepareStatement(sql);
                    stmt.setString(1, email);
                    stmt.setString(2, password);
                    stmt.setString(3, age);
                    stmt.setString(4, gender);
                  

                    // Execute the prepared statement
                    int rowsInserted = stmt.executeUpdate();
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(null, "Signup successful!");
                        SwingUtilities.invokeLater(() -> {
                        LoginPage1 searchScreen = new LoginPage1();
                        frmSignuppage.dispose();
                        });
                    } else {
                        JOptionPane.showMessageDialog(null, "Signup failed.");
                    }
                    // Close the prepared statement and the connection
                    stmt.close();
                    con.close();
                } 
				}
				catch (Exception e1) {
                    e1.printStackTrace();
                }
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton.setBounds(199, 258, 293, 23);
		frmSignuppage.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Already have an account ?");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(199, 331, 171, 14);
		frmSignuppage.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Log In");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage1 lp=new LoginPage1();
				frmSignuppage.dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton_1.setBounds(380, 327, 89, 23);
		frmSignuppage.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("Email :");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(42, 50, 46, 14);
		frmSignuppage.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Password :");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(42, 91, 76, 14);
		frmSignuppage.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Age :");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(42, 142, 46, 14);
		frmSignuppage.getContentPane().add(lblNewLabel_4);
		
	}
}
