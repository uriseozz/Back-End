package com.asdf.ho.validator;


import org.springframework.stereotype.Component;

@Component
public class CategoryValidator {
    public static void checkCategory(String categoryname, String savedCategory) {
        if(categoryname.equals(savedCategory)) {
            throw new IllegalArgumentException("해당 카테고리가 이미 존재합니다.");
        }
    }


}
