package Ch4_FundamentalAlgorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static junit.framework.Assert.fail;
import static org.junit.Assert.assertArrayEquals;


class TestJunit {

	@Test
	public void sortInts() {
		final int[] numbers = { -3, -5, 1, 7, 4, -2 };
		final int[] expected = { -5, -3, -2, 1, 4, 7 };

		Arrays.sort(numbers);
		assertArrayEquals(expected, numbers);
	}

	@Test
	public void sortObjects() {
		final String[] strings = { "z", "x", "y", "abc", "zzz", "zezzy" };
		final String[] expected = { "abc", "x", "y", "z", "zezzy", "zzz" };

		Arrays.sort(strings);
		assertArrayEquals(expected, strings);
	}

	private static class NotComparable{
		private int i;

		private NotComparable(final int i) {
			this.i = i;
		}
	}

	@Test
	public void sortNotComparable() {
		final List<NotComparable> objects = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			objects.add(new NotComparable(i));
		}

		try {
			Arrays.sort(objects.toArray());
		} catch (Exception e) {
			// correct behavior – cannot sort
			return;
		}

		fail();
	}


}

