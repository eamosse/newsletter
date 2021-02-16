package com.eamosse.gmail.gestion.myapplication.repositories;

import com.eamosse.gmail.gestion.myapplication.datasources.ArticleDataSource;
import com.eamosse.gmail.gestion.myapplication.datasources.models.ArticleItemResponse;
import com.eamosse.gmail.gestion.myapplication.datasources.models.ArticleResponse;
import com.eamosse.gmail.gestion.myapplication.dto.Article;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kotlin.NotImplementedError;

/**
 * Repository qui gère les articles
 */
public class ArticleRepository implements IArticleRepository {
    // Data source permettant de récupérer les articles depuis le web service
    ArticleDataSource dataSource = new ArticleDataSource();

    /**
     * Récupère la liste des articles depuis le web service
     * @return Une liste d'articles
     * @throws IOException s'il y a une erreur lors de l'éxécution de la requête
     */
    @Override
    public List<Article> getArticles(String country) throws IOException {
        ArticleResponse articleResponse = dataSource.getArticle(country);
        List<Article> articles = new ArrayList<>();
        // Le web service retourne un objet du web service, un objet serveur
        // Il faut dissocier les objets du web service des objets métiers de notre application
        // Ici on récupère les articles de la réponse du web service et on le convertit en une liste
        // d'objets métiers de notre application.
        // Si le web service bouge, notre classe métier ne bougera pas. De la même façon, si notre modèele
        // métier change, on ne sera pas obligé de modifier l'objet du web service.

        for (ArticleItemResponse item : articleResponse.getArticles()) {
            articles.add(new Article(
                    item.getTitle(),
                    item.getDescription(),
                    item.getAuthor()
            ));
        }
        return articles;
    }

    /**
     * TODO Documenter cette méthode
     * @param id
     * @return
     */
    @Override
    public Article getArticle(String id) {
        // TODO Implémenter la fonction permettant de retourner un article selectionné à partir de son id
        throw new NotImplementedError("Implémentez-moi");
    }
}
