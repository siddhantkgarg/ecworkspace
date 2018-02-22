import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

class Node {
	public Node next;
	public int data;

	public Node() {
		this.data = 0;
		this.next = null;
	}

	public Node(Node next, int data) {
		this.next = next;
		this.data = data;
	}

	public void addNext(Node node) {
		this.next = node;
	}

}

public class ListTraversals {

	public static Node head;

	// Given a list, return a random list from it with equal probability such that
	// we don't travel
	// any node more than once.

	private static int getRandomNumber(int n) {
		Random rand = new Random();
		if (n - 1 > 0)
			return (rand.nextInt(n - 1) + 1);
		return 1;
	}

	public static Node getRandomNode(Node head) {
		if (head == null)
			return null;
		Node p = head;
		Node current = p;
		int i = 0;
		;
		while (p != null) {
			i++;
			int n = getRandomNumber(i);
			if (n == i)
				current = p;
			p = p.next;
		}
		return current;
	}

	public static void testRandomNodeGenerator() throws FileNotFoundException, UnsupportedEncodingException {
		Node head = createTestList();
		int n = 10000;
		 PrintWriter writer = new PrintWriter("testfile.txt","UTF-8");
		 
		while (n > 0) {
			writer.println(getRandomNode(head).data);
			n--;
		}
		writer.close();
	}

	public static void printList(Node head) {
		if (head != null) {
			System.out.print(head.data + "->");
			printList(head.next);
		} else {
			System.out.println("null");
		}
	}

	public static void reverse(Node current, Node prev) {
		if (current.next == null) {
			head = current;
			current.next = prev;
			return;
		}
		Node ffd = current.next;
		current.next = prev;
		reverse(ffd, current);
	}

	public static Node iterativeReverse(Node head) {
		if (head == null)
			return null;
		Node p = head;
		Node q = head.next;
		Node r = null;
		if (q != null) {
			r = q.next;
		}
		while (r != null) {
			if (p == head) {
				p.next = null;
			}
			q.next = p;
			p = q;
			q = r;
			r = r.next;
		}
		if (q != null) {
			q.next = p;
			head.next = null;
			head = q;
		}

		return head;
	}

	public static void runTests() {
		Node head = createTestList();
		printList(head);
		System.out.println("");
		// reverse(head,null);
		printList(head);
		// System.out.println("");
		Node revHead = iterativeReverse(head);
		printList(revHead);
	}

	public static Node createTestList() {
		Node one = new Node(null, 1);
		Node two = new Node(null, 2);
		Node three = new Node(null, 3);
		Node four = new Node(null, 4);
		Node five = new Node(null, 5);
		head = one;
		one.addNext(two);
		two.addNext(three);
		three.addNext(four);
		four.addNext(five);

		System.out.print("List : ");
		printList(head);
		return head;
	}

	public static void main(String s[]) {
		// runTests();
		try {
			testRandomNodeGenerator();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
