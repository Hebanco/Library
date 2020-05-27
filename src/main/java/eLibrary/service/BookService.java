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
    @Value("${upload.file.path}")
    private String uploadFilePath;
    @Value("${upload.image.path}")
    private String uploadImagePath;

    private final BookRepo bookRepo;

    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public void uploadFile(Book book, MultipartFile file, MultipartFile image) throws IOException {

        if(!file.getOriginalFilename().isEmpty()) {
            String resultFilename = createFile(file, uploadFilePath);
            book.setFilename(resultFilename);
        }

        if(!image.getOriginalFilename().isEmpty()) {
            String resultFilename = createFile(image, uploadImagePath);
            book.setImageName(resultFilename);
        }

        bookRepo.save(book);
    }

    private String createFile(MultipartFile file, String uploadPath) throws IOException {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String uuidFile = UUID.randomUUID().toString();
        String resultFilename = uuidFile + "." + file.getOriginalFilename();

        file.transferTo(new File(uploadPath + "/" + resultFilename));

        return resultFilename;
    }

    public void downloadFile(String filename, HttpServletResponse response) throws IOException {
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setContentType("application/force-download");

        FileInputStream inputStream = new FileInputStream(uploadFilePath+"/"+filename);

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

    public void saveBook(Book book){
        bookRepo.save(book);
    }

    public void removeBook(Book book) {
        removeBookFile(book);
        removeBookImage(book);
        bookRepo.delete(book);
    }

    public void removeBookFile(Book book) {
        if(book.getFilename()!= null && !book.getFilename().isEmpty()) {
            File file = new File(uploadFilePath + book.getFilename());
            file.delete();
            book.setFilename(null);
            bookRepo.save(book);
        }
    }

    public void removeBookImage(Book book) {
        if(book.getImageName()!= null && !book.getImageName().isEmpty()) {
            File file = new File(uploadImagePath + book.getImageName());
            file.delete();
            book.setImageName(null);
            bookRepo.save(book);
        }
    }
}
