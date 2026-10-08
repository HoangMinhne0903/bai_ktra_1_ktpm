package com.utc.testing.e2e.base;

import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Tự động chụp ảnh màn hình khi test FAIL (Slide 61)
 * và đính kèm trực tiếp vào Allure Report.
 */
public class ScreenshotWatcher implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext ctx) throws Exception {
        WebDriver driver = BaseTest.getDriver();
        if (driver != null) {
            // Chụp ảnh đính kèm vào Allure Report
            captureScreenshotAllure(driver, ctx.getDisplayName());

            // Nếu test fail, lưu thêm file ra thư mục screenshots (Slide 61)
            if (ctx.getExecutionException().isPresent()) {
                try {
                    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    Path dir = Path.of("target/screenshots");
                    Files.createDirectories(dir);
                    Path dest = dir.resolve(ctx.getDisplayName().replaceAll("[^a-zA-Z0-9.-]", "_") + ".png");
                    Files.copy(src.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);
                } catch (Exception ignored) {
                }
            }
        }
    }

    @Attachment(value = "Screenshot: {name}", type = "image/png")
    public byte[] captureScreenshotAllure(WebDriver driver, String name) {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            return new byte[0];
        }
    }
}
