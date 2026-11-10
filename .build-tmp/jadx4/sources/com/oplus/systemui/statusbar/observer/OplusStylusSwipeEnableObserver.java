package com.oplus.systemui.statusbar.observer;

import android.content.Context;
import android.net.Uri;
import com.oplusos.systemui.common.multiuser.MultiUserContentHelper;
import com.oplusos.systemui.common.observer.AbstractObserver;
import java.util.function.Function;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusStylusSwipeEnableObserver extends AbstractObserver {
    public static final Companion Companion = new Companion();
    private static final String KEY_STYLUS_SWIPE_ENABLE = "ipe_pencil_swipe_function";
    public boolean enable;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public final boolean getEnable() {
        return this.enable;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return KEY_STYLUS_SWIPE_ENABLE;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toGlobalUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public final void onChange(boolean z) {
        int globalIntValue;
        Context context = getContext();
        boolean z2 = false;
        if (context != null && ((globalIntValue = MultiUserContentHelper.Companion.getGlobalIntValue(context, KEY_STYLUS_SWIPE_ENABLE, 2)) == 0 || globalIntValue == 1)) {
            z2 = true;
        }
        this.enable = z2;
        super.onChange(z);
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void onRegister(Context context) {
        int globalIntValue;
        Context context2 = getContext();
        boolean z = false;
        if (context2 != null && ((globalIntValue = MultiUserContentHelper.Companion.getGlobalIntValue(context2, KEY_STYLUS_SWIPE_ENABLE, 2)) == 0 || globalIntValue == 1)) {
            z = true;
        }
        this.enable = z;
        super.onRegister(context);
    }
}
