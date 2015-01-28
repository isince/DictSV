package nd.dictsv.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Since on 17/1/2558.
 */
public class WordDB {

    public static final String TAG = "WordDB";

    private  Context mContext;

    //Database field
    private DBHelper mDBHelper;
    private SQLiteDatabase mDatabase;

    //data
    private String computer_Category = "Computer";
    private String math_Category = "Math";
    private ArrayList<String> mCategory = new ArrayList<String>();

    public WordDB() {}

    public WordDB(Context context){
        this.mContext = context;


    }

    public void createCategory(){
        mCategory.add("Category");
        mCategory.add(1, computer_Category);
        mCategory.add(2, math_Category);

        //Category
        CategoryDAO categoryDAO = new CategoryDAO(mContext);
        categoryDAO.addCategory(mCategory.get(getIndexByName(computer_Category)));
        categoryDAO.addCategory(mCategory.get(getIndexByName(math_Category)));


    }

    public void createWord(){
        //Word
        WordDAO wordDAO = new WordDAO(mContext);

        //Array list start at 0
        wordDAO.addWord("A", "เอ", null, getIndexByName(computer_Category));
        wordDAO.addWord("Air", "แอร์", null, getIndexByName(computer_Category));
        wordDAO.addWord("Asia", "เอเซีย", null, getIndexByName(computer_Category));
        wordDAO.addWord("B", "บี", null, getIndexByName(computer_Category));
        wordDAO.addWord("C", "ซี", null, getIndexByName(computer_Category));
        wordDAO.addWord("Computer", "คอมพิวเตอร์", null, getIndexByName(computer_Category));
        wordDAO.addWord("E", "อี", null, getIndexByName(math_Category));
        wordDAO.addWord("F", "เอฟ", null, getIndexByName(math_Category));
        wordDAO.addWord("G", "จี", null, getIndexByName(computer_Category));
        wordDAO.addWord("H", "เฮซ", null, getIndexByName(computer_Category));

        for(int i=0; i<100; i++){
            wordDAO.addWord("zzz"+i, "แซ่ด" , null,
                    getIndexByName(computer_Category));
        }

    }

    //Getting Index of an item in an arraylist;
    public int getIndexByName(String name){
        for(String word : mCategory){
            if(word.equals(name)){
                return mCategory.indexOf(word);
            }
        }
        return -1;
    }
}
