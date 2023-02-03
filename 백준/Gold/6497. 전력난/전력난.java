import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int m;
	static int[] parent;
	static int[] rank;
	static PriorityQueue<node> q;

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
		if (a == parent[a])
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
			parent[a]=b;
		else {
			parent[b]=a;
			if (rank[a] == rank[b])
				rank[a]++;
		}

	}

	static boolean same(int a, int b) {
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
		while (true) {
			StringBuilder sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			if (m == 0 && n == 0)
				break;
			q = new PriorityQueue<>();
			rank = new int[m];
			parent = new int[m];
			for(int i=0;i<m;i++)
				parent[i]=i;
			
			long total = 0;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				int n3 = Integer.parseInt(st.nextToken());
				q.add(new node(n1, n2, n3));
				total += n3;
			}
			long res = 0;
			while (!q.isEmpty()) {
				node tm = q.poll();
				int x = tm.x;
				int y = tm.y;
				if (same(x, y) == false) {
					union(x, y);
					res += tm.cost;
				}
			}
			long rr=total-res;
			sb.append(rr);
			System.out.println(rr);

		}
	}
}
