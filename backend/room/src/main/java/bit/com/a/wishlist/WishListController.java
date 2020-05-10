package bit.com.a.wishlist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/wishlist")
public class WishListController {

    @Autowired
    WishListService service;

    @GetMapping("/addLike")
    public int addlike(WishListDto dto) {
        System.out.println("Wishlist Controller");
        System.out.println(dto.toString());
        return service.addlike(dto);
    }

    @GetMapping("/delWishList")
    public int deletelike(WishListDto dto) {
        System.out.println("delWishList()");
        return service.deletelike(dto);
    }

    @GetMapping("/getWishList")
    public List<WishListDto> getWishList(WishListDto dto) {
        System.out.println("getWishList()");
        return service.getWishList(dto);
    }

    @GetMapping("/getWishFourList")
    public List<WishListDto> getWishFourList(WishListDto dto) {
        System.out.println("getWishFourList()");
        return service.getWishFourList(dto);
    }

}