package project;

public class MovieDTO {
	
	private int movie_number;
	private String level;
	private String title;
	private String path;
	
	
	public MovieDTO(int movie_number, String level, String title, String path) {
		this.movie_number = movie_number;
		this.level = level;
		this.title = title;
		this.path = path;
		
	}
	public int getMovie_number() {
		return movie_number;
	}
	public void setMovie_number(int movie_number) {
		this.movie_number = movie_number;
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
