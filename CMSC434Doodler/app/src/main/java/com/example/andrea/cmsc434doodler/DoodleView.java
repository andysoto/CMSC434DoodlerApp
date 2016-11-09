package com.example.andrea.cmsc434doodler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Andrea on 11/2/2016.
 */

public class DoodleView extends View {

    ArrayList<MyPaint> drawing = new ArrayList<MyPaint>();
    int brushSize, brushColor, brushAlpha;
    //private Paint _paintDoodle = new Paint();
    //private Path _path = new Path();

    public DoodleView(Context context) {
        super(context);
        init(null, 0);
    }

    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public DoodleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        Paint _paintDoodle = new Paint();
        Path _path = new Path();
        MyPaint myPaint = new MyPaint(_path,_paintDoodle);
        myPaint.setMyPaintProperties(12, Color.MAGENTA, 255);
        brushSize = 12;
        brushColor = Color.MAGENTA;
        brushAlpha = 255;
        drawing.add(myPaint);

        /*_paintDoodle.setColor(Color.MAGENTA);
        _paintDoodle.setAntiAlias(true);
        _paintDoodle.setStyle(Paint.Style.STROKE);
        _paintDoodle.setStrokeWidth(12);
        _paintDoodle.setAlpha(255);
        _paintDoodle.setStrokeJoin(Paint.Join.ROUND);
        _paintDoodle.setStrokeCap(Paint.Cap.ROUND);*/

    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawLine(0, 0, getWidth(), getHeight(),_paintDoodle);
        //canvas.drawPath(_path, _paintDoodle);
        for (MyPaint mypaint : drawing) {
            canvas.drawPath(mypaint.getPath(), mypaint.getPaint());
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float touchX = motionEvent.getX();
        float touchY = motionEvent.getY();

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                MyPaint newPaint = new MyPaint(new Path(), new Paint());
                newPaint.setMyPaintProperties(brushColor, brushSize, brushAlpha);
                //Paint lastPaint = drawing.get(drawing.size()-1).getPaint();
                //newPaint.setMyPaintProperties(lastPaint.getColor(),(int)lastPaint.getStrokeWidth(),lastPaint.getAlpha());
                newPaint.getPath().moveTo(touchX,touchY);
                drawing.add(newPaint);
                //_path.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                //_path.lineTo(touchX, touchY);
                drawing.get(drawing.size()-1).getPath().lineTo(touchX,touchY);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return true;
    }


    public void changeBrushSize(int size) {
        //_paintDoodle.setStrokeWidth(size);
        brushSize = size;
        //invalidate();
    }

    public void changeBrushAlpha(int alpha) {
        //_paintDoodle.setAlpha(alpha);
        brushAlpha = alpha;
    }

    public void changeBrushColor(int color) {
        brushColor = color;
    }
    public void clear() {
        drawing.clear();
        invalidate();
    }

    public void undo() {
        drawing.remove(drawing.size()-1);
        invalidate();
    }

    private class MyPaint {
        private Path path;
        private Paint paint;
        int color, size, alpha;

        public MyPaint(Path path0, Paint paint0) {
            path = path0;
            paint = paint0;
        }
        public void setMyPaintProperties (int color0, int size0, int alpha0) {
            paint.setColor(color0);
            paint.setStrokeWidth(size0);
            paint.setAlpha(alpha0);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);
        }
        public Paint getPaint() {
            return paint;
        }
        public Path getPath() {
            return path;
        }

    }
}
