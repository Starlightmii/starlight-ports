package com.oplus.vfxsdk.common;

import _COROUTINE.CoroutineDebuggingKt$;
import android.content.Context;
import android.text.format.DateFormat;
import androidx.car.app.serialization.Bundler$;
import androidx.constraintlayout.motion.widget.MotionLayout$;
import com.oplus.transition.OplusAnimationController;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class Utils {
    public static final Companion Companion = new Companion();
    private static final String HOUR_FORMAT_12 = "h";
    private static final String HOUR_FORMAT_24 = "H";
    private static final String MINUTE_FORMAT = "mm";
    public static final String TAG = "VFX:LOG";
    public static boolean debugLog;
    public double mAllPauseTime;
    public double mStartTime = -1.0d;
    public double mPauseStartTime = -1.0d;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final boolean getDebugLog() {
            return Utils.debugLog;
        }

        public final void setDebugLog(boolean z) {
            Utils.debugLog = z;
        }
    }

    public final File copyAssetToFile(Context context, String str, File file) throws IOException {
        InputStream inputStreamOpen = context.getAssets().open(str);
        File file2 = new File(file, str);
        if (!file2.getParentFile().exists()) {
            file2.getParentFile().mkdir();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        try {
            try {
                byte[] bArr = new byte[OplusAnimationController.FLAG_FORCE_ENABLE_FLEXIBLE_SEAMLESS_V1];
                while (true) {
                    int i = inputStreamOpen.read(bArr);
                    if (i == -1) {
                        CloseableKt.closeFinally(fileOutputStream, (Throwable) null);
                        CloseableKt.closeFinally(inputStreamOpen, (Throwable) null);
                        return file2;
                    }
                    fileOutputStream.write(bArr, 0, i);
                    try {
                        throw th;
                    } catch (Throwable th) {
                        CloseableKt.closeFinally(inputStreamOpen, th);
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                try {
                    throw th2;
                } catch (Throwable th3) {
                    CloseableKt.closeFinally(fileOutputStream, th2);
                    throw th3;
                }
            }
        } catch (Throwable th4) {
            throw th4;
        }
    }

    public final String getTime(Context context) {
        Calendar calendar = Calendar.getInstance();
        String str = DateFormat.is24HourFormat(context) ? (String) DateFormat.format(HOUR_FORMAT_24, calendar) : (String) DateFormat.format(HOUR_FORMAT_12, calendar);
        try {
            str = String.format("%02d", Arrays.copyOf(new Object[]{Integer.valueOf(Integer.parseInt(str))}, 1));
        } catch (Exception unused) {
            MotionLayout$.ExternalSyntheticOutline0.m$1("fail to parse hour string to int, hour = ", str, TAG);
        }
        String str2 = (String) DateFormat.format(MINUTE_FORMAT, calendar);
        try {
            str2 = String.format("%02d", Arrays.copyOf(new Object[]{Integer.valueOf(Integer.parseInt(str2))}, 1));
        } catch (Exception unused2) {
            MotionLayout$.ExternalSyntheticOutline0.m$1("fail to parse hour string to int, mimute = ", str2, TAG);
        }
        return Bundler$.ExternalSyntheticOutline0.m(str, str2);
    }

    public final double refreshTimestamp(double d) {
        if (this.mStartTime < 0.0d) {
            this.mStartTime = d;
            this.mPauseStartTime = d;
            this.mAllPauseTime = 0.0d;
        }
        double d2 = this.mPauseStartTime;
        if (d - d2 > 0.5d) {
            this.mAllPauseTime = (d - d2) + this.mAllPauseTime;
        }
        this.mPauseStartTime = d;
        return (d - this.mStartTime) - this.mAllPauseTime;
    }

    public final File saveByteBufferToFile(ByteBuffer byteBuffer, String str) {
        byteBuffer.rewind();
        File file = new File(str);
        if (file.exists() && ((int) file.length()) != byteBuffer.remaining()) {
            file.delete();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr);
            fileOutputStream.write(bArr);
            CloseableKt.closeFinally(fileOutputStream, (Throwable) null);
            return file;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(fileOutputStream, th);
                throw th2;
            }
        }
    }

    public final void unzip(String str, String str2) throws IOException {
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        byte[] bArr = new byte[OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE];
        FileInputStream fileInputStream = new FileInputStream(str);
        ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);
        for (ZipEntry nextEntry = zipInputStream.getNextEntry(); nextEntry != null; nextEntry = zipInputStream.getNextEntry()) {
            File file2 = new File(CoroutineDebuggingKt$.ExternalSyntheticOutline0.m(str2, File.separator, nextEntry.getName()));
            if (nextEntry.isDirectory()) {
                file2.mkdirs();
            } else {
                File parentFile = file2.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                while (true) {
                    int i = zipInputStream.read(bArr);
                    if (i <= 0) {
                        break;
                    } else {
                        fileOutputStream.write(bArr, 0, i);
                    }
                }
                fileOutputStream.close();
            }
            zipInputStream.closeEntry();
        }
        zipInputStream.close();
        fileInputStream.close();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final HashMap<String, InputStream> unzipToMap(String str) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(str);
        ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);
        HashMap<String, InputStream> map = new HashMap<>();
        for (ZipEntry nextEntry = zipInputStream.getNextEntry(); nextEntry != null; nextEntry = zipInputStream.getNextEntry()) {
            if (!nextEntry.isDirectory()) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE];
                while (true) {
                    int i = zipInputStream.read(bArr);
                    if (i == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, i);
                }
                map.put(StringsKt__StringsKt.split$default(nextEntry.getName(), new String[]{"."}, 0, 6).get(0), new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            }
            zipInputStream.closeEntry();
        }
        zipInputStream.close();
        fileInputStream.close();
        return map;
    }

    public final void useFile(File file, Function1 function1) {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            function1.invoke(fileInputStream);
            CloseableKt.closeFinally(fileInputStream, (Throwable) null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(fileInputStream, th);
                throw th2;
            }
        }
    }
}
