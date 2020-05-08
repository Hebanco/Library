package eLibrary.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Введите название урока")
    private String name;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User teacher;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private Set<SubGroup> subGroups = new HashSet<>();

    public Lesson() {
    }

    public Lesson(String name, User teacher, Set<SubGroup> subGroups) {
        this.name = name;
        this.teacher = teacher;
        this.subGroups = subGroups;
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

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public Set<SubGroup> getSubGroups() {
        return subGroups;
    }

    public void setSubGroups(Set<SubGroup> subGroups) {
        this.subGroups = subGroups;
    }

}
