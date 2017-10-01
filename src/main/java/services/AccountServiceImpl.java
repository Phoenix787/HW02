package services;

import services.datasets.UserProfile;
import services.interfaces.AccountService;

import java.util.HashMap;
import java.util.Map;

public class AccountServiceImpl implements AccountService {
   public  Map<String, UserProfile> loginToProfile;
   public Map<String, UserProfile> sessionToProfile;

    public AccountServiceImpl() {
        loginToProfile = new HashMap<>();
        sessionToProfile = new HashMap<>();
    }

    @Override
    public void addUser(UserProfile user) {
        loginToProfile.put(user.getLogin(), user);
    }

    @Override
    public void addSessionId(String sessionId, UserProfile user) {
        sessionToProfile.put(sessionId, user);

    }

    @Override
    public UserProfile getUserByLogin(String login) {

        return loginToProfile.get(login);
    }

    @Override
    public UserProfile getUserBySession(String sessionId) {
        return sessionToProfile.get(sessionId);
    }


    @Override
    public void deleteSession(String sessionId) {

        sessionToProfile.remove(sessionId);
    }
}
