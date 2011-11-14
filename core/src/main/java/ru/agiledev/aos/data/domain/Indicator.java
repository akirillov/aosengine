package ru.agiledev.aos.data.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Bean for representing words - indicators for certain types of questions
 * Created by a.kirillov
 */

@Entity
@Table(name="indicator")
public class Indicator {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
    Integer id;


    @Column(name = "value")
    String value;

    @Enumerated(EnumType.STRING)
    @Column(name = "q_type", columnDefinition="TEXT")                     //TODO: refactor!
    QType qType;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition="TEXT")
    WordTypes wordType;

    public Indicator() {}

    public Indicator(String value, QType qType, WordTypes wordType) {
        this.value = value;
        this.qType = qType;
        this.wordType = wordType;
    }

    public Indicator(String value, QType qType) {
        this.value = value;
        this.qType = qType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public QType getqType() {
        return qType;
    }

    public void setqType(QType qType) {
        this.qType = qType;
    }

    public WordTypes getWordType() {
        return wordType;
    }

    public void setWordType(WordTypes wordType) {
        this.wordType = wordType;
    }
}
