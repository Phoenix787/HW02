package services.datasets;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sessions")
public class Sessions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "keysession")
    private String keySession;
    @Column(name = "id_user")
    private long idUser;

    protected Sessions() {
    }

    public Sessions(String keySession, long idUser) {
        this.keySession = keySession;
        this.idUser = idUser;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getKeySession() {
        return keySession;
    }

    public void setKeySession(String keySession) {
        this.keySession = keySession;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sessions sessions = (Sessions) o;
        return getId() == sessions.getId() &&
                getIdUser() == sessions.getIdUser() &&
                Objects.equals(getKeySession(), sessions.getKeySession());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getKeySession(), getIdUser());
    }

    @Override
    public String toString() {
        return "Sessions{" +
                "id=" + id +
                ", keySession='" + keySession + '\'' +
                ", idUser=" + idUser +
                '}';
    }
}
