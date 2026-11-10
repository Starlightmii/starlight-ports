package io.github.andrea_lyz.assistrestore.ui;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class ComposableSingletons$MainActivityKt {
    public static final ComposableSingletons$MainActivityKt INSTANCE = new ComposableSingletons$MainActivityKt();
    private static a4.e lambda$1662132675 = new n0.d(1662132675, false, new f0.k(20));
    private static a4.e lambda$1706964085 = new n0.d(1706964085, false, new f0.k(21));

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n lambda_1662132675$lambda$0(f0.p pVar, int i5) {
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 3) != 2)) {
            AssistRestoreAppKt.AssistRestoreApp(vVar, 0);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n lambda_1706964085$lambda$0(f0.p pVar, int i5) {
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 3) != 2)) {
            ThemeKt.AssistRestoreTheme(false, lambda$1662132675, vVar, 48, 1);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    public final a4.e getLambda$1662132675$app_release() {
        return lambda$1662132675;
    }

    public final a4.e getLambda$1706964085$app_release() {
        return lambda$1706964085;
    }
}
