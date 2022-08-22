package ob.assignments.bankmarketplaceconsoleapplication.general;

/**
 * Product
 */
public class Product {
    private double cost;
    private String name;

    public Product(int cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + ", price = " + cost;
    }
}
