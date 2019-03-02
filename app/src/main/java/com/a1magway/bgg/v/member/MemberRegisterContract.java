package com.a1magway.bgg.v.member;

import android.content.Intent;
import com.a1magway.bgg.data.entity.MemberUpgradeInfo;
import com.a1magway.bgg.refactor.BaseContract;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * author: Beaven
 * date: 2017/10/31 15:04
 */

public interface MemberRegisterContract {

    interface View extends BaseContract.BaseView {

        void setAdapter(MultiTypeAdapter adapter);

        MemberUpgradeInfo getMemberUpgradeInfo();

        void setPriceNormal(MemberUpgradeInfo info);

        void setPriceSell(MemberUpgradeInfo info);

        void showEditIncompleteDialog();

        void showPayCheckDialog();

        void showPayDialog(String pay, Object s);
    }


    interface Presenter extends BaseContract.BasePresenter {

        void handleSelectAddressResult(int requestCode, int resultCode, Intent data);

        void selectPay(String pay);

        void updateUserInfo();

        void upgradeClick();
    }
}
