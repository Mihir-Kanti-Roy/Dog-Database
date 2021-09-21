package com.infojunks.dogsearchapp;

import android.app.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.bumptech.glide.Glide;



import java.util.ArrayList;


public class Dog extends BaseAdapter implements ListAdapter {

    private final Activity context;
    private final ArrayList<String> imgid;

    public Dog(Activity applicationContext, ArrayList<String> jsonArray) {


        this.context= applicationContext;
        this.imgid=jsonArray;

    }

    @Override
    public int getCount() {
        return imgid.size();
    }

    @Override
    public Object getItem(int position) {
        return imgid.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        if (null == view) {
            view = inflater.inflate(R.layout.row_list, parent, false);
        }

        Glide.with(context).load(imgid.get(position)).into((ImageView) view);


        return view;

    };
}
