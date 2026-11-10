package androidx.compose.ui.draw;

import a4.c;
import d1.b;
import r0.p;
import y0.m;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static final p a(p pVar, c cVar) {
        return pVar.e(new DrawBehindElement(cVar));
    }

    public static final p b(p pVar, c cVar) {
        return pVar.e(new DrawWithCacheElement(cVar));
    }

    public static final p c(p pVar, c cVar) {
        return pVar.e(new DrawWithContentElement(cVar));
    }

    public static p d(p pVar, b bVar, m mVar, int i5) {
        return pVar.e(new PainterElement(bVar, r0.b.f5657h, 1.0f, mVar));
    }
}
