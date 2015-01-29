package nd.dictsv.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nd.dictsv.Debug.Message;

/**
 * Created by Since on 17/1/2558.
 */
public class WordDAO {

    public static final String TAG = "WordDAO";

    private Context mContext;

    private Word word;
    private Category category;
    HashMap<String ,Word> mapWordDAO;

    //Datebase field
    private SQLiteDatabase mDatabase;
    private DBHelper mDBHelper;
    private String[] mAllColumns = { DBHelper.COLUMN_WORD_ID,
                                     DBHelper.COLUMN_WORD_WORD,
                                     DBHelper.COLUMN_WORD_TRANSLITERATED,
                                     DBHelper.COLUMN_WORD_TERMINOLOGY,
                                     DBHelper.COLUMN_WORD_CATEGORY_ID};

    public WordDAO(Context context) {
        this.mContext = context;
        mDBHelper = new DBHelper(context);

        //open database
        try {
            open();
        } catch (Exception e) {
            Message.longToast(mContext, TAG, e.getMessage());
        }
    }

    public void open() {
        mDatabase = mDBHelper.getWritableDatabase();
    }

    public void close(){
        mDBHelper.close();
    }

    //void
    //add word by category object
    public void addWord(String word, String trans, String termino, Category category) {

        //Insert Content value
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_WORD_WORD, word);
        values.put(DBHelper.COLUMN_WORD_TRANSLITERATED, trans);
        values.put(DBHelper.COLUMN_WORD_TERMINOLOGY, termino);
        values.put(DBHelper.COLUMN_WORD_CATEGORY_ID, category.getmId());

        //insert row and id category
        long insertID = mDatabase.insert(DBHelper.TABLE_WORDS, null, values);

