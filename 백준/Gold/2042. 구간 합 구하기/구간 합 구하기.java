import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long[] sgtree;
	static long[] arr;

	static long init(int node, int left, int right) {
		if (left == right)
			return sgtree[node] = arr[left];
		int mid = (left + right) / 2;
		return sgtree[node] = init(node * 2, left, mid) + init(node * 2 + 1, mid + 1, right);
	}

	static void update(int node, int index, long val, int left, int right) {
		if (left > index || right < index)
			return;
		sgtree[node] += val;
		if (left != right) {
			int mid = (left + right) / 2;
			update(node * 2, index, val, left, mid);
			update(node * 2 + 1, index, val, mid + 1, right);
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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		sgtree = new long[1000000 * 4];
		arr = new long[1000001];
		for (int i = 1; i <= n; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		init(1, 1, 1000000);
		for (int i = 0; i < (m + k); i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			long n3 = Long.parseLong(st.nextToken());
			if (n1 == 1) {
				long im = n3 - arr[n2];
				update(1, n2, im, 1, 1000000);
				arr[n2] = n3;
			} else {
				long rr = sum(1, n2, (int) n3, 1, 1000000);
				sb.append(rr).append('\n');
			}
		}
		System.out.println(sb);

	}

}
