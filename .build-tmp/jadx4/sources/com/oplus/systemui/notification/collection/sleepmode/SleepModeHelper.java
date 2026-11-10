package com.oplus.systemui.notification.collection.sleepmode;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import androidx.core.app.NotificationCompat$Builder$;
import com.android.systemui.Dependency;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SleepModeHelper {
    private static final String STR_DEEP_SLEEP_STATUS = "oplus_deep_sleep_status";
    private static final String TAG = "SleepModeHelper";
    private final List<Callback> callbacks = new ArrayList();
    private Context context;
    public boolean isSleepMode;
    private Handler mainHandler;
    private final SleepModeHelper$sleepModeStateObserver$1 sleepModeStateObserver;
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new SleepModeHelper$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final SleepModeHelper getInstance() {
            return (SleepModeHelper) SleepModeHelper.instance$delegate.getValue();
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.oplus.systemui.notification.collection.sleepmode.SleepModeHelper$sleepModeStateObserver$1] */
    public SleepModeHelper() {
        final Handler handler = this.mainHandler;
        this.sleepModeStateObserver = new ContentObserver(handler) { // from class: com.oplus.systemui.notification.collection.sleepmode.SleepModeHelper$sleepModeStateObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                super.onChange(z);
                if (this.this$0.initSleepModeState()) {
                    SleepModeHelper.access$notifyCallback(this.this$0);
                }
            }
        };
    }

    public static final void access$notifyCallback(SleepModeHelper sleepModeHelper) {
        Iterator<Callback> it = sleepModeHelper.callbacks.iterator();
        if (it.hasNext()) {
            throw NotificationCompat$Builder$.ExternalSyntheticOutline0.m(it);
        }
    }

    public final void addCallback(Callback callback) {
        this.callbacks.add(callback);
    }

    public final void init$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(Context context) {
        ContentResolver contentResolver;
        this.context = context;
        this.mainHandler = (Handler) Dependency.sDependency.getDependencyInner(Dependency.MAIN_HANDLER);
        initSleepModeState();
        if (context == null || (contentResolver = context.getContentResolver()) == null) {
            return;
        }
        contentResolver.registerContentObserver(Settings.Secure.getUriFor(STR_DEEP_SLEEP_STATUS), false, this.sleepModeStateObserver, -1);
    }

    public final boolean initSleepModeState() {
        ContentResolver contentResolver;
        boolean z = this.isSleepMode;
        Context context = this.context;
        boolean z2 = (context == null || (contentResolver = context.getContentResolver()) == null || Settings.System.getInt(contentResolver, STR_DEEP_SLEEP_STATUS, 0) == 0) ? false : true;
        this.isSleepMode = z2;
        return z2 != z;
    }

    public final void removeCallback(Callback callback) {
        this.callbacks.remove(callback);
    }
}
