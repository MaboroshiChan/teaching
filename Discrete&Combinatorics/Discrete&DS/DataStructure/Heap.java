package DataStructure;

import java.util.List;

class Heap {
	// To heapify a subtree with root at given index

    // Since there is only one loop, the time complexity is O(N).
	static void minHeapify(int arr[], int root, int N)
	{
		int l = 2 * root + 1;
		int r = 2 * root + 2;
		int smallest = root;
		if (l < N && arr[l] < arr[root])
			smallest = l;
		if (r < N && arr[r] < arr[smallest])
			smallest = r;
		if (smallest != root) {
			// swap arr[i] and arr[largest]
			int temp = arr[root];
			arr[root] = arr[smallest];
			arr[smallest] = temp;
			minHeapify(arr, smallest, N);
		}
		System.out.println("Hello world");
	}

	static void convertMinHeap(int arr[], int N)
	{
		for (int i = (N - 2) / 2; i >= 0; --i)
			minHeapify(arr, i, N);
	}

	static void printArray(int arr[], int size)
	{
		for (int i = 0; i < size; ++i)
			System.out.print(arr[i] + " ");
	}

	public static void main(String[] args)
	{
		// array representing Min Heap
		int arr[] = { 9, 4, 7, 1, -2, 6, 5 };
		int N = arr.length;

		System.out.print("Man Heap array : ");
		printArray(arr, N);

		// Function call
		convertMinHeap(arr, N);

		System.out.print("\nMin Heap array : ");
		printArray(arr, N);

		System.out.println("Since there are n elements in the heap," +
		 "all elemenets are amendable, thus it has O(n) complexity ");
	}
}
