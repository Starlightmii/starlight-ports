package com.oplus.systemui.people.widget;

import com.android.systemui.people.widget.OplusPeopleSpaceWidgetManagerEx;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPeopleSpaceWidgetManagerExImpl extends OplusPeopleSpaceWidgetManagerEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "PeopleSpaceWidgetManager";

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public void onUserUnlocked(Executor executor, Runnable runnable) {
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isExpRegion()) {
            executor.execute(runnable);
        } else {
            LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "onUserUnlocked but the region is domestic, skip update", null, 8);
        }
    }
}
