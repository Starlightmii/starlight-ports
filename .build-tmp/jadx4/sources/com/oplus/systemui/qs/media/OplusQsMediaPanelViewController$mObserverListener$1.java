package com.oplus.systemui.qs.media;

import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.util.ViewController;
import com.oplusos.systemui.common.observer.IObserverListener;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsMediaPanelViewController$mObserverListener$1 implements IObserverListener {
    public /* synthetic */ OplusQsMediaPanelViewController this$0;

    @Override // com.oplusos.systemui.common.observer.IObserverListener
    public final void onChange(boolean z) {
        MediaData mediaDataSelectPlayingOnes = OplusQsMediaUtil.selectPlayingOnes();
        if (mediaDataSelectPlayingOnes != null ? Intrinsics.areEqual(mediaDataSelectPlayingOnes.isPlaying(), Boolean.TRUE) : false) {
            return;
        }
        ((OplusQsBaseMediaPanelView) ((ViewController) this.this$0).mView).unBindMediaData();
    }
}
