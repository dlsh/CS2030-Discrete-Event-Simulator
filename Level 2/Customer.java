
class Customer {
    private final double time;
    private final int id;
    private final int stateIndex;
    private final String[] stateArray = 
            new String[] {"test", "arrives", "served", "leaves", "done"};
    private final double serviceTime;


    Customer(double time, int id, int stateIndex, double serviceTime) {
        this.time = time;
        this.id = id;
        this.stateIndex = stateIndex;
        this.serviceTime = serviceTime;
    }

    // for arriving Customers
    static Customer arrivingCustomer(double time, int id) {
        return new Customer(time, id, 1, 0);
    }

    // for initialising empty Server
    static Customer fakeCustomer() {
        return new Customer(-9999, 0, 0, 0);
    }

    int compare(Customer a) {
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

    boolean toWait(double waitingTime, Customer a) {
        if (this.time >= (a.time + waitingTime)) {
            return true;
        } else {
            return false;
        }
    }

    Customer getServed(double serviceTime) {
        return new Customer(this.time, this.id, 2, serviceTime);
    }

    Customer leaves() {
        return new Customer(this.time, this.id, 3, this.serviceTime);
    }

    @Override
    public String toString() {
        if (this.stateIndex == 1) {
            return this.id + " " + this.stateArray[this.stateIndex] + 
                    " at " + String.format("%.3f", this.time);
        } else if (this.stateIndex == 2) {
            return "Customer served; next service @ " + 
                    String.format("%.3f", (this.time + this.serviceTime));
        } else if (this.stateIndex == 3) {
            return "Customer leaves";
        } else {
            return "error";
        }
    }
}
