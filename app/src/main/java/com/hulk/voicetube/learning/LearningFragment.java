package com.hulk.voicetube.learning;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hulk.voicetube.MainActivity;
import com.hulk.voicetube.R;

public class LearningFragment extends Fragment {

    private static final String TAG = "LearningFragment";
    private Toolbar mToolbar;
    private SearchView mSearchView;
    private TabLayout mTabLayout;

    private Context context = MainActivity.getInstance();

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
        View view = inflater.inflate(R.layout.learning_frag, container, false);

        //设置 Toolbar 相关操作
        //直接在 Fragment 中使用Toolbar ，不与ActionBar 关联使用
        mToolbar = (Toolbar) view.findViewById(R.id.learning_toolbar);
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
        mTabLayout = (TabLayout) view.findViewById(R.id.learning_tab);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(context, tab.getText(), Toast.LENGTH_SHORT).show();
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

}

