package com.a1magway.bgg.p.member;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import com.a1magway.bgg.R;
import com.a1magway.bgg.common.BaseObserver;
import com.a1magway.bgg.common.SimpleObserver;
import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.AddressData;
import com.a1magway.bgg.data.entity.LoginData;
import com.a1magway.bgg.data.entity.MemberRegisterGift;
import com.a1magway.bgg.data.entity.MemberRegisterValue;
import com.a1magway.bgg.data.entity.MemberUpgradeInfo;
import com.a1magway.bgg.data.repository.IMemberUpgradeInfoData;
import com.a1magway.bgg.data.repository.NetMemberRegisterInfoData;
import com.a1magway.bgg.data.repository.NetUserData;
import com.a1magway.bgg.data.repository.personalcenterdata.IPersonalSettingData;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.p.member.data.MemberRegisterEdit;
import com.a1magway.bgg.p.member.data.MemberRegisterEditViewBinder;
import com.a1magway.bgg.p.member.data.MemberRegisterGiftItemViewBinder;
import com.a1magway.bgg.p.member.data.MemberRegisterGiftSelectWrapper;
import com.a1magway.bgg.p.member.data.MemberRegisterGiftViewBinder;
import com.a1magway.bgg.p.member.data.MemberRegisterNotesViewBinder;
import com.a1magway.bgg.p.personal.AddressManagerP;
import com.a1magway.bgg.refactor.BasePresenter;
import com.a1magway.bgg.util.DateUtils;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.code.RequestCode;
import com.a1magway.bgg.v.main.MainActivity;
import com.a1magway.bgg.v.main.MainSubPages;
import com.a1magway.bgg.v.member.MemberRegisterContract;
import com.a1magway.bgg.v.personal.AddressManagerActivity;
import com.almagway.common.utils.StringUtil;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * author: Beaven
 * date: 2017/10/17 9:27
 */
