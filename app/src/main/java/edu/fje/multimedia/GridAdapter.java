package edu.fje.multimedia;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

       /* if (convertView == null) {
            // Si la vista no existe, crea una nueva ImageView
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            // Si la vista ya existe, reutiliza la vista
            imageView = (ImageView) convertView;
        }*/

        if (convertView == null) {
            // Si la vista no existe, crea un nuevo TextView
            textView = new TextView(mContext);
            textView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            textView.setGravity(android.view.Gravity.CENTER);
            textView.setTextSize(20); // Tamaño del texto
        } else {
            // Si la vista ya existe, reutiliza la vista
            textView = (TextView) convertView;
        }

        // Aquí puedes establecer la imagen para cada elemento de la cuadrícula
        // Puedes usar un array de recursos de imágenes, por ejemplo

        // imageView.setImageResource(mThumbIds[position]); // mThumbIds es un array de recursos de imágenes
        textView.setText(String.valueOf(position + 1));
        //return imageView;
        return textView;
    }
}

