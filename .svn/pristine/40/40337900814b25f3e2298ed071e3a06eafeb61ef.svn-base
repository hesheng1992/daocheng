package com.a1magway.bgg.v.member;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.a1magway.bgg.R;
import com.a1magway.bgg.data.entity.MemberUpgradeInfo;
import com.a1magway.bgg.data.entity.WXPayData;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.member.DaggerMemberRegisterComponent;
import com.a1magway.bgg.di.module.member.MemberRegisterModule;
import com.a1magway.bgg.p.member.MemberRegisterP;
import com.a1magway.bgg.refactor.PresenterActivity;
import com.a1magway.bgg.util.StringFormatUtil;
import com.a1magway.bgg.util.pay.PayCallback;
import com.a1magway.bgg.util.pay.PaySelectCallback;
import com.a1magway.bgg.widget.dialog.CenterDialogFragment;
import com.a1magway.bgg.widget.dialog.PayDialogFragment;
import com.almagway.common.json.Json;
import me.drakeet.multitype.MultiTypeAdapter;

public class MemberRegisterActivity extends PresenterActivity<MemberRegisterP>
    implements MemberRegisterContract.View {

    private static final String MEMBER_PRICE_TAG = "member_price";


    public static void start(Context context, MemberUpgradeInfo memberUpgradeInfo) {
        Intent intent = new Intent(context, MemberRegisterActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(MEMBER_PRICE_TAG, memberUpgradeInfo);
        intent.putExtra(MEMBER_PRICE_TAG, bundle);
        context.startActivity(intent);
    }


    @BindView(R.id.recycler_member_register)
    RecyclerView recyclerView;
    @BindView(R.id.layout_member_list_price)
    LinearLayout layoutListPrice;
    @BindView(R.id.text_member_list_price)
    TextView textListPrice;
    @BindView(R.id.text_member_sell_price)
    TextView textSellPrice;

    private MemberUpgradeInfo info;
    private PayDialogFragment dialogFragment;


    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_member_register_new;
    }


    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        DaggerMemberRegisterComponent.builder().appComponent(appComponent)
            .memberRegisterModule(new MemberRegisterModule(this))
            .build().inject(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.handleSelectAddressResult(requestCode, resultCode, data);
    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        Bundle bundle = getIntent().getBundleExtra(MEMBER_PRICE_TAG);
        if (bundle != null) {
            info = (MemberUpgradeInfo) bundle.getSerializable(MEMBER_PRICE_TAG);
        }
    }


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setTextTitle(R.string.member_register);
    }


    @Override
    public void setAdapter(MultiTypeAdapter adapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public MemberUpgradeInfo getMemberUpgradeInfo() {
        return info;
    }


    @Override
    public void setPriceNormal(MemberUpgradeInfo info) {
        layoutListPrice.setVisibility(View.GONE);
        String text = StringFormatUtil.getPrice(this, String.valueOf(info.getSellPrice())) +
            getResources().getString(R.string.per_year);
        textSellPrice.setText(text);
    }


    @Override
    public void setPriceSell(MemberUpgradeInfo info) {
        layoutListPrice.setVisibility(View.VISIBLE);
        String sellPrice =
            StringFormatUtil.getPrice(this, String.valueOf(info.getSellPrice())) +
                getResources().getString(R.string.per_year);
        String listPrice =
            StringFormatUtil.getPrice(this, String.valueOf(info.getListPrice())) +
                getResources().getString(R.string.per_year);
        textSellPrice.setText(sellPrice);
        textListPrice.setText(listPrice);
        textListPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }


    @Override
    public void showEditIncompleteDialog() {
        DialogFragment dialog = CenterDialogFragment.newInstance(
            getResources().getString(R.string.please_input_user_data),
            getResources().getString(R.string.member_register_check_desc));
        dialog.show(getSupportFragmentManager(), "dialog");
    }


    @Override
    public void showPayCheckDialog() {
        dialogFragment = PayDialogFragment.newInstance("");
        dialogFragment.setSelectCallback(new PaySelectCallback() {
            @Override
            public void select(String pay) {
                presenter.selectPay(pay);
            }
        });
        dialogFragment.show(getSupportFragmentManager(), "PayDialog");
    }


    @Override
    public void showPayDialog(String pay, Object s) {
        if (dialogFragment == null) return;
        if (pay.equals(PayDialogFragment.ALI_TAG)) {
            dialogFragment.invokeAliPayMember(s.toString());
        } else if (pay.equals(PayDialogFragment.WECHAT_TAG)) {
            String json = Json.getJson(s);
            WXPayData data = Json.fromJson(json, WXPayData.class);
            dialogFragment.invokeWXPay(data.getObj());
        }
        dialogFragment.setPayCallback(new PayCallback() {
            @Override
            public void onSuccess(int payType) {
                dialogFragment.dismiss();
                presenter.updateUserInfo();
            }


            @Override
            public void onFailed(String msg) {
                dialogFragment.dismiss();
            }
        });
    }


    @OnClick(R.id.bt_member_upgrade)
    public void upgradeClick(View view) {
        presenter.upgradeClick();
    }
}
