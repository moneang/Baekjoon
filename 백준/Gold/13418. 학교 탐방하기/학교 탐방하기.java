import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class node2 implements Comparable<node2> {
		int x;
		int y;
		int cost;

		node2(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(node2 o) {
			// TODO Auto-generated method stub
			return o.cost - this.cost;
		}

	}

	static class node implements Comparable<node> {
		int x;
		int y;
		int cost;

		node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(node o) {
			// TODO Auto-generated method stub
			return this.cost - o.cost;
		}
	}

	static int find(int a) {
		if (parent[a] == a)
			return a;
		else
			return parent[a] = find(parent[a]);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);
        if (a == b)
			return;
		if (rank[a] < rank[b])
			parent[a] = b;
		else {
			parent[b] = a;
			if (rank[a] == rank[b])
				rank[a]++;
		}
	}

	static boolean issame(int a, int b) {
		a = find(a);
		b = find(b);
		if (a != b)
			return false;
		else
			return true;
	}

	static int[] parent;
	static int[] rank;
	static PriorityQueue<node> q;
	static PriorityQueue<node2> q2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		parent = new int[n + 1];
		rank = new int[n + 1];
		for (int i = 0; i <= n; i++)
			parent[i] = i;
		st = new StringTokenizer(br.readLine());
		q = new PriorityQueue<>();
		q2 = new PriorityQueue<>();
		int n11 = Integer.parseInt(st.nextToken());
		int n12 = Integer.parseInt(st.nextToken());
		int n13 = Integer.parseInt(st.nextToken());
		int rr = n13 - 1;
		q.add(new node(n11, n12, Math.abs(rr)));
		q2.add(new node2(n11, n12, Math.abs(rr)));
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int n3 = Integer.parseInt(st.nextToken());
			int im = n3 - 1;
			q.add(new node(n1, n2, Math.abs(im)));
			q2.add(new node2(n1, n2, Math.abs(im)));
		}
		int res1 = 0;
		while (!q.isEmpty()) {
			node tm = q.poll();
			int x = tm.x;
			int y = tm.y;
			if (issame(x, y) == false) {
				res1 += tm.cost;
				union(x, y);
			}
		}
		for (int i =0; i <= n; i++)
			parent[i] = i;
		int res2 = 0;
		while (!q2.isEmpty()) {
			node2 tm = q2.poll();
			int x = tm.x;
			int y = tm.y;
			if (issame(x, y) == false) {
				res2 += tm.cost;
				union(x, y);
			}
		}
		long result = (res2 * res2) - (res1 * res1);
		sb.append(result);
		System.out.println(sb);
	}

}
