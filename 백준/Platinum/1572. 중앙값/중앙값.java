import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long[] sgtree;

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

	static long search(int node, int left, int right, long res) {
		if (left == right)
			return left;
		int mid = (left + right) / 2;
		long l = sgtree[node * 2];
		if (l >= res)
			return search(node * 2, left, mid, res);
		else
			return search(node * 2 + 1, mid+1,right, res - l);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		sgtree = new long[(65536 * 4)+1];
		int[] arr = new int[n + 1];
		long su=0;
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			update(1, arr[i], 1, 0, 65536);
			if(i>=k) {
				su+=search(1,0,65536,(k+1)/2);
				update(1, arr[i-k+1], -1, 0, 65536);
			}
		}
		sb.append(su);
		System.out.println(sb);
	}

}
