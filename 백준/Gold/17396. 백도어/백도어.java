import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class node implements Comparable<node> {
		int x;
		long cost;

		node(int x, long cost) {
			this.x = x;
			this.cost = cost;
		}

		@Override
		public int compareTo(node o) {
			// TODO Auto-generated method stub
			return (int) this.cost - (int) o.cost;
		}
	}

	static long[] dist;
	static int[] vi;
	static int[] arr;

	static PriorityQueue<node> q;
	static ArrayList<node> li[];

	static void dig(int start) {
		q = new PriorityQueue<>();
		dist[start] = 0;
		q.add(new node(start, 0));
		while (!q.isEmpty()) {
			node tm = q.poll();
			int end = tm.x;
			if (vi[end] == 1 || arr[end] == 1)
				continue;
			vi[end] = 1;
			for (int i = 0; i < li[end].size(); i++) {
				int v = li[end].get(i).x;
				long cost = li[end].get(i).cost;
				if (arr[v] == 1)
					continue;
				if (dist[v] > dist[end] + cost) {
					dist[v] = dist[end] + cost;
					q.add(new node(v, dist[v]));
				}
			}

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		arr = new int[n];
		vi = new int[n];
		dist = new long[n];
		li = new ArrayList[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			dist[i] = Long.MAX_VALUE;
			li[i] = new ArrayList<>();
			arr[i] = Integer.parseInt(st.nextToken());
		}
		arr[n - 1] = 0;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int n3 = Integer.parseInt(st.nextToken());
			li[n1].add(new node(n2, n3));
			li[n2].add(new node(n1, n3));
		}
		dig(0);
		if (dist[n - 1] != Long.MAX_VALUE)
			sb.append(dist[n - 1]);
		else
			sb.append(-1);
		System.out.println(sb);
	}
}
