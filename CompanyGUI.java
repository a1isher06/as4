import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompanyGUI {
    private JFrame frame;
    private Company company1;
    private Company company2;

    public CompanyGUI() {
        company1 = new Company("MobileA");
        company2 = new Company("MobileB");

        frame = new JFrame("Mobile Company Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new GridLayout(7, 1));

        JButton addTariffButton = new JButton("Add Tariff");
        JButton addSubscriberButton = new JButton("Add Subscriber");
        JButton displaySubscribersButton = new JButton("Display Subscribers");
        JButton replenishAccountButton = new JButton("Replenish Account");
        JButton searchSubscriberButton = new JButton("Search Subscriber");
        JButton removeSubscriberButton = new JButton("Remove Subscriber");

        addTariffButton.addActionListener(e -> addTariff());
        addSubscriberButton.addActionListener(e -> addSubscriber());
        displaySubscribersButton.addActionListener(e -> displaySubscribers());
        replenishAccountButton.addActionListener(e -> replenishAccount());
        searchSubscriberButton.addActionListener(e -> searchSubscriber());
        removeSubscriberButton.addActionListener(e -> removeSubscriber());

        frame.add(addTariffButton);
        frame.add(addSubscriberButton);
        frame.add(displaySubscribersButton);
        frame.add(replenishAccountButton);
        frame.add(searchSubscriberButton);
        frame.add(removeSubscriberButton);

        frame.setVisible(true);
    }

    private void addTariff() {
        String companyName = JOptionPane.showInputDialog("Enter company name (MobileA or MobileB):");
        String tariffName = JOptionPane.showInputDialog("Enter tariff name:");

        Company selectedCompany = companyName.equalsIgnoreCase("MobileA") ? company1 : companyName.equalsIgnoreCase("MobileB") ? company2 : null;
        if (selectedCompany != null) {
            Tariff tariff = new Tariff(tariffName, selectedCompany);
            selectedCompany.addTariff(tariff);
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid company name.");
        }
    }

    private void addSubscriber() {
        String companyName = JOptionPane.showInputDialog("Enter company name (MobileA or MobileB):");
        String tariffName = JOptionPane.showInputDialog("Enter tariff name:");
        String subscriberName = JOptionPane.showInputDialog("Enter subscriber name:");
        String phoneNumber = JOptionPane.showInputDialog("Enter phone number:");
        Subscriber subscriber = new Subscriber(subscriberName, phoneNumber);

        Company selectedCompany = companyName.equalsIgnoreCase("MobileA") ? company1 : companyName.equalsIgnoreCase("MobileB") ? company2 : null;
        if (selectedCompany != null) {
            for (Tariff t : selectedCompany.getTariffs()) {
                if (t.getName().equalsIgnoreCase(tariffName)) {
                    t.addSubscriber(subscriber);
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(frame, "Tariff or Company not found.");
    }

    private void displaySubscribers() {
        String companyName = JOptionPane.showInputDialog("Enter company name (MobileA or MobileB):");
        Company selectedCompany = companyName.equalsIgnoreCase("MobileA") ? company1 : companyName.equalsIgnoreCase("MobileB") ? company2 : null;
        if (selectedCompany == null) {
            JOptionPane.showMessageDialog(frame, "Invalid company name.");
            return;
        }

        StringBuilder sb = new StringBuilder("Subscribers:\n");
        for (Tariff tariff : selectedCompany.getTariffs()) {
            for (Subscriber subscriber : tariff.getSubscribers()) {
                sb.append("Name: ").append(subscriber.getName())
                        .append(", Phone: ").append(subscriber.getPhoneNumber())
                        .append(", Tariff: ").append(tariff.getName())
                        .append(", Balance: ").append(subscriber.getBalance())
                        .append("\n");
            }
        }
        JOptionPane.showMessageDialog(frame, sb.toString());
    }

    private void replenishAccount() {
        String phoneNumber = JOptionPane.showInputDialog("Enter subscriber phone number:");
        double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter amount to replenish:"));

        Subscriber sub = company1.findSubscriber(phoneNumber);
        if (sub == null) {
            sub = company2.findSubscriber(phoneNumber);
        }

        if (sub != null) {
            sub.replenishAccount(amount);
            JOptionPane.showMessageDialog(frame, "Account replenished. New balance: " + sub.getBalance());
        } else {
            JOptionPane.showMessageDialog(frame, "Subscriber not found.");
        }
    }

    private void searchSubscriber() {
        String phoneNumber = JOptionPane.showInputDialog("Enter subscriber phone number:");
        Subscriber foundSubscriber = company1.findSubscriber(phoneNumber);
        if (foundSubscriber == null) {
            foundSubscriber = company2.findSubscriber(phoneNumber);
        }

        if (foundSubscriber != null) {
            JOptionPane.showMessageDialog(frame, "Name: " + foundSubscriber.getName() + ", Tariff: " + (foundSubscriber.getTariff() != null ? foundSubscriber.getTariff().getName() : "None"));
        } else {
            JOptionPane.showMessageDialog(frame, "Subscriber not found.");
        }
    }

    private void removeSubscriber() {
        String phoneNumber = JOptionPane.showInputDialog("Enter subscriber phone number to remove:");
        if (company1.removeSubscriberByPhone(phoneNumber) || company2.removeSubscriberByPhone(phoneNumber)) {
            JOptionPane.showMessageDialog(frame, "Subscriber removed.");
        } else {
            JOptionPane.showMessageDialog(frame, "Subscriber not found.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CompanyGUI::new);
    }
}
