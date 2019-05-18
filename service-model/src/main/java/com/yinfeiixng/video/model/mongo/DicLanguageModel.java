package com.yinfeiixng.video.model.mongo;

import com.yinfeixing.entity.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "dic_language")
public class DicLanguageModel extends ToString {
    @Id
    private String id;

    // 语言
    @Field(value = "language")
    private String language;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}