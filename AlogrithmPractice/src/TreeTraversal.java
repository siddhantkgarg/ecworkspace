import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

class TreeNode{
	TreeNode left;
	TreeNode right;
	int data;
	
	public TreeNode(TreeNode left,TreeNode right,int data) {
		this.left = left;
		this.right = right;
		this.data = data;
	}
	public void addLeft(TreeNode node) {
		this.left = node;
	}
	public void addRight(TreeNode node) {
		this.right = node;
	}
}
public class TreeTraversal {
	
	public static void inorderTraversal(TreeNode root) {
		if(root!=null) {
			inorderTraversal(root.left);
			System.out.print(root.data + " ");
			inorderTraversal(root.right);
		}
	}
	
	
	public static TreeNode lca(TreeNode root,TreeNode a,TreeNode b) {
		if(root==null) return root;
		if(root == a || root == b) return root;
		TreeNode left = lca(root.left,a,b);
		TreeNode right = lca(root.right,a,b);
		if(left !=null && right!=null)
			return root;
		if(left!=null) return left;
		if(right!=null) return right;
		return null;
	}
	
	public static int distance(TreeNode root, TreeNode a,TreeNode b) {
		TreeNode lc = lca(root,a,b);
		int distA = distanceUtil(lc,a);
		System.out.println("A :" + distA);
		int distB = distanceUtil(lc, b);
		System.out.println("B :" + distB);
		return distA + distB;
	}
	private static int distanceUtil(TreeNode root, TreeNode a) {
		if(root == null)
			return 0;
		if(root.left == a || root.right==a)
			return 1;
		int left = distanceUtil(root.left,a);
		int right = distanceUtil(root.right,a);
		if(left!=0 || right !=0)
			return 1+left+right;
		return 0;
	}
	
	public static Map<Integer,Integer> verticalSum(TreeNode root) {
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		 verticalSumUtil(root,0,map);
		 return map;
	}
	private static void verticalSumUtil(TreeNode root,int index,HashMap<Integer,Integer> map){
		if(root!=null) {
			int sum = 0;
			if(map.containsKey(index)) {
				sum = map.get(index) + root.data;
			}else {
				sum = root.data;
			}
			map.put(index, sum);
			verticalSumUtil(root.left,index-1,map);
			verticalSumUtil(root.right,index+1,map);
		}
		
	}
	private static void printTree(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		List<Integer> space = new LinkedList<Integer>();
		if(root == null) {
			System.out.println("Empty Tree");
			return;
		}
		queue.add(root);
		queue.add(null);
		space.add(0);
		space.add(null);
		int min =0;
		int listIndex = 0;
		while(!queue.isEmpty()) {
			TreeNode node = queue.remove();
			if(node!=null) {
				Integer dist = space.get(listIndex);
				
				if(node.left!=null) {
					queue.add(node.left);
					if((dist-1) < min ) {
						min = dist - 1;
					}
					space.add(dist-1);
				}
				if(node.right!=null) {
					queue.add(node.right);
					space.add(dist+1);
				}
			}
			else {
				if(queue.size()==0)
					break;
				queue.add(null);
				space.add(null);
			}
			listIndex++;
			
		}
		
		queue.add(root);
		queue.add(null);
		min = Math.abs(min);
		Integer levelSpace = 0;
		Queue<Integer> spaceQueue = (Queue<Integer>) space;
		while(!queue.isEmpty()) {
			TreeNode node = queue.remove();
			Integer sp = spaceQueue.remove();
			if(node!=null) {
				int actualSpaces = (sp+min)-(levelSpace);; 
				while(actualSpaces>0) {
					levelSpace++;
					System.out.print(" ");
					actualSpaces--;
				}
				System.out.print(node.data+ " ");
				if(node.left!=null) {
					queue.add(node.left);
				}
				if(node.right!=null) {
					queue.add(node.right);
				}
			}
			else {
				if(queue.size()==0)
					break;
				queue.add(null);
				levelSpace = 0;
				System.out.println("");
			}
			
		}
		
	}
	
	public static void main(String s[]) {
		TreeNode root = new TreeNode(null,null,0);
		TreeNode o = new TreeNode(null,null,1);
		TreeNode t = new TreeNode(null,null,2);
		TreeNode th = new TreeNode(null,null,3);
		TreeNode f = new TreeNode(null,null,4);
		TreeNode fv = new TreeNode(null,null,5);
		TreeNode sx = new TreeNode(null,null,6);
		TreeNode sv = new TreeNode(null,null,7);
		TreeNode eight = new TreeNode(null,null,8);
		TreeNode nine = new TreeNode(null,null,9);
		TreeNode ten = new TreeNode(null,null,10);
		TreeNode eleven = new TreeNode(null,null,11);
		TreeNode twelve = new TreeNode(null,null,12);
		
		
		
		
		
		
		
		
		
		root.addLeft(o);
		root.addRight(t);
		
		o.addLeft(th);
		o.addRight(f);
		
		t.addLeft(fv);
		t.addRight(sx);
		
		f.addRight(sv);
		
		sv.addLeft(eight);
		sv.addRight(nine);
		
		nine.addLeft(ten);
		nine.addRight(eleven);
		
		fv.addRight(twelve);
//		//inorderTraversal(root);
		printTree(root);
		
		Map<Integer,Integer> map = verticalSum(root);
		System.out.println("");
		for (Entry<Integer,Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		System.out.println(lca(root,sv,th).data);
		System.out.println(distance(root, sv, twelve));
		//TreeNode lcanode = lca(root,sv,twelve);
		//if(lcanode!=null) System.out.println(lcanode.data);
		//	else System.out.println("null");
	}
}