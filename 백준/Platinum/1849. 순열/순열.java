import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static long[] sgtree;

	static void update(int node, int index, int val, int left, int right) {
		if (left > index || right < index)
			return;
		sgtree[node] += val;
		if (left != right) {
			int mid = (left + right) / 2;
			update(node * 2, index, val, left, mid);
			update(node * 2 + 1, index, val, mid + 1, right);
		}
	}

	static long search(int node, int left, int right, long index) {
		if (left == right)
			return left;
		long val = sgtree[node * 2];
		int mid = (left + right) / 2;
		if (val >= index)
			return search(node * 2, left, mid, index);
		else
			return search(node * 2 + 1, mid + 1, right, (index - val));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		long[] arr = new long[n + 1];
		int[] res = new int[n + 1];
		sgtree = new long[100000 * 4];
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			update(1, i, 1, 1, 100000);
		}

		for (int i = 1; i <= n; i++) {
			long rr = (search(1, 1, 100000, (arr[i] + 1)));
			res[(int) rr] = i;
			update(1, (int) rr, -1, 1, 100000);

		}
		for (int i = 1; i <= n; i++)
			sb.append(res[i]).append('\n');
		System.out.println(sb);
	}
}
