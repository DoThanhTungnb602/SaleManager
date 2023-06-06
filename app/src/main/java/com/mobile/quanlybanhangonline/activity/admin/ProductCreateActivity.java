package com.mobile.quanlybanhangonline.activity.admin;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.mobile.quanlybanhangonline.R;
import com.mobile.quanlybanhangonline.dbhandler.CategoryHandler;
import com.mobile.quanlybanhangonline.dbhandler.ProductHandler;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class ProductCreateActivity extends Activity {
    ProductHandler productHandler;
    CategoryHandler categoryHandler;
    Spinner spinnerCategory;
    ImageButton ibnCamera;
    ImageButton ibnFolder;
    ImageView imgHinh;
    int category_id;
    Button btnSave;
    EditText txtQuantity;
    EditText txtPrice;
    EditText txtProductName;
    int REQUEST_CODE_CAMERA = 123;
    int REQUEST_CODE_FOLDER = 456;

    public boolean validateProduct(String name, int price,int quantity, byte[] img) {
        if (name.isEmpty() || quantity == 0 || price == 0 || img == null)
            return false;
        else if (name.length() > 30 || quantity > 9999 || price < 0)
            return false;
        else
            return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_create);
        imgHinh = (ImageView) findViewById(R.id.imgHinh);
        btnSave = (Button) findViewById(R.id.btnSave);
        spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);
        ibnCamera = (ImageButton) findViewById(R.id.ibnCamera);
        ibnFolder = (ImageButton) findViewById(R.id.ibnFolder);
        txtPrice = (EditText) findViewById(R.id.txtPrice);
        txtProductName = (EditText) findViewById(R.id.txtProductName);
        txtQuantity = (EditText) findViewById(R.id.txtQuantity);
        productHandler = new ProductHandler(this);
        categoryHandler = new CategoryHandler(this);

        loadSpinner();

        btnSave.setOnClickListener(v -> {
            category_id = categoryHandler.getCategoryIdByName(spinnerCategory.getSelectedItem().toString().trim());
            String name = txtProductName.getText().toString().trim();
            int quantity = Integer.parseInt(txtQuantity.getText().toString().trim());
            int price = Integer.parseInt(txtPrice.getText().toString().trim());
            byte[] img = convertToArrayByte(imgHinh);
            if (name.isEmpty() || quantity == 0 || price == 0 || img == null) {
                Toast.makeText(ProductCreateActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            } else if (name.length() > 30 || quantity > 9999 || price < 0) {
                Toast.makeText(ProductCreateActivity.this, "Vui lòng nhập thông tin hợp lệ!", Toast.LENGTH_SHORT).show();
            } else {
                productHandler.createProduct(category_id, name, quantity, price, img);
                Toast.makeText(ProductCreateActivity.this, "Thêm sản phẩm thành công!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), ProductListActicity.class);
                startActivity(i);
                finish();
            }
        });

        ibnCamera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, REQUEST_CODE_CAMERA);

            }
        });
        ibnFolder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent in = new Intent(Intent.ACTION_PICK);
                in.setType("image/*");
                startActivityForResult(in, REQUEST_CODE_FOLDER);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK & data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgHinh.setImageBitmap(bitmap);

        }
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK & data != null) {
            Uri uri = data.getData();
            try {
                InputStream ipstream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(ipstream);
                imgHinh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void loadSpinner() {

        CategoryHandler categoryHandler = new CategoryHandler(this);
        List<String> category = categoryHandler.getAllNameCategoryForCreateProduct();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, category);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        spinnerCategory.setAdapter(dataAdapter);
    }

    public byte[] convertToArrayByte(ImageView img) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

}
