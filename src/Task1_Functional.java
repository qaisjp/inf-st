import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import st.Parser;

public class Task1_Functional {
	private Parser parser;

	@Before
	public void setup() {
		parser = new Parser();
	}

	@Test
	public void shortInt() {
	}

	@Test
	@Ignore
	public void longInt() {

	}

	@Test
	@Ignore
	public void shortBool() {

	}

	@Test
	@Ignore
	public void longBool() {

	}

	@Test
	public void shortStrEq() {
		// Short, equals separated
		parser.add("output", "o", Parser.STRING);
		parser.parse("-o=output.txt");
		assertEquals(parser.getString("o"), "output.txt");
	}

	@Test
	public void shortStrSp() {
		// Short, space separated
		parser.add("output", "o", Parser.STRING);
		parser.parse("-o output.txt");
		assertEquals(parser.getString("o"), "output.txt");
	}

	@Test
	public void longStrEq() {
		// Long, equals separated
		parser.add("output", "o", Parser.STRING);
		parser.parse("--output=output.txt");
		assertEquals(parser.getString("o"), "output.txt");
	}

	@Test
	public void longStrSp() {
		// Long, space separated
		parser.add("output", "o", Parser.STRING);
		parser.parse("--output output.txt");
		assertEquals(parser.getString("o"), "output.txt");
	}
}
