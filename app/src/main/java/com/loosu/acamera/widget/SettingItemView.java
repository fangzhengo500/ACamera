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
        float itemTextSize = typedArray.getFloat(R.styleable.SettingItemView_itemTextSize, 16);
        ColorStateList itemColorList = typedArray.getColorStateList(R.styleable.SettingItemView_itemTextColor);

        String itemSubText = typedArray.getString(R.styleable.SettingItemView_itemSubText);
        float itemSubTextSize = typedArray.getFloat(R.styleable.SettingItemView_itemSubTextSize, 14);
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

        setSwitchEnable(switchEnable);
        mSwitch.setChecked(switchChecked);
        mSwitch.setOnCheckedChangeListener(mMyCheckChangeListener);
    }

    // itemText
    public CharSequence getItemText() {
        return mItemTextView.getText();
    }

    public void setItemText(CharSequence text) {
        mItemTextView.setText(text);
    }

    public void getItemTextSize() {
        mItemTextView.getTextSize();
    }

    public void setItemTextSize(float size) {
        mItemTextView.setTextSize(size);
    }

    public ColorStateList getItemTextColor() {
        return mItemTextView.getTextColors();
    }

    public void setItemTextColor(int color) {
        mItemTextView.setTextColor(color);
    }

    public void setItemTextColorList(ColorStateList colors) {
        mItemTextView.setTextColor(colors);
    }

    // itemSubText
    public CharSequence getItemSubText() {
        return mItemSubTextView.getText();
    }

    public void setItemSubText(CharSequence text) {
        mItemSubTextView.setText(text);
    }

    public void getItemSubTextSize() {
        mItemSubTextView.getTextSize();
    }

    public void setItemSubTextSize(float size) {
        mItemSubTextView.setTextSize(size);
    }

    public void setItemSubTextColor(int color) {
        mItemSubTextView.setTextColor(color);
    }

    public ColorStateList getItemSubTextColor() {
        return mItemSubTextView.getTextColors();
    }

    public void setItemSubTextColorList(ColorStateList colors) {
        mItemSubTextView.setTextColor(colors);
    }

    public boolean isSwitchEnable() {
        return mSwitch.isEnabled();
    }

    public void setSwitchEnable(boolean enable) {
        mSwitch.setEnabled(enable);
        mSwitch.setVisibility(mSwitch.isEnabled() ? VISIBLE : GONE);
    }

    public boolean isSwitchChecked() {
        return isSwitchEnable() && mSwitch.isEnabled();
    }

    public void setSwitchChecked(boolean checked) {
        mSwitch.setChecked(checked);
    }

    public ItemCheckChangeLisenter getLisenter() {
        return mLisenter;
    }

    public void setLisenter(ItemCheckChangeLisenter lisenter) {
        mLisenter = lisenter;
    }

    public interface ItemCheckChangeLisenter {
        public void onItemCheckedChanged(View item, boolean isChecked);
    }
}
