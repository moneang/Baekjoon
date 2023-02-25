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

	static class node2 {
		int x;
		int y;

		node2(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static ArrayList<node> li[];
	static ArrayList<node2> li2[];
	static PriorityQueue<node> q;
	static int[] dist;
	static int[] vi;

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
				if (dist[v] > dist[end] + cost) {
					dist[v] = dist[end] + cost;
					li2[v].add(new node2(v, end));// 어차피 맨 마지막만 출력함
					q.add(new node(v, dist[v]));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		li = new ArrayList[n + 1];
		li2 = new ArrayList[n + 1];
		dist = new int[n + 1];
		vi = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			dist[i] = Integer.MAX_VALUE;
			li[i] = new ArrayList<>();
			li2[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int n3 = Integer.parseInt(st.nextToken());
			li[n1].add(new node(n2, n3));
			li[n2].add(new node(n1, n3));
		}
		dig(1);
		int res = 0;
		for (int i = 2; i <= n; i++) {
			if (dist[i] != Integer.MAX_VALUE)
				res++;
		}
		System.out.println(res);
		for (int i = 2; i <= n; i++) {
			int si = li2[i].size();
			System.out.println(li2[i].get(si - 1).x + " " + li2[i].get(si - 1).y);
		}
	}
}
