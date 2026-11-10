package v3;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class g extends a {
    public g(t3.c cVar) {
        super(cVar);
        if (cVar != null && cVar.getContext() != t3.i.f5807d) {
            throw new IllegalArgumentException("Coroutines with restricted suspension must have EmptyCoroutineContext");
        }
    }

    @Override // t3.c
    public final t3.h getContext() {
        return t3.i.f5807d;
    }
}
