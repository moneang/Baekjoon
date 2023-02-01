
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static long[] arr;
	static long m;

	static long upper() {
		ma++;
		long min = 0;
		long max = ma;
		while (min < max) {
			int count = 0;
			long mid = min + (max - min) / 2;
			for (int i = 0; i < arr.length; i++) {
				count += (arr[i] / mid);
			}
			if (count < m)
				max = mid;
			else
				min = mid+1;
		}
		return min;
	}

	static long ma = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new long[n];
		for (int i = 0; i < n; i++) {
			int n1 = Integer.parseInt(br.readLine());
			arr[i] = n1;
			if (ma < arr[i])
				ma = arr[i];
		}
		long res=upper();
		sb.append(res-1);
		System.out.println(sb);
	}

}
