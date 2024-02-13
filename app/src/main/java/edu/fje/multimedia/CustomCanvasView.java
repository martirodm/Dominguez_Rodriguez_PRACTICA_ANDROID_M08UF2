package edu.fje.multimedia;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class CustomCanvasView extends View {
    private Bitmap imageBitmap;

    public CustomCanvasView(Context context) {
        super(context);
        init();
    }

    public CustomCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomCanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // Cargar la imagen desde los recursos
        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.belligol);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Dibujar la imagen en el lienzo
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        // Definir el rectángulo donde se dibujará la imagen
        Rect destRect = new Rect(0, 0, width, height);
        // Dibujar la imagen en el lienzo
        canvas.drawBitmap(imageBitmap, null, destRect, null);
    }
}

