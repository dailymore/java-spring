package com.example.spring.annotation.user;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Target({ ElementType.PARAMETER }) // * Chỉ áp dụng cho tham số trong phương thức
@Retention(RetentionPolicy.RUNTIME) // * tồn tại lúc runtime
@AuthenticationPrincipal // * là alias cho annotation này, không có ý nghĩa gì
public @interface UserDetail {
}
