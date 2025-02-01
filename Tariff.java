import java.util.*;

public class Tariff {
    private String name;
    private Company company;
    private List<Subscriber> subscribers;

    public Tariff(String name, Company company) {
        this.name = name;
        this.company = company;
        this.subscribers = new ArrayList<>();
    }

    // Add a subscriber to the tariff
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
        subscriber.setTariff(this);  // Set the tariff for the subscriber
    }

    // Remove a subscriber by phone number
    public boolean removeSubscriberByPhone(String phoneNumber) {
        for (Subscriber subscriber : subscribers) {
            if (subscriber.getPhoneNumber().equals(phoneNumber)) {
                subscribers.remove(subscriber);
                return true;
            }
        }
        return false;
    }

    // Find a subscriber by phone number
    public Subscriber findSubscriber(String phoneNumber) {
        for (Subscriber subscriber : subscribers) {
            if (subscriber.getPhoneNumber().equals(phoneNumber)) {
                return subscriber;
            }
        }
        return null;
    }

    public int getSubscriberCount() {
        return subscribers.size();
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public String getName() {
        return name;
    }

    public Company getCompany() {
        return company;
    }
}
