package com.mobile.quanlybanhangonline;

import com.mobile.quanlybanhangonline.activity.login.ForgotPasswordActivity;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class ForgotPasswordUnitTest {
    private ForgotPasswordActivity forgotPasswordActivity;

    @Before
    public void setUp() {
        forgotPasswordActivity = new ForgotPasswordActivity();
    }

    // Nếu email trống thì trả về false (không hợp lệ)
    @Test
    public void emptyEmail_isFalse() {
        String email = "";
        boolean result = forgotPasswordActivity.validateEmail(email);
        assertThat(result).isFalse();
    }

    // Nếu email không tồn tại trong database thì trả về false (không hợp lệ)
    @Test
    public void validEmailAndNotExistedEmail_isFalse() {
        String email = "Dothanhtungnb602@gmail.com";
        boolean result = forgotPasswordActivity.validateEmail(email);
        assertThat(result).isFalse();
    }

    // Nếu email tồn tại trong database thì trả về true (hợp lệ)
    @Test
    public void existedEmail_isTrue() {
        String email = "SomeExistedEmail@gmail.com";
        boolean result = forgotPasswordActivity.validateEmail(email);
        assertThat(result).isTrue();
    }

    // Nếu mã xác thực trống thì trả về false (không hợp lệ)
    @Test
    public void emptyCode_isFalse() {
        String code = "";
        boolean result = forgotPasswordActivity.validateOTP(code);
        assertThat(result).isFalse();
    }

    // Nếu mã xác thực không trùng với mã xác thực ban đầu thì trả về false (không hợp lệ)
    @Test
    public void invalidCode_isFalse() {
        String code = "000000";
        boolean result = forgotPasswordActivity.validateOTP(code);
        assertThat(result).isFalse();
    }

    // Nếu mã xác thực trùng với mã xác thực ban đầu thì trả về true (hợp lệ)
    @Test
    public void validCode_isTrue() {
        String code = "123456";
        boolean result = forgotPasswordActivity.validateOTP(code);
        assertThat(result).isTrue();
    }

    // Nếu mật khẩu trống thì trả về false (không hợp lệ)
    @Test
    public void emptyPasswordOrEmptyConfirmPassword_isFalse() {
        String password = "";
        String confirmPassword = "";
        boolean result = forgotPasswordActivity.validatePassword(password, confirmPassword);
        assertThat(result).isFalse();
    }

    // Nếu mật khẩu không trùng khớp thì trả về false (không hợp lệ)
    @Test
    public void passwordNotEqualConfirmPassword_isFalse() {
        String password = "Tung2001";
        String confirmPassword = "Tung2002";
        boolean result = forgotPasswordActivity.validatePassword(password, confirmPassword);
        assertThat(result).isFalse();
    }

    // Nếu mật khẩu trùng khớp thì trả về true (hợp lệ)
    @Test
    public void validAndPasswordAndConfirmPasswordEqual_isTrue() {
        String password = "Tung2001";
        String confirmPassword = "Tung2001";
        boolean result = forgotPasswordActivity.validatePassword(password, confirmPassword);
        assertThat(result).isTrue();
    }
}
