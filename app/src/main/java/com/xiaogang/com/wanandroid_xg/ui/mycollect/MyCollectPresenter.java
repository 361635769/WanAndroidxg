package com.xiaogang.com.wanandroid_xg.ui.mycollect;

import com.xiaogang.com.wanandroid_xg.base.BasePresenter;
import com.xiaogang.com.wanandroid_xg.bean.Article;
import com.xiaogang.com.wanandroid_xg.bean.DataResponse;
import com.xiaogang.com.wanandroid_xg.net.ApiServer;
import com.xiaogang.com.wanandroid_xg.net.RetrofitManager;
import com.xiaogang.com.wanandroid_xg.utils.RxSchedulers;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * author: fangxiaogang
 * date: 2018/9/10
 */

public class MyCollectPresenter extends BasePresenter<MyCollectContract.View> implements MyCollectContract.Presenter {

    private int mPage = 0;

    private boolean isRefresh = true;

    @Inject
    public MyCollectPresenter (){

    }


    @Override
    public void getMyCollects() {
        RetrofitManager.create(ApiServer.class)
                .getMyCollect(mPage)
                .compose(RxSchedulers.<DataResponse<Article>>applySchedulers())
                .compose(mView.<DataResponse<Article>>bindToLife())
                .subscribe(new Consumer<DataResponse<Article>>() {
                    @Override
                    public void accept(DataResponse<Article> myCollectDataResponse) throws Exception {
                        if (isRefresh) {
                            mView.getMyCollectSuccess(myCollectDataResponse.getData(),0);
                        }else {
                            mView.getMyCollectSuccess(myCollectDataResponse.getData(),1);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void refresh() {
        mPage = 0;
        isRefresh = true;
        getMyCollects();
    }

    @Override
    public void loadMore() {
        mPage ++;
        isRefresh = false;
        getMyCollects();
    }


}
