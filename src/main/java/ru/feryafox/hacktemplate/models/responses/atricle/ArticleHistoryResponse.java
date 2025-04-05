package ru.feryafox.hacktemplate.models.responses.atricle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.feryafox.hacktemplate.entities.ArticleHistory;
import ru.feryafox.hacktemplate.models.responses.UserResponce;

import java.util.Date;

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

    public static


}
