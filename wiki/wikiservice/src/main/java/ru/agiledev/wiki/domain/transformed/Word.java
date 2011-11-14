package ru.agiledev.wiki.domain.transformed;

import ru.agiledev.wiki.domain.attribute.PartOfSpeech;

import javax.persistence.*;

/**
 * Word Entity
 * Contains all necessary information for word analysis: part of speech, characteristics, list of synonymous words etc.
 *
 * Created by a.kirillov
 * Date: 10/3/11 Time: 9:42 PM
 */
@Entity
@Table(name="word")
public class Word {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
    Integer id;

    @Column(name = "word")
    String word;        //word value

    @Enumerated(EnumType.STRING)
    @Column(name = "partOfSpeech", columnDefinition="TEXT")
    PartOfSpeech partOfSpeech;//part of speech

    @Column(name = "meaning", columnDefinition="TEXT")
    String meaning;     //word meaning

    @Column(name = "properties", columnDefinition="TEXT")
    String properties;     //word morphological properties

    @Column(name = "synonyms", columnDefinition="TEXT")
    String synonyms;    //string of synonyms divided by pipe (|)

    @Column(name = "antonyms", columnDefinition="TEXT")
    String antonyms;    //string of antonyms divided by pipe (|)

    @Column(name = "hyponyms", columnDefinition="TEXT")
    String hyponyms;    //string of hyponyms divided by pipe (|)

    @Column(name = "hypernyms", columnDefinition="TEXT")
    String hypernyms;    //string of hypernyms divided by pipe (|)

    public Word(){};

    public Word(String word, PartOfSpeech partOfSpeech, String meaning) {
        this.word = word;
        this.partOfSpeech = partOfSpeech;
        this.meaning = meaning;
    }


    //========== getters & setters ===================

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public PartOfSpeech getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(PartOfSpeech partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }

    public String getAntonyms() {
        return antonyms;
    }

    public void setAntonyms(String antonyms) {
        this.antonyms = antonyms;
    }

    public String getHyponyms() {
        return hyponyms;
    }

    public void setHyponyms(String hyponyms) {
        this.hyponyms = hyponyms;
    }

    public String getHypernyms() {
        return hypernyms;
    }

    public void setHypernyms(String hypernyms) {
        this.hypernyms = hypernyms;
    }
}
