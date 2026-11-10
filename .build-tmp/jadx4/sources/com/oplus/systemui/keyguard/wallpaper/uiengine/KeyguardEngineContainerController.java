package com.oplus.systemui.keyguard.wallpaper.uiengine;

import android.content.ComponentName;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.OplusBaseConfigurationAddon;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.os.Trace;
import android.os.customize.OplusCustomizeRestrictionManager;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import androidx.collection.FloatList$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.RepeatOnLifecycleKt;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.base.HostCallback;
import com.android.keyguard.base.OplusKeyguardCtrl;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.Dumpable;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.keyguard.shared.model.ClockSize;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.lifecycle.UnlockViewLifecycleOwner$;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.statusbar.OlockStateUtil$;
import com.android.systemui.statusbar.OplusLockscreenShadeTransitionControllerEx;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.oplus.keyguard.OplusLockIconViewControllerExImpl;
import com.oplus.keyguard.settings.proxy.KeyguardSettingBackupRestoreProxy$;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.keyguard.data.personalise.observer.KeyguardStyleClockEnabledSettingsObserver;
import com.oplus.systemui.keyguard.ui.viewmodel.OplusKeyguardClockViewModel;
import com.oplus.systemui.keyguard.wallpaper.observer.DefaultKeyguardInSettingsObserver;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator;
import com.oplus.systemui.keyguard.watcher.OplusKeyguardWatcher;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KeyguardEngineContainerController implements Dumpable {
    public static final Companion Companion = new Companion();
    private static final String KEYGUARD_WALLPAPER_STATISTICS_SETTINGS = "keyguard_wallpaper_statistics_settings";
    private static final String LOG_LIMIT_TYPE = "oplus_keyguard_show";
    private static final String OPLUS_UIENGINE_SUPPORT_PICTORIAL = "oplus_uiengine_support_pictorial";
    private static final String TAG = "KeyguardEngineContainer";
    private final ConfigurationController configurationController;
    private final Lazy defaultKeyguardInSettingsObserver;
    private final ContentObserver defaultSettingsObserver;
    private final DumpManager dumpManager;
    private final Lazy keyguardClockViewModel;
    private final Lazy keyguardInteractor;
    private final Lazy keyguardViewMediator;
    private final Lazy lockIconViewController;
    private final Lazy lockscreenShadeTransitionControllerEx;
    private final ActivityStarter mActivityStarter;
    public boolean mBouncerShow;
    private final Context mContext;
    public float mCurrentProcessY;
    public int mDensity;
    private final Handler mHandler;
    private final HostCallback mHostCallback;
    public boolean mIsQsExpanded;
    private final KeyguardEngineContainer mKeyguardEngineContainer;
    private KeyguardPluginManager mKeyguardPluginManager;
    private final KeyguardStateController mKeyguardStateControllerImpl;
    private final StatusBarKeyguardViewManager.KeyguardViewManagerCallback mKeyguardViewManagerCallback;
    public int mLastFlipFont;
    private String mLastLocale;
    public volatile boolean mLoadOplusKeyguardSuccess;
    private final KeyguardUpdateMonitorCallback mMonitorCallback;
    private volatile OplusKeyguardCtrl mOplusKeyguard;
    public boolean mReloadOplusKeyguardLater;
    private Runnable mReloadOplusKeyguardTask;
    private final ScreenLifecycle.Observer mScreenObserver;
    public int mStatusBarState;
    private final ContentObserver mUiEngineSupportObserver;
    private final KeyguardUpdateMonitor mUpdateMonitor;
    public boolean mUserSwitching;
    public int mViewFlag;
    private final WakefulnessLifecycle.Observer mWakefulnessObserver;
    private final Lazy statusBarKeyguardViewManagerLazy;

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.wallpaper.uiengine.KeyguardEngineContainerController$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function3 {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.keyguard.wallpaper.uiengine.KeyguardEngineContainerController$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ KeyguardEngineContainerController this$0;

            /* JADX INFO: renamed from: com.oplus.systemui.keyguard.wallpaper.uiengine.KeyguardEngineContainerController$2$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
            final class C00001 extends SuspendLambda implements Function2 {
                Object L$0;
                int label;
                final /* synthetic */ KeyguardEngineContainerController this$0;

                /* JADX INFO: renamed from: com.oplus.systemui.keyguard.wallpaper.uiengine.KeyguardEngineContainerController$2$1$1$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
                public final class C00011 implements FlowCollector {
                    public /* synthetic */ KeyguardEngineContainerController this$0;

                    public final Object emit(Object obj, Continuation continuation) {
                        int iIntValue = ((Number) obj).intValue();
                        OplusLockIconView$.ExternalSyntheticOutline0.m(iIntValue, "keyguardStateFlow.collect: mStatusBarState=", KeyguardEngineContainerController.TAG);
                        KeyguardEngineContainerController keyguardEngineContainerController = this.this$0;
                        keyguardEngineContainerController.mStatusBarState = iIntValue;
                        KeyguardEngineContainerController.access$updateVisibilityState(keyguardEngineContainerController, iIntValue == 1);
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00001(KeyguardEngineContainerController keyguardEngineContainerController, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = keyguardEngineContainerController;
                }

                public final Continuation create(Object obj, Continuation continuation) {
                    return new C00001(this.this$0, continuation);
                }

                public final Object invoke(Object obj, Object obj2) {
                    return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        StateFlow statusBarState = ((KeyguardInteractor) this.this$0.keyguardInteractor.get()).getStatusBarState();
                        StateFlow lockscreenAnimFraction = ((OplusLockscreenShadeTransitionControllerEx) this.this$0.lockscreenShadeTransitionControllerEx.get()).getLockscreenAnimFraction();
                        KeyguardEngineContainerController$2$1$1$invokeSuspend$$inlined$map$1 keyguardEngineContainerController$2$1$1$invokeSuspend$$inlined$map$1 = new KeyguardEngineContainerController$2$1$1$invokeSuspend$$inlined$map$1();
                        keyguardEngineContainerController$2$1$1$invokeSuspend$$inlined$map$1.$this_unsafeTransform$inlined = lockscreenAnimFraction;
                        VarHandle.storeStoreFence();
                        Flow flowDistinctUntilChanged = FlowKt.distinctUntilChanged(FlowKt.combine(statusBarState, FlowKt.distinctUntilChanged(keyguardEngineContainerController$2$1$1$invokeSuspend$$inlined$map$1), new KeyguardEngineContainerController$2$1$1$keyguardStateFlow$2(3, null)));
                        KeyguardEngineContainerController keyguardEngineContainerController = this.this$0;
                        C00011 c00011 = new C00011();
                        c00011.this$0 = keyguardEngineContainerController;
                        VarHandle.storeStoreFence();
                        this.L$0 = SpillingKt.nullOutSpilledVariable(flowDistinctUntilChanged);
                        this.label = 1;
                        if (flowDistinctUntilChanged.collect(c00011, this) == coroutine_suspended) {
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

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(KeyguardEngineContainerController keyguardEngineContainerController, Continuation continuation) {
                super(2, continuation);
                this.this$0 = keyguardEngineContainerController;
            }

            public final Continuation create(Object obj, Continuation continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            public final Object invoke(Object obj, Object obj2) {
                return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new C00001(this.this$0, null), 3);
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass2(Continuation continuation) {
            super(3, continuation);
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass2 anonymousClass2 = KeyguardEngineContainerController.this.new AnonymousClass2((Continuation) obj3);
            anonymousClass2.L$0 = (LifecycleOwner) obj;
            return anonymousClass2.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            LifecycleOwner lifecycleOwner = (LifecycleOwner) this.L$0;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Lifecycle.State state = Lifecycle.State.CREATED;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(KeyguardEngineContainerController.this, null);
                this.L$0 = SpillingKt.nullOutSpilledVariable(lifecycleOwner);
                this.label = 1;
                if (RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner, state, anonymousClass1, this) == coroutine_suspended) {
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

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public static String $r8$lambda$TqdGxED_X7pbuiwgq5RBzhSTSaY(KeyguardEngineContainerController keyguardEngineContainerController) {
        boolean zIsKeyguardVisible$1 = keyguardEngineContainerController.mUpdateMonitor.isKeyguardVisible$1();
        int i = keyguardEngineContainerController.mStatusBarState;
        boolean z = !keyguardEngineContainerController.mBouncerShow;
        KeyguardUtils.Companion companion = KeyguardUtils.Companion;
        float f = keyguardEngineContainerController.mCurrentProcessY;
        companion.getClass();
        boolean zIsFloatEqual = KeyguardUtils.Companion.isFloatEqual(f, 1.0f);
        boolean zIsScreenOn$3 = isScreenOn$3();
        boolean z2 = !keyguardEngineContainerController.mIsQsExpanded;
        ClockSize clockSize = ((OplusKeyguardClockViewModel.ClockSizeState) ((OplusKeyguardClockViewModel) keyguardEngineContainerController.keyguardClockViewModel.get()).getClockSizeStateFlow().getValue()).getClockSize();
        StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m("isNeedShow: isKeyguardVisible=", i, ", mStatusBarState=", ", !mBouncerShow=", zIsKeyguardVisible$1);
        KeyguardLogger$.ExternalSyntheticOutline0.m(", mCurrentProcessYEqual_1=", ", isScreenOn=", sbM, z, zIsFloatEqual);
        KeyguardLogger$.ExternalSyntheticOutline0.m(", !mIsQsExpanded=", ", clockSize=", sbM, zIsScreenOn$3, z2);
        sbM.append(clockSize);
        return sbM.toString();
    }

    public KeyguardEngineContainerController(Context context, KeyguardEngineContainer keyguardEngineContainer, ConfigurationController configurationController, Lazy lazy, KeyguardUpdateMonitor keyguardUpdateMonitor, KeyguardStateController keyguardStateController, ActivityStarter activityStarter, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, Lazy lazy7, DumpManager dumpManager) {
        this.mContext = context;
        this.mKeyguardEngineContainer = keyguardEngineContainer;
        this.configurationController = configurationController;
        this.statusBarKeyguardViewManagerLazy = lazy;
        this.mUpdateMonitor = keyguardUpdateMonitor;
        this.mKeyguardStateControllerImpl = keyguardStateController;
        this.mActivityStarter = activityStarter;
        this.keyguardViewMediator = lazy2;
        this.keyguardInteractor = lazy3;
        this.lockscreenShadeTransitionControllerEx = lazy4;
        this.lockIconViewController = lazy5;
        this.defaultKeyguardInSettingsObserver = lazy6;
        this.keyguardClockViewModel = lazy7;
        this.dumpManager = dumpManager;
        this.mLastLocale = "";
        KeyguardEngineContainerController$mReloadOplusKeyguardTask$1 keyguardEngineContainerController$mReloadOplusKeyguardTask$1 = new KeyguardEngineContainerController$mReloadOplusKeyguardTask$1();
        keyguardEngineContainerController$mReloadOplusKeyguardTask$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mReloadOplusKeyguardTask = keyguardEngineContainerController$mReloadOplusKeyguardTask$1;
        Configuration configuration = context.getResources().getConfiguration();
        if ((configuration != null ? configuration.getLocales() : null) != null) {
            this.mLastLocale = configuration.getLocales().toLanguageTags();
        }
        if (configuration != null) {
            this.mDensity = configuration.densityDpi;
            this.mLastFlipFont = OplusBaseConfigurationAddon.getFlipFont(configuration);
        }
        this.mKeyguardPluginManager = new KeyguardPluginManager(context);
        this.mHandler = new Handler(context.getMainLooper());
        keyguardEngineContainer.setMKeyguardEngineContainerController(this);
        dumpManager.registerDumpable(this);
        RepeatWhenAttachedKt.repeatWhenAttached$default(keyguardEngineContainer, (CoroutineContext) null, new AnonymousClass2(null), 3);
        KeyguardEngineContainerController$mScreenObserver$1 keyguardEngineContainerController$mScreenObserver$1 = new KeyguardEngineContainerController$mScreenObserver$1();
        keyguardEngineContainerController$mScreenObserver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mScreenObserver = keyguardEngineContainerController$mScreenObserver$1;
        KeyguardEngineContainerController$mWakefulnessObserver$1 keyguardEngineContainerController$mWakefulnessObserver$1 = new KeyguardEngineContainerController$mWakefulnessObserver$1();
        keyguardEngineContainerController$mWakefulnessObserver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mWakefulnessObserver = keyguardEngineContainerController$mWakefulnessObserver$1;
        KeyguardEngineContainerController$mMonitorCallback$1 keyguardEngineContainerController$mMonitorCallback$1 = new KeyguardEngineContainerController$mMonitorCallback$1();
        keyguardEngineContainerController$mMonitorCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mMonitorCallback = keyguardEngineContainerController$mMonitorCallback$1;
        KeyguardEngineContainerController$mKeyguardViewManagerCallback$1 keyguardEngineContainerController$mKeyguardViewManagerCallback$1 = new KeyguardEngineContainerController$mKeyguardViewManagerCallback$1();
        keyguardEngineContainerController$mKeyguardViewManagerCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mKeyguardViewManagerCallback = keyguardEngineContainerController$mKeyguardViewManagerCallback$1;
        KeyguardEngineContainerController$mHostCallback$1 keyguardEngineContainerController$mHostCallback$1 = new KeyguardEngineContainerController$mHostCallback$1();
        keyguardEngineContainerController$mHostCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mHostCallback = keyguardEngineContainerController$mHostCallback$1;
        final Handler handler = new Handler();
        final int i = 1;
        this.mUiEngineSupportObserver = new ContentObserver(this, handler) { // from class: com.oplus.systemui.keyguard.wallpaper.uiengine.KeyguardEngineContainerController$defaultSettingsObserver$1
            public final /* synthetic */ KeyguardEngineContainerController this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                boolean z2 = false;
                switch (i) {
                    case 0:
                        boolean zIsDefaultKeyguardInSettings = KeyguardUtils.Companion.isDefaultKeyguardInSettings(this.this$0.getMContext());
                        OplusLockIconView$.ExternalSyntheticOutline0.m("defaultSettingsObserver: onChange defaultSettings = ", "KeyguardEngineContainer", zIsDefaultKeyguardInSettings);
                        KeyguardEngineContainerController keyguardEngineContainerController = this.this$0;
                        if (zIsDefaultKeyguardInSettings) {
                            keyguardEngineContainerController.removeOplusKeyguard$1();
                            KeyguardStyleClockEnabledSettingsObserver.Companion.saveKeyguardStyleClockSwitch(this.this$0.getMContext(), 1);
                            OplusKeyguardWatcher.Companion.getInstance().dispatchOplusKeyguardLoad(false, this.this$0.getMContext());
                        } else {
                            KeyguardStyleClockEnabledSettingsObserver.Companion.saveKeyguardStyleClockSwitch(keyguardEngineContainerController.getMContext(), 0);
                        }
                        OplusLockIconViewControllerExImpl oplusLockIconViewControllerExImpl = (OplusLockIconViewControllerExImpl) this.this$0.lockIconViewController.get();
                        boolean z3 = this.this$0.getMUpdateMonitor().mIsDreaming;
                        oplusLockIconViewControllerExImpl.checkUpdateStateAndVisibility(true);
                        break;
                    default:
                        KeyguardEngineContainer mKeyguardEngineContainer = this.this$0.getMKeyguardEngineContainer();
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isExpRegion() && "true".equals(Settings.System.getString(this.this$0.getMContext().getContentResolver(), "oplus_uiengine_support_pictorial"))) {
                            z2 = true;
                        }
                        mKeyguardEngineContainer.setMIsUiEngineSupportPictorial(z2);
                        OplusLockIconView$.ExternalSyntheticOutline0.m("mUiEngineSupportObserver: mIsUiEngineSupportPictorial=", "KeyguardEngineContainer", this.this$0.getMKeyguardEngineContainer().getMIsUiEngineSupportPictorial());
                        break;
                }
            }
        };
        final Handler handler2 = new Handler(Looper.getMainLooper());
        final int i2 = 0;
        this.defaultSettingsObserver = new ContentObserver(this, handler2) { // from class: com.oplus.systemui.keyguard.wallpaper.uiengine.KeyguardEngineContainerController$defaultSettingsObserver$1
            public final /* synthetic */ KeyguardEngineContainerController this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                boolean z2 = false;
                switch (i2) {
                    case 0:
                        boolean zIsDefaultKeyguardInSettings = KeyguardUtils.Companion.isDefaultKeyguardInSettings(this.this$0.getMContext());
                        OplusLockIconView$.ExternalSyntheticOutline0.m("defaultSettingsObserver: onChange defaultSettings = ", "KeyguardEngineContainer", zIsDefaultKeyguardInSettings);
                        KeyguardEngineContainerController keyguardEngineContainerController = this.this$0;
                        if (zIsDefaultKeyguardInSettings) {
                            keyguardEngineContainerController.removeOplusKeyguard$1();
                            KeyguardStyleClockEnabledSettingsObserver.Companion.saveKeyguardStyleClockSwitch(this.this$0.getMContext(), 1);
                            OplusKeyguardWatcher.Companion.getInstance().dispatchOplusKeyguardLoad(false, this.this$0.getMContext());
                        } else {
                            KeyguardStyleClockEnabledSettingsObserver.Companion.saveKeyguardStyleClockSwitch(keyguardEngineContainerController.getMContext(), 0);
                        }
                        OplusLockIconViewControllerExImpl oplusLockIconViewControllerExImpl = (OplusLockIconViewControllerExImpl) this.this$0.lockIconViewController.get();
                        boolean z3 = this.this$0.getMUpdateMonitor().mIsDreaming;
                        oplusLockIconViewControllerExImpl.checkUpdateStateAndVisibility(true);
                        break;
                    default:
                        KeyguardEngineContainer mKeyguardEngineContainer = this.this$0.getMKeyguardEngineContainer();
                        FeatureOption.INSTANCE.getClass();
                        if (!FeatureOption.isExpRegion() && "true".equals(Settings.System.getString(this.this$0.getMContext().getContentResolver(), "oplus_uiengine_support_pictorial"))) {
                            z2 = true;
                        }
                        mKeyguardEngineContainer.setMIsUiEngineSupportPictorial(z2);
                        OplusLockIconView$.ExternalSyntheticOutline0.m("mUiEngineSupportObserver: mIsUiEngineSupportPictorial=", "KeyguardEngineContainer", this.this$0.getMKeyguardEngineContainer().getMIsUiEngineSupportPictorial());
                        break;
                }
            }
        };
    }

    public static final void access$updateVisibilityState(KeyguardEngineContainerController keyguardEngineContainerController, boolean z) {
        keyguardEngineContainerController.getClass();
        keyguardEngineContainerController.setVisibility(z ? 0 : 8);
        keyguardEngineContainerController.showOrHideOplusKeyguard$1();
        keyguardEngineContainerController.onVisibilityChange$1(z);
    }

    public static boolean isScreenOn$3() {
        return ((ScreenLifecycle) Dependency.sDependency.getDependencyInner(ScreenLifecycle.class)).getScreenState() == 2;
    }

    public final boolean allowInterceptTouch() {
        if (this.mKeyguardEngineContainer.getVisibility() != 0 || this.mStatusBarState != 1 || !this.mUpdateMonitor.isKeyguardVisible$1() || !isScreenOn$3() || this.mKeyguardStateControllerImpl.mKeyguardGoingAway || this.mUserSwitching || this.mBouncerShow || this.mKeyguardEngineContainer.getMOplusKeyguardView() == null) {
            return false;
        }
        View mOplusKeyguardView = this.mKeyguardEngineContainer.getMOplusKeyguardView();
        mOplusKeyguardView.getClass();
        return mOplusKeyguardView.getVisibility() == 0;
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        OplusKeyguardCtrl oplusKeyguardCtrl = this.mOplusKeyguard;
        Object feverExeData = oplusKeyguardCtrl != null ? oplusKeyguardCtrl.getFeverExeData() : null;
        printWriter.println("KeyguardEngineContainer:");
        printWriter.println("  mLoadOplusKeyguardSuccess=" + this.mLoadOplusKeyguardSuccess);
        printWriter.println("  mOplusKeyguard=" + oplusKeyguardCtrl);
        printWriter.println("  enginePlugin=" + (oplusKeyguardCtrl != null ? oplusKeyguardCtrl.getEnginePlugin() : null));
        printWriter.println("  getFeverExeData=" + feverExeData);
    }

    public final ConfigurationController getConfigurationController() {
        return this.configurationController;
    }

    public final Lazy getKeyguardViewMediator() {
        return this.keyguardViewMediator;
    }

    public final ActivityStarter getMActivityStarter() {
        return this.mActivityStarter;
    }

    public final Context getMContext() {
        return this.mContext;
    }

    public final Handler getMHandler() {
        return this.mHandler;
    }

    public final KeyguardEngineContainer getMKeyguardEngineContainer() {
        return this.mKeyguardEngineContainer;
    }

    public final KeyguardStateController getMKeyguardStateControllerImpl() {
        return this.mKeyguardStateControllerImpl;
    }

    public final KeyguardUpdateMonitor getMUpdateMonitor() {
        return this.mUpdateMonitor;
    }

    public final OplusKeyguardCtrl getOplusKeyguardCtrl() {
        return this.mOplusKeyguard;
    }

    public final Lazy getStatusBarKeyguardViewManagerLazy() {
        return this.statusBarKeyguardViewManagerLazy;
    }

    /* JADX WARN: Code duplicated, block: B:8:0x004b  */
    public final void inflate$1() {
        OplusKeyguardCtrl oplusKeyguardCtrl;
        OplusKeyguardCtrl oplusKeyguardCtrl2;
        boolean z;
        Trace.traceBegin(8L, "KeyguardEngineContainer#inflate");
        KeyguardUtils.Companion companion = KeyguardUtils.Companion;
        boolean zIsDefaultKeyguardInSettings = companion.isDefaultKeyguardInSettings(this.mContext);
        boolean z2 = SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState;
        boolean zIsChangeWallpaperDisabled = OplusCustomizeRestrictionManager.getInstance(this.mContext).isChangeWallpaperDisabled((ComponentName) null);
        if (!zIsDefaultKeyguardInSettings && !z2 && !zIsChangeWallpaperDisabled) {
            this.mOplusKeyguard = this.mKeyguardPluginManager.loadKeyguard(this);
            KgdLogUtil.i(TAG, "loadOplusKeyguard : mOplusKeyguard = " + this.mOplusKeyguard);
            if (this.mOplusKeyguard != null) {
                boolean z3 = (this.mViewFlag & 1) == 0;
                boolean z4 = companion.isDefaultKeyguardInSettings(this.mContext) && ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).isMainSwitchOn();
                Settings.System.putString(this.mContext.getContentResolver(), KEYGUARD_WALLPAPER_STATISTICS_SETTINGS, z4 + ";" + z3);
                boolean z5 = this.mKeyguardStateControllerImpl.mSecure;
                if (!z5 && (oplusKeyguardCtrl2 = this.mOplusKeyguard) != null) {
                    oplusKeyguardCtrl2.checkNeedsInput();
                }
                OplusKeyguardCtrl oplusKeyguardCtrl3 = this.mOplusKeyguard;
                if (oplusKeyguardCtrl3 != null) {
                    oplusKeyguardCtrl3.setHostCallback(this.mHostCallback);
                }
                OplusKeyguardCtrl oplusKeyguardCtrl4 = this.mOplusKeyguard;
                if (oplusKeyguardCtrl4 != null) {
                    oplusKeyguardCtrl4.checkNeedsChangeWallpaper(z5);
                }
                if (!this.mUpdateMonitor.mDeviceInteractive && (oplusKeyguardCtrl = this.mOplusKeyguard) != null) {
                    oplusKeyguardCtrl.onScreenTurnedOff();
                }
                KeyguardEngineContainer keyguardEngineContainer = this.mKeyguardEngineContainer;
                OplusKeyguardCtrl oplusKeyguardCtrl5 = this.mOplusKeyguard;
                keyguardEngineContainer.setMOplusKeyguardView(oplusKeyguardCtrl5 != null ? oplusKeyguardCtrl5.getExactLockView() : null);
                showOrHideOplusKeyguard$1();
                onVisibilityChange$1(!this.mBouncerShow);
                z = this.mKeyguardEngineContainer.getMOplusKeyguardView() != null;
            }
            this.mLoadOplusKeyguardSuccess = z;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("inflate isDefaultKeyguard = ", " mLoadOplusKeyguardSuccess = ", "KeyguardEngineContainer-->", zIsDefaultKeyguardInSettings, this.mLoadOplusKeyguardSuccess));
        }
        setVisibility(this.mLoadOplusKeyguardSuccess ? 0 : 8);
        OplusKeyguardWatcher.Companion companion2 = OplusKeyguardWatcher.Companion;
        companion2.getInstance().dispatchOplusKeyguardLoad(this.mLoadOplusKeyguardSuccess, this.mContext);
        if (!zIsDefaultKeyguardInSettings) {
            OplusKeyguardWatcher companion3 = companion2.getInstance();
            OplusKeyguardCtrl oplusKeyguardCtrl6 = this.mOplusKeyguard;
            companion3.dispatchSetBackground(oplusKeyguardCtrl6 != null ? oplusKeyguardCtrl6.getExtraWallpaper() : null);
        }
        Trace.traceEnd(8L);
    }

    public final boolean isNeedShow$2() {
        KeyguardEngineContainerController$$ExternalSyntheticLambda0 keyguardEngineContainerController$$ExternalSyntheticLambda0 = new KeyguardEngineContainerController$$ExternalSyntheticLambda0();
        keyguardEngineContainerController$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        KgdLogUtil.d$default(TAG, LOG_LIMIT_TYPE, 0, false, keyguardEngineContainerController$$ExternalSyntheticLambda0, 12);
        if (!this.mUpdateMonitor.isKeyguardVisible$1() || this.mStatusBarState != 1 || this.mBouncerShow || this.mUserSwitching || this.mKeyguardStateControllerImpl.mKeyguardGoingAway) {
            return false;
        }
        KeyguardUtils.Companion companion = KeyguardUtils.Companion;
        float f = this.mCurrentProcessY;
        companion.getClass();
        return KeyguardUtils.Companion.isFloatEqual(f, 1.0f) && isScreenOn$3() && !this.mIsQsExpanded && ((OplusKeyguardClockViewModel.ClockSizeState) ((OplusKeyguardClockViewModel) this.keyguardClockViewModel.get()).getClockSizeStateFlow().getValue()).getClockSize() != ClockSize.IMMERSED;
    }

    public final void needReloadOplusKeyguard() {
        boolean z = false;
        boolean z2 = this.mKeyguardPluginManager.maybeReloadNormalKeyguard(this.mOplusKeyguard != null) && this.mUpdateMonitor.mDeviceProvisioned;
        boolean z3 = ((DefaultKeyguardInSettingsObserver) this.defaultKeyguardInSettingsObserver.get()).isDefaultKeyguard;
        boolean z4 = this.mOplusKeyguard == null;
        if (!z3 && this.mKeyguardPluginManager.isKeyguardVersionUpdated()) {
            z = true;
        }
        if (z2 || z3 != z4 || z) {
            UnlockViewLifecycleOwner$.ExternalSyntheticOutline0.m(", hasVersionUpdate = ", TAG, TextFieldImplKt$.ExternalSyntheticOutline0.m("maybeReloadKeyguardMainPager , maybeReload = ", ", defaultKeyguardInSettings = ", ", defaultKeyguardInCurrent = ", z2, z3), z4, z);
            reloadOplusKeyguard$1();
        }
    }

    public final void onConfigChanged(Configuration configuration) {
        if (configuration.getLocales() != null) {
            String languageTags = configuration.getLocales().toLanguageTags();
            KeyguardSettingBackupRestoreProxy$.ExternalSyntheticOutline0.m("onConfigurationChanged, locale = ", languageTags, ", mLastLocale= ", this.mLastLocale, TAG);
            if (!Intrinsics.areEqual(languageTags, this.mLastLocale)) {
                reloadOplusKeyguard$1();
            }
            this.mLastLocale = languageTags;
        }
        int flipFont = OplusBaseConfigurationAddon.getFlipFont(configuration);
        int i = configuration.densityDpi;
        int i2 = this.mLastFlipFont;
        int i3 = this.mDensity;
        StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("onConfigurationChanged. font = ", flipFont, i2, ", mLastFlipFont = ", ", density = ");
        sbM.append(i);
        sbM.append(", mDensity = ");
        sbM.append(i3);
        KgdLogUtil.i(TAG, sbM.toString());
        if (flipFont != this.mLastFlipFont) {
            reloadOplusKeyguard$1();
            this.mLastFlipFont = flipFont;
        }
        if (this.mDensity != i) {
            this.mDensity = i;
            OplusLockIconView$.ExternalSyntheticOutline0.m("onConfigurationChanged mDensity != density isKeyguardVisible:", TAG, this.mUpdateMonitor.isKeyguardVisible$1());
            if (this.mUpdateMonitor.isKeyguardVisible$1()) {
                this.mHandler.removeCallbacks(this.mReloadOplusKeyguardTask);
                this.mHandler.postDelayed(this.mReloadOplusKeyguardTask, 100L);
            } else {
                this.mReloadOplusKeyguardLater = true;
            }
        }
        needReloadOplusKeyguard();
    }

    public final void onViewAttached() {
        ((ScreenLifecycle) Dependency.sDependency.getDependencyInner(ScreenLifecycle.class)).addObserver(this.mScreenObserver);
        ((WakefulnessLifecycle) Dependency.sDependency.getDependencyInner(WakefulnessLifecycle.class)).addObserver(this.mWakefulnessObserver);
        this.mUpdateMonitor.registerCallback(this.mMonitorCallback);
        ((StatusBarKeyguardViewManager) this.statusBarKeyguardViewManagerLazy.get()).addCallback(this.mKeyguardViewManagerCallback);
        inflate$1();
        ObserverDispatcher.getInstance().registerObserver(Settings.System.getUriFor(OPLUS_UIENGINE_SUPPORT_PICTORIAL), this.mUiEngineSupportObserver, -1);
        this.mUiEngineSupportObserver.onChange(true);
        MultiUserContentHelper.Companion.registerForUserSwitch(this.mContext, Settings.System.getUriFor("oplus_customize_unlock_change_pkg"), true, this.defaultSettingsObserver, KeyguardUpdateMonitor.getCurrentUser());
    }

    public final void onViewDetached() {
        ((ScreenLifecycle) Dependency.sDependency.getDependencyInner(ScreenLifecycle.class)).removeObserver(this.mScreenObserver);
        ((WakefulnessLifecycle) Dependency.sDependency.getDependencyInner(WakefulnessLifecycle.class)).removeObserver(this.mWakefulnessObserver);
        this.mUpdateMonitor.removeCallback(this.mMonitorCallback);
        StatusBarKeyguardViewManager statusBarKeyguardViewManager = (StatusBarKeyguardViewManager) this.statusBarKeyguardViewManagerLazy.get();
        statusBarKeyguardViewManager.mCallbacks.remove(this.mKeyguardViewManagerCallback);
        ObserverDispatcher.getInstance().unregisterObserver(this.mUiEngineSupportObserver);
        MultiUserContentHelper.Companion.unregister(this.mContext, this.defaultSettingsObserver);
    }

    public final void onVisibilityChange$1(boolean z) {
        if (this.mOplusKeyguard == null || this.mKeyguardEngineContainer.getMOplusKeyguardView() == null) {
            return;
        }
        OlockStateUtil$.ExternalSyntheticOutline0.m("onVisibilityChange show = ", ", ", TAG, z, this.mUpdateMonitor.isKeyguardVisible$1());
        if (z && this.mUpdateMonitor.isKeyguardVisible$1() && ((OplusKeyguardClockViewModel.ClockSizeState) ((OplusKeyguardClockViewModel) this.keyguardClockViewModel.get()).getClockSizeStateFlow().getValue()).getClockSize() != ClockSize.IMMERSED) {
            OplusKeyguardCtrl oplusKeyguardCtrl = this.mOplusKeyguard;
            if (oplusKeyguardCtrl != null) {
                oplusKeyguardCtrl.reset();
                return;
            }
            return;
        }
        OplusKeyguardCtrl oplusKeyguardCtrl2 = this.mOplusKeyguard;
        if (oplusKeyguardCtrl2 != null) {
            oplusKeyguardCtrl2.onRemoveWhenDouble();
        }
    }

    public final void reloadOplusKeyguard$1() {
        OplusLockIconView$.ExternalSyntheticOutline0.m("reloadOplusKeyguard mReloadOplusKeyguardLater: ", TAG, this.mReloadOplusKeyguardLater);
        this.mReloadOplusKeyguardLater = false;
        removeOplusKeyguard$1();
        inflate$1();
    }

    public final void removeOplusKeyguard$1() {
        OplusKeyguardCtrl oplusKeyguardCtrl = this.mOplusKeyguard;
        if (oplusKeyguardCtrl != null) {
            oplusKeyguardCtrl.cleanUp();
        }
        OplusKeyguardCtrl oplusKeyguardCtrl2 = this.mOplusKeyguard;
        if (oplusKeyguardCtrl2 != null) {
            oplusKeyguardCtrl2.setHostCallback((HostCallback) null);
        }
        this.mKeyguardEngineContainer.removeAllViews();
        this.mKeyguardPluginManager.clearKeyguardPlugIn();
        this.mOplusKeyguard = null;
        this.mKeyguardEngineContainer.setMOplusKeyguardView(null);
        this.mLoadOplusKeyguardSuccess = false;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x001b A[PHI: r0
      0x001b: PHI (r0v9 float) = (r0v5 float), (r0v6 float) binds: [B:10:0x0019, B:13:0x0020] A[DONT_GENERATE, DONT_INLINE]] */
    public final void setProcessY(float f) {
        if (this.mOplusKeyguard == null || this.mKeyguardEngineContainer.getMOplusKeyguardView() == null || this.mKeyguardEngineContainer.getVisibility() != 0) {
            return;
        }
        float f2 = 1.0f;
        if (f > 1.0f) {
            f = f2;
        } else {
            f2 = 0.0f;
            if (f < 0.0f) {
                f = f2;
            }
        }
        KeyguardUtils.Companion companion = KeyguardUtils.Companion;
        float f3 = this.mCurrentProcessY;
        companion.getClass();
        if (KeyguardUtils.Companion.isFloatEqual(f, f3)) {
            return;
        }
        this.mCurrentProcessY = f;
        showOrHideOplusKeyguard$1();
    }

    public final void setViewFlag(int i) {
        this.mViewFlag = i;
        OplusLockIconView$.ExternalSyntheticOutline0.m(i, "setViewFlag flag = ", TAG);
        if ((this.mViewFlag & 4) != 0) {
            this.mKeyguardEngineContainer.setLayerType(1, null);
        }
    }

    public final void setVisibility(int i) {
        if (this.mLoadOplusKeyguardSuccess && this.mUpdateMonitor.isKeyguardVisible$1()) {
            this.mKeyguardEngineContainer.setVisibility(i);
        } else {
            this.mKeyguardEngineContainer.setVisibility(8);
        }
    }

    public final void showOrHideOplusKeyguard$1() {
        if (this.mOplusKeyguard == null || this.mKeyguardEngineContainer.getMOplusKeyguardView() == null) {
            return;
        }
        if (isNeedShow$2()) {
            KgdLogUtil.d$default(TAG, LOG_LIMIT_TYPE, 0, false, new KeyguardEngineContainerController$.ExternalSyntheticLambda1(1), 12);
            OplusKeyguardCtrl oplusKeyguardCtrl = this.mOplusKeyguard;
            if (oplusKeyguardCtrl != null) {
                oplusKeyguardCtrl.show();
                return;
            }
            return;
        }
        KgdLogUtil.d$default(TAG, LOG_LIMIT_TYPE, 0, false, new KeyguardEngineContainerController$.ExternalSyntheticLambda1(0), 12);
        OplusKeyguardCtrl oplusKeyguardCtrl2 = this.mOplusKeyguard;
        if (oplusKeyguardCtrl2 != null) {
            oplusKeyguardCtrl2.hide();
        }
    }
}
