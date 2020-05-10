package bit.com.a.gallery;

import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bit.com.a.s3.S3Service;

/**
 * GalleryController
 */

@RestController
@RequestMapping("/api/gallery")
public class GalleryController {

  //
  @Autowired
  private GalleryService galleryService;

  @Autowired
  private S3Service s3Service;

  @PostMapping("/upload")
  public String execWrite(GalleryDto galleryDto, MultipartFile files) throws IOException {
    System.out.println(files);
    // for (int i = 0; i < files.length; i++) {
    String imgPath = s3Service.upload(galleryDto.getFilePath(), files);
    galleryDto.setFilePath("https://" + "dixo0q5vi6g16.cloudfront.net" + "/" + imgPath);
    // }
    return galleryService.upload(galleryDto) > 0 ? "OK" : "FAIL";
  }

  @GetMapping("/getImageList")
  public List<GalleryDto> getImageList() {
    return galleryService.getImageList();
  }

  // @PostMapping("/upload")
  // public String upload(MultipartFile file) throws IOException {
  // GalleryDto galleryDto = new GalleryDto();
  // galleryDto.setFilePath(file.getOriginalFilename());
  // System.out.println("들어왔나?");
  // System.out.println(galleryDto.toString());
  // String imgPath = s3Service.upload(galleryDto.getFilePath(), file);
  // galleryDto.setFilePath(imgPath);

  // return galleryService.upload(galleryDto) > 0 ? "OK" : "FAIL";
  // }

}
