package com.hulk.voicetube.profile;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.hulk.voicetube.MainActivity;
import com.hulk.voicetube.R;
import com.hulk.voicetube.util.HidingScrollListener;
import com.hulk.voicetube.util.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context context = MainActivity.getInstance();
    private BottomNavigationView mBottomNavigationView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Handler handler = new Handler();
    private List<Profile> profileList = new ArrayList<>();

    public ProfileFragment() {
        // Requires empty public constructor
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_frag, container, false);

        //RecyclerView的初始化
        mRecyclerView = view.findViewById(R.id.rcv_profile);
        //创建线性LinearLayoutManager
        mLayoutManager = new LinearLayoutManager(getActivity());
        //设置LayoutManager
        mRecyclerView.setLayoutManager(mLayoutManager);
        //设置item的动画，可以不设置
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置item的分割线
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL));
//        mRecyclerView.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL, R.drawable.divider_mileage));
        MyAdapter adapter = new MyAdapter(initPersonData());

        //获取主页面中底部导航
        mBottomNavigationView = getActivity().findViewById(R.id.view_navigation);

        //下拉刷新
        mSwipeRefreshLayout = view.findViewById(R.id.SwipeRefreshLayout_profile);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.blueStatus);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
//                mSwipeRefreshLayout.setRefreshing(true);
//                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        profileList.clear();
                        initPersonData();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        //添加RecycleView滚动事件监听，实现向下滚动，隐藏底部导航栏，向上滚动，显示底部导航栏
        mRecyclerView.addOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                Resources resources = context.getResources();
                DisplayMetrics dm = resources.getDisplayMetrics();
                float density = dm.density;
                int width = dm.widthPixels;
                int height = dm.heightPixels;

//                top.animate()
//                        .translationY(-height)
//                        .setDuration(800)
//                        .setInterpolator(new AccelerateInterpolator(2))
//                        .start();

                mBottomNavigationView.animate()
                        .translationY(height - mBottomNavigationView.getHeight())
                        .setInterpolator(new AccelerateInterpolator(2))
                        .setDuration(800)
                        .start();

            }

            @Override
            public void onShow() {
//                top.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).setDuration(800).start();
                mBottomNavigationView.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).setDuration(800).start();
            }
        });

        //设置Adapter
        mRecyclerView.setAdapter(adapter);

        return view;
    }

    private List<Profile> initPersonData() {
        profileList.add(new Profile("hulk", "1", "2", "3", "4", "5"));
        return profileList;
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<Profile> profileList;

        public MyAdapter(List<Profile> profileList) {
            this.profileList = profileList;
        }

        /**
         * 创建ViewHolder的布局
         *
         * @param parent
         * @param viewType
         * @return
         */
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_item, parent, false);
            return new MyAdapter.ViewHolder(view);
        }

        /**
         * 通过ViewHolder将数据绑定到界面上进行显示
         *
         * @param holder
         * @param position
         */
        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {
            holder.nickname.setText(profileList.get(position).getNickname());
            holder.watched.setText(profileList.get(position).getWatched());
            holder.streak.setText(profileList.get(position).getStreak());
            holder.recorded.setText(profileList.get(position).getRecorded());
            holder.spent.setText(profileList.get(position).getSpent());
            holder.collected.setText(profileList.get(position).getCollected());

        }

        @Override
        public int getItemCount() {
            return profileList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView nickname;
            public TextView watched;
            public TextView streak;
            public TextView recorded;
            public TextView spent;
            public TextView collected;

            public ViewHolder(View itemView) {
                super(itemView);
                nickname = itemView.findViewById(R.id.profile_nickname);
                watched = itemView.findViewById(R.id.profile_watched);
                streak = itemView.findViewById(R.id.profile_streak);
                recorded = itemView.findViewById(R.id.profile_recorded);
                spent = itemView.findViewById(R.id.profile_spent);
                collected = itemView.findViewById(R.id.profile_collected);
            }
        }
    }
}