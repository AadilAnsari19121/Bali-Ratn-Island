package com.example.bali_ratn_island;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;


import com.google.android.material.navigation.NavigationView;

public class drawer_man extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout_man;


    @Override
    public void setContentView(View view) {
        drawerLayout_man=(DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer_man,null);
        FrameLayout con_man=drawerLayout_man.findViewById(R.id.frame_layout);
        con_man.addView(view);
        super.setContentView(drawerLayout_man);

        Toolbar toolbar=drawerLayout_man.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SearchView searchView=drawerLayout_man.findViewById(R.id.search_tol);
        searchView.setVisibility(View.GONE);

        NavigationView navigationView_man=drawerLayout_man.findViewById(R.id.nav_view_man);
        navigationView_man.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout_man,toolbar,R.string.open_draw,R.string.close_draw);
        drawerLayout_man.addDrawerListener(toggle);
        //drawerLayout_man.setGroupDividerEnabled(menu, true)
        toggle.syncState();



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout_man.closeDrawer(GravityCompat.START);

        if(item.getItemId()==R.id.drawer_man_table)
        {
            startActivity(new Intent(this,manager_table_book.class));
            overridePendingTransition(0,0);
        }
        else if (item.getItemId()==R.id.drawer_man_order_status)
        {
            startActivity(new Intent(this,manager_order_status.class));
            overridePendingTransition(0,0);
        }
        else if (item.getItemId()==R.id.drawer_man_bill)
        {
            startActivity(new Intent(this,manger_bills.class));
            overridePendingTransition(0,0);
        }
        else if (item.getItemId()==R.id.drawer_man_profile)
        {
            startActivity(new Intent(this,manager_profile.class));
            overridePendingTransition(0,0);
        }

        else
        {
            startActivity(new Intent(this,manager_table_book.class));
            overridePendingTransition(0,0);
        }
        return false;
    }

    protected void allactivityy(String titleString)
    {
        if (getSupportActionBar() !=null)
        {
            getSupportActionBar().setTitle(titleString);
        }
    }
}