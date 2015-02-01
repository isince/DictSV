package nd.dictsv.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import nd.dictsv.DAO.Category;
import nd.dictsv.DAO.CategoryDAO;
import nd.dictsv.DAO.Word;
import nd.dictsv.DAO.WordDAO;
import nd.dictsv.Debug.Message;
import nd.dictsv.Interface.getDataTask;
import nd.dictsv.AsyncTask.getSpinnerTask;
import nd.dictsv.R;


/**
 * Created by ND on 9/7/2557.
 */

public class EditFragment extends Fragment implements getDataTask {

    private final String TAG = "EditFragment";

    public static EditFragment newInstance(){
        EditFragment fragment = new EditFragment();
        return fragment;
    }

    WordDAO wordDAO;
    CategoryDAO categoryDAO;
    List<String> categorylist;
    Category category;

    Word word;

    private EditText edt_vocab_word, edt_vocab_termino, edt_vocab_trans, edt_cat_name;
    private Spinner spn_vocab_cat_list, spn_cat_list;
    private Button btn_vocab_save, btn_vocab_clear, btn_cat_save, btn_cat_clear, btn_cat_delete;
    private View rootView;

    private int vocabSpinerCatID, cateSpinerCatID, vocabCatID, cateCatID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_edit, container, false);

        //initials widget
        initWidget();
        LoadCateSpinner();

        btn_vocab_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addWord();
            }
        });
        btn_vocab_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearEditText();
            }
        });

        btn_cat_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCategory();
            }
        });
        btn_cat_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearEditText();
            }
        });
        btn_cat_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCategory();
            }
        });

        edt_vocab_word.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //
                CheckVocab();

            }
        });
        edt_vocab_trans.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                CheckVocab();
            }
        });
        edt_vocab_termino.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                CheckVocab();
            }
        });
        edt_cat_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(edt_cat_name.getText().toString().trim().isEmpty()) btn_cat_save.setEnabled(false);
                else btn_cat_save.setEnabled(true);
            }
        });

        return rootView;
    }

    public void addWord() {
        boolean chk = true;
        if (!isEmpty(edt_vocab_word) && (!isEmpty(edt_vocab_termino) || !isEmpty(edt_vocab_trans))
                && vocabSpinerCatID > 0) {
            Message.toast2(getActivity(), "have text");

            word = getTextToWord();

            wordDAO = new WordDAO(getActivity());

            for (Word word1 : wordDAO.getWordByCategoryID(word.getmCategory().getmId())){
                if (word1.getmWord().toLowerCase().equals(word.getmWord().toLowerCase())){
                    Message.LogE("addWord forLoop","พบคำซ้ำ-"+word.getmWord());
                    chk = false;
                    break;
                }
            }

            if (chk)wordDAO.addWord(word.getmWord(), word.getmTrans(), word.getmTermino(),
                    word.getmCategory());

            clearEditText();
        } else {
            Message.toast2(getActivity(), "editText is Empty");
        }

        clearEditText();
        LoadCateSpinner();
    }

