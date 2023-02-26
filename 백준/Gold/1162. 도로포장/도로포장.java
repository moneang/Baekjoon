
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class node2 {
		int x;
		long cost;

		node2(int x, long cost) {
			this.x = x;
			this.cost = cost;
		}
	}

	static class node implements Comparable<node> {
		int x;
		long cost;
		int k;

		node(int x, long cost, int k) {
			this.x = x;
			this.cost = cost;
			this.k = k;
		}

		@Override
		public int compareTo(node o) {
			// TODO Auto-generated method stub
			return (int)this.cost-(int)o.cost;
		}

	}

	static PriorityQueue<node> q;
	static ArrayList<node2> li[];
	static int k;
	static long[][] dist;
	static int[][] vi;

	static void dig(int start) {
		q = new PriorityQueue<>();
		q.add(new node(start, 0, 0));
		int nu = 0;
		dist[start][0] = 0;
		while (!q.isEmpty()) {
			node tm = q.poll();
			int end = tm.x;
			long cost = tm.cost;
			int kk = tm.k;
			if (dist[end][kk] < cost)
				continue;

			for (int i = 0; i < li[end].size(); i++) {
				int v = li[end].get(i).x;
				long co = li[end].get(i).cost;
				long newdist = co + cost;
				// 포장할거
				if (kk < k && dist[v][kk + 1] > cost) {
					dist[v][kk + 1] = cost;
					q.add(new node(v, cost, kk + 1));
				} // 안할거
				if (dist[v][kk] > newdist) {
					dist[v][kk] = newdist;
					q.add(new node(v, newdist, kk));
				}
			}
		}
	}

	static ArrayList<Integer> li2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		li = new ArrayList[n + 1];
		dist = new long[n + 1][k + 1];
		vi = new int[n + 1][k + 1];
		for (int i = 0; i <= n; i++) {
			Arrays.fill(dist[i], Long.MAX_VALUE);
			li[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int n3 = Integer.parseInt(st.nextToken());
			li[n1].add(new node2(n2, n3));
			li[n2].add(new node2(n1, n3));
		}

		dig(1);
		long min=Long.MAX_VALUE;
		for (int j = 0; j <= k; j++) {
			if(min>dist[n][j])
				min=dist[n][j];
		}
		System.out.println(min);

	}

}
