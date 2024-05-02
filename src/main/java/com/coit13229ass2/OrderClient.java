package com.coit13229ass2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class OrderClient {

    public static void main(String[] args) {
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
                        +          "-------------------------------------------------------\n"
                        + "Enter '1' to place a book order\n"
                        + "Enter '2' to place a movie order\n"
                        + "Enter '3' to exit");
                moreOrders = false;
                      
                

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
