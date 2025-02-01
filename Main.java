import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Company company1 = new Company("MobileA");
        Company company2 = new Company("MobileB");

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add Tariff to Company");
            System.out.println("2. Add Subscriber to Tariff");
            System.out.println("3. Display Subscribers of a Company");
            System.out.println("4. Replenish Subscriber Account");
            System.out.println("5. Search Subscriber by Phone Number");
            System.out.println("6. Remove Subscriber");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter company name (MobileA or MobileB): ");
                    String companyName = scanner.nextLine();
                    System.out.print("Enter tariff name: ");
                    String tariffName = scanner.nextLine();
                    Tariff tariff = new Tariff(tariffName, companyName.equalsIgnoreCase("MobileA") ? company1 : company2);
                    if (companyName.equalsIgnoreCase("MobileA")) {
                        company1.addTariff(tariff);
                    } else if (companyName.equalsIgnoreCase("MobileB")) {
                        company2.addTariff(tariff);
                    } else {
                        System.out.println("Invalid company name.");
                    }
                    break;

                case 2:
                    System.out.print("Enter company name (MobileA or MobileB): ");
                    companyName = scanner.nextLine();
                    System.out.print("Enter tariff name: ");
                    tariffName = scanner.nextLine();
                    System.out.print("Enter subscriber name: ");
                    String subscriberName = scanner.nextLine();
                    System.out.print("Enter subscriber phone number: ");
                    String phoneNumber = scanner.nextLine();

                    Subscriber subscriber = new Subscriber(subscriberName, phoneNumber);
                    if (companyName.equalsIgnoreCase("MobileA")) {
                        for (Tariff t : company1.getTariffs()) {
                            if (t.getName().equalsIgnoreCase(tariffName)) {
                                t.addSubscriber(subscriber);
                                break;
                            }
                        }
                    } else if (companyName.equalsIgnoreCase("MobileB")) {
                        for (Tariff t : company2.getTariffs()) {
                            if (t.getName().equalsIgnoreCase(tariffName)) {
                                t.addSubscriber(subscriber);
                                break;
                            }
                        }
                    } else {
                        System.out.println("Invalid company name.");
                    }
                    break;

                case 3:
                    System.out.print("Enter company name (MobileA or MobileB): ");
                    companyName = scanner.nextLine();
                    if (companyName.equalsIgnoreCase("MobileA")) {
                        displaySubscribers(company1);
                    } else if (companyName.equalsIgnoreCase("MobileB")) {
                        displaySubscribers(company2);
                    } else {
                        System.out.println("Invalid company name.");
                    }
                    break;

                case 4:
                    System.out.print("Enter subscriber phone number: ");
                    phoneNumber = scanner.nextLine();
                    System.out.print("Enter amount to replenish: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline

                    Subscriber sub = company1.findSubscriber(phoneNumber);
                    if (sub == null) {
                        sub = company2.findSubscriber(phoneNumber);
                    }

                    if (sub != null) {
                        sub.replenishAccount(amount);
                        System.out.println("Account replenished. New balance: " + sub.getBalance());
                    } else {
                        System.out.println("Subscriber not found.");
                    }
                    break;

                case 5:
                    System.out.print("Enter subscriber phone number: ");
                    phoneNumber = scanner.nextLine();
                    Subscriber foundSubscriber = company1.findSubscriber(phoneNumber);
                    if (foundSubscriber == null) {
                        foundSubscriber = company2.findSubscriber(phoneNumber);
                    }

                    if (foundSubscriber != null) {
                        System.out.println("Found subscriber:");
                        System.out.println("Name: " + foundSubscriber.getName() +
                                ", Tariff: " + foundSubscriber.getTariff().getName());
                    } else {
                        System.out.println("Subscriber not found.");
                    }
                    break;

                case 6:
                    System.out.print("Enter subscriber phone number to remove: ");
                    phoneNumber = scanner.nextLine();
                    if (company1.removeSubscriberByPhone(phoneNumber) || company2.removeSubscriberByPhone(phoneNumber)) {
                        System.out.println("Subscriber removed.");
                    } else {
                        System.out.println("Subscriber not found.");
                    }
                    break;

                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void displaySubscribers(Company company) {
        System.out.println("Subscribers of " + company.getName() + ":");
        for (Tariff tariff : company.getTariffs()) {
            for (Subscriber subscriber : tariff.getSubscribers()) {
                System.out.println("Name: " + subscriber.getName() +
                        ", Phone: " + subscriber.getPhoneNumber() +
                        ", Tariff: " + tariff.getName() +
                        ", Balance: " + subscriber.getBalance());
            }
        }
    }
}
