package eLibrary.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
public class SubGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Введите название подгруппы")
    private String name;

    @ManyToOne()
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "SubGroup_books",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "subgroup_id")}
    )
    private Set<Book> groupBooks = new HashSet<>();

    public SubGroup() {
    }

    public SubGroup(String name, Set<Book> books) {
        this.name = name;
        groupBooks = books;
    }

    public SubGroup(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Set<Book> getGroupBooks() {
        return groupBooks;
    }

    public void setGroupBooks(Set<Book> groupBooks) {
        this.groupBooks = groupBooks;
    }
}
