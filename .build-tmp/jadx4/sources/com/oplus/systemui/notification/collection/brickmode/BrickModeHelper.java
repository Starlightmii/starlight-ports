package com.oplus.systemui.notification.collection.brickmode;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import com.android.systemui.Dependency;
import com.oplus.systemui.keyguard.clockstyle.KeyguardStyleWidgetUIControllerImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class BrickModeHelper {
    private static final String OP_BREATH_MODE_STATUS = "op_breath_mode_status";
    private static final String TAG = "BrickModeHelper";
    private final BrickModeHelper$brickModeStateObserver$1 brickModeStateObserver;
    private final List<Callback> callbacks = new ArrayList();
    private Context context;
    public boolean isBrickMode;
    private Handler mainHandler;
    public static final Companion Companion = new Companion();
    private static final Lazy instance$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new BrickModeHelper$$ExternalSyntheticLambda0());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Callback {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final BrickModeHelper getInstance() {
            return (BrickModeHelper) BrickModeHelper.instance$delegate.getValue();
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.oplus.systemui.notification.collection.brickmode.BrickModeHelper$brickModeStateObserver$1] */
    public BrickModeHelper() {
        final Handler handler = this.mainHandler;
        this.brickModeStateObserver = new ContentObserver(handler) { // from class: com.oplus.systemui.notification.collection.brickmode.BrickModeHelper$brickModeStateObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                super.onChange(z);
                if (this.this$0.initBrickModeState()) {
                    BrickModeHelper.access$notifyCallback(this.this$0);
                }
            }
        };
    }

    public static final void access$notifyCallback(BrickModeHelper brickModeHelper) {
        Iterator<Callback> it = brickModeHelper.callbacks.iterator();
        while (it.hasNext()) {
            KeyguardStyleWidgetUIControllerImpl.brickModeCallback.1 r1 = (Callback) it.next();
            boolean z = brickModeHelper.isBrickMode;
            KeyguardStyleWidgetUIControllerImpl.access$setWidgetsVisibility(r1.this$0, "onBrickModeChange isBrickMode:" + z);
        }
    }

    public final void addCallback(Callback callback) {
        this.callbacks.add(callback);
    }

    public final void init$vendor__oplus__packages__apps__systemui__oplus_systemui__android_common__Oplus_SystemUI_application_domestic(Context context) {
        ContentResolver contentResolver;
        this.context = context;
        this.mainHandler = (Handler) Dependency.sDependency.getDependencyInner(Dependency.MAIN_HANDLER);
        initBrickModeState();
        if (context == null || (contentResolver = context.getContentResolver()) == null) {
            return;
        }
        contentResolver.registerContentObserver(Settings.Secure.getUriFor("op_breath_mode_status"), false, this.brickModeStateObserver, -2);
    }

    public final boolean initBrickModeState() {
        ContentResolver contentResolver;
        boolean z = this.isBrickMode;
        Context context = this.context;
        boolean z2 = (context == null || (contentResolver = context.getContentResolver()) == null || Settings.Secure.getIntForUser(contentResolver, "op_breath_mode_status", 0, -2) == 0) ? false : true;
        this.isBrickMode = z2;
        return z2 != z;
    }

    public final void removeCallback(Callback callback) {
        this.callbacks.remove(callback);
    }
}
