package etf.dotsandboxes.ma150129d.structs;

public final class Settings {
    private static int numberOfRows;
    private static int numberOfCols;
    private static int playerOne;
    private static int playerTwo;
    private static int treeDepth;

    public static int getNumberOfRows() {
        return numberOfRows;
    }

    public static void setNumberOfRows(int numberOfRows) {
        Settings.numberOfRows = numberOfRows;
    }

    public static int getNumberOfCols() {
        return numberOfCols;
    }

    public static void setNumberOfCols(int numberOfCols) {
        Settings.numberOfCols = numberOfCols;
    }

    public static int getPlayerOne() {
        return playerOne;
    }

    public static void setPlayerOne(int playerOne) {
        Settings.playerOne = playerOne;
    }

    public static int getPlayerTwo() {
        return playerTwo;
    }

    public static void setPlayerTwo(int playerTwo) {
        Settings.playerTwo = playerTwo;
    }

    public static int getTreeDepth() {
        return treeDepth;
    }

    public static void setTreeDepth(int treeDepth) {
        Settings.treeDepth = treeDepth;
    }
}
