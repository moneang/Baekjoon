import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class node implements Comparable<node> {
		int x;
		int y;
		int g;
		int ng;

		node(int x, int y, int g, int ng) {
			this.x = x;
			this.y = y;
			this.g = g;
			this.ng = ng;
		}

		@Override
		public int compareTo(node o) {
			// TODO Auto-generated method stub
			if (this.g == o.g)
				return this.ng - o.ng;
			return this.g - o.g;
		}
	}

	static int n;
	static int m;
	static char[][] arr;
	static int[][] vi;
	static PriorityQueue<node> q;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	static int ngsearch(int a, int b) {
		int num = 0;
		for (int i = 0; i < 4; i++) {
			int nx = a + dx[i];
			int ny = b + dy[i];
			if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
				if (arr[nx][ny] == 'g')
					num++;
			}
		}
		return num;
	}

	static void dig(int a, int b) {
		q.add(new node(a, b, 0, 0));

		while (!q.isEmpty()) {
			node tm = q.poll();
			int x = tm.x;
			int y = tm.y;
			if (arr[x][y] == 'F') {
				System.out.println(tm.g + " " + tm.ng);
                return;
			}
			for (int i = 0; i < 4; i++) {
				int nx = tm.x + dx[i];
				int ny = tm.y + dy[i];
				if (nx >= 0 && ny >= 0 && nx < n && ny < m && vi[nx][ny] == 0) {

					vi[nx][ny] = 1;

					if (arr[nx][ny] == '.') {
							int nn = ngsearch(nx, ny);
							
						if(nn>=1)
							q.add(new node(nx, ny, tm.g, tm.ng+1));
						else
							q.add(new node(nx, ny, tm.g, tm.ng));
					}
					if (arr[nx][ny] == 'F') {
						q.add(new node(nx, ny, tm.g, tm.ng));
					}
					if (arr[nx][ny] == 'g') {
						q.add(new node(nx, ny, tm.g+1, tm.ng));
					}

				}

			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new char[n][m];
		vi = new int[n][m];
		q = new PriorityQueue<>();
		int start1 = 0;
		int start2 = 0;
		for (int i = 0; i < n; i++) {
			String ss = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = ss.charAt(j);
				if (arr[i][j] == 'S') {
					start1 = i;
					start2 = j;

				}
			}
		}
		dig(start1, start2);
		
	}

}
