package com.shelectric.bruce.testdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Paint paint;
    private Canvas canvas;
    private Bitmap bmCopy;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //加载图片
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a);

        //创建背景图的副本,画纸
        bmCopy = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());

        //创建画笔
        paint = new Paint();

        //创建画板
        //画板上布上画纸
        canvas = new Canvas(bmCopy);

        //绘制
        canvas.drawBitmap(bitmap, new Matrix(), paint);

        imageView = (ImageView) findViewById(R.id.image);

        imageView.setImageBitmap(bmCopy);

        imageView.setOnTouchListener(new View.OnTouchListener() {

            private float startY;
            private float startX;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("手指按下了");
                        startX =  motionEvent.getX();
                        startY =  motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        System.out.println("手指滑动");

                        float x =  motionEvent.getX();
                        float y =  motionEvent.getY();
                        System.out.println(x + "***" + y);
                        canvas.drawLine(startX, startY, x, y, paint);
                        //本次绘制结束之后的结束坐标，是下一次绘制的初始坐标
                        startX = x;
                        startY = y;
                        imageView.setImageBitmap(bmCopy);
                        break;
                    case MotionEvent.ACTION_UP:
                        System.out.println("手指离开屏幕");
                        //本次绘制结束之后的结束坐标，是下一次绘制的初始坐标
                        break;

                }

                return true;//true:告诉系统，这个触摸时间由我处理；false的话，触摸事件我不处理,系统会把这个返回到ImageView的父节点

            }
        });

    }

    public void red(View v) {
        paint.setColor(Color.RED);
    }

    public void green(View v) {
        paint.setColor(Color.GREEN);
    }

    public void brush(View v) {
        paint.setStrokeWidth(18);
    }
}
