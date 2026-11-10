package com.oplusos.systemui.common.olivelink;

import com.innopeaktech.olivelink.server_1_18.OliveLink;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OliveLinkScalarAdapter implements Scalar {
    private final OliveLink.Scalar<Number> scalar;

    public OliveLinkScalarAdapter(OliveLink.Scalar<Number> scalar) {
        this.scalar = scalar;
    }

    @Override // com.oplusos.systemui.common.olivelink.Scalar
    public Number get() {
        return this.scalar.get();
    }

    @Override // com.oplusos.systemui.common.olivelink.Scalar
    public void registerChangeListener(Object obj, Function0 function0) {
        OliveLink.Scalar<Number> scalar = this.scalar;
        registerChangeListener.1 r0 = new registerChangeListener.1();
        r0.$listener = function0;
        VarHandle.storeStoreFence();
        scalar.registerChangeListener(obj, r0);
    }

    @Override // com.oplusos.systemui.common.olivelink.Scalar
    public void set(Number number) {
        this.scalar.set(number);
    }
}
