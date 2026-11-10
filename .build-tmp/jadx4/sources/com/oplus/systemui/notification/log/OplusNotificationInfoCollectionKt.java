package com.oplus.systemui.notification.log;

import androidx.car.app.hardware.common.CarZone$;
import androidx.compose.runtime.collection.MutableVectorKt$;
import com.android.systemui.statusbar.notification.collection.ListAttachState;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifPromoter;
import com.oplus.systemui.preventmode.OplusPreventModeData;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusNotificationInfoCollectionKt {
    public static final String getDiffAttacthInfoOrNull(ListEntry listEntry) {
        StringBuilder sb = new StringBuilder();
        ListAttachState previousAttachState = listEntry.getPreviousAttachState();
        ListAttachState attachState = listEntry.getAttachState();
        if (previousAttachState.getSectionIndex() != attachState.getSectionIndex()) {
            sb.append(MutableVectorKt$.ExternalSyntheticOutline0.m("section:", previousAttachState.getSectionIndex(), attachState.getSectionIndex(), "->", OplusPreventModeData.SEPARATOR));
        }
        if (!Intrinsics.areEqual(previousAttachState.getExcludingFilter(), attachState.getExcludingFilter())) {
            NotifFilter excludingFilter = previousAttachState.getExcludingFilter();
            String name = excludingFilter != null ? excludingFilter.getName() : null;
            NotifFilter excludingFilter2 = attachState.getExcludingFilter();
            sb.append(CarZone$.ExternalSyntheticOutline0.m("filter:", name, "->", excludingFilter2 != null ? excludingFilter2.getName() : null, OplusPreventModeData.SEPARATOR));
        }
        if (!Intrinsics.areEqual(previousAttachState.getPromoter(), attachState.getPromoter())) {
            NotifPromoter promoter = previousAttachState.getPromoter();
            String name2 = promoter != null ? promoter.getName() : null;
            NotifPromoter promoter2 = attachState.getPromoter();
            sb.append(CarZone$.ExternalSyntheticOutline0.m("topLevel(", name2, "->", promoter2 != null ? promoter2.getName() : null, OplusPreventModeData.SEPARATOR));
        }
        if (!Intrinsics.areEqual(previousAttachState.getGroupPruneReason(), attachState.getGroupPruneReason())) {
            sb.append(CarZone$.ExternalSyntheticOutline0.m("gpr:", previousAttachState.getGroupPruneReason(), "->", attachState.getGroupPruneReason(), OplusPreventModeData.SEPARATOR));
        }
        if (!Intrinsics.areEqual(previousAttachState.getSuppressedChanges(), attachState.getSuppressedChanges())) {
            sb.append("suppressed:" + previousAttachState.getSuppressedChanges() + "->" + attachState.getSuppressedChanges());
        }
        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }
}
