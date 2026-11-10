package com.oplusos.systemui.common.olivelink;

import android.graphics.Color;
import com.innopeaktech.olivelink.server_1_18.OliveLink;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OliveLinkColorAdapter implements AndroidColor {
    private final OliveLink.Color color;

    public OliveLinkColorAdapter(OliveLink.Color color) {
        this.color = color;
    }

    @Override // com.oplusos.systemui.common.olivelink.AndroidColor
    public Color get() {
        return this.color.toAndroidColor();
    }

    @Override // com.oplusos.systemui.common.olivelink.AndroidColor
    public void registerChangeListener(Object obj, Function0 function0) {
        OliveLink.Color color = this.color;
        registerChangeListener.1 r0 = new registerChangeListener.1();
        r0.$listener = function0;
        VarHandle.storeStoreFence();
        color.registerChangeListener(obj, r0);
    }

    @Override // com.oplusos.systemui.common.olivelink.AndroidColor
    public void set(Color color) {
        this.color.set(color);
    }
}
