package eLibrary.controller;

import eLibrary.domain.Book;
import eLibrary.domain.Role;
import eLibrary.domain.User;
import eLibrary.repos.BookRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookRepo bookRepo;

    public BookController(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping("/list")
    public String main(
            Model model,
            @RequestParam(required = false, defaultValue = "") String filter
    ){

        Iterable<Book> books;

        if(!filter.isEmpty()) {
            books = bookRepo.findByName(filter);
        } else {
            books = bookRepo.findAll();
        }

        model.addAttribute("books", books);
        model.addAttribute("filter", filter);

        return "bookList";
    }

    @GetMapping("/new")
    public String addBook(Model model){
        return "bookCreate";
    }

    @PostMapping("/new")
    public String add(
            @RequestParam String name,
            @RequestParam String author,
            @RequestParam String description,
            Model model
    ){
        Book book = new Book(name, author, description);
        bookRepo.save(book);

        return "redirect:/book/list";
    }

    @GetMapping("{book}")
    public String bookEditForm(@PathVariable Book book, Model model){
        model.addAttribute("book", book);
        return "bookProfile";
    }

    @PostMapping
    public String bookSave(
            @RequestParam String name,
            @RequestParam String author,
            @RequestParam String description,
            @RequestParam("bookId") Book book
    ){
        book.setName(name);
        book.setAuthor(author);
        book.setDescriptions(description);

        bookRepo.save(book);

        return "redirect:/book/list";
    }

}
