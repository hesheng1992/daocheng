package com.a1magway.bgg.p.personal;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.BaseObserver;
import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.AddressSelectedDataBean;
import com.a1magway.bgg.data.repository.personalcenterdata.IAddressData;
import com.a1magway.bgg.eventbus.event.SelectedCityEvent;
import com.almagway.common.utils.CollectionUtil;
import com.a1magway.bgg.p.BasePresenter;
import com.a1magway.bgg.util.JsonBean;
import com.a1magway.bgg.util.Toaster;
import com.a1magway.bgg.v.personal.IEditAddressV;
import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.almagway.common.utils.AssetUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 修改 添加地址p
 * Created by lyx on 2017/8/16.
 */
public class EditAddressP extends BasePresenter<IEditAddressV> {

    public static final String RESULT_EXTRA_ADDRESS_DATA = "addressData";

    private static final int EDIT_ADD = 0;
    private static final int EDIT_EDIT = 1;

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    IAddressData mIAddressData;
    private AddressSelectedDataBean addressSelectedDataBean;

    private String sheng = "";//省
    private String shi = "";//市
    private String xian = "";//县
    private int cityId;

    @Inject
    public EditAddressP(@NonNull IEditAddressV view, IAddressData iAddressData) {
        super(view);
        mIAddressData = iAddressData;
    }

    public void showSelector() {
        initJsonData();
        showPickerView();
    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = AssetUtil.getString(getContext(), "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0, j = CollectionUtil.getSize(jsonBean); i < j; i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
    }


    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toaster.showShort(getContext(), "城市信息解析失败！");
        }
        return detail;
    }

    private void showPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerView.Builder(getContext(), new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);
                //去重，例如:北京市北京市东城区.
                if (options1Items.get(options1).getPickerViewText().equals(options2Items.get(options1).get(options2))) {
                    tx = options1Items.get(options1).getPickerViewText() + options3Items.get(options1).get(options2).get(options3);
                }
                mView.setDetailAddress(tx);
            }
        }).setTitleBgColor(getContext().getResources().getColor(R.color.common_divider_color))
                .setCancelColor(R.color.black)
                .setSubmitColor(R.color.black)
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    public void doRequest() {
        Map<String, Object> map = mView.getParamsMap();
        if (map == null) {
            if (mView.isAddNewAddress()) {
                Toaster.showShort(getContext(), "信息填写不完整，请补充完整！");
            } else {
                Toaster.showShort(getContext(), "地址信息没有变化！");
            }
            return;
        }
        int type = mView.isAddNewAddress() ? EDIT_ADD : EDIT_EDIT;
        mIAddressData.editAddress(map, type)
                .compose(this.<APIResponse>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<APIResponse>(getContext()) {
                    @Override
                    public void onNext(@NonNull APIResponse apiResponse) {
                        Toaster.showShort(getContext(), apiResponse.getMsg());
                        Intent intent = new Intent();
                        if (!mView.isAddNewAddress()) {
                            intent.putExtra(RESULT_EXTRA_ADDRESS_DATA, mView.getNewAddressData());
                        }
                        mView.getActivity().setResult(Activity.RESULT_OK, intent);
                        mView.getActivity().finish();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getCityList();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 得到城市列表
     */
    private void getCityList() {
        mIAddressData.getCityList()
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<AddressSelectedDataBean>() {
                    @Override
                    public void accept(AddressSelectedDataBean addressSelectedDataBean) throws Exception {
                        EditAddressP.this.addressSelectedDataBean = addressSelectedDataBean;
                    }
                });
    }

    public AddressSelectedDataBean getCityListData() {
        return addressSelectedDataBean;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SelectedCityEvent selectedCityEvent) {
        switch (selectedCityEvent.getGrade()) {
            case 0:
                sheng = selectedCityEvent.getCityName();
                shi = "";
                xian = "";
                break;
            case 1:
                shi = selectedCityEvent.getCityName();
                xian = "";
                break;
            case 2:
                xian = selectedCityEvent.getCityName();
                break;
        }
        cityId = selectedCityEvent.getCityId();
        mView.setDetailAddress(sheng + shi + xian);
    }

    public int getCityId(){
        return cityId;
    }

}
