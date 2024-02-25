package edu.fje.multimedia;

import android.graphics.Bitmap;

public class Bloc {

    private int id;
    private Bitmap imageBlock;

    public Bloc(int id, Bitmap imageBlock) {
        this.id = id;
        this.imageBlock = imageBlock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getImageBlock() {
        return imageBlock;
    }

    public void setImageBlock(Bitmap imageBlock) {
        this.imageBlock = imageBlock;
    }
}
