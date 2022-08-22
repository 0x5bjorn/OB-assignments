package ob.assignments.bankmarketplaceconsoleapplication.processing_strategies;

import ob.assignments.bankmarketplaceconsoleapplication.payment_methods.PaymentMethod;

/**
 * Concrete strategy, Way4 payment processing
 */
public class Way4Processing implements PaymentProcessingStrategy {

    @Override
    public void process(PaymentMethod card, double totalCost) {
        double rate = 0.6;
        card.performPayment(totalCost * rate);
    }
}
