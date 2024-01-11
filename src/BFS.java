import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {

    int nodeN; //number of nodes generated during the search
    State[] stateArray; //array to store visited states
    State initialState; //the initial state of the problem

    public BFS(State initialState) {
        this.initialState = initialState;
        stateArray = new State[100];
        nodeN = 0;
    }

    //method to print path from the initial to the final state
    public void displayPath() {
        int t = 0;
        State s = getFinalState();

        System.out.println("Number of generated nodes: " + nodeN);

        if (s == null) {
            System.out.println("No solution found.");
            return;
        }

        String[] str = new String[100005];

        //path reconstruction
        while (!s.equals(initialState)) {
            str[t] = s.toString();
            t++;
            s = stateArray[s.getParentState()];
        }
        str[t] = s.toString();

        System.out.println("BFS takes " + t + " steps.\n");

        //print reconstructed path
        for (int i = t; i >= 0; i--) {
            System.out.print(str[i]);
            if (i != 0) {
                System.out.print(" --> \n");
            }
        }
    }

    //method to find final state using Breadth-First Search
    public State getFinalState() {

        //if the initial state already the goal state, return it
        if (initialState.isItTheGoalState()) {
            return initialState;
        }

        nodeN = 0; // Reset the node count

        Queue<State> queue = new LinkedList<>(); //for BFS traversal
        HashMap<State, Integer> map = new HashMap<>(); //for state tracking

        map.put(initialState, nodeN);
        initialState.setParentState(-1); //set parent state -1 to mark it as the root
        stateArray[nodeN] = initialState;
        queue.add(initialState);

        while (!queue.isEmpty()) {
            State u = queue.poll();
            int indexU = map.get(u);

            List<State> successors = u.getSuccessors();

            for (State v : successors) {
                //process successor state
                if (!map.containsKey(v)) {

                    nodeN++;
                    v.setParentState(indexU);
                    stateArray[nodeN] = v;

                    //if successor is goal state, return it
                    if (v.isItTheGoalState()) {
                        return v;
                    }

                    //track state in the map and enqueue for further exploration
                    map.put(v, nodeN);
                    queue.add(v);
                }
            }
        }

        //if no solution found, return null
        return null;
    }
}