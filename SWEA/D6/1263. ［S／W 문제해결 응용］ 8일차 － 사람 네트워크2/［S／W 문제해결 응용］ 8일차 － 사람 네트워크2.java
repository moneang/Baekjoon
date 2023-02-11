import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
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
	static int[] dist;
	static int[] vi;
	static PriorityQueue<node> q;
	static int n;

	static void dig(int start) {
		dist = new int[n];
		vi = new int[n];
		for (int i = 0; i < n; i++)
			dist[i] = Integer.MAX_VALUE;
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
				if (dist[v] > cost + dist[end]) {
					dist[v] = cost + dist[end];
					// System.out.println("?? "+v+" "+dist[v]);
					q.add(new node(v, dist[v]));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			StringBuilder sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			li = new ArrayList[n];
			for (int i = 0; i < n; i++)
				li[i] = new ArrayList<>();
			for (int i = 0; i < n; i++) {

				for (int j = 0; j < n; j++) {
					int n1 = Integer.parseInt(st.nextToken());
					if (n1 == 1) {
						li[i].add(new node(j, 1));
						li[j].add(new node(i, 1));
					}
				}
			}
			int mi = 98765432;

			for (int i = 0; i < n; i++) {
				dig(i);
				int sum = 0;
				for (int j = 0; j < n; j++)
					sum += dist[j];
				if (mi > sum)
					mi = sum;
			}
			sb.append("#"+tc+" ").append(mi);
			System.out.println(sb);
		}
	}
}
