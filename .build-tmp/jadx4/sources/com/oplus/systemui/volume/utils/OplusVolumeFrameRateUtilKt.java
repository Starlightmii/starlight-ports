package com.oplus.systemui.volume.utils;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.collection.FloatList$;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.oplus.dynamicframerate.DynamicFrameRateManager;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusVolumeFrameRateUtilKt {
    private static final String TAG = "OplusVolumeFrameRateManager";
    public static final int dynamicFrameRateType = DynamicFrameRateManager.getDynamicFrameRateType();

    public static final void endFrameRateElevate(View view, int i) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "OplusVolumeFrameRateManager-->end frame rate elevate");
        }
        setFrameRateElevate(view, i, -2);
    }

    public static final void resetFrameRate(View view, int i) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "OplusVolumeFrameRateManager-->reset frame rate");
        }
        setFrameRateElevate(view, i, -2);
    }

    public static final void setFrameRateElevate(View view, int i, int i2) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            MobileInputLogger$.ExternalSyntheticOutline0.m(i2, "OplusVolumeFrameRateManager-->", "SystemUi--Volume", FloatList$.ExternalSyntheticOutline0.m("view.hashCode() = ", view.hashCode(), i, ", typeId = ", ", velocity = "));
        }
        int i3 = dynamicFrameRateType;
        if (i3 == 1 || i3 == 2) {
            DynamicFrameRateManager.setFrameRate(view, i, i2, (Bundle) null);
        }
    }

    public static final void startFrameRateElevate(View view, int i) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", "OplusVolumeFrameRateManager-->start frame rate elevate");
        }
        setFrameRateElevate(view, i, -1);
    }
}
