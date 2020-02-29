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
        }
        sc.close();
        System.out.println("# Adding arrivals");

        while (!customerQueue.isEmpty()) {
            PriorityQueue<Customer> customerQueueCopy = new PriorityQueue<Customer>(customerQueue);

            while (!customerQueueCopy.isEmpty()) {
                Customer b = customerQueueCopy.poll();
                System.out.println(b);
            }

            Customer a = customerQueue.poll();

            if (a.isArrival()) {
                if (s.canServe(a)) {
                    s = s.updateServer(a);
                    Customer servingEvent = s.serve();
                    customerQueue.add(servingEvent);
                } else {
                    customerQueue.add(a.leaves());
                }
            } else if (a.isServing()) {
                customerQueue.add(a.done());
            }
            System.out.println();
            System.out.println("# Get next event: " + a);



        }
        System.out.println();
        System.out.println("Number of customers: " + (id - 1));
    }
}