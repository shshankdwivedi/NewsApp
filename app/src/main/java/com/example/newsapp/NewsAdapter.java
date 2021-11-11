package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private ArrayList<Articles> list;
    private Context context;

    public NewsAdapter(ArrayList<Articles> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.news_rv_item,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, int position) {
        Articles articles=list.get(position);
        holder.subTitle.setText(articles.getDescription());
        holder.titleTV.setText(articles.getTitle());
        Picasso.get().load(articles.getUrlToImage()).into(holder.newsIV);

        holder.itemView.setOnClickListener(v->{
            Intent intent=new Intent(context,DetailNews.class);
            intent.putExtra("title",list.get(position).getTitle());
            intent.putExtra("desc",list.get(position).getDescription());
            intent.putExtra("content",list.get(position).getContent());
            intent.putExtra("url",list.get(position).getUrl());
            intent.putExtra("image",list.get(position).getUrlToImage());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder
    {
        private TextView titleTV,subTitle;
        private ImageView newsIV;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV=itemView.findViewById(R.id.idIVNewsHeading);
            subTitle=itemView.findViewById(R.id.idTVSubHeading);
            newsIV=itemView.findViewById(R.id.idIVNews);
        }

    }
}
