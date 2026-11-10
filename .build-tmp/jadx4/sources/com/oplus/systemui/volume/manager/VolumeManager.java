package com.oplus.systemui.volume.manager;

import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.shade.PanelType;
import com.android.systemui.shade.ShadeExpansionStateManager;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.Map;
import java.util.WeakHashMap;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class VolumeManager {
    public static final Companion Companion = new Companion();
    public static final String INTERCEPT_VOLUME_OUTPUT_TAG = "INTERCEPT_VOLUME_OUTPUT";
    public static final String INTERCEPT_VOLUME_QS_TAG = "INTERCEPT_VOLUME_QS";
    private static final String TAG = "VolumeManager";
    private final WeakHashMap<String, CallBack> callbacks = new WeakHashMap<>();
    private final ShadeExpansionStateManager mExpansionMgr;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface CallBack {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public VolumeManager(ShadeExpansionStateManager shadeExpansionStateManager) {
        this.mExpansionMgr = shadeExpansionStateManager;
    }

    public final void addCallBack(String str, CallBack callBack) {
        this.callbacks.put(str, callBack);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0052  */
    /* JADX WARN: Code duplicated, block: B:24:0x005a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:28:0x000b A[SYNTHETIC] */
    public final boolean isShowVolumeSeekbar() {
        boolean z = true;
        for (Map.Entry<String, CallBack> entry : this.callbacks.entrySet()) {
            String key = entry.getKey();
            CallBack value = entry.getValue();
            if (Intrinsics.areEqual(key, INTERCEPT_VOLUME_QS_TAG)) {
                value.getClass();
                if (this.mExpansionMgr.isClosed(PanelType.SHADE_PANEL)) {
                    if (Intrinsics.areEqual(key, INTERCEPT_VOLUME_OUTPUT_TAG)) {
                        value.getClass();
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("not allow volume show by tag： ", key, "VolumeManager-->", "SystemUi--Volume");
                        }
                    }
                } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                    MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("not allow volume show by tag： ", key, "VolumeManager-->", "SystemUi--Volume");
                }
                z = false;
            } else if (Intrinsics.areEqual(key, INTERCEPT_VOLUME_OUTPUT_TAG)) {
                value.getClass();
                if (LogUtil.getNormalDebug()) {
                    MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("not allow volume show by tag： ", key, "VolumeManager-->", "SystemUi--Volume");
                }
                z = false;
            }
        }
        return z;
    }

    public final void removeCallBack(String str) {
        this.callbacks.remove(str);
    }
}
