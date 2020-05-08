package eLibrary.controller;

import eLibrary.domain.Book;
import eLibrary.service.BookService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    @GetMapping("/list")
    public String main(
            Model model,
            @RequestParam(required = false, defaultValue = "") String filter
    ){

        Iterable<Book> books = bookService.getBooks(filter);

        model.addAttribute("books", books);
        model.addAttribute("filter", filter);

        return "bookList";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
    @GetMapping("/new")
    public String addBook(Model model){
        return "bookCreate";
    }

    @PostMapping("/new")
    public String add(
            @Valid Book book,
            BindingResult bindingResult,
            @RequestParam("file") MultipartFile file,
            Model model
    ) throws IOException {
        if(bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("book", book);
        }else {
            if(file.getBytes().length>0) {
                bookService.uploadFile(book, file);
            }

            model.addAttribute("book", null);
        }
        return "bookCreate";
    }

    @GetMapping("/{book}")
    public String bookEditForm(@PathVariable Book book, Model model){
        model.addAttribute("book", book);
        return "bookEdit";
    }

    @PostMapping
    public String bookSave(
            @RequestParam String name,
            @RequestParam String author,
            @RequestParam String description,
            @RequestParam("bookId") Book book,
            @RequestParam(required = false, defaultValue = "", name = "file") MultipartFile file
    ) throws IOException {
        book.setName(name);
        book.setAuthor(author);
        book.setDescriptions(description);

        if(file.getBytes().length>0) {
            bookService.uploadFile(book, file);
        }

        return "redirect:/book/list";
    }

    @GetMapping("/download/{filename}")
    @ResponseBody
    public void download(
            @PathVariable("filename") String filename,
            HttpServletResponse response
    ) throws IOException {
        bookService.downloadFile(filename, response);
    }
}
