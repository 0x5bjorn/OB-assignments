package ob.assignments.bankmarketplaceconsoleapplication.payment_methods;

/**
 * Abstract product interface for payment methods
 */
public interface PaymentMethod {
    void performPayment(double totalCost);
    double getAmount();
}
