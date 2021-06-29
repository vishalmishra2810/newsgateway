package com.example.newsapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SideBarActivity extends AppCompatActivity implements View.OnClickListener  {
    private Button button;
    private Button button2;
    private Button button3;
    private Button button5;
    private Button button14;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_bar);

        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button5 = (Button) findViewById(R.id.button5);
        button14 = (Button) findViewById(R.id.button14);


        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button5.setOnClickListener(this);
        button14.setOnClickListener(this);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_India, R.id.nav_Videos)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.side_bar, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                openActivity2();
                Toast.makeText(this, "Sports", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                openActivity3();
                Toast.makeText(this, "Politics", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button3:
                openActivity4();
                Toast.makeText(this, "Entertainment", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button5:
                openActivity5();
                Toast.makeText(this, "CoronaVirus", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button14:
                openActivity6();
                Toast.makeText(this, "News Feed", Toast.LENGTH_SHORT).show();
                break;
    }
}
    public void openActivity2() {
        Intent intent_sports = new Intent(this, Sports.class);
        startActivity(intent_sports);
    }
    public void openActivity3() {
        Intent intent_politics = new Intent(this, Politics.class);
        startActivity(intent_politics);
    }

    public void openActivity4() {
        Intent intent_entertainment = new Intent(this, Entertainment.class);
        startActivity(intent_entertainment);
    }
    public void openActivity5() {
        Intent intent_coronaVirus = new Intent(this, Coronavirus.class);
        startActivity(intent_coronaVirus);
    }
    public void openActivity6() {
        Intent intent_newsFeed = new Intent(this, NewsFeed.class);
        startActivity(intent_newsFeed);
    }
}