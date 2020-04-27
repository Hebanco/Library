package eLibrary.controller;

import eLibrary.domain.Book;
import eLibrary.repos.BookRepo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookRepo bookRepo;

    @Value("${upload.path}")
    private String uploadPath;

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

            uploadFile(book, file);

            model.addAttribute("book", null);

            bookRepo.save(book);
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

        if(file!=null) {
            uploadFile(book, file);
        }

        return "redirect:/book/list";
    }

    private void uploadFile(Book book,MultipartFile file) throws IOException {

        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists()){
            uploadDir.mkdirs();
        }

        String uuidFile = UUID.randomUUID().toString();
        String resultFilename = uuidFile + "." + file.getOriginalFilename();

        file.transferTo(new File(uploadPath+"/"+resultFilename));

        book.setFilename(resultFilename);
    }

    @GetMapping("/download/{filename}")
    @ResponseBody
    public void download(
            @PathVariable("filename") String filename,
            HttpServletResponse response
    ) throws IOException {
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setContentType("application/force-download");

        FileInputStream inputStream = new FileInputStream(uploadPath+"/"+filename);

        IOUtils.copy(inputStream, response.getOutputStream());

        response.flushBuffer();
    }

}
