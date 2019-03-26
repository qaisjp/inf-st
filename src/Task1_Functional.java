import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import st.Parser;

public class Task1_Functional {
	private Parser parser;

	/**
	 * 2. Parser Initialisation
	 */
	@Before
	public void setup() {
		parser = new Parser();
	}

	/**
	 * 3. Add options with a shortcut
	 *
	 * void add(String option_name, String shortcut, int value_type)
	 *
	 * Example:
	 * - parser.add("output" , "o" , Parser.STRING);
	 * - parser.add("optimise", "O" , Parser.BOOLEAN);
	 *
	 */

	/**
	 * 4. Add options without a shortcut
	 *
	 * void add(String option_name, int value_type)
	 *
	 * Example:
	 * - parser.add( "output" , Parser.STRING);
	 * - parser.add( "optimise" , Parser.BOOLEAN);
	 *
	 */

	/**
	 * 5. Parse command line options
	 *
	 * int parse(String command_line_options)
	 *
	 * Example:
	 * - parser.parse( "--input 1.txt --output=2.txt" );
	 * - parser.parse( "-O" );
	 *
	 */
	@Test
	public void shortStrEq() {
		// Short, equals separated
		parser.add("output", "o", Parser.STRING);
		parser.parse("-o=output.txt");
		assertEquals("output.txt", parser.getString("o"));
	}

	@Test
	public void shortStrSp() {
		// Short, space separated
		parser.add("output", "o", Parser.STRING);
		parser.parse("-o output.txt");
		assertEquals("output.txt", parser.getString("o"));
	}

	@Test
	public void longStrEq() {
		// Long, equals separated
		parser.add("output", "o", Parser.STRING);
		parser.parse("--output=output.txt");
		assertEquals("output.txt", parser.getString("o"));
	}

	@Test
	public void longStrSp() {
		// Long, space separated
		parser.add("output", "o", Parser.STRING);
		parser.parse("--output output.txt");
		assertEquals("output.txt", parser.getString("o"));
	}

	@Test
	public void getLong() {
		parser.add("int", "i", Parser.INTEGER);
		parser.add("bool", "b", Parser.BOOLEAN);
		parser.add("str", "s", Parser.STRING);
		parser.add("char", "c", Parser.CHAR);

		parser.parse("-i 100 -b -s Hi -c q");

		assertEquals(100, parser.getInteger("int"));
		assertEquals(true, parser.getBoolean("bool"));
		assertEquals("Hi", parser.getString("str"));
		assertEquals('q', parser.getChar("char"));
	}

	/*
	 * 6. Retrieve information
	 */

	/*
	 * 6.1. The order of search is full name of options first and then shortcut. For
	 * example, if “o” exists as a full name for an option and a shortcut for
	 * another option, this function returns the value of the first option.
	 */
	@Test
	public void orderSearch() {
		parser.add("output", "o", Parser.STRING);
		parser.add("o", "f", Parser.STRING);
		parser.parse("--output output.txt --o yada");
		assertEquals("yada", parser.getString("o"));
	}

	/*
	 * 6.2. If the option is not defined or not provided a value, a default value is
	 * used: 0 for INTEGER, false for BOOLEAN, an empty String “” for STRING and
	 * ‘\0’ for CHAR.
	 */
	@Test
	public void defaults() {
		parser.add("int", "i", Parser.INTEGER);
		parser.add("bool", "b", Parser.BOOLEAN);
		parser.add("str", "s", Parser.STRING);
		parser.add("char", "c", Parser.CHAR);
		parser.parse("");

		assertEquals(0, parser.getInteger("i"));
		assertEquals(false, parser.getBoolean("b"));
		assertEquals("", parser.getString("s"));
		assertEquals('\0', parser.getChar("c"));
	}
}
