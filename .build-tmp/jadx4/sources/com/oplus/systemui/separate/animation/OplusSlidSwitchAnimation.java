package com.oplus.systemui.separate.animation;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.statusbar.policy.CallbackController;
import com.oplus.systemui.separate.HorizontalProgressiveAnimController;
import io.grpc.Status$;
import java.lang.invoke.VarHandle;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusSlidSwitchAnimation {
    private final Lazy horizontalTranslationValue$delegate;
    private final Lazy overScrollTranslationValue$delegate;
    private final HorizontalProgressiveAnimController progressiveController = new HorizontalProgressiveAnimController();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public class HorizontalTranslationValue extends ScrollTranslationValue {
        public HorizontalTranslationValue(HorizontalProgressiveAnimController horizontalProgressiveAnimController) {
            super(horizontalProgressiveAnimController);
        }

        public final void flingTo(float f, Function1 function1) {
            getProgressiveController().flingTo(f, function1);
        }

        public final boolean isLogicRunning() {
            return getProgressiveController().isLogicRunning() || getProgressiveController().isRubberbandLogicRunning();
        }

        public final boolean isRunning() {
            return getProgressiveController().isRunning() || getProgressiveController().isRubberbandRunning();
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class OverScrollTranslationValue extends ScrollTranslationValue {
        public OverScrollTranslationValue(HorizontalProgressiveAnimController horizontalProgressiveAnimController) {
            super(horizontalProgressiveAnimController);
        }

        public final void flingToRubberband(Function1 function1) {
            getProgressiveController().flingToRubberband(function1);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface TranslationChangeListener {
    }

    public static HorizontalTranslationValue $r8$lambda$IixPjiF_L4Xsa2IQYt5H5v9FaYA(OplusSlidSwitchAnimation oplusSlidSwitchAnimation) {
        return new HorizontalTranslationValue(oplusSlidSwitchAnimation.progressiveController);
    }

    public static OverScrollTranslationValue $r8$lambda$Wk_sQuPhUho1T1TJyxVLR3hp7o8(OplusSlidSwitchAnimation oplusSlidSwitchAnimation) {
        return new OverScrollTranslationValue(oplusSlidSwitchAnimation.progressiveController);
    }

    public OplusSlidSwitchAnimation() {
        OplusSlidSwitchAnimation$$ExternalSyntheticLambda0 oplusSlidSwitchAnimation$$ExternalSyntheticLambda0 = new OplusSlidSwitchAnimation$$ExternalSyntheticLambda0(0);
        oplusSlidSwitchAnimation$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.horizontalTranslationValue$delegate = LazyKt__LazyJVMKt.lazy(oplusSlidSwitchAnimation$$ExternalSyntheticLambda0);
        OplusSlidSwitchAnimation$$ExternalSyntheticLambda0 oplusSlidSwitchAnimation$$ExternalSyntheticLambda1 = new OplusSlidSwitchAnimation$$ExternalSyntheticLambda0(1);
        oplusSlidSwitchAnimation$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.overScrollTranslationValue$delegate = LazyKt__LazyJVMKt.lazy(oplusSlidSwitchAnimation$$ExternalSyntheticLambda1);
    }

    public final HorizontalTranslationValue getHorizontalTranslationValue() {
        return (HorizontalTranslationValue) this.horizontalTranslationValue$delegate.getValue();
    }

    public final OverScrollTranslationValue getOverScrollTranslationValue() {
        return (OverScrollTranslationValue) this.overScrollTranslationValue$delegate.getValue();
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public class ScrollTranslationValue implements CallbackController {
        public static final Companion Companion = new Companion();
        private static final String TAG = "ScrollTranslationValue";
        private final Set<TranslationChangeListener> callbacks = new LinkedHashSet();
        private final HorizontalProgressiveAnimController progressiveController;
        public boolean scrollingToMax;
        public boolean scrollingToMin;
        public boolean waitForBlocking;

        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class Companion {
        }

        public ScrollTranslationValue(HorizontalProgressiveAnimController horizontalProgressiveAnimController) {
            this.progressiveController = horizontalProgressiveAnimController;
        }

        public void addCallback(TranslationChangeListener translationChangeListener) {
            synchronized (this.callbacks) {
                if (this.callbacks.contains(translationChangeListener)) {
                    return;
                }
                this.callbacks.add(translationChangeListener);
            }
        }

        public final void addGroup(View view, List<? extends View> list) {
            this.progressiveController.addGroup(view, list);
        }

        public final void clearDownEvent() {
            this.progressiveController.clearDownEvent();
        }

        public final void ensureReady(List<? extends ViewGroup> list, Context context) {
            this.progressiveController.ensureReady(list, context);
        }

        public final boolean getBlocking() {
            return this.waitForBlocking;
        }

        public final HorizontalProgressiveAnimController getProgressiveController() {
            return this.progressiveController;
        }

        public final boolean getScrollingToMax() {
            return this.scrollingToMax;
        }

        public final boolean getScrollingToMin() {
            return this.scrollingToMin;
        }

        public final boolean hasProgressiveHelper() {
            return this.progressiveController.getProgressiveHelper() != null;
        }

        public final boolean isDynamicChildViewsEqual(List<? extends View> list) {
            return this.progressiveController.isDynamicChildViewsEqual(list);
        }

        public final void onDown(MotionEvent motionEvent) {
            this.progressiveController.onDown(motionEvent);
        }

        public final void register(List<? extends View> list) {
            this.progressiveController.register(list);
        }

        public final void registerContainer(ViewGroup viewGroup, View view) {
            this.progressiveController.registerContainer(viewGroup, view);
        }

        public final void registerDynamicView(List<? extends View> list) {
            this.progressiveController.registerDynamicView(list);
        }

        public final void release() {
            HorizontalProgressiveAnimController horizontalProgressiveAnimController = this.progressiveController;
            horizontalProgressiveAnimController.resetHelper();
            horizontalProgressiveAnimController.clearDownEvent();
        }

        public void removeCallback(TranslationChangeListener translationChangeListener) {
            synchronized (this.callbacks) {
                this.callbacks.remove(translationChangeListener);
            }
        }

        public final void removeGroup() {
            this.progressiveController.removeGroup();
        }

        public final void resetHelper() {
            this.progressiveController.resetHelper();
        }

        public final void setBlocking(boolean z) {
            this.waitForBlocking = z;
        }

        public final void setOnTranslationChangedCallback(Function2 function2) {
            this.progressiveController.setOnTranslationChangedCallback(function2);
        }

        public final void setScrollingToMax(boolean z) {
            this.scrollingToMax = z;
        }

        public final void setScrollingToMin(boolean z) {
            this.scrollingToMin = z;
        }

        public final void unRegister(View view) {
            this.progressiveController.unRegister(view);
        }

        public final void unRegisterContainer(ViewGroup viewGroup) {
            this.progressiveController.unRegisterContainer(viewGroup);
        }

        public final void unRegisterDynamicView() {
            this.progressiveController.unRegisterDynamicView();
        }

        public final void updateSDKLogEnabled$1() {
            this.progressiveController.updateSDKLogEnabled();
        }

        public final void register(View view) {
            this.progressiveController.register(view);
        }

        public final /* synthetic */ void removeCallback(Object obj) {
            if (obj == null) {
                removeCallback((TranslationChangeListener) null);
            } else {
                Status$.ExternalSyntheticBUOutline0.m();
            }
        }

        public final /* synthetic */ void addCallback(Object obj) {
            if (obj == null) {
                addCallback((TranslationChangeListener) null);
            } else {
                Status$.ExternalSyntheticBUOutline0.m();
            }
        }
    }
}
