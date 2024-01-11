import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("\nIn the missionaries and cannibals problem,\n" +
                "3 missionaries and 3 cannibals must cross a river \n" +
                "using a boat which can carry at most 2 people.\n");

        Scanner in = new Scanner(System.in);

        System.out.println("Enter D/d for Depth-First Search, " +
                "B/b for Breadth-First Search, or X/x to exit:");

        char type = in.next().charAt(0);

        while (type != 'x' && type != 'X') {

            State initialState = new State(
                    Constants.initialMissionaryOnLeft,
                    Constants.initialCannibalOnLeft,
                    0,
                    0,
                    Constants.boatCapacity,
                    Constants.LEFT
            );

            /* Set parent state to -1 to mark it as the
            root node of the search tree/graph */
            initialState.setParentState(-1);

            if (type == 'D' || type == 'd') {
                findSolutionDFS(initialState);
            } else if (type == 'B' || type == 'b') {
                findSolutionBFS(initialState);
            } else {
                System.out.println("Invalid input. Please enter D/d, B/b, or X/x.");
            }

            System.out.println("\n\nEnter D/d for Depth-First Search, " +
                    "B/b for Breadth-First Search, or X/x to exit:");

            type = in.next().charAt(0);
        }
    }

    private static void findSolutionDFS(State initialState) {
        System.out.println("Finding a solution using DFS algorithm.\n");
//        System.out.println(initialState);
        DFS dfs = new DFS(initialState);
        dfs.displayPath();

    }

    private static void findSolutionBFS(State initialState) {
        System.out.println("\n\nFinding a solution using BFS algorithm.\n");
        BFS bfs = new BFS(initialState);
        bfs.displayPath();
    }
}
