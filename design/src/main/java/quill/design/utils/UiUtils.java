package quill.design.utils;

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

import quill.design.App;


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
			mToast = Toast.makeText(App.getContext(), "", Toast.LENGTH_SHORT);
		}
		mToast.setText(msg);
		mToast.show();
	}






	public static Resources getResource() {
		return App.getContext().getResources();
	}
	public static Context getContext(){
		return App.getContext();
	}


	//////////////////////////////////////////////////////////////////////////

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

	/**
	 * 获取到字符数组
	 * @param tabNames  字符数组的id
	 */
	public static String[] getStringArray(int tabNames) {
		return getResource().getStringArray(tabNames);
	}

}
