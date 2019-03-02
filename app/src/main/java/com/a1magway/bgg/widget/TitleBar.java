package com.a1magway.bgg.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 公用的TitleBar
 * Created by jph on 2017/7/28.
 */
public class TitleBar extends RelativeLayout {

    @BindView(R.id.title_img_left)
    ImageView mLeftImg;
    @BindView(R.id.title_txt_title)
    TextView mTitleTxt;
    @BindView(R.id.title_more_right)
    ImageView imageMore;

    public TitleBar(Context context) {
        super(context);
        init(null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.common_titlebar_layout, this, true);

        ButterKnife.bind(this);

        if (attrs != null) {
            TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.TitleBar);
            mTitleTxt.setText(array.getString(R.styleable.TitleBar_titleText));
            Drawable leftIcon = array.getDrawable(R.styleable.TitleBar_titleLeftIcon);
            if (leftIcon != null) {
                mLeftImg.setImageDrawable(leftIcon);
            }
            boolean isShowMore = array.getBoolean(R.styleable.TitleBar_titleMoreShow, false);
            imageMore.setVisibility(isShowMore ? VISIBLE : GONE);
            Drawable moreIcon = array.getDrawable(R.styleable.TitleBar_titleMoreImage);
            if (moreIcon != null && isShowMore) {
                imageMore.setImageDrawable(moreIcon);
            }
            array.recycle();
        }

    }

    public void setTitleTxt(int id) {
        mTitleTxt.setText(getResources().getString(id));
    }

    public void setTitleTxt(String str) {
        mTitleTxt.setText(str);
    }

    public String getTitleText() {
        return mTitleTxt.getText().toString().trim();
    }

    public void setLeftImg(int id) {
        mLeftImg.setImageResource(id);
    }

    public void setLeftImgClickListener(View.OnClickListener onClickListener) {
        ButterKnife.findById(this, R.id.title_img_left).setOnClickListener(onClickListener);
    }

    public void setDefLeftImgClickListener(final BaseActivity activity) {
        mLeftImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackButtonClick();
            }
        });
    }

    public void setShowMore(@DrawableRes int drawableId) {
        imageMore.setVisibility(VISIBLE);
        imageMore.setImageDrawable(getResources().getDrawable(drawableId));
    }

    public void setMoreClickListener(OnClickListener clickListener) {
        imageMore.setOnClickListener(clickListener);
    }
}
