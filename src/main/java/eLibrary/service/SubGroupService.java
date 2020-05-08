package eLibrary.service;

import eLibrary.domain.Book;
import eLibrary.domain.SubGroup;
import eLibrary.repos.BookRepo;
import eLibrary.repos.SubGroupRepo;
import org.springframework.stereotype.Service;

@Service
public class SubGroupService {
    private final SubGroupRepo subGroupRepo;
    private final BookRepo bookRepo;

    public SubGroupService(SubGroupRepo subGroupRepo, BookRepo bookRepo) {
        this.subGroupRepo = subGroupRepo;
        this.bookRepo = bookRepo;
    }

    public Iterable<Book> getBooks(String filter) {
        Iterable<Book> possibleBooks;
        if(filter.isEmpty()){
            possibleBooks = bookRepo.findFiveByRandom();
        } else{
            possibleBooks = bookRepo.findByNameLike(filter);
        }
        return possibleBooks;
    }

    public void renameSubGroup(String name, SubGroup subGroup) {
        subGroup.setName(name);
        subGroupRepo.save(subGroup);
    }

    public void removeBook(SubGroup subGroup, Book book) {
        subGroup.getGroupBooks().remove(book);
        subGroupRepo.save(subGroup);
    }

    public void addBook( SubGroup subGroup, Book book) {
        subGroup.getGroupBooks().add(book);
        subGroupRepo.save(subGroup);
    }
}
