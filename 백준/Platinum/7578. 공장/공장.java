import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static long[] sgtree;

	static void update(int node, int index, int left, int right) {
		if (left > index || right < index)
			return;
		sgtree[node] += 1;
		if (left != right) {
			int mid = (left + right) / 2;
			update(node * 2, index, left, mid);
			update(node * 2 + 1, index, mid + 1, right);
		}
	}

	static long search(int node, int start, int end, int left, int right) {
		if (end < left || right < start)
			return 0;
		if (start <= left && right <= end)
			return sgtree[node];
		int mid = (left + right) / 2;
		return search(node * 2, start, end, left, mid) + search(node * 2 + 1, start, end, mid + 1, right);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		HashMap<Integer, Integer> b = new HashMap<>();
		int n = Integer.parseInt(br.readLine());

		sgtree = new long[500000 * 4];
		int[] arr = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			b.put(Integer.parseInt(st.nextToken()), i);
		}
		long res = 0;
		for (int i = 1; i <= n; i++) {
			int index = b.get(arr[i]);
			long rr = search(1, index, n, 0, 500000);
			res +=  rr;
			update(1, index, 0, 500000);
		}
		System.out.println(res);

	}
}
