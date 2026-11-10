package c0;

import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f2 implements n1.f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a4.c f847a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f848b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f849c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final r.l0 f850d;

    public f2(a4.c cVar, boolean z2, float f5, r.l0 l0Var) {
        this.f847a = cVar;
        this.f848b = z2;
        this.f849c = f5;
        this.f850d = l0Var;
    }

    public final int a(n1.m mVar, List list, int i5, a4.e eVar) {
        Object obj;
        int iC0;
        int iIntValue;
        Object obj2;
        int iIntValue2;
        Object obj3;
        Object obj4;
        int iIntValue3;
        Object obj5;
        int i6;
        Object obj6;
        Object obj7;
        int size = list.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size) {
                obj = null;
                break;
            }
            obj = list.get(i7);
            if (b4.l.a(d0.u.d((n1.e0) obj), "Leading")) {
                break;
            }
            i7++;
        }
        n1.e0 e0Var = (n1.e0) obj;
        if (e0Var != null) {
            iC0 = i5 == Integer.MAX_VALUE ? i5 : i5 - e0Var.c0(Integer.MAX_VALUE);
            iIntValue = ((Number) eVar.invoke(e0Var, Integer.valueOf(i5))).intValue();
        } else {
            iC0 = i5;
            iIntValue = 0;
        }
        int size2 = list.size();
        int i8 = 0;
        while (true) {
            if (i8 >= size2) {
                obj2 = null;
                break;
            }
            obj2 = list.get(i8);
            if (b4.l.a(d0.u.d((n1.e0) obj2), "Trailing")) {
                break;
            }
            i8++;
        }
        n1.e0 e0Var2 = (n1.e0) obj2;
        if (e0Var2 != null) {
            int iC1 = e0Var2.c0(Integer.MAX_VALUE);
            if (iC0 != Integer.MAX_VALUE) {
                iC0 -= iC1;
            }
            iIntValue2 = ((Number) eVar.invoke(e0Var2, Integer.valueOf(i5))).intValue();
        } else {
            iIntValue2 = 0;
        }
        int size3 = list.size();
        int i9 = 0;
        while (true) {
            if (i9 >= size3) {
                obj3 = null;
                break;
            }
            obj3 = list.get(i9);
            if (b4.l.a(d0.u.d((n1.e0) obj3), "Label")) {
                break;
            }
            i9++;
        }
        Object obj8 = (n1.e0) obj3;
        int iIntValue4 = obj8 != null ? ((Number) eVar.invoke(obj8, Integer.valueOf(a.a.E(this.f849c, iC0, i5)))).intValue() : 0;
        int size4 = list.size();
        int i10 = 0;
        while (true) {
            if (i10 >= size4) {
                obj4 = null;
                break;
            }
            obj4 = list.get(i10);
            if (b4.l.a(d0.u.d((n1.e0) obj4), "Prefix")) {
                break;
            }
            i10++;
        }
        n1.e0 e0Var3 = (n1.e0) obj4;
        if (e0Var3 != null) {
            iIntValue3 = ((Number) eVar.invoke(e0Var3, Integer.valueOf(iC0))).intValue();
            int iC2 = e0Var3.c0(Integer.MAX_VALUE);
            if (iC0 != Integer.MAX_VALUE) {
                iC0 -= iC2;
            }
        } else {
            iIntValue3 = 0;
        }
        int size5 = list.size();
        int i11 = 0;
        while (true) {
            if (i11 >= size5) {
                obj5 = null;
                break;
            }
            obj5 = list.get(i11);
            if (b4.l.a(d0.u.d((n1.e0) obj5), "Suffix")) {
                break;
            }
            i11++;
        }
        n1.e0 e0Var4 = (n1.e0) obj5;
        if (e0Var4 != null) {
            int iIntValue5 = ((Number) eVar.invoke(e0Var4, Integer.valueOf(iC0))).intValue();
            int iC3 = e0Var4.c0(Integer.MAX_VALUE);
            if (iC0 != Integer.MAX_VALUE) {
                iC0 -= iC3;
            }
            i6 = iIntValue5;
        } else {
            i6 = 0;
        }
        int size6 = list.size();
        for (int i12 = 0; i12 < size6; i12++) {
            Object obj9 = list.get(i12);
            if (b4.l.a(d0.u.d((n1.e0) obj9), "TextField")) {
                int iIntValue6 = ((Number) eVar.invoke(obj9, Integer.valueOf(iC0))).intValue();
                int size7 = list.size();
                int i13 = 0;
                while (true) {
                    if (i13 >= size7) {
                        obj6 = null;
                        break;
                    }
                    obj6 = list.get(i13);
                    if (b4.l.a(d0.u.d((n1.e0) obj6), "Hint")) {
                        break;
                    }
                    i13++;
                }
                Object obj10 = (n1.e0) obj6;
                int iIntValue7 = obj10 != null ? ((Number) eVar.invoke(obj10, Integer.valueOf(iC0))).intValue() : 0;
                int size8 = list.size();
                int i14 = 0;
                while (true) {
                    if (i14 >= size8) {
                        obj7 = null;
                        break;
                    }
                    Object obj11 = list.get(i14);
                    if (b4.l.a(d0.u.d((n1.e0) obj11), "Supporting")) {
                        obj7 = obj11;
                        break;
                    }
                    i14++;
                }
                Object obj12 = (n1.e0) obj7;
                return d2.c(iIntValue, iIntValue2, iIntValue3, i6, iIntValue6, iIntValue4, iIntValue7, obj12 != null ? ((Number) eVar.invoke(obj12, Integer.valueOf(i5))).intValue() : 0, this.f849c, d0.u.f1579a, mVar.a(), this.f850d);
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Override // n1.f0
    public final int b(n1.m mVar, List list, int i5) {
        return c(mVar, list, i5, w.f1389n);
    }

    public final int c(n1.m mVar, List list, int i5, a4.e eVar) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        int size = list.size();
        for (int i6 = 0; i6 < size; i6++) {
            Object obj7 = list.get(i6);
            if (b4.l.a(d0.u.d((n1.e0) obj7), "TextField")) {
                int iIntValue = ((Number) eVar.invoke(obj7, Integer.valueOf(i5))).intValue();
                int size2 = list.size();
                int i7 = 0;
                while (true) {
                    obj = null;
                    if (i7 >= size2) {
                        obj2 = null;
                        break;
                    }
                    obj2 = list.get(i7);
                    if (b4.l.a(d0.u.d((n1.e0) obj2), "Label")) {
                        break;
                    }
                    i7++;
                }
                n1.e0 e0Var = (n1.e0) obj2;
                int iIntValue2 = e0Var != null ? ((Number) eVar.invoke(e0Var, Integer.valueOf(i5))).intValue() : 0;
                int size3 = list.size();
                int i8 = 0;
                while (true) {
                    if (i8 >= size3) {
                        obj3 = null;
                        break;
                    }
                    obj3 = list.get(i8);
                    if (b4.l.a(d0.u.d((n1.e0) obj3), "Trailing")) {
                        break;
                    }
                    i8++;
                }
                n1.e0 e0Var2 = (n1.e0) obj3;
                int iIntValue3 = e0Var2 != null ? ((Number) eVar.invoke(e0Var2, Integer.valueOf(i5))).intValue() : 0;
                int size4 = list.size();
                int i9 = 0;
                while (true) {
                    if (i9 >= size4) {
                        obj4 = null;
                        break;
                    }
                    obj4 = list.get(i9);
                    if (b4.l.a(d0.u.d((n1.e0) obj4), "Leading")) {
                        break;
                    }
                    i9++;
                }
                n1.e0 e0Var3 = (n1.e0) obj4;
                int iIntValue4 = e0Var3 != null ? ((Number) eVar.invoke(e0Var3, Integer.valueOf(i5))).intValue() : 0;
                int size5 = list.size();
                int i10 = 0;
                while (true) {
                    if (i10 >= size5) {
                        obj5 = null;
                        break;
                    }
                    obj5 = list.get(i10);
                    if (b4.l.a(d0.u.d((n1.e0) obj5), "Prefix")) {
                        break;
                    }
                    i10++;
                }
                n1.e0 e0Var4 = (n1.e0) obj5;
                int iIntValue5 = e0Var4 != null ? ((Number) eVar.invoke(e0Var4, Integer.valueOf(i5))).intValue() : 0;
                int size6 = list.size();
                int i11 = 0;
                while (true) {
                    if (i11 >= size6) {
                        obj6 = null;
                        break;
                    }
                    obj6 = list.get(i11);
                    if (b4.l.a(d0.u.d((n1.e0) obj6), "Suffix")) {
                        break;
                    }
                    i11++;
                }
                n1.e0 e0Var5 = (n1.e0) obj6;
                int iIntValue6 = e0Var5 != null ? ((Number) eVar.invoke(e0Var5, Integer.valueOf(i5))).intValue() : 0;
                int size7 = list.size();
                for (int i12 = 0; i12 < size7; i12++) {
                    Object obj8 = list.get(i12);
                    if (b4.l.a(d0.u.d((n1.e0) obj8), "Hint")) {
                        obj = obj8;
                        break;
                    }
                }
                n1.e0 e0Var6 = (n1.e0) obj;
                return d2.d(iIntValue4, iIntValue3, iIntValue5, iIntValue6, iIntValue, iIntValue2, e0Var6 != null ? ((Number) eVar.invoke(e0Var6, Integer.valueOf(i5))).intValue() : 0, this.f849c, d0.u.f1579a, mVar.a(), this.f850d);
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Override // n1.f0
    public final int d(n1.m mVar, List list, int i5) {
        return c(mVar, list, i5, w.f1387l);
    }

    @Override // n1.f0
    public final n1.g0 h(n1.h0 h0Var, List list, long j5) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        int i5;
        Object obj5;
        Object obj6;
        Object obj7;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        f2 f2Var = this;
        List list2 = list;
        r.l0 l0Var = f2Var.f850d;
        int iM = h0Var.M(l0Var.a());
        long jB = k2.a.b(j5, 0, 0, 0, 0, 10);
        int size = list2.size();
        int i13 = 0;
        while (true) {
            if (i13 >= size) {
                obj = null;
                break;
            }
            obj = list2.get(i13);
            if (b4.l.a(androidx.compose.ui.layout.a.a((n1.e0) obj), "Leading")) {
                break;
            }
            i13++;
        }
        n1.e0 e0Var = (n1.e0) obj;
        n1.m0 m0VarC = e0Var != null ? e0Var.c(jB) : null;
        float f5 = d0.u.f1580b;
        int i14 = m0VarC != null ? m0VarC.f4130d : 0;
        int iMax = Math.max(0, m0VarC != null ? m0VarC.f4131e : 0);
        int size2 = list2.size();
        int i15 = 0;
        while (true) {
            if (i15 >= size2) {
                obj2 = null;
                break;
            }
            obj2 = list2.get(i15);
            if (b4.l.a(androidx.compose.ui.layout.a.a((n1.e0) obj2), "Trailing")) {
                break;
            }
            i15++;
        }
        n1.e0 e0Var2 = (n1.e0) obj2;
        n1.m0 m0VarC2 = e0Var2 != null ? e0Var2.c(h1.c.H(jB, -i14, 0, 2)) : null;
        int i16 = i14 + (m0VarC2 != null ? m0VarC2.f4130d : 0);
        int iMax2 = Math.max(iMax, m0VarC2 != null ? m0VarC2.f4131e : 0);
        int size3 = list2.size();
        int i17 = 0;
        while (true) {
            if (i17 >= size3) {
                obj3 = null;
                break;
            }
            obj3 = list2.get(i17);
            if (b4.l.a(androidx.compose.ui.layout.a.a((n1.e0) obj3), "Prefix")) {
                break;
            }
            i17++;
        }
        n1.e0 e0Var3 = (n1.e0) obj3;
        n1.m0 m0VarC3 = e0Var3 != null ? e0Var3.c(h1.c.H(jB, -i16, 0, 2)) : null;
        int i18 = i16 + (m0VarC3 != null ? m0VarC3.f4130d : 0);
        int iMax3 = Math.max(iMax2, m0VarC3 != null ? m0VarC3.f4131e : 0);
        int size4 = list2.size();
        int i19 = 0;
        while (true) {
            if (i19 >= size4) {
                obj4 = null;
                break;
            }
            obj4 = list2.get(i19);
            if (b4.l.a(androidx.compose.ui.layout.a.a((n1.e0) obj4), "Suffix")) {
                break;
            }
            i19++;
        }
        n1.e0 e0Var4 = (n1.e0) obj4;
        n1.m0 m0VarC4 = e0Var4 != null ? e0Var4.c(h1.c.H(jB, -i18, 0, 2)) : null;
        int i20 = i18 + (m0VarC4 != null ? m0VarC4.f4130d : 0);
        int iMax4 = Math.max(iMax3, m0VarC4 != null ? m0VarC4.f4131e : 0);
        int iM2 = h0Var.M(l0Var.d(h0Var.getLayoutDirection())) + h0Var.M(l0Var.c(h0Var.getLayoutDirection()));
        int i21 = -i20;
        int iE = a.a.E(f2Var.f849c, i21 - iM2, -iM2);
        int i22 = -iM;
        long jG = h1.c.G(iE, i22, jB);
        int size5 = list2.size();
        int i23 = 0;
        while (true) {
            if (i23 >= size5) {
                i5 = iM;
                obj5 = null;
                break;
            }
            obj5 = list2.get(i23);
            int i24 = i23;
            i5 = iM;
            if (b4.l.a(androidx.compose.ui.layout.a.a((n1.e0) obj5), "Label")) {
                break;
            }
            i23 = i24 + 1;
            iM = i5;
        }
        n1.e0 e0Var5 = (n1.e0) obj5;
        n1.m0 m0VarC5 = e0Var5 != null ? e0Var5.c(jG) : null;
        f2Var.f847a.c(new x0.f(m0VarC5 != null ? p3.j.f(m0VarC5.f4130d, m0VarC5.f4131e) : 0L));
        int size6 = list2.size();
        int i25 = 0;
        while (true) {
            if (i25 >= size6) {
                obj6 = null;
                break;
            }
            obj6 = list2.get(i25);
            int i26 = size6;
            if (b4.l.a(androidx.compose.ui.layout.a.a((n1.e0) obj6), "Supporting")) {
                break;
            }
            i25++;
            size6 = i26;
        }
        n1.e0 e0Var6 = (n1.e0) obj6;
        int iE0 = e0Var6 != null ? e0Var6.e0(k2.a.k(j5)) : 0;
        int iMax5 = Math.max((m0VarC5 != null ? m0VarC5.f4131e : 0) / 2, h0Var.M(l0Var.b()));
        long jB2 = k2.a.b(h1.c.G(i21, (i22 - iMax5) - iE0, j5), 0, 0, 0, 0, 11);
        int size7 = list2.size();
        int i27 = 0;
        while (i27 < size7) {
            n1.e0 e0Var7 = (n1.e0) list2.get(i27);
            int i28 = i27;
            int i29 = size7;
            if (b4.l.a(androidx.compose.ui.layout.a.a(e0Var7), "TextField")) {
                n1.m0 m0VarC6 = e0Var7.c(jB2);
                long jB3 = k2.a.b(jB2, 0, 0, 0, 0, 14);
                int size8 = list2.size();
                int i30 = 0;
                while (true) {
                    if (i30 >= size8) {
                        obj7 = null;
                        break;
                    }
                    obj7 = list2.get(i30);
                    int i31 = size8;
                    int i32 = i30;
                    if (b4.l.a(androidx.compose.ui.layout.a.a((n1.e0) obj7), "Hint")) {
                        break;
                    }
                    i30 = i32 + 1;
                    size8 = i31;
                }
                n1.e0 e0Var8 = (n1.e0) obj7;
                n1.m0 m0VarC7 = e0Var8 != null ? e0Var8.c(jB3) : null;
                int iMax6 = Math.max(iMax4, Math.max(m0VarC6.f4131e, m0VarC7 != null ? m0VarC7.f4131e : 0) + iMax5 + i5);
                n1.m0 m0Var = m0VarC;
                int i33 = m0VarC != null ? m0Var.f4130d : 0;
                int i34 = m0VarC2 != null ? m0VarC2.f4130d : 0;
                if (m0VarC3 != null) {
                    i6 = m0VarC3.f4130d;
                    i33 = i33;
                } else {
                    i6 = 0;
                }
                if (m0VarC4 != null) {
                    i7 = i33;
                    i8 = m0VarC4.f4130d;
                } else {
                    i7 = i33;
                    i8 = 0;
                }
                int i35 = m0VarC6.f4130d;
                if (m0VarC5 != null) {
                    i11 = m0VarC5.f4130d;
                    i10 = i7;
                    i9 = i35;
                } else {
                    int i36 = i7;
                    i9 = i35;
                    i10 = i36;
                    i11 = 0;
                }
                if (m0VarC7 != null) {
                    i12 = m0VarC7.f4130d;
                    i10 = i10;
                } else {
                    i12 = 0;
                }
                int iD = d2.d(i10, i34, i6, i8, i9, i11, i12, f2Var.f849c, j5, h0Var.a(), f2Var.f850d);
                int i37 = 0;
                int i38 = iD;
                n1.m0 m0VarC8 = e0Var6 != null ? e0Var6.c(k2.a.b(h1.c.H(jB, 0, -iMax6, 1), 0, iD, 0, 0, 9)) : null;
                int i39 = m0VarC8 != null ? m0VarC8.f4131e : 0;
                int i40 = m0Var != null ? m0Var.f4131e : 0;
                if (m0VarC2 != null) {
                    i37 = m0VarC2.f4131e;
                }
                n1.m0 m0Var2 = m0VarC3;
                n1.m0 m0Var3 = m0VarC5;
                int iC = d2.c(i40, i37, m0VarC3 != null ? m0VarC3.f4131e : i37, m0VarC4 != null ? m0VarC4.f4131e : i37, m0VarC6.f4131e, m0VarC5 != null ? m0VarC5.f4131e : i37, m0VarC7 != null ? m0VarC7.f4131e : i37, m0VarC8 != null ? m0VarC8.f4131e : i37, f2Var.f849c, j5, h0Var.a(), f2Var.f850d);
                int i41 = iC - i39;
                int size9 = list2.size();
                int i42 = i37;
                while (i42 < size9) {
                    n1.e0 e0Var9 = (n1.e0) list2.get(i42);
                    if (b4.l.a(androidx.compose.ui.layout.a.a(e0Var9), "Container")) {
                        n1.m0 m0VarC9 = e0Var9.c(h1.c.b(i38 != Integer.MAX_VALUE ? i38 : i37, i38, i41 != Integer.MAX_VALUE ? i41 : 0, i41));
                        n1.m0 m0Var4 = m0VarC6;
                        n1.m0 m0Var5 = m0VarC8;
                        int i43 = i38;
                        return h0Var.F(i43, iC, q3.t.f5478d, new e2(iC, i43, m0Var, m0VarC2, m0Var2, m0VarC4, m0Var4, m0Var3, m0VarC7, m0VarC9, m0Var5, f2Var, h0Var));
                    }
                    i42++;
                    i38 = i38;
                    m0VarC8 = m0VarC8;
                    m0VarC6 = m0VarC6;
                    m0Var2 = m0Var2;
                    m0VarC4 = m0VarC4;
                    m0Var = m0Var;
                    f2Var = this;
                    list2 = list;
                }
                throw new NoSuchElementException("Collection contains no element matching the predicate.");
            }
            i27 = i28 + 1;
            f2Var = this;
            list2 = list;
            m0VarC4 = m0VarC4;
            size7 = i29;
            jB2 = jB2;
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Override // n1.f0
    public final int i(n1.m mVar, List list, int i5) {
        return a(mVar, list, i5, w.f1388m);
    }

    @Override // n1.f0
    public final int j(n1.m mVar, List list, int i5) {
        return a(mVar, list, i5, w.f1386k);
    }
}
