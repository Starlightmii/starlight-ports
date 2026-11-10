package com.oplus.systemui.media.seedling;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Handler;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.Dependency;
import com.android.systemui.Dumpable;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.media.MediaSeedlingControlEx;
import com.android.systemui.media.controls.domain.pipeline.MediaDataManager;
import com.android.systemui.media.controls.shared.model.MediaData;
import com.android.systemui.media.dialog.MediaOutputDialogManager;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.util.kotlin.Quad;
import com.oplus.systemui.media.seedling.client.AbsMediaClient;
import com.oplus.systemui.media.seedling.client.browser.spotify.SpotifyMediaBrowserClient;
import com.oplus.systemui.media.seedling.client.controller.MediaControllerClient;
import com.oplus.systemui.media.seedling.rus.OplusMediaRusUpdateManager;
import com.oplus.systemui.media.seedling.ums.UmsSettingsMode;
import com.oplus.systemui.media.seedling.utils.SpotifyUtils;
import com.oplus.systemui.media.seedling.utils.StatisticsManager;
import com.oplus.systemui.notification.aod.OplusPanoramicAodRepository;
import com.oplus.systemui.qs.utils.QsUtil;
import com.oplus.systemui.seedlingservice.mediaControl.MediaRecommendationData;
import com.oplus.systemui.seedlingservice.mediaControl.SeedlingMediaData;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.multiuser.MultiUserManager;
import com.oplusos.systemui.notification.logger.OplusNotificationLogger;
import dagger.Lazy;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusMediaSeedlingController implements Dumpable {
    private static final String ACTION_SCREEN_OFF = "android.intent.action.SCREEN_OFF";
    private static final String ACTION_SCREEN_ON = "android.intent.action.SCREEN_ON";
    public static final Companion Companion = new Companion();
    private static final String KEY_MEDIA_SEEDLING_SPECTRUM_DISABLE_ANIM = "media_fluid_disable_anim";
    private static final String KEY_SUPER_POWER_SAVE_MODE_STATE = "super_powersave_mode_state";
    private static final String SYSTEM_SETTING_FOLDING_MODE_KEY = "oplus_system_folding_mode";
    private static final String TAG = "OplusMediaSeedlingController";
    private final ActivityStarter activityStarter;
    private final Handler bgHandler;
    private final BroadcastDispatcher broadcastDispatcher;
    private final Context context;
    private final ContentObserver disableSpectrumAnimObserver;
    private final DumpManager dumpManager;
    private final ContentObserver foldObserver;
    public boolean hasBound;
    public boolean hasRegistered;
    public boolean isScreenOn;
    private final OplusMediaSeedlingController$mCallback$1 mCallback;
    private final Executor mainExecutor;
    private final Handler mainHandler;
    private final LinkedHashMap<String, AbsMediaClient> mediaClients;
    public boolean mediaControllerSetting;
    private final OplusSeedlingMediaDataCombineLatest mediaDataCombineLatest;
    private final MediaDataManager mediaDataManager;
    private final OplusMediaSeedlingController$mediaDataUpdateListener$1 mediaDataUpdateListener;
    private final Lazy mediaSeedling;
    private final MediaSeedlingControlEx mediaSeedlingControlEx;
    private final OplusPanoramicAodRepository oplusPanoramicAodRepository;
    private final MediaOutputDialogManager outputDialogFactory;
    private final OplusPanoramicAodRepository.PanoramicAodProgressChangeListener panoramicAodObserver;
    private final KFunction queryRunnable;
    public int retryCount;
    private final OplusMediaSeedlingController$screenStateBroadcastReceiver$1 screenStateBroadcastReceiver;
    private final kotlin.Lazy seedlingBgDispatcher$delegate;
    private final kotlin.Lazy seedlingBgScope$delegate;
    public boolean superPowerMode;
    private final ContentObserver superPowerModeObserver;
    private final ContentObserver umsSettingsObserver;
    private final OplusMediaSeedlingController$userCallback$1 userCallback;
    private final UserManager userManager;
    private final UserTracker userTracker;
    private final WakefulnessLifecycle.Observer wakefulnessObserver;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.media.seedling.OplusMediaSeedlingController$applyUmsSettings$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Runnable {
        public /* synthetic */ boolean $isLockscreenSwitchOn;
        public /* synthetic */ boolean $isStatusBarSwitchOn;
        public /* synthetic */ OplusMediaSeedlingController this$0;

        @Override // java.lang.Runnable
        public final void run() {
            OplusMediaSeedlingController oplusMediaSeedlingController = this.this$0;
            if (!oplusMediaSeedlingController.hasBound) {
                oplusMediaSeedlingController.hasBound = true;
                OplusMediaSeedlingController.access$bindMediaSeedling(oplusMediaSeedlingController);
            }
            if (((MediaSeedling) this.this$0.mediaSeedling.get()).isMediaSeedlingShowOnStatusBar != this.$isStatusBarSwitchOn) {
                ((MediaSeedling) this.this$0.mediaSeedling.get()).setMediaSeedlingShowOnStatusBar(this.$isStatusBarSwitchOn);
            }
            if (((MediaSeedling) this.this$0.mediaSeedling.get()).isUmsSeedlingShowOnLockScreen != this.$isLockscreenSwitchOn) {
                ((MediaSeedling) this.this$0.mediaSeedling.get()).setUmsSeedlingShowOnLockScreen(this.$isLockscreenSwitchOn);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r2v7, types: [com.oplus.systemui.media.seedling.OplusMediaSeedlingController$screenStateBroadcastReceiver$1] */
    public OplusMediaSeedlingController(Context context, UserTracker userTracker, Executor executor, ActivityStarter activityStarter, MediaOutputDialogManager mediaOutputDialogManager, MediaDataManager mediaDataManager, MediaSeedlingControlEx mediaSeedlingControlEx, Lazy lazy, OplusSeedlingMediaDataCombineLatest oplusSeedlingMediaDataCombineLatest, OplusPanoramicAodRepository oplusPanoramicAodRepository, UserManager userManager, DumpManager dumpManager, BroadcastDispatcher broadcastDispatcher, Handler handler) {
        this.context = context;
        this.userTracker = userTracker;
        this.mainExecutor = executor;
        this.activityStarter = activityStarter;
        this.outputDialogFactory = mediaOutputDialogManager;
        this.mediaDataManager = mediaDataManager;
        this.mediaSeedlingControlEx = mediaSeedlingControlEx;
        this.mediaSeedling = lazy;
        this.mediaDataCombineLatest = oplusSeedlingMediaDataCombineLatest;
        this.oplusPanoramicAodRepository = oplusPanoramicAodRepository;
        this.userManager = userManager;
        this.dumpManager = dumpManager;
        this.broadcastDispatcher = broadcastDispatcher;
        this.mainHandler = handler;
        final Handler handler2 = new Handler(MediaSeedlingBgThread.INSTANCE.getThreadLooper());
        this.bgHandler = handler2;
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED;
        final int i = 0;
        OplusMediaSeedlingController$$ExternalSyntheticLambda0 oplusMediaSeedlingController$$ExternalSyntheticLambda0 = new OplusMediaSeedlingController$$ExternalSyntheticLambda0(i);
        oplusMediaSeedlingController$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.seedlingBgDispatcher$delegate = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, oplusMediaSeedlingController$$ExternalSyntheticLambda0);
        final int i2 = 1;
        OplusMediaSeedlingController$$ExternalSyntheticLambda0 oplusMediaSeedlingController$$ExternalSyntheticLambda1 = new OplusMediaSeedlingController$$ExternalSyntheticLambda0(i2);
        oplusMediaSeedlingController$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.seedlingBgScope$delegate = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, oplusMediaSeedlingController$$ExternalSyntheticLambda1);
        this.mediaClients = new LinkedHashMap<>();
        OplusMediaSeedlingController$mCallback$1 oplusMediaSeedlingController$mCallback$1 = new OplusMediaSeedlingController$mCallback$1();
        oplusMediaSeedlingController$mCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mCallback = oplusMediaSeedlingController$mCallback$1;
        this.screenStateBroadcastReceiver = new BroadcastReceiver() { // from class: com.oplus.systemui.media.seedling.OplusMediaSeedlingController$screenStateBroadcastReceiver$1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                String action = intent != null ? intent.getAction() : null;
                OplusNotificationLogger.logMediaSeedling("OplusMediaSeedlingController", "onReceive: action=" + action);
                if (Intrinsics.areEqual(action, "android.intent.action.SCREEN_ON")) {
                    Handler handler3 = this.this$0.mainHandler;
                    OplusMediaSeedlingController oplusMediaSeedlingController = this.this$0;
                    OplusMediaSeedlingController.AnonymousClass1 anonymousClass1 = new OplusMediaSeedlingController.AnonymousClass1(3);
                    anonymousClass1.this$0 = oplusMediaSeedlingController;
                    VarHandle.storeStoreFence();
                    handler3.post(anonymousClass1);
                    return;
                }
                if (!Intrinsics.areEqual(action, "android.intent.action.SCREEN_OFF")) {
                    OplusNotificationLogger.logMediaSeedling("OplusMediaSeedlingController", "never be here!");
                    return;
                }
                Handler handler4 = this.this$0.mainHandler;
                OplusMediaSeedlingController oplusMediaSeedlingController2 = this.this$0;
                OplusMediaSeedlingController.AnonymousClass1 anonymousClass2 = new OplusMediaSeedlingController.AnonymousClass1(4);
                anonymousClass2.this$0 = oplusMediaSeedlingController2;
                VarHandle.storeStoreFence();
                handler4.post(anonymousClass2);
            }
        };
        this.isScreenOn = true;
        final int i3 = 2;
        this.superPowerModeObserver = new ContentObserver(this, handler2) { // from class: com.oplus.systemui.media.seedling.OplusMediaSeedlingController$foldObserver$1
            public final /* synthetic */ OplusMediaSeedlingController this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                switch (i3) {
                    case 0:
                        ((MediaSeedling) this.this$0.mediaSeedling.get()).setFold(this.this$0.getFoldMode());
                        OplusNotificationLogger.logMediaSeedling("OplusMediaSeedlingController", "onChange: isFold=" + ((MediaSeedling) this.this$0.mediaSeedling.get()).isFold);
                        break;
                    case 1:
                        ((MediaSeedling) this.this$0.mediaSeedling.get()).setDisableSpectrumAnim(this.this$0.getDisableSpectrumAnim());
                        OplusNotificationLogger.logMediaSeedling("OplusMediaSeedlingController", "onChange: mDisableSpectrumAnim=" + ((MediaSeedling) this.this$0.mediaSeedling.get()).getDisableSpectrumAnim());
                        break;
                    case 2:
                        OplusMediaSeedlingController oplusMediaSeedlingController = this.this$0;
                        oplusMediaSeedlingController.superPowerMode = oplusMediaSeedlingController.getSuperPowerMode();
                        ((MediaSeedling) this.this$0.mediaSeedling.get()).setSuperPowerMode(this.this$0.superPowerMode);
                        OplusNotificationLogger.logMediaSeedling("OplusMediaSeedlingController", "onChange: superPowerMode=" + this.this$0.superPowerMode);
                        OplusMediaSeedlingController oplusMediaSeedlingController2 = this.this$0;
                        if (!oplusMediaSeedlingController2.superPowerMode) {
                            oplusMediaSeedlingController2.registerMediaDataCallBack();
                        } else {
                            oplusMediaSeedlingController2.unRegisterMediaDataCallBack();
                        }
                        break;
                    default:
                        super.onChange(z, uri);
                        break;
                }
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                switch (i3) {
                    case 3:
                        OplusNotificationLogger.logMediaSeedling("OplusMediaSeedlingController", "umsSettingsObserver changed");
                        this.this$0.bgHandler.removeCallbacks(new OplusMediaSeedlingController.AnonymousClass1(this.this$0.queryRunnable));
                        OplusMediaSeedlingController oplusMediaSeedlingController = this.this$0;
                        oplusMediaSeedlingController.retryCount = 0;
                        oplusMediaSeedlingController.queryRunnable.invoke();
                        break;
                    default:
                        super.onChange(z);
                        break;
                }
            }
        };
        this.disableSpectrumAnimObserver = new ContentObserver(this, handler2) { // from class: com.oplus.systemui.media.seedling.OplusMediaSeedlingController$foldObserver$1
            public final /* synthetic */ OplusMediaSeedlingController this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                switch (i2) {
                    case 0:
                        ((MediaSeedling) this.this$0.mediaSeedling.get()).setFold(this.this$0.getFoldMode());
                        OplusNotificationLogger.logMediaSeedling("OplusMediaSeedlingController", "onChange: isFold=" + ((MediaSeedling) this.this$0.mediaSeedling.get()).isFold);
                        break;
                    case 1:
                        ((MediaSeedling) this.this$0.mediaSeedling.get()).setDisableSpectrumAnim(this.this$0.getDisableSpectrumAnim());
                        OplusNotificationLogger.logMediaSeedling("OplusMediaSeedlingController", "onChange: mDisableSpectrumAnim=" + ((MediaSeedling) this.this$0.mediaSeedling.get()).getDisableSpectrumAnim());
                        break;
                    case 2:
                        OplusMediaSeedlingController oplusMediaSeedlingController = this.this$0;
                        oplusMediaSeedlingController.superPowerMode = oplusMediaSeedlingController.getSuperPowerMode();
                        ((MediaSeedling) this.this$0.mediaSeedling.get()).setSuperPowerMode(this.this$0.superPowerMode);
                        OplusNotificationLogger.logMediaSeedling("OplusMediaSeedlingController", "onChange: superPowerMode=" + this.this$0.superPowerMode);
                        OplusMediaSeedlingController oplusMediaSeedlingController2 = this.this$0;
                        if (!oplusMediaSeedlingController2.superPowerMode) {
                            oplusMediaSeedlingController2.registerMediaDataCallBack();
                        } else {
                            oplusMediaSeedlingController2.unRegisterMediaDataCallBack();
                        }
                        break;
                    default:
                        super.onChange(z, uri);
                        break;
                }
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                switch (i2) {
                    case 3:
                        OplusNotificationLogger.logMediaSeedling("OplusMediaSeedlingController", "umsSettingsObserver changed");
                        this.this$0.bgHandler.removeCallbacks(new OplusMediaSeedlingController.AnonymousClass1(this.this$0.queryRunnable));
                        OplusMediaSeedlingController oplusMediaSeedlingController = this.this$0;
                        oplusMediaSeedlingController.retryCount = 0;
                        oplusMediaSeedlingController.queryRunnable.invoke();
                        break;
                    default:
                        super.onChange(z);
                        break;
                }
            }
        };
        this.foldObserver = new ContentObserver(this, handler2) { // from class: com.oplus.systemui.media.seedling.OplusMediaSeedlingController$foldObserver$1
            public final /* synthetic */ OplusMediaSeedlingController this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                switch (i) {
                    case 0:
                        ((MediaSeedling) this.this$0.mediaSeedling.get()).setFold(this.this$0.getFoldMode());
                        OplusNotificationLogger.logMediaSeedling("OplusMediaSeedlingController", "onChange: isFold=" + ((MediaSeedling) this.this$0.mediaSeedling.get()).isFold);
                        break;
                    case 1:
                        ((MediaSeedling) this.this$0.mediaSeedling.get()).setDisableSpectrumAnim(this.this$0.getDisableSpectrumAnim());
                        OplusNotificationLogger.logMediaSeedling("OplusMediaSeedlingController", "onChange: mDisableSpectrumAnim=" + ((MediaSeedling) this.this$0.mediaSeedling.get()).getDisableSpectrumAnim());
                        break;
                    case 2:
                        OplusMediaSeedlingController oplusMediaSeedlingController = this.this$0;
                        oplusMediaSeedlingController.superPowerMode = oplusMediaSeedlingController.getSuperPowerMode();
                        ((MediaSeedling) this.this$0.mediaSeedling.get()).setSuperPowerMode(this.this$0.superPowerMode);
                        OplusNotificationLogger.logMediaSeedling("OplusMediaSeedlingController", "onChange: superPowerMode=" + this.this$0.superPowerMode);
                        OplusMediaSeedlingController oplusMediaSeedlingController2 = this.this$0;
                        if (!oplusMediaSeedlingController2.superPowerMode) {
                            oplusMediaSeedlingController2.registerMediaDataCallBack();
                        } else {
                            oplusMediaSeedlingController2.unRegisterMediaDataCallBack();
                        }
                        break;
                    default:
                        super.onChange(z, uri);
                        break;
                }
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                switch (i) {
                    case 3:
                        OplusNotificationLogger.logMediaSeedling("OplusMediaSeedlingController", "umsSettingsObserver changed");
                        this.this$0.bgHandler.removeCallbacks(new OplusMediaSeedlingController.AnonymousClass1(this.this$0.queryRunnable));
                        OplusMediaSeedlingController oplusMediaSeedlingController = this.this$0;
                        oplusMediaSeedlingController.retryCount = 0;
                        oplusMediaSeedlingController.queryRunnable.invoke();
                        break;
                    default:
                        super.onChange(z);
                        break;
                }
            }
        };
        OplusMediaSeedlingController$wakefulnessObserver$1 oplusMediaSeedlingController$wakefulnessObserver$1 = new OplusMediaSeedlingController$wakefulnessObserver$1();
        oplusMediaSeedlingController$wakefulnessObserver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.wakefulnessObserver = oplusMediaSeedlingController$wakefulnessObserver$1;
        OplusMediaSeedlingController$panoramicAodObserver$1 oplusMediaSeedlingController$panoramicAodObserver$1 = new OplusMediaSeedlingController$panoramicAodObserver$1();
        oplusMediaSeedlingController$panoramicAodObserver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.panoramicAodObserver = oplusMediaSeedlingController$panoramicAodObserver$1;
        this.queryRunnable = new OplusMediaSeedlingController$queryRunnable$1(0, this, OplusMediaSeedlingController.class, "queryUmsSettingsWithRetry", "queryUmsSettingsWithRetry()V", 0);
        OplusMediaSeedlingController$userCallback$1 oplusMediaSeedlingController$userCallback$1 = new OplusMediaSeedlingController$userCallback$1();
        oplusMediaSeedlingController$userCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.userCallback = oplusMediaSeedlingController$userCallback$1;
        this.mediaControllerSetting = true;
        final int i4 = 3;
        this.umsSettingsObserver = new ContentObserver(this, handler2) { // from class: com.oplus.systemui.media.seedling.OplusMediaSeedlingController$foldObserver$1
            public final /* synthetic */ OplusMediaSeedlingController this$0;

            {
                this.this$0 = this;
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                switch (i4) {
                    case 0:
                        ((MediaSeedling) this.this$0.mediaSeedling.get()).setFold(this.this$0.getFoldMode());
                        OplusNotificationLogger.logMediaSeedling("OplusMediaSeedlingController", "onChange: isFold=" + ((MediaSeedling) this.this$0.mediaSeedling.get()).isFold);
                        break;
                    case 1:
                        ((MediaSeedling) this.this$0.mediaSeedling.get()).setDisableSpectrumAnim(this.this$0.getDisableSpectrumAnim());
                        OplusNotificationLogger.logMediaSeedling("OplusMediaSeedlingController", "onChange: mDisableSpectrumAnim=" + ((MediaSeedling) this.this$0.mediaSeedling.get()).getDisableSpectrumAnim());
                        break;
                    case 2:
                        OplusMediaSeedlingController oplusMediaSeedlingController = this.this$0;
                        oplusMediaSeedlingController.superPowerMode = oplusMediaSeedlingController.getSuperPowerMode();
                        ((MediaSeedling) this.this$0.mediaSeedling.get()).setSuperPowerMode(this.this$0.superPowerMode);
                        OplusNotificationLogger.logMediaSeedling("OplusMediaSeedlingController", "onChange: superPowerMode=" + this.this$0.superPowerMode);
                        OplusMediaSeedlingController oplusMediaSeedlingController2 = this.this$0;
                        if (!oplusMediaSeedlingController2.superPowerMode) {
                            oplusMediaSeedlingController2.registerMediaDataCallBack();
                        } else {
                            oplusMediaSeedlingController2.unRegisterMediaDataCallBack();
                        }
                        break;
                    default:
                        super.onChange(z, uri);
                        break;
                }
            }

            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                switch (i4) {
                    case 3:
                        OplusNotificationLogger.logMediaSeedling("OplusMediaSeedlingController", "umsSettingsObserver changed");
                        this.this$0.bgHandler.removeCallbacks(new OplusMediaSeedlingController.AnonymousClass1(this.this$0.queryRunnable));
                        OplusMediaSeedlingController oplusMediaSeedlingController = this.this$0;
                        oplusMediaSeedlingController.retryCount = 0;
                        oplusMediaSeedlingController.queryRunnable.invoke();
                        break;
                    default:
                        super.onChange(z);
                        break;
                }
            }
        };
        OplusMediaSeedlingController$mediaDataUpdateListener$1 oplusMediaSeedlingController$mediaDataUpdateListener$1 = new OplusMediaSeedlingController$mediaDataUpdateListener$1();
        oplusMediaSeedlingController$mediaDataUpdateListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mediaDataUpdateListener = oplusMediaSeedlingController$mediaDataUpdateListener$1;
    }

    public static final void access$bindMediaSeedling(OplusMediaSeedlingController oplusMediaSeedlingController) {
        OplusNotificationLogger.logMediaSeedling(TAG, "bindMediaSeedling");
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class);
        OplusMediaSeedlingController$mCallback$1 oplusMediaSeedlingController$mCallback$1 = oplusMediaSeedlingController.mCallback;
        KeyguardStateControllerImpl keyguardStateControllerImpl2 = keyguardStateControllerImpl;
        keyguardStateControllerImpl2.getClass();
        keyguardStateControllerImpl2.addCallback(oplusMediaSeedlingController$mCallback$1);
        oplusMediaSeedlingController.registerMediaDataCallBack();
        ((WakefulnessLifecycle) Dependency.sDependency.getDependencyInner(WakefulnessLifecycle.class)).addObserver(oplusMediaSeedlingController.wakefulnessObserver);
        oplusMediaSeedlingController.oplusPanoramicAodRepository.addCallback(oplusMediaSeedlingController.panoramicAodObserver);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_SCREEN_ON);
        intentFilter.addAction(ACTION_SCREEN_OFF);
        BroadcastDispatcher.registerReceiver$default(oplusMediaSeedlingController.broadcastDispatcher, oplusMediaSeedlingController.screenStateBroadcastReceiver, intentFilter, (Executor) null, (UserHandle) null, 0, 60);
        Handler handler = oplusMediaSeedlingController.bgHandler;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(2);
        anonymousClass1.this$0 = oplusMediaSeedlingController;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass1);
    }

    public static final AbsMediaClient access$createMediaClient(OplusMediaSeedlingController oplusMediaSeedlingController, String str, MediaData mediaData) {
        SeedlingMediaData seedlingMediaData = new SeedlingMediaData(null, null, null, null, 0L, 0, null, null, null, null, null, null, null, null, false, null, null, null, null, null, false, null, false, null, 0, 0, 0, 0L, false, false, 0L, 0.0f, false, null, null, 0L, false, 0L, false, null, 0, false, null, null, false, false, null, null, null, null, false, false, -1, 1048575, null);
        seedlingMediaData.setPlayerId(str);
        seedlingMediaData.setPackageName(mediaData.getPackageName());
        seedlingMediaData.setSpotifyWhichSupportsOplusMediaController(SpotifyUtils.isSpotifyWhichSupportsOplusMediaController(oplusMediaSeedlingController.context, seedlingMediaData.getPackageName(), true));
        boolean zIsSpotifyWhichSupportsOplusMediaController = seedlingMediaData.isSpotifyWhichSupportsOplusMediaController();
        Context context = oplusMediaSeedlingController.context;
        ActivityStarter activityStarter = oplusMediaSeedlingController.activityStarter;
        MediaOutputDialogManager mediaOutputDialogManager = oplusMediaSeedlingController.outputDialogFactory;
        return zIsSpotifyWhichSupportsOplusMediaController ? new SpotifyMediaBrowserClient(context, seedlingMediaData, activityStarter, mediaOutputDialogManager, oplusMediaSeedlingController.mediaDataCombineLatest, oplusMediaSeedlingController.mainHandler, oplusMediaSeedlingController.bgHandler, oplusMediaSeedlingController.getSeedlingBgDispatcher(), oplusMediaSeedlingController.getSeedlingBgScope()) : new MediaControllerClient(context, seedlingMediaData, activityStarter, mediaOutputDialogManager, oplusMediaSeedlingController.mediaDataCombineLatest, oplusMediaSeedlingController.mainHandler, oplusMediaSeedlingController.bgHandler, oplusMediaSeedlingController.getSeedlingBgDispatcher(), oplusMediaSeedlingController.getSeedlingBgScope());
    }

    public static final void access$queryUmsSettingsWithRetry(OplusMediaSeedlingController oplusMediaSeedlingController) {
        Quad settingsValue = UmsSettingsMode.getSettingsValue(oplusMediaSeedlingController.context);
        boolean zBooleanValue = ((Boolean) settingsValue.first).booleanValue();
        boolean zBooleanValue2 = ((Boolean) settingsValue.second).booleanValue();
        boolean zBooleanValue3 = ((Boolean) settingsValue.third).booleanValue();
        if (!((Boolean) settingsValue.fourth).booleanValue()) {
            oplusMediaSeedlingController.applyUmsSettings(zBooleanValue, zBooleanValue2, zBooleanValue3);
            oplusMediaSeedlingController.retryCount = 0;
            OplusNotificationLogger.logMediaSeedling(TAG, "Media settings query successful");
            return;
        }
        int i = oplusMediaSeedlingController.retryCount + 1;
        oplusMediaSeedlingController.retryCount = i;
        if (i > 2) {
            OplusNotificationLogger.logMediaSeedling(TAG, "Max retry count reached, using default Media settings");
            oplusMediaSeedlingController.applyUmsSettings(true, true, true);
            oplusMediaSeedlingController.retryCount = 0;
        } else {
            OplusNotificationLogger.logMediaSeedling(TAG, "Media settings query failed, retrying... " + i);
            oplusMediaSeedlingController.bgHandler.postDelayed(new AnonymousClass1(oplusMediaSeedlingController.queryRunnable), ((long) oplusMediaSeedlingController.retryCount) * 2000);
        }
    }

    public static final void access$removeMediaClient(OplusMediaSeedlingController oplusMediaSeedlingController, String str) {
        AbsMediaClient absMediaClientRemove = oplusMediaSeedlingController.mediaClients.remove(str);
        if (absMediaClientRemove == null) {
            OplusNotificationLogger.logMediaSeedling(TAG, "warning: mediaClient is null!");
        } else {
            absMediaClientRemove.onDestroy();
            absMediaClientRemove.onMediaDataRemoved(str);
        }
    }

    public static final void access$unBindMediaSeedling(OplusMediaSeedlingController oplusMediaSeedlingController) {
        OplusNotificationLogger.logMediaSeedling(TAG, "unBindMediaSeedling");
        ((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).removeCallback(oplusMediaSeedlingController.mCallback);
        oplusMediaSeedlingController.unRegisterMediaDataCallBack();
        ((WakefulnessLifecycle) Dependency.sDependency.getDependencyInner(WakefulnessLifecycle.class)).removeObserver(oplusMediaSeedlingController.wakefulnessObserver);
        oplusMediaSeedlingController.oplusPanoramicAodRepository.removeCallback(oplusMediaSeedlingController.panoramicAodObserver);
        oplusMediaSeedlingController.broadcastDispatcher.unregisterReceiver(oplusMediaSeedlingController.screenStateBroadcastReceiver);
        Handler handler = oplusMediaSeedlingController.bgHandler;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(5);
        anonymousClass1.this$0 = oplusMediaSeedlingController;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getDisableSpectrumAnim() {
        return Settings.Global.getInt(this.context.getContentResolver(), KEY_MEDIA_SEEDLING_SPECTRUM_DISABLE_ANIM, 0) == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getFoldMode() {
        return Settings.Global.getInt(this.context.getContentResolver(), "oplus_system_folding_mode", -1) == 0;
    }

    private final CoroutineDispatcher getSeedlingBgDispatcher() {
        return (CoroutineDispatcher) this.seedlingBgDispatcher$delegate.getValue();
    }

    private final CoroutineScope getSeedlingBgScope() {
        return (CoroutineScope) this.seedlingBgScope$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getSuperPowerMode() {
        return Settings.System.getInt(this.context.getContentResolver(), "super_powersave_mode_state", 0) == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setScreenOn(boolean z) {
        if (this.isScreenOn != z) {
            this.isScreenOn = z;
            ((MediaSeedling) this.mediaSeedling.get()).setScreenOn(z);
            OplusNotificationLogger.logMediaSeedling(TAG, "isScreenOn: " + ((MediaSeedling) this.mediaSeedling.get()).isScreenOn);
        }
    }

    public final void applyUmsSettings(boolean z, boolean z2, boolean z3) {
        this.mediaControllerSetting = z;
        this.mediaSeedlingControlEx.setMediaControllerSetting(z);
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("applyUmsSettings: umsSettings=", " isStatusBarSwitchOn=", ", isLockscreenSwitchOn: ", this.mediaControllerSetting, z2);
        sbM.append(z3);
        OplusNotificationLogger.logMediaSeedling(TAG, sbM.toString());
        if (!this.mediaControllerSetting) {
            OplusNotificationLogger.logMediaSeedling(TAG, "removeListener");
            Handler handler = this.mainHandler;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(1);
            anonymousClass1.this$0 = this;
            VarHandle.storeStoreFence();
            handler.post(anonymousClass1);
            return;
        }
        OplusNotificationLogger.logMediaSeedling(TAG, "addListener");
        Handler handler2 = this.mainHandler;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        anonymousClass2.$isStatusBarSwitchOn = z2;
        anonymousClass2.$isLockscreenSwitchOn = z3;
        VarHandle.storeStoreFence();
        handler2.post(anonymousClass2);
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        OplusLockIconView$.ExternalSyntheticOutline0.m(TextFieldImplKt$.ExternalSyntheticOutline0.m("\t mediaControllerSetting: ", ", hasBound: ", ", hasRegistered: ", this.mediaControllerSetting, this.hasBound), this.hasRegistered, printWriter);
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("mediaClients: ", this.mediaClients.size(), printWriter);
        Iterator<T> it = this.mediaClients.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            entry.getClass();
            String str = (String) entry.getKey();
            AbsMediaClient absMediaClient = (AbsMediaClient) entry.getValue();
            printWriter.println("\t " + str + " : " + absMediaClient + " \t " + absMediaClient.getMediaData());
        }
        this.mediaDataCombineLatest.dump(printWriter);
        ((MediaSeedling) this.mediaSeedling.get()).dump(printWriter);
    }

    public final void executeMediaAction(String str, String str2, String str3) {
        SeedlingMediaData mediaData;
        Object next;
        AbsMediaClient absMediaClient;
        Iterator<T> it = this.mediaClients.entrySet().iterator();
        do {
            mediaData = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((AbsMediaClient) ((Map.Entry) next).getValue()).getMediaData().getPlayerId(), str));
        Map.Entry entry = (Map.Entry) next;
        if (entry != null && (absMediaClient = (AbsMediaClient) entry.getValue()) != null) {
            mediaData = absMediaClient.getMediaData();
        }
        if (mediaData == null) {
            OplusNotificationLogger.logMediaSeedling(TAG, "executeMediaAction no mediaData, players: " + CollectionsKt___CollectionsKt.joinToString$default(this.mediaClients.entrySet(), (CharSequence) null, (CharSequence) null, (CharSequence) null, new OplusMediaSeedlingController$.ExternalSyntheticLambda2(), 31) + ", target: " + str);
            return;
        }
        OplusNotificationLogger.logMediaSeedling(TAG, "executeMediaAction buttonName " + str2 + ", playId " + str);
        QsUtil.getAndUnFreezePackage(mediaData.getPackageName(), mediaData.getUserId(), this.context);
        Handler handler = this.mainHandler;
        executeMediaAction.1.1 r8 = new executeMediaAction.1.1();
        r8.$it = mediaData;
        r8.$buttonName = str2;
        r8.$cardEntrance = str3;
        VarHandle.storeStoreFence();
        handler.post(r8);
    }

    public final void executeRecommendationAction(String str, int i) {
        Object next;
        Runnable action;
        AbsMediaClient absMediaClient;
        SeedlingMediaData mediaData;
        Iterator<T> it = this.mediaClients.entrySet().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((AbsMediaClient) ((Map.Entry) next).getValue()).getMediaData().getPlayerId(), str));
        Map.Entry entry = (Map.Entry) next;
        CopyOnWriteArrayList<MediaRecommendationData> recommendations = (entry == null || (absMediaClient = (AbsMediaClient) entry.getValue()) == null || (mediaData = absMediaClient.getMediaData()) == null) ? null : mediaData.getRecommendations();
        MediaRecommendationData mediaRecommendationData = recommendations != null ? (MediaRecommendationData) CollectionsKt___CollectionsKt.getOrNull(recommendations, i) : null;
        OplusNotificationLogger.logMediaSeedling(TAG, "executeRecommendationAction targetRecommendation " + mediaRecommendationData);
        if (mediaRecommendationData == null || (action = mediaRecommendationData.getAction()) == null) {
            return;
        }
        action.run();
    }

    public final void executeSeekToAction(String str, long j) {
        Object next;
        AbsMediaClient absMediaClient;
        MediaController.TransportControls transportControls;
        MediaController.TransportControls transportControls2;
        Iterator<T> it = this.mediaClients.entrySet().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((AbsMediaClient) ((Map.Entry) next).getValue()).getMediaData().getPlayerId(), str));
        Map.Entry entry = (Map.Entry) next;
        if (entry == null || (absMediaClient = (AbsMediaClient) entry.getValue()) == null) {
            return;
        }
        MediaController mediaController = absMediaClient.getMediaController();
        PlaybackState playbackState = mediaController != null ? mediaController.getPlaybackState() : null;
        boolean z = false;
        if (playbackState != null && (playbackState.getActions() & 256) != 0) {
            z = true;
        }
        StringBuilder sbM = KeyguardLogger$.ExternalSyntheticOutline0.m("executeSeekToAction ", absMediaClient.getMediaData().getPackageName(), " seekEnabled: ", z, " ");
        sbM.append(j);
        OplusNotificationLogger.logMediaSeedling(TAG, sbM.toString());
        if (z) {
            this.mediaDataCombineLatest.tempSuppressMediaListSort();
            MediaController mediaController2 = absMediaClient.getMediaController();
            if (mediaController2 == null || (transportControls2 = mediaController2.getTransportControls()) == null) {
                return;
            }
            transportControls2.seekTo(j);
            return;
        }
        MediaController mediaController3 = absMediaClient.getMediaController();
        if ("com.spotify.music".equals(mediaController3 != null ? mediaController3.getPackageName() : null)) {
            absMediaClient.dispatchMediaDataLoaded(1, "executeSeekToAction");
        } else {
            this.mediaDataCombineLatest.tempSuppressMediaListSort();
            MediaController mediaController4 = absMediaClient.getMediaController();
            if (mediaController4 != null && (transportControls = mediaController4.getTransportControls()) != null) {
                transportControls.seekTo(j);
            }
        }
        OplusNotificationLogger.logMediaSeedling(TAG, "executeSeekToAction not support seek");
    }

    public final List<SeedlingMediaData> getSortedEntries() {
        return this.mediaDataCombineLatest.getSortedEntriesExceptNonActive();
    }

    public final void registerMediaDataCallBack() {
        OplusNotificationLogger.logMediaSeedling(TAG, "registerMediaDataCallBack hasRegistered:" + this.hasRegistered);
        if (this.hasRegistered) {
            return;
        }
        this.mediaDataManager.addListener(this.mediaDataUpdateListener);
        this.hasRegistered = true;
    }

    public final void start() {
        OplusNotificationLogger.logMediaSeedling(TAG, "start");
        OplusMediaRusUpdateManager.Companion.getInstance().initWhiteAndBlackListData(this.context);
        if (!this.mediaSeedlingControlEx.isSupportMediaSeedlingByFeature()) {
            OplusNotificationLogger.logMediaSeedling(TAG, "init not support mediaSeedling");
            return;
        }
        StatisticsManager.Companion.getInstance().initStatisticsManager(this.context);
        this.dumpManager.registerDumpable(this);
        ((MediaSeedling) this.mediaSeedling.get()).start();
        this.userTracker.addCallback(this.userCallback, this.mainExecutor);
        MultiUserManager.Companion.getInstance().addListener(this.userCallback);
        Handler handler = this.bgHandler;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass1);
    }

    public final void unRegisterMediaDataCallBack() {
        OplusNotificationLogger.logMediaSeedling(TAG, "unRegisterMediaDataCallBack release");
        if (this.hasRegistered) {
            this.mediaDataManager.removeListener(this.mediaDataUpdateListener);
            Iterator<Map.Entry<String, AbsMediaClient>> it = this.mediaClients.entrySet().iterator();
            while (it.hasNext()) {
                it.next().getValue().onDestroy();
            }
            this.mediaClients.clear();
            this.mediaDataCombineLatest.release();
            ((MediaSeedling) this.mediaSeedling.get()).release();
            this.bgHandler.removeCallbacksAndMessages(null);
            this.hasRegistered = false;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.media.seedling.OplusMediaSeedlingController$start$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ Object this$0;

        public AnonymousClass1(Function0 function0) {
            this.$r8$classId = 6;
            this.this$0 = function0;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    MediaFileManager.Companion.getInstance().deleteAllFile(((OplusMediaSeedlingController) this.this$0).context);
                    UmsSettingsMode.registerSettingObserver(((OplusMediaSeedlingController) this.this$0).context, ((OplusMediaSeedlingController) this.this$0).umsSettingsObserver);
                    if (((OplusMediaSeedlingController) this.this$0).userManager.isUserUnlocked(((OplusMediaSeedlingController) this.this$0).userTracker.getUserId())) {
                        ((OplusMediaSeedlingController) this.this$0).queryRunnable.invoke();
                    }
                    break;
                case 1:
                    OplusMediaSeedlingController oplusMediaSeedlingController = (OplusMediaSeedlingController) this.this$0;
                    if (oplusMediaSeedlingController.hasBound) {
                        oplusMediaSeedlingController.hasBound = false;
                        OplusMediaSeedlingController.access$unBindMediaSeedling(oplusMediaSeedlingController);
                    }
                    break;
                case 2:
                    ((OplusMediaSeedlingController) this.this$0).context.getContentResolver().registerContentObserver(Settings.System.getUriFor("super_powersave_mode_state"), true, ((OplusMediaSeedlingController) this.this$0).superPowerModeObserver);
                    ((MediaSeedling) ((OplusMediaSeedlingController) this.this$0).mediaSeedling.get()).setSuperPowerMode(((OplusMediaSeedlingController) this.this$0).getSuperPowerMode());
                    ((OplusMediaSeedlingController) this.this$0).context.getContentResolver().registerContentObserver(Settings.Global.getUriFor(OplusMediaSeedlingController.KEY_MEDIA_SEEDLING_SPECTRUM_DISABLE_ANIM), true, ((OplusMediaSeedlingController) this.this$0).disableSpectrumAnimObserver);
                    ((MediaSeedling) ((OplusMediaSeedlingController) this.this$0).mediaSeedling.get()).setDisableSpectrumAnim(((OplusMediaSeedlingController) this.this$0).getDisableSpectrumAnim());
                    FeatureOption.INSTANCE.getClass();
                    if (FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled()) {
                        ((MediaSeedling) ((OplusMediaSeedlingController) this.this$0).mediaSeedling.get()).setFold(((OplusMediaSeedlingController) this.this$0).getFoldMode());
                        ((OplusMediaSeedlingController) this.this$0).context.getContentResolver().registerContentObserver(Settings.Global.getUriFor("oplus_system_folding_mode"), true, ((OplusMediaSeedlingController) this.this$0).foldObserver);
                        break;
                    }
                    break;
                case 3:
                    ((OplusMediaSeedlingController) this.this$0).setScreenOn(true);
                    break;
                case 4:
                    ((OplusMediaSeedlingController) this.this$0).setScreenOn(false);
                    break;
                case 5:
                    ((OplusMediaSeedlingController) this.this$0).context.getContentResolver().unregisterContentObserver(((OplusMediaSeedlingController) this.this$0).superPowerModeObserver);
                    ((OplusMediaSeedlingController) this.this$0).context.getContentResolver().unregisterContentObserver(((OplusMediaSeedlingController) this.this$0).disableSpectrumAnimObserver);
                    FeatureOption.INSTANCE.getClass();
                    if (FeatureOption.isFoldDevice() && FeatureOption.isFoldRemapDisabled()) {
                        ((OplusMediaSeedlingController) this.this$0).context.getContentResolver().unregisterContentObserver(((OplusMediaSeedlingController) this.this$0).foldObserver);
                        break;
                    }
                    break;
                default:
                    ((Function0) this.this$0).invoke();
                    break;
            }
        }

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }
    }
}
