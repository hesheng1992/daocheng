package com.almagway.umeng;

import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by enid on 2018/5/29.
 */

public class PlatformType {

    public static final int QQ = 0;

    public static final int QZONE = 1;

    public static final int WEIXIN = 2;

    public static final int WEIXIN_CIRCLE = 3;

    public static final int SINA = 4;

    /**
     * 默认为QQ
     *
     * @param mediaType
     * @return
     */
    public static final SHARE_MEDIA getUmShareMedai(int mediaType) {
        switch (mediaType) {
            case QQ:
                return SHARE_MEDIA.QQ;
            case QZONE:
                return SHARE_MEDIA.QZONE;
            case WEIXIN:
                return SHARE_MEDIA.WEIXIN;
            case WEIXIN_CIRCLE:
                return SHARE_MEDIA.WEIXIN_CIRCLE;
            case SINA:
                return SHARE_MEDIA.SINA;
            default:
                return SHARE_MEDIA.QQ;
        }
    }
}
