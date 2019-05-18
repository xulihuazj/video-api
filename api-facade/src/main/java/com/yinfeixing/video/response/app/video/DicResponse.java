package com.yinfeixing.video.response.app.video;

import com.yinfeixing.entity.ToString;
import com.yinfeixing.video.dto.video.DicDTO;

import java.util.List;

public class DicResponse extends ToString {

    private List<DicDTO> dicList;

    public List<DicDTO> getDicList() {
        return dicList;
    }

    public void setDicList(List<DicDTO> dicList) {
        this.dicList = dicList;
    }
}
