package Ch4_FundamentalAlgorithm;

import java.util.*;

public class QuickSort {
	public static List<Integer> quickSort(List<Integer> numbers) {
		if (numbers.size() < 2)
			return numbers;

		final Integer pivot = numbers.get(0);
		final List<Integer> lower = new ArrayList<>();
		final List<Integer> higher = new ArrayList<>();

		for (int i = 1; i < numbers.size(); i++) {
			if (numbers.get(i) < pivot) {
				lower.add(numbers.get(i));
			} else {
				higher.add(numbers.get(i));
			}
		}

		final List<Integer> sorted = quickSort(lower);
		sorted.add(pivot);
		sorted.addAll(quickSort(higher));

		return sorted;
	}
}
