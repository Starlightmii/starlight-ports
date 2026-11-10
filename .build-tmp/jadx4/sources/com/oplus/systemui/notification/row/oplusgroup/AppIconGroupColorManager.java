package com.oplus.systemui.notification.row.oplusgroup;

import android.content.Context;
import android.util.Log;
import androidx.car.app.SurfaceContainer$;
import androidx.collection.FloatList$;
import androidx.compose.animation.core.MonoSpline$;
import com.android.systemui.media.controls.domain.pipeline.MediaTimeoutListener$PlaybackStateListener$;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.util.Assert;
import com.oplus.keyguard.settings.KeyguardSettingProvider$;
import com.oplusos.systemui.common.util.LogUtil;
import dagger.Lazy;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class AppIconGroupColorManager {
    public static final Companion Companion = new Companion();
    private static final String TAG = "AppIconGroupColorManager";
    private final CoroutineDispatcher bgDispatcher;
    private final Context context;
    private final Lazy notificationResolverEx;
    private final CoroutineScope scope;
    private final Map<String, AppIconColorConfig> configMap = new LinkedHashMap();
    private final Map<String, Deferred> taskMap = new LinkedHashMap();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AppIconColorConfig {
        public final int darkColor;
        public final int lightColor;
        public final int seedColor;

        public AppIconColorConfig(int i, int i2, int i3) {
            this.lightColor = i;
            this.darkColor = i2;
            this.seedColor = i3;
        }

        public final AppIconColorConfig copy(int i, int i2, int i3) {
            return new AppIconColorConfig(i, i2, i3);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AppIconColorConfig)) {
                return false;
            }
            AppIconColorConfig appIconColorConfig = (AppIconColorConfig) obj;
            return this.lightColor == appIconColorConfig.lightColor && this.darkColor == appIconColorConfig.darkColor && this.seedColor == appIconColorConfig.seedColor;
        }

        public final int getDarkColor() {
            return this.darkColor;
        }

        public final int getLightColor() {
            return this.lightColor;
        }

        public final int getSeedColor() {
            return this.seedColor;
        }

        public final int hashCode() {
            return Integer.hashCode(this.seedColor) + MonoSpline$.ExternalSyntheticOutline0.m(this.darkColor, Integer.hashCode(this.lightColor) * 31, 31);
        }

        public String toString() {
            return SurfaceContainer$.ExternalSyntheticOutline0.m(this.seedColor, ")", FloatList$.ExternalSyntheticOutline0.m("AppIconColorConfig(lightColor=", this.lightColor, this.darkColor, ", darkColor=", ", seedColor="));
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public AppIconGroupColorManager(Context context, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, Lazy lazy) {
        this.context = context;
        this.scope = coroutineScope;
        this.bgDispatcher = coroutineDispatcher;
        this.notificationResolverEx = lazy;
    }

    public final String buildKey(NotificationEntry notificationEntry) {
        return notificationEntry.getSbn().getUserId() + ":" + notificationEntry.getSbn().getPackageName();
    }

    public final void clear(int i, String str) {
        Assert.isMainThread();
        String str2 = i + ":" + str;
        this.configMap.remove(str2);
        Deferred deferredRemove = this.taskMap.remove(str2);
        if (deferredRemove != null) {
            Job.Key key = Job.Key;
            deferredRemove.cancel((CancellationException) null);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            KeyguardSettingProvider$.ExternalSyntheticOutline0.m("clear: key=", str2, "AppIconGroupColorManager-->", "SystemUi--Notification");
        }
    }

    public final void clearAll() {
        Assert.isMainThread();
        this.configMap.clear();
        for (Deferred deferred : this.taskMap.values()) {
            Job.Key key = Job.Key;
            deferred.cancel((CancellationException) null);
        }
        this.taskMap.clear();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "AppIconGroupColorManager-->clearAll");
        }
    }

    public final AppIconColorConfig getCached(String str) {
        Assert.isMainThread();
        return this.configMap.get(str);
    }

    public final Deferred getOrCreateTask(NotificationEntry notificationEntry) {
        Assert.isMainThread();
        String strBuildKey = buildKey(notificationEntry);
        AppIconColorConfig appIconColorConfig = this.configMap.get(strBuildKey);
        if (appIconColorConfig != null) {
            return CompletableDeferredKt.CompletableDeferred(appIconColorConfig);
        }
        Deferred deferred = this.taskMap.get(strBuildKey);
        if (deferred != null) {
            return deferred;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            MediaTimeoutListener$PlaybackStateListener$.ExternalSyntheticOutline0.m("getOrCreateTask: create task key=", strBuildKey, "AppIconGroupColorManager-->", "SystemUi--Notification");
        }
        Deferred deferredAsync$default = BuildersKt.async$default(this.scope, this.bgDispatcher, 2, new AppIconGroupColorManager$getOrCreateTask$deferred$1(this, notificationEntry, strBuildKey, null));
        this.taskMap.put(strBuildKey, deferredAsync$default);
        return deferredAsync$default;
    }
}
