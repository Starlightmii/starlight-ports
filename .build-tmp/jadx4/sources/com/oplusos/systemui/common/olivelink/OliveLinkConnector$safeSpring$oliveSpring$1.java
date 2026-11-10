package com.oplusos.systemui.common.olivelink;

import com.innopeaktech.olivelink.server_1_18.OliveLink;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OliveLinkConnector$safeSpring$oliveSpring$1 implements OliveLink.Spring {
    public /* synthetic */ float $bounce;
    public /* synthetic */ float $dampingRatio;
    public /* synthetic */ OliveLink.SpringType $oliveSpringType;
    public /* synthetic */ float $response;
    public /* synthetic */ float $stiffness;
    public /* synthetic */ float $velocity;
    public String name;

    public final float bounce() {
        return this.$bounce;
    }

    public final float dampingRatio() {
        return this.$dampingRatio;
    }

    public String getName() {
        return this.name;
    }

    public final void registerChangeListener(Object obj, OliveLink.ValueChangeListener valueChangeListener) {
    }

    public final float response() {
        return this.$response;
    }

    public final void set(float f, float f2, float f3, OliveLink.SpringType springType) {
    }

    public final void set(float f, float f2, OliveLink.SpringType springType) {
    }

    public final OliveLink.SpringType springType() {
        return this.$oliveSpringType;
    }

    public final float stiffness() {
        return this.$stiffness;
    }

    public final float velocity() {
        return this.$velocity;
    }
}
