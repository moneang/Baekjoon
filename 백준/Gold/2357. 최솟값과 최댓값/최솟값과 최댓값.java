import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long[] sgtree1;
	static long[] sgtree2;
	static long[] arr;

	static long mi(int node, int left, int right) {
		if (left == right)
			return sgtree1[node] = arr[left];
		int mid = (left + right) / 2;
		return sgtree1[node] = Math.min(mi(node * 2, left, mid), mi(node * 2 + 1, mid + 1, right));
	}

	static long ma(int node, int left, int right) {
		if (left == right)
			return sgtree2[node] = arr[left];
		int mid = (left + right) / 2;
		return sgtree2[node] = Math.max(ma(node * 2, left, mid), ma(node * 2 + 1, mid + 1, right));
	}

	static long misearch(int node, int start, int end, int left, int right) {
		if (end < left || right < start)
			return 1000000000;
		if (start <= left && right <= end)
			return sgtree1[node];
		int mid = (left + right) / 2;
		return Math.min(misearch(node * 2, start, end, left, mid), misearch(node * 2 + 1, start, end, mid + 1, right));
	}
	static long masearch(int node, int start, int end, int left, int right) {
		if (end < left || right < start)
			return 0;
		if (start <= left && right <= end)
			return sgtree2[node];
		int mid = (left + right) / 2;
		return Math.max(masearch(node * 2, start, end, left, mid), masearch(node * 2 + 1, start, end, mid + 1, right));
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		arr = new long[n + 1];
		sgtree1 = new long[n * 4];
		sgtree2 = new long[n * 4];
		for (int i = 1; i <= n; i++)
			arr[i] = Long.parseLong(br.readLine());
		mi(1, 1, n);
		ma(1, 1, n);
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			long rr = misearch(1, n1, n2, 1, n);
			long rr2 = masearch(1, n1, n2, 1, n);
			sb.append(rr).append(' ').append(rr2).append('\n');
		}
		System.out.println(sb);

	}
}
