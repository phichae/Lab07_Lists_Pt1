
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

/**
 * Test Sort class using WrappedDLL class.
 * @author mvail
 */
public class SortTester {
	// initialized in constructor
	private final boolean PRINT_ALL_TESTS;
	private final boolean PRINT_FAILS_ONLY;
	private final boolean PRINT_SECTION_SUMMARIES;

	// named elements for use in tests
	private static final Integer ELEMENT_A = new Integer(1);
	private static final Integer ELEMENT_B = new Integer(2);
	private static final Integer ELEMENT_C = new Integer(3);
	private static final Integer ELEMENT_D = new Integer(4);
	
	private static final int BIG_SORT_SIZE = 20000;

	private int passes = 0;
	private int failures = 0;
	private int total = 0;

	private int secTotal = 0;
	private int secPasses = 0;
	private int secFails = 0;

	/** @param args optional parameters <-a|-f> <-s> */
	public static void main(String[] args) {
		SortTester tester = new SortTester(args);
		tester.runTests();
	}

	/** configure output based on command line args 
	 * 	print all tests: -a
	 *  print failed tests only: -f (-f overrules -a and filters all passed tests)
	 *  print section summaries: -s (can be combined with -a or -f)
	 *  print only final summary: no args
	 */
	public SortTester(String[] args) {
		if (args.length == 0) {
			PRINT_ALL_TESTS = false;
			PRINT_FAILS_ONLY = true;
			PRINT_SECTION_SUMMARIES = false;
		} 
		else {
			boolean found = false;
			for (int i = 0; i < args.length; i++) {
				if (args[i].equals("-a")) found = true;
			}
			if (found) {
				PRINT_ALL_TESTS = true;
			} else {
				PRINT_ALL_TESTS = false;
			}
			found = false;
			for (int i = 0; i < args.length; i++) {
				if (args[i].equals("-f")) found = true;
			}
			if (found) {
				PRINT_FAILS_ONLY = true;
			} else {
				PRINT_FAILS_ONLY = false;
			}
			found = false;
			for (int i = 0; i < args.length; i++) {
				if (args[i].equals("-s")) found = true;
			}
			if (found) {
				PRINT_SECTION_SUMMARIES = true;
			} else {
				PRINT_SECTION_SUMMARIES = false;
			}			
		}
	}

	///////////////////////////////////////
	// XXX LIST BUILDERS
	///////////////////////////////////////
	
	/**
	 * Returns chosen List so the implementation can be changed
	 * in this one location rather than in every test.
	 * @return [ ]
	 */
	private static IndexedUnsortedList<Integer> newList() {	//TODO: specify the underlying list class, here
		return new WrappedDLL<Integer>();
	}
	
	/** @return [A] */
	private static IndexedUnsortedList<Integer> listA() {
		Integer[] elements = {ELEMENT_A};
		return newListWithElements(elements);
	}
	
	/** @return [A,B] */
	private static IndexedUnsortedList<Integer> listAB() {
		Integer[] elements = {ELEMENT_A, ELEMENT_B};
		return newListWithElements(elements);
	}

	/** @return [B,A] */
	private static IndexedUnsortedList<Integer> listBA() {
		Integer[] elements = {ELEMENT_B, ELEMENT_A};
		return newListWithElements(elements);
	}
	
	/** @return [A,A] */
	private static IndexedUnsortedList<Integer> listAA() {
		Integer[] elements = {ELEMENT_A, ELEMENT_A};
		return newListWithElements(elements);
	}

	/** @return [A,B,C] */
	private static IndexedUnsortedList<Integer> listABC() {
		Integer[] elements = {ELEMENT_A, ELEMENT_B, ELEMENT_C};
		return newListWithElements(elements);
	}

	/** @return [A,C,B] */
	private static IndexedUnsortedList<Integer> listACB() {
		Integer[] elements = {ELEMENT_A, ELEMENT_C, ELEMENT_B};
		return newListWithElements(elements);
	}

	/** @return [B,A,C] */
	private static IndexedUnsortedList<Integer> listBAC() {
		Integer[] elements = {ELEMENT_B, ELEMENT_A, ELEMENT_C};
		return newListWithElements(elements);
	}
	
