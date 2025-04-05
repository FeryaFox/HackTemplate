package ru.feryafox.hacktemplate.services.minio;

import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class ArticleDefaultImageService extends BaseMinioService{
    private static final String fileName = "default.png";
    public final String DEFAULT_IMAGE;

    public ArticleDefaultImageService(
            @Value("${minio.url}") String url,
            @Value("${minio.access-key}") String accessKey,
            @Value("${minio.secret-key}") String secretKey,
            @Value("${minio.public-url}") String publicUrl){
        super(url, accessKey, secretKey, "article-image", publicUrl);
        DEFAULT_IMAGE = publicUrl + "/" + bucketName + "/" + fileName;
    }

    public void upload() {
        File file = new File(fileName);

        if(!file.exists()){
            throw new RuntimeException("File not found: " + fileName);
        }

        try (InputStream fileStream = new FileInputStream(file)) {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .stream(fileStream, file.length(), -1)
                            .contentType("image/jpeg")
                            .build()
            );
        } catch (IOException e) {
            throw new RuntimeException("Error uploading file to Minio", e);
        } catch (ServerException | InsufficientDataException | ErrorResponseException | NoSuchAlgorithmException |
                 InvalidKeyException | InvalidResponseException | XmlParserException | InternalException e) {
            throw new RuntimeException(e);
        }
    }
}
