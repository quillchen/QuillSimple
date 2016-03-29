package quill.simple.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by Quill on 2016/3/27.
 */
public class MultiViewGroup extends ViewGroup{
    private Context mContext;
    private static String TAG = "MultiViewGroup";
    private int curScreen = 0 ;  //当前屏
    private Scroller mScroller = null ;

    private static final int TOUCH_STATE_REST = 0;
    private static final int TOUCH_STATE_SCROLLING = 1;
    private int mTouchState = TOUCH_STATE_REST;
    //--------------------------
    //处理触摸事件 ~
    public static int  SNAP_VELOCITY = 600 ;
    private int mTouchSlop = 0 ;
    private float mLastionMotionX = 0 ;
    private float mLastMotionY = 0 ;
    //处理触摸的速率
    private VelocityTracker mVelocityTracker = null ;
    private int screenHeight;


    public MultiViewGroup(Context context) {
        this(context,null);
    }

    public MultiViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MultiViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        init();
    }

    private void init() {
        mScroller = new Scroller(mContext);

        // 初始化3个 LinearLayout控件
        LinearLayout oneLL = new LinearLayout(mContext);
        oneLL.setBackgroundColor(Color.RED);
        addView(oneLL);

        LinearLayout twoLL = new LinearLayout(mContext);
        twoLL.setBackgroundColor(Color.YELLOW);
        addView(twoLL);

        LinearLayout threeLL = new LinearLayout(mContext);
        threeLL.setBackgroundColor(Color.BLUE);
        addView(threeLL);

        //初始化一个最小滑动距离
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;
        Log.d(TAG," screenWidth="+screenWidth+" screenHeight="+screenHeight);

    }
    // measure过程
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        Log.i(TAG, "--- start onMeasure --");

        // 设置该ViewGroup的大小
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);

        int childCount = getChildCount();
        Log.i(TAG, "--- onMeasure childCount is -->" + childCount);
//        for (int i = 0; i < childCount; i++) {
//            View child = getChildAt(i);
//            // 设置每个子视图的大小 ， 即全屏
//            //如果measure(0,0),子视图的大小由父类觉得
//            child.measure(getWidth(), screenHeight);
//        }
    }
    /*
     *左上右下
     * @param l Left position, relative to parent
     * @param t Top position, relative to parent
     * @param r Right position, relative to parent
     * @param b Bottom position, relative to parent
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // TODO Auto-generated method stub
        Log.i(TAG, "--- start onLayout --");
        int startLeft = 0; // 每个子视图的起始布局坐标
        int startTop = 10; // 间距设置为10px 相当于 android：marginTop= "10px"
        int childCount = getChildCount();
        Log.i(TAG, "--- onLayout childCount is -->" + childCount );

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);

            //即使可见的，才划到屏幕上
            if(child.getVisibility() != View.GONE)
                child.layout(startLeft, startTop,
                        startLeft + getWidth(),
                        startTop + screenHeight);

            startLeft = startLeft + getWidth() ; //校准每个子View的起始布局位置
            //三个子视图的在屏幕中的分布如下 [0 , 320] / [320,640] / [640,960]
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.i(TAG, "--- onTouchEvent--> ");

        // TODO Auto-generated method stub
        Log.e(TAG, "onTouchEvent start");
        if (mVelocityTracker == null) {

            Log.e(TAG, "onTouchEvent start-------** VelocityTracker.obtain");

            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
        super.onTouchEvent(event);

        //手指位置地点
        float x = event.getX();
        float y = event.getY();

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //如果屏幕的动画还没结束，你就按下了，我们就结束该动画
                if(mScroller != null){
                    if(!mScroller.isFinished()){
                        mScroller.abortAnimation();
                    }
                }

                mLastionMotionX = x ;
                break ;
            case MotionEvent.ACTION_MOVE:
                int detaX = (int)(mLastionMotionX - x );
                scrollBy(detaX, 0);

                Log.e(TAG, "--- MotionEvent.ACTION_MOVE--> detaX is " + detaX );
                mLastionMotionX = x ;

                break ;
            case MotionEvent.ACTION_UP:

                final VelocityTracker velocityTracker = mVelocityTracker  ;
                velocityTracker.computeCurrentVelocity(1000);

                int velocityX = (int) velocityTracker.getXVelocity() ;

                Log.e(TAG , "---velocityX---" + velocityX);

                //滑动速率达到了一个标准(快速向右滑屏，返回上一个屏幕) 马上进行切屏处理
                if (velocityX > SNAP_VELOCITY && curScreen > 0) {
                    // Fling enough to move left
                    Log.e(TAG, "snap left");
                    snapToScreen(curScreen - 1);
                }
                //快速向左滑屏，返回下一个屏幕)
                else if(velocityX < -SNAP_VELOCITY && curScreen < (getChildCount()-1)){
                    Log.e(TAG, "snap right");
                    snapToScreen(curScreen + 1);
                }
                //以上为快速移动的 ，强制切换屏幕
                else{
                    //我们是缓慢移动的，因此先判断是保留在本屏幕还是到下一屏幕
                    snapToDestination();
                }

                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }

                mTouchState = TOUCH_STATE_REST ;

                break;
            case MotionEvent.ACTION_CANCEL:
                mTouchState = TOUCH_STATE_REST ;
                break;
        }
        return true;
    }

//-------------------------Scroll---------------------------------------


    private void snapToScreen(int whichScreen){
        //简单的移到目标屏幕，可能是当前屏或者下一屏幕
        //直接跳转过去，不太友好
        //scrollTo(mLastScreen * getWidth(), 0);
        //为了友好性，我们在增加一个动画效果
        //需要再次滑动的距离 屏或者下一屏幕的继续滑动距离

        curScreen = whichScreen ;

        if(curScreen > getChildCount() - 1)
            curScreen = getChildCount() - 1 ;

        int dx = curScreen*getWidth() - getScrollX() ;

        Log.e(TAG, "### onTouchEvent  ACTION_UP### dx is " + dx);

        mScroller.startScroll(getScrollX(), 0, dx, 0,Math.abs(dx) * 2);

        //此时需要手动刷新View 否则没效果
        invalidate();

    }

    ////我们是缓慢移动的
    private void snapToDestination(){
        //当前的偏移位置
        int scrollX = getScrollX() ;
        int scrollY = getScrollY() ;

        Log.e(TAG, "### onTouchEvent snapToDestination ### scrollX is " + scrollX);

        //判断是否超过下一屏的中间位置，如果达到就抵达下一屏，否则保持在原屏幕
        //直接使用这个公式判断是哪一个屏幕 前后或者自己
        //判断是否超过下一屏的中间位置，如果达到就抵达下一屏，否则保持在原屏幕
        // 这样的一个简单公式意思是：假设当前滑屏偏移值即 scrollCurX 加上每个屏幕一半的宽度，除以每个屏幕的宽度就是
        //  我们目标屏所在位置了。 假如每个屏幕宽度为320dip, 我们滑到了500dip处，很显然我们应该到达第二屏
        int destScreen = (getScrollX() + getWidth() / 2 ) / getWidth() ;


        Log.e(TAG, "### onTouchEvent  ACTION_UP### dx destScreen " + destScreen);

        snapToScreen(destScreen);
    }
}
