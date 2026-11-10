package com.oplus.systemui.notification.framerate;

import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceControl;
import android.view.View;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.media.controls.domain.resume.MediaResumeListener$getResumeAction$1$;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger$;
import com.oplus.dynamicframerate.DynamicFrameRateManager;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BoostFrameRateManager {
    public static final Companion Companion = new Companion();
    private static final String TAG = "BoostRefreshRateManagerImpl";
    public final int dynamicFrameRateType = DynamicFrameRateManager.getDynamicFrameRateType();
    private final Bundle bundle = MediaResumeListener$getResumeAction$1$.ExternalSyntheticOutline0.m("frtc_diff_description", true);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public final void boostFrameRate(View view, boolean z) {
        if (view == null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "BoostRefreshRateManagerImpl-->view is null do nothing");
                return;
            }
            return;
        }
        boolean normalDebug = LogUtil.getNormalDebug();
        int i = this.dynamicFrameRateType;
        if (normalDebug && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MobileInputLogger$.ExternalSyntheticOutline0.m(view.hashCode(), "BoostRefreshRateManagerImpl-->", "SystemUi--Notification", OplusLockIconView$.ExternalSyntheticOutline0.m("boost = ", i, ", FrameRate = ", ", view.hashCode() = ", z));
        }
        if (i == 1 || i == 2) {
            DynamicFrameRateManager.setFrameRateNoContext(view, (SurfaceControl.Transaction) null, 20301, z ? -1 : -2, this.bundle);
        }
    }
}
