package com.oplus.systemui.statusbar.phone;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.android.systemui.panelanimation.platformblur.PlatformBlurManager;
import com.android.systemui.shade.OplusSeparateNotificationAndQSEx;
import com.android.systemui.shade.ShadeSurface;
import com.android.systemui.statusbar.phone.ScrimBlurStateUtilEx;
import com.android.systemui.statusbar.phone.ScrimController;
import com.android.systemui.statusbar.phone.ScrimControllerEx;
import com.android.systemui.statusbar.phone.ScrimState;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.oplus.keyguard.VFXController$stopAnim$1$;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.notification.lockscreen.ILockScreenExtStateController;
import com.oplus.systemui.notification.lockscreen.immersive.ImmersiveCardControl;
import com.oplus.systemui.notification.lockscreen.immersive.OplusImmersiveViewManager;
import com.oplus.systemui.notification.lockscreen.statemanager.LockScreenNotificationExtStateManager;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.systemui.wallpaperblur.WallpaperBlurManager;
import com.oplus.zoom.animation.ZoomAnimationUtils;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.blurability.BlurConfig;
import com.oplusos.systemui.common.blurability.ViewBlurProxy;
import com.oplusos.systemui.common.blurability.WindowBlurConfig;
import com.oplusos.systemui.common.blurability.drawable.AutoBlurDrawable;
import com.oplusos.systemui.common.blurability.drawable.MaskDrawable;
import com.oplusos.systemui.common.blurability.screenshot.StaticBlurManager;
import com.oplusos.systemui.common.feature.KeyguardFeatureOption;
import com.oplusos.systemui.common.feature.NotificationFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.NotifiAndQsPlatformBlurExKt;
import com.oplusos.systemui.common.util.ScrimUtil;
import dagger.Lazy;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex$;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScrimBlurStateUtilsExImpl extends ScrimBlurStateUtilEx {
    public static final Companion Companion = new Companion();
    private static final String REASON_CLEAR_FRONT_SCRIM_WHEN_UNLOCK = "clearFrontScrimWhenUnlock";
    private static final String REASON_IMMERSIVE_EXIT_DURING_BOUNCER = "immersiveExitDuringBouncer";
    private static final String TAG = "ScrimBlurStateUtils";
    private final Lazy context;
    private final CoroutineScope coroutineScope;
    public float currentFrontBlurAmount;
    private ScrimState currentState;
    public boolean frontScrimBlurAnimRunning;
    private final Lazy immersiveCardControl;
    private final Lazy immersiveViewManager;
    private final kotlin.Lazy isBAnimEnableBouncerWallpaperBlur$delegate;
    private final Lazy keyguardStateController;
    private final Lazy lockScreenNotificationExtStateManager;
    private final Lazy platformBlurManager;
    private final Lazy scrimController;
    private final Lazy scrimControllerExImp;
    private final Lazy separateNotificationAndQSExImpl;
    private final Lazy shadeSurface;
    private final Lazy wallpaperBlurManager;

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.ScrimBlurStateUtilsExImpl$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.ScrimBlurStateUtilsExImpl$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class C01351 implements FlowCollector {
            public /* synthetic */ ScrimBlurStateUtilsExImpl this$0;

            public final Object emit(Object obj, Continuation continuation) {
                ILockScreenExtStateController.ExtStateEvent extStateEvent = (ILockScreenExtStateController.ExtStateEvent) obj;
                ScrimBlurStateUtilsExImpl scrimBlurStateUtilsExImpl = this.this$0;
                if (extStateEvent.getOldState() == 2 && extStateEvent.getNewState() != 2 && ScrimBlurStateUtilsExImpl.access$isBouncerActive(scrimBlurStateUtilsExImpl)) {
                    ScrimBlurStateUtilsExImpl.access$refreshBlurAfterImmersiveExit(scrimBlurStateUtilsExImpl);
                }
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return ScrimBlurStateUtilsExImpl.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow extStateEvent = ((LockScreenNotificationExtStateManager) ScrimBlurStateUtilsExImpl.this.lockScreenNotificationExtStateManager.get()).getExtStateEvent();
                ScrimBlurStateUtilsExImpl scrimBlurStateUtilsExImpl = ScrimBlurStateUtilsExImpl.this;
                C01351 c01351 = new C01351();
                c01351.this$0 = scrimBlurStateUtilsExImpl;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (extStateEvent.collect(c01351, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            Regex$.ExternalSyntheticBUOutline0.m$1();
            return null;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.phone.ScrimBlurStateUtilsExImpl$startFrontScrimBlurAnim$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class C02981 implements ValueAnimator.AnimatorUpdateListener {
        public /* synthetic */ ScrimBlurStateUtilsExImpl this$0;

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            this.this$0.setFrontScrimBlurAmount(fFloatValue, "FrontScrimBlurAnim");
            this.this$0.setKeyguardPanelAlpha(1.0f - fFloatValue);
        }
    }

    public ScrimBlurStateUtilsExImpl(Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, Lazy lazy7, Lazy lazy8, Lazy lazy9, Lazy lazy10, CoroutineScope coroutineScope, Lazy lazy11) {
        this.context = lazy;
        this.scrimControllerExImp = lazy2;
        this.separateNotificationAndQSExImpl = lazy3;
        this.scrimController = lazy4;
        this.shadeSurface = lazy5;
        this.lockScreenNotificationExtStateManager = lazy6;
        this.wallpaperBlurManager = lazy7;
        this.platformBlurManager = lazy8;
        this.keyguardStateController = lazy9;
        this.immersiveViewManager = lazy10;
        this.coroutineScope = coroutineScope;
        this.immersiveCardControl = lazy11;
        BuildersKt.launch$default(coroutineScope, Dispatchers.getMain().getImmediate(), (CoroutineStart) null, new AnonymousClass1(null), 2);
        this.currentState = ScrimState.OFF;
        this.isBAnimEnableBouncerWallpaperBlur$delegate = LazyKt__LazyJVMKt.lazy(new ScrimBlurStateUtilsExImpl$$ExternalSyntheticLambda0());
    }

    public static final boolean access$isBouncerActive(ScrimBlurStateUtilsExImpl scrimBlurStateUtilsExImpl) {
        ScrimState state = ((ScrimController) scrimBlurStateUtilsExImpl.scrimController.get()).getState();
        return state == ScrimState.BOUNCER || state == ScrimState.BOUNCER_SCRIMMED || ((KeyguardStateController) scrimBlurStateUtilsExImpl.keyguardStateController.get()).mPrimaryBouncerShowing || ((ScrimControllerEx) scrimBlurStateUtilsExImpl.scrimControllerExImp.get()).isEnteringBouncer();
    }

    public static final void access$refreshBlurAfterImmersiveExit(ScrimBlurStateUtilsExImpl scrimBlurStateUtilsExImpl) {
        KgdLogUtil.i(TAG, "refreshBlurAfterImmersiveExit: state=" + ((ScrimController) scrimBlurStateUtilsExImpl.scrimController.get()).getState());
        scrimBlurStateUtilsExImpl.setFrontScrimBlurAmount(scrimBlurStateUtilsExImpl.getCurrentFrontBlurAmount(), REASON_IMMERSIVE_EXIT_DURING_BOUNCER);
    }

    private final int getBouncerScrimMaxBlurRadius() {
        return ZoomAnimationUtils.TRANSIT_SCALE_AND_POSITION;
    }

    public final boolean couldFrontScrimBlurAnim() {
        ScrimState state = ((ScrimController) this.scrimController.get()).getState();
        boolean z = false;
        ScrimState scrimState = ScrimState.BOUNCER_SCRIMMED;
        if (state == scrimState && ((ScrimControllerEx) this.scrimControllerExImp.get()).getOldState() == scrimState) {
            KgdLogUtil.i(TAG, "startFrontScrimBlurAnim but scrim_bouncered");
            return false;
        }
        KgdLogUtil.i(TAG, "state=" + ((ScrimController) this.scrimController.get()).getState() + " oldState=" + ((ScrimControllerEx) this.scrimControllerExImp.get()).getOldState());
        boolean z2 = ((ScrimControllerEx) this.scrimControllerExImp.get()).getScrimBehindBlurAmount() == 1.0f;
        boolean zIsLowGaussianLevel = ScrimUtil.isLowGaussianLevel((Context) this.context.get());
        if (!z2 && !zIsLowGaussianLevel) {
            z = true;
        }
        if (!z) {
            KgdLogUtil.i(TAG, "startFrontScrimBlurAnim but could not");
            if (((ScrimController) this.scrimController.get()).getState() == scrimState && !((KeyguardStateController) this.keyguardStateController.get()).mKeyguardGoingAway) {
                setFrontScrimBlurAmount(1.0f, "couldNotFrontScrimBlurAnim");
                setKeyguardPanelAlpha(0.0f);
            }
        }
        return z;
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        BlurConfig blurConfig;
        printWriter.println("ScrimBlurStateUtils scrimInFront.blurType = " + ((ScrimController) this.scrimController.get()).getScrimInFront().getExt().getBlurType());
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("ScrimBlurStateUtils scrimInFront.targetAlpha = ", ((ScrimController) this.scrimController.get()).getScrimInFront().getExt().getTargetAlpha(), printWriter);
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("ScrimBlurStateUtils scrimInFront.alpha = ", ((ScrimController) this.scrimController.get()).getScrimInFront().getAlpha(), printWriter);
        ViewBlurProxy viewBlurProxy = ((ScrimController) this.scrimController.get()).getScrimInFront().getExt().getViewBlurProxy();
        printWriter.println("ScrimBlurStateUtils scrimInFront.blurColor = " + ((viewBlurProxy == null || (blurConfig = viewBlurProxy.getBlurConfig()) == null) ? null : Integer.valueOf(blurConfig.getBlurColor())));
        printWriter.println("ScrimBlurStateUtils scrimInFront.viewAlpha = " + ((ScrimController) this.scrimController.get()).getScrimInFront().getViewAlpha());
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("ScrimBlurStateUtils currentFrontBlurAmount = ", getCurrentFrontBlurAmount(), printWriter);
    }

    public float getControlCenterFraction() {
        return ((ScrimController) this.scrimController.get()).getQsExpansion();
    }

    public float getCurrentFrontBlurAmount() {
        return this.currentFrontBlurAmount;
    }

    public final ScrimState getCurrentState() {
        return ((ScrimController) this.scrimController.get()).getState();
    }

    public boolean getFrontScrimBlurAnimRunning() {
        return this.frontScrimBlurAnimRunning;
    }

    /* JADX WARN: Code duplicated, block: B:32:0x0081 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:33:0x0083  */
    /* JADX WARN: Code duplicated, block: B:34:0x0086  */
    /* JADX WARN: Code duplicated, block: B:36:0x0096  */
    /* JADX WARN: Code duplicated, block: B:37:0x0099  */
    /* JADX WARN: Code duplicated, block: B:59:0x0164  */
    /* JADX WARN: Code duplicated, block: B:61:0x016d  */
    /* JADX WARN: Code duplicated, block: B:62:0x0182  */
    /* JADX WARN: Code duplicated, block: B:64:0x018b  */
    /* JADX WARN: Code duplicated, block: B:65:0x01a0  */
    /* JADX WARN: Code duplicated, block: B:67:0x01ac  */
    /* JADX WARN: Code duplicated, block: B:68:0x01bf  */
    public ViewBlurProxy.BlurType getFrontScrimBlurType() {
        ViewBlurProxy.BlurType blurType;
        boolean z;
        Lazy lazy;
        ImmersiveCardControl immersiveCardControl;
        Integer numCurrImmersiveBgType;
        ImmersiveCardControl immersiveCardControl2 = (ImmersiveCardControl) this.immersiveCardControl.get();
        boolean z2 = immersiveCardControl2.currImmersiveBgType((ILockScreenExtStateController.ExtStateEvent) ((ILockScreenExtStateController) immersiveCardControl2.lockScreenExtStateController.get()).getExtStateEvent().getValue()) != null && ((numCurrImmersiveBgType = (immersiveCardControl = (ImmersiveCardControl) this.immersiveCardControl.get()).currImmersiveBgType((ILockScreenExtStateController.ExtStateEvent) ((ILockScreenExtStateController) immersiveCardControl.lockScreenExtStateController.get()).getExtStateEvent().getValue())) == null || numCurrImmersiveBgType.intValue() != 2);
        boolean z3 = ((KeyguardStateController) this.keyguardStateController.get()).mOccluded;
        boolean z4 = ScrimUtil.isBAnimLevel() || SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState;
        if (z2 && !z3) {
            blurType = ViewBlurProxy.BlurType.BlurTypeNon.INSTANCE;
        } else if (!ScrimUtil.isWallpaperBlurDisable() && ScrimUtil.isBAnimLevel()) {
            ScrimControllerEx.Companion.getClass();
            if (!ScrimControllerEx.isThirdTheme && !SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState && ((Boolean) this.isBAnimEnableBouncerWallpaperBlur$delegate.getValue()).booleanValue()) {
                blurType = ViewBlurProxy.BlurType.BlurTypeBlendWallpaper.INSTANCE;
            } else if (z4) {
                blurType = ViewBlurProxy.BlurType.BlurTypeNon.INSTANCE;
            } else if (KeyguardUtils.Companion.isSystemFbeMode((Context) this.context.get())) {
                blurType = ViewBlurProxy.BlurType.BlurTypeWindowBg.INSTANCE;
            } else {
                blurType = ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE;
            }
        } else if (z4) {
            blurType = ViewBlurProxy.BlurType.BlurTypeNon.INSTANCE;
        } else if (KeyguardUtils.Companion.isSystemFbeMode((Context) this.context.get())) {
            blurType = ViewBlurProxy.BlurType.BlurTypeWindowBg.INSTANCE;
        } else {
            blurType = ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE;
        }
        if (!Intrinsics.areEqual(((ScrimController) this.scrimController.get()).getScrimInFront().getExt().getLastBlurType(), blurType)) {
            setFrontScrimBlurAmount(0.0f, "refreshFrontDrawable");
            View scrimInFront = ((ScrimController) this.scrimController.get()).getScrimInFront();
            if (scrimInFront != null) {
                StaticBlurManager staticBlurManager = null;
                if (Intrinsics.areEqual(blurType, ViewBlurProxy.BlurType.BlurTypeBlendWallpaper.INSTANCE)) {
                    scrimInFront.setDrawable(((WallpaperBlurManager) this.wallpaperBlurManager.get()).requireWallpaperBlurDrawableForView(scrimInFront, true));
                    ((ScrimController) this.scrimController.get()).getScrimInFront().getExt().setTargetAlpha(1.0f);
                } else {
                    ViewBlurProxy.BlurType.BlurTypeNon blurTypeNon = ViewBlurProxy.BlurType.BlurTypeNon.INSTANCE;
                    if (Intrinsics.areEqual(blurType, blurTypeNon)) {
                        ViewBlurProxy viewBlurProxy = scrimInFront.getExt().getViewBlurProxy();
                        if (viewBlurProxy != null) {
                            viewBlurProxy.setBlurType(blurTypeNon);
                        }
                        if (((KeyguardStateController) this.keyguardStateController.get()).mOccluded) {
                            KeyguardFeatureOption.INSTANCE.getClass();
                            if (KeyguardFeatureOption.isDisableBouncerBlurScrim) {
                                ((ScrimController) this.scrimController.get()).getScrimInFront().getExt().setTargetAlpha(1.0f);
                            } else {
                                NotificationFeatureOption.INSTANCE.getClass();
                                if (NotificationFeatureOption.isGaussBlurDisabled) {
                                    ((ScrimController) this.scrimController.get()).getScrimInFront().getExt().setTargetAlpha(1.0f);
                                } else {
                                    z = SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState;
                                    lazy = this.scrimController;
                                    if (z) {
                                        ((ScrimController) lazy.get()).getScrimInFront().getExt().setTargetAlpha(1.0f);
                                    } else {
                                        ((ScrimController) lazy.get()).getScrimInFront().getExt().setTargetAlpha(0.2f);
                                    }
                                }
                            }
                        } else {
                            ImmersiveCardControl immersiveCardControl3 = (ImmersiveCardControl) this.immersiveCardControl.get();
                            if (immersiveCardControl3.currImmersiveBgType((ILockScreenExtStateController.ExtStateEvent) ((ILockScreenExtStateController) immersiveCardControl3.lockScreenExtStateController.get()).getExtStateEvent().getValue()) == null || !(((ScrimController) this.scrimController.get()).getState() == ScrimState.BOUNCER_SCRIMMED || ((ScrimController) this.scrimController.get()).getState() == ScrimState.BOUNCER)) {
                                KeyguardFeatureOption.INSTANCE.getClass();
                                if (KeyguardFeatureOption.isDisableBouncerBlurScrim) {
                                    ((ScrimController) this.scrimController.get()).getScrimInFront().getExt().setTargetAlpha(1.0f);
                                } else {
                                    NotificationFeatureOption.INSTANCE.getClass();
                                    if (NotificationFeatureOption.isGaussBlurDisabled) {
                                        ((ScrimController) this.scrimController.get()).getScrimInFront().getExt().setTargetAlpha(1.0f);
                                    } else {
                                        z = SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState;
                                        lazy = this.scrimController;
                                        if (z) {
                                            ((ScrimController) lazy.get()).getScrimInFront().getExt().setTargetAlpha(1.0f);
                                        } else {
                                            ((ScrimController) lazy.get()).getScrimInFront().getExt().setTargetAlpha(0.2f);
                                        }
                                    }
                                }
                            } else {
                                ((ScrimController) this.scrimController.get()).getScrimInFront().getExt().setTargetAlpha(0.0f);
                            }
                        }
                    } else {
                        ViewBlurProxy.BlurType.BlurTypePlatformStatic blurTypePlatformStatic = ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE;
                        int i = 28;
                        if (Intrinsics.areEqual(blurType, blurTypePlatformStatic)) {
                            if (ScrimUtil.isLowGaussianLevel((Context) this.context.get())) {
                                ((WallpaperBlurManager) this.wallpaperBlurManager.get()).releaseWallpaperBlurDrawableForView(scrimInFront);
                            }
                            ViewBlurProxy viewBlurProxy2 = new ViewBlurProxy(scrimInFront, new BlurConfig(getBouncerScrimMaxBlurRadius(), 0, null, null, null, null, true, NotifiAndQsPlatformBlurExKt.panelBouncerMixConfig(((Boolean) ((PlatformBlurManager) this.platformBlurManager.get()).getUseMotionBlur().getValue()).booleanValue()), null, 0, null, 33456126), staticBlurManager, i);
                            scrimInFront.setDrawable(new AutoBlurDrawable(viewBlurProxy2, new MaskDrawable(viewBlurProxy2.getBlurConfig())));
                            viewBlurProxy2.setBlurType(blurTypePlatformStatic);
                            ((ScrimController) this.scrimController.get()).getScrimInFront().getExt().setTargetAlpha(0.2f);
                        } else {
                            ViewBlurProxy.BlurType.BlurTypeWindowBg blurTypeWindowBg = ViewBlurProxy.BlurType.BlurTypeWindowBg.INSTANCE;
                            if (Intrinsics.areEqual(blurType, blurTypeWindowBg)) {
                                ViewBlurProxy viewBlurProxy3 = new ViewBlurProxy(scrimInFront, new BlurConfig(540, 0, null, null, null, null, true, null, null, 0, new WindowBlurConfig(((Context) this.context.get()).getResources().getDimensionPixelSize(2131170215), 50, ((Context) this.context.get()).getColor(2131099943)), 31424510), staticBlurManager, i);
                                scrimInFront.setDrawable(new AutoBlurDrawable(viewBlurProxy3, new MaskDrawable(viewBlurProxy3.getBlurConfig())));
                                ViewBlurProxy viewBlurProxy4 = scrimInFront.getExt().getViewBlurProxy();
                                if (viewBlurProxy4 != null) {
                                    viewBlurProxy4.setBlurType(blurTypeWindowBg);
                                }
                                ((ScrimController) this.scrimController.get()).getScrimInFront().getExt().setTargetAlpha(0.2f);
                            }
                        }
                    }
                }
                boolean zIsBAnimLevel = ScrimUtil.isBAnimLevel();
                ScrimControllerEx.Companion.getClass();
                boolean z5 = ScrimControllerEx.isThirdTheme;
                boolean z6 = SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState;
                Drawable drawable = scrimInFront.getDrawable();
                StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("refreshFrontDrawable isBAnimLevel: ", ", isThirdTheme: ", ", isSuperPowerSaveState: ", zIsBAnimLevel, z5);
                sbM.append(z6);
                sbM.append(", drawable: ");
                sbM.append(drawable);
                LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, sbM.toString(), null, 8);
            }
            if (!Intrinsics.areEqual(blurType, ViewBlurProxy.BlurType.BlurTypeNon.INSTANCE)) {
                ((ScrimController) this.scrimController.get()).getScrimInFront().setAlpha(1.0f);
            }
            KgdLogUtil.i(TAG, "FrontScrimBlurType Change: " + blurType);
        }
        ((ScrimController) this.scrimController.get()).getScrimInFront().getExt().setLastBlurType(((ScrimController) this.scrimController.get()).getScrimInFront().getExt().getBlurType());
        ((ScrimController) this.scrimController.get()).getScrimInFront().getExt().setBlurType(blurType);
        return blurType;
    }

    public void setControlCenterFraction(float f) {
    }

    public void setCurrentFrontBlurAmount(float f) {
        this.currentFrontBlurAmount = f;
    }

    public final void setCurrentState(ScrimState scrimState) {
        this.currentState = scrimState;
    }

    public void setFrontScrimBlurAmount(float f, String str) {
        BlurConfig blurConfig;
        BlurConfig blurConfig2;
        if (((KeyguardStateController) this.keyguardStateController.get()).getEx().isBlockAlphaFlag() && ((KeyguardStateController) this.keyguardStateController.get()).getEx().isUnOccludeAnimation() && ((((KeyguardStateController) this.keyguardStateController.get()).mPrimaryBouncerShowing || ((ScrimController) this.scrimController.get()).getState() == ScrimState.BOUNCER || ((ScrimController) this.scrimController.get()).getState() == ScrimState.BOUNCER_SCRIMMED) && getCurrentFrontBlurAmount() > 0.0f && f < getCurrentFrontBlurAmount())) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                float currentFrontBlurAmount = getCurrentFrontBlurAmount();
                StringBuilder sb = new StringBuilder("setFrontScrimBlurAmount hold front scrim blur during unocclude:skip amount=");
                sb.append(f);
                sb.append(" reason=");
                sb.append(str);
                sb.append(" current=");
                VFXController$stopAnim$1$.ExternalSyntheticOutline0.m(sb, currentFrontBlurAmount, "ScrimBlurStateUtils-->", "SystemUi--Keyguard");
                return;
            }
            return;
        }
        if (!StringsKt__StringsJVMKt.startsWith(str, REASON_CLEAR_FRONT_SCRIM_WHEN_UNLOCK, false)) {
            ImmersiveCardControl immersiveCardControl = (ImmersiveCardControl) this.immersiveCardControl.get();
            if (immersiveCardControl.currImmersiveBgType((ILockScreenExtStateController.ExtStateEvent) ((ILockScreenExtStateController) immersiveCardControl.lockScreenExtStateController.get()).getExtStateEvent().getValue()) != null) {
                if (((KeyguardStateController) this.keyguardStateController.get()).mPrimaryBouncerShowing) {
                    float f2 = 1.0f - f;
                    KgdLogUtil.i(TAG, "updateKeyguardViewAlpha1:" + f2);
                    ((OplusImmersiveViewManager) this.immersiveViewManager.get()).updateKeyguardViewAlpha(f2);
                } else if (!((KeyguardStateController) this.keyguardStateController.get()).mPrimaryBouncerShowing) {
                    KgdLogUtil.i(TAG, "updateKeyguardViewAlpha3:" + ((OplusImmersiveViewManager) this.immersiveViewManager.get()).getKeyguardViewAlpha());
                    ((OplusImmersiveViewManager) this.immersiveViewManager.get()).updateKeyguardViewAlpha(((OplusImmersiveViewManager) this.immersiveViewManager.get()).getKeyguardViewAlpha());
                }
            }
        }
        ViewBlurProxy.BlurType lastBlurType = Intrinsics.areEqual(str, "refreshFrontDrawable") ? ((ScrimController) this.scrimController.get()).getScrimInFront().getExt().getLastBlurType() : getFrontScrimBlurType();
        float targetAlpha = ((ScrimController) this.scrimController.get()).getScrimInFront().getExt().getTargetAlpha();
        if (f == 0.0f || f == 1.0f) {
            KgdLogUtil.i(TAG, "setFrontScrimBlurAmount: " + f + " from " + str + " for " + lastBlurType + " targetAlpha = " + targetAlpha + " frontalpha = " + ((ScrimController) this.scrimController.get()).getScrimInFront().getAlpha());
        }
        if (Intrinsics.areEqual(lastBlurType, ViewBlurProxy.BlurType.BlurTypeBlendWallpaper.INSTANCE)) {
            ((ScrimController) this.scrimController.get()).getScrimInFront().getExt().setBlurAmount(f, "setFrontScrimBlurAmount-BlurTypeBlendWallpaper");
            ((ScrimController) this.scrimController.get()).getScrimInFront().setViewAlpha(targetAlpha * f);
        } else if (Intrinsics.areEqual(lastBlurType, ViewBlurProxy.BlurType.BlurTypePlatformStatic.INSTANCE)) {
            ViewBlurProxy viewBlurProxy = ((ScrimController) this.scrimController.get()).getScrimInFront().getExt().getViewBlurProxy();
            if (viewBlurProxy != null && (blurConfig2 = viewBlurProxy.getBlurConfig()) != null) {
                blurConfig2.setBlurRadius(getBouncerScrimMaxBlurRadius());
            }
            ((ScrimController) this.scrimController.get()).getScrimInFront().getExt().setBlurAmount(f, "setFrontScrimBlurAmount-BlurTypePlatformStatic");
            ((ScrimController) this.scrimController.get()).getScrimInFront().setViewAlpha(targetAlpha * f);
        } else {
            boolean zAreEqual = Intrinsics.areEqual(lastBlurType, ViewBlurProxy.BlurType.BlurTypeWindowBg.INSTANCE);
            Lazy lazy = this.scrimController;
            if (zAreEqual) {
                ((ScrimController) lazy.get()).getScrimInFront().getExt().setBlurAmount(f, "setFrontScrimBlurAmount-BlurTypeWindowBg");
                ((ScrimController) this.scrimController.get()).getScrimInFront().setViewAlpha(targetAlpha * f);
            } else {
                ViewBlurProxy viewBlurProxy2 = ((ScrimController) lazy.get()).getScrimInFront().getExt().getViewBlurProxy();
                if (viewBlurProxy2 != null && (blurConfig = viewBlurProxy2.getBlurConfig()) != null) {
                    blurConfig.setBlurColor(-16777216);
                }
                float f3 = targetAlpha * f;
                ((ScrimController) this.scrimController.get()).getScrimInFront().setAlpha(f3);
                ((ScrimController) this.scrimController.get()).getScrimInFront().setViewAlpha(f3);
            }
        }
        setCurrentFrontBlurAmount(f);
    }

    public void setFrontScrimBlurAnimRunning(boolean z) {
        this.frontScrimBlurAnimRunning = z;
    }

    public void setKeyguardPanelAlpha(float f) {
        if (((OplusSeparateNotificationAndQSEx) this.separateNotificationAndQSExImpl.get()).enableSeparateNotificationAndQS()) {
            ((OplusSeparateNotificationAndQSEx) this.separateNotificationAndQSExImpl.get()).setPanelViewAlpha(f);
            return;
        }
        ViewGroup npv = ((ShadeSurface) this.shadeSurface.get()).getNpv();
        ((OplusSeparateNotificationAndQSEx) this.separateNotificationAndQSExImpl.get()).setPanelViewAlpha(1.0f);
        if (npv != null) {
            npv.setAlpha(f);
        }
    }

    public void setKeyguardPanelAlphaForFbe(float f) {
        if (KeyguardUtils.Companion.isSystemFbeMode((Context) this.context.get())) {
            setKeyguardPanelAlpha(f);
        }
    }

    public void startFrontScrimBlurAnim(float f, long j, final ScrimBlurStateUtilEx.ScrimBlurAnimListener scrimBlurAnimListener) {
        if (getFrontScrimBlurAnimRunning()) {
            KgdLogUtil.i(TAG, "startFrontScrimBlurAnim but scrimFrontanim isRunning");
            return;
        }
        if (couldFrontScrimBlurAnim()) {
            setFrontScrimBlurAnimRunning(true);
            ((ScrimControllerEx) this.scrimControllerExImp.get()).getScrimFrontAlpha();
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(getCurrentFrontBlurAmount(), f);
            valueAnimatorOfFloat.setDuration(j);
            C02981 c02981 = new C02981();
            c02981.this$0 = this;
            VarHandle.storeStoreFence();
            valueAnimatorOfFloat.addUpdateListener(c02981);
            valueAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.oplus.systemui.statusbar.phone.ScrimBlurStateUtilsExImpl.startFrontScrimBlurAnim.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationCancel(Animator animator) {
                    ScrimBlurStateUtilsExImpl.this.setFrontScrimBlurAnimRunning(false);
                    super.onAnimationCancel(animator);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    ScrimBlurStateUtilsExImpl.this.setFrontScrimBlurAnimRunning(false);
                    ScrimBlurStateUtilEx.ScrimBlurAnimListener scrimBlurAnimListener2 = scrimBlurAnimListener;
                    if (scrimBlurAnimListener2 != null) {
                        scrimBlurAnimListener2.onAnimEnd();
                    }
                }
            });
            valueAnimatorOfFloat.start();
            KgdLogUtil.i(TAG, "startFrontScrimBlurAnim-exit");
        }
    }
}
