package com.project;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Hotel_Reservation {

        private static Map<String, JSONObject> accounts = new HashMap<>();
        private static String currentAccount = null;
        private static JSONObject accountDetails = null;

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Welcome to Hotel Reservation System!");
                System.out.println("Please choose an option:");
                System.out.println("1. Create an account");
                System.out.println("2. Display my account details (account already available)");
                System.out.println("3. Do hotel reservation");
                System.out.println("4. Modify the reservation");
                System.out.println("5. Cancel reservation");
                System.out.println("6. Delete my account");
                System.out.println("7. Show details");
                System.out.println("Enter 0 to quit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        createAccount(scanner);
                        break;
                    case 2:
                        displayAccountDetails();
                        break;
                    case 3:
                        doHotelReservation(scanner);
                        break;
                    case 4:
                        modifyReservation(scanner);
                        break;
                    case 5:
                        cancelReservation();
                        break;
                    case 6:
                        deleteAccount();
                        break;
                    case 7:
                        showDetails();
                        break;
                    case 0:
                        System.out.println("Thank you for using Hotel Reservation System. Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }

        public static void createAccount(Scanner scanner)
        {


            System.out.println("Enter your address:");
            String address = scanner.next();

            System.out.println("Enter your phone number:");
            String phoneNumber = scanner.next();

            System.out.println("Enter your email address:");
            String email = scanner.next();

            String accountNumber = generateAccountNumber();
            String reservationNumber = generateReservationNumber();
            String folderPath = "C:/Harsha/Java/" + accountNumber;

            String filePath = folderPath + "/" + accountNumber + ".json";
            File folder = new File(folderPath);
            
            if (!folder.exists()) {
                boolean created = folder.mkdirs(); // Try creating the folder
                if (!created) {
                    System.out.println("Error creating the account folder. Please try again.");
                    return;
                }
            }
            // Create the JSON object and populate it with user data
            JSONObject accountDetails = new JSONObject();
            accountDetails.put("account_number", accountNumber);
            accountDetails.put("user_address", address);
            accountDetails.put("phone_number", phoneNumber);
            accountDetails.put("email_id", email);
            accountDetails.put("reservations", new JSONArray());
            // Write the JSON object to the file
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(accountDetails.toJSONString());
                System.out.println("Account created successfully with account number: " + accountNumber);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error creating account. Please try again.");
            }
            currentAccount = accountNumber;
            System.out.println("Account folder created at: " + folderPath);
            JSONArray reservationsArray = (JSONArray) accountDetails.get("reservations");
            reservationsArray.add(reservationNumber);
            accountDetails.put("reservations", reservationsArray);
            accounts.put(accountNumber, accountDetails);

        }

        private static String generateAccountNumber()
        {
            return "A" + (long) (Math.random() * 9_000_000_000L + 1_000_000_000L);
        }

        private static void displayAccountDetails() {
            if (currentAccount != null) {
                System.out.println("Username: " + currentAccount);
                System.out.println("Password: " + accounts.get(currentAccount));
            } else {
                System.out.println("No account is currently logged in.");
            }
        }

        public static void doHotelReservation(Scanner scanner) {
            if (currentAccount == null) {
                System.out.println("You must log in first to do a reservation.");
                return;
            }
            String reservationType;
            System.out.println("Enter 1 to book Villa");
            System.out.println("Enter 2 to book House");
            System.out.println("Enter 3 to book Resort");
            int choice = scanner.nextInt();
            switch(choice) {
                case 1:
                    reservationType = "V";
                    break;
                case 2:
                    reservationType = "H";
                    break;
                case 3:
                    reservationType = "R";
                    break;
                default:
                    System.out.println("Invalid choice. Reservation booking canceled.");
                    return;
            }
            String reservationNumber = generateReservationNumber();
            String filePath = "C:/Harsha/Java/" + currentAccount + "/" + reservationType + "-" + reservationNumber + ".json";


            // Create the JSON object for reservation details
            JSONObject reservationDetails = new JSONObject();
            reservationDetails.put("reservation_number", reservationNumber);
            reservationDetails.put("reservation_type", reservationType);
            // Add more reservation details here (e.g., check-in date, check-out date, etc.)

            // Write the JSON object to the file
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(reservationDetails.toJSONString());
                System.out.println("Reservation successfully booked with reservation number: " + reservationType + "-" + reservationNumber);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error booking the reservation. Please try again.");
            }
            JSONArray reservationsArray = (JSONArray) accounts.get(currentAccount).get("reservations");
            reservationsArray.add(reservationNumber);
            accounts.get(currentAccount).put("reservations", reservationsArray);

        }


    private static String generateReservationNumber() {
        return String.valueOf((long) (Math.random() * 9_000_000_000L + 1_000_000_000L));
    }
















        public static void modifyReservation(Scanner scanner) {
            if (currentAccount == null) {
                System.out.println("You must log in first to modify a reservation.");
            } else {// Your modify reservation logic here
                System.out.println("Modify reservation logic goes here.");
            }

        }


        private static void cancelReservation() {
            if (currentAccount == null) {
                System.out.println("You must log in first to cancel a reservation.");
                return;
            }

            // Your cancel reservation logic here
            System.out.println("Cancel reservation logic goes here.");
        }

        private static void deleteAccount() {
            if (currentAccount == null) {
                System.out.println("No account is currently logged in.");
                return;
            }

            accounts.remove(currentAccount);
            System.out.println("Account deleted successfully.");
            currentAccount = null;
        }

        private static void showDetails() {
            System.out.println("Current Account: " + currentAccount);
            System.out.println("Accounts: " + accounts);
        }


}
