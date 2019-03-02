package com.a1magway.bgg.common.enums;

/**
 * Created by lyx on 2017/8/8.
 */
public enum GenderType {
        GENDER_TYPE_MAN(0, "男"),
        GENDER_TYPE_WOMEN(1, "女"),
        GENDER_TYPE_OTHER(2, "保密");

        GenderType(int index, String value){
            this.index = index;
            this.value = value;

        }

        public int getIndex(){
            return index;
        }

        int index;
        String value;

        public String getValue(){
            return value;
        }

        public static GenderType get(int index) {
            for (GenderType type : GenderType.values()) {
                if (index == type.index) {
                    return type;
                }
            }
            throw new IllegalArgumentException("argument error: " + values());
        }

    public static int getIndex(String value) {
        for (GenderType type : GenderType.values()) {
            if (value.equals(type.value)) {
                return type.index;
            }
        }
        throw new IllegalArgumentException("argument error: " + values());
    }
}
