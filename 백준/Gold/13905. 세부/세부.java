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
			return o.cost - this.cost;
		}
	}

	static PriorityQueue<node> q;
	static int[] parent;
	static int[] rank;

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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		parent = new int[n + 1];
		rank = new int[n + 1];
		for (int i = 0; i <= n; i++)
			parent[i] = i;
		ArrayList<Integer> li = new ArrayList<>();
		q = new PriorityQueue<node>();
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int n3 = Integer.parseInt(st.nextToken());
			q.add(new node(n1, n2, n3));
		}
		int res = 0;
		while (!q.isEmpty()) {
			node tm = q.poll();
			int x = tm.x;
			int y = tm.y;
			if (issame(x, y) == false) {
				res = tm.cost;
				union(x, y);
			}
			if (find(start) == find(end))
				break;
		}
		if(parent[start]!=parent[end])
			System.out.println(0);
		else
			System.out.println(res);
	}

}
