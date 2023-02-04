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

	static void dig(int start) {
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
					q.add(new node(v, dist[v]));
				}
			}
		}
	}

	static int[] vi;
	static int[] dist;
	static ArrayList<node> li[];
	static PriorityQueue<node> q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			StringBuilder sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int n3 = Integer.parseInt(st.nextToken());
			vi = new int[n1 + 1];
			dist = new int[n1 + 1];
			li = new ArrayList[n1 + 1];
			q = new PriorityQueue<>();
			for (int i = 0; i <= n1; i++) {
				li[i] = new ArrayList<>();
				dist[i] = Integer.MAX_VALUE;
			}
			for (int i = 0; i < n2; i++) {
				st = new StringTokenizer(br.readLine());
				int n11 = Integer.parseInt(st.nextToken());
				int n12 = Integer.parseInt(st.nextToken());
				int n13 = Integer.parseInt(st.nextToken());
				li[n12].add(new node(n11, n13));
			}
			dig(n3);
			int re = 0;
			int ma = 0;
			for (int i = 1; i <= n1; i++) {
				if (dist[i] != Integer.MAX_VALUE) {
					re++;
					if (ma < dist[i])
						ma = dist[i];
				}
			}
			sb.append(re).append(' ').append(ma);
			System.out.println(sb);
		//	System.out.println(Arrays.toString(dist));
			
		}

	}
}
