import java.util.ArrayList;

public class Player {
    public final String name;
    private int money;

    public Player(String pName, int pMoney){
        this.name = pName;
        this.money = pMoney;
    }

    public int getBalance(){ return this.money; }

    public void addBalance(int amount){ this.money += amount; }

    public boolean removeBalance(int amount){
        // Subtracts amount from balance, return false if that results in negative balance.
        this.money -= amount;
        return this.money > 0;
    }
}
