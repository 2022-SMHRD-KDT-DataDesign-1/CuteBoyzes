package project;

public class MovieDTO {
	
	private String dif;
	private String title;
	private String path;
	
	public MovieDTO(String dif, String title, String path) {
		//난이도를 나타냄
		this.dif = dif;
		// 제목
		this.title = title;
		// 경로 
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
