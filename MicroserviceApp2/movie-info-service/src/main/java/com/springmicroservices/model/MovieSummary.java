package com.springmicroservices.model;

public class MovieSummary {

	private String title;
	private String overview;
	
	public MovieSummary() {
		
	}

	public MovieSummary(String title, String overview) {

		this.title = title;
		this.overview = overview;
	}

	public String getOverview() {
		return overview;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	@Override
	public String toString() {
		return "MovieSummary [title=" + title + ", overview=" + overview + "]";
	}

}
