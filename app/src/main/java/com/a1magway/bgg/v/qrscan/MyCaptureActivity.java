package com.a1magway.bgg.v.qrscan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a1magway.bgg.R;
import com.almagway.common.utils.StatusBarUtil;
import com.fanwe.zxing.CaptureActivity;
import com.fanwe.zxing.CaptureActivityHandler;
import com.google.zxing.Result;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Initial the camera
 *
 * @author Ryan.Tang
 */
public class MyCaptureActivity extends CaptureActivity implements OnClickListener {
    /**
     * 是否扫描成功后结束二维码扫描activity，0：否，1:是,值为字符串
     */
    public static final String EXTRA_IS_FINISH_ACTIVITY = "extra_is_finish_activity";
    /**
     * 扫描成功返回码
     */
    public static final int RESULT_CODE_SCAN_SUCCESS = 10;
    /**
     * 扫描成功，扫描activity结束后Intent中取扫描结果的key
     */
    public static final String EXTRA_RESULT_SUCCESS_STRING = "extra_result_success_string";

    public LinearLayout title;
    protected LinearLayout buttom;
    private TextView button_photo;

    private CaptureActivityHandler handler;

    private int mFinishActivityWhenScanFinish = 1;
    private View mTitleLeftImv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void initView(){
        View titleLayout = findViewById(R.id.layout_capture_base);
        StatusBarUtil.darkMode(this);
        StatusBarUtil.setMargin(this, titleLayout);
    }

    private void init() {
        initView();
        initIntentData();
        LayoutInflater inflater = getLayoutInflater();
        View includeTitle = inflater.inflate(R.layout.common_titlebar_layout, null);
        mTitleLeftImv = includeTitle.findViewById(R.id.title_img_left);
        TextView mTitleText = includeTitle.findViewById(R.id.title_txt_title);
        View includeButtom = inflater.inflate(R.layout.my_capture_include_buttom, null);
        button_photo = (TextView) includeButtom.findViewById(R.id.button_photo);

        title = (LinearLayout) super.findViewById(R.id.act_capture_ll_title);
        buttom = (LinearLayout) super.findViewById(R.id.act_capture_ll_bottom);

        LayoutParams lpTitle = new LayoutParams(LayoutParams.MATCH_PARENT, convertDIP2PX(getApplicationContext(), 46));
        LayoutParams lpBottom = new LayoutParams(LayoutParams.MATCH_PARENT, convertDIP2PX(getApplicationContext(), 46));
        title.addView(includeTitle, lpTitle);
        buttom.addView(includeButtom, lpBottom);
        mTitleText.setText("邀请码");

        registeClick();

    }

    private void initIntentData() {
        mFinishActivityWhenScanFinish = getIntent().getIntExtra(EXTRA_IS_FINISH_ACTIVITY, 1);
    }

    // 转换dip为px
    public static int convertDIP2PX(Context context, int dip) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
    }

    private void registeClick() {
        mTitleLeftImv.setOnClickListener(this);
        button_photo.setOnClickListener(this);
    }

    private void clickBack() {
        finish();
    }

    private void clickPhoto() {
        selectImageFromAlbum();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_photo:
                clickPhoto();
                break;
            case R.id.title_img_left:
                clickBack();
                break;

            default:
                break;
        }

    }

    @Override
    public void handleDecode(Result result, Bitmap barcode) {

        final String resultString = result.getText();
        // FIXME
        if (resultString.equals("")) {
            Toast.makeText(MyCaptureActivity.this, "Scan failed!", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_RESULT_SUCCESS_STRING, resultString);
            if (mFinishActivityWhenScanFinish == 1) {
                setResult(RESULT_CODE_SCAN_SUCCESS, intent);
                finish();
                return;
            }

            AlertDialog resutlDialog = new AlertDialog.Builder(MyCaptureActivity.this).create();
            resutlDialog.setTitle("扫描结果");
            resutlDialog.setMessage(resultString);
            resutlDialog.setButton(AlertDialog.BUTTON_POSITIVE, "打开链接", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    if (!isLegalUrlParameters2(resultString)) // 如果url不合法
                    {
                        Toast.makeText(getApplicationContext(), "该链接不是合法的URL", Toast.LENGTH_SHORT).show();
                        // 实现连续扫描
                        handler = new CaptureActivityHandler(MyCaptureActivity.this, null, null);
                        handler.restartPreviewAndDecode();
                        return;
                    }
                    Intent intent = new Intent(); // 打开链接
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(resultString);
                    intent.setData(content_url);
                    startActivity(intent);
                    finish();
                }
            });

            resutlDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                    handler = new CaptureActivityHandler(MyCaptureActivity.this, null, null);
                    handler.restartPreviewAndDecode();

                }
            });
            resutlDialog.show();
        }
    }

    /**
     * 过滤url
     *
     * @param str
     * @return
     */
    protected boolean isLegalUrlParameters2(String str) {
        String strPattern = "[a-zA-z]+://[^\\s]*";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(str);
        return m.find();
    }

}
