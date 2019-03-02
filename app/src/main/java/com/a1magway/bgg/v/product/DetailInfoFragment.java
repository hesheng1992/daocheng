package com.a1magway.bgg.v.product;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PFragment;
import com.a1magway.bgg.data.entity.Commodity;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.module.product.ProDetailsInfoModule;
import com.a1magway.bgg.p.product.ProDetailsInfoP;
import com.a1magway.bgg.util.dialog.DialogUtil;
import com.a1magway.bgg.v.cart.CartActivity;
import com.a1magway.bgg.widget.PingtuanCountDownTextView;
import com.a1magway.bgg.widget.banner.BannerView;
import com.a1magway.bgg.widget.dialog.ProductChooseGuiGeDialpg;
import com.a1magway.bgg.widget.sku.AllSkuLayout;
import com.youth.banner.Transformer;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;

/**
 * 商品详情页上半部分页面
 * Created by jph on 2017/8/8.
 */
public class DetailInfoFragment extends PFragment<ProDetailsInfoP> implements IProDetailsInfoV {

    public static final String EXTRA_SELECTED_COMMODITY = "extra_selected_commodity";

    @BindView(R.id.info_banner)
    BannerView mBannerView;
    @BindView(R.id.info_txt_store_info)
    TextView mStoreInfoTxt;
    @BindView(R.id.info_txt_price)
    TextView mPriceTxt;
    @BindView(R.id.info_txt_origin_price)
    TextView mOriginPriceTxt;
    @BindView(R.id.info_txt_discount)
    TextView mDiscountTxt;
    @BindView(R.id.info_txt_stock)
    TextView mStockTxt;
    @BindView(R.id.info_layout_discount)
    ViewGroup mDiscountLayout;
    @BindView(R.id.info_layout_all_sku)
    AllSkuLayout mAllSkuLayout;
    @BindView(R.id.info_brand_tv)
    TextView mBrandTv;
    @BindView(R.id.info_title_tv)
    TextView mTitleTv;
    @BindView(R.id.details_info_collect_image)
    ImageView collectImage;

    @BindView(R.id.details_info_collect_layout)
    LinearLayout collectIayout;

    @BindView(R.id.info_txt_product_info)
    TextView mInfoProductInfo;

    @BindView(R.id.lly_ping_tuan_down_time)
    LinearLayout lly_ping_tuan_down_time;

    @BindView(R.id.lly_ping_tuan_content)
    LinearLayout lly_ping_tuan_content;

    @BindView(R.id.pingtuan_recyclerView)
    RecyclerView pingtuan_recyclerView;

    @BindView(R.id.product_detail_tv_down_time)
    PingtuanCountDownTextView product_detail_tv_down_time;

    @BindView(R.id.pingtuan_current_much_people)
    TextView pingtuan_current_much_people;

    @BindView(R.id.pingtuan_img_look_more)
    ImageView pingtuan_img_look_more;

    @BindView(R.id.pingtuan_img_look_text)
    TextView pingtuan_img_look_text;
    Unbinder unbinder;

    @BindView(R.id.lly_ping_tuan_count)
    LinearLayout lly_ping_tuan_count;
    @BindView(R.id.pingtuan_current_count_tv)
    TextView pingtuan_current_count_tv;

    private Observable<Map<String, String>> allSkuObservableDialog;
    /**
     * 显示规格弹框
     */
    private ProductChooseGuiGeDialpg productChooseGuiGeDialpg;

    private AllSkuLayout allSkuLayout;
    /**
     * 点击返回按钮
     *
     * @param v
     */
    @OnClick(R.id.details_info_img_back)
    public void onClickBack(View v) {
        getActivity().finish();
    }


    /**
     * 点击购物车
     */
    @OnClick(R.id.details_info_cart_tv)
    public void onClickCart(View v) {
        CartActivity.start(getContext());
    }

    /**
     * 点击收藏
     */
    @OnClick(R.id.details_info_collect_layout)
    public void onClickCollect() {
        mPresenter.clickCollect();
    }


    private static final String EXTRA_BRAND_NAME = "extra_brand_name";

    private static final String EXTRA_TITLE_NAME = "extra_title_name";


    public static DetailInfoFragment newInstance(String brand, String name) {
        Bundle args = new Bundle();
        args.putString(EXTRA_BRAND_NAME, brand);
        args.putString(EXTRA_TITLE_NAME, name);
        DetailInfoFragment fragment = new DetailInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);

