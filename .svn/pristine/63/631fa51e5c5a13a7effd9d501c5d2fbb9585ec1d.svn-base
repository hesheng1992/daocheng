package com.a1magway.bgg.tool;

import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;
import com.a1magway.bgg.util.StringFormatUtil;
import com.almagway.common.utils.StringUtil;
import java.util.Date;

/**
 * author: Beaven date: 2017/10/26 13:46
 *
 * <p>倒计时控制器，主要用于限定时间购买的商品
 *
 * <p>示例： TimeCountDownController controller = new TimeCountDownController(date,textView,format, new
 * CountDownListener{ @Override public void onFinish() { // do something } }).start
 *
 * <p>controller.cancel(),停止倒计时，取消textView关联，防止内存泄漏
 */
public class TimeCountDownController {

    private static final long COUNT_DOWN_INTERVAL = 1000;
    public static final String TIME_DEFAULT = "00:00:00";

    private CountDownTimer timer;

    private Date endDate;
    private TextView textView;
    private String format;
    private TimeCountDownController.CountDownListener listener;

    public TimeCountDownController(
            @NonNull Date endDate,
            @NonNull TextView textView,
            @Nullable String format,
            @NonNull TimeCountDownController.CountDownListener listener) {
        this.endDate = endDate;
        this.textView = textView;
        this.format = format;
        this.listener = listener;
    }

    public TimeCountDownController start() {
        final long millisInFuture = endDate.getTime() - System.currentTimeMillis();
        if (millisInFuture <= 0) {
            textView.setText(TIME_DEFAULT);
            listener.onFinish();
            return this;
        }
        timer =
                new CountDownTimer(millisInFuture, COUNT_DOWN_INTERVAL) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (StringUtil.isEmpty(format)) {
                            textView.setText(
                                    StringFormatUtil.getCountDownTimeStr(
                                            millisUntilFinished / 1000));
                        } else {
                            textView.setText(
                                    String.format(
                                            format,
                                            StringFormatUtil.getCountDownTimeStr(
                                                    millisUntilFinished / 1000)));
                        }
                    }

                    @Override
                    public void onFinish() {
                        textView.setText(TimeCountDownController.TIME_DEFAULT);
                        listener.onFinish();
                    }
                }.start();
        return this;
    }

    public void cancel() {
        if (timer != null) {
            timer.cancel();
        }
        textView = null;
    }

    public interface CountDownListener {
        void onFinish();
    }
}
