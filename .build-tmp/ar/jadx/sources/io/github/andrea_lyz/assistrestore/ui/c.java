package io.github.andrea_lyz.assistrestore.ui;

import android.content.Context;
import f0.i1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c implements a4.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2866d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ i1 f2867e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f2868f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f2869g;

    public /* synthetic */ c(androidx.lifecycle.v vVar, Context context, i1 i1Var) {
        this.f2866d = 2;
        this.f2869g = vVar;
        this.f2868f = context;
        this.f2867e = i1Var;
    }

    public /* synthetic */ c(Object obj, p3.c cVar, i1 i1Var, int i5) {
        this.f2866d = i5;
        this.f2868f = obj;
        this.f2869g = cVar;
        this.f2867e = i1Var;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        switch (this.f2866d) {
            case 0:
                return AssistRestoreAppKt.AssistRestoreApp$lambda$57$0((Context) this.f2868f, (a4.c) this.f2869g, this.f2867e, ((Boolean) obj).booleanValue());
            case 1:
                return AssistRestoreAppKt.AssistRestoreApp$lambda$58$0((SettingsStore) this.f2868f, (a4.a) this.f2869g, this.f2867e, ((Boolean) obj).booleanValue());
            default:
                return AssistRestoreAppKt.AssistRestoreApp$lambda$38$0((androidx.lifecycle.v) this.f2869g, (Context) this.f2868f, this.f2867e, (f0.q0) obj);
        }
    }
}
