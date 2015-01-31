package nd.dictsv.Adaptor;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import nd.dictsv.Fragment.EditFragment;
import nd.dictsv.Fragment.FavoriteFragment;
import nd.dictsv.Fragment.SearchFragment;

/**
 * Created by ND on 9/7/2557.
 */
public class TabPageAdapter extends FragmentPagerAdapter {

    public static final int NUM_PAGE = 3;

    public TabPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        //get item count ตามจำนวน Tab
        return NUM_PAGE;
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0) {
            Log.i("pagerCheck","position 1 ");
            return SearchFragment.newInstance();
        }else if (position == 1 ) {
            Log.i("pagerCheck","position 2 ");
            return FavoriteFragment.newInstance();
        }else if(position == 2) {
            Log.i("pagerCheck", "position 3 ");
            return EditFragment.newInstance();
        }
        return null;
    }
}
