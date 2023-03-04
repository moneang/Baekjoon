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

	static PriorityQueue<node> q;
	static ArrayList<node> li[];
	static int[] dist;
	static int[] vi;
	static int n;

	static void dig(int start, int n1, int n2, boolean ch) {
		q = new PriorityQueue<>();
		dist = new int[n + 1];
		vi = new int[n + 1];

		for (int i = 0; i <= n; i++) {
			dist[i] = 50000000;
		}
		q.add(new node(1, 0));
		dist[1] = 0;
		while (!q.isEmpty()) {
			node tm = q.poll();
			int end = tm.x;
			if (vi[end] == 1)
				continue;
			for (int i = 0; i < li[end].size(); i++) {
				int v = li[end].get(i).x;
				int cost = li[end].get(i).cost;
				if ((end == n1 && v == n2) || (end == n2 && v == n1))
					continue;
				if (dist[v] > dist[end] + cost) {
					dist[v] = dist[end] + cost;
					if (ch == true)
						parent[v] = end;
					q.add(new node(v, dist[v]));

				}
			}
		}

	}

	static int mi = 50000000;
	static int ma = 0;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		li = new ArrayList[n + 1];
		int[][] vi2 = new int[n + 1][n + 1];
		for (int i = 0; i <= n; i++)
			li[i] = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int n3 = Integer.parseInt(st.nextToken());
			li[n1].add(new node(n2, n3));
			li[n2].add(new node(n1, n3));
		}
		parent = new int[n + 1];
		for (int i = 0; i <= n; i++)
			parent[i] = -1;
		dig(1, 0, 0, true);
		mi = dist[n];
		boolean res = false;
		int ma = 0;
		for (int i = n; i > 1; i = parent[i]) {
			dig(1, i, parent[i], false);
			if (dist[n] == 50000000) {
				res = true;
				break;
			} else {
				if (ma < dist[n])
					ma = dist[n];
			}
		}
		if (res == true)
			System.out.println(-1);
		else
			System.out.println(ma - mi);

	}
}
