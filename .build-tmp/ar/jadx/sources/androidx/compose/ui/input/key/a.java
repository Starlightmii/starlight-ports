package androidx.compose.ui.input.key;

import a4.c;
import r0.p;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static final p a(c cVar) {
        return new KeyInputElement(cVar, null);
    }

    public static final p b(p pVar, c cVar) {
        return pVar.e(new KeyInputElement(null, cVar));
    }
}
