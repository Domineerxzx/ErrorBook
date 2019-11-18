package com.domineer.triplebro.mistakebook.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.domineer.triplebro.mistakebook.R;
import com.domineer.triplebro.mistakebook.activities.ErrorDetailActivity;
import com.domineer.triplebro.mistakebook.activities.ErrorInfoActivity;
import com.domineer.triplebro.mistakebook.controllers.ErrorListController;
import com.domineer.triplebro.mistakebook.interfaces.OnItemClickListener;
import com.domineer.triplebro.mistakebook.models.ErrorInfo;
import com.domineer.triplebro.mistakebook.models.UserInfo;
import com.domineer.triplebro.mistakebook.providers.DataBaseProvider;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/11/18,4:13
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class ErrorAdapter extends BaseAdapter {
    private Context context;
    private List<ErrorInfo> errorInfoList;
    private PhotoWallAdapter photoWallAdapter;
    private List<String> errorImageList;
    private ErrorListController errorListController;
    private ImageView iv_close_image_large;
    private ImageView iv_image_large;
    private RelativeLayout rl_image_large;

    public ErrorAdapter(Context context, List<ErrorInfo> errorInfoList) {
        this.context = context;
        this.errorInfoList = errorInfoList;
    }

    public List<ErrorInfo> getErrorInfoList() {
        return errorInfoList;
    }

    public void setErrorInfoList(List<ErrorInfo> errorInfoList) {
        this.errorInfoList = errorInfoList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return errorInfoList.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_error,null);
            viewHolder.tv_error_title = convertView.findViewById(R.id.tv_error_title);
            viewHolder.tv_error_title_id = convertView.findViewById(R.id.tv_error_title_id);
            viewHolder.rv_error_list = convertView.findViewById(R.id.rv_error_list);
            viewHolder.tv_flag = convertView.findViewById(R.id.tv_flag);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_error_title.setText(errorInfoList.get(position).getErrorTitle());
        viewHolder.tv_error_title_id.setText(String.valueOf(errorInfoList.get(position).get_id()));
        viewHolder.rv_error_list.setLayoutManager(new GridLayoutManager(context,3){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        viewHolder.tv_flag.setText("排名："+(position+1));
        errorListController = new ErrorListController(context);
        errorImageList = errorListController.getErrorImageInfoList(errorInfoList.get(position).get_id());
        photoWallAdapter = new PhotoWallAdapter(context, errorImageList);
        viewHolder.rv_error_list.setAdapter(photoWallAdapter);
        photoWallAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(errorImageList.get(position).length() == 0){
                    return;
                }
                rl_image_large.setVisibility(View.VISIBLE);
                iv_image_large.setScaleType(ImageView.ScaleType.CENTER);
                Glide.with(context).load(errorImageList.get(position)).into(iv_image_large);
            }

            @Override
            public void onItemLongClick(View view) {

            }
        });
        iv_close_image_large.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl_image_large.setVisibility(View.GONE);
            }
        });
        /*isCollect = errorListController.getIsCollect(errorInfoList.get(position).get_id(),user_id);
        if(isCollect){
            viewHolder.iv_collect.setBackgroundResource(R.mipmap.collection_click);
        }else{
            viewHolder.iv_collect.setBackgroundResource(R.mipmap.collection);
        }
        viewHolder.iv_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user_id == -1){
                    Toast.makeText(context, "还没登录呢，快去登录吧！！！", Toast.LENGTH_SHORT).show();
                }
                if(isCollect){
                    viewHolder.iv_collect.setBackgroundResource(R.mipmap.collection);
                    errorListController.deleteCollect(errorInfoList.get(position).get_id(),user_id);
                    isCollect = false;
                }else{
                    viewHolder.iv_collect.setBackgroundResource(R.mipmap.collection_click);
                    errorListController.addCollect(errorInfoList.get(position).get_id(),user_id);
                    isCollect = true;
                }
            }
        });*/
        View.OnClickListener intent = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent errorInfo = new Intent(context, ErrorDetailActivity.class);
                errorInfo.putExtra("errorInfo",errorInfoList.get(position));
                context.startActivity(errorInfo);
            }
        };
        convertView.setOnClickListener(intent);
        viewHolder.tv_error_title.setOnClickListener(intent);
        viewHolder.tv_error_title_id.setOnClickListener(intent);
        viewHolder.rv_error_list.setOnClickListener(intent);
        return convertView;
    }

    public void setViewInfo(RelativeLayout rl_image_large, ImageView iv_image_large, ImageView iv_close_image_large) {
        this.rl_image_large = rl_image_large;
        this.iv_image_large = iv_image_large;
        this.iv_close_image_large = iv_close_image_large;
    }

    private class ViewHolder{
        private TextView tv_error_title;
        private TextView tv_error_title_id;
        private RecyclerView rv_error_list;
        private TextView tv_flag;
    }
}
