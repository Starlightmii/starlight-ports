package io.github.andrea_lyz.assistrestore;

import io.github.libxposed.api.XposedInterface;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class e implements XposedInterface.Hooker {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2816a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Field f2817b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ AssistRestoreModule f2818c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f2819d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f2820e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ AnnotatedElement f2821f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f2822g;

    public /* synthetic */ e(AssistRestoreModule assistRestoreModule, Field field, Method[] methodArr, Method[] methodArr2, Field field2, SystemServerHooks.HapticCall[] hapticCallArr) {
        this.f2816a = 2;
        this.f2818c = assistRestoreModule;
        this.f2817b = field;
        this.f2819d = methodArr;
        this.f2820e = methodArr2;
        this.f2821f = field2;
        this.f2822g = hapticCallArr;
    }

    public /* synthetic */ e(Field field, AssistRestoreModule assistRestoreModule, ClassLoader classLoader, SystemUiHooks.AssistPipeline assistPipeline, Class cls, SystemUiHooks.CtsPipeline ctsPipeline, int i5) {
        this.f2816a = i5;
        this.f2817b = field;
        this.f2818c = assistRestoreModule;
        this.f2819d = classLoader;
        this.f2820e = assistPipeline;
        this.f2821f = cls;
        this.f2822g = ctsPipeline;
    }

    public final Object intercept(XposedInterface.Chain chain) {
        switch (this.f2816a) {
            case 0:
                return SystemUiHooks.lambda$installOcrScreenHandleLongPress$4(this.f2817b, this.f2818c, (ClassLoader) this.f2819d, (SystemUiHooks.AssistPipeline) this.f2820e, (Class) this.f2821f, (SystemUiHooks.CtsPipeline) this.f2822g, chain);
            case 1:
                return SystemUiHooks.lambda$installGestureHandleLongPress$2(this.f2817b, this.f2818c, (ClassLoader) this.f2819d, (SystemUiHooks.AssistPipeline) this.f2820e, (Class) this.f2821f, (SystemUiHooks.CtsPipeline) this.f2822g, chain);
            default:
                return SystemServerHooks.lambda$install$0(this.f2818c, this.f2817b, (Method[]) this.f2819d, (Method[]) this.f2820e, (Field) this.f2821f, (SystemServerHooks.HapticCall[]) this.f2822g, chain);
        }
    }
}
