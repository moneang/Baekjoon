import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long[] arr;
	static long ma = 0;
	static int n;
	static int m;

	static long binary() {
		ma++;
		long ro = 0;
		long hi = ma;
		while (ro < hi) {
			long mid = ro + ((hi - ro) / 2);
			int num = 0;
			for (int i = 0; i < n; i++) {
				long im = arr[i] / mid;
				num += im;
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
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new long[n];
		for (int i = 0; i < n; i++) {
			long n1 = Long.parseLong(br.readLine());
			if (ma < n1)
				ma = n1;
			arr[i] = n1;
		}
		long rr = binary();
		sb.append(rr - 1);
		System.out.println(sb);
	}
}
