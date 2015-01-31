package nd.dictsv.Adaptor;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashMap;

import nd.dictsv.DAO.Category;
import nd.dictsv.DAO.FavoriteDAO;
import nd.dictsv.DAO.Word;
import nd.dictsv.Debug.Message;
import nd.dictsv.Fragment.FavoriteFragment;
import nd.dictsv.R;

/**
 * Created by Since on 23/1/2558.
 */
public class CustomAdapter2 extends BaseAdapter{

    private static final String TAG = "CustomAdapter2";

    public static boolean chk = false;

    private LayoutInflater mInflater;
    private Context mContext;

    private FavoriteDAO favoriteDAO;

    private HashMap<Long,Word> wordsHashMap;
    private HashMap<Integer,Category> categories;
    private Long[] mWordKey;

    private Category category;
    private Word word;

    public CustomAdapter2(Context context, HashMap<Long,Word> words,
                          HashMap<Integer,Category> categories) {
        this.mContext = context;
        this.wordsHashMap = words;
        this.mWordKey = words.keySet().toArray(new Long[words.size()]);
        this.categories = categories;
        this.mInflater = (LayoutInflater)mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.favoriteDAO = new FavoriteDAO(context);


    }

    @Override
    //Show Count list item
    public int getCount() {
        return wordsHashMap.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder mViewHolder;
        if(convertView==null){
            mViewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.search_list_item, parent, false);

            //view matching
            mViewHolder = new ViewHolder();

            mViewHolder.word = (TextView) convertView.findViewById(R.id.item_word_name);
            mViewHolder.trans = (TextView) convertView.findViewById(R.id.item_word_trans);
            mViewHolder.category = (TextView) convertView.findViewById(R.id.item_word_category);

            mViewHolder.favImage = (ImageButton) convertView.findViewById(R.id.item_fav_img);
            mViewHolder.voiceImage = (ImageButton) convertView.findViewById(R.id.item_voice_img);

            convertView.setTag(mViewHolder);

        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        //update content in convertView
        ///Word
        //word = wordsHashMap.get(mWordKey[position]);
        word = wordsHashMap.get(mWordKey[position]);
        mViewHolder.word.setText(word.getmWord());
        Message.LogE("Customaadapter", word.getmWord()+"");//TODO D
        if(word.getmTermino()!=null) {
            mViewHolder.trans.setText(word.getmTermino());
        } else {
            mViewHolder.trans.setText(word.getmTrans());
        }

        ///Category
        category = new Category();
        category = word.getmCategory();
        Message.LogE("Customaadapter", category.getmId()+"");//TODO D
        String categoryName = categories.get(category.getmId()).getmName();
        mViewHolder.category.setText(categoryName);

        ///favorite
        mViewHolder.favImage.setTag(position);
        mViewHolder.favImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //favoriteDAO.addFavorite(word);
                //Long id = (Long)v.getTag();
                Message.LogE("favImage.setOnClickListener", String .valueOf(v.getTag()));
                favoriteDAO.addFavorite(wordsHashMap.get(mWordKey[Integer.valueOf(v.getTag().toString())]));
                chk = true;

            }
        });

        //LongClick to edit word
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Message.toast2(mContext, "LongClick ID : " + position);
                return true;
                //return false;
            }
        });


        return convertView;
    }

    private static class ViewHolder{
        public TextView word;
        public TextView trans;
        public TextView category;

        public ImageButton favImage;
        public ImageButton voiceImage;
    }
}