        ((ProductDetailsActivity) getActivity()).getProDetailsComponent()
                .getInfoComponent(new ProDetailsInfoModule(this))
                .inject(this);
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.product_fragment_info;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        super.initView(savedInstanceState, contentView);
        productChooseGuiGeDialpg=new ProductChooseGuiGeDialpg(getActivity());
        productChooseGuiGeDialpg.setOnClickQueRenListenr(onClickQueRenListenr);

        allSkuLayout = productChooseGuiGeDialpg.getAllSkuLayout();
        mOriginPriceTxt.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线

        allSkuLayout.setSelectedCallback(new AllSkuLayout.SelectedCallback() {
            @Override
            public void onSelectedComplete(Map<String, String> selectedMap) {
                mPresenter.selectedCompleteSkuDilog(selectedMap);
            }
        });

        mAllSkuLayout.setSelectedCallback(new AllSkuLayout.SelectedCallback() {
            @Override
            public void onSelectedComplete(Map<String, String> selectedMap) {
                mPresenter.selectedCompleteSku(selectedMap);
            }
        });

        Bundle arguments = getArguments();
        if (arguments != null) {
            String brand = arguments.getString(EXTRA_BRAND_NAME);
            String title = arguments.getString(EXTRA_TITLE_NAME);
            mBrandTv.setText(brand);
            mTitleTv.setText(title);
        }
    }

    @Override
    public void showPingtuanMoreView(int count) {
        if (count < 4) {
            pingtuan_img_look_more.setVisibility(View.GONE);
            pingtuan_img_look_text.setVisibility(View.GONE);
        }
        lly_ping_tuan_content.setVisibility(View.VISIBLE);
        StringBuilder builder = new StringBuilder();
        builder.append(count)
                .append("人已发起拼团，可直接参与");
        pingtuan_current_much_people.setText(String.valueOf(builder));
    }

    @Override
    public void setPingtuanDownTime(long endTime) {
        lly_ping_tuan_down_time.setVisibility(View.VISIBLE);
        Date date = new Date();
        product_detail_tv_down_time.startTickWork(endTime - date.getTime(), PingtuanCountDownTextView.NORMAL);
    }

    @Override
    public void noPeoplePingtuan() {
        pingtuan_img_look_more.setVisibility(View.GONE);
        pingtuan_img_look_text.setVisibility(View.GONE);
        lly_ping_tuan_content.setVisibility(View.VISIBLE);
        pingtuan_current_much_people.setText("暂时还没人发起拼团，快去拼团吧！");
    }

    @Override
    public void showPingtuanCurrentCount(int count) {
        lly_ping_tuan_count.setVisibility(View.VISIBLE);
        pingtuan_current_count_tv.setText(String.valueOf(count));
    }

    @Override
    public void onStart() {
        super.onStart();
        mBannerView.setBannerAnimation(Transformer.Default);
        mBannerView.startAutoPlay();
    }

    @Override
    public void onDestroy() {
        product_detail_tv_down_time.stopTickWork();
        super.onDestroy();
    }

    @Override
    public void onStop() {
        super.onStop();
        mBannerView.stopAutoPlay();
    }

    @OnClick(R.id.info_txt_store_info)
    public void onClickStoreInfo(View v) {
        mPresenter.clickStoreInfo();
    }

    @OnClick(R.id.info_txt_size_intro)
    public void onClickSizeIntro(View v) {
        mPresenter.clickSizeIntro();
    }


    @Override
    public void showCoverBanner(List<String> coverList) {
        if (coverList == null) return;
        mBannerView
                .setDelayTime(3000)
                .setImages(coverList)
                .start();
    }

    @Override
    public void showStoreInfoTxt(String str) {
        mStoreInfoTxt.setText(str);
    }

    @Override
    public void showPriceTxt(String str, boolean isShow) {
        StringBuilder price = new StringBuilder();
        if (mPresenter.isCollege()) {
            price.append("拼成价:")
                    .append(str);
        } else {
            price.append(str);
        }
        if (isShow) {
            mPriceTxt.setVisibility(View.VISIBLE);
            mPriceTxt.setText(price);
        } else {
            mPriceTxt.setVisibility(View.GONE);
        }
    }

    @Override
    public void showOriginPriceTxt(String str) {
        mOriginPriceTxt.setText(str);
    }

    @Override
    public void showDiscountInfoTxt(String str) {
        mDiscountTxt.setText(str);
    }

    @Override
    public void showDiscountTxtVisible(boolean visible) {
        mDiscountLayout.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showStockTxt(String str) {
        mStockTxt.setText(str);
    }

    //选择了dialog对话框弹出的选择规格之后回调。显示库存和数量
    @Override
    public void showCommDialogCallBackData(Commodity mSelectedCommodityDialog) {
        productChooseGuiGeDialpg.upDateNumber(mSelectedCommodityDialog.getTotalStock(),true);
    }

    @Override
    public void showSkuContent(Observable<Map<String, String>> allSkuObservable) {
        allSkuObservableDialog=allSkuObservable;
        mAllSkuLayout.setData(allSkuObservable);
        allSkuLayout.setData(allSkuObservable);
    }

    //加载数据进来
    @Override
    public void setSelectedSku(Map<String, String> selectedSku) {
//        productChooseGuiGeDialpg.show();
//        allSkuLayout.setSelectedSku(selectedSku);
        mAllSkuLayout.setSelectedSku(selectedSku);
    }

    @Override
    public void showStoreInfoDialog(String message) {

        Dialog dialog = new AlertDialog.Builder(getContext())
                .setMessage(message)
                .setPositiveButton(R.string.dialog_confirm,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                .create();
        DialogUtil.showDialog(dialog, getChildFragmentManager(), "Store_Info_Dialog");
    }

    @Override
    public boolean isSelectedWholeSku() {
        return mAllSkuLayout.isSelectedComplete();
//        return allSkuLayout.isSelectedComplete();
    }

    @Override
    public boolean isSelectedWholeSkuDialog() {
        return allSkuLayout.isSelectedComplete();
//        return allSkuLayout.isSelectedComplete();
    }
    /**
     * 判断是加购还是立即购买
     */
    private int flag=0;
    /**
     * 点击加入购物车，弹出框
     * @return
     */
    public Serializable getSelectedCommodity(int flag) {
        boolean isNoUpdate=false;
        if (flag==0){
            return  mPresenter.getSelectedCommodity();
            //拼团数量为1 不能更改
        }else if (flag==3){
            isNoUpdate=true;
        }
        this.flag=flag;
        //获取当前显示的规格
        Commodity selectedCommodity = (Commodity) mPresenter.getSelectedCommodity();
        if(selectedCommodity!=null){
            HashMap<String, String> specsMap = selectedCommodity.getSpecsMap();
            mPresenter.setmSelectedCommodityDialogInside(selectedCommodity);
            //初次弹出对话框，如果有数据，就显示库存
            productChooseGuiGeDialpg.upDateNumber(selectedCommodity.getTotalStock(),true);
            //显示到
            allSkuLayout.setSelectedSku(specsMap);
        }else{
            allSkuLayout.setData(allSkuObservableDialog);
            mPresenter.setmSelectedCommodityDialog();
            productChooseGuiGeDialpg.upDateNumber(0,false);
        }
        productChooseGuiGeDialpg.setNumNoUpdate(isNoUpdate);
        productChooseGuiGeDialpg.show();
        return  mPresenter.getSelectedCommodity();
    }


    private ProductChooseGuiGeDialpg.OnClickQueRenListenr onClickQueRenListenr
            =new ProductChooseGuiGeDialpg.OnClickQueRenListenr() {
        @Override
        public Serializable onClickQueRen(int num) {

            if (getActivity() instanceof ProductDetailsActivity){
                ProductDetailsActivity productDetailsActivity= (ProductDetailsActivity) getActivity();
                productDetailsActivity.getSelectCommodity((Commodity) mPresenter.getSelectedCommodityDialog(),flag,num);
            }
            return null;
        }
    };

    @Override
    public void showIsCollected(boolean isCollected) {
        if (isCollected) {
            collectImage.setImageResource(R.drawable.ic_product_collecte_collected);
        } else {
            collectImage.setImageResource(R.drawable.ic_product_collecte_normal);
        }
    }

    @Override
    public void showProductInfo(String info) {
        if (info == null || info.trim().isEmpty()) {
            mInfoProductInfo.setVisibility(View.GONE);
        } else {
            mInfoProductInfo.setVisibility(View.VISIBLE);
            mInfoProductInfo.setText(info);
        }
    }

    @Override
    public void setPingtuanRecyclerView(RecyclerView.Adapter adapter) {
        pingtuan_recyclerView.setNestedScrollingEnabled(false);
        pingtuan_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pingtuan_recyclerView.setAdapter(adapter);
    }

    /**
     * 商品详情里面拼团查看更多
     */
    @OnClick({R.id.pingtuan_img_look_more, R.id.pingtuan_img_look_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pingtuan_img_look_more:
            case R.id.pingtuan_img_look_text:
                mPresenter.showMorePingtuanDialog();
                break;
        }
    }
}
