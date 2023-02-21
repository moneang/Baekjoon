import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long[] sgtree;

	static long search(int node, int left, int right, long val) {
		if (left == right)
			return left;
		int mid = (left + right) / 2;
		long index = sgtree[node * 2];
		if (index >= val)
			return search(node * 2, left, mid, val);
		else
			return search(node * 2 + 1, mid + 1, right, (val - index));
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
		StringBuilder sb=new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		sgtree = new long[2000001 * 4];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			if (n1 == 1) {
				update(1, n2, 1, 1, 2000001);
			} else {
				long rr = search(1, 1, 2000001, n2);
				sb.append(rr).append('\n');
				update(1, (int)rr, -1, 1, 2000001);
			}
		}
		System.out.println(sb);
	}
}
