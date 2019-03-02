package version;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.a1magway.bgg.App;
import com.a1magway.bgg.data.entity.AppVersionInfoData;
import com.a1magway.bgg.okhttp.LogUtil;
import com.a1magway.bgg.okhttp.OkHttpUtils;
import com.a1magway.bgg.okhttp.ProgressListener;
import com.a1magway.bgg.okhttp.ResultCallback;
import com.a1magway.bgg.util.AppUtils;
import com.a1magway.bgg.util.SharedPrefUtils;
import com.almagway.common.AppConfig;


import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import okhttp3.Headers;

/**
 * Created by enid on 2018/7/28.
 */

public class AppVersionUpdateService extends Service {
    private String apkUrl;
    private ApKDownloadProgressListener apKDownloadProgressListener;
    private VersionUpdateShowDialog versionUpdateShowDialog;
    private boolean force;//是否强制更新
    private String updateInfo;//更新信息
    private String apkName;//下载的apk名字
    private boolean downloading;//正在下载中
    private boolean saveApkSize = true;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        checkedVersion();
        return new MyBinder();
    }


    public void addApKDownloadProgressListener(ApKDownloadProgressListener apKDownloadProgressListener) {
        this.apKDownloadProgressListener = apKDownloadProgressListener;
    }

    public void addUpdateShowDialog(VersionUpdateShowDialog versionUpdateShowDialog) {
        this.versionUpdateShowDialog = versionUpdateShowDialog;
    }


    public class MyBinder extends Binder {
        public AppVersionUpdateService getAppVersionUpdateService() {
            return AppVersionUpdateService.this;
        }
    }

    //检查版本号
    private void checkedVersion() {
        final String nowVersionName = AppUtils.getAppVersionName(App.getContext());
        OkHttpUtils.getInstance()
                .get()
                .url(AppConfig.APP_RELEASE_VERSION_UPDATE_URL)
                .buildRx()
                .LogApi()
                .build(AppVersionInfoData.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<AppVersionInfoData>() {
                    @Override
                    public void accept(AppVersionInfoData appVersionInfoData) throws Exception {
                        apkUrl = appVersionInfoData.getUrl();
                        String[] ss = apkUrl.split("/");
                        apkName = ss[ss.length - 1];
                        if (!appVersionInfoData.getVersion().equals(nowVersionName)) {
                            force = appVersionInfoData.isForce();
                            updateInfo = appVersionInfoData.getInfo();
                            if (force) {
                                //强制更新
                                if (apkExists()) {
                                    versionUpdateShowDialog.showCompelInstallApkDialog(appVersionInfoData.getInfo());
                                } else {
                                    versionUpdateShowDialog.showCompelVersionUpdateDialog(appVersionInfoData.getInfo());
                                }
                            } else {
                                if (apkExists()) {
                                    //选择更新安装apk
                                    versionUpdateShowDialog.installApkDialog(updateInfo);
                                } else {
                                    startDownloadApk();
                                }
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtil.e("AppUpdateService", throwable.getMessage());
                    }
                });
    }


    //下载apk
    public void startDownloadApk() {
        if (downloading) {
            return;
        }
        cleanOldApkFolder();
        OkHttpUtils.getInstance()
                .get()
                .url(apkUrl)
                .downloadFile(AppUtils.DOWNLOAD_APK_FOLDER, new ProgressListener() {
                    @Override
                    public void update(long bytesRead, long contentLength, boolean done) {
                        LogUtil.e("update", bytesRead + " " + contentLength + " " + done);
                        downloading = true;
                        if (done) {
                            downloading = false;
                        }
                        if (saveApkSize) {
                            saveApkSize = false;
                            //保存更新包的大小，非强制安装前先校验
                            SharedPrefUtils.saveUpdateApkSize(App.getContext(), contentLength);
                        }
                        //更新进度条
                        if (apKDownloadProgressListener != null) {
                            apKDownloadProgressListener.current((int) (((double) bytesRead / contentLength) * 100));
                        }

                        //非强制更新提示用户安装apk
                        if (done && !force) {
                            versionUpdateShowDialog.installApkDialog(updateInfo);
                        }
                    }
                }).build().execute(new ResultCallback() {
            @Override
            public void onSuccess(String body, Headers headers, int statusCode) {

            }

            @Override
            public void onFailure(Exception e) {
                versionUpdateShowDialog.downloadApkError();
            }
        });
    }

    //安装apk
    public void installApk() {
        AppUtils.install(App.getContext(), AppUtils.DOWNLOAD_APK_FOLDER + File.separator + apkName);
    }


    //判断apk是否存在和是否完整
    private boolean apkExists() {
        long updateApkSize = SharedPrefUtils.getChckoutUpdateApkSize(App.getContext());
        File file = new File(AppUtils.DOWNLOAD_APK_FOLDER + File.separator + apkName);
        if (file.exists() && file.length() == updateApkSize) {
            //文件存在并且包大小一致才安装（防止下载过程中段，包未下载完整）
            return true;
        } else {
            return false;
        }
    }


    //删除以前的apk包
    private void cleanOldApkFolder() {
        File file = new File(AppUtils.DOWNLOAD_APK_FOLDER);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.exists()) {
                        f.delete();
                    }
                }
            }
        }
    }

}
