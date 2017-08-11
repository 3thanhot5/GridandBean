package com.example.root.gridandbean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by root on 8/1/17.
 */

public class GridAdapter extends BaseAdapter {

    Context context;
    int page1[] = {
            R.mipmap.ic_launcher,       R.mipmap.ic_launcher,   R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,       R.mipmap.ic_launcher,   R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,       R.mipmap.ic_launcher,   R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher_round, R.mipmap.ic_launcher,   R.mipmap.ic_launcher,
            R.mipmap.linux,             R.mipmap.saymyname,     R.mipmap.linux,
            R.mipmap.saymyname,         R.mipmap.linux,         R.mipmap.saymyname,
            R.mipmap.ic_launcher_round, R.mipmap.ic_launcher,   R.mipmap.ic_launcher,
    };


    public class ViewHolder {
        public ImageView imageView;
        public TextView textTitle;
    }

    private GridItems[] items;
    private LayoutInflater mInflater;

    public GridAdapter(Context context, GridItems[] locations) {

        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        items = locations;

    }

    public GridItems[] getItems() {
        return items;
    }

    public void setItems(GridItems[] items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        if (items != null) {
            return items.length;
        }
        return 0;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public Object getItem(int position) {
        if (items != null && position >= 0 && position < getCount()) {
            return items[position];
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        if (items != null && position >= 0 && position < getCount()) {
            return items[position].id;
        }
        return 0;
    }

    public void setItemsList(GridItems[] locations) {
        this.items = locations;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        ViewHolder viewHolder;
        if (view == null) {

            view = mInflater.inflate(R.layout.custom, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) view
                    .findViewById(R.id.grid_item_image);
            viewHolder.textTitle = (TextView) view
                    .findViewById(R.id.grid_item_label);
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        GridItems gridItems = items[position];
        //Set Img according ItemId.
        int pos= (int)(getItemId(position));
        setCatImage(pos, viewHolder, gridItems.title);
        return view;
    }

    private void setCatImage(int pos, ViewHolder viewHolder, String catTitle) {
        viewHolder.imageView.setImageResource(page1[pos]);
        viewHolder.textTitle.setText(catTitle);
    }
}