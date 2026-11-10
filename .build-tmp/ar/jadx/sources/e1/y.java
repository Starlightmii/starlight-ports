package e1;

import android.view.View;
import b.j0;
import io.github.andrea_lyz.assistrestore.R;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class y implements Iterator, c4.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f1969d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Iterator f1970e;

    public y(z zVar) {
        this.f1970e = zVar.f1972e.iterator();
    }

    public y(g4.j jVar) {
        this.f1970e = ((g4.f) jVar.f2565b).iterator();
    }

    public y(n0.g gVar) {
        k0.n[] nVarArr = new k0.n[8];
        for (int i5 = 0; i5 < 8; i5++) {
            nVarArr[i5] = new k0.p(this);
        }
        this.f1970e = new k0.e(gVar, nVarArr);
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.f1969d) {
            case 0:
                return this.f1970e.hasNext();
            case 1:
                return this.f1970e.hasNext();
            default:
                return ((k0.e) this.f1970e).f3365f;
        }
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.f1969d) {
            case 0:
                return (b0) this.f1970e.next();
            case 1:
                View view = (View) this.f1970e.next();
                b4.l.e("it", view);
                Object tag = view.getTag(R.id.view_tree_on_back_pressed_dispatcher_owner);
                if (tag instanceof j0) {
                    return (j0) tag;
                }
                return null;
            default:
                return (Map.Entry) ((k0.e) this.f1970e).next();
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.f1969d) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 1:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                ((k0.e) this.f1970e).remove();
                return;
        }
    }
}
