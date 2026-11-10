package com.oplus.systemui.statusbar.notification.collection;

import android.content.Context;
import com.android.systemui.statusbar.notification.collection.GroupEntry;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.OplusGroupEntryEx;
import com.android.systemui.statusbar.notification.collection.OplusNotificationEntryEx;
import com.android.systemui.statusbar.notification.interruption.KeyguardNotificationVisibilityProvider;
import com.oplus.systemui.statusbar.notification.collection.coordinator.OplusMediaCoordinator;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusGroupEntryExImpl extends OplusListEntryExImpl implements OplusGroupEntryEx {
    private final Context context;
    private final KeyguardNotificationVisibilityProvider keyguardNotificationVisibilityProvider;
    private final OplusMediaCoordinator oplusMediaCoordinator;

    public OplusGroupEntryExImpl(Context context, KeyguardNotificationVisibilityProvider keyguardNotificationVisibilityProvider, OplusMediaCoordinator oplusMediaCoordinator) {
        super(context);
        this.context = context;
        this.keyguardNotificationVisibilityProvider = keyguardNotificationVisibilityProvider;
        this.oplusMediaCoordinator = oplusMediaCoordinator;
    }

    @Override // com.oplus.systemui.statusbar.notification.collection.OplusListEntryExImpl
    public String getEncryptionKey() {
        String key;
        GroupEntry groupEntryM384getBase = m384getBase();
        return (groupEntryM384getBase == null || (key = groupEntryM384getBase.getKey()) == null) ? "<root>" : key;
    }

    @Override // com.oplus.systemui.statusbar.notification.collection.OplusListEntryExImpl
    public long getUpdateTime() {
        OplusNotificationEntryEx ex;
        GroupEntry groupEntryM384getBase = m384getBase();
        if (groupEntryM384getBase == null) {
            return 0L;
        }
        Long lValueOf = null;
        if (groupEntryM384getBase.getChildren().isEmpty()) {
            NotificationEntry representativeEntry = groupEntryM384getBase.getRepresentativeEntry();
            if (representativeEntry != null) {
                lValueOf = Long.valueOf(representativeEntry.getCreationTime());
            }
        } else {
            NotificationEntry notificationEntry = (NotificationEntry) groupEntryM384getBase.getChildren().get(0);
            if (notificationEntry != null && (ex = notificationEntry.getEx()) != null) {
                lValueOf = Long.valueOf(ex.getUpdateTime());
            }
        }
        if (lValueOf != null) {
            return lValueOf.longValue();
        }
        return 0L;
    }

    public final int keyguardFilterOutCount() {
        List children;
        GroupEntry groupEntryM384getBase = m384getBase();
        int i = 0;
        if (groupEntryM384getBase != null && (children = groupEntryM384getBase.getChildren()) != null) {
            List list = children;
            KeyguardNotificationVisibilityProvider keyguardNotificationVisibilityProvider = this.keyguardNotificationVisibilityProvider;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (keyguardNotificationVisibilityProvider.shouldHideNotification((NotificationEntry) it.next()) && (i = i + 1) < 0) {
                        CollectionsKt__CollectionsKt.throwCountOverflow();
                        throw null;
                    }
                }
            }
        }
        return i;
    }

    public final int oplusMediaCoordinatorFilterOutCount() {
        List children;
        GroupEntry groupEntryM384getBase = m384getBase();
        int i = 0;
        if (groupEntryM384getBase != null && (children = groupEntryM384getBase.getChildren()) != null) {
            List list = children;
            OplusMediaCoordinator oplusMediaCoordinator = this.oplusMediaCoordinator;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (oplusMediaCoordinator.shouldFilterOutByOplusMediaCoordinator((NotificationEntry) it.next()) && (i = i + 1) < 0) {
                        CollectionsKt__CollectionsKt.throwCountOverflow();
                        throw null;
                    }
                }
            }
        }
        return i;
    }
}
