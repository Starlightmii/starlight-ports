package b4;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n extends o implements f4.d, a4.c {
    public n(String str, String str2) {
        super(1, w1.s.class, c.f679d, str, str2);
    }

    @Override // a4.c
    public final Object c(Object obj) {
        g();
        throw null;
    }

    @Override // b4.d
    public final f4.a d() {
        x.f698a.getClass();
        return this;
    }

    public final void g() {
        if (this.f692j) {
            throw new UnsupportedOperationException("Kotlin reflection is not yet supported for synthetic Java properties. Please follow/upvote https://youtrack.jetbrains.com/issue/KT-55980");
        }
        f4.a aVarF = f();
        if (aVarF == this) {
            throw new i4.x("Kotlin reflection implementation is not found at runtime. Make sure you have kotlin-reflect.jar in the classpath");
        }
        ((n) ((f4.d) aVarF)).g();
    }
}
