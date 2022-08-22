package ob.assignments.bankmarketplaceconsoleapplication.card_factories;

import ob.assignments.bankmarketplaceconsoleapplication.payment_methods.Visa;

/**
 * Concrete factory to create Visa
 */
public class VisaFactory implements CardFactory {

    @Override
    public Visa createCard(String number) {
        return new Visa(number);
    }
}
