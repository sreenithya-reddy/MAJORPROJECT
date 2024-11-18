package majorproject;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import majorproject.Emoji;
import majorproject.Song;
import majorproject.EmojiJdbcService;


public class EmojiSongSearchApp extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2221759256687997122L;
	public JFrame frame;
	private JTextField textField;
	private JTable table;
	private JButton btnSearch;
	private JScrollPane scrollPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				EmojiSongSearchApp window = new EmojiSongSearchApp();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public EmojiSongSearchApp() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		JLabel emojiLabel = new JLabel("Enter Emoji:");
		panel.add(emojiLabel,BorderLayout.CENTER);
		textField = new JTextField();
		panel.add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		JButton btnExit = new JButton("Exit");
        panel.add(btnExit, BorderLayout.SOUTH);

		
		   // Create a list of items to populate the dropdown
		   JComboBox<Emoji> comboBox = new JComboBox<>();
        EmojiJdbcService jdbcService = new EmojiJdbcService();
        List<Emoji> emojis = jdbcService.getAllEmojiList();
         for(Emoji emoji:emojis) {
        	 comboBox.addItem(emoji);
         }
        // Create a DefaultComboBoxModel and populate it with the list of items
       // DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
     

       

        // Create a JComboBox and set its model to the DefaultComboBoxModel
       // JComboBox<String> comboBox = new JComboBox<>(comboBoxModel);

        // Create a label to display the selected item
        JLabel label = new JLabel("Selected Item: ");

        // Add an ActionListener to the JComboBox to respond to item selection
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Emoji emoji = (Emoji) comboBox.getSelectedItem();
                label.setText("Selected Item: " + emoji.getEmoji());
                //label.setI
				List<Song> songs = fetchSongsByEmoji(emoji.getId());
				displaySongsInTable(songs);
            }
        });
		panel.add(label, BorderLayout.NORTH);
		panel.add(comboBox, BorderLayout.NORTH);

		table = new JTable();
		scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int choice = JOptionPane.showConfirmDialog(null,
                		"Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0); // Exit the application
                }
            }
        });

		/*btnSearch = new JButton("Search");
		panel.add(btnSearch, BorderLayout.SOUTH);
		JLabel searchLabel = new JLabel("Songs by emoji");
		panel.add(searchLabel,BorderLayout.CENTER);
		searchLabel.setVisible(false);
		table = new JTable();
		scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.CENTER);

		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emoji = textField.getText();
				List<Song> songs = fetchSongsByEmoji(emoji);
				displaySongsInTable(songs);
			}
		});*/
	}

	private List<Song> fetchSongsByEmoji(int emoji) {
		List<Song> songs = new ArrayList<>();
	
		try {
		//	String encodedEmoji = URLEncoder.encode(emoji, "UTF-8");
			
			EmojiJdbcService jdbcService = new EmojiJdbcService();
		    songs= jdbcService.getSongsList(emoji);

			}
		 catch (Exception e) {
			e.printStackTrace();
		}
		return songs;
	}

	private void displaySongsInTable(List<Song> songs) {
		String[] columnNames = { "song", "description", "url" };
		Object[][] data = new Object[songs.size()][6];
		for (int i = 0; i < songs.size(); i++) {
			//data[i][0] = songs.get(i).getId();
			data[i][0] = songs.get(i).getSong1();
			data[i][1] = songs.get(i).getDescription();
			data[i][2] = songs.get(i).getUrl();
		}
		table.setModel(new DefaultTableModel(data, columnNames));
	}


}
