public class Item {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public Item(Item anotherItem) {
        this.name = anotherItem.getName();
    }
    public String getName() {
        return name;
    }

}
