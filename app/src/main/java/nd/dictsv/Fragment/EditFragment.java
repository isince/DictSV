package nd.dictsv.Fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import nd.dictsv.DAO.Category;
import nd.dictsv.DAO.CategoryDAO;
import nd.dictsv.DAO.DBHelper;
import nd.dictsv.DAO.Word;
import nd.dictsv.DAO.WordDAO;
import nd.dictsv.DAO.WordDB;
import nd.dictsv.Debug.Message;
import nd.dictsv.R;


/**
 * Created by ND on 9/7/2557.
 */

public class EditFragment extends Fragment {

    private final String TAG = "EditFragment";

    Cursor mCorsor;
    WordDAO wordDAO;
    CategoryDAO categoryDAO;

    Category category;
    Word word;

    private EditText edt_vocab_word, edt_vocab_termino, edt_vocab_trans,  edt_cat_name;
    private Spinner spn_vocab_cat_list, spn_cat_list;
    private Button btn_vocab_save, btn_vocab_clear, btn_cat_save, btn_cat_clear, btn_cat_delete;
    private View rootView;

    private int vocabSpinerCatID;
    private int cateSpinerCatID;

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

        return rootView;
    }

    public void addWord(){
        if(!isEmpty(edt_vocab_word)&&(!isEmpty(edt_vocab_termino)||!isEmpty(edt_vocab_trans))
                && vocabSpinerCatID >0){
            Message.toast2(getActivity(), "have text");

            word = getTextToWord();

            wordDAO = new WordDAO(getActivity());
            wordDAO.addWord(word.getmWord(), word.getmTrans(), word.getmTermino(),
                    word.getmCategory());

            clearEditText();
        } else {
            Message.toast2(getActivity(), "editText is Empty");
        }

        clearEditText();
        LoadCateSpinner();
    }

    public void addCategory(){
        category = new Category();
        categoryDAO = new CategoryDAO(getActivity());

        category = getTextToCategory();

        if (!isEmpty(edt_cat_name)) {
            Message.toast2(getActivity(), "have text");
            if (cateSpinerCatID ==0){//New
                categoryDAO.addCategory(category.getmName());
            } else {//Edit
                category.setmId(cateSpinerCatID);
                categoryDAO.updateCategory(category);
            }
        } else {
            Message.toast2(getActivity(), "EditText is Empty");
        }

        clearEditText();
        LoadCateSpinner();
    }

    public void deleteCategory(){
        category = new Category();
        CategoryDAO categoryDAO = new CategoryDAO(getActivity());
        category = getTextToCategory();

        category.setmId(cateSpinerCatID);

        //TODO john create confirm dialog

        categoryDAO.deleteCategory(category);
        /*TODO john return method
        /*categoryDAO.deleteCategory must have return boolean
        because it if easy to check success*/

        LoadCateSpinner();
    }

    public Cursor LoadCateSpinner() {

        CategoryDAO categoryDAO = new CategoryDAO(getActivity());

        List<String> CategoryList = categoryDAO.getAllCategoryList();
        List<String> CategoryList2 = new ArrayList<>();
        CategoryList2.add("New Category");
        for (String category : categoryDAO.getAllCategoryList()){
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
                vocabSpinerCatID = (int)id + 1;

                //Log-Debug Toast onItemSelected category
                //Message.longToast(getActivity(), TAG, "onItemSelected ID : " + id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spn_cat_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //set position item
                cateSpinerCatID = (int)id;

                //Log-Debug Toast onItemSelected category
                //Message.longToast(getActivity(), TAG, "onItemSelected ID : " + id);

                if(id==0){//New category
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

        return mCorsor;
    }
    
    //Layout frame
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

    }//initWidget

    private Word getTextToWord(){
        Word word = new Word();

        word.setmWord(edt_vocab_word.getText().toString());
        word.setmTermino(edt_vocab_termino.getText().toString());
        word.setmTrans(edt_vocab_trans.getText().toString());

        Category category = new Category();
        category.setmId(vocabSpinerCatID);
        word.setmCategory(category);

        return word;
    }

    private Category getTextToCategory(){
        Category category = new Category();

        category.setmName(edt_cat_name.getText().toString());

        return category;
    }

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().length() == 0;
    }

    private void clearEditText() {
        //Vocab
        edt_vocab_word.setText("");
        edt_vocab_termino.setText("");
        edt_vocab_trans.setText("");

        //Category
        edt_cat_name.setText("");
    }//clearText
}
