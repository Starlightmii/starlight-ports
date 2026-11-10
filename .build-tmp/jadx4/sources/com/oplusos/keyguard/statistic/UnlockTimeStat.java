package com.oplusos.keyguard.statistic;

import android.content.Context;
import android.os.SystemClock;
import com.oplusos.keyguard.utils.KeyguardThreadUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class UnlockTimeStat {
    public static final Companion Companion = new Companion();
    private static final String KEY_FACEUNLOCK_STAYON_SWITCH = "oplus_customize_face_unlock_remain_unlock_switch";
    private static final String TAG = "UnlockTimeStat";
    public static boolean isAuthenticated = false;
    private static String mCustomStr = "";
    public static int mUnlockType = -1;
    public static boolean mWakeupFromSleep;
    public static long onAuthenticatedTime;
    public static long onKeyguardLayerHideTime;
    public static long onStartKeyguardExitAnimationTime;
    public static long onSystemUIKeyguardGoingAwayTime;
    private static volatile UnlockTimeStat sInstance;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final UnlockTimeStat getInstance(Context context) {
            if (UnlockTimeStat.sInstance == null) {
                synchronized (UnlockTimeStat.class) {
                    if (UnlockTimeStat.sInstance == null) {
                        UnlockTimeStat.sInstance = new UnlockTimeStat();
                    }
                }
            }
            return UnlockTimeStat.sInstance;
        }
    }

    public static void recordAuthenticatedTime(int i) {
        mUnlockType = i;
        if (i == 5) {
            if (mWakeupFromSleep) {
                mUnlockType = 1;
            } else {
                mUnlockType = 2;
            }
        }
        mWakeupFromSleep = false;
        onAuthenticatedTime = 0L;
        onSystemUIKeyguardGoingAwayTime = 0L;
        onStartKeyguardExitAnimationTime = 0L;
        onKeyguardLayerHideTime = 0L;
        isAuthenticated = true;
        if (mUnlockType != 4) {
            onAuthenticatedTime = SystemClock.elapsedRealtime();
        }
    }

    public final void recordFinishedTime(Context context) {
        SystemClock.elapsedRealtime();
        if (isAuthenticated) {
            KeyguardThreadUtil.Companion companion = KeyguardThreadUtil.Companion;
            UnlockTimeStat$sendDateOfAbnormalUnlockTime$1 unlockTimeStat$sendDateOfAbnormalUnlockTime$1 = new UnlockTimeStat$sendDateOfAbnormalUnlockTime$1();
            unlockTimeStat$sendDateOfAbnormalUnlockTime$1.$context = context;
            unlockTimeStat$sendDateOfAbnormalUnlockTime$1.this$0 = this;
            VarHandle.storeStoreFence();
            companion.runOnWorkThread(unlockTimeStat$sendDateOfAbnormalUnlockTime$1);
        }
    }
}
