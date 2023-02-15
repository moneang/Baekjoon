import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static long[] sgtree;

	static long search(int node, int start, int end, int left, int right) {
		if (end < left || right < start)
			return 0;
		if (start <= left && right <= end)
			return sgtree[node];
		int mid = (left + right) / 2;
		return search(node * 2, start, end, left, mid) + search(node * 2 + 1, start, end, mid + 1, right);
	}

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

	static long[] arr;
	static long[] arr2;

	static long init(int node, int left, int right) {
		if (left == right)
			return sgtree[node] = arr2[left];
		int mid = (left + right) / 2;
		return sgtree[node] = init(node * 2, left, mid) + init(node * 2 + 1, mid + 1, right);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			StringBuilder sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			sgtree = new long[(n + m) * 4];
			arr = new long[n + m + 1];
			arr2 = new long[n + m + 1];
			for (int i = 1; i <= n; i++) {
				arr[i] = m + i;
				arr2[m + i] = 1;
			}
			init(1, 1, (n + m));

			st = new StringTokenizer(br.readLine());

			for (int i = 1; i <= m; i++) {
				int n1 = Integer.parseInt(st.nextToken());
				long rr = search(1, 1, (int) arr[n1] - 1, 1, (n + m));
				update(1, (int) arr[n1], -1, 1, (n + m));
				arr[n1] = m - i + 1;
				update(1, (int) arr[n1], 1, 1, (n + m));
				sb.append(rr).append(' ');
			}
			System.out.println(sb);

		}
	}

}
