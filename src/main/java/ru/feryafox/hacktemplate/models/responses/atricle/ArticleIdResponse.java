package ru.feryafox.hacktemplate.models.responses.atricle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleIdResponse {
    private String id;
}
