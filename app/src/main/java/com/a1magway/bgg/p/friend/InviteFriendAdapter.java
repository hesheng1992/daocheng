package com.a1magway.bgg.p.friend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.data.entity.InviteFriendData;
import com.a1magway.bgg.okhttp.LogUtil;
import com.a1magway.bgg.util.ImageLoaderUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by enid on 2018/8/16.
 */

public class InviteFriendAdapter extends RecyclerView.Adapter<InviteFriendAdapter.ViewHold> implements View.OnClickListener {

    private Context context;
    private List<InviteFriendData> data;
    private ClickInviteListener listener;

    public InviteFriendAdapter(Context context, List<InviteFriendData> data) {
        this.context = context;
        this.data = data;
        if (this.data != null) {
            dataInit();
        }
    }

    private void dataInit() {
        for (InviteFriendData inviteFriendData : data) {
            if (canInvited(inviteFriendData.getLastInvitedDate())) {
                inviteFriendData.setCanInvited(true);
            } else {
                inviteFriendData.setCanInvited(false);
            }
            //等级大于等于4级后就不能再邀请
            if (inviteFriendData.getMemberGrade() >= 4) {
                inviteFriendData.setCanInvited(false);
            }
        }
    }

    public List<InviteFriendData> getData() {
        return data;
    }


    public void addItemClickListener(ClickInviteListener listener) {
        this.listener = listener;
    }


    @Override
    public ViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.item_invite_friend, parent, false);
        return new ViewHold(layout);
    }

    private String getMemberGradeName(int gender, int memberGrade) {
        if (gender == 0) {
            switch (memberGrade) {
                case 4:
                    return "阿哥";
                case 6:
                    return "贝勒";
                default:
                    return "";
            }
        } else {
            switch (memberGrade) {
                case 4:
                    return "格格";
                case 6:
                    return "郡主";
                default:
                    return "";
            }
        }
    }

    /**
     * 得到能邀请的好友数量
     */
    public int getCanInvitedCount() {
        int count = 0;
        for (InviteFriendData inviteFriendData : data) {
            if (inviteFriendData.isCanInvited()) {
                count++;
            }
        }
        return count;
    }

    /**
     * 设置全部已邀请
     */
    public void setAllAlreadyInvited() {
        for (InviteFriendData inviteFriendData : data) {
            inviteFriendData.setCanInvited(false);
        }
        notifyDataSetChanged();
    }

    //是否能邀请该好友
    private boolean canInvited(String lastInvitedDate) {
        if (lastInvitedDate == null) {
            return true;
        }
        Date nowData = new Date();
        long invitedDateTime = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA);
        try {
            invitedDateTime = format.parse(lastInvitedDate).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long interval = nowData.getTime() - invitedDateTime;//间隔时间
        if (interval > 24 * 60 * 60 * 1000) {//大于24小时
            return true;
        }
        return false;
    }

    @Override
    public void onBindViewHolder(ViewHold holder, int position) {
        holder.tv_btn.setTag(position);
        InviteFriendData inviteFriendData = data.get(position);
        ImageLoaderUtil.loadCircleImage(context, holder.icon, inviteFriendData.getIconPath());
        holder.user_name.setText(inviteFriendData.getNickName());
        holder.rank_text.setText(String.valueOf("好友等级： " +
                getMemberGradeName(inviteFriendData.getGender(), inviteFriendData.getMemberGrade())));
        holder.rank_text.setVisibility(View.GONE);//隐藏等级名
        if (inviteFriendData.isCanInvited()) {
            holder.tv_btn.setSelected(true);
            holder.tv_btn.setText("邀请");
        } else {
            holder.tv_btn.setSelected(false);
            holder.tv_btn.setText("已邀请");
        }
        //等级大于等于4级后就不能再邀请
        if (inviteFriendData.getMemberGrade() >= 4) {
            holder.tv_btn.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.clickInvite((Integer) v.getTag());
        }
    }

    class ViewHold extends RecyclerView.ViewHolder {
        private ImageView icon;
        private TextView user_name;
        private TextView rank_text;
        private TextView tv_btn;

        public ViewHold(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.item_invite_friend_icon);
            user_name = itemView.findViewById(R.id.item_invite_friend_user_name);
            rank_text = itemView.findViewById(R.id.item_invite_friend_much_people);
            tv_btn = itemView.findViewById(R.id.item_invite_friend_btn);
            tv_btn.setOnClickListener(InviteFriendAdapter.this);
        }
    }

    public interface ClickInviteListener {
        void clickInvite(int position);
    }
}
