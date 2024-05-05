package com.coit13229ass2;

import java.io.Serializable;

public class BookOrder implements Task, Serializable {

    private int quantity;
    private double unitPrice;
    private final double taxRate = 10;
    private double totalBill;
    private double taxAmount;

    public BookOrder(int quantity, double unitPrice) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

//Calculate tax amount
    @Override
    public double executeTask() {

        taxAmount = (quantity * unitPrice) * (taxRate / 100);
        totalBill = taxAmount + (quantity * unitPrice);
        return totalBill;
    }

    public String getResult() {

        return String.format("The total bill for this book order is $%.2f", executeTask());
    }

}
