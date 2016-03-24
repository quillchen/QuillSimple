package quill.design.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by Quill on 2016/2/28.
 */
public class SysUtils {
    private TelephonyManager tm;
    private SysUtils()
    {
		/* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");

    }

    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context)
    {
        try
        {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context)
    {
        try
        {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取手机型号
     * @return
     */
    public static String getModel() {
        return android.os.Build.MODEL;
    }

    /**
     * Firmware/OS 版本号
     * @return
     */
    public static String getVersionRelease() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * SDK版本号
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String getSdkApi() {
        return android.os.Build.VERSION.SDK;
    }
    /**
     * 获取手机屏幕分辨率
     * @param activity
     * @return
     */
    public static String DisplayMetrics(Activity activity){
        android.util.DisplayMetrics dm=new android.util.DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        //获得手机的宽度和高度像素单位为px
        return "DisplayMetrics:" + dm.widthPixels+"* "+dm.heightPixels;
    }

    // 获取手机CPU信息
    public static String getCpuInfo() {
        String str1 = "/proc/cpuinfo";
        String str2 = "";
        String[] cpuInfo = { "", "" }; // 1-cpu型号 //2-cpu频率
        String[] arrayOfString;
        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            for (int i = 2; i < arrayOfString.length; i++) {
                cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
            }
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            cpuInfo[1] += arrayOfString[2];
            localBufferedReader.close();
        } catch (Exception e) {
        }
        return "1-cpu型号:" + cpuInfo[0] + "2-cpu频率:" + cpuInfo[1];
    }

    /**
     * 获取手机号
     * @return
     */
    public String getPhone(Context context){
        tm=(TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getLine1Number();
    }

    /**
     * 去掉 +86|86 的短信中心号和手机号码
     * @param str
     * @return
     */
    public static String getSub(String str) {
        String subStr = "";
        try {
            if (str == null) {
                return "";
            }
            int len = str.length();
            if (len > 11) {
                subStr = str.substring(len - 11);
            } else {
                subStr = str;
            }
        } catch (Exception ioe) {

        }
        return subStr;
    }

    /**
     * IMEI（International Mobile Equipment Identity，移动设备国际识别码，又称为国际移动设备标识）是手机的唯一识别号码。
     * @return
     */
    public String getImei(Context context){
        tm=(TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }
    /**
     * IMSI 全称为 International Mobile Subscriber Identity，中文翻译为国际移动用户识别码。
     * 它是在公众陆地移动电话网（PLMN）中用于唯一识别移动用户的一个号码。在GSM网络，这个号码通常被存放在SIM卡中
     * @return
     */
    public String getSubscriberId(Context context){
        if (isSimReady(context)) {
            return tm.getSubscriberId();
        }
        return "";
    }

    /**
     * 判断SIM卡是否准备好
     *
     * @param context
     * @return
     */
    public  boolean isSimReady(Context context) {
        try {

            int simState = tm.getSimState();
            if (simState == TelephonyManager.SIM_STATE_READY) {
                return true;
            }
        } catch (Exception e) {
            Log.w("PhoneHelper", "021:" + e.toString());
        }
        return false;
    }

    /**
     * 获取Manifest meta_data的Value
     * @param name
     * @param def
     * @return
     */
    public String getMetaDataValue(Context context,String name, String def) {
        String value = getMetaDataValue(context,name);
        return (value == null) ? def : value;
    }
    private String getMetaDataValue(Context context,String name) {
        Object value = null;
        PackageManager packageManager = context.getPackageManager();
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(),PackageManager.GET_META_DATA);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                value = applicationInfo.metaData.get(name);
            }
        } catch (Exception e) {
        }
        return value.toString();
    }

}
