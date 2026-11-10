package n1;

import c0.g1;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class x implements g0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4182a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ g0 f4183b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ b0 f4184c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f4185d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ g0 f4186e;

    public /* synthetic */ x(g0 g0Var, b0 b0Var, int i5, g0 g0Var2, int i6) {
        this.f4182a = i6;
        this.f4184c = b0Var;
        this.f4185d = i5;
        this.f4186e = g0Var2;
        this.f4183b = g0Var;
    }

    @Override // n1.g0
    public final int e() {
        switch (this.f4182a) {
            case 0:
                break;
        }
        return this.f4183b.e();
    }

    @Override // n1.g0
    public final int l() {
        switch (this.f4182a) {
            case 0:
                break;
        }
        return this.f4183b.l();
    }

    @Override // n1.g0
    public final Map m() {
        switch (this.f4182a) {
            case 0:
                break;
        }
        return this.f4183b.m();
    }

    @Override // n1.g0
    public final void n() {
        switch (this.f4182a) {
            case 0:
                int i5 = this.f4185d;
                b0 b0Var = this.f4184c;
                b0Var.f4094g = i5;
                this.f4186e.n();
                Set setEntrySet = b0Var.f4101n.entrySet();
                g1 g1Var = new g1(12, b0Var);
                b4.l.e("<this>", setEntrySet);
                Iterator it = setEntrySet.iterator();
                while (it.hasNext()) {
                    if (((Boolean) g1Var.c(it.next())).booleanValue()) {
                        it.remove();
                    }
                }
                break;
            default:
                int i6 = this.f4185d;
                b0 b0Var2 = this.f4184c;
                b0Var2.f4093f = i6;
                this.f4186e.n();
                b0Var2.d(b0Var2.f4093f);
                break;
        }
    }

    @Override // n1.g0
    public final a4.c o() {
        switch (this.f4182a) {
            case 0:
                break;
        }
        return this.f4183b.o();
    }
}
