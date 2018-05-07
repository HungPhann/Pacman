package entity.food;

public class Coin extends Food {
    public Coin() {
        this.setScore(10);
    }

    public boolean isSpecial(){
        return false;
    }
}
