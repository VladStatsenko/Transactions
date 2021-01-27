public class Account {
    private long money;
    private String accNumber;
    private boolean blocked;

    public Account(String accNumber, long money) {
        this.accNumber = accNumber;
        this.money = money;
        this.blocked = false;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public long getBalance() {
        return money;
    }

    public void putMoney(long money) {
        this.money = this.money + money;
    }

    public void takeMoney(long money) {
        this.money = this.money - money;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}