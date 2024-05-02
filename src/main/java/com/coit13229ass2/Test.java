/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coit13229ass2;

/**
 *
 * @author ashle
 */
public class Test {
  public static void main(String[] args){
    BookOrder bo = new BookOrder(3,5.00);
    MovieOrder mo = new MovieOrder(7,22.22){};
    System.out.println(bo.getResult());
    System.out.println(mo.getResult());
  }
  
}
