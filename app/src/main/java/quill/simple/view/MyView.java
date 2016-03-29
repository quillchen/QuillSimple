package quill.simple.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Quill on 2016/3/27.
 */
public class MyView extends View{

    private Paint paint  = new Paint() ;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(50,50);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    //存在canvas对象，即存在默认的显示区域
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.i("MyViewGroup", "MyView is onDraw ") ;
        //加粗
        paint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        paint.setColor(Color.RED);
        canvas.drawColor(Color.BLUE) ;
        canvas.drawRect(0, 0, 30, 30, paint);
        canvas.drawText("MyView", 10, 40, paint);

    }
}
