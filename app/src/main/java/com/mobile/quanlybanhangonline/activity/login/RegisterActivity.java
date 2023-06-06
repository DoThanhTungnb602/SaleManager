package com.mobile.quanlybanhangonline.activity.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobile.quanlybanhangonline.R;
import com.mobile.quanlybanhangonline.dbhandler.RegisterHandler;

public class RegisterActivity extends Activity {
    Button btnRegister;
    EditText etFullName;
    EditText etEmail;
    EditText etSdt;

    EditText etPassword;
    EditText etUsername;
    RegisterHandler registerHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerHandler = new RegisterHandler(this);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etFullName = (EditText) findViewById(R.id.etFullName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etSdt = (EditText) findViewById(R.id.etSdt);

        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String name = etFullName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String phone = etSdt.getText().toString().trim();

                if(validateSignup()){
                    if (registerHandler.register(name, email, username, password, phone)) {
                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),
                                "Đăng ký tài khoản thành công.",
                                Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Đăng ký tài khoản không thành công.",
                                Toast.LENGTH_LONG).show();
                    }
                }

            }
            public boolean validateSignup(){
                String name = etFullName.getText().toString();
                String email = etEmail.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String phone = etSdt.getText().toString();
                boolean flag = true;
                if(name.length() == 0){
                    etFullName.setError("Họ tên không được để trống!");
                    flag = false;
                }
                if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))
                {
                    etEmail.setError("Email không hợp lệ!");
                    flag = false;
                }
                if(username.length() == 0){
                    etUsername.setError("Tên tài khoản không được bỏ trống!");
                    flag =  false;
                }
                else{
                    if(!username.matches("[a-zA-Z]")){
                        etUsername.setError("Tên đăng nhập chỉ được phép chứa các ký tự a-z, A-Z");
                        flag = false;
                    }
                    if(username.length() < 3 || username.length() > 20){
                        etUsername.setError("Tên đăng nhập có chiều dài từ 6 đến 20 kí tự");
                        flag = false;
                    }
                }
                if(password.length() < 6){
                    if(password.length() == 0)
                        etPassword.setError("Mật khẩu không được để trống");
                    else
                        etPassword.setError("Mật khẩu yếu");
                    flag = false;
                }
                else if(password.length() > 20){
                    etPassword.setError("Mật khẩu không được quá 20 kí tự");
                    flag = false;
                }
                else {
                    String errorPassword = "";
                    if(!password.matches("(?=.*d)")){
                        errorPassword += "Mật khẩu phải chứa ít nhất 1 kí tự số";
                    }
                    if(!password.matches("(?=.*[A-Z])")){
                        errorPassword += "Mật khẩu phải chứa ít nhất 1 kí tự hoa";
                    }
                    if(!password.matches("[a-zA-Z0-9]")){
                        errorPassword += "Mật khẩu chỉ được phép chứa các ký tự a-z, A-Z, 0-9";
                    }
                    if(!errorPassword.equals("")){
                        etPassword.setError(errorPassword);
                        flag = false;
                    }
                }
                if(phone.length() == 0){
                    etSdt.setError("Số điện thoại không được để trống");
                    flag = false;
                }
                else if(phone.length() < 10){
                    etSdt.setError("Số điện thoại ít nhất 10 kí tự số");
                    flag = false;
                }
                else if(phone.length() > 11){
                    etSdt.setError("Số điện thoại tối đa 11 kí tự số");
                    flag = false;
                }
                else if(!password.matches("[0-9]")){
                    etSdt.setError("Số điện thoại chỉ được chứa các kí tự số 0-9");
                    flag = false;
                }
                return flag;
            }
        });
    }
}
