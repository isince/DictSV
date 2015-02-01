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
        long insertID = mDatebase.insert(DBHelper.TABLE_FAVORITE, null, values);

        //Log-Debug addFavorite
        //Check Word ID
        if (insertID > 0) {
            Message.LogE("addFavorite", "Create " + insertID + " : " + word.getmWord());
        } else {
            Message.LogE("addFavorite", "No Word");
        }
    }

    //HashMap<String,Word>
    public HashMap<Long,Favorite> getAllFavorite(){

        HashMap<Long,Favorite> favorities = new HashMap<>();
        word = new Word();

        //Selsect all
        //SELECT ALL FROM favorite
        Cursor cursor = mDatebase.query(DBHelper.TABLE_FAVORITE, mAllColumns,
                null, null, null, null, null);

        if(cursor!=null) {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                favorite = cursorToFavorite(cursor);
                favorities.put(favorite.getWord().getmId(), favorite);

                //Log-Debug getAllFavorite
                Message.LogE(TAG, favorite.getmId() + " : " + favorite.getWord().getmId());

                //move cursor
                cursor.moveToNext();
            }
        } else {
            Message.longToast(mContext, TAG, "Cursor not create");
        }

        return favorities;
    }
/*
    public HashMap<Long, Word> getAllFavoriteWord(){
        HashMap<Long, Word> favoritesWord = new HashMap<>();
        WordDAO wordDAO = new WordDAO(mContext);

        HashMap<Long, Favorite> favoriteHashMap = getAllFavorite();
        HashMap<Long, Word> wordsHashMap = wordDAO.getAllWordHashMapLong();


        for (long favKey : favoriteHashMap.keySet()){
            for (long wordKey : wordsHashMap.keySet()){
                if (favKey == wordsHashMap.get(wordKey).getmId())
                    favoritesWord.put(wordKey,wordsHashMap.get(wordKey));
            }
        }

        return favoritesWord;

    }*/

    public HashMap<Long,Word> getAllFavoriteWord(){

        WordDAO wordDAO = new WordDAO(mContext);
        HashMap<Long,Word> favoritiesHashMap = new HashMap<>();
        HashMap<Long, Word> wordsHashMap = wordDAO.getAllWordHashMapLong();
        word = new Word();

        //Selsect all
        //SELECT ALL FROM favorite
        Cursor cursor = mDatebase.query(DBHelper.TABLE_FAVORITE, mAllColumns,
                null, null, null, null, null);

        if(cursor!=null) {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                favorite = cursorToFavorite(cursor);
                favoritiesHashMap.put(favorite.getWord().getmId(),
                        wordsHashMap.get(favorite.getWord().getmId()));

                //Log-Debug getAllFavorite
                Message.LogE(TAG, favorite.getmId() + " : " + favorite.getWord().getmId());

                //move cursor
                cursor.moveToNext();
            }
        } else {
            Message.longToast(mContext, TAG, "Cursor not create");
        }

        return favoritiesHashMap;
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
