package nd.dictsv.DAO;

/**
 * Created by Since on 13/1/2558.
 */
public class Favorite {

    //private variable
    private long mId;
    private Word word;

    public Favorite() {}

    public Favorite(Word word) {
        this.word = word;
    }

    public long getmId() {
        return mId;
    }

    public void setmId(long id) {
        this.mId = id;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }
}
