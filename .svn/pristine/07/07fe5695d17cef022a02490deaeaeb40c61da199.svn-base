package com.a1magway.bgg.v.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PActivity;
import com.a1magway.bgg.data.entity.PresetFilterRule;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.search.DaggerSearchComponent;
import com.a1magway.bgg.di.component.search.SearchComponent;
import com.a1magway.bgg.di.module.search.SearchModule;
import com.a1magway.bgg.p.brand.BrandItem;
import com.a1magway.bgg.p.search.SearchP;
import com.a1magway.bgg.util.JumpUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * 承载搜索的Activity
 * Created by jph on 2017/7/27.
 */
public class SearchActivity extends PActivity<SearchP> {

    public static final String EXTRA_PRESET_CATE1_ID = "extra_preset_cate1_id";
    public static final String EXTRA_PRESET_BRAND = "extra_preset_brand";
    public static final String EXTRA_PRESET_KEY = "extra_preset_key";

    private SearchComponent mSearchComponent;

    @Inject
    SearchFragManager mSearchFragManager;
    @Inject
    PresetFilterRule mPresetFilterRule;

    @BindView(R.id.search_edt_key)
    EditText mKeyEdt;
    @BindView(R.id.search_img_clear)
    ImageView mClearKeyImg;//清空输入内容按钮
    @BindView(R.id.search_layout_frag)
    ViewGroup mFragLayout;
    @BindView(R.id.search_img)
    ImageView search;

    public static void start(Context context) {
        Intent starter = new Intent(context, SearchActivity.class);
        JumpUtil.startActivity(context, starter);
    }

    /**
     * 默认搜索某个关键字打开界面
     *
     * @param context
     * @param cate1Id   限制的筛选一级分类id
     * @param presetKey
     */
    public static void start(Context context, int cate1Id, String presetKey) {
        Intent starter = new Intent(context, SearchActivity.class);
        starter.putExtra(EXTRA_PRESET_CATE1_ID, cate1Id);
        starter.putExtra(EXTRA_PRESET_KEY, presetKey);
        JumpUtil.startActivity(context, starter);
    }

    public static void start(Context context, BrandItem brandItem, String presetKey) {
        Intent starter = new Intent(context, SearchActivity.class);
        starter.putExtra(EXTRA_PRESET_BRAND, brandItem);
        starter.putExtra(EXTRA_PRESET_KEY, presetKey);
        JumpUtil.startActivity(context, starter);
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);

        mSearchComponent = DaggerSearchComponent.builder()
                .appComponent(appComponent)
                .searchModule(new SearchModule(getSupportFragmentManager(), getIntent()))
                .build();

        mSearchComponent.inject(this);
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.search_activity_search;
    }

    @Override
    public void onCreateV(@Nullable Bundle savedInstanceState) {
        super.onCreateV(savedInstanceState);
        mSearchFragManager.showRecordsFragment(0);

        initPreset();
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        mKeyEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    executeSearch();
                    return true;
                }
                return false;
            }
        });
        mKeyEdt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mKeyEdt.setCursorVisible(true);
                return false;
            }
        });

    }

    @OnClick(R.id.search_title_img_back)
    public void onClickBack(View v) {
        onBackButtonClick();
    }

    @OnClick(R.id.search_img)
    public void onClickSearch(View v){
        executeSearch(false);
    }

    @OnClick(R.id.search_img_clear)
    public void onClickClearKey(View v) {
        mKeyEdt.setText("");
    }

    @Override
    public void onBackPressed() {
        if (mSearchFragManager.onBackPressed4Child()) {
            return;
        }

//        if (mSearchFragManager.isShowResultFragment()) {
////            mSearchFragManager.showRecordsFragment();
//            return;
//        }
        super.onBackPressed();
    }

    /**
     * 输入关键字
     *
     * @param key
     * @param execute 是否同时执行搜索
     */
    public void inputKey(String key, boolean execute) {
        mKeyEdt.setText(key);
        mKeyEdt.setSelection(mKeyEdt.length());
        if (execute) {
            executeSearch();
        }
    }

    /**
     * 正常的执行搜索
     */
    private void executeSearch() {
        executeSearch(false);
    }

    /**
     * 显示搜索结果页面，并执行搜索
     *
     * @param keepPresetRule 是否保持预设的规则
     */
    private void executeSearch(final boolean keepPresetRule) {
        if (mKeyEdt.length() == 0) {
            return;
        }
        final String key = mKeyEdt.getText().toString();
//        SoftInputUtil.hideSoftInput(this);
        int cateId=getIntent().getIntExtra(EXTRA_PRESET_CATE1_ID,0);
        mSearchFragManager.showResultFragment(cateId);

        mFragLayout.post(new Runnable() {
            @Override
            public void run() {
                //第一次显示需等带fragment加入layout完成才能执行之后的操作
                ResultFragment search = mSearchFragManager.getResultFragment();
                if (search == null) {
                    return;
                }

                if (!keepPresetRule) {
                    //正常执行一次搜索则使预设的规则无效
                    mPresetFilterRule.setValid(false);
                }
                search.executeSearch(key);
            }
        });
        if (cateId!=0){
            if (!getIntent().getStringExtra(EXTRA_PRESET_KEY).equals(key)){ //分类进去的搜索不加入缓存
                mPresenter.storeRecord(key);
            }
        }else {
            mPresenter.storeRecord(key);
        }


        mKeyEdt.setCursorVisible(false);
    }

    @OnTextChanged(value = R.id.search_edt_key, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void afterKeyTextChanged(Editable s) {
        mKeyEdt.setCursorVisible(true);
        //控制清空输入按钮的显示
        mClearKeyImg.setVisibility(s.length() == 0 ? View.GONE : View.VISIBLE);
        if (s.length() == 0) {
            //内容清空时，显示搜索记录界面

            mSearchFragManager.showRecordsFragment(0);
        }
    }

    public SearchComponent getSearchComponent() {
        return mSearchComponent;
    }

    /**
     * 初始化预设的搜索选项
     */
    private void initPreset() {
        final String key = getIntent().getStringExtra(EXTRA_PRESET_KEY);

        mFragLayout.post(new Runnable() {
            @Override
            public void run() {
                //执行搜索空内容
                mKeyEdt.setText(key);
                executeSearch(true);
             }
        });
    }

    @Override
    protected boolean fitsSystemWindows() {
        return true;
    }//


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction()==MotionEvent.ACTION_DOWN){
            View v=getCurrentFocus();
            boolean  hideInputResult =isShouldHideInput(v,ev);
            Log.v("hideInputResult","zzz-->>"+hideInputResult);
            if(hideInputResult){
                v.clearFocus();
                InputMethodManager imm = (InputMethodManager)this
                        .getSystemService(Activity.INPUT_METHOD_SERVICE);
                if(v != null){
                    if(imm.isActive()){
                        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    public  boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            //之前一直不成功的原因是,getX获取的是相对父视图的坐标,getRawX获取的才是相对屏幕原点的坐标！！！
            Log.v("leftTop[]","zz--left:"+left+"--top:"+top+"--bottom:"+bottom+"--right:"+right);
            Log.v("event","zz--getX():"+event.getRawX()+"--getY():"+event.getRawY());
            if (event.getRawX() > left && event.getRawX() < right
                    && event.getRawY() > top && event.getRawY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }


}
