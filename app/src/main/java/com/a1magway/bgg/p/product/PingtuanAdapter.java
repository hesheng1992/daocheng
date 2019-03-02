package com.a1magway.bgg.p.product;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.data.entity.LoginData;
import com.a1magway.bgg.data.entity.MorePingtuanData;
import com.a1magway.bgg.okhttp.LogUtil;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.ImageLoaderUtil;
import com.a1magway.bgg.util.Toaster;
import com.a1magway.bgg.widget.PingtuanCountDownTextView;
import com.almagway.common.utils.ToastUtil;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 拼团适配器
 * Created by enid on 2018/8/20.
 */

public class PingtuanAdapter extends RecyclerView.Adapter<PingtuanAdapter.ViewHold>
        implements View.OnClickListener {
    private Context context;
    private List<MorePingtuanData.CollageOrderBean> data;
    private Set<PingtuanCountDownTextView> set = new HashSet<>();
    private PingtuanBuyClickListener listener;

    public PingtuanAdapter(Context context, List<MorePingtuanData.CollageOrderBean> data) {
        this.context = context;
        this.data = data;
        this.set.clear();
    }

    public void addPingtuanBuyListener(PingtuanBuyClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.item_pingtuan, parent, false);
        return new ViewHold(layout);
    }

    /**
     * 销毁倒计时，防止内存泄露
     */
    public void destroyCountDownView() {
        for (PingtuanCountDownTextView textView : set) {
            if (textView != null) {
                textView.stopTickWork();
            }
        }
    }

    @Override
    public void onBindViewHolder(ViewHold holder, int position) {
        holder.goBuy.setTag(position);
        MorePingtuanData.CollageOrderBean collageOrderBean = data.get(position);
        ImageLoaderUtil.loadCircleImage(context, holder.item_crowdordering_icon, collageOrderBean.getIcon());
        holder.userName.setText(collageOrderBean.getNickName());
        StringBuilder builder = new StringBuilder();
        builder.append("还差")
                .append(collageOrderBean.getCount())
                .append("人，");
        holder.muchPeople.setText(String.valueOf(builder));
        LoginData loginData = GlobalData.getInstance().getLoginData();
        if (loginData != null && collageOrderBean.getUserId() == loginData.getId()) {
            holder.goBuy.setSelected(false);
        }else {
            holder.goBuy.setSelected(true);
        }
        Date date = new Date();
        LogUtil.e("ddddddddss",date.getTime()+" "+collageOrderBean.getEndTime());
        holder.downTextView.stopTickWork();
        holder.downTextView.startTickWork(collageOrderBean.getEndTime() - date.getTime(),
                PingtuanCountDownTextView.PING_TUAN_LIST);
        set.add(holder.downTextView);
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            if (v.isSelected()) {
                listener.goPingtuan(data.get((Integer) v.getTag()));
            }else {
                Toaster.showShort(context,"不能参加自己的拼团");
            }
        }
    }

    class ViewHold extends RecyclerView.ViewHolder {
        ImageView item_crowdordering_icon;
        TextView userName;
        TextView muchPeople;
        PingtuanCountDownTextView downTextView;
        TextView goBuy;

        ViewHold(View itemView) {
            super(itemView);
            item_crowdordering_icon = itemView.findViewById(R.id.item_crowdordering_icon);
            userName = itemView.findViewById(R.id.item_crowdordering_user_name);
            muchPeople = itemView.findViewById(R.id.item_crowdordering_much_people);
            downTextView = itemView.findViewById(R.id.item_crowdordering_down_time);
            goBuy = itemView.findViewById(R.id.item_crowdordering_go_buy);
            goBuy.setOnClickListener(PingtuanAdapter.this);
        }
    }

    public interface PingtuanBuyClickListener {
        void goPingtuan(MorePingtuanData.CollageOrderBean collageOrderBean);
    }
}
