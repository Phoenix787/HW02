package services.interfaces;

import com.sun.istack.internal.Nullable;
import services.datasets.UserDataSet;
import services.datasets.UserProfile;

public interface DBService {
    long addUser(String name, String password);
    long addUser(UserDataSet user);

    void addSessionId(String sessionId, UserDataSet user);

    long getId(String name);

    UserDataSet getUserById(long id);

    UserDataSet getUserByLogin(String login);

    UserDataSet getUserBySession(String sessionId);

    void deleteSession(String sessionId);

    void printConnectionInfo();
}
