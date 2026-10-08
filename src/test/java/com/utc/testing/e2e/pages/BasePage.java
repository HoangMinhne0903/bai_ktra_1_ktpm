package com.utc.testing.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Lớp cơ sở (BasePage) theo chuẩn POM trong Buổi 8 Slide 49-50.
 * Quản lý WebDriver và WebDriverWait, cung cấp các thao tác tương tác có Explicit Wait.
 */
public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Chờ phần tử sẵn sàng click rồi thực hiện click (Slide 50)
     */
    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    /**
     * Chờ phần tử hiển thị, xóa dữ liệu cũ và nhập text mới (Slide 50)
     */
    protected void type(By locator, String text) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        el.clear();
        if (text != null && !text.isEmpty()) {
            el.sendKeys(text);
        }
    }

    /**
     * Lấy văn bản hiển thị của phần tử sau khi đã hiển thị (Slide 50)
     */
    protected String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    /**
     * Lấy giá trị của thuộc tính (Slide 36: getAttribute)
     */
    protected String getAttribute(By locator, String attributeName) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator)).getAttribute(attributeName);
    }

    /**
     * Kiểm tra phần tử có hiển thị trên màn hình hay không
     */
    protected boolean isDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Kiểm tra phần tử có tồn tại trong DOM (Slide 59)
     */
    protected boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    /**
     * Gửi phím đặc biệt (Enter, Tab, etc.) vào phần tử
     */
    protected void sendKeys(By locator, CharSequence... keys) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        el.sendKeys(keys);
    }

    /**
     * Tìm WebElement
     */
    protected WebElement find(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
