package eLibrary.service;

import eLibrary.domain.Book;
import eLibrary.repos.BookRepo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class BookService {
    @Value("${upload.path}")
    private String uploadPath;

    private final BookRepo bookRepo;

    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public void uploadFile(Book book, MultipartFile file) throws IOException {

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String uuidFile = UUID.randomUUID().toString();
        String resultFilename = uuidFile + "." + file.getOriginalFilename();

        file.transferTo(new File(uploadPath + "/" + resultFilename));

        book.setFilename(resultFilename);

        bookRepo.save(book);

    }

    public void downloadFile(String filename, HttpServletResponse response) throws IOException {
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setContentType("application/force-download");

        FileInputStream inputStream = new FileInputStream(uploadPath+"/"+filename);

        IOUtils.copy(inputStream, response.getOutputStream());

        response.flushBuffer();
    }

    public Iterable<Book> getBooks(String filter) {
        Iterable<Book> books;
        if(!filter.isEmpty()) {
            books = bookRepo.findByName(filter);
        } else {
            books = bookRepo.findAll();
        }
        return books;
    }
}
