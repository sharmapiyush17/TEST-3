
public class PizzaOrder {
    private String name;
    private String mobile;
    private String size;
    private int toppings;
    private double totalBill;

    public PizzaOrder(String name, String mobile, String size, int toppings, double totalBill) {
        this.name = name;
        this.mobile = mobile;
        this.size = size;
        this.toppings = toppings;
        this.totalBill = totalBill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getToppings() {
        return toppings;
    }

    public void setToppings(int toppings) {
        this.toppings = toppings;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }
}
