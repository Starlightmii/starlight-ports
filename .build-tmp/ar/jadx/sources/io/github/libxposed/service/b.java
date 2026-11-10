package io.github.libxposed.service;

import java.util.function.Function;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b implements Function {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3061a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f3062b;

    public /* synthetic */ b(int i5, Object obj) {
        this.f3061a = i5;
        this.f3062b = obj;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        switch (this.f3061a) {
            case 0:
                return ((XposedService) this.f3062b).lambda$getRemotePreferences$0((String) obj);
            default:
                return ((XposedService.OnScopeEventListener) this.f3062b).lambda$asInterface$0((XposedService.OnScopeEventListener) obj);
        }
    }
}
