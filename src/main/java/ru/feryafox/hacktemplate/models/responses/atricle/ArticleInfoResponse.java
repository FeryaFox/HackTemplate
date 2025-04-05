package ru.feryafox.hacktemplate.models.responses.atricle;

import lombok.Builder;
import lombok.Data;
import ru.feryafox.hacktemplate.entities.Article;
import ru.feryafox.hacktemplate.models.responses.UserResponce;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
public class ArticleInfoResponse {
    private UUID id;
    private String image;
    private String title;
    private Date createdAt;
    private UserResponce updateBy;

    public static ArticleInfoResponse from(Article article, String defaultImage) {
        return ArticleInfoResponse.builder()
                .id(article.getId())
                .image(article.getImage() == null ? defaultImage : article.getImage())
                .title(article.getTitle())
                .createdAt(article.getCreatedAt())
                .updateBy(UserResponce.convertToUserResponse(article.getUpdatedUser()))
                .build();
    }

    public static List<ArticleInfoResponse> from(List<Article> articles, String defaultImage) {
        return articles.stream()
                .map(article -> from(article, defaultImage))
                .collect(Collectors.toList());
    }

}
