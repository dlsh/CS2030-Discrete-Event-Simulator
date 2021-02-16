import java.util.Arrays;
import java.util.List;

/**
 * Models a customer. A customer is initialised with ID, event and time of event.
 */
class Customer {
    /**
     * Time of event.
     */
    private final double time;

    /**
     * ID of Customer.
     */
    private final int id;

    /**
     * Customer state (Type of event).
     */
    private final int stateIndex;

    /**
     * Time taken for customer to be served by server.
     */
    private final double serviceTime;

    /**
     * List of event states to be referenced.
     */
    private static final List<String> stateList = 
            Arrays.asList("test", "arrives", "served", "leaves", "done", "waits");


    /**
     * Constructs a customer.
     * @param time Time of event
     * @param id ID of Customer
     * @param stateIndex Type of event
     * @param serviceTime How long it takes for the server to serve the customer
     */
    private Customer(final double time, final int id, 
            final int stateIndex, final double serviceTime) {
        this.time = time;
        this.id = id;
        this.stateIndex = stateIndex;
        this.serviceTime = serviceTime;
    }

    /**
     * Static constructor method for arriving customers. 
     * @param time Time of event
     * @param id ID of Customer
     * @return The Customer constructor with fixed state as "arrival" and service time as 0.
     */
    static Customer arrivingCustomer(final double time, final int id) {
        return new Customer(time, id, Customer.stateList.indexOf("arrives"), 0);
        
        
    }

    /**
     * Static constructor method for creating a "fake" customer 
     * to be used to initialise an empty server.
     * @return The Customer constructor with test state.
     */
    static Customer fakeCustomer() {
        return new Customer(-9999, 0, Customer.stateList.indexOf("test"), 0);
    }

    /**
     * Compare 2 customers based on their event time.
     * If the event time is the same, compare based on ID instead.
     * @param a The other Customer that this Customer is being compared with
     * @return 1 if it's behind in the queue, -1 if it's in front.
     */
    int compare(final Customer a) {
        if (this.time > a.time) {
            return 1;
        } else if (this.time < a.time) {
            return -1;
        } else {
            if (this.id > a.id) {
                return 1;
            } else if (this.id < a.id) {
                return -1;
            } else {
                if (this.stateIndex > a.stateIndex) {
                    return 1;
                } else if (this.stateIndex < a.stateIndex) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }

    /**
     * Checks whether incoming customer can be served 
     * by comparing with the customer stored in Server.
     * @param waitingTime Time taken for Server to serve the previous Customer
     * @param a Customer stored in server. Currently serving or will be served after waiting period.
     * @return True if customer can be served.
     */
    boolean toServe(final double waitingTime, final Customer a) {
        if (this.time >= (a.time + waitingTime)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks whether incoming customer can wait by compaing with the customer stored in server.
     * @param a Customer stored in Server. Currently serving or will be served after waiting period.
     * @return True if the customer can wait.
     */
    boolean toWait(final Customer a) {
        if (a.time > this.time) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks whether event state of Customer is "arrives".
     * @return True if Customer event state is "arrives".
     */
    boolean isArrival() {
        if (this.stateIndex == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check whether event state of Customer is "served".
     * @return True if Customer event state is "served".
     */
    boolean isServing() {
        if (this.stateIndex == 2) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Changes Customer event state to "served".
     * @param serviceTime Takes in time taken to be served from Server
     * @return The Customer constructor with event state set to "served"
     */
    Customer getServed(final double serviceTime) {
        return new Customer(this.time, this.id, Customer.stateList.indexOf("served"), serviceTime);
    }

    /**
     * Changes Customer event state to "leaves".
     * @return The Customer constructor with event state set to "leaves"
     */
    Customer leaves() {
        return new Customer(this.time, this.id, 
                Customer.stateList.indexOf("leaves"), this.serviceTime);
    }

    /**
     * Changes Customer event state to "done".
     * @return The Customer constructor with event state set to "done"
     */
    Customer done() {
        return new Customer((this.time + this.serviceTime), this.id, 
                Customer.stateList.indexOf("done"), 0);
    }

    /**
     * Changes Customer event state to "waits".
     * @return The Customer constructor with event state set to "waits"
     */
    Customer waiting() {
        return new Customer(this.time, this.id, 
                Customer.stateList.indexOf("waits"), this.serviceTime);
    }
    
    /**
     * Creates a new "arrived" event for Customer that has finished waiting.
     * @param serviceTime Time taken for Server to serve the Customer it is currently serving
     * @param a The Customer that the Server is currently serving
     * @return A new Customer with event state "arrives" and time set to after the 
     *      current Customer is done.
     */
    Customer finishWaiting(final double serviceTime, final Customer a) {
        return Customer.arrivingCustomer(serviceTime + a.time, this.id);
    }

    /**
     * Calculates total time that the Customer has been waiting for.
     * @param serviceTime Time taken for Server to serve the Customer it is currently serving
     * @param a The Customer that the Server is currently serving
     * @return Time this Customer waits for the previous Customer to be done
     */
    double waitingTime(final double serviceTime, final Customer a) {
        return a.time + serviceTime - this.time;
    }

    /**
     * Formats the output of a Customer as a String in the format
     * "time", "ID". "event state"
     */
    @Override
    public String toString() {
        return String.format("%.3f", this.time) + " " +  this.id + " " + 
                Customer.stateList.get(this.stateIndex);

    }
}
