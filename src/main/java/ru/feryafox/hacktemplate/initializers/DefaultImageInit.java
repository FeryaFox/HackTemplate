package ru.feryafox.hacktemplate.initializers;

import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.feryafox.hacktemplate.services.minio.ArticleDefaultImageService;

@Configuration
@RequiredArgsConstructor
public class DefaultImageInit {
    @Bean
    public CommandLineRunner init(ArticleDefaultImageService articleDefaultImageService) {
        return args -> {
            articleDefaultImageService.upload();
        };
    }
}
