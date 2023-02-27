import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	static int ma = 0;
	static int n;
	static int m;

	static int binary() {
		int ro = 0;
		int hi = ma;
		while (ro <= hi) {
			int mid = (ro + hi) / 2;
			long nu = 0;
			for (int i = 0; i < n; i++) {
				if ((arr[i]-mid) > 0)
					nu += (arr[i]-mid);

			}
			if (nu >= m)
				ro = mid + 1;
			else
				hi = mid - 1;
		}
		return ro-1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (ma < arr[i])
				ma = arr[i];
		}
		int rr = binary();
		System.out.println(rr);

	}
}
