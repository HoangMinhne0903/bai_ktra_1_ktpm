package com.utc.testing.e2e.base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

/**
 * Lớp kiểm thử cơ sở (BaseTest) theo chuẩn Slide 48 & 60-61.
 * Thiết lập ChromeDriver, cấu hình chế độ Headless / Maximized,
 * quản lý vòng đời Driver và chụp ảnh màn hình khi có sự cố.
 */
@ExtendWith(ScreenshotWatcher.class)
public abstract class BaseTest {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        // Kiểm tra cấu hình headless (Slide 60)
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"))
                || "true".equalsIgnoreCase(System.getenv("HEADLESS"));

        if (isHeadless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
        } else {
            options.addArguments("--start-maximized");
        }

        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driverThreadLocal.set(driver);
    }

    @AfterEach
    public void tearDown() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                driver.quit(); // Đảm bảo đóng trình duyệt hoàn toàn (Slide 8)
            } finally {
                driverThreadLocal.remove();
            }
        }
    }
}
