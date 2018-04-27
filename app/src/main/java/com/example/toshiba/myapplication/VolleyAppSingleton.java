package com.example.toshiba.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class VolleyAppSingleton {

    private static VolleyAppSingleton  mVolleyAppSingletonInstance ;
    private RequestQueue mRequestQue ;
    private ImageLoader mImageLoader ;
    private static Context mContext ;

    private VolleyAppSingleton(Context context){

        mContext = context;
        mRequestQue = getRequestQue();

        mImageLoader = new ImageLoader(mRequestQue, new ImageLoader.ImageCache() {

            private final LruCache<String,Bitmap>

            cache= new LruCache<String, Bitmap>(20);
            @Override
            public Bitmap getBitmap(String url) {

                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

                cache.put(url,bitmap);

            }
        });
    }

    public static synchronized VolleyAppSingleton getmVolleyAppSingletonInstance(Context context){
        if ((mVolleyAppSingletonInstance ==null)){

            mVolleyAppSingletonInstance= new VolleyAppSingleton( context);



        }
        return  mVolleyAppSingletonInstance ;
    }

    public RequestQueue getRequestQue() {

        if (mRequestQue==null){
            mRequestQue= Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQue;
    }

    public <T> void addToRequestQueue(Request<T> req,String tag ){
        req.setTag(tag);
        getRequestQue().add(req);
    }

    public ImageLoader getmImageLoader() {
        return mImageLoader;
    }

    public void cancelPendingRequest(Object tag){
        if (mRequestQue!=null){
            mRequestQue.cancelAll(tag);
        }
    }


}
