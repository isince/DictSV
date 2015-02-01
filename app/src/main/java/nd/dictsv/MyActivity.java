package nd.dictsv;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import nd.dictsv.Adaptor.CustomAdapter2;
import nd.dictsv.Adaptor.TabPageAdapter;
import nd.dictsv.DAO.DBHelper;
import nd.dictsv.DAO.WordDB;
import nd.dictsv.Debug.Message;
import nd.dictsv.Fragment.FavoriteFragment;


public class MyActivity extends ActionBarActivity implements ActionBar.TabListener{

    private ViewPager mViewPager;
    private TabPageAdapter mTapAdapter;
    private ActionBar actionBar;

    //Tab title
    private String[] Tabs;

    DBHelper mDBHelper;
    SQLiteDatabase mDb;
    WordDB wordDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Tabs = new String[]{getResources().getString(R.string.Tab_tittle_search),
                            getResources().getString(R.string.Tab_tittle_Favorited),
                            getResources().getString(R.string.Tab_tittle_Edit)};

        /////////////////////////////////////////////////////////////////////////////////////
        //Datebase
        mDBHelper = new DBHelper(this);
        mDb = mDBHelper.getWritableDatabase();

        ////Renew Database
        mDBHelper.onUpgrade(mDb, 1, 1);
        mDb.close();

        ////add Data
        wordDB = new WordDB(this);
        wordDB.createCategory();
        wordDB.createWord();
        wordDB.createFavorite();
        /////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////
        //ViewPager
        ////Matching view
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        ////Set Adapter
        mTapAdapter = new TabPageAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mTapAdapter);

        //ActionBar Tab
        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        for(String TabName : Tabs){
            actionBar.addTab(actionBar.newTab().setText(TabName).setTabListener(this));
        }
        ////////////////////////////////////////////////////////////////////////////////////

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Message.LogE("onPageScrolled", "");
            }

            @Override
            public void onPageSelected(int position) {
                Message.LogE("onPageSelected", String.valueOf(position));
                /*//((TabPageAdapter) mViewPager.getAdapter()).getLog();
                Fragment fragment = ((FragmentPagerAdapter) mViewPager.getAdapter()).getItem(position);

                if (position == 1 && fragment != null) {
                    Message.LogE("onPageSelected", "fragment !null");
                    //fragment.onResume();
                    //fragment.onDestroy();
                    //FavoriteFragment.newInstance();

                } else {
                    Message.LogE("onPageSelected", "null");
                }*/

                Fragment fragment = getActiveFragment(mViewPager, 1);
                FavoriteFragment favoriteFragment = (FavoriteFragment)fragment;
                if(favoriteFragment!=null && position == 1 && CustomAdapter2.chkFavoriteList) {
                    favoriteFragment.setAdapter();
                    CustomAdapter2.chkFavoriteList = false;
                }
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //Message.LogE("onPageScrollStateChanged", String.valueOf(state));
            }
        });
    }

    public Fragment getActiveFragment(ViewPager container, int position) {
        String name = "android:switcher:" + container.getId() + ":" + position;
        return getSupportFragmentManager().findFragmentByTag(name);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }
}

