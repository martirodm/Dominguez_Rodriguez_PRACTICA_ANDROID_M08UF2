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

public class GridAdapter extends BaseAdapter {
    private Context mContext;

    // Constructor
    public GridAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return 9; // Número de elementos en la cuadrícula (3x3)
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //ImageView imageView;
        TextView textView;

        if (convertView == null) {
            // Si la vista no existe, crea un nuevo TextView
            textView = new TextView(mContext);
            textView.setLayoutParams(new GridView.LayoutParams(200,200));
            textView.setGravity(android.view.Gravity.CENTER);
            textView.setTextSize(20); // Tamaño del texto
            textView.setPadding(0, 0, 0, 0);
            textView.setIncludeFontPadding(false);

            int[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN, Color.MAGENTA, Color.GRAY, Color.LTGRAY, Color.DKGRAY};
            textView.setBackgroundColor(colors[position % colors.length]);
        } else {
            // Si la vista ya existe, reutiliza la vista
            textView = (TextView) convertView;
        }

        textView.setText(String.valueOf(position + 1));

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Se ejecuta cuando se hace clic en un bloque
                Log.d("GridAdapter", "Bloque clicado: " + (position + 1));
            }
        });

        //return imageView;
        return textView;
    }
}

