import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main{
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

	static PriorityQueue<node> q;
	static int[] parent;
	static int[] rank;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		q = new PriorityQueue<>();
		parent = new int[n + 1];
		rank = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			parent[i] = i;
			int n1 = Integer.parseInt(br.readLine());
			q.add(new node(0, i, n1));
		}
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				int n2 = Integer.parseInt(st.nextToken());
				if (n2 != 0)
					q.add(new node(i, j, n2));// 어차피 넣어도 판별하니까...다 넣어도될듯?
			}
		}

		int te = 0;
		while (!q.isEmpty()) {
			node tm = q.poll();
			int x = tm.x;
			int y = tm.y;
			if (issame(x, y) == false) {
				te += tm.cost;
				union(x, y);
			}
		}
		System.out.println(te);

	}
}
