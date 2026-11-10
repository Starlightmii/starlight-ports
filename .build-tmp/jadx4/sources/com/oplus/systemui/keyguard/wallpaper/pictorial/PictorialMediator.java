package com.oplus.systemui.keyguard.wallpaper.pictorial;

import android.app.WallpaperColors;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.Trace;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Pair;
import androidx.activity.result.ActivityResultRegistry$;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.car.app.serialization.Bundler$;
import androidx.collection.FloatList$;
import androidx.compose.material3.internal.TextFieldImplKt$;
import androidx.lifecycle.MutableLiveData;
import androidx.slice.widget.RowView$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.keyguard.OplusLockIconView$;
import com.android.keyguard.base.OplusKeyguardCtrl$;
import com.android.keyguard.wallpaper.KeyguardWallpaperInfo;
import com.android.keyguard.wallpaper.OplusLockScreenWallpaper;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.Dumpable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.Lifecycle;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.lifecycle.UnlockViewLifecycleOwner$;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.statusbar.OlockStateUtil$;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.google.gson.ExclusionStrategy;
import com.google.gson.GsonBuilder;
import com.oplus.charge.viewmodel.OplusChargeAnimController$;
import com.oplus.dispatch.OCDHandler;
import com.oplus.keyguard.utils.KeyguardUtils;
import com.oplus.multiapp.OplusMultiAppManager;
import com.oplus.systemui.aod.aodclock.constant.AodData;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplus.systemui.aod.utils.AodStyleTypeSettingsObserver;
import com.oplus.systemui.biometrics.face.FaceSwitchHelper;
import com.oplus.systemui.biometrics.finger.udfps.OnScreenFingerprintUiMech;
import com.oplus.systemui.common.flavor.OplusFlavorManager;
import com.oplus.systemui.keyguard.OplusKeyguardTipsViewController;
import com.oplus.systemui.keyguard.customize.KeyguardCustomizeManager;
import com.oplus.systemui.keyguard.wallpaper.pictorial.app.PictorialAppData;
import com.oplus.systemui.keyguard.wallpaper.pictorial.app.PictorialAppDataImpl;
import com.oplus.systemui.keyguard.wallpaper.pictorial.app.PictorialAppDataImpl$;
import com.oplus.systemui.keyguard.wallpaper.pictorial.app.PictorialAppDataImpl$$ExternalSyntheticLambda0;
import com.oplus.systemui.keyguard.wallpaper.pictorial.app.PictorialAppInfo;
import com.oplus.systemui.keyguard.wallpaper.pictorial.dynamic.PictorialEngineContainer;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialBuriedHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialCursorHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialFactory;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialMediatorHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.oplus.OplusPictorialCursorHelper;
import com.oplus.systemui.keyguard.watcher.PictorialStateWatcher;
import com.oplus.systemui.navigationbar.gesture.manager.OplusNavigationBarManager;
import com.oplus.systemui.qs.observer.SuperPowerSaveSettingsObserver;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplusos.keyguard.utils.KeyguardThreadUtil;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.feature.AodFeatureOption;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.FlavorOneFeatureOption;
import com.oplusos.systemui.common.feature.FlavorTwoFeatureOption;
import com.oplusos.systemui.common.feature.KeyguardFeatureOption;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import com.oplusos.systemui.common.observer.ChildrenModeObserver;
import com.oplusos.systemui.common.observer.ObserverDispatcher;
import com.oplusos.systemui.common.util.BrandUtils;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import com.oplusos.systemui.common.utils.Util;
import com.oplusos.systemui.util.OpUtils;
import dagger.Lazy;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class PictorialMediator implements Dumpable {
    public static volatile AtomicBoolean sPictorialFestivalWallpaperUsed = new AtomicBoolean(false);
    public final PictorialMediator$$ExternalSyntheticLambda3 mAodStyleTypeListener;
    public boolean mAodStyleTypeObserverRegistered;
    public int mAutoPlaySwitchKeyState;
    public final AnonymousClass4 mAutoPlaySwitchObserver;
    public boolean mAutoPlaySwitchOn;
    public boolean mAwaitingDisableAliveStopAfterUnlock;
    public final AnonymousClass4 mBackToKeyguardObserver;
    public final List mCallbacks;
    public boolean mCanConnectOnLightOs;
    public boolean mCanUpdateWelcomePreloadWallpaper;
    public final ConfigurationController mConfigurationController;
    public final AnonymousClass23 mConnection;
    public final ContentResolver mContentResolver;
    public final Context mContext;
    public Context mDefaultDensityContext;
    public boolean mDisableAliveSettingsObserverRegistered;
    public int mEnablePlaySwitchKeyState;
    public final AnonymousClass4 mEnablePlaySwitchObserver;
    public boolean mEnablePlaySwitchOn;
    public boolean mEntryPictorialSlidePage;
    public volatile boolean mFirstGetPictorialDefaultSetting;
    public final AnonymousClass3 mHandler;
    public boolean mHasPictorialSpace;
    public boolean mHasTriggerActiveNotShowUpdate;
    public final AnonymousClass4 mHongshuSpecialLightEnableObServer;
    public KeyguardUpdateMonitorCallback mInfoCallback;
    public boolean mInfoCallbackRegistered;
    public volatile boolean mIsLastDrawBitmapFromPictorial;
    public boolean mIsLockScreenAttached;
    public boolean mIsMetaDataSupportRightSwipe;
    public boolean mIsNeedUpdateView;
    public boolean mIsPictorialFestivalWallpaperReady;
    public boolean mIsProcessStartWhenReboot;
    public boolean mIsSuperPowerSave;
    public boolean mIsSupportDisableAlive;
    public boolean mIsSupportLeftSwipe;
    public boolean mIsSupportPictorialFestivalWallpaper;
    public boolean mIsUiEngineSwipe;
    public boolean mIsValidNotShowEvent;
    public final KeyguardCustomizeManager mKeyguardManager;
    public volatile boolean mKeyguardShowing;
    public final Lazy mKeyguardViewMediator;
    public volatile boolean mKgdPictorialRecommendation;
    public final AnonymousClass4 mKgdPictorialRecommendationObserver;
    public final AnonymousClass10 mKgdStateCallback;
    public int mLastWallpaperID;
    public int mLeftSwipeSwitchKeyState;
    public final AnonymousClass4 mLeftSwipeSwitchObserver;
    public boolean mLeftSwipeSwitchOn;
    public int mMainSwitchKeyState;
    public final AnonymousClass4 mMainSwitchObserver;
    public boolean mMainSwitchOn;
    public volatile boolean mNeedReleaseForFestivalPaod;
    public int mNotShowAgainSwitchOn;
    public final AnonymousClass22 mObserver;
    public OnScreenFingerprintUiMech mOnScreenFingerprintUiMech;
    public final Lazy mOplusFlavorManagerLazy;
    public final Lazy mOplusLockScreenWallpaper;
    public final AnonymousClass4 mOtherPictorialAvailableSwitchObserver;
    public final PackageManager mPackageManager;
    public NotificationPanelViewController mPanelViewController;
    public final AnonymousClass4 mPictorialActivateObServer;
    public PictorialAppDataImpl mPictorialAppDataImpl;
    public boolean mPictorialAppSwitching;
    public PictorialBuriedHelper mPictorialBuriedHelper;
    public PictorialCursorHelper mPictorialCursorHelper;
    public boolean mPictorialEnabled;
    public PictorialEngineContainer mPictorialEngineContainer;
    public boolean mPictorialExist;
    public PictorialFactory mPictorialFactory;
    public final AnonymousClass4 mPictorialFestivalWallpaperObserver;
    public AnonymousClass4 mPictorialGuideUserObserver;
    public final AnonymousClass4 mPictorialImageObserver;
    public PictorialMediatorHelper mPictorialMediatorHelper;
    public final AnonymousClass4 mPictorialMessageViewObserver;
    public final AnonymousClass24 mPictorialReceiver;
    public boolean mPictorialServiceConnected;
    public PictorialStatusObserver mPictorialStatusOberver;
    public final AnonymousClass4 mPictorialTaglineSwitchObserver;
    public final PictorialWallpaperLoader mPictorialWallpaperLoader;
    public final AnonymousClass24 mReceiver;
    public boolean mRegisterSwitchObserver;
    public boolean mRegisteredPictorialImageObserver;
    public final PictorialMediator$$ExternalSyntheticLambda1 mReloadPictorialRunnable;
    public int mRightSwipeSwitchKeyState;
    public final AnonymousClass4 mRightSwipeSwitchObserver;
    public boolean mRightSwipeSwitchOn;
    public final AnonymousClass4 mSettingsObserver;
    public final AtomicBoolean mSkipClosePictorialFestivalWallpaper;
    public volatile boolean mStartGetPictorialDefaultSetting;
    public final StartPictorialServiceRunnable mStartPictorialServiceRunnable;
    public final AnonymousClass1 mStopPictorialServiceRunnable;
    public PictorialMediatorHelper.SwitchStateParams mSwitchStateParams;
    public boolean mTaglineSwitchOn;
    public TipsChangeWatcher mTipsChangeWatcher;
    public final AnonymousClass4 mUnlockChangePkgObserver;
    public PictorialMediator$2$$ExternalSyntheticLambda0 mUpdateHKTipsWhenScreenOn;
    public final MutableLiveData mWaitingPictorialData;
    public final AnonymousClass1 mWaitingPictorialOvertimeRunnable;
    public final AnonymousClass21 mWakefulnessObserver;
    public boolean mWallpaperChanged;
    public int mWallpaperId;
    public final WallpaperManager mWallpaperManager;

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ PictorialMediator this$0;

        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    KgdLogUtil.d("PictorialMediator", "waitingPictorialOvertime run");
                    this.this$0.mWaitingPictorialData.setValue(Boolean.FALSE);
                    this.this$0.doLiveCheck();
                    break;
                default:
                    Trace.traceBegin(8L, "mStopPictorialServiceRunnable");
                    if (!this.this$0.mPictorialServiceConnected) {
                        KgdLogUtil.i("PictorialMediator", "mStopPictorialServiceRunnable not connect, return");
                    } else {
                        try {
                            KgdLogUtil.i("PictorialMediator", "mStopPictorialServiceRunnable start");
                            PictorialMediator pictorialMediator = this.this$0;
                            int i = 0;
                            pictorialMediator.mPictorialServiceConnected = false;
                            pictorialMediator.mAwaitingDisableAliveStopAfterUnlock = false;
                            pictorialMediator.mContext.unbindService(pictorialMediator.mConnection);
                            Intent intent = new Intent();
                            intent.setAction(this.this$0.mPictorialAppDataImpl.getDecoupLingAction());
                            intent.setPackage(this.this$0.mPictorialAppDataImpl.getPictorialAppPackage());
                            this.this$0.mContext.stopService(intent);
                            if (this.this$0.mKeyguardShowing && this.this$0.isSupportDisableAliveOrOplusGLStyle()) {
                                KgdLogUtil.i("PictorialMediator", "mStopPictorialServiceRunnable reload after stop, showing=true");
                                AnonymousClass3 anonymousClass3 = this.this$0.mHandler;
                                PictorialMediator$2$$ExternalSyntheticLambda0 pictorialMediator$2$$ExternalSyntheticLambda0 = new PictorialMediator$2$$ExternalSyntheticLambda0(i);
                                pictorialMediator$2$$ExternalSyntheticLambda0.f$0 = this;
                                VarHandle.storeStoreFence();
                                anonymousClass3.post(pictorialMediator$2$$ExternalSyntheticLambda0);
                            }
                        } catch (Exception e) {
                            KgdLogUtil.w("PictorialMediator", "e = " + e.toString());
                        }
                        Trace.traceEnd(8L);
                    }
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator$10, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass10 implements KeyguardStateController.Callback {
        public /* synthetic */ PictorialMediator this$0;

        public final void onKeyguardShowingChanged() {
            PictorialMediator pictorialMediator = this.this$0;
            if (((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mShowing) {
                return;
            }
            KgdLogUtil.d("PictorialMediator", "onKeyguardShowingChanged isShowing:false");
            if (!pictorialMediator.mIsValidNotShowEvent) {
                pictorialMediator.mIsValidNotShowEvent = true;
            } else {
                if (pictorialMediator.mCanConnectOnLightOs) {
                    return;
                }
                KgdLogUtil.d("PictorialMediator", "can connect on LightOs");
                pictorialMediator.mCanConnectOnLightOs = true;
                pictorialMediator.mCanUpdateWelcomePreloadWallpaper = true;
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator$21, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass21 implements WakefulnessLifecycle.Observer {
        public /* synthetic */ PictorialMediator this$0;

        public final void onFinishedGoingToSleep() {
            PictorialMediator pictorialMediator = this.this$0;
            if (pictorialMediator.mHasTriggerActiveNotShowUpdate) {
                return;
            }
            boolean z = false;
            if (pictorialMediator.isForBidToConnectInternal(false)) {
                KgdLogUtil.i("PictorialMediator", "triggerActiveNotShowUpdate isForBidToConnect, return");
            } else {
                PictorialMediator$$ExternalSyntheticLambda1 pictorialMediator$$ExternalSyntheticLambda1 = new PictorialMediator$$ExternalSyntheticLambda1(3);
                pictorialMediator$$ExternalSyntheticLambda1.f$0 = pictorialMediator;
                VarHandle.storeStoreFence();
                KeyguardThreadUtil.postOnBackgroundThread(pictorialMediator$$ExternalSyntheticLambda1);
                z = true;
            }
            pictorialMediator.mHasTriggerActiveNotShowUpdate = z;
            OplusChargeAnimController$.ExternalSyntheticOutline0.m("PictorialMediator", new StringBuilder("mHasTriggerActiveNotShowUpdate:"), pictorialMediator.mHasTriggerActiveNotShowUpdate);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator$22, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass22 implements ScreenLifecycle.Observer {
        public boolean mRestartPictorial;
        public /* synthetic */ PictorialMediator this$0;

        public final void onScreenTurnedOff() {
            if (this.mRestartPictorial) {
                this.this$0.restartPictorialWallpaper();
                this.mRestartPictorial = false;
            }
        }

        public final void onScreenTurnedOn() {
            PictorialMediator pictorialMediator = this.this$0;
            AnonymousClass3 anonymousClass3 = pictorialMediator.mHandler;
            if (pictorialMediator.isHKStyle()) {
                if (pictorialMediator.mUpdateHKTipsWhenScreenOn == null) {
                    PictorialMediator$2$$ExternalSyntheticLambda0 pictorialMediator$2$$ExternalSyntheticLambda0 = new PictorialMediator$2$$ExternalSyntheticLambda0(1);
                    pictorialMediator$2$$ExternalSyntheticLambda0.f$0 = this;
                    VarHandle.storeStoreFence();
                    pictorialMediator.mUpdateHKTipsWhenScreenOn = pictorialMediator$2$$ExternalSyntheticLambda0;
                }
                anonymousClass3.removeCallbacks(pictorialMediator.mUpdateHKTipsWhenScreenOn);
                anonymousClass3.postDelayed(pictorialMediator.mUpdateHKTipsWhenScreenOn, 800L);
            }
        }

        public final void onScreenTurningOff() {
            PictorialMediator pictorialMediator = this.this$0;
            if (pictorialMediator.mPictorialAppDataImpl.mNextPictorialAppInfo != null) {
                pictorialMediator.stopProviderService();
                pictorialMediator.unRegisterSwitchObserver();
                pictorialMediator.unRegisterPictorialObserver();
                pictorialMediator.unregisterAodStyleTypeObserver();
                boolean zTrySwitchPictorialApp = pictorialMediator.mPictorialAppDataImpl.trySwitchPictorialApp();
                this.mRestartPictorial = zTrySwitchPictorialApp;
                if (zTrySwitchPictorialApp) {
                    pictorialMediator.mPictorialAppSwitching = true;
                }
                OplusChargeAnimController$.ExternalSyntheticOutline0.m("PictorialMediator", new StringBuilder("start SwitchPictorialApp "), this.mRestartPictorial);
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator$23, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass23 implements ServiceConnection {
        public /* synthetic */ PictorialMediator this$0;

        /* JADX WARN: Code duplicated, block: B:27:0x00a1  */
        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            boolean z;
            KeyguardFeatureOption keyguardFeatureOption = KeyguardFeatureOption.INSTANCE;
            keyguardFeatureOption.getClass();
            if (KeyguardFeatureOption.isSupportOtherPictorial) {
                this.this$0.mPictorialAppSwitching = false;
            }
            PictorialUtils.sPictorialHandler.removeCallbacks(this.this$0.mStartPictorialServiceRunnable);
            StringBuilder sb = new StringBuilder("onServiceConnected to Pictorial service mIsNeedUpdateView:");
            sb.append(this.this$0.mIsNeedUpdateView);
            sb.append(",mIsSupportDisableAlive:");
            OplusChargeAnimController$.ExternalSyntheticOutline0.m("PictorialMediator", sb, this.this$0.mIsSupportDisableAlive);
            PictorialMediator pictorialMediator = this.this$0;
            pictorialMediator.mPictorialServiceConnected = true;
            if (pictorialMediator.mIsNeedUpdateView) {
                pictorialMediator.handleObtainPictorialWallpaper(true);
                this.this$0.mIsNeedUpdateView = false;
            } else if (KeyguardFeatureOption.isKeepHeyTapPictorial && pictorialMediator.isMainSwitchStatusOn()) {
                PictorialMediator pictorialMediator2 = this.this$0;
                pictorialMediator2.mPictorialAppSwitching = true;
                pictorialMediator2.handleObtainPictorialWallpaper(true);
            } else {
                KeyguardWallpaperInfo currentWallpaperInfo = ((OplusLockScreenWallpaper) this.this$0.mOplusLockScreenWallpaper.get()).getCurrentWallpaperInfo();
                boolean z2 = currentWallpaperInfo != null && currentWallpaperInfo.mType == 2;
                boolean z3 = ((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).mKeyguardShowing;
                if (SmoothTransitionController.isPanoramicAodEnable(this.this$0.mContext)) {
                    KeyguardWallpaperInfo currentWallpaperInfo2 = ((OplusLockScreenWallpaper) this.this$0.mOplusLockScreenWallpaper.get()).getCurrentWallpaperInfo();
                    if (currentWallpaperInfo2 == null ? false : currentWallpaperInfo2.isPictorialType()) {
                        z = true;
                    } else {
                        z = false;
                    }
                } else {
                    z = false;
                }
                StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("onServiceConnected isEngineViewType=", ",isKeyguardShowing=", ",isPanoramicAodScene=", z2, z3);
                sbM.append(z);
                KgdLogUtil.d("PictorialMediator", sbM.toString());
                PictorialMediator pictorialMediator3 = this.this$0;
                pictorialMediator3.handleObtainPictorialWallpaper(!pictorialMediator3.mIsSupportDisableAlive || z2 || z3);
            }
            this.this$0.obtainPictorialMessageViewInfo();
            this.this$0.mPictorialMediatorHelper.onPictorialServicesConnect();
            if (this.this$0.isOplusGLStyle()) {
                this.this$0.checkSupportTaglineMsg();
                this.this$0.updatePictorialGuideEntryInfo();
                this.this$0.getPictorialBuriedHelper().uploadSwitch();
            }
            this.this$0.checkIsSupportPictorialFestivalWallpaper();
            this.this$0.updatePictorialFestivalWallpaperReady();
            boolean z4 = this.this$0.getSwitchStatusFromSettings("hongshu_special_light_group") == 1;
            KgdLogUtil.i("PictorialMediator", "checkHongshuSpecialLightEnable, specialLightEnable = " + z4);
            keyguardFeatureOption.setHongshuSpecialLightEnable(z4);
            PictorialMediator pictorialMediator4 = this.this$0;
            if (!(!pictorialMediator4.mIsSupportPictorialFestivalWallpaper ? false : pictorialMediator4.mIsPictorialFestivalWallpaperReady) || !PictorialUtils.isFestivalWallpaperEnabled(pictorialMediator4.mContext) || ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getKeyguardStyleClockController().isConfigPackageAsGalleryClock()) {
                PictorialMediator.sPictorialFestivalWallpaperUsed.set(false);
            } else if (!PictorialMediator.sPictorialFestivalWallpaperUsed.get()) {
                KeyguardWallpaperInfo currentWallpaperInfo3 = ((OplusLockScreenWallpaper) this.this$0.mOplusLockScreenWallpaper.get()).getCurrentWallpaperInfo();
                if (!(currentWallpaperInfo3 != null ? currentWallpaperInfo3.isPictorialType() : false)) {
                    KgdLogUtil.d("PictorialMediator", "onServiceConnected dispatchFestivalWallpaperChange");
                    Iterator it = PictorialStateWatcher.getInstance().mObserver.iterator();
                    while (it.hasNext()) {
                        ((PictorialStateWatcher.Observer) it.next()).onPictorialFestivalWallpaperChange(true);
                    }
                }
                PictorialMediator.sPictorialFestivalWallpaperUsed.set(true);
                PictorialStateWatcher.getInstance().dispatchPictorialFestivalWallpaperUsageChange(true);
            }
            if (this.this$0.mFirstGetPictorialDefaultSetting) {
                this.this$0.getPictorialDefaultSetting();
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            KgdLogUtil.i("PictorialMediator", "onServiceDisconnected to Pictorial service");
            PictorialMediator pictorialMediator = this.this$0;
            pictorialMediator.mPictorialServiceConnected = false;
            if (pictorialMediator.mPictorialMediatorHelper.onPictorialServicesDisconnect(pictorialMediator.mContext)) {
                return;
            }
            this.this$0.firstProviderService(false);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator$26, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass26 implements KeyguardCustomizeManager.OnChangeListener {
        public /* synthetic */ PictorialMediator this$0;

        public final void onChange() {
            PictorialMediator pictorialMediator = this.this$0;
            if (pictorialMediator.mKeyguardManager.needDisablePictorial()) {
                pictorialMediator.closePictorialSwitch();
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator$27, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass27 implements WallpaperManager.OnColorsChangedListener {
        public /* synthetic */ PictorialMediator this$0;

        @Override // android.app.WallpaperManager.OnColorsChangedListener
        public final void onColorsChanged(WallpaperColors wallpaperColors, int i) {
            int i2 = 2;
            if ((i & 2) != 0) {
                if (KeyguardUpdateMonitor.getCurrentUser() != 0) {
                    KgdLogUtil.d("PictorialMediator", "onColorsChanged: under the sub-user, return");
                    return;
                }
                PictorialMediator$2$$ExternalSyntheticLambda0 pictorialMediator$2$$ExternalSyntheticLambda0 = new PictorialMediator$2$$ExternalSyntheticLambda0(i2);
                pictorialMediator$2$$ExternalSyntheticLambda0.f$0 = this;
                VarHandle.storeStoreFence();
                PictorialUtils.runOnWorkThread(pictorialMediator$2$$ExternalSyntheticLambda0);
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator$9, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass9 extends KeyguardUpdateMonitorCallback {
        public /* synthetic */ PictorialMediator this$0;

        /* JADX WARN: Code duplicated, block: B:7:0x002a  */
        public final void onDeviceProvisioned() {
            KgdLogUtil.i("PictorialMediator", "onDeviceProvisioned");
            PictorialMediator pictorialMediator = this.this$0;
            AtomicBoolean atomicBoolean = PictorialMediator.sPictorialFestivalWallpaperUsed;
            pictorialMediator.initStatusWhenShowingLocked();
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isLightOsForLH()) {
                BrandUtils.INSTANCE.getClass();
                if (BrandUtils.isRealme) {
                    pictorialMediator.firstProviderService(true);
                } else {
                    KgdLogUtil.d("PictorialMediator", "not connect to pictorial");
                }
            } else {
                pictorialMediator.firstProviderService(true);
            }
            pictorialMediator.registerPictorialObserver();
        }

        public final void onKeyguardVisibilityChanged(boolean z) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("onKeyguardVisibilityChanged showing = ", "PictorialMediator", z);
            this.this$0.mKeyguardShowing = z;
            if (this.this$0.isSupportDisableAliveOrOplusGLStyle()) {
                this.this$0.ensureDisableAliveSettingsObserver();
                if (z) {
                    PictorialMediator pictorialMediator = this.this$0;
                    if (pictorialMediator.mAwaitingDisableAliveStopAfterUnlock && pictorialMediator.mPictorialServiceConnected && !pictorialMediator.isAutoPlaySwitchOn()) {
                        this.this$0.mAwaitingDisableAliveStopAfterUnlock = false;
                        KgdLogUtil.i("PictorialMediator", "quick return keyguard before disableAlive stop, force rebind");
                        this.this$0.stopProviderService();
                    } else {
                        PictorialMediator pictorialMediator2 = this.this$0;
                        pictorialMediator2.mAwaitingDisableAliveStopAfterUnlock = false;
                        AnonymousClass3 anonymousClass3 = pictorialMediator2.mHandler;
                        PictorialMediator$9$$ExternalSyntheticLambda0 pictorialMediator$9$$ExternalSyntheticLambda0 = new PictorialMediator$9$$ExternalSyntheticLambda0(1);
                        pictorialMediator$9$$ExternalSyntheticLambda0.f$0 = this;
                        VarHandle.storeStoreFence();
                        anonymousClass3.post(pictorialMediator$9$$ExternalSyntheticLambda0);
                    }
                } else {
                    KeyguardStateControllerImpl keyguardStateControllerImpl = OplusNavigationBarManager.getInstance().mKeyguardStateController;
                    if ((keyguardStateControllerImpl == null || !keyguardStateControllerImpl.mShowing) && !this.this$0.isAutoPlaySwitchOn()) {
                        this.this$0.mAwaitingDisableAliveStopAfterUnlock = true;
                        KgdLogUtil.i("PictorialMediator", "mark awaiting disableAlive stop after unlock");
                    }
                    AnonymousClass3 anonymousClass4 = this.this$0.mHandler;
                    PictorialMediator$9$$ExternalSyntheticLambda0 pictorialMediator$9$$ExternalSyntheticLambda1 = new PictorialMediator$9$$ExternalSyntheticLambda0(0);
                    pictorialMediator$9$$ExternalSyntheticLambda1.f$0 = this;
                    VarHandle.storeStoreFence();
                    anonymousClass4.post(pictorialMediator$9$$ExternalSyntheticLambda1);
                }
            } else if (!z && this.this$0.isMainSwitchOn()) {
                PictorialMediator pictorialMediator3 = this.this$0;
                if (!pictorialMediator3.mPictorialServiceConnected && !pictorialMediator3.mRegisteredPictorialImageObserver) {
                    KgdLogUtil.i("PictorialMediator", "check pictorial connect!");
                    PictorialMediator pictorialMediator4 = this.this$0;
                    pictorialMediator4.mHandler.removeCallbacks(pictorialMediator4.mReloadPictorialRunnable);
                    this.this$0.mReloadPictorialRunnable.run();
                }
            }
            if (z || ((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mShowing) {
                return;
            }
            PictorialMediator pictorialMediator5 = this.this$0;
            KgdLogUtil.i("PictorialMediator", "actionUnlock : caseState = 1, unlockType = 1");
            Bundle bundle = new Bundle();
            bundle.putInt("category", 10001);
            bundle.putString("eventname", "unlock");
            bundle.putInt("case", 1);
            bundle.putInt("type", 1);
            pictorialMediator5.reportStatisticData(bundle);
        }

        public final void onStartedGoingToSleep(int i) {
            String str;
            KgdLogUtil.d("PictorialMediator", "onStartedGoingToSleep why = " + i);
            PictorialMediator pictorialMediator = this.this$0;
            int i2 = pictorialMediator.mEntryPictorialSlidePage ? 2 : 1;
            if (i == 2) {
                str = "poweroff";
            } else {
                str = i == 3 ? "timeout" : null;
            }
            if (!TextUtils.isEmpty(str)) {
                StringBuilder sbM = FloatList$.ExternalSyntheticOutline0.m("actionSleep : why = ", i, i2, ", caseType = ", ", eventName = ");
                sbM.append(str);
                KgdLogUtil.d("PictorialMediator", sbM.toString());
                Bundle bundle = new Bundle();
                bundle.putInt("category", 10001);
                bundle.putString("eventname", str);
                bundle.putInt("case", i2);
                pictorialMediator.reportStatisticData(bundle);
            }
            pictorialMediator.mLeftSwipeSwitchOn = pictorialMediator.getLeftSwipeSwitchState();
            pictorialMediator.mRightSwipeSwitchOn = pictorialMediator.getRightSwipeSwitchState();
        }

        public final void onSuperPowerSaveChanged(boolean z) {
            int systemIntValue;
            int systemIntValue2;
            PictorialMediator.m4$$Nest$misWallpaperChange(this.this$0);
            PictorialMediator pictorialMediator = this.this$0;
            boolean z2 = SuperPowerSaveSettingsObserver.getInstance().isSuperPowerSaveState;
            Context context = pictorialMediator.mContext;
            if (z2) {
                systemIntValue = MultiUserContentHelper.getSystemIntValue(context, "oplus_customize_pictorial_apply", 0);
            } else {
                StringBuilder sb = PictorialUtils.sStringBuilder;
                systemIntValue = context.getSharedPreferences("sp_super_power_save", 0).getInt("oplus_customize_pictorial_apply", -1);
            }
            if (systemIntValue == 1) {
                if (SuperPowerSaveSettingsObserver.getInstance().isSuperPowerSaveState) {
                    pictorialMediator.closeMainSwtich();
                } else {
                    if (PictorialMediator.sPictorialFestivalWallpaperUsed.get()) {
                        pictorialMediator.mPictorialFactory.getPictorialLoader().setHasAlreadyFetchFromSlidePage(false);
                    }
                    Settings.System.putIntForUser(pictorialMediator.mContentResolver, "oplus_customize_pictorial_apply", 1, KeyguardUpdateMonitor.getCurrentUser());
                    pictorialMediator.mMainSwitchOn = true;
                }
                Context context2 = pictorialMediator.mContext;
                int i = SuperPowerSaveSettingsObserver.getInstance().isSuperPowerSaveState ? 1 : -1;
                StringBuilder sb2 = PictorialUtils.sStringBuilder;
                context2.getSharedPreferences("sp_super_power_save", 0).edit().putInt("oplus_customize_pictorial_apply", i).apply();
            } else {
                Iterator it = PictorialStateWatcher.getInstance().mObserver.iterator();
                while (it.hasNext()) {
                    ((PictorialStateWatcher.Observer) it.next()).onSuperPowerChanged();
                }
            }
            boolean z3 = SuperPowerSaveSettingsObserver.getInstance().isSuperPowerSaveState;
            Context context3 = pictorialMediator.mContext;
            if (z3) {
                systemIntValue2 = MultiUserContentHelper.getSystemIntValue(context3, "oplus_customize_pictorial_auto_play", 0);
            } else {
                StringBuilder sb3 = PictorialUtils.sStringBuilder;
                systemIntValue2 = context3.getSharedPreferences("sp_super_power_save", 0).getInt("oplus_customize_pictorial_auto_play", -1);
            }
            if (systemIntValue2 == 1) {
                if (SuperPowerSaveSettingsObserver.getInstance().isSuperPowerSaveState) {
                    pictorialMediator.closePictorialAutoSwitch();
                } else if (!pictorialMediator.mWallpaperChanged) {
                    pictorialMediator.enablePictorialAutoSwitch();
                }
                Context context4 = pictorialMediator.mContext;
                int i2 = SuperPowerSaveSettingsObserver.getInstance().isSuperPowerSaveState ? 1 : -1;
                StringBuilder sb4 = PictorialUtils.sStringBuilder;
                context4.getSharedPreferences("sp_super_power_save", 0).edit().putInt("oplus_customize_pictorial_auto_play", i2).apply();
            }
        }

        public final void onUserSwitchComplete(int i) {
            PictorialMediator pictorialMediator = this.this$0;
            KgdLogUtil.i("PictorialMediator", "onUserSwitchComplete userId = " + i);
            if (KeyguardUpdateMonitor.getCurrentUser() != 0) {
                return;
            }
            Trace.traceBegin(8L, "PictorialMediator#onUserSwitchComplete");
            if (pictorialMediator.mPictorialExist) {
                pictorialMediator.registerSwitchObserver();
            }
            pictorialMediator.registerPictorialObserver();
            pictorialMediator.registerAodStyleTypeObserver();
            pictorialMediator.firstProviderService(false);
            pictorialMediator.setDefaultSetting();
            Trace.traceEnd(8L);
        }

        public final void onUserSwitching(int i) {
            PictorialMediator pictorialMediator = this.this$0;
            StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(i, "onUserSwitching userId = ", ", KeyguardUpdateMonitor.getCurrentUser() = ");
            sbM.append(KeyguardUpdateMonitor.getCurrentUser());
            KgdLogUtil.i("PictorialMediator", sbM.toString());
            if (KeyguardUpdateMonitor.getCurrentUser() == 0) {
                AtomicBoolean atomicBoolean = PictorialMediator.sPictorialFestivalWallpaperUsed;
                pictorialMediator.initStatusWhenShowingLocked();
                return;
            }
            Trace.traceBegin(8L, "PictorialMediator#onUserSwitching");
            AtomicBoolean atomicBoolean2 = PictorialMediator.sPictorialFestivalWallpaperUsed;
            pictorialMediator.closePictorialSwitch();
            pictorialMediator.stopProviderService();
            pictorialMediator.unRegisterSwitchObserver();
            pictorialMediator.unRegisterPictorialObserver();
            pictorialMediator.unregisterAodStyleTypeObserver();
            Trace.traceEnd(8L);
        }

        public final void onUserUnlocked() {
            PictorialMediator pictorialMediator = this.this$0;
            AtomicBoolean atomicBoolean = PictorialMediator.sPictorialFestivalWallpaperUsed;
            pictorialMediator.updateSupportSwipeLeftStatus();
            this.this$0.initStatusWhenShowingLocked();
            if (!this.this$0.mKeyguardShowing) {
                KgdLogUtil.i("PictorialMediator", "onUserUnlocked: mKeyguardShowing is false, skip");
                return;
            }
            boolean zIsNeedDelayUnlock$1 = this.this$0.isNeedDelayUnlock$1();
            OplusLockIconView$.ExternalSyntheticOutline0.m("onUserUnlocked : isNeedDelayUnlock = ", "PictorialMediator", zIsNeedDelayUnlock$1);
            PictorialMediator pictorialMediator2 = this.this$0;
            pictorialMediator2.mHandler.removeCallbacks(pictorialMediator2.mReloadPictorialRunnable);
            PictorialMediator pictorialMediator3 = this.this$0;
            if (zIsNeedDelayUnlock$1) {
                pictorialMediator3.mHandler.postDelayed(pictorialMediator3.mReloadPictorialRunnable, 2000L);
            } else {
                pictorialMediator3.mReloadPictorialRunnable.run();
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface IPictorialMediatorCallback {
        default void onFingerPrintIconChanged() {
        }

        default void onMessageViewInfoChanged(PictorialMessageViewInfo pictorialMessageViewInfo) {
        }

        default void onPictorialGuideInfoChanged(PictorialGuideEntity pictorialGuideEntity) {
        }

        void onSwitchPictorialApp();

        default void onWallpaperChanged(List list, boolean z) {
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class StartPictorialServiceRunnable implements Runnable {
        public boolean mDeviceProvisioned;
        public /* synthetic */ PictorialMediator this$0;

        @Override // java.lang.Runnable
        public final void run() {
            Trace.traceBegin(8L, "StartPictorialServiceRunnable");
            boolean z = ((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).mDeviceProvisioned;
            if (!this.mDeviceProvisioned && !z) {
                KgdLogUtil.i("PictorialMediator", "StartPictorialServiceRunnable mDeviceProvisioned is false");
                return;
            }
            PictorialMediator pictorialMediator = this.this$0;
            if (pictorialMediator.mPictorialServiceConnected) {
                KgdLogUtil.i("PictorialMediator", "StartPictorialServiceRunnable already connect, return");
                return;
            }
            if (!pictorialMediator.mPictorialExist) {
                KgdLogUtil.i("PictorialMediator", "StartPictorialServiceRunnable mPictorialExist is not, return");
                return;
            }
            if (1 != pictorialMediator.getMainSwitchState()) {
                KeyguardFeatureOption.INSTANCE.getClass();
                if (!KeyguardFeatureOption.isSupportOplusGLPictorial) {
                    KgdLogUtil.i("PictorialMediator", "mStartPictorialServiceRunnable getMainSwitchState is off, return");
                    return;
                }
            }
            Intent intent = new Intent();
            intent.setAction(this.this$0.mPictorialAppDataImpl.getDecoupLingAction());
            intent.setPackage(this.this$0.mPictorialAppDataImpl.getPictorialAppPackage());
            try {
                KgdLogUtil.i("PictorialMediator", "mStartPictorialServiceRunnable start");
                PictorialMediator pictorialMediator2 = this.this$0;
                pictorialMediator2.mContext.bindService(intent, pictorialMediator2.mConnection, 1);
            } catch (Exception e) {
                KgdLogUtil.w("PictorialMediator", "mStartPictorialServiceRunnable exception " + e);
            }
            Trace.traceEnd(8L);
        }

        public void setDeviceProvisioned(boolean z) {
            this.mDeviceProvisioned = z;
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface TipsChangeWatcher {
    }

    public static void $r8$lambda$afokr_6l6CvzisMooRn8EX__67Q(PictorialMediator pictorialMediator, int i) {
        AnonymousClass3 anonymousClass3 = pictorialMediator.mHandler;
        Trace.traceBegin(8L, "obtainPictorialMessageViewInfo");
        if (i != KeyguardUpdateMonitor.getCurrentUser()) {
            StringBuilder sbM = ListPopupWindow$.ExternalSyntheticOutline0.m(i, "obtainPictorialMessageViewInfo not same userID in runnable, return, userId = ", ", KeyguardUpdateMonitor.getCurrentUser() = ");
            sbM.append(KeyguardUpdateMonitor.getCurrentUser());
            KgdLogUtil.i("PictorialMediator", sbM.toString());
            return;
        }
        String json = null;
        PictorialMessageViewInfo pictorialMessageViewInfo = pictorialMediator.mPictorialServiceConnected ? pictorialMediator.getPictorialMessageViewInfo() : null;
        if (pictorialMessageViewInfo != null) {
            PictorialMessageViewInfo pictorialMessageViewInfo2 = new PictorialMessageViewInfo();
            pictorialMessageViewInfo2.setEntryShow(pictorialMessageViewInfo.mEntryShow);
            pictorialMessageViewInfo2.setEntryDrawable(pictorialMessageViewInfo.getEntryDrawable());
            pictorialMessageViewInfo2.setEntryDrawableRes(pictorialMessageViewInfo.getEntryDrawableRes());
            SharedPreferences sharedPreferences = pictorialMediator.mContext.getSharedPreferences("pictorial_info", 0);
            try {
                json = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy[]{new PictorialMessageViewInfo.SkipStrategy()}).create().toJson(pictorialMessageViewInfo2, PictorialMessageViewInfo.class);
            } catch (Exception e) {
                KgdLogUtil.i("PictorialMediator", "saveViewStateInfoToSP exception " + e.getMessage());
            }
            if (!TextUtils.isEmpty(json)) {
                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                editorEdit.putString("view_state_info", json);
                editorEdit.apply();
            }
        } else {
            pictorialMessageViewInfo = pictorialMediator.getViewStateInfoFromSP();
        }
        if (pictorialMessageViewInfo == null) {
            KgdLogUtil.i("PictorialMediator", "obtainPictorialMessageViewInfo viewInfo is null, return");
            return;
        }
        Message messageObtainMessage = anonymousClass3.obtainMessage(2);
        messageObtainMessage.obj = pictorialMessageViewInfo;
        messageObtainMessage.arg1 = i;
        anonymousClass3.sendMessage(messageObtainMessage);
        Trace.traceEnd(8L);
    }

    /* JADX INFO: renamed from: -$$Nest$mhandleUnAutoPlayTips, reason: not valid java name */
    public static void m3$$Nest$mhandleUnAutoPlayTips(PictorialMediator pictorialMediator, List list) {
        PictorialWallpaperInfo pictorialWallpaperInfo;
        OplusKeyguardTipsViewController.tipsChangeWatcher.1 r1;
        if (pictorialMediator.getAutoPlaySwitchState() != 0 || list == null || list.isEmpty() || (pictorialWallpaperInfo = (PictorialWallpaperInfo) list.getFirst()) == null || (r1 = pictorialMediator.mTipsChangeWatcher) == null) {
            return;
        }
        r1.onTipsChange(pictorialWallpaperInfo);
    }

    /* JADX INFO: renamed from: -$$Nest$misWallpaperChange, reason: not valid java name */
    public static void m4$$Nest$misWallpaperChange(PictorialMediator pictorialMediator) {
        StringBuilder sb = new StringBuilder("superPowerSave state changed, old state = ");
        sb.append(pictorialMediator.mIsSuperPowerSave);
        sb.append(" new state = ");
        OplusChargeAnimController$.ExternalSyntheticOutline0.m("PictorialMediator", sb, SuperPowerSaveSettingsObserver.getInstance().isSuperPowerSaveState);
        if (pictorialMediator.mIsSuperPowerSave != SuperPowerSaveSettingsObserver.getInstance().isSuperPowerSaveState) {
            pictorialMediator.mIsSuperPowerSave = SuperPowerSaveSettingsObserver.getInstance().isSuperPowerSaveState;
            int wallpaperId = pictorialMediator.getWallpaperId();
            if (pictorialMediator.mIsSuperPowerSave) {
                pictorialMediator.mWallpaperId = wallpaperId;
                pictorialMediator.mWallpaperChanged = false;
            } else if (wallpaperId != pictorialMediator.mWallpaperId) {
                pictorialMediator.mWallpaperChanged = true;
            } else {
                pictorialMediator.mWallpaperChanged = false;
            }
        }
    }

    /* JADX INFO: renamed from: -$$Nest$monChangeAutoSwitchState, reason: not valid java name */
    public static void m5$$Nest$monChangeAutoSwitchState(PictorialMediator pictorialMediator) {
        int autoPlaySwitchState = pictorialMediator.getAutoPlaySwitchState();
        pictorialMediator.mAutoPlaySwitchKeyState = autoPlaySwitchState;
        boolean z = false;
        if (autoPlaySwitchState != 1) {
            SharedPreferences.Editor editorEdit = pictorialMediator.mContext.getSharedPreferences("rm_pictorial_info", 0).edit();
            editorEdit.remove("rm_pictorial_open_auto_play_by_itself");
            editorEdit.apply();
            KgdLogUtil.i("PictorialMediator", "onChangeAutoSwitchState rm pictorial self set auto play switch close");
        }
        pictorialMediator.mMainSwitchKeyState = pictorialMediator.getMainSwitchState();
        pictorialMediator.mEnablePlaySwitchKeyState = pictorialMediator.getEnablePlaySwitchState();
        boolean zIsDefaultKeyguardInSettings = KeyguardUtils.Companion.isDefaultKeyguardInSettings(pictorialMediator.mContext);
        StringBuilder sb = new StringBuilder("Pictorial auto play switch change, autoPlaySwitchState = ");
        sb.append(pictorialMediator.mAutoPlaySwitchKeyState == 1);
        sb.append(", mainSwitchState = ");
        UnlockViewLifecycleOwner$.ExternalSyntheticOutline0.m(", isDefaultKeyguard = ", "PictorialMediator", sb, pictorialMediator.mMainSwitchKeyState == 1, zIsDefaultKeyguardInSettings);
        pictorialMediator.mMainSwitchOn = pictorialMediator.mMainSwitchKeyState == 1;
        pictorialMediator.mAutoPlaySwitchOn = zIsDefaultKeyguardInSettings && pictorialMediator.mPictorialMediatorHelper.calculateAutoPlayState(pictorialMediator.getPictorialSwitchParams());
        KeyguardFeatureOption.INSTANCE.getClass();
        if (KeyguardFeatureOption.isSupportOplusGLPictorial) {
            if (pictorialMediator.mAutoPlaySwitchKeyState == 1 && ((pictorialMediator.mEnablePlaySwitchKeyState == 1 || pictorialMediator.mMainSwitchKeyState == 1) && zIsDefaultKeyguardInSettings)) {
                z = true;
            }
            pictorialMediator.mAutoPlaySwitchOn = z;
        }
        if (pictorialMediator.mAutoPlaySwitchOn) {
            KgdLogUtil.d("PictorialMediator", "onChangeAutoSwitchState releaseDrawableAndInfo");
            pictorialMediator.mPictorialFactory.getPictorialLoader().releaseDrawableAndInfo();
            pictorialMediator.mPictorialFactory.getPictorialLoader().clearWallpapers();
            pictorialMediator.obtainPictorialMessageViewInfo();
            if (pictorialMediator.isGlanceStyle() || pictorialMediator.isOplusGLStyle()) {
                pictorialMediator.updatePictorialGuideEntryInfo();
            }
        }
        PictorialStateWatcher.getInstance().dispatchPictorialAutoSwitchChange(pictorialMediator.isAutoPlaySwitchOn());
        pictorialMediator.ensurePictorialWallpaperForPanoramicAod("onChangeAutoSwitchState");
        if (pictorialMediator.isOplusGLStyle()) {
            return;
        }
        PictorialMediator$$ExternalSyntheticLambda1 pictorialMediator$$ExternalSyntheticLambda1 = new PictorialMediator$$ExternalSyntheticLambda1(4);
        pictorialMediator$$ExternalSyntheticLambda1.f$0 = pictorialMediator;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnWorkThread(pictorialMediator$$ExternalSyntheticLambda1);
    }

    /* JADX WARN: Code duplicated, block: B:30:0x00c2 A[LOOP:0: B:28:0x00bc->B:30:0x00c2, LOOP_END] */
    /* JADX INFO: renamed from: -$$Nest$monChangeEnableSwitchState, reason: not valid java name */
    public static void m6$$Nest$monChangeEnableSwitchState(PictorialMediator pictorialMediator) {
        PictorialMediator pictorialMediator2;
        boolean z;
        Iterator it;
        pictorialMediator.mEnablePlaySwitchKeyState = pictorialMediator.getEnablePlaySwitchState();
        pictorialMediator.mMainSwitchKeyState = pictorialMediator.getMainSwitchState();
        pictorialMediator.mAutoPlaySwitchKeyState = pictorialMediator.getAutoPlaySwitchState();
        boolean zIsDefaultKeyguardInSettings = KeyguardUtils.Companion.isDefaultKeyguardInSettings(pictorialMediator.mContext);
        boolean z2 = false;
        pictorialMediator.mMainSwitchOn = pictorialMediator.mMainSwitchKeyState == 1;
        pictorialMediator.mAutoPlaySwitchOn = pictorialMediator.mAutoPlaySwitchKeyState == 1;
        if (pictorialMediator.mEnablePlaySwitchKeyState == 1 && zIsDefaultKeyguardInSettings) {
            z2 = true;
        }
        pictorialMediator.mEnablePlaySwitchOn = z2;
        pictorialMediator.mLeftSwipeSwitchOn = pictorialMediator.getLeftSwipeSwitchState();
        pictorialMediator.mRightSwipeSwitchOn = pictorialMediator.getRightSwipeSwitchState();
        StringBuilder sb = new StringBuilder("Pictorial switch change Enable, mEnablePlaySwitchOn = ");
        sb.append(pictorialMediator.mEnablePlaySwitchOn);
        sb.append(", autoPlaySwitchState = ");
        sb.append(pictorialMediator.mAutoPlaySwitchOn);
        sb.append(", mLeftSwipeSwitchOn = ");
        sb.append(pictorialMediator.mLeftSwipeSwitchOn);
        sb.append(", mRightSwipeSwitchOn = ");
        OplusChargeAnimController$.ExternalSyntheticOutline0.m("PictorialMediator", sb, pictorialMediator.mRightSwipeSwitchOn);
        pictorialMediator.mPictorialMediatorHelper.onChangeEnableSwitchState(pictorialMediator.mContext, pictorialMediator.getPictorialSwitchParams());
        if (pictorialMediator.mEnablePlaySwitchOn) {
            if (pictorialMediator.mPictorialServiceConnected) {
                pictorialMediator2 = pictorialMediator;
                pictorialMediator2.obtainPictorialWallpaper("auto_play_enable", null, true, true, false);
                if (pictorialMediator2.isGlanceStyle() || pictorialMediator2.isHKStyle() || pictorialMediator2.isOplusGLStyle()) {
                    pictorialMediator2.updatePictorialGuideEntryInfo();
                }
            }
            pictorialMediator2.updatePictorialGuideEntryInfo();
            PictorialStateWatcher pictorialStateWatcher = PictorialStateWatcher.getInstance();
            z = pictorialMediator2.mEnablePlaySwitchOn;
            it = pictorialStateWatcher.mObserver.iterator();
            while (it.hasNext()) {
                ((PictorialStateWatcher.Observer) it.next()).onPictorialEnableSwitchChange(z);
            }
        }
        PictorialStateWatcher.getInstance().dispatchPictorialAutoSwitchChange(pictorialMediator.isAutoPlaySwitchOn());
        pictorialMediator2 = pictorialMediator;
        pictorialMediator2.updatePictorialGuideEntryInfo();
        PictorialStateWatcher pictorialStateWatcher2 = PictorialStateWatcher.getInstance();
        z = pictorialMediator2.mEnablePlaySwitchOn;
        it = pictorialStateWatcher2.mObserver.iterator();
        while (it.hasNext()) {
            ((PictorialStateWatcher.Observer) it.next()).onPictorialEnableSwitchChange(z);
        }
    }

    /* JADX INFO: renamed from: -$$Nest$monChangeMainSwitchState, reason: not valid java name */
    public static void m7$$Nest$monChangeMainSwitchState(PictorialMediator pictorialMediator) {
        pictorialMediator.mMainSwitchKeyState = pictorialMediator.getMainSwitchState();
        pictorialMediator.mAutoPlaySwitchKeyState = pictorialMediator.getAutoPlaySwitchState();
        int notShowAgainSwitchState = pictorialMediator.getNotShowAgainSwitchState();
        StringBuilder sb = new StringBuilder("Pictorial switch change, mainSwitchState = ");
        sb.append(pictorialMediator.mMainSwitchKeyState == 1);
        sb.append(", autoPlaySwitchState = ");
        sb.append(pictorialMediator.mAutoPlaySwitchKeyState == 1);
        sb.append(", notShowAgainSwitchState = ");
        sb.append(notShowAgainSwitchState == 1);
        sb.append(", isLightOsForLH = ");
        FeatureOption.INSTANCE.getClass();
        sb.append(FeatureOption.isLightOsForLH());
        KgdLogUtil.i("PictorialMediator", sb.toString());
        pictorialMediator.mMainSwitchOn = pictorialMediator.mMainSwitchKeyState == 1;
        pictorialMediator.mAutoPlaySwitchOn = pictorialMediator.mAutoPlaySwitchKeyState == 1;
        pictorialMediator.mNotShowAgainSwitchOn = notShowAgainSwitchState;
        pictorialMediator.mLeftSwipeSwitchOn = pictorialMediator.getLeftSwipeSwitchState();
        pictorialMediator.mRightSwipeSwitchOn = pictorialMediator.getRightSwipeSwitchState();
        pictorialMediator.mPictorialMediatorHelper.onChangeMainSwitchState(pictorialMediator.mContext, pictorialMediator.getPictorialSwitchParams());
        KeyguardFeatureOption.INSTANCE.getClass();
        if (KeyguardFeatureOption.isSupportOplusGLPictorial && KeyguardFeatureOption.isKeepHeyTapPictorial && KeyguardUtils.Companion.isDefaultKeyguardInSettings(pictorialMediator.mContext) && pictorialMediator.mPictorialAppDataImpl.getDefPictorialAppInfo().getEnable() && pictorialMediator.mPictorialAppDataImpl.getOplusGLPictorialAppInfo().getEnable()) {
            PictorialAppDataImpl pictorialAppDataImpl = pictorialMediator.mPictorialAppDataImpl;
            boolean z = pictorialMediator.mMainSwitchOn;
            boolean z2 = pictorialMediator.mEnablePlaySwitchOn;
            PictorialAppInfo pictorialAppInfo = pictorialAppDataImpl.mDefaultPictorialAppInfo;
            PictorialAppInfo pictorialAppInfo2 = pictorialAppDataImpl.mCurrentPictorialAppInfo;
            OlockStateUtil$.ExternalSyntheticOutline0.m(" updateNextPictorialApp mainSwitchOn:", ",enablePlaySwitchOn:", "PictorialAppData", z, z2);
            pictorialAppDataImpl.mNextPictorialAppInfo = null;
            PictorialAppInfo pictorialAppInfo3 = pictorialAppDataImpl.mOplusGLPictorialAppInfo;
            if (pictorialAppInfo3 != null && ((!z || z2) && !PictorialAppDataImpl.isSamePackage(pictorialAppInfo3, pictorialAppInfo2))) {
                KgdLogUtil.i("PictorialAppData", " updateNextPictorialApp oplusGLPictorialAppInfo");
                pictorialAppDataImpl.mNextPictorialAppInfo = pictorialAppInfo3;
                return;
            } else if (pictorialAppInfo == null || !z || PictorialAppDataImpl.isSamePackage(pictorialAppInfo, pictorialAppInfo2)) {
                KgdLogUtil.i("PictorialAppData", " updateNextPictorialApp nothing");
                return;
            } else {
                pictorialAppDataImpl.mNextPictorialAppInfo = pictorialAppInfo;
                KgdLogUtil.i("PictorialAppData", " updateNextPictorialApp defaultPictorialAppInfo");
                return;
            }
        }
        if (!KeyguardFeatureOption.isSupportOplusGLPictorial || KeyguardFeatureOption.isKeepHeyTapPictorial || pictorialMediator.mPictorialAppDataImpl.getNextPictorialAppInfo() == null || pictorialMediator.mMainSwitchOn) {
            if (pictorialMediator.mMainSwitchOn) {
                pictorialMediator.registerPictorialObserver();
                pictorialMediator.firstProviderService(false);
                pictorialMediator.obtainPictorialMessageViewInfo();
                if (pictorialMediator.isGlanceStyle() || pictorialMediator.isTbStyle()) {
                    pictorialMediator.updatePictorialGuideEntryInfo();
                }
                if (pictorialMediator.isFlavorOneExpLH() && !pictorialMediator.mLeftSwipeSwitchOn && !OplusMultiAppManager.getInstance().isMultiAppUserId(pictorialMediator.mContext.getUserId())) {
                    Settings.System.putIntForUser(pictorialMediator.mContentResolver, "swipe_left_display_content", 1, KeyguardUpdateMonitor.getCurrentUser());
                    pictorialMediator.mLeftSwipeSwitchOn = true;
                }
            } else {
                pictorialMediator.unRegisterPictorialObserver();
                pictorialMediator.stopProviderService();
            }
            if (pictorialMediator.isMainSwitchOffAndCanLeftSwipe() || pictorialMediator.isFlavorOneExpLH()) {
                pictorialMediator.onPictorialActivateState();
            }
            PictorialStateWatcher pictorialStateWatcher = PictorialStateWatcher.getInstance();
            boolean zIsMainSwitchOn = pictorialMediator.isMainSwitchOn();
            Iterator it = pictorialStateWatcher.mObserver.iterator();
            while (it.hasNext()) {
                ((PictorialStateWatcher.Observer) it.next()).onPictorialMainSwitchChange(zIsMainSwitchOn);
            }
        }
    }

    /* JADX INFO: renamed from: -$$Nest$monChangeTaglineSwitchState, reason: not valid java name */
    public static void m8$$Nest$monChangeTaglineSwitchState(PictorialMediator pictorialMediator) {
        pictorialMediator.getClass();
        KeyguardFeatureOption.INSTANCE.getClass();
        if (KeyguardFeatureOption.isSupportOtherPictorial || pictorialMediator.isOplusGLStyle() || pictorialMediator.isFlavorOneExpLH()) {
            int taglineSwitchState = pictorialMediator.getTaglineSwitchState();
            pictorialMediator.mTaglineSwitchOn = taglineSwitchState == 1;
            OplusLockIconView$.ExternalSyntheticOutline0.m(taglineSwitchState, "enablePictorialTaglineSwitch:", "PictorialMediator");
            ((ArrayList) pictorialMediator.mCallbacks).forEach(new PictorialMediator$$ExternalSyntheticLambda11(1));
        }
    }

    /* JADX INFO: renamed from: -$$Nest$mprintPictorialLoaderEnableParam, reason: not valid java name */
    public static void m9$$Nest$mprintPictorialLoaderEnableParam(PictorialMediator pictorialMediator, String str) {
        StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(str, ", isPictorialExist : ");
        sbM.append(pictorialMediator.mPictorialExist);
        sbM.append(", isMainSwitchOn : ");
        sbM.append(pictorialMediator.isMainSwitchOn());
        sbM.append(", isAutoPlaySwitchOn : ");
        sbM.append(pictorialMediator.isAutoPlaySwitchOn());
        sbM.append(", isUserUnlocked : ");
        sbM.append(isUserUnlocked$1());
        sbM.append(", isDeviceProvisioned : ");
        sbM.append(pictorialMediator.isDeviceProvisioned());
        sbM.append(", isDefaultKeyguard : ");
        sbM.append(KeyguardUtils.Companion.isDefaultKeyguardInSettings(pictorialMediator.mContext));
        sbM.append(", getCurrentUser : ");
        sbM.append(KeyguardUpdateMonitor.getCurrentUser() == 0);
        sbM.append(", needDisablePictorial : ");
        sbM.append(KeyguardCustomizeManager.Companion.getInstance().needDisablePictorial());
        KgdLogUtil.i("PictorialMediator", sbM.toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v3, types: [com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator$24] */
    /* JADX WARN: Type inference failed for: r5v1, types: [android.os.Handler, com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator$3] */
    public PictorialMediator(Context context, ScreenLifecycle screenLifecycle, ConfigurationController configurationController, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4) {
        MutableLiveData mutableLiveData = new MutableLiveData(Boolean.FALSE);
        this.mWaitingPictorialData = mutableLiveData;
        final int i = 0;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(i);
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mWaitingPictorialOvertimeRunnable = anonymousClass1;
        final int i2 = 1;
        AnonymousClass1 anonymousClass2 = new AnonymousClass1(i2);
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        this.mStopPictorialServiceRunnable = anonymousClass2;
        this.mIsProcessStartWhenReboot = false;
        this.mSkipClosePictorialFestivalWallpaper = new AtomicBoolean(false);
        StartPictorialServiceRunnable startPictorialServiceRunnable = new StartPictorialServiceRunnable();
        startPictorialServiceRunnable.this$0 = this;
        VarHandle.storeStoreFence();
        this.mStartPictorialServiceRunnable = startPictorialServiceRunnable;
        this.mCallbacks = new ArrayList();
        this.mMainSwitchOn = false;
        this.mAutoPlaySwitchOn = false;
        this.mEnablePlaySwitchOn = false;
        this.mTaglineSwitchOn = false;
        this.mIsSupportPictorialFestivalWallpaper = false;
        this.mIsPictorialFestivalWallpaperReady = false;
        this.mNeedReleaseForFestivalPaod = false;
        this.mNotShowAgainSwitchOn = -1;
        this.mHasTriggerActiveNotShowUpdate = false;
        this.mIsLastDrawBitmapFromPictorial = false;
        this.mInfoCallbackRegistered = false;
        this.mLeftSwipeSwitchOn = false;
        this.mRightSwipeSwitchOn = false;
        this.mPictorialEnabled = true;
        this.mIsUiEngineSwipe = false;
        this.mCanConnectOnLightOs = false;
        this.mIsValidNotShowEvent = false;
        this.mPictorialGuideUserObserver = null;
        this.mPictorialAppSwitching = false;
        this.mHasPictorialSpace = false;
        this.mIsLockScreenAttached = false;
        this.mIsSupportDisableAlive = false;
        this.mDisableAliveSettingsObserverRegistered = false;
        this.mAodStyleTypeObserverRegistered = false;
        this.mAwaitingDisableAliveStopAfterUnlock = false;
        this.mIsSupportLeftSwipe = false;
        this.mIsMetaDataSupportRightSwipe = false;
        this.mIsNeedUpdateView = false;
        this.mLastWallpaperID = 0;
        this.mMainSwitchKeyState = -1;
        this.mAutoPlaySwitchKeyState = -1;
        this.mEnablePlaySwitchKeyState = -1;
        this.mLeftSwipeSwitchKeyState = -1;
        this.mRightSwipeSwitchKeyState = -1;
        this.mFirstGetPictorialDefaultSetting = true;
        this.mCanUpdateWelcomePreloadWallpaper = false;
        ?? r5 = new Handler(Looper.getMainLooper()) { // from class: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator.3
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                int i3 = message.what;
                PictorialMediator pictorialMediator = PictorialMediator.this;
                if (i3 == 1) {
                    if (KeyguardUpdateMonitor.getCurrentUser() != message.arg1) {
                        StringBuilder sb = new StringBuilder("MSG_OBTAIN_PICTORIAL_WALLPAPER KeyguardUpdateMonitor.getCurrentUser() = ");
                        sb.append(KeyguardUpdateMonitor.getCurrentUser());
                        sb.append(", fetchUserID = ");
                        OplusKeyguardCtrl$.ExternalSyntheticOutline0.m(message.arg1, "PictorialMediator", sb);
                        return;
                    }
                    boolean zBooleanValue = ((Boolean) ((Pair) message.obj).first).booleanValue();
                    boolean z = ((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mShowing || SmoothTransitionController.isPanoramicAodEnable(pictorialMediator.mContext);
                    if (!zBooleanValue && !z) {
                        KgdLogUtil.i("PictorialMediator", "MSG_OBTAIN_PICTORIAL_WALLPAPER isShowing false,return");
                        return;
                    }
                    List list = (List) ((Pair) message.obj).second;
                    boolean z2 = message.arg2 == 1;
                    List list2 = pictorialMediator.mCallbacks;
                    PictorialMediator$3$$ExternalSyntheticLambda0 pictorialMediator$3$$ExternalSyntheticLambda0 = new PictorialMediator$3$$ExternalSyntheticLambda0();
                    pictorialMediator$3$$ExternalSyntheticLambda0.f$0 = list;
                    pictorialMediator$3$$ExternalSyntheticLambda0.f$1 = z2;
                    VarHandle.storeStoreFence();
                    ((ArrayList) list2).forEach(pictorialMediator$3$$ExternalSyntheticLambda0);
                    PictorialMediator.m3$$Nest$mhandleUnAutoPlayTips(pictorialMediator, list);
                    return;
                }
                if (i3 == 2) {
                    if (KeyguardUpdateMonitor.getCurrentUser() != message.arg1) {
                        StringBuilder sb2 = new StringBuilder("MSG_OBTAIN_PICTORIAL_MESSAGE_INFO KeyguardUpdateMonitor.getCurrentUser() = ");
                        sb2.append(KeyguardUpdateMonitor.getCurrentUser());
                        sb2.append(", fetchUserID = ");
                        OplusKeyguardCtrl$.ExternalSyntheticOutline0.m(message.arg1, "PictorialMediator", sb2);
                        return;
                    }
                    PictorialMessageViewInfo pictorialMessageViewInfo = (PictorialMessageViewInfo) message.obj;
                    List list3 = pictorialMediator.mCallbacks;
                    PictorialMediator$3$$ExternalSyntheticLambda1 pictorialMediator$3$$ExternalSyntheticLambda1 = new PictorialMediator$3$$ExternalSyntheticLambda1(0);
                    pictorialMediator$3$$ExternalSyntheticLambda1.f$0 = pictorialMessageViewInfo;
                    VarHandle.storeStoreFence();
                    ((ArrayList) list3).forEach(pictorialMediator$3$$ExternalSyntheticLambda1);
                    return;
                }
                if (i3 == 4) {
                    if (KeyguardUpdateMonitor.getCurrentUser() != message.arg1) {
                        StringBuilder sb3 = new StringBuilder("MSG_UPDATE_PICTORIAL_GUIDE_INFO KeyguardUpdateMonitor.getCurrentUser() = ");
                        sb3.append(KeyguardUpdateMonitor.getCurrentUser());
                        sb3.append(", fetchUserID = ");
                        OplusKeyguardCtrl$.ExternalSyntheticOutline0.m(message.arg1, "PictorialMediator", sb3);
                        return;
                    }
                    PictorialGuideEntity pictorialGuideEntity = (PictorialGuideEntity) message.obj;
                    List list4 = pictorialMediator.mCallbacks;
                    PictorialMediator$3$$ExternalSyntheticLambda1 pictorialMediator$3$$ExternalSyntheticLambda2 = new PictorialMediator$3$$ExternalSyntheticLambda1(1);
                    pictorialMediator$3$$ExternalSyntheticLambda2.f$0 = pictorialGuideEntity;
                    VarHandle.storeStoreFence();
                    ((ArrayList) list4).forEach(pictorialMediator$3$$ExternalSyntheticLambda2);
                    return;
                }
                if (i3 == 5) {
                    if (PictorialMediator.isExpFlavorOne() || pictorialMediator.isOplusGLStyle()) {
                        ((ArrayList) pictorialMediator.mCallbacks).forEach(new PictorialMediator$$ExternalSyntheticLambda11(2));
                        return;
                    }
                    return;
                }
                if (i3 != 6) {
                    StringBuilder sb4 = new StringBuilder("DEFASULT KeyguardUpdateMonitor.getCurrentUser() = ");
                    sb4.append(KeyguardUpdateMonitor.getCurrentUser());
                    sb4.append(", fetchUserID = ");
                    OplusKeyguardCtrl$.ExternalSyntheticOutline0.m(message.arg1, "PictorialMediator", sb4);
                    return;
                }
                if (pictorialMediator.mPictorialAppDataImpl.trySwitchPictorialApp()) {
                    pictorialMediator.stopProviderService();
                    pictorialMediator.unRegisterSwitchObserver();
                    pictorialMediator.unRegisterPictorialObserver();
                    pictorialMediator.unregisterAodStyleTypeObserver();
                    pictorialMediator.setPictorialAppSwitching(true);
                    pictorialMediator.restartPictorialWallpaper();
                }
            }
        };
        this.mHandler = r5;
        this.mPictorialActivateObServer = new AnonymousClass4(this, new Handler(), i);
        this.mHongshuSpecialLightEnableObServer = new AnonymousClass4(this, new Handler(), 13);
        this.mOtherPictorialAvailableSwitchObserver = new AnonymousClass4(this, new Handler(Looper.getMainLooper()), 14);
        this.mPictorialTaglineSwitchObserver = new AnonymousClass4(this, new Handler(), 15);
        this.mUnlockChangePkgObserver = new AnonymousClass4(this, new Handler(), 16);
        int i3 = 7;
        PictorialMediator$$ExternalSyntheticLambda1 pictorialMediator$$ExternalSyntheticLambda1 = new PictorialMediator$$ExternalSyntheticLambda1(i3);
        pictorialMediator$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.mReloadPictorialRunnable = pictorialMediator$$ExternalSyntheticLambda1;
        AnonymousClass9 anonymousClass9 = new AnonymousClass9();
        anonymousClass9.this$0 = this;
        VarHandle.storeStoreFence();
        this.mInfoCallback = anonymousClass9;
        AnonymousClass10 anonymousClass10 = new AnonymousClass10();
        anonymousClass10.this$0 = this;
        VarHandle.storeStoreFence();
        this.mPictorialImageObserver = new AnonymousClass4(this, new Handler(), i2);
        this.mPictorialMessageViewObserver = new AnonymousClass4(this, new Handler(), 2);
        this.mBackToKeyguardObserver = new AnonymousClass4(this, new Handler(), 3);
        this.mMainSwitchObserver = new AnonymousClass4(this, new Handler(), 4);
        this.mAutoPlaySwitchObserver = new AnonymousClass4(this, new Handler(), 5);
        this.mEnablePlaySwitchObserver = new AnonymousClass4(this, new Handler(Looper.getMainLooper()), 6);
        this.mPictorialFestivalWallpaperObserver = new AnonymousClass4(this, new Handler(), i3);
        this.mLeftSwipeSwitchObserver = new AnonymousClass4(this, new Handler(), 8);
        PictorialMediator$$ExternalSyntheticLambda3 pictorialMediator$$ExternalSyntheticLambda3 = new PictorialMediator$$ExternalSyntheticLambda3(i);
        pictorialMediator$$ExternalSyntheticLambda3.f$0 = this;
        VarHandle.storeStoreFence();
        this.mAodStyleTypeListener = pictorialMediator$$ExternalSyntheticLambda3;
        this.mRightSwipeSwitchObserver = new AnonymousClass4(this, new Handler(), 9);
        this.mKgdPictorialRecommendationObserver = new AnonymousClass4(this, new Handler(), 10);
        AnonymousClass21 anonymousClass21 = new AnonymousClass21();
        anonymousClass21.this$0 = this;
        VarHandle.storeStoreFence();
        AnonymousClass22 anonymousClass22 = new AnonymousClass22();
        anonymousClass22.this$0 = this;
        anonymousClass22.mRestartPictorial = false;
        VarHandle.storeStoreFence();
        AnonymousClass23 anonymousClass23 = new AnonymousClass23();
        anonymousClass23.this$0 = this;
        VarHandle.storeStoreFence();
        this.mConnection = anonymousClass23;
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver(this) { // from class: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator.24
            public final /* synthetic */ PictorialMediator this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                switch (i) {
                    case 0:
                        String action = intent.getAction();
                        KgdLogUtil.d("PictorialMediator", "onReceive: action= " + action);
                        Uri data = intent.getData();
                        if (data != null && "com.heytap.pictorial".equals(data.getSchemeSpecificPart()) && !TextUtils.isEmpty(action)) {
                            if ("android.intent.action.PACKAGE_CHANGED".equals(action) || "android.intent.action.PACKAGE_REPLACED".equals(action)) {
                                PictorialMediator pictorialMediator = this.this$0;
                                AtomicBoolean atomicBoolean = PictorialMediator.sPictorialFestivalWallpaperUsed;
                                pictorialMediator.hasPictorialSpaceActivity();
                            }
                            break;
                        }
                        break;
                    default:
                        Uri data2 = intent.getData();
                        if (data2 != null && TextUtils.equals(data2.getSchemeSpecificPart(), this.this$0.getPictorialPackageName())) {
                            Bundle extras = intent.getExtras();
                            int i4 = 0;
                            boolean z = extras != null ? extras.getBoolean("android.intent.extra.REPLACING", false) : false;
                            KgdLogUtil.d("PictorialMediator", "onReceive action:" + intent.getAction() + " replace:" + z);
                            String action2 = intent.getAction();
                            action2.hashCode();
                            switch (action2) {
                                case "android.intent.action.PACKAGE_REPLACED":
                                    PictorialMediator pictorialMediator2 = this.this$0;
                                    pictorialMediator2.updateMetaData();
                                    pictorialMediator2.updateSupportSwipeLeftStatus();
                                    PictorialAppDataImpl pictorialAppDataImpl = pictorialMediator2.mPictorialAppDataImpl;
                                    if (pictorialAppDataImpl != null) {
                                        KeyguardFeatureOption.INSTANCE.getClass();
                                        if (KeyguardFeatureOption.isSupportOtherPictorial) {
                                            PictorialAppDataImpl$$ExternalSyntheticLambda0 pictorialAppDataImpl$$ExternalSyntheticLambda0 = new PictorialAppDataImpl$$ExternalSyntheticLambda0(i4);
                                            pictorialAppDataImpl$$ExternalSyntheticLambda0.f$0 = pictorialAppDataImpl;
                                            VarHandle.storeStoreFence();
                                            PictorialUtils.runOnWorkThread(pictorialAppDataImpl$$ExternalSyntheticLambda0);
                                        }
                                    }
                                    if (pictorialMediator2.isMainSwitchOffAndCanLeftSwipe()) {
                                        pictorialMediator2.onPictorialActivateState();
                                    }
                                    if (pictorialMediator2.isFlavorOneExpLH()) {
                                        pictorialMediator2.mPictorialFactory.checkIsSupportSwipeLeft(pictorialMediator2.mPackageManager, pictorialMediator2.getPictorialAppData().getPictorialAppPackage(), pictorialMediator2.getPictorialAppData().getPictorialActivateClassName());
                                        pictorialMediator2.onPictorialActivateState();
                                    }
                                    Iterator it = PictorialStateWatcher.getInstance().mObserver.iterator();
                                    while (it.hasNext()) {
                                        ((PictorialStateWatcher.Observer) it.next()).onPictorialAppReplace();
                                    }
                                    break;
                                case "android.intent.action.PACKAGE_CHANGED":
                                    KgdLogUtil.d("PictorialMediator", "pictorial package change action");
                                    PictorialMediator pictorialMediator3 = this.this$0;
                                    if (pictorialMediator3.isAppEnabledChanged()) {
                                        if (!pictorialMediator3.isMainSwitchOn()) {
                                            Iterator it2 = PictorialStateWatcher.getInstance().mObserver.iterator();
                                            while (it2.hasNext()) {
                                                ((PictorialStateWatcher.Observer) it2.next()).onPictorialAppEnableChanged();
                                            }
                                        }
                                        if (!pictorialMediator3.mPictorialEnabled) {
                                            pictorialMediator3.closePictorialSwitch();
                                        } else {
                                            pictorialMediator3.enablePictorialSwitch();
                                        }
                                        break;
                                    }
                                    break;
                                case "android.intent.action.PACKAGE_REMOVED":
                                    if (this.this$0.isLHStyle()) {
                                        if (!z) {
                                            PictorialMediator pictorialMediator4 = this.this$0;
                                            pictorialMediator4.mPictorialEnabled = false;
                                            pictorialMediator4.mPictorialExist = false;
                                            pictorialMediator4.closePictorialSwitch();
                                            pictorialMediator4.setNotShowAgainSwitchState(true);
                                            pictorialMediator4.unRegisterSwitchObserver();
                                            pictorialMediator4.unRegisterPictorialObserver();
                                            pictorialMediator4.unregisterAodStyleTypeObserver();
                                            pictorialMediator4.setIsSupportLeftSwipe(false);
                                            Iterator it3 = PictorialStateWatcher.getInstance().mObserver.iterator();
                                            while (it3.hasNext()) {
                                                ((PictorialStateWatcher.Observer) it3.next()).onPictorialMainSwitchChange(false);
                                            }
                                            Iterator it4 = PictorialStateWatcher.getInstance().mObserver.iterator();
                                            while (it4.hasNext()) {
                                                ((PictorialStateWatcher.Observer) it4.next()).onPictorialActivateChange();
                                            }
                                            PictorialStateWatcher.getInstance().dispatchPictorialAutoSwitchChange(false);
                                        } else {
                                            KgdLogUtil.d("PictorialMediator", "current pictorial is replace, not update");
                                        }
                                        break;
                                    } else {
                                        KgdLogUtil.d("PictorialMediator", "current pictorial is not LeHua, no need to perform unregister");
                                        break;
                                    }
                                    break;
                                case "android.intent.action.PACKAGE_ADDED":
                                    if (this.this$0.isLHStyle()) {
                                        if (!z) {
                                            this.this$0.initPictorialInfo();
                                        } else {
                                            KgdLogUtil.d("PictorialMediator", "current pictorial is replace, not update");
                                        }
                                        break;
                                    } else {
                                        KgdLogUtil.d("PictorialMediator", "current pictorial is not LeHua, no need to perform reinitialize");
                                        break;
                                    }
                                    break;
                                default:
                                    KgdLogUtil.d("PictorialMediator", "not expected action");
                                    break;
                            }
                        }
                        break;
                }
            }
        };
        Trace.traceBegin(8L, "PictorialMediator#init");
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mContentResolver = applicationContext.getContentResolver();
        this.mKeyguardViewMediator = lazy;
        this.mConfigurationController = configurationController;
        this.mOplusLockScreenWallpaper = lazy4;
        this.mOplusFlavorManagerLazy = lazy3;
        this.mSettingsObserver = new AnonymousClass4(this, new Handler(Looper.getMainLooper()), 11);
        this.mDefaultDensityContext = KeyguardUtils.Companion.updateDisplayConfiguration(context);
        this.mPictorialWallpaperLoader = PictorialWallpaperLoader.getInstance();
        this.mPackageManager = applicationContext.getPackageManager();
        WallpaperManager wallpaperManager = (WallpaperManager) applicationContext.getSystemService("wallpaper");
        this.mWallpaperManager = wallpaperManager;
        this.mLastWallpaperID = wallpaperManager.getWallpaperIdForUser(2, 0);
        KeyguardCustomizeManager companion = KeyguardCustomizeManager.Companion.getInstance();
        this.mKeyguardManager = companion;
        String string = applicationContext.getString(2131955122);
        copyStateFromOldSwitch("oplus_customize_pictorial_apply", applicationContext.getString(2131955121));
        copyStateFromOldSwitch("oplus_customize_pictorial_auto_play", string);
        initPictorialInfo();
        this.mReceiver = new BroadcastReceiver(this) { // from class: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator.24
            public final /* synthetic */ PictorialMediator this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                switch (i2) {
                    case 0:
                        String action = intent.getAction();
                        KgdLogUtil.d("PictorialMediator", "onReceive: action= " + action);
                        Uri data = intent.getData();
                        if (data != null && "com.heytap.pictorial".equals(data.getSchemeSpecificPart()) && !TextUtils.isEmpty(action)) {
                            if ("android.intent.action.PACKAGE_CHANGED".equals(action) || "android.intent.action.PACKAGE_REPLACED".equals(action)) {
                                PictorialMediator pictorialMediator = this.this$0;
                                AtomicBoolean atomicBoolean = PictorialMediator.sPictorialFestivalWallpaperUsed;
                                pictorialMediator.hasPictorialSpaceActivity();
                            }
                            break;
                        }
                        break;
                    default:
                        Uri data2 = intent.getData();
                        if (data2 != null && TextUtils.equals(data2.getSchemeSpecificPart(), this.this$0.getPictorialPackageName())) {
                            Bundle extras = intent.getExtras();
                            int i4 = 0;
                            boolean z = extras != null ? extras.getBoolean("android.intent.extra.REPLACING", false) : false;
                            KgdLogUtil.d("PictorialMediator", "onReceive action:" + intent.getAction() + " replace:" + z);
                            String action2 = intent.getAction();
                            action2.hashCode();
                            switch (action2) {
                                case "android.intent.action.PACKAGE_REPLACED":
                                    PictorialMediator pictorialMediator2 = this.this$0;
                                    pictorialMediator2.updateMetaData();
                                    pictorialMediator2.updateSupportSwipeLeftStatus();
                                    PictorialAppDataImpl pictorialAppDataImpl = pictorialMediator2.mPictorialAppDataImpl;
                                    if (pictorialAppDataImpl != null) {
                                        KeyguardFeatureOption.INSTANCE.getClass();
                                        if (KeyguardFeatureOption.isSupportOtherPictorial) {
                                            PictorialAppDataImpl$$ExternalSyntheticLambda0 pictorialAppDataImpl$$ExternalSyntheticLambda0 = new PictorialAppDataImpl$$ExternalSyntheticLambda0(i4);
                                            pictorialAppDataImpl$$ExternalSyntheticLambda0.f$0 = pictorialAppDataImpl;
                                            VarHandle.storeStoreFence();
                                            PictorialUtils.runOnWorkThread(pictorialAppDataImpl$$ExternalSyntheticLambda0);
                                        }
                                    }
                                    if (pictorialMediator2.isMainSwitchOffAndCanLeftSwipe()) {
                                        pictorialMediator2.onPictorialActivateState();
                                    }
                                    if (pictorialMediator2.isFlavorOneExpLH()) {
                                        pictorialMediator2.mPictorialFactory.checkIsSupportSwipeLeft(pictorialMediator2.mPackageManager, pictorialMediator2.getPictorialAppData().getPictorialAppPackage(), pictorialMediator2.getPictorialAppData().getPictorialActivateClassName());
                                        pictorialMediator2.onPictorialActivateState();
                                    }
                                    Iterator it = PictorialStateWatcher.getInstance().mObserver.iterator();
                                    while (it.hasNext()) {
                                        ((PictorialStateWatcher.Observer) it.next()).onPictorialAppReplace();
                                    }
                                    break;
                                case "android.intent.action.PACKAGE_CHANGED":
                                    KgdLogUtil.d("PictorialMediator", "pictorial package change action");
                                    PictorialMediator pictorialMediator3 = this.this$0;
                                    if (pictorialMediator3.isAppEnabledChanged()) {
                                        if (!pictorialMediator3.isMainSwitchOn()) {
                                            Iterator it2 = PictorialStateWatcher.getInstance().mObserver.iterator();
                                            while (it2.hasNext()) {
                                                ((PictorialStateWatcher.Observer) it2.next()).onPictorialAppEnableChanged();
                                            }
                                        }
                                        if (!pictorialMediator3.mPictorialEnabled) {
                                            pictorialMediator3.closePictorialSwitch();
                                        } else {
                                            pictorialMediator3.enablePictorialSwitch();
                                        }
                                        break;
                                    }
                                    break;
                                case "android.intent.action.PACKAGE_REMOVED":
                                    if (this.this$0.isLHStyle()) {
                                        if (!z) {
                                            PictorialMediator pictorialMediator4 = this.this$0;
                                            pictorialMediator4.mPictorialEnabled = false;
                                            pictorialMediator4.mPictorialExist = false;
                                            pictorialMediator4.closePictorialSwitch();
                                            pictorialMediator4.setNotShowAgainSwitchState(true);
                                            pictorialMediator4.unRegisterSwitchObserver();
                                            pictorialMediator4.unRegisterPictorialObserver();
                                            pictorialMediator4.unregisterAodStyleTypeObserver();
                                            pictorialMediator4.setIsSupportLeftSwipe(false);
                                            Iterator it3 = PictorialStateWatcher.getInstance().mObserver.iterator();
                                            while (it3.hasNext()) {
                                                ((PictorialStateWatcher.Observer) it3.next()).onPictorialMainSwitchChange(false);
                                            }
                                            Iterator it4 = PictorialStateWatcher.getInstance().mObserver.iterator();
                                            while (it4.hasNext()) {
                                                ((PictorialStateWatcher.Observer) it4.next()).onPictorialActivateChange();
                                            }
                                            PictorialStateWatcher.getInstance().dispatchPictorialAutoSwitchChange(false);
                                        } else {
                                            KgdLogUtil.d("PictorialMediator", "current pictorial is replace, not update");
                                        }
                                        break;
                                    } else {
                                        KgdLogUtil.d("PictorialMediator", "current pictorial is not LeHua, no need to perform unregister");
                                        break;
                                    }
                                    break;
                                case "android.intent.action.PACKAGE_ADDED":
                                    if (this.this$0.isLHStyle()) {
                                        if (!z) {
                                            this.this$0.initPictorialInfo();
                                        } else {
                                            KgdLogUtil.d("PictorialMediator", "current pictorial is replace, not update");
                                        }
                                        break;
                                    } else {
                                        KgdLogUtil.d("PictorialMediator", "current pictorial is not LeHua, no need to perform reinitialize");
                                        break;
                                    }
                                    break;
                                default:
                                    KgdLogUtil.d("PictorialMediator", "not expected action");
                                    break;
                            }
                        }
                        break;
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_REPLACED");
        if (isLHStyle() && applicationContext.getUserId() == 0) {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isExpRegion()) {
                FlavorTwoFeatureOption.INSTANCE.getClass();
                if (FlavorTwoFeatureOption.isFlavorTwoDevice()) {
                    KgdLogUtil.d("PictorialMediator", "listener package add/remove for LeHua");
                    intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
                    intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
                }
            }
        }
        intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
        intentFilter.addDataScheme("package");
        ((BroadcastDispatcher) Dependency.sDependency.getDependencyInner(BroadcastDispatcher.class)).registerReceiver(this.mReceiver, intentFilter, applicationContext.getMainExecutor());
        setDefaultSetting();
        KeyguardFeatureOption.INSTANCE.getClass();
        if (KeyguardFeatureOption.isSupportOtherPictorial) {
            ((Lifecycle) screenLifecycle).mObservers.add(anonymousClass22);
        }
        FeatureOption.INSTANCE.getClass();
        if (!FeatureOption.isExpRegion()) {
            IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addAction("android.intent.action.PACKAGE_CHANGED");
            intentFilter2.addAction("android.intent.action.PACKAGE_REPLACED");
            intentFilter2.addDataScheme("package");
            intentFilter2.addDataSchemeSpecificPart("com.heytap.pictorial", 0);
            ((BroadcastDispatcher) Dependency.sDependency.getDependencyInner(BroadcastDispatcher.class)).registerReceiver(broadcastReceiver, intentFilter2, (Executor) lazy2.get());
            hasPictorialSpaceActivity();
        }
        if (KeyguardFeatureOption.isSupportOplusGLPictorial) {
            ChildrenModeObserver childrenModeObserver = ChildrenModeObserver.getInstance();
            PictorialMediator$$ExternalSyntheticLambda3 pictorialMediator$$ExternalSyntheticLambda4 = new PictorialMediator$$ExternalSyntheticLambda3(1);
            pictorialMediator$$ExternalSyntheticLambda4.f$0 = this;
            VarHandle.storeStoreFence();
            childrenModeObserver.addListener(pictorialMediator$$ExternalSyntheticLambda4);
        }
        AnonymousClass26 anonymousClass26 = new AnonymousClass26();
        anonymousClass26.this$0 = this;
        VarHandle.storeStoreFence();
        companion.addOnDisablePictorialChangeListener(anonymousClass26);
        ((Lifecycle) ((WakefulnessLifecycle) Dependency.sDependency.getDependencyInner(WakefulnessLifecycle.class))).mObservers.add(anonymousClass21);
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class);
        keyguardStateControllerImpl.getClass();
        keyguardStateControllerImpl.addCallback(anonymousClass10);
        AnonymousClass27 anonymousClass27 = new AnonymousClass27();
        anonymousClass27.this$0 = this;
        VarHandle.storeStoreFence();
        wallpaperManager.addOnColorsChangedListener(anonymousClass27, r5, -1);
        PictorialMediator$$ExternalSyntheticLambda5 pictorialMediator$$ExternalSyntheticLambda5 = new PictorialMediator$$ExternalSyntheticLambda5();
        pictorialMediator$$ExternalSyntheticLambda5.f$0 = this;
        VarHandle.storeStoreFence();
        mutableLiveData.observeForever(pictorialMediator$$ExternalSyntheticLambda5);
        Trace.traceEnd(8L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getAutoPlaySwitchState() {
        int switchStatusFromSettings = getSwitchStatusFromSettings("oplus_customize_pictorial_auto_play");
        if (switchStatusFromSettings == -1) {
            switchStatusFromSettings = 0;
        }
        this.mAutoPlaySwitchKeyState = switchStatusFromSettings;
        return switchStatusFromSettings;
    }

    private int getEnablePlaySwitchState() {
        int switchStatusFromSettings = getSwitchStatusFromSettings("oplus_customize_pictorial_enable_play");
        if (switchStatusFromSettings == -1) {
            switchStatusFromSettings = 0;
        }
        this.mEnablePlaySwitchKeyState = switchStatusFromSettings;
        return switchStatusFromSettings;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getPictorialDefaultSetting() {
        PictorialMediator$$ExternalSyntheticLambda1 pictorialMediator$$ExternalSyntheticLambda1 = new PictorialMediator$$ExternalSyntheticLambda1(0);
        pictorialMediator$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        PictorialUtils.runOnWorkThread(pictorialMediator$$ExternalSyntheticLambda1);
    }

    private int getPictorialFestivalWallpaperState() {
        int switchStatusFromSettings = getSwitchStatusFromSettings("pictorial_festival_wallpaper_flag");
        if (switchStatusFromSettings == -1) {
            return 0;
        }
        return switchStatusFromSettings;
    }

    private PictorialMessageViewInfo getPictorialMessageViewInfo() {
        return this.mPictorialFactory.getPictorialCursorHelper().getPictorialMessageViewInfo(this.mContentResolver, this.mPictorialAppDataImpl.getMessageViewShowUrl(), this.mContext, getPictorialResources());
    }

    private PictorialSpaceBean getPictorialSpace() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        PictorialSpaceBean pictorialSpaceBean = this.mPictorialCursorHelper.getPictorialSpaceBean(this.mContext, this.mContentResolver, this.mPictorialAppDataImpl.getPictorialImageUri());
        KgdLogUtil.i("PictorialMediator", "getPictorialSpace cost time = " + (System.currentTimeMillis() - jCurrentTimeMillis));
        return pictorialSpaceBean;
    }

    private PictorialMediatorHelper.SwitchStateParams getPictorialSwitchParams() {
        if (this.mSwitchStateParams == null) {
            this.mSwitchStateParams = new PictorialMediatorHelper.SwitchStateParams(-1, -1, -1, -1, -1, false, false, false, false, false, false);
        }
        this.mSwitchStateParams.setMainSwitchKeyState(this.mMainSwitchKeyState);
        this.mSwitchStateParams.setAutoPlaySwitchKeyState(this.mAutoPlaySwitchKeyState);
        this.mSwitchStateParams.setEnableSwitchKeyState(this.mEnablePlaySwitchKeyState);
        this.mSwitchStateParams.setLeftSwipeSwitchKeyState(this.mLeftSwipeSwitchKeyState);
        this.mSwitchStateParams.setRightSwipeSwitchKeyState(this.mRightSwipeSwitchKeyState);
        this.mSwitchStateParams.setMainSwitchOn(this.mMainSwitchOn);
        this.mSwitchStateParams.setEnableSwitchOn(this.mEnablePlaySwitchOn);
        this.mSwitchStateParams.setAutoSwitchOn(this.mAutoPlaySwitchOn);
        this.mSwitchStateParams.setPictorialEnable(this.mPictorialEnabled);
        this.mSwitchStateParams.setSupportLeftSwipe(this.mIsSupportLeftSwipe);
        this.mSwitchStateParams.setNotShowAgain(this.mNotShowAgainSwitchOn != 0);
        return this.mSwitchStateParams;
    }

    private int getTaglineSwitchState() {
        return getSwitchStatusFromSettings("oplus_customize_pictorial_tagline");
    }

    private PictorialMessageViewInfo getViewStateInfoFromSP() {
        Drawable drawable;
        try {
            PictorialMessageViewInfo pictorialMessageViewInfo = (PictorialMessageViewInfo) new GsonBuilder().setExclusionStrategies(new ExclusionStrategy[]{new PictorialMessageViewInfo.SkipStrategy()}).create().fromJson(PictorialMessageViewInfo.class, this.mContext.getSharedPreferences("pictorial_info", 0).getString("view_state_info", ""));
            if (pictorialMessageViewInfo == null) {
                return null;
            }
            Context context = this.mContext;
            Resources pictorialResources = getPictorialResources();
            int entryDrawableRes = pictorialMessageViewInfo.getEntryDrawableRes();
            if (pictorialResources == null) {
                drawable = context.getResources().getDrawable(2131235181, null);
            } else {
                try {
                    drawable = pictorialResources.getDrawable(entryDrawableRes, null);
                    if (drawable == null) {
                        drawable = context.getResources().getDrawable(2131235181, null);
                    }
                } catch (Exception e) {
                    KgdLogUtil.i("PictorialMediator", "getPictorialDrawable e : " + e);
                    drawable = context.getResources().getDrawable(2131235181, null);
                }
            }
            pictorialMessageViewInfo.setEntryDrawable(drawable);
            return pictorialMessageViewInfo;
        } catch (Exception e2) {
            KgdLogUtil.i("PictorialMediator", "getViewStateInfoFromSP exception " + e2.getMessage());
            return null;
        }
    }

    private int getWallpaperId() {
        WallpaperManager wallpaperManager = this.mWallpaperManager;
        if (wallpaperManager == null) {
            return 0;
        }
        return this.mWallpaperManager.getWallpaperIdForUser(wallpaperManager.getWallpaperInfoForUser(KeyguardUpdateMonitor.getCurrentUser()) != null ? 1 : 2, -2);
    }

    public static boolean isExpFlavorOne() {
        FlavorOneFeatureOption.INSTANCE.getClass();
        if (!FlavorOneFeatureOption.isFlavorOneDevice()) {
            return false;
        }
        FeatureOption.INSTANCE.getClass();
        return FeatureOption.isExpRegion();
    }

    public static boolean isUserUnlocked$1() {
        if (Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class) != null) {
            return ((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).mUserManager.isUserUnlocked(KeyguardUpdateMonitor.getCurrentUser());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setChildModeOplusGLStatus(boolean z) {
        PictorialMediator$$ExternalSyntheticLambda0 pictorialMediator$$ExternalSyntheticLambda0 = new PictorialMediator$$ExternalSyntheticLambda0();
        pictorialMediator$$ExternalSyntheticLambda0.f$0 = this;
        pictorialMediator$$ExternalSyntheticLambda0.f$1 = z;
        VarHandle.storeStoreFence();
        SystemUIThreadUtil.runOnWorkThread(pictorialMediator$$ExternalSyntheticLambda0);
    }

    public final void brightBottomTxtClick(KeyguardWallpaperInfo keyguardWallpaperInfo) {
        int i;
        if (keyguardWallpaperInfo == null) {
            return;
        }
        Bundle bundle = new Bundle();
        if (getAutoPlaySwitchState() == 0) {
            bundle.putInt("category", 10002);
            i = 2;
        } else {
            bundle.putInt("category", 10000);
            i = -1;
        }
        bundle.putString("eventname", "bright_bottom_txt_click");
        if (keyguardWallpaperInfo instanceof PictorialWallpaperInfo) {
            PictorialWallpaperInfo pictorialWallpaperInfo = (PictorialWallpaperInfo) keyguardWallpaperInfo;
            KgdLogUtil.i("PictorialMediator", "brightBottomTxtClick : Title = " + pictorialWallpaperInfo.getTextTitle() + " wallpaperTitle = " + pictorialWallpaperInfo.getWallpaperTitle());
            bundle.putString("groupId", pictorialWallpaperInfo.getGroupId());
            bundle.putString("imageId", pictorialWallpaperInfo.getImageId());
            bundle.putString("txtGroupId", pictorialWallpaperInfo.getTxtGroupId());
            bundle.putString("wallpaperWebType", pictorialWallpaperInfo.getWallpaperWebType());
            bundle.putString("transparent", pictorialWallpaperInfo.getTransparent());
            bundle.putInt("case", i);
            bundle.putString("wallpaperId", pictorialWallpaperInfo.getWallpaperId());
            bundle.putString("brightText", pictorialWallpaperInfo.getTextTitle());
        }
        reportStatisticData(bundle);
    }

    public final void brightBottomTxtShow(KeyguardWallpaperInfo keyguardWallpaperInfo) {
        int i;
        if (keyguardWallpaperInfo == null) {
            return;
        }
        Bundle bundle = new Bundle();
        if (getAutoPlaySwitchState() == 0) {
            bundle.putInt("category", 10002);
            i = 2;
        } else {
            bundle.putInt("category", 10000);
            i = -1;
        }
        bundle.putString("eventname", "bright_bottom_txt_show");
        if (keyguardWallpaperInfo instanceof PictorialWallpaperInfo) {
            PictorialWallpaperInfo pictorialWallpaperInfo = (PictorialWallpaperInfo) keyguardWallpaperInfo;
            KgdLogUtil.i("PictorialMediator", "brightBottomTxtShow : Title = " + pictorialWallpaperInfo.getTextTitle() + " wallpaperTitle = " + pictorialWallpaperInfo.getWallpaperTitle());
            bundle.putString("groupId", pictorialWallpaperInfo.getGroupId());
            bundle.putString("imageId", pictorialWallpaperInfo.getImageId());
            bundle.putString("txtGroupId", pictorialWallpaperInfo.getTxtGroupId());
            bundle.putString("wallpaperWebType", pictorialWallpaperInfo.getWallpaperWebType());
            bundle.putString("transparent", pictorialWallpaperInfo.getTransparent());
            bundle.putInt("case", i);
            bundle.putString("wallpaperId", pictorialWallpaperInfo.getWallpaperId());
            bundle.putString("brightText", pictorialWallpaperInfo.getTextTitle());
        }
        reportStatisticData(bundle);
    }

    public final boolean callPictorialPage(int i, Bundle bundle) {
        boolean z = false;
        if (this.mPictorialCursorHelper instanceof OplusPictorialCursorHelper) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            Bundle bundleCallPictorialPage = ((OplusPictorialCursorHelper) this.mPictorialCursorHelper).callPictorialPage(this.mContentResolver, this.mPictorialAppDataImpl.getCallPictorialUri(), i, bundle);
            if (bundleCallPictorialPage != null && bundleCallPictorialPage.getInt("p_code") == 0) {
                z = true;
            }
            KgdLogUtil.i("PictorialMediator", "callPictorialPage cost time = " + (System.currentTimeMillis() - jCurrentTimeMillis) + " resultSuccess = " + z);
        }
        return z;
    }

    public final void checkIsSupportPictorialFestivalWallpaper() {
        try {
            this.mIsSupportPictorialFestivalWallpaper = this.mContext.getPackageManager().getApplicationInfo(getPictorialPackageName(), 128).metaData.getBoolean("support_pictorial_festival_wallpaper", false);
        } catch (PackageManager.NameNotFoundException unused) {
            KgdLogUtil.e("PictorialMediator", "package: " + getPictorialPackageName() + " not found");
        }
    }

    public final void checkSupportTaglineMsg() {
        KeyguardFeatureOption.INSTANCE.getClass();
        if (KeyguardFeatureOption.isSupportOtherPictorial || isFlavorOneExpLH()) {
            KgdLogUtil.d("PictorialMediator", "checkSupportTaglineMsg start checkSupportTaglineMessage ");
            PictorialAppDataImpl pictorialAppDataImpl = this.mPictorialAppDataImpl;
            Context context = this.mContext;
            PictorialCursorHelper pictorialCursorHelper = this.mPictorialCursorHelper;
            30 r4 = new 30();
            r4.this$0 = this;
            VarHandle.storeStoreFence();
            pictorialAppDataImpl.getClass();
            if ((!KeyguardFeatureOption.isSupportSidefps() && !KeyguardFeatureOption.isSupportBackTouchFingerprint()) || KeyguardFeatureOption.isSupportUdfps()) {
                KgdLogUtil.d("PictorialAppData", " checkSupportTaglineMessage,the devices is unSupport");
                pictorialAppDataImpl.setDevicesSupportTaglineState(0, context);
                KgdLogUtil.d("PictorialMediator", "checkSupportTaglineMessage unSupportTaglineMessageView disEnablePictorialTaglineSwitch");
                Settings.System.putIntForUser(this.mContentResolver, "oplus_customize_pictorial_tagline", -1, KeyguardUpdateMonitor.getCurrentUser());
                this.mTaglineSwitchOn = false;
                return;
            }
            if (KeyguardFeatureOption.isSupportOplusGLPictorial) {
                KgdLogUtil.i("PictorialAppData", " check isSupportTaglineMessage,isSupportOplusGLPictorial");
                pictorialAppDataImpl.mIsSupportTaglineMessageView = true;
                pictorialAppDataImpl.setDevicesSupportTaglineState(1, context);
                r4.supportTagline();
                return;
            }
            PictorialAppDataImpl$.ExternalSyntheticLambda4 externalSyntheticLambda4 = new PictorialAppDataImpl$.ExternalSyntheticLambda4();
            externalSyntheticLambda4.f$0 = pictorialAppDataImpl;
            externalSyntheticLambda4.f$1 = pictorialCursorHelper;
            externalSyntheticLambda4.f$2 = context;
            externalSyntheticLambda4.f$3 = r4;
            VarHandle.storeStoreFence();
            PictorialUtils.runOnWorkThread(externalSyntheticLambda4);
        }
    }

    public final void clickDetail(PictorialWallpaperInfo pictorialWallpaperInfo, int i) {
        String webUrl;
        if (pictorialWallpaperInfo == null) {
            return;
        }
        KgdLogUtil.i("PictorialMediator", "clickDetail : imageInfo = " + pictorialWallpaperInfo.getTextTitle());
        Bundle bundle = new Bundle();
        bundle.putInt("category", 10001);
        bundle.putString("eventname", "click");
        bundle.putString("imageId", pictorialWallpaperInfo.getImageId());
        bundle.putString("groupId", pictorialWallpaperInfo.getGroupId());
        bundle.putInt("groupType", pictorialWallpaperInfo.getGroupType());
        bundle.putInt("case", 1);
        bundle.putInt("linktype", i);
        bundle.putString("clicktype", "more");
        if (i == 1) {
            webUrl = pictorialWallpaperInfo.getWebUrl();
        } else if (i != 2) {
            webUrl = i != 3 ? "" : pictorialWallpaperInfo.getDeepLink();
        } else {
            webUrl = pictorialWallpaperInfo.getInstantAppLink();
        }
        bundle.putString("url", webUrl);
        reportStatisticData(bundle);
    }

    public final void closeMainSwtich() {
        Settings.System.putIntForUser(this.mContentResolver, "oplus_customize_pictorial_apply", 0, KeyguardUpdateMonitor.getCurrentUser());
        this.mMainSwitchOn = false;
    }

    public final void closePictorialAutoSwitch() {
        KgdLogUtil.i("PictorialMediator", "closePictorialAutoSwitch()");
        Settings.System.putIntForUser(this.mContentResolver, "oplus_customize_pictorial_auto_play", 0, KeyguardUpdateMonitor.getCurrentUser());
        this.mAutoPlaySwitchOn = false;
    }

    public final void closePictorialSwitch() {
        closeMainSwtich();
        closePictorialAutoSwitch();
        if (isOplusGLStyle() || isExpFlavorOne()) {
            Settings.System.putIntForUser(this.mContentResolver, "oplus_customize_pictorial_enable_play", 0, KeyguardUpdateMonitor.getCurrentUser());
            this.mEnablePlaySwitchOn = false;
        }
    }

    public final void copyStateFromOldSwitch(String str, String str2) {
        int switchStatusFromSettings = getSwitchStatusFromSettings(str);
        if (switchStatusFromSettings == -1) {
            if (!TextUtils.isEmpty(str2)) {
                switchStatusFromSettings = getSwitchStatusFromSettings(str2);
            }
            if (switchStatusFromSettings != -1) {
                Settings.System.putIntForUser(this.mContentResolver, str, switchStatusFromSettings, KeyguardUpdateMonitor.getCurrentUser());
            }
        }
    }

    public final void createPictorialFactory() {
        PictorialFactory pictorialFactory = this.mPictorialFactory;
        if (pictorialFactory != null) {
            pictorialFactory.destory();
        }
        int pictorialStyle = this.mPictorialAppDataImpl.getPictorialStyle();
        OplusLockIconView$.ExternalSyntheticOutline0.m(pictorialStyle, "find pictorlai factory : ", "PictorialMediator");
        PictorialFactory pictorialFactory2 = ((OplusFlavorManager) DependencyEx.sDependency.getDependency(OplusFlavorManager.class)).getPictorialFactory(pictorialStyle);
        this.mPictorialFactory = pictorialFactory2;
        this.mPictorialCursorHelper = pictorialFactory2.getPictorialCursorHelper();
        this.mPictorialBuriedHelper = this.mPictorialFactory.getPictorialBuriedHelper();
        this.mPictorialMediatorHelper = this.mPictorialFactory.getPictorialMediatorHelper();
        Settings.System.putIntForUser(this.mContentResolver, "oplus_customize_pictorial_version_code", 401, KeyguardUpdateMonitor.getCurrentUser());
    }

    public final void doLiveCheck() {
        if (isSupportDisableAliveOrOplusGLStyle()) {
            boolean z = false;
            boolean z2 = Settings.System.getIntForUser(this.mContext.getContentResolver(), "oplus_disable_pictorial_alive", 0, -2) == 1;
            KeyguardStateControllerImpl keyguardStateControllerImpl = OplusNavigationBarManager.getInstance().mKeyguardStateController;
            if (keyguardStateControllerImpl != null && keyguardStateControllerImpl.mShowing) {
                z = true;
            }
            UnlockViewLifecycleOwner$.ExternalSyntheticOutline0.m(" shouldStopService ", "PictorialMediator", RowView$.ExternalSyntheticOutline0.m("isKeyguardLocked ", " mKeyguardShowing ", z), this.mKeyguardShowing, z2);
            if (this.mKeyguardShowing || z || !z2) {
                removeCallbacks(this.mReloadPictorialRunnable);
                this.mReloadPictorialRunnable.run();
                return;
            }
            AnonymousClass3 anonymousClass3 = this.mHandler;
            if (!Boolean.TRUE.equals(this.mWaitingPictorialData.getValue())) {
                stopProviderService();
                return;
            }
            KgdLogUtil.i("PictorialMediator", "waiting pictorial data, keep connection");
            if (anonymousClass3.hasCallbacks(this.mWaitingPictorialOvertimeRunnable)) {
                anonymousClass3.removeCallbacks(this.mWaitingPictorialOvertimeRunnable);
            }
            anonymousClass3.postDelayed(this.mWaitingPictorialOvertimeRunnable, 5000L);
        }
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        PictorialAppInfo pictorialAppInfo = this.mPictorialAppDataImpl.getPictorialAppInfo();
        StringBuilder sb = new StringBuilder("print pictorial App:\n");
        sb.append(pictorialAppInfo.toString());
        sb.append("\nprint something about Pictorial Factory:\n  Factory:");
        sb.append(this.mPictorialFactory.getClass().getSimpleName());
        sb.append("\n  loader:");
        sb.append(this.mPictorialFactory.getPictorialLoader().getClass().getSimpleName());
        sb.append("\n  CursorHelper:");
        sb.append(this.mPictorialFactory.getPictorialCursorHelper().getClass().getSimpleName());
        sb.append("\n  MessageViewHelper:");
        sb.append(this.mPictorialFactory.getPictorialBottomViewHelper().getClass().getSimpleName());
        sb.append("\n  PreloadHelper:");
        sb.append(this.mPictorialFactory.getPictorialPreloadHelper().getClass().getSimpleName());
        sb.append("\n");
        PictorialWallpaperInfo currentLockScreenWallpaperInfo = this.mPictorialFactory.getPictorialLoader().getCurrentLockScreenWallpaperInfo();
        if (currentLockScreenWallpaperInfo != null) {
            sb.append("  PictorialWallpaperInfo:");
            sb.append(currentLockScreenWallpaperInfo.toString());
            sb.append("\n");
            Map<String, Object> dataMap = currentLockScreenWallpaperInfo.getDataMap();
            if (dataMap != null) {
                sb.append("  PictorialWallpaperInfo DataMap:\n");
                for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
                    sb.append("    ");
                    sb.append(entry.getKey());
                    sb.append("=");
                    sb.append(entry.getValue());
                    sb.append("\n");
                }
            }
        }
        printWriter.println(sb.toString());
    }

    public final void enablePictorialAutoSwitch() {
        KgdLogUtil.i("PictorialMediator", "enablePictorialAutoSwitch()");
        Settings.System.putIntForUser(this.mContentResolver, "oplus_customize_pictorial_auto_play", 1, KeyguardUpdateMonitor.getCurrentUser());
        this.mAutoPlaySwitchOn = this.mMainSwitchOn;
        if (isGlanceStyle() || isOplusGLStyle()) {
            this.mAutoPlaySwitchOn = this.mEnablePlaySwitchOn;
        }
    }

    public final void enablePictorialSwitch() {
        KgdLogUtil.i("PictorialMediator", "enablePictorialSwitch authorize = false, " + KeyguardUpdateMonitor.getCurrentUser());
        if (KeyguardUpdateMonitor.getCurrentUser() != 0) {
            return;
        }
        KeyguardFeatureOption.INSTANCE.getClass();
        if (KeyguardFeatureOption.isSupportOplusGLPictorial) {
            return;
        }
        Settings.System.putIntForUser(this.mContentResolver, "oplus_customize_pictorial_apply", 1, KeyguardUpdateMonitor.getCurrentUser());
        this.mMainSwitchOn = true;
        enablePictorialAutoSwitch();
        if (isExpFlavorOne()) {
            Settings.System.putIntForUser(this.mContentResolver, "oplus_customize_pictorial_enable_play", 1, KeyguardUpdateMonitor.getCurrentUser());
            this.mEnablePlaySwitchOn = true;
        }
    }

    public final void ensureDisableAliveSettingsObserver() {
        if (this.mDisableAliveSettingsObserverRegistered || this.mSettingsObserver == null || this.mContext == null || !isSupportDisableAliveOrOplusGLStyle()) {
            return;
        }
        this.mDisableAliveSettingsObserverRegistered = true;
        PictorialMediator$$ExternalSyntheticLambda1 pictorialMediator$$ExternalSyntheticLambda1 = new PictorialMediator$$ExternalSyntheticLambda1(2);
        pictorialMediator$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        PictorialUtils.runOnWorkThread(pictorialMediator$$ExternalSyntheticLambda1);
    }

    public final void ensurePictorialWallpaperForPanoramicAod(String str) {
        if (SmoothTransitionController.isPanoramicAodEnable(this.mContext) && isDeviceProvisioned() && this.mPictorialExist) {
            StringBuilder sbM = ActivityResultRegistry$.ExternalSyntheticOutline0.m("ensurePictorialWallpaperForPanoramicAod reason=", str, ", connected=");
            sbM.append(this.mPictorialServiceConnected);
            sbM.append(", keyguardShowing=");
            sbM.append(this.mKeyguardShowing);
            sbM.append(", paodShowing=");
            sbM.append(AodData.getInstance(this.mContext).isPanoramicAodInShow());
            KgdLogUtil.i("PictorialMediator", sbM.toString());
            if (AodData.getInstance(this.mContext).isPanoramicAodInShow()) {
                if (this.mPictorialServiceConnected) {
                    return;
                }
                firstProviderService(true);
            } else {
                this.mIsNeedUpdateView = true;
                if (!this.mPictorialServiceConnected) {
                    firstProviderService(true);
                } else {
                    handleObtainPictorialWallpaper(true);
                    this.mIsNeedUpdateView = false;
                }
            }
        }
    }

    public final void firstProviderService(boolean z) {
        if (this.mPictorialServiceConnected) {
            return;
        }
        if (isForBidToConnectInternal(true)) {
            KgdLogUtil.i("PictorialMediator", "firstProviderService, isForBidToConnect() is false, return");
            return;
        }
        OCDHandler oCDHandler = PictorialUtils.sPictorialHandler;
        StartPictorialServiceRunnable startPictorialServiceRunnable = this.mStartPictorialServiceRunnable;
        if (oCDHandler.hasCallbacks(startPictorialServiceRunnable)) {
            KgdLogUtil.i("PictorialMediator", "firstProviderService hasSameTaskInWorkLooper, return");
            return;
        }
        KgdLogUtil.d("PictorialMediator", "firstProviderService(" + z + ") begin");
        startPictorialServiceRunnable.setDeviceProvisioned(z);
        PictorialUtils.runOnWorkThread(startPictorialServiceRunnable);
    }

    public String getActionSeePictorialDetails() {
        return this.mPictorialAppDataImpl.getActionSeePictorialDetails();
    }

    public boolean getCanUpdateWelcomePreloadWallpaper() {
        return this.mCanUpdateWelcomePreloadWallpaper;
    }

    public Context getContext() {
        return this.mContext;
    }

    public boolean getIsUiEngineSwipe() {
        return this.mIsUiEngineSwipe;
    }

    public boolean getKgdPictorialRecommendation() {
        return this.mKgdPictorialRecommendation;
    }

    public boolean getLeftSwipeSwitchState() {
        this.mLeftSwipeSwitchKeyState = getSwitchStatusFromSettings("swipe_left_display_content");
        KgdLogUtil.i("PictorialMediator", "getLeftSwipeSwitchState, main switch " + isMainSwitchOn());
        return this.mPictorialMediatorHelper.isLeftSwipeSwitchOn(getPictorialSwitchParams());
    }

    public int getMainSwitchState() {
        int switchStatusFromSettings = getSwitchStatusFromSettings("oplus_customize_pictorial_apply");
        if (switchStatusFromSettings == -1) {
            switchStatusFromSettings = 0;
        }
        this.mMainSwitchKeyState = switchStatusFromSettings;
        return switchStatusFromSettings;
    }

    public int getNotShowAgainSwitchState() {
        int switchStatusFromSettings = getSwitchStatusFromSettings("pictorial_activate_not_show");
        if (switchStatusFromSettings == -1 && isExpFlavorOne() && (isTbStyle() || isHKStyle())) {
            switchStatusFromSettings = 0;
        }
        OplusLockIconView$.ExternalSyntheticOutline0.m(switchStatusFromSettings, "getNotShowAgainSwitchState: ", "PictorialMediator");
        return switchStatusFromSettings;
    }

    public NotificationPanelViewController getPanelViewController() {
        return this.mPanelViewController;
    }

    public PictorialAppData getPictorialAppData() {
        return this.mPictorialAppDataImpl;
    }

    public PictorialBuriedHelper getPictorialBuriedHelper() {
        return this.mPictorialBuriedHelper;
    }

    public PictorialFactory getPictorialFactory() {
        return this.mPictorialFactory;
    }

    public String getPictorialPackageName() {
        return this.mPictorialAppDataImpl.getPictorialAppPackage();
    }

    public Resources getPictorialResources() {
        Context context = this.mContext;
        if (context == null || !this.mPictorialExist) {
            return null;
        }
        String pictorialAppPackage = this.mPictorialAppDataImpl.getPictorialAppPackage();
        StringBuilder sb = PictorialUtils.sStringBuilder;
        KeyguardUtils.Companion companion = KeyguardUtils.Companion;
        Context remoteContext = companion.getRemoteContext(context, pictorialAppPackage);
        if (remoteContext == null) {
            return null;
        }
        Resources resources = remoteContext.getResources();
        Configuration configuration = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        displayMetrics.widthPixels = companion.getRealScreenWidth();
        int realScreenHeight = companion.getRealScreenHeight();
        displayMetrics.heightPixels = realScreenHeight;
        displayMetrics.noncompatWidthPixels = displayMetrics.widthPixels;
        displayMetrics.noncompatHeightPixels = realScreenHeight;
        resources.updateConfiguration(configuration, displayMetrics, null);
        return resources;
    }

    public boolean getRightSwipeSwitchState() {
        this.mRightSwipeSwitchKeyState = getSwitchStatusFromSettings("swipe_right_display_content");
        return this.mPictorialMediatorHelper.isRightSwipeSwitchOn(getPictorialSwitchParams());
    }

    public final int getSwitchStatusFromSettings(String str) {
        try {
            return Settings.System.getIntForUser(this.mContentResolver, str, KeyguardUpdateMonitor.getCurrentUser());
        } catch (Settings.SettingNotFoundException unused) {
            KgdLogUtil.w("PictorialMediator", "getPictorialSwitchState switch = " + str + "  SettingNotFoundException return = -1");
            return -1;
        }
    }

    public int getWelcomeResourcesId() {
        Resources pictorialResources = getPictorialResources();
        if (pictorialResources != null) {
            try {
                return pictorialResources.getIdentifier("ic_welcome_launcher", "drawable", this.mPictorialAppDataImpl.getPictorialAppPackage());
            } catch (Exception e) {
                KgdLogUtil.i("PictorialMediator", "getWelcomeResourcesId e = " + e);
            }
        }
        return -1;
    }

    public PictorialAppDataImpl getmPictorialAppDataImpl() {
        return this.mPictorialAppDataImpl;
    }

    public final void handleObtainPictorialWallpaper(boolean z) {
        if (AodData.getInstance(this.mContext).isPanoramicAodInShow()) {
            KgdLogUtil.i("PictorialMediator", "handleObtainPictorialWallpaper return, Panoramic Aod showing");
        } else if (!AodData.getInstance(this.mContext).isPanoramicAod() || this.mPictorialFactory.getPictorialLoader().getCurrentLockScreenWallpaperInfo() == null) {
            obtainPictorialWallpaper("pictorial_connect", null, true, z, false);
        } else {
            KgdLogUtil.i("PictorialMediator", "handleObtainPictorialWallpaper return, wallpaper exist");
        }
    }

    public final boolean hasPictorialSpace() {
        FeatureOption.INSTANCE.getClass();
        return !FeatureOption.isExpRegion() && this.mHasPictorialSpace;
    }

    public final void hasPictorialSpaceActivity() {
        PackageManager packageManager = this.mContext.getPackageManager();
        Intent intent = new Intent("com.heytap.pictorial.action.START_SELECTION");
        intent.setPackage(getPictorialPackageName());
        List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, PackageManager.ResolveInfoFlags.of(65536L));
        boolean z = false;
        if (!listQueryIntentActivities.isEmpty() && listQueryIntentActivities.get(0).activityInfo.exported) {
            z = true;
        }
        this.mHasPictorialSpace = z;
    }

    public final void initPictorialInfo() {
        KeyguardFeatureOption keyguardFeatureOption = KeyguardFeatureOption.INSTANCE;
        keyguardFeatureOption.getClass();
        boolean z = false;
        if (KeyguardFeatureOption.isSupportOplusGLPictorial && KeyguardFeatureOption.isKeepHeyTapPictorial) {
            this.mMainSwitchOn = KeyguardUpdateMonitor.getCurrentUser() == 0 && getSwitchStatusFromSettings("oplus_customize_pictorial_apply") == 1;
            this.mPictorialAppDataImpl = new PictorialAppDataImpl(this.mContext, this.mMainSwitchOn);
        } else {
            this.mPictorialAppDataImpl = new PictorialAppDataImpl(this.mContext);
        }
        createPictorialFactory();
        if (KeyguardFeatureOption.isSupportOtherPictorial && this.mPictorialStatusOberver == null) {
            PictorialStatusObserver pictorialStatusObserver = new PictorialStatusObserver(this.mContext);
            this.mPictorialStatusOberver = pictorialStatusObserver;
            pictorialStatusObserver.setPictorialAppInfo(this.mPictorialAppDataImpl.getPictorialAppInfo());
        }
        updateMetaData();
        this.mPictorialExist = this.mPictorialAppDataImpl.mPictorialExist;
        initStatusWhenShowingLocked();
        updateSupportSwipeLeftStatus();
        if (this.mPictorialExist) {
            registerSwitchObserver();
        }
        registerPictorialObserver();
        if (this.mKeyguardManager.needDisablePictorial()) {
            closePictorialSwitch();
        }
        if (isAppEnabledChanged()) {
            if (this.mPictorialEnabled) {
                enablePictorialSwitch();
            } else {
                closePictorialSwitch();
            }
        }
        if (KeyguardFeatureOption.isSupportOtherPictorial) {
            KgdLogUtil.i("PictorialMediator", "Glance first start,should adjust it");
            if (KeyguardFeatureOption.isSupportOplusGLPictorial) {
                if (!this.mPictorialAppDataImpl.mIsSupportTaglineMessageView) {
                    KgdLogUtil.i("PictorialMediator", "adjustForVerticalApp unSupportTaglineMessageView disEnablePictorialTaglineSwitch");
                    Settings.System.putIntForUser(this.mContentResolver, "oplus_customize_pictorial_tagline", -1, KeyguardUpdateMonitor.getCurrentUser());
                    this.mTaglineSwitchOn = false;
                } else if (getSwitchStatusFromSettings("oplus_customize_pictorial_tagline") == -1) {
                    Settings.System.putIntForUser(this.mContentResolver, "oplus_customize_pictorial_tagline", 1, KeyguardUpdateMonitor.getCurrentUser());
                    this.mTaglineSwitchOn = true;
                    KgdLogUtil.i("PictorialMediator", "adjustForVerticalApp enablePictorialTaglineSwitch");
                }
                if (KeyguardUpdateMonitor.getCurrentUser() == 0 && getTaglineSwitchState() == 1) {
                    z = true;
                }
                this.mTaglineSwitchOn = z;
            } else {
                this.mPictorialMediatorHelper.adjustForVerticalApp(this.mContext, getPictorialSwitchParams());
            }
            OplusChargeAnimController$.ExternalSyntheticOutline0.m("Glance_Nudge", new StringBuilder("is version change ="), this.mPictorialAppDataImpl.mIsVersionChanged);
            if (this.mPictorialAppDataImpl.mIsVersionChanged) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("glance_status", Boolean.valueOf(this.mEnablePlaySwitchOn));
                contentValues.put("lehua_status", Boolean.valueOf(this.mMainSwitchOn));
                contentValues.put("wallpaper_status", Boolean.valueOf(this.mAutoPlaySwitchOn));
                this.mPictorialFactory.getPictorialBuriedHelper().collectSwitchData(contentValues);
            }
        }
        hasPictorialSpaceActivity();
        if (isMainSwitchOffAndCanLeftSwipe() || isFlavorOneExpLH()) {
            onPictorialActivateState();
        }
        if (keyguardFeatureOption.getSIsLehuaSwitchCorrected()) {
            FlavorOneFeatureOption.INSTANCE.getClass();
            if (FlavorOneFeatureOption.isFlavorOneDevice() && this.mPictorialExist && ((isLHStyle() || isHKStyle()) && getSwitchStatusFromSettings("corrected_switch_status") == -1)) {
                Settings.System.putIntForUser(this.mContentResolver, "oplus_customize_pictorial_apply", 1, KeyguardUpdateMonitor.getCurrentUser());
                this.mMainSwitchOn = true;
                enablePictorialAutoSwitch();
                Settings.System.putIntForUser(this.mContentResolver, "mobile_data_pictorial_support", 1, KeyguardUpdateMonitor.getCurrentUser());
                Settings.System.putIntForUser(this.mContentResolver, "corrected_switch_status", 1, KeyguardUpdateMonitor.getCurrentUser());
            }
        }
        registerAodStyleTypeObserver();
    }

    public final void initStatusWhenShowingLocked() {
        boolean z = false;
        this.mMainSwitchOn = KeyguardUpdateMonitor.getCurrentUser() == 0 && getMainSwitchState() == 1;
        this.mAutoPlaySwitchOn = KeyguardUpdateMonitor.getCurrentUser() == 0 && this.mMainSwitchOn && getAutoPlaySwitchState() == 1;
        updatePictorialFestivalWallpaperReady();
        this.mLeftSwipeSwitchOn = getLeftSwipeSwitchState();
        this.mRightSwipeSwitchOn = getRightSwipeSwitchState();
        this.mEnablePlaySwitchOn = KeyguardUpdateMonitor.getCurrentUser() == 0 && this.mMainSwitchOn && getEnablePlaySwitchState() == 1;
        this.mNotShowAgainSwitchOn = getNotShowAgainSwitchState();
        this.mPictorialMediatorHelper.initStatusWhenShowLocked(this.mContext, getPictorialSwitchParams());
        KeyguardFeatureOption.INSTANCE.getClass();
        if (KeyguardFeatureOption.isSupportOplusGLPictorial) {
            this.mEnablePlaySwitchOn = KeyguardUpdateMonitor.getCurrentUser() == 0 && getEnablePlaySwitchState() == 1;
            this.mAutoPlaySwitchOn = KeyguardUpdateMonitor.getCurrentUser() == 0 && (this.mEnablePlaySwitchOn || this.mMainSwitchOn) && getAutoPlaySwitchState() == 1;
            if (getSwitchStatusFromSettings("swipe_left_display_content") == -1) {
                Settings.System.putIntForUser(this.mContentResolver, "swipe_left_display_content", 1, KeyguardUpdateMonitor.getCurrentUser());
            }
            if (this.mPictorialAppDataImpl.mIsSupportTaglineMessageView) {
                if (KeyguardUpdateMonitor.getCurrentUser() == 0 && getTaglineSwitchState() == 1) {
                    z = true;
                }
                this.mTaglineSwitchOn = z;
            } else {
                Settings.System.putIntForUser(this.mContentResolver, "oplus_customize_pictorial_tagline", -1, KeyguardUpdateMonitor.getCurrentUser());
                this.mTaglineSwitchOn = false;
            }
        }
        checkIsSupportPictorialFestivalWallpaper();
    }

    public final boolean isAppEnabledChanged() {
        PackageManager packageManager;
        boolean z;
        if (!OplusMultiAppManager.getInstance().isMultiAppUserId(this.mContext.getUserId()) && this.mPictorialExist && (packageManager = this.mPackageManager) != null) {
            try {
                int applicationEnabledSetting = packageManager.getApplicationEnabledSetting(this.mPictorialAppDataImpl.getPictorialAppPackage());
                z = applicationEnabledSetting == 1 || applicationEnabledSetting == 0;
            } catch (IllegalArgumentException e) {
                KgdLogUtil.e("PictorialMediator", "isAppEnabledChanged IllegalArgumentException " + e);
            } catch (Exception e2) {
                KgdLogUtil.e("PictorialMediator", "isAppEnabledChanged Exception " + e2);
            }
            if (this.mPictorialEnabled != z) {
                OplusLockIconView$.ExternalSyntheticOutline0.m("isAppEnabledChanged ", "PictorialMediator", z);
                this.mPictorialEnabled = z;
                return true;
            }
        }
        return false;
    }

    public final boolean isAutoPlaySwitchOn() {
        if (!isOplusGLStyle() || this.mEnablePlaySwitchOn || this.mMainSwitchOn) {
            return this.mPictorialMediatorHelper.isAutoPlaySwitchOn(getPictorialSwitchParams());
        }
        return false;
    }

    public final boolean isDeviceProvisioned() {
        return Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class) != null && ((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).mDeviceProvisioned;
    }

    public final boolean isFlavorOneExpLH() {
        FeatureOption.INSTANCE.getClass();
        if (!FeatureOption.isExpRegion()) {
            return false;
        }
        FlavorOneFeatureOption.INSTANCE.getClass();
        return FlavorOneFeatureOption.isFlavorOneDevice() && isLHStyle() && this.mPictorialExist;
    }

    public final boolean isForBidToConnectInternal(boolean z) {
        if (!this.mPictorialExist) {
            return true;
        }
        KeyguardUpdateMonitor keyguardUpdateMonitor = (KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class);
        if (!keyguardUpdateMonitor.mUserManager.isUserUnlocked(KeyguardUpdateMonitor.getCurrentUser())) {
            KgdLogUtil.d("PictorialMediator", "isForBidToConnect: not user unlocked");
            return true;
        }
        if (!((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).mDeviceProvisioned) {
            KgdLogUtil.d("PictorialMediator", "isForBidToConnect: not DeviceProvisioned");
            return true;
        }
        if (z && !isMainSwitchOn()) {
            KgdLogUtil.d("PictorialMediator", "isForBidToConnect: not main swtich on");
            return true;
        }
        if (KeyguardUtils.Companion.isSystemFbeMode(this.mContext)) {
            KgdLogUtil.d("PictorialMediator", "isForBidToConnect: is fbe mode");
            return true;
        }
        if (KeyguardUpdateMonitor.getCurrentUser() != 0) {
            KgdLogUtil.d("PictorialMediator", "isForBidToConnect: not user 0");
            return true;
        }
        FeatureOption.INSTANCE.getClass();
        if (!FeatureOption.isLightOsForLH() || !this.mIsProcessStartWhenReboot) {
            return false;
        }
        BrandUtils.INSTANCE.getClass();
        if (BrandUtils.isRealme) {
            return false;
        }
        KgdLogUtil.d("PictorialMediator", "isForBidToConnect: KeyguardHaveBeenGone is false");
        return true;
    }

    public final boolean isGlanceStyle() {
        return this.mPictorialAppDataImpl.getPictorialStyle() == 1;
    }

    public final boolean isHKStyle() {
        return this.mPictorialAppDataImpl.getPictorialStyle() == 2 && KeyguardUpdateMonitor.getCurrentUser() == 0;
    }

    public final boolean isKeyguardOccluded() {
        return ((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mOccluded;
    }

    public final boolean isLHStyle() {
        return this.mPictorialAppDataImpl.getPictorialStyle() == 0;
    }

    public final boolean isLHSwitchOn() {
        if (this.mMainSwitchOn && KeyguardUpdateMonitor.getCurrentUser() == 0) {
            return !SuperPowerSaveSettingsObserver.getInstance().isSuperPowerSaveState;
        }
        return false;
    }

    public final boolean isLeftSwipeDisplaySwitchOn() {
        return KeyguardUpdateMonitor.getCurrentUser() == 0 && this.mLeftSwipeSwitchOn;
    }

    public final boolean isMainSwitchOffAndCanLeftSwipe() {
        if (!this.mIsSupportLeftSwipe) {
            KgdLogUtil.i("PictorialMediator", "isMainSwitchOffAndCanLeftSwipe checkIsSupportSwipeLeft is false");
            return false;
        }
        if (isExpFlavorOne() && (!this.mPictorialEnabled || !this.mLeftSwipeSwitchOn)) {
            return false;
        }
        if (!isLHStyle() && !isTbStyle() && !isHKStyle()) {
            KgdLogUtil.i("PictorialMediator", "isMainSwitchOffAndCanLeftSwipe OtherPictorial is false");
            return false;
        }
        if (this.mNotShowAgainSwitchOn != 0) {
            KgdLogUtil.i("PictorialMediator", "isMainSwitchOffAndCanLeftSwipe isNotShowAgain is true");
            return false;
        }
        if (KeyguardUpdateMonitor.getCurrentUser() != 0) {
            KgdLogUtil.i("PictorialMediator", "isMainSwitchOffAndCanLeftSwipe currentUser is not 0");
            return false;
        }
        if (SuperPowerSaveSettingsObserver.getInstance().isSuperPowerSaveState) {
            KgdLogUtil.i("PictorialMediator", "isMainSwitchOffAndCanLeftSwipe isSuperPowerSaveModel is true");
            return false;
        }
        if (!Util.isChildrenOrStudyCenterMode()) {
            return true;
        }
        KgdLogUtil.i("PictorialMediator", "isMainSwitchOffAndCanLeftSwipe isChildrenOrStudyCenterMode is true");
        return false;
    }

    public final boolean isMainSwitchOn() {
        if (!isOplusGLStyle()) {
            KeyguardFeatureOption.INSTANCE.getClass();
            if (!KeyguardFeatureOption.isKeepHeyTapPictorial || !this.mPictorialAppDataImpl.getDefPictorialAppInfo().getEnable() || !this.mPictorialAppDataImpl.getOplusGLPictorialAppInfo().getEnable()) {
                return this.mPictorialMediatorHelper.isMainSwitchOn(getPictorialSwitchParams());
            }
        }
        return !SuperPowerSaveSettingsObserver.getInstance().isSuperPowerSaveState && this.mPictorialEnabled;
    }

    public final boolean isMainSwitchStatusOn() {
        return getMainSwitchState() == 1;
    }

    public final boolean isNeedDelayUnlock$1() {
        return ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusKeyguardVerifyManager().isSystemReboot() && KeyguardUtils.Companion.isSystemFbeMode(this.mContext) && ((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mShowing;
    }

    public final boolean isOplusGLStyle() {
        PictorialAppDataImpl pictorialAppDataImpl;
        KeyguardFeatureOption.INSTANCE.getClass();
        return KeyguardFeatureOption.isSupportOplusGLPictorial && (pictorialAppDataImpl = this.mPictorialAppDataImpl) != null && pictorialAppDataImpl.getPictorialStyle() == 4;
    }

    public final boolean isPictorialEnable(boolean z) {
        if (!this.mPictorialExist || !isMainSwitchOn() || KeyguardUpdateMonitor.getCurrentUser() != 0 || !((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).mDeviceProvisioned) {
            return false;
        }
        if (((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).mUserManager.isUserUnlocked(KeyguardUpdateMonitor.getCurrentUser())) {
            return z || KeyguardUtils.Companion.isDefaultKeyguardInSettings(this.mContext);
        }
        return false;
    }

    public final boolean isPictorialTipsFeatherSupport() {
        return this.mPictorialAppDataImpl.mCurrentPictorialAppInfo.getShowTipsFeatherSupport();
    }

    public final boolean isRightSwipeDisplaySwitchOn() {
        boolean z = KeyguardUpdateMonitor.getCurrentUser() == 0 && isLeftSwipeDisplaySwitchOn() && this.mIsMetaDataSupportRightSwipe && this.mRightSwipeSwitchOn;
        StringBuilder sbM = RowView$.ExternalSyntheticOutline0.m("isRightSwipeDisplaySwitchOn: ", ", mIsMetaDataSupportRightSwipe: ", z);
        sbM.append(this.mIsMetaDataSupportRightSwipe);
        sbM.append(", mRightSwipeSwitchOn: ");
        sbM.append(this.mRightSwipeSwitchOn);
        KgdLogUtil.d("PictorialMediator", sbM.toString());
        return z;
    }

    public final boolean isSupportDisableAliveOrOplusGLStyle() {
        return this.mIsSupportDisableAlive || isOplusGLStyle();
    }

    public final boolean isSupportPictorialTips() {
        return this.mPictorialAppDataImpl.mCurrentPictorialAppInfo.isShowPictorialTipDevice();
    }

    public final boolean isTbStyle() {
        return this.mPictorialAppDataImpl.getPictorialStyle() == 3 && KeyguardUpdateMonitor.getCurrentUser() == 0;
    }

    public final void notifyPictorialFinish() {
        int i = 1;
        if (isForBidToConnectInternal(true)) {
            return;
        }
        KgdLogUtil.i("PictorialMediator", "notifyPictorialFinish");
        ContentValues contentValues = new ContentValues();
        contentValues.put("notify_off", (Integer) 1);
        PictorialMediator$$ExternalSyntheticLambda6 pictorialMediator$$ExternalSyntheticLambda6 = new PictorialMediator$$ExternalSyntheticLambda6(i);
        pictorialMediator$$ExternalSyntheticLambda6.f$0 = this;
        pictorialMediator$$ExternalSyntheticLambda6.f$1 = contentValues;
        VarHandle.storeStoreFence();
        PictorialUtils.runOnWorkThread(pictorialMediator$$ExternalSyntheticLambda6);
    }

    public final void obtainPictorialMessageViewInfo() {
        if (isForBidToConnectInternal(true)) {
            KgdLogUtil.i("PictorialMediator", "obtainPictorialMessageViewInfo isForBidToConnect, return");
            return;
        }
        KgdLogUtil.i("PictorialMediator", "obtainPictorialMessageViewInfo");
        int currentUser = KeyguardUpdateMonitor.getCurrentUser();
        PictorialMediator$$ExternalSyntheticLambda9 pictorialMediator$$ExternalSyntheticLambda9 = new PictorialMediator$$ExternalSyntheticLambda9(0);
        pictorialMediator$$ExternalSyntheticLambda9.f$0 = this;
        pictorialMediator$$ExternalSyntheticLambda9.f$1 = currentUser;
        VarHandle.storeStoreFence();
        PictorialUtils.runOnWorkThread(pictorialMediator$$ExternalSyntheticLambda9);
    }

    public final void obtainPictorialWallpaper(String str, String str2, boolean z, boolean z2, boolean z3) {
        if (isForBidToConnectInternal(true)) {
            KgdLogUtil.i("PictorialMediator", "obtainPictorialWallpaper isForBidToConnect, return");
            return;
        }
        if (!this.mPictorialServiceConnected) {
            this.mIsNeedUpdateView = z2;
            PictorialMediator$$ExternalSyntheticLambda1 pictorialMediator$$ExternalSyntheticLambda1 = new PictorialMediator$$ExternalSyntheticLambda1(5);
            pictorialMediator$$ExternalSyntheticLambda1.f$0 = this;
            VarHandle.storeStoreFence();
            SystemUIThreadUtil.runOnUiThread(pictorialMediator$$ExternalSyntheticLambda1);
            KgdLogUtil.i("PictorialMediator", "obtainPictorialWallpaper mPictorialServiceConnected is not, return");
            return;
        }
        if (LogUtil.getNormalDebug()) {
            StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("obtainPictorialWallpaper-isLightFetch=", " force=", " r=", z, z2);
            sbM.append(str);
            sbM.append(" isPAodMode=");
            sbM.append(z3);
            KgdLogUtil.i("PictorialMediator", sbM.toString());
        }
        int currentUser = KeyguardUpdateMonitor.getCurrentUser();
        this.mWaitingPictorialData.setValue(Boolean.TRUE);
        PictorialMediator$$ExternalSyntheticLambda10 pictorialMediator$$ExternalSyntheticLambda10 = new PictorialMediator$$ExternalSyntheticLambda10();
        pictorialMediator$$ExternalSyntheticLambda10.f$0 = this;
        pictorialMediator$$ExternalSyntheticLambda10.f$1 = currentUser;
        pictorialMediator$$ExternalSyntheticLambda10.f$2 = z3;
        pictorialMediator$$ExternalSyntheticLambda10.f$3 = z;
        pictorialMediator$$ExternalSyntheticLambda10.f$4 = str;
        pictorialMediator$$ExternalSyntheticLambda10.f$5 = str2;
        pictorialMediator$$ExternalSyntheticLambda10.f$6 = z2;
        VarHandle.storeStoreFence();
        PictorialUtils.runOnWorkThread(pictorialMediator$$ExternalSyntheticLambda10);
    }

    public final void onPictorialActivateState() {
        this.mNotShowAgainSwitchOn = getNotShowAgainSwitchState();
        StringBuilder sb = new StringBuilder("onPictorialActivateState, notShowAgainSwitchState = ");
        sb.append(this.mNotShowAgainSwitchOn == 1);
        KgdLogUtil.i("PictorialMediator", sb.toString());
        Iterator it = PictorialStateWatcher.getInstance().mObserver.iterator();
        while (it.hasNext()) {
            ((PictorialStateWatcher.Observer) it.next()).onPictorialActivateChange();
        }
    }

    public final void registerAodStyleTypeObserver() {
        AodFeatureOption.INSTANCE.getClass();
        if (!AodFeatureOption.isSupportAod() || this.mAodStyleTypeObserverRegistered) {
            return;
        }
        AodStyleTypeSettingsObserver.Companion.getInstance().addListener(this.mAodStyleTypeListener);
        this.mAodStyleTypeObserverRegistered = true;
    }

    public final void registerCallback(IPictorialMediatorCallback iPictorialMediatorCallback) {
        if (!this.mCallbacks.contains(iPictorialMediatorCallback)) {
            this.mCallbacks.add(iPictorialMediatorCallback);
        }
        if (iPictorialMediatorCallback instanceof PictorialFunctionContainer) {
            this.mIsLockScreenAttached = true;
            KgdLogUtil.w("PictorialMediator", "PictorialFunctionContainer attached");
            if (isHKStyle() || isGlanceStyle() || isOplusGLStyle()) {
                updatePictorialGuideEntryInfo();
            }
        }
    }

    public final void registerPictorialObserver() {
        if (Process.myUserHandle() != UserHandle.SYSTEM) {
            KgdLogUtil.w("PictorialMediator", "registerPictorialObserver: Process != UserHandle.SYSTEM return");
            return;
        }
        if (isForBidToConnectInternal(true)) {
            KgdLogUtil.w("PictorialMediator", "registerPictorialObserver: isForBidToConnect, return");
            return;
        }
        KgdLogUtil.i("PictorialMediator", "registerPictorialObserver");
        try {
            if (!this.mRegisteredPictorialImageObserver) {
                ObserverDispatcher observerDispatcher = ObserverDispatcher.getInstance();
                observerDispatcher.registerObserver(this.mPictorialAppDataImpl.getPictorialImageUri(), this.mPictorialImageObserver);
                observerDispatcher.registerObserver(this.mPictorialAppDataImpl.getMessageViewShowUrl(), this.mPictorialMessageViewObserver);
                observerDispatcher.registerObserver(this.mPictorialAppDataImpl.getKeyguardPopbackAnimationUri(), this.mBackToKeyguardObserver);
                if (isGlanceStyle() || isHKStyle() || isOplusGLStyle()) {
                    if (this.mPictorialGuideUserObserver == null) {
                        this.mPictorialGuideUserObserver = new AnonymousClass4(this, new Handler(), 12);
                    }
                    observerDispatcher.registerObserver(this.mPictorialAppDataImpl.getUsePictorialViewShowUrl(), this.mPictorialGuideUserObserver);
                }
            }
            this.mRegisteredPictorialImageObserver = true;
        } catch (Exception e) {
            KgdLogUtil.i("PictorialMediator", "registerPictorialObserver exception " + e.getMessage());
        }
    }

    public final void registerSwitchObserver() {
        PictorialStatusObserver pictorialStatusObserver;
        try {
            if (isExpFlavorOne() && Process.myUserHandle() != UserHandle.SYSTEM) {
                KgdLogUtil.e("PictorialMediator", "registerSwitchObserver not system user,not register");
                return;
            }
            if (!this.mRegisterSwitchObserver) {
                ObserverDispatcher.getInstance().registerObserver(Settings.System.getUriFor("oplus_customize_pictorial_apply"), this.mMainSwitchObserver);
                ObserverDispatcher.getInstance().registerObserver(Settings.System.getUriFor("oplus_customize_pictorial_auto_play"), this.mAutoPlaySwitchObserver);
                ObserverDispatcher.getInstance().registerObserver(Settings.System.getUriFor("pictorial_festival_wallpaper_flag"), this.mPictorialFestivalWallpaperObserver);
                ObserverDispatcher.getInstance().registerObserver(Settings.System.getUriFor("oplus_customize_unlock_change_pkg"), this.mUnlockChangePkgObserver);
                ObserverDispatcher.getInstance().registerObserver(Settings.System.getUriFor("swipe_left_display_content"), this.mLeftSwipeSwitchObserver);
                ObserverDispatcher.getInstance().registerObserver(Settings.System.getUriFor("swipe_right_display_content"), this.mRightSwipeSwitchObserver);
                ObserverDispatcher.getInstance().registerObserver(Settings.System.getUriFor("kgd_pictorial_recommendation"), this.mKgdPictorialRecommendationObserver);
                KeyguardFeatureOption.INSTANCE.getClass();
                boolean z = KeyguardFeatureOption.isSupportOtherPictorial;
                AnonymousClass4 anonymousClass4 = this.mPictorialTaglineSwitchObserver;
                if (z && (pictorialStatusObserver = this.mPictorialStatusOberver) != null) {
                    pictorialStatusObserver.register();
                    if (isGlanceStyle() || KeyguardFeatureOption.isSupportOplusGLPictorial) {
                        ObserverDispatcher.getInstance().registerObserver(Settings.System.getUriFor("oplus_customize_pictorial_enable_play"), this.mEnablePlaySwitchObserver);
                    }
                    ObserverDispatcher.getInstance().registerObserver(Settings.System.getUriFor("oplus_customize_other_pictorial_available"), this.mOtherPictorialAvailableSwitchObserver);
                    ObserverDispatcher.getInstance().registerObserver(Settings.System.getUriFor("oplus_customize_pictorial_tagline"), anonymousClass4);
                }
                if (isFlavorOneExpLH()) {
                    ObserverDispatcher.getInstance().registerObserver(Settings.System.getUriFor("oplus_customize_pictorial_tagline"), anonymousClass4);
                }
                ObserverDispatcher.getInstance().registerObserver(Settings.System.getUriFor("pictorial_activate_not_show"), this.mPictorialActivateObServer);
                ObserverDispatcher.getInstance().registerObserver(Settings.System.getUriFor("hongshu_special_light_group"), this.mHongshuSpecialLightEnableObServer);
            }
            this.mRegisterSwitchObserver = true;
        } catch (Exception e) {
            KgdLogUtil.i("PictorialMediator", "registerSwitchObserver exception " + e.getMessage());
        }
    }

    public final void reportStatisticData(Bundle bundle) {
        if (!bundle.isEmpty() && this.mPictorialServiceConnected) {
            if (isForBidToConnectInternal(true)) {
                KgdLogUtil.i("PictorialMediator", "reportStatisticData, isForBidToConnect() is true, return");
                return;
            }
            int i = 0;
            if (isGlanceStyle() || isOplusGLStyle()) {
                FaceSwitchHelper faceSwitchHelper = FaceSwitchHelper.getInstance(this.mContext);
                KeyguardFeatureOption.INSTANCE.getClass();
                if (KeyguardFeatureOption.isSupportFaceUnlock() || OpUtils.isUseOpFacelock()) {
                    int i2 = (faceSwitchHelper.mFaceUnlockEnable || ((OplusFlavorManager) this.mOplusFlavorManagerLazy.get()).getPalmUnlockController().isPalmUnlockValid()) ? 1 : 0;
                    int i3 = (i2 == 0 || !((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusBiometricAuthController().isFaceAuthStayOnKeyguard(false)) ? 0 : 1;
                    bundle.putInt("face_unlock", i2);
                    bundle.putInt("face_unlock_stay_on", i3);
                    KgdLogUtil.i("PictorialMediator", "addFaceUnlockState : face unlock = " + bundle.getInt("face_unlock") + ", face unlock with swipe = " + bundle.getInt("face_unlock_stay_on"));
                } else {
                    KgdLogUtil.i("PictorialMediator", "faceUnlock not supported");
                }
            }
            if (isTbStyle() && bundle.getString("eventname") != null) {
                if (bundle.getString("eventname").equals("show")) {
                    bundle.putString("eventname", "notify_visible");
                }
                bundle.putLong("timestamp", System.currentTimeMillis());
            }
            PictorialMediator$$ExternalSyntheticLambda6 pictorialMediator$$ExternalSyntheticLambda6 = new PictorialMediator$$ExternalSyntheticLambda6(i);
            pictorialMediator$$ExternalSyntheticLambda6.f$0 = this;
            pictorialMediator$$ExternalSyntheticLambda6.f$1 = bundle;
            VarHandle.storeStoreFence();
            PictorialUtils.runOnWorkThread(pictorialMediator$$ExternalSyntheticLambda6);
        }
    }

    public final void reportVerticalStyleData(ContentValues contentValues, boolean z) {
        KgdLogUtil.i("Glance_Nudge", "reportVerticalStyleData,isNudge = " + z);
        if (!contentValues.isEmpty() && this.mPictorialServiceConnected) {
            if (isForBidToConnectInternal(true)) {
                KgdLogUtil.i("PictorialMediator", "reportVerticalStyleData, isForBidToConnect() is true, return");
                return;
            }
            PictorialMediator$.ExternalSyntheticLambda16 externalSyntheticLambda16 = new PictorialMediator$.ExternalSyntheticLambda16();
            externalSyntheticLambda16.f$0 = this;
            externalSyntheticLambda16.f$1 = z;
            externalSyntheticLambda16.f$2 = contentValues;
            VarHandle.storeStoreFence();
            PictorialUtils.runOnWorkThread(externalSyntheticLambda16);
        }
    }

    public final void restartPictorialWallpaper() {
        createPictorialFactory();
        Iterator it = PictorialStateWatcher.getInstance().mObserver.iterator();
        while (it.hasNext()) {
            ((PictorialStateWatcher.Observer) it.next()).getClass();
        }
        Iterator it2 = PictorialStateWatcher.getInstance().mObserver.iterator();
        while (it2.hasNext()) {
            ((PictorialStateWatcher.Observer) it2.next()).startClearWallpaper();
        }
        boolean z = this.mPictorialAppDataImpl.mPictorialExist;
        this.mPictorialExist = z;
        if (z) {
            registerSwitchObserver();
        }
        registerPictorialObserver();
        registerAodStyleTypeObserver();
        firstProviderService(false);
        setDefaultSetting();
        this.mPictorialFactory.getPictorialBuriedHelper().switchPictorialApp(this.mContext);
        this.mPictorialFactory.registerFingerprintTipsShowCallback();
        ((ArrayList) this.mCallbacks).forEach(new PictorialMediator$$ExternalSyntheticLambda11(0));
    }

    public final void setDefaultSetting() {
        ContentResolver contentResolver = this.mContentResolver;
        Settings.System.putIntForUser(contentResolver, "keyguard_apk_show_pictorial", 1, KeyguardUpdateMonitor.getCurrentUser());
        Settings.System.putIntForUser(contentResolver, "pictorial_keyguard_decouping_version", 7, KeyguardUpdateMonitor.getCurrentUser());
    }

    public void setFestivalWallpaperSkipFlag(boolean z) {
        this.mSkipClosePictorialFestivalWallpaper.set(z);
    }

    public void setIsSupportLeftSwipe(boolean z) {
        this.mIsSupportLeftSwipe = z;
    }

    public void setLastDrawBitmapFromPictorial(boolean z) {
        this.mIsLastDrawBitmapFromPictorial = z;
    }

    public void setNotShowAgainSwitchState(boolean z) {
        this.mNotShowAgainSwitchOn = z ? 1 : 0;
        Settings.System.putIntForUser(this.mContentResolver, "pictorial_activate_not_show", z ? 1 : 0, KeyguardUpdateMonitor.getCurrentUser());
    }

    public void setOnScreenFingerprintUiMech(OnScreenFingerprintUiMech onScreenFingerprintUiMech) {
        this.mOnScreenFingerprintUiMech = onScreenFingerprintUiMech;
    }

    public void setPanelViewController(NotificationPanelViewController notificationPanelViewController) {
        this.mPanelViewController = notificationPanelViewController;
    }

    public void setPictorialAppSwitching(boolean z) {
        this.mPictorialAppSwitching = z;
    }

    public void setPictorialEngineContainer(PictorialEngineContainer pictorialEngineContainer) {
        this.mPictorialEngineContainer = pictorialEngineContainer;
    }

    public void setTipsChangeWatcher(TipsChangeWatcher tipsChangeWatcher) {
        this.mTipsChangeWatcher = tipsChangeWatcher;
    }

    public final void stopProviderService() {
        KgdLogUtil.i("PictorialMediator", "stopProviderService begin");
        if (!this.mPictorialServiceConnected) {
            KgdLogUtil.i("PictorialMediator", "stopProviderService not connect, return");
            return;
        }
        OCDHandler oCDHandler = PictorialUtils.sPictorialHandler;
        AnonymousClass1 anonymousClass1 = this.mStopPictorialServiceRunnable;
        if (oCDHandler.hasCallbacks(anonymousClass1)) {
            KgdLogUtil.i("PictorialMediator", "stopProviderService hasSameTaskInWorkLooper, return");
        } else {
            PictorialUtils.runOnWorkThread(anonymousClass1);
        }
    }

    public final void unRegisterPictorialObserver() {
        try {
            if (this.mRegisteredPictorialImageObserver) {
                ObserverDispatcher observerDispatcher = ObserverDispatcher.getInstance();
                observerDispatcher.unregisterObserver(this.mPictorialImageObserver);
                observerDispatcher.unregisterObserver(this.mPictorialMessageViewObserver);
                observerDispatcher.unregisterObserver(this.mBackToKeyguardObserver);
                AnonymousClass4 anonymousClass4 = this.mPictorialGuideUserObserver;
                if (anonymousClass4 != null) {
                    observerDispatcher.unregisterObserver(anonymousClass4);
                }
            }
            this.mRegisteredPictorialImageObserver = false;
        } catch (Exception e) {
            KgdLogUtil.i("PictorialMediator", "unRegisterPictorialObserver exception " + e.getMessage());
        }
    }

    public final void unRegisterSwitchObserver() {
        PictorialStatusObserver pictorialStatusObserver;
        try {
            if (this.mRegisterSwitchObserver) {
                ObserverDispatcher.getInstance().unregisterObserver(this.mMainSwitchObserver);
                ObserverDispatcher.getInstance().unregisterObserver(this.mAutoPlaySwitchObserver);
                ObserverDispatcher.getInstance().unregisterObserver(this.mUnlockChangePkgObserver);
                ObserverDispatcher.getInstance().unregisterObserver(this.mLeftSwipeSwitchObserver);
                ObserverDispatcher.getInstance().unregisterObserver(this.mRightSwipeSwitchObserver);
                ObserverDispatcher.getInstance().unregisterObserver(this.mKgdPictorialRecommendationObserver);
                KeyguardFeatureOption.INSTANCE.getClass();
                if (KeyguardFeatureOption.isSupportOtherPictorial && (pictorialStatusObserver = this.mPictorialStatusOberver) != null) {
                    pictorialStatusObserver.unregister();
                }
                AnonymousClass4 anonymousClass4 = this.mPictorialActivateObServer;
                if (anonymousClass4 != null) {
                    ObserverDispatcher.getInstance().unregisterObserver(anonymousClass4);
                }
                boolean zIsFlavorOneExpLH = isFlavorOneExpLH();
                AnonymousClass4 anonymousClass5 = this.mPictorialTaglineSwitchObserver;
                if (zIsFlavorOneExpLH) {
                    ObserverDispatcher.getInstance().unregisterObserver(anonymousClass5);
                }
                if (KeyguardFeatureOption.isSupportOtherPictorial || KeyguardFeatureOption.isSupportOplusGLPictorial) {
                    ObserverDispatcher.getInstance().unregisterObserver(anonymousClass5);
                    if (isGlanceStyle() || KeyguardFeatureOption.isSupportOplusGLPictorial) {
                        ObserverDispatcher.getInstance().unregisterObserver(this.mEnablePlaySwitchObserver);
                    }
                }
                ObserverDispatcher.getInstance().unregisterObserver(this.mHongshuSpecialLightEnableObServer);
            }
            this.mRegisterSwitchObserver = false;
        } catch (Exception e) {
            KgdLogUtil.i("PictorialMediator", "unRegisterSwitchObserver exception " + e.getMessage());
        }
    }

    public final void unregisterAodStyleTypeObserver() {
        if (this.mAodStyleTypeObserverRegistered) {
            AodStyleTypeSettingsObserver.Companion.getInstance().removeListener(this.mAodStyleTypeListener);
            this.mAodStyleTypeObserverRegistered = false;
        }
    }

    public final void unregisterCallback(IPictorialMediatorCallback iPictorialMediatorCallback) {
        if (this.mCallbacks.contains(iPictorialMediatorCallback)) {
            this.mCallbacks.remove(iPictorialMediatorCallback);
        }
        if (iPictorialMediatorCallback instanceof PictorialFunctionContainer) {
            this.mIsLockScreenAttached = false;
            KgdLogUtil.w("PictorialMediator", "PictorialFunctionContainer detached");
        }
    }

    public final void updateMetaData() {
        Context context = this.mContext;
        if (context == null) {
            this.mIsSupportDisableAlive = false;
            return;
        }
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(getPictorialPackageName(), 128).metaData;
            this.mIsSupportDisableAlive = this.mPictorialMediatorHelper.isSupportDisableAlive(this.mContext, bundle);
            this.mIsMetaDataSupportRightSwipe = bundle.getBoolean("pictorial_swipe_right_support", false);
            if ("com.heytap.pictorial".equals(getPictorialPackageName())) {
                this.mPictorialAppDataImpl.setSupportPictorialTipsFromMeta(bundle.getBoolean("SupportDisplayTextOnScreenWake", false));
            }
        } catch (Exception e) {
            KgdLogUtil.i("PictorialMediator", e.getMessage());
        }
        ensureDisableAliveSettingsObserver();
    }

    public final void updatePictorialFestivalWallpaperReady() {
        this.mIsPictorialFestivalWallpaperReady = getPictorialFestivalWallpaperState() == 1;
    }

    public final void updatePictorialGuideEntryInfo() {
        int i = 1;
        if (isForBidToConnectInternal(true)) {
            KgdLogUtil.i("PictorialMediator", "updatePictorialGuideEntryInfo isForBidToConnect, return");
            return;
        }
        if (!this.mIsLockScreenAttached) {
            KgdLogUtil.i("PictorialMediator", "lock screen not acttach window");
            return;
        }
        this.mNotShowAgainSwitchOn = getNotShowAgainSwitchState();
        if (isOplusGLStyle() && !this.mEnablePlaySwitchOn) {
            this.mNotShowAgainSwitchOn = 0;
        }
        if (this.mNotShowAgainSwitchOn != 0) {
            return;
        }
        KgdLogUtil.i("PictorialMediator", "updatePictorialGuideEntryInfo");
        int currentUser = KeyguardUpdateMonitor.getCurrentUser();
        PictorialMediator$$ExternalSyntheticLambda9 pictorialMediator$$ExternalSyntheticLambda9 = new PictorialMediator$$ExternalSyntheticLambda9(i);
        pictorialMediator$$ExternalSyntheticLambda9.f$0 = this;
        pictorialMediator$$ExternalSyntheticLambda9.f$1 = currentUser;
        VarHandle.storeStoreFence();
        PictorialUtils.runOnWorkThread(pictorialMediator$$ExternalSyntheticLambda9);
    }

    public void updateRomConfigForRus(List<PictorialAppInfo> list) {
        KeyguardFeatureOption.INSTANCE.getClass();
        boolean z = KeyguardFeatureOption.isSupportOplusGLPictorial;
        PictorialAppDataImpl pictorialAppDataImpl = this.mPictorialAppDataImpl;
        if (!z) {
            pictorialAppDataImpl.updateRomConfigForRus(list);
            return;
        }
        pictorialAppDataImpl.updateRomConfigForRus(list, isMainSwitchStatusOn());
        PictorialAppInfo nextPictorialAppInfo = this.mPictorialAppDataImpl.getNextPictorialAppInfo();
        StringBuilder sb = new StringBuilder("need updateSwitchStateFromRus:");
        sb.append(nextPictorialAppInfo);
        sb.append(",mMainSwitchOn:");
        sb.append(this.mMainSwitchOn);
        sb.append(",mEnablePlaySwitchOn:");
        OplusChargeAnimController$.ExternalSyntheticOutline0.m("PictorialMediator", sb, this.mEnablePlaySwitchOn);
        if (nextPictorialAppInfo != null) {
            if (nextPictorialAppInfo.getPkgName().equals("com.glance.lockscreenOp") && this.mMainSwitchOn) {
                closeMainSwtich();
            } else if (nextPictorialAppInfo.getPkgName().equals("com.heytap.pictorial") && this.mEnablePlaySwitchOn) {
                Settings.System.putIntForUser(this.mContentResolver, "oplus_customize_pictorial_enable_play", 0, KeyguardUpdateMonitor.getCurrentUser());
                this.mEnablePlaySwitchOn = false;
            }
            sendEmptyMessage(6);
        }
    }

    public final void updateSupportSwipeLeftStatus() {
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isExpRegion()) {
            FlavorOneFeatureOption.INSTANCE.getClass();
            if (FlavorOneFeatureOption.isFlavorOneDevice()) {
                setIsSupportLeftSwipe(true);
                return;
            }
        }
        String pictorialActivateClassName = this.mPictorialAppDataImpl.getPictorialActivateClassName();
        PackageManager packageManager = this.mPackageManager;
        PictorialMediator$$ExternalSyntheticLambda12 pictorialMediator$$ExternalSyntheticLambda12 = new PictorialMediator$$ExternalSyntheticLambda12();
        pictorialMediator$$ExternalSyntheticLambda12.f$0 = this;
        pictorialMediator$$ExternalSyntheticLambda12.f$2 = pictorialActivateClassName;
        pictorialMediator$$ExternalSyntheticLambda12.f$3 = packageManager;
        VarHandle.storeStoreFence();
        PictorialUtils.runOnWorkThread(pictorialMediator$$ExternalSyntheticLambda12);
    }

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator$4, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass4 extends ContentObserver {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ PictorialMediator this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public /* synthetic */ AnonymousClass4(PictorialMediator pictorialMediator, Handler handler, int i) {
            super(handler);
            this.$r8$classId = i;
            this.this$0 = pictorialMediator;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            switch (this.$r8$classId) {
                case 0:
                    this.this$0.onPictorialActivateState();
                    break;
                case 1:
                default:
                    super.onChange(z);
                    break;
                case 2:
                    super.onChange(z);
                    KgdLogUtil.i("PictorialMediator", "mPictorialMessageViewObserver onChange");
                    this.this$0.obtainPictorialMessageViewInfo();
                    break;
                case 3:
                    super.onChange(z);
                    KgdLogUtil.i("PictorialMediator", "mBackToKeyguardObserver onChange");
                    PictorialMediator pictorialMediator = this.this$0;
                    Lazy lazy = pictorialMediator.mKeyguardViewMediator;
                    KgdLogUtil.i("PictorialMediator", "notifyBackToKeyguard");
                    if (pictorialMediator.isForBidToConnectInternal(true)) {
                        KgdLogUtil.i("PictorialMediator", "notifyBackToKeyguard isForBidToConnect return");
                        break;
                    } else {
                        Iterator it = PictorialStateWatcher.getInstance().mObserver.iterator();
                        while (it.hasNext()) {
                            ((PictorialStateWatcher.Observer) it.next()).onBackToKeyguard();
                        }
                        if (lazy != null && !((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mShowing) {
                            KeyguardViewMediator.5 viewMediatorCallback = ((KeyguardViewMediator) lazy.get()).getViewMediatorCallback();
                            viewMediatorCallback.getClass();
                            KgdLogUtil.i("KeyguardViewMediator", "doKeyguardNotifyLock callback");
                            viewMediatorCallback.this$0.doKeyguardLocked((Bundle) null, true);
                            break;
                        }
                    }
                    break;
                case 4:
                    PictorialMediator.m7$$Nest$monChangeMainSwitchState(this.this$0);
                    PictorialMediator.m9$$Nest$mprintPictorialLoaderEnableParam(this.this$0, "mMainSwitchObserver onChange");
                    this.this$0.getPictorialDefaultSetting();
                    break;
                case 5:
                    PictorialMediator pictorialMediator2 = this.this$0;
                    int i = pictorialMediator2.mAutoPlaySwitchKeyState;
                    int autoPlaySwitchState = pictorialMediator2.getAutoPlaySwitchState();
                    if (autoPlaySwitchState != i) {
                        PictorialMediator.m5$$Nest$monChangeAutoSwitchState(this.this$0);
                        this.this$0.getPictorialDefaultSetting();
                        if (this.this$0.isDeviceProvisioned()) {
                            PictorialMediator$$ExternalSyntheticLambda9 pictorialMediator$$ExternalSyntheticLambda9 = new PictorialMediator$$ExternalSyntheticLambda9(2);
                            pictorialMediator$$ExternalSyntheticLambda9.f$0 = this;
                            pictorialMediator$$ExternalSyntheticLambda9.f$1 = autoPlaySwitchState;
                            VarHandle.storeStoreFence();
                            PictorialUtils.runOnWorkThread(pictorialMediator$$ExternalSyntheticLambda9);
                        }
                        PictorialMediator.m9$$Nest$mprintPictorialLoaderEnableParam(this.this$0, "mAutoPlaySwitchObserver onChange");
                    } else {
                        KgdLogUtil.i("PictorialMediator", "mAutoPlaySwitchObserver onChange: value not changed, skip execution");
                    }
                    break;
                case 6:
                    KgdLogUtil.i("PictorialMediator", "mEnablePlaySwitchObserver onChange");
                    PictorialMediator.m6$$Nest$monChangeEnableSwitchState(this.this$0);
                    break;
                case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                    PictorialMediator pictorialMediator3 = this.this$0;
                    AtomicBoolean atomicBoolean = PictorialMediator.sPictorialFestivalWallpaperUsed;
                    pictorialMediator3.checkIsSupportPictorialFestivalWallpaper();
                    pictorialMediator3.updatePictorialFestivalWallpaperReady();
                    if (pictorialMediator3.mIsSupportPictorialFestivalWallpaper) {
                        boolean zIsConfigPackageAsGalleryClock = ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getKeyguardStyleClockController().isConfigPackageAsGalleryClock();
                        boolean zIsPanoramicAodEnable = SmoothTransitionController.isPanoramicAodEnable(pictorialMediator3.mContext);
                        boolean z2 = pictorialMediator3.mIsPictorialFestivalWallpaperReady && !zIsConfigPackageAsGalleryClock;
                        pictorialMediator3.mNeedReleaseForFestivalPaod = z2 && zIsPanoramicAodEnable;
                        if (z2 && !zIsPanoramicAodEnable) {
                            KgdLogUtil.d("PictorialMediator", "onChangePictorialFestivalWallpaperState releaseDrawableAndInfo for non-PAOD festival wallpaper scene");
                            pictorialMediator3.mPictorialFactory.getPictorialLoader().releaseDrawableAndInfo();
                        }
                        PictorialStateWatcher pictorialStateWatcher = PictorialStateWatcher.getInstance();
                        boolean z3 = pictorialMediator3.mIsPictorialFestivalWallpaperReady;
                        Iterator it2 = pictorialStateWatcher.mObserver.iterator();
                        while (it2.hasNext()) {
                            ((PictorialStateWatcher.Observer) it2.next()).onPictorialFestivalWallpaperChange(z3);
                        }
                        if (!zIsConfigPackageAsGalleryClock) {
                            if (pictorialMediator3.mIsPictorialFestivalWallpaperReady) {
                                PictorialMediator.sPictorialFestivalWallpaperUsed.set(true);
                                PictorialStateWatcher.getInstance().dispatchPictorialFestivalWallpaperUsageChange(true);
                            } else {
                                PictorialMediator.sPictorialFestivalWallpaperUsed.set(false);
                                PictorialStateWatcher.getInstance().dispatchPictorialFestivalWallpaperUsageChange(false);
                            }
                        }
                    } else {
                        pictorialMediator3.mNeedReleaseForFestivalPaod = false;
                        KgdLogUtil.i("PictorialMediator", "onChangePictorialFestivalWallpaperState festival wallpaper is not ready");
                    }
                    PictorialMediator.m9$$Nest$mprintPictorialLoaderEnableParam(this.this$0, "mPictorialFestivalWallpaperObserver onChange");
                    break;
                case 8:
                    boolean leftSwipeSwitchState = this.this$0.getLeftSwipeSwitchState();
                    OplusLockIconView$.ExternalSyntheticOutline0.m("Pictorial LeftSwipeSwitch change, mLeftSwipeSwitchOn = ", "PictorialMediator", leftSwipeSwitchState);
                    this.this$0.mLeftSwipeSwitchOn = leftSwipeSwitchState;
                    PictorialStateWatcher.getInstance().dispatchPictorialSwipeSwitchChange();
                    break;
                case 9:
                    boolean rightSwipeSwitchState = this.this$0.getRightSwipeSwitchState();
                    OplusLockIconView$.ExternalSyntheticOutline0.m("Pictorial RightSwipeSwitch change, mRightSwipeSwitchOn = ", "PictorialMediator", rightSwipeSwitchState);
                    this.this$0.mRightSwipeSwitchOn = rightSwipeSwitchState;
                    PictorialStateWatcher.getInstance().dispatchPictorialSwipeSwitchChange();
                    break;
                case 10:
                    PictorialMediator pictorialMediator4 = this.this$0;
                    PictorialCursorHelper pictorialCursorHelper = pictorialMediator4.mPictorialCursorHelper;
                    if (pictorialCursorHelper instanceof OplusPictorialCursorHelper) {
                        pictorialMediator4.mKgdPictorialRecommendation = ((OplusPictorialCursorHelper) pictorialCursorHelper).getKgdPictorialRecommendationSetting(pictorialMediator4.mContext);
                    }
                    break;
                case 11:
                    int intForUser = Settings.System.getIntForUser(this.this$0.mContext.getContentResolver(), "oplus_disable_pictorial_alive", 0, -2);
                    StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m("onChange ", intForUser, " setting=", " showing=", z);
                    sbM.append(this.this$0.mKeyguardShowing);
                    sbM.append(" connected=");
                    sbM.append(this.this$0.mPictorialServiceConnected);
                    sbM.append(" awaiting=");
                    OplusChargeAnimController$.ExternalSyntheticOutline0.m("PictorialMediator", sbM, this.this$0.mAwaitingDisableAliveStopAfterUnlock);
                    if (intForUser == 1 && this.this$0.mKeyguardShowing && this.this$0.isSupportDisableAliveOrOplusGLStyle() && !this.this$0.isAutoPlaySwitchOn()) {
                        PictorialMediator pictorialMediator5 = this.this$0;
                        if (pictorialMediator5.mAwaitingDisableAliveStopAfterUnlock) {
                            pictorialMediator5.mAwaitingDisableAliveStopAfterUnlock = false;
                            if (!pictorialMediator5.mPictorialServiceConnected) {
                                pictorialMediator5.mHandler.removeCallbacks(pictorialMediator5.mReloadPictorialRunnable);
                                this.this$0.mReloadPictorialRunnable.run();
                            } else {
                                KgdLogUtil.i("PictorialMediator", "onChange force stop+rebind on keyguard");
                                this.this$0.stopProviderService();
                            }
                        }
                    }
                    this.this$0.doLiveCheck();
                    break;
                case 12:
                    super.onChange(z);
                    KgdLogUtil.i("PictorialMediator", "mPictorialGuideUserObserver onChange");
                    this.this$0.updatePictorialGuideEntryInfo();
                    break;
                case 13:
                    PictorialMediator pictorialMediator6 = this.this$0;
                    AtomicBoolean atomicBoolean2 = PictorialMediator.sPictorialFestivalWallpaperUsed;
                    boolean z4 = pictorialMediator6.getSwitchStatusFromSettings("hongshu_special_light_group") == 1;
                    OplusLockIconView$.ExternalSyntheticOutline0.m("specialLightEnableChange, notSwitchState = ", "PictorialMediator", z4);
                    KeyguardFeatureOption.INSTANCE.setHongshuSpecialLightEnable(z4);
                    break;
                case 14:
                    PictorialMediator pictorialMediator7 = this.this$0;
                    AtomicBoolean atomicBoolean3 = PictorialMediator.sPictorialFestivalWallpaperUsed;
                    int switchStatusFromSettings = pictorialMediator7.getSwitchStatusFromSettings("oplus_customize_other_pictorial_available");
                    KgdLogUtil.d("PictorialMediator", "other pictorial available switch availableState:" + switchStatusFromSettings);
                    if (switchStatusFromSettings == 0) {
                        this.this$0.mPictorialAppDataImpl.updateCurrentPictorialToLH();
                        this.this$0.setPictorialAppSwitching(true);
                    }
                    break;
                case 15:
                    PictorialMediator.m8$$Nest$monChangeTaglineSwitchState(this.this$0);
                    break;
                case 16:
                    KgdLogUtil.i("PictorialMediator", "unlock pkg change needReloadOplusKeyguard");
                    if (!KeyguardUtils.Companion.isUiengineKeyguardInSettings(this.this$0.mContext)) {
                        PictorialMediator.m5$$Nest$monChangeAutoSwitchState(this.this$0);
                    }
                    break;
            }
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            String queryParameter;
            switch (this.$r8$classId) {
                case 1:
                    super.onChange(z, uri);
                    KgdLogUtil.i("PictorialMediator", "mPictorialImageObserver onChange uri = " + uri);
                    if (uri != null) {
                        queryParameter = uri.getQueryParameter("p_params");
                        if (TextUtils.equals("11", uri.getQueryParameter("p_what"))) {
                            this.this$0.getPictorialDefaultSetting();
                        }
                    } else {
                        queryParameter = null;
                    }
                    String str = queryParameter;
                    boolean z2 = ((KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class)).mKeyguardShowing;
                    PictorialMediator pictorialMediator = this.this$0;
                    if (!z2) {
                        pictorialMediator.obtainPictorialWallpaper("pictorial_change", str, true, true, false);
                    } else {
                        pictorialMediator.obtainPictorialWallpaper("pictorial_change", str, false, false, false);
                    }
                    break;
                default:
                    super.onChange(z, uri);
                    break;
            }
        }
    }
}
