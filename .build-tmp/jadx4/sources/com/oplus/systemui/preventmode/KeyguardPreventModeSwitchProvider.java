package com.oplus.systemui.preventmode;

import android.content.Context;
import com.android.settingslib.drawer.SwitchController;
import com.android.settingslib.drawer.SwitchesProvider;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class KeyguardPreventModeSwitchProvider extends SwitchesProvider {
    /* JADX WARN: Multi-variable type inference failed */
    public List<SwitchController> createSwitchControllers() {
        Context applicationContext;
        ArrayList arrayList = new ArrayList();
        Context context = getContext();
        if (context != null && (applicationContext = context.getApplicationContext()) != null) {
            arrayList.add(new KeyguardPreventModeSwitchController(applicationContext));
        }
        return arrayList;
    }
}
