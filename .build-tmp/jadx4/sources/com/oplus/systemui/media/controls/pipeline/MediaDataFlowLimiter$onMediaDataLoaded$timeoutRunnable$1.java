package com.oplus.systemui.media.controls.pipeline;

import androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException$;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MediaDataFlowLimiter$onMediaDataLoaded$timeoutRunnable$1 implements Runnable {
    public /* synthetic */ MediaData $data;
    public /* synthetic */ String $key;
    public /* synthetic */ MediaDataFlowLimiter.MediaKey $mediaKey;
    public /* synthetic */ MediaDataFlowLimiter this$0;

    @Override // java.lang.Runnable
    public final void run() {
        MediaDataFlowLimiter.PendingWindow pendingWindow = (MediaDataFlowLimiter.PendingWindow) this.this$0.pendingWindows.remove(this.$key);
        if (pendingWindow != null && pendingWindow.getDirty()) {
            MediaDataFlowLimiter mediaDataFlowLimiter = this.this$0;
            mediaDataFlowLimiter.dispatchMediaData(this.$mediaKey, this.$data, PointerEventTimeoutCancellationException$.ExternalSyntheticOutline0.m(mediaDataFlowLimiter.getDelayTime(), "trailing data ", "ms"));
        }
        OplusNotificationLogger.logMediaPanelDebug("MediaDataFlowLimiter", "debounce remove " + this.$key + " dirty=" + (pendingWindow != null ? Boolean.valueOf(pendingWindow.getDirty()) : null));
    }
}
