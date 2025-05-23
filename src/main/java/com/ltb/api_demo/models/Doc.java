package com.ltb.api_demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doc {
    @JsonProperty("web_url")
    private String webUrl;
    private Headline headline;
    private Byline byline;
    private List<Multimedia> multimedia;
}
