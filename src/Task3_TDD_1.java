import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import st.Parser;

public class Task3_TDD_1 {
	private Parser parser;

	@Before
	public void setup() {
		parser = new Parser();
	}

	/**
	 * UNSORTED
	 */

	@Test
	public void singular() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list {1}");

		List<Integer> l = parser.getIntegerList("list");
		assertEquals(1, (int) l.get(0));
		assertEquals(1, l.size());
	}

	@Test
	public void multidigit() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list {1337, 69}");

		List<Integer> l = parser.getIntegerList("list");
		assertEquals(l, Arrays.asList(1337, 69));

	}

	@Test
	public void empty() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list {}");

		List<Integer> l = parser.getIntegerList("list");
		assertEquals(0, l.size());
	}

	/**
	 * 2. If the option is not provided a value, an empty list is returned.
	 */
	@Test
	public void missing() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("");

		List<Integer> l = parser.getIntegerList("list");
		assertEquals(0, l.size());
	}

	/**
	 * 3. Non number characters can be used as separators, including spaces, dots,
	 * commas.
	 */
	@Test
	public void separators() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list '1,2) >4!5{_<3-6}'");

		List<Integer> l = parser.getIntegerList("list");
		assertEquals(l, Arrays.asList(1, 2, 3, 4, 4, 5, 5, 6 ));

	}

	/**
	 * 4. A hyphens (-) indicates an inclusive range of numbers. For example, 4-7
	 * and 7-4 both represents integers 4, 5, 6, 7.
	 */
	@Test
	public void range() {
		parser.add("forwards", "f", Parser.STRING);
		parser.add("backwards", "b", Parser.STRING);
		parser.parse("-f 4-7 -b 7-4");

		List<Integer> f = parser.getIntegerList("forwards");
		assertEquals(f, Arrays.asList(4, 5, 6, 7));

		List<Integer> b = parser.getIntegerList("backwards");
		assertEquals(Arrays.asList(4, 5, 6, 7), b);
	}

	/**
	 * 5. The unary prefix hyphen indicates a negative value. For example, -7 is a
	 * negative value, -7--5 includes integers -7, -6, -5 and -2-1 includes integers
	 * -2, -1, 0, 1.
	 */
	@Test
	public void negativeRange() {
		parser.add("forwards", "f", Parser.STRING);
		parser.add("backwards", "b", Parser.STRING);
		parser.parse("-f -7--5 -b -2-1");

		List<Integer> f = parser.getIntegerList("forwards");
		assertEquals(Arrays.asList(-7, -6, -5 ), f);

		List<Integer> b = parser.getIntegerList("backwards");
		assertEquals(Arrays.asList(-2, -1, 0, 1 ), b);

	}

	/**
	 * 6. Hyphens cannot be used as a suffix. 3- , for instance, is invalid and an
	 * empty list should be returned.
	 */
	@Test
	public void invalid() {
		parser.add("list", "l", Parser.STRING);
		parser.parse("--list 3-");

		List<Integer> l = parser.getIntegerList("list");
		assertEquals(0, l.size());
	}

}
