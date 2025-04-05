package ru.feryafox.hacktemplate.models.responses.atricle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.feryafox.hacktemplate.entities.ArticleHistory;
import ru.feryafox.hacktemplate.models.responses.UserResponce;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleHistoryResponse {
    private String id;
    private ArticleHistory.EventType eventType;
    private Date changedAt;
    private UserResponce changedBy;

    public static ArticleHistoryResponse from(ArticleHistory articleHistory) {
        return ArticleHistoryResponse.builder()
                .id(articleHistory.getId().toString())
                .eventType(articleHistory.getEventType())
                .changedAt(articleHistory.getChangedAt())
                .changedBy(UserResponce.convertToUserResponse(articleHistory.getChangedUser()))
                .build();
    }

    public static List<ArticleHistoryResponse> from(Set<ArticleHistory> articleHistoryList) {
        return articleHistoryList.stream().map(ArticleHistoryResponse::from).collect(Collectors.toList());
    }
}
