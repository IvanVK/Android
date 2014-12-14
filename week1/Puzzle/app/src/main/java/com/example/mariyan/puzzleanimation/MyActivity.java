package com.example.mariyan.puzzleanimation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MyActivity extends Activity {

    private GridLayout grid;
    private List<Drawable> imageList;
    private List<ImageView> orderedImages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        imageList = fromTypedArrayToList();
        orderedImages = new ArrayList<ImageView>();
        grid = (GridLayout) findViewById(R.id.grid);
        int puzzleSize = (int) Math.sqrt(imageList.size());
        grid.setColumnCount(puzzleSize);
        grid.setRowCount(puzzleSize);
        final List<Drawable> randomImages = new ArrayList<Drawable>(imageList);
        Collections.shuffle(randomImages);
        for (int i = 0; i < puzzleSize; i++) {
            for (int j = 0; j < puzzleSize; j++) {
                final ImageView image = new ImageView(this);
                image.setAdjustViewBounds(true);
                image.setPadding(5, 5, 5, 5);
                image.setLayoutParams(gridParams(120, 80));
                image.setScaleType(ImageView.ScaleType.FIT_XY);
                image.setImageDrawable(randomImages.get(i * puzzleSize + j));
                orderedImages.add(image);
                Point point = new Point(j, i);
                image.setTag(point);
                image.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(image);
                        image.startDrag(null, shadowBuilder, image, 0);
                        return true;
                    }
                });
                image.setOnDragListener(new View.OnDragListener() {
                    @Override
                    public boolean onDrag(View view, DragEvent dragEvent) {
                        if (dragEvent.getAction() == dragEvent.ACTION_DROP) {
                            ImageView dragImage = (ImageView) dragEvent.getLocalState();
                            Point pointImage = (Point) image.getTag();
                            int coordinateXImage = pointImage.x;
                            int coordinateYImage = pointImage.y;
                            Point pointDrag = (Point) dragImage.getTag();
                            int coordinateXDrag = pointDrag.x;
                            int coordinateYDrag = pointDrag.y;
                            image.setTag(pointDrag);
                            dragImage.setTag(pointImage);
                            Animator animatorImageX = ObjectAnimator.ofFloat(dragImage, "x", coordinateXImage*image.getMeasuredWidth());
                            Animator animatorImageY = ObjectAnimator.ofFloat(dragImage, "y", coordinateYImage*image.getMeasuredHeight());
                            Animator animatorDragX = ObjectAnimator.ofFloat(image, "x", coordinateXDrag * image.getMeasuredWidth());
                            Animator animatorDragY = ObjectAnimator.ofFloat(image, "y", coordinateYDrag*image.getMeasuredHeight());
                            AnimatorSet animatorSet = new AnimatorSet();
                            animatorSet.playTogether(animatorImageX, animatorImageY, animatorDragX, animatorDragY);
                            animatorSet.start();
                        }
                        return true;
                    }
                });
                grid.addView(image);
            }
        }
    }

    private List<Drawable> fromTypedArrayToList() {
        TypedArray typedArray = getResources().obtainTypedArray(R.array.images);
        List<Drawable> images = new ArrayList<Drawable>();
        for (int i = 0; i < typedArray.length(); i++) {
            images.add(typedArray.getDrawable(i));
        }
        return images;
    }

    private GridLayout.LayoutParams gridParams(int width, int height) {
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width=width;
        params.height=height;
        return params;

    }
}
