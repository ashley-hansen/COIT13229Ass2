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
                        + "Enter '3' to exit\n");
                orderType = input.nextInt();
                    if((orderType) == 1){
                        System.out.println("please enter the order qty: \n");
                        quantity = input.nextInt();
                        System.out.println("please enter the price for each unit: \n");
                        unitPrice = input.nextInt();
                        BookOrder bo = new BookOrder(quantity,unitPrice);
                        String bookOrder = "book";
                        out.writeObject(bookOrder);
                        out.writeObject(bo);
                        String orderTotal = (String) in.readObject();
                        System.out.println("Recieving details from server...");
                        System.out.println(orderTotal);
                    }
                    else if((orderType)== 2){
                       System.out.println("please enter the order qty: \n");
                        quantity = input.nextInt();
                        System.out.println("please enter the price for each unit: \n");
                        unitPrice = input.nextInt();
                        MovieOrder mo = new MovieOrder(quantity,unitPrice);
                        String movieOrder = "movie";
                        out.writeObject(movieOrder);
                        out.writeObject(mo);
                        System.out.println(mo.getResult()); 
                    }
                    else if((orderType)==3){
                        moreOrders = false;
                    }
                    else{
                    System.out.println("Please enter a valid option.");
                    }
                
                
//                  read object test
//                String test = (String) in.readObject();
//                System.out.println(test);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
//        catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
