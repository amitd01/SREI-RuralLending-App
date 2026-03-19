package jaanpehchan.rural.srei;

public class ExpandableListItemSelected {

    private int index;
    private String itemString;
    public ExpandableListItemSelected(String itemString, int index) {
        this.index=index;
        this.itemString=itemString;
    }

    public String getItemString() {
        return itemString;
    }

    public int getIndex() {
        return index;
    }
}
