package com.oplus.systemui.keyguard.wallpaper.pictorial.dynamic;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.OplusBaseConfigurationAddon;
import android.os.Handler;
import android.os.Looper;
import android.os.Trace;
import androidx.collection.FloatList$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.ViewMediatorCallback;
import com.android.keyguard.base.HostCallback;
import com.android.keyguard.base.OplusKeyguardCtrl;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.systemui.Dependency;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.lifecycle.UnlockViewLifecycleOwner$;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.OlockStateUtil$;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.oplus.keyguard.settings.proxy.KeyguardSettingBackupRestoreProxy$;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator;
import com.oplus.systemui.keyguard.watcher.OplusKeyguardWatcher;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplusos.keyguard.utils.KgdLogUtil;
import dagger.Lazy;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PictorialEngineContainerController implements Dumpable {
    public static final Companion Companion = new Companion();
    private static final String TAG = "PictorialEngineContainerController";
    private final ConfigurationController configurationController;
    private final DumpManager dumpManager;
    private final ActivityStarter mActivityStarter;
    public boolean mBouncerShow;
    private final Context mContext;
    public float mCurrentProcessY;
    public int mDensity;
    private String mFileUnzipUri;
    private Handler mHandler;
    private final HostCallback mHostCallback;
    public boolean mIsQsExpanded;
    private final KeyguardStateController mKeyguardStateControllerImpl;
    private final StatusBarKeyguardViewManager.KeyguardViewManagerCallback mKeyguardViewManagerCallback;
    public int mLastFlipFont;
    private String mLastLocale;
    public volatile boolean mLoadOplusKeyguardSuccess;
    private final KeyguardUpdateMonitorCallback mMonitorCallback;
    private volatile OplusKeyguardCtrl mOplusKeyguard;
    private final PictorialEngineContainer mPictorialEngineContainer;
    private final PictorialMediator mPictorialMediator;
    private PictorialPluginManager mPictorialPluginManager;
    public boolean mReloadOplusKeyguardLater;
    private Runnable mReloadOplusKeyguardTask;
    private final ScreenLifecycle.Observer mScreenObserver;
    private final StatusBarStateController.StateListener mStateListener;
    public int mStatusBarState;
    private final KeyguardUpdateMonitor mUpdateMonitor;
    public boolean mUserSwitching;
    public int mViewFlag;
    private ViewMediatorCallback mViewMediatorCallback;
    private final WakefulnessLifecycle.Observer mWakefulnessObserver;
    private final Lazy statusBarKeyguardViewManagerLazy;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public PictorialEngineContainerController(Context context, PictorialEngineContainer pictorialEngineContainer, ConfigurationController configurationController, Lazy lazy, KeyguardUpdateMonitor keyguardUpdateMonitor, KeyguardStateController keyguardStateController, ActivityStarter activityStarter, PictorialMediator pictorialMediator, DumpManager dumpManager) {
        this.mContext = context;
        this.mPictorialEngineContainer = pictorialEngineContainer;
        this.configurationController = configurationController;
        this.statusBarKeyguardViewManagerLazy = lazy;
        this.mUpdateMonitor = keyguardUpdateMonitor;
        this.mKeyguardStateControllerImpl = keyguardStateController;
        this.mActivityStarter = activityStarter;
        this.mPictorialMediator = pictorialMediator;
        this.dumpManager = dumpManager;
        this.mLastLocale = "";
        PictorialEngineContainerController$mReloadOplusKeyguardTask$1 pictorialEngineContainerController$mReloadOplusKeyguardTask$1 = new PictorialEngineContainerController$mReloadOplusKeyguardTask$1();
        pictorialEngineContainerController$mReloadOplusKeyguardTask$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mReloadOplusKeyguardTask = pictorialEngineContainerController$mReloadOplusKeyguardTask$1;
        this.mStatusBarState = 1;
        Configuration configuration = context.getResources().getConfiguration();
        if ((configuration != null ? configuration.getLocales() : null) != null) {
            this.mLastLocale = configuration.getLocales().toLanguageTags();
        }
        if (configuration != null) {
            this.mDensity = configuration.densityDpi;
            this.mLastFlipFont = OplusBaseConfigurationAddon.getFlipFont(configuration);
        }
        this.mPictorialPluginManager = new PictorialPluginManager(context);
        Looper looperMyLooper = Looper.myLooper();
        if (looperMyLooper != null) {
            this.mHandler = new Handler(looperMyLooper);
        }
        pictorialEngineContainer.setMPictorialEngineContainerController(this);
        pictorialMediator.setPictorialEngineContainer(pictorialEngineContainer);
        dumpManager.registerDumpable(this);
        PictorialEngineContainerController$mScreenObserver$1 pictorialEngineContainerController$mScreenObserver$1 = new PictorialEngineContainerController$mScreenObserver$1();
        pictorialEngineContainerController$mScreenObserver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mScreenObserver = pictorialEngineContainerController$mScreenObserver$1;
        PictorialEngineContainerController$mWakefulnessObserver$1 pictorialEngineContainerController$mWakefulnessObserver$1 = new PictorialEngineContainerController$mWakefulnessObserver$1();
        pictorialEngineContainerController$mWakefulnessObserver$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mWakefulnessObserver = pictorialEngineContainerController$mWakefulnessObserver$1;
        PictorialEngineContainerController$mMonitorCallback$1 pictorialEngineContainerController$mMonitorCallback$1 = new PictorialEngineContainerController$mMonitorCallback$1();
        pictorialEngineContainerController$mMonitorCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mMonitorCallback = pictorialEngineContainerController$mMonitorCallback$1;
        PictorialEngineContainerController$mStateListener$1 pictorialEngineContainerController$mStateListener$1 = new PictorialEngineContainerController$mStateListener$1();
        pictorialEngineContainerController$mStateListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mStateListener = pictorialEngineContainerController$mStateListener$1;
        PictorialEngineContainerController$mKeyguardViewManagerCallback$1 pictorialEngineContainerController$mKeyguardViewManagerCallback$1 = new PictorialEngineContainerController$mKeyguardViewManagerCallback$1();
        pictorialEngineContainerController$mKeyguardViewManagerCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mKeyguardViewManagerCallback = pictorialEngineContainerController$mKeyguardViewManagerCallback$1;
        PictorialEngineContainerController$mHostCallback$1 pictorialEngineContainerController$mHostCallback$1 = new PictorialEngineContainerController$mHostCallback$1();
        pictorialEngineContainerController$mHostCallback$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mHostCallback = pictorialEngineContainerController$mHostCallback$1;
    }

    public void dump(PrintWriter printWriter, String[] strArr) {
        OplusKeyguardCtrl oplusKeyguardCtrl = this.mOplusKeyguard;
        Object feverExeData = oplusKeyguardCtrl != null ? oplusKeyguardCtrl.getFeverExeData() : null;
        printWriter.println("PictorialEngineContainerController:");
        printWriter.println("  mLoadOplusKeyguardSuccess=" + this.mLoadOplusKeyguardSuccess);
        printWriter.println("  mOplusKeyguard=" + oplusKeyguardCtrl);
        printWriter.println("  enginePlugin=" + (oplusKeyguardCtrl != null ? oplusKeyguardCtrl.getEnginePlugin() : null));
        printWriter.println("  getFeverExeData=" + feverExeData);
    }

    public final ConfigurationController getConfigurationController() {
        return this.configurationController;
    }

    public final ActivityStarter getMActivityStarter() {
        return this.mActivityStarter;
    }

    public final Context getMContext() {
        return this.mContext;
    }

    public final String getMFileUnzipUri() {
        return this.mFileUnzipUri;
    }

    public final KeyguardStateController getMKeyguardStateControllerImpl() {
        return this.mKeyguardStateControllerImpl;
    }

    public final PictorialEngineContainer getMPictorialEngineContainer() {
        return this.mPictorialEngineContainer;
    }

    public final PictorialMediator getMPictorialMediator() {
        return this.mPictorialMediator;
    }

    public final KeyguardUpdateMonitor getMUpdateMonitor() {
        return this.mUpdateMonitor;
    }

    public final Lazy getStatusBarKeyguardViewManagerLazy() {
        return this.statusBarKeyguardViewManagerLazy;
    }

    public final void onConfigChanged(Configuration configuration) {
        if (configuration.getLocales() != null) {
            String languageTags = configuration.getLocales().toLanguageTags();
            KeyguardSettingBackupRestoreProxy$.ExternalSyntheticOutline0.m("onConfigurationChanged, locale = ", languageTags, ", mLastLocale= ", this.mLastLocale, TAG);
            if (!Intrinsics.areEqual(languageTags, this.mLastLocale)) {
                reloadOplusKeyguard();
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
        reloadOplusKeyguard();
        if (flipFont != this.mLastFlipFont) {
            this.mLastFlipFont = flipFont;
        }
        if (this.mDensity != i) {
            this.mDensity = i;
            OplusLockIconView$.ExternalSyntheticOutline0.m("onConfigurationChanged mDensity != density isKeyguardVisible:", TAG, this.mUpdateMonitor.isKeyguardVisible$1());
            if (!this.mUpdateMonitor.isKeyguardVisible$1()) {
                this.mReloadOplusKeyguardLater = true;
                return;
            }
            Handler handler = this.mHandler;
            if (handler != null) {
                handler.removeCallbacks(this.mReloadOplusKeyguardTask);
            }
            Handler handler2 = this.mHandler;
            if (handler2 != null) {
                handler2.postDelayed(this.mReloadOplusKeyguardTask, 100L);
            }
        }
    }

    public final void onViewAttached() {
        ((ScreenLifecycle) Dependency.sDependency.getDependencyInner(ScreenLifecycle.class)).addObserver(this.mScreenObserver);
        ((WakefulnessLifecycle) Dependency.sDependency.getDependencyInner(WakefulnessLifecycle.class)).addObserver(this.mWakefulnessObserver);
        ((SysuiStatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class)).addCallback(this.mStateListener, 2);
        this.mUpdateMonitor.registerCallback(this.mMonitorCallback);
        ((StatusBarKeyguardViewManager) this.statusBarKeyguardViewManagerLazy.get()).addCallback(this.mKeyguardViewManagerCallback);
    }

    public final void onViewDetached() {
        ((ScreenLifecycle) Dependency.sDependency.getDependencyInner(ScreenLifecycle.class)).removeObserver(this.mScreenObserver);
        ((WakefulnessLifecycle) Dependency.sDependency.getDependencyInner(WakefulnessLifecycle.class)).removeObserver(this.mWakefulnessObserver);
        this.mUpdateMonitor.removeCallback(this.mMonitorCallback);
        StatusBarKeyguardViewManager statusBarKeyguardViewManager = (StatusBarKeyguardViewManager) this.statusBarKeyguardViewManagerLazy.get();
        statusBarKeyguardViewManager.mCallbacks.remove(this.mKeyguardViewManagerCallback);
    }

    public final void onVisibilityChange(boolean z) {
        if (this.mOplusKeyguard == null || this.mPictorialEngineContainer.getMOplusKeyguardView() == null) {
            return;
        }
        OlockStateUtil$.ExternalSyntheticOutline0.m("onVisibilityChange show = ", ", ", TAG, z, this.mUpdateMonitor.isKeyguardVisible$1());
        if (z && this.mUpdateMonitor.isKeyguardVisible$1()) {
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

    /* JADX WARN: Code duplicated, block: B:19:0x0078  */
    public final void reloadOplusKeyguard() {
        OplusKeyguardCtrl oplusKeyguardCtrl;
        OplusKeyguardCtrl oplusKeyguardCtrl2;
        boolean z;
        OplusLockIconView$.ExternalSyntheticOutline0.m("reloadOplusKeyguard mReloadOplusKeyguardLater: ", TAG, this.mReloadOplusKeyguardLater);
        this.mReloadOplusKeyguardLater = false;
        OplusKeyguardCtrl oplusKeyguardCtrl3 = this.mOplusKeyguard;
        if (oplusKeyguardCtrl3 != null) {
            oplusKeyguardCtrl3.cleanUp();
        }
        OplusKeyguardCtrl oplusKeyguardCtrl4 = this.mOplusKeyguard;
        if (oplusKeyguardCtrl4 != null) {
            oplusKeyguardCtrl4.setHostCallback((HostCallback) null);
        }
        this.mPictorialEngineContainer.removeAllViews();
        this.mPictorialPluginManager.clearKeyguardPlugIn();
        this.mOplusKeyguard = null;
        this.mPictorialEngineContainer.setMOplusKeyguardView(null);
        this.mLoadOplusKeyguardSuccess = false;
        Trace.traceBegin(8L, "KeyguardEngineContainer#inflate");
        boolean z2 = KeyguardUtils.Companion.isDefaultKeyguardInSettings(this.mContext) && this.mPictorialMediator.isMainSwitchOn();
        boolean z3 = SuperPowerSaveSettingsObserver.Companion.getInstance().isSuperPowerSaveState;
        if (z2 && !z3) {
            this.mOplusKeyguard = this.mPictorialPluginManager.loadKeyguard(this);
            KgdLogUtil.i(TAG, "loadOplusKeyguard : mOplusKeyguard = " + this.mOplusKeyguard);
            if (this.mOplusKeyguard != null) {
                boolean z4 = this.mKeyguardStateControllerImpl.mSecure;
                if (!z4 && (oplusKeyguardCtrl2 = this.mOplusKeyguard) != null) {
                    oplusKeyguardCtrl2.checkNeedsInput();
                }
                OplusKeyguardCtrl oplusKeyguardCtrl5 = this.mOplusKeyguard;
                if (oplusKeyguardCtrl5 != null) {
                    oplusKeyguardCtrl5.setHostCallback(this.mHostCallback);
                }
                OplusKeyguardCtrl oplusKeyguardCtrl6 = this.mOplusKeyguard;
                if (oplusKeyguardCtrl6 != null) {
                    oplusKeyguardCtrl6.checkNeedsChangeWallpaper(z4);
                }
                if (!this.mUpdateMonitor.mDeviceInteractive && (oplusKeyguardCtrl = this.mOplusKeyguard) != null) {
                    oplusKeyguardCtrl.onScreenTurnedOff();
                }
                PictorialEngineContainer pictorialEngineContainer = this.mPictorialEngineContainer;
                OplusKeyguardCtrl oplusKeyguardCtrl7 = this.mOplusKeyguard;
                pictorialEngineContainer.setMOplusKeyguardView(oplusKeyguardCtrl7 != null ? oplusKeyguardCtrl7.getExactLockView() : null);
                showOrHideOplusKeyguard();
                onVisibilityChange(!this.mBouncerShow);
                z = this.mPictorialEngineContainer.getMOplusKeyguardView() != null;
            }
            this.mLoadOplusKeyguardSuccess = z;
        }
        setVisibility(this.mLoadOplusKeyguardSuccess ? 0 : 8);
        if (z2) {
            OplusKeyguardWatcher companion = OplusKeyguardWatcher.Companion.getInstance();
            OplusKeyguardCtrl oplusKeyguardCtrl8 = this.mOplusKeyguard;
            companion.dispatchSetBackground(oplusKeyguardCtrl8 != null ? oplusKeyguardCtrl8.getExtraWallpaper() : null);
        }
        Trace.traceEnd(8L);
    }

    public final void setMFileUnzipUri(String str) {
        this.mFileUnzipUri = str;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x001b A[PHI: r0
      0x001b: PHI (r0v9 float) = (r0v5 float), (r0v6 float) binds: [B:10:0x0019, B:13:0x0020] A[DONT_GENERATE, DONT_INLINE]] */
    public final void setProcessY(float f) {
        if (this.mOplusKeyguard == null || this.mPictorialEngineContainer.getMOplusKeyguardView() == null || this.mPictorialEngineContainer.getVisibility() != 0) {
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
        showOrHideOplusKeyguard();
    }

    public final void setViewFlag(int i) {
        this.mViewFlag = i;
        OplusLockIconView$.ExternalSyntheticOutline0.m(i, "setViewFlag flag = ", TAG);
        if ((this.mViewFlag & 4) != 0) {
            this.mPictorialEngineContainer.setLayerType(1, null);
        }
    }

    public final void setVisibility(int i) {
        if (this.mLoadOplusKeyguardSuccess && this.mUpdateMonitor.isKeyguardVisible$1()) {
            this.mPictorialEngineContainer.setVisibility(i);
        } else {
            this.mPictorialEngineContainer.setVisibility(8);
        }
    }

    public final void showOrHideOplusKeyguard() {
        if (this.mOplusKeyguard == null || this.mPictorialEngineContainer.getMOplusKeyguardView() == null) {
            return;
        }
        boolean zIsKeyguardVisible$1 = this.mUpdateMonitor.isKeyguardVisible$1();
        int i = this.mStatusBarState;
        boolean z = !this.mBouncerShow;
        KeyguardUtils.Companion companion = KeyguardUtils.Companion;
        float f = this.mCurrentProcessY;
        companion.getClass();
        boolean zIsFloatEqual = KeyguardUtils.Companion.isFloatEqual(f, 1.0f);
        boolean z2 = ((ScreenLifecycle) Dependency.sDependency.getDependencyInner(ScreenLifecycle.class)).getScreenState() == 2;
        boolean z3 = !this.mIsQsExpanded;
        StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m("isNeedShow , mUpdateMonitor.isKeyguardVisible() = ", i, ", mStatusBarState = ", ", !mBouncerShow = ", zIsKeyguardVisible$1);
        KeyguardLogger$.ExternalSyntheticOutline0.m(", KeyguardUtils.isFloatEqual(mCurrentProcessY, 1f) = ", ", mUpdateMonitor.isScreenOn() = ", sbM, z, zIsFloatEqual);
        UnlockViewLifecycleOwner$.ExternalSyntheticOutline0.m(", !mIsQsExpanded = ", TAG, sbM, z2, z3);
        if (!this.mUpdateMonitor.isKeyguardVisible$1() || this.mStatusBarState != 1 || this.mBouncerShow || this.mUserSwitching || this.mKeyguardStateControllerImpl.mKeyguardGoingAway || !KeyguardUtils.Companion.isFloatEqual(this.mCurrentProcessY, 1.0f) || ((ScreenLifecycle) Dependency.sDependency.getDependencyInner(ScreenLifecycle.class)).getScreenState() != 2 || this.mIsQsExpanded) {
            KgdLogUtil.i(TAG, "hide");
            OplusKeyguardCtrl oplusKeyguardCtrl = this.mOplusKeyguard;
            if (oplusKeyguardCtrl != null) {
                oplusKeyguardCtrl.hide();
                return;
            }
            return;
        }
        KgdLogUtil.i(TAG, "show");
        OplusKeyguardCtrl oplusKeyguardCtrl2 = this.mOplusKeyguard;
        if (oplusKeyguardCtrl2 != null) {
            oplusKeyguardCtrl2.show();
        }
    }
}
