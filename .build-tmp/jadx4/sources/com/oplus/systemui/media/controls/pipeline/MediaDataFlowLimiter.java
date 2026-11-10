package com.oplus.systemui.media.controls.pipeline;

import android.content.Context;
import android.os.Handler;
import androidx.car.app.hardware.common.CarZone$;
import androidx.compose.animation.ChangeSize$;
import androidx.exifinterface.media.ExifInterface$;
import androidx.room.util.CursorUtil$;
import com.android.systemui.media.MediaDataExtKt;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import java.lang.invoke.VarHandle;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MediaDataFlowLimiter {
    public static final Companion Companion = new Companion();
    private static final String TAG = "MediaDataFlowLimiter";
    private final Context context;
    private final Lazy delayTime$delegate;
    private final Lazy mainHandler$delegate;
    private final MediaDataManager mediaDataManager;
    private final Map<String, PendingWindow> pendingWindows = new LinkedHashMap();
    private final Map<String, Boolean> lastDispatchedPlaying = new LinkedHashMap();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class MediaKey {
        private final String newKey;
        private final String oldKey;

        public MediaKey(String str, String str2) {
            this.newKey = str;
            this.oldKey = str2;
        }

        public final String component1() {
            return this.newKey;
        }

        public final String component2() {
            return this.oldKey;
        }

        public final MediaKey copy(String str, String str2) {
            return new MediaKey(str, str2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MediaKey)) {
                return false;
            }
            MediaKey mediaKey = (MediaKey) obj;
            return Intrinsics.areEqual(this.newKey, mediaKey.newKey) && Intrinsics.areEqual(this.oldKey, mediaKey.oldKey);
        }

        public final String getNewKey() {
            return this.newKey;
        }

        public final String getOldKey() {
            return this.oldKey;
        }

        public final int hashCode() {
            int iHashCode = this.newKey.hashCode() * 31;
            String str = this.oldKey;
            return iHashCode + (str == null ? 0 : str.hashCode());
        }

        public String toString() {
            return CarZone$.ExternalSyntheticOutline0.m("MediaKey(newKey=", this.newKey, ", oldKey=", this.oldKey, ")");
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class PendingWindow {
        public boolean dirty;
        private final MediaKey mediaKey;
        private final Runnable timeoutRunnable;

        public PendingWindow(MediaKey mediaKey, boolean z, Runnable runnable) {
            this.mediaKey = mediaKey;
            this.dirty = z;
            this.timeoutRunnable = runnable;
        }

        public final MediaKey component1() {
            return this.mediaKey;
        }

        public final Runnable component3() {
            return this.timeoutRunnable;
        }

        public final PendingWindow copy(MediaKey mediaKey, boolean z, Runnable runnable) {
            return new PendingWindow(mediaKey, z, runnable);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PendingWindow)) {
                return false;
            }
            PendingWindow pendingWindow = (PendingWindow) obj;
            return Intrinsics.areEqual(this.mediaKey, pendingWindow.mediaKey) && this.dirty == pendingWindow.dirty && Intrinsics.areEqual(this.timeoutRunnable, pendingWindow.timeoutRunnable);
        }

        public final boolean getDirty() {
            return this.dirty;
        }

        public final MediaKey getMediaKey() {
            return this.mediaKey;
        }

        public final Runnable getTimeoutRunnable() {
            return this.timeoutRunnable;
        }

        public final int hashCode() {
            return this.timeoutRunnable.hashCode() + ChangeSize$.ExternalSyntheticOutline0.m(this.mediaKey.hashCode() * 31, 31, this.dirty);
        }

        public final void setDirty(boolean z) {
            this.dirty = z;
        }

        public String toString() {
            return "PendingWindow(mediaKey=" + this.mediaKey + ", dirty=" + this.dirty + ", timeoutRunnable=" + this.timeoutRunnable + ")";
        }
    }

    public static long $r8$lambda$E2dC4oGBc6uk24d07X6wy55R3r4(MediaDataFlowLimiter mediaDataFlowLimiter) {
        return ScrimUtil.isLowGaussianLevel(mediaDataFlowLimiter.context) ? 300L : 200L;
    }

    public MediaDataFlowLimiter(MediaDataManager mediaDataManager, Context context) {
        this.mediaDataManager = mediaDataManager;
        this.context = context;
        MediaDataFlowLimiter$$ExternalSyntheticLambda0 mediaDataFlowLimiter$$ExternalSyntheticLambda0 = new MediaDataFlowLimiter$$ExternalSyntheticLambda0();
        mediaDataFlowLimiter$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.delayTime$delegate = LazyKt__LazyJVMKt.lazy(mediaDataFlowLimiter$$ExternalSyntheticLambda0);
        this.mainHandler$delegate = LazyKt__LazyJVMKt.lazy(new MediaDataFlowLimiter$$ExternalSyntheticLambda1());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long getDelayTime() {
        return ((Number) this.delayTime$delegate.getValue()).longValue();
    }

    public static String getLogKey(MediaData mediaData) {
        return mediaData.getApp() + "(isPlaying:" + mediaData.isPlaying() + ")";
    }

    private final Handler getMainHandler() {
        return (Handler) this.mainHandler$delegate.getValue();
    }

    public final void dispatchMediaData(MediaKey mediaKey, MediaData mediaData, String str) {
        MediaData mediaData2 = this.mediaDataManager.getMediaData(mediaKey.getNewKey());
        if (mediaData2 == null) {
            String newKey = mediaKey.getNewKey();
            String logKey = getLogKey(mediaData);
            String strPrintInfo = MediaDataExtKt.printInfo(mediaData, true);
            StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("dispatchMediaData, ", str, ", have no this data!, ", newKey, " ");
            sbM.append(logKey);
            sbM.append(", ");
            sbM.append(strPrintInfo);
            OplusNotificationLogger.logMediaPanel(TAG, sbM.toString());
            return;
        }
        String newKey2 = mediaKey.getNewKey();
        String logKey2 = getLogKey(mediaData2);
        String strPrintInfo2 = MediaDataExtKt.printInfo(mediaData2, true);
        StringBuilder sbM2 = ExifInterface$.ExternalSyntheticOutline1.m("dispatchMediaData, ", str, ", ", newKey2, " ");
        sbM2.append(logKey2);
        sbM2.append(", currentData = ");
        sbM2.append(strPrintInfo2);
        OplusNotificationLogger.logMediaPanel(TAG, sbM2.toString());
        this.mediaDataManager.notifyMediaDataLoaded(mediaKey.getNewKey(), mediaKey.getOldKey(), mediaData2);
        this.lastDispatchedPlaying.put(mediaKey.getNewKey(), mediaData2.isPlaying());
    }

    public final void onMediaDataLoaded(String str, String str2, MediaData mediaData) {
        String str3;
        PendingWindow pendingWindowRemove;
        MediaKey mediaKey = new MediaKey(str, str2);
        boolean z = (str2 == null || Intrinsics.areEqual(str, str2)) ? false : true;
        boolean z2 = this.lastDispatchedPlaying.containsKey(str) && !Intrinsics.areEqual(this.lastDispatchedPlaying.get(str), mediaData.isPlaying());
        if (!z && !z2) {
            PendingWindow pendingWindow = this.pendingWindows.get(str);
            if (pendingWindow != null) {
                pendingWindow.setDirty(true);
                return;
            }
            dispatchMediaData(mediaKey, mediaData, "first data");
            MediaDataFlowLimiter$onMediaDataLoaded$timeoutRunnable$1 mediaDataFlowLimiter$onMediaDataLoaded$timeoutRunnable$1 = new MediaDataFlowLimiter$onMediaDataLoaded$timeoutRunnable$1();
            mediaDataFlowLimiter$onMediaDataLoaded$timeoutRunnable$1.this$0 = this;
            mediaDataFlowLimiter$onMediaDataLoaded$timeoutRunnable$1.$key = str;
            mediaDataFlowLimiter$onMediaDataLoaded$timeoutRunnable$1.$mediaKey = mediaKey;
            mediaDataFlowLimiter$onMediaDataLoaded$timeoutRunnable$1.$data = mediaData;
            VarHandle.storeStoreFence();
            this.pendingWindows.put(str, new PendingWindow(mediaKey, false, mediaDataFlowLimiter$onMediaDataLoaded$timeoutRunnable$1));
            getMainHandler().postDelayed(mediaDataFlowLimiter$onMediaDataLoaded$timeoutRunnable$1, getDelayTime());
            return;
        }
        if (z && z2) {
            str3 = "immediate(key+isPlaying)";
        } else {
            str3 = z ? "immediate(key)" : "immediate(isPlaying)";
        }
        String strM = CursorUtil$.ExternalSyntheticOutline0.m("newKey: ", mediaKey.getNewKey(), ", oldKey: ", mediaKey.getOldKey());
        String logKey = getLogKey(mediaData);
        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("onMediaDataLoaded: ", str3, ", mediaKey: ", strM, ", ");
        sbM.append(logKey);
        OplusNotificationLogger.logMediaPanel(TAG, sbM.toString());
        if (z && str2 != null) {
            PendingWindow pendingWindowRemove2 = this.pendingWindows.remove(str2);
            if (pendingWindowRemove2 != null) {
                getMainHandler().removeCallbacks(pendingWindowRemove2.getTimeoutRunnable());
            }
            this.lastDispatchedPlaying.remove(str2);
        }
        if (str != null && (pendingWindowRemove = this.pendingWindows.remove(str)) != null) {
            getMainHandler().removeCallbacks(pendingWindowRemove.getTimeoutRunnable());
        }
        dispatchMediaData(mediaKey, mediaData, str3);
    }
}
