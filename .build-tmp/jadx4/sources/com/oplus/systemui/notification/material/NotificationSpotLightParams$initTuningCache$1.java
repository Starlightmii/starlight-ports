package com.oplus.systemui.notification.material;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NotificationSpotLightParams$initTuningCache$1 implements Runnable {
    public /* synthetic */ Context $context;
    public /* synthetic */ NotificationSpotLightParams this$0;

    @Override // java.lang.Runnable
    public final void run() {
        Context context = this.$context;
        String stringForUser = Settings.System.getStringForUser(context.getContentResolver(), "oplus_debug_notif_spot_light", context.getUserId());
        if (stringForUser != null) {
            StringsKt__StringsKt.toBooleanStrictOrNull(stringForUser);
        }
        Uri uriFor = Settings.System.getUriFor("oplus_debug_notif_spot_light");
        ContentResolver contentResolver = this.$context.getContentResolver();
        Handler workHandler = SystemUIThreadUtil.getWorkHandler();
        final NotificationSpotLightParams notificationSpotLightParams = this.this$0;
        final Context context2 = this.$context;
        contentResolver.registerContentObserver(uriFor, false, new ContentObserver(workHandler) { // from class: com.oplus.systemui.notification.material.NotificationSpotLightParams$initTuningCache$1.1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                Context context3 = context2;
                NotificationSpotLightParams.Companion companion = NotificationSpotLightParams.Companion;
                String stringForUser2 = Settings.System.getStringForUser(context3.getContentResolver(), "oplus_debug_notif_spot_light", context3.getUserId());
                if (stringForUser2 != null) {
                    StringsKt__StringsKt.toBooleanStrictOrNull(stringForUser2);
                }
            }
        });
        this.this$0.tuningCacheInitialized = true;
    }
}
