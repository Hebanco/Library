package eLibrary.service;

import eLibrary.domain.Lesson;
import eLibrary.domain.SubGroup;
import eLibrary.domain.User;
import eLibrary.repos.LessonRepo;
import eLibrary.repos.SubGroupRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class LessonService {

    private final LessonRepo lessonRepo;
    private final SubGroupRepo subGroupRepo;

    public LessonService(LessonRepo lessonRepo, SubGroupRepo subGroupRepo) {
        this.lessonRepo = lessonRepo;
        this.subGroupRepo = subGroupRepo;
    }

    public void saveLesson(Lesson lesson, User teacher) {
        lesson.setTeacher(teacher);
        lessonRepo.save(lesson);
    }

    public Set<Lesson> getByTeacher(User teacher) {
        return lessonRepo.findByTeacher(teacher);
    }

    public List<Lesson> getAll() {
        return lessonRepo.findAll();
    }

    public void addSubGroup(SubGroup subGroup, Lesson lesson) {
        subGroupRepo.save(subGroup);
        lesson.getSubGroups().add(subGroup);
        lessonRepo.save(lesson);
    }

    public void removeSubGroup(Lesson lesson, SubGroup subGroup) {
        lesson.getSubGroups().remove(subGroup);
        lessonRepo.save(lesson);
    }
}
