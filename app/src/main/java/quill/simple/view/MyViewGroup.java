package quill.simple.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import quill.simple.R;

/**
 * Created by Quill on 2016/3/27.
 */
public class MyViewGroup extends ViewGroup{
    private static String TAG = "MyViewGroup" ;
    private Context mContext ;

    public MyViewGroup(Context context) {
        super(context);
        mContext = context ;
        init() ;
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context ;
        init() ;
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context ;
        init();
    }

    private void init() {
        //调用ViewGroup父类addView()方法添加子View

        //child 对象一 ： Button
        Button btn= new Button(mContext) ;
        btn.setText("I am Button") ;
        this.addView(btn) ;

        //child 对象二 : ImageView
        ImageView img = new ImageView(mContext) ;
        img.setBackgroundResource(R.mipmap.camera) ;
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        layoutParams.width=200;
        img.setLayoutParams(layoutParams);
        this.addView(img) ;

        //child 对象三 : TextView
        TextView txt = new TextView(mContext) ;
        txt.setText("Only Text") ;
        this.addView(txt) ;

        //child 对象四 ： 自定义View
        MyView myView = new MyView(mContext) ;
        this.addView(myView) ;
    }

    @Override
    //对每个子View进行measure():设置每子View的大小，即实际宽和高
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //设置本ViewGroup的宽高,不按EXACTLY,AT_MOST模式来
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),MeasureSpec.getSize(heightMeasureSpec));
//        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(),widthMeasureSpec)
//        ,getDefaultSize(getSuggestedMinimumHeight(),heightMeasureSpec));
        Log.i(TAG, "**** onMeasure start ****") ;
        int childCount = getChildCount();
        for (int i = 0; i <childCount ; i++) {
            View child = getChildAt(i);
            getChildAt(i).measure(50,50);//简单的设置每个子View对象的宽高为 50px , 50px
            Log.i(TAG,"getWith="+child.getWidth()+" getMeasureWidth="+child.getMeasuredWidth());
            //或者可以调用ViewGroup父类方法measureChild()或者measureChildWithMargins()方法
            //this.measureChild(child, widthMeasureSpec, heightMeasureSpec) ;
        }
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //通过init()方法，我们为该ViewGroup对象添加了三个视图 ， Button、 ImageView、TextView
        int childCount = getChildCount() ;

        int startLeft = 0 ;//设置每个子View的起始横坐标
        int startTop = 10 ; //每个子View距离父视图的位置 ， 简单设置为10px吧 。 可以理解为 android:margin=10px ;

        Log.i(TAG, "**** onLayout start ****") ;

        for(int i=0 ;i<childCount ; i++){
            View child = getChildAt(i) ;   //获得每个对象的引用
            child.layout(startLeft, startTop, startLeft+100, startTop+child.getMeasuredHeight()) ;

            startLeft =startLeft+child.getWidth() + 10;  //校准startLeft值，View之间的间距设为10px ;

            Log.i(TAG, "**** onLayout startLeft ****" +startLeft);
            Log.i(TAG,"getWith="+child.getWidth()+" getMeasureWidth="+child.getMeasuredWidth());
        }
    }

    //绘图过程Android已经为我们封装好了 ,这儿只为了观察方法调用程
    protected void dispatchDraw(Canvas canvas){
        Log.i(TAG, "**** dispatchDraw start ****") ;

        super.dispatchDraw(canvas) ;
    }

    protected boolean drawChild(Canvas canvas , View child, long drawingTime){
        Log.i(TAG, "**** drawChild start ****") ;

        return super.drawChild(canvas, child, drawingTime) ;
    }
}
