package nd.dictsv.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashMap;

import nd.dictsv.DAO.Category;
import nd.dictsv.DAO.Favorite;
import nd.dictsv.DAO.FavoriteDAO;
import nd.dictsv.DAO.Word;
import nd.dictsv.Debug.Message;
import nd.dictsv.R;

/**
 * Created by Since on 23/1/2558.
 */
public class CustomAdapter2 extends BaseAdapter{

    private static final String TAG = "CustomAdapter2";

    public static boolean chkFavoriteList = false;

    private LayoutInflater mInflater;
    private Context mContext;
    ViewHolder mViewHolder;
    private FavoriteDAO favoriteDAO;

    private HashMap<Long,Word> wordsHashMap;
    private HashMap<Integer,Category> categories;
    private HashMap<Long,Favorite> favorites;
    private Long[] mWordKey;
    private Long[] mFavoritekey;

    private Category category;
    private Word word;
    private Favorite favoriteWord;

    public CustomAdapter2(Context context, HashMap<Long,Word> words,
                          HashMap<Integer,Category> categories) {
        this.mContext = context;
        this.wordsHashMap = words;
        this.mWordKey = words.keySet().toArray(new Long[words.size()]);
        this.categories = categories;
        this.mInflater = (LayoutInflater)mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.favoriteDAO = new FavoriteDAO(context);
        this.favorites = favoriteDAO.getAllFavorite();
        this.mFavoritekey = favorites.keySet().toArray(new Long[favorites.size()]);

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

        //ViewHolder mViewHolder;
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
        if (favorites.get(word.getmId())!=null) {
            mViewHolder.favImage.setImageResource(R.drawable.ic_star_20dp);
        } else {
            mViewHolder.favImage.setImageResource(R.drawable.ic_star_outline_20dp);
        }
        mViewHolder.favImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                if (view.isSelected()) {
                    //favoriteDAO.addFavorite(word);
                    //Long id = (Long)view.getTag();
                    Message.LogE("favImage.setOnClickListener", String.valueOf(view.getTag())); //TODO D
                    //if(favorites.get(position))
                    favoriteDAO.addFavorite(wordsHashMap.get(
                            mWordKey[Integer.valueOf(view.getTag().toString())]));
                    //mViewHolder.favImage.setImageResource(R.drawable.ic_star_20dp);
                    chkFavoriteList = true;
                } else {

                }
            }
        });

        //LongClick to edit word
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Message.toast2(mContext, "LongClick ID : " + position);  //TODO D
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
