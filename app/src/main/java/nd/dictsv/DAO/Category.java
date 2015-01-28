package nd.dictsv.DAO;

/**
 * Created by Since on 13/1/2558.
 */
public class Category{

    //private variable
    private int mId;
    private String mName;

    public Category() {}

    public Category(int id, String name) {
        this.mId = id;
        this.mName = name;
    }

    public Category(String name) {
        this.mName = name;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int id) {
        this.mId = id;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String name) {
        this.mName = name;
    }
}
