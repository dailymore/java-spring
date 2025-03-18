# Optional trong Java

## 1. Optional là gì?
`Optional<T>` là một class trong Java (`java.util.Optional`) giúp **tránh lỗi `NullPointerException`** bằng cách bọc một giá trị có thể có hoặc không (`null`).

---

## 2. Ví dụ `Optional` cơ bản
```java
Optional<String> optionalString = Optional.of("Hello, Java!");
System.out.println(optionalString.get()); // Output: Hello, Java!
```
✅ **Nếu có giá trị**, bạn có thể gọi `.get()` để lấy nó.

---

## 3. Optional giúp tránh `NullPointerException`
Nếu bạn dùng `null` trực tiếp, có thể gây lỗi:
```java
String str = null;
System.out.println(str.length()); // NullPointerException!
```
Với `Optional`, bạn có thể kiểm tra trước khi truy xuất:
```java
Optional<String> optionalStr = Optional.ofNullable(null);
System.out.println(optionalStr.isPresent()); // false (tránh lỗi)
```

---

## 4. Các cách tạo `Optional`
| Cách tạo | Mô tả |
|----------|--------|
| `Optional.of(value)` | ✅ Dùng khi chắc chắn `value` không phải `null`. Nếu `null` → lỗi ngay! |
| `Optional.ofNullable(value)` | ✅ Dùng khi `value` có thể `null`. Nếu `null`, tạo `Optional.empty()` |
| `Optional.empty()` | ✅ Tạo một `Optional` rỗng |

📌 **Ví dụ:**  
```java
Optional<String> optional1 = Optional.of("Hello"); // Có giá trị
Optional<String> optional2 = Optional.ofNullable(null); // Rỗng
Optional<String> optional3 = Optional.empty(); // Rỗng
```

---

## 5. Cách sử dụng `Optional`

### 1. Kiểm tra giá trị có tồn tại không
```java
Optional<String> optional = Optional.ofNullable(null);
if (optional.isPresent()) {
    System.out.println(optional.get());
} else {
    System.out.println("Không có giá trị!");
}
```

### 2. Dùng `orElse()` hoặc `orElseGet()` để có giá trị mặc định
```java
String result = optional.orElse("Giá trị mặc định");
System.out.println(result); // Output: Giá trị mặc định
```

### 3. Dùng `orElseThrow()` để ném lỗi nếu không có giá trị
```java
String result = optional.orElseThrow(() -> new RuntimeException("Không có dữ liệu!"));
```

### 4. Dùng `ifPresent()` để chạy logic nếu có giá trị
```java
optional.ifPresent(value -> System.out.println("Giá trị: " + value));
```

---

## 6. `Optional` trong Spring Data JPA
Spring Data JPA thường trả về `Optional` để tránh `null`, ví dụ:
```java
Optional<Student> student = studentRepository.findById(1);
```
Nếu chắc chắn có dữ liệu, dùng:
```java
Student student = studentRepository.findById(1)
    .orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên!"));
```

---

## 🎯 Kết luận:
✅ **Tránh lỗi `NullPointerException`**  
✅ **Giúp code rõ ràng, dễ đọc hơn**  
✅ **Nên dùng `Optional` thay vì kiểm tra `null` bằng `if-else`** 🚀

