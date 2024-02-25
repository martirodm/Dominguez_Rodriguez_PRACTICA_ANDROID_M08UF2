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

        List<Bitmap> bloques = dividirImagenEnBloques(bitmap);

        List<Bloc> blocs = new ArrayList<>();
        for (int i = 0; i < bloques.size(); i++) {
            Bloc bloc = new Bloc(i, bloques.get(i));
            blocs.add(bloc);
        }

        gridView.setAdapter(new GridAdapter(this, blocs));
    }

    private List<Bitmap> dividirImagenEnBloques(Bitmap bitmap) {
        List<Bitmap> bloques = new ArrayList<>();

        int blockSize = 100;

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        for (int y = 0; y < height; y += blockSize) {
            for (int x = 0; x < width; x += blockSize) {
                // Extraer el bloque de la imagen
                int blockWidth = Math.min(blockSize, width - x);
                int blockHeight = Math.min(blockSize, height - y);
                Bitmap bloque = Bitmap.createBitmap(bitmap, x, y, blockWidth, blockHeight);
                // Agregar el bloque a la lista
                bloques.add(bloque);
            }
        }

        return bloques;
    }
}
