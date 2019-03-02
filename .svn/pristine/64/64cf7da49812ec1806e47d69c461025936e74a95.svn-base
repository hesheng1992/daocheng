package com.a1magway.bgg.di.component;

import com.a1magway.bgg.di.module.InvitationCodeModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.personal.InvitationCodeActivity;

import dagger.Component;

/**
 * Created by enid on 2018/6/20.
 */
@PerActivity
@Component(modules = InvitationCodeModule.class,dependencies = AppComponent.class)
public interface InvitationCodeComponent {
    public void inject(InvitationCodeActivity activity);
}
