package com.a1magway.bgg.v.personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PFragment;
import com.a1magway.bgg.data.entity.LoginData;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.DaggerPersonalComponent;
import com.a1magway.bgg.di.module.PersonalModule;
import com.a1magway.bgg.p.personal.PersonalFeature;
import com.a1magway.bgg.p.personal.PersonalFeatureAdapter;
import com.a1magway.bgg.p.personal.PersonalP;
import com.a1magway.bgg.util.ActivityIntentUtil;
import com.a1magway.bgg.util.AppUtils;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.ImageLoaderUtil;
import com.a1magway.bgg.util.JumpUtil;
import com.a1magway.bgg.v.account.MyWalletActivity;
import com.a1magway.bgg.v.articleManager.ArticleManagerActivity;
import com.a1magway.bgg.v.articleManager.ArticleManagerWebActivity;
import com.a1magway.bgg.v.collection.CollectionActivity;
import com.a1magway.bgg.v.member.MemberCardActivity;
import com.a1magway.bgg.v.member.MemberUpgradeInfoActivity;
import com.a1magway.bgg.v.order.OrderListActivity;
import com.a1magway.bgg.v.productReturn.AfterSaleListActivity;
import com.a1magway.bgg.v.saleorder.SaleOrderListActivity;
import com.a1magway.bgg.v.web.WebActivity;
import com.a1magway.bgg.widget.divider.GridItemDecoration;
import com.almagway.common.AppConfig;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * @author Beaven
 * @date 2017/10/11 18:26
 */
public class PersonalNewFragment extends PFragment<PersonalP> implements IPersonalV {

    private static final String TAG = PersonalNewFragment.class.getSimpleName();

    @BindView(R.id.recycler_personal)
    RecyclerView recyclerView;

    @BindView(R.id.text_personal_name)
    TextView textPersonalName;

    @BindView(R.id.text_personal_login_exit)
    TextView textPersonalLoginExit;

    @BindView(R.id.image_personal_avatar)
    ImageView imageAvatar;

    // @BindView(R.id.text_personal_member_grade)
    // TextView mMemberGradText;


    @BindView(R.id.lly_login_show)
    LinearLayout llyLoginShow;
    @BindView(R.id.tv_login_or_register)
    TextView tvLoginOrRegister;
    @BindView(R.id.lly_no_login_show)
    LinearLayout llyNoLoginShow;
    @BindView(R.id.personal_version)
    TextView personal_version;
    @BindView(R.id.member_rank)
    TextView member_rank;
    @BindView(R.id.upgrade_grade_right_line)
    View upgrade_grade_right_line;
    @BindView(R.id.upgrade_grade)
    TextView upgrade_grade;
    @BindView(R.id.member_icon)
    ImageView member_icon;
    @BindView(R.id.image_personal_parent)
    RelativeLayout image_personal_parent;

    @Inject
    PersonalFeatureAdapter adapter;

