package com.a1magway.bgg.v.member;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.member.DaggerMemberCardsComponent;
import com.a1magway.bgg.di.module.member.MemberCardsModule;
import com.a1magway.bgg.p.member.MemberCardP;
import com.a1magway.bgg.refactor.PresenterActivity;

import butterknife.BindView;

public class MemberCardActivity extends PresenterActivity<MemberCardP>
        implements MemberCardsContract.View {

    public static void start(Context context) {
        Intent intent = new Intent(context, MemberCardActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.recycler_card)
    RecyclerView recyclerCard;

    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_member_card;
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        DaggerMemberCardsComponent.builder()
                .appComponent(appComponent)
                .memberCardsModule(new MemberCardsModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setTextTitle("卡券中心");
    }

    @Override
    public void setAdapter(RecyclerView.Adapter adapter) {
        recyclerCard.setLayoutManager(new LinearLayoutManager(this));
        recyclerCard.setHasFixedSize(true);
        recyclerCard.setAdapter(adapter);
    }
}
