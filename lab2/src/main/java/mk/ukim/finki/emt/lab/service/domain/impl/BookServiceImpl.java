package mk.ukim.finki.emt.lab.service.domain.impl;

import mk.ukim.finki.emt.lab.model.domain.Author;
import mk.ukim.finki.emt.lab.model.domain.Book;
import mk.ukim.finki.emt.lab.model.enums.Category;
import mk.ukim.finki.emt.lab.model.dto.BookDto;
import mk.ukim.finki.emt.lab.model.exceptions.InvalidBookIdException;
import mk.ukim.finki.emt.lab.repository.BookRepository;
import mk.ukim.finki.emt.lab.service.domain.AuthorService;
import mk.ukim.finki.emt.lab.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> listAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(this.bookRepository.findById(id).orElseThrow(InvalidBookIdException::new));
    }

    @Override
    public Book create(String name, Category category, Long id, Long availableCopies) {
        Optional<Author> author = this.authorService.findById(id);
        Book book = new Book(name, category, author.orElse(null), availableCopies);
        return this.bookRepository.save(book);
    }

    @Override
    public Book edit(Long id, String name, Category category, Long authorId, Long availableCopies) {
        Book book = this.bookRepository.findById(id).orElseThrow(InvalidBookIdException::new);
        Author author = this.authorService.findById(authorId).orElse(null);
        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);

        return this.bookRepository.save(book);
    }

    @Override
    public Book delete(Long id) {
        return this.bookRepository.findById(id).orElseThrow(InvalidBookIdException::new);
    }

    @Override
    public Optional<Book> isRented(Book book) {
//        Book book = this.bookRepository.findById(id).orElseThrow(InvalidBookIdException::new);
        if (book.getAvailableCopies() > 0) {
            book.setAvailableCopies(book.getAvailableCopies()-1);
            return Optional.of(this.bookRepository.save(book));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Book> create(BookDto book) {
        Author author = this.authorService.findById(book.getAuthor()).orElse(null);
        if (book.getName()!=null && book.getCategory()!=null
        && book.getAuthor()!=null  && book.getAvailableCopies() != null) {
            return Optional.of(this.bookRepository.save(new Book(book.getName(), book.getCategory(), author, book.getAvailableCopies())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Book> create(Book book) {
        Author author = this.authorService.findById(book.getAuthor().getId()).orElse(null);
        if (book.getAuthor() != null && author != null ) {
            return Optional.of(bookRepository.save(new Book(
                    book.getName(), book.getCategory(), author, book.getAvailableCopies()
            )));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Book> edit(Long id, Book book) {
        Author author = this.authorService.findById(book.getAuthor().getId()).orElse(null);
        return bookRepository.findById(id).map(
                existingBook ->{
                    if (book.getName()!=null){
                        existingBook.setName(book.getName());
                    }
                    if (book.getCategory()!=null){
                        existingBook.setCategory(book.getCategory());
                    }
                    if (book.getAuthor()!=null){
                        existingBook.setAuthor(author);
                    }
                    if (book.getAvailableCopies()!=null){
                        existingBook.setAvailableCopies(book.getAvailableCopies());
                    }
                    return bookRepository.save(existingBook);
                });

    }

    @Override
    public Optional<Book> edit(Long id, BookDto book) {
        Book existingBook = this.bookRepository.findById(id).orElseThrow(InvalidBookIdException::new);
        Author author = this.authorService.findById(book.getAuthor()).orElse(null);
        if (book.getName()!=null){
            existingBook.setName(book.getName());
        }
        if (book.getCategory()!=null){
            existingBook.setCategory(book.getCategory());
        }
        if (book.getAuthor()!=null){
            existingBook.setAuthor(author);
        }
        if (book.getAvailableCopies()!=null){
            existingBook.setAvailableCopies(book.getAvailableCopies());
        }
        return Optional.of(bookRepository.save(existingBook));

    }



}
