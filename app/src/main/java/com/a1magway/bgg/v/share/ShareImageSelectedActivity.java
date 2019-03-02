package com.a1magway.bgg.v.share;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.shre.ShareData;
import com.a1magway.bgg.data.entity.ShareImageSelectedData;
import com.a1magway.bgg.p.share.SelectShareImageAdapter;
import com.a1magway.bgg.refactor.AnimTransitionActivity;
import com.a1magway.bgg.util.ActivityIntentUtil;
import com.a1magway.bgg.util.IntentKey;
import com.a1magway.bgg.util.dialog.ShareCopyHintDialog;
import com.a1magway.bgg.widget.divider.GridItemDecoration;
import com.almagway.common.utils.StatusBarUtil;
import com.almagway.common.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by enid on 2018/8/7.
 */

public class ShareImageSelectedActivity extends AnimTransitionActivity implements SelectShareImageAdapter.OnItemClickListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.status_bar)
    LinearLayout statusBar;
    private ShareData mShareData;
    private List<ShareImageSelectedData> shareList;
    private SelectShareImageAdapter adapter;
    private int flag = -1; // 0分享到微信好友，1分享到朋友圈
    private String goodsDescription;
    private int downFailImageCount;
    private int canCheckMaxCount;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_shard_image_selected;
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
        mShareData = (ShareData) getIntent().getSerializableExtra(IntentKey.SHARE_DATA);
        flag = getIntent().getIntExtra(IntentKey.FLAG, -1);
        goodsDescription = getIntent().getStringExtra(IntentKey.GOODS_DESCRIPTION);
        downFailImageCount = getIntent().getIntExtra(IntentKey.DOWN_FAIL_IMAGE_COUNT, 0);
        canCheckMaxCount = downFailImageCount + 7;
        shareList = new ArrayList<>();
        List<String> allImage = mShareData.getMediaPath();
        //封装adapter数据，去除掉qr和添加到头的图片
        for (String path : allImage) {
            ShareImageSelectedData data;
            if (shareList.size() < canCheckMaxCount) {
                //小于7张默认选中
                data = new ShareImageSelectedData(path, true);
            } else {
                data = new ShareImageSelectedData(path, false);
            }
            shareList.add(data);
        }
        adapter = new SelectShareImageAdapter(this, shareList);
        adapter.addOnItemClickListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.addItemDecoration(new GridItemDecoration(this, R.color.transparent,
                R.dimen.product_grid_divider_v, R.dimen.product_grid_divider_v));
        recyclerView.setAdapter(adapter);
    }

    @OnClick({R.id.img_selected_complete, R.id.img_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_selected_complete:
                if (adapter.getSelectCount() < 1) {
                    ToastUtil.showShort("分享图片数量不能为0");
                } else {
                    ActivityIntentUtil.toShareImageConfirmActivity(this,
                            adapter.getSelectImageUrls(), mShareData.getShardAddHeadImageUrl(),
                            mShareData.getShardQRcodeImageUrl(), goodsDescription,canCheckMaxCount);
                }
                break;
        }
    }


    @Override
    public void onItemClick(int position) {
        ShareImageSelectedData shareImageSelectedData = adapter.getData().get(position);
        if (shareImageSelectedData.isChecked()) {
            shareImageSelectedData.setChecked(false);
        } else {
            if (adapter.getSelectCount() < canCheckMaxCount) {
                shareImageSelectedData.setChecked(true);
            } else {
                ToastUtil.showShort("分享图片数量不能大于"+canCheckMaxCount+"张");
            }
        }
        adapter.notifyDataSetChanged();
    }
}
