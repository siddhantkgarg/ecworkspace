package StateMachine;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class State{
	int id;
	int eventId;
	public State(int id) {
		this.id = id;
	}
	public State(int id,int event) {
		this.id = id;
		this.eventId = event;
	}
	@Override
	public String toString() {
		return id+"";
	}
	
}
class StateGraph {
 
    private int stateCount; 
     
    private List<ArrayList<State>> adjList; 
    
    private List<State> reachableStates;
     
    public StateGraph(int count){
        this.stateCount = count;
       //initAdjList(); 
    }
    public StateGraph(int count,ArrayList<State> states){
        this.stateCount = count;
        initAdjList(states); 
    }
     
    private void initAdjList(ArrayList<State> states)
    {
        adjList = new ArrayList<ArrayList<State>>(stateCount);
        
        for(int i = 0; i < stateCount; i++)
        {
            adjList.add(new ArrayList<State>());
        }
    }
     
    public void addEdge(State src, State dest,int eventId)
    {
    	dest.eventId = eventId;
        adjList.get(src.id).add(dest); 
    }
     
    public List<State> printAllPaths(State src,State dest) 
    {
        boolean[] isVisited = new boolean[stateCount];
        ArrayList<State> pathList = new ArrayList<>();
        reachableStates = new ArrayList<>();
        pathList.add(src);
         
        printPathsUtil(src, dest, isVisited, pathList);
        return reachableStates;
    }
    
 
    private void printPathsUtil(State src, State dest,
                                    boolean[] isVisited,
                            List<State> localPathList) {
         
        isVisited[src.id] = true;
         
        if (src.equals(dest)) 
        {
        	reachableStates.addAll(localPathList);
//            /System.out.println(localPathList);
            //return;
        }
        
        for (State i : adjList.get(src.id)) 
        {
        	
            if (!isVisited[i.id])
            {
                localPathList.add(i);
                printPathsUtil(i, dest, isVisited, localPathList);
                localPathList.remove(i);
            }
        }
        isVisited[src.id] = false;
    }
}

public class StateMachine{
	
	
	public static void runTest() {
		ArrayList<State> stateList = new ArrayList<State>();
		State s0 = new State(0);
		stateList.add(s0);
		State s1 = new State(1);
		stateList.add(s1);
		State s2 = new State(2);
		stateList.add(s2);
		State s3 = new State(3);
		stateList.add(s3);
		State s4 = new State(4);
		stateList.add(s4);
		State s5 = new State(5);
		stateList.add(s5);
		State s6 = new State(6);
		stateList.add(s6);
		State s7 = new State(7);
		stateList.add(s7);
		State s8 = new State(8);
		stateList.add(s8);
		State s9 = new State(9);
		stateList.add(s9);
		
		StateGraph graph = new StateGraph(10,stateList);
		graph.addEdge(s0, s1,1);
		graph.addEdge(s1, s5,2);
		graph.addEdge(s1, s4,3);
		graph.addEdge(s1, s3,4);
		graph.addEdge(s3, s8,5);
		graph.addEdge(s0, s2,6);
		graph.addEdge(s2, s7,7);
		graph.addEdge(s2, s6,8);
		graph.addEdge(s0, s1,9);
		graph.addEdge(s6, s9,10);
		
		
		//Test case 1
		List<State> pathList = graph.printAllPaths(s0, s9);
		List<State> ansList = new ArrayList(Arrays.asList(s0,s2,s6,s9));
		System.out.println(pathList);
		if(pathList.equals(ansList)) 
			System.out.println("Test 1 passed");
		
		//Test case 2
		List<State> ansList2 = new ArrayList<State>();
		pathList = graph.printAllPaths(s1, s9);
		System.out.println(pathList);
		if(pathList.equals(ansList2)) 
			System.out.println("Test 2 passed");
		
	}
	public static void main(String s[]) {
		runTest();
	}
}