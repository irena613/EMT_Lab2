package mk.ukim.finki.emt.lab.service.domain;

import mk.ukim.finki.emt.lab.model.domain.Book;

import java.util.List;

public interface UserServ {
    //koj funkcionalnosti treba da gi ima
    // Имплементација на wishlist функционалност
    //овозможете корисниците да додаваат книги во својата листа на желби и да ја прегледуваат пред изнајмување
    //корисникот може да додаде книга во листата само ако има достапни копии
    //ако корисникот се обиде да додаде книга без достапни копии, прикажете соодветна порака
    //oвозможете корисникот да ги изнајми сите книги од листата на желби со една акција, при што бројот на достапни копии ќе се намали за секоја изнајмена книга

    String addToWishlist(String username, Long bookId);

    List<Book> getWishlist(String username);

    String rentAllFromWishlist(String username);
}
