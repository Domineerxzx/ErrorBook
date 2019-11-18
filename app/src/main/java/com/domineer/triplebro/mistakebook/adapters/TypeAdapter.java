package com.domineer.triplebro.mistakebook.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.domineer.triplebro.mistakebook.R;
import com.domineer.triplebro.mistakebook.interfaces.OnItemClickListener;
import com.domineer.triplebro.mistakebook.models.TypeInfo;

import java.util.List;

/**
 * @author Domineer
 * @data 2019/11/2,13:44
 * ----------为梦想启航---------
 * --Set Sell For Your Dream--
 */
public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.ViewHolder>{

    private Context context;
    private List<TypeInfo> typeInfoList;
    private OnItemClickListener onItemClickListener;

    public TypeAdapter(Context context, List<TypeInfo> typeInfoList) {
        this.context = context;
        this.typeInfoList = typeInfoList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public TypeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_type, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(inflate, onItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Glide.with(context).load(typeInfoList.get(i).getTypeImage()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(viewHolder.iv_type);
        viewHolder.tv_type.setText(typeInfoList.get(i).getTypeName());
        String typeImage = typeInfoList.get(i).getTypeImage();
        Glide.with(context).load(R.drawable.image_default).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(viewHolder.iv_type);
        if (typeImage != null && typeImage.length() != 0) {
            Glide.with(context).load(typeImage).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(viewHolder.iv_type);
        }
    }

    @Override
    public int getItemCount() {
        return typeInfoList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView iv_type;
        private TextView tv_type;
        private OnItemClickListener onItemClickListener;

        public ViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            initView(itemView);
        }

        private void initView(View itemView) {
            iv_type = itemView.findViewById(R.id.iv_type);
            tv_type = itemView.findViewById(R.id.tv_type);
            iv_type.setScaleType(ImageView.ScaleType.CENTER_CROP);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, getPosition());
        }
    }
}
