package edu.fje.multimedia;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class PuzzleFacil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzlefacil);

        GridView gridView = findViewById(R.id.gridView);

        // Obtener la imagen enviada desde SegundaPantalla
        String imagePath = getIntent().getStringExtra("image_path");
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);

        List<Bitmap> bloques = dividirImagenEnBloques(bitmap, 3, 3);

        List<Bloc> blocs = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Bloc bloc = new Bloc(i, bloques.get(i));
            blocs.add(bloc);
        }

        gridView.setAdapter(new GridAdapter(this, blocs));
    }

    private List<Bitmap> dividirImagenEnBloques(Bitmap bitmap, int rows, int cols) {
        List<Bitmap> bloques = new ArrayList<>();

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int blockWidth = width / cols;
        int blockHeight = height / rows;

        for (int y = 0; y < rows * blockHeight; y += blockHeight) {
            for (int x = 0; x < cols * blockWidth; x += blockWidth) {
                Bitmap bloque = Bitmap.createBitmap(bitmap, x, y, blockWidth, blockHeight);
                bloques.add(bloque);
            }
        }

        return bloques;
    }

}
