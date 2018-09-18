package com.xiaogang.com.wanandroid_xg.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.blankj.utilcode.util.ToastUtils;
import com.chaychan.library.BottomBarItem;
import com.chaychan.library.BottomBarLayout;
import com.xiaogang.com.wanandroid_xg.R;
import com.xiaogang.com.wanandroid_xg.SupportFragment;
import com.xiaogang.com.wanandroid_xg.base.BaseActivity;
import com.xiaogang.com.wanandroid_xg.ui.home.HomeFragment;

import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottombar)
    BottomBarLayout mbottomBarLayout;

    private SupportFragment[] mFragments = new SupportFragment[1];

    private long mExitTime;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        SupportFragment homeFragment = findFragment(HomeFragment.class);
        if (homeFragment == null) {
            mFragments[0] = HomeFragment.newInstance();
//            mFragments[1] = HomeFragment.newInstance();
//            mFragments[2] = HomeFragment.newInstance();
//            mFragments[3] = HomeFragment.newInstance();
            loadMultipleRootFragment(R.id.layout_fragment, 0,
                    mFragments[0]
//                    ,
//                    mFragments[1],
//                    mFragments[2],
//                    mFragments[3]
            );
        } else {
            // 这里我们需要拿到mFragments的引用
            mFragments[0] = homeFragment;
//            mFragments[1] = findFragment(HomeFragment.class);
//            mFragments[2] = findFragment(HomeFragment.class);
//            mFragments[3] = findFragment(HomeFragment.class);
        }


        mbottomBarLayout.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(BottomBarItem bottomBarItem, int i, int i1) {

            }
        });
    }

    @Override
    protected void initInjector() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                ToastUtils.showShort("再按一次退出程序");
                mExitTime = System.currentTimeMillis();
            } else {
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}