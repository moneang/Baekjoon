import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class node implements Comparable<node> {
		int x;
		int cost;

		node(int x, int cost) {
			this.x = x;
			this.cost = cost;
		}

		@Override
		public int compareTo(node o) {
			// TODO Auto-generated method stub
			return this.cost - o.cost;
		}
	}

	static int n;
	static int m;
	static int r;
	static int[] arr;
	static int[] vi;
	static int[] dist;
	static int res = 0;
	static int ma = 0;
	static PriorityQueue<node> q;
	static ArrayList<node> li[];

	static void dig(int start) {
		q = new PriorityQueue<>();
		dist[start] = 0;
		q.add(new node(start, 0));
		while (!q.isEmpty()) {
			node tm = q.poll();
			int end = tm.x;
			if (vi[end] == 1)
				continue;
			vi[end] = 1;
			for (int i = 0; i < li[end].size(); i++) {
				int v = li[end].get(i).x;
				int cost = li[end].get(i).cost;
				if (dist[v] > cost + dist[end]) {
					dist[v] = cost + dist[end];
					q.add(new node(v, dist[v]));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		arr = new int[n];
		dist = new int[n];
		li = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			li[i] = new ArrayList<>();
		}
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int n3 = Integer.parseInt(st.nextToken());
			li[n1 - 1].add(new node(n2 - 1, n3));
			li[n2 - 1].add(new node(n1 - 1, n3));
		}
		for (int i = 0; i < n; i++) {
			dist = new int[n];
			vi = new int[n];
			for (int j = 0; j < n; j++)
				dist[j] = Integer.MAX_VALUE;
			dig(i);
			res = arr[i];
			for (int j = 0; j < n; j++) {
				if (dist[j] <= m && j != i)
					res += arr[j];
			}
			if (res > ma)
				ma = res;
		}
		bw.write(ma + "\n");
		br.close();
		bw.close();
	}
}