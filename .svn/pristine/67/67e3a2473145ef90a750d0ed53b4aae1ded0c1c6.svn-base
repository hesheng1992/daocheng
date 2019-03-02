package com.a1magway.bgg.di.component.pay;

import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.module.pay.PayModule;
import com.a1magway.bgg.di.scope.PerFragment;
import com.a1magway.bgg.widget.dialog.PayDialogFragment;

import dagger.Component;

/**
 * Created by jph on 2017/8/22.
 */
@PerFragment
@Component(modules = PayModule.class, dependencies = AppComponent.class)
public interface PayComponent {

    void inject(PayDialogFragment payDialogFragment);
}
