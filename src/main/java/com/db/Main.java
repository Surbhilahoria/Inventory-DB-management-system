package com.db;

import java.util.Scanner;

public class Main {
    private static final InventoryManager manager = new InventoryManager();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        DatabaseConnector.createNewDatabaseWithTable("database/inventory.db");
        while (true) {
            System.out.println("\nInventory Management System");
            System.out.println("1. Add Item");
            System.out.println("2. Update Item");
            System.out.println("3. Delete Item");
            System.out.println("4. List Items");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = readInt("", scanner);
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    updateItem();
                    break;
                case 3:
                    deleteItem();
                    break;
                case 4:
                    manager.listItems();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter again.");
            }
        }
    }
    private static void addItem() {
        System.out.print("Enter ID: ");
        int id = readInt("", scanner);
        scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        double price = readDouble("Enter Price: ", scanner);
        int quantity = readInt("Enter Quantity: ", scanner);
        scanner.nextLine();

        manager.addItem(new InventoryItem(id, name, price, quantity));
        System.out.println("Item added successfully!");
    }
    private static void updateItem() {
        int id = readInt("Enter ID of item to update: ", scanner);
        int quantity = readInt("Enter new Quantity: ", scanner);
        manager.updateItem(id, quantity);
        System.out.println("Item updated successfully!");
    }
    private static void deleteItem() {
        int id = readInt("Enter ID of item to delete: ", scanner);
        manager.deleteItem(id);
        System.out.println("Item deleted successfully!");
    }
    private static int readInt(String prompt, Scanner scanner) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Please enter a valid integer: ");
        }
        return scanner.nextInt();
    }
    private static double readDouble(String prompt, Scanner scanner) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            scanner.next();
            System.out.print("Please enter a valid number: ");
        }
        return scanner.nextDouble();
    }
}
