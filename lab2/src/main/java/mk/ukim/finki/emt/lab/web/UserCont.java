package mk.ukim.finki.emt.lab.web;

import mk.ukim.finki.emt.lab.model.domain.Book;
import mk.ukim.finki.emt.lab.service.domain.UserServ;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserCont {

    private final UserServ userServ;

    public UserCont(UserServ userServ) {
        this.userServ = userServ;
    }

    @PostMapping("/{username}/wishlist/add/{bookId}")
    public ResponseEntity<String> addToWishlist(@PathVariable String username, @PathVariable Long bookId) {
        return ResponseEntity.ok(userServ.addToWishlist(username, bookId));
    }

    @GetMapping("/{username}/wishlist")
    public ResponseEntity<List<Book>> viewWishlist(@PathVariable String username) {
        return ResponseEntity.ok(userServ.getWishlist(username));
    }

    @PostMapping("/{username}/wishlist/rent")
    public ResponseEntity<String> rentAllFromWishlist(@PathVariable String username) {
        return ResponseEntity.ok(userServ.rentAllFromWishlist(username));
    }
}
