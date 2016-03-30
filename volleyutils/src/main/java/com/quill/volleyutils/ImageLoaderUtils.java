package com.quill.volleyutils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.quill.volleyutils.controller.VolleyController;

public class ImageLoaderUtils {

    private Context context;

    public ImageLoaderUtils(Context context) {
        this.context = context;

    }

    public interface MyImageListener {
        void setImageBitmap(Bitmap bitmap);
    }

    public static void ImageView(Context context, String url, final MyImageListener listener) {

        ImageLoader imageLoader = VolleyController.getInstance(context).getImageLoader();

        imageLoader.get(url, new ImageListener() {
            @Override
            public void onResponse(ImageContainer response, boolean arg) {
                if (response.getBitmap() != null) {
                    //设置imageView
                    //	imageView.setImageBitmap(response.getBitmap());
                    listener.setImageBitmap(response.getBitmap());
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ImageLoaderUtils", "Image Error" + error.getMessage());
            }
        });
    }
/*
* 加载图片，带默认图片
*/
    public static void display(Context context, ImageView view, String url) {

        ImageLoader imageLoader = VolleyController.getInstance(context).getImageLoader();
        ImageListener listener = ImageLoader.getImageListener(view, R.drawable.default_image, R.drawable.default_image);
        imageLoader.get(url, listener);

    }
    /*
    * 加载图片，默认图片要自己传
    */
    public static void display(Context context, ImageView view, String url,int defaultImageResId,int errorImageResId) {
        ImageLoader imageLoader = VolleyController.getInstance(context).getImageLoader();
        ImageListener listener = ImageLoader.getImageListener(view, defaultImageResId, errorImageResId);
        imageLoader.get(url, listener);

    }

}
