package dto;

public class Email {
	private String to;
	private String subject;
	private String content;
	private String msg;

	public Email(String to, String subject, String content, String msg) {
		super();
		this.to = to;
		this.subject = subject;
		this.content = content;
		this.msg = msg;
	}

	public Email() {
		super();
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
