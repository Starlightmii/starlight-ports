package com.oplus.systemui.keyguard.wallpaper.pictorial;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.DependencyEx;
import com.oplus.systemui.keyguard.wallpaper.pictorial.app.PictorialAppDataImpl;
import com.oplus.systemui.keyguard.wallpaper.pictorial.app.PictorialAppInfo;
import com.oplus.systemui.statusbar.notification.icdf.IcdfNotificationManagerOuterClass$IcdfNotification;
import com.oplusos.keyguard.utils.KgdLogUtil;
import com.oplusos.systemui.common.feature.KeyguardFeatureOption;
import com.oplusos.systemui.common.observer.ObserverDispatcher;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class PictorialStatusObserver {
    public ContentObserver mAutoPlayObserver;
    public boolean mCanUseMobileData = false;
    public AnonymousClass1 mClearDataObserver;
    public final ContentResolver mContentResolver;
    public final Context mContext;
    public PictorialAppInfo mCurrentPictorialAppInfo;
    public AnonymousClass1 mDataSwitchObserver;
    public ContentObserver mEnablePlayObserver;
    public ContentObserver mMainSwitchObserver;
    public AnonymousClass1 mOtherPictorialActivateNotShowObserver;
    public AnonymousClass1 mOtherPictorialAvailableStateObserver;
    public AnonymousClass1 mSwipeLeftObserver;
    public ContentObserver mTaglineStateObserver;

    /* JADX INFO: renamed from: -$$Nest$mswitchStatusSetting, reason: not valid java name */
    public static void m11$$Nest$mswitchStatusSetting(PictorialStatusObserver pictorialStatusObserver, String str, int i) {
        int intForUser = pictorialStatusObserver.getIntForUser(str, i);
        int i2 = 1 - intForUser;
        KgdLogUtil.d("PictorialStatusObserver", str + " switch from " + intForUser + " to " + i2);
        if ("mobile_data_pictorial_support".equals(str)) {
            pictorialStatusObserver.mCanUseMobileData = i2 == 1;
        }
        Settings.System.putIntForUser(pictorialStatusObserver.mContentResolver, str, i2, KeyguardUpdateMonitor.getCurrentUser());
    }

    public PictorialStatusObserver(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mContentResolver = applicationContext.getContentResolver();
        this.mCurrentPictorialAppInfo = new PictorialAppInfo("com.heytap.pictorial", context);
    }

    private Uri getSpecialMainSwitchKey() {
        return Uri.parse("content://" + this.mCurrentPictorialAppInfo.getPkgName() + ".data.provider/keyguard_pictorial_main_switch_uri");
    }

    public Uri getClearDataKey() {
        return Uri.parse("content://" + this.mCurrentPictorialAppInfo.getPkgName() + ".data.provider/keyguard_app_data_clear_uri");
    }

    public final int getIntForUser(String str, int i) {
        try {
            return Settings.System.getIntForUser(this.mContentResolver, str, KeyguardUpdateMonitor.getCurrentUser());
        } catch (Settings.SettingNotFoundException unused) {
            KgdLogUtil.d("PictorialStatusObserver", str.concat(" SettingNotFoundException"));
            return i;
        }
    }

    public Uri getOplusGLAutoPlayKey() {
        return Uri.parse("content://com.glance.lockscreenOp.data.provider/keyguard_oplus_pictorial_auto_play_uri");
    }

    public Uri getOplusGLEnablePlayKey() {
        return Uri.parse("content://com.glance.lockscreenOp.data.provider/keyguard_oplus_pictorial_enable_play_uri");
    }

    public Uri getOplusGLMainSwitchKey() {
        return Uri.parse("content://com.glance.lockscreenOp.data.provider/keyguard_pictorial_main_switch_uri");
    }

    public Uri getOplusGLTaglineStateKey() {
        return Uri.parse("content://com.glance.lockscreenOp.data.provider/keyguard_tagline_state_uri");
    }

    public Uri getSpecialAutoPlayKey() {
        return Uri.parse("content://" + this.mCurrentPictorialAppInfo.getPkgName() + ".data.provider/keyguard_oplus_pictorial_auto_play_uri");
    }

    public Uri getSpecialDataSwitchKey() {
        return Uri.parse("content://" + this.mCurrentPictorialAppInfo.getPkgName() + ".data.provider/keyguard_pictorial_mobile_data_switch_uri");
    }

    public Uri getSpecialEnablePlayKey() {
        return Uri.parse("content://" + this.mCurrentPictorialAppInfo.getPkgName() + ".data.provider/keyguard_oplus_pictorial_enable_play_uri");
    }

    public Uri getSpecialOtherPictorialActivateNotShowKey() {
        return Uri.parse("content://" + this.mCurrentPictorialAppInfo.getPkgName() + ".data.provider/keyguard_oplus_other_pictorial_activate_not_show_uri");
    }

    public Uri getSpecialOtherPictorialAvailableKey() {
        return Uri.parse("content://" + this.mCurrentPictorialAppInfo.getPkgName() + ".data.provider/keyguard_oplus_other_pictorial_available_switch_uri");
    }

    public Uri getSpecialSwipeLiftKey() {
        StringBuilder sb = new StringBuilder("content://");
        sb.append(this.mCurrentPictorialAppInfo.getPkgName());
        sb.append(isGlanceStyle() ? ".data.provider/keyguard_swipe_left_gesture_uri" : ".data.provider//keyguard_pictorial_left_swipe_switch_uri");
        return Uri.parse(sb.toString());
    }

    public Uri getSpecialTaglineStateKey() {
        return Uri.parse("content://" + this.mCurrentPictorialAppInfo.getPkgName() + ".data.provider/keyguard_tagline_state_uri");
    }

    public final boolean isGlanceStyle() {
        return 1 == this.mCurrentPictorialAppInfo.getStyle();
    }

    public final boolean isHeytapPictorial() {
        return "com.heytap.pictorial".equals(this.mCurrentPictorialAppInfo.getPkgName());
    }

    /* JADX WARN: Type inference failed for: r0v14, types: [com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialStatusObserver$1] */
    /* JADX WARN: Type inference failed for: r0v16, types: [com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialStatusObserver$1] */
    /* JADX WARN: Type inference failed for: r0v17, types: [com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialStatusObserver$1] */
    /* JADX WARN: Type inference failed for: r0v18, types: [com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialStatusObserver$1] */
    /* JADX WARN: Type inference failed for: r0v8, types: [com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialStatusObserver$1] */
    public final void register() {
        KeyguardFeatureOption.INSTANCE.getClass();
        final int i = 3;
        final int i2 = 2;
        if (KeyguardFeatureOption.isSupportOplusGLPictorial) {
            if (this.mMainSwitchObserver == null) {
                final int i3 = 1;
                this.mMainSwitchObserver = new ContentObserver(this, new Handler(Looper.getMainLooper())) { // from class: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialStatusObserver.1
                    public final /* synthetic */ PictorialStatusObserver this$0;

                    {
                        this.this$0 = this;
                    }

                    @Override // android.database.ContentObserver
                    public final void onChange(boolean z) {
                        PictorialAppDataImpl pictorialAppDataImpl;
                        PictorialAppInfo pictorialAppInfo;
                        switch (i3) {
                            case 0:
                                if (!this.this$0.isHeytapPictorial()) {
                                    PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_apply", 0);
                                }
                                break;
                            case 1:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_apply", 0);
                                KgdLogUtil.d("PictorialStatusObserver", " mMainSwitchObserver gl switchStatusSetting set1 b=" + z + ",pkgcur=" + this.this$0.mCurrentPictorialAppInfo.getPkgName());
                                break;
                            case 2:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_enable_play", 0);
                                KgdLogUtil.d("PictorialStatusObserver", " mEnablePlayObserver switchStatusSetting set1 b=" + z + ",pkgcur=" + this.this$0.mCurrentPictorialAppInfo.getPkgName());
                                break;
                            case 3:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_auto_play", 0);
                                break;
                            case 4:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_tagline", 0);
                                break;
                            case 5:
                                if (!this.this$0.isHeytapPictorial()) {
                                    PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_auto_play", 0);
                                    if (this.this$0.getIntForUser("oplus_customize_pictorial_auto_play", 0) == 1) {
                                        SharedPreferences.Editor editorEdit = this.this$0.mContext.getSharedPreferences("rm_pictorial_info", 0).edit();
                                        editorEdit.putInt("rm_pictorial_open_auto_play_by_itself", 1);
                                        editorEdit.apply();
                                        KgdLogUtil.i("PictorialStatusObserver", "mAutoPlayObserver,rm pictorial self set auto play switch enable");
                                    }
                                }
                                break;
                            case 6:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "mobile_data_pictorial_support", 0);
                                break;
                            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                                if (!this.this$0.isHeytapPictorial()) {
                                    PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_enable_play", 0);
                                }
                                break;
                            case 8:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "swipe_left_display_content", 0);
                                break;
                            case 9:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "pictorial_activate_not_show", 0);
                                break;
                            case 10:
                                if (!this.this$0.isHeytapPictorial() && (pictorialAppInfo = (pictorialAppDataImpl = ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).mPictorialAppDataImpl).mCurrentPictorialAppInfo) != null) {
                                    pictorialAppDataImpl.clearPictorialApp(pictorialAppInfo);
                                    break;
                                }
                                break;
                            case 11:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_tagline", 0);
                                break;
                            default:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_other_pictorial_available", 1);
                                break;
                        }
                    }
                };
            }
            if (this.mEnablePlayObserver == null) {
                this.mEnablePlayObserver = new ContentObserver(this, new Handler(Looper.getMainLooper())) { // from class: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialStatusObserver.1
                    public final /* synthetic */ PictorialStatusObserver this$0;

                    {
                        this.this$0 = this;
                    }

                    @Override // android.database.ContentObserver
                    public final void onChange(boolean z) {
                        PictorialAppDataImpl pictorialAppDataImpl;
                        PictorialAppInfo pictorialAppInfo;
                        switch (i2) {
                            case 0:
                                if (!this.this$0.isHeytapPictorial()) {
                                    PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_apply", 0);
                                }
                                break;
                            case 1:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_apply", 0);
                                KgdLogUtil.d("PictorialStatusObserver", " mMainSwitchObserver gl switchStatusSetting set1 b=" + z + ",pkgcur=" + this.this$0.mCurrentPictorialAppInfo.getPkgName());
                                break;
                            case 2:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_enable_play", 0);
                                KgdLogUtil.d("PictorialStatusObserver", " mEnablePlayObserver switchStatusSetting set1 b=" + z + ",pkgcur=" + this.this$0.mCurrentPictorialAppInfo.getPkgName());
                                break;
                            case 3:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_auto_play", 0);
                                break;
                            case 4:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_tagline", 0);
                                break;
                            case 5:
                                if (!this.this$0.isHeytapPictorial()) {
                                    PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_auto_play", 0);
                                    if (this.this$0.getIntForUser("oplus_customize_pictorial_auto_play", 0) == 1) {
                                        SharedPreferences.Editor editorEdit = this.this$0.mContext.getSharedPreferences("rm_pictorial_info", 0).edit();
                                        editorEdit.putInt("rm_pictorial_open_auto_play_by_itself", 1);
                                        editorEdit.apply();
                                        KgdLogUtil.i("PictorialStatusObserver", "mAutoPlayObserver,rm pictorial self set auto play switch enable");
                                    }
                                }
                                break;
                            case 6:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "mobile_data_pictorial_support", 0);
                                break;
                            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                                if (!this.this$0.isHeytapPictorial()) {
                                    PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_enable_play", 0);
                                }
                                break;
                            case 8:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "swipe_left_display_content", 0);
                                break;
                            case 9:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "pictorial_activate_not_show", 0);
                                break;
                            case 10:
                                if (!this.this$0.isHeytapPictorial() && (pictorialAppInfo = (pictorialAppDataImpl = ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).mPictorialAppDataImpl).mCurrentPictorialAppInfo) != null) {
                                    pictorialAppDataImpl.clearPictorialApp(pictorialAppInfo);
                                    break;
                                }
                                break;
                            case 11:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_tagline", 0);
                                break;
                            default:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_other_pictorial_available", 1);
                                break;
                        }
                    }
                };
            }
            if (this.mAutoPlayObserver == null) {
                this.mAutoPlayObserver = new ContentObserver(this, new Handler(Looper.getMainLooper())) { // from class: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialStatusObserver.1
                    public final /* synthetic */ PictorialStatusObserver this$0;

                    {
                        this.this$0 = this;
                    }

                    @Override // android.database.ContentObserver
                    public final void onChange(boolean z) {
                        PictorialAppDataImpl pictorialAppDataImpl;
                        PictorialAppInfo pictorialAppInfo;
                        switch (i) {
                            case 0:
                                if (!this.this$0.isHeytapPictorial()) {
                                    PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_apply", 0);
                                }
                                break;
                            case 1:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_apply", 0);
                                KgdLogUtil.d("PictorialStatusObserver", " mMainSwitchObserver gl switchStatusSetting set1 b=" + z + ",pkgcur=" + this.this$0.mCurrentPictorialAppInfo.getPkgName());
                                break;
                            case 2:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_enable_play", 0);
                                KgdLogUtil.d("PictorialStatusObserver", " mEnablePlayObserver switchStatusSetting set1 b=" + z + ",pkgcur=" + this.this$0.mCurrentPictorialAppInfo.getPkgName());
                                break;
                            case 3:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_auto_play", 0);
                                break;
                            case 4:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_tagline", 0);
                                break;
                            case 5:
                                if (!this.this$0.isHeytapPictorial()) {
                                    PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_auto_play", 0);
                                    if (this.this$0.getIntForUser("oplus_customize_pictorial_auto_play", 0) == 1) {
                                        SharedPreferences.Editor editorEdit = this.this$0.mContext.getSharedPreferences("rm_pictorial_info", 0).edit();
                                        editorEdit.putInt("rm_pictorial_open_auto_play_by_itself", 1);
                                        editorEdit.apply();
                                        KgdLogUtil.i("PictorialStatusObserver", "mAutoPlayObserver,rm pictorial self set auto play switch enable");
                                    }
                                }
                                break;
                            case 6:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "mobile_data_pictorial_support", 0);
                                break;
                            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                                if (!this.this$0.isHeytapPictorial()) {
                                    PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_enable_play", 0);
                                }
                                break;
                            case 8:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "swipe_left_display_content", 0);
                                break;
                            case 9:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "pictorial_activate_not_show", 0);
                                break;
                            case 10:
                                if (!this.this$0.isHeytapPictorial() && (pictorialAppInfo = (pictorialAppDataImpl = ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).mPictorialAppDataImpl).mCurrentPictorialAppInfo) != null) {
                                    pictorialAppDataImpl.clearPictorialApp(pictorialAppInfo);
                                    break;
                                }
                                break;
                            case 11:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_tagline", 0);
                                break;
                            default:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_other_pictorial_available", 1);
                                break;
                        }
                    }
                };
            }
            if (this.mTaglineStateObserver == null) {
                final int i4 = 4;
                this.mTaglineStateObserver = new ContentObserver(this, new Handler(Looper.getMainLooper())) { // from class: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialStatusObserver.1
                    public final /* synthetic */ PictorialStatusObserver this$0;

                    {
                        this.this$0 = this;
                    }

                    @Override // android.database.ContentObserver
                    public final void onChange(boolean z) {
                        PictorialAppDataImpl pictorialAppDataImpl;
                        PictorialAppInfo pictorialAppInfo;
                        switch (i4) {
                            case 0:
                                if (!this.this$0.isHeytapPictorial()) {
                                    PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_apply", 0);
                                }
                                break;
                            case 1:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_apply", 0);
                                KgdLogUtil.d("PictorialStatusObserver", " mMainSwitchObserver gl switchStatusSetting set1 b=" + z + ",pkgcur=" + this.this$0.mCurrentPictorialAppInfo.getPkgName());
                                break;
                            case 2:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_enable_play", 0);
                                KgdLogUtil.d("PictorialStatusObserver", " mEnablePlayObserver switchStatusSetting set1 b=" + z + ",pkgcur=" + this.this$0.mCurrentPictorialAppInfo.getPkgName());
                                break;
                            case 3:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_auto_play", 0);
                                break;
                            case 4:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_tagline", 0);
                                break;
                            case 5:
                                if (!this.this$0.isHeytapPictorial()) {
                                    PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_auto_play", 0);
                                    if (this.this$0.getIntForUser("oplus_customize_pictorial_auto_play", 0) == 1) {
                                        SharedPreferences.Editor editorEdit = this.this$0.mContext.getSharedPreferences("rm_pictorial_info", 0).edit();
                                        editorEdit.putInt("rm_pictorial_open_auto_play_by_itself", 1);
                                        editorEdit.apply();
                                        KgdLogUtil.i("PictorialStatusObserver", "mAutoPlayObserver,rm pictorial self set auto play switch enable");
                                    }
                                }
                                break;
                            case 6:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "mobile_data_pictorial_support", 0);
                                break;
                            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                                if (!this.this$0.isHeytapPictorial()) {
                                    PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_enable_play", 0);
                                }
                                break;
                            case 8:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "swipe_left_display_content", 0);
                                break;
                            case 9:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "pictorial_activate_not_show", 0);
                                break;
                            case 10:
                                if (!this.this$0.isHeytapPictorial() && (pictorialAppInfo = (pictorialAppDataImpl = ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).mPictorialAppDataImpl).mCurrentPictorialAppInfo) != null) {
                                    pictorialAppDataImpl.clearPictorialApp(pictorialAppInfo);
                                    break;
                                }
                                break;
                            case 11:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_tagline", 0);
                                break;
                            default:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_other_pictorial_available", 1);
                                break;
                        }
                    }
                };
            }
            KgdLogUtil.i("PictorialStatusObserver", "registerOplusGLObserver" + this.mCurrentPictorialAppInfo);
            ObserverDispatcher observerDispatcher = ObserverDispatcher.getInstance();
            observerDispatcher.registerObserver(getOplusGLMainSwitchKey(), this.mMainSwitchObserver);
            observerDispatcher.registerObserver(getOplusGLEnablePlayKey(), this.mEnablePlayObserver);
            observerDispatcher.registerObserver(getOplusGLAutoPlayKey(), this.mAutoPlayObserver);
            observerDispatcher.registerObserver(getOplusGLTaglineStateKey(), this.mTaglineStateObserver);
            return;
        }
        if (isHeytapPictorial()) {
            return;
        }
        if (!isHeytapPictorial() && (this.mMainSwitchObserver == null || this.mOtherPictorialAvailableStateObserver == null || this.mDataSwitchObserver == null || this.mTaglineStateObserver == null || this.mAutoPlayObserver == null || ((isGlanceStyle() && this.mEnablePlayObserver == null) || ((2 == this.mCurrentPictorialAppInfo.getStyle() && this.mSwipeLeftObserver == null) || (3 == this.mCurrentPictorialAppInfo.getStyle() && this.mOtherPictorialActivateNotShowObserver == null))))) {
            final int i5 = 0;
            this.mMainSwitchObserver = new ContentObserver(this, new Handler(Looper.getMainLooper())) { // from class: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialStatusObserver.1
                public final /* synthetic */ PictorialStatusObserver this$0;

                {
                    this.this$0 = this;
                }

                @Override // android.database.ContentObserver
                public final void onChange(boolean z) {
                    PictorialAppDataImpl pictorialAppDataImpl;
                    PictorialAppInfo pictorialAppInfo;
                    switch (i5) {
                        case 0:
                            if (!this.this$0.isHeytapPictorial()) {
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_apply", 0);
                            }
                            break;
                        case 1:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_apply", 0);
                            KgdLogUtil.d("PictorialStatusObserver", " mMainSwitchObserver gl switchStatusSetting set1 b=" + z + ",pkgcur=" + this.this$0.mCurrentPictorialAppInfo.getPkgName());
                            break;
                        case 2:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_enable_play", 0);
                            KgdLogUtil.d("PictorialStatusObserver", " mEnablePlayObserver switchStatusSetting set1 b=" + z + ",pkgcur=" + this.this$0.mCurrentPictorialAppInfo.getPkgName());
                            break;
                        case 3:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_auto_play", 0);
                            break;
                        case 4:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_tagline", 0);
                            break;
                        case 5:
                            if (!this.this$0.isHeytapPictorial()) {
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_auto_play", 0);
                                if (this.this$0.getIntForUser("oplus_customize_pictorial_auto_play", 0) == 1) {
                                    SharedPreferences.Editor editorEdit = this.this$0.mContext.getSharedPreferences("rm_pictorial_info", 0).edit();
                                    editorEdit.putInt("rm_pictorial_open_auto_play_by_itself", 1);
                                    editorEdit.apply();
                                    KgdLogUtil.i("PictorialStatusObserver", "mAutoPlayObserver,rm pictorial self set auto play switch enable");
                                }
                            }
                            break;
                        case 6:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "mobile_data_pictorial_support", 0);
                            break;
                        case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                            if (!this.this$0.isHeytapPictorial()) {
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_enable_play", 0);
                            }
                            break;
                        case 8:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "swipe_left_display_content", 0);
                            break;
                        case 9:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "pictorial_activate_not_show", 0);
                            break;
                        case 10:
                            if (!this.this$0.isHeytapPictorial() && (pictorialAppInfo = (pictorialAppDataImpl = ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).mPictorialAppDataImpl).mCurrentPictorialAppInfo) != null) {
                                pictorialAppDataImpl.clearPictorialApp(pictorialAppInfo);
                                break;
                            }
                            break;
                        case 11:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_tagline", 0);
                            break;
                        default:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_other_pictorial_available", 1);
                            break;
                    }
                }
            };
            final int i6 = 5;
            this.mAutoPlayObserver = new ContentObserver(this, new Handler(Looper.getMainLooper())) { // from class: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialStatusObserver.1
                public final /* synthetic */ PictorialStatusObserver this$0;

                {
                    this.this$0 = this;
                }

                @Override // android.database.ContentObserver
                public final void onChange(boolean z) {
                    PictorialAppDataImpl pictorialAppDataImpl;
                    PictorialAppInfo pictorialAppInfo;
                    switch (i6) {
                        case 0:
                            if (!this.this$0.isHeytapPictorial()) {
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_apply", 0);
                            }
                            break;
                        case 1:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_apply", 0);
                            KgdLogUtil.d("PictorialStatusObserver", " mMainSwitchObserver gl switchStatusSetting set1 b=" + z + ",pkgcur=" + this.this$0.mCurrentPictorialAppInfo.getPkgName());
                            break;
                        case 2:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_enable_play", 0);
                            KgdLogUtil.d("PictorialStatusObserver", " mEnablePlayObserver switchStatusSetting set1 b=" + z + ",pkgcur=" + this.this$0.mCurrentPictorialAppInfo.getPkgName());
                            break;
                        case 3:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_auto_play", 0);
                            break;
                        case 4:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_tagline", 0);
                            break;
                        case 5:
                            if (!this.this$0.isHeytapPictorial()) {
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_auto_play", 0);
                                if (this.this$0.getIntForUser("oplus_customize_pictorial_auto_play", 0) == 1) {
                                    SharedPreferences.Editor editorEdit = this.this$0.mContext.getSharedPreferences("rm_pictorial_info", 0).edit();
                                    editorEdit.putInt("rm_pictorial_open_auto_play_by_itself", 1);
                                    editorEdit.apply();
                                    KgdLogUtil.i("PictorialStatusObserver", "mAutoPlayObserver,rm pictorial self set auto play switch enable");
                                }
                            }
                            break;
                        case 6:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "mobile_data_pictorial_support", 0);
                            break;
                        case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                            if (!this.this$0.isHeytapPictorial()) {
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_enable_play", 0);
                            }
                            break;
                        case 8:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "swipe_left_display_content", 0);
                            break;
                        case 9:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "pictorial_activate_not_show", 0);
                            break;
                        case 10:
                            if (!this.this$0.isHeytapPictorial() && (pictorialAppInfo = (pictorialAppDataImpl = ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).mPictorialAppDataImpl).mCurrentPictorialAppInfo) != null) {
                                pictorialAppDataImpl.clearPictorialApp(pictorialAppInfo);
                                break;
                            }
                            break;
                        case 11:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_tagline", 0);
                            break;
                        default:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_other_pictorial_available", 1);
                            break;
                    }
                }
            };
            final int i7 = 6;
            this.mDataSwitchObserver = new ContentObserver(this, new Handler(Looper.getMainLooper())) { // from class: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialStatusObserver.1
                public final /* synthetic */ PictorialStatusObserver this$0;

                {
                    this.this$0 = this;
                }

                @Override // android.database.ContentObserver
                public final void onChange(boolean z) {
                    PictorialAppDataImpl pictorialAppDataImpl;
                    PictorialAppInfo pictorialAppInfo;
                    switch (i7) {
                        case 0:
                            if (!this.this$0.isHeytapPictorial()) {
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_apply", 0);
                            }
                            break;
                        case 1:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_apply", 0);
                            KgdLogUtil.d("PictorialStatusObserver", " mMainSwitchObserver gl switchStatusSetting set1 b=" + z + ",pkgcur=" + this.this$0.mCurrentPictorialAppInfo.getPkgName());
                            break;
                        case 2:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_enable_play", 0);
                            KgdLogUtil.d("PictorialStatusObserver", " mEnablePlayObserver switchStatusSetting set1 b=" + z + ",pkgcur=" + this.this$0.mCurrentPictorialAppInfo.getPkgName());
                            break;
                        case 3:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_auto_play", 0);
                            break;
                        case 4:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_tagline", 0);
                            break;
                        case 5:
                            if (!this.this$0.isHeytapPictorial()) {
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_auto_play", 0);
                                if (this.this$0.getIntForUser("oplus_customize_pictorial_auto_play", 0) == 1) {
                                    SharedPreferences.Editor editorEdit = this.this$0.mContext.getSharedPreferences("rm_pictorial_info", 0).edit();
                                    editorEdit.putInt("rm_pictorial_open_auto_play_by_itself", 1);
                                    editorEdit.apply();
                                    KgdLogUtil.i("PictorialStatusObserver", "mAutoPlayObserver,rm pictorial self set auto play switch enable");
                                }
                            }
                            break;
                        case 6:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "mobile_data_pictorial_support", 0);
                            break;
                        case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                            if (!this.this$0.isHeytapPictorial()) {
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_enable_play", 0);
                            }
                            break;
                        case 8:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "swipe_left_display_content", 0);
                            break;
                        case 9:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "pictorial_activate_not_show", 0);
                            break;
                        case 10:
                            if (!this.this$0.isHeytapPictorial() && (pictorialAppInfo = (pictorialAppDataImpl = ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).mPictorialAppDataImpl).mCurrentPictorialAppInfo) != null) {
                                pictorialAppDataImpl.clearPictorialApp(pictorialAppInfo);
                                break;
                            }
                            break;
                        case 11:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_tagline", 0);
                            break;
                        default:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_other_pictorial_available", 1);
                            break;
                    }
                }
            };
            if (isGlanceStyle()) {
                final int i8 = 7;
                this.mEnablePlayObserver = new ContentObserver(this, new Handler(Looper.getMainLooper())) { // from class: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialStatusObserver.1
                    public final /* synthetic */ PictorialStatusObserver this$0;

                    {
                        this.this$0 = this;
                    }

                    @Override // android.database.ContentObserver
                    public final void onChange(boolean z) {
                        PictorialAppDataImpl pictorialAppDataImpl;
                        PictorialAppInfo pictorialAppInfo;
                        switch (i8) {
                            case 0:
                                if (!this.this$0.isHeytapPictorial()) {
                                    PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_apply", 0);
                                }
                                break;
                            case 1:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_apply", 0);
                                KgdLogUtil.d("PictorialStatusObserver", " mMainSwitchObserver gl switchStatusSetting set1 b=" + z + ",pkgcur=" + this.this$0.mCurrentPictorialAppInfo.getPkgName());
                                break;
                            case 2:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_enable_play", 0);
                                KgdLogUtil.d("PictorialStatusObserver", " mEnablePlayObserver switchStatusSetting set1 b=" + z + ",pkgcur=" + this.this$0.mCurrentPictorialAppInfo.getPkgName());
                                break;
                            case 3:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_auto_play", 0);
                                break;
                            case 4:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_tagline", 0);
                                break;
                            case 5:
                                if (!this.this$0.isHeytapPictorial()) {
                                    PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_auto_play", 0);
                                    if (this.this$0.getIntForUser("oplus_customize_pictorial_auto_play", 0) == 1) {
                                        SharedPreferences.Editor editorEdit = this.this$0.mContext.getSharedPreferences("rm_pictorial_info", 0).edit();
                                        editorEdit.putInt("rm_pictorial_open_auto_play_by_itself", 1);
                                        editorEdit.apply();
                                        KgdLogUtil.i("PictorialStatusObserver", "mAutoPlayObserver,rm pictorial self set auto play switch enable");
                                    }
                                }
                                break;
                            case 6:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "mobile_data_pictorial_support", 0);
                                break;
                            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                                if (!this.this$0.isHeytapPictorial()) {
                                    PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_enable_play", 0);
                                }
                                break;
                            case 8:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "swipe_left_display_content", 0);
                                break;
                            case 9:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "pictorial_activate_not_show", 0);
                                break;
                            case 10:
                                if (!this.this$0.isHeytapPictorial() && (pictorialAppInfo = (pictorialAppDataImpl = ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).mPictorialAppDataImpl).mCurrentPictorialAppInfo) != null) {
                                    pictorialAppDataImpl.clearPictorialApp(pictorialAppInfo);
                                    break;
                                }
                                break;
                            case 11:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_tagline", 0);
                                break;
                            default:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_other_pictorial_available", 1);
                                break;
                        }
                    }
                };
            }
            if (2 == this.mCurrentPictorialAppInfo.getStyle()) {
                final int i9 = 8;
                this.mSwipeLeftObserver = new ContentObserver(this, new Handler(Looper.getMainLooper())) { // from class: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialStatusObserver.1
                    public final /* synthetic */ PictorialStatusObserver this$0;

                    {
                        this.this$0 = this;
                    }

                    @Override // android.database.ContentObserver
                    public final void onChange(boolean z) {
                        PictorialAppDataImpl pictorialAppDataImpl;
                        PictorialAppInfo pictorialAppInfo;
                        switch (i9) {
                            case 0:
                                if (!this.this$0.isHeytapPictorial()) {
                                    PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_apply", 0);
                                }
                                break;
                            case 1:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_apply", 0);
                                KgdLogUtil.d("PictorialStatusObserver", " mMainSwitchObserver gl switchStatusSetting set1 b=" + z + ",pkgcur=" + this.this$0.mCurrentPictorialAppInfo.getPkgName());
                                break;
                            case 2:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_enable_play", 0);
                                KgdLogUtil.d("PictorialStatusObserver", " mEnablePlayObserver switchStatusSetting set1 b=" + z + ",pkgcur=" + this.this$0.mCurrentPictorialAppInfo.getPkgName());
                                break;
                            case 3:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_auto_play", 0);
                                break;
                            case 4:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_tagline", 0);
                                break;
                            case 5:
                                if (!this.this$0.isHeytapPictorial()) {
                                    PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_auto_play", 0);
                                    if (this.this$0.getIntForUser("oplus_customize_pictorial_auto_play", 0) == 1) {
                                        SharedPreferences.Editor editorEdit = this.this$0.mContext.getSharedPreferences("rm_pictorial_info", 0).edit();
                                        editorEdit.putInt("rm_pictorial_open_auto_play_by_itself", 1);
                                        editorEdit.apply();
                                        KgdLogUtil.i("PictorialStatusObserver", "mAutoPlayObserver,rm pictorial self set auto play switch enable");
                                    }
                                }
                                break;
                            case 6:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "mobile_data_pictorial_support", 0);
                                break;
                            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                                if (!this.this$0.isHeytapPictorial()) {
                                    PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_enable_play", 0);
                                }
                                break;
                            case 8:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "swipe_left_display_content", 0);
                                break;
                            case 9:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "pictorial_activate_not_show", 0);
                                break;
                            case 10:
                                if (!this.this$0.isHeytapPictorial() && (pictorialAppInfo = (pictorialAppDataImpl = ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).mPictorialAppDataImpl).mCurrentPictorialAppInfo) != null) {
                                    pictorialAppDataImpl.clearPictorialApp(pictorialAppInfo);
                                    break;
                                }
                                break;
                            case 11:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_tagline", 0);
                                break;
                            default:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_other_pictorial_available", 1);
                                break;
                        }
                    }
                };
            }
            if (3 == this.mCurrentPictorialAppInfo.getStyle()) {
                final int i10 = 9;
                this.mOtherPictorialActivateNotShowObserver = new ContentObserver(this, new Handler(Looper.getMainLooper())) { // from class: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialStatusObserver.1
                    public final /* synthetic */ PictorialStatusObserver this$0;

                    {
                        this.this$0 = this;
                    }

                    @Override // android.database.ContentObserver
                    public final void onChange(boolean z) {
                        PictorialAppDataImpl pictorialAppDataImpl;
                        PictorialAppInfo pictorialAppInfo;
                        switch (i10) {
                            case 0:
                                if (!this.this$0.isHeytapPictorial()) {
                                    PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_apply", 0);
                                }
                                break;
                            case 1:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_apply", 0);
                                KgdLogUtil.d("PictorialStatusObserver", " mMainSwitchObserver gl switchStatusSetting set1 b=" + z + ",pkgcur=" + this.this$0.mCurrentPictorialAppInfo.getPkgName());
                                break;
                            case 2:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_enable_play", 0);
                                KgdLogUtil.d("PictorialStatusObserver", " mEnablePlayObserver switchStatusSetting set1 b=" + z + ",pkgcur=" + this.this$0.mCurrentPictorialAppInfo.getPkgName());
                                break;
                            case 3:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_auto_play", 0);
                                break;
                            case 4:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_tagline", 0);
                                break;
                            case 5:
                                if (!this.this$0.isHeytapPictorial()) {
                                    PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_auto_play", 0);
                                    if (this.this$0.getIntForUser("oplus_customize_pictorial_auto_play", 0) == 1) {
                                        SharedPreferences.Editor editorEdit = this.this$0.mContext.getSharedPreferences("rm_pictorial_info", 0).edit();
                                        editorEdit.putInt("rm_pictorial_open_auto_play_by_itself", 1);
                                        editorEdit.apply();
                                        KgdLogUtil.i("PictorialStatusObserver", "mAutoPlayObserver,rm pictorial self set auto play switch enable");
                                    }
                                }
                                break;
                            case 6:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "mobile_data_pictorial_support", 0);
                                break;
                            case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                                if (!this.this$0.isHeytapPictorial()) {
                                    PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_enable_play", 0);
                                }
                                break;
                            case 8:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "swipe_left_display_content", 0);
                                break;
                            case 9:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "pictorial_activate_not_show", 0);
                                break;
                            case 10:
                                if (!this.this$0.isHeytapPictorial() && (pictorialAppInfo = (pictorialAppDataImpl = ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).mPictorialAppDataImpl).mCurrentPictorialAppInfo) != null) {
                                    pictorialAppDataImpl.clearPictorialApp(pictorialAppInfo);
                                    break;
                                }
                                break;
                            case 11:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_tagline", 0);
                                break;
                            default:
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_other_pictorial_available", 1);
                                break;
                        }
                    }
                };
            }
            final int i11 = 10;
            this.mClearDataObserver = new ContentObserver(this, new Handler(Looper.getMainLooper())) { // from class: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialStatusObserver.1
                public final /* synthetic */ PictorialStatusObserver this$0;

                {
                    this.this$0 = this;
                }

                @Override // android.database.ContentObserver
                public final void onChange(boolean z) {
                    PictorialAppDataImpl pictorialAppDataImpl;
                    PictorialAppInfo pictorialAppInfo;
                    switch (i11) {
                        case 0:
                            if (!this.this$0.isHeytapPictorial()) {
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_apply", 0);
                            }
                            break;
                        case 1:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_apply", 0);
                            KgdLogUtil.d("PictorialStatusObserver", " mMainSwitchObserver gl switchStatusSetting set1 b=" + z + ",pkgcur=" + this.this$0.mCurrentPictorialAppInfo.getPkgName());
                            break;
                        case 2:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_enable_play", 0);
                            KgdLogUtil.d("PictorialStatusObserver", " mEnablePlayObserver switchStatusSetting set1 b=" + z + ",pkgcur=" + this.this$0.mCurrentPictorialAppInfo.getPkgName());
                            break;
                        case 3:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_auto_play", 0);
                            break;
                        case 4:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_tagline", 0);
                            break;
                        case 5:
                            if (!this.this$0.isHeytapPictorial()) {
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_auto_play", 0);
                                if (this.this$0.getIntForUser("oplus_customize_pictorial_auto_play", 0) == 1) {
                                    SharedPreferences.Editor editorEdit = this.this$0.mContext.getSharedPreferences("rm_pictorial_info", 0).edit();
                                    editorEdit.putInt("rm_pictorial_open_auto_play_by_itself", 1);
                                    editorEdit.apply();
                                    KgdLogUtil.i("PictorialStatusObserver", "mAutoPlayObserver,rm pictorial self set auto play switch enable");
                                }
                            }
                            break;
                        case 6:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "mobile_data_pictorial_support", 0);
                            break;
                        case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                            if (!this.this$0.isHeytapPictorial()) {
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_enable_play", 0);
                            }
                            break;
                        case 8:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "swipe_left_display_content", 0);
                            break;
                        case 9:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "pictorial_activate_not_show", 0);
                            break;
                        case 10:
                            if (!this.this$0.isHeytapPictorial() && (pictorialAppInfo = (pictorialAppDataImpl = ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).mPictorialAppDataImpl).mCurrentPictorialAppInfo) != null) {
                                pictorialAppDataImpl.clearPictorialApp(pictorialAppInfo);
                                break;
                            }
                            break;
                        case 11:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_tagline", 0);
                            break;
                        default:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_other_pictorial_available", 1);
                            break;
                    }
                }
            };
            final int i12 = 11;
            this.mTaglineStateObserver = new ContentObserver(this, new Handler(Looper.getMainLooper())) { // from class: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialStatusObserver.1
                public final /* synthetic */ PictorialStatusObserver this$0;

                {
                    this.this$0 = this;
                }

                @Override // android.database.ContentObserver
                public final void onChange(boolean z) {
                    PictorialAppDataImpl pictorialAppDataImpl;
                    PictorialAppInfo pictorialAppInfo;
                    switch (i12) {
                        case 0:
                            if (!this.this$0.isHeytapPictorial()) {
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_apply", 0);
                            }
                            break;
                        case 1:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_apply", 0);
                            KgdLogUtil.d("PictorialStatusObserver", " mMainSwitchObserver gl switchStatusSetting set1 b=" + z + ",pkgcur=" + this.this$0.mCurrentPictorialAppInfo.getPkgName());
                            break;
                        case 2:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_enable_play", 0);
                            KgdLogUtil.d("PictorialStatusObserver", " mEnablePlayObserver switchStatusSetting set1 b=" + z + ",pkgcur=" + this.this$0.mCurrentPictorialAppInfo.getPkgName());
                            break;
                        case 3:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_auto_play", 0);
                            break;
                        case 4:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_tagline", 0);
                            break;
                        case 5:
                            if (!this.this$0.isHeytapPictorial()) {
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_auto_play", 0);
                                if (this.this$0.getIntForUser("oplus_customize_pictorial_auto_play", 0) == 1) {
                                    SharedPreferences.Editor editorEdit = this.this$0.mContext.getSharedPreferences("rm_pictorial_info", 0).edit();
                                    editorEdit.putInt("rm_pictorial_open_auto_play_by_itself", 1);
                                    editorEdit.apply();
                                    KgdLogUtil.i("PictorialStatusObserver", "mAutoPlayObserver,rm pictorial self set auto play switch enable");
                                }
                            }
                            break;
                        case 6:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "mobile_data_pictorial_support", 0);
                            break;
                        case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                            if (!this.this$0.isHeytapPictorial()) {
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_enable_play", 0);
                            }
                            break;
                        case 8:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "swipe_left_display_content", 0);
                            break;
                        case 9:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "pictorial_activate_not_show", 0);
                            break;
                        case 10:
                            if (!this.this$0.isHeytapPictorial() && (pictorialAppInfo = (pictorialAppDataImpl = ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).mPictorialAppDataImpl).mCurrentPictorialAppInfo) != null) {
                                pictorialAppDataImpl.clearPictorialApp(pictorialAppInfo);
                                break;
                            }
                            break;
                        case 11:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_tagline", 0);
                            break;
                        default:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_other_pictorial_available", 1);
                            break;
                    }
                }
            };
            final int i13 = 12;
            this.mOtherPictorialAvailableStateObserver = new ContentObserver(this, new Handler(Looper.getMainLooper())) { // from class: com.oplus.systemui.keyguard.wallpaper.pictorial.PictorialStatusObserver.1
                public final /* synthetic */ PictorialStatusObserver this$0;

                {
                    this.this$0 = this;
                }

                @Override // android.database.ContentObserver
                public final void onChange(boolean z) {
                    PictorialAppDataImpl pictorialAppDataImpl;
                    PictorialAppInfo pictorialAppInfo;
                    switch (i13) {
                        case 0:
                            if (!this.this$0.isHeytapPictorial()) {
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_apply", 0);
                            }
                            break;
                        case 1:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_apply", 0);
                            KgdLogUtil.d("PictorialStatusObserver", " mMainSwitchObserver gl switchStatusSetting set1 b=" + z + ",pkgcur=" + this.this$0.mCurrentPictorialAppInfo.getPkgName());
                            break;
                        case 2:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_enable_play", 0);
                            KgdLogUtil.d("PictorialStatusObserver", " mEnablePlayObserver switchStatusSetting set1 b=" + z + ",pkgcur=" + this.this$0.mCurrentPictorialAppInfo.getPkgName());
                            break;
                        case 3:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_auto_play", 0);
                            break;
                        case 4:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_tagline", 0);
                            break;
                        case 5:
                            if (!this.this$0.isHeytapPictorial()) {
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_auto_play", 0);
                                if (this.this$0.getIntForUser("oplus_customize_pictorial_auto_play", 0) == 1) {
                                    SharedPreferences.Editor editorEdit = this.this$0.mContext.getSharedPreferences("rm_pictorial_info", 0).edit();
                                    editorEdit.putInt("rm_pictorial_open_auto_play_by_itself", 1);
                                    editorEdit.apply();
                                    KgdLogUtil.i("PictorialStatusObserver", "mAutoPlayObserver,rm pictorial self set auto play switch enable");
                                }
                            }
                            break;
                        case 6:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "mobile_data_pictorial_support", 0);
                            break;
                        case IcdfNotificationManagerOuterClass$IcdfNotification.EXPANDEDCHILD_FIELD_NUMBER /* 7 */:
                            if (!this.this$0.isHeytapPictorial()) {
                                PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_enable_play", 0);
                            }
                            break;
                        case 8:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "swipe_left_display_content", 0);
                            break;
                        case 9:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "pictorial_activate_not_show", 0);
                            break;
                        case 10:
                            if (!this.this$0.isHeytapPictorial() && (pictorialAppInfo = (pictorialAppDataImpl = ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).mPictorialAppDataImpl).mCurrentPictorialAppInfo) != null) {
                                pictorialAppDataImpl.clearPictorialApp(pictorialAppInfo);
                                break;
                            }
                            break;
                        case 11:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_pictorial_tagline", 0);
                            break;
                        default:
                            PictorialStatusObserver.m11$$Nest$mswitchStatusSetting(this.this$0, "oplus_customize_other_pictorial_available", 1);
                            break;
                    }
                }
            };
        }
        ObserverDispatcher observerDispatcher2 = ObserverDispatcher.getInstance();
        observerDispatcher2.registerObserver(getSpecialMainSwitchKey(), this.mMainSwitchObserver);
        observerDispatcher2.registerObserver(getSpecialDataSwitchKey(), this.mDataSwitchObserver);
        observerDispatcher2.registerObserver(getSpecialAutoPlayKey(), this.mAutoPlayObserver);
        if (isGlanceStyle()) {
            observerDispatcher2.registerObserver(getSpecialEnablePlayKey(), this.mEnablePlayObserver);
        }
        if (2 == this.mCurrentPictorialAppInfo.getStyle()) {
            observerDispatcher2.registerObserver(getSpecialSwipeLiftKey(), this.mSwipeLeftObserver);
        }
        if (3 == this.mCurrentPictorialAppInfo.getStyle()) {
            observerDispatcher2.registerObserver(getSpecialOtherPictorialActivateNotShowKey(), this.mOtherPictorialActivateNotShowObserver);
        }
        observerDispatcher2.registerObserver(getSpecialTaglineStateKey(), this.mTaglineStateObserver);
        observerDispatcher2.registerObserver(getSpecialOtherPictorialAvailableKey(), this.mOtherPictorialAvailableStateObserver);
        observerDispatcher2.registerObserver(getClearDataKey(), this.mClearDataObserver);
    }

    public void setPictorialAppInfo(PictorialAppInfo pictorialAppInfo) {
        this.mCurrentPictorialAppInfo = pictorialAppInfo;
    }

    public final void unregister() {
        KeyguardFeatureOption.INSTANCE.getClass();
        if (KeyguardFeatureOption.isSupportOplusGLPictorial) {
            KgdLogUtil.i("PictorialStatusObserver", "unregisterOplusGLObserver" + this.mCurrentPictorialAppInfo);
            ObserverDispatcher observerDispatcher = ObserverDispatcher.getInstance();
            observerDispatcher.unregisterObserver(this.mEnablePlayObserver);
            observerDispatcher.unregisterObserver(this.mAutoPlayObserver);
            observerDispatcher.unregisterObserver(this.mTaglineStateObserver);
            observerDispatcher.unregisterObserver(this.mMainSwitchObserver);
            return;
        }
        if (isHeytapPictorial()) {
            return;
        }
        ObserverDispatcher observerDispatcher2 = ObserverDispatcher.getInstance();
        observerDispatcher2.unregisterObserver(this.mMainSwitchObserver);
        observerDispatcher2.unregisterObserver(this.mDataSwitchObserver);
        observerDispatcher2.unregisterObserver(this.mAutoPlayObserver);
        if (isGlanceStyle()) {
            observerDispatcher2.unregisterObserver(this.mEnablePlayObserver);
        }
        if (2 == this.mCurrentPictorialAppInfo.getStyle()) {
            observerDispatcher2.unregisterObserver(this.mSwipeLeftObserver);
        }
        if (3 == this.mCurrentPictorialAppInfo.getStyle()) {
            observerDispatcher2.unregisterObserver(this.mOtherPictorialActivateNotShowObserver);
        }
        observerDispatcher2.unregisterObserver(this.mClearDataObserver);
        observerDispatcher2.unregisterObserver(this.mTaglineStateObserver);
        observerDispatcher2.unregisterObserver(this.mOtherPictorialAvailableStateObserver);
    }
}
