package com.a1magway.bgg.p.member;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.data.entity.Permission;
import com.a1magway.bgg.util.AndroidUtil;
import java.util.List;
import java.util.Locale;

import static com.a1magway.bgg.p.member.MemberCardViewBinder.MemberCardVH.PERMISSION_CLOSE_ID;

/** author: Beaven date: 2017/10/20 9:39 */
public class MemberCardsPermissionAdapter
        extends BaseRecyclerAdapter<
                MemberCardsPermissionAdapter.MemberCardsPermissionVH, Permission> {
    public MemberCardsPermissionAdapter(List<Permission> list) {
        super(list);
    }

    @Override
    public MemberCardsPermissionVH onRealCreateViewHolder(ViewGroup parent, int viewType) {
        return new MemberCardsPermissionVH(parent);
    }

    @Override
    public void onRealBindViewHolder(MemberCardsPermissionVH holder, int position) {
        super.onRealBindViewHolder(holder, position);
        holder.showViewContent(getItem(position));
    }

    static class MemberCardsPermissionVH extends BaseRecyclerVH<Permission> {

        @BindView(R.id.text_card_permission_normal)
        TextView textPermission;

        @BindView(R.id.layout_permission)
        LinearLayout layoutPermission;

        @BindView(R.id.layout_received)
        LinearLayout layoutReceived;

        @BindView(R.id.text_received_des)
        TextView textReceived;

        int[] colorId = {
            R.color.member_card_item_bg_one,
            R.color.member_card_item_bg_two,
            R.color.member_card_item_bg_three,
            R.color.member_card_item_bg_four,
            R.color.member_card_item_bg_three,
            R.color.member_card_item_bg_four,
            R.color.member_card_item_bg_one,
            R.color.member_card_item_bg_two,
            R.color.member_card_item_bg_two,
        };

        public MemberCardsPermissionVH(@NonNull ViewGroup parent) {
            super(parent, R.layout.item_member_card_permisson);
            int height = AndroidUtil.getScreenWidth(getContext()) / 4;
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
            itemView.setLayoutParams(params);
        }

        @Override
        public void showViewContent(Permission permission) {
            super.showViewContent(permission);
            if (permission.getPermissionsType() == Permission.PERMISSION_TYPE_BOOLEAN) {
                if (permission.getCount() > 0) {
                    showDefault(permission);
                } else {
                    showReceived(permission);
                }
            } else {
                showDefault(permission);
            }
        }

        private void showDefault(Permission permission) {
            textPermission.setVisibility(View.VISIBLE);
            layoutReceived.setVisibility(View.GONE);
            if (permission.getId() == PERMISSION_CLOSE_ID) {
                textPermission.setBackground(
                        getContext()
                                .getResources()
                                .getDrawable(R.drawable.enable_dark_corners_five));
                textPermission.setTextColor(Color.WHITE);
                textPermission.setPadding(10, 0, 10, 0);
            } else {
                int num = getAdapterPosition() % colorId.length;
                layoutPermission.setBackgroundColor(
                        getContext().getResources().getColor(colorId[num]));
            }
            if (permission.getId() == 9) {
                String text =
                        String.format(
                                Locale.getDefault(),
                                "%s%d次",
                                permission.getDescription(),
                                permission.getCount());
                textPermission.setText(text);
            } else {
                textPermission.setText(permission.getDescription());
            }
        }

        private void showReceived(Permission permission) {
            textPermission.setVisibility(View.GONE);
            layoutReceived.setVisibility(View.VISIBLE);
            layoutPermission.setBackground(
                    getContext()
                            .getResources()
                            .getDrawable(R.drawable.member_card_des_received_bg));
            textReceived.setText(permission.getDescription());
        }
    }
}
