package fi.metropolia.musicrecommendations;

public class Track {
	
	private String name;
	
	public Track(String n){
		this.setName(n);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