	/** @return [B,C,A] */
	private static IndexedUnsortedList<Integer> listBCA() {
		Integer[] elements = {ELEMENT_B, ELEMENT_C, ELEMENT_A};
		return newListWithElements(elements);
	}
	
	/** @return [C,A,B] */
	private static IndexedUnsortedList<Integer> listCAB() {
		Integer[] elements = {ELEMENT_C, ELEMENT_A, ELEMENT_B};
		return newListWithElements(elements);
	}

	/** @return [C,B,A] */
	private static IndexedUnsortedList<Integer> listCBA() {
		Integer[] elements = {ELEMENT_C, ELEMENT_B, ELEMENT_A};
		return newListWithElements(elements);
	}

	/** @return [A,A,B] */
	private static IndexedUnsortedList<Integer> listAAB() {
		Integer[] elements = {ELEMENT_A, ELEMENT_A, ELEMENT_B};
		return newListWithElements(elements);
	}

	/** @return [A,B,A] */
	private static IndexedUnsortedList<Integer> listABA() {
		Integer[] elements = {ELEMENT_A, ELEMENT_B, ELEMENT_A};
		return newListWithElements(elements);
	}

	/** @return [B,A,A] */
	private static IndexedUnsortedList<Integer> listBAA() {
		Integer[] elements = {ELEMENT_B, ELEMENT_A, ELEMENT_A};
		return newListWithElements(elements);
	}

	/** @return [A,B,B] */
	private static IndexedUnsortedList<Integer> listABB() {
		Integer[] elements = {ELEMENT_A, ELEMENT_B, ELEMENT_B};
		return newListWithElements(elements);
	}

	/** @return [B,A,B] */
	private static IndexedUnsortedList<Integer> listBAB() {
		Integer[] elements = {ELEMENT_B, ELEMENT_A, ELEMENT_B};
		return newListWithElements(elements);
	}

	/** @return [B,B,A] */
	private static IndexedUnsortedList<Integer> listBBA() {
		Integer[] elements = {ELEMENT_B, ELEMENT_B, ELEMENT_A};
		return newListWithElements(elements);
	}
	
	/** @return [A,B,C,D] */
	private static IndexedUnsortedList<Integer> listABCD() {
		Integer[] elements = {ELEMENT_A, ELEMENT_B, ELEMENT_C, ELEMENT_D};
		return newListWithElements(elements);
	}

	/** @return [B,D,A,C] */
	private static IndexedUnsortedList<Integer> listBDAC() {
		Integer[] elements = {ELEMENT_B, ELEMENT_D, ELEMENT_A, ELEMENT_C};
		return newListWithElements(elements);
	}
	
	/** @return [C,A,D,B] */
	private static IndexedUnsortedList<Integer> listCADB() {
		Integer[] elements = {ELEMENT_C, ELEMENT_A, ELEMENT_D, ELEMENT_B};
		return newListWithElements(elements);
	}

	/** @return [D,C,B,A] */
	private static IndexedUnsortedList<Integer> listDCBA() {
		Integer[] elements = {ELEMENT_D, ELEMENT_C, ELEMENT_B, ELEMENT_A};
		return newListWithElements(elements);
	}

	/** @return [A,B,C,C] */
	private static IndexedUnsortedList<Integer> listABCC() {
		Integer[] elements = {ELEMENT_A, ELEMENT_B, ELEMENT_C, ELEMENT_C};
		return newListWithElements(elements);
	}

	/** @return [C,A,C,B] */
	private static IndexedUnsortedList<Integer> listCACB() {
		Integer[] elements = {ELEMENT_C, ELEMENT_A, ELEMENT_C, ELEMENT_B};
		return newListWithElements(elements);
	}

	/** @return [C,C,B,A] */
	private static IndexedUnsortedList<Integer> listCCBA() {
		Integer[] elements = {ELEMENT_C, ELEMENT_C, ELEMENT_B, ELEMENT_A};
		return newListWithElements(elements);
	}

