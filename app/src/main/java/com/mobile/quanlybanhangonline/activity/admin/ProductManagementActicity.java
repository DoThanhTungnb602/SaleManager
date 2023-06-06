package com.mobile.quanlybanhangonline.activity.admin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.mobile.quanlybanhangonline.R;

public class ProductManagementActicity extends Activity {
    Button btnCategory;
    Button btnProduct;
    Button btnQuantity;
    ImageButton btnBackToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_management);
        btnCategory = (Button) findViewById(R.id.btnCategory_PM);
        btnProduct = (Button) findViewById(R.id.btnProduct_PM);
        btnBackToMenu = (ImageButton) findViewById(R.id.btnBackToMenu);
        btnBackToMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), BusinessManagementActicity.class);
                startActivity(i);
                finish();
            }
        });
        btnCategory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(i);
            }
        });
        btnProduct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), ProductListActicity.class);
                startActivity(i);
            }
        });
    }
}
