import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static class node {
		int x;
		int cost;

		node(int x, int cost) {
			this.x = x;
			this.cost = cost;
		}
	}

	static int n;
	static int[] vi;
	static int[] dep;
	static int[] dist;
	static int[][] parent;
	static ArrayList<node> li[];

	static void dfs(int here, int depth) {
		vi[here] = 1;
		dep[here] = depth;
		for (int i = 0; i < li[here].size(); i++) {
			int v = li[here].get(i).x;
			int cost = li[here].get(i).cost;
			if (vi[v] == 1)
				continue;
            parent[v][0]=here;
			dist[v] += dist[here] + cost;
			dfs(v, depth + 1);
		}
	}

	static void fillparent() {
		for (int j = 1; j <= 20; j++) {
			for (int i = 1; i <= n; i++) {
				parent[i][j] = parent[parent[i][j - 1]][j - 1];
			}
		}
	}

	static int lca(int a, int b) {
		if (dep[a] > dep[b]) {
			int te = b;
			b = a;
			a= te;
		}
		for (int i = 20; i >= 0; i--) {
			if (dep[b] - dep[a] >= (1 << i))
				b = parent[b][i];
		}
		if (a == b)
			return a;
		for (int i = 20; i >= 0; i--) {
			if (parent[a][i] != parent[b][i]) {
				a = parent[a][i];
				b = parent[b][i];
			}
		}
		return parent[a][0];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		vi = new int[n + 1];
		dep = new int[n + 1];
		dist= new int[n + 1];
		parent = new int[n + 1][21];
		li=new ArrayList[n+1];
		for (int i = 0; i <= n; i++) {
			li[i] = new ArrayList<>();
		}
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int n3 = Integer.parseInt(st.nextToken());
			li[n1].add(new node(n2, n3));
			li[n2].add(new node(n1, n3));
		}
		int m = Integer.parseInt(br.readLine());
		dfs(1,0);
		fillparent();
		for (int i = 0; i < m; i++) {
			st=new StringTokenizer(br.readLine());
			int n1=Integer.parseInt(st.nextToken());
			int n2=Integer.parseInt(st.nextToken());
			int res=lca(n1,n2);
			bw.write(dist[n1]+dist[n2]-(2*dist[res])+"\n");
		}
		br.close();
		bw.close();
	}
}