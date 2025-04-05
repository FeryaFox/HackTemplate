package ru.feryafox.hacktemplate.models.responses.atricle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.feryafox.hacktemplate.entities.ArticleHistory;
import ru.feryafox.hacktemplate.models.responses.UserResponce;

import java.util.Calendar;
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
    private String title;
    private ArticleHistory.EventType eventType;
    private Date changedAt;
    private UserResponce changedBy;
    private Boolean isDeleted;

    public static ArticleHistoryResponse from(ArticleHistory articleHistory) {
        return ArticleHistoryResponse.builder()
                .id(articleHistory.getId().toString())
                .title(articleHistory.getArticle().getTitle())
                .eventType(articleHistory.getEventType())
                .changedAt(articleHistory.getChangedAt())
                .changedBy(UserResponce.convertToUserResponse(articleHistory.getChangedUser()))
                .isDeleted(isDeletedBefore1Week(articleHistory))
                .build();
    }

    private static boolean isDeletedBefore1Week(ArticleHistory articleHistory) {
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date oneWeekAgo = calendar.getTime();

        return articleHistory.getArticle().getIsDeleted()
                && articleHistory.getChangedAt().after(oneWeekAgo)
                && articleHistory.getChangedAt().before(now);
    }

    public static List<ArticleHistoryResponse> from(Set<ArticleHistory> articleHistoryList) {
        return articleHistoryList.stream().map(ArticleHistoryResponse::from).collect(Collectors.toList());
    }

    public static List<ArticleHistoryResponse> from(List<ArticleHistory> articleHistoryList) {
        return articleHistoryList.stream().map(ArticleHistoryResponse::from).collect(Collectors.toList());
    }
}
