package com.oplus.systemui.qs.base.spotlight;

import android.util.Log;
import android.view.MotionEvent;
import com.android.systemui.biometrics.udfps.NormalizedTouchData$;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.coui.appcompat.spotlight.COUISpotLightEffectDrawable;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SharedSpotLightEffect$$ExternalSyntheticLambda0 implements Function2 {
    public /* synthetic */ SharedSpotLightEffect.SpotLightSession f$0;
    public /* synthetic */ MotionEvent f$1;

    /* JADX WARN: Code duplicated, block: B:45:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:48:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:51:0x00e9  */
    public final Object invoke(Object obj, Object obj2) {
        SharedSpotLightEffect.SpotLightSession spotLightSession = this.f$0;
        MotionEvent motionEvent = this.f$1;
        float fFloatValue = ((Float) obj).floatValue();
        float fFloatValue2 = ((Float) obj2).floatValue();
        SharedSpotLightEffect.INSTANCE.getClass();
        COUISpotLightEffectDrawable drawable = spotLightSession.getDrawable();
        int iWidth = drawable.getBounds().width();
        int iHeight = drawable.getBounds().height();
        if (iWidth > 0 && iHeight > 0) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                spotLightSession.setAwaitingExtinguishEnd(false);
                spotLightSession.setActivePointerId(motionEvent.getPointerId(motionEvent.getActionIndex()));
                spotLightSession.setPausedOutsideBounds(false);
                SharedSpotLightEffect.applySpotLightPosition(drawable, fFloatValue, fFloatValue2, iWidth, iHeight);
                drawable.glow();
                spotLightSession.setLayerActive(true);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    StringBuilder sbM = NormalizedTouchData$.ExternalSyntheticOutline0.m(fFloatValue / iWidth, spotLightSession.getActivePointerId(), "[ACTION_DOWN] glow ptr=", " norm=(", ", ");
                    sbM.append(fFloatValue2 / iHeight);
                    sbM.append(")");
                    Log.d("SystemUi--QuickSettings", "SharedSpotLightEffect-->".concat(sbM.toString()));
                }
            } else if (actionMasked == 1) {
                if (motionEvent.findPointerIndex(spotLightSession.getActivePointerId()) < 0) {
                    motionEvent.getActionIndex();
                }
                SharedSpotLightEffect.applySpotLightPosition(drawable, fFloatValue, fFloatValue2, iWidth, iHeight);
                if (!spotLightSession.getAwaitingExtinguishEnd()) {
                    drawable.extinguish();
                    spotLightSession.setAwaitingExtinguishEnd(true);
                }
                spotLightSession.setActivePointerId(-1);
                spotLightSession.setPausedOutsideBounds(false);
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "SharedSpotLightEffect-->[ACTION_UP/CANCEL] extinguish");
                }
            } else if (actionMasked == 2) {
                int iFindPointerIndex = motionEvent.findPointerIndex(spotLightSession.getActivePointerId());
                if (iFindPointerIndex < 0 && motionEvent.getPointerCount() > 0) {
                    spotLightSession.setActivePointerId(motionEvent.getPointerId(0));
                    spotLightSession.setAwaitingExtinguishEnd(false);
                    drawable.cancel();
                    spotLightSession.setLayerActive(false);
                    spotLightSession.setPausedOutsideBounds(true);
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(spotLightSession.getActivePointerId(), "[ACTION_MOVE] adopt ptr=", "SharedSpotLightEffect-->", "SystemUi--QuickSettings");
                    }
                    iFindPointerIndex = 0;
                }
                if (iFindPointerIndex >= 0) {
                    float f = SharedSpotLightEffect.isOneHandedEnable ? (SharedSpotLightEffect.screenHeight / 2) + iHeight : iHeight;
                    if (fFloatValue >= 0.0f && fFloatValue <= iWidth && fFloatValue2 >= 0.0f && fFloatValue2 <= f) {
                        if (spotLightSession.getPausedOutsideBounds()) {
                            spotLightSession.setPausedOutsideBounds(false);
                            spotLightSession.setAwaitingExtinguishEnd(false);
                            drawable.cancel();
                            SharedSpotLightEffect.applySpotLightPosition(drawable, fFloatValue, fFloatValue2, iWidth, iHeight);
                            drawable.glow();
                            spotLightSession.setLayerActive(true);
                        }
                        SharedSpotLightEffect.applySpotLightPosition(drawable, fFloatValue, fFloatValue2, iWidth, iHeight);
                    } else if (!spotLightSession.getPausedOutsideBounds()) {
                        spotLightSession.setPausedOutsideBounds(true);
                        SharedSpotLightEffect.applySpotLightPosition(drawable, fFloatValue, fFloatValue2, iWidth, iHeight);
                        drawable.extinguish();
                        spotLightSession.setAwaitingExtinguishEnd(true);
                    }
                }
            } else if (actionMasked == 3) {
                if (motionEvent.findPointerIndex(spotLightSession.getActivePointerId()) < 0) {
                    motionEvent.getActionIndex();
                }
                SharedSpotLightEffect.applySpotLightPosition(drawable, fFloatValue, fFloatValue2, iWidth, iHeight);
                if (!spotLightSession.getAwaitingExtinguishEnd()) {
                    drawable.extinguish();
                    spotLightSession.setAwaitingExtinguishEnd(true);
                }
                spotLightSession.setActivePointerId(-1);
                spotLightSession.setPausedOutsideBounds(false);
                if (LogUtil.getNormalDebug()) {
                    Log.d("SystemUi--QuickSettings", "SharedSpotLightEffect-->[ACTION_UP/CANCEL] extinguish");
                }
            }
        } else if (motionEvent.getActionMasked() == 0 && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "SharedSpotLightEffect-->[ACTION_DOWN] touch ignored action=DOWN (bounds zero)");
        }
        return Unit.INSTANCE;
    }
}
