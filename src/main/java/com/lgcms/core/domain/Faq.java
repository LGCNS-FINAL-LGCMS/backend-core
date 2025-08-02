package com.lgcms.core.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Faq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    private String answer;

    private String imageUrl;

    private String url;


    public void modifyFaq(String question, String answer, String imageUrl, String url){
        if(question != null) this.question = question;
        if(answer != null) this.answer = answer;
        if(imageUrl != null) this.imageUrl = imageUrl;
        if(url != null) this.url = url;
    }
}
