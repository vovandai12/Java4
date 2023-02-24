package dto;

import java.util.Date;

public class ShareUser {
	private String username;
	private String fullname;
	private String videoId;
	private String title;
	private String emailTo;
	private Date shareDate;

	public ShareUser(String username, String fullname, String videoId, String title, String emailTo, Date shareDate) {
		super();
		this.username = username;
		this.fullname = fullname;
		this.videoId = videoId;
		this.title = title;
		this.emailTo = emailTo;
		this.shareDate = shareDate;
	}

	public ShareUser() {
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

	public String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public Date getShareDate() {
		return shareDate;
	}

	public void setShareDate(Date shareDate) {
		this.shareDate = shareDate;
	}

}
