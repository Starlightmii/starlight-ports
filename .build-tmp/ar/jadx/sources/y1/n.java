package y1;

import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n extends b4.m implements a4.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f6797e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ f3.a f6798f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n(f3.a aVar, int i5) {
        super(0);
        this.f6797e = i5;
        this.f6798f = aVar;
    }

    @Override // a4.a
    public final Object a() {
        Object obj;
        Object obj2;
        switch (this.f6797e) {
            case 0:
                ArrayList arrayList = (ArrayList) this.f6798f.f2453e;
                if (arrayList.isEmpty()) {
                    obj = null;
                } else {
                    Object obj3 = arrayList.get(0);
                    float fB = ((p) obj3).f6806a.f2524i.b();
                    int iZ = p3.j.z(arrayList);
                    int i5 = 1;
                    if (1 <= iZ) {
                        while (true) {
                            Object obj4 = arrayList.get(i5);
                            float fB2 = ((p) obj4).f6806a.f2524i.b();
                            if (Float.compare(fB, fB2) < 0) {
                                obj3 = obj4;
                                fB = fB2;
                            }
                            if (i5 != iZ) {
                                i5++;
                            }
                        }
                    }
                    obj = obj3;
                }
                p pVar = (p) obj;
                return Float.valueOf(pVar != null ? pVar.f6806a.f2524i.b() : 0.0f);
            default:
                ArrayList arrayList2 = (ArrayList) this.f6798f.f2453e;
                if (arrayList2.isEmpty()) {
                    obj2 = null;
                } else {
                    Object obj5 = arrayList2.get(0);
                    float fA = ((p) obj5).f6806a.a();
                    int iZ2 = p3.j.z(arrayList2);
                    int i6 = 1;
                    if (1 <= iZ2) {
                        while (true) {
                            Object obj6 = arrayList2.get(i6);
                            float fA2 = ((p) obj6).f6806a.a();
                            if (Float.compare(fA, fA2) < 0) {
                                obj5 = obj6;
                                fA = fA2;
                            }
                            if (i6 != iZ2) {
                                i6++;
                            }
                        }
                    }
                    obj2 = obj5;
                }
                p pVar2 = (p) obj2;
                return Float.valueOf(pVar2 != null ? pVar2.f6806a.a() : 0.0f);
        }
    }
}
