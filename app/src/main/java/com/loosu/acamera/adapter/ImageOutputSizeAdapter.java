package com.loosu.acamera.adapter;

import android.content.Context;
import android.text.format.Formatter;
import android.util.Size;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;

import com.loosu.acamera.R;
import com.loosu.acamera.adapter.base.recyclerview.ARecyclerAdapter;
import com.loosu.acamera.adapter.base.recyclerview.RecyclerHolder;
import com.loosu.acamera.utils.MathUtil;

import java.util.List;
import java.util.Locale;

public class ImageOutputSizeAdapter extends ARecyclerAdapter<Size> {

    private int mPosition;

    public ImageOutputSizeAdapter() {
        super(null);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.item_image_output_size;
    }

    @Override
    protected void onBindViewData(RecyclerHolder holder, int position, List<Size> datas) {
        Context context = holder.getItemView().getContext();

        Size size = getItem(position);
        int width = size.getWidth();
        int height = size.getHeight();
        long gcd = MathUtil.gcd(width, height);

        int fileSize = height * width;
        String fileSizeStr = Formatter.formatFileSize(context, fileSize);

        holder.setText(R.id.tv_text, String.format(Locale.US, "%d:%d (%s)", width / gcd, height / gcd, fileSizeStr));
        holder.setText(R.id.tv_size, width + " * " + height);

        CompoundButton radioBtn = holder.getView(R.id.radio_btn);
        radioBtn.setChecked(mPosition == position);
    }

    @Override
    public Size getItem(int position) {
        return mDatas.get(position);
    }

    public void setDatas(List<Size> datas) {
        super.setDatas(datas);
        notifyDataSetChanged();
    }

    public void setItemSelected(int position) {
        notifyItemChanged(mPosition, null);
        mPosition = position;
        notifyItemChanged(mPosition);
    }
}
