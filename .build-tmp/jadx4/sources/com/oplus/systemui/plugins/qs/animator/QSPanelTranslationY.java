package com.oplus.systemui.plugins.qs.animator;

import android.view.View;
import androidx.compose.animation.core.MonoSpline$;
import androidx.core.view.insets.Protection$;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import com.android.internal.dynamicanimation.animation.DynamicAnimation;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.oplus.systemui.plugins.qs.CellAreaConfig$$ExternalSyntheticOutline0;
import com.oplus.systemui.plugins.qs.OplusQSSpecialModeProvider;
import com.oplus.systemui.plugins.qs.seamless.SeparateQSFakeStatusController;
import com.oplus.systemui.plugins.qs.seamless.SeparateQSFakeStatusController$qsPanelTranslationYListener$1;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QSPanelTranslationY {
    public static final Companion Companion = new Companion();
    private static final String TAG = "QSPanelTranslationY";
    public float destTranslationY;
    public boolean isOnFling;
    public float lastMoveResponse;
    public final float maxResponse;
    public boolean panelClosedOnDown;
    private final View qsPanelView;
    private final Lazy translationYAnimation$delegate;
    public float yVelocity;
    private final Set<PanelTranslationYChangeListener> callbacks = new LinkedHashSet();
    public float translationYValue = Float.MIN_VALUE;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface PanelTranslationYChangeListener {
        public static final Companion Companion = Companion.$$INSTANCE;
    }

    public static COUISpringAnimation $r8$lambda$D98CiKZHSi11hbT1wym3vMffrC0(QSPanelTranslationY qSPanelTranslationY) {
        COUISpringAnimation cOUISpringAnimation = new COUISpringAnimation(FloatPropertyCompat.createFloatPropertyCompat(DynamicAnimation.TRANSLATION_Y), qSPanelTranslationY.qsPanelView);
        cOUISpringAnimation.setSpring(new COUISpringForce().setDampingRatio(1.0f));
        cOUISpringAnimation.getSpring().setStiffness(AnimatorUtils.responseToStiffness(0.35f));
        cOUISpringAnimation.getSpring().setBounce(0.15f);
        cOUISpringAnimation.setMinimumVisibleChange(1.0f);
        Ref.FloatRef floatRef = new Ref.FloatRef();
        floatRef.element = qSPanelTranslationY.maxResponse;
        QSPanelTranslationY$translationYAnimation$2$1$1 qSPanelTranslationY$translationYAnimation$2$1$1 = new QSPanelTranslationY$translationYAnimation$2$1$1();
        qSPanelTranslationY$translationYAnimation$2$1$1.this$0 = qSPanelTranslationY;
        qSPanelTranslationY$translationYAnimation$2$1$1.$this_apply = cOUISpringAnimation;
        qSPanelTranslationY$translationYAnimation$2$1$1.$lastResponse = floatRef;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addUpdateListener(qSPanelTranslationY$translationYAnimation$2$1$1);
        QSPanelTranslationY$translationYAnimation$2$1$2 qSPanelTranslationY$translationYAnimation$2$1$2 = new QSPanelTranslationY$translationYAnimation$2$1$2();
        qSPanelTranslationY$translationYAnimation$2$1$2.this$0 = qSPanelTranslationY;
        VarHandle.storeStoreFence();
        cOUISpringAnimation.addEndListener(qSPanelTranslationY$translationYAnimation$2$1$2);
        return cOUISpringAnimation;
    }

    public QSPanelTranslationY(View view) {
        this.qsPanelView = view;
        QSPanelTranslationY$$ExternalSyntheticLambda0 qSPanelTranslationY$$ExternalSyntheticLambda0 = new QSPanelTranslationY$$ExternalSyntheticLambda0();
        qSPanelTranslationY$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.translationYAnimation$delegate = LazyKt__LazyJVMKt.lazy(qSPanelTranslationY$$ExternalSyntheticLambda0);
        boolean zIsLowGaussianLevel = ScrimUtil.isLowGaussianLevel(view.getContext());
        this.destTranslationY = 102.0f;
        this.panelClosedOnDown = true;
        this.maxResponse = zIsLowGaussianLevel ? 0.65f : 0.45f;
        if (view.isAttachedToWindow()) {
            trySetCurrValue(-this.destTranslationY);
            if (view.isAttachedToWindow()) {
                view.addOnAttachStateChangeListener(new QSPanelTranslationY$special$$inlined$doOnAttach$1(view, this));
                return;
            } else {
                getTranslationYAnimation().cancelComplete();
                return;
            }
        }
        QSPanelTranslationY$special$$inlined$doOnAttach$1 qSPanelTranslationY$special$$inlined$doOnAttach$1 = new QSPanelTranslationY$special$$inlined$doOnAttach$1();
        qSPanelTranslationY$special$$inlined$doOnAttach$1.$this_doOnAttach = view;
        qSPanelTranslationY$special$$inlined$doOnAttach$1.this$0 = this;
        VarHandle.storeStoreFence();
        view.addOnAttachStateChangeListener(qSPanelTranslationY$special$$inlined$doOnAttach$1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final COUISpringAnimation getTranslationYAnimation() {
        return (COUISpringAnimation) this.translationYAnimation$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTranslationYValue(float f) {
        if (this.translationYValue == f) {
            return;
        }
        synchronized (this.callbacks) {
            Iterator<T> it = this.callbacks.iterator();
            while (it.hasNext()) {
                SeparateQSFakeStatusController separateQSFakeStatusController = ((SeparateQSFakeStatusController$qsPanelTranslationYListener$1) ((PanelTranslationYChangeListener) it.next())).this$0;
                separateQSFakeStatusController.qSPanelViewTranslationY = f;
                separateQSFakeStatusController.updateTranslationY$1(separateQSFakeStatusController.separateQsFakeLayout.getTranslationY());
            }
        }
        this.translationYValue = f;
    }

    public final void addCallback(PanelTranslationYChangeListener panelTranslationYChangeListener) {
        synchronized (this.callbacks) {
            this.callbacks.add(panelTranslationYChangeListener);
        }
        float f = this.translationYValue;
        if (f == Float.MIN_VALUE) {
            return;
        }
        SeparateQSFakeStatusController separateQSFakeStatusController = ((SeparateQSFakeStatusController$qsPanelTranslationYListener$1) panelTranslationYChangeListener).this$0;
        separateQSFakeStatusController.qSPanelViewTranslationY = f;
        separateQSFakeStatusController.updateTranslationY$1(separateQSFakeStatusController.separateQsFakeLayout.getTranslationY());
    }

    public final float getDefaultSettingEngine() {
        OplusQSSpecialModeProvider.Companion companion = OplusQSSpecialModeProvider.Companion;
        float f = 102.0f;
        if (!((Boolean) CellAreaConfig$$ExternalSyntheticOutline0.m(companion)).booleanValue() && !companion.getInstance().isLargeFoldDeviceUnFoldingCache) {
            float f2 = this.destTranslationY;
            if (0.0f <= f2) {
                f = f2;
            }
        }
        return f / 0.86f;
    }

    public final float getDestTranslationY() {
        return this.destTranslationY;
    }

    public final void onFling(float f, boolean z) {
        this.isOnFling = true;
        if (!z) {
            this.panelClosedOnDown = false;
        }
        if (f > 0.0f) {
            this.yVelocity = f;
        }
        float f2 = z ? 0.0f : -this.destTranslationY;
        getTranslationYAnimation().getSpring().setStiffness(AnimatorUtils.responseToStiffness((!this.panelClosedOnDown || f <= 1000.0f) ? 0.35f : this.maxResponse));
        if (f2 != this.translationYValue || ((COUIDynamicAnimation) getTranslationYAnimation()).mRunning) {
            getTranslationYAnimation().animateToFinalPosition(f2);
        }
    }

    public final void onMove(float f, float f2, boolean z) {
        this.panelClosedOnDown = z;
        this.yVelocity = f2;
        this.isOnFling = false;
        float defaultSettingEngine = f - (z ? this.destTranslationY : getDefaultSettingEngine());
        float f3 = this.destTranslationY;
        if (f3 <= 0.0f) {
            defaultSettingEngine = -defaultSettingEngine;
        }
        float fCoerceIn = RangesKt___RangesKt.coerceIn(defaultSettingEngine, f3 > 0.0f ? -f3 : 0.0f, f3 > 0.0f ? 0.0f : -f3);
        if (fCoerceIn != this.translationYValue || ((COUIDynamicAnimation) getTranslationYAnimation()).mRunning) {
            float fM = 0.35f;
            if (f2 > 1000.0f) {
                float fCoerceIn2 = RangesKt___RangesKt.coerceIn(Math.abs((Math.abs(this.translationYValue) - Math.abs(this.destTranslationY)) / (this.destTranslationY * 0.1f)), 0.0f, 1.0f);
                float f4 = this.maxResponse;
                fM = MonoSpline$.ExternalSyntheticOutline0.m(f4, 0.35f, fCoerceIn2, f4);
            }
            if (this.lastMoveResponse != fM) {
                AnimatorUtils.tryUpdateStiffness(getTranslationYAnimation(), AnimatorUtils.responseToStiffness(fM));
                this.lastMoveResponse = fM;
            }
            getTranslationYAnimation().animateToFinalPosition(fCoerceIn);
        }
    }

    public final void removeCallback(PanelTranslationYChangeListener panelTranslationYChangeListener) {
        synchronized (this.callbacks) {
            this.callbacks.remove(panelTranslationYChangeListener);
        }
    }

    public final void setDestTranslationY(float f) {
        this.destTranslationY = f;
    }

    public final void skipToEnd(boolean z) {
        float f = z ? 0.0f : -this.destTranslationY;
        float f2 = this.translationYValue;
        boolean z2 = ((COUIDynamicAnimation) getTranslationYAnimation()).mRunning;
        StringBuilder sbM = NotificationPanelViewController$.ExternalSyntheticOutline0.m("[skipToEnd]toExpand=", z, ", dest=", f, ", lastValue=");
        sbM.append(f2);
        sbM.append(", isRunning=");
        sbM.append(z2);
        LogUtil.i$default(LogUtil.TAG_QS, TAG, sbM.toString(), null, 8);
        if (!((COUIDynamicAnimation) getTranslationYAnimation()).mRunning) {
            trySetCurrValue(f);
            return;
        }
        getTranslationYAnimation().animateToFinalPosition(f);
        if (getTranslationYAnimation().canSkipToEnd()) {
            getTranslationYAnimation().skipToEnd();
        }
    }

    public final void trySetCurrValue(float f) {
        if (((COUIDynamicAnimation) getTranslationYAnimation()).mRunning) {
            return;
        }
        LogUtil.i$default(LogUtil.TAG_QS, TAG, Protection$.ExternalSyntheticOutline0.m(f, "[trySetCurrValue] translationY="), null, 8);
        DynamicAnimation.TRANSLATION_Y.setValue(this.qsPanelView, f);
        setTranslationYValue(f);
        getTranslationYAnimation().setStartValue(f);
    }
}
