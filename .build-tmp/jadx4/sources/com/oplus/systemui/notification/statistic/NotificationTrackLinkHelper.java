package com.oplus.systemui.notification.statistic;

import android.app.AlarmManager;
import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import com.android.internal.statusbar.IStatusBarService;
import com.android.systemui.InitController;
import com.android.systemui.application.impl.SystemUIApplicationImpl$;
import com.android.systemui.statusbar.notification.collection.GroupEntry;
import com.android.systemui.statusbar.notification.collection.ListAttachState;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.OplusListEntryEx;
import com.android.systemui.statusbar.notification.collection.OplusNotificationEntryEx;
import com.android.systemui.statusbar.notification.collection.PipelineEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter;
import com.android.systemui.util.AlarmTimeout;
import com.oplus.systemui.aod.anim.OplusAODMaskAnimController$;
import com.oplus.systemui.notification.lockscreen.statistic.LockScreenReportHelper;
import com.oplus.systemui.statusbar.notification.collection.OplusNotificationEntryExImpl;
import com.oplus.systemui.statusbar.util.NotificationStatisticUtil;
import com.oplus.systemui.statusbar.util.NotificationStatisticUtil$$ExternalSyntheticLambda2;
import com.oplus.transition.OplusAnimationController;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ThreadManager;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationTrackLinkHelper {
    private static final String FEEDBACK_FILTER_REASON = "filter.reason";
    private static final String FEEDBACK_SHOW = "notification.show";
    private static final String FEEDBACK_SHOWN = "notification.shown";
    private static final String TAG = "NotificationTrackLinkHelper";
    private AlarmManager alarmManager;
    private final NotificationTrackLinkHelper$beforeRenderListListener$1 beforeRenderListListener;
    private Context context;
    private AlarmTimeout dailyAlarmTimeout;
    private Handler handler;
    private final List<NotificationEntry> newNotificationData = new ArrayList();
    private final NotificationTrackLinkHelper$notifCollectionListener$1 notifCollectionListener;
    private IStatusBarService statusBarService;
    public static final Companion Companion = new Companion();
    private static final Map<String, Integer> WHITELIST_FILTER_LIST = MapsKt__MapsKt.mutableMapOf(new Pair[]{TuplesKt.to("IsSuspendedFilter", 40), TuplesKt.to("DndSuppressingVisualEffects", 41), TuplesKt.to("PreparationCoordinatorInflationError", 42), TuplesKt.to("PrivacyCoordinatorAppHide", 43), TuplesKt.to("GroupSummaryCoordinator", 44), TuplesKt.to("SuperPowerSaveCoordinatorSuperPowerSave", 45), TuplesKt.to("NotCurrentUserFilter", 46)});
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new NotificationTrackLinkHelper$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public static /* synthetic */ void getInstance$annotations() {
        }

        public final NotificationTrackLinkHelper getInstance() {
            return (NotificationTrackLinkHelper) NotificationTrackLinkHelper.instance$delegate.getValue();
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.statistic.NotificationTrackLinkHelper$init$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ NotifPipeline $pipe;
        public /* synthetic */ NotificationTrackLinkHelper this$0;

        @Override // java.lang.Runnable
        public final void run() {
            this.$pipe.addCollectionListener(this.this$0.notifCollectionListener);
            this.$pipe.addOnBeforeRenderListListener(this.this$0.beforeRenderListListener);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.notification.statistic.NotificationTrackLinkHelper$init$2, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass2 implements Runnable {
        public /* synthetic */ NotificationTrackLinkHelper this$0;

        @Override // java.lang.Runnable
        public final void run() {
            this.this$0.scheduleDailyExposeNotifications();
        }
    }

    public static Unit $r8$lambda$vXfHCNFJekkSAwi9S0jsqFpNclk(NotificationTrackLinkHelper notificationTrackLinkHelper, NotificationEntry notificationEntry, long j) {
        NotifFilter excludingFilter;
        Map<String, Integer> map = WHITELIST_FILTER_LIST;
        ListAttachState attachState = notificationEntry.getAttachState();
        int iIntValue = map.getOrDefault((attachState == null || (excludingFilter = attachState.getExcludingFilter()) == null) ? null : excludingFilter.getName(), -1).intValue();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            StringBuilder sbM = SystemUIApplicationImpl$.ExternalSyntheticOutline0.m(j - notificationEntry.getSbn().getPostTime(), "handleNotification key=", notificationEntry.getKey(), ", exposeTime=");
            sbM.append(", reason=");
            sbM.append(iIntValue);
            Log.d("SystemUi--Notification", "NotificationTrackLinkHelper-->".concat(sbM.toString()));
        }
        if (iIntValue != -1) {
            notificationTrackLinkHelper.onNotificationFeedbackReceived(iIntValue, notificationEntry.getKey(), false);
        } else {
            boolean zIsInDailyUploadWhiteList = NotificationTrackerLinkConfig.isInDailyUploadWhiteList(notificationEntry.getSbn().getPackageName());
            if (NotificationTrackerLinkConfig.isInPolicyWhiteList(notificationEntry.getSbn().getPackageName()) || zIsInDailyUploadWhiteList) {
                notificationTrackLinkHelper.onNotificationFeedbackReceived(-1, notificationEntry.getKey(), true);
            }
            if (zIsInDailyUploadWhiteList) {
                NotificationStatisticUtil.putAddNotificationData(notificationTrackLinkHelper.context, notificationEntry.getSbn(), j);
            }
        }
        return Unit.INSTANCE;
    }

    public NotificationTrackLinkHelper() {
        NotificationTrackLinkHelper$notifCollectionListener$1 notificationTrackLinkHelper$notifCollectionListener$1 = new NotificationTrackLinkHelper$notifCollectionListener$1();
        notificationTrackLinkHelper$notifCollectionListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.notifCollectionListener = notificationTrackLinkHelper$notifCollectionListener$1;
        NotificationTrackLinkHelper$beforeRenderListListener$1 notificationTrackLinkHelper$beforeRenderListListener$1 = new NotificationTrackLinkHelper$beforeRenderListListener$1();
        notificationTrackLinkHelper$beforeRenderListListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.beforeRenderListListener = notificationTrackLinkHelper$beforeRenderListListener$1;
    }

    public static final void access$handleOnBeforeRenderList(NotificationTrackLinkHelper notificationTrackLinkHelper, List list) {
        if (!notificationTrackLinkHelper.newNotificationData.isEmpty()) {
            Iterator<NotificationEntry> it = notificationTrackLinkHelper.newNotificationData.iterator();
            long jCurrentTimeMillis = System.currentTimeMillis();
            while (it.hasNext()) {
                NotificationEntry next = it.next();
                OplusNotificationEntryEx ex = next.getEx();
                if (ex != null) {
                    ex.setNotificationShowTime(jCurrentTimeMillis);
                }
                ThreadManager companion = ThreadManager.Companion.getInstance();
                NotificationTrackLinkHelper$$ExternalSyntheticLambda1 notificationTrackLinkHelper$$ExternalSyntheticLambda1 = new NotificationTrackLinkHelper$$ExternalSyntheticLambda1();
                notificationTrackLinkHelper$$ExternalSyntheticLambda1.f$0 = notificationTrackLinkHelper;
                notificationTrackLinkHelper$$ExternalSyntheticLambda1.f$1 = next;
                notificationTrackLinkHelper$$ExternalSyntheticLambda1.f$2 = jCurrentTimeMillis;
                VarHandle.storeStoreFence();
                companion.postInThread(notificationTrackLinkHelper$$ExternalSyntheticLambda1);
                it.remove();
            }
        }
        if (list != null) {
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                GroupEntry groupEntry = (PipelineEntry) it2.next();
                OplusListEntryEx mListEntryEx = groupEntry.getMListEntryEx();
                if (mListEntryEx != null) {
                    mListEntryEx.setHasBeShowed(true);
                }
                if (groupEntry instanceof GroupEntry) {
                    Iterator it3 = groupEntry.getChildren().iterator();
                    while (it3.hasNext()) {
                        OplusListEntryEx mListEntryEx2 = ((NotificationEntry) it3.next()).getMListEntryEx();
                        if (mListEntryEx2 != null) {
                            mListEntryEx2.setHasBeShowed(true);
                        }
                    }
                }
            }
        }
    }

    public static final void access$onNotificationPost(NotificationTrackLinkHelper notificationTrackLinkHelper, NotificationEntry notificationEntry) {
        Notification notification;
        Notification notification2 = notificationEntry.getSbn().getNotification();
        if (notification2 == null || (notification2.flags & OplusAnimationController.FLAG_SUPPORT_FLEXIBLE_SEAMLESS_V1_FEATURE) <= 0 || (notification = notificationEntry.getSbn().getNotification()) == null || !notification.isGroupSummary()) {
            OplusNotificationEntryEx ex = notificationEntry.getEx();
            if (ex != null && ((OplusNotificationEntryExImpl) ex).isShown) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                    OplusAODMaskAnimController$.ExternalSyntheticOutline0.m("onNotificationPost key=", notificationEntry.getKey(), ", isShown=true, skip", "NotificationTrackLinkHelper-->", "SystemUi--Notification");
                    return;
                }
                return;
            }
            boolean zIsInDailyUploadWhiteList = NotificationTrackerLinkConfig.isInDailyUploadWhiteList(notificationEntry.getSbn().getPackageName());
            boolean zIsInPolicyWhiteList = NotificationTrackerLinkConfig.isInPolicyWhiteList(notificationEntry.getSbn().getPackageName());
            if (zIsInDailyUploadWhiteList || zIsInPolicyWhiteList) {
                notificationTrackLinkHelper.newNotificationData.add(notificationEntry);
            }
        }
    }

    public static final void access$sendDailyExposeNotificationsData(NotificationTrackLinkHelper notificationTrackLinkHelper) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "NotificationTrackLinkHelper-->sendDailyExposeNotificationsData");
        }
        notificationTrackLinkHelper.scheduleDailyExposeNotifications();
        Context context = notificationTrackLinkHelper.context;
        Lock lock = NotificationStatisticUtil.NOTIFICATION_LOCK;
        ThreadManager threadManager = ThreadManager.getInstance();
        NotificationStatisticUtil$$ExternalSyntheticLambda2 notificationStatisticUtil$$ExternalSyntheticLambda2 = new NotificationStatisticUtil$$ExternalSyntheticLambda2(1);
        notificationStatisticUtil$$ExternalSyntheticLambda2.f$0 = context;
        VarHandle.storeStoreFence();
        threadManager.postInThread(notificationStatisticUtil$$ExternalSyntheticLambda2);
    }

    public static final NotificationTrackLinkHelper getInstance() {
        return Companion.getInstance();
    }

    public final void init(Context context, Handler handler, NotifPipeline notifPipeline, InitController initController, IStatusBarService iStatusBarService) {
        this.context = context;
        this.handler = handler;
        this.statusBarService = iStatusBarService;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$pipe = notifPipeline;
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        initController.addPostInitTask(anonymousClass1);
        NotificationTrackerLinkConfig notificationTrackerLinkConfig = NotificationTrackerLinkConfig.INSTANCE;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2();
        anonymousClass2.this$0 = this;
        VarHandle.storeStoreFence();
        notificationTrackerLinkConfig.init(context, handler, anonymousClass2);
        TcpFileReportHelper.INSTANCE.init(context, handler);
        LockScreenReportHelper.INSTANCE.init(context, handler);
        Context context2 = this.context;
        Object systemService = context2 != null ? context2.getSystemService("alarm") : null;
        AlarmManager alarmManager = systemService instanceof AlarmManager ? (AlarmManager) systemService : null;
        if (alarmManager == null) {
            return;
        }
        NotificationTrackLinkHelper$initDailyExposeNotificationsAlarm$1 notificationTrackLinkHelper$initDailyExposeNotificationsAlarm$1 = new NotificationTrackLinkHelper$initDailyExposeNotificationsAlarm$1();
        notificationTrackLinkHelper$initDailyExposeNotificationsAlarm$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.dailyAlarmTimeout = new AlarmTimeout(alarmManager, notificationTrackLinkHelper$initDailyExposeNotificationsAlarm$1, TAG, this.handler);
        scheduleDailyExposeNotifications();
    }

    public final void onNotificationFeedbackReceived(int i, String str, boolean z) {
        if (str == null || str.length() == 0) {
            return;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putInt(FEEDBACK_SHOW, z ? 1 : 2);
            bundle.putInt(FEEDBACK_FILTER_REASON, i);
            bundle.putBoolean(FEEDBACK_SHOWN, true);
            IStatusBarService iStatusBarService = this.statusBarService;
            if (iStatusBarService != null) {
                iStatusBarService.onNotificationFeedbackReceived(str, bundle);
            }
        } catch (RemoteException unused) {
            Log.w(TAG, "Failed to send feedback key=".concat(str));
        }
    }

    public final void scheduleDailyExposeNotifications() {
        AlarmTimeout alarmTimeout = this.dailyAlarmTimeout;
        if (alarmTimeout != null) {
            alarmTimeout.schedule(2, 3, NotificationTrackerLinkConfig.INSTANCE.getExposeDailyReportCount());
        }
    }
}
