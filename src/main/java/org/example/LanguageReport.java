package org.example;

public class LanguageReport {
    private String language;
    private long speakers;
    private double worldPercentage;

    public LanguageReport() {
    }

    public LanguageReport(String language, long speakers, double worldPercentage) {
        this.language = language;
        this.speakers = speakers;
        this.worldPercentage = worldPercentage;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public long getSpeakers() {
        return speakers;
    }

    public void setSpeakers(long speakers) {
        this.speakers = speakers;
    }

    public double getWorldPercentage() {
        return worldPercentage;
    }

    public void setWorldPercentage(double worldPercentage) {
        this.worldPercentage = worldPercentage;
    }
}