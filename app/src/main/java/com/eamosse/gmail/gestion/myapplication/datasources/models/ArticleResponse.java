package com.eamosse.gmail.gestion.myapplication.datasources.models;

import java.util.List;

public class ArticleResponse {
    private String status;
    private int totalResults;
    private List<ArticleItemResponse> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<ArticleItemResponse> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleItemResponse> articles) {
        this.articles = articles;
    }
}
