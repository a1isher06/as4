import java.util.*;

public class Company {
    private String name;
    private List<Tariff> tariffs;

    public Company(String name) {
        this.name = name;
        this.tariffs = new ArrayList<>();
    }

    // Add a tariff to the company
    public void addTariff(Tariff tariff) {
        tariffs.add(tariff);
    }

    // Find a subscriber by phone number
    public Subscriber findSubscriber(String phoneNumber) {
        for (Tariff tariff : tariffs) {
            Subscriber subscriber = tariff.findSubscriber(phoneNumber);
            if (subscriber != null) {
                return subscriber;
            }
        }
        return null;
    }

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    public String getName() {
        return name;
    }

    // Remove subscriber from the company
    public boolean removeSubscriberByPhone(String phoneNumber) {
        for (Tariff tariff : tariffs) {
            if (tariff.removeSubscriberByPhone(phoneNumber)) {
                return true;
            }
        }
        return false;
    }
}
