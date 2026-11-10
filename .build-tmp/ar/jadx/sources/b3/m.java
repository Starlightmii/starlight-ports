package b3;

import java.util.concurrent.ThreadPoolExecutor;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class m extends h1.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ h1.c f628i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ ThreadPoolExecutor f629j;

    public m(h1.c cVar, ThreadPoolExecutor threadPoolExecutor) {
        this.f628i = cVar;
        this.f629j = threadPoolExecutor;
    }

    @Override // h1.c
    public final void I(Throwable th) {
        ThreadPoolExecutor threadPoolExecutor = this.f629j;
        try {
            this.f628i.I(th);
        } finally {
            threadPoolExecutor.shutdown();
        }
    }

    @Override // h1.c
    public final void J(w wVar) {
        ThreadPoolExecutor threadPoolExecutor = this.f629j;
        try {
            this.f628i.J(wVar);
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
