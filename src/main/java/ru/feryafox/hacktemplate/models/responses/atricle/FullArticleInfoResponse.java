package ru.feryafox.hacktemplate.models.responses.atricle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.feryafox.hacktemplate.entities.Article;
import ru.feryafox.hacktemplate.entities.ArticleHistory;
import ru.feryafox.hacktemplate.models.responses.UserResponce;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FullArticleInfoResponse {
    private UUID id;
    private String image;
    private String title;
    private String content;
    private Date createdAt;
    private UserResponce createdBy;
    private UserResponce updatedBy;

    public static FullArticleInfoResponse from(Article article, String defaultImage) {
        return FullArticleInfoResponse.builder()
                .id(article.getId())
                .image(article.getImage() == null ? defaultImage : article.getImage())
                .title(article.getTitle())
                .content(article.getContent())
                .createdAt(article.getCreatedAt())
                .createdBy(UserResponce.convertToUserResponse(article.getCreatedUser()))
                .updatedBy(UserResponce.convertToUserResponse(article.getUpdatedUser()))
                .build();
    }
}
