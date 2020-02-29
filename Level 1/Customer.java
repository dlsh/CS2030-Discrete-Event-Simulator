
class Customer {
    private final double time;
    private final int id;
    private final String state;

    Customer(double time, int id, String state) {
        this.time = time;
        this.id = id;
        this.state = state;
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
                return 0;
            }
        }
    }

    @Override
    public String toString() {
        return this.id + " " + this.state + " at " + String.format("%.3f", this.time);
    }
}
