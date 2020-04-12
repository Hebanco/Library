package eLibrary.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User teacher;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private Set<LessonSubGroup> subGroups = new HashSet<>();

    public Lesson() {
    }

    public Lesson(String name, User teacher, Set<LessonSubGroup> subGroups) {
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

    public Set<LessonSubGroup> getSubGroups() {
        return subGroups;
    }

    public void setSubGroups(Set<LessonSubGroup> subGroups) {
        this.subGroups = subGroups;
    }
}
