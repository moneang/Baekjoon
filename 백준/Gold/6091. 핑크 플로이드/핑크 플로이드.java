import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
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
			return this.cost - o.cost;
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

	static ArrayList<Integer> li[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		li = new ArrayList[n + 1];
		parent = new int[n + 1];
		rank = new int[n + 1];
		q = new PriorityQueue<>();
		for (int i = 0; i <= n; i++) {
			li[i] = new ArrayList<>();
			parent[i] = i;
		}
		int end = n;
		for (int i = 1; i <= n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = i + 1; j <= end; j++) {
				int n1 = Integer.parseInt(st.nextToken());
				q.add(new node(i, j, n1));
			}
		}
		while (!q.isEmpty()) {
			node tm = q.poll();
			int x = tm.x;
			int y = tm.y;
			if (issame(x, y) == false) {
				union(x, y);
				li[x].add(y);
				li[y].add(x);
			}
		}
		for (int i = 1; i <= n; i++)
			Collections.sort(li[i]);

		for (int j = 1; j <= n; j++) {
			sb.append(li[j].size()).append(' ');
			for (int i = 0; i < li[j].size(); i++)
				sb.append(li[j].get(i)).append(' ');
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
