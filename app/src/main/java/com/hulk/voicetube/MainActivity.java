package com.hulk.voicetube;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.hulk.voicetube.blog.BlogFragment;
import com.hulk.voicetube.learning.LearningFragment;
import com.hulk.voicetube.profile.ProfileFragment;

/**
 * Created by hulk-out on 2017/8/9.
 */

public class MainActivity extends AppCompatActivity {

    private static MainActivity instance;

    //fragment管理器
    FragmentManager fragmentManager;

    //fragment
    LearningFragment learningFragment;
    BlogFragment blogFragment;
    ProfileFragment profileFragment;


    //底部导航栏view
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.main_act);

        //获取 fragment 管理器
        fragmentManager = getSupportFragmentManager();

        //默认显示LearningFragment
        //获得Fragment 事务处理器
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        learningFragment = LearningFragment.newInstance();
        fragmentTransaction.replace(R.id.view_content, learningFragment);
        fragmentTransaction.commit();

        navigationView = (BottomNavigationView) findViewById(R.id.view_navigation);

        //为navigation设置监听
        navigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        //获取事务处理器
                        FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();

                        switch (item.getItemId()) {

                            //当选中的item的id是 navigation_learning 时，
                            //隐藏其他fragment，把navigation_learning显示出来
                            case R.id.navigation_learning:

                                //如果 blogFragment 不等于空，就把他隐藏掉
                                if (blogFragment != null) {
                                    fragmentTransaction1.hide(blogFragment);
                                }
                                if (profileFragment != null) {
                                    fragmentTransaction1.hide(profileFragment);
                                }

                                //如果fragment还没创建就创建一个，然后add
                                //如果fragment已经创建了，就show
                                if (learningFragment == null) {
                                    learningFragment = LearningFragment.newInstance();
                                    fragmentTransaction1.add(R.id.view_content, learningFragment);
                                } else
                                    fragmentTransaction1.show(learningFragment);
                                break;

                            case R.id.navigation_blog:
                                if (learningFragment != null) {
                                    fragmentTransaction1.hide(learningFragment);
                                }
                                if (profileFragment != null) {
                                    fragmentTransaction1.hide(profileFragment);
                                }
                                if (blogFragment == null) {
                                    blogFragment = BlogFragment.newInstance();
                                    fragmentTransaction1.add(R.id.view_content, blogFragment);
                                } else
                                    fragmentTransaction1.show(blogFragment);
                                break;

                            case R.id.navigation_profile:
                                if (learningFragment != null) {
                                    fragmentTransaction1.hide(learningFragment);
                                }
                                if (blogFragment != null) {
                                    fragmentTransaction1.hide(blogFragment);
                                }
                                if (profileFragment == null) {
                                    profileFragment = ProfileFragment.newInstance();
                                    fragmentTransaction1.add(R.id.view_content, profileFragment);
                                } else
                                    fragmentTransaction1.show(profileFragment);
                                break;


                        }
                        fragmentTransaction1.commit();

                        //一定要返回true，否则会选择不了item
                        return true;
                    }
                }
        );
    }

    //单例模式 来对外保持 MainActivity 的引用
    public static MainActivity getInstance() {
        return instance;
    }
}
