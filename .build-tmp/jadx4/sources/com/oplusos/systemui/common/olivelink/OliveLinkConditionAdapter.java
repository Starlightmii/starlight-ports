package com.oplusos.systemui.common.olivelink;

import com.innopeaktech.olivelink.server_1_18.OliveLink;
import java.lang.invoke.VarHandle;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OliveLinkConditionAdapter implements Condition {
    private final OliveLink.Condition condition;

    public OliveLinkConditionAdapter(OliveLink.Condition condition) {
        this.condition = condition;
    }

    @Override // com.oplusos.systemui.common.olivelink.Condition
    public boolean get() {
        return this.condition.get();
    }

    @Override // com.oplusos.systemui.common.olivelink.Condition
    public void registerChangeListener(Object obj, Function0 function0) {
        OliveLink.Condition condition = this.condition;
        registerChangeListener.1 r0 = new registerChangeListener.1();
        r0.$listener = function0;
        VarHandle.storeStoreFence();
        condition.registerChangeListener(obj, r0);
    }

    @Override // com.oplusos.systemui.common.olivelink.Condition
    public void set(boolean z) {
        this.condition.set(z);
    }
}
