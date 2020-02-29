import java.util.PriorityQueue;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriorityQueue<Customer> customerQueue = 
                new PriorityQueue<Customer>(1, new CustomerComparator());

        int id = 1;
        while (sc.hasNext()) {
            customerQueue.add(new Customer(sc.nextDouble(), id, "arrives"));
            id++;
        }
        sc.close();

        while (!customerQueue.isEmpty()) {
            Customer a = customerQueue.poll();
            System.out.println(a);
        }

        System.out.println("Number of customers: " + (id - 1));
    }
}