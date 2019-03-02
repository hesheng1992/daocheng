package com.a1magway.bgg.okhttp;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Headers;

/**
 * Class are created, On 2017/07/04.
 */

public class RxOkHttpExecute {
    private String url;
    private RequestMode requestWay;
    private List<OkHttpUtils.Param> params;
    private List<OkHttpUtils.Param> files;
    private Map<String, String> headers;
    private LogApiUtils logApiUtils;
    private ProgressListener progressListener;
    private String destFileDir;

    RxOkHttpExecute(String url, RequestMode requestWay, List<OkHttpUtils.Param> files, List<OkHttpUtils.Param> params, Map<String, String> headers,
                    ProgressListener progressListener, String destFileDir) {
        this.url = url;
        this.requestWay = requestWay;
        this.files = files;
        this.params = params;
        this.headers = headers;
        this.progressListener = progressListener;
        this.destFileDir = destFileDir;
    }

    public <T> Observable<T> build(final Class<T> cl) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) throws Exception {
                executeRx(e, cl);
            }
        }).subscribeOn(Schedulers.io());
    }


    private <T> void executeRx(ObservableEmitter<T> observableEmitter, Class<T> cl) {
        OkHttpBase okHttpBase = OkHttpBase.getInstance();
        switch (requestWay) {
            case GET:
                appendGet();
                okHttpBase.getRequest(url, Headers.of(headers), null, observableEmitter, cl, logApiUtils);
                break;
            case POST:
                okHttpBase.postRequest(url, Headers.of(headers), null, observableEmitter, cl, params, logApiUtils);
                break;
            case PUT:
                okHttpBase.putRequest(url, Headers.of(headers), null, observableEmitter, cl, params, logApiUtils);
                break;
            case DELETE:
                okHttpBase.deleteRequest(url, Headers.of(headers), null, observableEmitter, cl, logApiUtils);
                break;
            case POST_UPLOAD_IMAGE:
                okHttpBase.postImageRequest(url, Headers.of(headers), null, observableEmitter, cl, progressListener, files, params, logApiUtils);
                break;
            case PUT_UPLOAD_IMAGE:
                okHttpBase.putImageRequest(url, Headers.of(headers), null, observableEmitter, cl, progressListener, files, params, logApiUtils);
                break;
            case POST_UPLOAD_FILE:
                okHttpBase.postUploadFileRequest(url, Headers.of(headers), files, params, null, observableEmitter, cl, progressListener, logApiUtils);
                break;
            case PUT_UPLOAD_FILE:
                okHttpBase.putUploadFileRequest(url, Headers.of(headers), files, params, null, observableEmitter, cl, progressListener, logApiUtils);
                break;
            case DOWNLOAD_FILE:
                okHttpBase.downloadFileRequest(url, destFileDir, Headers.of(headers), null, observableEmitter, cl, progressListener, logApiUtils);
                break;
            case POST_JSON:
                okHttpBase.postJsonRequest(url, Headers.of(headers), null, observableEmitter, cl, params, logApiUtils);
                break;
        }
    }

    private void appendGet() {
        if (params.size() != 0) {
            StringBuffer buffer = new StringBuffer(url);
            buffer.append("?");
            for (OkHttpUtils.Param param : params) {
                buffer.append(param.key)
                        .append("=")
                        .append(param.value)
                        .append("&");
            }
            buffer.substring(0, buffer.length() - 1);
            url = String.valueOf(buffer);
        }
    }


    public RxOkHttpExecute LogApi() {
        List<OkHttpUtils.Param> log_params = new ArrayList<>();
        log_params.addAll(files);
        log_params.addAll(params);
        StackTraceElement ste = new Throwable().getStackTrace()[1];
        String logLocation = ste.getClassName() + " " + ste.getLineNumber();
        logApiUtils = new LogApiUtils(url, requestWay.name(), log_params, headers, logLocation);
        return this;
    }

}
