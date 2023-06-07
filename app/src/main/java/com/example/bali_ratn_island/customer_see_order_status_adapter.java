package com.example.bali_ratn_island;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class customer_see_order_status_adapter extends BaseAdapter {
    Context c;
    ArrayList<String > arl1;
    ArrayList<String > arl2;

    public customer_see_order_status_adapter(Context c, ArrayList<String> arl1, ArrayList<String> arl2) {
        this.c = c;
        this.arl1 = arl1;
        this.arl2 = arl2;
    }

    @Override
    public int getCount() {
        return arl1.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.customer_see_order_status_des, viewGroup,false);

        TextView customer_see_order_status_itemnm_X_qn;
        TextView customer_see_order_status_itempr_X_qn;

        customer_see_order_status_itemnm_X_qn=view.findViewById(R.id.customer_see_order_status_itemnm_X_qn);
        customer_see_order_status_itempr_X_qn=view.findViewById(R.id.customer_see_order_status_itemnm_X_pr_tam);

        customer_see_order_status_itemnm_X_qn.setText(arl1.get(i));
        customer_see_order_status_itempr_X_qn.setText(arl2.get(i));
        return view;
    }
}
