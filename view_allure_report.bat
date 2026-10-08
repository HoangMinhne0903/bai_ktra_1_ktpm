@echo off
chcp 65001 > nul
echo ========================================================
echo [UTC] MỞ ALLURE REPORT TRÊN TRÌNH DUYỆT (ALLURE SERVE)
echo ========================================================
call .\mvnw.cmd allure:serve
pause
