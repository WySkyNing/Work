package com.ning.fastwork.titlebar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ning.fastwork.R;


/**
 * wy
 */

public class CustomTitleBar extends RelativeLayout {

    private TextView leftTextView;
    private String leftBtnText = "";
    private ImageView leftImageView;
    private int leftBtnSize;
    private int leftBtnColor;
    private Drawable leftDrawable;
    private Drawable rightDrawable;


    private TextView titleTextView;
    private String titleText;
    private int titleTextSize;
    private int titleTextColor;

    private TextView rightTextView;
    private String rightText;
    private int rightTextSize;
    private int rightTextColor;


    public CustomTitleBar(Context context) {
        super(context);

    }

    public CustomTitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initData(context, attrs, 0);

    }

    public CustomTitleBar(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initData(context, attrs, defStyle);

    }

    private void initData(Context context, AttributeSet attrs, int defStyle) {

        /**
         * AttributeSet
         * */

        TypedArray typedArray
                = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTitleBar, defStyle, 0);

        leftBtnText = typedArray.getString(R.styleable.CustomTitleBar_leftBtnText);
        leftBtnColor = typedArray.getColor(R.styleable.CustomTitleBar_leftBtnTextColor, Color.BLACK);
        leftBtnSize = typedArray.getDimensionPixelSize(R.styleable.CustomTitleBar_leftBtnTextSize,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
        leftDrawable = typedArray.getDrawable(R.styleable.CustomTitleBar_leftBtnImage);

        titleText = typedArray.getString(R.styleable.CustomTitleBar_titleText);
        titleTextColor = typedArray.getColor(R.styleable.CustomTitleBar_titleColor, Color.BLACK);
        titleTextSize = typedArray.getDimensionPixelSize(R.styleable.CustomTitleBar_titleSize,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));

        rightText = typedArray.getString(R.styleable.CustomTitleBar_rightBtnText);
        rightTextColor = typedArray.getColor(R.styleable.CustomTitleBar_rightBtnTextColor, Color.BLACK);
        rightTextSize = typedArray.getDimensionPixelSize(R.styleable.CustomTitleBar_rightBtnTextSize,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
        rightDrawable = typedArray.getDrawable(R.styleable.CustomTitleBar_rightBtnImage);



        typedArray.recycle();

        initView(context);
    }


    private void initView(Context context) {

        if (!TextUtils.isEmpty(leftBtnText) || leftDrawable != null) {
            //左边的文字
            leftTextView = new TextView(context);
            leftTextView.setText(leftBtnText);
            leftTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftBtnSize);
            leftTextView.setTextColor(leftBtnColor);
            leftTextView.setGravity(Gravity.CENTER);

            //左边的图片
            leftDrawable.setBounds(0, 0, leftDrawable.getMinimumWidth(), leftDrawable.getMinimumHeight());
            leftTextView.setCompoundDrawables(leftDrawable, null, null, null);

            LayoutParams leftTextLp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            leftTextLp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            leftTextLp.addRule(RelativeLayout.CENTER_VERTICAL);
//
            //使用工具类里面的静态方法布局文件一直提示类没有找到异常，烦的要死
            int padding = dip2px(context, 15);
            leftTextView.setPadding(padding, padding, padding, padding);

            addView(leftTextView, leftTextLp);

        }



        //中间
        if (!TextUtils.isEmpty(titleText)) {

            titleTextView = new TextView(context);
            titleTextView.setText(titleText);
            titleTextView.setTextColor(titleTextColor);
            titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);
            titleTextView.setGravity(Gravity.CENTER);

            LayoutParams titleTextLp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            titleTextLp.addRule(RelativeLayout.CENTER_IN_PARENT);

            addView(titleTextView, titleTextLp);

        }


        //右边
        if (!TextUtils.isEmpty(rightText) || rightDrawable != null) {

            rightTextView = new TextView(context);
            rightTextView.setText(rightText);
            rightTextView.setTextColor(rightTextColor);
            rightTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightTextSize);
            rightTextView.setGravity(Gravity.CENTER);


            if (rightDrawable != null){

                //又边的图片
                rightDrawable.setBounds(0, 0, 70, 50);
                rightTextView.setCompoundDrawables(rightDrawable, null, null, null);
            }

            LayoutParams rightTextLp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            rightTextLp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            rightTextLp.addRule(RelativeLayout.CENTER_VERTICAL);

            int padding = dip2px(context, 15);
            rightTextView.setPadding(padding, padding, padding, padding);

            addView(rightTextView, rightTextLp);

        }



    }

    public void setLeftListener(final ILeftBtnListener leftListener) {

        leftTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                leftListener.onLeftBtnClick();
            }
        });

    }

    public void setRightListener(final IRightBtnListener rightListener) {

        rightTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                rightListener.onRightBtnClick();
            }
        });
    }

    public interface ILeftBtnListener {

        void onLeftBtnClick();
    }

    public interface IRightBtnListener {

        void onRightBtnClick();
    }
    public void setTitle(String text){

        titleTextView.setText(text);
    }


    public void setRightView(View view){

        LayoutParams rightTextLp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rightTextLp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rightTextLp.addRule(RelativeLayout.CENTER_VERTICAL);

        addView(view,rightTextLp);
    }


    private int dip2px(Context context, float dpValue) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpValue, context.getResources().getDisplayMetrics());
        return (int) px;
    }



}
