package ob.assignments.bankmarketplaceconsoleapplication.card_factories;

import ob.assignments.bankmarketplaceconsoleapplication.payment_methods.PaymentMethod;

/**
 * Abstract factory interface for creating cards
 */
public interface CardFactory {
    PaymentMethod createCard(String number);
}
