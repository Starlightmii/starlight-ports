package io.github.andrea_lyz.assistrestore;

import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class d implements XposedInterface.Hooker {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2810a = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ AssistRestoreModule f2811b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Method f2812c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Method f2813d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f2814e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f2815f;

    public /* synthetic */ d(AssistRestoreModule assistRestoreModule, ClassLoader classLoader, Method method, Constructor constructor, Method method2) {
        this.f2811b = assistRestoreModule;
        this.f2814e = classLoader;
        this.f2812c = method;
        this.f2815f = constructor;
        this.f2813d = method2;
    }

    public /* synthetic */ d(AssistRestoreModule assistRestoreModule, Method method, Method method2, Method method3, Object obj) {
        this.f2812c = method;
        this.f2811b = assistRestoreModule;
        this.f2813d = method2;
        this.f2814e = method3;
        this.f2815f = obj;
    }

    public final Object intercept(XposedInterface.Chain chain) {
        switch (this.f2810a) {
            case 0:
                return HansProcessFreezeHooks.lambda$installMethod$0(this.f2812c, this.f2811b, this.f2813d, (Method) this.f2814e, this.f2815f, chain);
            default:
                return SystemUiHooks.lambda$installAssistantAvailability$1(this.f2811b, (ClassLoader) this.f2814e, this.f2812c, (Constructor) this.f2815f, this.f2813d, chain);
        }
    }
}
