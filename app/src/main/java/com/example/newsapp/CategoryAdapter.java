package com.example.newsapp;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context context;
    private ArrayList<CategoryModel> list;
    private CategoryClickInterface categoryClickInterface;

    public CategoryAdapter(Context context, ArrayList<CategoryModel> list, CategoryClickInterface categoryClickInterface) {
        this.context = context;
        this.list = list;
        this.categoryClickInterface = categoryClickInterface;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.category_rv_item,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        Picasso.get().load(list.get(position).getCategoyImages()).into(holder.categoryIV);
        holder.categoryTV.setText(list.get(position).getCategory());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryClickInterface.onCategoryClick(position);
            }
        });
    }

    public interface CategoryClickInterface
    {
        void onCategoryClick(int position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder
    {
        private TextView categoryTV;
        private ImageView categoryIV;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTV=itemView.findViewById(R.id.idTVCategories);
            categoryIV=itemView.findViewById(R.id.idIVCategory);
        }
    }
}
