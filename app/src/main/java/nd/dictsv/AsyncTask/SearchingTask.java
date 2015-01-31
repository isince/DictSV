package nd.dictsv.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import java.util.HashMap;

import nd.dictsv.Adaptor.CustomAdapter2;
import nd.dictsv.DAO.Category;
import nd.dictsv.DAO.CategoryDAO;
import nd.dictsv.DAO.Word;

/**
 * Created by Since on 28/1/2558.
 */
public class SearchingTask extends AsyncTask<HashMap<Long,Word>, Integer, HashMap<Long,Word>> {

    Context mContext;
    String inputText;
    HashMap<Long, Word> words;
    ListView listView;

    public SearchingTask(Context context, ListView listView,
                         String inputText, HashMap<Long, Word> words) {
        this.mContext = context;
        this.inputText = inputText;
        this.words = words;
        this.listView = listView;
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
             *  ดึงค่าทั้งหมดที่รับเข้ามา
             */
                AutoText_Words = words;
            }

            return AutoText_Words;
        }
    }


    @Override
    protected void onPostExecute(HashMap<Long, Word> words) {
        CategoryDAO categoryDAO = new CategoryDAO(mContext);
        HashMap<Integer,Category> categories = categoryDAO.getAllCategoryHashmap();

        listView.setAdapter(new CustomAdapter2(mContext, words, categories));
    }
}
