package ru.agiledev.wiki.domain.transformed;

import javax.persistence.*;

/**
 * Created by a.kirillov
 * Date: 11/10/11 Time: 10:49 PM
 */
@Entity
@Table(name="stem")
public class Stem {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
    Integer id;

    @Column(name = "value", columnDefinition="TEXT")
    String value; //initial word form

    @Column(name = "stems", columnDefinition="TEXT")
    String stems; //string of base forms divided by pipe (|)

    public Stem(){};

    public Stem(String value, String stems) {
        this.value = value;
        this.stems = stems;
    }

    //-------------------- getters and setters --------------------
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

    public String getStems() {
        return stems;
    }

    public void setStems(String stems) {
        this.stems = stems;
    }
}
