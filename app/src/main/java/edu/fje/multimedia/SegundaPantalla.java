package edu.fje.multimedia;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SegundaPantalla extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;
    private ImageButton cameraButton;
    private ImageButton galleryButton;
    private RadioGroup radioGroup;
    private Button button;
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout2);
        coordinatorLayout = findViewById(R.id.coordinator);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cameraButton = findViewById(R.id.imageButton2);
        galleryButton = findViewById(R.id.imageButton3);

        radioGroup = findViewById(R.id.radioGroup);

        button = findViewById(R.id.button);

        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(button, "scaleX", 0f, 1f);
        scaleXAnimator.setDuration(1000);
        scaleXAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(button, "scaleY", 0f, 1f);
        scaleYAnimator.setDuration(1000);
        scaleYAnimator.setInterpolator(new AccelerateDecelerateInterpolator());

        scaleXAnimator.start();
        scaleYAnimator.start();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SegundaPantalla.this, PuzzleFacil.class);
                startActivity(intent);
            }
        });

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCamara();
            }
        });

        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGaleria();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButtonFacil = findViewById(R.id.radioButtonFacil);
                RadioButton radioButtonIntermedio = findViewById(R.id.radioButtonIntermedio);
                RadioButton radioButtonDificil = findViewById(R.id.radioButtonDificil);

                if (checkedId == R.id.radioButtonFacil) {
                    radioButtonIntermedio.setChecked(false);
                    radioButtonDificil.setChecked(false);
                } else if (checkedId == R.id.radioButtonIntermedio) {
                    radioButtonFacil.setChecked(false);
                    radioButtonDificil.setChecked(false);
                } else if (checkedId == R.id.radioButtonDificil) {
                    radioButtonFacil.setChecked(false);
                    radioButtonIntermedio.setChecked(false);
                }

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
        if (item.getItemId() == R.id.ajustos) {
            abrirAjustos();
            return true;
        }
        return false;
    }

    private void abrirCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void abrirGaleria() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    private void abrirAjustos() {
        Snackbar.make(coordinatorLayout, "AJUSTOS", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                String imagePath = saveImageToTempFile(bitmap);
                Intent intent = new Intent(SegundaPantalla.this, PuzzleFacil.class);
                intent.putExtra("image_path", imagePath);
                startActivity(intent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            String imagePath = saveImageToTempFile(bitmap);
            Intent intent = new Intent(SegundaPantalla.this, PuzzleFacil.class);
            intent.putExtra("image_path", imagePath);
            startActivity(intent);
        }
    }


    private String saveImageToTempFile(Bitmap bitmap) {
        ContextWrapper wrapper = new ContextWrapper(getApplicationContext());
        File directory = wrapper.getDir("images", Context.MODE_PRIVATE);
        File file = new File(directory, "temp_image.jpg");

        try {
            OutputStream stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            stream.flush();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file.getAbsolutePath();
    }

}


