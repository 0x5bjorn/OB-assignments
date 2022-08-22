package ob.assignments.bankmarketplaceconsoleapplication.card_factories;

import ob.assignments.bankmarketplaceconsoleapplication.payment_methods.Elcart;

/**
 * Concrete factory to create Elcart
 */
public class ElcartFactory implements CardFactory {

    @Override
    public Elcart createCard(String number) {
        return new Elcart(number);
    }
}
