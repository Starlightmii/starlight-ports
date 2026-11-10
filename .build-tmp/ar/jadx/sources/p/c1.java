package p;

import android.content.res.Configuration;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class c1 extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f4422e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ f0.i1 f4423f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c1(f0.i1 i1Var, int i5) {
        super(1);
        this.f4422e = i5;
        this.f4423f = i1Var;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        int i5 = this.f4422e;
        p3.n nVar = p3.n.f5026a;
        f0.i1 i1Var = this.f4423f;
        switch (i5) {
            case 0:
                return (Float) ((a4.c) i1Var.getValue()).c(Float.valueOf(((Number) obj).floatValue()));
            case 1:
                Configuration configuration = new Configuration((Configuration) obj);
                f0.j0 j0Var = AndroidCompositionLocals_androidKt.f203a;
                i1Var.setValue(configuration);
                return nVar;
            default:
                ((a4.c) i1Var.getValue()).c(new x0.c(((x0.c) obj).f6532a));
                return nVar;
        }
    }
}
