import java.util.PriorityQueue;
import java.util.Scanner;

class Main {
    public static void main(final String[] args) {
        final Scanner sc = new Scanner(System.in);
        final PriorityQueue<Customer> customerQueue = 
                new PriorityQueue<Customer>(1, new CustomerComparator());
        Server s = Server.emptyServer(1);

        int id = 1;
        while (sc.hasNext()) {
            Customer a = Customer.arrivingCustomer(sc.nextDouble(), id);
            customerQueue.add(a);
            id++;

            if (s.canServe(a)) {
                s = s.updateServer(a);
                Customer servingEvent = s.serve();
                customerQueue.add(servingEvent);
            } else {
                customerQueue.add(a.leaves());
            }

            
        }
        sc.close();

        while (!customerQueue.isEmpty()) {
            final Customer a = customerQueue.poll();
            System.out.println(a);

        }

        System.out.println("Number of customers: " + (id - 1));
    }
}