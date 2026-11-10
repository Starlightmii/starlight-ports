package com.oplus.systemui.keyguard.wallpaper.pictorial;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface$;
import com.android.keyguard.wallpaper.KeyguardWallpaperInfo;
import com.android.systemui.Dependency;
import com.android.systemui.DependencyEx;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.oplus.systemui.keyguard.lockdead.LockDeadUtil;
import com.oplus.systemui.keyguard.wallpaper.pictorial.app.PictorialAppInfo;
import com.oplus.systemui.shade.ShadeExpansionHelperImpl;
import com.oplusos.keyguard.statistic.KeyguardStatistic;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import java.util.HashMap;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PictorialWallpaperStatistic {
    private static final String ACTION_SHOW = "show";
    private static final String ACTION_SHOW_END = "show_end";
    private static final String BUTTON_TEXT = "button_text";
    private static final String CASE = "case";
    private static final String CATEGORY = "category";
    private static final String DURATION = "duration";
    private static final String EVENT_NAME = "eventname";
    private static final String GROUP_ID = "groupId";
    private static final String GROUP_TYPE = "groupType";
    private static final String IF_COVER = "if_cover";
    private static final String IMAGE_ID = "imageId";
    private static final String IS_FIRST_UPDATE_SCREEN_STATE = "is_first_update_screen_state";
    private static final String LAST_STATISTIC_PICTORIAL_SWITCH_TIME = "last_statistic_pictorial_switch_time";
    private static final String MODE = "mode";
    private static final String MODE_PANORAMIC_AOD = "p_aod";
    private static final String OBSTRUCTIONS_HEIGHT = "obstructions_height_px";
    private static final String PICTORIAL_SP_FILE_NAME = "pictorial_sp";
    private static final String SLIDE_TYPE = "slidetype";
    public static final String TAG = "PictorialWallpaperStatistic";
    private static final String TYPE = "type";
    public static long mExposeStartTime;
    public static boolean mExposureStart;
    public static boolean mHasStatisticExposeStart;
    public static long mLastSendTime;
    public static final PictorialWallpaperStatistic INSTANCE = new PictorialWallpaperStatistic();
    private static final ExposedWallpapersQueue exposedWallpapersQueue = new ExposedWallpapersQueue();
    private static final PictorialWallpaperStatistic$mExposureStartRunnable$1 mExposureStartRunnable = new PictorialWallpaperStatistic$mExposureStartRunnable$1();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface StartRunnable extends Runnable {
        KeyguardWallpaperInfo getKeyguardWallpaperInfo();

        void setKeyguardWallpaperInfo(KeyguardWallpaperInfo keyguardWallpaperInfo);
    }

    public static final boolean isCurrentWallpaperExposedWhenPanoramicAod(KeyguardWallpaperInfo keyguardWallpaperInfo) {
        if (keyguardWallpaperInfo != null && keyguardWallpaperInfo.isPictorialType()) {
            PictorialWallpaperInfo pictorialWallpaperInfo = keyguardWallpaperInfo instanceof PictorialWallpaperInfo ? (PictorialWallpaperInfo) keyguardWallpaperInfo : null;
            if (pictorialWallpaperInfo != null && !exposedWallpapersQueue.contains(pictorialWallpaperInfo.getImageId())) {
                return false;
            }
        }
        return true;
    }

    public static final void statisticExposeEnd(int i, KeyguardWallpaperInfo keyguardWallpaperInfo, boolean z) {
        Handler mainHandler = SystemUIThreadUtil.getMainHandler();
        PictorialWallpaperStatistic$mExposureStartRunnable$1 pictorialWallpaperStatistic$mExposureStartRunnable$1 = mExposureStartRunnable;
        mainHandler.removeCallbacks(pictorialWallpaperStatistic$mExposureStartRunnable$1);
        pictorialWallpaperStatistic$mExposureStartRunnable$1.setKeyguardWallpaperInfo(null);
        if (mHasStatisticExposeStart) {
            if (keyguardWallpaperInfo == null || !keyguardWallpaperInfo.isPictorialType()) {
                mHasStatisticExposeStart = false;
                return;
            }
            PictorialWallpaperInfo pictorialWallpaperInfo = (PictorialWallpaperInfo) keyguardWallpaperInfo;
            long jElapsedRealtime = SystemClock.elapsedRealtime() - mExposeStartTime;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                Log.d("SystemUi--Keyguard", "PictorialWallpaperStatistic-->".concat("statisticExposeEnd time = " + jElapsedRealtime + ", type = " + i + ", isPanoramicAod = " + z));
            }
            INSTANCE.getClass();
            if (mExposureStart) {
                mExposureStart = false;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                    StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("exposureEnd : imageInfo = ", pictorialWallpaperInfo.getTextTitle(), ", id = ", pictorialWallpaperInfo.getImageId(), ", duration = ");
                    sbM.append(jElapsedRealtime);
                    sbM.append(", type = ");
                    sbM.append(i);
                    sbM.append(", isPanoramicAod = ");
                    sbM.append(z);
                    Log.d("SystemUi--Keyguard", "PictorialWallpaperStatistic-->".concat(sbM.toString()));
                }
                Bundle bundle = new Bundle();
                bundle.putInt(CATEGORY, 10000);
                bundle.putString(EVENT_NAME, ACTION_SHOW_END);
                bundle.putString(IMAGE_ID, pictorialWallpaperInfo.getImageId());
                if (pictorialWallpaperInfo.getGroupId() != null) {
                    bundle.putString(GROUP_ID, pictorialWallpaperInfo.getGroupId());
                }
                bundle.putInt(GROUP_TYPE, pictorialWallpaperInfo.getGroupType());
                bundle.putInt(CASE, 1);
                bundle.putInt("type", i);
                bundle.putInt(SLIDE_TYPE, 1);
                bundle.putLong(DURATION, jElapsedRealtime);
                if (z) {
                    bundle.putString(MODE, MODE_PANORAMIC_AOD);
                }
                ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).reportStatisticData(bundle);
            }
            mHasStatisticExposeStart = false;
            if (z) {
                exposedWallpapersQueue.add(pictorialWallpaperInfo.getImageId());
            }
        }
    }

    public static final void statisticExposeStart(KeyguardWallpaperInfo keyguardWallpaperInfo, boolean z) {
        if (keyguardWallpaperInfo == null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                Log.d("SystemUi--Keyguard", "PictorialWallpaperStatistic-->keyguardWallpaperInfo is null, return");
                return;
            }
            return;
        }
        if (mHasStatisticExposeStart) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                Log.d("SystemUi--Keyguard", "PictorialWallpaperStatistic-->mHasStatisticExposeStart started, return");
                return;
            }
            return;
        }
        if (!keyguardWallpaperInfo.isPictorialType()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                Log.d("SystemUi--Keyguard", "PictorialWallpaperStatistic-->is not PictorialType, return");
                return;
            }
            return;
        }
        mExposeStartTime = SystemClock.elapsedRealtime();
        PictorialWallpaperStatistic$mExposureStartRunnable$1 pictorialWallpaperStatistic$mExposureStartRunnable$1 = mExposureStartRunnable;
        pictorialWallpaperStatistic$mExposureStartRunnable$1.setKeyguardWallpaperInfo(keyguardWallpaperInfo);
        pictorialWallpaperStatistic$mExposureStartRunnable$1.setPanoramicAod(z);
        SystemUIThreadUtil.getMainHandler().postDelayed(pictorialWallpaperStatistic$mExposureStartRunnable$1, 200L);
    }

    public static final void statisticPictorialSwitchState(Context context, KeyguardWallpaperInfo keyguardWallpaperInfo) {
        if (keyguardWallpaperInfo == null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                Log.d("SystemUi--Keyguard", "PictorialWallpaperStatistic-->statisticPictorialSwitchState--keyguardWallpaperInfo is null, return");
                return;
            }
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", "PictorialWallpaperStatistic-->".concat("statisticPictorialSwitchState--keyguardWallpaperInfo " + keyguardWallpaperInfo));
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        INSTANCE.getClass();
        long j = mLastSendTime;
        if (j <= 0) {
            StringBuilder sb = PictorialUtils.sStringBuilder;
            j = context.getSharedPreferences(PICTORIAL_SP_FILE_NAME, 0).getLong(LAST_STATISTIC_PICTORIAL_SWITCH_TIME, -1L);
            mLastSendTime = j;
        }
        if (jCurrentTimeMillis - j >= 86400000) {
            HashMap map = new HashMap();
            String str = ((PictorialMediator) DependencyEx.sDependency.getDependency(PictorialMediator.class)).isMainSwitchOn() ? "1" : "0";
            String str2 = keyguardWallpaperInfo.mType != 0 ? "1" : "0";
            map.put("switch_magazine", str);
            map.put("lock_wallpaper", str2);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                Log.d("SystemUi--Keyguard", SystemUIApplicationImpl$.ExternalSyntheticOutline0.m("statisticPictorialSwitchState switchState = ", str, ", ", str2, "PictorialWallpaperStatistic-->"));
            }
            KeyguardStatistic.sendEventData(context, "static_switch_os", map);
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            StringBuilder sb2 = PictorialUtils.sStringBuilder;
            context.getSharedPreferences(PICTORIAL_SP_FILE_NAME, 0).edit().putLong(LAST_STATISTIC_PICTORIAL_SWITCH_TIME, jCurrentTimeMillis2).apply();
            mLastSendTime = jCurrentTimeMillis2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0061  */
    public static final void statisticScreenState(Context context, KeyguardWallpaperInfo keyguardWallpaperInfo) {
        boolean z;
        if (keyguardWallpaperInfo == null) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
                Log.d("SystemUi--Keyguard", "PictorialWallpaperStatistic-->statisticScreenState--keyguardWallpaperInfo is null, return");
                return;
            }
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_KEYGUARD)) {
            Log.d("SystemUi--Keyguard", "PictorialWallpaperStatistic-->".concat("statisticScreenState--keyguardWallpaperInfo " + keyguardWallpaperInfo));
        }
        HashMap map = new HashMap();
        if (((KeyguardStateController) Dependency.sDependency.getDependencyInner(KeyguardStateController.class)).mOccluded) {
            map.put("type", "3");
            z = true;
        } else {
            LockDeadUtil.INSTANCE.getClass();
            if (LockDeadUtil.isLockDeadState) {
                map.put("type", "3");
                z = true;
            } else {
                z = false;
            }
        }
        if (keyguardWallpaperInfo.isPictorialType()) {
            map.put(z ? "pictype" : "type", "0");
            PictorialWallpaperInfo pictorialWallpaperInfo = (PictorialWallpaperInfo) keyguardWallpaperInfo;
            map.put(PictorialAppInfo.ID, pictorialWallpaperInfo.getImageId());
            if (pictorialWallpaperInfo.getGroupId() != null) {
                map.put("group_id", pictorialWallpaperInfo.getGroupId());
            }
        } else if (keyguardWallpaperInfo.mType == 0) {
            map.put(z ? "pictype" : "type", "1");
        } else {
            map.put(z ? "pictype" : "type", ShadeExpansionHelperImpl.VAL_EXPANDED_PANEL_QS_BOTH);
        }
        if (map.isEmpty()) {
            return;
        }
        KeyguardStatistic.sendEventData(context, "screen_show", map);
    }

    public static final void updateFirstUploadScreenState(Context context, KeyguardWallpaperInfo keyguardWallpaperInfo) {
        INSTANCE.getClass();
        StringBuilder sb = PictorialUtils.sStringBuilder;
        if (context.getSharedPreferences(PICTORIAL_SP_FILE_NAME, 0).getBoolean(IS_FIRST_UPDATE_SCREEN_STATE, true)) {
            context.getSharedPreferences(PICTORIAL_SP_FILE_NAME, 0).edit().putBoolean(IS_FIRST_UPDATE_SCREEN_STATE, false).apply();
            statisticScreenState(context, keyguardWallpaperInfo);
        }
    }
}
