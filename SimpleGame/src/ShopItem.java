public class ShopItem {

    //
    // Public
    //
    public ShopItem() {
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }

    public ShopItem getNext() {
        return next;
    }
    public void setNext(ShopItem next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "[ShopItem name=" + this.name
                + " desc=" + this.desc
                + " cost=" + this.cost + "]";
    }

    //
    // Private
    //
    private String name;
    private String desc;
    private double cost;
    private ShopItem next = null;
}
