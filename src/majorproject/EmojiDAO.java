package majorproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmojiDAO {

    // Database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/sr";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // Method to execute a query and return a ResultSet
    public ResultSet executeQuery(String query) {
        ResultSet resultSet = null;
        try {
            // Establish connection and execute query
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(query);
            System.out.println("Executing query: " + query);
            resultSet = statement.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    // Method to retrieve all emoji data
    public List<Emoji> getAllEmojiLst() throws SQLException {
        List<Emoji> emojis = new ArrayList<>();
        String query = "SELECT emoji_id, description, emoji FROM emoji1";
        System.out.println("Executing query: " + query);

        // Execute query and process the results
        try (ResultSet resultSet = executeQuery(query)) {
            Emoji emoji = null;
            while (resultSet.next()) {
                emoji = new Emoji();
                emoji.setId(resultSet.getInt("emoji_id"));
                emoji.setDescription(resultSet.getString("description"));
                emoji.setEmoji(resultSet.getString("emoji"));
                emojis.add(emoji);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emojis;
    }

    // Method to retrieve songs associated with a specific emoji
    public List<Song> getSongsData(int emojiId) {
        List<Song> songs = new ArrayList<>();
        StringBuilder table = new StringBuilder();

        String query = "SELECT s.sid, s.song, s.description, s.url " +
                       "FROM emoji1 e " +
                       "LEFT JOIN emoji_to_song em ON e.emoji_id = em.emoji_id " +
                       "LEFT JOIN songs s ON em.emoji_id = s.sid " +
                       "WHERE e.emoji_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = conn.prepareStatement(query)) {
             
            // Set the emojiId parameter in the query
            statement.setInt(1, emojiId);
            System.out.println("Executing query: " + statement);

            ResultSet resultSet = statement.executeQuery();

            // Prepare the table structure for display
            table.append("+----------------+---------------------+--------------------------------------------------+\n");
            table.append(String.format("| %-30s | %-50s | %-100s |\n", "song", "Description", "url"));
            table.append("+----------------+---------------------+--------------------------------------------------+\n");

            // Process each song result
            while (resultSet.next()) {
                Song song = new Song();
                song.setSid(resultSet.getInt("sid"));
                song.setSong(resultSet.getString("song"));
                song.setDescription(resultSet.getString("description"));
                song.setUrl(resultSet.getString("url"));

                songs.add(song);

                // Formatting the table data
                table.append(String.format("| %-30s | %-50s | %-70s |\n", 
                              resultSet.getString("song"), 
                              resultSet.getString("description"), 
                              resultSet.getString("url")));
            }

            table.append("+----------------+---------------------+--------------------------------------------------+\n");
            System.out.println(table.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return songs;
    }
}
