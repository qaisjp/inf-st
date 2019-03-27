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
	 * UNSORTED
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
	 * 3.1. Adding an option with the same name as an existing option will override
	 * the option defined previously.
	 */
	@Test
	public void addOverrideDupe() {
		parser.add("output", "f", Parser.STRING);
		parser.add("output", "o", Parser.STRING);

		parser.parse("-o output.txt");
		assertEquals("output.txt", parser.getString("o"));
		assertEquals("", parser.getString("f"));
	}

	/**
	 * 3.2. Name and shortcut of options should only contain numbers, alphabets and
	 * underscores. Numbers cannot appear as the first character. A runtime
	 * exception is thrown otherwise.
	 */
	@Test
	public void addCheckCharsValid() {
		parser.add("out_p00t", "o", Parser.STRING);

		parser.parse("--out_p00t output.txt");
		assertEquals("output.txt", parser.getString("out_p00t"));
	}

	@Test(expected = RuntimeException.class)
	public void addCheckCharsInvalidStartNum() {
		parser.add("000000a", "o", Parser.STRING);
	}

	@Test(expected = RuntimeException.class)
	public void addCheckCharsInvalidChars() {
		parser.add("a_!!!!", "o", Parser.STRING);
	}

	/**
	 * 3.3. Option names and shortcuts are case-sensitive.
	 */
	@Test
	public void addCaseSensitive() {
		Parser parser2 = new Parser();
		Parser[] parsers = {parser, parser2};

		for (Parser p : parsers) {
			p.add("output", "o", Parser.STRING);
			p.add("oUtPuT", "O", Parser.STRING);
		}

		parser.parse("--output output.txt --oUtPuT OUTPUT.txt");
		parser2.parse("-o output.txt -O OUTPUT.txt");

		for (Parser p : parsers) {
			assertEquals("output.txt", p.getString("o"));
			assertEquals("output.txt", p.getString("output"));
			assertEquals("OUTPUT.txt", p.getString("O"));
			assertEquals("OUTPUT.txt", p.getString("oUtPuT"));
		}
	}

	/**
	 * 3.4. An option can have a shortcut that is the same as the name of another
	 * option. For example, the user can define an option whose name is “output”
	 * with a shortcut “o” and another option whose name is “o”. When assigning
	 * values to these options, “--output” and “-o” is used for the first option and
	 * “--o” is used for the second option.
	 */
	@Test
	public void addCrossNameMatch() {
		parser.add("output", "o", Parser.STRING);

		// fullname/shortcut precedence is defined by test 6.1
		// so we work around it here by having a shortcut "f"
		parser.add("o", "f", Parser.STRING);

		parser.parse("-o output.txt --o name.txt");

		assertEquals("output.txt", parser.getString("output"));
		assertEquals("name.txt", parser.getString("f"));
	}

	/**
	 * 3.5. An option can have a shortcut that is the same as the name of another
	 * option. For example, the user can define an option whose name is “output”
	 * with a shortcut “o” and another option whose name is “o”. When assigning
	 * values to these options, “--output” and “-o” is used for the first option and
	 * “--o” is used for the second option.
	 */

	// Better to use a JUnit parameterized class here,
	// but due to coursework restrictions we can't properly do this.
	public void addBoolOpts(String opts, boolean expected) {
		Parser p = new Parser();
		p.add("optimise", "O", Parser.BOOLEAN);
		p.parse(opts);
		assertEquals(expected, p.getBoolean("optimise"));
	}

	@Test
	public void addTrueShortPresent() {
		addBoolOpts("-O", true);
		addBoolOpts("--optimise", true);
	}

	@Test
	public void addTrueEqTrue() {
		addBoolOpts("-O=true", true);
		addBoolOpts("--optimise=true", true);
	}

	@Test
	public void addTrueEq1() {
		addBoolOpts("-O=1", true);
		addBoolOpts("--optimise=1", true);
	}

	@Test
	public void addTrueGarbage() {
		addBoolOpts("-O yeah", true);
		addBoolOpts("--optimise yeah", true);
		addBoolOpts("-O=totes", true);
	}

	@Test
	public void addFalseNotPresent() {
		addBoolOpts("", false);
	}

	@Test
	public void addFalseEqFalse() {
		addBoolOpts("-O false", false);
	}

	@Test
	public void addFalseEqZero() {
		addBoolOpts("--optimise=0", false);
	}

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
	 * 4.1. Same as 3.1.
	 * Adding an option with the same name as an existing option will override
	 * the option defined previously.
	 *
	 * TODO(q): this test could be improved
	 */
	@Test
	public void longaddOverrideDupe() {
		parser.add("output", Parser.INTEGER);
		parser.add("output", Parser.STRING);

		parser.parse("--output output.txt");
		assertEquals("output.txt", parser.getString("output"));
	}

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


	/*
	 * 6. Retrieve information
	 *
	 * int getInteger(String option)
	 * boolean getBoolean(String option)
	 * String getString(String option)
	 * char getChar(String option)
	 *
	 */

	/*
	 * getShort tests whether parser.getType("x") works where "x" is the shortcut
	 * setting for a specific type, and "getType" is the correct getter.
	 */
	@Test
	public void getShort() {
		parser.add("int", "i", Parser.INTEGER);
		parser.add("bool", "b", Parser.BOOLEAN);
		parser.add("str", "s", Parser.STRING);
		parser.add("char", "c", Parser.CHAR);

		parser.parse("-i 100 -b -s Hi -c q");

		assertEquals(100, parser.getInteger("i"));
		assertEquals(true, parser.getBoolean("b"));
		assertEquals("Hi", parser.getString("s"));
		assertEquals('q', parser.getChar("c"));
	}

	/*
	 * getLong tests whether parser.getType("xxx") works where "xxx" is the long
	 * setting for a specific type, and "getType" is the correct getter.
	 */
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
