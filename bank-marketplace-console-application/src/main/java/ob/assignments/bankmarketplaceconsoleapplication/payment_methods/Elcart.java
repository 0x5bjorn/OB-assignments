package ob.assignments.bankmarketplaceconsoleapplication.payment_methods;

import java.util.Random;

/**
 * Concrete product, Elcart payment method
 */
public class Elcart implements PaymentMethod {
    private int id;
    private String number;
    private double amount;

    public Elcart(String number) {
        this.number = number;

        /**
         * Set random id and balance amount,
         * since this info should be queried from DB
         */
        Random random = new Random();
        this.id = random.nextInt(100);
        this.amount = random.nextInt(50) * 1000;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public void performPayment(double totalCost) {
        double rate = 1.02;
        if (amount >= totalCost)
            amount -= totalCost * rate;
    }
}
