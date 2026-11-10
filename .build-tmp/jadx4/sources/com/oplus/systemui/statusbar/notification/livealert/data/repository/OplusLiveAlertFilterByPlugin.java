package com.oplus.systemui.statusbar.notification.livealert.data.repository;

import android.os.Bundle;
import android.util.ArrayMap;
import androidx.car.app.SurfaceContainer$;
import androidx.collection.FloatList$;
import androidx.compose.animation.core.MonoSpline$;
import com.android.systemui.plugins.statusbar.SeedlingPlugin;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.oplus.systemui.statusbar.seeding.SeedlingPluginCallUtils;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusLiveAlertFilterByPlugin extends OplusLiveAlertFilter {
    public static final Companion Companion = new Companion();
    private static final String METHOD_ON_LIVE_NOTIFICATION_POST = "onLiveNotificationPost";
    private static final String PARAM_STATUS_BAR_NOTIFICATION = "statusBarNotification";
    private static final String VALUE_LIVE_ALERT_SHOW_FLAG = "liveAlertShowFlag";
    private final ArrayMap<String, CacheValue> cache = new ArrayMap<>();
    private final SeedlingPlugin plugin;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class CacheValue {
        public final int flag;
        public final int importance;
        public final int sbnIdentity;

        public CacheValue(int i, int i2, int i3) {
            this.sbnIdentity = i;
            this.importance = i2;
            this.flag = i3;
        }

        public final CacheValue copy(int i, int i2, int i3) {
            return new CacheValue(i, i2, i3);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CacheValue)) {
                return false;
            }
            CacheValue cacheValue = (CacheValue) obj;
            return this.sbnIdentity == cacheValue.sbnIdentity && this.importance == cacheValue.importance && this.flag == cacheValue.flag;
        }

        public final int getFlag() {
            return this.flag;
        }

        public final int getImportance() {
            return this.importance;
        }

        public final int getSbnIdentity() {
            return this.sbnIdentity;
        }

        public final int hashCode() {
            return Integer.hashCode(this.flag) + MonoSpline$.ExternalSyntheticOutline0.m(this.importance, Integer.hashCode(this.sbnIdentity) * 31, 31);
        }

        public String toString() {
            return SurfaceContainer$.ExternalSyntheticOutline0.m(this.flag, ")", FloatList$.ExternalSyntheticOutline0.m("CacheValue(sbnIdentity=", this.sbnIdentity, this.importance, ", importance=", ", flag="));
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusLiveAlertFilterByPlugin(SeedlingPlugin seedlingPlugin) {
        this.plugin = seedlingPlugin;
    }

    @Override // com.oplus.systemui.statusbar.notification.livealert.data.repository.OplusLiveAlertFilter
    public final void clearCache$1() {
        this.cache.clear();
    }

    @Override // com.oplus.systemui.statusbar.notification.livealert.data.repository.OplusLiveAlertFilter
    public int filterEntry(NotificationEntry notificationEntry) {
        String key = notificationEntry.getKey();
        int iIdentityHashCode = System.identityHashCode(notificationEntry.getSbn());
        int importance = notificationEntry.getImportance();
        CacheValue cacheValue = this.cache.get(key);
        if (cacheValue != null && cacheValue.getSbnIdentity() == iIdentityHashCode && cacheValue.getImportance() == importance) {
            return cacheValue.getFlag();
        }
        Bundle bundle = SeedlingPluginCallUtils.getBundle(METHOD_ON_LIVE_NOTIFICATION_POST, PARAM_STATUS_BAR_NOTIFICATION, notificationEntry.getSbn());
        bundle.putInt(SeedlingPluginCallUtils.PARAM_NOTIFICATION_IMPORTANCE, importance);
        Bundle bundleCall = this.plugin.call(bundle);
        int i = bundleCall != null ? bundleCall.getInt(VALUE_LIVE_ALERT_SHOW_FLAG, 0) : 0;
        this.cache.put(key, new CacheValue(iIdentityHashCode, importance, i));
        return i;
    }

    @Override // com.oplus.systemui.statusbar.notification.livealert.data.repository.OplusLiveAlertFilter
    public void removeCache(String str) {
        this.cache.remove(str);
    }
}
