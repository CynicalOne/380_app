public class Profile
{
    public String profileName;
    public int key;
    public String businessOrPersonal;
    public boolean isBusiness;
    public ImageView profilePic;

    public ArrayList Locations;

    // Default constructor
    public Profile() {
        this.profileName = "Name";
        this.key = -1;
        this.businessOrPersonal = "Company";
        this.isBusiness = true;
        this.ImageView = null;
        this.Locations = new ArrayList<Location>;
    }

    public void setProfileName(String newName) {
        this.profileName = newName;
    }

    public void setProfilePic(ImageView newImage) {
        this.profilePic = newImage;
    }

    public void setBusinessOrPersonal(boolean business) {
        this.isBusiness = business;
    }

}