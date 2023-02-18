import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static class node2 implements Comparable<node2> {
		int x;
		int y;

		node2(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(node2 o) {
			// TODO Auto-generated method stub
			return this.x - o.x;
		}
	}

	static class node implements Comparable<node> {
		int x;
		int y;

		node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(node o) {
			// TODO Auto-generated method stub
			return this.y - o.y;
		}
	}

	static long[] sgtree;

	static void update(int node, int index, int val, int left, int right) {
		if (left > index || right < index)
			return;
		sgtree[node] += val;
		if (left != right) {
			int mid = (left + right) / 2;
			update(node * 2, index, val, left, mid);
			update(node * 2 + 1, index, val, mid + 1, right);
		}
	}

	static long search(int node, int start, int end, int left, int right) {
		if (end < left || start > right)
			return 0;
		if (start <= left && right <= end)
			return sgtree[node];
		int mid = (left + right) / 2;
		return search(node * 2, start, end, left, mid) + search(node * 2 + 1, start, end, mid + 1, right);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		ArrayList<node> li = new ArrayList<>();
		ArrayList<node2> li2 = new ArrayList<>();
		int ma = 0;
		int[] arr = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			int n1 = Integer.parseInt(br.readLine());
			arr[i] = n1;
			if (ma < n1)
				ma = n1;
			li.add(new node(i, n1));
		}
		Collections.sort(li);
		for (int i = 0; i < li.size(); i++) {
			li2.add(new node2(li.get(i).x, (i + 1)));
		}
		Collections.sort(li2);
		// 좌표압축..
		sgtree = new long[500000 * 4];
		for (int i = 0; i < li2.size(); i++) {
			//System.out.println("~ "+li2.get(i).y);
			update(1, li2.get(i).y, 1, 0, (500000));
			long rr = search(1, li2.get(i).y, n, 0, (500000));
			sb.append(rr).append('\n');
		}
		System.out.println(sb);
	}

}
