package nd.dictsv;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.widget.ListView;

import java.util.HashMap;
import java.util.List;

import nd.dictsv.Adaptor.CustomAdapter2;
import nd.dictsv.DAO.Category;
import nd.dictsv.DAO.CategoryDAO;
import nd.dictsv.DAO.Word;
import nd.dictsv.Debug.Message;

/**
 * Created by Since on 28/1/2558.
 */
public class SeachingTask extends AsyncTask<HashMap<String,Word>, Integer, HashMap<String,Word>> {

    Context mContext;
    String inputText;
    HashMap<String, Word> words;
    ListView listView;

    public SeachingTask(Context context,ListView listView,
                        String inputText, HashMap<String, Word> words) {
        this.mContext = context;
        this.inputText = inputText;
        this.words = words;
        this.listView = listView;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected HashMap<String, Word> doInBackground(HashMap<String, Word>... params) {
        HashMap<String, Word> AutoText_Words = new HashMap<>();
        int textLength = inputText.length();

        if (inputText.length() == 0) {
            return null;
        } else {
            if (inputText.matches(".*[ก-๙].*")) {
                //Message.toast2(mContext, "thai");

                for (String keyWord : words.keySet()) {
                    Word word = words.get(keyWord);
                    try {
                        if (inputText.equalsIgnoreCase(word.getmTrans()
                                .subSequence(0, textLength).toString())) {
                            AutoText_Words.put(word.getmWord(), word);
                        } else if (inputText.equalsIgnoreCase(word.getmTermino()
                                .subSequence(0, textLength).toString())) {
                            AutoText_Words.put(word.getmWord(), word);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else if (inputText.matches("[a-z,A-Z].*")) {
                //Message.toast2(mContext, "Eng");

                for (String keyWord : words.keySet()) {
                    Word word = words.get(keyWord);
                    try {
                        if (inputText.equalsIgnoreCase(word.getmWord()
                                .subSequence(0, textLength).toString())) {
                            AutoText_Words.put(word.getmWord(), word);
                        } else if (inputText.equalsIgnoreCase(word.getmTermino()
                                .subSequence(0, textLength).toString())) {
                            AutoText_Words.put(word.getmWord(), word);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return AutoText_Words;
        }
    }


    @Override
    protected void onPostExecute(HashMap<String, Word> words) {
        CategoryDAO categoryDAO = new CategoryDAO(mContext);
        List<Category> categories = categoryDAO.getAllCategory();
        HashMap<String, Category> categoryHashMap = new HashMap<>();
        //List<Category> categories = categoryDAO.getAllCategory();
        for (Category category : categoryDAO.getAllCategory()) {
            categoryHashMap.put(category.getmName(), category);
        }

        //return new CustomAdapter2(mContext, words, categories);
        listView.setAdapter(new CustomAdapter2(mContext, words, categories));
    }
}
