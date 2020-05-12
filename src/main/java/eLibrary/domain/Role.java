package eLibrary.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    TEACHER("Преподаватель"),
    LIBRARIAN("Библиотекарь"),
    USER_OVERSEER("Следящий за пользователями"),
    LESSON_OVERSEER("Следящий за занятиями"),
    ADMIN("Администратор");

    private String rusName;
    Role(String rusName){
        this.rusName = rusName;
    }

    public String getRusName(){
        return rusName;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
