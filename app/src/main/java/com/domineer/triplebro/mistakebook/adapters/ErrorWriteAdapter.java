package com.domineer.triplebro.mistakebook.adapters;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.domineer.triplebro.mistakebook.R;
import com.domineer.triplebro.mistakebook.models.UserInfo;
import com.domineer.triplebro.mistakebook.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/11/18,19:23
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class ErrorWriteAdapter extends BaseAdapter {

    private Context context;
    private List<Integer> userIdList;
    private List<Integer> countList;

    public ErrorWriteAdapter(Context context, List<Integer> userIdList, List<Integer> countList) {
        this.context = context;
        this.userIdList = userIdList;
        this.countList = countList;
    }

    @Override
    public int getCount() {
        return userIdList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_error_write, null);
            viewHolder.tv_flag = convertView.findViewById(R.id.tv_flag);
            viewHolder.iv_user_head = convertView.findViewById(R.id.iv_user_head);
            viewHolder.tv_nickname = convertView.findViewById(R.id.tv_nickname);
            viewHolder.tv_count = convertView.findViewById(R.id.tv_count);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        int flag = position + 1;
        viewHolder.tv_flag.setText("TOP"+ flag);
        if(position == 0  ){
            viewHolder.tv_flag.setTextColor(0xFFFFD700);
        }else if(position == 1){
            viewHolder.tv_flag.setTextColor(0xFFC0C0C0);
        }else if(position == 2){
            viewHolder.tv_flag.setTextColor(0xFFA78E44);
        }else{
            viewHolder.tv_flag.setTextColor(Color.BLACK);
        }
        DataBaseProvider dataBaseProvider = new DataBaseProvider(context);
        UserInfo userInfo = dataBaseProvider.getUserInfo(userIdList.get(position));
        viewHolder.tv_nickname.setText(userInfo.getNickName());
        String userHead = userInfo.getUserHead();
        if (TextUtils.isEmpty(userHead)) {
            Glide.with(context).load(R.drawable.user_head_default).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(viewHolder.iv_user_head);
        } else {
            Glide.with(context).load(userHead).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(viewHolder.iv_user_head);
        }
        viewHolder.tv_count.setText(String.valueOf(countList.get(position)));
        return convertView;
    }

    private class ViewHolder{
        private TextView tv_flag;
        private ImageView iv_user_head;
        private TextView tv_nickname;
        private TextView tv_count;
    }
}
