package com.oplus.systemui.keyguard.wallpaper.pictorial;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.OplusKeyguardDependencyEx;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.keyguard.wallpaper.KeyguardWallpaperInfo;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.keyguard.Lifecycle;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.lifecycle.UnlockViewLifecycleOwner$;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.oplus.charge.viewmodel.OplusChargeAnimController$;
import com.oplus.keyguard.IOplusKeyguardStyleServiceCallback;
import com.oplus.systemui.aod.display.AODDisplayUtil;
import com.oplus.systemui.aod.display.BaseDisplayUtil;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplus.systemui.keyguard.wallpaper.BaseWallpaperHelper;
import com.oplus.systemui.keyguard.wallpaper.OplusWallpaperBitmapUtil;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialLoader;
import com.oplus.systemui.keyguard.watcher.OplusKgdImmersiveController;
import com.oplus.systemui.keyguard.watcher.PictorialStateWatcher;
import com.oplus.systemui.keyguard.watcher.WallpaperChangeWatcher;
import com.oplusos.keyguard.utils.KeyguardThreadUtil;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.feature.KeyguardFeatureOption;
import com.oplusos.systemui.navigationbar.devicestate.DeviceStateController;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PictorialWallpaperHelper extends BaseWallpaperHelper {
    public static volatile PictorialWallpaperHelper sInstance;
    public volatile int mDisplayState;
    public final AnonymousClass2 mDisplayStateListener;
    public boolean mFoldStateChange;
    public boolean mHasFetchWallpaperWhenKeyguardShow;
    public volatile boolean mHasWallpaperInit;
    public final OplusKgdImmersiveController mImmersiveController;
    public final KeyguardStateControllerImpl mKeyguardStateControllerImpl;
    public final IOplusKeyguardStyleServiceCallback mKssCallback;
    public KeyguardUpdateMonitorCallback mMonitorCallback;
    public volatile String mPanoramicAodOldFileUri;
    public volatile String mPictorialCacheKey;
    public PictorialLoader mPictorialLoader;
    public final PictorialWallpaperHelper$$ExternalSyntheticLambda1 mPictorialLoaderCallback;
    public final PictorialMediator mPictorialMediator;
    public final AnonymousClass5 mPictorialMediatorCallback;
    public final AnonymousClass6 mPictorialStateWatcherObserver;
    public final List mPreloadChangeListeners;
    public final ConcurrentHashMap mSaveBitmapLatchHashMap;
    public final ScreenLifecycle mScreenLifecycle;
    public final AnonymousClass3 mScreenObserver;
    public long mScreenOffTime;
    public volatile boolean mShouldUpdateUiWhenPanoramicAod;
    public final AnonymousClass1 mStateChangedListener;
    public final KeyguardUpdateMonitor mUpdateMonitor;
    public KeyguardWallpaperInfo mWallpaperInfo;
    public final AnonymousClass7 mWallpaperObserver;

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperHelper$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements DeviceStateController.StateChangedListener {
        public int mLastFoldState;
        public /* synthetic */ PictorialWallpaperHelper this$0;

        @Override // com.oplusos.systemui.navigationbar.devicestate.DeviceStateController.StateChangedListener
        public final void onStateChanged(int i) {
            if ((i == 0 || i == 3) && this.mLastFoldState != i) {
                this.this$0.mFoldStateChange = true;
                this.mLastFoldState = i;
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperHelper$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements BaseDisplayUtil.OplusDisplayStateListener {
        public /* synthetic */ PictorialWallpaperHelper this$0;

        public final void onDisplayChanged(int i) {
            if (this.this$0.mDisplayState != i) {
                this.this$0.mDisplayState = i;
                int i2 = 1;
                if (this.this$0.mDisplayState == 1) {
                    Executor mainExecutor = ((BaseWallpaperHelper) this.this$0).mContext.getMainExecutor();
                    PictorialWallpaperHelper$$ExternalSyntheticLambda2 pictorialWallpaperHelper$$ExternalSyntheticLambda2 = new PictorialWallpaperHelper$$ExternalSyntheticLambda2(i2);
                    pictorialWallpaperHelper$$ExternalSyntheticLambda2.f$0 = this;
                    VarHandle.storeStoreFence();
                    mainExecutor.execute(pictorialWallpaperHelper$$ExternalSyntheticLambda2);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperHelper$3, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass3 implements ScreenLifecycle.Observer {
        public /* synthetic */ PictorialWallpaperHelper this$0;

        /* JADX WARN: Code duplicated, block: B:21:0x0098  */
        /* JADX WARN: Code duplicated, block: B:23:0x009c  */
        /* JADX WARN: Code duplicated, block: B:27:0x00ae  */
        /* JADX WARN: Code duplicated, block: B:29:0x00b4  */
        public final void onScreenTurnedOff() {
            PictorialWallpaperHelper pictorialWallpaperHelper = this.this$0;
            PictorialMediator pictorialMediator = pictorialWallpaperHelper.mPictorialMediator;
            KeyguardStateControllerImpl keyguardStateControllerImpl = pictorialWallpaperHelper.mKeyguardStateControllerImpl;
            boolean z = keyguardStateControllerImpl != null ? keyguardStateControllerImpl.mShowing : false;
            StringBuilder sb = new StringBuilder("onScreenTurnedOff, mHasAlreadyFetchFromSlidePage = ");
            sb.append(pictorialWallpaperHelper.mPictorialLoader.hasAlreadyFetchFromSlidePage());
            sb.append(", mHasFetchWallpaperWhenKeyguardShow = ");
            KeyguardLogger$.ExternalSyntheticOutline0.m(", isKeyguardShowing = ", ", mFoldStateChange = ", sb, pictorialWallpaperHelper.mHasFetchWallpaperWhenKeyguardShow, z);
            sb.append(pictorialWallpaperHelper.mFoldStateChange);
            sb.append(", isPictorialAppSwitching = ");
            sb.append(pictorialMediator.mPictorialAppSwitching);
            KgdLogUtil.i("PictorialWallpaperHelper", sb.toString());
            pictorialWallpaperHelper.mScreenOffTime = System.currentTimeMillis();
            KgdLogUtil.i("PictorialWallpaperHelper", "[RapidScreen] onScreenTurnedOff: record screen off time=" + pictorialWallpaperHelper.mScreenOffTime);
            if (pictorialWallpaperHelper.mFoldStateChange) {
                pictorialWallpaperHelper.mFoldStateChange = false;
                return;
            }
            if (SmoothTransitionController.isPanoramicAodEnable(((BaseWallpaperHelper) pictorialWallpaperHelper).mContext)) {
                KgdLogUtil.d("PictorialWallpaperHelper", "onScreenTurnedOff return isPanoramicAodEnable");
                return;
            }
            PictorialWallpaperStatistic.statisticExposeEnd(6, pictorialWallpaperHelper.mWallpaperInfo, false);
            if (!pictorialWallpaperHelper.mHasFetchWallpaperWhenKeyguardShow) {
                KeyguardFeatureOption.INSTANCE.getClass();
                if (KeyguardFeatureOption.isSupportOtherPictorial) {
                    if (!z) {
                        FeatureOption.INSTANCE.getClass();
                        if (FeatureOption.isDragonfly()) {
                            if (!pictorialMediator.mPictorialAppSwitching) {
                                pictorialWallpaperHelper.updateAndFetchWallpaper("screen_off", true);
                            }
                        }
                    } else if (!pictorialMediator.mPictorialAppSwitching) {
                        pictorialWallpaperHelper.updateAndFetchWallpaper("screen_off", true);
                    }
                } else if (!z) {
                    FeatureOption.INSTANCE.getClass();
                    if (FeatureOption.isDragonfly()) {
                        if (pictorialMediator.isMainSwitchStatusOn()) {
                            pictorialWallpaperHelper.updateAndFetchWallpaper("screen_off", false);
                        }
                    }
                } else if (pictorialMediator.isMainSwitchStatusOn()) {
                    pictorialWallpaperHelper.updateAndFetchWallpaper("screen_off", false);
                }
            }
            pictorialWallpaperHelper.mHasFetchWallpaperWhenKeyguardShow = false;
        }

        public final void onScreenTurnedOn() {
            KgdLogUtil.i("PictorialWallpaperHelper", "onScreenTurnedOn");
            PictorialWallpaperHelper pictorialWallpaperHelper = this.this$0;
            PictorialWallpaperHelper.m13$$Nest$mhandleStatisticExposeStart(pictorialWallpaperHelper);
            PictorialWallpaperStatistic.updateFirstUploadScreenState(((BaseWallpaperHelper) pictorialWallpaperHelper).mContext, pictorialWallpaperHelper.mWallpaperInfo);
            PictorialWallpaperStatistic.statisticPictorialSwitchState(((BaseWallpaperHelper) pictorialWallpaperHelper).mContext, pictorialWallpaperHelper.mWallpaperInfo);
        }

        public final void onScreenTurningOn() {
            KgdLogUtil.i("PictorialWallpaperHelper", "onScreenTurningOn");
            this.this$0.mHasFetchWallpaperWhenKeyguardShow = false;
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperHelper$5, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass5 implements PictorialMediator.IPictorialMediatorCallback {
        public /* synthetic */ PictorialWallpaperHelper this$0;

        @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator.IPictorialMediatorCallback
        public final void onSwitchPictorialApp() {
            PictorialWallpaperHelper pictorialWallpaperHelper = this.this$0;
            pictorialWallpaperHelper.mPictorialLoader = pictorialWallpaperHelper.mPictorialMediator.getPictorialFactory().getPictorialLoader();
            List list = pictorialWallpaperHelper.mPreloadChangeListeners;
            PictorialWallpaperHelper$5$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new PictorialWallpaperHelper$5$.ExternalSyntheticLambda0();
            externalSyntheticLambda0.f$0 = this;
            VarHandle.storeStoreFence();
            ((ArrayList) list).forEach(externalSyntheticLambda0);
            pictorialWallpaperHelper.mPictorialLoader.setPictorialLoaderCallback(pictorialWallpaperHelper.mPictorialLoaderCallback);
        }

        @Override // com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialMediator.IPictorialMediatorCallback
        public final void onWallpaperChanged(List list, boolean z) {
            if (list == null || list.isEmpty()) {
                return;
            }
            PictorialWallpaperHelper.m14$$Nest$mwallpaperChange(this.this$0, list, z);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperHelper$6, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass6 implements PictorialStateWatcher.Observer {
        public /* synthetic */ PictorialWallpaperHelper this$0;

        @Override // com.oplus.systemui.keyguard.watcher.PictorialStateWatcher.Observer
        public final void onBackToKeyguard() {
            PictorialWallpaperHelper pictorialWallpaperHelper = this.this$0;
            if (pictorialWallpaperHelper.mKeyguardStateControllerImpl.mShowing) {
                return;
            }
            pictorialWallpaperHelper.mPictorialLoader.setHasAlreadyFetchFromSlidePage(false);
            KgdLogUtil.d("PictorialWallpaperHelper", "onBackToKeyguard update wallpaper");
            pictorialWallpaperHelper.setCurrentAndPreloadNext();
            pictorialWallpaperHelper.mPictorialLoader.setHasAlreadyFetchFromSlidePage(true);
        }

        @Override // com.oplus.systemui.keyguard.watcher.PictorialStateWatcher.Observer
        public final void onPictorialMainSwitchChange(boolean z) {
            PictorialWallpaperHelper pictorialWallpaperHelper = this.this$0;
            if (!z) {
                pictorialWallpaperHelper.releaseDrawableAndInfo();
                pictorialWallpaperHelper.mPictorialLoader.releaseDrawableCache();
            }
            if (z || !pictorialWallpaperHelper.mPictorialMediator.isMainSwitchOffAndCanLeftSwipe()) {
                return;
            }
            pictorialWallpaperHelper.mPictorialLoader.updateWelcomePreloadWallpaper(((BaseWallpaperHelper) pictorialWallpaperHelper).mContext);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperHelper$7, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass7 implements WallpaperChangeWatcher.Observer {
        public /* synthetic */ PictorialWallpaperHelper this$0;

        @Override // com.oplus.systemui.keyguard.watcher.WallpaperChangeWatcher.Observer
        public final void onWallpaperChange(KeyguardWallpaperInfo keyguardWallpaperInfo) {
            String string;
            Uri fileUri;
            Uri fileUri2;
            if (PictorialUtils.isSameWallpaperInfo(keyguardWallpaperInfo, this.this$0.mWallpaperInfo)) {
                return;
            }
            boolean zIsPanoramicAodEnable = SmoothTransitionController.isPanoramicAodEnable(((BaseWallpaperHelper) this.this$0).mContext);
            if (zIsPanoramicAodEnable && TextUtils.isEmpty(this.this$0.mPanoramicAodOldFileUri)) {
                PictorialWallpaperHelper pictorialWallpaperHelper = this.this$0;
                KeyguardWallpaperInfo keyguardWallpaperInfo2 = pictorialWallpaperHelper.mWallpaperInfo;
                pictorialWallpaperHelper.mPanoramicAodOldFileUri = (!(keyguardWallpaperInfo2 instanceof PictorialWallpaperInfo) || (fileUri2 = ((PictorialWallpaperInfo) keyguardWallpaperInfo2).getFileUri()) == null) ? null : fileUri2.toString();
            }
            PictorialWallpaperHelper pictorialWallpaperHelper2 = this.this$0;
            if (zIsPanoramicAodEnable) {
                string = pictorialWallpaperHelper2.mPanoramicAodOldFileUri;
            } else {
                string = (!(keyguardWallpaperInfo instanceof PictorialWallpaperInfo) || (fileUri = ((PictorialWallpaperInfo) keyguardWallpaperInfo).getFileUri()) == null) ? null : fileUri.toString();
            }
            if (!zIsPanoramicAodEnable) {
                this.this$0.mPanoramicAodOldFileUri = null;
            }
            if (!TextUtils.isEmpty(string)) {
                PictorialWallpaperHelper$7$$ExternalSyntheticLambda0 pictorialWallpaperHelper$7$$ExternalSyntheticLambda0 = new PictorialWallpaperHelper$7$$ExternalSyntheticLambda0();
                pictorialWallpaperHelper$7$$ExternalSyntheticLambda0.f$0 = this;
                pictorialWallpaperHelper$7$$ExternalSyntheticLambda0.f$1 = string;
                VarHandle.storeStoreFence();
                PictorialUtils.runOnWorkThread(pictorialWallpaperHelper$7$$ExternalSyntheticLambda0);
            }
            PictorialWallpaperHelper pictorialWallpaperHelper3 = this.this$0;
            pictorialWallpaperHelper3.mWallpaperInfo = keyguardWallpaperInfo;
            if (pictorialWallpaperHelper3.mScreenLifecycle.getScreenState() == 2) {
                PictorialWallpaperStatistic.statisticExposeEnd(9, this.this$0.mWallpaperInfo, zIsPanoramicAodEnable);
            }
            PictorialWallpaperHelper.m13$$Nest$mhandleStatisticExposeStart(this.this$0);
            PictorialWallpaperStatistic.statisticScreenState(((BaseWallpaperHelper) this.this$0).mContext, this.this$0.mWallpaperInfo);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperHelper$8, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass8 extends KeyguardUpdateMonitorCallback {
        public /* synthetic */ PictorialWallpaperHelper this$0;

        public final void onDreamingStateChanged(boolean z) {
            if (z) {
                return;
            }
            this.this$0.mHasFetchWallpaperWhenKeyguardShow = false;
        }

        public final void onKeyguardVisibilityChanged(boolean z) {
            if (z) {
                PictorialWallpaperHelper.m13$$Nest$mhandleStatisticExposeStart(this.this$0);
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface IPictorialPreloadListener {
    }

    /* JADX INFO: renamed from: $r8$lambda$1cyCoj-GudjY2gn_Yyos40xM5fc, reason: not valid java name */
    public static /* synthetic */ void m12$r8$lambda$1cyCojGudjY2gn_Yyos40xM5fc(PictorialWallpaperHelper pictorialWallpaperHelper, Bitmap bitmap, CountDownLatch countDownLatch) {
        KgdLogUtil.i("PictorialWallpaperHelper", "save fileCacheBitmap");
        OplusWallpaperBitmapUtil.savePictorialBitmap(((BaseWallpaperHelper) pictorialWallpaperHelper).mContext, bitmap);
        countDownLatch.countDown();
    }

    public static void $r8$lambda$NCOVHCUz7YGrCZyj0qjZDwGUssI(PictorialWallpaperHelper pictorialWallpaperHelper) {
        CountDownLatch countDownLatch;
        String currentSourceKey = pictorialWallpaperHelper.getCurrentSourceKey();
        if (currentSourceKey != null && !currentSourceKey.isEmpty() && (countDownLatch = (CountDownLatch) pictorialWallpaperHelper.mSaveBitmapLatchHashMap.remove(currentSourceKey)) != null) {
            try {
                countDownLatch.await(5000L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                KgdLogUtil.i("PictorialWallpaperHelper", "releaseDrawable InterruptedException:" + e.getMessage());
            }
        }
        if (pictorialWallpaperHelper.mUpdateMonitor.mKeyguardShowing) {
            return;
        }
        synchronized (((BaseWallpaperHelper) pictorialWallpaperHelper).wallpaperLock) {
            pictorialWallpaperHelper.mPictorialLoader.releaseCurrentDrawable();
        }
    }

    /* JADX INFO: renamed from: -$$Nest$mhandleStatisticExposeStart, reason: not valid java name */
    public static void m13$$Nest$mhandleStatisticExposeStart(PictorialWallpaperHelper pictorialWallpaperHelper) {
        KeyguardWallpaperInfo keyguardWallpaperInfo = pictorialWallpaperHelper.mWallpaperInfo;
        if (keyguardWallpaperInfo == null) {
            KgdLogUtil.i("PictorialWallpaperHelper", "mWallpaperInfo is null, return");
            return;
        }
        if (!keyguardWallpaperInfo.isPictorialType()) {
            KgdLogUtil.i("PictorialWallpaperHelper", "is not PictorialType, return");
            return;
        }
        if (!pictorialWallpaperHelper.isEnable()) {
            KgdLogUtil.i("PictorialWallpaperHelper", "pictorial wallpaper enable false");
            return;
        }
        if (!pictorialWallpaperHelper.mUpdateMonitor.isKeyguardVisible$1()) {
            KgdLogUtil.i("PictorialWallpaperHelper", "isKeyguardVisible false");
            return;
        }
        if (pictorialWallpaperHelper.mScreenLifecycle.getScreenState() != 2) {
            KgdLogUtil.i("PictorialWallpaperHelper", "isScreenOn false");
            return;
        }
        OplusKgdImmersiveController oplusKgdImmersiveController = pictorialWallpaperHelper.mImmersiveController;
        if (oplusKgdImmersiveController == null || oplusKgdImmersiveController.isImmersiveWallpaperState < 3) {
            PictorialWallpaperStatistic.statisticExposeStart(pictorialWallpaperHelper.mWallpaperInfo, SmoothTransitionController.isPanoramicAodEnable(((BaseWallpaperHelper) pictorialWallpaperHelper).mContext));
        } else {
            KgdLogUtil.i("PictorialWallpaperHelper", "immersive state, return");
        }
    }

    /* JADX WARN: Code duplicated, block: B:10:0x004b  */
    /* JADX INFO: renamed from: -$$Nest$mwallpaperChange, reason: not valid java name */
    public static void m14$$Nest$mwallpaperChange(PictorialWallpaperHelper pictorialWallpaperHelper, List list, boolean z) {
        boolean z2;
        StringBuilder sb = new StringBuilder("wallpaperChange wallpapers = ");
        sb.append(list.size());
        sb.append(", updateViews = ");
        sb.append(z);
        sb.append(", isShowing = ");
        KeyguardStateControllerImpl keyguardStateControllerImpl = pictorialWallpaperHelper.mKeyguardStateControllerImpl;
        OplusChargeAnimController$.ExternalSyntheticOutline0.m("PictorialWallpaperHelper", sb, keyguardStateControllerImpl.mShowing);
        pictorialWallpaperHelper.mPictorialLoader.onWallpaperChange(list);
        pictorialWallpaperHelper.mPictorialLoader.setHasAlreadyFetchFromSlidePage(false);
        if (keyguardStateControllerImpl.mShowing) {
            z2 = true;
        } else {
            FeatureOption.INSTANCE.getClass();
            if (FeatureOption.isDragonfly() || SmoothTransitionController.isPanoramicAodEnable(((BaseWallpaperHelper) pictorialWallpaperHelper).mContext)) {
                z2 = true;
            } else {
                z2 = false;
            }
        }
        if (z && z2) {
            pictorialWallpaperHelper.setCurrentAndPreloadNext();
            pictorialWallpaperHelper.mPictorialLoader.setHasAlreadyFetchFromSlidePage(true);
            return;
        }
        KeyguardFeatureOption.INSTANCE.getClass();
        if (KeyguardFeatureOption.isSupportOplusGLPictorial && KeyguardFeatureOption.isKeepHeyTapPictorial) {
            KgdLogUtil.i("PictorialWallpaperHelper", "setPictorialAppSwitching false");
            pictorialWallpaperHelper.mPictorialMediator.setPictorialAppSwitching(false);
        }
    }

    public PictorialWallpaperHelper(Context context) {
        super(context);
        this.mPreloadChangeListeners = new ArrayList();
        this.mSaveBitmapLatchHashMap = new ConcurrentHashMap();
        this.mPictorialCacheKey = null;
        this.mHasWallpaperInit = false;
        this.mDisplayState = 0;
        this.mShouldUpdateUiWhenPanoramicAod = true;
        this.mScreenOffTime = 0L;
        this.mFoldStateChange = false;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        anonymousClass1.mLastFoldState = -1;
        VarHandle.storeStoreFence();
        this.mStateChangedListener = anonymousClass1;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        this.mDisplayStateListener = anonymousClass2;
        AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        anonymousClass3.this$0 = this;
        VarHandle.storeStoreFence();
        this.mScreenObserver = anonymousClass3;
        IOplusKeyguardStyleServiceCallback iOplusKeyguardStyleServiceCallback = new IOplusKeyguardStyleServiceCallback() { // from class: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialWallpaperHelper.4
            public IBinder asBinder() {
                return null;
            }

            public void onCall(String str, Bundle bundle) {
                if ("onAODUiStateChanged".equals(str) && bundle != null && SmoothTransitionController.isPanoramicAodEnable(((BaseWallpaperHelper) PictorialWallpaperHelper.this).mContext)) {
                    boolean z = bundle.getBoolean("shouldUpdateUi", true);
                    KgdLogUtil.d("PictorialWallpaperHelper", "shouldUpdateUi:" + z);
                    PictorialWallpaperHelper.this.mShouldUpdateUiWhenPanoramicAod = z;
                    PictorialWallpaperHelper pictorialWallpaperHelper = PictorialWallpaperHelper.this;
                    if (z) {
                        PictorialWallpaperHelper.m13$$Nest$mhandleStatisticExposeStart(pictorialWallpaperHelper);
                        return;
                    }
                    PictorialWallpaperStatistic.statisticExposeEnd(6, pictorialWallpaperHelper.mWallpaperInfo, true);
                    PictorialWallpaperHelper pictorialWallpaperHelper2 = PictorialWallpaperHelper.this;
                    if (pictorialWallpaperHelper2.mDisplayState == 1) {
                        pictorialWallpaperHelper2.updateAndFetchWallpaper("screen_off_p_aod", false);
                    }
                }
            }
        };
        this.mKssCallback = iOplusKeyguardStyleServiceCallback;
        AnonymousClass5 anonymousClass5 = new AnonymousClass5();
        anonymousClass5.this$0 = this;
        VarHandle.storeStoreFence();
        this.mPictorialMediatorCallback = anonymousClass5;
        AnonymousClass6 anonymousClass6 = new AnonymousClass6();
        anonymousClass6.this$0 = this;
        VarHandle.storeStoreFence();
        this.mPictorialStateWatcherObserver = anonymousClass6;
        AnonymousClass7 anonymousClass7 = new AnonymousClass7();
        anonymousClass7.this$0 = this;
        VarHandle.storeStoreFence();
        this.mWallpaperObserver = anonymousClass7;
        AnonymousClass8 anonymousClass8 = new AnonymousClass8();
        anonymousClass8.this$0 = this;
        VarHandle.storeStoreFence();
        this.mMonitorCallback = anonymousClass8;
        PictorialMediator pictorialMediator = (PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class);
        this.mPictorialMediator = pictorialMediator;
        this.mPictorialLoader = pictorialMediator.getPictorialFactory().getPictorialLoader();
        KeyguardUpdateMonitor keyguardUpdateMonitor = (KeyguardUpdateMonitor) Dependency.sDependency.getDependencyInner(KeyguardUpdateMonitor.class);
        this.mUpdateMonitor = keyguardUpdateMonitor;
        this.mKeyguardStateControllerImpl = (KeyguardStateControllerImpl) Dependency.sDependency.getDependencyInner(KeyguardStateController.class);
        pictorialMediator.registerCallback(anonymousClass5);
        this.mImmersiveController = (OplusKgdImmersiveController) ((OplusKeyguardDependencyEx) DependencyEx.sDependency.getDependency(OplusKeyguardDependencyEx.class)).getOplusKgdImmersiveController();
        ScreenLifecycle screenLifecycle = (ScreenLifecycle) Dependency.sDependency.getDependencyInner(ScreenLifecycle.class);
        this.mScreenLifecycle = screenLifecycle;
        ((Lifecycle) screenLifecycle).mObservers.add(anonymousClass3);
        PictorialStateWatcher.getInstance().addCallback((PictorialStateWatcher.Observer) anonymousClass6);
        FeatureOption.INSTANCE.getClass();
        if (FeatureOption.isFoldDevice() && !FeatureOption.isFoldRemapDisabled()) {
            DeviceStateController.getInstance().addCallback(anonymousClass1);
        }
        PictorialWallpaperHelper$$ExternalSyntheticLambda1 pictorialWallpaperHelper$$ExternalSyntheticLambda1 = new PictorialWallpaperHelper$$ExternalSyntheticLambda1();
        pictorialWallpaperHelper$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.mPictorialLoaderCallback = pictorialWallpaperHelper$$ExternalSyntheticLambda1;
        this.mPictorialLoader.setPictorialLoaderCallback(pictorialWallpaperHelper$$ExternalSyntheticLambda1);
        WallpaperChangeWatcher.getInstance().addCallback((WallpaperChangeWatcher.Observer) anonymousClass7);
        keyguardUpdateMonitor.registerCallback(this.mMonitorCallback);
        SmoothTransitionController.getInstance(context).registerSmoothTransitionCallback(iOplusKeyguardStyleServiceCallback, false, true);
        AODDisplayUtil.getInstance(((BaseWallpaperHelper) this).mContext).registerOplusDisplayStateListener(anonymousClass2);
    }

    private String getCurrentSourceKey() {
        PictorialWallpaperInfo currentLockScreenWallpaperInfo = this.mPictorialLoader.getCurrentLockScreenWallpaperInfo();
        if (currentLockScreenWallpaperInfo != null) {
            return currentLockScreenWallpaperInfo.getImageId();
        }
        return null;
    }

    public static PictorialWallpaperHelper getInstance(Context context) {
        if (sInstance == null) {
            synchronized (PictorialWallpaperHelper.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new PictorialWallpaperHelper(context.getApplicationContext());
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    public final void clearWallpapers() {
        this.mPictorialLoader.clearWallpapers();
    }

    public final Bitmap copyWallpaperBitmapSafely() {
        synchronized (((BaseWallpaperHelper) this).wallpaperLock) {
            try {
                Drawable wallpaper = this.mPictorialLoader.getWallpaper(((BaseWallpaperHelper) this).mContext);
                String currentSourceKey = getCurrentSourceKey();
                if (wallpaper == null) {
                    if (currentSourceKey != null && currentSourceKey.equals(this.mPictorialCacheKey)) {
                        KgdLogUtil.i("PictorialWallpaperHelper", "read fileCacheBitmap");
                        Bitmap pictorialBitmap = OplusWallpaperBitmapUtil.readPictorialBitmap(((BaseWallpaperHelper) this).mContext);
                        if (pictorialBitmap != null && pictorialBitmap.getConfig() != null) {
                            return pictorialBitmap.copy(pictorialBitmap.getConfig(), pictorialBitmap.isMutable());
                        }
                    }
                    return null;
                }
                if (!(wallpaper instanceof BitmapDrawable)) {
                    return null;
                }
                Bitmap bitmap = ((BitmapDrawable) wallpaper).getBitmap();
                if (bitmap != null && !bitmap.isRecycled() && bitmap.getConfig() != null) {
                    if (!this.mHasWallpaperInit) {
                        savePictorialBitmap(bitmap, currentSourceKey);
                        this.mHasWallpaperInit = true;
                    }
                    return bitmap.copy(bitmap.getConfig(), bitmap.isMutable());
                }
                return null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void disable(BaseWallpaperHelper baseWallpaperHelper) {
        this.mPictorialLoader.setEnable(false);
        releaseDrawableAndInfo();
        this.mPictorialLoader.disable();
    }

    public final void enable(BaseWallpaperHelper baseWallpaperHelper) {
        this.mPictorialLoader.setEnable(true);
        KeyguardFeatureOption.INSTANCE.getClass();
        if (!KeyguardFeatureOption.isSupportOtherPictorial) {
            setCurrentAndPreloadNext();
        } else {
            if (this.mPictorialMediator.mPictorialAppSwitching) {
                return;
            }
            setCurrentAndPreloadNext();
        }
    }

    public PictorialLoader getPictorialLoader() {
        return this.mPictorialLoader;
    }

    public PictorialSpaceBean getPictorialSpaceBean() {
        return null;
    }

    public Drawable getPreloadWallpaper() {
        if (isEnable()) {
            return this.mPictorialLoader.getPreloadWallpaper(((BaseWallpaperHelper) this).mContext);
        }
        return null;
    }

    public PictorialWallpaperInfo getPreloadWallpaperInfo() {
        return this.mPictorialLoader.getCurrentPreloadWallpaperInfo();
    }

    public Drawable getWallpaper() {
        Drawable wallpaper;
        if (!isEnableLockScreen()) {
            KgdLogUtil.i("PictorialWallpaperHelper", "getWallpaper fail,sine PictorialLoader isEnableLockScreen is false");
            return null;
        }
        synchronized (((BaseWallpaperHelper) this).wallpaperLock) {
            wallpaper = this.mPictorialLoader.getWallpaper(((BaseWallpaperHelper) this).mContext);
        }
        return wallpaper;
    }

    public KeyguardWallpaperInfo getWallpaperInfo() {
        return this.mPictorialLoader.getCurrentLockScreenWallpaperInfo();
    }

    public final void hide() {
        KeyguardStateControllerImpl keyguardStateControllerImpl = this.mKeyguardStateControllerImpl;
        boolean z = keyguardStateControllerImpl != null && keyguardStateControllerImpl.mShowing;
        boolean zIsPanoramicAodEnable = SmoothTransitionController.isPanoramicAodEnable(((BaseWallpaperHelper) this).mContext);
        UnlockViewLifecycleOwner$.ExternalSyntheticOutline0.m(" isPanoramicAod : ", "PictorialWallpaperHelper", new StringBuilder("hide, isKeyguardHiding : "), !z, zIsPanoramicAodEnable);
        if (!z) {
            this.mHasFetchWallpaperWhenKeyguardShow = false;
            releaseDrawableAndInfo();
        }
        if (keyguardStateControllerImpl == null || !keyguardStateControllerImpl.mShowing) {
            PictorialWallpaperStatistic.statisticExposeEnd(1, this.mWallpaperInfo, zIsPanoramicAodEnable);
        } else {
            PictorialWallpaperStatistic.statisticExposeEnd(5, this.mWallpaperInfo, zIsPanoramicAodEnable);
        }
        if (SmoothTransitionController.isPanoramicAodEnable(((BaseWallpaperHelper) this).mContext) && !this.mUpdateMonitor.mKeyguardOccluded && PictorialWallpaperStatistic.isCurrentWallpaperExposedWhenPanoramicAod(this.mWallpaperInfo)) {
            updateAndFetchWallpaper("keyguard_hide", false);
        }
    }

    public final boolean isEnable() {
        KeyguardUpdateMonitor keyguardUpdateMonitor;
        PictorialMediator pictorialMediator = this.mPictorialMediator;
        return (pictorialMediator.isMainSwitchOn() || pictorialMediator.isMainSwitchOffAndCanLeftSwipe()) && KeyguardUpdateMonitor.getCurrentUser() == 0 && (keyguardUpdateMonitor = this.mUpdateMonitor) != null && keyguardUpdateMonitor.mUserManager.isUserUnlocked(0) && keyguardUpdateMonitor.mDeviceProvisioned;
    }

    public final boolean isEnableLockScreen() {
        return this.mPictorialLoader.isEnableLockScreen(((BaseWallpaperHelper) this).mContext);
    }

    public final void onKeyguardDone() {
        releaseDrawableAndInfo();
        this.mPictorialLoader.releasePreloadCache();
        this.mPictorialLoader.releaseDrawableCache();
    }

    public final void onSimStateChange(int i) {
        if (i == 5) {
            setCurrentAndPreloadNext();
        }
    }

    public final void onWallpaperChange(int i) {
        super.onWallpaperChange(i);
        KeyguardFeatureOption.INSTANCE.getClass();
        if (KeyguardFeatureOption.isSupportOplusGLPictorial && KeyguardFeatureOption.isKeepHeyTapPictorial) {
            KgdLogUtil.i("PictorialWallpaperHelper", "setPictorialAppSwitching false");
            this.mPictorialMediator.setPictorialAppSwitching(false);
        }
    }

    public final void releaseDrawable() {
        PictorialWallpaperHelper$$ExternalSyntheticLambda2 pictorialWallpaperHelper$$ExternalSyntheticLambda2 = new PictorialWallpaperHelper$$ExternalSyntheticLambda2(0);
        pictorialWallpaperHelper$$ExternalSyntheticLambda2.f$0 = this;
        VarHandle.storeStoreFence();
        PictorialUtils.runOnWorkThread(pictorialWallpaperHelper$$ExternalSyntheticLambda2);
    }

    public final void releaseDrawableAndInfo() {
        this.mPanoramicAodOldFileUri = null;
        synchronized (((BaseWallpaperHelper) this).wallpaperLock) {
            this.mPictorialLoader.releaseDrawableAndInfo();
        }
    }

    public final void savePictorialBitmap(Bitmap bitmap, String str) {
        if (str == null || str.isEmpty() || str.equals(this.mPictorialCacheKey)) {
            return;
        }
        this.mPictorialCacheKey = str;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        this.mSaveBitmapLatchHashMap.put(str, countDownLatch);
        PictorialWallpaperHelper$$ExternalSyntheticLambda0 pictorialWallpaperHelper$$ExternalSyntheticLambda0 = new PictorialWallpaperHelper$$ExternalSyntheticLambda0();
        pictorialWallpaperHelper$$ExternalSyntheticLambda0.f$0 = this;
        pictorialWallpaperHelper$$ExternalSyntheticLambda0.f$1 = bitmap;
        pictorialWallpaperHelper$$ExternalSyntheticLambda0.f$2 = countDownLatch;
        VarHandle.storeStoreFence();
        KeyguardThreadUtil.runOnIOThread(pictorialWallpaperHelper$$ExternalSyntheticLambda0);
    }

    public final void setCurrentAndPreloadNext() {
        ScreenLifecycle screenLifecycle;
        KeyguardFeatureOption.INSTANCE.getClass();
        if (KeyguardFeatureOption.isLihgtOsLowLevel() && (screenLifecycle = this.mScreenLifecycle) != null && screenLifecycle.getScreenState() == 2 && this.mScreenOffTime > 0 && this.mPictorialMediator.mIsLastDrawBitmapFromPictorial) {
            long jCurrentTimeMillis = System.currentTimeMillis() - this.mScreenOffTime;
            if (jCurrentTimeMillis < 3000) {
                KgdLogUtil.i("PictorialWallpaperHelper", "[RapidScreen] setCurrentAndPreloadNext: rapid screen on/off detected, timeSinceScreenOff=" + jCurrentTimeMillis + "ms, screen is ON, block wallpaper refresh to avoid flickering");
                return;
            }
        }
        this.mPictorialLoader.updateCurrentWallpaper(((BaseWallpaperHelper) this).mContext);
        if (SmoothTransitionController.isPanoramicAodEnable(((BaseWallpaperHelper) this).mContext)) {
            return;
        }
        this.mPictorialLoader.updateNextWallpaper(((BaseWallpaperHelper) this).mContext);
    }

    public void setPreloadWallpaperChangeListener(IPictorialPreloadListener iPictorialPreloadListener) {
        if (this.mPreloadChangeListeners.contains(iPictorialPreloadListener)) {
            return;
        }
        this.mPreloadChangeListeners.add(iPictorialPreloadListener);
        this.mPictorialLoader.setPreloadWallpaperChangeListener(iPictorialPreloadListener);
    }

    public final void show() {
        KeyguardStateControllerImpl keyguardStateControllerImpl = this.mKeyguardStateControllerImpl;
        boolean z = keyguardStateControllerImpl != null && keyguardStateControllerImpl.mShowing;
        boolean z2 = (this.mUpdateMonitor == null || this.mScreenLifecycle.getScreenState() == 2) ? false : true;
        boolean z3 = this.mPictorialLoader.getCurrentLockScreenWallpaperInfo() == null;
        boolean zIsPanoramicAodEnable = SmoothTransitionController.isPanoramicAodEnable(((BaseWallpaperHelper) this).mContext);
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("show, keyguardShow : ", ", infoIsNull : ", ", isScreenOff : ", z, z3);
        KeyguardLogger$.ExternalSyntheticOutline0.m(", isPanoramicAod : ", ", isPictorialAppSwitching:", sbM, z2, zIsPanoramicAodEnable);
        PictorialMediator pictorialMediator = this.mPictorialMediator;
        OplusChargeAnimController$.ExternalSyntheticOutline0.m("PictorialWallpaperHelper", sbM, pictorialMediator.mPictorialAppSwitching);
        if (z && z3 && !z2 && !zIsPanoramicAodEnable && pictorialMediator.isAutoPlaySwitchOn()) {
            this.mHasFetchWallpaperWhenKeyguardShow = true;
            updateAndFetchWallpaper("keyguard_show", false);
        }
    }

    public final void showWallpaperAddRotaDialog(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        this.mPictorialLoader.showWallpaperAddRotaDialog(((BaseWallpaperHelper) this).mContext, drawable);
    }

    public final int type() {
        return 1;
    }

    public final void updateAndFetchWallpaper(String str, boolean z) {
        if (isEnable()) {
            KgdLogUtil.d("PictorialWallpaperHelper", "updateAndFetchWallpaper: cur=" + z + " r=" + str);
            if (z) {
                setCurrentAndPreloadNext();
            }
            boolean zIsPanoramicAodEnable = SmoothTransitionController.isPanoramicAodEnable(((BaseWallpaperHelper) this).mContext);
            PictorialMediator pictorialMediator = this.mPictorialMediator;
            if (zIsPanoramicAodEnable) {
                pictorialMediator.obtainPictorialWallpaper(str, null, true, true, true);
            } else {
                pictorialMediator.obtainPictorialWallpaper(str, null, true, true, false);
            }
            this.mPictorialLoader.setHasAlreadyFetchFromSlidePage(false);
        }
    }

    public final void updateCurrentPreloadWallpaper() {
        this.mPictorialLoader.updateCurrentPreloadWallpaper(((BaseWallpaperHelper) this).mContext);
    }

    public final void updateWelcomeWallpaper() {
        KgdLogUtil.d("PictorialWallpaperHelper", "updateWelcomeWallpaper");
        this.mPictorialLoader.updateWelcomePreloadWallpaper(((BaseWallpaperHelper) this).mContext);
    }
}
