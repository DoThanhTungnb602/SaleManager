package com.mobile.quanlybanhangonline;

import static com.google.common.truth.Truth.assertThat;

import com.mobile.quanlybanhangonline.activity.admin.ProductCreateActivity;
import com.mobile.quanlybanhangonline.activity.login.ForgotPasswordActivity;

import org.junit.Before;
import org.junit.Test;

public class CreateProductUnitTest {
    private ProductCreateActivity productCreateActivity;
    private byte[] imageForTest = new byte[8];

    @Before
    public void setUp() {
        productCreateActivity = new ProductCreateActivity();
    }

    // Nếu tên sản phẩm trống thì trả về false (không hợp lệ)
    @Test
    public void emptyProductName_isFalse() {
        String productName = ""; // Tên sản phẩm trống!
        int productPrice = 100000;
        int productQuantity = 10;
        byte[] productImage = null;
        boolean result = productCreateActivity.validateProduct(productName, productPrice, productQuantity, productImage);
        assertThat(result).isFalse();
    }

    // Nếu giá sản phẩm trống thì trả về false (không hợp lệ)
    @Test
    public void emptyProductPrice_isFalse() {
        String productName = "SomeProductName";
        int productPrice = 0; // Giá sản phẩm trống!
        int productQuantity = 10;
        byte[] productImage = null;
        boolean result = productCreateActivity.validateProduct(productName, productPrice, productQuantity, productImage);
        assertThat(result).isFalse();
    }

    // Nếu số lượng sản phẩm trống thì trả về false (không hợp lệ)
    @Test
    public void emptyProductQuantity_isFalse() {
        String productName = "SomeProductName";
        int productPrice = 100000;
        int productQuantity = 0; // Số lượng sản phẩm trống!
        byte[] productImage = null;
        boolean result = productCreateActivity.validateProduct(productName, productPrice, productQuantity, productImage);
        assertThat(result).isFalse();
    }

    // Nếu hình ảnh sản phẩm trống thì trả về false (không hợp lệ)
    @Test
    public void emptyProductImage_isFalse() {
        String productName = "SomeProductName";
        int productPrice = 100000;
        int productQuantity = 10;
        byte[] productImage = null; // Hình ảnh sản phẩm trống!
        boolean result = productCreateActivity.validateProduct(productName, productPrice, productQuantity, productImage);
        assertThat(result).isFalse();
    }

    // Nếu tên sản phẩm dài hơn 30 ký tự thì trả về false (không hợp lệ)
    @Test
    public void productNameLongerThan30_isFalse() {
        String productName = "SomeProductNameSomeProductNameSomeProductName"; // Tên sản phẩm dài hơn 30 ký tự!
        int productPrice = 100000;
        int productQuantity = 10;
        byte[] productImage = null;
        boolean result = productCreateActivity.validateProduct(productName, productPrice, productQuantity, productImage);
        assertThat(result).isFalse();
    }

    // Nếu giá sản phẩm nhỏ hơn 0 thì trả về false (không hợp lệ)
    @Test
    public void productPriceLessThan0_isFalse() {
        String productName = "SomeProductName";
        int productPrice = -1; // Giá sản phẩm nhỏ hơn 0!
        int productQuantity = 10;
        byte[] productImage = null;
        boolean result = productCreateActivity.validateProduct(productName, productPrice, productQuantity, productImage);
        assertThat(result).isFalse();
    }

    // Nếu số lượng sản phẩm nhỏ hơn 0 thì trả về false (không hợp lệ)
    @Test
    public void productQuantityLessThan0_isFalse() {
        String productName = "SomeProductName";
        int productPrice = 100000;
        int productQuantity = -1; // Số lượng sản phẩm nhỏ hơn 0!
        byte[] productImage = null;
        boolean result = productCreateActivity.validateProduct(productName, productPrice, productQuantity, productImage);
        assertThat(result).isFalse();
    }

    // Nếu số lượng sản phẩm lớn hơn 9999 thì trả về false (không hợp lệ)
    @Test
    public void productQuantityGreaterThan9999_isFalse() {
        String productName = "SomeProductName";
        int productPrice = 100000;
        int productQuantity = 10000; // Số lượng sản phẩm lớn hơn 9999!
        byte[] productImage = null;
        boolean result = productCreateActivity.validateProduct(productName, productPrice, productQuantity, productImage);
        assertThat(result).isFalse();
    }

    // Nếu tên sản phẩm, giá sản phẩm, số lượng sản phẩm, hình ảnh sản phẩm không trống thì trả về true (hợp lệ)
    @Test
    public void validProduct_isTrue() {
        String productName = "SomeProductName";
        int productPrice = 100000;
        int productQuantity = 10;
        byte[] productImage = imageForTest;
        boolean result = productCreateActivity.validateProduct(productName, productPrice, productQuantity, productImage);
        assertThat(result).isTrue();
    }
}
