import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static long[] sgtree;
	static int[] arr;

	static long search(int node, int start, int end, int left, int right) {
		if (end < left || start > right)
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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		sgtree = new long[1000000 * 4];
		arr = new int[1000000];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int n3 = Integer.parseInt(st.nextToken());
			if (n1 == 1) {
				int im = n3 - arr[n2];
				
				update(1, n2, im, 1, 1000000);
				arr[n2] = n3;
			} else {
				int ro = Math.min(n2, n3);
				int hi = Math.max(n2, n3);
				long rr = search(1, ro, hi, 1, 1000000);
				sb.append(rr).append('\n');

			}
		}
		System.out.println(sb);
	}
}
