package nd.dictsv;


import android.app.FragmentTransaction;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import nd.dictsv.Adaptor.TabPageAdapter;
import nd.dictsv.DAO.Category;
import nd.dictsv.DAO.DBHelper;
import nd.dictsv.DAO.Word;
import nd.dictsv.DAO.WordDB;


public class MyActivity extends ActionBarActivity implements ActionBar.TabListener{
    /////////////////////////////////
    private ViewPager mViewPager;
    private TabPageAdapter mTapAdapter;
    //private android.app.ActionBar mActionBar; // Actionbar เฉยๆไม่ได้
    private ActionBar actionBar;
    //Tab title
    private String[] tabs = {"Search", "Favorited", "Edit"};
    //////////////////////////////////

    DBHelper mDBHelper;
    SQLiteDatabase mDb;

    WordDB wordDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        /////////////////////////////////////////////////////////////////////////////////////
        //Datebase
        mDBHelper = new DBHelper(this);
        mDb = mDBHelper.getWritableDatabase();

        //Renew Database
        mDBHelper.onUpgrade(mDb, 1, 1);
        mDb.close();

        //add Data
        wordDB = new WordDB(this);
        wordDB.createCategory();
        wordDB.createWord();
        /////////////////////////////////////////////////////////////////////////////////////

        //เชื่อม Adaptor กับ View ใน activity_maim
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        // set Adapter
        mTapAdapter = new TabPageAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mTapAdapter);
        //จบเชื่อม จะทำแค่ส่วนนี้ก็ได้แต่จะไม่มี Tab แค่ swipe ซ้ายขวาได้อยางเดียว

        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        for (String Tab_name : tabs){
            actionBar.addTab(actionBar.newTab().setText(Tab_name)
                    .setTabListener(this));
        }
        // set Listener in View
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //auto Implement 3 method
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i("Check","mViewpager >> "+position);
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
    //Actionbar.TabListener
    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
        Log.i("Check","setCurentItem Position >> "+tab.getPosition());
        //
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

