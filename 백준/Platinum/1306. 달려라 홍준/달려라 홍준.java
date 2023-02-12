import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static long[] sgtree;

	// 리프노드 채우기
	static long update(int node, int index, int val, int left, int right) {
		if (left > index || right < index)
			return sgtree[node];
		if (left == right)
			return sgtree[node] = val;
		int mid = (left + right) / 2;
		return sgtree[node] = Math.max(update(node * 2, index, val, left, mid),
				update(node * 2 + 1, index, val, mid + 1, right));

	}

	static long search(int node, int start, int end, int left, int right) {
		if (end < left || right < start)
			return 0;
		if (start <= left && right <= end)
			return sgtree[node];
		int mid = (left + right) / 2;
		return Math.max(search(node * 2, start, end, left, mid), search(node * 2 + 1, start, end, mid + 1, right));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		sgtree = new long[1000000 * 4];
		for (int i = 0; i < n; i++) {
			int n1 = Integer.parseInt(st.nextToken());
			update(1, i, n1, 0, n - 1);
		}
		
		for (int i = m - 1; i < n - m + 1; i++) {
			int ro = i - m + 1;
			int hi = i + m - 1;
			long rr = search(1, ro, hi, 0, n - 1);
			sb.append(rr).append(' ');
		}
		System.out.println(sb);
	}

}
