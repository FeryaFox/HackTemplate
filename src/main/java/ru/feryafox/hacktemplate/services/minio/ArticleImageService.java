package ru.feryafox.hacktemplate.services.minio;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ArticleImageService extends BaseMinioService{
    public ArticleImageService(
            @Value("${minio.url}") String url,
            @Value("${minio.access-key}") String accessKey,
            @Value("${minio.secret-key}") String secretKey,
            @Value("${minio.public-url}")String publicUrl){
        super(url, accessKey, secretKey, "article-image", publicUrl);
    }
}
