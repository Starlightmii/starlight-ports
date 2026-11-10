package com.oplusos.systemui.common.olivelink;

import com.innopeaktech.olivelink.server_1_18.OliveLink;
import kotlin.LazyKt__LazyJVMKt$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OliveLinkSpringAdapter implements Spring {
    private final OliveLink.Spring spring;

    public OliveLinkSpringAdapter(OliveLink.Spring spring) {
        this.spring = spring;
    }

    @Override // com.oplusos.systemui.common.olivelink.Spring
    public final float bounce() {
        return this.spring.bounce();
    }

    @Override // com.oplusos.systemui.common.olivelink.Spring
    public final float dampingRatio() {
        return this.spring.dampingRatio();
    }

    @Override // com.oplusos.systemui.common.olivelink.Spring
    public final float response() {
        return this.spring.response();
    }

    @Override // com.oplusos.systemui.common.olivelink.Spring
    public SpringType springType() {
        int i = WhenMappings.$EnumSwitchMapping$0[this.spring.springType().ordinal()];
        if (i == 1) {
            return SpringType.SPRING_INTERPOLATOR;
        }
        if (i == 2) {
            return SpringType.SPRING_ANIMATION;
        }
        LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
        return null;
    }

    @Override // com.oplusos.systemui.common.olivelink.Spring
    public final float stiffness() {
        return this.spring.stiffness();
    }

    @Override // com.oplusos.systemui.common.olivelink.Spring
    public final float velocity() {
        return this.spring.velocity();
    }
}
