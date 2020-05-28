package eLibrary.service;

import eLibrary.domain.Book;
import eLibrary.domain.Lesson;
import eLibrary.repos.BookRepo;
import eLibrary.repos.LessonRepo;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ReportService {

    @Value("${upload.report.path}")
    private String reportPath;

    private final LessonRepo lessonRepo;
    private final BookRepo bookRepo;

    public ReportService(LessonRepo lessonRepo, BookRepo bookRepo) {
        this.lessonRepo = lessonRepo;
        this.bookRepo = bookRepo;
    }

    public File reportLessonWithTeacher() throws IOException {
        // создаем модель docx документа,
        // к которой будем прикручивать наполнение (колонтитулы, текст)
        XWPFDocument docxModel = getXwpfDocument();

        File file = new File(reportPath+"/" + "отчетЗанятия.docx");

        FileOutputStream outputStream = new FileOutputStream(file);
        docxModel.write(outputStream);
        outputStream.close();

        return file;
    }

    private XWPFDocument getXwpfDocument() {
        XWPFDocument docxModel = new XWPFDocument();
        CTSectPr ctSectPr = docxModel.getDocument().getBody().addNewSectPr();

        XWPFParagraph bodyParagraph = docxModel.createParagraph();
        bodyParagraph.setAlignment(ParagraphAlignment.BOTH);
        XWPFRun paragraphConfig = bodyParagraph.createRun();
        paragraphConfig.setFontSize(12);
        paragraphConfig.setFontFamily("Times New Roman");
        paragraphConfig.setText(
                getLessonsAndTeacher()
        );

        // сохраняем модель docx документа в файл
        File uploadDir = new File(reportPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        return docxModel;
    }

    private String getLessonsAndTeacher(){
        StringBuffer result = new StringBuffer();
        List<Lesson> lessons = lessonRepo.findAll();
        for (Lesson lesson: lessons) {
            result.append(lesson.getName())
            .append(" ")
            .append(lesson.getTeacher().getFio())
            .append("\n");
        }
        return String.valueOf(result);
    }

    public File reportAllBook() throws IOException {
        // создаем модель docx документа,
        // к которой будем прикручивать наполнение (колонтитулы, текст)
        XWPFDocument docxModel = new XWPFDocument();
        CTSectPr ctSectPr = docxModel.getDocument().getBody().addNewSectPr();

        XWPFParagraph bodyParagraph = docxModel.createParagraph();
        bodyParagraph.setAlignment(ParagraphAlignment.BOTH);
        XWPFRun paragraphConfig = bodyParagraph.createRun();
        paragraphConfig.setFontSize(12);
        paragraphConfig.setFontFamily("Times New Roman");
        paragraphConfig.setText(
                getBooks()
        );

        // сохраняем модель docx документа в файл
        File uploadDir = new File(reportPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        File file = new File(reportPath+"/"+ "отчетКниги.docx");

        FileOutputStream outputStream = new FileOutputStream(file);
        docxModel.write(outputStream);
        outputStream.close();

        return file;
    }

    private String getBooks(){
        StringBuffer result = new StringBuffer();
        List<Book> books = (List<Book>) bookRepo.findAll();
        for (Book book: books) {
            result.append(book.getName())
                    .append(" ")
                    .append(book.getAuthor())
                    .append("\n");
        }
        return String.valueOf(result);
    }

    public File reportAllFiles() throws IOException {
        XWPFDocument docxModel = new XWPFDocument();
        CTSectPr ctSectPr = docxModel.getDocument().getBody().addNewSectPr();

        XWPFParagraph bodyParagraph = docxModel.createParagraph();
        bodyParagraph.setAlignment(ParagraphAlignment.BOTH);
        XWPFRun paragraphConfig = bodyParagraph.createRun();
        paragraphConfig.setFontSize(12);
        paragraphConfig.setFontFamily("Times New Roman");
        paragraphConfig.setText(
                getFiles()
        );

        // сохраняем модель docx документа в файл
        File uploadDir = new File(reportPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        File file = new File(reportPath+"/"+ "отчетФайлы.docx");

        FileOutputStream outputStream = new FileOutputStream(file);
        docxModel.write(outputStream);
        outputStream.close();

        return file;
    }

    private String getFiles() {
        StringBuffer result = new StringBuffer();
        List<Book> books = (List<Book>) bookRepo.findAll();
        for (Book book: books) {
            result.append(book.getName())
                    .append(" ")
                    .append(book.getAuthor())
                    .append(" ")
                    .append(book.getFilename())
                    .append(" ")
                    .append(book.getImageName())
                    .append("\n");
        }
        return String.valueOf(result);
    }
}
