class Server {
    private final double serviceTime;
    private final Customer currentlyServing;

    Server(Customer currentlyServing, double serviceTime) {
        this.serviceTime = serviceTime;
        this.currentlyServing = currentlyServing;
    }

    // Create empty Server with a fake customer that will always be replaced
    static Server emptyServer(double serviceTime) {
        return new Server(Customer.fakeCustomer(), serviceTime);
    }

    boolean canServe(Customer a) {
        if (a.toServe(serviceTime, currentlyServing)) {
            return true;
        } else {
            return false;
        }
    }

    boolean canWait(Customer a) {
        if (a.toWait(currentlyServing)) {
            return true;
        } else {
            return false;
        }
    }

    Server updateServer(Customer a) {
        return new Server(a, this.serviceTime);

    }

    Customer serve() {
        return currentlyServing.getServed(serviceTime);
    }

    Customer setWaitingReservation(Customer a) {
        return a.finishWaiting(this.serviceTime, this.currentlyServing);
    }

    double waitingTime(Customer a) {
        return a.waitingTime(this.serviceTime, this.currentlyServing);
    }

}