package nd.dictsv.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import nd.dictsv.AsyncTask.SearchingTask2;
import nd.dictsv.DAO.CategoryDAO;
import nd.dictsv.DAO.FavoriteDAO;
import nd.dictsv.Debug.Message;
import nd.dictsv.R;
import nd.dictsv.AsyncTask.SearchingTask;

/**
 * Created by ND on 9/7/2557.
 */
public class FavoriteFragment extends Fragment{

    public static FavoriteFragment newInstance(){
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }

    CategoryDAO categoryDAO;
    FavoriteDAO favoriteDAO;

    ListView listViewFavorite;

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_favorite, container, false);

        listViewFavorite = (ListView) rootView.findViewById(R.id.Favorite_listView);
        setAdapter();
        //categoryDAO = new CategoryDAO(getActivity());
        //favoriteDAO = new FavoriteDAO(getActivity());


        //setAdapter();
        return rootView;
    }

    @Override
    public void onStart() {
        Message.LogE("FavoriteFragment", "onStart");

        super.onStart();
    }

    public void setAdapter(){
        Message.LogE("FavoriteFragment", "setAdapter");

        /*SearchingTask searchingTask = new SearchingTask(getActivity(), listViewFavorite,
                String.valueOf(0), new FavoriteDAO(this.getActivity()).getAllFavoriteWord());*/
        SearchingTask2 searchingTask = new SearchingTask2(getActivity(), listViewFavorite,
                String.valueOf(0), 0);
        searchingTask.execute();

    }
}
