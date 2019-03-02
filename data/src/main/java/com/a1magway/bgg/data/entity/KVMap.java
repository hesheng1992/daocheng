package com.a1magway.bgg.data.entity;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by jph on 2017/7/31.
 */
public class KVMap extends HashMap<String, Object> implements Serializable {
    private static final long serialVersionUID = 6225019278260326687L;

    @Override
    public Object put(String key, Object value) {
        if (value == null) {
            return null;
        }
        return super.put(key, value);
    }
}
