import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static long[] sgtree;
	static long[] arr;

	static long search(int index) {
		int ro = 1;
		int hi = 1000000;
		while (ro <= hi) {
			int mid = (ro + hi) / 2;
			long cal = sum(1, 1, mid, 1, 1000000);
			if (cal < index)
				ro = mid + 1;
			else
				hi = mid - 1;
		}
		return ro;
	}

	static void update(int node, int index, int diff, int left, int right) {
		if (left > index || right < index)
			return;
		sgtree[node] += diff;
		if (left != right) {
			int mid = (left + right) / 2;
			update(node * 2, index, diff, left, mid);
			update(node * 2 + 1, index, diff, mid + 1, right);
		}
	}

	static long sum(int node, int start, int end, int left, int right) {
		if (end < left || right < start)
			return 0;
		if (start <= left && right <= end)
			return sgtree[node];
		int mid = (left + right) / 2;
		return sum(node * 2, start, end, left, mid) + sum(node * 2 + 1, start, end, mid + 1, right);
	}

	static long init(int node, int left, int right) {
		if (left == right)
			return sgtree[node] = 0;
		int mid = (left + right) / 2;
		return sgtree[node] = init(node * 2, left, mid) + init(node * 2 + 1, mid + 1, right);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		sgtree = new long[1000000 * 4];
		arr = new long[1000001];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());

			if (n1 == 2) {
				int n2 = Integer.parseInt(st.nextToken());
				int n3 = Integer.parseInt(st.nextToken());
				arr[n2] += n3;
				update(1, n2, n3, 1, 1000000);
			} else {
				int n4 = Integer.parseInt(st.nextToken());
				long re = search(n4);
				update(1, (int) re, -1, 1, 1000000);
				sb.append(re).append('\n');
			}
		}
		System.out.println(sb);
	}
}
