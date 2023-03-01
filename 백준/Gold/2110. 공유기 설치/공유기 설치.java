import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int m;
	static int n;
	static int[] arr;

	static int binary() {
		int ro = 0;
		int hi = 1000000001;
		while (ro < hi) {
			int mid = (ro + hi) / 2;
			int lo1 = arr[0];
			int lo2 = 0;
			int num = 1;
			for (int i = 1; i < n; i++) {
				lo2 = arr[i];
				if (lo2 - lo1 >= mid) {
					num++;
					lo1 = arr[i];
				}
			}
			if (num >= m)
				ro = mid + 1;
			else
				hi = mid;
		}
		return ro;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
		int rr = binary();
		sb.append(rr-1);
		System.out.println(sb);
	}
}
