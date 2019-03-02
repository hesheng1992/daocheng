package com.a1magway.bgg.widget.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a1magway.bgg.App;
import com.a1magway.bgg.R;
import com.a1magway.bgg.common.shre.ShareData;
import com.a1magway.bgg.common.shre.ShareType;
import com.a1magway.bgg.common.shre.ShareUtils;
import com.a1magway.bgg.common.shre.WXShareTools;
import com.a1magway.bgg.v.share.ShareFeature;
import com.almagway.common.utils.ToastUtil;
import com.almagway.umeng.PlatformType;
import com.almagway.umeng.UmShare;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by enid on 2018/6/12.
 */

public class ShareDialogFragment extends BottomDialogFragment {

    @BindView(R.id.dialog_share_recycler)
    RecyclerView mShareRecyclerView;

    private static final String SHARE_EXTRA_NAME = "share_extra_name";
    private static final String SHARD_INVITATION_CODE = "shard_invitationCode";
    private static final String GOODS_DESCRIPTION = "goodsDescription";
    private static final String SHARD_TYPE = "shard_type";
    public static final String PRODUCT_DETAIL_SHARD = "product_detail_shard";//来自产品详情页面的分享
    public static final String MY_INVITATIONCODE_SHARD = "my_invitationcode_shard";//来自我的邀请码页面的分享
    public static final String ORDER_LIST_SHARD = "order_list_shard";//来自订单列表邀请好友拼团的分享
    private ShareData mShareData;

    @OnClick(R.id.dialog_share_cancel_tv)
    public void onClickEvent(View view) {
        dismiss();
    }

    private ShareAdapter shareAdapter;

    private String shardQRcodeImageUrl;
    private boolean shard_invitationCode;//是否来自分享邀请码页面
    private String goodsDescription;//商品描述
    private static String shardType;
    private String miniTitle;//分享小程序的标题
    private String miniImageUrl;//分享小程序的封面
    private String miniPath;//分享小程序的页面路径

    //设置分享的小程序信息
    public void setMiniInfo(String miniTitle, String miniImageUrl, String miniPath) {
        this.miniTitle = miniTitle;
        this.miniImageUrl = miniImageUrl;
        this.miniPath = miniPath;
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.dialog_share_custom;
    }


