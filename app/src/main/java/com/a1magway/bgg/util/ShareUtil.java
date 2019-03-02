package com.a1magway.bgg.util;

import android.content.Context;
import android.content.Intent;

/**
 * Created by enid on 2018/6/19.
 */

public class ShareUtil {
    public static void share(Context context){
        Intent textIntent = new Intent(Intent.ACTION_SEND);
        textIntent.setType("text/plain");
        textIntent.putExtra(Intent.EXTRA_TEXT, "这是一段分享的文字");
        context.startActivity(Intent.createChooser(textIntent, "分享"));
    }
}
