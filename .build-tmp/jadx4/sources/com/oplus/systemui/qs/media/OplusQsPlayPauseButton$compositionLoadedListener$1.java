package com.oplus.systemui.qs.media;

import android.util.Log;
import com.oplus.anim.EffectiveOnCompositionLoadedListener;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsPlayPauseButton$compositionLoadedListener$1 implements EffectiveOnCompositionLoadedListener {
    public /* synthetic */ OplusQsPlayPauseButton this$0;

    public final void onCompositionLoaded() {
        OplusQsPlayPauseButton oplusQsPlayPauseButton = this.this$0;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("onCompositionLoaded: ", oplusQsPlayPauseButton.curLottieRes, oplusQsPlayPauseButton.curPlayData.getLottieResPlay(), " ", "OplusQsPlayPauseButton-->"));
        }
        if (oplusQsPlayPauseButton.curLottieRes == oplusQsPlayPauseButton.curPlayData.getLottieResPlay()) {
            oplusQsPlayPauseButton.playLottieAnimationInternal();
        }
    }
}
