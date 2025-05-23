package com.ltb.api_demo.services;

import com.ltb.api_demo.models.Article;
import com.ltb.api_demo.models.Doc;
import com.ltb.api_demo.models.NytResponse;
import com.ltb.api_demo.models.NytSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    @Value("${api_key}")
    private String apikey;

    @Value("${mostPopularUrl}")
    private String mostPopularUrl;

    @Value("${searchUrl}")
    private String searchUrl;

    @Autowired
    RestTemplate restTemplate;

    public List<Article> getMostPopular() {
        NytResponse response = restTemplate.getForObject(
                mostPopularUrl + "api-key=" + apikey, NytResponse.class);
        List<Article> results = new ArrayList<>();
        if (response != null && response.getStatus().equals("OK")) {
            results = response.getResults();
            setThumbnails(results);
        }
        return results;
    }

    public void setThumbnails(List<Article> articles) {

        for (Article article : articles) {
            if (checkForMedia(article)) {
                article.setImageUrl(article.getMedia().get(0).getMediaMetadata().get(0).getUrl());
            }
        }

    }

    public boolean checkForMedia(Article article) {

        if (article.getMedia() != null && !article.getMedia().isEmpty()) {
            return article.getMedia().get(0).getMediaMetadata() != null &&
                    !article.getMedia().get(0).getMediaMetadata().isEmpty();
        }
        return false;
    }

    public List<Doc> getSearchResults(String searchText) {

        ResponseEntity<NytSearchResponse> searchResponse = restTemplate.getForEntity(
                searchUrl +
                        "q=" + searchText +
                        "&api-key=" + apikey,
                NytSearchResponse.class);

        List<Doc> results = new ArrayList<>();

        if (searchResponse.getStatusCode().is2xxSuccessful()) {
            results = searchResponse.getBody().getResponse().getDocs();
        }

        return results;

    }

}
