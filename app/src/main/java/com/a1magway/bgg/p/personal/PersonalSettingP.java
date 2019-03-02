package com.a1magway.bgg.p.personal;

import android.view.View;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.BaseObserver;
import com.a1magway.bgg.common.enums.GenderType;
import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.LoginData;
import com.a1magway.bgg.data.repository.personalcenterdata.IPersonalSettingData;
import com.a1magway.bgg.eventbus.event.ChangeUserSettingEvent;
import com.almagway.common.utils.StringUtil;
import com.a1magway.bgg.p.BasePresenter;
import com.a1magway.bgg.util.DateUtils;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.v.personal.IPersonalSettingV;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lyx on 2017/8/8.
 */
public class PersonalSettingP extends BasePresenter<IPersonalSettingV> {

    IPersonalSettingData mData;

    @Inject
    public PersonalSettingP(IPersonalSettingV iPersonalSettingV, IPersonalSettingData data) {
        super(iPersonalSettingV);
        this.mData = data;
    }

    /**
     * 时间选择控件
     *
     * @param date
     */
    public void showDateSelectDialog(String date) {
        Calendar calendar = Calendar.getInstance();
        if (StringUtil.isNotEmpty(date)) {
            calendar.setTime(DateUtils.formatDate(date, DateUtils.DATE_CHINESE));
        }
        //时间选择器
        TimePickerView timePickerView = new TimePickerView.Builder(getContext(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                doChangeInfo(date);
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .isCenterLabel(false)
                .setDate(calendar)
                .setTitleBgColor(getContext().getResources().getColor(R.color.common_divider_color))
                .setCancelColor(getContext().getResources().getColor(R.color.black))
                .setSubmitColor(getContext().getResources().getColor(R.color.black)).build();
        timePickerView.show();
    }

    private void doChangeInfo(final Date date) {
        mData.updateUserInfo(GlobalData.getInstance().getLoginData().getTelephone(), null, date.getTime(),GlobalData.getInstance().getLoginData().getNickName())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.<APIResponse>bindToDestroyEvent())
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseObserver<APIResponse>(getContext()) {
                    @Override
                    public void onNext(@NonNull APIResponse apiResponse) {
                        String dateStr = DateUtils.formatString(date, DateUtils.DATE_CHINESE);
                        LoginData loginData = GlobalData.getInstance().getLoginData();
                        loginData.setBirthday(dateStr);
                        GlobalData.getInstance().setLoginData(loginData);
                        mView.setBirthDay(dateStr);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void showGenderSelectDialog(Integer gender) {
        final List<String> list = new ArrayList<>();
        for (GenderType type : GenderType.values()) {
            list.add(type.getValue());
        }
        OptionsPickerView genderPicker = new OptionsPickerView.Builder(getContext(), new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(final int options1, int options2, int options3, View v) {
                genderSelected(list, options1);
            }
        }).setTitleBgColor(getContext().getResources().getColor(R.color.common_divider_color))
                .setCancelColor(getContext().getResources().getColor(R.color.black))
                .setSubmitColor(getContext().getResources().getColor(R.color.black))
                .build();
        genderPicker.setSelectOptions(gender == null ? 0 : gender);
        genderPicker.setPicker(list);
        genderPicker.show();
    }


    private void genderSelected(final List<String> list, final int index) {
        mData.updateUserInfo(GlobalData.getInstance().getLoginData().getTelephone(), index, null,GlobalData.getInstance().getLoginData().getNickName())
                .compose(this.<APIResponse>bindToDestroyEvent())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<APIResponse>(getContext()) {
                    @Override
                    public void onNext(@NonNull APIResponse apiResponse) {
                        LoginData loginData = GlobalData.getInstance().getLoginData();
                        loginData.setGender(list.get(index));
                        GlobalData.getInstance().setLoginData(loginData);
                        mView.setGender(list.get(index));
                        EventBus.getDefault().post(new ChangeUserSettingEvent());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void nickNameChanged() {
        final String nickname = mView.getNickname();
        mData.updateUserInfo(GlobalData.getInstance().getLoginData().getTelephone(), null, null,nickname)
                .compose(this.<APIResponse>bindToDestroyEvent())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<APIResponse>(getContext()) {
                    @Override
                    public void onNext(@NonNull APIResponse apiResponse) {
                        LoginData loginData = GlobalData.getInstance().getLoginData();
                        loginData.setNickName(nickname);
                        GlobalData.getInstance().setLoginData(loginData);
                        EventBus.getDefault().post(new ChangeUserSettingEvent());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
