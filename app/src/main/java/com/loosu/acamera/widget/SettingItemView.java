package com.loosu.acamera.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.loosu.acamera.R;

public class SettingItemView extends LinearLayout {

    private TextView mItemTextView;
    private TextView mItemSubTextView;
    private Switch mSwitch;

    private ItemCheckChangeLisenter mLisenter = null;

    private final CompoundButton.OnCheckedChangeListener mMyCheckChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            if (mLisenter != null) {
                mLisenter.onItemCheckedChanged(SettingItemView.this, isChecked);
            }
        }
    };

    public SettingItemView(Context context) {
        this(context, null);
    }

    public SettingItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SettingItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_setting_item, this, true);

        findView();
        iniView(context, attrs);
    }

    private void findView() {
        mItemTextView = (TextView) findViewById(R.id.setting_item_text);
        mItemSubTextView = (TextView) findViewById(R.id.setting_item_sub_text);
        mSwitch = (Switch) findViewById(R.id.setting_item_switch);
    }

    private void iniView(Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SettingItemView);

        String itemText = typedArray.getString(R.styleable.SettingItemView_itemText);
        int itemTextSize = typedArray.getInt(R.styleable.SettingItemView_itemTextSize, 16);
        ColorStateList itemColorList = typedArray.getColorStateList(R.styleable.SettingItemView_itemTextColor);

        String itemSubText = typedArray.getString(R.styleable.SettingItemView_itemSubText);
        int itemSubTextSize = typedArray.getInt(R.styleable.SettingItemView_itemSubTextSize, 14);
        ColorStateList itemSubTextColorList = typedArray.getColorStateList(R.styleable.SettingItemView_itemSubTextColor);

        boolean switchEnable = typedArray.getBoolean(R.styleable.SettingItemView_itemSwitchEnable, false);
        boolean switchChecked = typedArray.getBoolean(R.styleable.SettingItemView_itemSwitchChecked, false);

        typedArray.recycle();

        mItemTextView.setText(itemText);
        mItemTextView.setTextSize(itemTextSize);
        mItemTextView.setTextColor(itemColorList);

        mItemSubTextView.setText(itemSubText);
        mItemSubTextView.setTextSize(itemSubTextSize);
        mItemSubTextView.setTextColor(itemSubTextColorList);

        mSwitch.setVisibility(switchEnable ? VISIBLE : GONE);
        mSwitch.setChecked(switchChecked);
        mSwitch.setOnCheckedChangeListener(mMyCheckChangeListener);
    }

    public void setItemText(CharSequence text) {
        mItemTextView.setText(text);
    }

    public CharSequence getItemText() {
        return mItemTextView.getText();
    }

    public void setItemSubText(CharSequence text) {
        mItemSubTextView.setText(text);
    }

    public CharSequence getItemSubText() {
        return mItemSubTextView.getText();
    }

    public interface ItemCheckChangeLisenter {
        public void onItemCheckedChanged(View item, boolean isChecked);
    }
}
