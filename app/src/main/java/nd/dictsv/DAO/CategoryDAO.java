package nd.dictsv.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nd.dictsv.Debug.Message;

/**
 * Created by Since on 13/1/2558.
 */
public class CategoryDAO {

    public static final String TAG = "CategoryDAO";

    private Context mContext;
    private Category category;
    private ArrayList<String> listCategory;

    //Database field
    private SQLiteDatabase mDatabase;
    private DBHelper mDbHelper;
    private String[] mAllColumns = { DBHelper.COLUMN_CATEGORY_ID,
                                     DBHelper.COLUMN_CATEGORY_NAME};

    public CategoryDAO(Context context) {
        this.mContext = context;
        mDbHelper = new DBHelper(context);

        //open database
        try{
            open();
        } catch (SQLException e){
            Message.longToast(mContext, TAG, e.getMessage());
            //Log.e(TAG, "SQLException on opening database" + e.getMessage());
        }
    }

    public void open()throws SQLException{
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close(){
        mDbHelper.close();
    }

    //Void
    public void addCategory(String name){
        //Insert content value
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_CATEGORY_NAME, name);

        //insert row and get id category
        long insertId = mDatabase.insert(DBHelper.TABLE_CATEGORY, null, values);

        //Log-Debug addCategory
        ////Check Category ID
        if(insertId>0){
            Message.longToast(mContext, TAG, "Create " + insertId + " " + name);
        } else {
            Message.longToast(mContext, TAG, "No category create");
        }
    }

    //void
    public void addCategory(Category category){
        //mDatabase = mDbHelper.getWritableDatabase();

        //Insert content value
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_CATEGORY_NAME, category.getmName());

        //insert row and get id category
        long insertId = mDatabase.insert(DBHelper.TABLE_CATEGORY, null, values);