        //Log-Debug addCatetory
        //Check Word ID
        /*if (insertID > 0) {
            Message.shortToast(mContext, TAG, "Create " + insertID + " " + word);
        } else {
            Message.shortToast(mContext, TAG, "No Word");
        }*/
    }

    //add word by categoryID(int)
    public void addWord(String word, String trans, String termino, int categoryID) {

        //Insert Content value
        ContentValues values = new ContentValues();
        //values.put(DBHelper.COLUMN_WORD_ID, null);
        values.put(DBHelper.COLUMN_WORD_WORD, word);
        values.put(DBHelper.COLUMN_WORD_TRANSLITERATED, trans);
        values.put(DBHelper.COLUMN_WORD_TERMINOLOGY, termino);
        values.put(DBHelper.COLUMN_WORD_CATEGORY_ID, categoryID);

        //insert row and id category
        long insertID = mDatabase.insert(DBHelper.TABLE_WORDS, null, values);

        ////Log-Debug addCatetory
        //Check Word ID
        /*if (insertID > 0) {
            Message.shortToast(mContext, TAG, "Create " + insertID + " " + word);
        } else {
            Message.shortToast(mContext, TAG, "No Word");
        }*/
    }

    public List<Word> getAllWord(){

        List<Word> listWord = new ArrayList<>();
        word = new Word();

        //Select all
        //SELECT _id word trans termino cat_id FROM Words
        Cursor cursor = mDatabase.query(DBHelper.TABLE_WORDS, mAllColumns,
                null, null, null, null, null);

        //Buffer
        StringBuffer buffer = new StringBuffer();

        if(cursor!=null) {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {

                word = cursorToWord(cursor);
                listWord.add(word);

                //Log-Debug getAllWord StringBuffer
                //add String Buffer
                /*buffer.append(cursor.getString(
                        cursor.getColumnIndex(DBHelper.COLUMN_WORD_ID)) + " ");
                buffer.append(cursor.getString(
                        cursor.getColumnIndex(DBHelper.COLUMN_WORD_WORD)));
                buffer.append("\n");*/

                //move cursor
                cursor.moveToNext();
            }
        } else {
            Message.longToast(mContext, TAG, "Cursor not create");
        }

        //Log-Debug getAllWord
        /*Message.toast2(mContext, buffer.toString());*/

        if(cursor!=null) {
            cursor.close();
        }
        return listWord;
    }

    public HashMap<String,Word> getAllWordHashMap(){

        mapWordDAO = new HashMap<>();

        //SELECT ALL FROM words
        Cursor cursor = mDatabase.query(DBHelper.TABLE_WORDS, mAllColumns,
                null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();

            while (!cursor.isAfterLast()){
                word = cursorToWord(cursor);
                mapWordDAO.put(word.getmWord(), word);

                //move cursor
                cursor.moveToNext();
            }
        }

        //Log-Debug toast all HashMap
        for (String  word : mapWordDAO.keySet()){
            Message.LogE(TAG, "HashMap : " + word);
        }

        cursor.close();
        return mapWordDAO;
    }

    public HashMap<Long,Word> getAllWordHashMapLong(){

        HashMap<Long,Word> mapWordDAOInt = new HashMap<>();

        //SELECT ALL FROM words
        Cursor cursor = mDatabase.query(DBHelper.TABLE_WORDS, mAllColumns,
                null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();

            while (!cursor.isAfterLast()){
                word = cursorToWord(cursor);
                mapWordDAOInt.put(word.getmId(), word);

                //move cursor
                cursor.moveToNext();
            }
        }

        //Log-Debug toast all HashMap
        for (Long  wordID : mapWordDAOInt.keySet()){
            Message.LogE(TAG, "HashMap : " + wordID);
        }

        cursor.close();
        return mapWordDAOInt;
    }

    //void
    public List<Word> getWordByCategoryID(int id){

        List<Word> listWord = new ArrayList<>();
        word = new Word();

        //SELECT * FROM Words WHERE category_id = 0
        String[] whereArgs = new String[]{String.valueOf(id)};
        Cursor cursor = mDatabase.query(DBHelper.TABLE_WORDS, mAllColumns,
                DBHelper.COLUMN_WORD_CATEGORY_ID + "=?", whereArgs, null, null, null);

        //Buffer
        StringBuffer buffer = new StringBuffer();

        //Cursor management
        if (cursor!=null){
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){

                word = cursorToWord(cursor);
                listWord.add(word);

                //Log-Debug getWordByCategoryID StringBuffer
                //add String Buffer
                buffer.append(cursor.getString(
                        cursor.getColumnIndex(DBHelper.COLUMN_WORD_WORD)));
                buffer.append("\n");

                //move cursor
                cursor.moveToNext();
            }
        } else {
            Message.longToast(mContext, TAG, "Cursor not creaate");
        }

        //Log-Debug getWordByCategoryID
        Message.toast2(mContext, buffer.toString());

        if(cursor!=null) {
            cursor.close();
        }
        return listWord;
    }

    public void updateWord(Word word){
        //add content
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_WORD_ID, word.getmId());
        values.put(DBHelper.COLUMN_WORD_WORD, word.getmWord());
        values.put(DBHelper.COLUMN_WORD_TRANSLITERATED, word.getmTrans());
        values.put(DBHelper.COLUMN_WORD_TERMINOLOGY, word.getmTermino());
        values.put(DBHelper.COLUMN_WORD_CATEGORY_ID, word.getmCategory().getmId());

        //update
        //UPDATE category SET word = "new_word" WHERE word_id = 0
        String[] whereArgs = new String[]{String.valueOf(word.getmId())};
        //long updateID = mDatabase.update(DBHelper.TABLE_WORDS, values,
        //        DBHelper.COLUMN_WORD_ID + "=?", whereArgs);
        int updateID = mDatabase.update(DBHelper.TABLE_WORDS, values,
                DBHelper.COLUMN_WORD_ID + "=?", whereArgs);


        //Log-Debug updateWord
        //database.update return 1(Successful) and 0(Unsuccessful)
        if(updateID>0) {
            Message.longToast(mContext, TAG, updateID + " - " + word.getmWord());
        } else {
            Message.longToast(mContext, TAG, "No word update");
        }
    }

    //void
    public void deleteWord(Word word){
        long id = word.getmId();

        Message.LogE(TAG, "del:" + word.getmId()+ " : " +word.getmWord());
        //DELETE FROM WORD WHERE word_id = 0
        String[] whereArgs = {String.valueOf(word.getmId())};
        int delID = mDatabase.delete(DBHelper.TABLE_WORDS,
                DBHelper.COLUMN_WORD_ID+"= ?", whereArgs);


        //Log-Debug deleteWord
        //database.delete return 1(Successful) and 0(Unsuccessful)
        if (delID>0) {
            Message.longToast(mContext, TAG, "Delete Successful");
        } else {
            Message.longToast(mContext, TAG, "Unsuccessful Delete");
        }
    }

    public Word cursorToWord(Cursor cursor){
        word = new Word();
        category = new Category();
        word.setmId(cursor.getLong(cursor.getColumnIndex(
                DBHelper.COLUMN_WORD_ID)));
        word.setmWord(cursor.getString(cursor.getColumnIndex(
                DBHelper.COLUMN_WORD_WORD)));
        word.setmTrans(cursor.getString(cursor.getColumnIndex(
                DBHelper.COLUMN_WORD_TRANSLITERATED)));
        word.setmTermino(cursor.getString(cursor.getColumnIndex(
                DBHelper.COLUMN_WORD_TERMINOLOGY)));

        category.setmId(cursor.getInt(cursor.getColumnIndex(
                DBHelper.COLUMN_WORD_CATEGORY_ID)));
        word.setmCategory(category);

        return word;
    }

}
