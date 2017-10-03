package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;
    @Column(name = "url")
    private String url;

    public Document() {
    }

    public Document(Contract Contract, String url) {
        this.contract = Contract;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(getId(), document.getId()) &&
                Objects.equals(getContract(), document.getContract()) &&
                Objects.equals(getUrl(), document.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getContract(), getUrl());
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", contract=" + contract +
                ", url='" + url + '\'' +
                '}';
    }
}
