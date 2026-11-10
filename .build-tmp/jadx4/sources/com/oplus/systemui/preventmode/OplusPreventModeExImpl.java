package com.oplus.systemui.preventmode;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.database.ContentObserver;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.util.ArraySet;
import android.util.Log;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.ViewMediatorCallback;
import com.android.keyguard.base.OplusKeyguardCtrl$;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.OplusCommonSensorEx;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.keyguard.anim.transition.OplusTransitionExtendedInfoUtil$;
import com.android.systemui.preventmode.IOplusPreventModeCallback;
import com.android.systemui.preventmode.OplusPreventModeEx;
import com.android.systemui.statusbar.policy.CallbackController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.util.Assert;
import com.oplus.charge.viewmodel.OplusChargeAnimController$;
import com.oplus.keyguard.settings.KeyguardSettingUtil;
import com.oplus.keyguard.settings.proxy.KeyguardSettingBackupRestoreProxy$;
import com.oplus.systemui.biometrics.face.FaceSwitchHelper;
import com.oplus.systemui.biometrics.finger.FingerSwitchHelper;
import com.oplus.systemui.common.flavor.OplusFlavorManager;
import com.oplus.systemui.keyguard.charginganim.ChargingAnimationImpl;
import com.oplus.systemui.subdisplay.OplusSubDisplayUtils;
import com.oplusos.keyguard.utils.KeyguardThreadUtil;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.feature.KeyguardFeatureOption;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.observer.SmartTouchSettingsObserver;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusPreventModeExImpl extends OplusPreventModeEx implements CallbackController, Handler.Callback, ProximitySensorEventListener.ProximityStateChangedCallback {
    public static final Companion Companion = new Companion();
    public static final String TAG = "OplusPreventModeExImpl";
    public boolean boundsChangedByRotation;
    private final OplusPreventModeExImpl$configurationChangeListener$1 configurationChangeListener;
    private Context context;
    private final OplusPreventModeExImpl$mCallback$1 mCallback;
    private ArrayList<IOplusPreventModeCallback> mCallbacks;
    public volatile int mCurrentResult;
    private final DeviceStateController.StateChangedListener mFoldStateListener;
    private Handler mHandler;
    public boolean mKeyguardIsShowing;
    public volatile boolean mOccluded;
    private OplusPreventWindowController mOplusPreventWindowController;
    private final ContentObserver mPreventModeSwitchObserver;
    public boolean mPreventModeTrigger;
    private volatile ProximitySensorEventListener mProximitySensorEventListener;
    public volatile boolean mRegisteredSensor;
    public volatile boolean mScreenOn;
    private Sensor mSensor;
    private volatile SensorManager mSensorManager;
    private ArraySet<String> mShowPreventModeViewPackages;
    public boolean mShowPreventModeWindow;
    public volatile boolean mSubDisplayPreventModeEnable;
    private ViewMediatorCallback mViewMediatorCallback;
    private final Context originContext;
    private final OplusPreventModeExImpl$updateMonitorCallback$1 updateMonitorCallback;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.preventmode.OplusPreventModeExImpl$registerSensorListener$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ long $mainThreadStartTime;
        public /* synthetic */ OplusPreventModeExImpl this$0;

        @Override // java.lang.Runnable
        public final void run() {
            Handler mHandler;
            if (this.this$0.getMRegisteredSensor()) {
                KgdLogUtil.w(OplusPreventModeExImpl.TAG, "proximity sensor has registered");
                return;
            }
            if (this.this$0.shouldNotRegisterSensor()) {
                KgdLogUtil.w(OplusPreventModeExImpl.TAG, "should not register sensor listener, return");
                return;
            }
            SensorManager mSensorManager = this.this$0.getMSensorManager();
            if (mSensorManager != null) {
                OplusPreventModeExImpl oplusPreventModeExImpl = this.this$0;
                long j = this.$mainThreadStartTime;
                if (OplusSubDisplayUtils.Companion.getInstance(oplusPreventModeExImpl.context).getSubDisplayShow()) {
                    oplusPreventModeExImpl.setMSubDisplayPreventModeEnable(true);
                    Handler mHandler2 = oplusPreventModeExImpl.getMHandler();
                    if (mHandler2 != null && mHandler2.hasMessages(2) && (mHandler = oplusPreventModeExImpl.getMHandler()) != null) {
                        mHandler.removeMessages(2);
                    }
                    Handler mHandler3 = oplusPreventModeExImpl.getMHandler();
                    if (mHandler3 != null) {
                        mHandler3.sendEmptyMessageDelayed(2, 500L);
                    }
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                FeatureOption.INSTANCE.getClass();
                oplusPreventModeExImpl.setMSensor(FeatureOption.isTablet() ? ((OplusCommonSensorEx) DependencyEx.sDependency.getDependency(OplusCommonSensorEx.class)).getPocketTypeSensor() : ((OplusCommonSensorEx) DependencyEx.sDependency.getDependency(OplusCommonSensorEx.class)).getProximitySensor());
                if (oplusPreventModeExImpl.getMSensor() != null) {
                    oplusPreventModeExImpl.setMRegisteredSensor(mSensorManager.registerListener(oplusPreventModeExImpl.getMProximitySensorEventListener(), oplusPreventModeExImpl.getMSensor(), 3, oplusPreventModeExImpl.getMHandler()));
                    long jCurrentTimeMillis2 = System.currentTimeMillis();
                    KgdLogUtil.i(OplusPreventModeExImpl.TAG, "registerSensor: " + oplusPreventModeExImpl.getMRegisteredSensor() + "; async cost time: " + (jCurrentTimeMillis - j) + "ms;registerListener cost time: " + (jCurrentTimeMillis2 - jCurrentTimeMillis));
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.preventmode.OplusPreventModeExImpl$unregisterSensorListener$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class RunnableC02481 implements Runnable {
        public /* synthetic */ OplusPreventModeExImpl this$0;

        @Override // java.lang.Runnable
        public final void run() {
            if (!this.this$0.getMRegisteredSensor()) {
                KgdLogUtil.w(OplusPreventModeExImpl.TAG, "proximity sensor had unregistered, or not registered");
                return;
            }
            SensorManager mSensorManager = this.this$0.getMSensorManager();
            if (mSensorManager != null) {
                OplusPreventModeExImpl oplusPreventModeExImpl = this.this$0;
                mSensorManager.unregisterListener(oplusPreventModeExImpl.getMProximitySensorEventListener());
                Handler mHandler = oplusPreventModeExImpl.getMHandler();
                if (mHandler != null) {
                    mHandler.removeMessages(2);
                }
                KgdLogUtil.i(OplusPreventModeExImpl.TAG, "unregisterSensor");
                oplusPreventModeExImpl.setMRegisteredSensor(false);
                oplusPreventModeExImpl.setMCurrentResult(0);
            }
        }
    }

    public OplusPreventModeExImpl(Context context) {
        this.originContext = context;
        this.context = context;
        ProximitySensorEventListener proximitySensorEventListener = new ProximitySensorEventListener();
        proximitySensorEventListener.downOrNot = -1;
        this.mProximitySensorEventListener = proximitySensorEventListener;
        this.mCallbacks = new ArrayList<>();
        this.mSubDisplayPreventModeEnable = true;
        this.mShowPreventModeViewPackages = new ArraySet<>();
        OplusPreventModeExImpl$mCallback$1 oplusPreventModeExImpl$mCallback$1 = new OplusPreventModeExImpl$mCallback$1();
        oplusPreventModeExImpl$mCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mCallback = oplusPreventModeExImpl$mCallback$1;
        OplusPreventModeExImpl$mFoldStateListener$1 oplusPreventModeExImpl$mFoldStateListener$1 = new OplusPreventModeExImpl$mFoldStateListener$1();
        oplusPreventModeExImpl$mFoldStateListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mFoldStateListener = oplusPreventModeExImpl$mFoldStateListener$1;
        final Handler handler = new Handler();
        this.mPreventModeSwitchObserver = new ContentObserver(handler) { // from class: com.oplus.systemui.preventmode.OplusPreventModeExImpl$mPreventModeSwitchObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                super.onChange(z);
                OplusLockIconView$.ExternalSyntheticOutline0.m("prevent mode switch selfChange: ", OplusPreventModeExImpl.TAG, z);
                boolean preventModeSettingsForUser = KeyguardSettingUtil.Companion.getPreventModeSettingsForUser(this.this$0.context);
                OplusPreventModeExImpl oplusPreventModeExImpl = this.this$0;
                if (preventModeSettingsForUser) {
                    oplusPreventModeExImpl.start();
                } else {
                    oplusPreventModeExImpl.stop();
                }
            }
        };
        OplusPreventModeExImpl$updateMonitorCallback$1 oplusPreventModeExImpl$updateMonitorCallback$1 = new OplusPreventModeExImpl$updateMonitorCallback$1();
        oplusPreventModeExImpl$updateMonitorCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.updateMonitorCallback = oplusPreventModeExImpl$updateMonitorCallback$1;
        OplusPreventModeExImpl$configurationChangeListener$1 oplusPreventModeExImpl$configurationChangeListener$1 = new OplusPreventModeExImpl$configurationChangeListener$1();
        oplusPreventModeExImpl$configurationChangeListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.configurationChangeListener = oplusPreventModeExImpl$configurationChangeListener$1;
        handlePreventMode(KeyguardUpdateMonitor.getCurrentUser());
    }

    public static final void access$rebuildController(OplusPreventModeExImpl oplusPreventModeExImpl) {
        KgdLogUtil.i(TAG, "rebuildController");
        oplusPreventModeExImpl.mShowPreventModeWindow = false;
        oplusPreventModeExImpl.mPreventModeTrigger = false;
        OplusPreventWindowController oplusPreventWindowController = oplusPreventModeExImpl.mOplusPreventWindowController;
        if (oplusPreventWindowController != null) {
            oplusPreventWindowController.clearView();
        }
        Handler handler = oplusPreventModeExImpl.mHandler;
        if (handler != null && handler.hasMessages(3)) {
            handler.removeMessages(3);
        }
        oplusPreventModeExImpl.mOplusPreventWindowController = new OplusPreventWindowController(oplusPreventModeExImpl.context);
    }

    public static void notifyPreventModeChanged$default(OplusPreventModeExImpl oplusPreventModeExImpl, boolean z) {
        ArrayList arrayList = new ArrayList(oplusPreventModeExImpl.mCallbacks);
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ChargingAnimationImpl chargingAnimationImpl = (IOplusPreventModeCallback) obj;
            chargingAnimationImpl.mPreventModeShow = z;
            OplusChargeAnimController$.ExternalSyntheticOutline0.m("ChargingAnimationImpl", new StringBuilder("onPreventModeStateChanged , mPreventModeShow : "), chargingAnimationImpl.mPreventModeShow);
            chargingAnimationImpl.updateAnimeState(4, "prevent show");
        }
        oplusPreventModeExImpl.updateAuthState(1, z);
    }

    public void addCallback(IOplusPreventModeCallback iOplusPreventModeCallback) {
        Objects.requireNonNull(iOplusPreventModeCallback, "Callback must not be null.");
        if (this.mCallbacks.contains(iOplusPreventModeCallback)) {
            return;
        }
        this.mCallbacks.add(iOplusPreventModeCallback);
    }

    public final boolean getBoundsChangedByRotation() {
        return this.boundsChangedByRotation;
    }

    public final ArrayList<IOplusPreventModeCallback> getMCallbacks() {
        return this.mCallbacks;
    }

    public final int getMCurrentResult() {
        return this.mCurrentResult;
    }

    public final Handler getMHandler() {
        return this.mHandler;
    }

    public final boolean getMKeyguardIsShowing() {
        return this.mKeyguardIsShowing;
    }

    public final boolean getMOccluded() {
        return this.mOccluded;
    }

    public final OplusPreventWindowController getMOplusPreventWindowController() {
        return this.mOplusPreventWindowController;
    }

    public final boolean getMPreventModeTrigger() {
        return this.mPreventModeTrigger;
    }

    public final ProximitySensorEventListener getMProximitySensorEventListener() {
        return this.mProximitySensorEventListener;
    }

    public final boolean getMRegisteredSensor() {
        return this.mRegisteredSensor;
    }

    public final boolean getMScreenOn() {
        return this.mScreenOn;
    }

    public final Sensor getMSensor() {
        return this.mSensor;
    }

    public final SensorManager getMSensorManager() {
        return this.mSensorManager;
    }

    public final ArraySet<String> getMShowPreventModeViewPackages() {
        return this.mShowPreventModeViewPackages;
    }

    public final boolean getMShowPreventModeWindow() {
        return this.mShowPreventModeWindow;
    }

    public final boolean getMSubDisplayPreventModeEnable() {
        return this.mSubDisplayPreventModeEnable;
    }

    public final ViewMediatorCallback getMViewMediatorCallback() {
        return this.mViewMediatorCallback;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 2) {
            KgdLogUtil.i(TAG, "mSubDisplayPreventModeEnable changed false");
            this.mSubDisplayPreventModeEnable = false;
            return true;
        }
        if (i != 3) {
            return true;
        }
        KgdLogUtil.i(TAG, "handleMessage showPreventModeView");
        showPreventModeView();
        return true;
    }

    public final void handlePreventMode(int i) {
        if (KeyguardSettingUtil.Companion.getPreventModeSettingsForUser(this.context)) {
            start();
        }
        ObserverDispatcher.getInstance().registerObserver(Settings.System.getUriFor("key_is_open_prevent_mode"), this.mPreventModeSwitchObserver, i);
    }

    public final void hidePreventModeView(boolean z) {
        KeyguardViewMediator.5 r3;
        if (this.mShowPreventModeWindow) {
            try {
                OplusPreventWindowController oplusPreventWindowController = this.mOplusPreventWindowController;
                if (oplusPreventWindowController != null) {
                    oplusPreventWindowController.removeWindow();
                }
                this.mShowPreventModeWindow = false;
                if (z && this.mPreventModeTrigger && (r3 = this.mViewMediatorCallback) != null) {
                    r3.this$0.userActivity();
                }
                if (this.mPreventModeTrigger) {
                    this.mPreventModeTrigger = false;
                    notifyPreventModeChanged$default(this, false);
                    ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusKeyguardViewMediatorEx().handlePreventModeChanged();
                }
                updateAuthState(0, this.mShowPreventModeWindow);
            } catch (IllegalArgumentException e) {
                OplusTransitionExtendedInfoUtil$.ExternalSyntheticOutline0.m("caught exception: ", e.getMessage(), TAG);
            }
        }
    }

    public final void initiativeExit() {
        KgdLogUtil.i(TAG, "initiativeExit");
        hidePreventModeView(true);
        unregisterSensorListener();
    }

    public final boolean isPreventModeTrigger() {
        return this.mPreventModeTrigger;
    }

    public final boolean isPreventModeViewShown() {
        return this.mShowPreventModeWindow;
    }

    public final void onProximityStateChanged(int i, int i2) {
        OplusKeyguardCtrl$.ExternalSyntheticOutline0.m("nearOrFar: ", i, i2, "; downOrNot: ", TAG);
        if (i != 1) {
            if (i != 2) {
                return;
            }
            OplusPreventWindowController oplusPreventWindowController = this.mOplusPreventWindowController;
            if (oplusPreventWindowController != null) {
                oplusPreventWindowController.writeNodeFileByDevice(0, 194, "0");
            }
            Handler handler = this.mHandler;
            if (handler != null && handler.hasMessages(3)) {
                KgdLogUtil.i(TAG, "handleHidePreventModeView removeMessages");
                Handler handler2 = this.mHandler;
                if (handler2 != null) {
                    handler2.removeMessages(3);
                }
            }
            hidePreventModeView(true);
            return;
        }
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (!FlavorOneFeatureOption.isFlavorOneDevice()) {
            if (i2 != 0) {
                showPreventModeView();
                return;
            }
            Handler handler3 = this.mHandler;
            if (handler3 != null) {
                handler3.sendEmptyMessageDelayed(3, 100L);
                return;
            }
            return;
        }
        if (i2 == 1) {
            showPreventModeView();
            return;
        }
        KgdLogUtil.i(TAG, "handleShowPreventModeView sendEmptyMessageDelayed");
        Handler handler4 = this.mHandler;
        if (handler4 != null) {
            handler4.sendEmptyMessageDelayed(3, 200L);
        }
    }

    public final void registerSensorListener() {
        if (KeyguardFeatureOption.INSTANCE.getSSupportSmartTouch() && SmartTouchSettingsObserver.Companion.getInstance().getSmartTouchModeState()) {
            KgdLogUtil.i(TAG, "register device smart touch enable");
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        KeyguardThreadUtil.Companion companion = KeyguardThreadUtil.Companion;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        anonymousClass1.$mainThreadStartTime = jCurrentTimeMillis;
        VarHandle.storeStoreFence();
        companion.runOnWorkThread(anonymousClass1);
    }

    public void removeCallback(IOplusPreventModeCallback iOplusPreventModeCallback) {
        Objects.requireNonNull(iOplusPreventModeCallback, "Callback must not be null.");
        this.mCallbacks.remove(iOplusPreventModeCallback);
    }

    public final void setBoundsChangedByRotation(boolean z) {
        this.boundsChangedByRotation = z;
    }

    public final void setMCallbacks(ArrayList<IOplusPreventModeCallback> arrayList) {
        this.mCallbacks = arrayList;
    }

    public final void setMCurrentResult(int i) {
        this.mCurrentResult = i;
    }

    public final void setMHandler(Handler handler) {
        this.mHandler = handler;
    }

    public final void setMKeyguardIsShowing(boolean z) {
        this.mKeyguardIsShowing = z;
    }

    public final void setMOccluded(boolean z) {
        this.mOccluded = z;
    }

    public final void setMOplusPreventWindowController(OplusPreventWindowController oplusPreventWindowController) {
        this.mOplusPreventWindowController = oplusPreventWindowController;
    }

    public final void setMPreventModeTrigger(boolean z) {
        this.mPreventModeTrigger = z;
    }

    public final void setMProximitySensorEventListener(ProximitySensorEventListener proximitySensorEventListener) {
        this.mProximitySensorEventListener = proximitySensorEventListener;
    }

    public final void setMRegisteredSensor(boolean z) {
        this.mRegisteredSensor = z;
    }

    public final void setMScreenOn(boolean z) {
        this.mScreenOn = z;
    }

    public final void setMSensor(Sensor sensor) {
        this.mSensor = sensor;
    }

    public final void setMSensorManager(SensorManager sensorManager) {
        this.mSensorManager = sensorManager;
    }

    public final void setMShowPreventModeViewPackages(ArraySet<String> arraySet) {
        this.mShowPreventModeViewPackages = arraySet;
    }

    public final void setMShowPreventModeWindow(boolean z) {
        this.mShowPreventModeWindow = z;
    }

    public final void setMSubDisplayPreventModeEnable(boolean z) {
        this.mSubDisplayPreventModeEnable = z;
    }

    public final void setMViewMediatorCallback(ViewMediatorCallback viewMediatorCallback) {
        this.mViewMediatorCallback = viewMediatorCallback;
    }

    public void setViewMediatorCallback(ViewMediatorCallback viewMediatorCallback) {
        this.mViewMediatorCallback = viewMediatorCallback;
    }

    public final boolean shouldNotRegisterSensor() {
        if (!KeyguardSettingUtil.Companion.getPreventModeSettingsForUser(this.context)) {
            KgdLogUtil.w(TAG, "prevent mode switch off, return");
            return true;
        }
        if (!this.mScreenOn) {
            KgdLogUtil.w(TAG, "screen off, return");
            return true;
        }
        if (!((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mShowing) {
            KgdLogUtil.w(TAG, "keyguard going away, return");
            return true;
        }
        if (this.mOccluded && !OplusPreventModeData.Companion.getInstance(this.context).f0switch) {
            ActivityManager activityManager = (ActivityManager) this.context.getSystemService(ActivityManager.class);
            List<ActivityManager.RunningTaskInfo> runningTasks = activityManager != null ? activityManager.getRunningTasks(1) : null;
            if (runningTasks != null && !runningTasks.isEmpty()) {
                ComponentName componentName = runningTasks.get(0).topActivity;
                String packageName = componentName != null ? componentName.getPackageName() : null;
                for (String str : this.mShowPreventModeViewPackages) {
                    KeyguardSettingBackupRestoreProxy$.ExternalSyntheticOutline0.m("topPackageName: ", packageName, "; packageName: ", str, TAG);
                    if (Intrinsics.areEqual(str, packageName)) {
                    }
                }
            }
            KgdLogUtil.w(TAG, "occluded and not meet the requirements, return");
            return true;
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x009e, code lost:
    
        if ((r0 != null ? kotlin.collections.CollectionsKt___CollectionsKt.contains(r0, r4) : false) != false) goto L44;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void showPreventModeView() {
        /*
            Method dump skipped, instruction units count: 208
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oplus.systemui.preventmode.OplusPreventModeExImpl.showPreventModeView():void");
    }

    public final void start() {
        Assert.isMainThread();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", "OplusPreventModeExImpl-->start");
        }
        this.mScreenOn = ((ScreenLifecycle) Dependency.sDependency.getDependencyInner(ScreenLifecycle.class)).getScreenState() == 2;
        this.mShowPreventModeViewPackages.add(OplusPreventModeUtils.getSosPackageName(this.context));
        this.mShowPreventModeViewPackages.add(OplusPreventModeUtils.getEmergencyPhonePackageName(this.context));
        ((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).registerCallback(this.updateMonitorCallback);
        ((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).addCallback(this.mCallback);
        ((ConfigurationController) Dependency.sDependency.getDependencyInner(ConfigurationController.class)).addCallback(this.configurationChangeListener);
        DeviceStateController.getInstance().addCallback(this.mFoldStateListener);
        this.mHandler = new Handler(Looper.getMainLooper(), this);
        if (this.mOplusPreventWindowController == null) {
            this.mOplusPreventWindowController = new OplusPreventWindowController(this.context);
        }
        Object systemService = this.context.getSystemService("sensor");
        this.mSensorManager = systemService instanceof SensorManager ? (SensorManager) systemService : null;
        this.mProximitySensorEventListener.setMProximityStateChangedCallback(this);
        registerSensorListener();
    }

    public final void stop() {
        Assert.isMainThread();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", "OplusPreventModeExImpl-->stop");
        }
        ((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).removeCallback(this.mCallback);
        ((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).removeCallback(this.updateMonitorCallback);
        DeviceStateController.getInstance().removeCallback(this.mFoldStateListener);
        ((ConfigurationController) Dependency.sDependency.getDependencyInner(ConfigurationController.class)).removeCallback(this.configurationChangeListener);
        hidePreventModeView(false);
        this.mProximitySensorEventListener.setMProximityStateChangedCallback(null);
        unregisterSensorListener();
        this.mShowPreventModeViewPackages.clear();
        this.mSensorManager = null;
        OplusPreventWindowController oplusPreventWindowController = this.mOplusPreventWindowController;
        if (oplusPreventWindowController != null) {
            oplusPreventWindowController.clear();
        }
        this.mOplusPreventWindowController = null;
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeMessages(3);
        }
        this.mHandler = null;
    }

    public final void unRegisterPreventModeSwitchObserver() {
        ObserverDispatcher.getInstance().unregisterObserver(this.mPreventModeSwitchObserver);
    }

    public final void unregisterSensorListener() {
        if (KeyguardFeatureOption.INSTANCE.getSSupportSmartTouch() && SmartTouchSettingsObserver.Companion.getInstance().getSmartTouchModeState()) {
            KgdLogUtil.i(TAG, "unregister device smart touch enable");
            return;
        }
        KeyguardThreadUtil.Companion companion = KeyguardThreadUtil.Companion;
        RunnableC02481 runnableC02481 = new RunnableC02481();
        runnableC02481.this$0 = this;
        VarHandle.storeStoreFence();
        companion.runOnWorkThread(runnableC02481);
    }

    public final void updateAuthState(int i, boolean z) {
        OplusLockIconView$.ExternalSyntheticOutline0.m("mPreventModeOn: ", TAG, z);
        if (i == 0) {
            KeyguardUpdateMonitor keyguardUpdateMonitor = (KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class);
            if (keyguardUpdateMonitor.mOplusKeyguardUpdateMonitor.isFingerprintUnlockPossible(KeyguardUpdateMonitor.getCurrentUser()) && FingerSwitchHelper.Companion.getInstance(this.context).isFingerprintUnlockEnable()) {
                ((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).updateFingerprintListeningState(2);
                return;
            }
            return;
        }
        if (i == 1 && z) {
            if ((((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).isUnlockWithFacePossible() && FaceSwitchHelper.getInstance(this.context).mFaceUnlockEnable) || ((OplusFlavorManager) DependencyEx.sDependency.getDependency(OplusFlavorManager.class)).getPalmUnlockController().isPalmUnlockValid()) {
                ((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).cancelFaceAuth();
            }
        }
    }

    public final void updateLayoutParams$1() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", "OplusPreventModeExImpl-->updateLayoutParams, update prevent window alpha");
        }
        if (!this.mShowPreventModeWindow) {
            KgdLogUtil.w(TAG, "updateLayoutParams, prevent view had removed");
            return;
        }
        try {
            OplusPreventWindowController oplusPreventWindowController = this.mOplusPreventWindowController;
            if (oplusPreventWindowController != null) {
                oplusPreventWindowController.updateLayoutParams();
            }
            this.mPreventModeTrigger = true;
            notifyPreventModeChanged$default(this, true);
            ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusKeyguardViewMediatorEx().handlePreventModeChanged();
            KeyguardViewMediator.5 r3 = this.mViewMediatorCallback;
            if (r3 != null) {
                r3.this$0.userActivity();
            }
        } catch (IllegalArgumentException e) {
            OplusTransitionExtendedInfoUtil$.ExternalSyntheticOutline0.m("caught exception: ", e.getMessage(), TAG);
        }
    }
}
