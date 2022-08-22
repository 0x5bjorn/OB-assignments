package ob.assignments.bankmarketplaceconsoleapplication.processing_strategies;

import ob.assignments.bankmarketplaceconsoleapplication.payment_methods.PaymentMethod;

/**
 * Concrete strategy, IPC payment processing
 */
public class IPCProcessing implements PaymentProcessingStrategy {

    @Override
    public void process(PaymentMethod card, double totalCost) {
        card.performPayment(totalCost);
    }
}
