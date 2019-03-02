package com.a1magway.bgg.v.personal;

import com.a1magway.bgg.common.ILoadingV;

/**
 * Created by lyx on 2017/8/8.
 */
public interface IPersonalSettingV extends ILoadingV {

    void setBirthDay(String string);

    void setGender(String string);

    void setNickname(String nickname);

    String getNickname();

}
