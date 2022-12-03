package com.example.hanghae_blog.dto;

import lombok.Getter;

@Getter
public class DeleteReponseDto {

    private String response;

    public DeleteReponseDto(String response){
        this.response = response;
    }

}
