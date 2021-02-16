package com.eamosse.gmail.gestion.myapplication.dto;

/**
 * Classe modélisant un article dans l'application
 * TODO Compléter le modèle pour prendre en compte les données suivantes : URL, URL de l'image, source, date de publication
 */
public class Article {
    String title;
    String description;
    String author;

    public Article(String title, String description, String author) {
        this.title = title;
        this.description = description;
        this.author = author;
    }
}
