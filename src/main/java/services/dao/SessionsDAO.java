package services.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import services.datasets.Sessions;
import services.datasets.UserDataSet;

public class SessionsDAO {
    private Session session;

    public SessionsDAO(Session session) {
        this.session = session;
    }

    public long insertSession(String key, long idUser) {
        return (Long)session.save(new Sessions(key, idUser));
    }


    public Sessions get(long id) {
        return (Sessions)session.get(Sessions.class, id);
    }

    public long getUserId(String keySession) {
        long idUser = 0;
        Criteria criteria = session.createCriteria(Sessions.class);
        //todo здесь нажно как-то проверить что Sessions возращается null
        Sessions sessions = (Sessions)criteria.add(Restrictions.eq("keySession", keySession)).uniqueResult();
        if (sessions != null) {
            idUser = sessions.getIdUser();
        }
        return idUser;
    }

    public long getSessionId(String keySession) {
        Criteria criteria = session.createCriteria(Sessions.class);
        return ((Sessions)criteria.add(Restrictions.eq("keySession", keySession)).uniqueResult()).getId();
    }

    public void delete(String sessionKey) {
        long id = getSessionId(sessionKey);
        Sessions sessions = get(id);
        session.delete(sessions);
    }
}
