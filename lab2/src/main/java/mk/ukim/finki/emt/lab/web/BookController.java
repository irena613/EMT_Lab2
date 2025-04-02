package mk.ukim.finki.emt.lab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.lab.model.dto.CreateBookDto;
import mk.ukim.finki.emt.lab.model.dto.DisplayBookDto;
import mk.ukim.finki.emt.lab.model.dto.UpdateBookDto;
import mk.ukim.finki.emt.lab.service.aplication.BookApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Book Controller", description = "Operations related to book management")
public class BookController {

    private final BookApplicationService bookApplicationService;

    public BookController(BookApplicationService bookApplicationService) {
        this.bookApplicationService = bookApplicationService;
    }

    @GetMapping
    @Operation(summary = "Get all books", description = "Retrieves a list of all books.")
    public List<DisplayBookDto> findAll() {
        return bookApplicationService.listAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by ID", description = "Retrieves a book by its unique identifier.")
    public ResponseEntity<DisplayBookDto> findById(@PathVariable Long id) {
        return bookApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @Operation(summary = "Add a new book", description = "Creates a new book and returns the created entity.")
    public ResponseEntity<DisplayBookDto> create(@RequestBody CreateBookDto createBookDto) {
        return bookApplicationService.create(createBookDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @Operation(summary = "Edit an existing book", description = "Updates an existing book's information.")
    public ResponseEntity<DisplayBookDto> update(@PathVariable Long id, @RequestBody UpdateBookDto bookDto) {
        return bookApplicationService.edit(id, bookDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @Operation(summary = "Delete a book", description = "Deletes a book by its unique identifier.")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (bookApplicationService.findById(id).isPresent()) {
            bookApplicationService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/rentOut/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @Operation(summary = "Rent out a book", description = "Marks a book as rented and returns the updated book information.")
    public ResponseEntity<DisplayBookDto> rentBook(@PathVariable Long id) {
        return bookApplicationService.isRented(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
