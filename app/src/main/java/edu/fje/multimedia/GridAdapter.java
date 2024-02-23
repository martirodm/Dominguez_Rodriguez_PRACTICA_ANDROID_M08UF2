package edu.fje.multimedia;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;
import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    private Context mContext;
    private List<Bloc> mBlocs;

    // Constructor
    public GridAdapter(Context context, List<Bloc> blocs) {
        mContext = context;
        mBlocs = blocs;
    }

    @Override
    public int getCount() {
        return mBlocs.size();
    }

    @Override
    public Object getItem(int position) {
        return mBlocs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;

        if (convertView == null) {
            textView = new TextView(mContext);
            textView.setLayoutParams(new GridView.LayoutParams(200,200));
            textView.setGravity(android.view.Gravity.CENTER);
            textView.setTextSize(20);
            textView.setPadding(0, 0, 0, 0);
            textView.setIncludeFontPadding(false);

        } else {
            textView = (TextView) convertView;
        }

        final Bloc bloc = mBlocs.get(position);

        textView.setBackgroundColor(bloc.getColor());
        textView.setText(String.valueOf(bloc.getId()));

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("GridAdapter", "Bloque clicado: " + bloc.getId());
            }
        });

        return textView;
    }
}

