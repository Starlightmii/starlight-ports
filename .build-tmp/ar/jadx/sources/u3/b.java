package u3;

import b4.l;
import b4.z;
import p3.j;
import v3.g;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class b extends g {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5842d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ a4.e f5843e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ t3.c f5844f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(a4.e eVar, t3.c cVar, t3.c cVar2) {
        super(cVar);
        this.f5843e = eVar;
        this.f5844f = cVar2;
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        int i5 = this.f5842d;
        if (i5 != 0) {
            if (i5 != 1) {
                throw new IllegalStateException("This coroutine had already completed");
            }
            this.f5842d = 2;
            j.O(obj);
            return obj;
        }
        this.f5842d = 1;
        j.O(obj);
        a4.e eVar = this.f5843e;
        l.c("null cannot be cast to non-null type kotlin.Function2<R of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted, kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted>, kotlin.Any?>", eVar);
        z.c(2, eVar);
        return eVar.invoke(this.f5844f, this);
    }
}
