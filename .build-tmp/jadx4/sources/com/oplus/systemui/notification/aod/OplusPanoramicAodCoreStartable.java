package com.oplus.systemui.notification.aod;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.core.view.insets.Protection$;
import com.android.internal.dynamicanimation.animation.FloatValueHolder;
import com.android.internal.dynamicanimation.animation.SpringAnimation;
import com.android.internal.dynamicanimation.animation.SpringForce;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.CoreStartable;
import com.android.systemui.keyguard.Lifecycle;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.shade.data.repository.ShadeRepository;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplus.systemui.aod.proxy.AodSettingsValueProxy;
import com.oplus.systemui.aod.utils.AodStyleTypeSettingsObserver;
import com.oplus.systemui.aod.utils.AodSwitchEnableObserver;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPanoramicAodCoreStartable implements CoreStartable, OplusPanoramicAodRepository, WakefulnessLifecycle.Observer {
    private static final Companion Companion = new Companion();
    private static final String TAG = "OplusPanoramicAodCoreStartable";
    private final OplusPanoramicAodCoreStartable$aodSwitchOrStyleTypeChangedListener$1 aodSwitchOrStyleTypeChangedListener;
    private final Set<OplusPanoramicAodRepository.PanoramicAodProgressChangeListener> callbacks;
    private final Lazy context;
    private SmoothTransitionController.SmoothTransitionUiState curUiState = SmoothTransitionController.SmoothTransitionUiState.UNKNOW_STATE;
    public boolean isInScreenOffProcess;
    public boolean isPanoramicAod;
    public boolean needAnimation;
    private final Lazy nssl;
    private final kotlin.Lazy oplusKeyguardStyleServiceCallback$delegate;
    public float panoramicAodProgress;
    private final SpringAnimation panoramicAodProgressAnimator;
    private final Lazy shadeRepository;
    public boolean shouldUpdateUi;
    private final kotlin.Lazy smoothTransitionCallback$delegate;
    private final Lazy statusBarKeyguardViewManager;
    private final Lazy statusBarStateController;
    public boolean switchAodEnable;
    private final Lazy visualStabilityCoordinator;
    private final Lazy wakefulnessLifecycle;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusPanoramicAodCoreStartable(Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, Lazy lazy7) {
        this.context = lazy;
        this.nssl = lazy2;
        this.visualStabilityCoordinator = lazy3;
        this.statusBarStateController = lazy4;
        this.wakefulnessLifecycle = lazy5;
        this.shadeRepository = lazy6;
        this.statusBarKeyguardViewManager = lazy7;
        SpringAnimation springAnimation = new SpringAnimation(new FloatValueHolder());
        springAnimation.setSpring(new SpringForce().setDampingRatio(1.0f));
        springAnimation.setStartValue(0.0f);
        springAnimation.setMinimumVisibleChange(0.001f);
        OplusPanoramicAodCoreStartable$panoramicAodProgressAnimator$1$1 oplusPanoramicAodCoreStartable$panoramicAodProgressAnimator$1$1 = new OplusPanoramicAodCoreStartable$panoramicAodProgressAnimator$1$1();
        oplusPanoramicAodCoreStartable$panoramicAodProgressAnimator$1$1.this$0 = this;
        VarHandle.storeStoreFence();
        springAnimation.addUpdateListener(oplusPanoramicAodCoreStartable$panoramicAodProgressAnimator$1$1);
        OplusPanoramicAodCoreStartable$panoramicAodProgressAnimator$1$2 oplusPanoramicAodCoreStartable$panoramicAodProgressAnimator$1$2 = new OplusPanoramicAodCoreStartable$panoramicAodProgressAnimator$1$2();
        oplusPanoramicAodCoreStartable$panoramicAodProgressAnimator$1$2.this$0 = this;
        VarHandle.storeStoreFence();
        springAnimation.addEndListener(oplusPanoramicAodCoreStartable$panoramicAodProgressAnimator$1$2);
        this.panoramicAodProgressAnimator = springAnimation;
        this.shouldUpdateUi = true;
        OplusPanoramicAodCoreStartable$aodSwitchOrStyleTypeChangedListener$1 oplusPanoramicAodCoreStartable$aodSwitchOrStyleTypeChangedListener$1 = new OplusPanoramicAodCoreStartable$aodSwitchOrStyleTypeChangedListener$1();
        oplusPanoramicAodCoreStartable$aodSwitchOrStyleTypeChangedListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.aodSwitchOrStyleTypeChangedListener = oplusPanoramicAodCoreStartable$aodSwitchOrStyleTypeChangedListener$1;
        OplusPanoramicAodCoreStartable$$ExternalSyntheticLambda0 oplusPanoramicAodCoreStartable$$ExternalSyntheticLambda0 = new OplusPanoramicAodCoreStartable$$ExternalSyntheticLambda0(0);
        oplusPanoramicAodCoreStartable$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.oplusKeyguardStyleServiceCallback$delegate = LazyKt__LazyJVMKt.lazy(oplusPanoramicAodCoreStartable$$ExternalSyntheticLambda0);
        OplusPanoramicAodCoreStartable$$ExternalSyntheticLambda0 oplusPanoramicAodCoreStartable$$ExternalSyntheticLambda1 = new OplusPanoramicAodCoreStartable$$ExternalSyntheticLambda0(1);
        oplusPanoramicAodCoreStartable$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.smoothTransitionCallback$delegate = LazyKt__LazyJVMKt.lazy(oplusPanoramicAodCoreStartable$$ExternalSyntheticLambda1);
        this.callbacks = new LinkedHashSet();
    }

    public static final void access$onPanoramicAodChanged(OplusPanoramicAodCoreStartable oplusPanoramicAodCoreStartable, boolean z) {
        float f = oplusPanoramicAodCoreStartable.isPanoramicAod ? 1.0f : 0.0f;
        SpringForce spring = oplusPanoramicAodCoreStartable.panoramicAodProgressAnimator.getSpring();
        if (spring != null) {
            spring.setStiffness(438.65f);
        }
        if (!z || !oplusPanoramicAodCoreStartable.panoramicAodProgressAnimator.canSkipToEnd()) {
            oplusPanoramicAodCoreStartable.panoramicAodProgressAnimator.animateToFinalPosition(f);
            return;
        }
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, Protection$.ExternalSyntheticOutline0.m(f, "onPanoramicAodChanged skipToEnd end "), null, 8);
        oplusPanoramicAodCoreStartable.setPanoramicAodProgress(f);
        oplusPanoramicAodCoreStartable.panoramicAodProgressAnimator.setStartValue(f);
        oplusPanoramicAodCoreStartable.panoramicAodProgressAnimator.animateToFinalPosition(f);
        oplusPanoramicAodCoreStartable.panoramicAodProgressAnimator.skipToEnd();
    }

    private final OplusPanoramicAodCoreStartable$oplusKeyguardStyleServiceCallback$2$1 getOplusKeyguardStyleServiceCallback() {
        return (OplusPanoramicAodCoreStartable$oplusKeyguardStyleServiceCallback$2$1) this.oplusKeyguardStyleServiceCallback$delegate.getValue();
    }

    private final OplusPanoramicAodCoreStartable$smoothTransitionCallback$2$1 getSmoothTransitionCallback() {
        return (OplusPanoramicAodCoreStartable$smoothTransitionCallback$2$1) this.smoothTransitionCallback$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setAnimationsEnabled(boolean z) {
        if (this.isPanoramicAod && !this.isInScreenOffProcess) {
            ((NotificationStackScrollLayoutController) this.nssl.get()).setAnimationsEnabled(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPanoramicAod(boolean z) {
        if (this.isPanoramicAod == z) {
            return;
        }
        this.isPanoramicAod = z;
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("isPanoramicAod  ", z), null, 8);
        Iterator<T> it = this.callbacks.iterator();
        while (it.hasNext()) {
            ((OplusPanoramicAodRepository.PanoramicAodProgressChangeListener) it.next()).onPanoramicAodStateChanged(this.isPanoramicAod, getShouldUpdateUi());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPanoramicAodProgress(float f) {
        this.panoramicAodProgress = f;
        Iterator<T> it = this.callbacks.iterator();
        while (it.hasNext()) {
            ((OplusPanoramicAodRepository.PanoramicAodProgressChangeListener) it.next()).onProgressChanged(f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setShouldUpdateUi(boolean z) {
        if (this.shouldUpdateUi == z) {
            return;
        }
        this.shouldUpdateUi = z;
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("shouldUpdateUi  ", z), null, 8);
        Iterator<T> it = this.callbacks.iterator();
        while (it.hasNext()) {
            ((OplusPanoramicAodRepository.PanoramicAodProgressChangeListener) it.next()).onPanoramicAodStateChanged(this.isPanoramicAod, getShouldUpdateUi());
        }
    }

    private void setSwitchAodEnable(boolean z) {
        if (this.switchAodEnable == z) {
            return;
        }
        this.switchAodEnable = z;
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("switchAodEnable  ", z), null, 8);
    }

    @Override // com.oplus.systemui.notification.aod.OplusPanoramicAodRepository
    public void addCallback(OplusPanoramicAodRepository.PanoramicAodProgressChangeListener panoramicAodProgressChangeListener) {
        if (!Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "should be called from the main thread.");
        }
        this.callbacks.add(panoramicAodProgressChangeListener);
    }

    public final void changeSwitchAodEnable() {
        setSwitchAodEnable(AodSettingsValueProxy.getAodSwitchEnable((Context) this.context.get()) == 1 && AodSettingsValueProxy.getAodType((Context) this.context.get()) == 2);
        boolean switchAodEnable = getSwitchAodEnable();
        Lazy lazy = this.wakefulnessLifecycle;
        if (!switchAodEnable) {
            ((WakefulnessLifecycle) lazy.get()).removeObserver(this);
        } else {
            ((WakefulnessLifecycle) lazy.get()).removeObserver(this);
            ((Lifecycle) ((WakefulnessLifecycle) this.wakefulnessLifecycle.get())).mObservers.add(this);
        }
    }

    @Override // com.oplus.systemui.notification.aod.OplusPanoramicAodRepository
    public float getPanoramicAodProgress() {
        return this.panoramicAodProgress;
    }

    @Override // com.oplus.systemui.notification.aod.OplusPanoramicAodRepository
    public boolean getShouldUpdateUi() {
        return this.shouldUpdateUi;
    }

    @Override // com.oplus.systemui.notification.aod.OplusPanoramicAodRepository
    public boolean getSwitchAodEnable() {
        return this.switchAodEnable;
    }

    public final void onFinishedGoingToSleep() {
        setInScreenOffProcess(false);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("onFinishedGoingToSleep shouldUpdateUi setAnimationsEnabled ", getShouldUpdateUi()), null, 8);
        Handler mainHandler = SystemUIThreadUtil.getMainHandler();
        onFinishedGoingToSleep.1 r2 = new onFinishedGoingToSleep.1(0);
        r2.this$0 = this;
        VarHandle.storeStoreFence();
        mainHandler.post(r2);
    }

    public final void onStartedGoingToSleep() {
        boolean z = true;
        setInScreenOffProcess(true);
        int state = ((StatusBarStateController) this.statusBarStateController.get()).getState();
        if (state == 1 ? ((StatusBarKeyguardViewManager) this.statusBarKeyguardViewManager.get()).mPrimaryBouncerInteractor.isFullyShowing() || ((Boolean) ((ShadeRepository) this.shadeRepository.get()).getLegacyIsQsExpanded().getValue()).booleanValue() || ((Boolean) ((ShadeRepository) this.shadeRepository.get()).getLegacyExpandedOrAwaitingInputTransferForQS().getValue()).booleanValue() : state != 2) {
            z = false;
        }
        this.needAnimation = z;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", VFXController$stopAnim$1$.ExternalSyntheticOutline0.m("onStartedGoingToSleep:", state, " ,state=", "OplusPanoramicAodCoreStartable-->", this.needAnimation));
        }
    }

    @Override // com.oplus.systemui.notification.aod.OplusPanoramicAodRepository
    public void removeCallback(OplusPanoramicAodRepository.PanoramicAodProgressChangeListener panoramicAodProgressChangeListener) {
        if (!Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
            LogUtil.w$default(8, LogUtil.TAG_NOTIFICATION, TAG, "should be called from the main thread.");
        }
        this.callbacks.remove(panoramicAodProgressChangeListener);
    }

    public void setInScreenOffProcess(boolean z) {
        this.isInScreenOffProcess = z;
    }

    public final void start() {
        SmoothTransitionController.Companion companion = SmoothTransitionController.Companion;
        SmoothTransitionController.registerSmoothTransitionCallback$default(companion.getInstance((Context) this.context.get()), getSmoothTransitionCallback());
        SmoothTransitionController.registerSmoothTransitionCallback$default(companion.getInstance((Context) this.context.get()), getOplusKeyguardStyleServiceCallback());
        changeSwitchAodEnable();
        AodSwitchEnableObserver.Companion.getInstance().addListener(this.aodSwitchOrStyleTypeChangedListener);
        AodStyleTypeSettingsObserver.Companion.getInstance().addListener(this.aodSwitchOrStyleTypeChangedListener);
    }
}
