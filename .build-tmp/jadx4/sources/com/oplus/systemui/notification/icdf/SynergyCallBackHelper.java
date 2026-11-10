package com.oplus.systemui.notification.icdf;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.android.systemui.statusbar.notification.collection.NotifCollection;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.inflation.BindEventManager;
import com.android.systemui.statusbar.notification.helper.InflationStatesHelper;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.oplus.synergynotificationsdk.SynergyNotificationManager;
import com.oplus.synergynotificationsdk.callback.INotificationIntentCallback;
import com.oplus.synergynotificationsdk.icdf.IcdfNotificationManagerImpl;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SynergyCallBackHelper {
    public static final Companion Companion = new Companion();
    public static final String TAG = "PC_Synergy";
    public BindEventManager bindEventManager;
    private final SynergyCallBackHelper$entryListener$1 entryListener;
    public IcdfBuilder icdfBuilder;
    public IcdfHeadsUpDecisionRecorder icdfHeadsUpDecisionRecorder;
    public IcdfNotificationHelper icdfNotificationHelper;
    private final IcdfNotificationManagerImpl.CallBack implCallBack;
    private final INotificationIntentCallback mINotificationIntentCallback;
    public InflationStatesHelper mInflationStatesHelper;
    public NotifPipeline mNotifPipeline;
    public Handler mainHandler;
    public NotifCollection notifCollection;
    public Handler workHandler;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public SynergyCallBackHelper() {
        SynergyCallBackHelper$implCallBack$1 synergyCallBackHelper$implCallBack$1 = new SynergyCallBackHelper$implCallBack$1();
        synergyCallBackHelper$implCallBack$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mINotificationIntentCallback = synergyCallBackHelper$implCallBack$1;
        SynergyCallBackHelper$implCallBack$1 synergyCallBackHelper$implCallBack$2 = new SynergyCallBackHelper$implCallBack$1();
        synergyCallBackHelper$implCallBack$2.this$0 = this;
        VarHandle.storeStoreFence();
        this.implCallBack = synergyCallBackHelper$implCallBack$2;
        SynergyCallBackHelper$entryListener$1 synergyCallBackHelper$entryListener$1 = new SynergyCallBackHelper$entryListener$1();
        synergyCallBackHelper$entryListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.entryListener = synergyCallBackHelper$entryListener$1;
    }

    public static final void access$alert(SynergyCallBackHelper synergyCallBackHelper, int i) {
        Handler workHandler = synergyCallBackHelper.getWorkHandler();
        alert.1 r1 = new alert.1();
        r1.this$0 = synergyCallBackHelper;
        r1.$errorId = i;
        VarHandle.storeStoreFence();
        workHandler.post(r1);
    }

    public static final NotificationEntry access$findNotificationEntryWithKey(SynergyCallBackHelper synergyCallBackHelper, String str) {
        for (NotificationEntry notificationEntry : synergyCallBackHelper.getNotifCollection().getAllNotifs()) {
            if (Intrinsics.areEqual(str, notificationEntry.getKey())) {
                return notificationEntry;
            }
        }
        return null;
    }

    public static final void access$updateRanking(SynergyCallBackHelper synergyCallBackHelper) {
        Handler mainHandler = synergyCallBackHelper.getMainHandler();
        SynergyCallBackHelper$updateRanking$1 synergyCallBackHelper$updateRanking$1 = new SynergyCallBackHelper$updateRanking$1(0);
        synergyCallBackHelper$updateRanking$1.this$0 = synergyCallBackHelper;
        VarHandle.storeStoreFence();
        mainHandler.post(synergyCallBackHelper$updateRanking$1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Map<String, Integer> getIcdfRankingMap() {
        HashMap map = new HashMap();
        List list = CollectionsKt___CollectionsKt.toList(getNotifCollection().getAllNotifs());
        int size = 0;
        for (int i = 0; i < list.size(); i++) {
            NotificationEntry notificationEntry = (NotificationEntry) list.get(i);
            if (notificationEntry.rowExists() && notificationEntry.getRow().isChildInGroup()) {
                size--;
            } else if (notificationEntry.rowExists() && notificationEntry.getRow().mIsSummaryWithChildren) {
                List attachedChildren = notificationEntry.getRow().getAttachedChildren();
                int i2 = i + size;
                map.put(notificationEntry.getKey(), Integer.valueOf(i2));
                int size2 = attachedChildren.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    map.put(((ExpandableNotificationRow) attachedChildren.get(i3)).getEntry().getKey(), Integer.valueOf(i2 + i3 + 1));
                }
                size += attachedChildren.size();
            } else {
                map.put(notificationEntry.getKey(), Integer.valueOf(i + size));
            }
        }
        return map;
    }

    public static /* synthetic */ void getMainHandler$annotations() {
    }

    public static /* synthetic */ void getWorkHandler$annotations() {
    }

    public final Object fetchData(String str, Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getMain(), new fetchData.2(this, str, (Continuation) null), continuation);
    }

    public final BindEventManager getBindEventManager() {
        BindEventManager bindEventManager = this.bindEventManager;
        if (bindEventManager != null) {
            return bindEventManager;
        }
        return null;
    }

    public final IcdfBuilder getIcdfBuilder() {
        IcdfBuilder icdfBuilder = this.icdfBuilder;
        if (icdfBuilder != null) {
            return icdfBuilder;
        }
        return null;
    }

    public final IcdfHeadsUpDecisionRecorder getIcdfHeadsUpDecisionRecorder() {
        IcdfHeadsUpDecisionRecorder icdfHeadsUpDecisionRecorder = this.icdfHeadsUpDecisionRecorder;
        if (icdfHeadsUpDecisionRecorder != null) {
            return icdfHeadsUpDecisionRecorder;
        }
        return null;
    }

    public final IcdfNotificationHelper getIcdfNotificationHelper() {
        IcdfNotificationHelper icdfNotificationHelper = this.icdfNotificationHelper;
        if (icdfNotificationHelper != null) {
            return icdfNotificationHelper;
        }
        return null;
    }

    public final InflationStatesHelper getMInflationStatesHelper() {
        InflationStatesHelper inflationStatesHelper = this.mInflationStatesHelper;
        if (inflationStatesHelper != null) {
            return inflationStatesHelper;
        }
        return null;
    }

    public final NotifPipeline getMNotifPipeline() {
        NotifPipeline notifPipeline = this.mNotifPipeline;
        if (notifPipeline != null) {
            return notifPipeline;
        }
        return null;
    }

    public final Handler getMainHandler() {
        Handler handler = this.mainHandler;
        if (handler != null) {
            return handler;
        }
        return null;
    }

    public final NotifCollection getNotifCollection() {
        NotifCollection notifCollection = this.notifCollection;
        if (notifCollection != null) {
            return notifCollection;
        }
        return null;
    }

    public final Handler getWorkHandler() {
        Handler handler = this.workHandler;
        if (handler != null) {
            return handler;
        }
        return null;
    }

    public final void registerListeners() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "PC_Synergy-->registerListeners");
        }
        IcdfNotificationManagerImpl.Companion.setCallBack(this.implCallBack);
        SynergyNotificationManager.Companion companion = SynergyNotificationManager.Companion;
        companion.setCallBack(this.implCallBack);
        companion.setNotificationIntentCallback(this.mINotificationIntentCallback);
        Handler mainHandler = getMainHandler();
        SynergyCallBackHelper$updateRanking$1 synergyCallBackHelper$updateRanking$1 = new SynergyCallBackHelper$updateRanking$1(3);
        synergyCallBackHelper$updateRanking$1.this$0 = this;
        VarHandle.storeStoreFence();
        mainHandler.post(synergyCallBackHelper$updateRanking$1);
    }

    public final void setBindEventManager(BindEventManager bindEventManager) {
        this.bindEventManager = bindEventManager;
    }

    public final void setIcdfBuilder(IcdfBuilder icdfBuilder) {
        this.icdfBuilder = icdfBuilder;
    }

    public final void setIcdfHeadsUpDecisionRecorder(IcdfHeadsUpDecisionRecorder icdfHeadsUpDecisionRecorder) {
        this.icdfHeadsUpDecisionRecorder = icdfHeadsUpDecisionRecorder;
    }

    public final void setIcdfNotificationHelper(IcdfNotificationHelper icdfNotificationHelper) {
        this.icdfNotificationHelper = icdfNotificationHelper;
    }

    public final void setMInflationStatesHelper(InflationStatesHelper inflationStatesHelper) {
        this.mInflationStatesHelper = inflationStatesHelper;
    }

    public final void setMNotifPipeline(NotifPipeline notifPipeline) {
        this.mNotifPipeline = notifPipeline;
    }

    public final void setMainHandler(Handler handler) {
        this.mainHandler = handler;
    }

    public final void setNotifCollection(NotifCollection notifCollection) {
        this.notifCollection = notifCollection;
    }

    public final void setWorkHandler(Handler handler) {
        this.workHandler = handler;
    }

    public final void synergyNotificationSDKInit(Context context) {
        SynergyNotificationManager.Companion.getInstance().init(context);
    }
}
