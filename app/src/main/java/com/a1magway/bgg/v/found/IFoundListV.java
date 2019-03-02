package com.a1magway.bgg.v.found;

import com.a1magway.bgg.data.entity.FoundTitleData;
import com.a1magway.bgg.v.IView;

import java.util.List;

public interface IFoundListV extends IView {

    void getFounTitle(List<FoundTitleData> list);
}
