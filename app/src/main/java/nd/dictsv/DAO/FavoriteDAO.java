package nd.dictsv.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.HashMap;

import nd.dictsv.Debug.Message;

/**
 * Created by Since on 24/1/2558.
 */
public class FavoriteDAO {

    public static final String TAG = "FavoriteDAO";

    private Context mContext;

    private Word word;
    private Category category;
    private Favorite favorite;

    //Database field
    private SQLiteDatabase mDatebase;
    private DBHelper mDbHelper;
    private String[] mAllColumns = { DBHelper.COLUMN_FAVORITE_ID,
                                     DBHelper.COLUMN_FAVORITE_WORD_ID};

    public FavoriteDAO(Context context) {
        this.mContext = context;
        this.mDbHelper = new DBHelper(context);

        //open database
        try{
            open();
        } catch (SQLException e){
            Message.longToast(mContext, TAG, e.getMessage());
            //Log.e(TAG, "SQLException on opening database" + e.getMessage());
        }
    }

    public void open() throws SQLException{
        mDatebase = mDbHelper.getWritableDatabase();
    }

    public void close(){
        mDbHelper.close();
    }

    public void addFavorite(Word word){
        //Insert Content value
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_FAVORITE_WORD_ID, word.getmId());

        //insert row and id category
        long insertID = mDatebase.insert(DBHelper.TABLE_WORDS, null, values);

        //Log-Debug addFavorite
        //Check Word ID
        if (insertID > 0) {
            Message.shortToast(mContext, TAG, "Create " + insertID + " " + word);
        } else {
            Message.shortToast(mContext, TAG, "No Word");
        }
    }

    //HashMap<String,Word>
    public void getAllFavorite(){

        HashMap<String,Favorite> favorities = new HashMap<>();
        word = new Word();

        //Selsect all
        //SELECT ALL FROM favorite
        Cursor cursor = mDatebase.query(DBHelper.TABLE_FAVORITE, mAllColumns,
                null, null, null, null, null);

        if(cursor!=null) {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                favorite = cursorToFavorite(cursor);
                favorities.put(String.valueOf(favorite.getmId()), favorite);

                //Log-Debug getAllFavorite
                Message.LogE(TAG, favorite.getmId() + " : " + favorite.getWord().getmId());

                //move cursor
                cursor.moveToNext();
            }
        } else {
            Message.longToast(mContext, TAG, "Cursor not create");
        }
    }

    public void deleteFavorite(){

    }

    public Favorite cursorToFavorite(Cursor cursor){
        favorite = new Favorite();
        word = new Word();

        favorite.setmId(cursor.getLong(
                cursor.getColumnIndex(DBHelper.COLUMN_FAVORITE_ID)));
        //set word object
        word.setmId(cursor.getLong(
                cursor.getColumnIndex(DBHelper.COLUMN_FAVORITE_WORD_ID)));
        favorite.setWord(word);

        return favorite;
    }
}
