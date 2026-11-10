package z1;

import android.graphics.RectF;
import android.text.Layout;
import android.text.SegmentFinder;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f7112a = new b();

    /* JADX WARN: Type inference failed for: r0v2, types: [z1.a] */
    public final int[] a(y yVar, RectF rectF, int i5, final a4.e eVar) {
        SegmentFinder segmentFinderI;
        if (i5 == 1) {
            segmentFinderI = a2.b.f39a.a(new a2.h(yVar.f7155e.getText(), 0, yVar.j()));
        } else {
            x.l.n();
            segmentFinderI = x.l.i(x.l.h(yVar.f7155e.getText(), yVar.f7151a));
        }
        return yVar.f7155e.getRangeForRect(rectF, segmentFinderI, new Layout.TextInclusionStrategy() { // from class: z1.a
            @Override // android.text.Layout.TextInclusionStrategy
            public final boolean isSegmentInside(RectF rectF2, RectF rectF3) {
                return ((Boolean) eVar.invoke(rectF2, rectF3)).booleanValue();
            }
        });
    }
}
