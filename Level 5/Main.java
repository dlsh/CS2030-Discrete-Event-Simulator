import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Takes in input of Customer arrival timings.
 * Based on this input, determine what happens to the Customer
 * and output this in terms of a series of events.
 */
class Main {
    public static void main(final String[] args) {
        final Scanner sc = new Scanner(System.in);
        final PriorityQueue<Customer> customerQueue = 
                new PriorityQueue<Customer>(1, new CustomerComparator());
        Server s = Server.emptyServer(1);

        double totalWaitingTime = 0;
        int customersServed = 0;
        int customersLeft = 0;

        int id = 1;
        while (sc.hasNext()) {
            Customer a = Customer.arrivingCustomer(sc.nextDouble(), id);
            customerQueue.add(a);
            id++;      
        }
        sc.close();


        while (!customerQueue.isEmpty()) {

            Customer a = customerQueue.poll();

            if (a.isArrival()) {
                if (s.canServe(a)) {
                    s = s.updateServer(a);
                    Customer servingEvent = s.serve();
                    customerQueue.add(servingEvent);

                } else if (s.canWait(a)) {
                    customerQueue.add(a.waiting());
                    totalWaitingTime = totalWaitingTime + s.waitingTime(a);
                    Customer finishWaitingEvent = s.setWaitingReservation(a);
                    s = s.updateServer(finishWaitingEvent);
                    Customer reservedServingEvent = s.serve();
                    customerQueue.add(reservedServingEvent);
                } else {
                    customerQueue.add(a.leaves());
                    customersLeft++;
                }
            } else if (a.isServing()) {
                customerQueue.add(a.done());
                customersServed++;
            }
            System.out.println(a);

        }
        System.out.println("[" + String.format("%.3f", totalWaitingTime / customersServed) + 
                " " + customersServed + " " + customersLeft + "]");
    }
}