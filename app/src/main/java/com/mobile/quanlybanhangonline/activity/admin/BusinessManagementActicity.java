package com.mobile.quanlybanhangonline.activity.admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mobile.quanlybanhangonline.activity.MainActivity;
import com.mobile.quanlybanhangonline.R;

public class BusinessManagementActicity extends Activity {
    Button btnProduct;
    Button btnBill;
    Button btnStatistic;
    Button btnUserManagement;
    Button btnLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);
        btnProduct = (Button) findViewById(R.id.btnProduct);
        btnBill = (Button) findViewById(R.id.btnBill);
        btnStatistic = (Button) findViewById(R.id.btnStatistic);
        btnUserManagement = (Button) findViewById(R.id.btnUserManagement);
        btnLogout = (Button) findViewById(R.id.btnLogout);

        btnProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), ProductManagementActicity.class);
                startActivity(i);
            }
        });
        btnBill.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), BillStatisticActivity.class);
                startActivity(i);
            }
        });
        btnStatistic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), StatisticActivity.class);
                startActivity(i);
            }
        });
        btnUserManagement.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent b = new Intent(getApplicationContext(), CustomerListActivity.class);
                startActivity(b);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent b = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(b);
                finish();
            }
        });
    }
}
