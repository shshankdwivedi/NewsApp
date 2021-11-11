package com.example.newsapp;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailNews extends AppCompatActivity {

    private ImageView newsIV;
    private TextView titleTV,descriptionTV,contentTV;
    private Button button;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(this.getResources().getColor(R.color.black_shade));
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_detail_news);

        newsIV=findViewById(R.id.idnewsIV);
        titleTV=findViewById(R.id.idTVtitle);
        descriptionTV=findViewById(R.id.idTVsubdesk);
        contentTV=findViewById(R.id.idTVcontent);
        button=findViewById(R.id.idbutton);

        String title=getIntent().getStringExtra("title");
        String desc=getIntent().getStringExtra("desc");
        String content=getIntent().getStringExtra("content");
        String url=getIntent().getStringExtra("url");
        String image=getIntent().getStringExtra("image");

        Picasso.get().load(image).into(newsIV);
        titleTV.setText(title);
        descriptionTV.setText(desc);
        contentTV.setText(content);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                finish();
            }
        });


    }
}