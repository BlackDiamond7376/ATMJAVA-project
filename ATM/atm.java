package ATM;

import java.util.Scanner;
import java.io.*;

public class atm {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String userChoice;

        while (true) {
            System.out.println("\nWelcome to ATM");
            System.out.println("Enter 1 to make new account");
            System.out.println("Enter 2 to deposit money");
            System.out.println("Enter 3 to withdraw money");
            System.out.println("Enter 4 to check account balance");
            System.out.println("Type 'exit' to close the program");

            userChoice = sc.next();

            if (userChoice.equalsIgnoreCase("exit")) {
                break;
            }

            int choice;
            try {
                choice = Integer.parseInt(userChoice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number from 1 to 4 or 'exit'.");
                continue;
            }

            double balance = 0;
            String name, path, Ac, password;
            double deposit, withdraw;

            switch (choice) {
                case 1:
                    System.out.println("Enter your name");
                    name = sc.next();
                    path = "C:\\Users\\kartavya\\OneDrive\\Documents\\icse past year programs\\ATM\\Data\\" + name + ".txt";
                    try {
                        FileWriter writer = new FileWriter(path, false);
                        System.out.println("Enter your Account number");
                        Ac = sc.next();
                        System.out.println("Enter your password");
                        password = sc.next();
                        writer.write("Account number: " + Ac + "\n");
                        writer.write("Password: " + password + "\n");
                        writer.write("Balance: 0.0\n");
                        writer.close();
                        System.out.println("Account created successfully!");
                    } catch (IOException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("Enter your name");
                    name = sc.next();
                    path = "C:\\Users\\kartavya\\OneDrive\\Documents\\icse past year programs\\ATM\\Data\\" + name + ".txt";
                    File depositFile = new File(path);

                    if (depositFile.exists()) {
                        String acLine = "", passLine = "", line;
                        double currentBalance = 0.0;

                        try {
                            Scanner fileReader = new Scanner(depositFile);
                            while (fileReader.hasNextLine()) {
                                line = fileReader.nextLine();
                                if (line.startsWith("Account number:")) acLine = line;
                                else if (line.startsWith("Password:")) passLine = line;
                                else if (line.startsWith("Balance:")) {
                                    currentBalance = Double.parseDouble(line.substring(8).trim());
                                }
                            }
                            fileReader.close();
                        } catch (IOException | NumberFormatException e) {
                            System.out.println("Error reading file: " + e.getMessage());
                            continue;
                        }

                        System.out.println("Enter amount to deposit:");
                        deposit = sc.nextDouble();
                        currentBalance += deposit;

                        try {
                            FileWriter writer2 = new FileWriter(path, false);
                            writer2.write(acLine + "\n");
                            writer2.write(passLine + "\n");
                            writer2.write("Balance: " + currentBalance + "\n");
                            writer2.close();
                            System.out.println("Deposit successful! New Balance: " + currentBalance);
                        } catch (IOException e) {
                            System.out.println("Error writing file: " + e.getMessage());
                        }

                    } else {
                        System.out.println("No such account exists.");
                    }
                    break;

                case 3:
                    System.out.println("Enter your name");
                    name = sc.next();
                    path = "C:\\Users\\kartavya\\OneDrive\\Documents\\icse past year programs\\ATM\\Data\\" + name + ".txt";
                    File withdrawFile = new File(path);

                    if (withdrawFile.exists()) {
                        String acLine = "", passLine = "", line;
                        double currentBalance = 0.0;

                        try {
                            Scanner fileReader = new Scanner(withdrawFile);
                            while (fileReader.hasNextLine()) {
                                line = fileReader.nextLine();
                                if (line.startsWith("Account number:")) acLine = line;
                                else if (line.startsWith("Password:")) passLine = line;
                                else if (line.startsWith("Balance:")) {
                                    currentBalance = Double.parseDouble(line.substring(8).trim());
                                }
                            }
                            fileReader.close();
                        } catch (IOException | NumberFormatException e) {
                            System.out.println("Error reading file: " + e.getMessage());
                            continue;
                        }

                        System.out.println("Enter amount to withdraw:");
                        withdraw = sc.nextDouble();

                        if (withdraw > currentBalance) {
                            System.out.println("Insufficient funds!");
                        } else {
                            currentBalance -= withdraw;
                            try {
                                FileWriter writer2 = new FileWriter(path, false);
                                writer2.write(acLine + "\n");
                                writer2.write(passLine + "\n");
                                writer2.write("Balance: " + currentBalance + "\n");
                                writer2.close();
                                System.out.println("Withdrawal successful! New Balance: " + currentBalance);
                            } catch (IOException e) {
                                System.out.println("Error writing file: " + e.getMessage());
                            }
                        }
                    } else {
                        System.out.println("No such account exists.");
                    }
                    break;

                case 4:
                    System.out.println("Enter your name");
                    name = sc.next();
                    path = "C:\\Users\\kartavya\\OneDrive\\Documents\\icse past year programs\\ATM\\Data\\" + name + ".txt";
                    File balanceFile = new File(path);

                    if (balanceFile.exists()) {
                        double currentBalance = 0.0;
                        String line;

                        try {
                            Scanner fileReader = new Scanner(balanceFile);
                            while (fileReader.hasNextLine()) {
                                line = fileReader.nextLine();
                                if (line.startsWith("Balance:")) {
                                    currentBalance = Double.parseDouble(line.substring(8).trim());
                                }
                            }
                            fileReader.close();
                            System.out.println("Your current balance is: " + currentBalance);
                        } catch (IOException | NumberFormatException e) {
                            System.out.println("Error reading file: " + e.getMessage());
                        }
                    } else {
                        System.out.println("No such account exists.");
                    }
                    break;

                default:
                    System.out.println("Invalid choice! Enter 1-4 or 'exit'");
            }
        }

        sc.close();
        System.out.println("Thanks for using ATM. Goodbye!");
    }
}
