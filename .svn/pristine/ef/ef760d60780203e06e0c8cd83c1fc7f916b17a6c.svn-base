package com.a1magway.bgg.di.component;

import com.a1magway.bgg.di.module.InvitationModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.invitation.InvitationActivity;

import dagger.Component;

/**
 * Created by enid on 2018/5/29.
 */
@PerActivity
@Component(modules = InvitationModule.class,dependencies = AppComponent.class)
public interface InvitationComponent {
    void inject(InvitationActivity registerActivity);
}
