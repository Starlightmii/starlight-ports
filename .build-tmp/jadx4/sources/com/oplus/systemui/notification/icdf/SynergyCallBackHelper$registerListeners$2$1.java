package com.oplus.systemui.notification.icdf;

import android.os.Handler;
import android.util.Log;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.inflation.BindEventManager;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import kotlin.Function;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class SynergyCallBackHelper$registerListeners$2$1 implements BindEventManager.Listener, FunctionAdapter {
    public /* synthetic */ SynergyCallBackHelper $tmp0;

    public final boolean equals(Object obj) {
        if ((obj instanceof BindEventManager.Listener) && (obj instanceof FunctionAdapter)) {
            return Intrinsics.areEqual(getFunctionDelegate(), ((FunctionAdapter) obj).getFunctionDelegate());
        }
        return false;
    }

    public final Function getFunctionDelegate() {
        return new FunctionReferenceImpl(1, this.$tmp0, SynergyCallBackHelper.class, "onViewBound", "onViewBound(Lcom/android/systemui/statusbar/notification/collection/NotificationEntry;)V", 0);
    }

    public final int hashCode() {
        return getFunctionDelegate().hashCode();
    }

    public final void onViewBound(NotificationEntry notificationEntry) {
        int i;
        SynergyCallBackHelper synergyCallBackHelper = this.$tmp0;
        Integer entryInflationState = synergyCallBackHelper.getMInflationStatesHelper().getEntryInflationState(notificationEntry);
        if (entryInflationState != null && entryInflationState.intValue() == 0) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "PC_Synergy-->onAddNotification");
            }
            i = 1;
        } else {
            if (entryInflationState == null || entryInflationState.intValue() != 2) {
                synergyCallBackHelper.getMInflationStatesHelper().removeEntryInflationState(notificationEntry);
                return;
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "PC_Synergy-->onUpdateNotification");
            }
            i = 2;
        }
        synergyCallBackHelper.getMInflationStatesHelper().removeEntryInflationState(notificationEntry);
        if (synergyCallBackHelper.getIcdfNotificationHelper().canSyncNotification()) {
            boolean zResolvePinnedForIcdf = synergyCallBackHelper.getIcdfBuilder().resolvePinnedForIcdf(notificationEntry, i);
            boolean zResolveOnGoingForIcdf = synergyCallBackHelper.getIcdfBuilder().resolveOnGoingForIcdf(notificationEntry);
            Handler workHandler = synergyCallBackHelper.getWorkHandler();
            SynergyCallBackHelper$onViewBound$1 synergyCallBackHelper$onViewBound$1 = new SynergyCallBackHelper$onViewBound$1();
            synergyCallBackHelper$onViewBound$1.this$0 = synergyCallBackHelper;
            synergyCallBackHelper$onViewBound$1.$entry = notificationEntry;
            synergyCallBackHelper$onViewBound$1.$state = i;
            synergyCallBackHelper$onViewBound$1.$pinned = zResolvePinnedForIcdf;
            synergyCallBackHelper$onViewBound$1.$onGoing = zResolveOnGoingForIcdf;
            VarHandle.storeStoreFence();
            workHandler.post(synergyCallBackHelper$onViewBound$1);
        }
    }
}
