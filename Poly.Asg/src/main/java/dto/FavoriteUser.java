package dto;

import java.util.Date;

public class FavoriteUser {
	private String username;
	private String fullname;
	private String videoId;
	private String title;
	private Date likeDate;

	public FavoriteUser(String username, String fullname, String videoId, String title, Date likeDate) {
		super();
		this.username = username;
		this.fullname = fullname;
		this.videoId = videoId;
		this.title = title;
		this.likeDate = likeDate;
	}

	public FavoriteUser() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getLikeDate() {
		return likeDate;
	}

	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
	}

}
