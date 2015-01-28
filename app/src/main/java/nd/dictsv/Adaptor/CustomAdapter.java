package nd.dictsv.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import nd.dictsv.DAO.Category;
import nd.dictsv.DAO.Word;
import nd.dictsv.Debug.Message;
import nd.dictsv.R;

/**
 * Created by Since on 23/1/2558.
 */
public class CustomAdapter extends BaseAdapter{

    private static final String TAG = "CustomAdapter";

    private LayoutInflater mInflater;
    private Context mContext;

    private List<Word> words;
    private List<Category> categories;

    public CustomAdapter(Context context, List<Word> words, List<Category> categories) {
        Message.longToast(context, TAG, "CustomAdapter Constructor");

        this.mContext = context;
        this.words = words;
        this.categories = categories;
        this.mInflater = (LayoutInflater)mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    //Show Count list item
    public int getCount() {
        return words.size();
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
        Word word = words.get(position);
        mViewHolder.word.setText(word.getmWord());
        if(word.getmTermino()!=null) {
            mViewHolder.trans.setText(word.getmTermino());
        } else {
            mViewHolder.trans.setText(word.getmTrans());
        }
        ///Category
        Category category = word.getmCategory();
        String categoryName = categories.get(category.getmId()).getmName();
        mViewHolder.category.setText(categoryName);
        ///favorite
        mViewHolder.favImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
