package com.a1magway.bgg.p.articleManager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.MyCreateArticle;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.okhttp.LogUtil;
import com.a1magway.bgg.p.BaseLoadP;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.Toaster;
import com.a1magway.bgg.v.articleManager.ArticleManagerWebActivity;
import com.a1magway.bgg.v.articleManager.AticleManagerContract;
import com.a1magway.bgg.v.found.GeneralWebActivity;
import com.a1magway.bgg.v.web.WebActivity;
import com.almagway.common.AppConfig;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by enid on 2018/8/22.
 */

public class ArticleManagerP extends BaseLoadP<List<MyCreateArticle>, AticleManagerContract.View>
        implements AticleManagerContract.Presenter {
    @Inject
    APIManager apiManager;

    private ArticleManagerAdapter adapter;

    @Inject
    public ArticleManagerP(@NonNull AticleManagerContract.View view) {
        super(view);
        adapter = new ArticleManagerAdapter(getContext());
    }

    @Nullable
    @Override
    public Observable<List<MyCreateArticle>> getDataObservable() {
        return apiManager.getMyArticle(GlobalData.getInstance().getUserId(), 1);
    }

    @Override
    protected void onLoadSuccess(List<MyCreateArticle> myCreateArticles) {
        adapter.setData(myCreateArticles);
        adapter.addDeleteListener(new ArticleManagerAdapter.DeleteListener() {
            @Override
            public void delete(int position) {
                deleteMyArticle(adapter.getItemData(position).getId());
            }

            @Override
            public void toDetail(int position) {
                MyCreateArticle myCreateArticle = adapter.getItemData(position);
                if (myCreateArticle.getPublishStatus() == 1) {
                    //文章审核中
                    AlertDialog dialog = new AlertDialog.Builder(getContext())
                            .setTitle("文章审核中")
                            .setMessage("文章尚未发布，请等待系统审核或联系客服。")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).create();
                    dialog.show();
                } else {
                    int id = myCreateArticle.getId();
                    String title = myCreateArticle.getTitle();
                    String url = AppConfig.FOUND_WEB_URL + "/HotDetail?id=" + id + "&title=" + title + "&type=" + AppConfig.BASE_URL;
                    WebActivity.start(getContext(), url, title, false);
                }
            }
        });
        mView.setAdapter(adapter);
    }

    @Override
    protected void onLoadFinish() {
        super.onLoadFinish();
        if (adapter.getItemCount() == 0) {
            mView.showCreateAticle();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        loadData(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    private void deleteMyArticle(int id) {
        apiManager.deleteMyArticle(GlobalData.getInstance().getUserId(), id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<APIResponse>() {
                    @Override
                    public void accept(APIResponse apiResponse) throws Exception {
                        if (apiResponse.isSuccess()) {
                            loadData(false);
                        }
                        Toaster.showShort(getContext(), apiResponse.getMsg() + "");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

}
