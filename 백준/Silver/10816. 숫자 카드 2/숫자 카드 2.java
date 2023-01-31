import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
	static int[] arr;
	static int n;
	static int down(int key) {
		int lo=0;
		int hi=n;
		while (lo < hi) {
			int mid=lo+((hi-lo)/2);
			if(key<=arr[mid])
				hi=mid;
			else
				lo=mid+1;
		
		}
		return lo;
	}
	
	static int upper(int key) {
		int lo=0;
		int hi=n;
		while (lo < hi) {
			int mid=lo+((hi-lo)/2);
			if(key<arr[mid])
				hi=mid;
			else
				lo=mid+1;
		
		}
		return lo;
	}
	
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		arr = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			int nn = Integer.parseInt(st.nextToken());
			int re=upper(nn)-down(nn);
			sb.append(re).append(' ');
			
		}
		System.out.println(sb);
	}
}
