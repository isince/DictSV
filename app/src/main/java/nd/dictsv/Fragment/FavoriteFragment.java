package nd.dictsv.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import nd.dictsv.DAO.Category;
import nd.dictsv.DAO.CategoryDAO;
import nd.dictsv.DAO.WordDAO;
import nd.dictsv.Debug.Message;
import nd.dictsv.R;

/**
 * Created by ND on 9/7/2557.
 */
public class FavoriteFragment extends Fragment {
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //bundle
       /* Bundle bundle = this.getArguments();
        int id = bundle.getInt("WordID", 0);
        Message.toast2(getActivity(), String.valueOf(id));*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);

        final WordDAO wordDAO = new WordDAO(getActivity());
        final CategoryDAO categoryDAO = new CategoryDAO(getActivity());


        //TODO initWidget Debug
        Button btn_debug_getAllWord = (Button) rootView.findViewById(R.id.debug_getAll_word_btn);
        Button btn_debug_getAllCat = (Button) rootView.findViewById(R.id.debug_getAll_cat_btn);

        btn_debug_getAllWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordDAO.getAllWord();
            }
        });
        
        btn_debug_getAllCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryDAO.getAllCategory();
            }
        });


        return rootView;
    }
}
