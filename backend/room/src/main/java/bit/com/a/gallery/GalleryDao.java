package bit.com.a.gallery;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GalleryDao {
  public int upload(GalleryDto galleryDto);

  public List<GalleryDto> getImageList();

}