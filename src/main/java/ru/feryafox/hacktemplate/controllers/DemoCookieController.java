package ru.feryafox.hacktemplate.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cookie-test")
public class DemoCookieController {

    @GetMapping("/set")
    public ResponseEntity<?> setCookie() {
        ResponseCookie cookie = ResponseCookie
                .from("my_cookie", "hello_world")
                .httpOnly(true)
                .secure(false)          // мы же на http://localhost
                .path("/")
                .maxAge(60 * 60 * 24)  // 1 день
                .build();

        // Шлём Set-Cookie и смотрим, что произойдёт
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("Cookie set!");
    }

    // Для отладки — проверка, что мы можем прочитать куку (не httpOnly)
    @GetMapping("/check")
    public ResponseEntity<?> checkCookie(@CookieValue(name = "my_cookie", required = false) String cookieVal) {
        return ResponseEntity.ok("Cookie my_cookie = " + cookieVal);
    }
}
