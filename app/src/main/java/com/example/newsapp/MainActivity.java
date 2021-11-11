package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryAdapter.CategoryClickInterface {

    private RecyclerView newsRV,categoryRV;
    private ProgressBar progressBar;
    private NewsAdapter newsAdapter;
    private CategoryAdapter categoryAdapter;
    private ArrayList<CategoryModel> categoryArraylist;
    private RecyclerView.LayoutManager newsManager,manager;
    private ArrayList<Articles> articlesArrayList;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(this.getResources().getColor(R.color.black_shade));
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        newsRV=findViewById(R.id.idRVNews);
        newsManager=new LinearLayoutManager(this);
        newsRV.setLayoutManager(newsManager);

        progressBar=findViewById(R.id.idProgressBar);
        articlesArrayList=new ArrayList<>();

        categoryRV=findViewById(R.id.idRVCategories);
        manager=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        categoryRV.setLayoutManager(manager);
        categoryRV.setHasFixedSize(true);

        categoryArraylist=new ArrayList<>();

        categoryArraylist.add(new CategoryModel("All","https://images.unsplash.com/photo-1485115905815-74a5c9fda2f5?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjV8fG5ld3MlMjBpY29ufGVufDB8fDB8fA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        categoryArraylist.add(new CategoryModel("business","https://images.unsplash.com/photo-1460925895917-afdab827c52f?ixid=MnwxMjA3fDB8MHxzZWFyY2h8OXx8YnVzaW5lc3N8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        categoryArraylist.add(new CategoryModel("entertainment","https://images.unsplash.com/photo-1496337589254-7e19d01cec44?ixid=MnwxMjA3fDB8MHxzZWFyY2h8OXx8ZW50ZXJ0YWlubWVudHxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        categoryArraylist.add(new CategoryModel("general","https://images.unsplash.com/photo-1512314889357-e157c22f938d?ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8Z2VuZXJhbHxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        categoryArraylist.add(new CategoryModel("health","https://images.unsplash.com/photo-1505751172876-fa1923c5c528?ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8aGVhbHRofGVufDB8fDB8fA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        categoryArraylist.add(new CategoryModel("science","https://images.unsplash.com/photo-1581093588401-fbb62a02f120?ixid=MnwxMjA3fDB8MHxzZWFyY2h8N3x8c2NpZW5jZXxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        categoryArraylist.add(new CategoryModel("sports","https://images.unsplash.com/photo-1547347298-4074fc3086f0?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTF8fHNwb3J0c3xlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        categoryArraylist.add(new CategoryModel("technology","https://images.unsplash.com/photo-1486312338219-ce68d2c6f44d?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fHRlY2hub2xvZ3l8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));

        categoryAdapter=new CategoryAdapter(this,categoryArraylist, this);
        categoryRV.setAdapter(categoryAdapter);


        getNews("All");
        //newsAdapter.notifyDataSetChanged();
    }

    public void getNews(String category)
    {
        progressBar.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        String categoryURL = " https://newsapi.org/v2/top-headlines?country=in&category="+category+"&apiKey=94ef3dd967ea4faaae6d38328bf226c1";
        String url = " https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apiKey=94ef3dd967ea4faaae6d38328bf226c1";
        String BASE_URL = "https://newsapi.org/";

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitApi retrofitApi=retrofit.create(RetrofitApi.class);

        Call<NewsModel> call;

        if(category.equals("All"))
        {
            call=retrofitApi.getNewsModel(url);
        }
        else {
            call=retrofitApi.getCategoryModel(categoryURL);
        }

        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                progressBar.setVisibility(View.GONE);
                NewsModel newsModel=response.body();
                ArrayList<Articles> articles=newsModel.getArticles();
                for(int i=0;i<articles.size();i++)
                {
                    newsAdapter=new NewsAdapter(articles,MainActivity.this);
                    newsRV.setAdapter(newsAdapter);
                }
                newsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }



    public void onCategoryClick(int position)
    {
        String category=categoryArraylist.get(position).getCategory();
        getNews(category);
    }
}