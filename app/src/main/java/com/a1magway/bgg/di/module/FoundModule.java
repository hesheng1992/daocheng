package com.a1magway.bgg.di.module;

import com.a1magway.bgg.di.scope.PerFragment;
import com.a1magway.bgg.v.found.IFoundV;


import dagger.Module;
import dagger.Provides;

@Module
public class FoundModule {

    private IFoundV mIFoundV;

    public FoundModule(IFoundV mIFoundV) {
        this.mIFoundV = mIFoundV;
    }

    @PerFragment
    @Provides
    public IFoundV provideFoundV() {
        return mIFoundV;
    }

}
