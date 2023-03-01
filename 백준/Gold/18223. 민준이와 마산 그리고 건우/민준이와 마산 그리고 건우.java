import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

	static ArrayList<node> li[];
	static PriorityQueue<node> q;
	static int[] dist;
	static int[] vi;
	static int v;

	static void dig(int start) {
		q = new PriorityQueue<>();
		dist[start] = 0;
		q.add(new node(start, 0));
		while (!q.isEmpty()) {
			node tm = q.poll();
			int end = tm.x;
			if (vi[end] == 1)
				continue;
			for (int i = 0; i < li[end].size(); i++) {
				int v = li[end].get(i).x;
				int cost = li[end].get(i).cost;
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
		st = new StringTokenizer(br.readLine());
		v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		dist = new int[v + 1];
		vi = new int[v + 1];
		li = new ArrayList[v + 1];
		for (int i = 0; i <= v; i++) {
			dist[i] = Integer.MAX_VALUE;
			li[i] = new ArrayList<>();
		}
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int n3 = Integer.parseInt(st.nextToken());
			li[n1].add(new node(n2, n3));
			li[n2].add(new node(n1, n3));
		}
		dig(1);
		int re1 = dist[p];
		int re2 = dist[v];
		dist = new int[v + 1];
		vi = new int[v + 1];
		for (int i = 0; i <= v; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dig(p);
		re1 += dist[v];
		if (re1 == re2)
			System.out.println("SAVE HIM");
		else
			System.out.println("GOOD BYE");

	}
}
