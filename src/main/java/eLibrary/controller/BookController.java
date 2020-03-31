package eLibrary.controller;

import eLibrary.domain.Book;
import eLibrary.repos.BookRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    private final BookRepo bookRepo;

    public BookController(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping("bookList")
    public String main(Model model){

        Iterable<Book> books = bookRepo.findAll();
        model.addAttribute("books", books);

        return "bookList";
    }

    @GetMapping("newBook")
    public String addBook(Model model){
        return "bookProfile";
    }

    @PostMapping("newBook")
    public String add(
            @RequestParam String name,
            @RequestParam String author,
            @RequestParam String description,
            Model model
    ){
        Book book = new Book(name, author, description);
        bookRepo.save(book);

        return "redirect:/bookList";
    }

    @PostMapping("filter")
    public String filter(
            @RequestParam String filter,
            Model model
    ){
        Iterable<Book> books;

        if(!filter.isEmpty()) {
            books = bookRepo.findByName(filter);
        } else {
            books = bookRepo.findAll();
        }
        model.addAttribute("books",books);

        return "bookList";
    }
}