/*    public void addCategory() {
        Boolean chk = false;
        category = new Category();
        categoryDAO = new CategoryDAO(getActivity());

        category = getTextToCategory();

        if (!isEmpty(edt_cat_name)) {
            Message.toast2(getActivity(), "have text");
            if (cateSpinerCatID == 0) {//New
                for (String categoryName : categoryDAO.getAllCategoryList()){
                    if(category.getmName().trim().equals(categoryName)){
                        Message.LogE("addCategory forLoop","พบหมวดซ้ำ-"+category.getmName());
                        chk = false;
                    }
                    else chk = true;
                }
                if(chk) categoryDAO.addCategory(category.getmName().trim());
            } else {//Edit
                categoryDAO.updateCategory(category);
            }
        } else {
            Message.toast2(getActivity(), "EditText is Empty");
        }

        clearEditText();
        LoadCateSpinner();
    }*/

    public void addCategory() {
        categoryDAO = new CategoryDAO(getActivity());

        Boolean chk = false;

        category = new Category();
        category = getTextToCategory();

        if (!isEmpty(edt_cat_name)) {
            Message.toast2(getActivity(), "have text");
            if (cateSpinerCatID == 0) {//New
                for (String categoryName : categorylist){
                    if(category.getmName().trim().equals(categoryName)){
                        Message.LogE("addCategory forLoop","พบหมวดซ้ำ-"+category.getmName());
                        chk = false;
                    }
                    else chk = true;
                }
                if(chk) categoryDAO.addCategory(category.getmName().trim());
            } else {//Edit
                categoryDAO.updateCategory(category);
            }
        } else {
            Message.toast2(getActivity(), "EditText is Empty");
        }

        clearEditText();
        LoadCateSpinner();
    }

    public void deleteCategory() {
        categoryDAO = new CategoryDAO(getActivity());

        category = new Category();
        category = getTextToCategory();

        /*categoryDAO.deleteCategory(category);
        *//*TODO john return method
        *//*categoryDAO.deleteCategory must have return boolean
        because it if easy to check success*//*


        if (categoryDAO.getAllCategoryList().isEmpty()) {
            vocabCatID = 0;
            Message.LogE("if onNothingSelected", "vocabCatID : " + vocabCatID);
        }
        LoadCateSpinner();
        clearEditText();*/

        new AlertDialog.Builder(getActivity())
                .setTitle("Confirm Delete")
                .setMessage("Are you sure you want delete this category?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        categoryDAO.deleteCategory(category);

                        //TODO john delete favoriteWord

                        if (categoryDAO.getAllCategoryList().isEmpty()) {
                            vocabCatID = 0;
                            Message.LogE("if onNothingSelected", "vocabCatID : " + vocabCatID);
                        }
                        LoadCateSpinner();
                        clearEditText();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        clearEditText();
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /*public void LoadCateSpinner2() {

        final CategoryDAO categoryDAO = new CategoryDAO(getActivity());

        List<String> CategoryList = categoryDAO.getAllCategoryList();
        final List<String> CategoryList2 = new ArrayList<>();
        CategoryList2.add("New Category");
        for (String category : categoryDAO.getAllCategoryList()) {
            CategoryList2.add(category);
        }

        ArrayAdapter<String> vocabArrayAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, CategoryList);
        ArrayAdapter<String> cateArrayAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, CategoryList2);

        //setAdapter
        spn_vocab_cat_list.setAdapter(vocabArrayAdapter);
        spn_cat_list.setAdapter(cateArrayAdapter);

        spn_vocab_cat_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //set position item
                //id+=1;
                vocabSpinerCatID = (int) id + 1;
                //cateSpinerCatName = CategoryList2.get(vocabSpinerCatID);
                Category category = categoryDAO.getCatIDByName(CategoryList2.get(vocabSpinerCatID));
                vocabCatID = category.getmId();
                //Log-Debug Toast onItemSelected category
                Message.LogE("onItemSelected", "vocabCatID : " + vocabCatID);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spn_cat_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //set position item
                cateSpinerCatID = (int) id;

                //set enable button
                if(cateSpinerCatID>0) btn_cat_delete.setEnabled(true);
                else btn_cat_delete.setEnabled(false);

                Category category = categoryDAO.getCatIDByName(CategoryList2.get(cateSpinerCatID));
                cateCatID = category.getmId();
                //Log-Debug Toast onItemSelected category
                Message.LogE("onItemSelected", "cateCatID : " + cateCatID);
                if (id == 0) {//New category
                    //setText create
                    btn_cat_save.setText(R.string.category_submit_add_btn);
                    edt_cat_name.setHint(R.string.category_name_add_hint_tv);
                    //Log-Debug Toast onItemSelected category
                    //Message.toast2(getActivity(), "New Category : " + cateSpinerCatID);
                } else {
                    //setText Edit
                    btn_cat_save.setText(R.string.category_submit_edit_btn);
                    edt_cat_name.setHint(R.string.category_name_edit_hint_tv);
                    //Log-Debug Toast onItemSelected category
                    //Message.toast2(getActivity(), "Edit Category: " + cateSpinerCatID);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }*/

    public void LoadCateSpinner() {

        final CategoryDAO categoryDAO = new CategoryDAO(getActivity());

        getSpinnerTask getSpinnerTask = new getSpinnerTask(getActivity(),
                spn_vocab_cat_list, spn_cat_list, this);
        getSpinnerTask.execute();

        spn_vocab_cat_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //set position item
                //id+=1;
                vocabSpinerCatID = (int) id + 1;
                //cateSpinerCatName = CategoryList2.get(vocabSpinerCatID);
                Category category = categoryDAO.getCatIDByName(categorylist.get(vocabSpinerCatID));
                vocabCatID = category.getmId();
                //Log-Debug Toast onItemSelected category
                Message.LogE("onItemSelected", "vocabCatID : " + vocabCatID);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spn_cat_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //set position item
                cateSpinerCatID = (int) id;

                //set enable button
                if(cateSpinerCatID>0) btn_cat_delete.setEnabled(true);
                else btn_cat_delete.setEnabled(false);

                Category category = categoryDAO.getCatIDByName(categorylist.get(cateSpinerCatID));
                cateCatID = category.getmId();
                //Log-Debug Toast onItemSelected category
                Message.LogE("onItemSelected", "cateCatID : " + cateCatID);
                if (id == 0) {//New category
                    //setText create
                    btn_cat_save.setText(R.string.category_submit_add_btn);
                    edt_cat_name.setHint(R.string.category_name_add_hint_tv);
                    //Log-Debug Toast onItemSelected category
                    //Message.toast2(getActivity(), "New Category : " + cateSpinerCatID);
                } else {
                    //setText Edit
                    btn_cat_save.setText(R.string.category_submit_edit_btn);
                    edt_cat_name.setHint(R.string.category_name_edit_hint_tv);
                    //Log-Debug Toast onItemSelected category
                    //Message.toast2(getActivity(), "Edit Category: " + cateSpinerCatID);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initWidget() {
        //initWidgetVocab
        edt_vocab_word = (EditText) rootView.findViewById(R.id.vocab_word_edt);
        edt_vocab_termino = (EditText) rootView.findViewById(R.id.vocab_terminology_edt);
        edt_vocab_trans = (EditText) rootView.findViewById(R.id.vocab_transliterated_edt);

        spn_vocab_cat_list = (Spinner) rootView.findViewById(R.id.vocab_cate_spn);
        btn_vocab_save = (Button) rootView.findViewById(R.id.vocab_save_btn);
        btn_vocab_clear = (Button) rootView.findViewById(R.id.vocab_clear_btn);

        //initWidgetCate
        edt_cat_name = (EditText) rootView.findViewById(R.id.cat_name_edt);
        spn_cat_list = (Spinner) rootView.findViewById(R.id.categories_spn);
        btn_cat_save = (Button) rootView.findViewById(R.id.cat_save_btn);
        btn_cat_clear = (Button) rootView.findViewById(R.id.cat_clear_btn);
        btn_cat_delete = (Button) rootView.findViewById(R.id.cat_delete_btn);

    }

    private Word getTextToWord() {
        Word word = new Word();

        word.setmWord(edt_vocab_word.getText().toString());
        word.setmTermino(edt_vocab_termino.getText().toString());
        word.setmTrans(edt_vocab_trans.getText().toString());

        Category category = new Category();
        category.setmId(vocabCatID);
        word.setmCategory(category);

        return word;
    }

    private Category getTextToCategory() {
        Category category = new Category();
        category.setmId(cateCatID);
        category.setmName(edt_cat_name.getText().toString().trim());

        return category;
    }

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
    }

    public void CheckVocab() {
        Message.LogE("afterTextChanged", "Vocab");
        if (!isEmpty(edt_vocab_word) && (!isEmpty(edt_vocab_termino) || !isEmpty(edt_vocab_trans))
                && vocabCatID > 0) {
            btn_vocab_save.setEnabled(true);
        } else {
            btn_vocab_save.setEnabled(false);
        }
    }

    private void clearEditText() {
        //Vocab
        edt_vocab_word.setText("");
        edt_vocab_termino.setText("");
        edt_vocab_trans.setText("");

        //Category
        edt_cat_name.setText("");
    }

    @Override
    public void getCategories(List<String> categories) {
        Message.LogE("loadSpinnerTask", "getCategories");
        this.categorylist = categories;
    }

}
