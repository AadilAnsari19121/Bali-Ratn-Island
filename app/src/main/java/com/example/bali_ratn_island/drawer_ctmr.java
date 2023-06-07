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

public class drawer_ctmr extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout_ctmr;

    @Override
    public void setContentView(View view) {
        drawerLayout_ctmr=(DrawerLayout) getLayoutInflater().inflate(R.layout.activity_drawer_ctmr,null);
        FrameLayout con_man=drawerLayout_ctmr.findViewById(R.id.frame_layout);
        con_man.addView(view);
        super.setContentView(drawerLayout_ctmr);

        Toolbar toolbar=drawerLayout_ctmr.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SearchView searchView=drawerLayout_ctmr.findViewById(R.id.search_tol);
        searchView.setVisibility(View.GONE);

        NavigationView navigationView_ctmr=drawerLayout_ctmr.findViewById(R.id.nav_view_ctmr);
        navigationView_ctmr.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout_ctmr,toolbar,R.string.open_draw,R.string.close_draw);
        drawerLayout_ctmr.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout_ctmr.closeDrawer(GravityCompat.START);

        if(item.getItemId()==R.id.drawer_customer_category)
        {
            startActivity(new Intent(this,cutomer_category.class));
            overridePendingTransition(0,0);
        }
        else if (item.getItemId()==R.id.drawer_customer_cart)
        {
            startActivity(new Intent(this,customer_see_cart.class));
            overridePendingTransition(0,0);
        }
        else if (item.getItemId()==R.id.drawer_customer_order_status)
        {
            startActivity(new Intent(this,cutomer_order_status.class));
            overridePendingTransition(0,0);
        }
        else if (item.getItemId()==R.id.drawer_customer_bill)
        {
            startActivity(new Intent(this,customer_bills.class));
            overridePendingTransition(0,0);
        }

        else if (item.getItemId()==R.id.drawer_customer_feedback)
        {
            startActivity(new Intent(this,customer_feedback.class));
            overridePendingTransition(0,0);
        }

        else if (item.getItemId()==R.id.drawer_customer_menu)
        {
            startActivity(new Intent(this,customer_see_menu_item.class));
            overridePendingTransition(0,0);
        }
        else
        {
            startActivity(new Intent(this,cutomer_category.class));
            overridePendingTransition(0,0);
        }
        return false;
    }

    protected void allactivityy2(String titleString)
    {
        if (getSupportActionBar() !=null)
        {
            getSupportActionBar().setTitle(titleString);
        }
    }
}