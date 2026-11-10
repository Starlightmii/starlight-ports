package com.oplus.systemui.statusbar.notification.collection;

import android.util.Log;
import com.android.systemui.statusbar.notification.collection.EntryWithDismissStats;
import com.android.systemui.statusbar.notification.collection.NotifCollection;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.notifcollection.DismissedByUserStats;
import com.android.systemui.statusbar.notification.collection.render.NotificationVisibilityProvider;
import com.android.systemui.util.Assert;
import com.android.systemui.util.time.SystemClock;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.collections.CollectionsKt__IterablesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationBootSuppressor {
    public static final Companion Companion = new Companion();
    private static final String TAG = "NotificationBootSuppressor";
    private final Lazy notifCollectionLazy;
    public boolean suppressArmed;
    private final SystemClock systemClock;
    private final Lazy visibilityProviderLazy;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public NotificationBootSuppressor(Lazy lazy, Lazy lazy2, SystemClock systemClock) {
        this.notifCollectionLazy = lazy;
        this.visibilityProviderLazy = lazy2;
        this.systemClock = systemClock;
    }

    public final void clearAllNotifications() {
        Assert.isMainThread();
        this.suppressArmed = true;
        NotifCollection notifCollection = (NotifCollection) this.notifCollectionLazy.get();
        NotificationVisibilityProvider notificationVisibilityProvider = (NotificationVisibilityProvider) this.visibilityProviderLazy.get();
        Collection<NotificationEntry> allNotifs = notifCollection.getAllNotifs();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(allNotifs, 10));
        for (NotificationEntry notificationEntry : allNotifs) {
            notificationEntry.getClass();
            arrayList.add(new EntryWithDismissStats(new DismissedByUserStats(3, notificationVisibilityProvider.obtain(notificationEntry, true)), notificationEntry.getKey(), notificationEntry.hashCode()));
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            int size = arrayList.size();
            this.systemClock.getClass();
            Log.d("SystemUi--Notification", "NotificationBootSuppressor-->".concat("clearAllAndArm: dismiss " + size + " notifs, elapsedRealtime=" + android.os.SystemClock.elapsedRealtime()));
        }
        notifCollection.dismissNotifications(arrayList, false);
    }

    public final boolean shouldSuppressPostInBootWindow() {
        Assert.isMainThread();
        if (!this.suppressArmed) {
            return false;
        }
        this.systemClock.getClass();
        boolean z = android.os.SystemClock.elapsedRealtime() <= 900000;
        if (!z) {
            this.suppressArmed = false;
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "NotificationBootSuppressor-->boot suppress window elapsed, resume normal notification posting");
            }
        }
        return z;
    }
}
