package version;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.TextView;

import com.a1magway.bgg.R;


/**
 * Created on 2017/_9/28 0028.
 */

public class AppUpdateDialog extends DialogFragment implements View.OnClickListener, ApKDownloadProgressListener {
    private String messageText;
    private String buttonText;
    private SeekBar seekBar;
    private TextView tv_progress;
    private GeneralRoundDialogCallBack callBack;
    private AppVersionUpdateService appVersionUpdateService;
    private TextView button;
    private boolean showCancel;
    private boolean showSeekBar;
    private Handler handler = new Handler();

    public void setMsg(boolean showSeekBar, boolean showCancel, String messageText, String buttonText, GeneralRoundDialogCallBack callBack) {
        this.messageText = messageText;
        this.buttonText = buttonText;
        this.callBack = callBack;
        this.showCancel = showCancel;
        this.showSeekBar = showSeekBar;
        setCancelable(false);
    }


    public void addAppVersionUpdateService(AppVersionUpdateService appVersionUpdateService) {
        this.appVersionUpdateService = appVersionUpdateService;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.round_dialog, container);
        TextView message = view.findViewById(R.id.dialog_round_message);
        button = view.findViewById(R.id.dialog_round_button);
        TextView cancel = view.findViewById(R.id.cancel);
        seekBar = view.findViewById(R.id.seekBar);
        tv_progress = view.findViewById(R.id.tv_show_progress);
        initSeekBar();
        message.setText(messageText);
        button.setText(buttonText);
        //非强制更新
        if (showCancel) {
            cancel.setVisibility(View.VISIBLE);
            seekBar.setVisibility(View.GONE);
            tv_progress.setVisibility(View.GONE);
        }
        //强制更新并且apk之前已下载好
        if (!showCancel && !showSeekBar) {
            cancel.setVisibility(View.GONE);
            seekBar.setVisibility(View.GONE);
            tv_progress.setVisibility(View.GONE);
        }
        button.setOnClickListener(this);
        cancel.setOnClickListener(this);
        return view;
    }

    private void initSeekBar() {
        seekBar.setProgress(0);
        seekBar.setMax(100);
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().setCanceledOnTouchOutside(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_round_button:
                if (callBack != null) {
                    callBack.clickRoundDialogButton(button);
                }
                break;
            case R.id.cancel:
                dismiss();
                break;
        }
    }


    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public void current(final int progress) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (seekBar != null && tv_progress != null) {
                    seekBar.setProgress(progress);
                    tv_progress.setText(String.valueOf(progress + "/100"));
                    if (progress == 100 && getShowsDialog()) {
                        appVersionUpdateService.installApk();
                        button.setText("开始安装");
                    }
                }
            }
        });
    }


    public interface GeneralRoundDialogCallBack {
        void clickRoundDialogButton(TextView textView);
    }
}
