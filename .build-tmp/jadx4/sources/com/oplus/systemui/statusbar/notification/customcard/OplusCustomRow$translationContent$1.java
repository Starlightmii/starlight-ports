package com.oplus.systemui.statusbar.notification.customcard;

import android.util.FloatProperty;
import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusCustomRow$translationContent$1 extends FloatProperty {
    @Override // android.util.Property
    public final Float get(Object obj) {
        return Float.valueOf(((OplusCustomRow) ((View) obj)).getTranslation());
    }

    @Override // android.util.FloatProperty
    public final void setValue(Object obj, float f) {
        ((OplusCustomRow) ((View) obj)).setTranslation(f);
    }
}
