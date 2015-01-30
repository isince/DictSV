package nd.dictsv.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import nd.dictsv.Adaptor.CustomAdapter2;
import nd.dictsv.DAO.Category;
import nd.dictsv.DAO.CategoryDAO;
import nd.dictsv.DAO.FavoriteDAO;
import nd.dictsv.DAO.WordDAO;
import nd.dictsv.Debug.Message;
import nd.dictsv.R;

/**
 * Created by ND on 9/7/2557.
 */
public class FavoriteFragment extends Fragment {

    CategoryDAO categoryDAO;
    FavoriteDAO favoriteDAO;

    ListView listViewFavorite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);

        //matching
        listViewFavorite = (ListView) rootView.findViewById(R.id.Favorite_listView);

        categoryDAO = new CategoryDAO(getActivity());
        favoriteDAO = new FavoriteDAO(getActivity());

        ViewPager viewPager = (ViewPager) container;
        ((ViewPager) container).setCurrentItem(1);

        return rootView;
    }

}
