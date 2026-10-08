package com.utc.testing.e2e.tests;

import com.utc.testing.e2e.base.BaseTest;
import com.utc.testing.e2e.pages.LoginPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Bộ kiểm thử E2E Đăng nhập UTC theo mô hình Page Object Model (POM)
 * Tài liệu Buổi 8 - Kiểm thử Web UI Tự động: Selenium & POM
 */
@Epic("Kiểm thử Tự động Hóa Web UI (Buổi 8)")
@Feature("Chức năng Đăng nhập Văn phòng điện tử UTC")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginE2ETest extends BaseTest {

    @Test
    @Order(1)
    @Story("TC01 - Validation")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("TC01: Để trống cả Tên đăng nhập và Mật khẩu")
    @Description("Kiểm tra hệ thống hiển thị cảnh báo validation khi người dùng không nhập username và password")
    public void testTC01_EmptyBoth() {
        LoginPage loginPage = new LoginPage(getDriver()).open();
        loginPage.clickLogin();
        assertThat(loginPage.hasErrorText("Bạn chưa nhập tên đăng nhập"))
                .as("Hệ thống phải hiển thị thông báo chưa nhập tên đăng nhập")
                .isTrue();
    }
}
