package quill.simple.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sixgui.tmaster.MyApplication;

public class UiUtils {




	public static int dip2px(int dip) {
		final float scale = getResource().getDisplayMetrics().density;
		return (int) (dip * scale + 0.5f);
	}


	public static int px2dip(int px) {
		final float scale = getResource().getDisplayMetrics().density;
		return (int) (px / scale + 0.5f);
	}


	/**
	 * 得到屏幕宽度（像素）
	 */
	public static int getScreenWidth(){
		WindowManager wm = (WindowManager) getContext()
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}

	/**
	 * 得到屏幕搞定（像素）
	 */
	public static int getScreenHeight(){
		WindowManager wm = (WindowManager) getContext()
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.heightPixels;
	}

	/**
	 * 获取状态栏高度
	 */
	public static int getStatusHeight()
	{

		int statusHeight = -1;
		try
		{
			Class<?> clazz = Class.forName("com.android.internal.R$dimen");
			Object object = clazz.newInstance();
			int height = Integer.parseInt(clazz.getField("status_bar_height")
					.get(object).toString());
			statusHeight = getResource().getDimensionPixelSize(height);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return statusHeight;
	}

	public static Toast mToast;

	public static void showToast( String msg) {
		if (mToast == null) {
			mToast = Toast.makeText(MyApplication.getApplication(), "", Toast.LENGTH_SHORT);
		}
		mToast.setText(msg);
		mToast.show();
	}



	public static void showInfoDialog(Context context, String message) {
		showInfoDialog(context, message, "提示", "确定", null);
	}

	public static void showInfoDialog(Context context, String message,
									  String titleStr, String positiveStr,
									  DialogInterface.OnClickListener onClickListener) {

		AlertDialog.Builder localBuilder = new AlertDialog.Builder(context);
		localBuilder.setTitle(titleStr);
		localBuilder.setMessage(message);
		if (onClickListener == null)
			onClickListener = new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {

				}
			};
		localBuilder.setPositiveButton(positiveStr, onClickListener);
		localBuilder.show();
	}


	public static Resources getResource() {
		return MyApplication.getApplication().getResources();
	}
	public static Context getContext(){
		return MyApplication.getApplication();
	}


	//////////////////////////////////////////////////////////////////////////
	/**
	 * 把Runnable 方法提交到主线程运行
	 * @param runnable
	 */
	public static void runOnUiThread(Runnable runnable) {
		// 在主线程运行
		if(android.os.Process.myTid()== MyApplication.getMainTid()){
			runnable.run();
		}else{
			//获取handler  
			MyApplication.getHandler().post(runnable);
		}
	}

	public static View inflate(int id) {
		return View.inflate(getContext(), id, null);
	}

	public static Drawable getDrawalbe(int id) {
		return getResource().getDrawable(id);
	}

	public static int getDimens(int homePictureHeight) {
		return (int) getResource().getDimension(homePictureHeight);
	}
	/**
	 * 延迟执行 任务
	 * @param run   任务
	 * @param time  延迟的时间
	 */
	public static void postDelayed(Runnable run, int time) {
		MyApplication.getHandler().postDelayed(run, time); // 调用Runable里面的run方法
	}
	/**
	 * 取消任务
	 * @param auToRunTask
	 */
	public static void cancel(Runnable auToRunTask) {
		MyApplication.getHandler().removeCallbacks(auToRunTask);
	}
	/**
	 * 可以打开activity
	 *
	 */
//	public static void startActivity(Intent intent) {
//		// 如果不在activity里去打开activity  需要指定任务栈  需要设置标签
//		if(BaseActivity.activity==null){
//			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			getContext().startActivity(intent);
//		}else{
//			BaseActivity.activity.startActivity(intent);
//		}
//	}
	public static void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();

		if (listAdapter == null) {
			// pre-condition
			return;
		}

		int totalHeight = 0;

		for (int i = 0; i < listAdapter.getCount(); i++) {
			LogUtils.w("listAdapter.getCount="+listAdapter.getCount());
			View listItem = listAdapter.getView(i, null, listView);
			LogUtils.w("listItem="+i);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

	/**
	 * 获取到字符数组
	 * @param tabNames  字符数组的id
	 */
	public static String[] getStringArray(int tabNames) {
		return getResource().getStringArray(tabNames);
	}

}
