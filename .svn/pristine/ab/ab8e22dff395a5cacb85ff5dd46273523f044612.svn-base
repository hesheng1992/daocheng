package com.a1magway.bgg.common.shre;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;

import com.almagway.common.utils.FileUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by enid on 2018/6/12.
 */

public class ShareUtils {
    public static String IMAGE_NAME = "iv_share_";
    public static int i = 0;

    private File mFile;


    private SaveImageListener mSaveImageListener;

    public void setSaveImageListener(SaveImageListener listener, int size) {
        this.mSaveImageListener = listener;
    }

    public interface SaveImageListener {
        void onFinished();
    }

    //创建本地保存路径
    public static File createStableImageFile(Context context) throws IOException {
        i++;
        String imageFileName = IMAGE_NAME + i + ".jpg";
        File storageDir = context.getExternalCacheDir();
        File image = new File(storageDir, imageFileName);
        return image;
    }


    public static synchronized String createStableImageFilePath(Context context) throws IOException {
        i++;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String timeTag = dateFormat.format(System.currentTimeMillis());
        String imageFileName = timeTag + "_14752" + i + ".jpg";
        String DCIM = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString();
        String DIRECTORY = DCIM + "/tiny/";
        FileUtil.createOrExistsDir(DIRECTORY);

        return DIRECTORY + imageFileName;
    }

    //判断是否安装了微信
    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 检测客户端有木有安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean uninstallSoftware(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, PackageManager.COMPONENT_ENABLED_STATE_DEFAULT);
            if (packageInfo != null) {
                return true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
