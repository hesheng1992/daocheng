package com.a1magway.bgg.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.a1magway.bgg.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 带有清除框的editText
 * Created by lyx on 2017/8/4.
 */
public class ClearEditText extends FrameLayout {

    @BindView(R.id.input_edt)
    EditText editText;

    @BindView(R.id.input_clear_iv)
    ImageView imageView;

    MyTextWatcher mMyTextWatcher;

    public ClearEditText(Context context) {
        super(context);
        init(null);
    }

    public void addTextChangedListener(MyTextWatcher myTextWatcher) {
        this.mMyTextWatcher = myTextWatcher;

    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }


    /**
     * 清除按钮的显隐
     * @param editText
     * @param imageView
     */
    public void showClearIv(EditText editText, ImageView imageView) {
        if (editText.getEditableText().length() > 0 && editText.hasFocus()) {
            if (imageView.getVisibility() == View.GONE) {
            }
            imageView.setVisibility(View.VISIBLE);
        } else {
            if (imageView.getVisibility() == View.VISIBLE) {
            }
            imageView.setVisibility(View.GONE);
        }
    }

    /**
     * 提供给外部的监听事件
     */
    public interface MyTextWatcher {
        void beforeTextChanged(CharSequence s, int start, int count, int after);

        void onTextChanged(CharSequence s, int start, int before, int count);

        void afterTextChanged(Editable s);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.clear_edittext, this, true);
        ButterKnife.bind(this);

        if (attrs != null) {
            TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.ClearEditText);
            editText.setHint(array.getString(R.styleable.ClearEditText_hint));
            InputFilter[] inputFilter = new InputFilter[0];
            if (array.getInt(R.styleable.ClearEditText_maxLength, 0) != 0 && array.getString(R.styleable.ClearEditText_digits) != null ){
                inputFilter = new InputFilter[]{new InputFilter.LengthFilter(array.getInt(R.styleable.ClearEditText_maxLength, 0))
                        ,DigitsKeyListener.getInstance(array.getString(R.styleable.ClearEditText_digits))};
            }else {
                if(array.getInt(R.styleable.ClearEditText_maxLength, 0) != 0 ){
                    inputFilter = new InputFilter[]{new InputFilter.LengthFilter(array.getInt(R.styleable.ClearEditText_maxLength, 0))};
                }

                if (array.getString(R.styleable.ClearEditText_digits) != null && array.getString(R.styleable.ClearEditText_digits).length() >0){
                    inputFilter = new InputFilter[]{DigitsKeyListener.getInstance(array.getString(R.styleable.ClearEditText_digits))};
                }
            }
            editText.setFilters(inputFilter);
            if(array.getInt(R.styleable.ClearEditText_inputType, 0) == 128){
                editText.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }else {
                editText.setInputType(array.getInt(R.styleable.ClearEditText_inputType, 0));
            }
            array.recycle();
        }

        editText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showClearIv(editText, imageView);
                } else {
                    imageView.setVisibility(View.GONE);
                }
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (mMyTextWatcher != null) {
                    mMyTextWatcher.beforeTextChanged(s, start, count, after);
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mMyTextWatcher != null) {
                    mMyTextWatcher.onTextChanged(s, start, before, count);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mMyTextWatcher != null) {
                    mMyTextWatcher.afterTextChanged(s);
                }
                if (s.length() > 0 && editText.hasFocus()) {
                    if (imageView.getVisibility() == View.GONE) {
                    }
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    if (imageView.getVisibility() == View.VISIBLE) {
                    }
                    imageView.setVisibility(View.GONE);
                }
            }
        });
    }

    @OnClick(R.id.input_clear_iv)
    void onClearClick(){
        editText.setText("");
    }

    public Editable getEditable(){
        return editText.getEditableText();
    }

    public void setTransformationMethod(TransformationMethod method){
        editText.setTransformationMethod(method);
    }

    public void setSelection(int index){
        editText.setSelection(index);
    }

    public void setText(int id){
        editText.setText(getResources().getString(id));
    }

    public void setText(String str){
        editText.setText(str);
    }

}
