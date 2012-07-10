package name.hash;

public class TweetModel {
	long id;
	String name;
	String text;
	String date;

	public TweetModel(long id, String name, String text, String date) {
		this.id = id;
		this.name = name;
		this.text = text;
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getText() {
		return text;
	}

	public String getDate() {
		return date;
	}

	public void show() {
		System.out.println(id + " : " + name + " : " + text + " : " + date);
	}

}