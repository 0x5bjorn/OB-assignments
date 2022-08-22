package ob.assignments.bankmarketplaceconsoleapplication.general;

import ob.assignments.bankmarketplaceconsoleapplication.payment_methods.PaymentMethod;

/**
 * Client
 */
public class Client {
    private int id;
    private String fullname;
    private boolean retired;
    private PaymentMethod card;

    public Client() { }

    public Client(int id, String fullname, boolean retired) {
        this.id = id;
        this.fullname = fullname;
        this.retired = retired;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setRetired(boolean retired) {
        this.retired = retired;
    }

    public boolean isRetired() {
        return retired;
    }

    public void setCard(PaymentMethod card) {
        this.card = card;
    }

    public PaymentMethod getCard() {
        return card;
    }
}