    public static ShareDialogFragment newInstance(ShareData data, String goodsDescription, String shardType) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(SHARE_EXTRA_NAME, data);
        bundle.putString(SHARD_TYPE, shardType);
        bundle.putString(GOODS_DESCRIPTION, goodsDescription);
        ShareDialogFragment fragment = new ShareDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mShareData = (ShareData) getArguments().getSerializable(SHARE_EXTRA_NAME);
        goodsDescription = getArguments().getString(GOODS_DESCRIPTION);
        shardType = getArguments().getString(SHARD_TYPE, "");
        int spanCount = 3;
        switch (shardType) {
            case PRODUCT_DETAIL_SHARD:
                spanCount = 2;
                break;
            case MY_INVITATIONCODE_SHARD:
                spanCount = 3;
                shard_invitationCode = true;
                break;
            case ORDER_LIST_SHARD:
                spanCount = 1;
                break;
        }
        mShareRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), spanCount));
        List<ShareFeature> shareFeature = getShareFeature(shard_invitationCode);
        shareAdapter = new ShareAdapter(shareFeature);
        mShareRecyclerView.setAdapter(shareAdapter);
    }

    public static final List<ShareFeature> getShareFeature(boolean shard_invitationCode) {
        List<ShareFeature> shareFeatures = new ArrayList<>();
        switch (shardType) {
            case PRODUCT_DETAIL_SHARD:
                shareFeatures.add(new ShareFeature(ShareFeature.WEIXIN_MINI_TAG, R.drawable.icon_wechat_mini, "小程序"));
                shareFeatures.add(new ShareFeature(ShareFeature.WEIXIN_TAG, R.drawable.icon_wechat, "微信"));
                break;
            case MY_INVITATIONCODE_SHARD:
                shareFeatures.add(new ShareFeature(ShareFeature.WEIXIN_MINI_TAG, R.drawable.icon_wechat_mini, "小程序"));
                shareFeatures.add(new ShareFeature(ShareFeature.WEIXIN_TAG, R.drawable.icon_wechat, "微信"));
                shareFeatures.add(new ShareFeature(ShareFeature.WEIXIN_CIRCLE_TAG, R.drawable.icon_wxcircle, "朋友圈"));
                break;
            case ORDER_LIST_SHARD:
                shareFeatures.add(new ShareFeature(ShareFeature.WEIXIN_MINI_TAG, R.drawable.icon_wechat_mini, "小程序"));
                break;
        }
        return shareFeatures;
//        shareFeatures.add(new ShareFeature(ShareFeature.WEIXIN_MINI_TAG, R.drawable.icon_wechat_mini, "小程序"));
//        shareFeatures.add(new ShareFeature(ShareFeature.QQ_TAG,R.drawable.icon_qq,"QQ"));
//        shareFeatures.add(new ShareFeature(ShareFeature.QZONE_TAG,R.drawable.icon_qzone,"QQ空间"));
//        shareFeatures.add(new ShareFeature(ShareFeature.WEIXIN_TAG, R.drawable.icon_wechat, "微信"));
//        shareFeatures.add(new ShareFeature(ShareFeature.WEIXIN_CIRCLE_TAG, R.drawable.icon_wxcircle, "朋友圈"));
//        shareFeatures.add(new ShareFeature(ShareFeature.SINA_TAG, R.drawable.icon_sina, "微博"));
//        return shareFeatures;
    }

    List<ShareFeature> mShareFeature;

    class ShareAdapter extends RecyclerView.Adapter<ViewHolder> {

        public ShareAdapter(List<ShareFeature> shareFeatures) {
            mShareFeature = shareFeatures;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_share_custom, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mIconImg.setImageResource(mShareFeature.get(position).getDrawableId());
            holder.mIconText.setText(mShareFeature.get(position).getText());
            holder.mIconImg.setOnClickListener(new ShareItemOnClickListener(holder, position));
        }

        @Override
        public int getItemCount() {
            return mShareFeature.size();
        }
    }

    class ShareItemOnClickListener implements View.OnClickListener {
        private ViewHolder holder;
        private int position;

        public ShareItemOnClickListener(ViewHolder holder, int position) {
            this.holder = holder;
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            switch (mShareFeature.get(position).getId()) {
                case ShareFeature.WEIXIN_MINI_TAG:
                    if (!ShareUtils.isWeixinAvilible(App.getContext())) {
                        ToastUtil.showShort("微信未安装");
                        dismiss();
                        return;
                    }
                    new UmShare(getActivity()).shareMini(miniTitle, miniImageUrl, miniPath);
//                    new UmShare(getActivity()).share(mShareData.getUrl(), mShareData.getTitle(), mShareData.getDescription(), PlatformType.WEIXIN);
                    break;
                case ShareFeature.QQ_TAG:
                    new UmShare(getActivity()).share(mShareData.getUrl(), mShareData.getTitle(), mShareData.getDescription(), PlatformType.QQ);
                    break;
                case ShareFeature.QZONE_TAG:
                    new UmShare(getActivity()).share(mShareData.getUrl(), mShareData.getTitle(), mShareData.getDescription(), PlatformType.QZONE);
                    break;
                case ShareFeature.WEIXIN_TAG:
                    if (mShareData.getType().equals(ShareType.SYSTEM)) {
                        WXShareTools.getInstance().shareImags(mShareData, getContext(), 0, "Kdescription test",
                                shard_invitationCode, goodsDescription);
                    } else {
                        new UmShare(getActivity()).share(mShareData.getUrl(), mShareData.getTitle(), mShareData.getDescription(), PlatformType.WEIXIN);
                    }
                    break;
                case ShareFeature.WEIXIN_CIRCLE_TAG:
                    if (mShareData.getType().equals(ShareType.SYSTEM)) {
//                        ToastUtil.showLong("请稍后...");
                        WXShareTools.getInstance().shareImags(mShareData, getContext(), 1, "Kdescription test",
                                shard_invitationCode, goodsDescription);
                    } else {
                        new UmShare(getActivity()).share(mShareData.getUrl(), mShareData.getTitle(), mShareData.getDescription(), PlatformType.WEIXIN_CIRCLE);
                    }
                    break;
                case ShareFeature.SINA_TAG:
                    if (mShareData.getType().equals(ShareType.SYSTEM)) {
//                        ToastUtil.showLong("请稍后...");
                        WXShareTools.getInstance().shareImags(mShareData, getContext(), 2, "Kdescription test",
                                shard_invitationCode, goodsDescription);
                    } else {
                        new UmShare(getActivity()).openPic(mShareData.getUrl(), mShareData.getTitle(), mShareData.getDescription(), mShareData.getMediaPath());
                    }
                    break;
            }
            dismiss();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIconImg;
        private TextView mIconText;

        public ViewHolder(View itemView) {
            super(itemView);
            mIconImg = itemView.findViewById(R.id.item_share_icon);
            mIconText = itemView.findViewById(R.id.item_share_text);
        }
    }
}
