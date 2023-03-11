/*
 * Name:
 * Student ID #:
 */

/*
 * Do NOT import any additional packages/classes.
 * If you (un)intentionally use some additional packages/classes we did not
 * provide, you may receive a 0 for the homework.
 */

public class Sorter implements ISorter {
	public Sorter() { ; }

	@Override
	public int[] ascending(int[] a) {
		mergeSort(a, 0, a.length - 1, 1);
//		quickSort(a, 0, a.length -1, 1);
//		insertionSort(a, 1);
//		selectionSort(a, 1);
//		bubbleSort(a, 1);
		return a;
	}

	@Override
	public int[] descending(int[] a) {
//		mergeSort(a, 0, a.length - 1, -1);
		quickSort(a, 0, a.length -1, -1);
//		insertionSort(a, -1);
//		selectionSort(a, -1);
//		bubbleSort(a, -1);
		return a;
	}

	private void mergeSort(int[] a, int s, int e, int asc) {
		if (s >= e) return;
		int m = (s + e) / 2;
		mergeSort(a, s, m, asc);
		mergeSort(a, m + 1, e, asc);
		merge(a, s, m, e, asc);
	}

	private void merge(int[] a, int s, int m, int e, int asc) {
		int[] merged = new int[e - s + 1];
		int l_idx = s;
		int r_idx = m + 1;
		int m_idx = 0;
		while(m_idx < merged.length) {
			if (l_idx > m) merged[m_idx++] = a[r_idx++];
			else if (r_idx > e) merged[m_idx++] = a[l_idx++];
			else if ((a[r_idx] - a[l_idx]) * asc > 0) merged[m_idx++] = a[l_idx++];
			else merged[m_idx++] = a[r_idx++];
		}

		for (int i = s; i <= e; i++) {
			a[i] = merged[i - s];
		}
	}

	private void quickSort(int[] a, int s, int e, int asc) {
		if (s >= e) return;
		int pivot = partition(a, s, e, asc);
		quickSort(a, s, pivot - 1, asc);
		quickSort(a, pivot + 1, e, asc);
	}

	private int partition(int[] a, int s, int e, int asc) {
		int pivot = a[(s + e) / 2];
		while(s < e) {
			while ((pivot - a[s]) * asc > 0 && s < e) s++;
			while ((pivot - a[e]) * asc <= 0 && s < e) e--;
			swap(a, s, e);
		}
		return s;
	}

	private void insertionSort(int[] a, int asc) {
		for (int i = 1; i < a.length; i++) {
			int target = a[i];
			int idx = i;
			while(idx > 0 && (a[idx - 1] - target) * asc > 0) {
				a[idx] = a[idx - 1];
				idx--;
			}
			a[idx] = target;
		}
	}

	private void selectionSort(int[] a, int asc) {
		for (int i = 0; i < a.length; i++) {
			int idx = i;
			for (int j = i + 1; j < a.length; j++) {
				if ((a[idx] - a[j]) * asc > 0) idx = j;
			}
			swap(a, i, idx);
		}
	}

	private void bubbleSort(int[] a, int asc) {
		for (int i = a.length - 1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				if ((a[j] - a[j+1]) * asc > 0) {
					swap(a, j, j+1);
				}
			}
		}
	}

	private void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
