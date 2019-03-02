package com.a1magway.bgg.di.component;

import com.a1magway.bgg.di.module.UpgradePayModel;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.upgrade.UpgradePayActivity;

import dagger.Component;

/**
 * Created by enid on 2018/8/4.
 */

@PerActivity
@Component(modules = UpgradePayModel.class,dependencies = AppComponent.class)
public interface UpgradePayComponent {
    void inject(UpgradePayActivity upgradePayActivity);
}
