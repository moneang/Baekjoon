import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int binary(long[] ar, long key) {
		int lo = 0;
		int hi = ar.length - 1;
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (key < ar[mid]) {
				hi = mid - 1;
			} else if (key > ar[mid])
				lo = mid + 1;
			else
				return mid;
		}
		return -1;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		long[] arr = new long[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		Arrays.sort(arr);
		for (int i = 0; i < m; i++) {
			long n1 = Long.parseLong(st.nextToken());
			long re = binary(arr, n1);
			if (re != -1)
				sb.append(1).append('\n');
			else
				sb.append(0).append('\n');
		}
		System.out.println(sb);
		br.close();

	}
}
