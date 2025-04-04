//package mk.ukim.finki.emt.lab.service.domain.impl;
//
//import mk.ukim.finki.emt.lab.model.domain.Book;
//import mk.ukim.finki.emt.lab.model.domain.User;
//import mk.ukim.finki.emt.lab.repository.UserRepo;
//import mk.ukim.finki.emt.lab.service.domain.BookService;
//import mk.ukim.finki.emt.lab.service.domain.UserServ;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserServImpl implements UserServ {
//
//    private final UserRepo userRepo;
//    private final BookService bookService;
//
//    public UserServImpl(UserRepo userRepo, BookService bookService) {
//        this.userRepo = userRepo;
//        this.bookService = bookService;
//    }
//
//
//    @Override
//    public String addToWishlist(String username, Long bookId) {
//        Book book = bookService.findById(bookId).get();
//        User user = userRepo.findByUsername(username);
//        if(user.getWishlist().contains(book)) {
//            return "Book is already in Wishlist";
//        }
//        if (book.getAvailableCopies()>=1){
//            user.getWishlist().add(book);
//        }
//
//        userRepo.save(user);
//        return "Book added to Wishlist";
//    }
//
//    @Override
//    public List<Book> getWishlist(String username) {
//        return userRepo.findByUsername(username).getWishlist().stream().toList();
//    }
//
//    @Override
//    public String rentAllFromWishlist(String username) {
//        User user = userRepo.findByUsername(username);
//        for (Book book : user.getWishlist()) {
//            if (book.getAvailableCopies()>=1) {
//                bookService.isRented(book);
//            }
//        }
//        return "";
//    }
//}
