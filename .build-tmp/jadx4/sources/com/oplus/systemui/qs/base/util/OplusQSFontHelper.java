package com.oplus.systemui.qs.base.util;

import android.content.Context;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSFontHelper {
    public static volatile OplusQSFontHelper sInstance;
    public Context mContext;
    public float[] mFontScaleSizes;

    private float[] getFontScaleSize() {
        String[] stringArray = this.mContext.getResources().getStringArray(2130903200);
        if (stringArray.length <= 0) {
            return null;
        }
        float[] fArr = new float[stringArray.length];
        for (int i = 0; i != stringArray.length; i++) {
            try {
                fArr[i] = Float.parseFloat(stringArray[i]);
            } catch (Exception unused) {
                LogUtil.d(LogUtil.TAG_QS, "OplusQSFontHelper", "parse font size float exception");
            }
        }
        return fArr;
    }

    public static OplusQSFontHelper getInstance(Context context) {
        if (sInstance == null) {
            synchronized (OplusQSFontHelper.class) {
                try {
                    if (sInstance == null) {
                        OplusQSFontHelper oplusQSFontHelper = new OplusQSFontHelper();
                        Context applicationContext = context.getApplicationContext();
                        oplusQSFontHelper.mContext = applicationContext;
                        if (applicationContext == null) {
                            LogUtil.d(LogUtil.TAG_QS, "OplusQSFontHelper", "mContext is null");
                        } else {
                            oplusQSFontHelper.mFontScaleSizes = oplusQSFontHelper.getFontScaleSize();
                        }
                        sInstance = oplusQSFontHelper;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }
}
