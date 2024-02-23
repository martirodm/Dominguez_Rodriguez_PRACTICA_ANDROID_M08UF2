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
        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.belligol);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        Rect destRect = new Rect(0, 0, width, height);

        canvas.drawBitmap(imageBitmap, null, destRect, null);
    }
}

