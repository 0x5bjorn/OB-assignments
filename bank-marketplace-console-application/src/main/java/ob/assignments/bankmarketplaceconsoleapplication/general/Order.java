package ob.assignments.bankmarketplaceconsoleapplication.general;

import ob.assignments.bankmarketplaceconsoleapplication.payment_methods.PaymentMethod;
import ob.assignments.bankmarketplaceconsoleapplication.processing_strategies.PaymentProcessingStrategy;

import java.util.ArrayList;

/**
 * Order
 */
public class Order {
    private ArrayList<Product> shoppingCart;
    private double totalCost = 0;
    private boolean isClosed;

    public Order() {
        this.shoppingCart = new ArrayList<>();
        this.isClosed = false;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public void processOrder(PaymentMethod card, PaymentProcessingStrategy processingStrategy) {
        processingStrategy.process(card, this.totalCost);
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void calculateTotalCost(){
        for(Product product : shoppingCart) {
            this.totalCost += product.getCost();
        }
    }

    public void addToCart(Product product) {
        this.shoppingCart.add(product);
    }

    public ArrayList<Product> getShoppingCart() {
        return shoppingCart;
    }
}
