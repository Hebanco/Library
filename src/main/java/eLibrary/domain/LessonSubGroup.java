package eLibrary.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class LessonSubGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "SubGroup_books",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "subgroup_id")}
    )
    private Set<Book> groupBooks = new HashSet<>();

    public LessonSubGroup() {
    }

    public LessonSubGroup(String name, Set<Book> books) {
        this.name = name;
        groupBooks = books;
    }

    public LessonSubGroup(String name) {
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

    public Set<Book> getGroupsBook() {
        return groupBooks;
    }

    public void setGroupsBook(Set<Book> groupBooks) {
        this.groupBooks = groupBooks;
    }
}
