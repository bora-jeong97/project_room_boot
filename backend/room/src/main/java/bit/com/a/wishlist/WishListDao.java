package bit.com.a.wishlist;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface WishListDao {

    public int addlike(WishListDto dto);

    public int deletelike(WishListDto dto);

    public List<WishListDto> getWishList(WishListDto dto);

    public List<WishListDto> getWishFourList(WishListDto dto);
}