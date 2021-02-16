import java.util.Comparator;

/**
 * A Comparator Object for comparing Customers.
 */

class CustomerComparator implements Comparator<Customer> {
 
    /**
     * Initialises the CustomerComparator.
     */
    CustomerComparator() {
    }

    /**
     * Compares 2 Customers by calling upon the compare method in Customer.
     */
    public int compare(Customer c1, Customer c2) {
        return c1.compare(c2);
    }
}