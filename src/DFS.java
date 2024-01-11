import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class DFS {

    int nodeN; //number of nodes generated during the search
    State[] stateArray; //array to store visited states
    State initialState; //the initial state of the problem

    public DFS(State initialState) {
        this.initialState = initialState;
        stateArray = new State[100]; // Assuming a maximum of 100 states
        nodeN = 0;
    }

    //display path from initial state to final state
    public void displayPath() {
        int t = 0;

        // Get the final state
        State s = getFinalState();

        System.out.println("Number of generated nodes: " + nodeN);

        if (s == null) {
            System.out.println("No solution found.");
            return;
        }

        String[] str = new String[100];

        // Trace back the path from the final state to the initial state
        while (!s.equals(initialState)) {
            str[t] = s.toString();
            t++;
            s = stateArray[s.getParentState()];
        }
        str[t] = s.toString();

        System.out.println("DFS takes " + t + " steps.\n");

        // Display the path in reverse order
        for (int i = t; i >= 0; i--) {
            System.out.print(str[i]);
            if (i != 0) {
                System.out.print(" --> \n");
            }
        }
    }

    //perform DFS search and return the final state if found
    public State getFinalState() {

        // If the initial state is already the goal state, return it
        if (initialState.isItTheGoalState()) {
            return initialState;
        }

        nodeN = 0;

        Stack<State> stack = new Stack<>();

        //map to keep track of visited states and their indices in the stateArray
        HashMap<State, Integer> map = new HashMap<>();

        map.put(initialState, nodeN);

        initialState.setParentState(-1);

        stateArray[nodeN] = initialState;

        stack.push(initialState);

        // DFS search
        while (!stack.isEmpty()) {

            //pop a state from the stack to explore
            State u = stack.pop();
            //get the index of the popped state in the map
            int indexU = map.get(u);

            List<State> successors = u.getSuccessors();

            //explore successors
            for (State v : successors) {
                //check if the successor has not been visited
                if (!map.containsKey(v)) {
                    nodeN++;
                    v.setParentState(indexU);
                    stateArray[nodeN] = v;
                    if (v.isItTheGoalState()) {
                        return v;
                    }

                    //mark successor as visited and push it onto the stack for further exploration
                    map.put(v, nodeN);
                    stack.push(v);
                }
            }
        }
        return null;
    }
}