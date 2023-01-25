package project;

public class MovieDTO {
	
	private String dif;
	private String title;
	private String path;
	
	public MovieDTO(String dif, String title, String path) {
		this.dif = dif;
		this.title = title;
		this.path = path;
	}
	public String getDif() {
		return dif;
	}
	public void setDif(String dif) {
		this.dif = dif;
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
