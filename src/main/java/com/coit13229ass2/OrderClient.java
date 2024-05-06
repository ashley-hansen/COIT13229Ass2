package com.coit13229ass2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class OrderClient {

    public static void main(String[] args) throws ClassNotFoundException {
        Socket socket = null;
        int quantity;
        int unitPrice;
        int orderType;
        boolean moreOrders = true;
        Scanner input = new Scanner(System.in);
        ObjectInputStream in = null;
        ObjectOutputStream out = null;

        try {
            int serverPort = 7611;
            while (moreOrders) {
                socket = new Socket("localhost", serverPort);
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());

                System.out.println("Please select the type of order you would like to place\n"
                        + "-------------------------------------------------------\n"
                        + "Enter '1' to place a book order\n"
                        + "Enter '2' to place a movie order\n"
                        + "Enter '3' to exit");
                orderType = input.nextInt();

                //Condition statement to create book order object and send to server coordinator
                if ((orderType) == 1) {
                    System.out.println("please enter the order qty: ");
                    quantity = input.nextInt();
                    System.out.println("please enter the price for each unit: ");
                    unitPrice = input.nextInt();
                    BookOrder bo = new BookOrder(quantity, unitPrice);
                    String bookOrder = "book";// Sring for server to deterind ordertype
                    out.writeObject(bookOrder);//serialise string object and send
                    out.writeObject(bo);//serialise book object and send
                    String orderTotal = (String) in.readObject();//read response from server cordinator
                    System.out.println("Recieving total from server...");//print message to notify user
                    System.out.println(orderTotal);// print order total
                } 

                //statement is identical to the above but creating a movie object instead of book
                else if ((orderType) == 2) {
                    System.out.println("please enter the order qty: ");
                    quantity = input.nextInt();
                    System.out.println("please enter the price for each unit: ");
                    unitPrice = input.nextInt();
                    MovieOrder mo = new MovieOrder(quantity, unitPrice);
                    String movieOrder = "movie";
                    out.writeObject(movieOrder);
                    out.writeObject(mo);
                    String orderTotal = (String) in.readObject();
                    System.out.println("Recieving total from server...");
                    System.out.println(orderTotal);
                } 
                //checks condition if user wishes to exit order system
                else if ((orderType) == 3) {
                    moreOrders = false;
                } else {
                    System.out.println("Please enter a valid option.");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