        //Log-Debug addCategory(object)
        //Check Category ID
        if(insertId>0){
            Message.shortToast(mContext, TAG, "Create " + insertId + " " + category.getmName());
        } else {
            Message.shortToast(mContext, TAG, "No category create");
        }
    }

    //retrun List Category
    public List<Category> getAllCategory(){

        List<Category> listCategory= new ArrayList<>();
        category = new Category();
        listCategory.add(new Category("Category"));

        //Select All
        //SELECT _id, cate_name FROM CATEGORY
        Cursor cursor = mDatabase.query(DBHelper.TABLE_CATEGORY, mAllColumns,
                null, null, null, null, null);

        //Buffer
        StringBuffer buffer = new StringBuffer();

        if (cursor!=null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){

                category = cursorToCategory(cursor);
                listCategory.add(category);

                ////Log-Debug getAllCategory StringBuffer
                //add String Buffer
                buffer.append(cursor.getString(
                        cursor.getColumnIndex(DBHelper.COLUMN_CATEGORY_ID)) + " ");
                buffer.append(cursor.getString(
                        cursor.getColumnIndex(DBHelper.COLUMN_CATEGORY_NAME)));
                buffer.append("\n");

                //move cursor
                cursor.moveToNext();
            }
        } else {
            Message.longToast(mContext, TAG, "Cursor not create");
        }

        //Log-Debug getAllCategory
        //Message.toast2(mContext, buffer.toString());

        //close
        cursor.close();

        return listCategory;
    }

    public HashMap<Integer ,Category> getAllCategoryHashmap() {
        HashMap<Integer,Category> mapCategoryDAO = new HashMap<>();

        //SELECT ALL FROM category
        Cursor cursor = mDatabase.query(DBHelper.TABLE_CATEGORY, mAllColumns,
                null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();

            while (!cursor.isAfterLast()){
                category = cursorToCategory(cursor);
                mapCategoryDAO.put(category.getmId(), category);

                //move cursor
                cursor.moveToNext();
            }
        }

        //Log-Debug toast all HashMap
        for (int id : mapCategoryDAO.keySet()){
            Message.LogE(TAG, "HashMap : " + id);
        }

        cursor.close();

        return mapCategoryDAO;
    }

    //return arraylist<String>
    public List<String> getAllCategoryList(){

        listCategory = new ArrayList<>();
        category = new Category();


        StringBuffer buffer = new StringBuffer();

        //Selectall
        ////SELCET _id, cate_name FROM Category
        Cursor cursor = mDatabase.query(DBHelper.TABLE_CATEGORY, mAllColumns,
                null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){

                category = cursorToCategory(cursor);
                listCategory.add(category.getmName());

                ////Log-Debug getallCategoryList StringBuffer
                //add String buffer
                buffer.append(category.getmId()
                        + " - " + category.getmName());

                cursor.moveToNext();

                if (!cursor.isAfterLast()) buffer.append("\n");
            }
        }

        //Toast listAll
        //Message.toast2(mContext, buffer.toString());

        return listCategory;
    }

    //return category object
    public void getCategoryById(long id){

        //SELECT * FROM category WHERE category_id = id
        String[] whereArgs = new String[]{String.valueOf(id)};
        Cursor cursor = mDatabase.query(DBHelper.TABLE_CATEGORY, mAllColumns,
                DBHelper.COLUMN_CATEGORY_ID + "= ?",whereArgs, null, null, null, null);

        //cursor management
        if (cursor!=null){
            cursor.moveToFirst();
        }

        Category category = cursorToCategory(cursor);

        //Log-Debug getCategoryById
        Message.longToast(mContext, TAG, category.getmId() + " - " + category.getmName());

        //close
        cursor.close();
    }

    //void
    public void updateCategory(Category category){

        //adding content
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_CATEGORY_NAME, category.getmName());

        //update
        //UPDATE category SET category_name ="New_name" WHERE category_name = "name"
        String[] whereArgs = new String[]{String.valueOf(category.getmId())};
        long updateID = mDatabase.update(DBHelper.TABLE_CATEGORY, values,
                DBHelper.COLUMN_CATEGORY_ID + "=?",whereArgs);

        //Log-Debug updateCategory
        if (updateID>0) {
            Message.longToast(mContext, TAG, "Successful - " + category.getmName());
        } else {
            Message.longToast(mContext, TAG, "No category update");
        }
    }

    //void
    public void deleteCategory(Category category){
        WordDAO wordDAO = new WordDAO(mContext);
        List<Word> listWords = new ArrayList<Word>();
        int delID = 0;

        listWords = wordDAO.getWordByCategoryID(category.getmId());

        if (!listWords.isEmpty()){
            Message.toast2(mContext, "มีคำศัพท์");
            for(Word mWord : listWords){
                wordDAO.deleteWord(mWord);
            }
        } else {
            Message.shortToast(mContext, TAG, "Category no data");
        }

        Message.toast2(mContext, "Delete..." + category.getmId() + " - " + category.getmName());
        String[] whereArgs = new String[]{String.valueOf(category.getmId())};
        delID = mDatabase.delete(DBHelper.TABLE_CATEGORY,
                DBHelper.COLUMN_CATEGORY_ID + "=?",whereArgs);

        //Log-Debug deleteCategory
        //database.delete return 1(Successful) and 0(Unsuccessful)
        if (delID>0) {
            Message.longToast(mContext, TAG, "Delete Successful");
        } else {
            Message.longToast(mContext, TAG, "Unsuccessful Delete");
        }
    }

    public Category getCatIDByName(String categoryName){

        //SELECT * FROM category WHERE category_id = id
        Category category = new Category();
        String[] whereArgs = new String[]{String.valueOf(categoryName)};
        Cursor cursor = mDatabase.query(DBHelper.TABLE_CATEGORY, mAllColumns,
                DBHelper.COLUMN_CATEGORY_NAME + "=?",whereArgs, null, null, null, null);

        //cursor management
        if (cursor!=null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                category = cursorToCategory(cursor);

                cursor.moveToNext();
            }
        } else {
            Message.LogE(TAG, "getCatIDByName is null");
        }



        //Log-Debug getCategoryById
        Message.longToast(mContext, TAG, category.getmId() + " - " + category.getmName());

        //close
        cursor.close();
        return category;
    }

    private Category cursorToCategory(Cursor cursor){
        Category category = new Category();
        category.setmId(cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_CATEGORY_ID)));
        category.setmName(cursor.getString(
                cursor.getColumnIndex(DBHelper.COLUMN_CATEGORY_NAME)));

        return category;
    }


}
