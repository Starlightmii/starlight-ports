package com.oplusos.keyguard.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.ParcelFileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class FileUtils {
    public static void closeFd(ParcelFileDescriptor parcelFileDescriptor) {
        if (parcelFileDescriptor != null) {
            try {
                parcelFileDescriptor.close();
            } catch (IOException e) {
                KgdLogUtil.w("FileUtils", "getBitmapFromUri, close ParcelFileDescriptor; exception " + e);
            }
        }
    }

    public static void closeInputStream(FileInputStream fileInputStream) {
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                KgdLogUtil.w("FileUtils", "getBitmapFromUri, close FileInputStream; exception " + e);
            }
        }
    }

    public static Bitmap getBitmapFromFile(Resources resources, int i, BitmapFactory.Options options) {
        if (i == 0) {
            return null;
        }
        try {
            return BitmapFactory.decodeResource(resources, i, options);
        } catch (Exception e) {
            KgdLogUtil.d("FileUtils", "getBitmapFromFile e = " + e.getMessage());
            return null;
        }
    }
}
