package edu.fje.multimedia;

import android.graphics.Color;
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

        List<Bloc> blocs = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            // Crear un bloque con un ID y un color
            Bloc bloc = new Bloc(i, obtenirColorPerID(i));
            // Agregar el bloque a la lista
            blocs.add(bloc);
        }

        gridView.setAdapter(new GridAdapter(this, blocs));
    }

    private int obtenirColorPerID(int id) {
        switch (id) {
            case 1:
                return Color.RED;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.BLUE;
            case 4:
                return Color.YELLOW;
            case 5:
                return Color.CYAN;
            case 6:
                return Color.MAGENTA;
            case 7:
                return Color.GRAY;
            case 8:
                return Color.LTGRAY;
            case 9:
                return Color.DKGRAY;
            default:
                return Color.WHITE;
        }
    }
}
