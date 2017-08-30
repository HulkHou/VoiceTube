package com.hulk.voicetube.blog;

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

public class BlogsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context context = MainActivity.getInstance();
    private BottomNavigationView mBottomNavigationView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private Handler handler = new Handler();

    private List<Blogs> blogList = new ArrayList<>();


    public BlogsFragment() {
        // Requires empty public constructor
    }

    public static BlogsFragment newInstance() {
        return new BlogsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.blogs_frag, container, false);
        //RecyclerView的初始化
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rcv_blogs);
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
        mBottomNavigationView = (BottomNavigationView) getActivity().findViewById(R.id.view_navigation);

        //下拉刷新
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.SwipeRefreshLayout_blogs);
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
                        blogList.clear();
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

    private List<Blogs> initPersonData() {
        blogList.add(new Blogs(getString(R.string.news_one_title), R.mipmap.news_one, "Aug 4 2017", "1024 Pageviews", "Hulk", "This is short desc"));
        blogList.add(new Blogs(getString(R.string.news_two_title), R.mipmap.news_two));
        blogList.add(new Blogs(getString(R.string.news_three_title), R.mipmap.news_three));
        blogList.add(new Blogs(getString(R.string.news_four_title), R.mipmap.news_four));
        blogList.add(new Blogs(getString(R.string.news_one_title), R.mipmap.news_one, "Aug 4 2017", "1024 Pageviews", "Hulk", "This is short desc"));
        blogList.add(new Blogs(getString(R.string.news_two_title), R.mipmap.news_two));
        blogList.add(new Blogs(getString(R.string.news_three_title), R.mipmap.news_three));
        blogList.add(new Blogs(getString(R.string.news_four_title), R.mipmap.news_four));
        blogList.add(new Blogs(getString(R.string.news_one_title), R.mipmap.news_one, "Aug 4 2017", "1024 Pageviews", "Hulk", "This is short desc"));
        blogList.add(new Blogs(getString(R.string.news_two_title), R.mipmap.news_two));
        blogList.add(new Blogs(getString(R.string.news_three_title), R.mipmap.news_three));
        blogList.add(new Blogs(getString(R.string.news_four_title), R.mipmap.news_four));
        blogList.add(new Blogs(getString(R.string.news_one_title), R.mipmap.news_one, "Aug 4 2017", "1024 Pageviews", "Hulk", "This is short desc"));
        blogList.add(new Blogs(getString(R.string.news_two_title), R.mipmap.news_two));
        blogList.add(new Blogs(getString(R.string.news_three_title), R.mipmap.news_three));
        blogList.add(new Blogs(getString(R.string.news_four_title), R.mipmap.news_four));
        blogList.add(new Blogs(getString(R.string.news_one_title), R.mipmap.news_one, "Aug 4 2017", "1024 Pageviews", "Hulk", "This is short desc"));
        blogList.add(new Blogs(getString(R.string.news_two_title), R.mipmap.news_two));
        blogList.add(new Blogs(getString(R.string.news_three_title), R.mipmap.news_three));
        blogList.add(new Blogs(getString(R.string.news_four_title), R.mipmap.news_four));

        return blogList;
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<Blogs> blog;

        public MyAdapter(List<Blogs> blog) {
            this.blog = blog;
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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blogs_item, parent, false);
            return new ViewHolder(view);
        }

        /**
         * 通过ViewHolder将数据绑定到界面上进行显示
         *
         * @param holder
         * @param position
         */
        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {
            holder.mImageView.setImageResource(blog.get(position).getPhotoId());
            holder.blogTitle.setText(blog.get(position).getTitle());
            holder.createTime.setText(blog.get(position).getCreateTime());
            holder.pageViews.setText(blog.get(position).getPageViews());
            holder.author.setText(blog.get(position).getAuthor());
            holder.blogDesc.setText(blog.get(position).getBlogDesc());

            //为btn_share btn_readMore cardView设置点击事件
//            holder.mCardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context, BlogsFragment.class);
//                    intent.putExtra("Blogs", blog.get(position));
//                    context.startActivity(intent);
//                }
//            });

//            holder.blog_share.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(Intent.ACTION_SEND);
//                    intent.setType("text/plain");
//                    intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
//                    intent.putExtra(Intent.EXTRA_TEXT, blog.get(position).getDesc());
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(Intent.createChooser(intent, blog.get(position).getTitle()));
//                }
//            });
//
//            holder.blog_readMore.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context, BlogsFragment.class);
//                    intent.putExtra("News", blog.get(position));
//                    context.startActivity(intent);
//                }
//            });
        }

        @Override
        public int getItemCount() {
            return blog.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public CardView mCardView;
            public ImageView mImageView;
            public TextView blogTitle;
            public TextView createTime;
            public TextView pageViews;
            public TextView author;
            public TextView blogDesc;

            public ViewHolder(View itemView) {
                super(itemView);
                mCardView = itemView.findViewById(R.id.card_view_blogs);
                mImageView = itemView.findViewById(R.id.blogs_photo);
                blogTitle = itemView.findViewById(R.id.blogs_title);
                createTime = itemView.findViewById(R.id.blogs_createTime);
                pageViews = itemView.findViewById(R.id.blogs_page_views);
                author = itemView.findViewById(R.id.blogs_author);
                blogDesc = itemView.findViewById(R.id.blogs_desc);
            }
        }
    }
}

