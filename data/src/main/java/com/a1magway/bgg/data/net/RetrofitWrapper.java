package com.a1magway.bgg.data.net;

import com.a1magway.bgg.data.entity.APIResponse;
import com.almagway.common.AppConfig;
import com.almagway.common.net.HttpConfig;
import com.almagway.common.net.NetLogInterceptor;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.almagway.common.json.Json.gson;

/**
 * 使用Retrofit请求接口的封装类,为每个api请求封装基本参数
 * Created by jph on 2016/11/17.
 */
public class RetrofitWrapper {
    private APIService mAPIService;


    public RetrofitWrapper(CommonParamInterceptor commonParamInterceptor) {

        NetLogInterceptor netLogInterceptor = HttpConfig.buildNetLog(NetLogInterceptor.Level.BODY);
        OkHttpClient client = HttpConfig.buildOkHttpClient(AppConfig.HTTP_TIMEOUT,
                netLogInterceptor);
        if (commonParamInterceptor != null) {
            client = client.newBuilder().addInterceptor(commonParamInterceptor).build();
        }

        Retrofit retrofit = HttpConfig.buildRetrofit(AppConfig.BASE_URL, client,
                GsonConverterFactory.create(gson), RxJava2CallAdapterFactory.create());

        mAPIService = retrofit.create(APIService.class);
    }


    public APIService getAPIService() {
        return mAPIService;
    }


    protected <T> ObservableTransformer<APIResponse<T>, T> getTransformer() {
        return (ObservableTransformer<APIResponse<T>, T>) new ObservableTransformer() {
            @Override
            public ObservableSource apply(@NonNull io.reactivex.Observable upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .flatMap(new Function<APIResponse, Observable>() {
                            @Override
                            public Observable apply(@NonNull APIResponse apiResponse) throws Exception {
                                if (apiResponse.isSuccess()) {
                                    if (apiResponse.getData() != null) {
                                        return Observable.just(apiResponse.getData());
                                    }
                                } else {
                                    return Observable.error(new APIException(apiResponse.getCode(),
                                            apiResponse.getMsg()));
                                }
                                return Observable.empty();
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    protected <T> ObservableTransformer<APIResponse<T>, T> getTransformerCanNull() {
        return (ObservableTransformer<APIResponse<T>, T>) new ObservableTransformer() {
            @Override
            public ObservableSource apply(@NonNull io.reactivex.Observable upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .flatMap(new Function<APIResponse, Observable>() {
                            @Override
                            public Observable apply(@NonNull APIResponse apiResponse) throws Exception {
                                if (apiResponse.isSuccess()) {
                                        return Observable.just(apiResponse.getData());
                                } else {
                                    return Observable.error(new APIException(apiResponse.getCode(),
                                            apiResponse.getMsg()));
                                }
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }



    /**
     * 得到只返回服务器message的transformer，转换过程中不处理data
     */
    protected ObservableTransformer<APIResponse, String> getMessageTransformer() {
        return (ObservableTransformer<APIResponse, String>) new ObservableTransformer() {
            @Override
            public ObservableSource apply(@NonNull io.reactivex.Observable upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .flatMap(new Function<APIResponse, Observable>() {
                            @Override
                            public Observable apply(@NonNull APIResponse apiResponse) throws Exception {
                                if (apiResponse.isSuccess()) {
                                    if (apiResponse.getMsg() != null) {
                                        return Observable.just(apiResponse.getMsg());
                                    }
                                } else {
                                    return Observable.error(new APIException(apiResponse.getCode(),
                                            apiResponse.getMsg()));
                                }
                                return Observable.empty();
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    protected Observable<APIResponse> noDataObservable(Observable<APIResponse> observable) {
        return observable.flatMap(new Function<APIResponse, ObservableSource<APIResponse>>() {
            @Override
            public ObservableSource<APIResponse> apply(@NonNull APIResponse apiResponse)
                    throws Exception {
                if (apiResponse.isSuccess()) {
                    if (apiResponse.getData() == null) {
                        return Observable.just(apiResponse);
                    }
                } else {
                    return Observable.error(new APIException(apiResponse.getCode(),
                            apiResponse.getMsg()));
                }
                return Observable.empty();
            }
        });
    }




    protected CommonParamInterceptor getCommonParamInterceptor() {
        return null;
    }


    private static boolean isDisposedObserver(Observer observer) {
        if (observer instanceof Disposable &&
                ((Disposable) observer).isDisposed()) {
            return true;
        }

        return false;
    }
}
