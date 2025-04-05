package ru.feryafox.hacktemplate.models.requests.article;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateArticleRequest {
    @NotNull
    private String title;
    private String content;
}
