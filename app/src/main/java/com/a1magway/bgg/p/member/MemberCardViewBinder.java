package com.a1magway.bgg.p.member;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.data.entity.Cards;
import com.a1magway.bgg.data.entity.Permission;
import com.a1magway.bgg.util.AndroidUtil;
import com.a1magway.bgg.util.DateUtils;
import com.a1magway.bgg.widget.divider.GridItemDecoration;
import com.almagway.common.recycler.BaseRecyclerViewBinder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/** author: Beaven date: 2017/10/19 16:57 */
public class MemberCardViewBinder
        extends BaseRecyclerViewBinder<Cards, MemberCardViewBinder.MemberCardVH> {

    @NonNull
    @Override
    protected MemberCardVH onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new MemberCardVH(parent);
    }

    @Override
    protected void onBindViewHolder(@NonNull MemberCardVH holder, @NonNull Cards item) {
        holder.showViewContent(item);
    }

    static class MemberCardVH extends BaseRecyclerVH<Cards> {

        private int mHiddenViewMeasuredHeight;
        static final int PERMISSION_CLOSE_ID = 0x10000;

        @BindView(R.id.layout_card_info)
        LinearLayout layoutCardInfo;

        @BindView(R.id.recycler_card)
        RecyclerView recyclerCard;

        @BindView(R.id.text_card_time)
        TextView textCardTime;

        @BindView(R.id.view_bg)
        View viewBg;

        private Cards cards;

        MemberCardVH(@NonNull ViewGroup parent) {
            super(parent, R.layout.item_member_card);
            itemView.post(
                    new Runnable() {
                        @Override
                        public void run() {
                            ViewGroup.MarginLayoutParams params =
                                    (ViewGroup.MarginLayoutParams) viewBg.getLayoutParams();
                            int height = itemView.getHeight();
                            params.topMargin = height / 2;
                            viewBg.setLayoutParams(params);
                        }
                    });
        }

        @Override
        public void showViewContent(Cards cards) {
            super.showViewContent(cards);
            this.cards = cards;
            textCardTime.setText(getCardTime(cards));
            setCardInfoAdapter(cards);
        }

        private void setCardInfoAdapter(Cards cards) {
            GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
            if (cards.getPermission() == null || cards.getPermission().size() == 0) return;
            int size = cards.getPermission().size() + 1;
            List<Permission> permissionList = new ArrayList<>(size);
            permissionList.addAll(cards.getPermission());
            permissionList.add(createClosePermission());
            final MemberCardsPermissionAdapter adapter =
                    new MemberCardsPermissionAdapter(permissionList);
            manager.setSpanSizeLookup(
                    new GridLayoutManager.SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) {
                            // 剩余海外包邮次数item是两列
                            int id = adapter.getItem(position).getId();
                            if (id == 9 || id == PERMISSION_CLOSE_ID) return 2;
                            return 1;
                        }
                    });
            adapter.setOnItemClickListener(
                    new BaseRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View v, int position) {
                            if (adapter.getItem(position).getId() == PERMISSION_CLOSE_ID) {
                                close();
                            }
                        }

                        @Override
                        public void onItemLongClick(View v, int position) {}
                    });
            recyclerCard.setLayoutManager(manager);
            recyclerCard.setHasFixedSize(true);
            recyclerCard.addItemDecoration(
                    new GridItemDecoration(
                            getContext(),
                            R.color.transparent,
                            R.dimen.product_detail_divider,
                            R.dimen.product_detail_divider));
            recyclerCard.setAdapter(adapter);
        }

        private Permission createClosePermission() {
            Permission closePermission = new Permission();
            closePermission.setDescription("收起");
            closePermission.setId(PERMISSION_CLOSE_ID);
            closePermission.setPermissionsStatus(true);
            return closePermission;
        }

        private String getCardTime(Cards cards) {
            String startDate =
                    DateUtils.dateFormatConversion(
                            cards.getStartTime(), DateUtils.TIME_DEFAULT_ONE, DateUtils.DATE_ONE);
            String endDate =
                    DateUtils.dateFormatConversion(
                            cards.getEndTime(), DateUtils.TIME_DEFAULT_ONE, DateUtils.DATE_ONE);
            return startDate + " / " + endDate;
        }

        private int getInfoHeight(Cards cards) {
            if (cards.getPermission() == null || cards.getPermission().size() == 0) {
                return 0;
            }
            int size = cards.getPermission().size();
            int row;
            if (size % 4 == 0) {
                row = size / 4;
            } else {
                row = size / 4 + 1;
            }
            int oneHeight = AndroidUtil.getScreenWidth(getContext()) / 4;
            int height = itemView.getHeight() / 2;
            return oneHeight * row + height;
        }

        @OnClick(R.id.image_card)
        public void clickCard(View view) {
            if (mHiddenViewMeasuredHeight == 0) {
                mHiddenViewMeasuredHeight = getInfoHeight(cards);
            }
            if (layoutCardInfo.getVisibility() == View.VISIBLE) {
                close();
            } else {
                open();
            }
        }

        private void open() {
            ValueAnimator animator = createAnimator(0, mHiddenViewMeasuredHeight);
            animator.addListener(
                    new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            layoutCardInfo.setVisibility(View.VISIBLE);
                        }
                    });
            animator.start();
        }

        private void close() {
            ValueAnimator animator = createAnimator(mHiddenViewMeasuredHeight, 0);
            animator.addListener(
                    new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);
                            layoutCardInfo.setVisibility(View.GONE);
                        }
                    });
            animator.start();
        }

        private ValueAnimator createAnimator(int start, int end) {
            ValueAnimator animator = ValueAnimator.ofInt(start, end).setDuration(400);
            animator.addUpdateListener(
                    new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            int value = (int) animation.getAnimatedValue();
                            ViewGroup.LayoutParams params = viewBg.getLayoutParams();
                            params.height = value;
                            viewBg.setLayoutParams(params);
                        }
                    });
            return animator;
        }
    }
}
