package com.a1magway.bgg.p.member;

import android.support.annotation.NonNull;
import com.a1magway.bgg.common.SimpleObserver;
import com.a1magway.bgg.data.entity.Cards;
import com.a1magway.bgg.data.entity.LoginData;
import com.a1magway.bgg.data.repository.NetUserData;
import com.a1magway.bgg.refactor.BasePresenter;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.v.member.MemberCardsContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import me.drakeet.multitype.MultiTypeAdapter;

/** author: Beaven date: 2017/10/19 10:54 */
public class MemberCardP extends BasePresenter<MemberCardsContract.View> {

    private MultiTypeAdapter adapter;
    private NetUserData userData;

    @Inject
    public MemberCardP(
            @NonNull MemberCardsContract.View view,
            MultiTypeAdapter adapter,
            NetUserData userData) {
        super(view);
        this.adapter = adapter;
        this.userData = userData;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        adapter.register(Cards.class, new MemberCardViewBinder());
        adapter.register(String.class, new MemberCardsAddViewBinder());
        // 隐藏添加按钮
        // items.add("add");
        mView.setAdapter(adapter);
        updateUserInfo();
    }

    private void updateUserInfo() {
        userData.getUserInfo(String.valueOf(GlobalData.getInstance().getUserId()))
                .compose(this.<LoginData>bindToDestroyEvent())
                .subscribe(
                        new SimpleObserver<LoginData>(getContext()) {
                            @Override
                            public void onNext(@NonNull LoginData loginData) {
                                super.onNext(loginData);
                                GlobalData.getInstance().setLoginData(loginData);
                                if (loginData.getCards() == null
                                        || loginData.getCards().size() == 0) return;
                                List<Cards> cardsList = loginData.getCards();
                                List<Cards> cardAdapterList = new ArrayList<>();
                                for (int i = 0; i < cardsList.size(); i++) {
                                    if (cardsList.get(i).getCardType() == 1) {
                                        cardAdapterList.add(cardsList.get(i));
                                    }
                                }
                                adapter.setItems(cardAdapterList);
                                adapter.notifyDataSetChanged();
                            }
                        });
    }
}
