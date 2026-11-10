package com.oplus.systemui.notification.material;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import com.oplusos.systemui.common.util.SystemUIThreadUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class GroupSlideExpandParams$initTuningCache$1 implements Runnable {
    public /* synthetic */ Context $context;
    public /* synthetic */ GroupSlideExpandParams this$0;

    @Override // java.lang.Runnable
    public final void run() {
        GroupSlideExpandParams.access$refreshTuningEnabled(this.this$0, this.$context);
        Uri uriFor = Settings.System.getUriFor("oplus_debug_group_slide_expand");
        ContentResolver contentResolver = this.$context.getContentResolver();
        Handler workHandler = SystemUIThreadUtil.getWorkHandler();
        final GroupSlideExpandParams groupSlideExpandParams = this.this$0;
        final Context context = this.$context;
        contentResolver.registerContentObserver(uriFor, false, new ContentObserver(workHandler) { // from class: com.oplus.systemui.notification.material.GroupSlideExpandParams$initTuningCache$1.1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                GroupSlideExpandParams.access$refreshTuningEnabled(groupSlideExpandParams, context);
            }
        });
        this.this$0.tuningCacheInitialized = true;
    }
}
