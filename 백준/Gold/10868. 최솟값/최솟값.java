import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static long[] sgtree;

	// sum응용
	static long misearch(int node, int start, int end, int left, int right) {
		if(end<left||right<start)
			return 1000000000;
		if(start<=left && end>=right)
			return sgtree[node];
		int mid = (left + right) / 2;
		return Math.min(misearch(node * 2, start, end, left,mid), misearch(node * 2 + 1, start, end, mid+1, right));

	}
	static long init(int node, long[] arr, int left, int right) {
		if (left == right)
			return sgtree[node] = arr[left];
		int mid = (left + right) / 2;
		return sgtree[node] = Math.min(init(node * 2, arr, left, mid), init(node * 2 + 1, arr, mid + 1, right));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		long[] arr = new long[n + 1];
		sgtree = new long[(n * 4) + 1];
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		init(1, arr, 1, (n));
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			long min = misearch(1,start,end,1,n);
			sb.append(min).append("\n");
		}
		System.out.println(sb);

	}
}
