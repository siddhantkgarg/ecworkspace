public class Graph {

    private Node[] vertices; // stores the nodes of the graph
    private int size; // number of nodes in the graph
   // private MinPriorityQueue queue;

    public Graph(int size) {
        this.size = size;
        vertices = new Node[size];
        addNodes();
     //	   queue = new MinPriorityQueue(size);
    }
    public enum State {
        NEW, IN_Q, VISITED
    }
    public class Node {
        int name;
        int cost;
        Neighbour neighbourList;
        State state;

        Node(int name) {
            this.name = name;
            state = State.NEW;
            cost = Integer.MAX_VALUE;
        }
    }

    public class Neighbour {
        int index;
        int weight;
        Neighbour next;

        public Neighbour(int index, Neighbour next, int weight) {
            this.index = index;
            this.next = next;
            this.weight = weight;
        }
    }

    private void addNodes() {
        for (int i = 1; i <= size; i++) {
            addNode(i);
        }
    }

    public void addNode(int name) {
        vertices[name - 1] = new Node(name);
    }

    public void addEdge(int sourceName, int destiName, int weight) {
        int srcIndex = sourceName - 1;
        int destiIndex = destiName - 1;
        Node srcNode = vertices[srcIndex];
        Node destiNode = vertices[destiIndex];
        srcNode.neighbourList = new Neighbour(destiIndex, srcNode.neighbourList, weight);
        // the graph is non directional so if from S, D is reachable then vice
        // versa is also true
        destiNode.neighbourList = new Neighbour(srcIndex, destiNode.neighbourList, weight);
    }
}