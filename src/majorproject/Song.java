package majorproject;

public class Song {
	private int sid;
	private String song;
	private String description;
	private String url;

	public Song() {
		
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public void setSong(String song) {
		this.song = song;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setUrl(String url) {
		this.url=url;
	}
	public Song(int sid, String song, String description, String url) {
		super();
		this.sid = sid;
		this.song = song;
		this.description = description;
		this.url=url;
	}

	public int  getId() {
		return sid;
	}

	public String getSong1() {
		return song;
	}
	public String getDescription() {
		return description;
	}
	public String getUrl() {
		return url;
	}

}
