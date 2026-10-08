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

    @Test
    @Order(2)
    @Story("TC02 - Validation")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("TC02: Để trống Tên đăng nhập, chỉ nhập Mật khẩu")
    @Description("Kiểm tra hệ thống yêu cầu nhập tên đăng nhập khi chỉ điền mật khẩu")
    public void testTC02_EmptyUsername() {
        LoginPage loginPage = new LoginPage(getDriver()).open();
        loginPage.enterPassword("password123");
        loginPage.clickLogin();
        assertThat(loginPage.hasErrorText("Bạn chưa nhập tên đăng nhập"))
                .as("Hệ thống phải hiển thị thông báo chưa nhập tên đăng nhập")
                .isTrue();
    }

    @Test
    @Order(3)
    @Story("TC03 - Validation")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("TC03: Nhập Tên đăng nhập, để trống Mật khẩu")
    @Description("Kiểm tra hệ thống yêu cầu nhập mật khẩu khi chỉ điền tên đăng nhập")
    public void testTC03_EmptyPassword() {
        LoginPage loginPage = new LoginPage(getDriver()).open();
        loginPage.enterUsername("huongnt");
        loginPage.clickLogin();
        assertThat(loginPage.hasErrorText("Bạn chưa nhập mật khẩu"))
                .as("Hệ thống phải hiển thị thông báo chưa nhập mật khẩu")
                .isTrue();
    }

    @Test
    @Order(4)
    @Story("TC04 - Security & Authentication")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("TC04: Đúng Tên đăng nhập, sai Mật khẩu")
    @Description("Kiểm tra xử lý bảo mật khi người dùng nhập đúng username nhưng sai password")
    public void testTC04_CorrectUser_WrongPassword() {
        LoginPage loginPage = new LoginPage(getDriver()).open();
        loginPage.loginAs("huongnt", "wrong_password_123");
        assertThat(loginPage.hasErrorText("Tài khoản hoặc mật khẩu không đúng") || loginPage.hasErrorText("Tài khoản không đúng"))
                .as("Hệ thống phải báo lỗi tài khoản hoặc mật khẩu không đúng")
                .isTrue();
    }

    @Test
    @Order(5)
    @Story("TC05 - Security & Authentication")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("TC05: Sai Tên đăng nhập, nhập đúng định dạng Mật khẩu")
    @Description("Kiểm tra hệ thống từ chối đăng nhập khi tài khoản không tồn tại trên hệ thống")
    public void testTC05_WrongUser_CorrectPassword() {
        LoginPage loginPage = new LoginPage(getDriver()).open();
        loginPage.loginAs("user_invalid_not_exist", "matkhau123");
        assertThat(loginPage.hasErrorText("Tài khoản hoặc mật khẩu không đúng") || loginPage.hasErrorText("Tài khoản không đúng"))
                .as("Hệ thống phải báo lỗi tài khoản không đúng")
                .isTrue();
    }

    @Test
    @Order(6)
    @Story("TC06 - Security & Authentication")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("TC06: Sai cả Tên đăng nhập và Mật khẩu")
    @Description("Kiểm tra hệ thống từ chối đăng nhập khi cả tên đăng nhập và mật khẩu đều không đúng")
    public void testTC06_WrongBoth() {
        LoginPage loginPage = new LoginPage(getDriver()).open();
        loginPage.loginAs("fake_user_9999", "fake_pass_9999");
        assertThat(loginPage.hasErrorText("Tài khoản hoặc mật khẩu không đúng") || loginPage.hasErrorText("Tài khoản không đúng"))
                .as("Hệ thống phải báo lỗi tài khoản hoặc mật khẩu không đúng")
                .isTrue();
    }

    @Test
    @Order(7)
    @Story("TC07 - Session Persistence")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("TC07: Đăng nhập với tuỳ chọn Giữ tôi luôn đăng nhập")
    @Description("Kiểm tra chức năng lưu phiên làm việc với checkbox persistent (Slide 38)")
    public void testTC07_LoginWithPersistent() {
        LoginPage loginPage = new LoginPage(getDriver()).open();
        loginPage.enterUsername("huongnt");
        loginPage.enterPassword("password123");
        loginPage.setPersistent(true);
        assertThat(loginPage.isPersistentChecked())
                .as("Checkbox persistent phải ở trạng thái được tích chọn")
                .isTrue();
        loginPage.clickLogin();
    }

    @Test
    @Order(8)
    @Story("TC08 - Session Persistence")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("TC08: Đăng nhập không chọn Giữ tôi luôn đăng nhập")
    @Description("Kiểm tra hành vi mặc định khi không kích hoạt checkbox ghi nhớ đăng nhập")
    public void testTC08_LoginWithoutPersistent() {
        LoginPage loginPage = new LoginPage(getDriver()).open();
        loginPage.enterUsername("huongnt");
        loginPage.enterPassword("password123");
        loginPage.setPersistent(false);
        assertThat(loginPage.isPersistentChecked())
                .as("Checkbox persistent không được tích chọn")
                .isFalse();
        loginPage.clickLogin();
    }
}
