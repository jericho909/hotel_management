package core;

public class ComboItem {
    private int key;
    private String value;
    //we need this custom item class for populating combo boxes. this item will keep track of the name and the id of the item
    public ComboItem(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    //we generally only need the name or the value of the item so the toString only returns the value,
    //if we need the id we simply use the getKey method; because we don't need the String value of the id
    @Override
    public String toString() {
        return this.value;
    }
}
