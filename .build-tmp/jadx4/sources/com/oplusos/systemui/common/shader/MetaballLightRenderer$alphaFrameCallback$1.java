package com.oplusos.systemui.common.shader;

import android.os.SystemClock;
import android.view.Choreographer;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable$;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MetaballLightRenderer$alphaFrameCallback$1 implements Choreographer.FrameCallback {
    public /* synthetic */ MetaballLightRenderer this$0;

    @Override // android.view.Choreographer.FrameCallback
    public final void doFrame(long j) {
        if (this.this$0.alphaAnimation != MetaballLightRenderer.AlphaAnimation.NONE) {
            MetaballLightRenderer metaballLightRenderer = this.this$0;
            if (metaballLightRenderer.isVisible && metaballLightRenderer.hostView.isAttachedToWindow()) {
                long j2 = this.this$0.alphaAnimDurationMs;
                if (j2 < 0) {
                    j2 = 0;
                }
                float fCoerceIn = j2 == 0 ? 1.0f : RangesKt___RangesKt.coerceIn((SystemClock.elapsedRealtime() - this.this$0.alphaAnimStartElapsed) / j2, 0.0f, 1.0f);
                MetaballLightRenderer metaballLightRenderer2 = this.this$0;
                float f = metaballLightRenderer2.alphaStartMultiplier;
                metaballLightRenderer2.alphaMultiplier = DrawerArrowDrawable$.ExternalSyntheticOutline0.m(metaballLightRenderer2.alphaEndMultiplier, f, fCoerceIn, f);
                metaballLightRenderer2.hostView.invalidate();
                MetaballLightRenderer metaballLightRenderer3 = this.this$0;
                if (fCoerceIn >= 1.0f) {
                    MetaballLightRenderer.access$finishAlphaAnimation(metaballLightRenderer3);
                } else {
                    metaballLightRenderer3.scheduleAlphaFrame();
                }
            }
        }
    }
}
