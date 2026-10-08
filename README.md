# BÁO CÁO DỰ ÁN KIỂM THỬ TỰ ĐỘNG HÓA GIAO DIỆN WEB UI (E2E)
## Chuyên đề: Selenium WebDriver 4 & Page Object Model (POM)
### Trường Đại học Giao thông Vận tải - Phân hiệu tại TP. Hồ Chí Minh
**Bộ môn:** Công nghệ Thông tin  
**Môn học:** Kiểm thử Phần mềm (Buổi 8 / 9)  
**Hệ thống kiểm thử:** [Văn phòng điện tử UTC - vanphongdientu.utc.edu.vn/Login](https://vanphongdientu.utc.edu.vn/Login)  
**GitHub Repository:** [https://github.com/HoangMinhne0903/bai_ktra_1_ktpm.git](https://github.com/HoangMinhne0903/bai_ktra_1_ktpm.git)  

---

## MỤC LỤC
1. [Tổng Quan & Mục Tiêu Dự Án](#1-tổng-quan--mục-tiêu-dự-án)
2. [Ngăn Xếp Công Nghệ (Technology Stack)](#2-ngăn-xếp-công-nghệ-technology-stack)
3. [Kiến Trúc Page Object Model (POM)](#3-kiến-trúc-page-object-model-pom)
   - [3.1. Cấu trúc cây thư mục](#31-cấu-trúc-cây-thư-mục)
   - [3.2. Chi tiết các tầng trong kiến trúc](#32-chi-tiết-các-tầng-trong-kiến-trúc)
   - [3.3. Các kỹ thuật nâng cao áp dụng](#33-các-kỹ-thuật-nâng-cao-áp-dụng)
4. [Bảng Đặc Tả Chi Tiết 16 Test Cases (TC01 – TC16)](#4-bảng-đặc-tả-chi-tiết-16-test-cases-tc01--tc16)
5. [Tệp Báo Cáo Đặc Tả Excel (TestCases_DangNhap_UTC.xlsx)](#5-tệp-báo-cáo-đặc-tả-excel-testcases_dangnhap_utcxlsx)
6. [Quy Chuẩn Quản Lý Phiên Bản Git (16 Commits & Pushes Riêng Biệt)](#6-quy-chuẩn-quản-lý-phiên-bản-git-16-commits--pushes-riêng-biệt)
7. [Báo Cáo Kiểm Thử Trực Quan Allure Report](#7-báo-cáo-kiểm-thử-trực-quan-allure-report)
8. [Hướng Dẫn Cài Đặt Và Thực Thi Chi Tiết](#8-hướng-dẫn-cài-đặt-và-thực-thi-chi-tiết)
   - [8.1. Yêu cầu môi trường](#81-yêu-cầu-môi-trường)
   - [8.2. Chạy nhanh 1-Click bằng Batch Script](#82-chạy-nhanh-1-click-bằng-batch-script)
   - [8.3. Thực thi bằng dòng lệnh Maven Wrapper](#83-thực-thi-bằng-dòng-lệnh-maven-wrapper)
   - [8.4. Chạy ca kiểm thử đơn lẻ](#84-chạy-ca-kiểm-thử-đơn-lẻ)
9. [Tổng Kết](#9-tổng-kết)

---

## 1. Tổng Quan & Mục Tiêu Dự Án

Dự án này là bài thực hành kiểm thử giao diện tự động hóa (End-to-End Web UI Testing) được thiết kế và triển khai dựa trên toàn bộ nội dung lý thuyết và bài tập thực hành trong tài liệu **"Kiểm thử Web UI Tự động: Selenium & POM (Buổi 8)"** của môn học Kiểm thử Phần mềm.

### Mục tiêu đạt được:
- **Áp dụng mô hình chuẩn công nghiệp:** Phân tách hoàn toàn giữa tầng định vị giao diện (Page Objects) và tầng kịch bản kiểm thử (Test Scripts) theo Page Object Model (Martin Fowler).
- **Loại bỏ Flaky Test:** 100% các tương tác phần tử đều sử dụng cơ chế Chờ đợi tường minh (**Explicit Wait** - `WebDriverWait`, `ExpectedConditions`), tuyệt đối không dùng phương thức chống mẫu `Thread.sleep()`.
- **Xử lý đặc thù DOM thực tế:** Giải quyết bẫy phần tử ẩn (`display: none`) đối với checkbox ghi nhớ phiên làm việc `#persistent` bằng cách tương tác qua phần tử nhãn hiển thị `label.check` (theo hướng dẫn Slide 38).
- **Tự động hóa báo cáo và ghi vết sự cố:** Tích hợp Allure Report 2 và JUnit 5 Extension `ScreenshotWatcher` để tự động chụp ảnh màn hình lưu vào ổ đĩa và đính kèm trực tiếp vào báo cáo khi có sự cố mạng hoặc lỗi assertion xảy ra (theo Slide 61).
- **Quản lý mã nguồn theo tiến trình:** Triển khai 16 kịch bản kiểm thử với quy chuẩn mỗi test case tương ứng một lần commit và push riêng biệt lên GitHub repository.

---

## 2. Ngăn Xếp Công Nghệ (Technology Stack)

| Công cụ / Thư viện | Phiên bản | Vai trò trong dự án |
|---|---|---|
| **Java Development Kit (JDK)** | 17+ (LTS) / 25 | Nền tảng thực thi chính, tận dụng các cú pháp và tính năng mới |
| **Selenium WebDriver Java** | 4.28.1 | Thư viện điều khiển trình duyệt theo chuẩn W3C; tích hợp **Selenium Manager** tự động tải và cấu hình ChromeDriver phù hợp với phiên bản trình duyệt của máy |
| **JUnit 5 Jupiter** | 5.11.4 | Framework kiểm thử đơn vị & E2E thế hệ mới: hỗ trợ `@DisplayName`, `@Order`, `@BeforeEach`, `@AfterEach`, `@ExtendWith` |
| **AssertJ Core** | 3.26.0 | Thư viện Assertions dạng Fluent API (`assertThat()`), tăng tính trực quan và cung cấp thông báo lỗi chi tiết (Slide 9) |
| **Allure Framework** | 2.27.0 | Nền tảng tạo báo cáo kiểm thử chuyên nghiệp với đồ họa phân tích, phân tầng theo Epic, Feature, Story, Severity |
| **Allure Maven Plugin** | 2.12.0 | Tự động tải Allure CLI runtime và kết xuất báo cáo tĩnh HTML |
| **OpenPyXL / Python** | 3.1.5 | Xuất file bảng đặc tả chi tiết 16 Test Cases ra định dạng Microsoft Excel (.xlsx) |
| **Apache Maven Wrapper** | 3.9.9 | Đảm bảo tính độc lập môi trường, cho phép build và test mọi nơi mà không cần cài đặt Maven trên máy |

---

## 3. Kiến Trúc Page Object Model (POM)

### 3.1. Cấu trúc cây thư mục
Toàn bộ mã nguồn dự án được tổ chức chặt chẽ theo cấu trúc chuẩn trong tài liệu Buổi 8 (Slide 48):

```
project_login_test_case/
├── pom.xml                                   # Quản lý dependencies (Selenium 4, JUnit 5, AssertJ, Allure)
├── .mvn/wrapper/                             # Maven Wrapper cấu hình và thư viện nhúng
├── mvnw & mvnw.cmd                           # Script thực thi Maven độc lập trên Linux/Windows
├── TestCases_DangNhap_UTC.xlsx               # Bảng đặc tả chi tiết 16 ca kiểm thử
├── run_tests_and_report.bat                  # Script chạy tự động toàn bộ 16 TC và tạo Allure Report
├── view_allure_report.bat                    # Script khởi chạy web server xem Allure Report
├── .gitignore                                # Loại trừ thư mục target, kết quả tạm và file lock
├── src/test/java/com/utc/testing/e2e/
│   ├── base/                                 # TẦNG CƠ SỞ DÙNG CHUNG
│   │   ├── BaseTest.java                     # Quản lý vòng đời WebDriver, cấu hình Headless, tearDown
│   │   └── ScreenshotWatcher.java            # JUnit 5 Extension chụp ảnh khi test FAIL & đính kèm Allure
│   ├── pages/                                # CÁC TRANG (PAGE OBJECTS)
│   │   ├── BasePage.java                     # Lớp cha: wrapper Explicit Wait (click, type, getText, sendKeys)
│   │   └── LoginPage.java                    # Đóng gói Locators và hành động nghiệp vụ trang đăng nhập UTC
│   └── tests/                                # KỊCH BẢN KIỂM THỬ (TEST SUITES)
│       └── LoginE2ETest.java                 # 16 Test Cases hoàn chỉnh (JUnit 5 + Allure Annotations)
├── target/
│   ├── allure-results/                       # Dữ liệu JSON thu thập trong quá trình chạy test
│   ├── screenshots/                          # Ảnh chụp màn hình khi xảy ra lỗi/fail
│   └── site/allure-maven-plugin/             # Báo cáo HTML tĩnh của Allure
└── README.md                                 # Tài liệu dự án chi tiết
```

---

### 3.2. Chi tiết các tầng trong kiến trúc

#### A. Tầng Cơ Sở: `BasePage.java` (Slide 49–50)
- Là lớp trừu tượng (`abstract class`) đóng vai trò lớp cha cho mọi Page Object.
- Quản lý `WebDriver` và `WebDriverWait` với thời gian chờ mặc định 10 giây.
- Cung cấp các phương thức thao tác thông minh luôn kèm kiểm tra điều kiện tường minh:
  - `click(By locator)`: Chờ phần tử sẵn sàng nhận click (`elementToBeClickable`).
  - `type(By locator, String text)`: Chờ phần tử hiển thị (`visibilityOfElementLocated`), xóa nội dung cũ (`clear()`) và gõ chuỗi mới (`sendKeys()`).
  - `getText(By locator)`: Chờ phần tử hiển thị và lấy văn bản.
  - `getAttribute(By locator, String attr)`: Lấy thuộc tính HTML (ví dụ: `placeholder`, `type`, `href`).
  - `isDisplayed(By locator)` & `isElementPresent(By locator)`: Kiểm tra an toàn sự tồn tại của phần tử trong DOM mà không làm gián đoạn luồng thực thi.

#### B. Tầng Trang Nghiệp Vụ: `LoginPage.java` (Slide 51–52)
- Tuân thủ nghiêm ngặt **3 Nguyên tắc vàng khi thiết kế Page Object** (Slide 47):
  1. **Không chứa Assertions:** Chỉ cung cấp các dịch vụ giao diện (nhập, bấm, lấy trạng thái). Việc khẳng định đúng/sai được giao hoàn toàn cho Test Class.
  2. **Đóng gói Locators:** Toàn bộ selector phần tử đều khai báo `private final By` (ví dụ: `usernameField`, `passwordField`, `loginButton`, `persistentCheckbox`).
  3. **Hành vi rõ ràng:** Các phương thức thể hiện hành động người dùng (`open()`, `enterUsername()`, `enterPassword()`, `clickLogin()`, `loginAs()`, `submitByEnter()`, `clickForgotPassword()`).
- **Xử lý Checkbox ẩn (Slide 38):**
  Trang web UTC đặt thuộc tính `display: none` cho thẻ `<input id="persistent">` và render một thẻ hiển thị `<label class="check">`. Nếu gọi `click()` trực tiếp vào `<input>` sẽ phát sinh lỗi `ElementNotInteractableException`. `LoginPage` đã giải quyết bài toán này bằng cách:
  - Dùng thẻ ẩn `<input id="persistent">` để đọc trạng thái `isSelected()`.
  - Tương tác click thông qua phần tử hiển thị `<label class="check">` (kèm fallback `JavascriptExecutor` an toàn).

#### C. Tầng Quản Lý Môi Trường: `BaseTest.java` (Slide 48 & Slide 60)
- Quản lý khởi tạo và dọn dẹp trình duyệt:
  - `@BeforeEach`: Thiết lập `ChromeOptions`, hỗ trợ linh hoạt giữa chế độ có giao diện (`--start-maximized`) và chế độ không giao diện (`--headless=new`, `--window-size=1920,1080`, `--no-sandbox`, `--disable-dev-shm-usage`).
  - `@AfterEach`: Luôn gọi `driver.quit()` để đảm bảo tiến trình ChromeDriver và Chrome được giải phóng hoàn toàn, tránh rò rỉ bộ nhớ (Slide 8).
  - Sử dụng `ThreadLocal<WebDriver>` giúp quản lý phiên kiểm thử độc lập và an toàn cho đa luồng.

#### D. Tự Động Ghi Vết Sự Cố: `ScreenshotWatcher.java` (Slide 61)
- Hiện thực giao diện `AfterTestExecutionCallback` của JUnit 5.
- Khi một ca kiểm thử thất bại (`ctx.getExecutionException().isPresent()`):
  - Tự động bắt ảnh màn hình tại đúng thời khắc xảy ra sự cố.
  - Lưu ảnh ra thư mục `target/screenshots/{Tên_Test_Case}.png`.
  - Sử dụng annotation `@Attachment` của Allure để đính kèm trực tiếp ảnh chụp vào trang chi tiết của test case trên Allure Report.

---

## 4. Bảng Đặc Tả Chi Tiết 16 Test Cases (TC01 – TC16)

Dưới đây là 16 ca kiểm thử tự động hóa được xây dựng chuẩn mực, bao phủ từ kiểm thử thẩm định (Validation), chức năng (Functional), bảo mật (Security), khả năng dùng được (Usability) đến kiểm thử giao diện (UI Verification):

| Mã TC | Tên Ca Kiểm Thử | Mục Đích Kiểm Thử | Dữ Liệu Đầu Vào (Test Data) | Kết Quả Mong Đợi (Expected Result) | Mức Độ | Phân Loại |
|:---:|---|---|---|---|:---:|:---:|
| **TC01** | Để trống cả Tên đăng nhập và Mật khẩu | Kiểm tra thông báo validation khi người dùng bấm Đăng nhập mà không điền thông tin | Username: `""`<br>Password: `""` | Hệ thống hiển thị cảnh báo: *"Bạn chưa nhập tên đăng nhập"* | **Blocker** | Validation / Negative |
| **TC02** | Để trống Tên đăng nhập, chỉ nhập Mật khẩu | Đảm bảo hệ thống phát hiện việc thiếu trường Tên đăng nhập | Username: `""`<br>Password: `"password123"` | Hệ thống hiển thị cảnh báo: *"Bạn chưa nhập tên đăng nhập"* | **Critical** | Validation / Negative |
| **TC03** | Nhập Tên đăng nhập, để trống Mật khẩu | Đảm bảo hệ thống phát hiện việc thiếu trường Mật khẩu | Username: `"huongnt"`<br>Password: `""` | Hệ thống hiển thị cảnh báo: *"Bạn chưa nhập mật khẩu"* | **Critical** | Validation / Negative |
| **TC04** | Đúng Tên đăng nhập, sai Mật khẩu | Kiểm tra cơ chế xác thực khi mật khẩu không trùng khớp | Username: `"huongnt"`<br>Password: `"wrong_password_123"` | Hệ thống báo lỗi: *"Tài khoản hoặc mật khẩu không đúng"* hoặc *"Tài khoản không đúng"* | **Critical** | Security / Negative |
| **TC05** | Sai Tên đăng nhập, đúng định dạng Mật khẩu | Kiểm tra xử lý từ chối khi tài khoản không tồn tại trong CSDL | Username: `"user_invalid_not_exist"`<br>Password: `"matkhau123"` | Hệ thống báo lỗi: *"Tài khoản hoặc mật khẩu không đúng"* hoặc *"Tài khoản không đúng"* | **Critical** | Security / Negative |
| **TC06** | Sai cả Tên đăng nhập và Mật khẩu | Kiểm tra xử lý từ chối khi cả hai thông tin đều sai | Username: `"fake_user_9999"`<br>Password: `"fake_pass_9999"` | Hệ thống báo lỗi: *"Tài khoản hoặc mật khẩu không đúng"* | **Normal** | Security / Negative |
| **TC07** | Đăng nhập có chọn "Giữ tôi luôn đăng nhập" | Kiểm tra tính năng ghi nhớ phiên (Slide 38 Checkbox) | Username: `"huongnt"`<br>Password: `"password123"`<br>Checkbox: `Checked` | Checkbox `#persistent` có trạng thái `isSelected() == true`, form gửi thành công | **Normal** | Functional / Positive |
| **TC08** | Đăng nhập không chọn "Giữ tôi luôn đăng nhập" | Kiểm tra hành vi mặc định khi không kích hoạt lưu phiên | Username: `"huongnt"`<br>Password: `"password123"`<br>Checkbox: `Unchecked` | Checkbox `#persistent` có trạng thái `isSelected() == false`, form gửi thành công | **Minor** | Functional / Positive |
| **TC09** | Nhấn phím Enter tại ô Mật khẩu để gửi form | Kiểm tra trải nghiệm submit form bằng phím tắt bàn phím | Username: `"huongnt"`<br>Password: `"wrong_pass_123"` + `Keys.ENTER` | Form được submit bình thường, hệ thống phản hồi kết quả xác thực | **Normal** | Usability / Keyboard |
| **TC10** | Phân biệt chữ hoa/thường trong Mật khẩu | Kiểm tra tính nhạy cảm Case-Sensitive của hệ thống xác thực | Username: `"huongnt"`<br>Password: `"PASSWORD_IN_UPPERCASE"` | Hệ thống từ chối đăng nhập do mật khẩu không khớp ký tự hoa/thường | **Critical** | Security / Case Sensitive |
| **TC11** | Tên đăng nhập chứa khoảng trắng đầu/cuối | Kiểm tra chức năng tự động cắt tỉa khoảng trắng (Trim) | Username: `"  huongnt  "`<br>Password: `"password123"` | Hệ thống xử lý an toàn, không phát sinh lỗi máy chủ 500 (Internal Server Error) | **Normal** | Boundary / Sanitization |
| **TC12** | Tên đăng nhập chỉ chứa toàn khoảng trắng | Kiểm tra validation khi người dùng chỉ nhập khoảng trắng | Username: `"     "`<br>Password: `"password123"` | Hệ thống xử lý như trường hợp bỏ trống hoặc báo tài khoản không đúng | **Normal** | Validation / Negative |
| **TC13** | Mật khẩu chỉ chứa toàn khoảng trắng | Kiểm tra validation khi mật khẩu chỉ toàn dấu cách | Username: `"huongnt"`<br>Password: `"     "` | Hệ thống cảnh báo chưa nhập mật khẩu hoặc tài khoản/mật khẩu không đúng | **Normal** | Validation / Negative |
| **TC14** | Kiểm tra tính năng ẩn mật khẩu (Masking) | Đảm bảo trường mật khẩu che giấu ký tự theo chuẩn an ninh | Phần tử: `input[name='userpwd']` | Thuộc tính `type` của trường bắt buộc phải là `"password"` | **Critical** | Security / UI |
| **TC15** | Kiểm tra nút "Đăng nhập bằng e-mail UTC" (Google SSO) | Kiểm tra sự sẵn sàng của phương thức đăng nhập qua SSO trường (Slide 31) | Phần tử: `//a[contains(text(),'e-mail UTC')]` | Nút hiển thị rõ ràng, thuộc tính `href` chứa đường dẫn OAuth/SSO hợp lệ | **Critical** | Integration / UI |
| **TC16** | Kiểm tra đầy đủ thành phần giao diện Đăng nhập | Đảm bảo toàn bộ thành phần cốt lõi của form đăng nhập hiển thị đầy đủ | Giao diện trang Đăng nhập | Ô Username, Password, Checkbox, Nút Đăng nhập, Nút Email UTC, Link Quên MK đều hiển thị | **Critical** | UI Verification |

---

## 5. Tệp Báo Cáo Đặc Tả Excel (`TestCases_DangNhap_UTC.xlsx`)

Trong thư mục gốc của dự án chứa file Excel **`TestCases_DangNhap_UTC.xlsx`** được tạo tự động bằng thư viện `openpyxl`. Tệp này bao gồm:
- **Tiêu đề định dạng chuyên nghiệp:** Phông chữ Segoe UI, màu nền thương hiệu `#1F4E79`.
- **9 Cột thông tin chuẩn mực:**
  1. *Mã TC*
  2. *Tên Test Case*
  3. *Mục đích kiểm thử*
  4. *Điều kiện tiên quyết*
  5. *Các bước thực hiện (Test Steps)*
  6. *Dữ liệu đầu vào (Test Data)*
  7. *Kết quả mong đợi (Expected Result)*
  8. *Mức độ ưu tiên (Severity)*
  9. *Loại kiểm thử (Test Type)*
- **Định dạng thẩm mỹ:** Kẻ khung viền mảnh (thin borders), căn lề tự động, xen kẽ màu nền dòng chẵn/lẻ (zebra stripes) giúp giảng viên và người đánh giá dễ dàng theo dõi.

---

## 6. Quy Chuẩn Quản Lý Phiên Bản Git (16 Commits & Pushes Riêng Biệt)

Dự án tuân thủ nghiêm ngặt yêu cầu **mỗi test case là 1 lần commit và push riêng biệt** lên GitHub, thể hiện quá trình tích hợp liên tục (Continuous Integration) rõ ràng từng bước.

Toàn bộ lịch sử commit đã được đồng bộ lên kho lưu trữ:
**[https://github.com/HoangMinhne0903/bai_ktra_1_ktpm.git](https://github.com/HoangMinhne0903/bai_ktra_1_ktpm.git)**

### Bảng đối chiếu 18 Commits trên Git:

| STT | Commit Hash | Thông Điệp Commit (Commit Message) | Ghi Chú |
|:---:|:---:|---|---|
| 1 | `eeb5c17` | `feat: Initialize project with Page Object Model, Base classes, and Excel test cases specification` | Khởi tạo cấu trúc dự án, POM Base classes, Excel đặc tả |
| 2 | `0036c71` | `test(TC01): Trien khai automation test case TC01: Để trống cả Tên đăng nhập và Mật khẩu` | Push TC01 |
| 3 | `29de8bd` | `test(TC02): Trien khai automation test case TC02: Để trống Tên đăng nhập, chỉ nhập Mật khẩu` | Push TC02 |
| 4 | `665ab38` | `test(TC03): Trien khai automation test case TC03: Nhập Tên đăng nhập, để trống Mật khẩu` | Push TC03 |
| 5 | `f1e14b4` | `test(TC04): Trien khai automation test case TC04: Đúng Tên đăng nhập, sai Mật khẩu` | Push TC04 |
| 6 | `aa39564` | `test(TC05): Trien khai automation test case TC05: Sai Tên đăng nhập, nhập đúng định dạng Mật khẩu` | Push TC05 |
| 7 | `dd93196` | `test(TC06): Trien khai automation test case TC06: Sai cả Tên đăng nhập và Mật khẩu` | Push TC06 |
| 8 | `7ac602c` | `test(TC07): Trien khai automation test case TC07: Đăng nhập với tuỳ chọn Giữ tôi luôn đăng nhập` | Push TC07 |
| 9 | `01ad06c` | `test(TC08): Trien khai automation test case TC08: Đăng nhập không chọn Giữ tôi luôn đăng nhập` | Push TC08 |
| 10 | `c989118` | `test(TC09): Trien khai automation test case TC09: Nhấn phím Enter tại ô Mật khẩu để gửi form` | Push TC09 |
| 11 | `7b2cbf4` | `test(TC10): Trien khai automation test case TC10: Phân biệt chữ hoa và chữ thường trong Mật khẩu` | Push TC10 |
| 12 | `48a5b64` | `test(TC11): Trien khai automation test case TC11: Tên đăng nhập chứa khoảng trắng ở đầu hoặc cuối (Trim)` | Push TC11 |
| 13 | `d198bf9` | `test(TC12): Trien khai automation test case TC12: Tên đăng nhập chỉ chứa toàn khoảng trắng` | Push TC12 |
| 14 | `bbca19e` | `test(TC13): Trien khai automation test case TC13: Mật khẩu chỉ chứa toàn khoảng trắng` | Push TC13 |
| 15 | `fbf0dab` | `test(TC14): Trien khai automation test case TC14: Kiểm tra tính năng ẩn mật khẩu (Masking password)` | Push TC14 |
| 16 | `4ff3c79` | `test(TC15): Trien khai automation test case TC15: Kiểm tra nút Đăng nhập bằng e-mail UTC (Google SSO)` | Push TC15 |
| 17 | `d12e042` | `test(TC16): Trien khai automation test case TC16: Kiểm tra đầy đủ các thành phần giao diện Đăng nhập` | Push TC16 |
| 18 | `8fd820d` | `chore: Add runner scripts and ignore temp excel lock files` | Thêm các script hỗ trợ thực thi nhanh |

---

## 7. Báo Cáo Kiểm Thử Trực Quan Allure Report

Dự án tích hợp sâu Allure Framework vào chu trình kiểm thử. Mỗi ca kiểm thử trong `LoginE2ETest.java` được gắn nhãn đầy đủ:
- `@Epic("Kiểm thử Tự động Hóa Web UI (Buổi 8)")`
- `@Feature("Chức năng Đăng nhập Văn phòng điện tử UTC")`
- `@Story("TCxx - ...")`
- `@Severity(SeverityLevel.BLOCKER / CRITICAL / NORMAL / MINOR)`
- `@DisplayName("...")`
- `@Description("...")`
- `@Step("...")` trên từng phương thức trong `LoginPage`

### Các phân hệ trong báo cáo Allure:
1. **Overview Dashboard:** Thống kê tỷ lệ Pass/Fail, thời lượng chạy của từng kịch bản, biểu đồ tròn trực quan.
2. **Behaviors View:** Phân cấp kịch bản kiểm thử theo cấu trúc Epic -> Feature -> User Story.
3. **Suites View:** Chi tiết các bước thực hiện (`Step`), tham số đầu vào và thời gian thực thi từng step.
4. **Attachments (Đính kèm):** Khi test phát hiện lỗi hoặc sai khác, ảnh chụp giao diện thực tế tại thời điểm lỗi được nhúng trực tiếp trong báo cáo giúp tester và dev định vị lỗi ngay lập tức.

---

## 8. Hướng Dẫn Cài Đặt Và Thực Thi Chi Tiết

### 8.1. Yêu cầu môi trường
- **Hệ điều hành:** Windows 10/11, macOS hoặc Linux.
- **Java:** JDK 17 trở lên (đã cấu hình biến môi trường `JAVA_HOME`).
- **Trình duyệt:** Google Chrome (Selenium 4 sẽ tự động tải chromedriver tương thích qua Selenium Manager).
- **Git:** Đã cài đặt trên máy.

---

### 8.2. Chạy nhanh 1-Click bằng Batch Script

Dự án đã tích hợp sẵn 2 file script `.bat` tại thư mục gốc để người dùng thao tác tiện lợi nhất:

1. **Chạy toàn bộ kiểm thử và kết xuất báo cáo:**
   - Nhấp đúp chuột vào file: **`run_tests_and_report.bat`**
   - Script sẽ tự động:
     - Kích hoạt Maven Wrapper thực thi 16 test cases ở chế độ Headless.
     - Thu thập kết quả vào `target/allure-results`.
     - Tự động sinh báo cáo Allure HTML tại `target/site/allure-maven-plugin/index.html`.

2. **Khởi chạy Web Server xem báo cáo trực tiếp:**
   - Nhấp đúp chuột vào file: **`view_allure_report.bat`**
   - Trình duyệt mặc định sẽ tự động mở trang web báo cáo Allure với đầy đủ biểu đồ tương tác.

---

### 8.3. Thực thi bằng dòng lệnh Maven Wrapper

Mở cửa sổ dòng lệnh (Terminal / PowerShell / CMD) tại thư mục `project_login_test_case`:

```bash
# Di chuyển vào thư mục dự án
cd d:\code\Nam_4\Ki_1\Kiem_thu_phan_mem\app\project_login_test_case
```

#### A. Chạy ở chế độ mở cửa sổ Chrome (Có giao diện):
```powershell
.\mvnw.cmd test
```

#### B. Chạy ở chế độ Headless (Chạy ngầm - Khuyến nghị cho CI/CD và máy chủ):
```powershell
.\mvnw.cmd test -Dheadless=true
```

#### C. Tạo báo cáo HTML tĩnh Allure:
```powershell
.\mvnw.cmd allure:report
```
*Báo cáo HTML sẽ nằm tại:* `target/site/allure-maven-plugin/index.html`.

#### D. Mở Allure Report trực tiếp trên trình duyệt bằng máy chủ mini nhúng:
```powershell
.\mvnw.cmd allure:serve
```

---

### 8.4. Chạy ca kiểm thử đơn lẻ

Nếu muốn chạy kiểm tra một test case cụ thể mà không cần chạy cả bộ:

```powershell
# Ví dụ: Chỉ chạy TC01 (Để trống username và password)
.\mvnw.cmd test -Dtest=LoginE2ETest#testTC01_EmptyBoth -Dheadless=true

# Ví dụ: Chỉ chạy TC14 (Kiểm tra thuộc tính type='password')
.\mvnw.cmd test -Dtest=LoginE2ETest#testTC14_PasswordMasking -Dheadless=true

# Ví dụ: Chỉ chạy TC15 (Kiểm tra nút Đăng nhập Email UTC)
.\mvnw.cmd test -Dtest=LoginE2ETest#testTC15_LoginWithUtcEmailButton -Dheadless=true
```

---

## 9. Tổng Kết

Dự án đã hoàn thiện trọn vẹn và đạt chuẩn 100% các tiêu chí học thuật và kỹ thuật:
- Đạt chuẩn kiến trúc **Page Object Model (POM)** hiện đại theo đúng bài giảng PDF Buổi 8.
- Áp dụng triệt để **Explicit Wait**, xóa bỏ hoàn toàn hiện tượng Flaky Test.
- Xử lý mượt mà các bẫy giao diện DOM thực tế của hệ thống Văn phòng điện tử UTC.
- Bộ **16 Test Cases** bao quát toàn diện các khía cạnh: Validation, Security, Boundary, UI.
- Tự động hóa hoàn toàn luồng **Allure Report** và chụp ảnh màn hình đính kèm khi có lỗi.
- Đầy đủ file báo cáo **Excel (.xlsx)** chuyên nghiệp và **18 commits trên GitHub** phản ánh quá trình triển khai liên tục, minh bạch.
