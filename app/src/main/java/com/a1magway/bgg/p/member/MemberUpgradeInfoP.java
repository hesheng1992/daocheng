package com.a1magway.bgg.p.member;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.a1magway.bgg.data.entity.MemberUpgradeInfo;
import com.a1magway.bgg.data.entity.Permission;
import com.a1magway.bgg.data.repository.IMemberUpgradeInfoData;
import com.a1magway.bgg.refactor.BaseLoadPresenter;
import com.a1magway.bgg.v.member.MemberUpgradeInfoContract;
import io.reactivex.Observable;
import javax.inject.Inject;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * author: Beaven
 * date: 2017/10/13 14:35
 */

public class MemberUpgradeInfoP
    extends BaseLoadPresenter<MemberUpgradeInfoContract.View, MemberUpgradeInfo>
    implements MemberUpgradeInfoContract.Presenter {

    private IMemberUpgradeInfoData memberUpgradeInfoData;
    private MultiTypeAdapter adapter;
    private MemberUpgradeInfo memberUpgradeInfo;


    @Inject
    public MemberUpgradeInfoP(
        @NonNull MemberUpgradeInfoContract.View view, IMemberUpgradeInfoData memberUpgradeInfoData,
        MultiTypeAdapter adapter) {
        super(view);
        this.memberUpgradeInfoData = memberUpgradeInfoData;
        this.adapter = adapter;
    }


    @Nullable
    @Override
    public Observable<MemberUpgradeInfo> getDataObservable() {
        return memberUpgradeInfoData.getMemberUpgradeInfo();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        adapter.register(MemberUpgradeInfo.class, new MemberUpgradeHeadViewBinder());
        adapter.register(Permission.class, new MemberUpgradeInfoViewBinder());
        mView.setRecyclerViewAdapter(adapter);
        loadData();
    }


    @Override
    protected void onLoadSuccess(MemberUpgradeInfo memberUpgradeInfo) {
        super.onLoadSuccess(memberUpgradeInfo);
        this.memberUpgradeInfo = memberUpgradeInfo;

        Items items = new Items();
        items.add(memberUpgradeInfo);
        items.addAll(memberUpgradeInfo.getPermission());
        adapter.setItems(items);
        adapter.notifyDataSetChanged();

        double sellPrice = memberUpgradeInfo.getSellPrice();
        double listPrice = memberUpgradeInfo.getListPrice();
        if (sellPrice - listPrice == 0) {
            mView.setPriceNormal(memberUpgradeInfo);
        } else if (sellPrice - listPrice < 0) {
            mView.setPriceSell(memberUpgradeInfo);
        }
    }


    @Override
    protected void onLoadFinish() {
        super.onLoadFinish();
    }


    @Override
    public MemberUpgradeInfo getMemberUpgradeInfo() {
        return memberUpgradeInfo;
    }
}
