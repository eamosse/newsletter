package com.eamosse.gmail.gestion.myapplication.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.eamosse.gmail.gestion.myapplication.dto.Article;
import com.eamosse.gmail.gestion.myapplication.repositories.ArticleRepository;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Un view model pour la gestion des articles dans l'application
 */
public class ArticleViewModel extends ViewModel {
    // Instance du repository qui gère les données de l'application
    ArticleRepository repository = new ArticleRepository();
    Executor executor = Executors.newSingleThreadExecutor();

    // Live data qui expose une liste d'articles récupérée depuis le serveur
    private MutableLiveData<List<Article>> listArticle = new MutableLiveData<>();

    /**
     * Retourne un live data qui expose la liste des articles au fragment qui l'observe
     *
     * @return un live data non modifiable
     */
    public LiveData<List<Article>> getListArticle() {
        return listArticle;
    }

    /**
     * Supprime un article
     * // TODO A supprimer
     */
    public void removeArticle() {
        List<Article> articles = listArticle.getValue();
        articles.remove(0);
        listArticle.postValue(articles);
    }

    /**
     * Méthode qui charge la liste des articles depuis le web service en utilisant les méthodes
     * du repository
     */
    public void getArticles(String country) {
        // Le chargement des articles droit se faire dans un thread secondaire car il y une connexion à internet
        executor.execute(() -> {
            try {
                // Récupérer la liste des articles via le repository
                List<Article> articles = repository.getArticles(country);
                // Mettre à jour le live data
                listArticle.postValue(articles);
            } catch (IOException e) {
                // TODO: Gérer les erreurs liées au chargement de la liste des articles
                // Pour cela, il faut utiliser un autre live data (errorLiveData) qui notifiera le
                // fragment quand il y a une erreur
                // TODO Observer le live data dans le fragment pour afficher un message à l'utilisateur
                e.printStackTrace();
            }
        });
    }
}
