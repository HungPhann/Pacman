package group2.entity.food;

public abstract class Food {
    private int score;

    public int getScore(){
        return score;
    }

    protected void setScore(int score) {
        this.score = score;
    }

    public abstract boolean isSpecial();
}



