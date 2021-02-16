package com.eamosse.gmail.gestion.myapplication.datasources;

import com.eamosse.gmail.gestion.myapplication.datasources.models.ArticleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Interface exposant les actions du web service qu'on veut consommer
 */
public interface ArticleService {
    @GET("top-headlines")
        // L'annotation @Query ajoutera automatiquement country en query param à la requête
        // Exemple, à l'exécution on aura top-headlines?country=fr
    Call<ArticleResponse> getHeadLines(@Query("country") String country);
}
