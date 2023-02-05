import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static long[] sgtree;

	static long init(long[] arr, int node, int left, int right) {
		if (left == right)
			return sgtree[node] = arr[left];
		int mid = (left + right) / 2;
		return sgtree[node] = init(arr, node * 2, left, mid) + init(arr, node * 2 + 1, mid + 1, right);
	}

	static long search(int node, int start, int end, int left, int right) {
		if (end < left || start > right)
			return 0;
		if (start <= left && right <= end)
			return sgtree[node];
		int mid = (left + right) / 2;
		return search(node * 2, start, end, left, mid) + search(node * 2 + 1, start, end, mid + 1, right);
	}

	static void update(int node, long diff, int index, int left, int right) {
		if (left > index || right < index)
			return;
		sgtree[node] += diff;
		if (left != right) {
			int mid = (left + right) / 2;
			update(node * 2, diff, index, left, mid);
			update(node * 2 + 1, diff, index, mid + 1, right);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		sgtree = new long[n * 4];
		st = new StringTokenizer(br.readLine());
		long[] arr = new long[n + 1];
		for (int i = 1; i <= n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		init(arr, 1, 1, n);
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int n3 = Integer.parseInt(st.nextToken());
			int n4 = Integer.parseInt(st.nextToken());
			long res = search(1, Math.min(n1, n2), Math.max(n1, n2), 1, n);
			sb.append(res);
			System.out.println(sb);
			long dif = n4 - arr[n3];
			update(1, dif, n3, 1, n);
			arr[n3] = n4;
			sb.setLength(0);
		}
	}
}
