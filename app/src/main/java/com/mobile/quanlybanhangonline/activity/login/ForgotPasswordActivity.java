package com.mobile.quanlybanhangonline.activity.login;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobile.quanlybanhangonline.R;
import com.mobile.quanlybanhangonline.dbhandler.CustomerHandler;
import com.mobile.quanlybanhangonline.dbhandler.ForgotPasswordHandler;

import java.util.Random;

public class ForgotPasswordActivity extends Activity {
    Button forgetPassword;
    EditText emailField;
    ForgotPasswordHandler forgotPasswordHandler;
    private EditText otpCode;
    private EditText etNewPassword;
    private EditText etNewPasswordConfirm;
    private CustomerHandler customerHandler;

    private String originalCode = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        forgotPasswordHandler = new ForgotPasswordHandler(this);
        forgetPassword = (Button) findViewById(R.id.btnSendEmail);
        emailField = (EditText) findViewById(R.id.etEmail);

        forgetPassword.setOnClickListener(v -> {
            String email = emailField.getText().toString().trim();
            if (!forgotPasswordHandler.checkEmail(email))
                Toast.makeText(getApplicationContext(), "Tài khoản không tồn tại.", Toast.LENGTH_LONG).show();
            else {
                Random random = new Random();
                String code = String.valueOf(random.nextInt(900000) + 100000);
                forgotPasswordHandler.sendEmail(email, code);
                Toast.makeText(getApplicationContext(), "Đã gửi email xác nhận", Toast.LENGTH_LONG).show();
                String txtCode = otpCode.getText().toString().trim();
                if (txtCode.equals(code)) {
                    String password = etNewPassword.getText().toString().trim();
                    String passwordConfirm = etNewPasswordConfirm.getText().toString().trim();
                    if (!password.equals(passwordConfirm))
                        Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_LONG).show();
                    else if (customerHandler.updatePassword(email, password))
                        Toast.makeText(getApplicationContext(), "Cập nhật mật khẩu thành công", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplicationContext(), "Cập nhật mật khẩu thất bại", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(getApplicationContext(), "Mã xác nhận sai, vui lòng nhập lại", Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean isEmailExist(String email) {
        String[] emails = {"SomeExistedEmail@gmail.com", "AnotherExistedEmail@gmail.com"};
        for (String e : emails) {
            if (e.equals(email))
                return true;
        }
        return false;
    }
    // Hàm kiểm tra email có hợp lệ hay không
    public boolean validateEmail(String email) {
        if (email.isEmpty())
            return false;
        else if (this.isEmailExist(email))
            return true;
        else
            return false;
    }

    // Hàm kiểm tra mã OTP có hợp lệ hay không
    public boolean validateOTP(String code) {
        if (code.isEmpty())
            return false;
        else if (!code.equals(originalCode))
            return false;
        else
            return true;
    }

    // Hàm kiểm tra mật khẩu mới có hợp lệ hay không
    public boolean validatePassword(String password, String passwordConfirm) {
        if (password.isEmpty() || passwordConfirm.isEmpty())
            return false;
        else if (!password.equals(passwordConfirm))
            return false;
        else
            return true;
    }

}
