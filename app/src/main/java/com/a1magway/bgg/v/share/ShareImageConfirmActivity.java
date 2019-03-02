package com.a1magway.bgg.v.share;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.a1magway.bgg.App;
import com.a1magway.bgg.R;
import com.a1magway.bgg.common.shre.WXShareTools;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.p.share.ConfrimShareImageAdapter;
import com.a1magway.bgg.refactor.AnimTransitionActivity;
import com.a1magway.bgg.util.AppManager;
import com.a1magway.bgg.util.DownloadShardImageUtils;
import com.a1magway.bgg.util.IntentKey;
import com.a1magway.bgg.util.dialog.ShareCopyHintDialog;
import com.a1magway.bgg.widget.divider.GridItemDecoration;
import com.almagway.common.utils.StatusBarUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by enid on 2018/8/8.
 */

public class ShareImageConfirmActivity extends AnimTransitionActivity implements ConfrimShareImageAdapter.OnItemClickListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.status_bar)
    LinearLayout statusBar;
    private List<String> shareData;
    private ConfrimShareImageAdapter adapter;
    public final static String ADD_IMAGE = "addImage";
    private String share_add_head_image;
    private String share_qr_image;
    private String goodsDescription;
    private int canCheckMaxCount;

    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_shard_image_confirm;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        hideTitle();
        StatusBarUtil.setMargin(this, statusBar);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        Intent intent = getIntent();
        shareData = intent.getStringArrayListExtra(IntentKey.SHARE_DATA);
        share_add_head_image = intent.getStringExtra(IntentKey.ADD_HEAD_IMAGE);
        share_qr_image = intent.getStringExtra(IntentKey.QR_CODE_IMAGE);
        goodsDescription = intent.getStringExtra(IntentKey.GOODS_DESCRIPTION);
        canCheckMaxCount = intent.getIntExtra(IntentKey.CAN_CHECK_MAX_COUNT, 0);
        if (shareData.size() < canCheckMaxCount) {
            shareData.add(ADD_IMAGE);
        }

        adapter = new ConfrimShareImageAdapter(this, shareData);
        adapter.addOnItemClickListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.addItemDecoration(new GridItemDecoration(this, R.color.transparent,
                R.dimen.product_grid_divider_v, R.dimen.product_grid_divider_v));
        recyclerView.setAdapter(adapter);
    }

    @OnClick({R.id.img_back, R.id.complete, R.id.share_pengyouquan, R.id.share_haoyou})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.complete:
                AppManager.getInstance().finishActivity(ShareImageSelectedActivity.class);
                finish();
                break;
            case R.id.share_haoyou:
                ShareCopyHintDialog.show(this, goodsDescription, new ShareCopyHintDialog.BtnClickListener() {
                    @Override
                    public void click() {
                        ShareCopyHintDialog.dismiss(ShareImageConfirmActivity.this);
                        startShare(0, "Kdescription test");
                    }
                });
                break;
            case R.id.share_pengyouquan:
                ShareCopyHintDialog.show(this, goodsDescription, new ShareCopyHintDialog.BtnClickListener() {
                    @Override
                    public void click() {
                        ShareCopyHintDialog.dismiss(ShareImageConfirmActivity.this);
                        startShare(1, "Kdescription test");
                    }
                });
                break;
        }
    }

    @Override
    public void onItemClick(int position) {
        if (position == adapter.getItemCount() - 1) {
            finish();
        }
    }

    private void startShare(int flag, String Kdescription) {
        List<String> stringList = new ArrayList<>();
        stringList.add(share_add_head_image);
        stringList.addAll(adapter.getShareData());
        stringList.add(share_qr_image);

        //封装分享的文件
        List<File> fileList = new ArrayList<>();
        for (String str : stringList) {
            File file = new File(DownloadShardImageUtils.getFileName(str));
            if (file.exists()) {
                fileList.add(file);
            }
        }
        WXShareTools.startShare(this, fileList, flag, Kdescription);
    }
}
