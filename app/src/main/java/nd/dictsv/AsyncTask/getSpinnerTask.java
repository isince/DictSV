package nd.dictsv.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import nd.dictsv.DAO.CategoryDAO;
import nd.dictsv.Interface.getDataTask;

/**
 * Created by Since on 31/1/2558.
 */

public class getSpinnerTask extends AsyncTask<Void, Void, List<String>> {

    private getDataTask getDataTask;

    Context mContext;

    public getSpinnerTask(Context context, Spinner topSpinner, Spinner bottomSpinner,
                          getDataTask getDataTask) {
        this.mContext = context;
        this.getDataTask = getDataTask;
        this.topSpinner = topSpinner;
        this.bottomSpinner = bottomSpinner;
    }

    private List<String> CategoryListTop, CategoryListBottom;
    ArrayAdapter vocabArrayAdapter, cateArrayAdapter;
    Spinner topSpinner, bottomSpinner;

    @Override
    protected List<String> doInBackground(Void... params) {

        CategoryDAO categoryDAO = new CategoryDAO(mContext);

        CategoryListTop = categoryDAO.getAllCategoryList();
        CategoryListBottom = new ArrayList<>();
        CategoryListBottom.add("New Category");
        for (String category : CategoryListTop) {
            CategoryListBottom.add(category);
        }

        vocabArrayAdapter = new ArrayAdapter<>(mContext,
                android.R.layout.simple_spinner_dropdown_item, CategoryListTop);
        cateArrayAdapter = new ArrayAdapter<>(mContext,
                android.R.layout.simple_spinner_dropdown_item, CategoryListBottom);

        return CategoryListBottom;
    }

    @Override
    protected void onPostExecute(List<String> result) {
        //setAdapter
        topSpinner.setAdapter(vocabArrayAdapter);
        bottomSpinner.setAdapter(cateArrayAdapter);

        getDataTask.getCategories(result);
    }
}

