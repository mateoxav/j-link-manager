package com.mateoxav.jlinkmanager.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Link {
    private String title;
    private String url;
    private String category;
    private String notes;

    public Link() {}

    public Link(String title, String url, String category, String notes) {
        this.title = title;
        this.url = url;
        this.category = category;
        this.notes = notes;
    }

    public String getTitle() { return title; }
    public String getUrl() { return url; }
    public String getCategory() { return category; }
    public String getNotes() { return notes; }

    public void setTitle(String title) { this.title = title; }
    public void setUrl(String url) { this.url = url; }
    public void setCategory(String category) { this.category = category; }
    public void setNotes(String notes) { this.notes = notes; }

}
