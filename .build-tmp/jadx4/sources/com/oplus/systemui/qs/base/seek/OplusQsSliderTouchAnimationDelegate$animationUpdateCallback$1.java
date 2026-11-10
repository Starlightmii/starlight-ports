package com.oplus.systemui.qs.base.seek;

import android.util.Log;
import androidx.car.app.serialization.Bundler$;
import androidx.compose.animation.core.MonoSpline$;
import com.coui.appcompat.animation.dynamicanimation.COUIComposeMotionAnimator;
import com.coui.appcompat.animation.touchanimation.COUIIComposeMotionUnit;
import com.coui.appcompat.animation.touchanimation.COUITouchMotionAnimator;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsSliderTouchAnimationDelegate$animationUpdateCallback$1 implements COUIComposeMotionAnimator.OnAnimationUpdateCallback {
    public /* synthetic */ OplusQsSliderTouchAnimationDelegate this$0;

    public final void onAnimationUpdate(COUIIComposeMotionUnit.ContributeSink contributeSink) {
        OplusQsSliderTouchAnimationDelegate oplusQsSliderTouchAnimationDelegate = this.this$0;
        if (LogUtil.getNormalDebug()) {
            String str = oplusQsSliderTouchAnimationDelegate.tag;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, LogUtil.SEPARATOR);
                COUITouchMotionAnimator.TouchMotionCollector touchMotionCollector = (COUITouchMotionAnimator.TouchMotionCollector) contributeSink;
                float translateX = touchMotionCollector.getTranslateX();
                float translateY = touchMotionCollector.getTranslateY();
                float scaleX = touchMotionCollector.getScaleX();
                float scaleY = touchMotionCollector.getScaleY();
                StringBuilder sbM2 = MonoSpline$.ExternalSyntheticOutline0.m("OnAnimationUpdateCallback: translationX=", translateX, ", translationY= ", translateY, ", scaleX=");
                sbM2.append(scaleX);
                sbM2.append(", scaleY=");
                sbM2.append(scaleY);
                sbM.append(sbM2.toString());
                Log.d("SystemUi--QuickSettings", sbM.toString());
            }
        }
        Function3 touchAnimationCallback = oplusQsSliderTouchAnimationDelegate.getTouchAnimationCallback();
        if (touchAnimationCallback != null) {
            COUITouchMotionAnimator.TouchMotionCollector touchMotionCollector2 = (COUITouchMotionAnimator.TouchMotionCollector) contributeSink;
            touchAnimationCallback.invoke(Float.valueOf(touchMotionCollector2.getTranslateY()), Float.valueOf(touchMotionCollector2.getScaleX()), Float.valueOf(touchMotionCollector2.getScaleY()));
        }
    }
}
