import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import st.Parser;

public class Task1_Functional {
	private Parser parser;

	@Before
	public void setup() {
		parser = new Parser();
	}

	@Test
	public void example() {
		parser.add("output", "o", Parser.STRING);
		parser.parse("--output=output.txt");
		assertEquals(parser.getString("o"), "output.txt");
	}
}
