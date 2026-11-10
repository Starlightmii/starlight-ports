package com.oplus.systemui.statusbar.phone;

import android.content.Context;
import android.view.MotionEvent;
import androidx.collection.FloatList$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.systemui.statusbar.phone.OplusPhoneStatusBarViewTouchHandlerEx;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPhoneStatusBarViewTouchHandlerExImpl extends OplusPhoneStatusBarViewTouchHandlerEx {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusPhoneStatusBarViewTouchHandlerExImpl";
    private final OplusStylusSwipeEnableObserverStarter stylusSwipeObserverStarter;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusPhoneStatusBarViewTouchHandlerExImpl(OplusStylusSwipeEnableObserverStarter oplusStylusSwipeEnableObserverStarter) {
        this.stylusSwipeObserverStarter = oplusStylusSwipeEnableObserverStarter;
    }

    public boolean isValidEvent(Context context, int i, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return super.isValidEvent(context, i, motionEvent);
        }
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(2131173048);
        boolean zIsStylusPointer = motionEvent.isStylusPointer();
        boolean state = this.stylusSwipeObserverStarter.getState();
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("sbWidth: ", i, dimensionPixelSize, ", regionWidth: ", ", x: ");
        VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM, x, ", y: ", y, "， ");
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m(sbM, zIsStylusPointer, ", enable: ", state), null, 8);
        return !state || !zIsStylusPointer || motionEvent.getY() > ((float) dimensionPixelSize) || motionEvent.getX() < ((float) (i - dimensionPixelSize));
    }
}
