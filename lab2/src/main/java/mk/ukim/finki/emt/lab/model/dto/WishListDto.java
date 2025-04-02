package mk.ukim.finki.emt.lab.model.dto;

import mk.ukim.finki.emt.lab.model.domain.Wishlist;

import java.time.LocalDateTime;
import java.util.List;

public record WishListDto (Long id,
                               LocalDateTime dateCreated,
                               DisplayUserDto user,
                               List<DisplayBookDto> books){

        public static WishListDto from(Wishlist wishlist) {
            return new WishListDto(
                    wishlist.getId(),
                    wishlist.getDateCreated(),
                    DisplayUserDto.from(wishlist.getUser()),
                    DisplayBookDto.from(wishlist.getBooks())
            );
        }
}
