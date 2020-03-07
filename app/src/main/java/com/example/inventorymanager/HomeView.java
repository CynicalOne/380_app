public class HomeView implements ListEdit<Profile>, EditView<E>
{
    public Button editButton;
    public Button addButton;
    public boolean inEditMode;
    final String APP_NAME = "Trackoholic";

    ArrayList Profiles;
    

    public void goToProfileView(Profile profile) {
        
    }

    // methods from ListEdit interface
    public void deleteListElement(int index) {
        Profiles.remove(index);
    }

    public void deleteEntireList() {
        Profiles = new ArrayList<Profile>;
    }

    public void addListElement(Profile profileToAdd) {
        Profiles.add(profileToAdd);
    }

    public void clickAddButton() {

    }

    public void clickDeleteButton() {

    }
}
