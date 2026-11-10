package com.oplus.systemui.plugins.qs.animator;

import android.view.Choreographer;
import androidx.compose.animation.core.MonoSpline$;
import androidx.core.view.insets.Protection$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSPanelExpandFraction {
    public static final Companion Companion = new Companion();
    private static final String TAG = "QSPanelExpandFraction";
    public static boolean screenShotClick;
    public boolean beingSkipToEnd;
    private final Lazy blurFractionAnimation$delegate;
    public float blurRawFraction;
    public float curCalcFinalPosition;
    private final Function0 defaultSettingEngine;
    private final Set<ElementVisibleChangeListener> elementVisibleChangeListeners;
    private final Lazy fractionAnimation$delegate;
    private final Set<ExpandedFractionChangeListener> fractionChangeListeners;
    public boolean isFirstElementVisible;
    public boolean isFixedElementVisible;
    public boolean isOnFling;
    public boolean isSecondElementVisible;
    public final float maxFastBlurResponse;
    public final float maxFastResponse;
    public boolean panelClosedOnDown;
    public float rawFraction;
    private final OplusSeparateQSPlugin.IStub stub;
    public boolean suppressBlurFractionNotify;
    public boolean toExpand;
    public float yVelocity;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        /* JADX INFO: Access modifiers changed from: private */
        public final float getRawHideResponse() {
            return getScreenShotClick() ? 0.2f : 0.3f;
        }

        public final boolean getScreenShotClick() {
            return QSPanelExpandFraction.screenShotClick;
        }

        public final void setScreenShotClick(boolean z) {
            QSPanelExpandFraction.screenShotClick = z;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface ElementEndListener {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface ElementVisibleChangeListener {
        void onFirstElementVisibleChanged(boolean z, boolean z2);

        void onFixedElementVisibleChanged(boolean z, boolean z2);

        void onSecondElementVisibleChanged(boolean z, boolean z2);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface ExpandedFractionChangeListener {
        void onBlurFractionChange();

        void onBlurFractionEnd(boolean z);

        void onFractionChanged(float f);

        void onFractionEnd(boolean z);
    }

    public QSPanelExpandFraction(OplusSeparateQSPlugin.IStub iStub, Function0 function0) {
        this.stub = iStub;
        this.defaultSettingEngine = function0;
        QSPanelExpandFraction$$ExternalSyntheticLambda1 qSPanelExpandFraction$$ExternalSyntheticLambda1 = new QSPanelExpandFraction$$ExternalSyntheticLambda1(0);
        qSPanelExpandFraction$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.fractionAnimation$delegate = LazyKt__LazyJVMKt.lazy(qSPanelExpandFraction$$ExternalSyntheticLambda1);
        QSPanelExpandFraction$$ExternalSyntheticLambda1 qSPanelExpandFraction$$ExternalSyntheticLambda2 = new QSPanelExpandFraction$$ExternalSyntheticLambda1(1);
        qSPanelExpandFraction$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        this.blurFractionAnimation$delegate = LazyKt__LazyJVMKt.lazy(qSPanelExpandFraction$$ExternalSyntheticLambda2);
        this.panelClosedOnDown = true;
        this.elementVisibleChangeListeners = new LinkedHashSet();
        this.fractionChangeListeners = new LinkedHashSet();
        this.maxFastResponse = 0.35f;
        this.maxFastBlurResponse = 0.45f;
    }

    public static final void access$skipToEndForBlurAnimation(QSPanelExpandFraction qSPanelExpandFraction, boolean z) {
        if (!z) {
            qSPanelExpandFraction.setBlurRawFraction(0.0f);
        }
        if (((COUIDynamicAnimation) qSPanelExpandFraction.getBlurFractionAnimation()).mRunning && qSPanelExpandFraction.getBlurFractionAnimation().canSkipToEnd()) {
            qSPanelExpandFraction.getBlurFractionAnimation().skipToEnd();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final COUISpringAnimation getBlurFractionAnimation() {
        return (COUISpringAnimation) this.blurFractionAnimation$delegate.getValue();
    }

    private final COUISpringAnimation getFractionAnimation() {
        return (COUISpringAnimation) this.fractionAnimation$delegate.getValue();
    }

    public static void resetTo(COUISpringAnimation cOUISpringAnimation, float f) {
        cOUISpringAnimation.setStartValue(f);
        cOUISpringAnimation.animateToFinalPosition(f);
        cOUISpringAnimation.skipToEnd();
        cOUISpringAnimation.doAnimationFrame((Choreographer.getInstance().getLastFrameTimeNanos() == Long.MIN_VALUE ? Choreographer.getInstance().getLastFrameTimeNanos() : System.nanoTime()) / 1000000);
        cOUISpringAnimation.doAnimationFrame(0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setBlurRawFraction(float f) {
        if (this.blurRawFraction == f) {
            return;
        }
        this.blurRawFraction = f;
        if (this.suppressBlurFractionNotify || ((COUIDynamicAnimation) getFractionAnimation()).mRunning) {
            return;
        }
        synchronized (this.fractionChangeListeners) {
            for (ExpandedFractionChangeListener expandedFractionChangeListener : this.fractionChangeListeners) {
                getBlurFraction();
                expandedFractionChangeListener.onBlurFractionChange();
            }
        }
    }

    private final void setFirstElementVisible(boolean z) {
        if (this.isFirstElementVisible != z) {
            float f = this.yVelocity;
            float rawFraction = getRawFraction();
            StringBuilder sbM = NotificationPanelViewController$.ExternalSyntheticOutline0.m("[setIsFirstElementVisible] ", z, " yVelocity=", f, " rawFraction=");
            sbM.append(rawFraction);
            LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
            this.isFirstElementVisible = z;
            synchronized (this.elementVisibleChangeListeners) {
                Iterator<T> it = this.elementVisibleChangeListeners.iterator();
                while (it.hasNext()) {
                    ((ElementVisibleChangeListener) it.next()).onFirstElementVisibleChanged(z, this.beingSkipToEnd);
                }
            }
        }
    }

    private final void setFixedElementVisible(boolean z) {
        if (this.isFixedElementVisible != z) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("[setIsFixedElementVisible] ", z), null, 8);
            this.isFixedElementVisible = z;
            synchronized (this.elementVisibleChangeListeners) {
                Iterator<T> it = this.elementVisibleChangeListeners.iterator();
                while (it.hasNext()) {
                    ((ElementVisibleChangeListener) it.next()).onFixedElementVisibleChanged(z, this.beingSkipToEnd);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRawFraction(float f) {
        float fCoerceIn = RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f);
        if (this.rawFraction == fCoerceIn) {
            return;
        }
        this.rawFraction = fCoerceIn;
        onFractionChanged(fCoerceIn);
        QSPanelExpandFraction$$ExternalSyntheticLambda0 qSPanelExpandFraction$$ExternalSyntheticLambda0 = new QSPanelExpandFraction$$ExternalSyntheticLambda0(0);
        qSPanelExpandFraction$$ExternalSyntheticLambda0.f$0 = fCoerceIn;
        VarHandle.storeStoreFence();
        notifyFractionChangeCallback(qSPanelExpandFraction$$ExternalSyntheticLambda0);
    }

    private final void setSecondElementVisible(boolean z) {
        if (this.isSecondElementVisible != z) {
            float f = this.yVelocity;
            float rawFraction = getRawFraction();
            float f2 = this.curCalcFinalPosition;
            StringBuilder sbM = NotificationPanelViewController$.ExternalSyntheticOutline0.m("[setIsSecondElementVisible] ", z, " yVelocity=", f, " rawFraction=");
            sbM.append(rawFraction);
            sbM.append(" curCalculateFinalPosition=");
            sbM.append(f2);
            LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
            this.isSecondElementVisible = z;
            synchronized (this.elementVisibleChangeListeners) {
                Iterator<T> it = this.elementVisibleChangeListeners.iterator();
                while (it.hasNext()) {
                    ((ElementVisibleChangeListener) it.next()).onSecondElementVisibleChanged(z, this.beingSkipToEnd);
                }
            }
        }
    }

    public final void addCallback(ExpandedFractionChangeListener expandedFractionChangeListener) {
        synchronized (this.fractionChangeListeners) {
            this.fractionChangeListeners.add(expandedFractionChangeListener);
        }
        expandedFractionChangeListener.onFractionChanged(getRawFraction());
    }

    public final void cancel() {
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "[cancel]", null, 8);
        getBlurFractionAnimation().cancelComplete();
        getFractionAnimation().cancelComplete();
    }

    public final float getBlurFraction() {
        float fCoerceIn = RangesKt___RangesKt.coerceIn(this.blurRawFraction / 0.86f, 0.0f, 1.0f);
        if (this.stub.getUseMotionBlur() || fCoerceIn >= 0.02f) {
            return fCoerceIn;
        }
        return 0.0f;
    }

    public final float getGetCurCalculateFinalPosition() {
        return this.curCalcFinalPosition;
    }

    public final float getRawFraction() {
        return RangesKt___RangesKt.coerceIn(this.rawFraction, 0.0f, 1.0f);
    }

    public final boolean isBlurFractionAnimationRunning() {
        return ((COUIDynamicAnimation) getBlurFractionAnimation()).mRunning;
    }

    public final boolean isFractionAnimationRunning() {
        return ((COUIDynamicAnimation) getFractionAnimation()).mRunning;
    }

    public final void notifyFractionChangeCallback(Function1 function1) {
        synchronized (this.fractionChangeListeners) {
            Iterator<T> it = this.fractionChangeListeners.iterator();
            while (it.hasNext()) {
                function1.invoke((ExpandedFractionChangeListener) it.next());
            }
        }
    }

    public final void onFling(float f, boolean z) {
        this.isOnFling = true;
        this.toExpand = z;
        if (f > 0.0f) {
            this.yVelocity = f;
        }
        float rawHideResponse = z ? 0.2f : Companion.getRawHideResponse();
        float f2 = z ? 1.0f : 0.0f;
        float f3 = 0.35f;
        if (!z) {
            this.panelClosedOnDown = false;
            if (!this.stub.getUseMotionBlur()) {
                f3 = 0.45f;
            }
        }
        if (z && this.panelClosedOnDown && this.yVelocity > 1000.0f) {
            rawHideResponse = this.maxFastBlurResponse;
            f3 = rawHideResponse;
        }
        AnimatorUtils.tryUpdateStiffness(getBlurFractionAnimation(), AnimatorUtils.responseToStiffness(f3));
        AnimatorUtils.tryUpdateStiffness(getFractionAnimation(), AnimatorUtils.responseToStiffness(rawHideResponse));
        tryAnimateToFinalPosition(f2);
        this.curCalcFinalPosition = f2;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0033  */
    /* JADX WARN: Code duplicated, block: B:19:0x0035  */
    public final void onFractionChanged(float f) {
        boolean z;
        boolean z2 = this.yVelocity > 1000.0f && this.curCalcFinalPosition >= 0.86f;
        setFixedElementVisible(f > 0.05f);
        if (this.panelClosedOnDown) {
            if (f >= (z2 ? 0.2f : 0.5f)) {
                z = true;
            } else {
                z = false;
            }
        } else {
            if (f >= (z2 ? 0.2f : 0.5f)) {
                z = true;
            } else {
                z = false;
            }
        }
        setFirstElementVisible(z);
        setSecondElementVisible(f >= (z2 ? 0.2f : 0.86f));
    }

    public final void onMove(float f, float f2, boolean z) {
        float fM;
        this.panelClosedOnDown = z;
        this.yVelocity = f2;
        boolean z2 = false;
        this.toExpand = false;
        this.isOnFling = false;
        float fCoerceIn = RangesKt___RangesKt.coerceIn(f / ((Number) this.defaultSettingEngine.invoke()).floatValue(), 1.0E-5f, 1.0f);
        if (z && this.yVelocity > 1000.0f) {
            z2 = true;
        }
        if (z2) {
            float fCoerceIn2 = RangesKt___RangesKt.coerceIn(getRawFraction() / 0.2f, 0.0f, 1.0f);
            float f3 = this.maxFastResponse;
            fM = MonoSpline$.ExternalSyntheticOutline0.m(f3, 0.2f, fCoerceIn2, f3);
        } else {
            fM = 0.2f;
        }
        AnimatorUtils.tryUpdateStiffness(getFractionAnimation(), AnimatorUtils.responseToStiffness(fM));
        float fM2 = 0.35f;
        if (z2) {
            float fCoerceIn3 = RangesKt___RangesKt.coerceIn(this.blurRawFraction / 0.2f, 0.0f, 1.0f);
            float f4 = this.maxFastBlurResponse;
            fM2 = MonoSpline$.ExternalSyntheticOutline0.m(f4, 0.35f, fCoerceIn3, f4);
        } else if (fCoerceIn == 1.0E-5f && getRawFraction() < 0.015f) {
            fM2 = 0.1f;
        }
        AnimatorUtils.tryUpdateStiffness(getBlurFractionAnimation(), AnimatorUtils.responseToStiffness(fM2));
        tryAnimateToFinalPosition(fCoerceIn);
        this.curCalcFinalPosition = fCoerceIn;
    }

    public final void removeCallback(ExpandedFractionChangeListener expandedFractionChangeListener) {
        synchronized (this.fractionChangeListeners) {
            this.fractionChangeListeners.remove(expandedFractionChangeListener);
        }
    }

    public final void setAnimationStartValue(float f) {
        setRawFraction(f);
        setBlurRawFraction(f);
        getFractionAnimation().setStartValue(f);
        getBlurFractionAnimation().setStartValue(f);
        onFractionChanged(f);
        QSPanelExpandFraction$$ExternalSyntheticLambda0 qSPanelExpandFraction$$ExternalSyntheticLambda0 = new QSPanelExpandFraction$$ExternalSyntheticLambda0(1);
        qSPanelExpandFraction$$ExternalSyntheticLambda0.f$0 = f;
        VarHandle.storeStoreFence();
        notifyFractionChangeCallback(qSPanelExpandFraction$$ExternalSyntheticLambda0);
    }

    public final void skipToEnd(boolean z) {
        float f = z ? 1.0f : 0.0f;
        float f2 = this.blurRawFraction;
        if (f != f2) {
            LogUtil.i$default(LogUtil.TAG_QS, TAG, Protection$.ExternalSyntheticOutline0.m(f2, "[skipToEnd] blurFractionAnimation skipToEnd blurRawFraction="), null, 8);
            this.stub.cancelBrightnessMirrorFadeAnimation();
            getBlurFractionAnimation().animateToFinalPosition(f);
            if (getBlurFractionAnimation().canSkipToEnd()) {
                resetTo(getBlurFractionAnimation(), f);
            }
        }
        if (f == getRawFraction()) {
            return;
        }
        this.beingSkipToEnd = true;
        LogUtil.i$default(LogUtil.TAG_QS, TAG, "[skipToEnd] fractionAnimation skipToEnd", null, 8);
        getFractionAnimation().animateToFinalPosition(f);
        if (getFractionAnimation().canSkipToEnd()) {
            resetTo(getFractionAnimation(), f);
        }
    }

    public final boolean syncBlurFractionFromExternal(float f) {
        if (((COUIDynamicAnimation) getBlurFractionAnimation()).mRunning) {
            return false;
        }
        float fCoerceIn = RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f) * 0.86f;
        if (this.blurRawFraction == fCoerceIn) {
            return true;
        }
        this.suppressBlurFractionNotify = true;
        setBlurRawFraction(fCoerceIn);
        this.suppressBlurFractionNotify = false;
        getBlurFractionAnimation().setStartValue(this.blurRawFraction);
        return true;
    }

    public final void tryAnimateToFinalPosition(float f) {
        if (f != getRawFraction() || ((COUIDynamicAnimation) getFractionAnimation()).mRunning) {
            getFractionAnimation().animateToFinalPosition(f);
        }
        if (f != this.blurRawFraction || ((COUIDynamicAnimation) getBlurFractionAnimation()).mRunning) {
            this.stub.cancelBrightnessMirrorFadeAnimation();
            getBlurFractionAnimation().animateToFinalPosition(f);
        }
    }

    public final void removeCallback(ElementVisibleChangeListener elementVisibleChangeListener) {
        synchronized (this.elementVisibleChangeListeners) {
            this.elementVisibleChangeListeners.remove(elementVisibleChangeListener);
        }
    }

    public final void addCallback(ElementVisibleChangeListener elementVisibleChangeListener) {
        synchronized (this.elementVisibleChangeListeners) {
            this.elementVisibleChangeListeners.add(elementVisibleChangeListener);
        }
    }
}
