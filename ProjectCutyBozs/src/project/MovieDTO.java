package project;

public class MovieDTO {
	
	private String level;
	private String title;
	private String path; 
	
	
	public MovieDTO(String level, String title, String path) {
		super();
		this.level = level;
		this.title = title;
		this.path = path;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
