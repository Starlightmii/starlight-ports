package com.oplusos.systemui.common.sched;

import android.app.ActivityThread;
import android.app.Application;
import android.app.OplusActivityTaskManager;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.Trace;
import android.util.Log;
import android.view.WindowManagerGlobal;
import androidx.activity.result.ActivityResultRegistry$;
import com.oplus.dispatch.OCDHandler;
import com.oplus.memory.FilePageManager;
import com.oplus.osense.OsenseResClient;
import com.oplusos.systemui.common.concurrency.OplusUiOffloadThread;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.PerformanceUtils;
import java.lang.invoke.VarHandle;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSysUiSchedMgr {
    private static final String GAUSSIAN_BLUR_THREAD_NAME = "GaussianBlur";
    private static final String GESTURE_BG_THREAD_NAME = "GestureBackground";
    public static final String SCENE_KEYGUARD_GONE = "keyguard gone";
    private static final String TAG = "OplusSysUiSchedMgr";
    private static final String TRACK_NAME = "UAF-Scene";
    private final Lazy backgroundHandler$delegate;
    private final Lazy delayMs$delegate;
    public volatile boolean screenOffThresholdReached;
    private final Lazy sysuiApkPath$delegate;
    private final Lazy sysuiApkStartDexIndex$delegate;
    private final Lazy trimCacheValue$delegate;
    private final Lazy trimMemoryValue$delegate;
    private final Lazy unlockCleanMaxCount$delegate;
    public static final Companion Companion = new Companion();
    private static final OplusSysUiSchedMgr sInstance = new OplusSysUiSchedMgr();
    public boolean isShowingDot = false;
    public boolean isNotificationShadeExpanded = false;
    private final OsenseResClient osenseResClient = OsenseResClient.get(OplusSysUiSchedMgr.class);
    private final AtomicInteger unlockCleanCount = new AtomicInteger(0);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final OplusSysUiSchedMgr getInstance() {
            return OplusSysUiSchedMgr.sInstance;
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v22 com.oplusos.systemui.common.sched.OplusSysUiSchedMgr$UafSceneEvent[], still in use, count: 1, list:
      (r0v22 com.oplusos.systemui.common.sched.OplusSysUiSchedMgr$UafSceneEvent[]) from 0x0140: INVOKE (r0v22 com.oplusos.systemui.common.sched.OplusSysUiSchedMgr$UafSceneEvent[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[WRAPPED]
    	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
    	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
    	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:100)
    	at jadx.core.utils.InsnRemover.removeAllAndUnbind(InsnRemover.java:257)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:187)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class UafSceneEvent {
        SYSTEM_FOLDSCREEN_SPEED(1004),
        SHADE_EXPAND_PENDING(3000),
        EDGE_SWIPE_BACK_UI(3001),
        EDGE_SWIPE_BACK_EXIT(3002),
        VOLUME_DIALOG_SHOWING(3003),
        PRIVACY_UI_SHOWING(3004),
        NOTIFICATION_CLICK(3005),
        SEEDLING_ANIMATION(3006),
        SEEDLING_EVENT_TRIGGERED(3007),
        UI_MODE_CHANGED(3008),
        SYSTEMUI_HEADSUP_INCALL_INFLATION(3009),
        FINGERPRINT_UNLOCK(3100),
        KEYGUARD_GOING_AWAY(3101),
        LOCK_SCREEN_SHOW(3101),
        GESTURE_SWIPE_DETECT(3999),
        SYSTEM_STATUS_ICON_ADDING(3020),
        CONTROL_PANEL_SWITCH(3030),
        CAMERA_PAUSE_OR_RESUME(3040),
        KEYGUARD_ANIM_RUNNING(3102),
        KEYGUARD_GO_SLEEP(3103),
        SYSTEMUI_SHOW_DIMLAYER(3104),
        SYSTEMUI_SHOW_BIOMETRIC_PROMPT(3105);

        public static final /* synthetic */ EnumEntries $ENTRIES;
        private final int id;

        static {
            $ENTRIES = EnumEntriesKt.enumEntries(uafSceneEventArr);
        }

        public UafSceneEvent(int i) {
            super(str, i);
            this.id = i;
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        public static UafSceneEvent valueOf(String str) {
            return (UafSceneEvent) Enum.valueOf(UafSceneEvent.class, str);
        }

        public static UafSceneEvent[] values() {
            return (UafSceneEvent[]) $VALUES.clone();
        }

        public final int getId() {
            return this.id;
        }
    }

    /* JADX INFO: renamed from: com.oplusos.systemui.common.sched.OplusSysUiSchedMgr$trimMemory$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public /* synthetic */ OplusSysUiSchedMgr this$0;

        public /* synthetic */ AnonymousClass2(int i) {
            this.$r8$classId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    this.this$0.freeApkFilePages();
                    this.this$0.unlockCleanCount.incrementAndGet();
                    break;
                default:
                    this.this$0.reportKeyThread(OplusSysUiSchedMgr.GESTURE_BG_THREAD_NAME, Process.myTid(), UafSceneEvent.CAMERA_PAUSE_OR_RESUME, false);
                    break;
            }
        }
    }

    public OplusSysUiSchedMgr() {
        final int i = 0;
        this.trimMemoryValue$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.sched.OplusSysUiSchedMgr$$ExternalSyntheticLambda0
            public final Object invoke() {
                ApplicationInfo applicationInfo;
                switch (i) {
                    case 0:
                        FeatureOption.INSTANCE.getClass();
                        return Integer.valueOf(SystemProperties.getInt("persist.systemui.trim.memory.value", FeatureOption.isLightOs() ? 80 : 20));
                    case 1:
                        return Integer.valueOf(SystemProperties.getInt("persist.systemui.trim.cache.value", 0));
                    case 2:
                        return Long.valueOf(((long) SystemProperties.getInt("persist.systemui.unlock.clean.time", 25)) * 1000);
                    case 3:
                        return Integer.valueOf(SystemProperties.getInt("persist.systemui.unlock.clean.max.count", 1));
                    case 4:
                        return Integer.valueOf(SystemProperties.getInt("persist.systemui.dex.clean.start.index", 0));
                    default:
                        Application applicationCurrentApplication = ActivityThread.currentApplication();
                        String str = (applicationCurrentApplication == null || (applicationInfo = applicationCurrentApplication.getApplicationInfo()) == null) ? null : applicationInfo.sourceDir;
                        return str == null ? "" : str;
                }
            }
        });
        final int i2 = 1;
        this.trimCacheValue$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.sched.OplusSysUiSchedMgr$$ExternalSyntheticLambda0
            public final Object invoke() {
                ApplicationInfo applicationInfo;
                switch (i2) {
                    case 0:
                        FeatureOption.INSTANCE.getClass();
                        return Integer.valueOf(SystemProperties.getInt("persist.systemui.trim.memory.value", FeatureOption.isLightOs() ? 80 : 20));
                    case 1:
                        return Integer.valueOf(SystemProperties.getInt("persist.systemui.trim.cache.value", 0));
                    case 2:
                        return Long.valueOf(((long) SystemProperties.getInt("persist.systemui.unlock.clean.time", 25)) * 1000);
                    case 3:
                        return Integer.valueOf(SystemProperties.getInt("persist.systemui.unlock.clean.max.count", 1));
                    case 4:
                        return Integer.valueOf(SystemProperties.getInt("persist.systemui.dex.clean.start.index", 0));
                    default:
                        Application applicationCurrentApplication = ActivityThread.currentApplication();
                        String str = (applicationCurrentApplication == null || (applicationInfo = applicationCurrentApplication.getApplicationInfo()) == null) ? null : applicationInfo.sourceDir;
                        return str == null ? "" : str;
                }
            }
        });
        final int i3 = 2;
        this.delayMs$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.sched.OplusSysUiSchedMgr$$ExternalSyntheticLambda0
            public final Object invoke() {
                ApplicationInfo applicationInfo;
                switch (i3) {
                    case 0:
                        FeatureOption.INSTANCE.getClass();
                        return Integer.valueOf(SystemProperties.getInt("persist.systemui.trim.memory.value", FeatureOption.isLightOs() ? 80 : 20));
                    case 1:
                        return Integer.valueOf(SystemProperties.getInt("persist.systemui.trim.cache.value", 0));
                    case 2:
                        return Long.valueOf(((long) SystemProperties.getInt("persist.systemui.unlock.clean.time", 25)) * 1000);
                    case 3:
                        return Integer.valueOf(SystemProperties.getInt("persist.systemui.unlock.clean.max.count", 1));
                    case 4:
                        return Integer.valueOf(SystemProperties.getInt("persist.systemui.dex.clean.start.index", 0));
                    default:
                        Application applicationCurrentApplication = ActivityThread.currentApplication();
                        String str = (applicationCurrentApplication == null || (applicationInfo = applicationCurrentApplication.getApplicationInfo()) == null) ? null : applicationInfo.sourceDir;
                        return str == null ? "" : str;
                }
            }
        });
        final int i4 = 3;
        this.unlockCleanMaxCount$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.sched.OplusSysUiSchedMgr$$ExternalSyntheticLambda0
            public final Object invoke() {
                ApplicationInfo applicationInfo;
                switch (i4) {
                    case 0:
                        FeatureOption.INSTANCE.getClass();
                        return Integer.valueOf(SystemProperties.getInt("persist.systemui.trim.memory.value", FeatureOption.isLightOs() ? 80 : 20));
                    case 1:
                        return Integer.valueOf(SystemProperties.getInt("persist.systemui.trim.cache.value", 0));
                    case 2:
                        return Long.valueOf(((long) SystemProperties.getInt("persist.systemui.unlock.clean.time", 25)) * 1000);
                    case 3:
                        return Integer.valueOf(SystemProperties.getInt("persist.systemui.unlock.clean.max.count", 1));
                    case 4:
                        return Integer.valueOf(SystemProperties.getInt("persist.systemui.dex.clean.start.index", 0));
                    default:
                        Application applicationCurrentApplication = ActivityThread.currentApplication();
                        String str = (applicationCurrentApplication == null || (applicationInfo = applicationCurrentApplication.getApplicationInfo()) == null) ? null : applicationInfo.sourceDir;
                        return str == null ? "" : str;
                }
            }
        });
        final int i5 = 4;
        this.sysuiApkStartDexIndex$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.sched.OplusSysUiSchedMgr$$ExternalSyntheticLambda0
            public final Object invoke() {
                ApplicationInfo applicationInfo;
                switch (i5) {
                    case 0:
                        FeatureOption.INSTANCE.getClass();
                        return Integer.valueOf(SystemProperties.getInt("persist.systemui.trim.memory.value", FeatureOption.isLightOs() ? 80 : 20));
                    case 1:
                        return Integer.valueOf(SystemProperties.getInt("persist.systemui.trim.cache.value", 0));
                    case 2:
                        return Long.valueOf(((long) SystemProperties.getInt("persist.systemui.unlock.clean.time", 25)) * 1000);
                    case 3:
                        return Integer.valueOf(SystemProperties.getInt("persist.systemui.unlock.clean.max.count", 1));
                    case 4:
                        return Integer.valueOf(SystemProperties.getInt("persist.systemui.dex.clean.start.index", 0));
                    default:
                        Application applicationCurrentApplication = ActivityThread.currentApplication();
                        String str = (applicationCurrentApplication == null || (applicationInfo = applicationCurrentApplication.getApplicationInfo()) == null) ? null : applicationInfo.sourceDir;
                        return str == null ? "" : str;
                }
            }
        });
        final int i6 = 5;
        this.sysuiApkPath$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.oplusos.systemui.common.sched.OplusSysUiSchedMgr$$ExternalSyntheticLambda0
            public final Object invoke() {
                ApplicationInfo applicationInfo;
                switch (i6) {
                    case 0:
                        FeatureOption.INSTANCE.getClass();
                        return Integer.valueOf(SystemProperties.getInt("persist.systemui.trim.memory.value", FeatureOption.isLightOs() ? 80 : 20));
                    case 1:
                        return Integer.valueOf(SystemProperties.getInt("persist.systemui.trim.cache.value", 0));
                    case 2:
                        return Long.valueOf(((long) SystemProperties.getInt("persist.systemui.unlock.clean.time", 25)) * 1000);
                    case 3:
                        return Integer.valueOf(SystemProperties.getInt("persist.systemui.unlock.clean.max.count", 1));
                    case 4:
                        return Integer.valueOf(SystemProperties.getInt("persist.systemui.dex.clean.start.index", 0));
                    default:
                        Application applicationCurrentApplication = ActivityThread.currentApplication();
                        String str = (applicationCurrentApplication == null || (applicationInfo = applicationCurrentApplication.getApplicationInfo()) == null) ? null : applicationInfo.sourceDir;
                        return str == null ? "" : str;
                }
            }
        });
        OplusSysUiSchedMgr$$ExternalSyntheticLambda6 oplusSysUiSchedMgr$$ExternalSyntheticLambda6 = new OplusSysUiSchedMgr$$ExternalSyntheticLambda6();
        oplusSysUiSchedMgr$$ExternalSyntheticLambda6.f$0 = this;
        VarHandle.storeStoreFence();
        this.backgroundHandler$delegate = LazyKt__LazyJVMKt.lazy(oplusSysUiSchedMgr$$ExternalSyntheticLambda6);
    }

    private final long getDelayMs() {
        return ((Number) this.delayMs$delegate.getValue()).longValue();
    }

    public static final OplusSysUiSchedMgr getInstance() {
        return Companion.getInstance();
    }

    private final String getSysuiApkPath() {
        return (String) this.sysuiApkPath$delegate.getValue();
    }

    private final int getSysuiApkStartDexIndex() {
        return ((Number) this.sysuiApkStartDexIndex$delegate.getValue()).intValue();
    }

    private final int getTrimCacheValue() {
        return ((Number) this.trimCacheValue$delegate.getValue()).intValue();
    }

    private final int getTrimMemoryValue() {
        return ((Number) this.trimMemoryValue$delegate.getValue()).intValue();
    }

    private final int getUnlockCleanMaxCount() {
        return ((Number) this.unlockCleanMaxCount$delegate.getValue()).intValue();
    }

    public static void schedForSystemUiSwitched(int i) {
        try {
            OplusActivityTaskManager.getInstance().notifyUiSwitched("com.android.systemui", i);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "OplusSysUiSchedMgr-->".concat("notifyUiSwitched: " + i));
            }
        } catch (RemoteException e) {
            LogUtil.e$default(8, LogUtil.TAG_COMMON, TAG, "Failed to call notifyUiSwitched: " + e.getMessage());
        }
    }

    public final void freeApkFilePages() {
        try {
            long jUptimeMillis = SystemClock.uptimeMillis();
            String[] strArr = {getSysuiApkPath(), "*.apk", "*.jar", "*.ttf"};
            int[] iArr = {getSysuiApkStartDexIndex(), 0, 0, 0};
            LogUtil.i$default(LogUtil.TAG_COMMON, TAG, "freeFilePages fileList=" + Arrays.toString(strArr) + ", startList=" + Arrays.toString(iArr) + ", result=" + FilePageManager.freeFilePages(strArr, iArr, -1, false) + ", cost=" + (SystemClock.uptimeMillis() - jUptimeMillis), null, 8);
        } catch (Exception e) {
            LogUtil.e$default(8, LogUtil.TAG_COMMON, TAG, "freeFilePages failed: " + e);
        }
    }

    public final Handler getBackgroundHandler() {
        return (Handler) this.backgroundHandler$delegate.getValue();
    }

    public final void releaseSysResource(UafSceneEvent uafSceneEvent) {
        try {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "OplusSysUiSchedMgr-->".concat("releaseUafSysResource for event: " + uafSceneEvent));
            }
            this.osenseResClient.releaseSysResource(uafSceneEvent.getId());
            Trace.asyncTraceForTrackEnd(8L, TRACK_NAME, uafSceneEvent.getId());
        } catch (Throwable th) {
            LogUtil.e$default(8, LogUtil.TAG_COMMON, TAG, "releaseSysResource error for event: " + uafSceneEvent.getId() + ", msg: " + th.getMessage());
        }
    }

    public final void reportKeyThread(String str, int i, UafSceneEvent uafSceneEvent, boolean z) {
        try {
            this.osenseResClient.reportKeyThread(str, i, uafSceneEvent.getId(), (Bundle) null);
            if (z) {
                requestSysResource(uafSceneEvent);
            }
        } catch (Throwable th) {
            StringBuilder sbM = ActivityResultRegistry$.ExternalSyntheticOutline0.m("reportKeyThread error for event: ", str, ", msg: ");
            sbM.append(th.getMessage());
            LogUtil.e$default(8, LogUtil.TAG_COMMON, TAG, sbM.toString());
        }
    }

    public final void requestSysResource(UafSceneEvent uafSceneEvent, String str) {
        try {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "OplusSysUiSchedMgr-->".concat("requestUafSysResource for event: " + uafSceneEvent + ", extraMsg: " + str));
            }
            Trace.asyncTraceForTrackEnd(8L, TRACK_NAME, uafSceneEvent.getId());
            String str2 = "eventId=" + uafSceneEvent.getId();
            if (!StringsKt__StringsKt.isBlank(str)) {
                str2 = str2 + ", " + str;
            }
            Trace.asyncTraceForTrackBegin(8L, TRACK_NAME, str2, uafSceneEvent.getId());
            this.osenseResClient.requestSysResource(uafSceneEvent.getId(), (Bundle) null);
        } catch (Throwable th) {
            LogUtil.e$default(8, LogUtil.TAG_COMMON, TAG, "requestUafSysResource error for event: " + uafSceneEvent.getId() + ", msg: " + th.getMessage());
        }
    }

    public final void schedForNotificationShadeExpand(boolean z) {
        boolean z2 = this.isNotificationShadeExpanded;
        if (z) {
            if (!z2) {
                requestSysResource(UafSceneEvent.SHADE_EXPAND_PENDING);
                this.isNotificationShadeExpanded = true;
                return;
            } else {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                    Log.d("SystemUi--Common", "OplusSysUiSchedMgr-->NotificationShade is already expanded");
                    return;
                }
                return;
            }
        }
        if (z2) {
            releaseSysResource(UafSceneEvent.SHADE_EXPAND_PENDING);
            this.isNotificationShadeExpanded = false;
        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "OplusSysUiSchedMgr-->NotificationShade is not expanded");
        }
    }

    public final void schedForSeedlingAnimation(boolean z, String str, int i) {
        if (!z) {
            releaseSysResource(UafSceneEvent.SEEDLING_ANIMATION);
            return;
        }
        PerformanceUtils.notifySFAnimating(i);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "PerformanceUtils-->notifyAnimationSeedling notifySFAnimating done");
        }
        requestSysResource(UafSceneEvent.SEEDLING_ANIMATION, "animationType=" + str);
    }

    public final void schedForSeedlingEventTriggered(boolean z, String str) {
        if (!z) {
            releaseSysResource(UafSceneEvent.SEEDLING_EVENT_TRIGGERED);
            return;
        }
        requestSysResource(UafSceneEvent.SEEDLING_EVENT_TRIGGERED, "eventType=" + str);
    }

    public final void schedForVolumeDialog(boolean z) {
        if (z) {
            requestSysResource(UafSceneEvent.VOLUME_DIALOG_SHOWING);
        } else {
            releaseSysResource(UafSceneEvent.VOLUME_DIALOG_SHOWING);
        }
    }

    public final void setScreenOffThresholdReached(boolean z) {
        this.screenOffThresholdReached = z;
    }

    public final void trimMemory(String str) {
        Trace.traceBegin(8L, "trimMemory for " + str);
        try {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "OplusSysUiSchedMgr-->".concat("trimMemory for " + str + ", trimMemoryValue: " + getTrimMemoryValue() + ", trimCacheValue: " + getTrimCacheValue()));
            }
            if (getTrimMemoryValue() >= 0) {
                WindowManagerGlobal.getInstance().trimMemory(getTrimMemoryValue());
            }
            if (getTrimCacheValue() >= 0) {
                WindowManagerGlobal.getInstance().trimCaches(getTrimCacheValue());
            }
            if (this.screenOffThresholdReached && Intrinsics.areEqual(str, SCENE_KEYGUARD_GONE) && this.unlockCleanCount.get() < getUnlockCleanMaxCount()) {
                this.screenOffThresholdReached = false;
                OCDHandler subHandler = OplusUiOffloadThread.Companion.getInstance().getSubHandler();
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(0);
                anonymousClass2.this$0 = this;
                VarHandle.storeStoreFence();
                subHandler.postDelayed(anonymousClass2, getDelayMs());
            }
        } catch (Exception e) {
            LogUtil.e(LogUtil.TAG_COMMON, TAG, "trim error", e);
        } finally {
            Trace.traceEnd(8L);
        }
    }

    public final void requestSysResource(UafSceneEvent uafSceneEvent) {
        requestSysResource(uafSceneEvent, "");
    }
}
