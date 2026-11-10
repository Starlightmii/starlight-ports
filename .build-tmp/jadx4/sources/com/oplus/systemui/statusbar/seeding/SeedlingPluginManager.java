package com.oplus.systemui.statusbar.seeding;

import android.app.INotificationManager;
import android.app.PendingIntent;
import android.app.TaskInfo;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hidl.base.V1_0.DebugInfo$;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.Trace;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import androidx.car.app.serialization.Bundler$;
import com.android.app.displaylib.PerDisplayRepository;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.ActivityIntentHelper;
import com.android.systemui.DependencyEx;
import com.android.systemui.Dumpable;
import com.android.systemui.LauncherProxyService;
import com.android.systemui.activity.data.repository.ActivityIntentRepository;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.android.systemui.bluetooth.qsdialog.DeviceItem$;
import com.android.systemui.broadcast.ActionReceiver$;
import com.android.systemui.communal.data.db.CommunalWidgetItem$;
import com.android.systemui.compose.RecompositionTracerKt$;
import com.android.systemui.display.dagger.ReferenceSysUIDisplaySubcomponent;
import com.android.systemui.doze.DozeLogger$;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.media.controls.domain.resume.MediaResumeListener$getResumeAction$1$;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.PluginListener;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.plugins.custom.CustomRowEntry;
import com.android.systemui.plugins.remoteViews.RemoteViewsWrapper;
import com.android.systemui.plugins.statusbar.SeedlingPlugin;
import com.android.systemui.plugins.statusbar.SysUiStateController;
import com.android.systemui.plugins.statusbar.TaskStackChangeController;
import com.android.systemui.plugins.statusbar.UserProxy;
import com.android.systemui.plugins.template.TemplateProxy;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.shade.data.repository.ShadeDisplaysRepository;
import com.android.systemui.shared.recents.ILauncherProxy;
import com.android.systemui.statusbar.CommonProcessObserverEx;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.inflation.NotifInflateDelayStrategy;
import com.android.systemui.statusbar.notification.domain.interactor.NotificationLaunchAnimationInteractor;
import com.android.systemui.statusbar.notification.headsup.HeadsUpManager;
import com.android.systemui.statusbar.notification.headsup.OnHeadsUpChangedListener;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.OplusNotificationSectionsManagerEx;
import com.android.systemui.statusbar.phone.StartInKeyGuardShowParams;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.window.StatusBarWindowController;
import com.android.systemui.statusbar.window.StatusBarWindowControllerStore;
import com.android.systemui.statusbar.window.data.repository.StatusBarWindowStatePerDisplayRepository;
import com.android.systemui.statusbar.window.shared.model.StatusBarWindowState;
import com.android.systemui.util.AnimAfsManager;
import com.android.wm.shell.startingsurface.SplashscreenContentDrawer;
import com.android.wm.shell.startingsurface.StartingSurface;
import com.heytap.accessory.bean.FlowControlConfig$;
import com.oplus.app.OplusAppSwitchConfig;
import com.oplus.app.OplusAppSwitchManager;
import com.oplus.systemui.aod.display.AODDisplayUtil;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplus.systemui.aod.plugin.AODPluginManager;
import com.oplus.systemui.capsule.FlavorOneCapsuleController;
import com.oplus.systemui.keyguard.clockstyle.KeyguardStyleServiceCallbackImpl;
import com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController;
import com.oplus.systemui.notification.lockscreen.seedling.LockScreenSeedingInteractor;
import com.oplus.systemui.oplusplugin.OplusPluginDependencyProvider;
import com.oplus.systemui.oplusplugin.OplusUserContext;
import com.oplus.systemui.oplusplugin.RemoteViewsWrapperImp;
import com.oplus.systemui.statusbar.CommonProcessObserver;
import com.oplus.systemui.statusbar.SysUiStateControllerImpl;
import com.oplus.systemui.statusbar.TaskStackChangeControllerImpl;
import com.oplus.systemui.statusbar.notification.customcard.CustomViewManager;
import com.oplus.systemui.statusbar.notification.helper.NotificationKeyguardHelper;
import com.oplus.systemui.statusbar.notification.livealert.data.interactor.OplusLiveAlertNotificationsInteractor;
import com.oplus.systemui.statusbar.phone.OplusCentralSurfacesExImpl;
import com.oplus.systemui.statusbar.phone.OplusCentralSurfacesExImplKt;
import com.oplus.systemui.statusbar.phone.StatusBarPaddingProvider;
import com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.dynamicevent.DynamicEventRepository;
import com.oplus.systemui.statusbar.phone.fragment.CollapsedStatusBarViewModelEx;
import com.oplus.systemui.statusbar.seeding.livealert.LiteUmsLoader;
import com.oplus.systemui.statusbar.seeding.livealert.SeedlingServiceObserver;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.observer.ChildrenModeObserver;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.sched.OplusFrameRateManager;
import com.oplusos.systemui.common.sched.OplusSysUiSchedMgr;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ScrimUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import dagger.Lazy;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Deprecated;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex$;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SharedFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeedlingPluginManager implements PluginListener, Dumpable {
    private static final String ENTRY_TYPE_NOTIFICATION = "notification";
    private static final String ENTRY_TYPE_STATUS_BAR = "statusBar";
    private static final String KEY_APP_USER_ID = "baseUserId";
    private static final String KEY_DISPLAY_ID = "displayid";
    private static final String KEY_ENTRANCE_TYPE = "entrance_type";
    private static final String KEY_ENTRY_TYPE = "entryType";
    private static final String KEY_IS_TRANSIENT_HIDE = "isTransientHide";
    private static final String KEY_KEYGUARD_SHOWING = "keyguardShowing";
    private static final String KEY_TASK_ID = "taskId";
    private static final String METHOD_CHECK_LIVE_ALERT_ENABLE_STATE = "checkLiveAlertEnableState";
    private static final String METHOD_DISALLOW_STATUS_BAR_ICON_SCALE = "disallowStatusBarIconScale";
    private static final String METHOD_DISMISS_NOTIFICATION_LIVE_ALERT = "dismissNotificationLiveAlert";
    private static final String METHOD_IS_MENU_ICON_WITH_RESOURCE_SUPPORTED = "isMenuIconWithResourceSupported";
    private static final String METHOD_NOTIFY_LIVE_ALERT_CHANGED = "notifyLiveAlertChanged";
    private static final String METHOD_ON_KEYGUARD_STATE_CHANGED = "keyguardChanged";
    private static final String METHOD_ON_SCREEN_STATE_CHANGED = "screenChanged";
    private static final String METHOD_START_PENDINGINTENT = "startPendingIntent";
    private static final String METHOD_START_TASK = "startTask";
    private static final String PARAM_IS_DISALLOWED = "isDisallowed";
    private static final String PARAM_IS_KEYGUARD_OCCLUDED = "isKeyguardOccluded";
    private static final String PARAM_IS_KEYGUARD_VISIBLE = "isKeyguardVisible";
    private static final String PARAM_IS_SCREEN_ON = "isScreenOn";
    private static final String PARAM_IS_SUPPORTED = "isSupported";
    private static final String PARAM_LIVE_ALERT_ENABLE_STATE = "liveAlertEnableState";
    private static final String PARAM_NOTIFICATION_KEY = "key";
    private static final String PARAM_PKG = "pkg";
    private static final String PARAM_UID = "uid";
    private static final String TAG = "SeedlingPluginManager";
    private final ActivityIntentHelper activityIntentHelper;
    private final ActivityStarter activityStarter;
    private OplusAppSwitchManager.OnAppSwitchObserver appSwitchObserver;
    private final CopyOnWriteArrayList<Listener> callbacks = new CopyOnWriteArrayList<>();
    private CapsuleContainerStateListener capsuleListener;
    private final Lazy centralSurfacesEx;
    private final CommonProcessObserver commonProcessObserver;
    private final ConfigurationController configurationController;
    private SeedlingPluginManager$configurationListener$1 configurationListener;
    private final Context context;
    private final CustomViewManager customViewManager;
    private final Lazy deviceProvisionedController;
    private final Lazy displaySubComponentRepository;
    private final DumpManager dumpManager;
    public boolean hasSystemCapsule;
    private final HeadsUpManager headsUpManager;
    private final HiddenIconDispatcher hiddenIconDispatcher;
    private final StatusBarPaddingProvider.IScreenHoleChanged holeRectListener;
    public boolean isAdd;
    public boolean isForceStatusBarVisible;
    public boolean isPluginAvailable;
    public boolean isShowActivityOverLockscreen;
    private final Lazy keyguardHelper;
    private final SeedlingPluginManager$keyguardStyleServiceCallback$1 keyguardStyleServiceCallback;
    private KeyguardUpdateMonitor keyguardUpdateMonitor;
    private final KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback;
    private String lastLoadException;
    private String lastLoadMsg;
    public boolean liteSeedlingEnable;
    private final kotlin.Lazy liteUmsLoader$delegate;
    private final Lazy lockScreenSeedingInteractor;
    private LauncherProxyService mOverviewProxyService;
    private final kotlin.Lazy mScreenLifeCycleObserver$delegate;
    private final ShadeDisplaysRepository mShadeDisplaysRepository;
    private NotificationStackScrollLayoutController mStackScrollerController;
    private final Handler mainHandler;
    private final NotificationLaunchAnimationInteractor notificationLaunchAnimationInteractor;
    private final OplusLiveAlertNotificationsInteractor notificationLiveAlertInteractor;
    private final INotificationManager notificationManager;
    private final Lazy notificationSectionsManagerEx;
    private final OnHeadsUpChangedListener onHeadsUpChangedListener;
    private final OplusKgdImmersiveController oplusKgdImmersiveController;
    private final OplusPluginDependencyProvider oplusPluginDependency;
    private final OplusUserContext oplusUserContext;
    private final Lazy panelViewControllerLazy;
    private Context pluginContext;
    private final PluginManager pluginManager;
    private final CommonProcessObserverEx.ICommonProcessObserver processObserver;
    private SeedlingPlugin.IStub proxy;
    private final RemoteViewsWrapperImp remoteViewsWrapperImp;
    private final CoroutineScope scope;
    private final Lazy screenLifecycle;
    private final IObserverListener seedlingEnableListener;
    private SeedlingPlugin seedlingPlugin;
    private final Optional<StartingSurface> startingSurfaceOptional;
    private final StatusBarPaddingProvider statusBarPaddingProvider;
    private final SysuiStatusBarStateController statusBarStateController;
    private final StatusBarWindowControllerStore statusBarWindowControllerStore;
    private StatusBarWindowStatePerDisplayRepository statusBarWindowStateRepository;
    private final SysUiStateControllerImpl sysUiStateController;
    private final SysUiStateController.ISysUiStateListener sysUiStateListener;
    private final TaskStackChangeControllerImpl taskStackChangeController;
    private TemplateProxy.Listener templateViewStatusListener;
    private final UserTracker userTracker;
    private Job windowStateCollectJob;
    public static final Companion Companion = new Companion();
    private static final kotlin.Lazy isFluidEntrySupported$delegate = LazyKt__LazyJVMKt.lazy(new SeedlingPluginManager$$ExternalSyntheticLambda2());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static boolean isFluidEntrySupported() {
            return ((Boolean) SeedlingPluginManager.isFluidEntrySupported$delegate.getValue()).booleanValue();
        }

        public final boolean isSupportCapsuleFake(Context context) {
            return isFluidEntrySupported() && ScrimUtil.isHighGaussianLevel(context);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class SeedlingHost implements SeedlingPlugin.IStub {
        private final Context context;

        public SeedlingHost(Context context) {
            this.context = context;
        }

        private final void setForceStatusBarVisible(boolean z) {
            SeedlingPluginManager seedlingPluginManager = SeedlingPluginManager.this;
            ((StatusBarWindowController) seedlingPluginManager.statusBarWindowControllerStore.getDefaultDisplay()).setForceStatusBarVisible(z, "SeedlingPluginManager#setForceStatusBarVisible");
            seedlingPluginManager.isForceStatusBarVisible = z;
        }

        private final void setFrameRate(Bundle bundle) {
            String string = bundle.getString("reason");
            float f = bundle.getFloat("targetFps");
            float f2 = bundle.getFloat("prerequisiteFps");
            if (string != null) {
                OplusFrameRateManager.INSTANCE.setFrameRate(SeedlingPluginManager.this.context, string, f, f2);
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        public Bundle call(Bundle bundle) {
            String str;
            String str2;
            String str3;
            String str4;
            String str5;
            boolean z;
            Bundle bundle2 = new Bundle();
            boolean zContainsKey = bundle.containsKey("method");
            SeedlingPluginManager seedlingPluginManager = SeedlingPluginManager.this;
            if (zContainsKey) {
                String string = bundle.getString("method");
                if (string != null) {
                    int iHashCode = string.hashCode();
                    str2 = LogUtil.TAG_STATUSBAR;
                    String str6 = SeedlingPluginManager.TAG;
                    switch (iHashCode) {
                        case -2129302265:
                            str = str6;
                            if (string.equals(SeedlingPluginManager.METHOD_START_TASK)) {
                                Intent intent = (Intent) bundle.getParcelable("startIntent");
                                int i = bundle.getInt(SeedlingPluginManager.KEY_TASK_ID);
                                int i2 = bundle.getInt("userId");
                                boolean z2 = bundle.getBoolean("isOverLockscreen");
                                String string2 = bundle.getString("serviceId");
                                String string3 = bundle.getString(SeedlingPluginManager.KEY_ENTRY_TYPE);
                                boolean z3 = bundle.getBoolean("overrideTransition", false);
                                boolean z4 = seedlingPluginManager.keyguardUpdateMonitor.mKeyguardShowing;
                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str2)) {
                                    StringBuilder sb = new StringBuilder("start task taskId:");
                                    sb.append(i);
                                    sb.append(" baseIntent:");
                                    sb.append(intent);
                                    sb.append(" overrideTransition:");
                                    DeviceItem$.ExternalSyntheticOutline0.m(" ", string3, " ", sb, z3);
                                    Log.d("SystemUi--Statusbar", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(" ", "SeedlingPluginManager-->", sb, z2, z4));
                                }
                                if (SeedlingPluginManager.ENTRY_TYPE_NOTIFICATION.equals(string3)) {
                                    ((OplusCentralSurfacesExImpl) seedlingPluginManager.centralSurfacesEx.get()).startInKeyGuardShow(new StartInKeyGuardShowParams.StartTaskIdInKeyGuardShowParam(string2, i, intent, z2, z3));
                                } else if (SeedlingPluginManager.ENTRY_TYPE_STATUS_BAR.equals(string3) && i != -1 && z4) {
                                    SeedlingPluginManager$SeedlingHost$startTaskInKeyGuardShow$postKeyguardAction$1 seedlingPluginManager$SeedlingHost$startTaskInKeyGuardShow$postKeyguardAction$1 = new SeedlingPluginManager$SeedlingHost$startTaskInKeyGuardShow$postKeyguardAction$1();
                                    seedlingPluginManager$SeedlingHost$startTaskInKeyGuardShow$postKeyguardAction$1.$taskId = i;
                                    seedlingPluginManager$SeedlingHost$startTaskInKeyGuardShow$postKeyguardAction$1.$baseIntent = intent;
                                    seedlingPluginManager$SeedlingHost$startTaskInKeyGuardShow$postKeyguardAction$1.this$0 = this;
                                    seedlingPluginManager$SeedlingHost$startTaskInKeyGuardShow$postKeyguardAction$1.$userId = i2;
                                    VarHandle.storeStoreFence();
                                    if (z2) {
                                        seedlingPluginManager.setLaunchActivityOverLockscreen(true);
                                        seedlingPluginManager$SeedlingHost$startTaskInKeyGuardShow$postKeyguardAction$1.onDismiss();
                                    } else {
                                        seedlingPluginManager.activityStarter.dismissKeyguardThenExecute(seedlingPluginManager$SeedlingHost$startTaskInKeyGuardShow$postKeyguardAction$1, (Runnable) null, true);
                                    }
                                }
                                bundle2.putBoolean("keyguardShowing", z4);
                                return bundle2;
                            }
                            bundle2 = bundle2;
                            str3 = "!";
                            str4 = "call ";
                            break;
                        case -2034446997:
                            if (string.equals(SeedlingPluginManager.METHOD_DISALLOW_STATUS_BAR_ICON_SCALE)) {
                                ((HiddenIconDispatcherImpl) seedlingPluginManager.hiddenIconDispatcher).disallowStatusBarIconScale(bundle.getBoolean(SeedlingPluginManager.PARAM_IS_DISALLOWED, false));
                                return bundle2;
                            }
                            str2 = str2;
                            str3 = "!";
                            str4 = "call ";
                            str = str6;
                            break;
                        case -1983627515:
                            if (string.equals("requestImmersiveState")) {
                                int i3 = bundle.getInt("mode");
                                int i4 = bundle.getInt("reason");
                                String string4 = bundle.getString("item");
                                if (string4 != null) {
                                    seedlingPluginManager.customViewManager.requestImmersiveState(i3, i4, string4);
                                    return bundle2;
                                }
                            }
                            str2 = str2;
                            str3 = "!";
                            str4 = "call ";
                            str = str6;
                            break;
                        case -1872436166:
                            str5 = str2;
                            if (string.equals("setForceStatusBarVisible")) {
                                setForceStatusBarVisible(bundle.getBoolean("visible"));
                                return bundle2;
                            }
                            str2 = str5;
                            str = str6;
                            str3 = "!";
                            str4 = "call ";
                            break;
                        case -1488947194:
                            str5 = str2;
                            if (string.equals(SeedlingPluginManager.METHOD_CHECK_LIVE_ALERT_ENABLE_STATE)) {
                                String string5 = bundle.getString("pkg", "");
                                int i5 = bundle.getInt(SeedlingPluginManager.PARAM_UID, 0);
                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str5)) {
                                    Log.d("SystemUi--Statusbar", "SeedlingPluginManager-->".concat("Received a request to check live alert status: " + string5 + " " + i5));
                                }
                                if (!TextUtils.isEmpty(string5) && i5 != 0) {
                                    bundle2.putBoolean(SeedlingPluginManager.PARAM_LIVE_ALERT_ENABLE_STATE, seedlingPluginManager.notificationManager.appCanBePromoted(string5, i5));
                                    return bundle2;
                                }
                            }
                            str2 = str5;
                            str = str6;
                            str3 = "!";
                            str4 = "call ";
                            break;
                        case -1421484713:
                            str6 = str6;
                            if (string.equals("updateCacheMusicArt")) {
                                Bitmap bitmap = (Bitmap) bundle.getParcelable("lockWallpaper");
                                LogUtil.i$default(str2, str6, "updateCacheMusicArt " + bitmap, null, 8);
                                seedlingPluginManager.oplusKgdImmersiveController.updateCacheMusicArt(bitmap);
                                return bundle2;
                            }
                            str = str6;
                            str3 = "!";
                            str4 = "call ";
                            break;
                        case -1262385999:
                            str6 = str6;
                            if (string.equals("isHideForCharge")) {
                                bundle2.putBoolean("valueHideForCharge", ((NotificationKeyguardHelper) seedlingPluginManager.keyguardHelper.get()).mHideForCharge);
                                return bundle2;
                            }
                            str = str6;
                            str3 = "!";
                            str4 = "call ";
                            break;
                        case -658701809:
                            str6 = str6;
                            if (string.equals("onAvailableCallback")) {
                                boolean z5 = bundle.getBoolean("available", false);
                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str2)) {
                                    OplusLockIconView$.ExternalSyntheticOutline0.m("receive plugin available ", "SeedlingPluginManager-->", "SystemUi--Statusbar", z5);
                                }
                                SeedlingPluginManager.access$onPluginAvailableCallback(seedlingPluginManager, z5);
                                return bundle2;
                            }
                            str = str6;
                            str3 = "!";
                            str4 = "call ";
                            break;
                        case -604463571:
                            str6 = str6;
                            if (string.equals(SeedlingPluginManager.METHOD_NOTIFY_LIVE_ALERT_CHANGED)) {
                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str2)) {
                                    Log.d("SystemUi--Statusbar", "SeedlingPluginManager-->Received a request to update live alerts");
                                }
                                seedlingPluginManager.notificationLiveAlertInteractor.updateLiveAlerts();
                                return bundle2;
                            }
                            str = str6;
                            str3 = "!";
                            str4 = "call ";
                            break;
                        case -431818287:
                            str6 = str6;
                            if (string.equals(SeedlingPluginManager.METHOD_START_PENDINGINTENT)) {
                                PendingIntent pendingIntent = (PendingIntent) bundle.getParcelable("startIntent");
                                int i6 = bundle.getInt("userId");
                                boolean z6 = bundle.getBoolean("isOverLockscreen");
                                boolean z7 = bundle.getBoolean("overrideTransition", false);
                                String string6 = bundle.getString("serviceId");
                                String string7 = bundle.getString(SeedlingPluginManager.KEY_ENTRY_TYPE);
                                boolean z8 = seedlingPluginManager.keyguardUpdateMonitor.mKeyguardShowing;
                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str2)) {
                                    StringBuilder sb2 = new StringBuilder("start PendingIntent PendingIntent: ");
                                    sb2.append(pendingIntent);
                                    sb2.append(" entryType: ");
                                    sb2.append(string7);
                                    sb2.append(" overLockscreen:");
                                    KeyguardLogger$.ExternalSyntheticOutline0.m(" isKeyguardShowing:", " overrideTransition:", sb2, z6, z8);
                                    SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("SeedlingPluginManager-->", "SystemUi--Statusbar", sb2, z7);
                                }
                                if (pendingIntent != null && SeedlingPluginManager.ENTRY_TYPE_NOTIFICATION.equals(string7)) {
                                    ((OplusCentralSurfacesExImpl) seedlingPluginManager.centralSurfacesEx.get()).startInKeyGuardShow(new StartInKeyGuardShowParams.StartPendingIntentInKeyGuardShowParam(string6, pendingIntent, z6, z7));
                                } else if (SeedlingPluginManager.ENTRY_TYPE_STATUS_BAR.equals(string7) && pendingIntent != null && z8) {
                                    boolean z9 = z6 || seedlingPluginManager.activityIntentHelper.wouldPendingShowOverLockscreen(i6, pendingIntent);
                                    SeedlingPluginManager$SeedlingHost$startPendingIntentInKeyGuardShow$postKeyguardAction$1 seedlingPluginManager$SeedlingHost$startPendingIntentInKeyGuardShow$postKeyguardAction$1 = new SeedlingPluginManager$SeedlingHost$startPendingIntentInKeyGuardShow$postKeyguardAction$1();
                                    seedlingPluginManager$SeedlingHost$startPendingIntentInKeyGuardShow$postKeyguardAction$1.$pendingIntent = pendingIntent;
                                    seedlingPluginManager$SeedlingHost$startPendingIntentInKeyGuardShow$postKeyguardAction$1.this$0 = this;
                                    VarHandle.storeStoreFence();
                                    if (z9) {
                                        seedlingPluginManager.setLaunchActivityOverLockscreen(true);
                                        seedlingPluginManager$SeedlingHost$startPendingIntentInKeyGuardShow$postKeyguardAction$1.onDismiss();
                                    } else {
                                        seedlingPluginManager.activityStarter.dismissKeyguardThenExecute(seedlingPluginManager$SeedlingHost$startPendingIntentInKeyGuardShow$postKeyguardAction$1, (Runnable) null, true);
                                    }
                                }
                                bundle2.putBoolean("keyguardShowing", z8);
                                return bundle2;
                            }
                            str = str6;
                            str3 = "!";
                            str4 = "call ";
                            break;
                        case -174217777:
                            str6 = str6;
                            if (string.equals("notifyCapsuleChangeToLauncher")) {
                                LauncherProxyService launcherProxyService = seedlingPluginManager.mOverviewProxyService;
                                launcherProxyService.getClass();
                                try {
                                    ILauncherProxy iLauncherProxy = launcherProxyService.mLauncherProxy;
                                    if (iLauncherProxy != null) {
                                        iLauncherProxy.notifyCapsuleChange(bundle);
                                        return bundle2;
                                    }
                                    Log.e("LauncherProxyService", "Failed to get overview proxy for notifyCapsuleChange.");
                                    return bundle2;
                                } catch (RemoteException e) {
                                    Log.e("LauncherProxyService", "Failed to call notifyCapsuleChange()", e);
                                    return bundle2;
                                }
                            }
                            str = str6;
                            str3 = "!";
                            str4 = "call ";
                            break;
                        case 380660929:
                            str6 = str6;
                            if (string.equals("dismissPopupMenu")) {
                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str2)) {
                                    Log.d("SystemUi--Statusbar", "SeedlingPluginManager-->Received a request to dismissPopupMenu");
                                }
                                seedlingPluginManager.customViewManager.dismissPopupMenu(bundle.getString("dismissReason"));
                                return bundle2;
                            }
                            str = str6;
                            str3 = "!";
                            str4 = "call ";
                            break;
                        case 444627760:
                            if (string.equals("setProcessUx")) {
                                int i7 = bundle.getInt("type");
                                boolean z10 = bundle.getBoolean("open");
                                int i8 = 3;
                                if (i7 != 3006) {
                                    if (i7 != 3007) {
                                        LogUtil.w$default(8, LogUtil.TAG_COMMON, str6, "unsupported seedling scene: " + i7);
                                        return bundle2;
                                    }
                                    String string8 = bundle.getString(DynamicEventRepository.EXTRA_EVENT_TYPE);
                                    if (TextUtils.isEmpty(string8)) {
                                        LogUtil.w$default(8, LogUtil.TAG_COMMON, str6, "eventType is empty");
                                        return bundle2;
                                    }
                                    OplusSysUiSchedMgr.Companion.getInstance().schedForSeedlingEventTriggered(z10, string8);
                                    Handler handler = seedlingPluginManager.mainHandler;
                                    RunnableC03071 runnableC03071 = new RunnableC03071(i8);
                                    runnableC03071.this$0 = string8;
                                    VarHandle.storeStoreFence();
                                    handler.postDelayed(runnableC03071, 500L);
                                    return bundle2;
                                }
                                String string9 = bundle.getString("animationType");
                                if (TextUtils.isEmpty(string9)) {
                                    LogUtil.w$default(8, LogUtil.TAG_COMMON, str6, "animationType is empty");
                                    return bundle2;
                                }
                                AnimAfsManager.INSTANCE.scheduleAnimationCtrl(100, z10);
                                OplusSysUiSchedMgr.Companion.getInstance().schedForSeedlingAnimation(z10, string9, 1000);
                                if (bundle.getBoolean("suppressNotification", true)) {
                                    try {
                                        Trace.traceBegin(8L, "NotifInflateDelayStrategy " + z10);
                                        NotifInflateDelayStrategy notifInflateDelayStrategy = (NotifInflateDelayStrategy) DependencyEx.sDependency.getDependency(NotifInflateDelayStrategy.class);
                                        if (notifInflateDelayStrategy == null) {
                                            LogUtil.w$default(8, LogUtil.TAG_COMMON, str6, "DependencyEx.get() returned null strategy");
                                        } else if (!z10 || ((NotificationPanelViewController) seedlingPluginManager.panelViewControllerLazy.get()).isFullyExpanded()) {
                                            notifInflateDelayStrategy.stopWaitAction(3);
                                        } else {
                                            notifInflateDelayStrategy.startWaitAction(3);
                                        }
                                        return bundle2;
                                    } finally {
                                        Trace.traceEnd(8L);
                                    }
                                }
                            }
                            str3 = "!";
                            str4 = "call ";
                            str = str6;
                            break;
                        case 624218419:
                            if (string.equals("onShowSeedlingCard")) {
                                bundle2.putString("pkg", ((OplusNotificationSectionsManagerEx) seedlingPluginManager.notificationSectionsManagerEx.get()).hideHeadsUpNotification(true, false));
                                return bundle2;
                            }
                            str3 = "!";
                            str4 = "call ";
                            str = str6;
                            break;
                        case 758191463:
                            if (string.equals("isExpRegion")) {
                                FeatureOption.INSTANCE.getClass();
                                bundle2.putBoolean("valueExpRegion", FeatureOption.isExpRegion());
                                return bundle2;
                            }
                            str3 = "!";
                            str4 = "call ";
                            str = str6;
                            break;
                        case 898544124:
                            if (string.equals("setProcessFps")) {
                                setFrameRate(bundle);
                                return bundle2;
                            }
                            str3 = "!";
                            str4 = "call ";
                            str = str6;
                            break;
                        case 993823608:
                            if (string.equals(SeedlingPluginManager.METHOD_IS_MENU_ICON_WITH_RESOURCE_SUPPORTED)) {
                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str2)) {
                                    Log.d("SystemUi--Statusbar", "SeedlingPluginManager-->isMenuIconWithResourceSupported");
                                }
                                bundle2.putBoolean(SeedlingPluginManager.PARAM_IS_SUPPORTED, true);
                                return bundle2;
                            }
                            str3 = "!";
                            str4 = "call ";
                            str = str6;
                            break;
                        case 1136583762:
                            if (string.equals("isChildernMode")) {
                                bundle2.putBoolean("valueChildernMode", ChildrenModeObserver.getInstance().mIsChildrenMode);
                                return bundle2;
                            }
                            str3 = "!";
                            str4 = "call ";
                            str = str6;
                            break;
                        case 1154794351:
                            if (string.equals("isOnKeyguard")) {
                                bundle2.putBoolean("valueOnKeyguard", seedlingPluginManager.statusBarStateController.getState() == 1);
                                return bundle2;
                            }
                            str3 = "!";
                            str4 = "call ";
                            str = str6;
                            break;
                        case 1168773166:
                            if (string.equals(SeedlingPluginManager.METHOD_ON_KEYGUARD_STATE_CHANGED)) {
                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str2)) {
                                    Log.d("SystemUi--Statusbar", "SeedlingPluginManager-->Received a request to get the keyguard status");
                                }
                                bundle2.putBoolean(SeedlingPluginManager.PARAM_IS_KEYGUARD_VISIBLE, seedlingPluginManager.keyguardUpdateMonitor.isKeyguardVisible$1());
                                bundle2.putBoolean(SeedlingPluginManager.PARAM_IS_KEYGUARD_OCCLUDED, seedlingPluginManager.keyguardUpdateMonitor.mKeyguardOccluded);
                                return bundle2;
                            }
                            str3 = "!";
                            str4 = "call ";
                            str = str6;
                            break;
                        case 1188583438:
                            if (string.equals("startActivityAsUser")) {
                                Intent intent2 = (Intent) bundle.getParcelable("startIntent");
                                int i9 = bundle.getInt("userId");
                                boolean z11 = bundle.getBoolean("isOverLockscreen");
                                boolean z12 = seedlingPluginManager.keyguardUpdateMonitor.mKeyguardShowing;
                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str2)) {
                                    OplusLockIconView$.ExternalSyntheticOutline0.m("isKeyguardShowing ", "SeedlingPluginManager-->", "SystemUi--Statusbar", z12);
                                }
                                if (intent2 == null || !z12) {
                                    z = true;
                                } else {
                                    boolean z13 = z11 || ActivityIntentRepository.wouldActivityInfoShowOverLockscreen(seedlingPluginManager.activityIntentHelper.getTargetActivityInfo(intent2, i9, false));
                                    SeedlingPluginManager$SeedlingHost$startActivityInKeyGuardShow$postKeyguardAction$1 seedlingPluginManager$SeedlingHost$startActivityInKeyGuardShow$postKeyguardAction$1 = new SeedlingPluginManager$SeedlingHost$startActivityInKeyGuardShow$postKeyguardAction$1();
                                    seedlingPluginManager$SeedlingHost$startActivityInKeyGuardShow$postKeyguardAction$1.this$0 = this;
                                    seedlingPluginManager$SeedlingHost$startActivityInKeyGuardShow$postKeyguardAction$1.$intent = intent2;
                                    seedlingPluginManager$SeedlingHost$startActivityInKeyGuardShow$postKeyguardAction$1.$userId = i9;
                                    VarHandle.storeStoreFence();
                                    if (z13) {
                                        z = true;
                                        seedlingPluginManager.setLaunchActivityOverLockscreen(true);
                                        seedlingPluginManager$SeedlingHost$startActivityInKeyGuardShow$postKeyguardAction$1.onDismiss();
                                    } else {
                                        z = true;
                                        seedlingPluginManager.activityStarter.dismissKeyguardThenExecute(seedlingPluginManager$SeedlingHost$startActivityInKeyGuardShow$postKeyguardAction$1, (Runnable) null, true);
                                    }
                                }
                                bundle2.putBoolean("startRemoteAnimator", z ^ z12);
                                return bundle2;
                            }
                            str3 = "!";
                            str4 = "call ";
                            str = str6;
                            break;
                        case 1438944937:
                            if (string.equals("resetProcessFps")) {
                                String string10 = bundle.getString("reason");
                                if (string10 != null) {
                                    OplusFrameRateManager.INSTANCE.resetFrameRate(seedlingPluginManager.context, string10);
                                    return bundle2;
                                }
                            }
                            str3 = "!";
                            str4 = "call ";
                            str = str6;
                            break;
                        case 1479312059:
                            if (string.equals(SeedlingPluginManager.METHOD_DISMISS_NOTIFICATION_LIVE_ALERT)) {
                                String string11 = bundle.getString(SeedlingPluginManager.PARAM_NOTIFICATION_KEY, "");
                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str2)) {
                                    MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("dismissLiveAlert: ", string11, "SeedlingPluginManager-->", "SystemUi--Statusbar");
                                }
                                OplusLiveAlertNotificationsInteractor oplusLiveAlertNotificationsInteractor = seedlingPluginManager.notificationLiveAlertInteractor;
                                string11.getClass();
                                oplusLiveAlertNotificationsInteractor.dismissLiveAlert(string11);
                                return bundle2;
                            }
                            str3 = "!";
                            str4 = "call ";
                            str = str6;
                            break;
                        case 1631389684:
                            if (string.equals("isDeviceProvisioned")) {
                                bundle2.putBoolean("valueDeviceProvisioned", ((DeviceProvisionedController) seedlingPluginManager.deviceProvisionedController.get()).isDeviceProvisioned());
                                return bundle2;
                            }
                            str3 = "!";
                            str4 = "call ";
                            str = str6;
                            break;
                        case 1889947342:
                            if (string.equals("updateLockWallpaper")) {
                                OplusKgdImmersiveController.updateImmersiveLockScreenWallpaper$default(seedlingPluginManager.oplusKgdImmersiveController, (Bitmap) bundle.getParcelable("lockWallpaper"), null, 6);
                                return bundle2;
                            }
                            str3 = "!";
                            str4 = "call ";
                            str = str6;
                            break;
                        case 1905454413:
                            if (string.equals("loopStartActivity")) {
                                ArrayList parcelableArrayList = bundle.getParcelableArrayList("intentList");
                                String string12 = bundle.getString("serviceId");
                                boolean z14 = bundle.getBoolean("isOverLockscreen");
                                boolean z15 = bundle.getBoolean("overrideTransition", false);
                                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(str2)) {
                                    Log.d("SystemUi--Statusbar", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("start loop start activity overLockscreen:", " overrideTransition:", "SeedlingPluginManager-->", z14, z15));
                                }
                                Iterator it = parcelableArrayList != null ? parcelableArrayList.iterator() : null;
                                if (it != null) {
                                    ((OplusCentralSurfacesExImpl) seedlingPluginManager.centralSurfacesEx.get()).startInKeyGuardShow(new StartInKeyGuardShowParams.StartIntentInKeyGuardShowParam(it, string12, z14, z15));
                                    return bundle2;
                                }
                            }
                            str3 = "!";
                            str4 = "call ";
                            str = str6;
                            break;
                        case 1941917642:
                            if (string.equals("startSettingIntent")) {
                                Intent intent3 = (Intent) bundle.getParcelable("startIntent");
                                int i10 = bundle.getInt("appUid");
                                String string13 = bundle.getString("serviceKey");
                                int i11 = bundle.getInt(SeedlingPluginManager.KEY_ENTRANCE_TYPE, -1);
                                if (intent3 != null && string13 != null) {
                                    seedlingPluginManager.customViewManager.startSettingIntent(intent3, i10, string13, i11);
                                    return bundle2;
                                }
                            }
                            str3 = "!";
                            str4 = "call ";
                            str = str6;
                            break;
                        default:
                            str3 = "!";
                            str4 = "call ";
                            str = str6;
                            break;
                    }
                } else {
                    str = SeedlingPluginManager.TAG;
                    str2 = LogUtil.TAG_STATUSBAR;
                    str3 = "!";
                    str4 = "call ";
                }
                LogUtil.w$default(8, str2, str, Bundler$.ExternalSyntheticOutline0.m(str4, string, str3));
                return bundle2;
            }
            if (!bundle.containsKey("state")) {
                LogUtil.w$default(8, LogUtil.TAG_STATUSBAR, SeedlingPluginManager.TAG, "receive data " + bundle);
                return bundle2;
            }
            String string14 = bundle.getString("state");
            if (!Intrinsics.areEqual(string14, "hasCapsule")) {
                LogUtil.w$default(8, LogUtil.TAG_STATUSBAR, SeedlingPluginManager.TAG, Bundler$.ExternalSyntheticOutline0.m("call ", string14, "!"));
                return bundle2;
            }
            seedlingPluginManager.hasSystemCapsule = bundle.getBoolean("has_system", false);
            CapsuleContainerStateListener capsuleListener = seedlingPluginManager.getCapsuleListener();
            if (capsuleListener != null) {
                ((CollapsedStatusBarViewModelEx) capsuleListener).onSystemCapsuleChanged(seedlingPluginManager.hasSystemCapsule);
            }
            return bundle2;
        }

        public void dispatchEvent(MotionEvent motionEvent) {
            Iterator it = SeedlingPluginManager.this.callbacks.iterator();
            while (it.hasNext()) {
                ((Listener) it.next()).onDispatchTouchEvent(motionEvent);
            }
        }

        public int getBackgroundColor(Context context, TaskInfo taskInfo) {
            int backgroundColor;
            SeedlingPluginManager seedlingPluginManager = SeedlingPluginManager.this;
            if (seedlingPluginManager.startingSurfaceOptional.isPresent()) {
                backgroundColor = ((StartingSurface) seedlingPluginManager.startingSurfaceOptional.get()).getBackgroundColor(taskInfo);
            } else {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    Log.d("SystemUi--Statusbar", "SeedlingPluginManager-->No starting surface, defaulting to SystemBGColor");
                }
                backgroundColor = SplashscreenContentDrawer.getSystemBGColor();
            }
            return OplusCentralSurfacesExImplKt.refactSplashscreenBgColor(context, backgroundColor);
        }

        public final Context getContext() {
            return this.context;
        }

        public Object getProxy(String str) {
            if (Intrinsics.areEqual(str, "aodPlugin")) {
                return AODPluginManager.getInstance().getAODManager();
            }
            return null;
        }

        public int onSeedingNotificationDataChange(List<? extends CustomRowEntry> list, boolean z) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "SeedlingPluginManager-->onSeedingNotificationDataChange");
            }
            SeedlingPluginManager.this.customViewManager.onSeedingNotificationDataChange(list, z);
            return 1;
        }

        public void requestScreenState(View view, int i) {
            if (AODDisplayUtil.getInstance(this.context).isPanoramicProcessType()) {
                SmoothTransitionController.Companion.getInstance(this.context).requestScreenStateForSysUiComponent(SeedlingPluginManager.TAG, 500);
            }
        }

        public void startPopupMenu(CustomRowEntry customRowEntry) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "SeedlingPluginManager-->startPopupMenu");
            }
            SeedlingPluginManager.this.customViewManager.startPopupMenu(customRowEntry);
        }

        public void updateMusicArtBg(CustomRowEntry customRowEntry) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "SeedlingPluginManager-->updateMusicArtBg");
            }
            SeedlingPluginManager.this.oplusKgdImmersiveController.updateMusicArtBg(customRowEntry != null ? customRowEntry.getImmersiveBgView() : null);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.seeding.SeedlingPluginManager$addCallback$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ Object $listener;
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ SeedlingPluginManager this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    SeedlingPluginManager seedlingPluginManager = this.this$0;
                    if (seedlingPluginManager.isPluginAvailable && seedlingPluginManager.getSeedlingPlugin() != null) {
                        ((Listener) this.$listener).onPluginAdded(this.this$0.getSeedlingPlugin(), this.this$0.context);
                        break;
                    }
                    break;
                default:
                    LogUtil.i$default(LogUtil.TAG_STATUSBAR, SeedlingPluginManager.TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("seedling enable changed. Loaded: ", this.this$0.isAdd), null, 8);
                    SeedlingPluginManager seedlingPluginManager2 = this.this$0;
                    seedlingPluginManager2.liteSeedlingEnable = true;
                    seedlingPluginManager2.requestLoadPlugin("seedling enable");
                    SeedlingServiceObserver.Companion companion = SeedlingServiceObserver.Companion;
                    companion.getInstance().unregister(this.this$0.context);
                    companion.getInstance().removeListener((SeedlingPluginManager$seedlingEnableListener$1) this.$listener);
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.seeding.SeedlingPluginManager$init$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class RunnableC03071 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ Object this$0;

        public /* synthetic */ RunnableC03071(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    ((SeedlingPluginManager) this.this$0).getLiteUmsLoader().init();
                    ((SeedlingPluginManager) this.this$0).liteSeedlingEnable = SeedlingServiceObserver.Companion.getInstance().existSeedlingService(((SeedlingPluginManager) this.this$0).context);
                    LogUtil.i$default(LogUtil.TAG_STATUSBAR, SeedlingPluginManager.TAG, KeyguardLogger$.ExternalSyntheticOutline0.m("seedling enable: ", ((SeedlingPluginManager) this.this$0).liteSeedlingEnable), null, 8);
                    SeedlingPluginManager seedlingPluginManager = (SeedlingPluginManager) this.this$0;
                    if (!seedlingPluginManager.liteSeedlingEnable) {
                        RunnableC03071 runnableC03071 = new RunnableC03071(2);
                        runnableC03071.this$0 = seedlingPluginManager;
                        VarHandle.storeStoreFence();
                        SystemUIThreadUtil.runOnUiThread(runnableC03071);
                    } else {
                        RunnableC03071 runnableC03072 = new RunnableC03071(1);
                        runnableC03072.this$0 = seedlingPluginManager;
                        VarHandle.storeStoreFence();
                        SystemUIThreadUtil.runOnUiThread(runnableC03072);
                    }
                    break;
                case 1:
                    ((SeedlingPluginManager) this.this$0).requestLoadPlugin("seedling enable auto load");
                    SeedlingServiceObserver.Companion companion = SeedlingServiceObserver.Companion;
                    companion.getInstance().unregister(((SeedlingPluginManager) this.this$0).context);
                    companion.getInstance().removeListener(((SeedlingPluginManager) this.this$0).seedlingEnableListener);
                    break;
                case 2:
                    SeedlingServiceObserver.Companion companion2 = SeedlingServiceObserver.Companion;
                    companion2.getInstance().register(((SeedlingPluginManager) this.this$0).context, false);
                    companion2.getInstance().addListener(((SeedlingPluginManager) this.this$0).seedlingEnableListener);
                    break;
                default:
                    OplusSysUiSchedMgr.Companion.getInstance().schedForSeedlingEventTriggered(false, (String) this.this$0);
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.statusbar.seeding.SeedlingPluginManager$init$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        int label;

        /* JADX INFO: renamed from: com.oplus.systemui.statusbar.seeding.SeedlingPluginManager$init$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
        public final class AnonymousClass1 implements FlowCollector {
            public final /* synthetic */ int $r8$classId;
            public /* synthetic */ SeedlingPluginManager this$0;

            public /* synthetic */ AnonymousClass1(int i) {
                this.$r8$classId = i;
            }

            public final Object emit(Object obj, Continuation continuation) {
                switch (this.$r8$classId) {
                    case 0:
                        String str = (String) obj;
                        SeedlingPluginManager seedlingPluginManager = this.this$0;
                        LogUtil.i$default(LogUtil.TAG_STATUSBAR, SeedlingPluginManager.TAG, DozeLogger$.ExternalSyntheticOutline0.m("live update request loadPlugin: ", " for ", str, seedlingPluginManager.liteSeedlingEnable), null, 8);
                        seedlingPluginManager.requestLoadPlugin("live update for " + str);
                        break;
                    default:
                        StatusBarWindowState statusBarWindowState = (StatusBarWindowState) obj;
                        Bundle bundleM = FlowControlConfig$.ExternalSyntheticOutline0.m("state", "onWindowVisibleStateChanged");
                        bundleM.putInt("windowVisibleState", (statusBarWindowState == StatusBarWindowState.Hidden || statusBarWindowState == StatusBarWindowState.Hiding) ? 2 : 0);
                        SeedlingPlugin seedlingPlugin = this.this$0.getSeedlingPlugin();
                        if (seedlingPlugin != null) {
                            seedlingPlugin.onStateChange(bundleM);
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }

        public AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        public final Continuation create(Object obj, Continuation continuation) {
            return SeedlingPluginManager.this.new AnonymousClass2(continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return create((CoroutineScope) obj, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SharedFlow loadPlugin = SeedlingPluginManager.this.notificationLiveAlertInteractor.getLoadPlugin();
                SeedlingPluginManager seedlingPluginManager = SeedlingPluginManager.this;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
                anonymousClass1.this$0 = seedlingPluginManager;
                VarHandle.storeStoreFence();
                this.label = 1;
                if (loadPlugin.collect(anonymousClass1, this) == coroutine_suspended) {
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

    public static LiteUmsLoader $r8$lambda$iiXG7Ic1ttEj1QtimIz022HG5V4(SeedlingPluginManager seedlingPluginManager) {
        return new LiteUmsLoader(seedlingPluginManager.context);
    }

    /* JADX WARN: Type inference failed for: r1v9, types: [com.oplus.systemui.statusbar.seeding.SeedlingPluginManager$keyguardStyleServiceCallback$1] */
    public SeedlingPluginManager(Context context, CoroutineScope coroutineScope, DumpManager dumpManager, CommonProcessObserver commonProcessObserver, Lazy lazy, UserTracker userTracker, LauncherProxyService launcherProxyService, PluginManager pluginManager, HeadsUpManager headsUpManager, StatusBarWindowControllerStore statusBarWindowControllerStore, CustomViewManager customViewManager, Lazy lazy2, Lazy lazy3, StatusBarPaddingProvider statusBarPaddingProvider, SysuiStatusBarStateController sysuiStatusBarStateController, Lazy lazy4, Optional<StartingSurface> optional, ActivityStarter activityStarter, KeyguardUpdateMonitor keyguardUpdateMonitor, ConfigurationController configurationController, NotificationLaunchAnimationInteractor notificationLaunchAnimationInteractor, ShadeDisplaysRepository shadeDisplaysRepository, OplusLiveAlertNotificationsInteractor oplusLiveAlertNotificationsInteractor, INotificationManager iNotificationManager, SysUiStateControllerImpl sysUiStateControllerImpl, HiddenIconDispatcher hiddenIconDispatcher, OplusPluginDependencyProvider oplusPluginDependencyProvider, RemoteViewsWrapperImp remoteViewsWrapperImp, OplusUserContext oplusUserContext, TaskStackChangeControllerImpl taskStackChangeControllerImpl, Lazy lazy5, OplusKgdImmersiveController oplusKgdImmersiveController, Lazy lazy6, Lazy lazy7, Lazy lazy8) {
        this.context = context;
        this.scope = coroutineScope;
        this.dumpManager = dumpManager;
        this.commonProcessObserver = commonProcessObserver;
        this.notificationSectionsManagerEx = lazy;
        this.userTracker = userTracker;
        this.mOverviewProxyService = launcherProxyService;
        this.pluginManager = pluginManager;
        this.headsUpManager = headsUpManager;
        this.statusBarWindowControllerStore = statusBarWindowControllerStore;
        this.customViewManager = customViewManager;
        this.deviceProvisionedController = lazy2;
        this.centralSurfacesEx = lazy3;
        this.statusBarPaddingProvider = statusBarPaddingProvider;
        this.statusBarStateController = sysuiStatusBarStateController;
        this.keyguardHelper = lazy4;
        this.startingSurfaceOptional = optional;
        this.activityStarter = activityStarter;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.configurationController = configurationController;
        this.notificationLaunchAnimationInteractor = notificationLaunchAnimationInteractor;
        this.mShadeDisplaysRepository = shadeDisplaysRepository;
        this.notificationLiveAlertInteractor = oplusLiveAlertNotificationsInteractor;
        this.notificationManager = iNotificationManager;
        this.sysUiStateController = sysUiStateControllerImpl;
        this.hiddenIconDispatcher = hiddenIconDispatcher;
        this.oplusPluginDependency = oplusPluginDependencyProvider;
        this.remoteViewsWrapperImp = remoteViewsWrapperImp;
        this.oplusUserContext = oplusUserContext;
        this.taskStackChangeController = taskStackChangeControllerImpl;
        this.panelViewControllerLazy = lazy5;
        this.oplusKgdImmersiveController = oplusKgdImmersiveController;
        this.lockScreenSeedingInteractor = lazy6;
        this.displaySubComponentRepository = lazy7;
        this.screenLifecycle = lazy8;
        SeedlingPluginManager$$ExternalSyntheticLambda0 seedlingPluginManager$$ExternalSyntheticLambda0 = new SeedlingPluginManager$$ExternalSyntheticLambda0(0);
        seedlingPluginManager$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.liteUmsLoader$delegate = LazyKt__LazyJVMKt.lazy(seedlingPluginManager$$ExternalSyntheticLambda0);
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.activityIntentHelper = new ActivityIntentHelper(context);
        SeedlingPluginManager$sysUiStateListener$1 seedlingPluginManager$sysUiStateListener$1 = new SeedlingPluginManager$sysUiStateListener$1();
        seedlingPluginManager$sysUiStateListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.sysUiStateListener = seedlingPluginManager$sysUiStateListener$1;
        SeedlingPluginManager$$ExternalSyntheticLambda0 seedlingPluginManager$$ExternalSyntheticLambda1 = new SeedlingPluginManager$$ExternalSyntheticLambda0(1);
        seedlingPluginManager$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.mScreenLifeCycleObserver$delegate = LazyKt__LazyJVMKt.lazy(seedlingPluginManager$$ExternalSyntheticLambda1);
        SeedlingPluginManager$keyguardUpdateMonitorCallback$1 seedlingPluginManager$keyguardUpdateMonitorCallback$1 = new SeedlingPluginManager$keyguardUpdateMonitorCallback$1();
        seedlingPluginManager$keyguardUpdateMonitorCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.keyguardUpdateMonitorCallback = seedlingPluginManager$keyguardUpdateMonitorCallback$1;
        SeedlingPluginManager$appSwitchObserver$1 seedlingPluginManager$appSwitchObserver$1 = new SeedlingPluginManager$appSwitchObserver$1();
        seedlingPluginManager$appSwitchObserver$1.this$0 = this;
        seedlingPluginManager$appSwitchObserver$1.bundle = new Bundle();
        VarHandle.storeStoreFence();
        this.appSwitchObserver = seedlingPluginManager$appSwitchObserver$1;
        SeedlingPluginManager$onHeadsUpChangedListener$1 seedlingPluginManager$onHeadsUpChangedListener$1 = new SeedlingPluginManager$onHeadsUpChangedListener$1();
        seedlingPluginManager$onHeadsUpChangedListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.onHeadsUpChangedListener = seedlingPluginManager$onHeadsUpChangedListener$1;
        SeedlingPluginManager$processObserver$1 seedlingPluginManager$processObserver$1 = new SeedlingPluginManager$processObserver$1();
        seedlingPluginManager$processObserver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.processObserver = seedlingPluginManager$processObserver$1;
        SeedlingPluginManager$holeRectListener$1 seedlingPluginManager$holeRectListener$1 = new SeedlingPluginManager$holeRectListener$1();
        seedlingPluginManager$holeRectListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.holeRectListener = seedlingPluginManager$holeRectListener$1;
        this.keyguardStyleServiceCallback = new KeyguardStyleServiceCallbackImpl() { // from class: com.oplus.systemui.statusbar.seeding.SeedlingPluginManager$keyguardStyleServiceCallback$1
            public IBinder asBinder() {
                return null;
            }

            public void onCall(String str, Bundle bundle) {
                SeedlingPluginManager seedlingPluginManager = this.this$0;
                SeedlingPluginManager$keyguardStyleServiceCallback$1$onCall$1 seedlingPluginManager$keyguardStyleServiceCallback$1$onCall$1 = new SeedlingPluginManager$keyguardStyleServiceCallback$1$onCall$1();
                seedlingPluginManager$keyguardStyleServiceCallback$1$onCall$1.$method = str;
                seedlingPluginManager$keyguardStyleServiceCallback$1$onCall$1.$bundle = bundle;
                seedlingPluginManager$keyguardStyleServiceCallback$1$onCall$1.this$0 = seedlingPluginManager;
                VarHandle.storeStoreFence();
                SystemUIThreadUtil.runOnUiThread(seedlingPluginManager$keyguardStyleServiceCallback$1$onCall$1);
            }

            public void onUiStateChanged(SmoothTransitionController.SmoothTransitionUiState smoothTransitionUiState, boolean z) {
            }
        };
        SeedlingPluginManager$seedlingEnableListener$1 seedlingPluginManager$seedlingEnableListener$1 = new SeedlingPluginManager$seedlingEnableListener$1();
        seedlingPluginManager$seedlingEnableListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.seedlingEnableListener = seedlingPluginManager$seedlingEnableListener$1;
        SeedlingPluginManager$configurationListener$1 seedlingPluginManager$configurationListener$1 = new SeedlingPluginManager$configurationListener$1();
        seedlingPluginManager$configurationListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.configurationListener = seedlingPluginManager$configurationListener$1;
        this.templateViewStatusListener = new SeedlingPluginManager$templateViewStatusListener$1();
    }

    public static final void access$onKeyguardStateChanged(SeedlingPluginManager seedlingPluginManager, boolean z, boolean z2) {
        Bundle bundle = new Bundle();
        bundle.putString("method", METHOD_ON_KEYGUARD_STATE_CHANGED);
        bundle.putBoolean(PARAM_IS_KEYGUARD_VISIBLE, z);
        bundle.putBoolean(PARAM_IS_KEYGUARD_OCCLUDED, z2);
        SeedlingPlugin seedlingPlugin = seedlingPluginManager.seedlingPlugin;
        if (seedlingPlugin != null) {
            seedlingPlugin.call(bundle);
        }
    }

    public static final void access$onPluginAvailableCallback(SeedlingPluginManager seedlingPluginManager, boolean z) {
        FlavorOneCapsuleController flavorOneCapsuleController;
        if (!z) {
            if (seedlingPluginManager.isAdd) {
                seedlingPluginManager.pluginManager.removePluginListener(seedlingPluginManager);
                seedlingPluginManager.isAdd = false;
            }
            FlavorOneFeatureOption.INSTANCE.getClass();
            if (FlavorOneFeatureOption.isSupportMiniCapsule() && (flavorOneCapsuleController = (FlavorOneCapsuleController) DependencyEx.sDependency.getDependency(FlavorOneCapsuleController.class)) != null) {
                flavorOneCapsuleController.destroy();
            }
            if (FeatureOption.isLiteLiveAlertDevice()) {
                seedlingPluginManager.getLiteUmsLoader().destroy();
                return;
            }
            return;
        }
        seedlingPluginManager.isPluginAvailable = true;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "SeedlingPluginManager-->handlePluginAvailable");
        }
        SeedlingPlugin seedlingPlugin = seedlingPluginManager.seedlingPlugin;
        if (seedlingPlugin != null) {
            OplusAppSwitchManager.getInstance().registerAppSwitchObserver(seedlingPluginManager.context, seedlingPluginManager.appSwitchObserver, (OplusAppSwitchConfig) null);
            Iterator<T> it = seedlingPluginManager.callbacks.iterator();
            while (it.hasNext()) {
                ((Listener) it.next()).onPluginAdded(seedlingPlugin, seedlingPluginManager.context);
            }
            seedlingPluginManager.headsUpManager.addListener(seedlingPluginManager.onHeadsUpChangedListener);
            seedlingPluginManager.commonProcessObserver.addProcessObserver(seedlingPluginManager.processObserver);
            seedlingPluginManager.statusBarPaddingProvider.addCallback(seedlingPluginManager.holeRectListener);
            seedlingPluginManager.windowStateCollectJob = BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), (CoroutineContext) null, (CoroutineStart) null, new SeedlingPluginManager$addStatusBarWindowStateListener$1(seedlingPluginManager, null), 3);
            SmoothTransitionController.registerSmoothTransitionCallback$default(SmoothTransitionController.Companion.getInstance(seedlingPluginManager.context), seedlingPluginManager.keyguardStyleServiceCallback);
            seedlingPluginManager.sysUiStateController.addListener(seedlingPluginManager.sysUiStateListener);
            ((LockScreenSeedingInteractor) seedlingPluginManager.lockScreenSeedingInteractor.get()).notifyColorChanged();
            if (FeatureOption.isLiteLiveAlertDevice()) {
                seedlingPluginManager.notificationLiveAlertInteractor.updateLiveAlerts();
            }
            ((ScreenLifecycle) seedlingPluginManager.screenLifecycle.get()).addObserver(seedlingPluginManager.getMScreenLifeCycleObserver());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LiteUmsLoader getLiteUmsLoader() {
        return (LiteUmsLoader) this.liteUmsLoader$delegate.getValue();
    }

    private final ScreenLifecycle.Observer getMScreenLifeCycleObserver() {
        return (ScreenLifecycle.Observer) this.mScreenLifeCycleObserver$delegate.getValue();
    }

    public final void addCallback(Listener listener) {
        if (!this.callbacks.contains(listener)) {
            this.callbacks.add(listener);
        }
        Handler handler = this.mainHandler;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(0);
        anonymousClass1.this$0 = this;
        anonymousClass1.$listener = listener;
        VarHandle.storeStoreFence();
        handler.post(anonymousClass1);
    }

    public final void addPluginListener(String str) {
        Companion.getClass();
        if (Companion.isFluidEntrySupported() && !this.isAdd) {
            if (FeatureOption.isLiteLiveAlertDevice()) {
                OplusSysUiSchedMgr.Companion.getInstance().requestSysResource(OplusSysUiSchedMgr.UafSceneEvent.SYSTEM_STATUS_ICON_ADDING, "scheduleForPluginLoading");
                this.mainHandler.postDelayed(SeedlingPluginManager$scheduleForPluginLoading$1.INSTANCE, 2000L);
            }
            this.pluginManager.addPluginListener(this, SeedlingPlugin.class, false);
            this.lastLoadMsg = str;
            this.isAdd = true;
        }
    }

    public final Pair<Boolean, String> aodClick(View view) {
        Object tag = view.getTag(2130706434);
        if (!(tag instanceof String)) {
            return new Pair<>(Boolean.FALSE, null);
        }
        Bundle bundleM = FlowControlConfig$.ExternalSyntheticOutline0.m("method", "aodClick");
        bundleM.putString("seedlingId", (String) tag);
        SeedlingPlugin seedlingPlugin = this.seedlingPlugin;
        Bundle bundleCall = seedlingPlugin != null ? seedlingPlugin.call(bundleM) : null;
        return new Pair<>(bundleCall != null ? Boolean.valueOf(bundleCall.getBoolean("aodClickHandled")) : null, bundleCall != null ? bundleCall.getString("aodPkg") : null);
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("SeedlingPluginManager state:");
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("userId: ", this.userTracker.getUserId(), printWriter);
        printWriter.println("proxy: " + this.proxy);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "isForceStatusBarVisible: ", this.isForceStatusBarVisible);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "isShowActivityOverLockscreen: ", this.isShowActivityOverLockscreen);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "isAdd: ", this.isAdd);
        ActionReceiver$.ExternalSyntheticOutline1.m("lastLoadMsg: ", this.lastLoadMsg, printWriter);
        ActionReceiver$.ExternalSyntheticOutline1.m("lastLoadException: ", this.lastLoadException, printWriter);
        Companion.getClass();
        printWriter.println("isFluidEntrySupported: " + Companion.isFluidEntrySupported());
        printWriter.println("isLiteLiveAlertDevice: " + FeatureOption.isLiteLiveAlertDevice());
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "isFullLiveAlertDevice: ", FeatureOption.isFullLiveAlertDevice());
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "liteSeedlingEnable: ", this.liteSeedlingEnable);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "hasSystemCapsule: ", this.hasSystemCapsule);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "isPluginAvailable: ", this.isPluginAvailable);
        printWriter.println("seedlingPlugin: " + this.seedlingPlugin);
        printWriter.println("pluginContext: " + this.pluginContext);
        printWriter.println("frameRateRequests: " + OplusFrameRateManager.INSTANCE.getRequests());
        SeedlingPlugin seedlingPlugin = this.seedlingPlugin;
        if (seedlingPlugin != null) {
            seedlingPlugin.dump(printWriter, strArr);
        }
    }

    public final CapsuleContainerStateListener getCapsuleListener() {
        return this.capsuleListener;
    }

    public final String getLastLoadException() {
        return this.lastLoadException;
    }

    public final String getLastLoadMsg() {
        return this.lastLoadMsg;
    }

    public final Context getPluginContext() {
        return this.pluginContext;
    }

    public final Object getProxy(String str) {
        SeedlingPlugin seedlingPlugin;
        if (!Intrinsics.areEqual(str, "TemplateFactory") || (seedlingPlugin = this.seedlingPlugin) == null) {
            return null;
        }
        return seedlingPlugin.getProxy("TemplateFactory");
    }

    public final SeedlingPlugin getSeedlingPlugin() {
        return this.seedlingPlugin;
    }

    public final void init() {
        FlavorOneCapsuleController flavorOneCapsuleController;
        LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, "init", null, 8);
        this.oplusPluginDependency.allowPluginDependency(RemoteViewsWrapper.class, this.remoteViewsWrapperImp);
        this.oplusPluginDependency.allowPluginDependency(UserProxy.class, this.oplusUserContext);
        this.oplusPluginDependency.allowPluginDependency(TaskStackChangeController.class, this.taskStackChangeController);
        if (FeatureOption.isFullLiveAlertDevice()) {
            addPluginListener("auto load");
        } else if (FeatureOption.isLiteLiveAlertDevice()) {
            Handler workHandler = SystemUIThreadUtil.getWorkHandler();
            RunnableC03071 runnableC03071 = new RunnableC03071(0);
            runnableC03071.this$0 = this;
            VarHandle.storeStoreFence();
            workHandler.post(runnableC03071);
            BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass2(null), 3);
        }
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (FlavorOneFeatureOption.isSupportMiniCapsule() && (flavorOneCapsuleController = (FlavorOneCapsuleController) DependencyEx.sDependency.getDependency(FlavorOneCapsuleController.class)) != null) {
            flavorOneCapsuleController.init();
        }
        this.dumpManager.registerDumpable(TAG, this);
    }

    public final void notifyAppTransition(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putString("state", "notifyAppTransition");
        bundle2.putBundle("notifyAppTransition", bundle);
        SeedlingPlugin seedlingPlugin = this.seedlingPlugin;
        if (seedlingPlugin != null) {
            seedlingPlugin.onStateChange(bundle2);
        }
    }

    public final void onExpandFractionNoAnimAfter() {
        Bundle bundleM = FlowControlConfig$.ExternalSyntheticOutline0.m("state", "onExpandFractionNoAnimAfter");
        SeedlingPlugin seedlingPlugin = this.seedlingPlugin;
        if (seedlingPlugin != null) {
            seedlingPlugin.onStateChange(bundleM);
        }
    }

    public final void onExpandFractionNoAnimBefore() {
        Bundle bundleM = FlowControlConfig$.ExternalSyntheticOutline0.m("state", "onExpandFractionNoAnimBefore");
        SeedlingPlugin seedlingPlugin = this.seedlingPlugin;
        if (seedlingPlugin != null) {
            seedlingPlugin.onStateChange(bundleM);
        }
    }

    public final void onHeadsUpPinnedOrUnPinned(boolean z, NotificationEntry notificationEntry) {
        StatusBarNotification sbn;
        StatusBarNotification sbn2;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            StringBuilder sb = new StringBuilder("SeedlingPluginManager-->");
            sb.append("onHeadsUpPinnedOrUnPinned, inPinnedMode: " + z + " packageName: " + ((notificationEntry == null || (sbn2 = notificationEntry.getSbn()) == null) ? null : sbn2.getPackageName()));
            Log.d("SystemUi--Statusbar", sb.toString());
        }
        Bundle bundle = new Bundle();
        bundle.putString("state", "onShowHeadsUpNotification");
        bundle.putBoolean("onShowHeadsUpNotification", z);
        bundle.putString("headsUpNotificationPackage", (notificationEntry == null || (sbn = notificationEntry.getSbn()) == null) ? null : sbn.getPackageName());
        bundle.putParcelable("sbn", notificationEntry != null ? notificationEntry.getSbn() : null);
        SeedlingPlugin seedlingPlugin = this.seedlingPlugin;
        if (seedlingPlugin != null) {
            seedlingPlugin.onStateChange(bundle);
        }
    }

    public final void onOverviewProxyServiceInit() {
        Bundle bundleM = FlowControlConfig$.ExternalSyntheticOutline0.m("state", "onOverviewProxyServiceInit");
        SeedlingPlugin seedlingPlugin = this.seedlingPlugin;
        if (seedlingPlugin != null) {
            seedlingPlugin.onStateChange(bundleM);
        }
    }

    public final void onPanelExpandFractionChange(float f, int i) {
        Bundle bundle = SeedlingPluginCallUtils.getBundle();
        bundle.putString("state", "onPanelExpandFractionChange");
        bundle.putFloat("expandFraction", f);
        bundle.putInt("expandFrom", i);
        SeedlingPlugin seedlingPlugin = this.seedlingPlugin;
        if (seedlingPlugin != null) {
            seedlingPlugin.onStateChange(bundle);
        }
    }

    public void onPluginConnected(SeedlingPlugin seedlingPlugin, Context context) throws Throwable {
        Object obj;
        try {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                Log.d("SystemUi--Statusbar", "SeedlingPluginManager-->".concat("onPluginConnected: " + seedlingPlugin + ",pluginContext: " + context));
            }
            onPluginConnectedImpl(seedlingPlugin, context);
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 == null) {
            return;
        }
        String strM = DebugInfo$.ExternalSyntheticOutline0.m(CommunalWidgetItem$.ExternalSyntheticOutline0.m(System.currentTimeMillis(), "time:", ", msg:", th2.getMessage()), ", ", ArraysKt___ArraysKt.joinToString$default(th2.getStackTrace(), (CharSequence) null, (CharSequence) null, (RecompositionTracerKt$.ExternalSyntheticLambda0) null, 63));
        this.lastLoadException = strM;
        LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, TAG, "onPluginConnected with exception, ".concat(strM));
        throw th2;
    }

    public final void onPluginConnectedImpl(SeedlingPlugin seedlingPlugin, Context context) {
        FlavorOneCapsuleController flavorOneCapsuleController;
        this.seedlingPlugin = seedlingPlugin;
        this.pluginContext = context;
        SeedlingHost seedlingHost = new SeedlingHost(context);
        this.proxy = seedlingHost;
        seedlingPlugin.bindStub(seedlingHost);
        Bundle bundle = SeedlingPluginCallUtils.getBundle("checkAvailable", "userId", this.userTracker.getUserId());
        bundle.putBoolean("liteSeedlingEnable", this.liteSeedlingEnable || FeatureOption.isFullLiveAlertDevice());
        seedlingPlugin.call(bundle);
        this.keyguardUpdateMonitor.registerCallback(this.keyguardUpdateMonitorCallback);
        this.configurationController.addCallback(this.configurationListener);
        this.notificationLiveAlertInteractor.start(seedlingPlugin);
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (FlavorOneFeatureOption.isSupportMiniCapsule() && (flavorOneCapsuleController = (FlavorOneCapsuleController) DependencyEx.sDependency.getDependency(FlavorOneCapsuleController.class)) != null) {
            flavorOneCapsuleController.onPluginConnected(seedlingPlugin, context);
        }
        ReferenceSysUIDisplaySubcomponent referenceSysUIDisplaySubcomponent = (ReferenceSysUIDisplaySubcomponent) ((PerDisplayRepository) this.displaySubComponentRepository.get()).get(((Number) this.mShadeDisplaysRepository.getDisplayId().getValue()).intValue());
        this.statusBarWindowStateRepository = referenceSysUIDisplaySubcomponent != null ? referenceSysUIDisplaySubcomponent.getStatusBarWindowStateRepository() : null;
    }

    @Deprecated
    public void onPluginDisconnected(SeedlingPlugin seedlingPlugin) {
        super.onPluginDisconnected(seedlingPlugin);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "SeedlingPluginManager-->".concat("onPluginDisconnected:" + seedlingPlugin));
        }
        this.isPluginAvailable = false;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "SeedlingPluginManager-->handlePluginInAvailable");
        }
        SeedlingPlugin seedlingPlugin2 = this.seedlingPlugin;
        if (seedlingPlugin2 != null) {
            ((ScreenLifecycle) this.screenLifecycle.get()).removeObserver(getMScreenLifeCycleObserver());
            OplusAppSwitchManager.getInstance().unregisterAppSwitchObserver(this.context, this.appSwitchObserver);
            Iterator<T> it = this.callbacks.iterator();
            while (it.hasNext()) {
                ((Listener) it.next()).onPluginRemove(seedlingPlugin2);
            }
            this.headsUpManager.removeListener(this.onHeadsUpChangedListener);
            this.commonProcessObserver.removeProcessObserver(this.processObserver);
            this.statusBarPaddingProvider.removeCallback(this.holeRectListener);
            Job job = this.windowStateCollectJob;
            if (job != null) {
                Job.Key key = Job.Key;
                job.cancel((CancellationException) null);
            }
            SmoothTransitionController.Companion.getInstance(this.context).unregisterSmoothTransitionCallback(this.keyguardStyleServiceCallback);
            this.sysUiStateController.removeListener(this.sysUiStateListener);
        }
        this.seedlingPlugin = null;
        this.pluginContext = null;
        this.proxy = null;
        this.keyguardUpdateMonitor.removeCallback(this.keyguardUpdateMonitorCallback);
        this.configurationController.removeCallback(this.configurationListener);
        this.notificationLiveAlertInteractor.stop();
    }

    public final void onRegisterAOD() {
        Bundle bundleM = FlowControlConfig$.ExternalSyntheticOutline0.m("state", "onAodRegister");
        SeedlingPlugin seedlingPlugin = this.seedlingPlugin;
        if (seedlingPlugin != null) {
            seedlingPlugin.onStateChange(bundleM);
        }
    }

    public final void onSeamlessCollapseEnd() {
        Bundle bundleM = FlowControlConfig$.ExternalSyntheticOutline0.m("state", "onSeamlessCollapseEnd");
        SeedlingPlugin seedlingPlugin = this.seedlingPlugin;
        if (seedlingPlugin != null) {
            seedlingPlugin.onStateChange(bundleM);
        }
    }

    public final void onSeamlessExpandStart() {
        Bundle bundleM = FlowControlConfig$.ExternalSyntheticOutline0.m("state", "onSeamlessExpandStart");
        SeedlingPlugin seedlingPlugin = this.seedlingPlugin;
        if (seedlingPlugin != null) {
            seedlingPlugin.onStateChange(bundleM);
        }
    }

    public final boolean queryData(String str) {
        Bundle bundleM = MediaResumeListener$getResumeAction$1$.ExternalSyntheticOutline0.m(str, false);
        SeedlingPlugin seedlingPlugin = this.seedlingPlugin;
        Bundle bundleCall = seedlingPlugin != null ? seedlingPlugin.call(bundleM) : null;
        return Intrinsics.areEqual(bundleCall != null ? Boolean.valueOf(bundleCall.getBoolean(str)) : null, Boolean.TRUE);
    }

    public final void removeCallback(Listener listener) {
        if (this.callbacks.contains(listener)) {
            this.callbacks.remove(listener);
        }
    }

    public final void requestLoadPlugin(String str) {
        addPluginListener(str);
        SeedlingPlugin seedlingPlugin = this.seedlingPlugin;
        if (seedlingPlugin != null) {
            Bundle bundle = SeedlingPluginCallUtils.getBundle("enableSource");
            bundle.putBoolean("liteSeedlingEnable", this.liteSeedlingEnable || FeatureOption.isFullLiveAlertDevice());
            seedlingPlugin.call(bundle);
        }
    }

    public final void setCapsuleListener(CapsuleContainerStateListener capsuleContainerStateListener) {
        this.capsuleListener = capsuleContainerStateListener;
    }

    public final void setLastLoadException(String str) {
        this.lastLoadException = str;
    }

    public final void setLastLoadMsg(String str) {
        this.lastLoadMsg = str;
    }

    public final void setLaunchActivityOverLockscreen(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("setLaunchActivityOverLockscreen isShow=", "SeedlingPluginManager-->", "SystemUi--Statusbar", z);
        }
        this.isShowActivityOverLockscreen = z;
        this.notificationLaunchAnimationInteractor.setIsLaunchAnimationRunning(z);
    }

    public final void setNssLController(NotificationStackScrollLayoutController notificationStackScrollLayoutController) {
        this.mStackScrollerController = notificationStackScrollLayoutController;
        this.customViewManager.setNssLController(notificationStackScrollLayoutController);
    }

    public final void setPluginContext(Context context) {
        this.pluginContext = context;
    }

    public final void setSeedlingPlugin(SeedlingPlugin seedlingPlugin) {
        this.seedlingPlugin = seedlingPlugin;
    }

    public final void unRegisterAOD() {
        Bundle bundleM = FlowControlConfig$.ExternalSyntheticOutline0.m("state", "onAodUnRegister");
        SeedlingPlugin seedlingPlugin = this.seedlingPlugin;
        if (seedlingPlugin != null) {
            seedlingPlugin.onStateChange(bundleM);
        }
    }
}
