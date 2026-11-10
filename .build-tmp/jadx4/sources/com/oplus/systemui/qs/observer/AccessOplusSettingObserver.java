package com.oplus.systemui.qs.observer;

import android.content.Context;
import android.net.Uri;
import com.oplusos.systemui.common.observer.AbstractObserver;
import java.util.function.Function;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class AccessOplusSettingObserver extends AbstractObserver {
    public boolean isInitialized;
    public int oldValue;

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public String getKey() {
        return "access_color_setting";
    }

    public final int getOldValue() {
        return this.oldValue;
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public Function<String, Uri> getUri() {
        return AbstractObserver.Companion.toSystemUri();
    }

    @Override // com.oplusos.systemui.common.observer.AbstractObserver
    public void onRegister(Context context) {
        super.onRegister(context);
        if (this.isInitialized) {
            return;
        }
        setOldValue(AccessOplusSettingValueProxy.Companion.getAccessOplusSettingValue(context));
    }

    public final void setInitialized(boolean z) {
        this.isInitialized = z;
    }

    public final void setOldValue(int i) {
        this.isInitialized = true;
        this.oldValue = i;
    }
}
