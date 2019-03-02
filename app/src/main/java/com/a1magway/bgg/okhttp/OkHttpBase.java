package com.a1magway.bgg.okhttp;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.concurrent.TimeUnit;


import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.ObservableEmitter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Class are created, On 2017/07/04.
 */

class OkHttpBase {
    private static OkHttpBase mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mDelivery;


    private OkHttpBase() {
        /*mOkHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS).build();*/
        try {
            mOkHttpClient = setSSL();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mDelivery = new Handler(Looper.getMainLooper());
    }

    synchronized static <T> OkHttpBase getInstance() {
        if (mInstance == null) {
            mInstance = new OkHttpBase();
        }
        return mInstance;
    }

    <T> void getRequest(String url, Headers header, final ResultCallback callback, ObservableEmitter<T> observableEmitter,
                        Class<T> cl, LogApiUtils logApiUtils) {
        Request request;
        if (header.size() > 0) {
            request = new Request.Builder().url(url).headers(header).build();
        } else {
            request = new Request.Builder().url(url).build();
        }
        deliveryResult(callback, observableEmitter, cl, request, logApiUtils);
    }


    <T> void postRequest(String url, Headers header, final ResultCallback callback, ObservableEmitter<T> observableEmitter,
                         Class<T> cl, List<OkHttpUtils.Param> params, LogApiUtils logApiUtils) {
        Request request = buildPostRequest(url, header, params);
        deliveryResult(callback, observableEmitter, cl, request, logApiUtils);
    }

    <T> void postJsonRequest(String url, Headers header, final ResultCallback callback, ObservableEmitter<T> observableEmitter,
                             Class<T> cl, List<OkHttpUtils.Param> params, LogApiUtils logApiUtils) {
        Request request = buildPostJsonRequest(url, header, params);
        deliveryResult(callback, observableEmitter, cl, request, logApiUtils);
    }


    <T> void putRequest(String url, Headers header, final ResultCallback callback, ObservableEmitter<T> observableEmitter,
                        Class<T> cl, List<OkHttpUtils.Param> params, LogApiUtils logApiUtils) {
        Request request = buildPutRequest(url, header, params);
        deliveryResult(callback, observableEmitter, cl, request, logApiUtils);
    }


    <T> void deleteRequest(String url, Headers header, final ResultCallback callback, ObservableEmitter<T> observableEmitter,
                           Class<T> cl, LogApiUtils logApiUtils) {
        Request request = buildDeleteRequest(url, header);
        deliveryResult(callback, observableEmitter, cl, request, logApiUtils);
    }


    private <T> void deliveryResult(final ResultCallback callback, final ObservableEmitter<T> observableEmitter, final Class<T> cl,
                                    final Request request, final LogApiUtils logApiUtils) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if (logApiUtils != null) {
                    logApiUtils.setResponse(null, e);
                }

                if (observableEmitter != null) {
                    RxResponse.onFailure(e, observableEmitter);
                } else {
                    sendFailCallback(callback, e);
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {
                    if (response.getClass().equals(cl) && observableEmitter != null) {
                        RxResponse.onResponse(observableEmitter, response, mDelivery);
                    } else {
                        ResponseBody body = response.body();
                        int statusCode = response.code();
                        Headers headers = response.headers();
                        String str = "";
                        if (body != null) {
                            str = body.string();
                        }
                        if (logApiUtils != null) {
                            logApiUtils.setResponse(UnicodeUtils.decodeUnicode(str), null);
                        }

                        if (observableEmitter != null) {
                            RxResponse.onResponse(UnicodeUtils.decodeUnicode(str), observableEmitter, cl, mDelivery);
                        } else {
                            sendSuccessCallBack(callback, UnicodeUtils.decodeUnicode(str), headers, statusCode);
                        }
                    }
                } catch (Exception e) {
                    if (observableEmitter != null) {
                        RxResponse.onFailure(e, observableEmitter);
                    } else {
                        sendFailCallback(callback, e);
                    }
                }
            }
        });

        if (callback instanceof ResultCallbackAttachCall) {
            ((ResultCallbackAttachCall) callback).attachCall(call);
        }
    }

    private void sendFailCallback(final ResultCallback callback, final Exception e) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onFailure(e);
                }
            }
        });
    }

    private void sendSuccessCallBack(final ResultCallback callback, final String body, final Headers headers,
                                     final int statusCode) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onSuccess(body, headers, statusCode);
                }
            }
        });
    }


    private Request buildPostRequest(String url, Headers header, List<OkHttpUtils.Param> params) {
        FormBody.Builder builder = new FormBody.Builder();
        for (OkHttpUtils.Param param : params) {
            builder.add(param.key, param.value);
        }
        RequestBody requestBody = builder.build();
        if (header.size() > 0) {
            return new Request.Builder().url(url).post(requestBody).headers(header).build();
        } else {
            return new Request.Builder().url(url).post(requestBody).build();
        }
    }

    //add json date
    private Request buildPostJsonRequest(String url, Headers header, List<OkHttpUtils.Param> params) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), params.get(0).value);
        if (header.size() > 0) {
            return new Request.Builder().url(url).post(requestBody).headers(header).build();
        } else {
            return new Request.Builder().url(url).post(requestBody).build();
        }
    }


    private Request buildPutRequest(String url, Headers header, List<OkHttpUtils.Param> params) {
        FormBody.Builder builder = new FormBody.Builder();
        for (OkHttpUtils.Param param : params) {
            builder.add(param.key, param.value);
        }
        RequestBody requestBody = builder.build();
        if (header.size() > 0) {
            return new Request.Builder().url(url).put(requestBody).headers(header).build();
        } else {
            return new Request.Builder().url(url).put(requestBody).build();
        }
    }


    private Request buildDeleteRequest(String url, Headers header) {
        FormBody.Builder builder = new FormBody.Builder();
        RequestBody requestBody = builder.build();
        if (header.size() > 0) {
            return new Request.Builder().url(url).delete(requestBody).headers(header).build();
        } else {
            return new Request.Builder().url(url).delete(requestBody).build();
        }
    }


    //Post upload Image
    <T> void postImageRequest(String url, Headers headers, ResultCallback callback, ObservableEmitter<T> observableEmitter,
                              Class<T> cl, ProgressListener progressListener, List<OkHttpUtils.Param> files, List<OkHttpUtils.Param> params,
                              LogApiUtils logApiUtils) {
        Request request = buildPostImageRequest(url, headers, progressListener, files, params);
        deliveryResult(callback, observableEmitter, cl, request, logApiUtils);
    }

    /**
     * Upload a image
     *
     * @param url   server address
     * @param files files.value = image path
     * @return request
     */
    private Request buildPostImageRequest(String url, Headers headers, ProgressListener progressListener,
                                          List<OkHttpUtils.Param> files, List<OkHttpUtils.Param> params) {
        if (files == null || files.size() == 0) {
            throw new RuntimeException("画像アップパスがnull");
        }
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        //set file
        for (OkHttpUtils.Param param : files) {
            File file = new File(param.value);
            if (file.exists()) {
                builder.addFormDataPart(param.key, file.getName(),
                        RequestBody.create(MediaType.parse("image/*"), file));
            }
        }
        //set params
        for (OkHttpUtils.Param param : params) {
            builder.addFormDataPart(param.key, param.value);
        }
        RequestBody requestBody = builder.build();
        if (progressListener != null) {
            ProgressRequestBody progressRequestBody = new ProgressRequestBody(requestBody, progressListener);
            return headers.size() > 0 ? new Request.Builder().url(url).post(progressRequestBody).headers(headers).build()
                    : new Request.Builder().url(url).post(progressRequestBody).build();
        }
        return headers.size() > 0 ? new Request.Builder().url(url).post(requestBody).headers(headers).build()
                : new Request.Builder().url(url).post(requestBody).build();
    }


    //Put upload image
    <T> void putImageRequest(String url, Headers headers, ResultCallback callback, ObservableEmitter<T> observableEmitter, Class<T> cl, ProgressListener
            progressListener, List<OkHttpUtils.Param> files, List<OkHttpUtils.Param> params, LogApiUtils logApiUtils) {
        Request request = buildPutImageRequest(url, headers, files, params, progressListener);
        deliveryResult(callback, observableEmitter, cl, request, logApiUtils);
    }

    /**
     * Upload a image
     *
     * @param url   server address
     * @param files files.value = image path
     * @return request
     */
    private Request buildPutImageRequest(String url, Headers headers, List<OkHttpUtils.Param> files, List<OkHttpUtils.Param> params,
                                         ProgressListener progressListener) {
        if (files == null || files.size() == 0) {
            throw new RuntimeException("画像アップパスがnull");
        }
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        //set files
        for (OkHttpUtils.Param param : files) {
            File file = new File(param.value);
            if (file.exists()) {
                builder.addFormDataPart(param.key, file.getName(),
                        RequestBody.create(MediaType.parse("image/*"), file));
            }
        }
        //set params
        for (OkHttpUtils.Param param : params) {
            builder.addFormDataPart(param.key, param.value);
        }
        RequestBody requestBody = builder.build();
        if (progressListener != null) {
            ProgressRequestBody progressRequestBody = new ProgressRequestBody(requestBody, progressListener);
            return headers.size() > 0 ? new Request.Builder().url(url).put(progressRequestBody).headers(headers).build()
                    : new Request.Builder().url(url).put(progressRequestBody).build();
        }
        return headers.size() > 0 ? new Request.Builder().url(url).put(requestBody).headers(headers).build()
                : new Request.Builder().url(url).put(requestBody).build();
    }


    // Post upload file
    <T> void postUploadFileRequest(String url, Headers headers, List<OkHttpUtils.Param> files, List<OkHttpUtils.Param> params, ResultCallback callback,
                                   ObservableEmitter<T> observableEmitter, Class<T> cl, ProgressListener progressListener, LogApiUtils logApiUtils) {
        Request request = postBuildUploadFile(url, headers, files, params, progressListener);
        deliveryResult(callback, observableEmitter, cl, request, logApiUtils);
    }


    // Put upload file
    <T> void putUploadFileRequest(String url, Headers headers, List<OkHttpUtils.Param> files, List<OkHttpUtils.Param> params, ResultCallback callback,
                                  ObservableEmitter<T> observableEmitter, Class<T> cl, ProgressListener progressListener, LogApiUtils logApiUtils) {
        Request request = putBuildUploadFile(url, headers, files, params, progressListener);
        deliveryResult(callback, observableEmitter, cl, request, logApiUtils);
    }

    /**
     * Upload a files
     *
     * @param url   server address
     * @param files files.value = pdf path
     * @return request
     */
    private Request postBuildUploadFile(String url, Headers headers, List<OkHttpUtils.Param> files, List<OkHttpUtils.Param> params,
                                        ProgressListener progressListener) {
        if (files == null || files.size() == 0) {
            throw new RuntimeException("files params is null");
        }
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        //set files
        for (OkHttpUtils.Param param : files) {
            File file = new File(param.value);
            if (file.exists()) {
                builder.addFormDataPart(param.key, file.getName(),
                        RequestBody.create(MediaType.parse("application/*; charset=utf-_8"), file));
            }
        }
        //set params
        for (OkHttpUtils.Param param : params) {
            builder.addFormDataPart(param.key, param.value);
        }
        RequestBody requestBody = builder.build();
        if (progressListener != null) {
            //have progress listener
            ProgressRequestBody progressRequestBody = new ProgressRequestBody(requestBody, progressListener);
            return headers.size() > 0 ? new Request.Builder().url(url).post(progressRequestBody).headers(headers).build()
                    : new Request.Builder().url(url).post(progressRequestBody).build();
        }
        //No progress listener
        return headers.size() > 0 ? new Request.Builder().url(url).post(requestBody).headers(headers).build()
                : new Request.Builder().url(url).post(requestBody).build();
    }


    /**
     * Upload a files
     *
     * @param url   server address
     * @param files files.value = pdf path
     * @return request
     */
    private Request putBuildUploadFile(String url, Headers headers, List<OkHttpUtils.Param> files, List<OkHttpUtils.Param> params,
                                       ProgressListener progressListener) {
        if (files == null || files.size() == 0) {
            throw new RuntimeException("files params is null");
        }
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        //set files
        for (OkHttpUtils.Param param : files) {
            File file = new File(param.value);
            if (file.exists()) {
                builder.addFormDataPart(param.key, file.getName(),
                        RequestBody.create(MediaType.parse("application/*; charset=utf-_8"), file));
            }
        }
        //set params
        for (OkHttpUtils.Param param : params) {
            builder.addFormDataPart(param.key, param.value);
        }
        RequestBody requestBody = builder.build();
        if (progressListener != null) {
            //have progress listener
            ProgressRequestBody progressRequestBody = new ProgressRequestBody(requestBody, progressListener);
            return headers.size() > 0 ? new Request.Builder().url(url).put(progressRequestBody).headers(headers).build()
                    : new Request.Builder().url(url).put(progressRequestBody).build();
        }
        //No progress listener
        return headers.size() > 0 ? new Request.Builder().url(url).put(requestBody).headers(headers).build()
                : new Request.Builder().url(url).put(requestBody).build();
    }


    //downloadFile
    <T> void downloadFileRequest(final String url, final String destFileDir, Headers headers, final ResultCallback callback,
                                 final ObservableEmitter<T> observableEmitter, final Class<T> cl, final ProgressListener progressListener, final LogApiUtils logApiUtils) {
        Request request;
        if (headers.size() > 0) {
            request = new Request.Builder().url(url).headers(headers).build();
        } else {
            request = new Request.Builder().url(url).build();
        }
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if (logApiUtils != null) {
                    logApiUtils.setResponse(null, e);
                }

                if (observableEmitter != null) {
                    RxResponse.onFailure(e, observableEmitter);
                } else {
                    sendFailCallback(callback, e);
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (logApiUtils != null) {
                    logApiUtils.setResponse("success", null);
                }
                int statusCode = response.code();
                Headers headers = response.headers();
                ResponseBody body = response.body();
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len;
                long current = 0;
                long total = 0;
                FileOutputStream fos = null;
                try {
                    if (body != null) {
                        total = body.contentLength();
                        is = body.byteStream();
                    }
                    if (is == null) {
                        sendSuccessCallBack(callback, "null", headers, statusCode);
                        return;
                    }
                    File folder = new File(destFileDir);
                    boolean a = folder.exists();
                    if (!a) {
                        if (!folder.mkdirs()) {
                            LogUtil.e("okHttpBase", "crateFolderFail");
                        }
                    }
                    File file = new File(destFileDir, getFileName(url));
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
                        current += len;
                        fos.write(buf, 0, len);
                        LogUtil.e("download current------>" + current);
                        //update progress
                        if (progressListener != null) {
                            progressListener.update(current, total, current == total);
                        }
                    }
                    fos.flush();
                    sendSuccessCallBack(callback, "success", headers, statusCode);
                } catch (IOException e) {
                    LogUtil.e("sss", e.getMessage());
                    if (observableEmitter != null) {
                        RxResponse.onResponse("success", observableEmitter, cl, mDelivery);
                    } else {
                        sendSuccessCallBack(callback, "fail", headers, statusCode);
                    }
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private String getFileName(String url) {
        String[] names = url.split("/");
        return names[names.length - 1];
    }

    /**
     * 设置https 访问的时候对所有证书都进行信任
     *
     * @throws Exception
     */
    public OkHttpClient setSSL() throws Exception {
        final X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());
        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

        return new OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory, trustManager)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();

    }

}
