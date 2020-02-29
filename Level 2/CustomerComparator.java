import java.util.Comparator;

class CustomerComparator implements Comparator<Customer> {
 
    CustomerComparator() {
    }

    public int compare(Customer c1, Customer c2) {
        return c1.compare(c2);
    }
}