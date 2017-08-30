package com.hulk.voicetube.blog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.hulk.voicetube.MainActivity;
import com.hulk.voicetube.R;

import java.lang.reflect.Field;

public class BlogFragment extends Fragment {

    private static final String TAG = "BlogFragment";
    private TabLayout mTabLayout;

    private Context context = MainActivity.getInstance();

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FragmentTransaction fragmentTransactionTab;
    private BlogsFragment blogsFragment;
    private CollectionFragment collectionFragment;

    private MaterialDialog.Builder builder;
    private MaterialDialog dialog;


    public BlogFragment() {
        // Requires empty public constructor
    }

    public static BlogFragment newInstance() {
        return new BlogFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //获取 BlogFragment 子管理器
        fragmentManager = getChildFragmentManager();

        fragmentTransaction = fragmentManager.beginTransaction();
        blogsFragment = BlogsFragment.newInstance();
        fragmentTransaction.replace(R.id.view_blog, blogsFragment);
        fragmentTransaction.commit();

        final View view = inflater.inflate(R.layout.blog_frag, container, false);

        //标签页相关
        mTabLayout = view.findViewById(R.id.tab_blog);

        //标签页增加点击事件
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            final TabLayout.Tab tab = mTabLayout.getTabAt(i);
            //这里使用到反射，拿到Tab对象后获取Class
            Class c = tab.getClass();
            try {
                //Filed “字段、属性”的意思,c.getDeclaredField 获取私有属性。
                //"mView"是Tab的私有属性名称(可查看TabLayout源码),类型是 TabView,TabLayout私有内部类。
                Field field = c.getDeclaredField("mView");
                //值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。值为 false 则指示反射的对象应该实施 Java 语言访问检查。
                //如果不这样会报如下错误
                // java.lang.IllegalAccessException:
                //Class com.test.accessible.Main
                //can not access
                //a member of class com.test.accessible.AccessibleTest
                //with modifiers "private"
                field.setAccessible(true);
                final View tabView = (View) field.get(tab);
                tabView.setTag(i);
                tabView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = (int) tabView.getTag();
                        //处理点击事件
                        showFragment(position);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    //切换Blog模块下的各个标签页
    private void showFragment(int position) {
        fragmentTransactionTab = fragmentManager.beginTransaction();

        switch (position) {
            //Blog下第一个标签页 Updates
            case 0:
                //如果 collectionFragment 不等于空，就把他隐藏掉
                if (collectionFragment != null) {
                    Log.d(TAG, "onTabSelected: collection is not null");
                    fragmentTransactionTab.hide(collectionFragment);
                }
                //如果fragment还没创建就创建一个，然后add
                //如果fragment已经创建了，就show
                if (blogsFragment == null) {
                    Log.d(TAG, "onTabSelected: updates is null");
                    blogsFragment = BlogsFragment.newInstance();
                    fragmentTransactionTab.add(R.id.view_blog, blogsFragment);
                } else
                    fragmentTransactionTab.show(blogsFragment);
                break;
            //Blog下第二个标签页 Collection
            case 1:
                //如果 blogsFragment 不等于空，就把他隐藏掉
                if (blogsFragment != null) {
                    Log.d(TAG, "onTabSelected: updates is not null");
                    fragmentTransactionTab.hide(blogsFragment);
                }

                //如果fragment还没创建就创建一个，然后add
                //如果fragment已经创建了，就show
                if (collectionFragment == null) {
                    Log.d(TAG, "onTabSelected: collection is null");
                    collectionFragment = CollectionFragment.newInstance();
                    fragmentTransactionTab.add(R.id.view_blog, collectionFragment);
                } else
                    fragmentTransactionTab.show(collectionFragment);
                break;
        }
        fragmentTransactionTab.commit();
    }

}

