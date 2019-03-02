package com.a1magway.bgg.di.component.friend;

import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.module.friend.InviteFriendModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.friend.InviteFriendActivity;

import dagger.Component;

/**
 * Created by enid on 2018/8/15.
 */
@PerActivity
@Component(modules = InviteFriendModule.class,dependencies = AppComponent.class)
public interface InviteFriendComponent {
    void inject(InviteFriendActivity inviteFriendActivity);
}
