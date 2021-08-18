package com.example.newsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsFeed extends AppCompatActivity implements CategoryRVAdapter.CategoryClickInterface{

    //6a8614d4b9a640f9a4fcf963a2035633

    private RecyclerView newsRV,categoryRV;
    private ProgressBar loadingPB;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<CategoryRVModal> categoryRVModalArrayList;
    private CategoryRVAdapter categoryRVAdapter;
    private NewsRVAdapter newsRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_news_feed);
        newsRV = findViewById (R.id.idRVNews);
        categoryRV = findViewById (R.id.idRVCategories);
        loadingPB = findViewById (R.id.idPBLoading);
        articlesArrayList = new ArrayList<> ();
        categoryRVModalArrayList = new ArrayList<> ();
        newsRVAdapter = new NewsRVAdapter (articlesArrayList,this);
        categoryRVAdapter = new CategoryRVAdapter (categoryRVModalArrayList, this, this::onCategoryClick);
        newsRV.setLayoutManager (new LinearLayoutManager (this));
        newsRV.setAdapter (newsRVAdapter);
        categoryRV.setAdapter (categoryRVAdapter);
        getCategories ();
        getNews ("All");
        newsRVAdapter.notifyDataSetChanged ();

    }

    private void getCategories(){
        categoryRVModalArrayList.add (new CategoryRVModal ("All", "https://images.unsplash.com/photo-1588681664899-f142ff2dc9b1?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8bmV3c3xlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60"));
        categoryRVModalArrayList.add (new CategoryRVModal ("Technology", "https://images.unsplash.com/photo-1488590528505-98d2b5aba04b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80"));
        categoryRVModalArrayList.add (new CategoryRVModal ("Science", "https://images.unsplash.com/photo-1564325724739-bae0bd08762c?ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8c2NpZW5jZXxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60"));
        categoryRVModalArrayList.add (new CategoryRVModal ("Business", "https://images.unsplash.com/39/lIZrwvbeRuuzqOoWJUEn_Photoaday_CSD%20(1%20of%201)-5.jpg?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8YnVzaW5lc3N8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60"));
        categoryRVModalArrayList.add (new CategoryRVModal ("Health", "https://images.unsplash.com/photo-1498837167922-ddd27525d352?ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8aGVhbHRofGVufDB8fDB8fA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60"));
        categoryRVModalArrayList.add (new CategoryRVModal ("Sports", "https://images.unsplash.com/photo-1484482340112-e1e2682b4856?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTN8fHNwb3J0c3xlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60"));
        categoryRVModalArrayList.add (new CategoryRVModal ("General", "https://images.unsplash.com/photo-1586339949216-35c2747cc36d?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTd8fG5ld3N8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60"));
        categoryRVModalArrayList.add (new CategoryRVModal ("Entertainment", "https://images.unsplash.com/photo-1499364615650-ec38552f4f34?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8ZW50ZXJ0YWlubWVudHxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=60"));

        categoryRVAdapter.notifyDataSetChanged ();

    }

    private void getNews(String category){
        loadingPB.setVisibility (View.VISIBLE);
        articlesArrayList.clear ();
        String categoryURL = "https://newsapi.org/v2/top-headlines?country=in&category=" + category + "&apiKey=6a8614d4b9a640f9a4fcf963a2035633&language=en";
        String url = "https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apiKey=6a8614d4b9a640f9a4fcf963a2035633";
        String BASE_URL = "https://newsapi.org/";
        Retrofit retrofit = new Retrofit.Builder ()
                .baseUrl (BASE_URL)
                .addConverterFactory (GsonConverterFactory.create ())
                .build ();
        RetrofitAPI retrofitAPI = retrofit.create (RetrofitAPI.class);
        Call<NewsModal> call;
        if(category.equals ("All")){
            call = retrofitAPI.getAllNews (url);
        }else{
            call = retrofitAPI.getNewsByCategory(categoryURL);
        }

        call.enqueue (new Callback<NewsModal> () {
            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
                NewsModal newsModal = response.body ();
                loadingPB.setVisibility (View.GONE);
                ArrayList<Articles> articles = newsModal.getArticles ();
                for(int i = 0; i< articles.size (); i++){
                    articlesArrayList.add(new Articles (articles.get(i).getTitle (),articles.get (i).getDescription (), articles.get (i).getUrlToImage (),
                            articles.get (i).getUrl (),articles.get (i).getContent ()));

                }

                newsRVAdapter.notifyDataSetChanged ();
            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {
                Toast.makeText (NewsFeed.this,"Fail to get news", Toast.LENGTH_SHORT).show ();
            }
        });
    }
    @Override
    public void onCategoryClick(int position) {
        String category = categoryRVModalArrayList.get (position).getCategory ();
        getNews (category);
    }
}