package com.a1magway.bgg.util;

import android.content.Context;
import android.content.Intent;

import com.a1magway.bgg.common.shre.ShareData;
import com.a1magway.bgg.v.authorization.AuthorizationBookActivity;
import com.a1magway.bgg.v.friend.InviteFriendActivity;
import com.a1magway.bgg.v.invitation.InvitationGuideActivity;
import com.a1magway.bgg.v.share.ShareImageConfirmActivity;
import com.a1magway.bgg.v.share.ShareImageSelectedActivity;
import com.a1magway.bgg.v.upgrade.UpgradePayActivity;
import com.a1magway.bgg.v.login.UserBindPhoneActivity;
import com.a1magway.bgg.v.upgrade.UpgradeGuidePager1Activity;
import com.a1magway.bgg.v.upgrade.UpgradeGuidePager2Activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by enid on 2018/8/3.
 */

public class ActivityIntentUtil {

    public static void toBindPhoneActivity(Context context) {
        Intent intent = new Intent(context, UserBindPhoneActivity.class);
        context.startActivity(intent);
    }

    public static void toInvitationGuideActivity(Context context, String title, int backId) {
        Intent intent = new Intent(context, InvitationGuideActivity.class);
        intent.putExtra(IntentKey.TITLE, title);
        intent.putExtra(IntentKey.BACK_ID, backId);
        context.startActivity(intent);
    }


    public static void toUpgradeGuidePager1Activity(Context context) {
        Intent intent = new Intent(context, UpgradeGuidePager1Activity.class);
        context.startActivity(intent);
    }

    public static void toUpgradeGuidePager2Activity(Context context, String type) {
        Intent intent = new Intent(context, UpgradeGuidePager2Activity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    public static void toUpgradePayActivity(Context context, int money) {
        Intent intent = new Intent(context, UpgradePayActivity.class);
        intent.putExtra("pay_money", money);
        context.startActivity(intent);
    }

    public static void toAuthorizationBookActivity(Context context) {
        Intent intent = new Intent(context, AuthorizationBookActivity.class);
        context.startActivity(intent);
    }


    public static void toShareImageSelectedActivity(Context context, ShareData mShareData, int flag, String goodsDescription
            , int downFailImageCount) {
        Intent intent = new Intent(context, ShareImageSelectedActivity.class);
        intent.putExtra(IntentKey.SHARE_DATA, mShareData);
        intent.putExtra(IntentKey.FLAG, flag);
        intent.putExtra(IntentKey.GOODS_DESCRIPTION, goodsDescription);
        intent.putExtra(IntentKey.DOWN_FAIL_IMAGE_COUNT, downFailImageCount);
        context.startActivity(intent);
    }

    public static void toShareImageConfirmActivity(Context context, List<String> mShareData, String addHeadImage, String QrCodeImage, String goodsDescription,
                                                   int canCheckMaxCount) {
        Intent intent = new Intent(context, ShareImageConfirmActivity.class);
        intent.putStringArrayListExtra(IntentKey.SHARE_DATA, (ArrayList<String>) mShareData);
        intent.putExtra(IntentKey.ADD_HEAD_IMAGE, addHeadImage);
        intent.putExtra(IntentKey.QR_CODE_IMAGE, QrCodeImage);
        intent.putExtra(IntentKey.GOODS_DESCRIPTION, goodsDescription);
        intent.putExtra(IntentKey.CAN_CHECK_MAX_COUNT, canCheckMaxCount);
        context.startActivity(intent);
    }

    /**
     * 到邀请好友升级界面
     */
    public static void toInviteFriendActivity(Context context) {
        Intent intent = new Intent(context, InviteFriendActivity.class);
        context.startActivity(intent);
    }
}
