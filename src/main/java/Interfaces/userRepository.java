package Interfaces;

import java.util.List;

public interface userRepository <T> {
    List<T> find_password_mail (String mal, String password);
    Long getIdByPassword(String password);
    Long getIdByCookie(String cookie);
    String getNameByCookie(String cookie);

}
