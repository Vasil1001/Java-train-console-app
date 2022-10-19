/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseworkjava;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vasilis
 */
public class CourseworkJava {

    static final int SEATING_CAPACITY = 8;

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        Scanner in = new Scanner(System.in);
        String[] train = new String[SEATING_CAPACITY];
        System.out.println("Welcome to the train application!");

        for (int i = 0; i < train.length; i++) {
            train[i] = "e";
        }

        boolean exit = false;

        while (!exit) {

            try {
                runMenu(); //display the options menu

                String choice = in.next().toUpperCase();

                switch (choice) {

                    case "A":
                        System.out.println("You chose add user, please type a name.");
                        addCust(train);
                        viewSeats(train);
                        //train[i] = in.nextLine();

                        break;

                    case "V":
                        System.out.println("You chose view seats option");
                        viewSeats(train);
                        break;

                    case "E":

                        System.out.println("You chose display empty room");
                        System.out.println("'e' represents empty seats available.");
                        eSeats(train);
                        break;

                    case "D":
                        System.out.println("You chose delete user");
                        deleteCustomer(train);
                        viewSeats(train);
                        break;

                    case "F":
                        System.out.println("You chose display empty room");
                        findCustomer(train);
                        viewSeats(train);
                        break;

                    case "S":
                        System.out.println("You chose store data to file");
                        storeFile(train);
                        System.out.println("The data has been saved on a file!");
                        break;

                    case "L":
                        System.out.println("You chose load file!");
                        loadFile(train);
                        System.out.println("The data from file has been loaded!");
                        viewSeats(train);
                        break;

                    case "O":
                        System.out.println("You chose view seats Ordered alphabetically by name");
                        bubbleSort(train);
                        break;

                    case "Q":
                        System.out.println("Thanks for using our program, bye!");
                        exit = true;
                        break;

                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(CourseworkJava.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private static void viewSeats(String[] train) {
        for (String train1 : train) {
            System.out.println(train1);
        }
    }

    private static void addCust(String[] train) {
        Scanner Customer = new Scanner(System.in);

        String Customer_name = Customer.next();
        System.out.println("Please select a seat up to 8. ");

        int position = Customer.nextInt();
        if (position > 8 || position < 1) {
            System.out.println("Seat does not exist.");
        } else {
            train[position - 1] = Customer_name;

        }
    }

    private static void deleteCustomer(String[] train) {
        Scanner Customer = new Scanner(System.in);
        System.out.println("Enter a name to delete ");

        String Customer_delete = Customer.next();
        for (int i = 0; i < train.length; i++) {
            if (train[i].equals(Customer_delete)) {
                train[i] = "e";
                return;

            }
        }
        System.out.println("Name does not exist");

    }

    private static void findCustomer(String[] train) {
        Scanner Customer = new Scanner(System.in);
        System.out.println("Enter a name to find ");
        String Customer_find = Customer.next();

        for (String train1 : train) {
            if (train1.equals(Customer_find)) {
                System.out.println("The user exist: " + Customer_find);
                return;
            }
        }
        System.out.println("Name does not exist");

    }

    private static void eSeats(String[] train) {
        //go through train array and
        //if a room is empty - display it
        for (int i = 0; i < train.length; i++) {
            if ("e".equals(train[i])) {
                System.out.println((i + 1) + " is empty");
            }
        }
    }

    private static void storeFile(String[] train) throws IOException {
        try ( FileWriter storeData = new FileWriter("file.txt")) {
            for (int i = 0; i < train.length; i++) {
                storeData.write(train[i] + "\n");
            }
        }
    }

    private static void loadFile(String[] train) throws FileNotFoundException, IOException {

        try{
            int i = 0;
            BufferedReader reader;
            reader = new BufferedReader(new FileReader("file.txt"));
            
            String line = reader.readLine();
            while ((line != null)) {
                train[i]=line;
                line = reader.readLine();
                i++;
            }
            reader.close();
        }
        catch (FileNotFoundException ex){
              System.out.println("Could not find given file");
              }
        }

    

    private static void bubbleSort(String[] train) {
        String value;
        for (int j = 0; j < train.length; j++) {

            for (int i = j + 1; i < train.length; i++) {

                if (train[i].compareTo(train[j]) < 0) {
                    value = train[j];
                    train[j] = train[i];
                    train[i] = value;
                }
            }
            System.out.println(train[j]);
        }
    }

    private static void runMenu() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("--");
        System.out.println("Enter a choice from the list below or 'Q' to exit");
        System.out.println("-------------------------");
        System.out.println("A: to add a new user");
        System.out.println("V: to view seats");
        System.out.println("E: to display empty seats");
        System.out.println("D: to delete customer from seat");
        System.out.println("F: to find the seat for a given customers name");
        System.out.println("S: to store program data in to file");
        System.out.println("L: to load program data from file");
        System.out.println("O: to view customer seats");
        System.out.println("Q: to quit");
    }

}
