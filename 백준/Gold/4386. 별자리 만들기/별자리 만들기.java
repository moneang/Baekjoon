import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class node2 {
		float x;
		float y;

		node2(float x, float y) {
			this.x = x;
			this.y = y;
		}
	}

	static ArrayList<node2> li;

	static class node implements Comparable<node> {
		int a;
		int b;
		double cost;

		node(int a, int b, double cost) {
			this.a = a;
			this.b = b;
			this.cost = cost;
		}

		@Override
		public int compareTo(node o) {
			// TODO Auto-generated method stub
			return Double.compare(this.cost, o.cost);
		}

	}

	static PriorityQueue<node> q;
	static int[] rank;
	static int[] parent;

	static int n;

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
			parent[a] = b;
		else {
			parent[b] = a;
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
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		BigDecimal val;
		rank = new int[n+1];
		parent = new int[n+1];
		q = new PriorityQueue<>();
		li = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			float n1 = Float.parseFloat(st.nextToken());
			float n2 = Float.parseFloat(st.nextToken());
			li.add(new node2(n1, n2));
		}
		for (int i = 0; i < (li.size() - 1); i++) {
			for (int j = i+1; j < li.size(); j++) {
				double im=Math.abs(li.get(i).x-li.get(j).x)+Math.abs(li.get(i).y-li.get(j).y);
				double im3=Math.sqrt(Math.pow(Math.abs(li.get(i).x-li.get(j).x), 2) + Math.pow(Math.abs(li.get(i).y-li.get(j).y), 2));
				q.add(new node(i+1,j+1,im3));
			}
		}
		double res=0;
		while (!q.isEmpty()) {
			node tm = q.poll();
			int x = tm.a;
			int y = tm.b;
			if(same(x,y)==false) {
				union(x,y);
				res+=tm.cost;
			}
		}
		System.out.printf("%.2f",res);
        br.close();
	}

}
