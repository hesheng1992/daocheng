package com.a1magway.bgg.v.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PFragment;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.module.search.RecordsModule;
import com.a1magway.bgg.p.search.SearchRecordsP;
import com.dl7.tag.TagLayout;
import com.dl7.tag.TagView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 搜索记录子界面
 * Created by jph on 2017/7/27.
 */
public class RecordsFragment extends PFragment<SearchRecordsP> implements ISearchRecordsV {


    @BindView(R.id.records_layout_tag)
    TagLayout mTagLayout;

    public static RecordsFragment newInstance() {

        Bundle args = new Bundle();

        RecordsFragment fragment = new RecordsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.search_fragment_records;
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);

        ((SearchActivity) getActivity()).getSearchComponent()
                .getRecordsComponent(new RecordsModule(this))
                .inject(this);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        super.initView(savedInstanceState, contentView);

        mTagLayout.setTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int i, String s, int i1) {
                //将选择的记录关键字放入搜索界面输入框，并执行搜索
                SearchActivity searchActivity = (SearchActivity) getActivity();
                if (searchActivity == null) {
                    return;
                }

                searchActivity.inputKey(s, true);
            }
        });
    }

    @Override
    public void showRecords(List<String> recordList) {
        mTagLayout.setTags(recordList);
    }

    @OnClick(R.id.records_txt_clear)
    public void onClickClearRecords(View v) {
        mTagLayout.cleanTags();
        mPresenter.clearRecords();
    }

    /**
     * 重新显示
     */
    public void resetRecords() {
        if (mPresenter != null) {
            mPresenter.loadData();
        }
    }
}