@PerActivity
public class MemberRegisterP extends BasePresenter<MemberRegisterContract.View>
    implements MemberRegisterContract.Presenter, MemberRegisterEditViewBinder.ItemEditClickListener,
    MemberRegisterGiftItemViewBinder.ItemGiftClickListener, CompoundButton.OnCheckedChangeListener {

    private IMemberUpgradeInfoData memberUpgradeInfoData;
    private IPersonalSettingData personalSettingData;
    private NetMemberRegisterInfoData registerInfoData;
    private NetUserData userData;
    private MultiTypeAdapter adapter;
    private AddressData addressData;
    private MemberRegisterValue registerValue;

    private boolean isCheckNotes;


    @Inject
    public MemberRegisterP(@NonNull MemberRegisterContract.View view,
                           IMemberUpgradeInfoData memberUpgradeInfoData,
                           IPersonalSettingData personalSettingData,
                           NetMemberRegisterInfoData registerInfoData,
                           NetUserData userData,
                           MultiTypeAdapter adapter) {
        super(view);
        this.memberUpgradeInfoData = memberUpgradeInfoData;
        this.personalSettingData = personalSettingData;
        this.registerInfoData = registerInfoData;
        this.userData = userData;
        this.adapter = adapter;
        this.isCheckNotes = true;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        setAdapter();
        setMemberPrice();
        showItems(addNotesItem(getEditView()));
        getRegisterGift();
    }


    private void setMemberPrice() {
        MemberUpgradeInfo info = mView.getMemberUpgradeInfo();
        if (info != null) {
            double sellPrice = info.getSellPrice();
            double listPrice = info.getListPrice();
            if (sellPrice - listPrice == 0) {
                mView.setPriceNormal(info);
            } else if (sellPrice - listPrice < 0) {
                mView.setPriceSell(info);
            }
        }
    }


    /**
     * 设置Adapter
     */
    private void setAdapter() {
        adapter.register(MemberRegisterEdit.class, new MemberRegisterEditViewBinder(this));
        adapter.register(MemberRegisterGiftSelectWrapper.class,
            new MemberRegisterGiftItemViewBinder(this));
        adapter.register(Integer.class, new MemberRegisterGiftViewBinder());
        adapter.register(Boolean.class, new MemberRegisterNotesViewBinder(this));
        mView.setAdapter(adapter);
    }


    /**
     * 显示列表
     */
    private void showItems(Items items) {
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }


    /**
     * 获取Edit样式item列表
     */
    private Items getEditView() {
        Items items = new Items();
        items.add(getTelephoneEdit());
        items.add(new MemberRegisterEdit(MemberRegisterEdit.TAG_NAME, false,
            getContext().getResources().getString(R.string.order_commit_hint_remark), true));
        items.add(new MemberRegisterEdit(MemberRegisterEdit.TAG_SEX, false,
            getContext().getResources().getString(R.string.select_please), false));
        items.add(getBirthdayEdit());
        items.add(new MemberRegisterEdit(MemberRegisterEdit.TAG_CAREER, false,
            getContext().getResources().getString(R.string.order_commit_hint_remark), true));
        items.add(new MemberRegisterEdit(MemberRegisterEdit.TAG_INCOME, false,
            getContext().getResources().getString(R.string.select_please), false));
        return items;
    }


    private Items addNotesItem(Items items) {
        items.add(isCheckNotes);
        return items;
    }


    /**
     * 获取转化的giftList列表
     */
    private Items getMemberGift(List<MemberRegisterGift> giftList) {
        if (giftList == null || giftList.size() < 1) return null;
        Items items = new Items();
        items.add(R.drawable.image_member_register_gift_head);
        for (int i = 0; i < giftList.size(); i++) {
            MemberRegisterGiftSelectWrapper giftWrapper =
                new MemberRegisterGiftSelectWrapper(giftList.get(i), i == 0, i + 1);
            items.add(giftWrapper);
        }
        items.add(new MemberRegisterEdit(MemberRegisterEdit.TAG_GIFT_ADDRESS, false,
            getContext().getResources().getString(R.string.select_please), false));
        return items;
    }


    /**
     * 获取edit项的bean
     */
    private MemberRegisterEdit getMemberRegisterEdit(String title) {
        List list = adapter.getItems();
        for (Object object : list) {
            if (object instanceof MemberRegisterEdit) {
                MemberRegisterEdit edit = (MemberRegisterEdit) object;
                if (edit.getTitle().equals(title)) {
                    return edit;
                }
            }
        }
        return null;
    }


    /**
     * 获取会员注册的礼品
     */
    private void getRegisterGift() {
        memberUpgradeInfoData.getMemberRegisterGift()
            .compose(this.<List<MemberRegisterGift>>bindToDestroyEvent())//根据生命周期取消订阅
            .subscribe(new SimpleObserver<List<MemberRegisterGift>>(getContext()) {
                @Override
                public void onError(@NonNull Throwable e) {
                    super.onError(e);
                }


                @Override
                public void onFinish() {
                    super.onFinish();
                }


                @Override
                public void onNext(@NonNull List<MemberRegisterGift> giftList) {
                    super.onNext(giftList);
                    Items items = getEditView();
                    items.addAll(getMemberGift(giftList));
                    showItems(addNotesItem(items));
                }
            });
    }


    /**
     * 时间选择控件
     */
    private void showDateSelectDialog(String date) {
        Calendar calendar = Calendar.getInstance();
        if (StringUtil.isNotEmpty(date)) {
            calendar.setTime(DateUtils.formatDate(date, DateUtils.DATE_CHINESE));
        }
        //时间选择器
        TimePickerView timePickerView = new TimePickerView.Builder(getContext(),
            new TimePickerView.OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {//选中事件回调
                    setBirthDay(date);
                }
            })
            .setType(new boolean[] { true, true, true, false, false, false })// 默认全部显示
            .isCenterLabel(false)
            .setDate(calendar)
            .setTitleBgColor(getContext().getResources().getColor(R.color.common_divider_color))
            .setCancelColor(getContext().getResources().getColor(R.color.black))
            .setSubmitColor(getContext().getResources().getColor(R.color.black)).build();
        timePickerView.show();
    }


    /**
     * 设置生日
     */
    private void setBirthDay(final Date day) {
        final String birthday = day == null
                                ?
                                GlobalData.getInstance().getLoginData().getBirthday()
                                : DateUtils.formatString(day, DateUtils.DATE_CHINESE);
        MemberRegisterEdit edit = getMemberRegisterEdit(MemberRegisterEdit.TAG_BIRTHDAY);
        if (edit == null) return;
        edit.setText(birthday);
        edit.setEditEnable(false);
        adapter.notifyDataSetChanged();
    }


    /**
     * 性别选择
     */
    private void showSexSelectDialog() {
        final List<String> sexList = Arrays.asList("男", "女");
        OptionsPickerView optionsPickerView = new OptionsPickerView.Builder(getContext(),
            new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    String sex = sexList.get(options1);
                    MemberRegisterEdit edit = getMemberRegisterEdit(MemberRegisterEdit.TAG_SEX);
                    if (edit == null) return;
                    edit.setText(sex);
                    adapter.notifyDataSetChanged();
                }
            })
            .setCancelColor(getContext().getResources().getColor(R.color.black))
            .setSubmitColor(getContext().getResources().getColor(R.color.black))
            .setTitleBgColor(getContext().getResources().getColor(R.color.common_divider_color))
            .build();
        optionsPickerView.setPicker(sexList);
        optionsPickerView.show();
    }


    /**
     * 收入选择
     */
    private void showIncomeSelectDialog() {
        final List<String> incomeList = Arrays.asList("3000以下", "3000-5000", "5000-8000",
            "8000-10000",
            "10000-20000", "20000以上");
        OptionsPickerView optionsPickerView = new OptionsPickerView.Builder(getContext(),
            new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    String income = incomeList.get(options1);
                    MemberRegisterEdit edit = getMemberRegisterEdit(MemberRegisterEdit.TAG_INCOME);
                    if (edit == null) return;
                    edit.setText(income);
                    adapter.notifyDataSetChanged();
                }
            })
            .setCancelColor(getContext().getResources().getColor(R.color.black))
            .setSubmitColor(getContext().getResources().getColor(R.color.black))
            .setTitleBgColor(getContext().getResources().getColor(R.color.common_divider_color))
            .build();
        optionsPickerView.setPicker(incomeList);
        optionsPickerView.show();
    }


    /**
     * 保存生日更改
     */
    private void doChangeInfo(final Date date) {
        personalSettingData.updateUserInfo(GlobalData.getInstance().getLoginData().getTelephone(),
            null, date.getTime(),GlobalData.getInstance().getLoginData().getNickName())
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
                }


                @Override
                public void onComplete() {

                }
            });
    }


    @Override
    public void editClick(int position, MemberRegisterEdit item) {
        String title = item.getTitle();
        if (MemberRegisterEdit.TAG_SEX.equals(title)) {
            showSexSelectDialog();
        } else if (MemberRegisterEdit.TAG_BIRTHDAY.equals(title)) {
            showDateSelectDialog(item.getText());
        } else if (MemberRegisterEdit.TAG_INCOME.equals(title)) {
            showIncomeSelectDialog();
        } else if (MemberRegisterEdit.TAG_GIFT_ADDRESS.equals(title)) {
            AddressManagerActivity.startActivityForResult(getContext(), true);
        }
    }


    @Override
    public void saveClick(int position, MemberRegisterEdit item) {
        String title = item.getTitle();
        if (MemberRegisterEdit.TAG_BIRTHDAY.equals(title)) {
            item.setLayoutEnable(false);
            item.setShowRightButton(false);
            adapter.notifyItemChanged(position);
            doChangeInfo(DateUtils.formatDate(item.getText(), DateUtils.DATE_CHINESE));
        }
    }


    @Override
    public void editWatch(int position, String value) {
        MemberRegisterEdit edit = (MemberRegisterEdit) adapter.getItems().get(position);
        if (edit != null) {
            edit.setText(value);
        }
    }


    @Override
    public void onClick(int position, MemberRegisterGiftSelectWrapper item) {
        List list = adapter.getItems();
        for (Object oj : list) {
            if (oj instanceof MemberRegisterGiftSelectWrapper) {
                MemberRegisterGiftSelectWrapper wrapper = (MemberRegisterGiftSelectWrapper) oj;
                wrapper.setSelect(false);
            }
        }
        item.setSelect(true);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        isCheckNotes = isChecked;
    }


    /**
     * 设置固定的手机号
     */
    private MemberRegisterEdit getTelephoneEdit() {
        String telephone = GlobalData.getInstance().getLoginData().getTelephone();
        MemberRegisterEdit edit = new MemberRegisterEdit(MemberRegisterEdit.TAG_TELEPHONE, false,
            "", false);
        edit.setText(telephone);
        edit.setLayoutEnable(false);
        return edit;
    }


    private MemberRegisterEdit getBirthdayEdit() {
        String birthday = GlobalData.getInstance().getLoginData().getBirthday();
        MemberRegisterEdit edit = new MemberRegisterEdit(MemberRegisterEdit.TAG_BIRTHDAY, true,
            getContext().getResources().getString(R.string.select_please), false);
        edit.setText(birthday);
        return edit;
    }


    /**
     * 确定升级，支付按钮
     */
    @Override
    public void upgradeClick() {
        registerValue = getListValue();
        if (!checkList(registerValue)) {
            mView.showEditIncompleteDialog();
            return;
        }
        mView.showPayCheckDialog();
    }


    /**
     * 获取填写的所有值
     */
    private MemberRegisterValue getListValue() {
        MemberRegisterValue registerValue = new MemberRegisterValue();
        registerValue.setUserId(GlobalData.getInstance().getUserId());
        registerValue.setRoleType(1);

        List list = adapter.getItems();
        for (Object object : list) {
            if (object instanceof MemberRegisterEdit) {
                MemberRegisterEdit edit = (MemberRegisterEdit) object;
                String title = edit.getTitle();
                switch (title) {
                    case MemberRegisterEdit.TAG_NAME:
                        registerValue.setUserName(edit.getText());
                        break;
                    case MemberRegisterEdit.TAG_SEX:
                        registerValue.setSex(getSex(edit.getText()));
                        break;
                    case MemberRegisterEdit.TAG_CAREER:
                        registerValue.setProfession(edit.getText());
                        break;
                    case MemberRegisterEdit.TAG_INCOME:
                        registerValue.setMonthlySalary(edit.getText());
                        break;
                    case MemberRegisterEdit.TAG_BIRTHDAY:
                        long birthday = edit.isLayoutEnable() ? 0
                                                              : DateUtils.formatMillions(
                                                                  edit.getText(),
                                                                  DateUtils.DATE_CHINESE);
                        registerValue.setBirthday(birthday);
                        break;
                    case MemberRegisterEdit.TAG_GIFT_ADDRESS:
                        if (addressData != null) {
                            registerValue.setAddressId(addressData.getId());
                        }
                        break;
                    default:
                        break;
                }
            } else if (object instanceof MemberRegisterGiftSelectWrapper) {
                MemberRegisterGiftSelectWrapper wrapper = (MemberRegisterGiftSelectWrapper) object;
                if (wrapper.isSelect()) {
                    int giftId = Integer.parseInt(wrapper.getMemberRegisterGift().getId());
                    registerValue.setGiftId(giftId);
                }
            }
        }
        return registerValue;
    }


    private boolean checkList(MemberRegisterValue value) {
        return !TextUtils.isEmpty(value.getUserName()) && value.getSex() != -1 &&
            value.getBirthday() > 0
            && value.getGiftId() > 0 && value.getAddressId() > 0 && isCheckNotes;
    }


    /**
     * 转换性别
     */
    private int getSex(String sex) {
        if ("男".equals(sex)) {
            return 1;
        } else if ("女".equals(sex)) {
            return 0;
        }
        return -1;
    }


    /**
     * 获取选中地址显示
     */
    @Override
    public void handleSelectAddressResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != RequestCode.REQUEST_CODE_ADDRESS_SELECT ||
            resultCode != Activity.RESULT_OK || data == null) {
            return;
        }
        addressData = (AddressData) data.getSerializableExtra(
            AddressManagerP.EXTRA_ADDRESS_DATA);
        if (addressData != null) {
            MemberRegisterEdit edit = getMemberRegisterEdit(MemberRegisterEdit.TAG_GIFT_ADDRESS);
            if (edit != null) {
                String address = addressData.getOrderuserAddress()
                    + " " + addressData.getOrderuserDetailAddress();
                edit.setText(address);
                adapter.notifyDataSetChanged();
            }
        }
    }


    /**
     * 选择支付类型
     */
    @Override
    public void selectPay(String pay) {
        if (registerValue == null) return;
        registerValue.setPayType(pay);
        netRegisterMember(pay);
    }


    private void netRegisterMember(final String pay) {
        registerInfoData.registerMemberInfo(registerValue)
            .compose(this.bindToDestroyEvent())
            .subscribe(new SimpleObserver<Object>(getContext()) {
                @Override
                public void onNext(@NonNull Object o) {
                    super.onNext(o);
                    mView.showPayDialog(pay, o);
                }
            });
    }


    @Override
    public void updateUserInfo() {
        userData.getUserInfo(String.valueOf(GlobalData.getInstance().getUserId()))
            .compose(this.<LoginData>bindToDestroyEvent())
            .subscribe(new SimpleObserver<LoginData>(getContext()) {
                @Override
                public void onNext(@io.reactivex.annotations.NonNull LoginData loginData) {
                    super.onNext(loginData);
                    GlobalData.getInstance().setLoginData(loginData);
                    MainActivity.start(getContext(), MainSubPages.PERSONAL);
                }
            });
    }
}
