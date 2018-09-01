package Ch4_FundamentalAlgorithm;

import java.util.*;

public class InsertionSort {

	public static List<Integer> insertSort(final List<Integer> numbers) {
		final List<Integer> sortedList = new LinkedList<>();

		originalList: for (Integer number : numbers) {
			for (int i = 0; i < sortedList.size(); i++) {
				if (number < sortedList.get(i)) {
					sortedList.add(i, number);
					continue originalList;
				}
			}
			sortedList.add(sortedList.size(), number);
		}
		return sortedList;
	}
}
