package com.will;

import com.will.Models.AcctChanges;
import com.will.helpers.DatabaseManager;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("Welcome to the ATM");


        Class.forName("org.sqlite.JDBC");

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:history.db")) {

            DatabaseManager dbm = new DatabaseManager(conn);

            while (true) {
                System.out.println("=====================");
                System.out.println("(1) Make a Deposit ");
                System.out.println("(2) Make a Withdrawal ");
                System.out.println("(3) Check Balance ");
                System.out.println("(4) See Account History ");
                System.out.println("(5) My business is done here ");
                Scanner scanner = new Scanner(System.in);

                String userInput = scanner.nextLine();

                if (userInput.equals("1")) {
                    System.out.println("What was this transaction for? ");
                    String changeName = scanner.nextLine();
                    System.out.println("How much would you like to deposit? ");
                    double changeAmount = scanner.nextDouble();

                    AcctChanges myChange = new AcctChanges(changeName, changeAmount);
                    dbm.insertIntoHistoryTable(myChange);
                } else if (userInput.equals("2")) {
                    System.out.println("Reason for withdrawal? ");
                    String changeName = scanner.nextLine();
                    System.out.println("How much would you like to withdraw? ");
                    double changeAmount = scanner.nextDouble();
                    double balance = 0;
                    ArrayList<AcctChanges> history = dbm.getHistory();
                    for (AcctChanges change : history) {
                        balance += change.getTransAmount();
                    }
                    if (changeAmount > balance) {
                        System.out.println("You do not have enough funds! Current balance is: " + " " + balance);
                    } else {
                        AcctChanges myChange = new AcctChanges(changeName, changeAmount * -1);
                        dbm.insertIntoHistoryTable(myChange);
                    }


                } else if (userInput.equals("3")) {
                    double balance = 0;
                    ArrayList<AcctChanges> history = dbm.getHistory();
                    for (AcctChanges change : history) {
                        balance += change.getTransAmount();
                    }
                    System.out.println(balance);
                } else if (userInput.equals("4")) {
                    ArrayList<AcctChanges> results = dbm.getHistory();
                        for (AcctChanges change : results) {
                            System.out.println(change);
                        }
                } else if (userInput.equals("5")) {
                    System.exit(0);
                } else {
                    break;
                }



            }

            

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("There was an error talking to the database.");
        }

        System.out.println("it's working!");
    }
}
