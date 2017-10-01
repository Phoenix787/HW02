package services.interfaces;

import services.datasets.UserProfile;

public interface AccountService {
    void addUser(UserProfile user);

    void addSessionId(String sessionId, UserProfile user);

    UserProfile getUserByLogin(String login);

    UserProfile getUserBySession(String sessionId);

    void deleteSession(String sessionId);
}
