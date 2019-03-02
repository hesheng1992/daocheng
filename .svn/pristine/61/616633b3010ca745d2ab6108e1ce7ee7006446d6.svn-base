package com.a1magway.bgg.p.found;

import com.a1magway.bgg.common.BaseObserver;
import com.a1magway.bgg.data.entity.FoundTitleData;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.p.BasePresenter;
import com.a1magway.bgg.v.found.IFoundListV;

import java.util.List;

public class FoundListP extends BasePresenter<IFoundListV> {


    private IFoundListV mIFoundListV;

//    @Inject
//    IPayData mPayData;

    public FoundListP(IFoundListV f) {
        super(f);
        mIFoundListV=f;
    }


    public void getFoundTitle(){
        new APIManager(null).getFoundList().subscribe(new BaseObserver<List<FoundTitleData>>(getContext()){

            @Override
            public void onComplete() {

            }

            @Override
            public void onNext(List<FoundTitleData> foundTitleData) {
                mIFoundListV.getFounTitle(foundTitleData);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

}
