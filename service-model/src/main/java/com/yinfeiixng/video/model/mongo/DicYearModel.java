package com.yinfeiixng.video.model.mongo;

import com.yinfeixing.entity.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "dic_year")
public class DicYearModel extends ToString {
    @Id
    private String id;

    @Field(value = "year")
    private String year;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}