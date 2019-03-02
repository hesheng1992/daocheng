package com.a1magway.bgg.p.personal;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.a1magway.bgg.R;
import com.a1magway.bgg.util.AndroidUtil;
import com.a1magway.bgg.v.login.LoginActivity;
import java.util.List;

/** author: Beaven date: 2017/10/12 15:03 */
public class PersonalFeatureAdapter
        extends RecyclerView.Adapter<PersonalFeatureAdapter.ViewHolder> {

    private List<PersonalFeature> featureList;
    private OnClickListener onClickListener;

    public void setFeatureList(List<PersonalFeature> featureList) {
        this.featureList = featureList;
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_personal_feature, parent, false);
        int width = AndroidUtil.getScreenWidth(parent.getContext());
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, width / 4);
        view.setLayoutParams(params);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final PersonalFeature feature = featureList.get(position);
        holder.textView.setText(feature.getText());
        holder.imageView.setBackgroundResource(feature.getDrawableId());

        int backgroundId;
        if (feature.getId() == PersonalFeature.CARD_CENTER_TAG) {
            if (feature.isEnable() && !feature.isMember()) {
                backgroundId = R.drawable.personal_feature_item_gold_bg;
            } else {
                backgroundId = R.drawable.base_press_white_bg;
            }
        } else {
            backgroundId = R.drawable.base_press_white_bg;
        }
        holder.layout.setBackgroundResource(backgroundId);
        holder.layout.setAlpha(feature.isEnable() ? 1.0f : 0.6f);
        holder.layout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (feature.isEnable()) {
                            if (onClickListener != null) {
                                onClickListener.onClick(feature.getId());
                            }
                        } else {
                            LoginActivity.start(holder.imageView.getContext());
                        }
                    }
                });
    }

    @Override
    public int getItemCount() {
        return featureList == null ? 0 : featureList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        LinearLayout layout;

        ViewHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout_personal_feature);
            imageView = itemView.findViewById(R.id.image_personal_feature);
            textView = itemView.findViewById(R.id.text_personal_feature);
        }
    }

    public interface OnClickListener {
        void onClick(int id);
    }
}
