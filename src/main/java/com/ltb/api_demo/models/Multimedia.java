package com.ltb.api_demo.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Multimedia {
    private String caption;
    private String credits;
    private Thumbnail thumbnail;
}
