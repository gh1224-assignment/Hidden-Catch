public class GameState {
    private int level;
    private int correctNum, wrongNum;
    private boolean[] isFound;

    public void reset() {
        level = 0;
        correctNum = 0;
        wrongNum = 0;
        isFound = new boolean[5];
    }

    public void next() {
        level++;
        correctNum = 0;
        wrongNum = 0;
        isFound = new boolean[5];
    }

    public void correct(int idx) {
        correctNum++;
        isFound[idx] = true;
    }

    public void wrong() {
        wrongNum++;
    }

    public boolean isEnd() {
        return level == 3;
    }

    public boolean isWin() {
        return correctNum == 5;
    }

    public boolean isLose() {
        return wrongNum == 5;
    }

    public boolean hasFound(int idx) {
        return isFound[idx];
    }

    public int getLevel() {
        return level;
    }

    public int getCorrectNum() {
        return correctNum;
    }

    public int getWrongNum() {
        return wrongNum;
    }
}
