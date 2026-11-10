package g4;

import e1.y;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class j implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2564a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f2565b;

    public /* synthetic */ j(int i5, Object obj) {
        this.f2564a = i5;
        this.f2565b = obj;
    }

    @Override // g4.f
    public final Iterator iterator() {
        switch (this.f2564a) {
            case 0:
                return (Iterator) this.f2565b;
            default:
                return new y(this);
        }
    }
}
