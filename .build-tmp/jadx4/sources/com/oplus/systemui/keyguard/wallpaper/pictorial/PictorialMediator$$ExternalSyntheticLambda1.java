package com.oplus.systemui.keyguard.wallpaper.pictorial;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.os.Bundle;
import android.provider.Settings;
import android.view.ViewGroup;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.communal.widgets.CommunalWidgetHost$;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.oplus.systemui.aod.display.SmoothTransitionController;
import com.oplus.systemui.keyguard.OplusKeyguardTipsViewController;
import com.oplus.systemui.keyguard.wallpaper.pictorial.dynamic.PictorialEngineContainer;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.PictorialCursorHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.factory.oplus.OplusPictorialCursorHelper;
import com.oplus.systemui.keyguard.wallpaper.pictorial.tips.PictorialOpenAlertDialog;
import com.oplus.systemui.keyguard.watcher.PictorialStateWatcher;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class PictorialMediator$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ PictorialMediator f$0;

    public /* synthetic */ PictorialMediator$$ExternalSyntheticLambda1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        int i2 = 8;
        PictorialMediator pictorialMediator = this.f$0;
        switch (i) {
            case 0:
                PictorialCursorHelper pictorialCursorHelper = pictorialMediator.mPictorialCursorHelper;
                if (pictorialCursorHelper instanceof OplusPictorialCursorHelper) {
                    OplusPictorialCursorHelper oplusPictorialCursorHelper = (OplusPictorialCursorHelper) pictorialCursorHelper;
                    if (pictorialMediator.isMainSwitchStatusOn() && !pictorialMediator.mStartGetPictorialDefaultSetting) {
                        pictorialMediator.mFirstGetPictorialDefaultSetting = false;
                        pictorialMediator.mStartGetPictorialDefaultSetting = true;
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        Bundle bundleCallPictorialPage = oplusPictorialCursorHelper.callPictorialPage(pictorialMediator.mContentResolver, pictorialMediator.mPictorialAppDataImpl.getCallPictorialUri(), 11, CommunalWidgetHost$.ExternalSyntheticOutline0.m(1, "pictorial_recommendation"));
                        if (bundleCallPictorialPage == null || bundleCallPictorialPage.getInt("p_code") != 0) {
                            pictorialMediator.mKgdPictorialRecommendation = oplusPictorialCursorHelper.getKgdPictorialRecommendationSetting(pictorialMediator.mContext);
                            KgdLogUtil.w("PictorialMediator", "getPictorialDefaultSetting cost time = " + (System.currentTimeMillis() - jCurrentTimeMillis) + " result = " + bundleCallPictorialPage);
                        } else {
                            boolean z = bundleCallPictorialPage.getInt("pictorial_recommendation_value", 0) == 1;
                            boolean z2 = bundleCallPictorialPage.getInt("pictorial_recommendation_show", 0) == 1;
                            oplusPictorialCursorHelper.setKgdPictorialRecommendationSetting(pictorialMediator.mContext, z);
                            oplusPictorialCursorHelper.setKgdPictorialRecommendationShow(pictorialMediator.mContext, z2);
                            pictorialMediator.mKgdPictorialRecommendation = z;
                            KgdLogUtil.i("PictorialMediator", "getPictorialDefaultSetting cost time = " + (System.currentTimeMillis() - jCurrentTimeMillis) + " pictorialRecommendationValue = " + z + " pictorialRecommendationShow = " + z2);
                        }
                        pictorialMediator.mStartGetPictorialDefaultSetting = false;
                    } else {
                        pictorialMediator.mStartGetPictorialDefaultSetting = false;
                        oplusPictorialCursorHelper.setKgdPictorialRecommendationShow(pictorialMediator.mContext, false);
                        PictorialMediator$$ExternalSyntheticLambda1 pictorialMediator$$ExternalSyntheticLambda1 = new PictorialMediator$$ExternalSyntheticLambda1(i2);
                        pictorialMediator$$ExternalSyntheticLambda1.f$0 = pictorialMediator;
                        VarHandle.storeStoreFence();
                        SystemUIThreadUtil.runOnUiThread(pictorialMediator$$ExternalSyntheticLambda1);
                    }
                }
                break;
            case 1:
                if (SmoothTransitionController.isPanoramicAodEnable(pictorialMediator.mContext)) {
                    pictorialMediator.mLeftSwipeSwitchOn = pictorialMediator.getLeftSwipeSwitchState();
                    if (pictorialMediator.isMainSwitchOn() && pictorialMediator.isLeftSwipeDisplaySwitchOn()) {
                        PictorialStateWatcher.getInstance().dispatchPictorialSwipeSwitchChange();
                        pictorialMediator.ensurePictorialWallpaperForPanoramicAod("onAodStyleTypeChangedForLeftSwipe");
                    } else {
                        KgdLogUtil.i("PictorialMediator", "onAodStyleTypeChangedForLeftSwipe skip, main=" + pictorialMediator.isMainSwitchOn() + ", leftSwipe=" + pictorialMediator.isLeftSwipeDisplaySwitchOn());
                    }
                    break;
                }
                break;
            case 2:
                AtomicBoolean atomicBoolean = PictorialMediator.sPictorialFestivalWallpaperUsed;
                try {
                    pictorialMediator.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor("oplus_disable_pictorial_alive"), false, pictorialMediator.mSettingsObserver, -2);
                    KgdLogUtil.i("PictorialMediator", "ensureDisableAliveSettingsObserver registered");
                } catch (Exception e) {
                    pictorialMediator.mDisableAliveSettingsObserverRegistered = false;
                    KgdLogUtil.w("PictorialMediator", "ensureDisableAliveSettingsObserver e=" + e);
                    return;
                }
                break;
            case 3:
                OplusLockIconView$.ExternalSyntheticOutline0.m(pictorialMediator.mPictorialCursorHelper.getPictorialActivateNotShow(pictorialMediator.mContentResolver, pictorialMediator.mPictorialAppDataImpl.getBaseUri()), "triggerActiveNotShowUpdate: ", "PictorialMediator");
                break;
            case 4:
                AtomicBoolean atomicBoolean2 = PictorialMediator.sPictorialFestivalWallpaperUsed;
                ActivityManager.RunningTaskInfo runningTask = ActivityManagerWrapper.getInstance().getRunningTask();
                ComponentName componentName = runningTask != null ? runningTask.topActivity : null;
                if (componentName != null && pictorialMediator.getPictorialPackageName().equals(componentName.getPackageName()) && pictorialMediator.isAutoPlaySwitchOn()) {
                    PictorialMediator$$ExternalSyntheticLambda1 pictorialMediator$$ExternalSyntheticLambda2 = new PictorialMediator$$ExternalSyntheticLambda1(6);
                    pictorialMediator$$ExternalSyntheticLambda2.f$0 = pictorialMediator;
                    VarHandle.storeStoreFence();
                    SystemUIThreadUtil.runOnUiThread(pictorialMediator$$ExternalSyntheticLambda2);
                    break;
                }
                break;
            case 5:
                PictorialEngineContainer pictorialEngineContainer = pictorialMediator.mPictorialEngineContainer;
                if (pictorialEngineContainer != null && pictorialEngineContainer.getParent() != null && pictorialMediator.mPictorialEngineContainer.getVisibility() == 0) {
                    pictorialMediator.mPictorialEngineContainer.setVisibility(8);
                    ((ViewGroup) pictorialMediator.mPictorialEngineContainer.getParent()).removeView(pictorialMediator.mPictorialEngineContainer);
                    break;
                }
                break;
            case 6:
                AtomicBoolean atomicBoolean3 = PictorialMediator.sPictorialFestivalWallpaperUsed;
                new PictorialOpenAlertDialog().tryShow(pictorialMediator.mContext, pictorialMediator.mConfigurationController);
                break;
            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                AtomicBoolean atomicBoolean4 = PictorialMediator.sPictorialFestivalWallpaperUsed;
                pictorialMediator.firstProviderService(false);
                pictorialMediator.registerPictorialObserver();
                break;
            default:
                OplusKeyguardTipsViewController.tipsChangeWatcher.1 r11 = pictorialMediator.mTipsChangeWatcher;
                if (r11 != null) {
                    r11.onTipsChange((PictorialWallpaperInfo) null);
                }
                break;
        }
    }
}
