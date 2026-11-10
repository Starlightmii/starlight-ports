package com.oplus.systemui.notification.interruption.wakeup;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Pair;
import com.android.systemui.CoreStartable;
import com.android.systemui.DependencyEx;
import com.android.systemui.InitController;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.media.NotificationMediaManager;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeRenderListListener;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;
import com.android.systemui.statusbar.notification.interruption.KeyguardNotificationVisibilityProvider;
import com.oplus.systemui.notification.log.OplusNotificationInfoCollection;
import com.oplus.systemui.statusbar.notification.dependence.NotificationDependOnKeyguard;
import com.oplus.systemui.statusbar.notification.helper.NotificationKeyguardHelper;
import com.oplus.systemui.statusbar.observer.ZenModeKeyguardSettingsObserver;
import com.oplus.systemui.statusbar.observer.ZenModeSettingsObserver;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ThreadManager;
import dagger.Lazy;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class WakeupScreenHelper implements NotifCollectionListener, OnBeforeRenderListListener, CoreStartable {
    public static final List ONGOING_WAKEUP_PACKAGE_WHITE_LIST = List.of("com.tencent.mm");
    public final Context mContext;
    NotificationDependOnKeyguard mDependOnKeyguard;
    InitController mInitController;
    public NotificationKeyguardHelper mKeyguardHelper;
    KeyguardNotificationVisibilityProvider mKeyguardNotificationVisibilityProvider;
    public List mListEntries;
    Lazy mNotifPipeline;
    public NotificationMediaManager mNotificationMediaManager;
    OplusNotificationInfoCollection mOplusInfoCollection;
    public final AnonymousClass1 mSensorListener;
    public SensorManager mSensorManager;
    public final ZenModeSettingsObserver mZenModeSettingsObserver = new ZenModeSettingsObserver();
    public final ZenModeKeyguardSettingsObserver mZenModeKeyguardSettingsObserver = new ZenModeKeyguardSettingsObserver();
    public boolean mHasRegisteredSensor = false;
    public final CopyOnWriteArrayList mNeedNotifyEntries = new CopyOnWriteArrayList();
    public final List mFilters = new ArrayList();

    /* JADX INFO: renamed from: com.oplus.systemui.notification.interruption.wakeup.WakeupScreenHelper$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements SensorEventListener {
        public /* synthetic */ WakeupScreenHelper this$0;

        @Override // android.hardware.SensorEventListener
        public final void onAccuracyChanged(Sensor sensor, int i) {
        }

        @Override // android.hardware.SensorEventListener
        public final void onSensorChanged(SensorEvent sensorEvent) {
            float[] fArr = sensorEvent.values;
            if (fArr != null && (8 == sensorEvent.sensor.getType() || 33171075 == sensorEvent.sensor.getType())) {
                LogUtil.d(LogUtil.TAG_NOTIFICATION, "WakeupScreenHelper", "Notification--onSensorChanged sensorValues[0] = " + fArr[0]);
                if (fArr[0] > 0.0d) {
                    WakeupScreenHelper wakeupScreenHelper = this.this$0;
                    List list = WakeupScreenHelper.ONGOING_WAKEUP_PACKAGE_WHITE_LIST;
                    wakeupScreenHelper.powerOnScreen();
                }
            }
            WakeupScreenHelper wakeupScreenHelper2 = this.this$0;
            List list2 = WakeupScreenHelper.ONGOING_WAKEUP_PACKAGE_WHITE_LIST;
            synchronized (wakeupScreenHelper2) {
                try {
                    if (wakeupScreenHelper2.mHasRegisteredSensor) {
                        wakeupScreenHelper2.mSensorManager.unregisterListener(wakeupScreenHelper2.mSensorListener);
                        wakeupScreenHelper2.mHasRegisteredSensor = false;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Filter {
        Pair shouldWakeup(NotificationEntry notificationEntry);
    }

    public WakeupScreenHelper(Context context) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mSensorListener = anonymousClass1;
        this.mContext = context;
    }

    public final void onBeforeRenderList(List list) {
        this.mListEntries = new ArrayList(list);
        if (this.mNeedNotifyEntries.size() > 0) {
            int size = ((ArrayList) this.mListEntries).size();
            CopyOnWriteArrayList<NotificationEntry> copyOnWriteArrayList = this.mNeedNotifyEntries;
            if (size <= 0) {
                copyOnWriteArrayList.removeIf(new WakeupScreenHelper$$ExternalSyntheticLambda4());
                return;
            }
            for (NotificationEntry notificationEntry : copyOnWriteArrayList) {
                boolean zBooleanValue = false;
                if (notificationEntry == null) {
                    LogUtil.d(LogUtil.TAG_NOTIFICATION, "WakeupScreenHelper", "Notification--pokeWakeLock sbn null");
                } else {
                    ScreenLifecycle screenLifecycle = (ScreenLifecycle) DependencyEx.sDependency.getDependency(ScreenLifecycle.class);
                    if (screenLifecycle.getScreenState() == 2 || screenLifecycle.getScreenState() == 1) {
                        LogUtil.d(LogUtil.TAG_NOTIFICATION, "WakeupScreenHelper", "Notification--screenLifecycle not pass");
                    } else {
                        Pair pairCreate = Pair.create(Boolean.FALSE, "no filter");
                        Iterator it = this.mFilters.iterator();
                        while (it.hasNext()) {
                            pairCreate = ((Filter) it.next()).shouldWakeup(notificationEntry);
                            if (!((Boolean) pairCreate.first).booleanValue()) {
                                break;
                            }
                        }
                        LogUtil.d(LogUtil.TAG_NOTIFICATION, "WakeupScreenHelper", "shouldWakeupForEntry:" + ((String) pairCreate.second));
                        this.mOplusInfoCollection.addWakeupInfo(notificationEntry, ((Boolean) pairCreate.first).booleanValue(), (String) pairCreate.second);
                        zBooleanValue = ((Boolean) pairCreate.first).booleanValue();
                    }
                }
                if (zBooleanValue) {
                    ThreadManager threadManager = ThreadManager.getInstance();
                    WakeupScreenHelper$$ExternalSyntheticLambda5 wakeupScreenHelper$$ExternalSyntheticLambda5 = new WakeupScreenHelper$$ExternalSyntheticLambda5();
                    wakeupScreenHelper$$ExternalSyntheticLambda5.f$0 = this;
                    VarHandle.storeStoreFence();
                    threadManager.postInThread(wakeupScreenHelper$$ExternalSyntheticLambda5);
                }
            }
            this.mNeedNotifyEntries.clear();
        }
    }

    public final void onEntryAdded(NotificationEntry notificationEntry) {
        this.mNeedNotifyEntries.add(notificationEntry);
    }

    public final void onEntryRemoved(NotificationEntry notificationEntry, int i) {
        if (this.mNeedNotifyEntries.size() > 0) {
            this.mNeedNotifyEntries.remove(notificationEntry);
        }
    }

    public final void onEntryUpdated(NotificationEntry notificationEntry) {
        if (ONGOING_WAKEUP_PACKAGE_WHITE_LIST.contains(notificationEntry.getSbn().getPackageName()) && notificationEntry.getSbn().isOngoing()) {
            this.mOplusInfoCollection.addWakeupInfo(notificationEntry, false, "wechat onGoing notification updated");
        } else {
            this.mNeedNotifyEntries.add(notificationEntry);
        }
    }

    public final void powerOnScreen() {
        PowerManager powerManager = (PowerManager) this.mContext.getSystemService("power");
        if (powerManager.isScreenOn()) {
            powerManager.userActivity(SystemClock.uptimeMillis(), false);
        } else {
            LogUtil.d(LogUtil.TAG_NOTIFICATION, "WakeupScreenHelper", "Notification--powerOnScreen, powerManager.wakeUp");
            powerManager.wakeUp(SystemClock.uptimeMillis(), 999, "Notification--WakeUp");
        }
    }

    public final void start() {
        InitController initController = this.mInitController;
        WakeupScreenHelper$$ExternalSyntheticLambda0 wakeupScreenHelper$$ExternalSyntheticLambda0 = new WakeupScreenHelper$$ExternalSyntheticLambda0();
        wakeupScreenHelper$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        initController.addPostInitTask(wakeupScreenHelper$$ExternalSyntheticLambda0);
    }
}
