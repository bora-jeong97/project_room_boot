package bit.com.a.wishlist;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class WishListService {

    @Autowired
    WishListDao dao;

    public int addlike(WishListDto dto) {
        System.out.println("wishlist service");
        System.out.println(dto.toString());
        return dao.addlike(dto);
    }

    public int deletelike(WishListDto dto) {
        System.out.println("delWishList()");
        return dao.deletelike(dto);
    }

    public List<WishListDto> getWishList(WishListDto dto) {
        System.out.println("getWishList()");
        return dao.getWishList(dto);
    }

    public List<WishListDto> getWishFourList(WishListDto dto) {
        System.out.println("getWishFourList()");
        return dao.getWishFourList(dto);
    }
}