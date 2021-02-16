package com.eamosse.gmail.gestion.myapplication.datasources;

import com.eamosse.gmail.gestion.myapplication.datasources.models.ArticleResponse;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * TODO Renommer cette classe en OnlineDataSource
 */
public class ArticleDataSource {
    // Instance du service qui gère les actions du web service
    ArticleService service;

    /**
     * Constructeur de la data source
     */
    public ArticleDataSource() {
        // Interceteur permettant de logger le traffic HTTP lors des appels au web service
        // Très utile pour débuguer, il permet de voir les requêtes et réponses du web service
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        // ici on met le niveau à body ==> on affiche tout (requête, réponse, headers, ...)
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Client HTTP utilisé pour effectuer les requêtes
        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        // Ajouter un intercepteur sur toutes les requêtes afin de rejouter automatiquement l'API Key
        // Qui est obligatoire pour appeler l'API

        httpClient.interceptors().add(chain -> {
            Request original = chain.request();
            HttpUrl originalHttpUrl = original.url();
            HttpUrl url = originalHttpUrl.newBuilder()
                    .addQueryParameter("apikey", "786a348d1d0c462db93a014b4793683b")
                    .build();
            Request.Builder requestBuilder = original.newBuilder()
                    .url(url);
            Request request = requestBuilder.build();
            return chain.proceed(request);
        });

        // Instance de retrofit pour la gestion des requêtes vers le web service
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/") // Url de base du web service
                .client(httpClient) // Client HTTP
                .addConverterFactory(GsonConverterFactory.create()) // Converter JSON --> Objet Web service
                .build();

        // On utilise retrofit pour instancier le service
        service = retrofit.create(ArticleService.class);
    }

    public ArticleResponse getArticle(String country) throws IOException {
        return service.getHeadLines(country).execute().body();

    }
}
