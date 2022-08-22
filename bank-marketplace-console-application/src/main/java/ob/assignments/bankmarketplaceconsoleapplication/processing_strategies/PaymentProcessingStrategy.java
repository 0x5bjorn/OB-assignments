package ob.assignments.bankmarketplaceconsoleapplication.processing_strategies;

import ob.assignments.bankmarketplaceconsoleapplication.payment_methods.PaymentMethod;

/**
 * Strategy interface for payment processing
 */
public interface PaymentProcessingStrategy {
    void process(PaymentMethod card, double totalCost);
}
