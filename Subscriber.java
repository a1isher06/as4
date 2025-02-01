public class Subscriber {
    private String name;
    private String phoneNumber;
    private double balance;
    private Tariff tariff;

    public Subscriber(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.balance = 0.0;  // Default balance is 0
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getBalance() {
        return balance;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    // Replenish account balance
    public void replenishAccount(double amount) {
        this.balance += amount;
    }
}
