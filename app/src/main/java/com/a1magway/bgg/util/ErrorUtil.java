package com.a1magway.bgg.util;

import android.content.Context;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.BaseActivity;
import com.a1magway.bgg.data.net.APIException;
import com.almagway.common.utils.StringUtil;
import com.almagway.common.log.MLog;

import java.net.UnknownHostException;

/**
 * Created by jph on 2017/8/16.
 */
public class ErrorUtil {

    private static String TAG = "ErrorUtil";

    /**
     * 处理服务器错误
     *
     * @param context
     * @param e
     */
    public static void processAPIError(Context context, Throwable e) {
        String msg = context.getString(R.string.unknown_fail);
        if (APIException.class.isInstance(e)) {
            APIException apiException = (APIException) e;
            MLog.e(TAG, "error_code-----" + (apiException.getCode()));
            //统一处理api错误,提示错误信息
            if (StringUtil.isNotEmpty(msg)) {
                MLog.i(TAG, e.getMessage());
                msg = apiException.getMessage();
                Toaster.showShort(context, msg);
            }
        } else if(UnknownHostException.class.isInstance(e)){
            msg = "网络连接失败！";
            Toaster.showShort(context, msg);
        }else {
            e.printStackTrace();
        }
//        Toaster.showShort(context, msg);
        if(context instanceof BaseActivity){
            ((BaseActivity) context).hideLoadingDialog();
        }
    }
}
