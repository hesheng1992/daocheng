package com.a1magway.bgg.p.personal;

import android.support.annotation.NonNull;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.BaseActivity;
import com.a1magway.bgg.common.BaseObserver;
import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.repository.IModifyPwdData;
import com.a1magway.bgg.p.BasePresenter;
import com.almagway.common.utils.AesUtil;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.Toaster;
import com.a1magway.bgg.v.personal.IModifyPwdV;
import com.a1magway.bgg.widget.ClearEditText;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lyx on 2017/8/8.
 */
public class ModifyPwdP extends BasePresenter<IModifyPwdV> {

    IModifyPwdData mIModifyPwdData;

    @Inject
    public ModifyPwdP(@NonNull IModifyPwdV view, IModifyPwdData data) {
        super(view);
        mIModifyPwdData = data;
    }

    public void doChange(List<ClearEditText> list) {
        if (!list.get(1).getEditable().toString().equals(list.get(2).getEditable().toString())) {
            Toaster.showShort(getContext(), getContext().getString(R.string.change_pwd_differ));
            return;
        }
        mIModifyPwdData.changePassword(GlobalData.getInstance().getLoginData().getTelephone(),
                AesUtil.encryptAES(list.get(0).getEditable().toString()), AesUtil.encryptAES(list.get(1).getEditable().toString()))
                .compose(this.<APIResponse>bindToDestroyEvent())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<APIResponse>(getContext()) {
                    @Override
                    public void onNext(@NonNull APIResponse apiResponse) {
                        Toaster.showShort(getContext(), apiResponse.getMsg());
                        if(apiResponse.isSuccess()) {
                            ((BaseActivity) mView.getActivity()).finishWithAnim();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 是否可以进行请求 检查长度
     *
     * @param list
     */
    public void canGoNext(List<ClearEditText> list) {
        boolean length = true;
//        boolean lengthEqual = true;
//        int strl = 0;
        for (ClearEditText clearEditText : list) {
            int lenthStr = clearEditText.getEditable().length();
            if (lenthStr < getContext().getResources().getInteger(R.integer.pwd_min_length)) {
                length = false;
                break;
            }
//            else {
//                if (strl == 0) {
//                    strl = lenthStr;
//                } else {
//                    lengthEqual = strl == lenthStr;
//                    if (!lengthEqual) {
//                        break;
//                    }
//                }
//            }
        }

        if (length) {
            mView.setNextEnable(true);
        } else {
            mView.setNextEnable(false);
        }
    }
}