    @Override
    public int getContentViewLayoutId() {
        return R.layout.personal_fragment_personal_new;
    }


    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        DaggerPersonalComponent.builder()
                .appComponent(appComponent)
                .personalModule(new PersonalModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        super.initView(savedInstanceState, contentView);
        LoginData data = GlobalData.getInstance().getLoginData();
        if (data == null) {
            showNoLoginLayout();
        } else {
            textPersonalName.setText(data.getNickName());
            setRankName(GlobalData.getInstance().getLoginData().getMemberGradeName());
            showLoginLayout();
            hideShowUpgradeText(data.getMemberGrade());
        }
        notifyFeatureList();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(
                new GridItemDecoration(
                        getContext(),
                        R.color.transparent,
                        R.dimen.product_detail_divider,
                        R.dimen.product_detail_divider));
        adapter.setOnClickListener(
                new PersonalFeatureAdapter.OnClickListener() {
                    @Override
                    public void onClick(int id) {
                        personalFeatureClick(id);
                    }
                });
        recyclerView.setAdapter(adapter);

        if (data != null && data.getIconPath() != null) {
            ImageLoaderUtil.loadCircleImage(getContext(), imageAvatar, data.getIconPath());
            image_personal_parent.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.user_icon_bg));
        }

        //显示版本号
        personal_version.setText(String.valueOf("v" + AppUtils.getAppVersionName(getContext())));
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        Log.d(TAG, "initData() execute");
    }

    @Override
    public void onResume() {
        super.onResume();
        notifyFeatureList();
    }

    @Override
    public void setViewEnable(boolean enable) {
    }

    @Override
    public void setButtonText(String str) {
//        textPersonalLoginExit.setText(str);
    }

    @Override
    public void setUserName(String name) {
        textPersonalName.setText(name);
    }

    @Override
    public void setMemberGrade(String gradeName) {
//        if (TextUtils.isEmpty(gradeName)) {
//            mMemberGradText.setVisibility(View.GONE);
//        } else {
////            mMemberGradText.setVisibility(View.VISIBLE);
//            mMemberGradText.setText(gradeName);
//        }
//        member_rank.setText(String.valueOf("Hi~" + gradeName));
        setRankName(gradeName);
        int memberGrade = GlobalData.getInstance().getLoginData().getMemberGrade();
        switch (memberGrade) {
            case 0:
            case 1:
            case 2:
            case 3:
                member_icon.setImageResource(R.mipmap.member_putong);
                break;
            case 4:
            case 5:
                member_icon.setImageResource(R.mipmap.member_maishou);
                break;
            case 6:
                member_icon.setImageResource(R.mipmap.member_daili);
            default:
                member_icon.setImageResource(R.mipmap.member_daili);
                break;
        }
    }

    @Override
    public void notifyFeatureList() {
        boolean isLogin;
        boolean isMember;
        LoginData data = GlobalData.getInstance().getLoginData();
        if (data == null) {
            isMember = isLogin = false;
        } else {
            isLogin = true;
            isMember =
                    data.getRoleType() == GlobalData.USER_ROLE_TYPE_MEMBER_DEFAULT
                            || data.getRoleType() == GlobalData.USER_ROLE_TYPE_MEMBER_AND_WANDA;
        }
        adapter.setFeatureList(mPresenter.getFeatureNew(isLogin, isMember));
        adapter.notifyDataSetChanged();
        int avaId =
                isMember ? R.drawable.ic_personal_ava_member : R.drawable.ic_personal_ava_default;
        if (data != null && data.getIconPath() != null) {
            ImageLoaderUtil.loadCircleImage(getContext(), imageAvatar, data.getIconPath());
            image_personal_parent.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.user_icon_bg));
        }
    }

    @Override
    public void showLoginLayout() {
        llyLoginShow.setVisibility(View.VISIBLE);
        llyNoLoginShow.setVisibility(View.GONE);
        member_icon.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoLoginLayout() {
        llyNoLoginShow.setVisibility(View.VISIBLE);
        llyLoginShow.setVisibility(View.GONE);
        member_icon.setVisibility(View.GONE);
        //退出恢复默认头像
        image_personal_parent.setBackgroundColor(getResources().getColor(R.color.black));
        imageAvatar.setImageResource(R.drawable.ic_personal_ava_default);
    }

    @Override
    public void hideShowUpgradeText(int memberGrade) {
        if (memberGrade >= 6) {
            upgrade_grade.setVisibility(View.GONE);
            upgrade_grade_right_line.setVisibility(View.GONE);
        } else {
            upgrade_grade.setVisibility(View.VISIBLE);
            upgrade_grade_right_line.setVisibility(View.VISIBLE);
        }
    }


    @OnClick({R.id.text_personal_login_exit, R.id.tv_login_or_register, R.id.image_personal_avatar, R.id.upgrade_grade})
    public void login(View view) {
        switch (view.getId()) {
            case R.id.text_personal_login_exit:
            case R.id.tv_login_or_register:
                mPresenter.onBtnClick(PersonalNewFragment.this);
                notifyFeatureList();
                break;
            case R.id.image_personal_avatar:
                break;
            case R.id.upgrade_grade:
                //判断会员等级
                int memberGrade = GlobalData.getInstance().getLoginData().getMemberGrade();
                if (memberGrade<2){
                    if (GlobalData.getInstance().getLoginData().getInviterName() != null) {
                        InvitationCodeActivity.start(getContext());
                    } else {
                        ActivityIntentUtil.toInvitationGuideActivity(getContext(), "我的邀请码", R.drawable.ic_arrow_left);
                    }
                }
                ActivityIntentUtil.toUpgradeGuidePager1Activity(getContext());
                break;
        }

    }

    @OnClick({R.id.personal_order_tv_1, R.id.personal_order_tv_2, R.id.personal_order_tv_3, R.id.personal_order_tv_4})
    public void clickOrder(View view) {
        OrderListActivity.start(getContext());
    }

    private void personalFeatureClick(int id) {
        switch (id) {
            case PersonalFeature.MY_COLLECT_TAG://我的收藏
                CollectionActivity.start(getContext());
                break;
            case PersonalFeature.PERSONAL_SETTING_TAG://个人设置
                PersonalSettingActivity.startPersonalCenterActivity(getContext());
//                InviteFriendBuyDialog.show(getActivity(),100000);
//                CrowdorderingFinishDialog.show(getActivity());
                break;
            case PersonalFeature.ORDER_MANAGE_TAG://订单管理
                OrderListActivity.start(getContext());
                break;
            case PersonalFeature.SALE_ORDER_TAG://销售订单
                SaleOrderListActivity.start(getContext());
                break;
            case PersonalFeature.ADDRESS_MANAGE_TAG://地址管理
                AddressManagerActivity.startActivityForResult(getActivity(), false);
                break;
            case PersonalFeature.MY_WALLET_TAG://我的钱包
                MyWalletActivity.start(getContext());
                break;
            case PersonalFeature.INVITATION_CODE_TAG://邀请码
                if (GlobalData.getInstance().getLoginData().getInviterName() != null) {
                    InvitationCodeActivity.start(getContext());
                } else {
                    ActivityIntentUtil.toInvitationGuideActivity(getContext(), "我的邀请码", R.drawable.ic_arrow_left);
                }
                break;
            case PersonalFeature.CONTACT_TAG://联系我们
//                mPresenter.huanxinLogin("test1", "123456");//用于测试
                mPresenter.hxLoginInfoGet();
                break;
            case PersonalFeature.BUY_NOTE_TAG://购买须知//TODO
//                WebActivity.start(
//                        getContext(), getString(R.string.personal_contact_buy_notice_url));

                WebActivity.start(
                        getContext(), String.format(AppConfig.BASE_URL + "/pictures/platform/commodity/picture/html/buyNotice.html"), "购买须知", false);
                break;
            case PersonalFeature.CARD_CENTER_TAG://卡券中心
                int roleType = GlobalData.getInstance().getLoginData().getRoleType();
                if (roleType == GlobalData.USER_ROLE_TYPE_MEMBER_DEFAULT
                        || roleType == GlobalData.USER_ROLE_TYPE_MEMBER_AND_WANDA) {
                    MemberCardActivity.start(getContext());
                } else {
                    MemberUpgradeInfoActivity.start(getContext());
                }
                break;
            case PersonalFeature.AUTHORIZATION_BOOK://授权书
                ActivityIntentUtil.toAuthorizationBookActivity(getContext());
                break;
            case PersonalFeature.ARTICLE_MANAGER://文章管理
//                ArticleManagerWebActivity.start(getContext(),"https://www.baidu.com/");
                ArticleManagerActivity.start(getContext());
                break;
            case PersonalFeature.MY_FRIEND://我的好友
                ActivityIntentUtil.toInviteFriendActivity(getContext());
                break;
                //售后
            case PersonalFeature.After_sale:
                Intent intent=new Intent(getContext(),AfterSaleListActivity.class);
                getActivity().startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.hxLoginInfoRelease();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
//            textPersonalLoginExit.setText(getString(R.string.personal_exit));
//            textPersonalName.setText(GlobalData.getInstance().getLoginData().getTelephone());
            showLoginLayout();
            textPersonalName.setText(GlobalData.getInstance().getLoginData().getNickName());
//            mMemberGradText.setText(GlobalData.getInstance().getLoginData().getMemberGradeName());
//            member_rank.setText(String.valueOf("Hi~" + GlobalData.getInstance().getLoginData().getMemberGradeName()));
            setRankName(GlobalData.getInstance().getLoginData().getMemberGradeName());
//            mMemberGradText.setVisibility(View.VISIBLE);
            notifyFeatureList();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            mPresenter.updateUserInfo();
        }
    }

    private void setRankName(String rankName) {
        if (rankName.trim().isEmpty()) {
            member_rank.setText(rankName.trim());
        } else {
            member_rank.setText(String.valueOf("Hi~" + rankName));
        }
    }


}
