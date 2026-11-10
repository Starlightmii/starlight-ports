package com.oplus.systemui.notification.collection.drivingmode;

import android.app.Notification;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import com.android.systemui.Dependency;
import com.oplus.systemui.notification.mediapanel.AodMediaPromptController;
import com.oplusos.systemui.common.feature.FlavorTwoFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.util.OpUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DrivingModeHelper {
    private static final String DRIVING_MODE_STATE = "driving_mode_state";
    private static final String GMS_MAPS_PK = "com.google.android.apps.maps";
    private static final String TAG = "DrivingModeHelper";
    private final List<Callback> callbacks = new ArrayList();
    private Context context;
    private final DrivingModeHelper$drivingModeStateObserver$1 drivingModeStateObserver;
    public boolean isDrivingMode;
    public boolean isGmsDrivingMode;
    private Handler mainHandler;
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new DrivingModeHelper$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Callback {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final DrivingModeHelper getInstance() {
            return (DrivingModeHelper) DrivingModeHelper.instance$delegate.getValue();
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.oplus.systemui.notification.collection.drivingmode.DrivingModeHelper$drivingModeStateObserver$1] */
    public DrivingModeHelper() {
        final Handler handler = this.mainHandler;
        this.drivingModeStateObserver = new ContentObserver(handler) { // from class: com.oplus.systemui.notification.collection.drivingmode.DrivingModeHelper$drivingModeStateObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                super.onChange(z);
                if (this.this$0.initDrivingModeState()) {
                    this.this$0.notifyCallback();
                }
            }
        };
    }

    public static boolean isGmsNavigationNotification(StatusBarNotification statusBarNotification) {
        Notification notification;
        return statusBarNotification != null && statusBarNotification.getId() == 1 && Intrinsics.areEqual(statusBarNotification.getPackageName(), GMS_MAPS_PK) && (notification = statusBarNotification.getNotification()) != null && notification.isForegroundService();
    }

    public final void addCallback(Callback callback) {
        this.callbacks.add(callback);
    }

    public final void init$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(Context context) {
        ContentResolver contentResolver;
        this.context = context;
        this.mainHandler = (Handler) Dependency.sDependency.getDependencyInner(Dependency.MAIN_HANDLER);
        List list = OpUtils.PACKAGE_LISTENERS;
        FlavorTwoFeatureOption.INSTANCE.getClass();
        if (FlavorTwoFeatureOption.isFlavorTwoDeviceExp()) {
            initDrivingModeState();
            if (context == null || (contentResolver = context.getContentResolver()) == null) {
                return;
            }
            contentResolver.registerContentObserver(Settings.Secure.getUriFor(DRIVING_MODE_STATE), false, this.drivingModeStateObserver, -1);
        }
    }

    public final boolean initDrivingModeState() {
        ContentResolver contentResolver;
        boolean z = this.isDrivingMode;
        Context context = this.context;
        boolean z2 = (context == null || (contentResolver = context.getContentResolver()) == null || Settings.Secure.getIntForUser(contentResolver, DRIVING_MODE_STATE, 0, -2) == 0) ? false : true;
        this.isDrivingMode = z2;
        return z2 != z;
    }

    public final void notifyCallback() {
        Iterator<Callback> it = this.callbacks.iterator();
        while (it.hasNext()) {
            ((AodMediaPromptController) it.next()).onDrivingModeChange();
        }
    }

    public final void onNotificationPosted$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(StatusBarNotification statusBarNotification) {
        if (this.isGmsDrivingMode || !isGmsNavigationNotification(statusBarNotification)) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", "DrivingModeHelper-->onNotificationPosted: Is gms driving mode");
        }
        this.isGmsDrivingMode = true;
        notifyCallback();
    }

    public final void onNotificationRemoved$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(StatusBarNotification statusBarNotification) {
        if (this.isGmsDrivingMode && isGmsNavigationNotification(statusBarNotification)) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
                Log.d("SystemUi--Notification", "DrivingModeHelper-->onNotificationRemoved: Isn't gms driving mode");
            }
            this.isGmsDrivingMode = false;
            notifyCallback();
        }
    }

    public final void removeCallback(Callback callback) {
        this.callbacks.remove(callback);
    }
}
