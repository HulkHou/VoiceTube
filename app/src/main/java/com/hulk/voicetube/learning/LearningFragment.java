package com.hulk.voicetube.learning;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.hulk.voicetube.MainActivity;
import com.hulk.voicetube.R;

import java.lang.reflect.Field;

public class LearningFragment extends Fragment {

    private static final String TAG = "LearningFragment";
    private Toolbar mToolbar;
    private SearchView mSearchView;
    private TabLayout mTabLayout;

    private Context context = MainActivity.getInstance();

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FragmentTransaction fragmentTransactionTab;
    private LevelsFragment levelsFragment;
    private ChannelsFragment channelsFragment;

    private MaterialDialog.Builder builder;
    private MaterialDialog dialog;


    public LearningFragment() {
        // Requires empty public constructor
    }

    public static LearningFragment newInstance() {
        return new LearningFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //获取 LearningFragment 子管理器
        fragmentManager = getChildFragmentManager();

        fragmentTransaction = fragmentManager.beginTransaction();
        levelsFragment = LevelsFragment.newInstance();
        fragmentTransaction.replace(R.id.view_learning, levelsFragment);
        fragmentTransaction.commit();

        final View view = inflater.inflate(R.layout.learning_frag, container, false);

        //设置 Toolbar 相关操作
        //直接在 Fragment 中使用Toolbar ，不与ActionBar 关联使用
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar_learning);
        mToolbar.inflateMenu(R.menu.learning_toolbar_menu);

        //搜索框相关
        mSearchView = (SearchView) view.findViewById(R.id.learning_search_menu);
        //设置搜索框点击之后的提示语
        mSearchView.setQueryHint(context.getString(R.string.learning_search));

        //搜索框展开时后面叉叉按钮的点击事件
        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Toast.makeText(context, "Close", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //搜索图标按钮(打开搜索框的按钮)的点击事件
        mSearchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Open", Toast.LENGTH_SHORT).show();
            }
        });

        //搜索框文字变化监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "onQueryTextSubmit: onQueryTextSubmit");
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, "onQueryTextChange: onQueryTextChange");
                return true;
            }
        });

        //标签页相关
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_learning);

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
//                        showFragment(position);
                        showTabChooseDialogs(position, tab);
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

        //Toolbar 监听Menu
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return true;
            }
        });

        return view;
    }

    //切换Learning模块下的各个标签页
    private void showFragment(int position) {
        fragmentTransactionTab = fragmentManager.beginTransaction();

        switch (position) {
            //Learning下第一个标签页 Levels
            case 0:
                //如果 channelsFragment 不等于空，就把他隐藏掉
                if (channelsFragment != null) {
                    Log.d(TAG, "onTabSelected: channels is not null");
                    fragmentTransactionTab.hide(channelsFragment);
                }
                //如果fragment还没创建就创建一个，然后add
                //如果fragment已经创建了，就show
                if (levelsFragment == null) {
                    Log.d(TAG, "onTabSelected: levels is null");
                    levelsFragment = LevelsFragment.newInstance();
                    fragmentTransactionTab.add(R.id.view_learning, levelsFragment);
                } else
                    fragmentTransactionTab.show(levelsFragment);
                break;
            //Learning下第二个标签页 Channels
            case 1:
                //如果 levelsFragment 不等于空，就把他隐藏掉
                if (levelsFragment != null) {
                    Log.d(TAG, "onTabSelected: levels is not null");
                    fragmentTransactionTab.hide(levelsFragment);
                }

                //如果fragment还没创建就创建一个，然后add
                //如果fragment已经创建了，就show
                if (channelsFragment == null) {
                    Log.d(TAG, "onTabSelected: channels is null");
                    channelsFragment = ChannelsFragment.newInstance();
                    fragmentTransactionTab.add(R.id.view_learning, channelsFragment);
                } else
                    fragmentTransactionTab.show(channelsFragment);
                break;
        }
        fragmentTransactionTab.commit();
    }

    //点击标签页显示弹出框
    private void showTabChooseDialogs(int position, TabLayout.Tab tab) {

        final TabLayout.Tab showTab = tab;

        switch (position) {
            //Learning下第一个标签页 Levels的对话框 使用BASIC LIST对话框
            case 0:
                builder = new MaterialDialog.Builder(context)
                        .items(R.array.levels_item)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                showTab.setText(text);
                                Toast.makeText(context, text,
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            //Learning下第二个标签页 Channels的对话框
            case 1:
                builder = new MaterialDialog.Builder(context)
                        .items(R.array.channels_item)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                showTab.setText(text);
                                Toast.makeText(context, text,
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            //Learning下第三个标签页 Accents的对话框
            case 2:
                builder = new MaterialDialog.Builder(context)
                        .items(R.array.accents_item)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                showTab.setText(text);
                                Toast.makeText(context, text,
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            //Learning下第四个标签页 Videos的对话框
            case 3:
                builder = new MaterialDialog.Builder(context)
                        .items(R.array.videos_item)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                showTab.setText(text);
                                Toast.makeText(context, text,
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
        }
        dialog = builder.build();
        dialog.show();
    }

}