	/////////////////////////////////
	// XXX RUN TESTS
	/////////////////////////////////
	
	/**
	 * Run tests to confirm required functionality from list constructors and methods
	 */
	private void runTests() {
		//		EMPTY LIST []
		printTest("testSort_newList", testSort(newList(), newList()));
		printTest("testSortComparator_newList", testSort(newList(), newList(), new ReverseComparator<Integer>()));

		if (PRINT_SECTION_SUMMARIES) printSectionSummary("Empty List");

		//		ONE ELEMENT LIST [A]
		printTest("testSort_A", testSort(listA(), listA()));
		printTest("testSortComparator_A", testSort(listA(), listA(), new ReverseComparator<Integer>()));
		
		//		TWO ELEMENT LIST [A, B]
		printTest("testSort_AB", testSort(listAB(), listAB()));
		printTest("testSortComparator_AB", testSort(listAB(), listBA(), new ReverseComparator<Integer>()));
		
		//		TWO ELEMENT LIST [B, A]
		printTest("testSort_BA", testSort(listBA(), listAB()));
		printTest("testSortComparator_BA", testSort(listBA(), listBA(), new ReverseComparator<Integer>()));

		//		TWO ELEMENT LIST WITH DUPLICATE [A, A]
		printTest("testSort_AA", testSort(listAA(), listAA()));
		printTest("testSortComparator_AA", testSort(listAA(), listAA(), new ReverseComparator<Integer>()));

		//		THREE ELEMENT LIST [A, B, C]
		printTest("testSort_ABC", testSort(listABC(), listABC()));
		printTest("testSortComparator_ABC", testSort(listABC(), listCBA(), new ReverseComparator<Integer>()));

		//		THREE ELEMENT LIST [A, C, B]
		printTest("testSort_ACB", testSort(listACB(), listABC()));
		printTest("testSortComparator_ACB", testSort(listACB(), listCBA(), new ReverseComparator<Integer>()));

		//		THREE ELEMENT LIST [B, A, C]
		printTest("testSort_BAC", testSort(listBAC(), listABC()));
		printTest("testSortComparator_BAC", testSort(listBAC(), listCBA(), new ReverseComparator<Integer>()));
	
		//		THREE ELEMENT LIST [B, C, A]
		printTest("testSort_BCA", testSort(listBCA(), listABC()));
		printTest("testSortComparator_BCA", testSort(listBCA(), listCBA(), new ReverseComparator<Integer>()));
	
		//		THREE ELEMENT LIST [C, A, B]
		printTest("testSort_CAB", testSort(listCAB(), listABC()));
		printTest("testSortComparator_CAB", testSort(listCAB(), listCBA(), new ReverseComparator<Integer>()));

		//		THREE ELEMENT LIST [C, B, A]
		printTest("testSort_CBA", testSort(listCBA(), listABC()));
		printTest("testSortComparator_CBA", testSort(listCBA(), listCBA(), new ReverseComparator<Integer>()));
	
		//		THREE ELEMENT LIST WITH DUPLICATE [A, A, B]
		printTest("testSort_AAB", testSort(listAAB(), listAAB()));
		printTest("testSortComparator_AAB", testSort(listAAB(), listBAA(), new ReverseComparator<Integer>()));

		//		THREE ELEMENT LIST WITH DUPLICATE [A, B, A]
		printTest("testSort_ABA", testSort(listABA(), listAAB()));
		printTest("testSortComparator_ABA", testSort(listABA(), listBAA(), new ReverseComparator<Integer>()));

		//		THREE ELEMENT LIST WITH DUPLICATE [B, A, A]
		printTest("testSort_BAA", testSort(listBAA(), listAAB()));
		printTest("testSortComparator_BAA", testSort(listBAA(), listBAA(), new ReverseComparator<Integer>()));
	
		//		THREE ELEMENT LIST WITH DUPLICATE [B, A, B]
		printTest("testSort_BAB", testSort(listBAB(), listABB()));
		printTest("testSortComparator_BAB", testSort(listBAB(), listBBA(), new ReverseComparator<Integer>()));

		//		FOUR ELEMENT LIST [B, D, A, C]
		printTest("testSort_BDAC", testSort(listBDAC(), listABCD()));
		printTest("testSortComparator_BDAC", testSort(listBDAC(), listDCBA(), new ReverseComparator<Integer>()));

		//		FOUR ELEMENT LIST [C, A, D, B]
		printTest("testSort_CADB", testSort(listCADB(), listABCD()));
		printTest("testSortComparator_CADB", testSort(listCADB(), listDCBA(), new ReverseComparator<Integer>()));
		
		//		FOUR ELEMENT LIST WITH DUPLICATE [C, A, C, B]
		printTest("testSort_CACB", testSort(listCACB(), listABCC()));
		printTest("testSortComparator_CACB", testSort(listCACB(), listCCBA(), new ReverseComparator<Integer>()));
		
		// FINAL RESULTS (in case big list times out)
		printFinalSummary();
		System.out.println("(untimed tests, in case remaining tests time out)");
		
		//		BIG LIST
		printTest("testTimeToSortBigList", testTimeToSortBigList());
		printTest("testTimeToSortComparatorBigList", testTimeToSortComparatorBigList());
		
		// FINAL FINAL RESULTS
		printFinalSummary();
	}

