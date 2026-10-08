@echo off
chcp 65001 > nul
echo ========================================================
echo [UTC] CHẠY TỰ ĐỘNG 16 TEST CASES (CHẾ ĐỘ HEADLESS - ẨN TRÌNH DUYỆT)
echo ========================================================
call .\mvnw.cmd test -Dheadless=true
echo.
echo ========================================================
echo [UTC] ĐANG XUẤT ALLURE REPORT...
echo ========================================================
call .\mvnw.cmd allure:report
echo.
echo Báo cáo hoàn tất tại: target\site\allure-maven-plugin\index.html
pause
