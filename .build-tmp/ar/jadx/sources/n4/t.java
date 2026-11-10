package n4;

import i4.i0;
import i4.j0;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class t {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f4310b = AtomicIntegerFieldUpdater.newUpdater(t.class, "_size$volatile");
    private volatile /* synthetic */ int _size$volatile;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public i0[] f4311a;

    public final void a(i0 i0Var) {
        i0Var.c((j0) this);
        i0[] i0VarArr = this.f4311a;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f4310b;
        if (i0VarArr == null) {
            i0VarArr = new i0[4];
            this.f4311a = i0VarArr;
        } else if (atomicIntegerFieldUpdater.get(this) >= i0VarArr.length) {
            Object[] objArrCopyOf = Arrays.copyOf(i0VarArr, atomicIntegerFieldUpdater.get(this) * 2);
            b4.l.d("copyOf(...)", objArrCopyOf);
            i0VarArr = (i0[]) objArrCopyOf;
            this.f4311a = i0VarArr;
        }
        int i5 = atomicIntegerFieldUpdater.get(this);
        atomicIntegerFieldUpdater.set(this, i5 + 1);
        i0VarArr[i5] = i0Var;
        i0Var.f2741e = i5;
        c(i5);
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0045  */
    /* JADX WARN: Code duplicated, block: B:14:0x0052  */
    /* JADX WARN: Code duplicated, block: B:17:0x0063  */
    /* JADX WARN: Code duplicated, block: B:21:0x0075 A[LOOP:0: B:9:0x003a->B:21:0x0075, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:24:0x007a A[EDGE_INSN: B:24:0x007a->B:22:0x007a BREAK  A[LOOP:0: B:9:0x003a->B:21:0x0075], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:25:0x007a A[EDGE_INSN: B:25:0x007a->B:22:0x007a BREAK  A[LOOP:0: B:9:0x003a->B:21:0x0075], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:? A[SYNTHETIC] */
    public final i0 b(int i5) {
        int i6;
        int i7;
        Object[] objArr;
        int i8;
        Comparable comparable;
        Comparable comparable2;
        Comparable comparable3;
        Object obj;
        Object[] objArr2 = this.f4311a;
        b4.l.b(objArr2);
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f4310b;
        atomicIntegerFieldUpdater.set(this, atomicIntegerFieldUpdater.get(this) - 1);
        if (i5 < atomicIntegerFieldUpdater.get(this)) {
            d(i5, atomicIntegerFieldUpdater.get(this));
            int i9 = (i5 - 1) / 2;
            if (i5 > 0) {
                i0 i0Var = objArr2[i5];
                b4.l.b(i0Var);
                Object obj2 = objArr2[i9];
                b4.l.b(obj2);
                if (i0Var.compareTo(obj2) < 0) {
                    d(i5, i9);
                    c(i9);
                } else {
                    while (true) {
                        i6 = i5 * 2;
                        i7 = i6 + 1;
                        if (i7 >= atomicIntegerFieldUpdater.get(this)) {
                            break;
                        }
                        objArr = this.f4311a;
                        b4.l.b(objArr);
                        i8 = i6 + 2;
                        if (i8 < atomicIntegerFieldUpdater.get(this)) {
                            comparable3 = objArr[i8];
                            b4.l.b(comparable3);
                            obj = objArr[i7];
                            b4.l.b(obj);
                            if (comparable3.compareTo(obj) >= 0) {
                                i8 = i7;
                            }
                        } else {
                            i8 = i7;
                        }
                        comparable = objArr[i5];
                        b4.l.b(comparable);
                        comparable2 = objArr[i8];
                        b4.l.b(comparable2);
                        if (comparable.compareTo(comparable2) <= 0) {
                            break;
                        }
                        d(i5, i8);
                        i5 = i8;
                    }
                }
            } else {
                while (true) {
                    i6 = i5 * 2;
                    i7 = i6 + 1;
                    if (i7 >= atomicIntegerFieldUpdater.get(this)) {
                        break;
                        break;
                    }
                    objArr = this.f4311a;
                    b4.l.b(objArr);
                    i8 = i6 + 2;
                    if (i8 < atomicIntegerFieldUpdater.get(this)) {
                        comparable3 = objArr[i8];
                        b4.l.b(comparable3);
                        obj = objArr[i7];
                        b4.l.b(obj);
                        if (comparable3.compareTo(obj) >= 0) {
                            i8 = i7;
                        }
                    } else {
                        i8 = i7;
                    }
                    comparable = objArr[i5];
                    b4.l.b(comparable);
                    comparable2 = objArr[i8];
                    b4.l.b(comparable2);
                    if (comparable.compareTo(comparable2) <= 0) {
                        break;
                        break;
                    }
                    d(i5, i8);
                    i5 = i8;
                }
            }
        }
        i0 i0Var2 = objArr2[atomicIntegerFieldUpdater.get(this)];
        b4.l.b(i0Var2);
        i0Var2.c(null);
        i0Var2.f2741e = -1;
        objArr2[atomicIntegerFieldUpdater.get(this)] = null;
        return i0Var2;
    }

    public final void c(int i5) {
        while (i5 > 0) {
            i0[] i0VarArr = this.f4311a;
            b4.l.b(i0VarArr);
            int i6 = (i5 - 1) / 2;
            i0 i0Var = i0VarArr[i6];
            b4.l.b(i0Var);
            i0 i0Var2 = i0VarArr[i5];
            b4.l.b(i0Var2);
            if (i0Var.compareTo(i0Var2) <= 0) {
                return;
            }
            d(i5, i6);
            i5 = i6;
        }
    }

    public final void d(int i5, int i6) {
        i0[] i0VarArr = this.f4311a;
        b4.l.b(i0VarArr);
        i0 i0Var = i0VarArr[i6];
        b4.l.b(i0Var);
        i0 i0Var2 = i0VarArr[i5];
        b4.l.b(i0Var2);
        i0VarArr[i5] = i0Var;
        i0VarArr[i6] = i0Var2;
        i0Var.f2741e = i5;
        i0Var2.f2741e = i6;
    }
}
