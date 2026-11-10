package io.github.andrea_lyz.assistrestore.ui;

import c0.a3;
import c0.c3;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
@v3.e(c = "io.github.andrea_lyz.assistrestore.ui.AssistRestoreAppKt$AssistRestoreApp$notify$1$1$1", f = "AssistRestoreApp.kt", l = {334}, m = "invokeSuspend", v = 2)
public final class AssistRestoreAppKt$AssistRestoreApp$notify$1$1$1 extends v3.i implements a4.e {
    final /* synthetic */ String $message;
    final /* synthetic */ c3 $snackbarHost;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AssistRestoreAppKt$AssistRestoreApp$notify$1$1$1(c3 c3Var, String str, t3.c cVar) {
        super(2, cVar);
        this.$snackbarHost = c3Var;
        this.$message = str;
    }

    @Override // v3.a
    public final t3.c create(Object obj, t3.c cVar) {
        return new AssistRestoreAppKt$AssistRestoreApp$notify$1$1$1(this.$snackbarHost, this.$message, cVar);
    }

    @Override // a4.e
    public final Object invoke(i4.v vVar, t3.c cVar) {
        return ((AssistRestoreAppKt$AssistRestoreApp$notify$1$1$1) create(vVar, cVar)).invokeSuspend(p3.n.f5026a);
    }

    @Override // v3.a
    public final Object invokeSuspend(Object obj) {
        int i5 = this.label;
        if (i5 == 0) {
            p3.j.O(obj);
            c3 c3Var = this.$snackbarHost;
            String str = this.$message;
            this.label = 1;
            c3Var.getClass();
            Object objA = c3Var.a(new a3(str), this);
            u3.a aVar = u3.a.f5838d;
            if (objA == aVar) {
                return aVar;
            }
        } else {
            if (i5 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            p3.j.O(obj);
        }
        return p3.n.f5026a;
    }
}
