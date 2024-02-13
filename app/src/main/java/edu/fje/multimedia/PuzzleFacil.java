package edu.fje.multimedia;

import android.os.Bundle;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
public class PuzzleFacil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzlefacil);

        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(new GridAdapter(this));
    }
}
