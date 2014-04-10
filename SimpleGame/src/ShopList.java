public class ShopList {

    //
    // Public
    //
    public ShopList() {
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

    public ShopItem getHead() {
        return head;
    }
    public void setHead(ShopItem head) {
        this.head = head;
    }

    public ShopItem getLast() {
        return last;
    }
    public void setLast(ShopItem last) {
        this.last = last;
    }

    public void add(ShopItem item) {
        // System.out.println("adding " + item.toString());
        if (this.head == null) {
            // The list is empty.
            this.head = item;
            this.last = item;
        } else {
            // The list is NOT empty.
            this.last.setNext(item);
            this.last = item;

            // Before:
            // 1. Move to the end of the list.
            /*
            ShopItem lastItem = this.head;
            while (lastItem.getNext() != null) {
                lastItem = lastItem.getNext();
            }
            // 2. Attach the passed-in item to the last item in the list.
            lastItem.setNext(item);
            */
        }
    }



    // TODO Add length property

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ShopList: name=" + this.name + " desc=" + this.desc + "] List Items:\n");
        ShopItem currentItem = this.head;
        while (currentItem != null) {
            sb.append(currentItem.toString());
            sb.append("\n");
            currentItem = currentItem.getNext();
        }
        return sb.toString();
    }

    //
    // Private
    //
    private String name;
    private String desc;
    private ShopItem head = null;
    private ShopItem last = null;
}
