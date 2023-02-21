import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long[] sgtree;

	static void update(int node, int index, int left, int right,int val) {
		if (left > index || right < index)
			return;
		sgtree[node] += val;
		if (left != right) {
			int mid = (left + right) / 2;
			update(node * 2, index, left, mid,val);
			update(node * 2 + 1, index, mid + 1, right,val);
		}
	}

	static long search(int node, int left, int right, long val) {
		if (left == right)
			return left;
		long index = sgtree[node * 2];
		int mid = (left + right) / 2;
		if (index >= val)
			return search(node * 2, left, mid, val);
		else
			return search(node * 2 + 1, mid + 1, right, (val - index));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		sgtree = new long[65536 * 4];
		int k = Integer.parseInt(st.nextToken());
		int[] arr=new int[n+1];
		long res = 0;
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			update(1, arr[i], 0, 65536,1);
			if (i >= k) {
				int start = i - k + 1;
				long rr = search(1, 0, 65536, (k + 1) / 2);
				res += rr;
				update(1,arr[(i-k+1)],0,65536,-1);
			}
		}
		System.out.println(res);
	}
}
