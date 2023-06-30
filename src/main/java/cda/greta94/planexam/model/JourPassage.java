package cda.greta94.planexam.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class JourPassage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Date datePassage;

    @ManyToOne
    @JoinColumn(name = "session_e_5_id")
    private SessionE5 sessionE5;

    public SessionE5 getSessionE5() {
        return sessionE5;
    }

    public void setSessionE5(SessionE5 sessionE5) {
        this.sessionE5 = sessionE5;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatePassage() {
        return datePassage;
    }

    public void setDatePassage(Date datePassage) {
        this.datePassage = datePassage;
    }
}
