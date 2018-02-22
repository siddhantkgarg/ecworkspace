import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.InputMismatchException;

public class Main {

	// Helper methods
	static final long modOp = 1000000007;

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

	// static BufferedInputStream in = new BufferedInputStream(System.in);

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

	public static class Graph {
		Vertex[] vertices;
		int maxSize;
		int size;
		

		public Graph(int maxSize) {
			this.maxSize = maxSize;
			vertices = new Vertex[maxSize];
		}

		public void addVertex(int name) {
			vertices[size++] = new Vertex(name);
		}

		public void addEdge(int sourceName, int destinationName, int weight) {
			int srcIndex = sourceName;
			int destiIndex = destinationName;
			vertices[srcIndex].adj = new Neighbour(destiIndex, weight, vertices[srcIndex].adj);
			vertices[destiIndex].indegree++;
		}

		public int[] findShortestPaths(int sourceName) {
			int dist[] = new int[maxSize];
			for (int i = 0; i < maxSize; i++) {
				vertices[i].cost = Integer.MAX_VALUE;
				vertices[i].state = State.NEW;
			}
			applyDikjstraAlgorith(vertices[sourceName]);
			for (int i = 0; i < maxSize; i++) {
				dist[i] = vertices[i].cost;
			}
			return dist;
		}

		public class Vertex {
			int cost;
			int name;
			Neighbour adj;
			int indegree;
			State state;

			public Vertex(int name) {
				this.name = name;
				cost = Integer.MAX_VALUE;
				state = State.NEW;
			}

			public int compareTo(Vertex v) {
				if (this.cost == v.cost) {
					return 0;
				}
				if (this.cost < v.cost) {
					return -1;
				}
				return 1;
			}
		}

		public enum State {
			NEW, IN_Q, VISITED
		}

		public class Neighbour {
			int index;
			Neighbour next;
			int weight;

			Neighbour(int index, int weight, Neighbour next) {
				this.index = index;
				this.next = next;
				this.weight = weight;
			}
		}

		public void applyDikjstraAlgorith(Vertex src) {
			Heap heap = new Heap(maxSize);
			heap.add(src);
			src.state = State.IN_Q;
			src.cost = 0;
			while (!heap.isEmpty()) {
				Vertex u = heap.remove();
				u.state = State.VISITED;
				Neighbour temp = u.adj;
				while (temp != null) {
					if (vertices[temp.index].state == State.NEW) {
						heap.add(vertices[temp.index]);
						vertices[temp.index].state = State.IN_Q;
					}
					if (vertices[temp.index].cost > u.cost + temp.weight) {
						vertices[temp.index].cost = u.cost + temp.weight;
						heap.heapifyUP(vertices[temp.index]);
					}
					temp = temp.next;
				}
			}
		}

		public static class Heap {
			private Vertex[] heap;
			private int maxSize;
			private int size;

			public Heap(int maxSize) {
				this.maxSize = maxSize;
				heap = new Vertex[maxSize];
			}

			public void add(Vertex u) {
				heap[size++] = u;
				heapifyUP(size - 1);
			}

			public void heapifyUP(Vertex u) {
				for (int i = 0; i < maxSize; i++) {
					if (u == heap[i]) {
						heapifyUP(i);
						break;
					}
				}
			}

			public void heapifyUP(int position) {
				int currentIndex = position;
				Vertex currentItem = heap[currentIndex];
				int parentIndex = (currentIndex - 1) / 2;
				Vertex parentItem = heap[parentIndex];
				while (currentItem.compareTo(parentItem) == -1) {
					swap(currentIndex, parentIndex);
					currentIndex = parentIndex;
					if (currentIndex == 0) {
						break;
					}
					currentItem = heap[currentIndex];
					parentIndex = (currentIndex - 1) / 2;
					parentItem = heap[parentIndex];
				}
			}

			public Vertex remove() {
				Vertex v = heap[0];
				swap(0, size - 1);
				heap[size - 1] = null;
				size--;
				heapifyDown(0);
				return v;
			}

			public void heapifyDown(int postion) {
				if (size == 1) {
					return;
				}

				int currentIndex = postion;
				Vertex currentItem = heap[currentIndex];
				int leftChildIndex = 2 * currentIndex + 1;
				int rightChildIndex = 2 * currentIndex + 2;
				int childIndex;
				if (heap[leftChildIndex] == null) {
					return;
				}
				if (heap[rightChildIndex] == null) {
					childIndex = leftChildIndex;
				} else if (heap[rightChildIndex].compareTo(heap[leftChildIndex]) == -1) {
					childIndex = rightChildIndex;
				} else {
					childIndex = leftChildIndex;
				}
				Vertex childItem = heap[childIndex];
				while (currentItem.compareTo(childItem) == 1) {
					swap(currentIndex, childIndex);
					currentIndex = childIndex;
					currentItem = heap[currentIndex];
					leftChildIndex = 2 * currentIndex + 1;
					rightChildIndex = 2 * currentIndex + 2;
					if (heap[leftChildIndex] == null) {
						return;
					}
					if (heap[rightChildIndex] == null) {
						childIndex = leftChildIndex;
					} else if (heap[rightChildIndex].compareTo(heap[leftChildIndex]) == -1) {
						childIndex = rightChildIndex;
					} else {
						childIndex = leftChildIndex;
					}
				}
			}

			public void swap(int index1, int index2) {
				Vertex temp = heap[index1];
				heap[index1] = heap[index2];
				heap[index2] = temp;
			}

			public boolean isEmpty() {

				return size == 0;
			}
		}
	}
	public static long[] fib(int n, long a, long b) {
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
		try {
			int t, n, q, x, y;
			int v, k;
			long a, b;
			File initialFile = new File("C:/Users/sigarg/Desktop/inputcf.txt");
		    InputStream targetStream = new FileInputStream(initialFile);
			InputReader in = new InputReader(targetStream);
			//InputReader in = new InputReader(System.in);
			OutputWriter out = new OutputWriter(System.out);

			t = in.readInt();
			Graph graph = new Graph(300001);
			while (t > 0) {
				n = in.readInt();
				q = in.readInt();
				
				for (int i = 0; i < n + 1; i++) {
					graph.addVertex(i);
				}

				long value[] = new long[n + 1];
				for (int i = 0; i < n - 1; i++) {
					x = in.readInt();
					y = in.readInt();
					graph.addEdge(x, y, 1);
					graph.addEdge(y, x, 1);
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
						dist = graph.findShortestPaths(v);
						long f[] = Main.fib(n, a, b);
						for (int j = 1; j < (n + 1); j++) {
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
			return;

		}
		catch(Exception e) {
			e.printStackTrace();
            return;
		}

		
	}

	// public static void main(String[] args) {
	//
	// Graph graph = new Graph(7);
	// for (int i = 0; i < 7; i++) {
	// graph.addVertex(i);
	// }
	// graph.addEdge(4, 5, 1);
	// graph.addEdge(5, 4, 1);
	// graph.addEdge(1, 5, 1);
	// graph.addEdge(5, 1, 1);
	// graph.addEdge(5, 2, 1);
	// graph.addEdge(2, 5, 1);
	// graph.addEdge(3, 4, 1);
	// graph.addEdge(4, 3, 1);
	// graph.addEdge(6, 1, 1);
	// graph.addEdge(1, 6, 1);
	// int dist[] = graph.findShortestPaths(4);
	// System.out.println(Arrays.toString(dist));
	// }
}
