package com.oplus.systemui.qs.base.spotlight;

import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.exifinterface.media.ExifInterface$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.coui.appcompat.spotlight.COUISpotLightEffect;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SharedSpotLightEffect$setupProgressListener$1 implements COUISpotLightEffect.OnSpotLightProgressScopeListener {
    public /* synthetic */ SharedSpotLightEffect.SpotLightSession $session;
    public /* synthetic */ SharedSpotLightEffect.TypeState $typeState;

    public final void onProgressScopeChanged(float f) {
        SharedSpotLightEffect.TypeState typeState = this.$typeState;
        SharedSpotLightEffect.SpotLightSession spotLightSession = this.$session;
        if (!spotLightSession.getPausedOutsideBounds() && !spotLightSession.getRecycling() && spotLightSession.getAwaitingExtinguishEnd() && f <= 0.001f) {
            SharedSpotLightEffect sharedSpotLightEffect = SharedSpotLightEffect.INSTANCE;
            sharedSpotLightEffect.getClass();
            if (spotLightSession.getRecycling()) {
                return;
            }
            spotLightSession.setRecycling(true);
            spotLightSession.setAwaitingExtinguishEnd(false);
            spotLightSession.setLayerActive(false);
            spotLightSession.setActivePointerId(-1);
            spotLightSession.setSupportSpotLight(false);
            Drawable.Callback callback = spotLightSession.getDrawable().getCallback();
            View view = callback instanceof View ? (View) callback : null;
            if (view != null) {
                view.invalidate();
            }
            Drawable.Callback callback2 = spotLightSession.getDrawable().getCallback();
            View view2 = callback2 instanceof View ? (View) callback2 : null;
            if (view2 != null) {
                int iIdentityHashCode = System.identityHashCode(view2);
                if (typeState.getRunningSessions().get(iIdentityHashCode) == spotLightSession) {
                    typeState.getRunningSessions().remove(iIdentityHashCode);
                    SharedSpotLightEffect.recycleSession(SharedSpotLightEffect.access$resolveSessionType(sharedSpotLightEffect, typeState), typeState, spotLightSession, "progressSettled");
                }
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m(SharedSpotLightEffect.typeStateLog(SharedSpotLightEffect.access$resolveSessionType(sharedSpotLightEffect, typeState), typeState), "SharedSpotLightEffect-->", "SystemUi--QuickSettings", ExifInterface$.ExternalSyntheticOutline1.m("[progressListener] extinguish settled ", SharedSpotLightEffect.hostLog(view2), " session=", SharedSpotLightEffect.sessionLog(spotLightSession), " "));
            }
        }
    }
}
