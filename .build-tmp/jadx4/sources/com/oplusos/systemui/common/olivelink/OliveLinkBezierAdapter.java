package com.oplusos.systemui.common.olivelink;

import com.innopeaktech.olivelink.server_1_18.OliveLink;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OliveLinkBezierAdapter implements CubicBezier {
    private final OliveLink.CubicBezier bezier;

    public OliveLinkBezierAdapter(OliveLink.CubicBezier cubicBezier) {
        this.bezier = cubicBezier;
    }

    @Override // com.oplusos.systemui.common.olivelink.CubicBezier
    public final float x1() {
        return this.bezier.x1();
    }

    @Override // com.oplusos.systemui.common.olivelink.CubicBezier
    public final float x2() {
        return this.bezier.x2();
    }

    @Override // com.oplusos.systemui.common.olivelink.CubicBezier
    public final float y1() {
        return this.bezier.y1();
    }

    @Override // com.oplusos.systemui.common.olivelink.CubicBezier
    public final float y2() {
        return this.bezier.y2();
    }
}
