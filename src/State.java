import java.util.ArrayList;
import java.util.List;

public class State {
    int parentState; //index of parent state in search tree
    int MissionaryOnLeft;
    int MissionaryOnRight;
    int CannibalOnLeft;
    int CannibalOnRight;
    int boatCapacity;
    int side; //current side of the boat (Constants.LEFT or Constants.RIGHT)

    //constructor
    public State(int MissionaryOnLeft, int CannibalOnLeft, int MissionaryOnRight, int CannibalOnRight,
                 int boatCapacity, int side) {

        this.MissionaryOnLeft = MissionaryOnLeft;
        this.CannibalOnLeft = CannibalOnLeft;
        this.MissionaryOnRight = MissionaryOnRight;
        this.CannibalOnRight = CannibalOnRight;
        this.boatCapacity = boatCapacity;
        this.side = side;
    }

    //check if state satisfies the problem constraints
    public boolean isThisAValidState() {

        if (MissionaryOnLeft >= 0 && CannibalOnLeft >= 0 && MissionaryOnRight >= 0 && CannibalOnRight >= 0
                && (MissionaryOnLeft == 0 || MissionaryOnLeft >= CannibalOnLeft)
                && (MissionaryOnRight == 0 || MissionaryOnRight >= CannibalOnRight)) {
            return true;
        }
        return false;
    }

    //check if state -> goal state
    public boolean isItTheGoalState() {
        if (MissionaryOnLeft == 0 && CannibalOnLeft == 0) {
            return true;
        }
        return false;
    }

    //generate and return list of valid successor states
    public List<State> getSuccessors() {
        List<State> successors = new ArrayList<>();
        generateSuccessors(successors);
        // printAll(successors);
        return successors;
    }

    //generate successor states based on current side of the boat
    public void generateSuccessors(List<State> successors) {
        //if the boat is on the left side
        if (side == Constants.LEFT) {
            //missionaries (i) and cannibals (j) on the left side
            for (int i = 0; i <= MissionaryOnLeft; i++) {
                for (int j = 0; j <= CannibalOnLeft; j++) {

                    /*check if at least one person is on the boat and the total number on the boat does
                      not exceed its capacity also, check if the number of missionaries is greater than
                      or equal to the number of cannibals on the boat*/
                    if ((i + j) != 0 && ((i + j) <= boatCapacity) && (i == 0 || i >= j)) {

                        //create new state representing the move to right side
                        State tempRight = new State(
                                MissionaryOnLeft - i, CannibalOnLeft - j,
                                MissionaryOnRight + i, CannibalOnRight + j,
                                boatCapacity, Constants.RIGHT);

                        //check if new state is valid
                        if (tempRight.isThisAValidState()) {
                            successors.add(tempRight);
                        }
                    }
                }
            }
        }

        //if the boat is on the right side
        else if (side == Constants.RIGHT) {
            for (int i = 0; i <= MissionaryOnRight; i++) {
                for (int j = 0; j <= CannibalOnRight; j++) {
                    // Check if at least one person is on the boat and the total number on the boat
                    // does not exceed its capacity
                    if ((i + j) != 0 && ((i + j) <= boatCapacity)) {
                        // Create a new state representing the move to the left side
                        State tempLeft = new State(
                                MissionaryOnLeft + i, CannibalOnLeft + j,
                                MissionaryOnRight - i, CannibalOnRight - j,
                                boatCapacity, Constants.LEFT);

                        if (tempLeft.isThisAValidState()) {
                            successors.add(tempLeft);
                        }
                    }
                }
            }
        }
    }

    //print all states in the given list
    public void printAll(List<State> l) {
        for (State s : l) {
            System.out.println(s);
        }
    }
    public int getParentState() {
        return parentState;
    }
    public void setParentState(int parentState) {
        this.parentState = parentState;
    }

    //override the hashCode method for object comparison
    @Override
    public int hashCode() {
        int hash = 7; // 7 and 61 are commonly used prime numbers
        hash = 61 * hash + this.MissionaryOnLeft;
        hash = 61 * hash + this.MissionaryOnRight;
        hash = 61 * hash + this.CannibalOnLeft;
        hash = 61 * hash + this.CannibalOnRight;
        hash = 61 * hash + this.side;
        return hash;
    }

    //override toString method for state representation
    @Override
    public String toString() {
        String leftMissionaries = generateEmojis("🚶", MissionaryOnLeft);
        String leftCannibals = generateEmojis("👹", CannibalOnLeft);

        String rightMissionaries = generateEmojis("🚶‍♂️", MissionaryOnRight);
        String rightCannibals = generateEmojis("👹", CannibalOnRight);

        String leftSide = "Left ⬅️ " + leftMissionaries + " Missionaries, " + leftCannibals + " Cannibals";
        String rightSide = "Right ➡️ " + rightMissionaries + " Missionaries , " + rightCannibals + " Cannibals";

        return leftSide + " , " + rightSide + " -->";
    }

    private String generateEmojis(String emoji, int count) {
        StringBuilder emojis = new StringBuilder();
        for (int i = 0; i < count; i++) {
            emojis.append(emoji);
        }
        return emojis.toString();
    }

    //override equals method for object comparison
    @Override
    public boolean equals(Object obj) {
        //check if the given object is an instance of the State class
        if (!(obj instanceof State)) {
            return false;
        }

        //cast the object to a State reference for comparison
        State s = (State) obj;

        //compare the individual attributes of the two states
        return (s.CannibalOnLeft == CannibalOnLeft && s.MissionaryOnLeft == MissionaryOnLeft
                && s.side == side && s.CannibalOnRight == CannibalOnRight
                && s.MissionaryOnRight == MissionaryOnRight);
    }
}