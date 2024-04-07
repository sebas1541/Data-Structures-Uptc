package co.edu.uptc.models;

public class Word {

    private String spanish;
    private String english;
    private String french;

    public Word(String spanish, String english, String french) {
        this.spanish = spanish;
        this.english = english;
        this.french = french;
    }

    public String getSpanish() {
        return spanish;
    }

    public void setSpanish(String spanish) {
        this.spanish = spanish;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getFrench() {
        return french;
    }

    public void setFrench(String french) {
        this.french = french;
    }
}
