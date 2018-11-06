package Ch8_Java_Basic;

import java.util.List;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;


public class Generic {
	private List<Author> authors;
	
	private class Author{
		private final String name;
		private Author(final String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
		
		//Before
		public void createAuthors() {
			authors = new ArrayList();
		
			authors.add(new Author("king"));
			authors.add(new Author("hyuk"));
			authors.add(new Author("been"));
		}
		
		//Test
		public void authorListAccess() {
			final Author author = authors.get(2);
			assertEquals("king", author.getName());
		}
	}

	public static void main(String[] args) {
		
	}
}
