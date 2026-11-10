package x;

import y0.f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b extends b4.j implements a4.c {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ q f6462k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(q qVar) {
        super(1, b4.k.class, "localToScreen", "startInput$localToScreen(Landroidx/compose/foundation/text/input/internal/LegacyPlatformTextInputServiceAdapter$LegacyPlatformTextInputNode;[F)V", 0);
        this.f6462k = qVar;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        float[] fArr = ((f0) obj).f6626a;
        n1.p pVar = (n1.p) this.f6462k.f6481t.getValue();
        if (pVar != null) {
            if (!pVar.N()) {
                pVar = null;
            }
            if (pVar != null) {
                pVar.P(fArr);
            }
        }
        return p3.n.f5026a;
    }
}
