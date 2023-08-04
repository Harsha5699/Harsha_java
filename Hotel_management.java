package com.project;
import java.util.Scanner;

public class Hotel_management {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int input;
        System.out.println("");
        System.out.println("\t\tWelcome to Hotel Management");
        System.out.println("\t       please choose \n" +
                "\n" +
                "\t        1) Create an account\n" +
                "\n" +
                "\t        2) Display my account details(account already available)\n" +
                "\n" +
                "\t        3) Do hotel reservation\n" +
                "\n" +
                "\t        4) Modify the reservation\n" +
                "\n" +
                "\t        5) Cancel reservation\n" +
                "\n" +
                "\t        6) Delete my account\n" +
                "\n" +
                "\t        7) Show details");

            input = readinput();
            switch (input) {
                case 1:
                    System.out.println("You entered: " + input + "). Create an Account");
                    break;
                case 2:
                    System.out.println("You entered: " + input + "). Display my account details(account already available)");
                    break;
                case 3:
                    System.out.println("You entered: " + input + "). Do hotel reservation");
                    break;
                case 4:
                    System.out.println("You entered: " + input + "). Modify the reservation");
                    break;
                case 5:
                    System.out.println("You entered: " + input + "). Cancel reservation");
                    break;
                case 6:
                    System.out.println("You entered: " + input + "). Delete my account");
                    break;
                case 7:
                    System.out.println("You entered: " + input + "). Show details");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                   break;
        }
    }
        private static int readinput() {

            int input;

            while (true) {
                if (scanner.hasNextInt()) {
                    input = scanner.nextInt();
                    if (input >= 0 && input <= 7) {
                        break;
                    }
                } else {
                    scanner.next(); // Consume the invalid input
                }
                System.out.println("Invalid choice. Please try again between 1 and 7.");
            }
            return input;
        }
}


