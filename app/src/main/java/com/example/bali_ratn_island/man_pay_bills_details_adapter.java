package com.example.bali_ratn_island;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class man_pay_bills_details_adapter extends BaseAdapter {

    Context c;
    ArrayList<String > man_pay_arrayList;
    ArrayList<String > man_pay_arrayList2;

    public man_pay_bills_details_adapter(Context c, ArrayList<String> man_pay_arrayList, ArrayList<String> man_pay_arrayList2) {
        this.c = c;
        this.man_pay_arrayList = man_pay_arrayList;
        this.man_pay_arrayList2 = man_pay_arrayList2;
    }

    @Override
    public int getCount() {
        return man_pay_arrayList.size();
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

        customer_see_order_status_itemnm_X_qn.setText(man_pay_arrayList.get(i));
        customer_see_order_status_itempr_X_qn.setText(man_pay_arrayList2.get(i));
        return view;
    }
}
