package c0;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class k2 extends b4.m implements a4.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f976e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f977f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ n0.d f978g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f979h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Object f980i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ Object f981j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ Object f982k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f983l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k2(int i5, n0.d dVar, n0.d dVar2, n0.d dVar3, a4.e eVar, d0.k kVar, n0.d dVar4) {
        super(2);
        this.f977f = i5;
        this.f978g = dVar;
        this.f979h = dVar2;
        this.f980i = dVar3;
        this.f982k = eVar;
        this.f983l = kVar;
        this.f981j = dVar4;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k2(n0.d dVar, n0.d dVar2, a4.e eVar, int i5, r.z0 z0Var, n0.d dVar3, n0.d dVar4) {
        super(2);
        this.f978g = dVar;
        this.f979h = dVar2;
        this.f982k = eVar;
        this.f977f = i5;
        this.f983l = z0Var;
        this.f980i = dVar3;
        this.f981j = dVar4;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k2(r.z0 z0Var, n1.z0 z0Var2, ArrayList arrayList, int i5, ArrayList arrayList2, Integer num, n0.d dVar) {
        super(2);
        this.f979h = z0Var;
        this.f980i = z0Var2;
        this.f981j = arrayList;
        this.f977f = i5;
        this.f982k = arrayList2;
        this.f983l = num;
        this.f978g = dVar;
    }

    /* JADX WARN: Code duplicated, block: B:165:0x03d6  */
    /* JADX WARN: Code duplicated, block: B:167:0x03e4  */
    /* JADX WARN: Code duplicated, block: B:168:0x03ed  */
    /* JADX WARN: Code duplicated, block: B:175:0x040d  */
    /* JADX WARN: Code duplicated, block: B:178:0x041d  */
    /* JADX WARN: Code duplicated, block: B:179:0x0426  */
    /* JADX WARN: Code duplicated, block: B:182:0x0434  */
    /* JADX WARN: Code duplicated, block: B:183:0x043d  */
    /* JADX WARN: Code duplicated, block: B:193:0x0476  */
    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        r.z0 z0Var;
        float fI0;
        float fI1;
        k2.k layoutDirection;
        k2.k kVar;
        float fI2;
        k2.k layoutDirection2;
        float fI3;
        Integer num;
        Object obj3;
        Object obj4;
        Object obj5;
        int i5;
        ArrayList arrayList;
        i0 i0Var;
        Object obj6;
        int i6;
        ArrayList arrayList2;
        Integer numValueOf;
        int i7;
        int iIntValue;
        int iM;
        int iA;
        Object obj7;
        Object obj8;
        int i8;
        int iM2;
        int iM3;
        switch (this.f976e) {
            case 0:
                f0.p pVar = (f0.p) obj;
                if ((((Number) obj2).intValue() & 3) == 2) {
                    f0.v vVar = (f0.v) pVar;
                    if (vVar.z()) {
                        vVar.Q();
                    } else {
                        o2.b(this.f977f, this.f978g, (n0.d) this.f979h, (n0.d) this.f980i, (a4.e) this.f982k, (d0.k) this.f983l, (n0.d) this.f981j, pVar, 0);
                    }
                } else {
                    o2.b(this.f977f, this.f978g, (n0.d) this.f979h, (n0.d) this.f980i, (a4.e) this.f982k, (d0.k) this.f983l, (n0.d) this.f981j, pVar, 0);
                }
                return p3.n.f5026a;
            case 1:
                f0.p pVar2 = (f0.p) obj;
                int iIntValue2 = ((Number) obj2).intValue();
                n1.z0 z0Var2 = (n1.z0) this.f980i;
                if ((iIntValue2 & 3) == 2) {
                    f0.v vVar2 = (f0.v) pVar2;
                    if (vVar2.z()) {
                        vVar2.Q();
                    } else {
                        z0Var = (r.z0) this.f979h;
                        if (((ArrayList) this.f981j).isEmpty()) {
                            fI0 = z0Var2.i0(z0Var.b(z0Var2));
                        } else {
                            fI0 = z0Var2.i0(this.f977f);
                        }
                        if (!((ArrayList) this.f982k).isEmpty() || (num = (Integer) this.f983l) == null) {
                            fI1 = z0Var2.i0(z0Var.a(z0Var2));
                        } else {
                            fI1 = z0Var2.i0(num.intValue());
                        }
                        layoutDirection = z0Var2.getLayoutDirection();
                        kVar = k2.k.f3416d;
                        if (layoutDirection == kVar) {
                            fI2 = z0Var2.i0(z0Var.d(z0Var2, layoutDirection));
                        } else {
                            fI2 = z0Var2.i0(z0Var.c(z0Var2, layoutDirection));
                        }
                        layoutDirection2 = z0Var2.getLayoutDirection();
                        if (layoutDirection2 == kVar) {
                            fI3 = z0Var2.i0(z0Var.c(z0Var2, layoutDirection2));
                        } else {
                            fI3 = z0Var2.i0(z0Var.d(z0Var2, layoutDirection2));
                        }
                        this.f978g.b(new r.m0(fI2, fI0, fI3, fI1), pVar2, 0);
                    }
                } else {
                    z0Var = (r.z0) this.f979h;
                    if (((ArrayList) this.f981j).isEmpty()) {
                        fI0 = z0Var2.i0(z0Var.b(z0Var2));
                    } else {
                        fI0 = z0Var2.i0(this.f977f);
                    }
                    if (((ArrayList) this.f982k).isEmpty()) {
                        fI1 = z0Var2.i0(z0Var.a(z0Var2));
                    } else {
                        fI1 = z0Var2.i0(z0Var.a(z0Var2));
                    }
                    layoutDirection = z0Var2.getLayoutDirection();
                    kVar = k2.k.f3416d;
                    if (layoutDirection == kVar) {
                        fI2 = z0Var2.i0(z0Var.d(z0Var2, layoutDirection));
                    } else {
                        fI2 = z0Var2.i0(z0Var.c(z0Var2, layoutDirection));
                    }
                    layoutDirection2 = z0Var2.getLayoutDirection();
                    if (layoutDirection2 == kVar) {
                        fI3 = z0Var2.i0(z0Var.c(z0Var2, layoutDirection2));
                    } else {
                        fI3 = z0Var2.i0(z0Var.d(z0Var2, layoutDirection2));
                    }
                    this.f978g.b(new r.m0(fI2, fI0, fI3, fI1), pVar2, 0);
                }
                return p3.n.f5026a;
            default:
                n1.z0 z0Var3 = (n1.z0) obj;
                long j5 = ((k2.a) obj2).f3400a;
                r.z0 z0Var4 = (r.z0) this.f983l;
                int i9 = k2.a.i(j5);
                int iH = k2.a.h(j5);
                long jB = k2.a.b(j5, 0, 0, 0, 0, 10);
                List listH = z0Var3.H(this.f978g, p2.f1155d);
                ArrayList arrayList3 = new ArrayList(listH.size());
                int size = listH.size();
                for (int i10 = 0; i10 < size; i10++) {
                    arrayList3.add(((n1.e0) listH.get(i10)).c(jB));
                }
                if (arrayList3.isEmpty()) {
                    obj3 = null;
                } else {
                    obj3 = arrayList3.get(0);
                    int i11 = ((n1.m0) obj3).f4131e;
                    int iZ = p3.j.z(arrayList3);
                    if (1 <= iZ) {
                        int i12 = 1;
                        while (true) {
                            Object obj9 = arrayList3.get(i12);
                            int i13 = ((n1.m0) obj9).f4131e;
                            if (i11 < i13) {
                                i11 = i13;
                                obj3 = obj9;
                            }
                            if (i12 != iZ) {
                                i12++;
                            }
                        }
                    }
                }
                n1.m0 m0Var = (n1.m0) obj3;
                int i14 = m0Var != null ? m0Var.f4131e : 0;
                List listH2 = z0Var3.H((n0.d) this.f979h, p2.f1157f);
                ArrayList arrayList4 = new ArrayList(listH2.size());
                int size2 = listH2.size();
                int i15 = 0;
                while (i15 < size2) {
                    arrayList4.add(((n1.e0) listH2.get(i15)).c(h1.c.G((-z0Var4.d(z0Var3, z0Var3.getLayoutDirection())) - z0Var4.c(z0Var3, z0Var3.getLayoutDirection()), -z0Var4.a(z0Var3), jB)));
                    i15++;
                    listH2 = listH2;
                }
                if (arrayList4.isEmpty()) {
                    obj4 = null;
                } else {
                    obj4 = arrayList4.get(0);
                    int i16 = ((n1.m0) obj4).f4131e;
                    int iZ2 = p3.j.z(arrayList4);
                    if (1 <= iZ2) {
                        Object obj10 = obj4;
                        int i17 = i16;
                        int i18 = 1;
                        while (true) {
                            Object obj11 = arrayList4.get(i18);
                            int i19 = ((n1.m0) obj11).f4131e;
                            if (i17 < i19) {
                                obj10 = obj11;
                                i17 = i19;
                            }
                            if (i18 != iZ2) {
                                i18++;
                            } else {
                                obj4 = obj10;
                            }
                        }
                    }
                }
                n1.m0 m0Var2 = (n1.m0) obj4;
                int i20 = m0Var2 != null ? m0Var2.f4131e : 0;
                if (arrayList4.isEmpty()) {
                    i5 = i20;
                    obj5 = null;
                } else {
                    obj5 = arrayList4.get(0);
                    int i21 = ((n1.m0) obj5).f4130d;
                    int iZ3 = p3.j.z(arrayList4);
                    if (1 <= iZ3) {
                        Object obj12 = obj5;
                        int i22 = i21;
                        int i23 = 1;
                        while (true) {
                            Object obj13 = arrayList4.get(i23);
                            i5 = i20;
                            int i24 = ((n1.m0) obj13).f4130d;
                            if (i22 < i24) {
                                i22 = i24;
                                obj12 = obj13;
                            }
                            if (i23 != iZ3) {
                                i23++;
                                i20 = i5;
                            } else {
                                obj5 = obj12;
                            }
                        }
                    } else {
                        i5 = i20;
                    }
                }
                n1.m0 m0Var3 = (n1.m0) obj5;
                int i25 = m0Var3 != null ? m0Var3.f4130d : 0;
                List listH3 = z0Var3.H((a4.e) this.f982k, p2.f1158g);
                ArrayList arrayList5 = new ArrayList(listH3.size());
                int size3 = listH3.size();
                int i26 = 0;
                while (i26 < size3) {
                    List list = listH3;
                    ArrayList arrayList6 = arrayList4;
                    n1.m0 m0VarC = ((n1.e0) listH3.get(i26)).c(h1.c.G((-z0Var4.d(z0Var3, z0Var3.getLayoutDirection())) - z0Var4.c(z0Var3, z0Var3.getLayoutDirection()), -z0Var4.a(z0Var3), jB));
                    if (m0VarC.f4131e == 0 || m0VarC.f4130d == 0) {
                        m0VarC = null;
                    }
                    if (m0VarC != null) {
                        arrayList5.add(m0VarC);
                    }
                    i26++;
                    listH3 = list;
                    arrayList4 = arrayList6;
                }
                ArrayList arrayList7 = arrayList4;
                boolean zIsEmpty = arrayList5.isEmpty();
                int i27 = this.f977f;
                if (zIsEmpty) {
                    arrayList = arrayList3;
                    i0Var = null;
                } else {
                    if (arrayList5.isEmpty()) {
                        arrayList = arrayList3;
                        obj7 = null;
                    } else {
                        obj7 = arrayList5.get(0);
                        int i28 = ((n1.m0) obj7).f4130d;
                        int iZ4 = p3.j.z(arrayList5);
                        if (1 <= iZ4) {
                            int i29 = i28;
                            int i30 = 1;
                            while (true) {
                                Object obj14 = arrayList5.get(i30);
                                arrayList = arrayList3;
                                int i31 = ((n1.m0) obj14).f4130d;
                                if (i29 < i31) {
                                    i29 = i31;
                                    obj7 = obj14;
                                }
                                if (i30 != iZ4) {
                                    i30++;
                                    arrayList3 = arrayList;
                                }
                            }
                        } else {
                            arrayList = arrayList3;
                        }
                    }
                    b4.l.b(obj7);
                    int i32 = ((n1.m0) obj7).f4130d;
                    if (arrayList5.isEmpty()) {
                        i8 = i32;
                        obj8 = null;
                    } else {
                        obj8 = arrayList5.get(0);
                        int i33 = ((n1.m0) obj8).f4131e;
                        int iZ5 = p3.j.z(arrayList5);
                        if (1 <= iZ5) {
                            Object obj15 = obj8;
                            int i34 = i33;
                            int i35 = 1;
                            while (true) {
                                Object obj16 = arrayList5.get(i35);
                                i8 = i32;
                                int i36 = ((n1.m0) obj16).f4131e;
                                if (i34 < i36) {
                                    i34 = i36;
                                    obj15 = obj16;
                                }
                                if (i35 != iZ5) {
                                    i35++;
                                    i32 = i8;
                                } else {
                                    obj8 = obj15;
                                }
                            }
                        } else {
                            i8 = i32;
                        }
                    }
                    b4.l.b(obj8);
                    int i37 = ((n1.m0) obj8).f4131e;
                    k2.k kVar2 = k2.k.f3416d;
                    if (i27 == 0) {
                        if (z0Var3.getLayoutDirection() == kVar2) {
                            iM2 = z0Var3.M(o2.f1133a);
                        } else {
                            iM3 = z0Var3.M(o2.f1133a);
                            iM2 = (i9 - iM3) - i8;
                        }
                    } else if (i27 != 2 && i27 != 3) {
                        iM2 = (i9 - i8) / 2;
                    } else if (z0Var3.getLayoutDirection() == kVar2) {
                        iM3 = z0Var3.M(o2.f1133a);
                        iM2 = (i9 - iM3) - i8;
                    } else {
                        iM2 = z0Var3.M(o2.f1133a);
                    }
                    i0Var = new i0(iM2, i37);
                }
                List listH4 = z0Var3.H(new n0.d(-2146438447, true, new q((n0.d) this.f980i, 2)), p2.f1159h);
                ArrayList arrayList8 = new ArrayList(listH4.size());
                int size4 = listH4.size();
                int i38 = 0;
                while (i38 < size4) {
                    arrayList8.add(((n1.e0) listH4.get(i38)).c(jB));
                    i38++;
                    listH4 = listH4;
                }
                if (arrayList8.isEmpty()) {
                    arrayList2 = arrayList8;
                    i6 = i14;
                    obj6 = null;
                } else {
                    obj6 = arrayList8.get(0);
                    int i39 = ((n1.m0) obj6).f4131e;
                    int iZ6 = p3.j.z(arrayList8);
                    i6 = i14;
                    if (1 <= iZ6) {
                        int i40 = i39;
                        int i41 = 1;
                        while (true) {
                            Object obj17 = arrayList8.get(i41);
                            arrayList2 = arrayList8;
                            int i42 = ((n1.m0) obj17).f4131e;
                            if (i40 < i42) {
                                i40 = i42;
                                obj6 = obj17;
                            }
                            if (i41 != iZ6) {
                                i41++;
                                arrayList8 = arrayList2;
                            }
                        }
                    } else {
                        arrayList2 = arrayList8;
                    }
                }
                n1.m0 m0Var4 = (n1.m0) obj6;
                Integer numValueOf2 = m0Var4 != null ? Integer.valueOf(m0Var4.f4131e) : null;
                if (i0Var != null) {
                    int i43 = i0Var.f918b;
                    if (numValueOf2 == null || i27 == 3) {
                        iM = z0Var3.M(o2.f1133a) + i43;
                        iA = z0Var4.a(z0Var3);
                    } else {
                        iM = numValueOf2.intValue() + i43;
                        iA = z0Var3.M(o2.f1133a);
                    }
                    numValueOf = Integer.valueOf(iA + iM);
                } else {
                    numValueOf = null;
                }
                if (i5 != 0) {
                    iIntValue = i5 + (numValueOf != null ? numValueOf.intValue() : numValueOf2 != null ? numValueOf2.intValue() : z0Var4.a(z0Var3));
                    i7 = i9;
                } else {
                    i7 = i9;
                    iIntValue = 0;
                }
                i0 i0Var2 = i0Var;
                ArrayList arrayList9 = arrayList;
                ArrayList arrayList10 = arrayList2;
                List listH5 = z0Var3.H(new n0.d(-1213360416, true, new k2((r.z0) this.f983l, z0Var3, arrayList9, i6, arrayList10, numValueOf2, (n0.d) this.f981j)), p2.f1156e);
                ArrayList arrayList11 = new ArrayList(listH5.size());
                int size5 = listH5.size();
                int i44 = 0;
                while (i44 < size5) {
                    arrayList11.add(((n1.e0) listH5.get(i44)).c(jB));
                    i44++;
                    i7 = i7;
                }
                int i45 = i7;
                return z0Var3.F(i45, iH, q3.t.f5478d, new m2(arrayList11, arrayList9, arrayList7, arrayList10, i0Var2, i45, i25, (r.z0) this.f983l, z0Var3, iH, iIntValue, numValueOf2, arrayList5, numValueOf));
        }
    }
}
