/**
 * Models a Server. A server has the amount of time to takes to serve a Customer
 * and a Customer that it is currently serving.
 */
class Server {
    /**
     * Amount of time that the Server will take to serve a Customer.
     */
    private final double serviceTime;

    /**
     * The Customer that the Server is currently serving.
     */
    private final Customer currentlyServing;

    /**
     * Constructs a Server.
     * @param currentlyServing Takes in a Customer that the Server is serving.
     * @param serviceTime Takes in the amount of time needed for a Server to finish serving
     *      a Customer
     */
    private Server(Customer currentlyServing, double serviceTime) {
        this.serviceTime = serviceTime;
        this.currentlyServing = currentlyServing;
    }

    /**
     * A static Constructor to initialise an empty Server.
     * @param serviceTime Takes in amount of time needed for the Server to finish serving
     *      a Customer
     * @return An empty server. The Customer stored inside it is "fake", 
     *      representing an empty Customer
     */
    static Server emptyServer(double serviceTime) {
        return new Server(Customer.fakeCustomer(), serviceTime);
    }

    /**
     * Check whether the Server can serve a Customer.
     * @param a Incoming Customer
     * @return True if the Customer can be served
     */
    boolean canServe(Customer a) {
        if (a.toServe(serviceTime, currentlyServing)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if the Server can "queue" the Customer to wait.
     * @param a Incoming Customer
     * @return True if there are no other Customers currently waiting
     */
    boolean canWait(Customer a) {
        if (a.toWait(currentlyServing)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Update the Server with the new Customer it is serving.
     * @param a New Customer the Server is serving
     * @return A new Server with the new Customer stored inside
     */
    Server updateServer(Customer a) {
        return new Server(a, this.serviceTime);

    }

    /**
     * Serves the Customer.
     * @return Calls upon the Customer getServed method to update the Customer
     */
    Customer serve() {
        return currentlyServing.getServed(serviceTime);
    }

    /**
     * Set a reservation for after the Customer finishes waiting.
     * @param a The Customer currently waiting
     * @return Calls upon the Customer finishWaiting method
     */
    Customer setWaitingReservation(Customer a) {
        return a.finishWaiting(this.serviceTime, this.currentlyServing);
    }

    /**
     * Amount of time the Customer has waited.
     * @param a The waiting Customer
     * @return Amount of time the waiting Customer has waited for.
     */
    double waitingTime(Customer a) {
        return a.waitingTime(this.serviceTime, this.currentlyServing);
    }

}