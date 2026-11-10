package com.oplus.systemui.plugins.tileservice.plugin;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import com.oplus.systemui.plugins.tileservice.util.TileThreadUtil;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class TileStateController$$ExternalSyntheticLambda0 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ Object f$0;

    public /* synthetic */ TileStateController$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    public final Object invoke() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                return TileStateController.$r8$lambda$JV_ywhmOBuhnJNJ5rXFDqKjtc0g((TileStateController) obj);
            default:
                final TileStateController.CustomTileContentProviderInfo customTileContentProviderInfo = (TileStateController.CustomTileContentProviderInfo) obj;
                final Handler workHandler = TileThreadUtil.getWorkHandler();
                return new ContentObserver(workHandler) { // from class: com.oplus.systemui.plugins.tileservice.plugin.TileStateController$CustomTileContentProviderInfo$observer$2$1
                    @Override // android.database.ContentObserver
                    public final void onChange(boolean z, Uri uri, int i2) {
                        Context context = (Context) customTileContentProviderInfo.contextReference.get();
                        if (context == null) {
                            return;
                        }
                        int statusFromProvider = customTileContentProviderInfo.getStatusFromProvider(context);
                        customTileContentProviderInfo.observedStatus.set(statusFromProvider);
                        Function1 function1 = (Function1) customTileContentProviderInfo.callBackReference.get();
                        if (function1 != null) {
                            function1.invoke(Integer.valueOf(statusFromProvider));
                        }
                    }
                };
        }
    }
}
