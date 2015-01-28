package nd.dictsv.Fragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nd.dictsv.Adaptor.CustomAdapter2;
import nd.dictsv.DAO.Category;
import nd.dictsv.DAO.CategoryDAO;
import nd.dictsv.DAO.Word;
import nd.dictsv.DAO.WordDAO;
import nd.dictsv.Debug.Message;
import nd.dictsv.R;
import nd.dictsv.SeachingTask;


/**
 * Created by ND on 9/7/2557.
 */
public class SearchFragment extends Fragment {

    private static final String TAG = "SearchFragment";

    private View rootView;
    EditText edt_search;
    ListView listViewSearch;

    private List<Word> words;
    HashMap<String, Word> wordHashMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_search, container, false);

        //view matching
        listViewSearch = (ListView) rootView.findViewById(R.id.search_listView);
        edt_search = (EditText) rootView.findViewById(R.id.edt_search);

        //search by catID (0 : All category)
        WordDAO wordDAO = new WordDAO(getActivity());
        //words = wordDAO.getAllWord();
        //TODO john
        wordHashMap = wordDAO.getAllWordHashMap();

        if (wordHashMap.isEmpty()) Message.shortToast(getActivity(), TAG, "Empty");
        CustomAdapter2 customAdapter2 = new CustomAdapter2(getActivity(), wordHashMap,
                new CategoryDAO(getActivity()).getAllCategory());
        listViewSearch.setAdapter(customAdapter2);

        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Toast.makeText(getActivity(),"baforeTextChange",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Toast.makeText(getActivity(),"onTextChange",Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
                Message.toast2(getActivity(), "afterTextChanged");

                String inputText = edt_search.getText().toString();
                if (inputText.length()==0) {
                    listViewSearch.setAdapter(null);
                } else {
                    /*CustomAdapter2 autoCompleteAdapter =
                            listviewAutocomplete(inputText, wordHashMap);
                    listViewSearch.setAdapter(autoCompleteAdapter);*/
                    SeachingTask seachingTask = new SeachingTask(getActivity(), listViewSearch,
                            inputText, wordHashMap);
                    seachingTask.execute();
                }

            }
        });

        return rootView;
    }

    private CustomAdapter2 listviewAutocomplete(String inputText, HashMap<String,Word> words){
        CategoryDAO categoryDAO = new CategoryDAO(getActivity());
        List<Category> categories = categoryDAO.getAllCategory();

        HashMap<String,Word> AutoText_Words = new HashMap<>();
        int textLength = inputText.length();

        if (inputText.length()==0){
            return null;
        } else {
            if (inputText.matches(".*[ก-๙].*")) {
                Message.toast2(getActivity(), "thai");

                for(String keyWord : words.keySet()){
                    Word word = words.get(keyWord);
                    try{
                        if (inputText.equalsIgnoreCase(word.getmTrans()
                                .subSequence(0, textLength).toString())){
                            AutoText_Words.put(word.getmWord(), word);
                        } else if(inputText.equalsIgnoreCase(word.getmTermino()
                                .subSequence(0, textLength).toString())) {
                            AutoText_Words.put(word.getmWord(), word);
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            else if (inputText.matches("[a-z,A-Z].*")){
                Message.toast2(getActivity(), "Eng");

                for(String keyWord : words.keySet()){
                    Word word = words.get(keyWord);
                    try {
                        if (inputText.equalsIgnoreCase(word.getmWord()
                                .subSequence(0, textLength).toString())){
                            AutoText_Words.put(word.getmWord(), word);
                        }else if(inputText.equalsIgnoreCase(word.getmTermino()
                                .subSequence(0, textLength).toString())) {
                            AutoText_Words.put(word.getmWord(), word);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            return new CustomAdapter2(getActivity(), AutoText_Words, categories);

        }
    }
}
