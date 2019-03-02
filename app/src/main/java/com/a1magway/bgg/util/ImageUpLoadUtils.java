package com.a1magway.bgg.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.a1magway.bgg.common.SimpleObserver;
import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.OrderItem;
import com.a1magway.bgg.data.entity.UploadPicBean;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.net.CommonParamInterceptor;
import com.a1magway.bgg.okhttp.OkHttpUtils;
import com.a1magway.bgg.okhttp.ProgressListener;
import com.a1magway.bgg.v.productReturn.IApplyReturnV;
import com.almagway.common.AppConfig;
import com.hyphenate.helpdesk.easeui.Constant;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;

import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;

/**
 * Created by enid on 2018/9/4.
 */

public class ImageUpLoadUtils implements IApplyReturnV,TakePhoto.TakeResultListener,InvokeListener {

    private Activity activity;
    private static int FILECHOOSER_RESULTCODE=1;
    private Uri imageUri;
    private ChoosePicUploadListener choosePicUploadListener;
    //TakePhoto
    private TakePhoto takePhoto;

    private InvokeParam invokeParam;

    public ImageUpLoadUtils(Activity activity){
        this.activity=activity;
    }

    public void setChoosePicUploadListener(ChoosePicUploadListener choosePicUploadListener) {
        this.choosePicUploadListener = choosePicUploadListener;
    }

    /**
     * 创建take //需要在act中调用
     */
    public void onCreateTakePhoto(@Nullable Bundle savedInstanceState){
        getTakePhoto().onCreate(savedInstanceState);
    }
    //需要在act中调用
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
    }
    //需要在act中调用
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
    }
    //上传图片
    public void postPic(final String file){
        if (choosePicUploadListener!=null){
            choosePicUploadListener.startUplaod();
        }
        //构建请求
        final File f=new File(file);

        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), f);
        //表单类型
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", f.getName(), imageBody);
        MultipartBody.Part part=builder.build().part(0);

        new APIManager(new CommonParamInterceptor()).uploadReturnPic(part)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObserver<APIResponse<UploadPicBean>>(activity) {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (choosePicUploadListener!=null){
                            choosePicUploadListener.onFailure(null);
                         }
                    }

                    @Override
                    public void onNext(APIResponse<UploadPicBean> uploadPicBeanAPIResponse) {
                        super.onNext(uploadPicBeanAPIResponse);
                        System.out.println("上传成功");
                        if (choosePicUploadListener!=null){
                             choosePicUploadListener.onResponse(null,uploadPicBeanAPIResponse.getData(),Uri.parse(file));
                         }
                    }
                });

//        new Observer<APIResponse<UploadPicBean>>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                System.out.println("上传成功");
//            }
//
//            @Override
//            public void onNext(APIResponse<UploadPicBean> listAPIResponse) {
//                System.out.println("上传成功");
//                if (choosePicUploadListener!=null){
//                    choosePicUploadListener.onResponse(null,listAPIResponse.getData());
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("上传失败:e.getLocalizedMessage() = " + e.getLocalizedMessage());
//                if (choosePicUploadListener!=null){
//                    choosePicUploadListener.onFailure(null);
//                }
//            }
//
//            @Override
//            public void onComplete() {
//                System.out.println("上传完成 ");
//            }
//        }

    }

    private TakePhoto getTakePhoto(){
        if (takePhoto==null){
            takePhoto= (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(activity,this));
        }
        return takePhoto;
    }
    @Override
    public Context getContext() {
        return activity;
    }

    @Override
    public Activity getActivity() {
        return activity;
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {

    }

    @Override
    public void initAdapter(BaseRecyclerAdapter adapter) {

    }

    @Override
    public void initPhotoAdapter(BaseRecyclerAdapter adapter) {

    }

    @Override
    public void onSucess(APIResponse apiResponse) {

    }

    @Override
    public void onFaile(Throwable e) {

    }

    @Override
    public void GoodsInfo(BaseRecyclerAdapter mAdapter) {

    }

    @Override
    public void selectNumber(Integer number) {

    }

    @Override
    public void selectGoods(OrderItem orderItem) {

    }

    @Override
    public void getPhone(String phone) {

    }

    @Override
    public void takeSuccess(TResult result) {

    }

    @Override
    public void takeFail(TResult result, String msg) {

    }

    @Override
    public void takeCancel() {

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        PermissionManager.TPermissionType type=PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
        PermissionManager.handlePermissionsResult(activity,type,invokeParam,this);
    }
    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(activity),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam=invokeParam;
        }
        return type;
    }

    //上传图片回调
    public interface ChoosePicUploadListener{
        void startUplaod();
        void onFailure(IOException e);
        void onResponse(Call call, UploadPicBean response,Uri localFile);
    }



}
