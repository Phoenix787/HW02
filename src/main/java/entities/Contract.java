package entities;

import entities.enums.StatusDocument;
import entities.enums.StatusContract;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "num_reg_doc")
    private String numRegDoc;

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    @OneToMany(mappedBy = "contracts", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Document> documents = new HashSet<>(); //список документов

    @Enumerated(EnumType.ORDINAL)
    private StatusContract flgStatusCon; //сохраняем из enum

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "enum('active','completed','terminated')")
    private StatusDocument flgStatusDoc;

    public Contract() {
    }

    public Contract(String numRegDoc, Date date, Set<Document> documents, StatusContract flgStatusCon, StatusDocument flgStatusDoc) {
        this.numRegDoc = numRegDoc;
        this.date = date;
        this.documents = documents;
        this.flgStatusCon = flgStatusCon;
        this.flgStatusDoc = flgStatusDoc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumRegDoc() {
        return numRegDoc;
    }

    public void setNumRegDoc(String numRegDoc) {
        this.numRegDoc = numRegDoc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public StatusContract getFlgStatusCon() {
        return flgStatusCon;
    }

    public void setFlgStatusCon(StatusContract flgStatusCon) {
        this.flgStatusCon = flgStatusCon;
    }

    public StatusDocument getFlgStatusDoc() {
        return flgStatusDoc;
    }

    public void setFlgStatusDoc(StatusDocument flgStatusDoc) {
        this.flgStatusDoc = flgStatusDoc;
    }

    public void addDocument(Document doc){
        doc.setContract(this);
        getDocuments().add(doc);
    }
}
