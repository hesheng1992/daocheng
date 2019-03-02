package com.a1magway.bgg.util.dialog;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.lang.reflect.Field;

/**
 * 弹窗工具类
 * Created by jph on 2016/11/28.
 */
public class DialogUtil {

    /**
     * 避免出现 java.lang.IllegalStateException
     * Can not perform this action after onSaveInstanceState
     *
     * @param manager
     * @param tag
     */
    public static void showDialogAllowingStateLoss(FragmentManager manager, String tag,
                                                   DialogFragment dialogFragment) {
        try {
            Field dismissed = DialogFragment.class.getDeclaredField("mDismissed");
            dismissed.setAccessible(true);
            dismissed.set(dialogFragment, false);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            Field shown = DialogFragment.class.getClass().getDeclaredField("mShownByMe");
            shown.setAccessible(true);
            shown.set(dialogFragment, true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        FragmentTransaction ft = manager.beginTransaction();
        ft.add(dialogFragment, tag);
        ft.commitAllowingStateLoss();
    }

    /**
     * 通过DialogFragment包裹Dialog
     * @param dialog
     * @param fragmentManager
     * @param tag
     */
    public static void showDialog(Dialog dialog, FragmentManager fragmentManager, String tag) {
        WrapDialogFragment.newDialogFragment()
                .setDialog(dialog)
                .show(fragmentManager, tag);
    }
}
