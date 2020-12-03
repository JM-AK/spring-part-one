package ru.geekbrains;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class OrderApp {

    public static void main(String[] args) {
        System.out.println("To separate arguments data use /");
        Scanner scanner = new Scanner(System.in);
        DBService dbService = DBService.getInstance();
        dbService.init();

        while (scanner.hasNext()){
            String message = scanner.nextLine();
            String[] cmdArr = message.split("/");
            String cmd = cmdArr[0];
            String[] cmdArgs = (cmdArr.length < 2)? null : cmdArr[1].split(" ");

            switch (cmd) {
                case ("exit"):
                    return;
                case ("addCategory"):
                    if (cmdArgs != null) {
                        dbService.addCategory(cmdArgs);
                        System.out.println("Success addCategory");
                    } else {
                        System.out.println("Empty args, try again");
                    }
                    break;
                case ("addProduct"):

                    if (cmdArgs.length == 4) {
                        try {
                            dbService.addProduct(cmdArgs[0], cmdArgs[1], cmdArgs[2], cmdArgs[3]);
                        } catch (DBService.DBServiceException e) {
                            System.out.println("Incorrect arguments");
                            break;
                        }
                        System.out.println("Success addProduct");
                    } else {
                        System.out.println("Empty args, try again");
                    }
                    break;
                case("addConsumer"):
                    if (cmdArgs.length == 4) {
                        dbService.addConsumer(cmdArgs);
                        System.out.println("Success addConsumer");
                    } else {
                        System.out.println("Empty args, try again");
                    }
                    break;
                case ("addOrder"):
                    if (cmdArgs.length > 1) {
                        dbService.addOrder(cmdArgs[0], Arrays.copyOfRange(cmdArgs,1, cmdArgs.length));
                        System.out.println("Success addOrder");
                    } else {
                        System.out.println("Empty args, try again");
                    }
                default:
                    System.out.println("Unknown command");
            }
        }
        dbService.close();
        scanner.close();
    }
}