	////////////////////////
	// XXX BIG LIST
	////////////////////////
	
	/** @return true if test passes, else false */
	private boolean testTimeToSortBigList() {
		final int bigNum = BIG_SORT_SIZE; //okay, not THAT big
		final double maxTime = 0.02;
		final double targetTime = 0.01;
		try {
			IndexedUnsortedList<Integer> list1 = newList();
			Random rand = new Random(123);
			for (int i = 0; i < bigNum; i++) {
				list1.add(new Integer(rand.nextInt()));
			}

			long startTime = System.nanoTime();
			Sort.sort(list1);
			long endTime = System.nanoTime();
			long totalTime = endTime - startTime;
			double seconds = (double)totalTime/10e9;
			System.out.printf("\nTime to sort %d random integers: %.3f seconds\n", bigNum, seconds);
			System.out.printf("Target time < %.3f seconds. Time > %.3f suggests O(n^2) runtime.\n", targetTime, maxTime);

			return (seconds < maxTime);
		} catch (Exception e) {
			System.out.printf("caught unexpected %s\n", e.toString());
			return false;
		}
	}	

	/** @return true if test passes, else false */
	private boolean testTimeToSortComparatorBigList() {
		final int bigNum = BIG_SORT_SIZE; //okay, not THAT big
		final double maxTime = 0.02;
		final double targetTime = 0.01;
		try {
			IndexedUnsortedList<Integer> list1 = newList();
			Random rand = new Random(123);
			for (int i = 0; i < bigNum; i++) {
				list1.add(new Integer(rand.nextInt()));
			}

			long startTime = System.nanoTime();
			Sort.sort(list1, new ReverseComparator<Integer>());
			long endTime = System.nanoTime();
			long totalTime = endTime - startTime;
			double seconds = (double)totalTime/10e9;
			System.out.printf("\nTime to sort %d random integers: %.3f seconds\n", bigNum, seconds);
			System.out.printf("Target time < %.3f seconds. Time > %.3f suggests O(n^2) runtime.\n", targetTime, maxTime);

			return (seconds < maxTime);
		} catch (Exception e) {
			System.out.printf("caught unexpected %s\n", e.toString());
			return false;
		}
	}	
	
	/////////////
	// Tests
	/////////////
	
