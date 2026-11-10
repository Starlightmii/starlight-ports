package io.github.libxposed.service;

import android.os.IBinder;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c implements IBinder.DeathRecipient {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3063a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ XposedService f3064b;

    public /* synthetic */ c(XposedService xposedService, int i5) {
        this.f3063a = i5;
        this.f3064b = xposedService;
    }

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
        switch (this.f3063a) {
            case 0:
                XposedServiceHelper.lambda$registerListener$1(this.f3064b);
                break;
            default:
                XposedServiceHelper.lambda$onBinderReceived$0(this.f3064b);
                break;
        }
    }
}
