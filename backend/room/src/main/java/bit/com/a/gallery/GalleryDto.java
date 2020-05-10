package bit.com.a.gallery;

public class GalleryDto {
  private Long id;
  private String title;
  private String filePath;
  private String imgFullPath;

  public GalleryDto() {

  }

  public GalleryDto(Long id, String title, String filePath, String imgFullPath) {
    this.id = id;
    this.title = title;
    this.filePath = filePath;
    this.imgFullPath = imgFullPath;
  }

  @Override
  public String toString() {
    return "{" + " id='" + id + "'" + ", title='" + title + "'" + ", filePath='" + filePath + "'" + ", imgFullPath='"
        + imgFullPath + "'" + "}";
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getFilePath() {
    return this.filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public String getImgFullPath() {
    return this.imgFullPath;
  }

  public void setImgFullPath(String imgFullPath) {
    this.imgFullPath = imgFullPath;
  }

}