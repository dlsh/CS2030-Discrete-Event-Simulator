import java.util.Arrays;
import java.util.List;

class Customer {
    private final double time;
    private final int id;
    private final int stateIndex;
    private final double serviceTime;

    private static final List<String> stateList = 
            Arrays.asList("test", "arrives", "served", "leaves", "done", "waits");



    Customer(double time, int id, int stateIndex, double serviceTime) {
        this.time = time;
        this.id = id;
        this.stateIndex = stateIndex;
        this.serviceTime = serviceTime;
    }

    // for arriving Customers
    static Customer arrivingCustomer(double time, int id) {
        return new Customer(time, id, Customer.stateList.indexOf("arrives"), 0);
        
        
    }

    // for initialising empty Server
    static Customer fakeCustomer() {
        return new Customer(-9999, 0, Customer.stateList.indexOf("test"), 0);
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

    boolean toServe(double waitingTime, Customer a) {
        if (this.time >= (a.time + waitingTime)) {
            return true;
        } else {
            return false;
        }
    }

    boolean toWait(Customer a) {
        if (a.time > this.time) {
            return false;
        } else {
            return true;
        }
    }

    boolean isArrival() {
        if (this.stateIndex == 1) {
            return true;
        } else {
            return false;
        }
    }

    boolean isServing() {
        if (this.stateIndex == 2) {
            return true;
        } else {
            return false;
        }
    }

    Customer getServed(double serviceTime) {
        return new Customer(this.time, this.id, Customer.stateList.indexOf("served"), serviceTime);
    }

    Customer leaves() {
        return new Customer(this.time, this.id, 
                Customer.stateList.indexOf("leaves"), this.serviceTime);
    }

    Customer done() {
        return new Customer((this.time + this.serviceTime), this.id, 
                Customer.stateList.indexOf("done"), 0);
    }

    Customer waiting() {
        return new Customer(this.time, this.id, 
                Customer.stateList.indexOf("waits"), this.serviceTime);
    }
    

    Customer finishWaiting(double serviceTime, Customer a) {
        return Customer.arrivingCustomer(serviceTime + a.time, this.id);
    }

    double waitingTime(double serviceTime, Customer a) {
        return a.time + serviceTime - this.time;
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.time) + " " +  this.id + " " + 
                Customer.stateList.get(this.stateIndex);

    }
}
