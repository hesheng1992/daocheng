package com.a1magway.bgg.data.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * 品牌馆数据,实际是map，为了不写复杂的范型
 * Created by jph on 2017/7/29.
 */
public class BrandMuseumMap extends HashMap<String, List<Brand>> implements Serializable {
    private static final long serialVersionUID = 8522538837709479245L;
}
