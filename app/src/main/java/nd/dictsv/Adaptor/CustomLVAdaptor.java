package nd.dictsv.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import nd.dictsv.R;

/**
 * Created by N_D on 12/23/2014.
 */

public class CustomLVAdaptor extends BaseAdapter {
    Context mContext;
    public LayoutInflater mInflater;
    ArrayList<String> arr_list_cate;
    ArrayList<String> arr_list_word;
    ArrayList<String> arr_list_termer;

    public CustomLVAdaptor(Context context,  ArrayList<String> arr_list_cate,
                           ArrayList<String> arr_list_word,  ArrayList<String> arr_list_termer) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.arr_list_cate = arr_list_cate;
        this.arr_list_word = arr_list_word;
        this.arr_list_termer = arr_list_termer;
    }

    @Override
    public int getCount() {
        return arr_list_termer.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if(convertView ==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.search_list_item, null);
            //convertView = mInflater.inflate(R.layout.item_content, null);

            viewHolder.cate = (TextView) convertView.findViewById(R.id.item_word_name);


            viewHolder.word = (TextView)convertView.findViewById(R.id.item_word_trans);


            viewHolder.termer = (TextView)convertView.findViewById(R.id.item_word_category);


            viewHolder.favImage = (ImageButton)convertView.findViewById(R.id.item_fav_img);
            viewHolder.soundImage = (ImageButton)convertView.findViewById(R.id.item_voice_img);

            convertView.setTag(viewHolder);
        }else{
            //rebind widget in convertView
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //update content in convertView
        viewHolder.word.setText(arr_list_word.get(position));
        viewHolder.termer.setText(arr_list_termer.get(position));
        //viewHolder.cate.setText(arr_list_cate.get(position));

        viewHolder.word.setText(String.valueOf(position + 1));
        viewHolder.favImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(!view.isSelected());
                if (view.isSelected()) {

                    viewHolder.favImage.setImageResource(R.drawable.ic_star_20dp);

                    Toast.makeText(mContext, "หมาเย็ดเป็ด เพิ่มรายการโปรดแล้ว" + view.isSelected(), Toast.LENGTH_SHORT).show();

                } else {

                    viewHolder.favImage.setImageResource(R.drawable.ic_star_outline_20dp);

                    Toast.makeText(mContext, "ลบออกจากรายการโปรดแล้ว" + view.isSelected(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewHolder.word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext, "คุณเลือก"+(viewHolder.word).getText(), Toast.LENGTH_SHORT).show();
            }
        });
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Toast.makeText(mContext,"Test",Toast.LENGTH_LONG).show();
                return true;
            }
        });

        return convertView;
    }
    final class ViewHolder {
        public TextView cate;
        public TextView word;
        public TextView termer;
        public ImageButton favImage;
        public ImageButton soundImage;

    }

}
