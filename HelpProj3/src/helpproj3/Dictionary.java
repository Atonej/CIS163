package helpproj3;

public class Dictionary extends Book {
	protected String type;   // French, English, etc.

	public Dictionary(int pages, String name, String type) {
		super(pages, name);
		this.type = type;
	}

	public String getType() {
		return type;
	}
}