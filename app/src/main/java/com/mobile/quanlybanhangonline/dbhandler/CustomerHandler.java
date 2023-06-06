package com.mobile.quanlybanhangonline.dbhandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mobile.quanlybanhangonline.data.DBManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerHandler extends SQLiteOpenHelper {

    DBManager dbManager;
    SQLiteDatabase db;
    private Context context;
    // Tên cơ sở dữ liệu
    private static final String DATABASE_NAME = "QUANLYDONHANG";
    // Phiên bản cơ sở dữ liệu
    private static final int DATABASE_VERSION = 1;

    // Tên bảng
    private static final String TABLE_CUSTOMER = "customers";
    private static final String TABLE_BILLS = "bills";


    // Các trường trong bảng Users
    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_USER_NAME = "name";
    private static final String COLUMN_USER_EMAIL = "email";
    private static final String COLUMN_USER_USERNAME = "username";
    private static final String COLUMN_USER_PASSWORD = "password";
    private static final String COLUMN_USER_ADDRESS = "address";
    private static final String COLUMN_USER_PHONE = "phone";

    // Các trường trong bảng Bills
    private static final String COLUMN_BILL_ID = "id";
    private static final String COLUMN_BILL_USER_ID = "user_id";
    private static final String COLUMN_BILL_TOTAL_PRICE = "total_price";
    private static final String COLUMN_BILL_DESCRIPTION = "description";
    private static final String COLUMN_BILL_DATE_CREATED = "date_created";

    // Constructor của DatabaseHelper
    public CustomerHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        dbManager = new DBManager(context);
        db = dbManager.getWritableDatabase();
    }



    // Customer
    public Cursor getAllCustomer() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM customers;", null);
        return cursor;
    }


    public void deleteAllCustomers() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CUSTOMER, null, null);
        db.close();
    }
    public void deleteAllBills() {
        SQLiteDatabase db = getWritableDatabase();
        int result = db.delete(TABLE_BILLS, null, null);
        db.close();
    }


    public Cursor getBillByCustomerID(int customerID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_BILLS + " WHERE " + COLUMN_BILL_USER_ID + " = ?";
        String[] selectionArgs = { String.valueOf(customerID) };
        Cursor cursor = db.rawQuery(query, selectionArgs);
        return cursor;
    }

    public String getUserNameById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT name FROM customers WHERE id = ?";
        String[] selectionArgs = {String.valueOf(id)};
        Cursor cursor = db.rawQuery(selectQuery, selectionArgs);
        String userName = null;
        if (cursor.moveToFirst()) {
            userName = cursor.getString(0);
        }
        cursor.close();
        return userName;
    }

    public boolean updatePassword(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", password);
        int rowsAffected = db.update("customers", values, "email = ?", new String[]{email});
        return rowsAffected > 0;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
