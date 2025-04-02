package ru.feryafox.hacktemplate.utils.auth.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class RefreshToken {
    private String refreshToken;
    private Date expiredAt;
}
