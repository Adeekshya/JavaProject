public class Message{

	public String title;
	public String body;
	public int sender;
	public int recipient;

	public Message(){

	}

	public Message(String title, String body, int sender, int recipient){
		this.title = title;
		this.body = body;
		this.sender = sender;
		this.recipient = recipient;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public int getRecipient() {
		return recipient;
	}

	public void setRecipient(int recipient) {
		this.recipient = recipient;
	}
	
}