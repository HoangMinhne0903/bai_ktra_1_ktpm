package com.utc.testing.e2e.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Page Object cho trang đăng nhập UTC (https://vanphongdientu.utc.edu.vn/Login)
 * Cài đặt theo chuẩn Slide 51-52, nguyên tắc 1-3 của POM (Slide 47).
 */
public class LoginPage extends BasePage {

    public static final String URL = "https://vanphongdientu.utc.edu.vn/Login";

    // 1. Locators đóng gói private (Slide 47 & Slide 51)
    private final By usernameField = By.name("username");
    private final By passwordField = By.name("userpwd");
    private final By loginButton = By.cssSelector("input.submit_login");
    private final By persistentCheckbox = By.id("persistent");
    private final By persistentLabel = By.cssSelector("label.check");
    private final By forgotPasswordLink = By.cssSelector("a[href*='GetPass']");
    private final By utcEmailButton = By.xpath("//a[contains(text(),'e-mail UTC')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // 2. Navigation & Actions (Slide 52)

    @Step("Mở trang đăng nhập: " + URL)
    public LoginPage open() {
        driver.get(URL);
        return this;
    }

    @Step("Nhập tên đăng nhập: {username}")
    public LoginPage enterUsername(String username) {
        type(usernameField, username);
        return this;
    }

    @Step("Nhập mật khẩu: [PROTECTED]")
    public LoginPage enterPassword(String password) {
        type(passwordField, password);
        return this;
    }

    @Step("Bấm nút Đăng nhập")
    public LoginPage clickLogin() {
        click(loginButton);
        return this;
    }

    @Step("Đăng nhập với tài khoản: {username}")
    public LoginPage loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        return this;
    }

    @Step("Nhập mật khẩu và nhấn phím ENTER để gửi form")
    public LoginPage submitByEnter(String username, String password) {
        enterUsername(username);
        type(passwordField, password);
        sendKeys(passwordField, Keys.ENTER);
        return this;
    }

    /**
     * Xử lý checkbox 'Giữ tôi luôn đăng nhập' theo chuẩn Slide 38:
     * Checkbox thật (#persistent) bị ẩn (display:none), click thông qua label.check.
     */
    @Step("Thiết lập lựa chọn Giữ tôi luôn đăng nhập: {check}")
    public LoginPage setPersistent(boolean check) {
        WebElement realCheckbox = find(persistentCheckbox);
        boolean isChecked = realCheckbox.isSelected();
        if (isChecked != check) {
            try {
                click(persistentLabel);
            } catch (Exception e) {
                // Fallback JavaScript click nếu label bị che
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", realCheckbox);
            }
        }
        return this;
    }

    public boolean isPersistentChecked() {
        return find(persistentCheckbox).isSelected();
    }

    public String getPasswordFieldType() {
        return getAttribute(passwordField, "type");
    }

    public boolean isUsernameFieldDisplayed() {
        return isDisplayed(usernameField);
    }

    public boolean isPasswordFieldDisplayed() {
        return isDisplayed(passwordField);
    }

    public boolean isLoginButtonDisplayed() {
        return isDisplayed(loginButton);
    }

    public boolean isUtcEmailButtonDisplayed() {
        return isDisplayed(utcEmailButton);
    }

    public String getUtcEmailButtonHref() {
        return getAttribute(utcEmailButton, "href");
    }

    public boolean isForgotPasswordLinkDisplayed() {
        return isDisplayed(forgotPasswordLink);
    }

    @Step("Click vào liên kết Quên mật khẩu")
    public LoginPage clickForgotPassword() {
        click(forgotPasswordLink);
        return this;
    }

    public boolean isOnLoginPage() {
        return driver.getCurrentUrl().toLowerCase().contains("/login");
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Kiểm tra sự xuất hiện của thông báo lỗi trên trang
     */
    public boolean hasErrorText(String text) {
        try {
            List<WebElement> elements = driver.findElements(By.xpath("//*[contains(text(), '" + text + "')]"));
            return elements.stream().anyMatch(WebElement::isDisplayed);
        } catch (Exception e) {
            return false;
        }
    }
}
