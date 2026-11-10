package o0;

import a2.h;
import b4.l;
import f0.k;
import f0.k1;
import f0.o;
import f0.p;
import f0.v;
import java.util.Arrays;
import p3.n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h f4367a = new h(new k(27), 15, new k1(3));

    public static final String a(Object obj) {
        return obj + " cannot be saved using the current SaveableStateRegistry. The default implementation only supports types which can be stored inside the Bundle. Please consider implementing a custom Saver for this class and pass it to rememberSaveable().";
    }

    public static final Object b(Object[] objArr, f fVar, a4.a aVar, p pVar, int i5, int i6) {
        Object[] objArr2;
        final Object obj;
        Object objB;
        if ((i6 & 2) != 0) {
            fVar = f4367a;
        }
        final f fVar2 = fVar;
        v vVar = (v) pVar;
        long j5 = vVar.S;
        h1.c.m(36);
        final String string = Long.toString(j5, 36);
        l.d("toString(...)", string);
        l.c("null cannot be cast to non-null type androidx.compose.runtime.saveable.Saver<T of androidx.compose.runtime.saveable.RememberSaveableKt.rememberSaveable, kotlin.Any>", fVar2);
        final c cVar = (c) vVar.j(e.f4366a);
        Object objK = vVar.K();
        f0.h hVar = o.f2281a;
        if (objK == hVar) {
            Object objI = (cVar == null || (objB = cVar.b(string)) == null) ? null : fVar2.i(objB);
            if (objI == null) {
                objI = aVar.a();
            }
            objArr2 = objArr;
            b bVar = new b(fVar2, cVar, string, objI, objArr2);
            vVar.f0(bVar);
            objK = bVar;
        } else {
            objArr2 = objArr;
        }
        final b bVar2 = (b) objK;
        Object objA = Arrays.equals(objArr2, bVar2.f4361h) ? bVar2.f4360g : null;
        if (objA == null) {
            objA = aVar.a();
        }
        boolean zH = vVar.h(bVar2) | vVar.h(fVar2) | vVar.h(cVar) | vVar.f(string) | vVar.h(objA) | vVar.h(objArr2);
        Object objK2 = vVar.K();
        if (zH || objK2 == hVar) {
            final Object[] objArr3 = objArr2;
            obj = objA;
            a4.a aVar2 = new a4.a() { // from class: o0.a
                @Override // a4.a
                public final Object a() {
                    boolean z2;
                    b bVar3 = bVar2;
                    c cVar2 = bVar3.f4358e;
                    c cVar3 = cVar;
                    boolean z3 = true;
                    if (cVar2 != cVar3) {
                        bVar3.f4358e = cVar3;
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    String str = bVar3.f4359f;
                    String str2 = string;
                    if (l.a(str, str2)) {
                        z3 = z2;
                    } else {
                        bVar3.f4359f = str2;
                    }
                    bVar3.f4357d = fVar2;
                    bVar3.f4360g = obj;
                    bVar3.f4361h = objArr3;
                    a1.b bVar4 = bVar3.f4362i;
                    if (bVar4 != null && z3) {
                        bVar4.E();
                        bVar3.f4362i = null;
                        bVar3.a();
                    }
                    return n.f5026a;
                }
            };
            vVar.f0(aVar2);
            objK2 = aVar2;
        } else {
            obj = objA;
        }
        f0.l.g((a4.a) objK2, vVar);
        return obj;
    }
}
