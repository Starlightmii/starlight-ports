package io.github.andrea_lyz.assistrestore.ui;

import f0.h1;
import f0.i1;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class v implements a4.f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f3020d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ List f3021e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f3022f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f3023g;

    public /* synthetic */ v(List list, Object obj, Object obj2, int i5) {
        this.f3020d = i5;
        this.f3021e = list;
        this.f3022f = obj;
        this.f3023g = obj2;
    }

    @Override // a4.f
    public final Object b(Object obj, Object obj2, Object obj3) {
        switch (this.f3020d) {
            case 0:
                int iIntValue = ((Integer) obj3).intValue();
                return AssistRestoreAppKt.CustomTargetScreen$lambda$18$0$4$0$3(this.f3021e, (h1) this.f3022f, (i1) this.f3023g, (r.w) obj, (f0.p) obj2, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj3).intValue();
                return AssistRestoreAppKt.EntriesScreen$lambda$1$0$1(this.f3021e, (AssistantSnapshot) this.f3022f, (a4.c) this.f3023g, (r.w) obj, (f0.p) obj2, iIntValue2);
        }
    }
}
