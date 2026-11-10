package com.oplus.systemui.plugins.tileservice.plugin;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class TileStateController$onPackageDataClearReceiver$1 extends BroadcastReceiver {
    public final AtomicBoolean isRegister = new AtomicBoolean(false);
    public final /* synthetic */ TileStateController this$0;

    public TileStateController$onPackageDataClearReceiver$1(TileStateController tileStateController) {
        this.this$0 = tileStateController;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Uri data;
        String encodedSchemeSpecificPart;
        if (!"android.intent.action.PACKAGE_DATA_CLEARED".equals(intent != null ? intent.getAction() : null) || (data = intent.getData()) == null || (encodedSchemeSpecificPart = data.getEncodedSchemeSpecificPart()) == null) {
            return;
        }
        Set setEntrySet = this.this$0.infoMap.entrySet();
        ArrayList arrayList = new ArrayList();
        for (Object obj : setEntrySet) {
            if (Intrinsics.areEqual(((ComponentName) ((Map.Entry) obj).getKey()).getPackageName(), encodedSchemeSpecificPart)) {
                arrayList.add(obj);
            }
        }
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj2 = arrayList.get(i);
            i++;
            Map.Entry entry = (Map.Entry) obj2;
            Log.d("TileStateController", "onPackageDataClearReceiver component: " + entry.getKey());
            TileStateController.CustomTileContentProviderInfo customTileContentProviderInfo = ((TileStateController.OplusCustomTileServiceInfo) entry.getValue()).getCustomTileContentProviderInfo();
            if (customTileContentProviderInfo != null) {
                customTileContentProviderInfo.restoreStatus();
            }
        }
    }
}
