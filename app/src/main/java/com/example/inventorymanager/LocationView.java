public class LocationView implements ListEdit<Item>
{
    public Location location;
    public Button editButton;
    public Button addButton;
    public boolean inEditMode;

    public void goToItemView(Item item) {

    }
    
    // methods from ListEdit interface
    public void deleteListElement(int index) {
        location.Items.remove(index);
    }

    public void deleteEntireList() {

    }

    public void addListElement(Item itemToAdd) {
        location.Items.add(itemToAdd);
    }

    public void clickAddButton() {

    }

    public void clickDeleteButton() {

    }
}