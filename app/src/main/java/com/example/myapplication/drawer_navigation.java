package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class drawer_navigation extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle Toggle;
    private NavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_navigation);

        drawerLayout = findViewById(R.id.drawerlayout);

        nav= findViewById(R.id.nav);


        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.abt:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_lay,new AboutMe()).commit();
                        System.out.println("clicked");
                        break;
                    case R.id.nav_logout:
                        new AlertDialog.Builder(drawer_navigation.this)
                                .setTitle("Logout")
                                .setMessage("Are you sure you want to logout?")
                                .setPositiveButton("Yes", (dialog, which) -> {
                        Intent intent = new Intent(drawer_navigation.this, LogIn.class);
                        startActivity(intent);
                        finish();

                        invalidateOptionsMenu();})
                                .setNegativeButton("No", null)
                                .show();
                        return true;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        Toggle = new ActionBarDrawerToggle(this,
                drawerLayout,R.string.openmode,R.string.closemode);
        drawerLayout.addDrawerListener(Toggle);
        Toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    public boolean onOptionsItemSelected(MenuItem item){
        if(Toggle.onOptionsItemSelected(item)){
            System.out.println("clicked");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();

        }
    }



}