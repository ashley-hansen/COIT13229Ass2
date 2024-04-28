
package com.coit13229ass2;


public class BookOrder implements Task{
    private int quantity;
    private float unitPrice;
    private final int taxRate = 10;
    private float totalBill;

    public BookOrder(int quantity, float unitPrice) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;

    }
    
    

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
