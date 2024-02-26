package edu.fje.multimedia;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;
    private CustomCanvasView customCanvasView;
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        coordinatorLayout = findViewById(R.id.coordinator);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        customCanvasView = findViewById(R.id.customCanvas);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SegundaPantalla.class);
                startActivity(intent);
            }
        });

        mediaPlayer = MediaPlayer.create(this, R.raw.m02_audio1);

        Button audioButton = findViewById(R.id.audio);

        audioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isPlaying) {
                    mediaPlayer.start();
                    isPlaying = true;
                    audioButton.setText("Pausar Audio");
                } else {
                    mediaPlayer.pause();
                    isPlaying = false;
                    audioButton.setText("Reproducir Audio");
                }
            }
        });

        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("tiempo_juego");
        Query query = databaseRef.orderByChild("segundos_transcurridos").limitToFirst(5);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Float> tiempos = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    float tiempo = snapshot.getValue(Float.class);
                    tiempos.add(tiempo);
                }
                Collections.sort(tiempos);
                TextView tiempo1 = findViewById(R.id.tiempo1);
                TextView tiempo2 = findViewById(R.id.tiempo2);
                TextView tiempo3 = findViewById(R.id.tiempo3);
                TextView tiempo4 = findViewById(R.id.tiempo4);
                TextView tiempo5 = findViewById(R.id.tiempo5);

                if (tiempos.size() > 0) tiempo1.setText(String.valueOf(tiempos.get(0)));
                if (tiempos.size() > 1) tiempo2.setText(String.valueOf(tiempos.get(1)));
                if (tiempos.size() > 2) tiempo3.setText(String.valueOf(tiempos.get(2)));
                if (tiempos.size() > 3) tiempo4.setText(String.valueOf(tiempos.get(3)));
                if (tiempos.size() > 4) tiempo5.setText(String.valueOf(tiempos.get(4)));
            
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.ajustos){
            obrirAjustos();
            return true;
        }
        return false;
    }

    private void obrirAjustos() {
        Snackbar.make(coordinatorLayout, "AJUSTOS", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
