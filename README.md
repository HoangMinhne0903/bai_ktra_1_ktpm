# Dự án Kiểm thử Tự động Hóa Giao diện Đăng nhập UTC (POM & Allure Report)

Dự án áp dụng mô hình **Page Object Model (POM)** theo chuẩn tài liệu **Kiểm thử Web UI Tự động: Selenium & POM (Buổi 8)** - Trường Đại học Giao thông Vận tải Phân hiệu tại TP.HCM.

- **Hệ thống kiểm thử:** [Văn phòng điện tử UTC](https://vanphongdientu.utc.edu.vn/Login)
- **Công nghệ cốt lõi:**
  - Java 17+ (Temurin OpenJDK)
  - Selenium WebDriver 4 (v4.28.1 - Chuẩn W3C & Selenium Manager tự động)
  - JUnit 5 Jupiter (v5.11.4)
  - AssertJ Core (v3.26.0)
  - Allure Report (v2.27.0)
  - Apache POI / OpenPyXL (Xuất đặc tả kiểm thử TestCases_DangNhap_UTC.xlsx)

---

## 1. Cấu trúc Dự án (Page Object Model)

```
project_login_test_case/
├── pom.xml                                 # Cấu hình thư viện Maven & plugin Allure
├── .mvn/wrapper/                           # Maven Wrapper tự động
├── mvnw & mvnw.cmd                         # Script thực thi Maven
├── TestCases_DangNhap_UTC.xlsx             # Bảng đặc tả 16 Test Cases
├── .gitignore
├── src/test/java/com/utc/testing/e2e/
│   ├── base/
│   │   ├── BaseTest.java                   # Khởi tạo WebDriver, timeout, đóng driver (@AfterEach)
│   │   └── ScreenshotWatcher.java          # Tự động chụp ảnh khi test FAIL & đính kèm Allure (Slide 61)
│   ├── pages/
│   │   ├── BasePage.java                   # Lớp cơ sở chứa Explicit Wait: click, type, getText (Slide 49-50)
│   │   └── LoginPage.java                  # Encapsulated Locators & hành động trang login (Slide 51-52)
│   └── tests/
│       └── LoginE2ETest.java               # 16 Test Cases chuẩn JUnit 5 & Allure Annotations
└── README.md
```

---

## 2. Danh sách 16 Test Cases

| Mã TC | Tên Test Case | Loại kiểm thử |
|---|---|---|
| **TC01** | Để trống cả Tên đăng nhập và Mật khẩu | Validation / Negative |
| **TC02** | Để trống Tên đăng nhập, chỉ nhập Mật khẩu | Validation / Negative |
| **TC03** | Nhập Tên đăng nhập, để trống Mật khẩu | Validation / Negative |
| **TC04** | Đúng Tên đăng nhập, sai Mật khẩu | Security / Negative |
| **TC05** | Sai Tên đăng nhập, nhập đúng định dạng Mật khẩu | Security / Negative |
| **TC06** | Sai cả Tên đăng nhập và Mật khẩu | Security / Negative |
| **TC07** | Đăng nhập với tuỳ chọn "Giữ tôi luôn đăng nhập" (Slide 38) | Functional / Positive |
| **TC08** | Đăng nhập không chọn "Giữ tôi luôn đăng nhập" | Functional / Positive |
| **TC09** | Nhấn phím Enter tại ô Mật khẩu để gửi form | Usability / Keyboard |
| **TC10** | Phân biệt chữ hoa và chữ thường trong Mật khẩu | Security / Case Sensitive |
| **TC11** | Tên đăng nhập chứa khoảng trắng ở đầu hoặc cuối (Trim) | Functional / Boundary |
| **TC12** | Tên đăng nhập chỉ chứa toàn khoảng trắng | Validation / Negative |
| **TC13** | Mật khẩu chỉ chứa toàn khoảng trắng | Validation / Negative |
| **TC14** | Kiểm tra tính năng ẩn mật khẩu (type="password") | Security / UI |
| **TC15** | Kiểm tra nút "Đăng nhập bằng e-mail UTC" (Google SSO) | Integration / UI |
| **TC16** | Kiểm tra đầy đủ các thành phần giao diện Đăng nhập | UI Verification |

---

## 3. Hướng dẫn Chạy Kiểm thử và Tạo Báo cáo Allure

### Bước 1: Chạy toàn bộ 16 test cases
```bash
# Chạy ở chế độ giao diện mặc định
.\mvnw.cmd test

# Hoặc chạy ở chế độ Headless (Slide 60)
.\mvnw.cmd test -Dheadless=true
```

### Bước 2: Tạo báo cáo Allure Report
```bash
# Tạo báo cáo tĩnh HTML (lưu tại target/site/allure-maven-plugin)
.\mvnw.cmd allure:report

# Hoặc mở trực tiếp trên trình duyệt
.\mvnw.cmd allure:serve
```
