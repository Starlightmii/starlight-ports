package com.oplus.systemui.panelanimation.platformblur;

import android.content.Context;
import android.os.Handler;
import android.support.v4.content.FileProvider$;
import androidx.car.app.serialization.Bundler$;
import com.android.compose.nestedscroll.PriorityNestedScrollConnection$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.launcher3.icons.IconProvider$;
import com.android.systemui.panelanimation.platformblur.PlatformBlurManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.statusbar.phone.ScrimController;
import com.android.systemui.statusbar.phone.ScrimState;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.util.Assert;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplus.posteffect.Blur;
import com.oplus.systemui.blur.GaussBlurUtils;
import com.oplus.systemui.keyguard.OplusKeyguardViewMediatorExImpl;
import com.oplus.systemui.keyguard.anim.OplusWallpaperAnimControllerImpl;
import com.oplus.systemui.notification.aod.OplusPanoramicAodCoreStartable;
import com.oplus.systemui.notification.aod.OplusPanoramicAodRepository;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import com.oplus.systemui.qs.base.res.BaseQSResPool;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PlatformBlurManagerImpl extends PlatformBlurManager {
    public static final Companion Companion = new Companion();
    private static final String TAG = "PlatformBlurManagerImpl";
    private final MutableStateFlow _forceStopMotionBlurRefresh;
    private final MutableStateFlow _useMotionBlur;
    private final Lazy aodRepository;
    private final Runnable blurTimeOut;
    private final Context context;
    private volatile PlatformBlurManager.PlatformBlurMode currentPlatformBlurMode;
    private final StateFlow forceStopMotionBlurRefresh;
    private Boolean isBlurResumed;
    private final Lazy keyguardStateController;
    private final Handler mainHandler;
    private final MaterialBlurStateManager materialBlurStateManager;
    private final Lazy oplusKeyguardViewMediatorExImpl;
    private final Lazy oplusWallpaperAnimControllerImpl;
    public boolean panelExpandBoost;
    private final PowerInteractor powerInteractor;
    private final CoroutineScope scope;
    private final Lazy scrimController;
    private final StatusBarStateController statusBarStateController;
    public boolean timeoutNeedPauseBlur;
    private final StateFlow useMotionBlur;
    public boolean volumeBlurActive;
    public boolean volumeSideIdle;

    /* JADX INFO: renamed from: com.oplus.systemui.panelanimation.platformblur.PlatformBlurManagerImpl$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.panelanimation.platformblur.PlatformBlurManagerImpl$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class C00391 implements FlowCollector {
            public final /* synthetic */ int $r8$classId;
            public /* synthetic */ PlatformBlurManagerImpl this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.panelanimation.platformblur.PlatformBlurManagerImpl$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00401 extends SuspendLambda implements Function2 {
                final /* synthetic */ boolean $it;
                int label;
                final /* synthetic */ PlatformBlurManagerImpl this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00401(PlatformBlurManagerImpl platformBlurManagerImpl, boolean z, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = platformBlurManagerImpl;
                    this.$it = z;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C00401(this.this$0, this.$it, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    this.this$0.handleTimeoutPauseBlur(false);
                    this.this$0.updatePlatformBlurState("powerInteractor.isAwake(" + this.$it + ")");
                    return Unit.INSTANCE;
                }
            }

            public /* synthetic */ C00391(int i) {
                this.$r8$classId = i;
            }

            public final Object emit(Object obj, Continuation continuation) {
                switch (this.$r8$classId) {
                    case 0:
                        Object objWithContext = BuildersKt.withContext(Dispatchers.getMain().getImmediate(), new C00401(this.this$0, ((Boolean) obj).booleanValue(), null), continuation);
                        return objWithContext == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
                    default:
                        PlatformBlurManagerImpl platformBlurManagerImpl = this.this$0;
                        LogUtil.i$default(LogUtil.TAG_COMMON, PlatformBlurManagerImpl.TAG, "updatePlatformBlur: " + ((Pair) obj) + ", currentPlatformBlurMode=" + platformBlurManagerImpl.currentPlatformBlurMode, null, 8);
                        Object objWithContext2 = BuildersKt.withContext(Dispatchers.getMain().getImmediate(), new PlatformBlurManagerImpl$3$2$1(platformBlurManagerImpl, null), continuation);
                        return objWithContext2 == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext2 : Unit.INSTANCE;
                }
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return PlatformBlurManagerImpl.this.new AnonymousClass1(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flowIsAwake = PlatformBlurManagerImpl.this.getPowerInteractor().isAwake();
                PlatformBlurManagerImpl platformBlurManagerImpl = PlatformBlurManagerImpl.this;
                C00391 c00391 = new C00391(0);
                c00391.this$0 = platformBlurManagerImpl;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (flowIsAwake.collect(c00391, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.panelanimation.platformblur.PlatformBlurManagerImpl$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements OplusPanoramicAodRepository.PanoramicAodProgressChangeListener {
        public /* synthetic */ PlatformBlurManagerImpl this$0;

        @Override // com.oplus.systemui.notification.aod.OplusPanoramicAodRepository.PanoramicAodProgressChangeListener
        public final void onPanoramicAodStateChanged(boolean z, boolean z2) {
            this.this$0.updatePlatformBlurState(IconProvider$.ExternalSyntheticOutline0.m("onPanoramicAodState.isAod(", OplusPreventModeData.SEPARATOR, ")", z, z2));
        }

        @Override // com.oplus.systemui.notification.aod.OplusPanoramicAodRepository.PanoramicAodProgressChangeListener
        public final void onProgressChanged(float f) {
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.panelanimation.platformblur.PlatformBlurManagerImpl$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass3 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.panelanimation.platformblur.PlatformBlurManagerImpl$3$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass1 extends SuspendLambda implements Function3 {
            /* synthetic */ boolean Z$0;
            /* synthetic */ boolean Z$1;
            int label;

            public final Object invoke(Object obj, Object obj2, Object obj3) {
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                boolean zBooleanValue2 = ((Boolean) obj2).booleanValue();
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(3, (Continuation) obj3);
                anonymousClass1.Z$0 = zBooleanValue;
                anonymousClass1.Z$1 = zBooleanValue2;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                boolean z = this.Z$0;
                boolean z2 = this.Z$1;
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    return new Pair(Boxing.boxBoolean(z), Boxing.boxBoolean(z2));
                }
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
        }

        public AnonymousClass3(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return PlatformBlurManagerImpl.this.new AnonymousClass3(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            int i2 = 1;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flowCombine = FlowKt.combine(PlatformBlurManagerImpl.this.materialBlurStateManager.getEnableMaterialBlur(), PlatformBlurManagerImpl.this.forceStopMotionBlurRefresh, new AnonymousClass1(3, null));
                PlatformBlurManagerImpl platformBlurManagerImpl = PlatformBlurManagerImpl.this;
                AnonymousClass1.C00391 c00391 = new AnonymousClass1.C00391(i2);
                c00391.this$0 = platformBlurManagerImpl;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (flowCombine.collect(c00391, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.panelanimation.platformblur.PlatformBlurManagerImpl$4, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass4 implements StatusBarStateController.StateListener {
        public /* synthetic */ PlatformBlurManagerImpl this$0;

        public final void onStateChanged(int i) {
            PlatformBlurManagerImpl platformBlurManagerImpl = this.this$0;
            platformBlurManagerImpl.handleTimeoutPauseBlur(false);
            platformBlurManagerImpl.updatePlatformBlurState("onStateChanged-" + i);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public PlatformBlurManagerImpl(Context context, Lazy lazy, Lazy lazy2, PowerInteractor powerInteractor, CoroutineScope coroutineScope, MaterialBlurStateManager materialBlurStateManager, Handler handler, StatusBarStateController statusBarStateController, Lazy lazy3, Lazy lazy4, Lazy lazy5) {
        this.context = context;
        this.scrimController = lazy;
        this.keyguardStateController = lazy2;
        this.powerInteractor = powerInteractor;
        this.scope = coroutineScope;
        this.materialBlurStateManager = materialBlurStateManager;
        this.mainHandler = handler;
        this.statusBarStateController = statusBarStateController;
        this.aodRepository = lazy3;
        this.oplusWallpaperAnimControllerImpl = lazy4;
        this.oplusKeyguardViewMediatorExImpl = lazy5;
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this._forceStopMotionBlurRefresh = MutableStateFlow;
        this.forceStopMotionBlurRefresh = FlowKt.asStateFlow(MutableStateFlow);
        MutableStateFlow MutableStateFlow2 = StateFlowKt.MutableStateFlow(Boolean.valueOf(useMotionBlur()));
        this._useMotionBlur = MutableStateFlow2;
        this.useMotionBlur = FlowKt.asStateFlow(MutableStateFlow2);
        PlatformBlurManagerImpl$blurTimeOut$1 platformBlurManagerImpl$blurTimeOut$1 = new PlatformBlurManagerImpl$blurTimeOut$1();
        platformBlurManagerImpl$blurTimeOut$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.blurTimeOut = platformBlurManagerImpl$blurTimeOut$1;
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(null), 3);
        OplusPanoramicAodRepository oplusPanoramicAodRepository = (OplusPanoramicAodRepository) lazy3.get();
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        oplusPanoramicAodRepository.addCallback(anonymousClass2);
        BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass3(null), 3);
        AnonymousClass4 anonymousClass4 = new AnonymousClass4();
        anonymousClass4.this$0 = this;
        VarHandle.storeStoreFence();
        statusBarStateController.addCallback(anonymousClass4);
    }

    public final void applyVolumeMotionFrequency() {
        PlatformBlurManager.PlatformBlurMode platformBlurMode = ((Boolean) getUseMotionBlur().getValue()).booleanValue() ? PlatformBlurManager.PlatformBlurMode.MOTION_60 : PlatformBlurManager.PlatformBlurMode.STATIC;
        int blurFrequency = platformBlurMode.getBlurFrequency(this);
        Context applicationContext = this.context.getApplicationContext();
        if (!ScrimUtil.isBAnimLevel()) {
            Blur.setBlurFrequency(applicationContext, blurFrequency);
        }
        boolean z = this.volumeBlurActive;
        boolean z2 = this.volumeSideIdle;
        StringBuilder sb = new StringBuilder("applyVolumeMotionFrequency: mode=");
        sb.append(platformBlurMode);
        sb.append("(frequency=");
        sb.append(blurFrequency);
        sb.append("),volumeBlurActive=");
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m(sb, z, ",volumeSideIdle=", z2), null, 8);
    }

    public void ensureVolumeBlurResumed(String str) {
        Assert.isMainThread();
        if (this.volumeBlurActive) {
            resumeBlur("volume active: " + str);
            applyVolumeMotionFrequency();
            LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "ensureVolumeBlurResumed: " + str, null, 8);
        }
    }

    public final void forceResumeForVolume(String str) {
        if (GaussBlurUtils.isGaussBlurDisabled() || (ScrimUtil.isLowGaussianLevel(this.context) && !ScrimUtil.isLightOsGaussianLevel())) {
            LogUtil.w$default(8, LogUtil.TAG_COMMON, TAG, "forceResumeForVolume skipped, unsupportBlur: ".concat(str));
            return;
        }
        Context context = this.context;
        if (!ScrimUtil.isBAnimLevel()) {
            Blur.pauseWindowBlur(context);
        }
        this.isBlurResumed = Boolean.TRUE;
        Context context2 = this.context;
        if (!ScrimUtil.isBAnimLevel()) {
            Blur.resumeWindowBlur(context2);
        }
        applyVolumeMotionFrequency();
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "forceResumeForVolume: ".concat(str), null, 8);
    }

    public final void forceStopMotionBlurRefresh(boolean z) {
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("forceStopMotionBlur: stop=", z), null, 8);
        if (((Boolean) this._forceStopMotionBlurRefresh.getValue()).booleanValue() != z) {
            this._forceStopMotionBlurRefresh.setValue(Boolean.valueOf(z));
        }
    }

    public final Lazy getAodRepository() {
        return this.aodRepository;
    }

    public final Context getContext() {
        return this.context;
    }

    public final Lazy getKeyguardStateController() {
        return this.keyguardStateController;
    }

    public final PowerInteractor getPowerInteractor() {
        return this.powerInteractor;
    }

    public final CoroutineScope getScope() {
        return this.scope;
    }

    public final Lazy getScrimController() {
        return this.scrimController;
    }

    public final StatusBarStateController getStatusBarStateController() {
        return this.statusBarStateController;
    }

    public StateFlow getUseMotionBlur() {
        return this.useMotionBlur;
    }

    public final void handleTimeoutPauseBlur(boolean z) {
        if (this.mainHandler.hasCallbacks(this.blurTimeOut)) {
            this.mainHandler.removeCallbacks(this.blurTimeOut);
        }
        if (z && this.statusBarStateController.getState() == 0) {
            this.mainHandler.postDelayed(this.blurTimeOut, 600000L);
        } else {
            this.timeoutNeedPauseBlur = false;
        }
    }

    public final void pauseBlur(String str, boolean z) {
        if (this.volumeBlurActive && !z) {
            LogUtil.i$default(LogUtil.TAG_COMMON, TAG, FileProvider$.ExternalSyntheticOutline0.m("pauseBlur skipped while volumeBlurActive: ", str), null, 8);
            ensureVolumeBlurResumed("pauseBlur-skipped");
            return;
        }
        Boolean bool = this.isBlurResumed;
        Boolean bool2 = Boolean.FALSE;
        if (Intrinsics.areEqual(bool, bool2)) {
            return;
        }
        this.isBlurResumed = bool2;
        Context context = this.context;
        if (!ScrimUtil.isBAnimLevel()) {
            Blur.pauseWindowBlur(context);
        }
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, Bundler$.ExternalSyntheticOutline0.m("pauseBlur: ", str, "."), null, 8);
    }

    public final void resumeBlur(String str) {
        Boolean bool = this.isBlurResumed;
        Boolean bool2 = Boolean.TRUE;
        if (Intrinsics.areEqual(bool, bool2)) {
            return;
        }
        this.isBlurResumed = bool2;
        Context context = this.context;
        if (!ScrimUtil.isBAnimLevel()) {
            Blur.resumeWindowBlur(context);
        }
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, Bundler$.ExternalSyntheticOutline0.m("resumeBlur: ", str, "."), null, 8);
    }

    public void setPlatformBlurMode(PlatformBlurManager.PlatformBlurMode platformBlurMode) {
        int blurFrequency;
        Assert.isMainThread();
        this.currentPlatformBlurMode = platformBlurMode;
        if (this.volumeBlurActive) {
            blurFrequency = (((Boolean) getUseMotionBlur().getValue()).booleanValue() ? PlatformBlurManager.PlatformBlurMode.MOTION_60 : PlatformBlurManager.PlatformBlurMode.STATIC).getBlurFrequency(this);
        } else {
            blurFrequency = (getSupportBlurRefresh() && this.panelExpandBoost) ? PlatformBlurManager.PlatformBlurMode.MOTION_120.getBlurFrequency(this) : platformBlurMode.getBlurFrequency(this);
        }
        Context applicationContext = this.context.getApplicationContext();
        if (!ScrimUtil.isBAnimLevel()) {
            Blur.setBlurFrequency(applicationContext, blurFrequency);
        }
        boolean z = this.volumeBlurActive;
        boolean z2 = this.panelExpandBoost;
        StringBuilder sb = new StringBuilder("setPlatformBlurMode: blurMode=");
        sb.append(platformBlurMode);
        sb.append("(blurFrequency=");
        sb.append(blurFrequency);
        sb.append("),volumeBlurActive=");
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m(sb, z, ",panelExpandBoost=", z2), null, 8);
    }

    public void setVolumeBlurActive(boolean z) {
        Assert.isMainThread();
        if (this.volumeBlurActive == z) {
            if (z) {
                forceResumeForVolume("setVolumeBlurActive-same");
                return;
            }
            return;
        }
        this.volumeBlurActive = z;
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("setVolumeBlurActive: ", z), null, 8);
        if (z) {
            this.volumeSideIdle = false;
            forceResumeForVolume("setVolumeBlurActive-true");
            return;
        }
        this.volumeSideIdle = false;
        updatePlatformBlurState("setVolumeBlurActive-false");
        PlatformBlurManager.PlatformBlurMode platformBlurMode = this.currentPlatformBlurMode;
        if (platformBlurMode != null) {
            setPlatformBlurMode(platformBlurMode);
        }
    }

    public void setVolumeSideIdleBlur(boolean z) {
        Assert.isMainThread();
        if (this.volumeSideIdle == z) {
            return;
        }
        this.volumeSideIdle = z;
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("setVolumeSideIdleBlur: ", ", volumeBlurActive=", z, this.volumeBlurActive), null, 8);
        if (this.volumeBlurActive) {
            applyVolumeMotionFrequency();
        }
    }

    public final void updateMotionFrequency(boolean z) {
        if (this.panelExpandBoost == z) {
            return;
        }
        if (!z) {
            forceStopMotionBlurRefresh(false);
        }
        this.panelExpandBoost = z;
        String strM = KeyguardLogger$.ExternalSyntheticOutline0.m("panelExpandBoost=", z);
        if (this.volumeBlurActive) {
            applyVolumeMotionFrequency();
        } else {
            PlatformBlurManager.PlatformBlurMode platformBlurMode = this.currentPlatformBlurMode;
            if (platformBlurMode != null) {
                setPlatformBlurMode(platformBlurMode);
            }
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            KeyguardSettingProvider$.ExternalSyntheticOutline0.m("reapplyBlurFrequency: ", strM, "PlatformBlurManagerImpl-->", "SystemUi--Common");
        }
        LogUtil.i$default(LogUtil.TAG_COMMON, TAG, PriorityNestedScrollConnection$.ExternalSyntheticOutline0.m("updateMotionFrequency: panelExpandBoost=", ",volumeBlurActive=", z, this.volumeBlurActive), null, 8);
    }

    public void updatePlatformBlurForLargeScreen(boolean z, String str) {
        if (z) {
            pauseBlur(str, false);
        } else {
            resumeBlur(str);
        }
    }

    public void updatePlatformBlurState(String str) {
        Assert.isMainThread();
        boolean zUseMotionBlur = useMotionBlur();
        if (((Boolean) this._useMotionBlur.getValue()).booleanValue() != zUseMotionBlur) {
            LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "_useMotionBlurOld:" + this._useMotionBlur.getValue() + " useMotionBlurNew:" + zUseMotionBlur, null, 8);
            this._useMotionBlur.setValue(Boolean.valueOf(zUseMotionBlur));
            BaseQSResPool.updateMirrorScaleValue(zUseMotionBlur ? 0.94f : 1.0f);
        }
        boolean zIsGaussBlurDisabled = GaussBlurUtils.isGaussBlurDisabled();
        boolean z = ScrimUtil.isLowGaussianLevel(this.context) && !ScrimUtil.isLightOsGaussianLevel();
        if (zIsGaussBlurDisabled || z) {
            StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("updatePlatformBlurState(", str, "): unsupportBlur(isGaussBlurDisabled=", zIsGaussBlurDisabled, ",isLowGaussianLevel=");
            sbM.append(z);
            sbM.append(")");
            pauseBlur(sbM.toString(), true);
            return;
        }
        boolean qsBottomVisible = ((ScrimController) this.scrimController.get()).getQsBottomVisible();
        boolean z2 = ((ScrimController) this.scrimController.get()).getState() == ScrimState.SHADE_LOCKED;
        boolean zIsVisible = ((KeyguardStateController) this.keyguardStateController.get()).isVisible();
        boolean z3 = ((OplusWallpaperAnimControllerImpl) this.oplusWallpaperAnimControllerImpl.get()).isLiveWallpaperShownForLockscreen;
        if ("kgdOneshotAnim".equals(str) && zIsVisible && !z3) {
            zIsVisible = !((OplusKeyguardViewMediatorExImpl) this.oplusKeyguardViewMediatorExImpl.get()).oneShotAnimRunning();
        }
        boolean z4 = zIsVisible || qsBottomVisible || z2;
        boolean z5 = ((OplusPanoramicAodRepository) this.aodRepository.get()).getShouldUpdateUi() && ((OplusPanoramicAodCoreStartable) ((OplusPanoramicAodRepository) this.aodRepository.get())).isPanoramicAod;
        boolean z6 = ((OplusPanoramicAodCoreStartable) ((OplusPanoramicAodRepository) this.aodRepository.get())).isPanoramicAod && !((OplusPanoramicAodRepository) this.aodRepository.get()).getShouldUpdateUi();
        boolean z7 = this.timeoutNeedPauseBlur;
        boolean z8 = z7 && !this.volumeBlurActive;
        if ((!z4 && !z5 && !this.volumeBlurActive) || z8 || z6) {
            StringBuilder sbM2 = KeyguardLogger$.ExternalSyntheticOutline0.m("updatePlatformBlurState(", str, "): should not blur, volumeBlurActive=", this.volumeBlurActive, ",timeoutNeedPauseBlur=");
            sbM2.append(z7);
            pauseBlur(sbM2.toString(), false);
            return;
        }
        boolean z9 = this.volumeBlurActive;
        StringBuilder sbM3 = KeyguardLogger$.ExternalSyntheticOutline0.m("updatePlatformBlurState(", str, "): shouldBlur, isKeyguardVisible=", zIsVisible, ", isPanelVisible=");
        KeyguardLogger$.ExternalSyntheticOutline0.m(", isShadeLock=", ",inPanel=", sbM3, qsBottomVisible, z2);
        KeyguardLogger$.ExternalSyntheticOutline0.m(",isAodUiState=", ",volumeBlurActive=", sbM3, z4, z5);
        sbM3.append(z9);
        resumeBlur(sbM3.toString());
        if (this.volumeBlurActive) {
            applyVolumeMotionFrequency();
        }
    }

    public final boolean useMotionBlur() {
        boolean z = false;
        boolean z2 = (ScrimUtil.isHighGaussianLevel(this.context) || "true".equals(ScrimUtil.ANIM_DYNAMIC_BLUR)) && ((Boolean) this.materialBlurStateManager.getEnableMaterialBlur().getValue()).booleanValue() && !GaussBlurUtils.isGaussBlurDisabled();
        if (!((Boolean) this._forceStopMotionBlurRefresh.getValue()).booleanValue() && z2) {
            z = true;
        }
        setSupportBlurRefresh(z);
        return z2;
    }
}
