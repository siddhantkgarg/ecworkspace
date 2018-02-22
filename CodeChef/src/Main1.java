import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Scanner;

import java.io.*;
import java.util.InputMismatchException;

//InputReader in = new InputReader(System.in);
//OutputWriter out = new OutputWriter(System.out);

public class Main1 {
	static final long modOp = 1000000007;

	//static BufferedInputStream in = new BufferedInputStream(System.in);

	private static class InputReader {
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;
		private SpaceCharFilter filter;

		public InputReader(InputStream stream) {
			this.stream = stream;
		}

		public int read() {
			if (numChars == -1) {
				throw new InputMismatchException();
			}
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (numChars <= 0) {
					return -1;
				}
			}
			return buf[curChar++];
		}

		public int readInt() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public String readString() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isSpaceChar(c));
			return res.toString();
		}

		public double readDouble() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			double res = 0;
			while (!isSpaceChar(c) && c != '.') {
				if (c == 'e' || c == 'E') {
					return res * Math.pow(10, readInt());
				}
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = read();
			}
			if (c == '.') {
				c = read();
				double m = 1;
				while (!isSpaceChar(c)) {
					if (c == 'e' || c == 'E') {
						return res * Math.pow(10, readInt());
					}
					if (c < '0' || c > '9') {
						throw new InputMismatchException();
					}
					m /= 10;
					res += (c - '0') * m;
					c = read();
				}
			}
			return res * sgn;
		}

		public long readLong() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public boolean isSpaceChar(int c) {
			if (filter != null) {
				return filter.isSpaceChar(c);
			}
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		public String next() {
			return readString();
		}

		public interface SpaceCharFilter {
			public boolean isSpaceChar(int ch);
		}
	}

	private static class OutputWriter {
		private final PrintWriter writer;

		public OutputWriter(OutputStream outputStream) {
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
		}

		public OutputWriter(Writer writer) {
			this.writer = new PrintWriter(writer);
		}

		public void print(Object... objects) {
			for (int i = 0; i < objects.length; i++) {
				if (i != 0) {
					writer.print(' ');
				}
				writer.print(objects[i]);
			}
			writer.flush();
		}

		public void printLine(Object... objects) {
			print(objects);
			writer.println();
			writer.flush();
		}

		public void close() {
			writer.close();
		}

		public void flush() {
			writer.flush();
		}
	}

//	static int readInt() {
//		int no = 0;
//		boolean minus = false;
//		try {
//			int a = in.read();
//			while (a == 32 || a == 10) // 10 is newline & 32 is ASCII for space
//				a = in.read();
//			if (a == '-') {
//				minus = true;
//				a = in.read();
//			}
//			while (a != 10 && a != 32 && a != -1) {
//				no = no * 10 + (a - '0');
//				a = in.read();
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return minus ? -no : no;
//	}
//
//	static long readLong() {
//		long no = 0;
//		boolean minus = false;
//		try {
//			long a = in.read();
//			while (a == 32 || a == 10) // 10 is newline & 32 is ASCII for space
//				a = in.read();
//			if (a == '-') {
//				minus = true;
//				a = in.read();
//			}
//			while (a != 10 && a != 32 && a != -1) {
//				no = no * 10 + (a - '0');
//				a = in.read();
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return minus ? -no : no;
//	}

	int minDistance(int dist[], Boolean sptSet[], int n) {
		int min = Integer.MAX_VALUE, min_index = -1;

		for (int v = 0; v <= n; v++)
			if (sptSet[v] == false && dist[v] <= min) {
				min = dist[v];
				min_index = v;
			}

		return min_index;
	}

	int[] dijkstra(int graph[][], int src, int n) {
		int dist[] = new int[n + 1];
		Boolean sptSet[] = new Boolean[n + 1];

		for (int i = 0; i <= n; i++) {
			dist[i] = Integer.MAX_VALUE;
			sptSet[i] = false;
		}
		dist[src] = 0;
		for (int count = 0; count < n; count++) {
			int u = minDistance(dist, sptSet, n);
			sptSet[u] = true;
			for (int v = 0; v <= n; v++)
				if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
					dist[v] = dist[u] + graph[u][v];
		}
		return dist;

	}

	public long[] fib(int n, long a, long b) {
		/* Declare an array to store Fibonacci numbers. */
		long f[] = new long[n + 1];
		int i;

		/* 0th and 1st number of the series are 0 and 1 */
		f[0] = a;
		f[1] = b;

		for (i = 2; i <= n; i++) {
			/*
			 * Add the previous 2 numbers in the series and store it
			 */
			f[i] = (f[i - 1] % modOp + f[i - 2] % modOp) % modOp;
		}

		return f;
	}

	public static void main(String[] args) {
		int t, n, q, x, y;
		int v, k;
		long a, b;
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);


		Main1 prg = new Main1();
		t = in.readInt();
		while (t > 0) {
			n = in.readInt();
			q = in.readInt();
			int graph[][] = new int[n + 1][n + 1];

			long value[] = new long[n + 1];
			for (int i = 0; i < n - 1; i++) {
				x = in.readInt();
				y = in.readInt();
				graph[x][y] = 1;
				graph[y][x] = 1;
			}
			// query starts
			for (int i = 0; i < q; i++) {
				int qtype = in.readInt();
				if (qtype == 1) {
					int dist[] = new int[n + 1];
					v = in.readInt();
					k = in.readInt();
					a = in.readLong();
					b = in.readLong();
					dist = prg.dijkstra(graph, v, n);
					long f[] = prg.fib(n, a, b);
					for (int j = 1; j < n + 1; j++) {
						if (dist[j] <= k)
							value[j] = (value[j] % modOp + f[dist[j]] % modOp) % modOp;
					}
				} else {
					v = in.readInt();
					out.printLine(value[v]);
				}
			}

			t--;
		}

	}
}
