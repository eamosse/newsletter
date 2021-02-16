package com.eamosse.gmail.gestion.myapplication.repositories;

import com.eamosse.gmail.gestion.myapplication.dto.Article;

import java.io.IOException;
import java.util.List;

/**
 * Interface définissant les méthodes publiques du repository
 */
public interface IArticleRepository {
    List<Article> getArticles(String country) throws IOException;

    Article getArticle(String id);
}
