
package com.coit13229ass2;


public class MovieOrder implements Task {
    private int quantity;
    private float unitPrice;
    private final int taxRate = 30;
    private float totalBill;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    //Calculate tax amount
    public float executeTask(){
    
    float taxAmount = (quantity * unitPrice)*(taxRate/100);
    totalBill = taxAmount + (quantity * unitPrice);
    return totalBill;
    }
    
    public String getResult(){
    
        return "The total bill for this order is" + totalBill;
    }
}
