package mk.ukim.finki.emt.lab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.lab.model.dto.CreateAuthorDto;
import mk.ukim.finki.emt.lab.model.dto.DisplayAuthorDto;
import mk.ukim.finki.emt.lab.model.dto.UpdateAuthorDto;
import mk.ukim.finki.emt.lab.service.aplication.AuthorApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@Tag(name = "Author Controller", description = "Operations related to authors management")
public class AuthorController {

    private final AuthorApplicationService authorApplicationService;

    public AuthorController(AuthorApplicationService authorApplicationService) {
        this.authorApplicationService = authorApplicationService;
    }

    @GetMapping()
    @Operation(summary = "Get all authors", description = "Retrieves a list of all authors.")
    public List<DisplayAuthorDto> findAll() {
        return authorApplicationService.listAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get author by ID", description = "Retrieves an author by their unique identifier.")
    public ResponseEntity<DisplayAuthorDto> findById(@PathVariable Long id) {
        return authorApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new author", description = "Creates a new author and returns the created entity.")
    public ResponseEntity<DisplayAuthorDto> create(@RequestBody CreateAuthorDto createAuthorDto) {
        return authorApplicationService.create(createAuthorDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Edit an existing author", description = "Updates an existing author's information.")
    public ResponseEntity<DisplayAuthorDto> edit(@PathVariable Long id, @RequestBody UpdateAuthorDto updateAuthorDto) {
        return authorApplicationService.edit(id, updateAuthorDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete an author", description = "Deletes an author by their unique identifier.")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (this.authorApplicationService.findById(id).isPresent()) {
            authorApplicationService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
