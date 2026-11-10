package com.oplus.systemui.plugins.qs.animator;

import android.support.v4.content.FileProvider$;
import android.util.Log;
import android.view.View;
import androidx.car.app.serialization.Bundler$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.app.displaylib.PerDisplayInstanceRepositoryImpl$;
import com.android.mechanics.effects.VerticalTactileSurfaceRevealEffect$;
import com.android.settingslib.volume.MediaSessions$MediaControllerRecord$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSElementAnimator {
    public static final Companion Companion = new Companion();
    private static final String TAG = "QSElementAnimator";
    private final Lazy alphaAnimation$delegate;
    private Function1 alphaUpdateProxy;
    private final View hostView;
    private final Lazy negativeOffsetYAnimation$delegate;
    private Function0 negativeOffsetYEndListener;
    private Function1 negativeOffsetYUpdateProxy;
    private final Lazy scaleAnimation$delegate;
    private Function1 scaleUpdateProxy;
    private final Lazy updateAnimation$delegate;
    private Function1 updateAnimationEndListener;
    private Function2 updateProxy;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final String simpleNameAndHash(Object obj) {
            return PerDisplayInstanceRepositoryImpl$.ExternalSyntheticOutline0.m(obj.hashCode(), obj.getClass().getSimpleName(), "@");
        }
    }

    public QSElementAnimator(View view) {
        this.hostView = view;
        QSElementAnimator$$ExternalSyntheticLambda0 qSElementAnimator$$ExternalSyntheticLambda0 = new QSElementAnimator$$ExternalSyntheticLambda0(0);
        qSElementAnimator$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.alphaAnimation$delegate = LazyKt__LazyJVMKt.lazy(qSElementAnimator$$ExternalSyntheticLambda0);
        QSElementAnimator$$ExternalSyntheticLambda0 qSElementAnimator$$ExternalSyntheticLambda1 = new QSElementAnimator$$ExternalSyntheticLambda0(1);
        qSElementAnimator$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.scaleAnimation$delegate = LazyKt__LazyJVMKt.lazy(qSElementAnimator$$ExternalSyntheticLambda1);
        QSElementAnimator$$ExternalSyntheticLambda0 qSElementAnimator$$ExternalSyntheticLambda2 = new QSElementAnimator$$ExternalSyntheticLambda0(2);
        qSElementAnimator$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.negativeOffsetYAnimation$delegate = LazyKt__LazyJVMKt.lazy(qSElementAnimator$$ExternalSyntheticLambda2);
        QSElementAnimator$$ExternalSyntheticLambda0 qSElementAnimator$$ExternalSyntheticLambda3 = new QSElementAnimator$$ExternalSyntheticLambda0(3);
        qSElementAnimator$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        this.updateAnimation$delegate = LazyKt__LazyJVMKt.lazy(qSElementAnimator$$ExternalSyntheticLambda3);
    }

    private final COUISpringAnimation getAlphaAnimation() {
        return (COUISpringAnimation) this.alphaAnimation$delegate.getValue();
    }

    private final COUISpringAnimation getNegativeOffsetYAnimation() {
        return (COUISpringAnimation) this.negativeOffsetYAnimation$delegate.getValue();
    }

    private final COUISpringAnimation getScaleAnimation() {
        return (COUISpringAnimation) this.scaleAnimation$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getTag() {
        return FileProvider$.ExternalSyntheticOutline0.m("QSElementAnimator-", Companion.simpleNameAndHash(this.hostView));
    }

    private final COUISpringAnimation getUpdateAnimation() {
        return (COUISpringAnimation) this.updateAnimation$delegate.getValue();
    }

    public final void alphaTo(boolean z, boolean z2, float f, float f2, Function1 function1) {
        String tag = getTag();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(tag, LogUtil.SEPARATOR);
            StringBuilder sbM2 = TextFieldImplKt$.ExternalSyntheticOutline0.m("[alphaTo] toShow=", ", withAnimation=", " response=", z, z2);
            sbM2.append(f);
            sbM2.append(" bounce=");
            sbM2.append(f2);
            sbM.append(sbM2.toString());
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        float f3 = z ? 1.0f : 0.0f;
        if (!z2) {
            getUpdateAnimation().cancelComplete();
            getAlphaAnimation().cancelComplete();
            if (function1 != null) {
                function1.invoke(Float.valueOf(f3));
                return;
            }
            Companion companion = Companion;
            View view = this.hostView;
            companion.getClass();
            view.setTransitionAlpha(f3);
            return;
        }
        if (((COUIDynamicAnimation) getAlphaAnimation()).mRunning || this.hostView.getTransitionAlpha() != f3) {
            this.alphaUpdateProxy = function1;
            if (!((COUIDynamicAnimation) getAlphaAnimation()).mRunning) {
                getAlphaAnimation().setStartValue(this.hostView.getTransitionAlpha());
            }
            AnimatorUtils.tryUpdateStiffness(getAlphaAnimation(), AnimatorUtils.responseToStiffness(f));
            getAlphaAnimation().getSpring().setBounce(f2);
            getAlphaAnimation().animateToFinalPosition(f3);
            getUpdateAnimation().cancelComplete();
            return;
        }
        String tag2 = getTag();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM3 = Bundler$.ExternalSyntheticOutline0.m(tag2, LogUtil.SEPARATOR);
            sbM3.append("[alphaTo] toShow=" + z + " currentAlpha equals endValue, return");
            Log.d("SystemUi--QuickSettings", sbM3.toString());
        }
        getUpdateAnimation().cancelComplete();
    }

    public final void animateTo(boolean z, boolean z2, Function2 function2, Function0 function0, float f, float f2, float f3, Function1 function1) {
        float f4;
        String tag = getTag();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(tag, LogUtil.SEPARATOR);
            StringBuilder sbM2 = TextFieldImplKt$.ExternalSyntheticOutline0.m("[animateTo] toShow=", " withAnimation=", " response=", z, z2);
            f4 = f2;
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM2, f4, " bounce=", f3, " hideEndValue=");
            sbM2.append(f);
            sbM.append(sbM2.toString());
            Log.d("SystemUi--QuickSettings", sbM.toString());
        } else {
            f4 = f2;
        }
        if (!z2) {
            getAlphaAnimation().cancelComplete();
            getScaleAnimation().cancelComplete();
            getUpdateAnimation().cancelComplete();
            float f5 = z ? 1.0f : 0.8f;
            float f6 = z ? 1.0f : 0.0f;
            if (function2 != null) {
                function2.invoke(Float.valueOf(f6), Float.valueOf(f5));
                return;
            }
            Companion companion = Companion;
            View view = this.hostView;
            companion.getClass();
            view.setScaleX(f5);
            view.setScaleY(f5);
            view.setTransitionAlpha(f6);
            return;
        }
        float f7 = z ? 1.0f : f;
        float fFloatValue = function0 != null ? ((Number) function0.invoke()).floatValue() : this.hostView.getTransitionAlpha();
        if (((COUIDynamicAnimation) getUpdateAnimation()).mRunning || fFloatValue != f7) {
            this.updateProxy = function2;
            this.updateAnimationEndListener = function1;
            if (!((COUIDynamicAnimation) getUpdateAnimation()).mRunning) {
                getUpdateAnimation().setStartValue(fFloatValue);
            }
            AnimatorUtils.tryUpdateStiffness(getUpdateAnimation(), AnimatorUtils.responseToStiffness(f4));
            getUpdateAnimation().setMinimumVisibleChange(f > 0.0f ? 3.0E-4f : 0.00390625f);
            getUpdateAnimation().getSpring().setBounce(f3);
            getUpdateAnimation().animateToFinalPosition(f7);
            getAlphaAnimation().cancelComplete();
            getScaleAnimation().cancelComplete();
            return;
        }
        String tag2 = getTag();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM3 = Bundler$.ExternalSyntheticOutline0.m(tag2, LogUtil.SEPARATOR);
            sbM3.append("[animateTo] toShow=" + z + " currentAlpha equals endValue, return");
            Log.d("SystemUi--QuickSettings", sbM3.toString());
        }
        getAlphaAnimation().cancelComplete();
        getScaleAnimation().cancelComplete();
    }

    public final void animateToWithProxyLogic(boolean z, boolean z2, Function2 function2, Function0 function0, float f, float f2, float f3, Function1 function1) {
        animateTo(z, z2, function2, function0, f, f2, f3, function1);
    }

    public final void cancelAll() {
        String tag = getTag();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            MediaSessions$MediaControllerRecord$.ExternalSyntheticOutline0.m(tag, "-->[cancelAll]", "SystemUi--QuickSettings");
        }
        getAlphaAnimation().cancelComplete();
        getScaleAnimation().cancelComplete();
        getUpdateAnimation().cancelComplete();
        getNegativeOffsetYAnimation().cancelComplete();
        this.updateAnimationEndListener = null;
        this.negativeOffsetYEndListener = null;
    }

    public final boolean isRunning() {
        return ((COUIDynamicAnimation) getAlphaAnimation()).mRunning || ((COUIDynamicAnimation) getScaleAnimation()).mRunning || ((COUIDynamicAnimation) getUpdateAnimation()).mRunning || ((COUIDynamicAnimation) getNegativeOffsetYAnimation()).mRunning;
    }

    public final void negativeOffsetYTo(boolean z, boolean z2, float f, float f2, Function0 function0, Function1 function1) {
        float f3 = z ? 1.0f : 0.0f;
        float f4 = z ? 0.0f : 1.0f;
        float finalPosition = getNegativeOffsetYAnimation().getSpring().getFinalPosition();
        boolean z3 = ((COUIDynamicAnimation) getNegativeOffsetYAnimation()).mRunning;
        String tag = getTag();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(tag, LogUtil.SEPARATOR);
            StringBuilder sbM2 = TextFieldImplKt$.ExternalSyntheticOutline0.m("[negativeOffsetYTo] toShow=", " withAnimation=", " endNeg=", z, z2);
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM2, f3, " startNeg=", f4, " response=");
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM2, f, " bounce=", f2, " isRunning=");
            sbM2.append(z3);
            sbM2.append(" finalPosition=");
            sbM2.append(finalPosition);
            sbM.append(sbM2.toString());
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        if (!z2) {
            getNegativeOffsetYAnimation().cancelComplete();
            if (function1 != null) {
                function1.invoke(Float.valueOf(f3));
                return;
            }
            return;
        }
        this.negativeOffsetYEndListener = function0;
        this.negativeOffsetYUpdateProxy = function1;
        if (!z3) {
            getNegativeOffsetYAnimation().setStartValue(f4);
        }
        AnimatorUtils.tryUpdateStiffness(getNegativeOffsetYAnimation(), AnimatorUtils.responseToStiffness(f));
        getNegativeOffsetYAnimation().getSpring().setBounce(f2);
        getNegativeOffsetYAnimation().animateToFinalPosition(f3);
    }

    public final void scaleTo(boolean z, boolean z2, float f, float f2, float f3, Function1 function1) {
        float f4;
        float f5 = f;
        String tag = getTag();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(tag, LogUtil.SEPARATOR);
            float scaleX = this.hostView.getScaleX();
            boolean z3 = ((COUIDynamicAnimation) getScaleAnimation()).mRunning;
            StringBuilder sbM2 = TextFieldImplKt$.ExternalSyntheticOutline0.m("[scaleTo] toShow=", " withAnimation=", " startValue=", z, z2);
            f4 = f2;
            VerticalTactileSurfaceRevealEffect$.ExternalSyntheticOutline0.m(sbM2, f5, " response=", f4, " scaleX=");
            sbM2.append(scaleX);
            sbM2.append(" isRunning=");
            sbM2.append(z3);
            sbM.append(sbM2.toString());
            Log.d("SystemUi--QuickSettings", sbM.toString());
        } else {
            f4 = f2;
        }
        if (!z2) {
            getScaleAnimation().cancelComplete();
            getUpdateAnimation().cancelComplete();
            if (z) {
                f5 = 1.0f;
            }
            if (function1 != null) {
                function1.invoke(Float.valueOf(f5));
                return;
            }
            Companion companion = Companion;
            View view = this.hostView;
            companion.getClass();
            view.setScaleX(f5);
            view.setScaleY(f5);
            return;
        }
        float f6 = z ? 1.0f : f5;
        if (((COUIDynamicAnimation) getScaleAnimation()).mRunning || this.hostView.getScaleX() != f6) {
            this.scaleUpdateProxy = function1;
            if (!((COUIDynamicAnimation) getScaleAnimation()).mRunning) {
                getScaleAnimation().setStartValue(RangesKt___RangesKt.coerceIn(this.hostView.getScaleX(), f5, 1.0f));
            }
            AnimatorUtils.tryUpdateStiffness(getScaleAnimation(), AnimatorUtils.responseToStiffness(f4));
            getScaleAnimation().getSpring().setBounce(f3);
            getScaleAnimation().animateToFinalPosition(f6);
            getUpdateAnimation().cancelComplete();
            return;
        }
        String tag2 = getTag();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM3 = Bundler$.ExternalSyntheticOutline0.m(tag2, LogUtil.SEPARATOR);
            sbM3.append("[scaleTo] toShow=" + z + " currentScale equals endValue, return");
            Log.d("SystemUi--QuickSettings", sbM3.toString());
        }
        getUpdateAnimation().cancelComplete();
    }

    public String toString() {
        return getTag() + ", isRunning=" + isRunning();
    }
}
