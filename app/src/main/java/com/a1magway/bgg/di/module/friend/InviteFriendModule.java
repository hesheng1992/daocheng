package com.a1magway.bgg.di.module.friend;

import com.a1magway.bgg.v.friend.InviteFriendContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by enid on 2018/8/15.
 */
@Module
public class InviteFriendModule {
    private InviteFriendContract.View view;

    public InviteFriendModule(InviteFriendContract.View view) {
        this.view = view;
    }

    @Provides
    public InviteFriendContract.View provideInviteFriendView() {
        return view;
    }
}
