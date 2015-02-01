package nd.dictsv.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import java.util.HashMap;

import nd.dictsv.Adaptor.CustomAdapter2;
import nd.dictsv.DAO.Category;
import nd.dictsv.DAO.CategoryDAO;
import nd.dictsv.DAO.Favorite;
import nd.dictsv.DAO.FavoriteDAO;
import nd.dictsv.DAO.Word;
import nd.dictsv.DAO.WordDAO;

/**
 * Created by Since on 1/2/2558.
 */
public class SearchingTask2 extends AsyncTask<HashMap<Long,Word>, Integer, HashMap<Long,Word>> {

    Context mContext;
    ListView mListview;

    CategoryDAO categoryDAO;
    FavoriteDAO favoriteDAO;
    WordDAO wordDAO;

    HashMap<Long, Word> words;
    HashMap<Long, Favorite> favorites;
    String inputText;
    int categoryID;

    public SearchingTask2(Context context, ListView listView,
                         String inputText, int catergoryID) {
        this.mContext = context;
        this.mListview = listView;
        this.inputText = inputText;
        this.categoryID = catergoryID;

    }

    @Override
    protected void onPreExecute() {
        wordDAO = new WordDAO(mContext);
        favoriteDAO = new FavoriteDAO(mContext);
        if(categoryID==0 && inputText.equals(String.valueOf(0))) { //favorite tab
            //words = favoriteDAO.getAllFavoriteWord();
            favorites = favoriteDAO.getAllFavorite();
            words = wordDAO.getAllWordHashMapLong();
        } else if (categoryID==0){ //Search tab
            words = wordDAO.getAllWordHashMapLong();
        } else {
            //TODO john เลือกคำศัพท์ตามหมวด
            //words = wordDAO.getWordByCategoryID(categoryID);
        }
    }

    @Override
    protected HashMap<Long, Word> doInBackground(HashMap<Long, Word>... params) {
        HashMap<Long, Word> AutoText_Words = new HashMap<>();
        int textLength = inputText.length();

        if (inputText.length() == 0) {
            return null;
        } else {
            if (inputText.matches("[ก-๙].*")) {
                //Message.toast2(mContext, "thai");

                for (long keyWord : words.keySet()) {
                    Word word = words.get(keyWord);
                    try {
                        if (inputText.equalsIgnoreCase(word.getmTrans()
                                .subSequence(0, textLength).toString())) {
                            AutoText_Words.put(word.getmId(), word);
                        } else if (inputText.equalsIgnoreCase(word.getmTermino()
                                .subSequence(0, textLength).toString())) {
                            AutoText_Words.put(word.getmId(), word);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (inputText.matches("[a-z,A-Z].*")) {
                //Message.toast2(mContext, "Eng");

                for (long keyWord : words.keySet()) {
                    Word word = words.get(keyWord);
                    try {
                        if (inputText.equalsIgnoreCase(word.getmWord()
                                .subSequence(0, textLength).toString())) {
                            AutoText_Words.put(word.getmId(), word);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (inputText.matches(String.valueOf(0))) {
                /**
                 *  For favorite tab
                 *  fav
                 */
                Word word = new Word();
                for(long keyFavorite : favorites.keySet()) {
                    if (words.get(keyFavorite) != null)
                        word = words.get(keyFavorite);
                    AutoText_Words.put(word.getmId(), word);
                }
                //AutoText_Words = words;
            }
            return AutoText_Words;
        }
    }

    @Override
    protected void onPostExecute(HashMap<Long, Word> words) {
        categoryDAO = new CategoryDAO(mContext);
        HashMap<Integer,Category> categories = categoryDAO.getAllCategoryHashmap();

        mListview.setAdapter(new CustomAdapter2(mContext, words, categories));
    }
}
