package bit.com.a.s3;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * S3Service
 */
@Service
public class S3Service {

  public static final String CLOUD_FRONT_DOMAIN_NAME = "https://" + "dixo0q5vi6g16.cloudfront.net" + "/";

  private AmazonS3 s3Client;

  @Value("${cloud.aws.credentials.accessKey}")
  private String accessKey;

  @Value("${cloud.aws.credentials.secretKey}")
  private String secretKey;

  @Value("${cloud.aws.s3.bucket}")
  private String bucket;

  @Value("${cloud.aws.region.static}")
  private String region;

  public S3Service() {

  }

  @PostConstruct
  public void setS3Client() {
    AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

    s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
        .withRegion(this.region).build();
  }

  public String upload(MultipartFile file) throws IOException {
    String fileName = file.getOriginalFilename();

    s3Client.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), null)
        .withCannedAcl(CannedAccessControlList.PublicRead));

    return fileName;
  }

  public String upload(String currentFilePath, MultipartFile file) throws IOException {
    // 고유한 key 값을 갖기위해 현재 시간을 postfix로 붙여줌
    // SimpleDateFormat date = new SimpleDateFormat("yyyymmddHHmmss");
    String fileName = file.getOriginalFilename() + "-" + LocalDateTime.now();
    // + date.format(new Date());

    // key가 존재하면 기존 파일은 삭제
    if ("".equals(currentFilePath) == false && currentFilePath != null) {
      boolean isExistObject = s3Client.doesObjectExist(bucket, currentFilePath);

      if (isExistObject == true) {
        s3Client.deleteObject(bucket, currentFilePath);
      }
    }

    // 파일 업로드
    s3Client.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), null)
        .withCannedAcl(CannedAccessControlList.PublicRead));

    return fileName;
  }

}