	/**
	 * Sorts listToSort and compares the result to sortedList.
	 * @param listToSort
	 * @param sortedList
	 * @return true if listToSort and sortedList are the same after sorting listToSort
	 */
	private static boolean testSort(IndexedUnsortedList<Integer> listToSort, IndexedUnsortedList<Integer> sortedList) {
		try {
			Sort.sort(listToSort);
			if (!equivalentLists(listToSort, sortedList)) {
				printLists(listToSort, sortedList);
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			System.out.println("***Unexpected exception***");
			e.printStackTrace(System.out);
			return false;
		}
	}
	
	/**
	 * Sorts listToSort and compares the result to sortedList.
	 * @param listToSort
	 * @param sortedList
	 * @param c a Comparator to use in sorting
	 * @return true if listToSort and sortedList are the same after sorting listToSort
	 */
	private static boolean testSort(IndexedUnsortedList<Integer> listToSort, IndexedUnsortedList<Integer> sortedList, Comparator<Integer> c) {
		try {
			Sort.sort(listToSort, c);
			if (!equivalentLists(listToSort, sortedList)) {
				printLists(listToSort, sortedList);
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			System.out.println("***Unexpected exception***");
			e.printStackTrace(System.out);
			return false;
		}
	}
		
	///////////////////////////////////////////////////////
	// XXX OTHER UTILITY METHODS THE TESTER NEEDS
	///////////////////////////////////////////////////////

	/**
	 * @param elements array containing elements that should be in the list
	 * @return the list
	 */
	private static IndexedUnsortedList<Integer> newListWithElements(Integer[] elements) {
		IndexedUnsortedList<Integer> list = newList();
		for (Integer element : elements) {
			list.add(element);
		}
		return list;
	}

	/**
	 * @param list1 first list
	 * @param list2 second list
	 * @return true if they are the same size and contain all of the same elements in the same order, else false
	 */
	private static <T> boolean equivalentLists(IndexedUnsortedList<T> list1, IndexedUnsortedList<T> list2) {
		if (list1.size() != list2.size()) return false;

		Iterator<T> it1 = list1.iterator();
		Iterator<T> it2 = list2.iterator();

		for (int i = 0; i < list1.size(); i++) {
			T t1 = it1.next();
			T t2 = it2.next();

			if (!t1.equals(t2)) return false;
		}
		return true;
	}

	/**
	 * Prints out contents of two lists, labels list1 "current list"  and list2 "expected list" 
	 * @param list1 - first list printed
	 * @param list2 - second list printed 
	 */
	private static void printLists(IndexedUnsortedList<Integer> list1, IndexedUnsortedList<Integer> list2) {
		System.out.println("\tcurrent list:");
		System.out.print("\t[");
		for(Integer i : list1) {
			System.out.print(i + ",");
		}
		System.out.println("]");
		System.out.println("\texpected list:");
		System.out.print("\t[");
		for(Integer i : list2) {
			System.out.print(i + ",");
		}
		System.out.println("]");
	}

	/** Print test results in a consistent format
	 * @param testDesc description of the test
	 * @param result indicates if the test passed or failed
	 */
	private void printTest(String testDesc, boolean result) {
		total++;
		if (result) {
			passes++;
		} else {
			failures++;
		}
		if (PRINT_ALL_TESTS || PRINT_FAILS_ONLY) {
			if (!PRINT_FAILS_ONLY || !result) {
				System.out.printf("%-48s\t%s\n", testDesc, (result ? "   PASS" : "***FAIL***"));
			}
		}
	}

	/** Print a final summary */
	private void printFinalSummary() {
		System.out.printf("\nTotal Tests: %d,  Passed: %d (%.1f%%),  Failed: %d\n", total, passes, 100.0*passes/total, failures);
	}

	/** Print a section summary */
	private void printSectionSummary(String secLabel) {
		secTotal = total - secTotal;
		secPasses = passes - secPasses;
		secFails = failures - secFails;
		System.out.printf("\n%s Tests: %d,  Passed: %d,  Failed: %d\n\n", secLabel, secTotal, secPasses, secFails);
		secTotal = total; //reset for next section
		secPasses = passes;
		secFails = failures;		
	}

	///////////////////////////////////////////
	// XXX COMPARATOR FOR ALTERNATE ORDER	//
	///////////////////////////////////////////

	/**
	 * Reverse the natural ordering dictated by elements' compareTo() method.
	 * 
	 * @param <T extends Comparable<T>> type of elements being compared 
	 */
	private class ReverseComparator<T extends Comparable<T>> implements Comparator<T> {
		@Override
		public int compare(T o1, T o2) {
			return -(o1.compareTo(o2));
		}
	}
}
