package ob.assignments.bankmarketplaceconsoleapplication.payment_methods;

import java.util.Random;

/**
 * Concrete product, Visa payment method
 */
public class Visa implements PaymentMethod {
    private int id;
    private String number;
    private double amount;

    public Visa(String number) {
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
        if (amount >= totalCost)
            amount -= totalCost;
    }
}
