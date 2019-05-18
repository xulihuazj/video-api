package com.yinfeixing.video.dto.video;

import com.yinfeixing.entity.ToString;

public class DicDTO extends ToString {

    private String dicCode;

    private String dicValue;

    public String getDicCode() {
        return dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
    }

    public String getDicValue() {
        return dicValue;
    }

    public void setDicValue(String dicValue) {
        this.dicValue = dicValue;
    }
}

