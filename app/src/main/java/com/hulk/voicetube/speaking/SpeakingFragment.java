package com.hulk.voicetube.speaking;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.hulk.voicetube.MainActivity;
import com.hulk.voicetube.R;
import com.hulk.voicetube.util.HidingScrollListener;
import com.hulk.voicetube.util.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

public class SpeakingFragment extends Fragment {

    private static final String TAG = "SpeakingFragment";
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context context = MainActivity.getInstance();
    private BottomNavigationView mBottomNavigationView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Handler handler = new Handler();
    private List<Speaking> speakingList = new ArrayList<>();

    public SpeakingFragment() {
        // Requires empty public constructor
    }

    public static SpeakingFragment newInstance() {
        return new SpeakingFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.speaking_frag, container, false);
        //RecyclerView的初始化
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rcv_speaking);
        //创建线性LinearLayoutManager
        mLayoutManager = new LinearLayoutManager(getActivity());
        //设置LayoutManager
        mRecyclerView.setLayoutManager(mLayoutManager);
        //设置item的动画，可以不设置
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置item的分割线
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL));
//        mRecyclerView.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL, R.drawable.divider_mileage));
        SpeakingFragment.MyAdapter adapter = new SpeakingFragment.MyAdapter(initPersonData());

        //获取主页面中底部导航
        mBottomNavigationView = (BottomNavigationView) getActivity().findViewById(R.id.view_navigation);

        //下拉刷新
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.SwipeRefreshLayout_speaking);
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
                        speakingList.clear();
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

    private List<Speaking> initPersonData() {
        speakingList.add(new Speaking(R.mipmap.news_one, "This is title", "This is short desc", "Hulk", "Aug 4 2017"));
        speakingList.add(new Speaking(R.mipmap.news_one, "This is title", "This is short desc", "Hulk", "Aug 4 2017"));
        speakingList.add(new Speaking(R.mipmap.news_one, "This is title", "This is short desc", "Hulk", "Aug 4 2017"));
        speakingList.add(new Speaking(R.mipmap.news_one, "This is title", "This is short desc", "Hulk", "Aug 4 2017"));
        speakingList.add(new Speaking(R.mipmap.news_one, "This is title", "This is short desc", "Hulk", "Aug 4 2017"));
        speakingList.add(new Speaking(R.mipmap.news_one, "This is title", "This is short desc", "Hulk", "Aug 4 2017"));
        return speakingList;
    }

    class MyAdapter extends RecyclerView.Adapter<SpeakingFragment.MyAdapter.ViewHolder> {
        private List<Speaking> speaking;

        public MyAdapter(List<Speaking> speaking) {
            this.speaking = speaking;
        }

        /**
         * 创建ViewHolder的布局
         *
         * @param parent
         * @param viewType
         * @return
         */
        @Override
        public SpeakingFragment.MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.speaking_item, parent, false);
            return new SpeakingFragment.MyAdapter.ViewHolder(view);
        }

        /**
         * 通过ViewHolder将数据绑定到界面上进行显示
         *
         * @param holder
         * @param position
         */
        @Override
        public void onBindViewHolder(SpeakingFragment.MyAdapter.ViewHolder holder, final int position) {
            holder.mImageView.setImageResource(speaking.get(position).getPhotoId());
            holder.speakingTitle.setText(speaking.get(position).getTitle());
            holder.author.setText(speaking.get(position).getAuthor());
            holder.speakingDesc.setText(speaking.get(position).getDesc());
            holder.postedTime.setText(speaking.get(position).getPostedTime());
        }

        @Override
        public int getItemCount() {
            return speaking.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public CardView mCardView;
            public ImageView mImageView;
            public TextView speakingTitle;
            public TextView author;
            public TextView speakingDesc;
            public TextView postedTime;


            public ViewHolder(View itemView) {
                super(itemView);
                mCardView = itemView.findViewById(R.id.card_view_speaking);
                mImageView = itemView.findViewById(R.id.iv_speaking_pic);
                speakingTitle = itemView.findViewById(R.id.tv_speaking_title);
                author = itemView.findViewById(R.id.tv_speaking_author);
                speakingDesc = itemView.findViewById(R.id.tv_speaking_desc);
                postedTime = itemView.findViewById(R.id.tv_speaking_posted_time);
            }
        }
    }
}