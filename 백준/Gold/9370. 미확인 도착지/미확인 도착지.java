import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class node2 {
		int x;
		int cost;

		node2(int x, int cost) {
			this.x = x;
			this.cost = cost;
		}
	}

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
	static ArrayList<node2> li[];
	static ArrayList<Integer> li2;
	static int[] vi;
	static int[] dist;
	static int g;
	static int h;
	static int tnum;

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
				if (dist[v] > cost + dist[end]) {
					dist[v] = cost + dist[end];
					q.add(new node(v, dist[v]));
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int tt = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < tt; tc++) {
			StringBuilder sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			vi = new int[n + 1];
			dist = new int[n + 1];
			li = new ArrayList[n + 1];
			li2 = new ArrayList<>();
			for (int i = 0; i <= n; i++) {
				dist[i] = Integer.MAX_VALUE;
				li[i] = new ArrayList<>();
			}
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				int n3 = Integer.parseInt(st.nextToken());
				if ((n1 == g && n2 == h) || (n2 == g && n1 == h)) {
					li[n1].add(new node2(n2, (n3 * 2) - 1));
					li[n2].add(new node2(n1, (n3 * 2) - 1));
				} else {
					li[n1].add(new node2(n2, (n3 * 2)));
					li[n2].add(new node2(n1, (n3 * 2)));
				}

			}
			for (int i = 0; i < t; i++) {
				li2.add(Integer.parseInt(br.readLine()));
			}
			dig(start);
			int[] rearr = new int[t];
			int nn = 0;
			for (int i = 0; i < li2.size(); i++) {
					if (dist[li2.get(i)] % 2 != 0 && (dist[li2.get(i)]!=Integer.MAX_VALUE)) {
					rearr[nn] = li2.get(i);
					nn++;
				}
			}
			Arrays.sort(rearr);
			for (int i = 0; i < t; i++) {
				if (rearr[i] > 0)
					sb.append(rearr[i]).append(' ');
			}
			System.out.println(sb);
		}

	}
}
