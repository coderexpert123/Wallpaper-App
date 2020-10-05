package com.example.wallpaerapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class WallpaperAdapter extends RecyclerView.Adapter<WallPaperViewholder> {
    private Context context;
    private List<WallpaperModel> wallpaperModelList;

    public WallpaperAdapter(Context context, List<WallpaperModel> wallpaperModels) {
        this.context = context;
        this.wallpaperModelList = wallpaperModels;
    }

    @NonNull
    @Override
    public WallPaperViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.imae_item,parent,false);

        return new WallPaperViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WallPaperViewholder holder, final int position) {


        Glide.with(context).load(wallpaperModelList.get(position).getMediumUrl()).into(holder.imageView);


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,FullScree.class)
                .putExtra("originalurl",wallpaperModelList.get(position).getOrigialurl()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return wallpaperModelList.size();
    }
}
class WallPaperViewholder extends RecyclerView.ViewHolder{
    ImageView imageView;

    public WallPaperViewholder(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.imageItemView);

    }
}
