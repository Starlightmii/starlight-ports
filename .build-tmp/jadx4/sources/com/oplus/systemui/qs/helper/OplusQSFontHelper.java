package com.oplus.systemui.qs.helper;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import com.android.systemui.statusbar.notification.OplusPhysicsPropertyAnimator$;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSFontHelper {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusQSFontHelper";
    private static volatile OplusQSFontHelper sInstance;
    private final Context mContext;
    private final float[] mFontScaleSizes;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final OplusQSFontHelper getInstance(Context context) {
            OplusQSFontHelper oplusQSFontHelper;
            OplusQSFontHelper oplusQSFontHelper2 = OplusQSFontHelper.sInstance;
            if (oplusQSFontHelper2 != null) {
                return oplusQSFontHelper2;
            }
            synchronized (OplusQSFontHelper.class) {
                oplusQSFontHelper = OplusQSFontHelper.sInstance;
                if (oplusQSFontHelper == null) {
                    oplusQSFontHelper = new OplusQSFontHelper(context);
                    OplusQSFontHelper.sInstance = oplusQSFontHelper;
                }
            }
            return oplusQSFontHelper;
        }
    }

    public OplusQSFontHelper(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        float[] fArr = null;
        if (applicationContext == null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "OplusQSFontHelper-->mContext is null");
            }
            this.mFontScaleSizes = null;
            return;
        }
        String[] stringArray = applicationContext.getResources().getStringArray(2130903200);
        if (stringArray.length != 0) {
            fArr = new float[stringArray.length];
            int length = stringArray.length;
            for (int i = 0; i < length; i++) {
                try {
                    fArr[i] = Float.parseFloat(stringArray[i]);
                } catch (Exception unused) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        Log.d("SystemUi--QuickSettings", "OplusQSFontHelper-->parse font size float exception");
                    }
                }
            }
        }
        this.mFontScaleSizes = fArr;
    }

    public static final OplusQSFontHelper getInstance(Context context) {
        return Companion.getInstance(context);
    }

    public final float getAdapterTextSizeSp(int i) {
        float[] fArr;
        int[] intArray;
        float f = 12.0f;
        if (this.mContext != null && (fArr = this.mFontScaleSizes) != null) {
            float f2 = Resources.getSystem().getConfiguration().fontScale;
            int i2 = 0;
            float fAbs = Math.abs(fArr[0] - f2);
            int length = fArr.length;
            for (int i3 = 1; i3 < length; i3++) {
                float fAbs2 = Math.abs(fArr[i3] - f2);
                if (fAbs2 <= fAbs) {
                    i2 = i3;
                    fAbs = fAbs2;
                }
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                OplusPhysicsPropertyAnimator$.ExternalSyntheticOutline0.m("fontScale: ", f2, "OplusQSFontHelper-->", "SystemUi--QuickSettings");
            }
            Context context = this.mContext;
            switch (i) {
                case 0:
                    intArray = context.getResources().getIntArray(2130903267);
                    break;
                case 1:
                    intArray = context.getResources().getIntArray(2130903266);
                    break;
                case 2:
                    intArray = context.getResources().getIntArray(2130903269);
                    break;
                case 3:
                    intArray = context.getResources().getIntArray(2130903273);
                    break;
                case 4:
                    intArray = context.getResources().getIntArray(2130903272);
                    break;
                case 5:
                    intArray = context.getResources().getIntArray(2130903270);
                    break;
                case 6:
                    intArray = context.getResources().getIntArray(2130903268);
                    break;
                case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                    intArray = context.getResources().getIntArray(2130903337);
                    break;
                default:
                    intArray = context.getResources().getIntArray(2130903265);
                    break;
            }
            f = intArray.length > i2 ? intArray[i2] : 12.0f;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "OplusQSFontHelper-->".concat("type: " + i + " fontSize: " + f));
            }
        }
        return f;
    }
}
