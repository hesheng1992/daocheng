package com.almagway.common;


/**
 * author: Beaven
 * date: 2017/10/24 18:10
 * <p>
 * 全局配置类
 */
public class AppConfig {
    //版本升级
    public static final String APP_VERSION_UPDATE_URL = "https://120.77.3.83/app/getAppVersionInfo";
    public static final String APP_RELEASE_VERSION_UPDATE_URL = "https://47.99.50.56/app/getAppVersionInfo";//新的
    // Http通信超时时间
    public static final int HTTP_TIMEOUT = 7676;

    // list列表商品分页一次显示数量
    public static final int PAGE_SIZE_2_ROW = 16;

    //选择服务器地址
    public static final String BASE_URL = getUrl(UrlType.IS_RELEASE1);

    public static final String FOUND_WEB_URL = BASE_URL + "/pictures/platform/commodity/picture/html/dist/index.html#";
    public static final String FOUND_PATH = "http://192.168.0.67:8081/#/";

    public static final String UPLOAD_PIC=BASE_URL+"/android/order/uploadRefundPictures";


    private static String getUrl(UrlType urlType) {
        return urlType.getBase_url();
    }

    private enum UrlType {
        IS_TEST("https://smjdev.1magway.com"),
        IS_DEV0("https://mmdev.1magway.com"),//72
        IS_DEV1("http://192.168.0.105:8080"),
        IS_DEV2("http://192.168.0.243:8081"),
        IS_DEV3("http://192.168.0.243:80"),
        IS_DEV4("https://bgg2.1magway.com"),
        IS_DEV5("http://192.168.0.55:8081"),
        IS_DEV6("http://192.168.0.166:8080"),
        IS_DEV7("http://192.168.0.154:8080"),
        IS_RELEASE0("https://1magway.shop:18085"),
        IS_RELEASE1("https://bgg.1magway.com");

        String base_url;

        UrlType(String base_url) {
            this.base_url = base_url;
        }

        public String getBase_url() {
            return base_url;
        }
    }
}
