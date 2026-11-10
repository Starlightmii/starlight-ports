package io.github.andrea_lyz.assistrestore.ui;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import androidx.compose.foundation.BorderModifierNodeElement;
import androidx.compose.foundation.layout.FillElement;
import androidx.compose.foundation.layout.LayoutWeightElement;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import c0.a4;
import c0.c3;
import c0.d2;
import c0.h2;
import c0.i4;
import c0.n4;
import c0.o2;
import c0.o3;
import c0.r1;
import c0.r2;
import c0.s2;
import c0.s3;
import c0.s4;
import c0.t4;
import f0.b2;
import f0.c2;
import f0.h1;
import f0.h3;
import f0.i1;
import f0.j2;
import f0.p1;
import f0.w1;
import f0.y1;
import io.github.andrea_lyz.assistrestore.AssistConfig;
import io.github.libxposed.service.XposedService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.WeakHashMap;
import r.a1;
import r.c1;
import r.t0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class AssistRestoreAppKt {
    private static final String BREENO_PACKAGE = "com.heytap.speechassist";
    private static final List<e1.e> ENTRY_ICONS;
    private static final String ROUTE_ADVANCED = "advanced";
    private static final String ROUTE_CUSTOM = "custom";
    private static final String ROUTE_ENTRIES = "entries";
    private static final String ROUTE_TARGET = "target";
    private static final List<String> ENTRY_NAMES = p3.j.H("长按电源键", "长按手势条", "底角内滑");
    private static final List<String> ENTRY_IDS = p3.j.H(AssistConfig.ENTRY_POWER, AssistConfig.ENTRY_HANDLE, AssistConfig.ENTRY_CORNER);

    static {
        e1.e eVarB = h1.c.f2609e;
        if (eVarB == null) {
            e1.d dVar = new e1.d("Rounded.PowerSettingsNew");
            int i5 = e1.a0.f1864a;
            y0.s0 s0Var = new y0.s0(y0.u.f6689b);
            e1.f fVar = new e1.f();
            fVar.h(12.0f, 3.0f);
            fVar.c(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
            fVar.l(8.0f);
            fVar.c(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
            fVar.j(1.0f, -0.45f, 1.0f, -1.0f);
            fVar.f(13.0f, 4.0f);
            fVar.c(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
            fVar.a();
            fVar.h(17.14f, 5.86f);
            fVar.c(-0.39f, 0.39f, -0.38f, 1.0f, -0.01f, 1.39f);
            fVar.c(1.13f, 1.2f, 1.83f, 2.8f, 1.87f, 4.57f);
            fVar.c(0.09f, 3.83f, -3.08f, 7.13f, -6.91f, 7.17f);
            fVar.b(8.18f, 19.05f, 5.0f, 15.9f, 5.0f, 12.0f);
            fVar.c(0.0f, -1.84f, 0.71f, -3.51f, 1.87f, -4.76f);
            fVar.c(0.37f, -0.39f, 0.37f, -1.0f, -0.01f, -1.38f);
            fVar.c(-0.4f, -0.4f, -1.05f, -0.39f, -1.43f, 0.02f);
            fVar.b(3.98f, 7.42f, 3.07f, 9.47f, 3.0f, 11.74f);
            fVar.c(-0.14f, 4.88f, 3.83f, 9.1f, 8.71f, 9.25f);
            fVar.c(5.1f, 0.16f, 9.29f, -3.93f, 9.29f, -9.0f);
            fVar.c(0.0f, -2.37f, -0.92f, -4.51f, -2.42f, -6.11f);
            fVar.c(-0.38f, -0.41f, -1.04f, -0.42f, -1.44f, -0.02f);
            fVar.a();
            e1.d.a(dVar, fVar.f1910d, s0Var);
            eVarB = dVar.b();
            h1.c.f2609e = eVarB;
        }
        e1.e eVarB2 = a.a.f14h;
        if (eVarB2 == null) {
            e1.d dVar2 = new e1.d("Rounded.Gesture");
            int i6 = e1.a0.f1864a;
            y0.s0 s0Var2 = new y0.s0(y0.u.f6689b);
            e1.f fVar2 = new e1.f();
            fVar2.h(3.72f, 6.04f);
            fVar2.c(0.47f, 0.46f, 1.21f, 0.48f, 1.71f, 0.06f);
            fVar2.c(0.37f, -0.32f, 0.69f, -0.51f, 0.87f, -0.43f);
            fVar2.c(0.5f, 0.2f, 0.0f, 1.03f, -0.3f, 1.52f);
            fVar2.c(-0.25f, 0.42f, -2.86f, 3.89f, -2.86f, 6.31f);
            fVar2.c(0.0f, 1.28f, 0.48f, 2.34f, 1.34f, 2.98f);
            fVar2.c(0.75f, 0.56f, 1.74f, 0.73f, 2.64f, 0.46f);
            fVar2.c(1.07f, -0.31f, 1.95f, -1.4f, 3.06f, -2.77f);
            fVar2.c(1.21f, -1.49f, 2.83f, -3.44f, 4.08f, -3.44f);
            fVar2.c(1.63f, 0.0f, 1.65f, 1.01f, 1.76f, 1.79f);
            fVar2.c(-3.78f, 0.64f, -5.38f, 3.67f, -5.38f, 5.37f);
            fVar2.c(0.0f, 1.7f, 1.44f, 3.09f, 3.21f, 3.09f);
            fVar2.c(1.63f, 0.0f, 4.29f, -1.33f, 4.69f, -6.1f);
            fVar2.e(1.21f);
            fVar2.c(0.69f, 0.0f, 1.25f, -0.56f, 1.25f, -1.25f);
            fVar2.j(-0.56f, -1.25f, -1.25f, -1.25f);
            fVar2.e(-1.22f);
            fVar2.c(-0.15f, -1.65f, -1.09f, -4.2f, -4.03f, -4.2f);
            fVar2.c(-2.25f, 0.0f, -4.18f, 1.91f, -4.94f, 2.84f);
            fVar2.c(-0.58f, 0.73f, -2.06f, 2.48f, -2.29f, 2.72f);
            fVar2.c(-0.25f, 0.3f, -0.68f, 0.84f, -1.11f, 0.84f);
            fVar2.c(-0.45f, 0.0f, -0.72f, -0.83f, -0.36f, -1.92f);
            fVar2.c(0.35f, -1.09f, 1.4f, -2.86f, 1.85f, -3.52f);
            fVar2.c(0.78f, -1.14f, 1.3f, -1.92f, 1.3f, -3.28f);
            fVar2.b(8.95f, 3.69f, 7.31f, 3.0f, 6.44f, 3.0f);
            fVar2.c(-1.09f, 0.0f, -2.04f, 0.63f, -2.7f, 1.22f);
            fVar2.c(-0.53f, 0.48f, -0.53f, 1.32f, -0.02f, 1.82f);
            fVar2.a();
            fVar2.h(13.88f, 18.55f);
            fVar2.c(-0.31f, 0.0f, -0.74f, -0.26f, -0.74f, -0.72f);
            fVar2.c(0.0f, -0.6f, 0.73f, -2.2f, 2.87f, -2.76f);
            fVar2.c(-0.3f, 2.69f, -1.43f, 3.48f, -2.13f, 3.48f);
            fVar2.a();
            e1.d.a(dVar2, fVar2.f1910d, s0Var2);
            eVarB2 = dVar2.b();
            a.a.f14h = eVarB2;
        }
        e1.e eVarB3 = d4.a.f1636g;
        if (eVarB3 == null) {
            e1.d dVar3 = new e1.d("Rounded.Swipe");
            int i7 = e1.a0.f1864a;
            long j5 = y0.u.f6689b;
            y0.s0 s0Var3 = new y0.s0(j5);
            e1.f fVar3 = new e1.f();
            fVar3.h(21.15f, 2.85f);
            fVar3.g(-1.02f, 1.02f);
            fVar3.b(18.69f, 2.17f, 15.6f, 1.0f, 12.0f, 1.0f);
            fVar3.i(5.31f, 2.17f, 3.87f, 3.87f);
            fVar3.f(2.85f, 2.85f);
            fVar3.b(2.54f, 2.54f, 2.0f, 2.76f, 2.0f, 3.21f);
            fVar3.k(6.5f);
            fVar3.b(2.0f, 6.78f, 2.22f, 7.0f, 2.5f, 7.0f);
            fVar3.e(3.29f);
            fVar3.c(0.45f, 0.0f, 0.67f, -0.54f, 0.35f, -0.85f);
            fVar3.f(4.93f, 4.93f);
            fVar3.c(1.0f, -1.29f, 3.7f, -2.43f, 7.07f, -2.43f);
            fVar3.j(6.07f, 1.14f, 7.07f, 2.43f);
            fVar3.g(-1.22f, 1.22f);
            fVar3.b(17.54f, 6.46f, 17.76f, 7.0f, 18.21f, 7.0f);
            fVar3.e(3.29f);
            fVar3.b(21.78f, 7.0f, 22.0f, 6.78f, 22.0f, 6.5f);
            fVar3.k(3.21f);
            fVar3.b(22.0f, 2.76f, 21.46f, 2.54f, 21.15f, 2.85f);
            fVar3.a();
            e1.d.a(dVar3, fVar3.f1910d, s0Var3);
            y0.s0 s0Var4 = new y0.s0(j5);
            e1.f fVar4 = new e1.f();
            fVar4.h(14.5f, 12.71f);
            fVar4.c(-0.28f, -0.14f, -0.58f, -0.21f, -0.89f, -0.21f);
            fVar4.d(13.0f);
            fVar4.l(-6.0f);
            fVar4.b(13.0f, 5.67f, 12.33f, 5.0f, 11.5f, 5.0f);
            fVar4.i(10.0f, 5.67f, 10.0f, 6.5f);
            fVar4.l(10.74f);
            fVar4.g(-3.44f, -0.72f);
            fVar4.c(-0.37f, -0.08f, -0.76f, 0.04f, -1.03f, 0.31f);
            fVar4.g(0.0f, 0.0f);
            fVar4.c(-0.43f, 0.44f, -0.43f, 1.14f, 0.01f, 1.58f);
            fVar4.g(4.01f, 4.01f);
            fVar4.b(9.92f, 22.79f, 10.43f, 23.0f, 10.96f, 23.0f);
            fVar4.e(6.41f);
            fVar4.c(1.0f, 0.0f, 1.84f, -0.73f, 1.98f, -1.72f);
            fVar4.g(0.63f, -4.46f);
            fVar4.c(0.12f, -0.85f, -0.32f, -1.69f, -1.09f, -2.07f);
            fVar4.f(14.5f, 12.71f);
            fVar4.a();
            e1.d.a(dVar3, fVar4.f1910d, s0Var4);
            eVarB3 = dVar3.b();
            d4.a.f1636g = eVarB3;
        }
        ENTRY_ICONS = p3.j.H(eVarB, eVarB2, eVarB3);
    }

    private static final void AdvancedScreen(final boolean z2, final a4.c cVar, final boolean z3, final a4.c cVar2, final boolean z4, final a4.c cVar3, final boolean z5, final a4.c cVar4, final boolean z6, final a4.c cVar5, final boolean z7, final a4.c cVar6, final int i5, final a4.c cVar7, f0.p pVar, final int i6, final int i7) {
        int i8;
        a4.c cVar8;
        boolean z8;
        a4.c cVar9;
        int i9;
        f0.v vVar = (f0.v) pVar;
        vVar.X(-1497503009);
        if ((i6 & 6) == 0) {
            i8 = (vVar.g(z2) ? 4 : 2) | i6;
        } else {
            i8 = i6;
        }
        if ((i6 & 48) == 0) {
            cVar8 = cVar;
            i8 |= vVar.h(cVar8) ? 32 : 16;
        } else {
            cVar8 = cVar;
        }
        if ((i6 & 384) == 0) {
            z8 = z3;
            i8 |= vVar.g(z8) ? 256 : 128;
        } else {
            z8 = z3;
        }
        if ((i6 & 3072) == 0) {
            cVar9 = cVar2;
            i8 |= vVar.h(cVar9) ? 2048 : 1024;
        } else {
            cVar9 = cVar2;
        }
        if ((i6 & 24576) == 0) {
            i8 |= vVar.g(z4) ? 16384 : 8192;
        }
        if ((i6 & 196608) == 0) {
            i8 |= vVar.h(cVar3) ? 131072 : 65536;
        }
        if ((i6 & 1572864) == 0) {
            i8 |= vVar.g(z5) ? 1048576 : 524288;
        }
        if ((i6 & 12582912) == 0) {
            i8 |= vVar.h(cVar4) ? 8388608 : 4194304;
        }
        if ((i6 & 100663296) == 0) {
            i8 |= vVar.g(z6) ? 67108864 : 33554432;
        }
        if ((i6 & 805306368) == 0) {
            i8 |= vVar.h(cVar5) ? 536870912 : 268435456;
        }
        if ((i7 & 6) == 0) {
            i9 = i7 | (vVar.g(z7) ? 4 : 2);
        } else {
            i9 = i7;
        }
        if ((i7 & 48) == 0) {
            i9 |= vVar.h(cVar6) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i9 |= vVar.d(i5) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i9 |= vVar.h(cVar7) ? 2048 : 1024;
        }
        if (vVar.N(i8 & 1, ((i8 & 306783379) == 306783378 && (i9 & 1171) == 1170) ? false : true)) {
            AppScreen("高级", null, null, null, n0.i.c(423762017, new o(i5, 1, cVar7), vVar), n0.i.c(-987353333, new g(z2, cVar8, z8, cVar9, z4, cVar3, z5, cVar4, z6, cVar5, z7, cVar6, 1), vVar), vVar, 221190, 14);
        } else {
            vVar.Q();
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.r
                @Override // a4.e
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return AssistRestoreAppKt.AdvancedScreen$lambda$2(z2, cVar, z3, cVar2, z4, cVar3, z5, cVar4, z6, cVar5, z7, cVar6, i5, cVar7, i6, i7, (f0.p) obj, iIntValue);
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AdvancedScreen$lambda$0(int i5, a4.c cVar, f0.p pVar, int i6) {
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i6 & 1, (i6 & 3) != 2)) {
            AppBottomBar(i5, cVar, vVar, 0);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AdvancedScreen$lambda$1(boolean z2, a4.c cVar, boolean z3, a4.c cVar2, boolean z4, a4.c cVar3, boolean z5, a4.c cVar4, boolean z6, a4.c cVar5, boolean z7, a4.c cVar6, r0.p pVar, f0.p pVar2, int i5) {
        int i6;
        b4.l.e("modifier", pVar);
        if ((i5 & 6) == 0) {
            i6 = i5 | (((f0.v) pVar2).f(pVar) ? 4 : 2);
        } else {
            i6 = i5;
        }
        f0.v vVar = (f0.v) pVar2;
        if (vVar.N(i6 & 1, (i6 & 19) != 18)) {
            float f5 = 16;
            r0.p pVarH = androidx.compose.foundation.layout.a.h(pVar, f5, 0.0f, 2);
            r.v vVarA = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, 0);
            int iHashCode = Long.hashCode(vVar.S);
            w1 w1VarL = vVar.l();
            r0.p pVarC = r0.a.c(vVar, pVarH);
            p1.j.f4892c.getClass();
            p1.n nVar = p1.i.f4867b;
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            f0.l.x(p1.i.f4870e, vVar, vVarA);
            f0.l.x(p1.i.f4869d, vVar, w1VarL);
            p1.h hVar = p1.i.f4871f;
            if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode))) {
                b.b.i(iHashCode, vVar, iHashCode, hVar);
            }
            f0.l.x(p1.i.f4868c, vVar, pVarC);
            r0.m mVar = r0.m.f5680a;
            r.c.a(vVar, androidx.compose.foundation.layout.c.d(mVar, f5));
            SectionCard(null, n0.i.c(-1581872774, new g(z2, cVar, z3, cVar2, z4, cVar3, z5, cVar4, z6, cVar5, z7, cVar6, 0), vVar), vVar, 48, 1);
            r.c.a(vVar, androidx.compose.foundation.layout.c.d(mVar, 24));
            vVar.p(true);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AdvancedScreen$lambda$1$0$0(boolean z2, a4.c cVar, boolean z3, a4.c cVar2, boolean z4, a4.c cVar3, boolean z5, a4.c cVar4, boolean z6, a4.c cVar5, boolean z7, a4.c cVar6, r.w wVar, f0.p pVar, int i5) {
        float f5;
        b4.l.e("$this$SectionCard", wVar);
        int i6 = 1;
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 17) != 16)) {
            e1.e eVarB = h1.c.f2606b;
            if (eVarB == null) {
                e1.d dVar = new e1.d("Rounded.Block");
                int i7 = e1.a0.f1864a;
                y0.s0 s0Var = new y0.s0(y0.u.f6689b);
                e1.f fVar = new e1.f();
                fVar.h(12.0f, 2.0f);
                fVar.b(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
                fVar.j(4.48f, 10.0f, 10.0f, 10.0f);
                fVar.j(10.0f, -4.48f, 10.0f, -10.0f);
                fVar.i(17.52f, 2.0f, 12.0f, 2.0f);
                fVar.a();
                fVar.h(4.0f, 12.0f);
                fVar.c(0.0f, -4.42f, 3.58f, -8.0f, 8.0f, -8.0f);
                fVar.c(1.85f, 0.0f, 3.55f, 0.63f, 4.9f, 1.69f);
                fVar.f(5.69f, 16.9f);
                fVar.b(4.63f, 15.55f, 4.0f, 13.85f, 4.0f, 12.0f);
                fVar.a();
                fVar.h(12.0f, 20.0f);
                fVar.c(-1.85f, 0.0f, -3.55f, -0.63f, -4.9f, -1.69f);
                fVar.f(18.31f, 7.1f);
                fVar.b(19.37f, 8.45f, 20.0f, 10.15f, 20.0f, 12.0f);
                fVar.c(0.0f, 4.42f, -3.58f, 8.0f, -8.0f, 8.0f);
                fVar.a();
                e1.d.a(dVar, fVar.f1910d, s0Var);
                eVarB = dVar.b();
                h1.c.f2606b = eVarB;
            }
            ListRow("跳过识屏服务预绑定", "长按手势条时不再白唤醒一次小布识屏服务", eVarB, null, n0.i.c(-1436869651, new g0(z2, cVar, i6), vVar), null, false, vVar, 24630, 104);
            RowDivider(vVar, 0);
            boolean z8 = AssistConfig.PAGE_FLAGS_SUPPORTED;
            String str = z8 ? "设置这类页面也能用底角内滑" : "Android 17 起系统不再提供页面级屏蔽，此项无效";
            e1.e eVarB2 = d4.a.f1634e;
            if (eVarB2 != null) {
                f5 = 7.0f;
            } else {
                e1.d dVar2 = new e1.d("Rounded.LockOpen");
                int i8 = e1.a0.f1864a;
                y0.s0 s0Var2 = new y0.s0(y0.u.f6689b);
                e1.f fVar2 = new e1.f();
                fVar2.h(12.0f, 13.0f);
                fVar2.c(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
                fVar2.j(0.9f, 2.0f, 2.0f, 2.0f);
                fVar2.j(2.0f, -0.9f, 2.0f, -2.0f);
                fVar2.j(-0.9f, -2.0f, -2.0f, -2.0f);
                fVar2.a();
                fVar2.h(18.0f, 8.0f);
                fVar2.e(-1.0f);
                fVar2.f(17.0f, 6.0f);
                fVar2.c(0.0f, -2.76f, -2.24f, -5.0f, -5.0f, -5.0f);
                fVar2.c(-2.28f, 0.0f, -4.27f, 1.54f, -4.84f, 3.75f);
                fVar2.c(-0.14f, 0.54f, 0.18f, 1.08f, 0.72f, 1.22f);
                fVar2.c(0.53f, 0.14f, 1.08f, -0.18f, 1.22f, -0.72f);
                fVar2.b(9.44f, 3.93f, 10.63f, 3.0f, 12.0f, 3.0f);
                fVar2.c(1.65f, 0.0f, 3.0f, 1.35f, 3.0f, 3.0f);
                fVar2.l(2.0f);
                fVar2.f(6.0f, 8.0f);
                fVar2.c(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
                fVar2.l(10.0f);
                fVar2.c(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
                fVar2.e(12.0f);
                fVar2.c(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
                fVar2.f(20.0f, 10.0f);
                fVar2.c(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
                fVar2.a();
                fVar2.h(18.0f, 19.0f);
                fVar2.c(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
                f5 = 7.0f;
                fVar2.f(7.0f, 20.0f);
                fVar2.c(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
                fVar2.l(-8.0f);
                fVar2.c(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
                fVar2.e(10.0f);
                fVar2.c(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
                fVar2.l(8.0f);
                fVar2.a();
                e1.d.a(dVar2, fVar2.f1910d, s0Var2);
                eVarB2 = dVar2.b();
                d4.a.f1634e = eVarB2;
            }
            ListRow("解除页面级手势限制", str, eVarB2, null, n0.i.c(-56696810, new g0(z3, cVar2, 2), vVar), null, z8, vVar, 1597446, 40);
            RowDivider(vVar, 0);
            e1.e eVarB3 = a.a.f16j;
            if (eVarB3 == null) {
                e1.d dVar3 = new e1.d("Rounded.Smartphone");
                int i9 = e1.a0.f1864a;
                y0.s0 s0Var3 = new y0.s0(y0.u.f6689b);
                e1.f fVar3 = new e1.f();
                fVar3.h(17.0f, 1.01f);
                fVar3.f(f5, 1.0f);
                fVar3.c(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
                fVar3.l(18.0f);
                fVar3.c(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
                fVar3.e(10.0f);
                fVar3.c(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
                fVar3.k(3.0f);
                fVar3.c(0.0f, -1.1f, -0.9f, -1.99f, -2.0f, -1.99f);
                fVar3.a();
                fVar3.h(17.0f, 19.0f);
                fVar3.d(f5);
                fVar3.k(5.0f);
                fVar3.e(10.0f);
                fVar3.l(14.0f);
                fVar3.a();
                e1.d.a(dVar3, fVar3.f1910d, s0Var3);
                eVarB3 = dVar3.b();
                a.a.f16j = eVarB3;
            }
            ListRow("Google 应用机型伪装", "伪装为 SM-S928B，解锁一圈即搜", eVarB3, null, n0.i.c(-1088158027, new g0(z4, cVar3, 3), vVar), null, false, vVar, 24630, 104);
            RowDivider(vVar, 0);
            ListRow("隐藏手势条时保持长按", "手势条隐藏后，底部原位置的长按仍能召唤助理", h1.c.x(), null, n0.i.c(-2119619244, new g0(z5, cVar4, 4), vVar), null, false, vVar, 24630, 104);
            RowDivider(vVar, 0);
            e1.e eVarB4 = a.a.f10d;
            if (eVarB4 == null) {
                e1.d dVar4 = new e1.d("Rounded.AccountBalanceWallet");
                int i10 = e1.a0.f1864a;
                y0.s0 s0Var4 = new y0.s0(y0.u.f6689b);
                e1.f fVar4 = new e1.f();
                fVar4.h(10.0f, 16.0f);
                fVar4.f(10.0f, 8.0f);
                fVar4.c(0.0f, -1.1f, 0.89f, -2.0f, 2.0f, -2.0f);
                fVar4.e(9.0f);
                fVar4.f(21.0f, 5.0f);
                fVar4.c(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
                fVar4.f(5.0f, 3.0f);
                fVar4.c(-1.11f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
                fVar4.l(14.0f);
                fVar4.c(0.0f, 1.1f, 0.89f, 2.0f, 2.0f, 2.0f);
                fVar4.e(14.0f);
                fVar4.c(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
                fVar4.l(-1.0f);
                fVar4.e(-9.0f);
                fVar4.c(-1.11f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
                fVar4.a();
                fVar4.h(13.0f, 8.0f);
                fVar4.c(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
                fVar4.l(6.0f);
                fVar4.c(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
                fVar4.e(9.0f);
                fVar4.f(22.0f, 8.0f);
                fVar4.e(-9.0f);
                fVar4.a();
                fVar4.h(16.0f, 13.5f);
                fVar4.c(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
                fVar4.j(0.67f, -1.5f, 1.5f, -1.5f);
                fVar4.j(1.5f, 0.67f, 1.5f, 1.5f);
                fVar4.j(-0.67f, 1.5f, -1.5f, 1.5f);
                fVar4.a();
                e1.d.a(dVar4, fVar4.f1910d, s0Var4);
                eVarB4 = dVar4.b();
                a.a.f10d = eVarB4;
            }
            ListRow("双击电源键改用 Google 钱包", "双击电源键打开系统快捷钱包界面（Google 钱包刷卡），替代 ColorOS 钱包；需保持系统「双击电源键唤起快捷服务」开启", eVarB4, null, n0.i.c(1143886835, new g0(z6, cVar5, 5), vVar), null, false, vVar, 24630, 104);
            RowDivider(vVar, 0);
            e1.e eVarB5 = d4.a.f1637h;
            if (eVarB5 == null) {
                e1.d dVar5 = new e1.d("Rounded.VisibilityOff");
                int i11 = e1.a0.f1864a;
                y0.s0 s0Var5 = new y0.s0(y0.u.f6689b);
                e1.f fVar5 = new e1.f();
                fVar5.h(12.0f, 6.5f);
                fVar5.c(2.76f, 0.0f, 5.0f, 2.24f, 5.0f, 5.0f);
                fVar5.c(0.0f, 0.51f, -0.1f, 1.0f, -0.24f, 1.46f);
                fVar5.g(3.06f, 3.06f);
                fVar5.c(1.39f, -1.23f, 2.49f, -2.77f, 3.18f, -4.53f);
                fVar5.b(21.27f, 7.11f, 17.0f, 4.0f, 12.0f, 4.0f);
                fVar5.c(-1.27f, 0.0f, -2.49f, 0.2f, -3.64f, 0.57f);
                fVar5.g(2.17f, 2.17f);
                fVar5.c(0.47f, -0.14f, 0.96f, -0.24f, 1.47f, -0.24f);
                fVar5.a();
                fVar5.h(2.71f, 3.16f);
                fVar5.c(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
                fVar5.g(1.97f, 1.97f);
                fVar5.b(3.06f, 7.83f, 1.77f, 9.53f, 1.0f, 11.5f);
                fVar5.b(2.73f, 15.89f, 7.0f, 19.0f, 12.0f, 19.0f);
                fVar5.c(1.52f, 0.0f, 2.97f, -0.3f, 4.31f, -0.82f);
                fVar5.g(2.72f, 2.72f);
                fVar5.c(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
                fVar5.c(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
                fVar5.f(4.13f, 3.16f);
                fVar5.c(-0.39f, -0.39f, -1.03f, -0.39f, -1.42f, 0.0f);
                fVar5.a();
                fVar5.h(12.0f, 16.5f);
                fVar5.c(-2.76f, 0.0f, -5.0f, -2.24f, -5.0f, -5.0f);
                fVar5.c(0.0f, -0.77f, 0.18f, -1.5f, 0.49f, -2.14f);
                fVar5.g(1.57f, 1.57f);
                fVar5.c(-0.03f, 0.18f, -0.06f, 0.37f, -0.06f, 0.57f);
                fVar5.c(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
                fVar5.c(0.2f, 0.0f, 0.38f, -0.03f, 0.57f, -0.07f);
                fVar5.f(14.14f, 16.0f);
                fVar5.c(-0.65f, 0.32f, -1.37f, 0.5f, -2.14f, 0.5f);
                fVar5.a();
                fVar5.h(14.97f, 11.17f);
                fVar5.c(-0.15f, -1.4f, -1.25f, -2.49f, -2.64f, -2.64f);
                fVar5.g(2.64f, 2.64f);
                fVar5.a();
                e1.d.a(dVar5, fVar5.f1910d, s0Var5);
                eVarB5 = dVar5.b();
                d4.a.f1637h = eVarB5;
            }
            ListRow("隐藏桌面图标", "隐藏后可从 LSPosed 模块页或系统设置的应用详情打开", eVarB5, null, n0.i.c(112425618, new g0(z7, cVar6, 6), vVar), null, false, vVar, 24630, 104);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AdvancedScreen$lambda$1$0$0$0(boolean z2, a4.c cVar, f0.p pVar, int i5) {
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 3) != 2)) {
            Toggle(z2, cVar, false, vVar, 0, 4);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AdvancedScreen$lambda$1$0$0$1(boolean z2, a4.c cVar, f0.p pVar, int i5) {
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 3) != 2)) {
            Toggle(z2, cVar, AssistConfig.PAGE_FLAGS_SUPPORTED, vVar, 384, 0);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AdvancedScreen$lambda$1$0$0$2(boolean z2, a4.c cVar, f0.p pVar, int i5) {
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 3) != 2)) {
            Toggle(z2, cVar, false, vVar, 0, 4);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AdvancedScreen$lambda$1$0$0$3(boolean z2, a4.c cVar, f0.p pVar, int i5) {
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 3) != 2)) {
            Toggle(z2, cVar, false, vVar, 0, 4);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AdvancedScreen$lambda$1$0$0$4(boolean z2, a4.c cVar, f0.p pVar, int i5) {
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 3) != 2)) {
            Toggle(z2, cVar, false, vVar, 0, 4);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AdvancedScreen$lambda$1$0$0$5(boolean z2, a4.c cVar, f0.p pVar, int i5) {
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 3) != 2)) {
            Toggle(z2, cVar, false, vVar, 0, 4);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AdvancedScreen$lambda$2(boolean z2, a4.c cVar, boolean z3, a4.c cVar2, boolean z4, a4.c cVar3, boolean z5, a4.c cVar4, boolean z6, a4.c cVar5, boolean z7, a4.c cVar6, int i5, a4.c cVar7, int i6, int i7, f0.p pVar, int i8) {
        AdvancedScreen(z2, cVar, z3, cVar2, z4, cVar3, z5, cVar4, z6, cVar5, z7, cVar6, i5, cVar7, pVar, f0.l.z(i6 | 1), f0.l.z(i7));
        return p3.n.f5026a;
    }

    private static final void AppBottomBar(final int i5, final a4.c cVar, f0.p pVar, final int i6) {
        int i7;
        f0.v vVar = (f0.v) pVar;
        vVar.X(1336292354);
        if ((i6 & 6) == 0) {
            i7 = (vVar.d(i5) ? 4 : 2) | i6;
        } else {
            i7 = i6;
        }
        if ((i6 & 48) == 0) {
            i7 |= vVar.h(cVar) ? 32 : 16;
        }
        if (vVar.N(i7 & 1, (i7 & 19) != 18)) {
            r1.a(null, 0L, 0L, 0.0f, null, n0.i.c(-839786565, new a4.f() { // from class: io.github.andrea_lyz.assistrestore.ui.k
                @Override // a4.f
                public final Object b(Object obj, Object obj2, Object obj3) {
                    int iIntValue = ((Integer) obj3).intValue();
                    return AssistRestoreAppKt.AppBottomBar$lambda$0(i5, cVar, (t0) obj, (f0.p) obj2, iIntValue);
                }
            }, vVar), vVar, 196608);
        } else {
            vVar.Q();
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.l
                @Override // a4.e
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return AssistRestoreAppKt.AppBottomBar$lambda$1(i5, cVar, i6, (f0.p) obj, iIntValue);
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AppBottomBar$lambda$0(int i5, a4.c cVar, t0 t0Var, f0.p pVar, int i6) {
        t0 t0Var2 = t0Var;
        b4.l.e("$this$NavigationBar", t0Var2);
        int i7 = 4;
        int i8 = (i6 & 6) == 0 ? i6 | (((f0.v) pVar).f(t0Var2) ? 4 : 2) : i6;
        int i9 = 0;
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i8 & 1, (i8 & 19) != 18)) {
            p3.l lVar = new p3.l("入口", h1.c.x(), 0);
            e1.e eVarB = d4.a.f1635f;
            if (eVarB == null) {
                e1.d dVar = new e1.d("Rounded.Settings");
                int i10 = e1.a0.f1864a;
                y0.s0 s0Var = new y0.s0(y0.u.f6689b);
                e1.f fVar = new e1.f();
                fVar.h(19.5f, 12.0f);
                fVar.c(0.0f, -0.23f, -0.01f, -0.45f, -0.03f, -0.68f);
                fVar.g(1.86f, -1.41f);
                fVar.c(0.4f, -0.3f, 0.51f, -0.86f, 0.26f, -1.3f);
                fVar.g(-1.87f, -3.23f);
                fVar.c(-0.25f, -0.44f, -0.79f, -0.62f, -1.25f, -0.42f);
                fVar.g(-2.15f, 0.91f);
                fVar.c(-0.37f, -0.26f, -0.76f, -0.49f, -1.17f, -0.68f);
                fVar.g(-0.29f, -2.31f);
                fVar.b(14.8f, 2.38f, 14.37f, 2.0f, 13.87f, 2.0f);
                fVar.e(-3.73f);
                fVar.b(9.63f, 2.0f, 9.2f, 2.38f, 9.14f, 2.88f);
                fVar.f(8.85f, 5.19f);
                fVar.c(-0.41f, 0.19f, -0.8f, 0.42f, -1.17f, 0.68f);
                fVar.f(5.53f, 4.96f);
                fVar.c(-0.46f, -0.2f, -1.0f, -0.02f, -1.25f, 0.42f);
                fVar.f(2.41f, 8.62f);
                fVar.c(-0.25f, 0.44f, -0.14f, 0.99f, 0.26f, 1.3f);
                fVar.g(1.86f, 1.41f);
                fVar.b(4.51f, 11.55f, 4.5f, 11.77f, 4.5f, 12.0f);
                fVar.j(0.01f, 0.45f, 0.03f, 0.68f);
                fVar.g(-1.86f, 1.41f);
                fVar.c(-0.4f, 0.3f, -0.51f, 0.86f, -0.26f, 1.3f);
                fVar.g(1.87f, 3.23f);
                fVar.c(0.25f, 0.44f, 0.79f, 0.62f, 1.25f, 0.42f);
                fVar.g(2.15f, -0.91f);
                fVar.c(0.37f, 0.26f, 0.76f, 0.49f, 1.17f, 0.68f);
                fVar.g(0.29f, 2.31f);
                fVar.b(9.2f, 21.62f, 9.63f, 22.0f, 10.13f, 22.0f);
                fVar.e(3.73f);
                fVar.c(0.5f, 0.0f, 0.93f, -0.38f, 0.99f, -0.88f);
                fVar.g(0.29f, -2.31f);
                fVar.c(0.41f, -0.19f, 0.8f, -0.42f, 1.17f, -0.68f);
                fVar.g(2.15f, 0.91f);
                fVar.c(0.46f, 0.2f, 1.0f, 0.02f, 1.25f, -0.42f);
                fVar.g(1.87f, -3.23f);
                fVar.c(0.25f, -0.44f, 0.14f, -0.99f, -0.26f, -1.3f);
                fVar.g(-1.86f, -1.41f);
                fVar.b(19.49f, 12.45f, 19.5f, 12.23f, 19.5f, 12.0f);
                fVar.a();
                fVar.h(12.04f, 15.5f);
                fVar.c(-1.93f, 0.0f, -3.5f, -1.57f, -3.5f, -3.5f);
                fVar.j(1.57f, -3.5f, 3.5f, -3.5f);
                fVar.j(3.5f, 1.57f, 3.5f, 3.5f);
                fVar.i(13.97f, 15.5f, 12.04f, 15.5f);
                fVar.a();
                e1.d.a(dVar, fVar.f1910d, s0Var);
                eVarB = dVar.b();
                d4.a.f1635f = eVarB;
            }
            for (p3.l lVar2 : p3.j.H(lVar, new p3.l("高级", eVarB, 1))) {
                String str = (String) lVar2.f5022d;
                e1.e eVar = (e1.e) lVar2.f5023e;
                int iIntValue = ((Number) lVar2.f5024f).intValue();
                boolean z2 = i5 == iIntValue;
                boolean zF = vVar.f(cVar) | vVar.d(iIntValue);
                Object objK = vVar.K();
                if (zF || objK == f0.o.f2281a) {
                    objK = new s(iIntValue, i9, cVar);
                    vVar.f0(objK);
                }
                r1.b(t0Var2, z2, (a4.a) objK, n0.i.c(-69415837, new c0(eVar, i7, str), vVar), null, false, n0.i.c(-1661179328, new t(str, i9), vVar), false, null, vVar, (i8 & 14) | 1575936);
                t0Var2 = t0Var;
            }
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AppBottomBar$lambda$0$0$0$0(a4.c cVar, int i5) {
        cVar.c(Integer.valueOf(i5));
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AppBottomBar$lambda$0$0$1(e1.e eVar, String str, f0.p pVar, int i5) {
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 3) != 2)) {
            c0.q0.b(eVar, str, null, 0L, vVar, 0, 12);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AppBottomBar$lambda$0$0$2(String str, f0.p pVar, int i5) {
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 3) != 2)) {
            n4.b(str, null, 0L, 0L, 0L, 0L, 0, false, 0, 0, null, vVar, 0, 0, 131070);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AppBottomBar$lambda$1(int i5, a4.c cVar, int i6, f0.p pVar, int i7) {
        AppBottomBar(i5, cVar, pVar, f0.l.z(i6 | 1));
        return p3.n.f5026a;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x003f  */
    /* JADX WARN: Code duplicated, block: B:25:0x0044  */
    /* JADX WARN: Code duplicated, block: B:27:0x0048  */
    /* JADX WARN: Code duplicated, block: B:29:0x0050  */
    /* JADX WARN: Code duplicated, block: B:30:0x0053  */
    /* JADX WARN: Code duplicated, block: B:34:0x005a  */
    /* JADX WARN: Code duplicated, block: B:36:0x005f  */
    /* JADX WARN: Code duplicated, block: B:38:0x0063  */
    /* JADX WARN: Code duplicated, block: B:40:0x006b  */
    /* JADX WARN: Code duplicated, block: B:41:0x006e  */
    /* JADX WARN: Code duplicated, block: B:45:0x0075  */
    /* JADX WARN: Code duplicated, block: B:47:0x007a  */
    /* JADX WARN: Code duplicated, block: B:49:0x007e  */
    /* JADX WARN: Code duplicated, block: B:51:0x0086  */
    /* JADX WARN: Code duplicated, block: B:52:0x0089  */
    /* JADX WARN: Code duplicated, block: B:56:0x0091  */
    /* JADX WARN: Code duplicated, block: B:58:0x0097  */
    /* JADX WARN: Code duplicated, block: B:59:0x009a  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:64:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:67:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:69:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:71:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:72:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:74:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:75:0x00be  */
    /* JADX WARN: Code duplicated, block: B:78:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:80:0x0120  */
    /* JADX WARN: Code duplicated, block: B:83:0x012f  */
    /* JADX WARN: Code duplicated, block: B:85:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:67:0x00b1, please report this as an issue */
    private static final void AppScreen(final String str, c3 c3Var, a4.a aVar, a4.e eVar, a4.e eVar2, final a4.f fVar, f0.p pVar, final int i5, final int i6) {
        int i7;
        c3 c3Var2;
        int i8;
        a4.a aVar2;
        int i9;
        int i10;
        a4.e eVar3;
        int i11;
        int i12;
        a4.e eVar4;
        int i13;
        boolean z2;
        f0.v vVar;
        final c3 c3Var3;
        final a4.a aVar3;
        final a4.e eVar5;
        final a4.e eVar6;
        b2 b2VarR;
        a4.e eVar7;
        int i14;
        f0.v vVar2 = (f0.v) pVar;
        vVar2.X(1291730389);
        if ((i5 & 6) == 0) {
            i7 = (vVar2.f(str) ? 4 : 2) | i5;
        } else {
            i7 = i5;
        }
        int i15 = i6 & 2;
        if (i15 == 0) {
            if ((i5 & 48) == 0) {
                c3Var2 = c3Var;
                i7 |= vVar2.f(c3Var2) ? 32 : 16;
            }
            i8 = i6 & 4;
            if (i8 != 0) {
                if ((i5 & 384) == 0) {
                    aVar2 = aVar;
                    if (vVar2.h(aVar2)) {
                        i9 = 256;
                    } else {
                        i9 = 128;
                    }
                    i7 |= i9;
                }
                i10 = i6 & 8;
                if (i10 != 0) {
                    if ((i5 & 3072) == 0) {
                        eVar3 = eVar;
                        if (vVar2.h(eVar3)) {
                            i11 = 2048;
                        } else {
                            i11 = 1024;
                        }
                        i7 |= i11;
                    }
                    i12 = i6 & 16;
                    if (i12 != 0) {
                        if ((i5 & 24576) == 0) {
                            eVar4 = eVar2;
                            if (vVar2.h(eVar4)) {
                                i13 = 16384;
                            } else {
                                i13 = 8192;
                            }
                            i7 |= i13;
                        }
                        if ((196608 & i5) == 0) {
                            if (vVar2.h(fVar)) {
                                i14 = 131072;
                            } else {
                                i14 = 65536;
                            }
                            i7 |= i14;
                        }
                        if ((74899 & i7) != 74898) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if (vVar2.N(i7 & 1, z2)) {
                            if (i15 != 0) {
                                c3Var2 = null;
                            }
                            if (i8 != 0) {
                                aVar3 = null;
                            } else {
                                aVar3 = aVar2;
                            }
                            if (i10 != 0) {
                                eVar7 = null;
                            } else {
                                eVar7 = eVar3;
                            }
                            a4.e eVar8 = i12 == 0 ? eVar4 : null;
                            vVar = vVar2;
                            o2.a(androidx.compose.foundation.layout.c.c(r0.m.f5680a, 1.0f), n0.i.c(1846647441, new i0(str, aVar3, eVar7), vVar2), n0.i.c(-1298487568, new f0.y(3, eVar8), vVar2), n0.i.c(-148655281, new f0.y(4, c3Var2), vVar2), null, 0, ((c0.s) vVar2.j(c0.u.f1329a)).f1257n, 0L, new r.b0(), n0.i.c(1374184934, new e0(fVar, 1), vVar2), vVar, 805309878);
                            a4.e eVar9 = eVar7;
                            eVar6 = eVar8;
                            c3Var3 = c3Var2;
                            eVar5 = eVar9;
                        } else {
                            vVar = vVar2;
                            vVar.Q();
                            c3Var3 = c3Var2;
                            aVar3 = aVar2;
                            eVar5 = eVar3;
                            eVar6 = eVar4;
                        }
                        b2VarR = vVar.r();
                        if (b2VarR != null) {
                            b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.j0
                                @Override // a4.e
                                public final Object invoke(Object obj, Object obj2) {
                                    int iIntValue = ((Integer) obj2).intValue();
                                    return AssistRestoreAppKt.AppScreen$lambda$4(str, c3Var3, aVar3, eVar5, eVar6, fVar, i5, i6, (f0.p) obj, iIntValue);
                                }
                            };
                        }
                    }
                    i7 |= 24576;
                    eVar4 = eVar2;
                    if ((196608 & i5) == 0) {
                        if (vVar2.h(fVar)) {
                            i14 = 131072;
                        } else {
                            i14 = 65536;
                        }
                        i7 |= i14;
                    }
                    if ((74899 & i7) != 74898) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (vVar2.N(i7 & 1, z2)) {
                        if (i15 != 0) {
                            c3Var2 = null;
                        }
                        if (i8 != 0) {
                            aVar3 = null;
                        } else {
                            aVar3 = aVar2;
                        }
                        if (i10 != 0) {
                            eVar7 = null;
                        } else {
                            eVar7 = eVar3;
                        }
                        if (i12 == 0) {
                        }
                        vVar = vVar2;
                        o2.a(androidx.compose.foundation.layout.c.c(r0.m.f5680a, 1.0f), n0.i.c(1846647441, new i0(str, aVar3, eVar7), vVar2), n0.i.c(-1298487568, new f0.y(3, eVar8), vVar2), n0.i.c(-148655281, new f0.y(4, c3Var2), vVar2), null, 0, ((c0.s) vVar2.j(c0.u.f1329a)).f1257n, 0L, new r.b0(), n0.i.c(1374184934, new e0(fVar, 1), vVar2), vVar, 805309878);
                        a4.e eVar10 = eVar7;
                        eVar6 = eVar8;
                        c3Var3 = c3Var2;
                        eVar5 = eVar10;
                    } else {
                        vVar = vVar2;
                        vVar.Q();
                        c3Var3 = c3Var2;
                        aVar3 = aVar2;
                        eVar5 = eVar3;
                        eVar6 = eVar4;
                    }
                    b2VarR = vVar.r();
                    if (b2VarR != null) {
                        b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.j0
                            @Override // a4.e
                            public final Object invoke(Object obj, Object obj2) {
                                int iIntValue = ((Integer) obj2).intValue();
                                return AssistRestoreAppKt.AppScreen$lambda$4(str, c3Var3, aVar3, eVar5, eVar6, fVar, i5, i6, (f0.p) obj, iIntValue);
                            }
                        };
                    }
                }
                i7 |= 3072;
                eVar3 = eVar;
                i12 = i6 & 16;
                if (i12 != 0) {
                    if ((i5 & 24576) == 0) {
                        eVar4 = eVar2;
                        if (vVar2.h(eVar4)) {
                            i13 = 16384;
                        } else {
                            i13 = 8192;
                        }
                        i7 |= i13;
                    }
                    if ((196608 & i5) == 0) {
                        if (vVar2.h(fVar)) {
                            i14 = 131072;
                        } else {
                            i14 = 65536;
                        }
                        i7 |= i14;
                    }
                    if ((74899 & i7) != 74898) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (vVar2.N(i7 & 1, z2)) {
                        if (i15 != 0) {
                            c3Var2 = null;
                        }
                        if (i8 != 0) {
                            aVar3 = null;
                        } else {
                            aVar3 = aVar2;
                        }
                        if (i10 != 0) {
                            eVar7 = null;
                        } else {
                            eVar7 = eVar3;
                        }
                        if (i12 == 0) {
                        }
                        vVar = vVar2;
                        o2.a(androidx.compose.foundation.layout.c.c(r0.m.f5680a, 1.0f), n0.i.c(1846647441, new i0(str, aVar3, eVar7), vVar2), n0.i.c(-1298487568, new f0.y(3, eVar8), vVar2), n0.i.c(-148655281, new f0.y(4, c3Var2), vVar2), null, 0, ((c0.s) vVar2.j(c0.u.f1329a)).f1257n, 0L, new r.b0(), n0.i.c(1374184934, new e0(fVar, 1), vVar2), vVar, 805309878);
                        a4.e eVar11 = eVar7;
                        eVar6 = eVar8;
                        c3Var3 = c3Var2;
                        eVar5 = eVar11;
                    } else {
                        vVar = vVar2;
                        vVar.Q();
                        c3Var3 = c3Var2;
                        aVar3 = aVar2;
                        eVar5 = eVar3;
                        eVar6 = eVar4;
                    }
                    b2VarR = vVar.r();
                    if (b2VarR != null) {
                        b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.j0
                            @Override // a4.e
                            public final Object invoke(Object obj, Object obj2) {
                                int iIntValue = ((Integer) obj2).intValue();
                                return AssistRestoreAppKt.AppScreen$lambda$4(str, c3Var3, aVar3, eVar5, eVar6, fVar, i5, i6, (f0.p) obj, iIntValue);
                            }
                        };
                    }
                }
                i7 |= 24576;
                eVar4 = eVar2;
                if ((196608 & i5) == 0) {
                    if (vVar2.h(fVar)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i7 |= i14;
                }
                if ((74899 & i7) != 74898) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (vVar2.N(i7 & 1, z2)) {
                    if (i15 != 0) {
                        c3Var2 = null;
                    }
                    if (i8 != 0) {
                        aVar3 = null;
                    } else {
                        aVar3 = aVar2;
                    }
                    if (i10 != 0) {
                        eVar7 = null;
                    } else {
                        eVar7 = eVar3;
                    }
                    if (i12 == 0) {
                    }
                    vVar = vVar2;
                    o2.a(androidx.compose.foundation.layout.c.c(r0.m.f5680a, 1.0f), n0.i.c(1846647441, new i0(str, aVar3, eVar7), vVar2), n0.i.c(-1298487568, new f0.y(3, eVar8), vVar2), n0.i.c(-148655281, new f0.y(4, c3Var2), vVar2), null, 0, ((c0.s) vVar2.j(c0.u.f1329a)).f1257n, 0L, new r.b0(), n0.i.c(1374184934, new e0(fVar, 1), vVar2), vVar, 805309878);
                    a4.e eVar12 = eVar7;
                    eVar6 = eVar8;
                    c3Var3 = c3Var2;
                    eVar5 = eVar12;
                } else {
                    vVar = vVar2;
                    vVar.Q();
                    c3Var3 = c3Var2;
                    aVar3 = aVar2;
                    eVar5 = eVar3;
                    eVar6 = eVar4;
                }
                b2VarR = vVar.r();
                if (b2VarR != null) {
                    b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.j0
                        @Override // a4.e
                        public final Object invoke(Object obj, Object obj2) {
                            int iIntValue = ((Integer) obj2).intValue();
                            return AssistRestoreAppKt.AppScreen$lambda$4(str, c3Var3, aVar3, eVar5, eVar6, fVar, i5, i6, (f0.p) obj, iIntValue);
                        }
                    };
                }
            }
            i7 |= 384;
            aVar2 = aVar;
            i10 = i6 & 8;
            if (i10 != 0) {
                if ((i5 & 3072) == 0) {
                    eVar3 = eVar;
                    if (vVar2.h(eVar3)) {
                        i11 = 2048;
                    } else {
                        i11 = 1024;
                    }
                    i7 |= i11;
                }
                i12 = i6 & 16;
                if (i12 != 0) {
                    if ((i5 & 24576) == 0) {
                        eVar4 = eVar2;
                        if (vVar2.h(eVar4)) {
                            i13 = 16384;
                        } else {
                            i13 = 8192;
                        }
                        i7 |= i13;
                    }
                    if ((196608 & i5) == 0) {
                        if (vVar2.h(fVar)) {
                            i14 = 131072;
                        } else {
                            i14 = 65536;
                        }
                        i7 |= i14;
                    }
                    if ((74899 & i7) != 74898) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (vVar2.N(i7 & 1, z2)) {
                        if (i15 != 0) {
                            c3Var2 = null;
                        }
                        if (i8 != 0) {
                            aVar3 = null;
                        } else {
                            aVar3 = aVar2;
                        }
                        if (i10 != 0) {
                            eVar7 = null;
                        } else {
                            eVar7 = eVar3;
                        }
                        if (i12 == 0) {
                        }
                        vVar = vVar2;
                        o2.a(androidx.compose.foundation.layout.c.c(r0.m.f5680a, 1.0f), n0.i.c(1846647441, new i0(str, aVar3, eVar7), vVar2), n0.i.c(-1298487568, new f0.y(3, eVar8), vVar2), n0.i.c(-148655281, new f0.y(4, c3Var2), vVar2), null, 0, ((c0.s) vVar2.j(c0.u.f1329a)).f1257n, 0L, new r.b0(), n0.i.c(1374184934, new e0(fVar, 1), vVar2), vVar, 805309878);
                        a4.e eVar13 = eVar7;
                        eVar6 = eVar8;
                        c3Var3 = c3Var2;
                        eVar5 = eVar13;
                    } else {
                        vVar = vVar2;
                        vVar.Q();
                        c3Var3 = c3Var2;
                        aVar3 = aVar2;
                        eVar5 = eVar3;
                        eVar6 = eVar4;
                    }
                    b2VarR = vVar.r();
                    if (b2VarR != null) {
                        b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.j0
                            @Override // a4.e
                            public final Object invoke(Object obj, Object obj2) {
                                int iIntValue = ((Integer) obj2).intValue();
                                return AssistRestoreAppKt.AppScreen$lambda$4(str, c3Var3, aVar3, eVar5, eVar6, fVar, i5, i6, (f0.p) obj, iIntValue);
                            }
                        };
                    }
                }
                i7 |= 24576;
                eVar4 = eVar2;
                if ((196608 & i5) == 0) {
                    if (vVar2.h(fVar)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i7 |= i14;
                }
                if ((74899 & i7) != 74898) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (vVar2.N(i7 & 1, z2)) {
                    if (i15 != 0) {
                        c3Var2 = null;
                    }
                    if (i8 != 0) {
                        aVar3 = null;
                    } else {
                        aVar3 = aVar2;
                    }
                    if (i10 != 0) {
                        eVar7 = null;
                    } else {
                        eVar7 = eVar3;
                    }
                    if (i12 == 0) {
                    }
                    vVar = vVar2;
                    o2.a(androidx.compose.foundation.layout.c.c(r0.m.f5680a, 1.0f), n0.i.c(1846647441, new i0(str, aVar3, eVar7), vVar2), n0.i.c(-1298487568, new f0.y(3, eVar8), vVar2), n0.i.c(-148655281, new f0.y(4, c3Var2), vVar2), null, 0, ((c0.s) vVar2.j(c0.u.f1329a)).f1257n, 0L, new r.b0(), n0.i.c(1374184934, new e0(fVar, 1), vVar2), vVar, 805309878);
                    a4.e eVar14 = eVar7;
                    eVar6 = eVar8;
                    c3Var3 = c3Var2;
                    eVar5 = eVar14;
                } else {
                    vVar = vVar2;
                    vVar.Q();
                    c3Var3 = c3Var2;
                    aVar3 = aVar2;
                    eVar5 = eVar3;
                    eVar6 = eVar4;
                }
                b2VarR = vVar.r();
                if (b2VarR != null) {
                    b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.j0
                        @Override // a4.e
                        public final Object invoke(Object obj, Object obj2) {
                            int iIntValue = ((Integer) obj2).intValue();
                            return AssistRestoreAppKt.AppScreen$lambda$4(str, c3Var3, aVar3, eVar5, eVar6, fVar, i5, i6, (f0.p) obj, iIntValue);
                        }
                    };
                }
            }
            i7 |= 3072;
            eVar3 = eVar;
            i12 = i6 & 16;
            if (i12 != 0) {
                if ((i5 & 24576) == 0) {
                    eVar4 = eVar2;
                    if (vVar2.h(eVar4)) {
                        i13 = 16384;
                    } else {
                        i13 = 8192;
                    }
                    i7 |= i13;
                }
                if ((196608 & i5) == 0) {
                    if (vVar2.h(fVar)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i7 |= i14;
                }
                if ((74899 & i7) != 74898) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (vVar2.N(i7 & 1, z2)) {
                    if (i15 != 0) {
                        c3Var2 = null;
                    }
                    if (i8 != 0) {
                        aVar3 = null;
                    } else {
                        aVar3 = aVar2;
                    }
                    if (i10 != 0) {
                        eVar7 = null;
                    } else {
                        eVar7 = eVar3;
                    }
                    if (i12 == 0) {
                    }
                    vVar = vVar2;
                    o2.a(androidx.compose.foundation.layout.c.c(r0.m.f5680a, 1.0f), n0.i.c(1846647441, new i0(str, aVar3, eVar7), vVar2), n0.i.c(-1298487568, new f0.y(3, eVar8), vVar2), n0.i.c(-148655281, new f0.y(4, c3Var2), vVar2), null, 0, ((c0.s) vVar2.j(c0.u.f1329a)).f1257n, 0L, new r.b0(), n0.i.c(1374184934, new e0(fVar, 1), vVar2), vVar, 805309878);
                    a4.e eVar15 = eVar7;
                    eVar6 = eVar8;
                    c3Var3 = c3Var2;
                    eVar5 = eVar15;
                } else {
                    vVar = vVar2;
                    vVar.Q();
                    c3Var3 = c3Var2;
                    aVar3 = aVar2;
                    eVar5 = eVar3;
                    eVar6 = eVar4;
                }
                b2VarR = vVar.r();
                if (b2VarR != null) {
                    b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.j0
                        @Override // a4.e
                        public final Object invoke(Object obj, Object obj2) {
                            int iIntValue = ((Integer) obj2).intValue();
                            return AssistRestoreAppKt.AppScreen$lambda$4(str, c3Var3, aVar3, eVar5, eVar6, fVar, i5, i6, (f0.p) obj, iIntValue);
                        }
                    };
                }
            }
            i7 |= 24576;
            eVar4 = eVar2;
            if ((196608 & i5) == 0) {
                if (vVar2.h(fVar)) {
                    i14 = 131072;
                } else {
                    i14 = 65536;
                }
                i7 |= i14;
            }
            if ((74899 & i7) != 74898) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (vVar2.N(i7 & 1, z2)) {
                if (i15 != 0) {
                    c3Var2 = null;
                }
                if (i8 != 0) {
                    aVar3 = null;
                } else {
                    aVar3 = aVar2;
                }
                if (i10 != 0) {
                    eVar7 = null;
                } else {
                    eVar7 = eVar3;
                }
                if (i12 == 0) {
                }
                vVar = vVar2;
                o2.a(androidx.compose.foundation.layout.c.c(r0.m.f5680a, 1.0f), n0.i.c(1846647441, new i0(str, aVar3, eVar7), vVar2), n0.i.c(-1298487568, new f0.y(3, eVar8), vVar2), n0.i.c(-148655281, new f0.y(4, c3Var2), vVar2), null, 0, ((c0.s) vVar2.j(c0.u.f1329a)).f1257n, 0L, new r.b0(), n0.i.c(1374184934, new e0(fVar, 1), vVar2), vVar, 805309878);
                a4.e eVar16 = eVar7;
                eVar6 = eVar8;
                c3Var3 = c3Var2;
                eVar5 = eVar16;
            } else {
                vVar = vVar2;
                vVar.Q();
                c3Var3 = c3Var2;
                aVar3 = aVar2;
                eVar5 = eVar3;
                eVar6 = eVar4;
            }
            b2VarR = vVar.r();
            if (b2VarR != null) {
                b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.j0
                    @Override // a4.e
                    public final Object invoke(Object obj, Object obj2) {
                        int iIntValue = ((Integer) obj2).intValue();
                        return AssistRestoreAppKt.AppScreen$lambda$4(str, c3Var3, aVar3, eVar5, eVar6, fVar, i5, i6, (f0.p) obj, iIntValue);
                    }
                };
            }
        }
        i7 |= 48;
        c3Var2 = c3Var;
        i8 = i6 & 4;
        if (i8 != 0) {
            if ((i5 & 384) == 0) {
                aVar2 = aVar;
                if (vVar2.h(aVar2)) {
                    i9 = 256;
                } else {
                    i9 = 128;
                }
                i7 |= i9;
            }
            i10 = i6 & 8;
            if (i10 != 0) {
                if ((i5 & 3072) == 0) {
                    eVar3 = eVar;
                    if (vVar2.h(eVar3)) {
                        i11 = 2048;
                    } else {
                        i11 = 1024;
                    }
                    i7 |= i11;
                }
                i12 = i6 & 16;
                if (i12 != 0) {
                    if ((i5 & 24576) == 0) {
                        eVar4 = eVar2;
                        if (vVar2.h(eVar4)) {
                            i13 = 16384;
                        } else {
                            i13 = 8192;
                        }
                        i7 |= i13;
                    }
                    if ((196608 & i5) == 0) {
                        if (vVar2.h(fVar)) {
                            i14 = 131072;
                        } else {
                            i14 = 65536;
                        }
                        i7 |= i14;
                    }
                    if ((74899 & i7) != 74898) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (vVar2.N(i7 & 1, z2)) {
                        if (i15 != 0) {
                            c3Var2 = null;
                        }
                        if (i8 != 0) {
                            aVar3 = null;
                        } else {
                            aVar3 = aVar2;
                        }
                        if (i10 != 0) {
                            eVar7 = null;
                        } else {
                            eVar7 = eVar3;
                        }
                        if (i12 == 0) {
                        }
                        vVar = vVar2;
                        o2.a(androidx.compose.foundation.layout.c.c(r0.m.f5680a, 1.0f), n0.i.c(1846647441, new i0(str, aVar3, eVar7), vVar2), n0.i.c(-1298487568, new f0.y(3, eVar8), vVar2), n0.i.c(-148655281, new f0.y(4, c3Var2), vVar2), null, 0, ((c0.s) vVar2.j(c0.u.f1329a)).f1257n, 0L, new r.b0(), n0.i.c(1374184934, new e0(fVar, 1), vVar2), vVar, 805309878);
                        a4.e eVar17 = eVar7;
                        eVar6 = eVar8;
                        c3Var3 = c3Var2;
                        eVar5 = eVar17;
                    } else {
                        vVar = vVar2;
                        vVar.Q();
                        c3Var3 = c3Var2;
                        aVar3 = aVar2;
                        eVar5 = eVar3;
                        eVar6 = eVar4;
                    }
                    b2VarR = vVar.r();
                    if (b2VarR != null) {
                        b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.j0
                            @Override // a4.e
                            public final Object invoke(Object obj, Object obj2) {
                                int iIntValue = ((Integer) obj2).intValue();
                                return AssistRestoreAppKt.AppScreen$lambda$4(str, c3Var3, aVar3, eVar5, eVar6, fVar, i5, i6, (f0.p) obj, iIntValue);
                            }
                        };
                    }
                }
                i7 |= 24576;
                eVar4 = eVar2;
                if ((196608 & i5) == 0) {
                    if (vVar2.h(fVar)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i7 |= i14;
                }
                if ((74899 & i7) != 74898) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (vVar2.N(i7 & 1, z2)) {
                    if (i15 != 0) {
                        c3Var2 = null;
                    }
                    if (i8 != 0) {
                        aVar3 = null;
                    } else {
                        aVar3 = aVar2;
                    }
                    if (i10 != 0) {
                        eVar7 = null;
                    } else {
                        eVar7 = eVar3;
                    }
                    if (i12 == 0) {
                    }
                    vVar = vVar2;
                    o2.a(androidx.compose.foundation.layout.c.c(r0.m.f5680a, 1.0f), n0.i.c(1846647441, new i0(str, aVar3, eVar7), vVar2), n0.i.c(-1298487568, new f0.y(3, eVar8), vVar2), n0.i.c(-148655281, new f0.y(4, c3Var2), vVar2), null, 0, ((c0.s) vVar2.j(c0.u.f1329a)).f1257n, 0L, new r.b0(), n0.i.c(1374184934, new e0(fVar, 1), vVar2), vVar, 805309878);
                    a4.e eVar18 = eVar7;
                    eVar6 = eVar8;
                    c3Var3 = c3Var2;
                    eVar5 = eVar18;
                } else {
                    vVar = vVar2;
                    vVar.Q();
                    c3Var3 = c3Var2;
                    aVar3 = aVar2;
                    eVar5 = eVar3;
                    eVar6 = eVar4;
                }
                b2VarR = vVar.r();
                if (b2VarR != null) {
                    b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.j0
                        @Override // a4.e
                        public final Object invoke(Object obj, Object obj2) {
                            int iIntValue = ((Integer) obj2).intValue();
                            return AssistRestoreAppKt.AppScreen$lambda$4(str, c3Var3, aVar3, eVar5, eVar6, fVar, i5, i6, (f0.p) obj, iIntValue);
                        }
                    };
                }
            }
            i7 |= 3072;
            eVar3 = eVar;
            i12 = i6 & 16;
            if (i12 != 0) {
                if ((i5 & 24576) == 0) {
                    eVar4 = eVar2;
                    if (vVar2.h(eVar4)) {
                        i13 = 16384;
                    } else {
                        i13 = 8192;
                    }
                    i7 |= i13;
                }
                if ((196608 & i5) == 0) {
                    if (vVar2.h(fVar)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i7 |= i14;
                }
                if ((74899 & i7) != 74898) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (vVar2.N(i7 & 1, z2)) {
                    if (i15 != 0) {
                        c3Var2 = null;
                    }
                    if (i8 != 0) {
                        aVar3 = null;
                    } else {
                        aVar3 = aVar2;
                    }
                    if (i10 != 0) {
                        eVar7 = null;
                    } else {
                        eVar7 = eVar3;
                    }
                    if (i12 == 0) {
                    }
                    vVar = vVar2;
                    o2.a(androidx.compose.foundation.layout.c.c(r0.m.f5680a, 1.0f), n0.i.c(1846647441, new i0(str, aVar3, eVar7), vVar2), n0.i.c(-1298487568, new f0.y(3, eVar8), vVar2), n0.i.c(-148655281, new f0.y(4, c3Var2), vVar2), null, 0, ((c0.s) vVar2.j(c0.u.f1329a)).f1257n, 0L, new r.b0(), n0.i.c(1374184934, new e0(fVar, 1), vVar2), vVar, 805309878);
                    a4.e eVar19 = eVar7;
                    eVar6 = eVar8;
                    c3Var3 = c3Var2;
                    eVar5 = eVar19;
                } else {
                    vVar = vVar2;
                    vVar.Q();
                    c3Var3 = c3Var2;
                    aVar3 = aVar2;
                    eVar5 = eVar3;
                    eVar6 = eVar4;
                }
                b2VarR = vVar.r();
                if (b2VarR != null) {
                    b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.j0
                        @Override // a4.e
                        public final Object invoke(Object obj, Object obj2) {
                            int iIntValue = ((Integer) obj2).intValue();
                            return AssistRestoreAppKt.AppScreen$lambda$4(str, c3Var3, aVar3, eVar5, eVar6, fVar, i5, i6, (f0.p) obj, iIntValue);
                        }
                    };
                }
            }
            i7 |= 24576;
            eVar4 = eVar2;
            if ((196608 & i5) == 0) {
                if (vVar2.h(fVar)) {
                    i14 = 131072;
                } else {
                    i14 = 65536;
                }
                i7 |= i14;
            }
            if ((74899 & i7) != 74898) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (vVar2.N(i7 & 1, z2)) {
                if (i15 != 0) {
                    c3Var2 = null;
                }
                if (i8 != 0) {
                    aVar3 = null;
                } else {
                    aVar3 = aVar2;
                }
                if (i10 != 0) {
                    eVar7 = null;
                } else {
                    eVar7 = eVar3;
                }
                if (i12 == 0) {
                }
                vVar = vVar2;
                o2.a(androidx.compose.foundation.layout.c.c(r0.m.f5680a, 1.0f), n0.i.c(1846647441, new i0(str, aVar3, eVar7), vVar2), n0.i.c(-1298487568, new f0.y(3, eVar8), vVar2), n0.i.c(-148655281, new f0.y(4, c3Var2), vVar2), null, 0, ((c0.s) vVar2.j(c0.u.f1329a)).f1257n, 0L, new r.b0(), n0.i.c(1374184934, new e0(fVar, 1), vVar2), vVar, 805309878);
                a4.e eVar110 = eVar7;
                eVar6 = eVar8;
                c3Var3 = c3Var2;
                eVar5 = eVar110;
            } else {
                vVar = vVar2;
                vVar.Q();
                c3Var3 = c3Var2;
                aVar3 = aVar2;
                eVar5 = eVar3;
                eVar6 = eVar4;
            }
            b2VarR = vVar.r();
            if (b2VarR != null) {
                b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.j0
                    @Override // a4.e
                    public final Object invoke(Object obj, Object obj2) {
                        int iIntValue = ((Integer) obj2).intValue();
                        return AssistRestoreAppKt.AppScreen$lambda$4(str, c3Var3, aVar3, eVar5, eVar6, fVar, i5, i6, (f0.p) obj, iIntValue);
                    }
                };
            }
        }
        i7 |= 384;
        aVar2 = aVar;
        i10 = i6 & 8;
        if (i10 != 0) {
            if ((i5 & 3072) == 0) {
                eVar3 = eVar;
                if (vVar2.h(eVar3)) {
                    i11 = 2048;
                } else {
                    i11 = 1024;
                }
                i7 |= i11;
            }
            i12 = i6 & 16;
            if (i12 != 0) {
                if ((i5 & 24576) == 0) {
                    eVar4 = eVar2;
                    if (vVar2.h(eVar4)) {
                        i13 = 16384;
                    } else {
                        i13 = 8192;
                    }
                    i7 |= i13;
                }
                if ((196608 & i5) == 0) {
                    if (vVar2.h(fVar)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i7 |= i14;
                }
                if ((74899 & i7) != 74898) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (vVar2.N(i7 & 1, z2)) {
                    if (i15 != 0) {
                        c3Var2 = null;
                    }
                    if (i8 != 0) {
                        aVar3 = null;
                    } else {
                        aVar3 = aVar2;
                    }
                    if (i10 != 0) {
                        eVar7 = null;
                    } else {
                        eVar7 = eVar3;
                    }
                    if (i12 == 0) {
                    }
                    vVar = vVar2;
                    o2.a(androidx.compose.foundation.layout.c.c(r0.m.f5680a, 1.0f), n0.i.c(1846647441, new i0(str, aVar3, eVar7), vVar2), n0.i.c(-1298487568, new f0.y(3, eVar8), vVar2), n0.i.c(-148655281, new f0.y(4, c3Var2), vVar2), null, 0, ((c0.s) vVar2.j(c0.u.f1329a)).f1257n, 0L, new r.b0(), n0.i.c(1374184934, new e0(fVar, 1), vVar2), vVar, 805309878);
                    a4.e eVar111 = eVar7;
                    eVar6 = eVar8;
                    c3Var3 = c3Var2;
                    eVar5 = eVar111;
                } else {
                    vVar = vVar2;
                    vVar.Q();
                    c3Var3 = c3Var2;
                    aVar3 = aVar2;
                    eVar5 = eVar3;
                    eVar6 = eVar4;
                }
                b2VarR = vVar.r();
                if (b2VarR != null) {
                    b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.j0
                        @Override // a4.e
                        public final Object invoke(Object obj, Object obj2) {
                            int iIntValue = ((Integer) obj2).intValue();
                            return AssistRestoreAppKt.AppScreen$lambda$4(str, c3Var3, aVar3, eVar5, eVar6, fVar, i5, i6, (f0.p) obj, iIntValue);
                        }
                    };
                }
            }
            i7 |= 24576;
            eVar4 = eVar2;
            if ((196608 & i5) == 0) {
                if (vVar2.h(fVar)) {
                    i14 = 131072;
                } else {
                    i14 = 65536;
                }
                i7 |= i14;
            }
            if ((74899 & i7) != 74898) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (vVar2.N(i7 & 1, z2)) {
                if (i15 != 0) {
                    c3Var2 = null;
                }
                if (i8 != 0) {
                    aVar3 = null;
                } else {
                    aVar3 = aVar2;
                }
                if (i10 != 0) {
                    eVar7 = null;
                } else {
                    eVar7 = eVar3;
                }
                if (i12 == 0) {
                }
                vVar = vVar2;
                o2.a(androidx.compose.foundation.layout.c.c(r0.m.f5680a, 1.0f), n0.i.c(1846647441, new i0(str, aVar3, eVar7), vVar2), n0.i.c(-1298487568, new f0.y(3, eVar8), vVar2), n0.i.c(-148655281, new f0.y(4, c3Var2), vVar2), null, 0, ((c0.s) vVar2.j(c0.u.f1329a)).f1257n, 0L, new r.b0(), n0.i.c(1374184934, new e0(fVar, 1), vVar2), vVar, 805309878);
                a4.e eVar112 = eVar7;
                eVar6 = eVar8;
                c3Var3 = c3Var2;
                eVar5 = eVar112;
            } else {
                vVar = vVar2;
                vVar.Q();
                c3Var3 = c3Var2;
                aVar3 = aVar2;
                eVar5 = eVar3;
                eVar6 = eVar4;
            }
            b2VarR = vVar.r();
            if (b2VarR != null) {
                b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.j0
                    @Override // a4.e
                    public final Object invoke(Object obj, Object obj2) {
                        int iIntValue = ((Integer) obj2).intValue();
                        return AssistRestoreAppKt.AppScreen$lambda$4(str, c3Var3, aVar3, eVar5, eVar6, fVar, i5, i6, (f0.p) obj, iIntValue);
                    }
                };
            }
        }
        i7 |= 3072;
        eVar3 = eVar;
        i12 = i6 & 16;
        if (i12 != 0) {
            if ((i5 & 24576) == 0) {
                eVar4 = eVar2;
                if (vVar2.h(eVar4)) {
                    i13 = 16384;
                } else {
                    i13 = 8192;
                }
                i7 |= i13;
            }
            if ((196608 & i5) == 0) {
                if (vVar2.h(fVar)) {
                    i14 = 131072;
                } else {
                    i14 = 65536;
                }
                i7 |= i14;
            }
            if ((74899 & i7) != 74898) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (vVar2.N(i7 & 1, z2)) {
                if (i15 != 0) {
                    c3Var2 = null;
                }
                if (i8 != 0) {
                    aVar3 = null;
                } else {
                    aVar3 = aVar2;
                }
                if (i10 != 0) {
                    eVar7 = null;
                } else {
                    eVar7 = eVar3;
                }
                if (i12 == 0) {
                }
                vVar = vVar2;
                o2.a(androidx.compose.foundation.layout.c.c(r0.m.f5680a, 1.0f), n0.i.c(1846647441, new i0(str, aVar3, eVar7), vVar2), n0.i.c(-1298487568, new f0.y(3, eVar8), vVar2), n0.i.c(-148655281, new f0.y(4, c3Var2), vVar2), null, 0, ((c0.s) vVar2.j(c0.u.f1329a)).f1257n, 0L, new r.b0(), n0.i.c(1374184934, new e0(fVar, 1), vVar2), vVar, 805309878);
                a4.e eVar113 = eVar7;
                eVar6 = eVar8;
                c3Var3 = c3Var2;
                eVar5 = eVar113;
            } else {
                vVar = vVar2;
                vVar.Q();
                c3Var3 = c3Var2;
                aVar3 = aVar2;
                eVar5 = eVar3;
                eVar6 = eVar4;
            }
            b2VarR = vVar.r();
            if (b2VarR != null) {
                b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.j0
                    @Override // a4.e
                    public final Object invoke(Object obj, Object obj2) {
                        int iIntValue = ((Integer) obj2).intValue();
                        return AssistRestoreAppKt.AppScreen$lambda$4(str, c3Var3, aVar3, eVar5, eVar6, fVar, i5, i6, (f0.p) obj, iIntValue);
                    }
                };
            }
        }
        i7 |= 24576;
        eVar4 = eVar2;
        if ((196608 & i5) == 0) {
            if (vVar2.h(fVar)) {
                i14 = 131072;
            } else {
                i14 = 65536;
            }
            i7 |= i14;
        }
        if ((74899 & i7) != 74898) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (vVar2.N(i7 & 1, z2)) {
            if (i15 != 0) {
                c3Var2 = null;
            }
            if (i8 != 0) {
                aVar3 = null;
            } else {
                aVar3 = aVar2;
            }
            if (i10 != 0) {
                eVar7 = null;
            } else {
                eVar7 = eVar3;
            }
            if (i12 == 0) {
            }
            vVar = vVar2;
            o2.a(androidx.compose.foundation.layout.c.c(r0.m.f5680a, 1.0f), n0.i.c(1846647441, new i0(str, aVar3, eVar7), vVar2), n0.i.c(-1298487568, new f0.y(3, eVar8), vVar2), n0.i.c(-148655281, new f0.y(4, c3Var2), vVar2), null, 0, ((c0.s) vVar2.j(c0.u.f1329a)).f1257n, 0L, new r.b0(), n0.i.c(1374184934, new e0(fVar, 1), vVar2), vVar, 805309878);
            a4.e eVar114 = eVar7;
            eVar6 = eVar8;
            c3Var3 = c3Var2;
            eVar5 = eVar114;
        } else {
            vVar = vVar2;
            vVar.Q();
            c3Var3 = c3Var2;
            aVar3 = aVar2;
            eVar5 = eVar3;
            eVar6 = eVar4;
        }
        b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.j0
                @Override // a4.e
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return AssistRestoreAppKt.AppScreen$lambda$4(str, c3Var3, aVar3, eVar5, eVar6, fVar, i5, i6, (f0.p) obj, iIntValue);
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AppScreen$lambda$0(String str, a4.a aVar, a4.e eVar, f0.p pVar, int i5) {
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 3) != 2)) {
            AppTopBar(str, aVar, eVar, vVar, 0);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AppScreen$lambda$1(a4.e eVar, f0.p pVar, int i5) {
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 3) != 2)) {
            if (eVar == null) {
                vVar.V(1527309929);
            } else {
                vVar.V(1988930712);
                eVar.invoke(vVar, 0);
            }
            vVar.p(false);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AppScreen$lambda$2(c3 c3Var, f0.p pVar, int i5) {
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 3) != 2)) {
            if (c3Var != null) {
                vVar.V(-1123803351);
                h2.f(c3Var, null, null, vVar, 0);
            } else {
                vVar.V(-478140525);
            }
            vVar.p(false);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AppScreen$lambda$3(a4.f fVar, r.l0 l0Var, f0.p pVar, int i5) {
        b4.l.e("insets", l0Var);
        if ((i5 & 6) == 0) {
            i5 |= ((f0.v) pVar).f(l0Var) ? 4 : 2;
        }
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 19) != 18)) {
            fVar.b(h1.c.S(androidx.compose.foundation.layout.c.c(androidx.compose.foundation.layout.a.e(r0.m.f5680a, l0Var), 1.0f), h1.c.L(vVar)), vVar, 0);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AppScreen$lambda$4(String str, c3 c3Var, a4.a aVar, a4.e eVar, a4.e eVar2, a4.f fVar, int i5, int i6, f0.p pVar, int i7) {
        AppScreen(str, c3Var, aVar, eVar, eVar2, fVar, pVar, f0.l.z(i5 | 1), i6);
        return p3.n.f5026a;
    }

    private static final void AppTopBar(final String str, final a4.a aVar, final a4.e eVar, f0.p pVar, final int i5) {
        int i6;
        f0.v vVar = (f0.v) pVar;
        vVar.X(942166924);
        if ((i5 & 6) == 0) {
            i6 = (vVar.f(str) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            i6 |= vVar.h(aVar) ? 32 : 16;
        }
        if ((i5 & 384) == 0) {
            i6 |= vVar.h(eVar) ? 256 : 128;
        }
        if (vVar.N(i6 & 1, (i6 & 147) != 146)) {
            o3.a(null, null, ((c0.s) vVar.j(c0.u.f1329a)).f1257n, 0L, 0.0f, 0.0f, n0.i.c(-548972079, new i0(aVar, str, eVar), vVar), vVar, 12582912, 123);
        } else {
            vVar.Q();
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.k0
                @Override // a4.e
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return AssistRestoreAppKt.AppTopBar$lambda$1(str, aVar, eVar, i5, (f0.p) obj, iIntValue);
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AppTopBar$lambda$0(a4.a aVar, String str, a4.e eVar, f0.p pVar, int i5) {
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 3) != 2)) {
            FillElement fillElement = androidx.compose.foundation.layout.c.f113a;
            WeakHashMap weakHashMap = a1.f5506u;
            r.a aVar2 = r.b.e(vVar).f5512f;
            o1.g gVar = c1.f5542a;
            r0.p pVarD = androidx.compose.foundation.layout.c.d(r0.a.a(fillElement, new s3(3, aVar2)), 64);
            float f5 = 4;
            r0.p pVarH = androidx.compose.foundation.layout.a.h(pVarD, f5, 0.0f, 2);
            r.s0 s0VarA = r.q0.a(r.j.f5571a, r0.b.f5663n, vVar, 48);
            int iHashCode = Long.hashCode(vVar.S);
            w1 w1VarL = vVar.l();
            r0.p pVarC = r0.a.c(vVar, pVarH);
            p1.j.f4892c.getClass();
            p1.n nVar = p1.i.f4867b;
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            f0.l.x(p1.i.f4870e, vVar, s0VarA);
            f0.l.x(p1.i.f4869d, vVar, w1VarL);
            p1.h hVar = p1.i.f4871f;
            if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode))) {
                b.b.i(iHashCode, vVar, iHashCode, hVar);
            }
            f0.l.x(p1.i.f4868c, vVar, pVarC);
            r0.m mVar = r0.m.f5680a;
            if (aVar != null) {
                vVar.V(-1843103376);
                h2.e(aVar, null, false, null, ComposableSingletons$AssistRestoreAppKt.INSTANCE.getLambda$474000685$app_release(), vVar, 196608);
                vVar.p(false);
            } else {
                vVar.V(-1842944656);
                r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 12));
                vVar.p(false);
            }
            y1.h0 h0Var = ((s4) vVar.j(t4.f1328a)).f1286g;
            if (1.0f <= 0.0d) {
                throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
            }
            n4.b(str, androidx.compose.foundation.layout.a.i(new LayoutWeightElement(1.0f), 12, 0.0f, 0.0f, 14), 0L, 0L, 0L, 0L, 2, false, 1, 0, h0Var, vVar, 0, 3120, 55292);
            if (eVar == null) {
                vVar.V(-1842550492);
            } else {
                vVar.V(1741678205);
                eVar.invoke(vVar, 0);
            }
            vVar.p(false);
            r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, f5));
            vVar.p(true);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AppTopBar$lambda$1(String str, a4.a aVar, a4.e eVar, int i5, f0.p pVar, int i6) {
        AppTopBar(str, aVar, eVar, pVar, f0.l.z(i5 | 1));
        return p3.n.f5026a;
    }

    /* JADX WARN: Code duplicated, block: B:152:0x0446 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:153:0x0448  */
    /* JADX WARN: Code duplicated, block: B:156:0x0459  */
    public static final void AssistRestoreApp(f0.p pVar, int i5) {
        c3 c3Var;
        boolean z2;
        boolean zH;
        Object objK;
        Object objK2;
        f0.v vVar = (f0.v) pVar;
        vVar.X(-528682601);
        if (vVar.N(i5 & 1, i5 != 0)) {
            final Context context = (Context) vVar.j(AndroidCompositionLocals_androidKt.f204b);
            Object objK3 = vVar.K();
            f0.h hVar = f0.o.f2281a;
            if (objK3 == hVar) {
                objK3 = f0.l.s(AssistantSnapshot.Companion.load$default(AssistantSnapshot.Companion, context, 0, 2, null));
                vVar.f0(objK3);
            }
            i1 i1Var = (i1) objK3;
            Object objK4 = vVar.K();
            if (objK4 == hVar) {
                objK4 = new SettingsStore(context);
                vVar.f0(objK4);
            }
            final SettingsStore settingsStore = (SettingsStore) objK4;
            Object objK5 = vVar.K();
            if (objK5 == hVar) {
                objK5 = new c3();
                vVar.f0(objK5);
            }
            final c3 c3Var2 = (c3) objK5;
            Object objK6 = vVar.K();
            if (objK6 == hVar) {
                objK6 = f0.l.l(vVar);
                vVar.f0(objK6);
            }
            final i4.v vVar2 = (i4.v) objK6;
            Object objK7 = vVar.K();
            if (objK7 == hVar) {
                objK7 = f0.l.s(ROUTE_ENTRIES);
                vVar.f0(objK7);
            }
            final i1 i1Var2 = (i1) objK7;
            Object objK8 = vVar.K();
            if (objK8 == hVar) {
                objK8 = new p1(1);
                vVar.f0(objK8);
            }
            final h1 h1Var = (h1) objK8;
            Object objK9 = vVar.K();
            if (objK9 == hVar) {
                objK9 = f0.l.s(Boolean.valueOf(App.Companion.getService() != null));
                vVar.f0(objK9);
            }
            i1 i1Var3 = (i1) objK9;
            Object objK10 = vVar.K();
            if (objK10 == hVar) {
                objK10 = f0.l.s(Boolean.valueOf(settingsStore.isEnabled()));
                vVar.f0(objK10);
            }
            i1 i1Var4 = (i1) objK10;
            Object objK11 = vVar.K();
            if (objK11 == hVar) {
                objK11 = f0.l.s(loadChoices(settingsStore));
                vVar.f0(objK11);
            }
            final i1 i1Var5 = (i1) objK11;
            Object objK12 = vVar.K();
            if (objK12 == hVar) {
                objK12 = f0.l.s(Boolean.valueOf(settingsStore.skipOcrPreload()));
                vVar.f0(objK12);
            }
            final i1 i1Var6 = (i1) objK12;
            Object objK13 = vVar.K();
            if (objK13 == hVar) {
                objK13 = f0.l.s(Boolean.valueOf(settingsStore.unblockPageFlags()));
                vVar.f0(objK13);
            }
            final i1 i1Var7 = (i1) objK13;
            Object objK14 = vVar.K();
            if (objK14 == hVar) {
                objK14 = f0.l.s(Boolean.valueOf(settingsStore.spoofGoogleBuild()));
                vVar.f0(objK14);
            }
            final i1 i1Var8 = (i1) objK14;
            Object objK15 = vVar.K();
            if (objK15 == hVar) {
                objK15 = f0.l.s(Boolean.valueOf(settingsStore.handleWhenBarHidden()));
                vVar.f0(objK15);
            }
            final i1 i1Var9 = (i1) objK15;
            Object objK16 = vVar.K();
            if (objK16 == hVar) {
                objK16 = f0.l.s(Boolean.valueOf(settingsStore.doublePressWallet()));
                vVar.f0(objK16);
            }
            final i1 i1Var10 = (i1) objK16;
            Object objK17 = vVar.K();
            if (objK17 == hVar) {
                objK17 = f0.l.s(Boolean.valueOf(LauncherIcon.INSTANCE.isHidden(context)));
                vVar.f0(objK17);
            }
            i1 i1Var11 = (i1) objK17;
            androidx.lifecycle.v vVar3 = (androidx.lifecycle.v) vVar.j(AndroidCompositionLocals_androidKt.getLocalLifecycleOwner());
            boolean zH2 = vVar.h(context) | vVar.h(vVar3);
            Object objK18 = vVar.K();
            if (zH2 || objK18 == hVar) {
                objK18 = new c(vVar3, context, i1Var);
                vVar.f0(objK18);
            }
            f0.l.c(vVar3, (a4.c) objK18, vVar);
            boolean zH3 = vVar.h(settingsStore);
            Object objK19 = vVar.K();
            if (zH3 || objK19 == hVar) {
                objK19 = new j2(settingsStore, i1Var3, i1Var4, i1Var5, i1Var6, i1Var7, i1Var8, i1Var9, i1Var10);
                vVar.f0(objK19);
            }
            f0.l.c(p3.n.f5026a, (a4.c) objK19, vVar);
            boolean zH4 = vVar.h(context);
            Object objK20 = vVar.K();
            if (zH4 || objK20 == hVar) {
                final int i6 = 0;
                objK20 = new a4.a() { // from class: io.github.andrea_lyz.assistrestore.ui.f
                    @Override // a4.a
                    public final Object a() {
                        switch (i6) {
                            case 0:
                                return AssistRestoreAppKt.AssistRestoreApp$lambda$40$0(context);
                            default:
                                return AssistRestoreAppKt.AssistRestoreApp$lambda$43$0(context);
                        }
                    }
                };
                vVar.f0(objK20);
            }
            final a4.a aVar = (a4.a) objK20;
            boolean zH5 = vVar.h(context);
            Object objK21 = vVar.K();
            if (zH5 || objK21 == hVar) {
                objK21 = new c2(3, context);
                vVar.f0(objK21);
            }
            a4.c cVar = (a4.c) objK21;
            boolean zH6 = vVar.h(vVar2);
            Object objK22 = vVar.K();
            if (zH6 || objK22 == hVar) {
                final int i7 = 1;
                objK22 = new a4.c() { // from class: io.github.andrea_lyz.assistrestore.ui.d
                    @Override // a4.c
                    public final Object c(Object obj) {
                        switch (i7) {
                            case 0:
                                return AssistRestoreAppKt.AssistRestoreApp$lambda$59$0((h1) vVar2, (i1) c3Var2, ((Integer) obj).intValue());
                            default:
                                return AssistRestoreAppKt.AssistRestoreApp$lambda$42$0((i4.v) vVar2, (c3) c3Var2, (String) obj);
                        }
                    }
                };
                vVar.f0(objK22);
            }
            a4.c cVar2 = (a4.c) objK22;
            boolean zH7 = vVar.h(context);
            Object objK23 = vVar.K();
            if (zH7 || objK23 == hVar) {
                final int i8 = 1;
                objK23 = new a4.a() { // from class: io.github.andrea_lyz.assistrestore.ui.f
                    @Override // a4.a
                    public final Object a() {
                        switch (i8) {
                            case 0:
                                return AssistRestoreAppKt.AssistRestoreApp$lambda$40$0(context);
                            default:
                                return AssistRestoreAppKt.AssistRestoreApp$lambda$43$0(context);
                        }
                    }
                };
                vVar.f0(objK23);
            }
            a4.a aVar2 = (a4.a) objK23;
            Object objK24 = vVar.K();
            if (objK24 == hVar) {
                objK24 = new h(i1Var2, 0);
                vVar.f0(objK24);
            }
            a4.c cVar3 = (a4.c) objK24;
            String strAssistRestoreApp$lambda$6 = AssistRestoreApp$lambda$6(i1Var2);
            int iHashCode = strAssistRestoreApp$lambda$6.hashCode();
            if (iHashCode == -1349088399) {
                c3Var = c3Var2;
                z2 = false;
                if (strAssistRestoreApp$lambda$6.equals("custom")) {
                    vVar.V(-331245595);
                    Object objK25 = vVar.K();
                    if (objK25 == hVar) {
                        objK25 = new i(i1Var2, 5);
                        vVar.f0(objK25);
                    }
                    h1.c.a(false, (a4.a) objK25, vVar, 48);
                    String str = ENTRY_IDS.get(AssistRestoreApp$lambda$9(h1Var));
                    boolean zH8 = vVar.h(settingsStore);
                    Object objK26 = vVar.K();
                    if (zH8 || objK26 == hVar) {
                        objK26 = new f0.q(settingsStore, i1Var2, i1Var5, 2);
                        vVar.f0(objK26);
                    }
                    CustomTargetScreen(str, settingsStore, (a4.a) objK26, c3Var, cVar2, vVar, 3072);
                    vVar.p(false);
                } else {
                    vVar.V(-1257554861);
                    AssistantSnapshot assistantSnapshotAssistRestoreApp$lambda$1 = AssistRestoreApp$lambda$1(i1Var);
                    List<TargetChoice> listAssistRestoreApp$lambda$18 = AssistRestoreApp$lambda$18(i1Var5);
                    boolean zAssistRestoreApp$lambda$15 = AssistRestoreApp$lambda$15(i1Var4);
                    zH = vVar.h(settingsStore) | vVar.f(aVar);
                    objK = vVar.K();
                    if (zH) {
                        objK = new c(settingsStore, aVar, i1Var4, 1);
                        vVar.f0(objK);
                    } else {
                        objK = new c(settingsStore, aVar, i1Var4, 1);
                        vVar.f0(objK);
                    }
                    a4.c cVar4 = (a4.c) objK;
                    objK2 = vVar.K();
                    if (objK2 == hVar) {
                        final int i9 = 0;
                        objK2 = new a4.c() { // from class: io.github.andrea_lyz.assistrestore.ui.d
                            @Override // a4.c
                            public final Object c(Object obj) {
                                switch (i9) {
                                    case 0:
                                        return AssistRestoreAppKt.AssistRestoreApp$lambda$59$0((h1) h1Var, (i1) i1Var2, ((Integer) obj).intValue());
                                    default:
                                        return AssistRestoreAppKt.AssistRestoreApp$lambda$42$0((i4.v) h1Var, (c3) i1Var2, (String) obj);
                                }
                            }
                        };
                        vVar.f0(objK2);
                    }
                    EntriesScreen(assistantSnapshotAssistRestoreApp$lambda$1, listAssistRestoreApp$lambda$18, zAssistRestoreApp$lambda$15, cVar4, (a4.c) objK2, aVar2, AssistRestoreApp$lambda$12(i1Var3), c3Var, 0, cVar3, vVar, 918577152);
                    vVar.p(z2);
                }
            } else if (iHashCode == -880905839) {
                z2 = false;
                if (strAssistRestoreApp$lambda$6.equals(ROUTE_TARGET)) {
                    vVar.V(-332152407);
                    Object objK27 = vVar.K();
                    if (objK27 == hVar) {
                        objK27 = new i(i1Var2, 0);
                        vVar.f0(objK27);
                    }
                    h1.c.a(false, (a4.a) objK27, vVar, 48);
                    int iAssistRestoreApp$lambda$9 = AssistRestoreApp$lambda$9(h1Var);
                    Object objK28 = vVar.K();
                    if (objK28 == hVar) {
                        objK28 = new c2(4, h1Var);
                        vVar.f0(objK28);
                    }
                    a4.c cVar5 = (a4.c) objK28;
                    TargetChoice targetChoice = AssistRestoreApp$lambda$18(i1Var5).get(AssistRestoreApp$lambda$9(h1Var));
                    boolean zH9 = vVar.h(settingsStore) | vVar.f(aVar);
                    Object objK29 = vVar.K();
                    if (zH9 || objK29 == hVar) {
                        objK29 = new a4.c() { // from class: io.github.andrea_lyz.assistrestore.ui.j
                            @Override // a4.c
                            public final Object c(Object obj) {
                                return AssistRestoreAppKt.AssistRestoreApp$lambda$47$0(i1Var5, h1Var, settingsStore, aVar, (TargetChoice) obj);
                            }
                        };
                        vVar.f0(objK29);
                    }
                    a4.c cVar6 = (a4.c) objK29;
                    AssistantSnapshot assistantSnapshotAssistRestoreApp$lambda$2 = AssistRestoreApp$lambda$1(i1Var);
                    Object objK30 = vVar.K();
                    if (objK30 == hVar) {
                        objK30 = new i(i1Var2, 1);
                        vVar.f0(objK30);
                    }
                    a4.a aVar3 = (a4.a) objK30;
                    Object objK31 = vVar.K();
                    if (objK31 == hVar) {
                        objK31 = new i(i1Var2, 4);
                        vVar.f0(objK31);
                    }
                    List<String> list = ENTRY_IDS;
                    TargetScreen(iAssistRestoreApp$lambda$9, cVar5, targetChoice, cVar6, assistantSnapshotAssistRestoreApp$lambda$2, aVar3, (a4.a) objK31, b4.l.a(list.get(AssistRestoreApp$lambda$9(h1Var)), AssistConfig.ENTRY_HANDLE), !b4.l.a(list.get(AssistRestoreApp$lambda$9(h1Var)), AssistConfig.ENTRY_HANDLE), b4.l.a(list.get(AssistRestoreApp$lambda$9(h1Var)), AssistConfig.ENTRY_CORNER), c3Var2, vVar, 1769520, 6);
                    vVar.p(false);
                } else {
                    c3Var = c3Var2;
                    vVar.V(-1257554861);
                    AssistantSnapshot assistantSnapshotAssistRestoreApp$lambda$3 = AssistRestoreApp$lambda$1(i1Var);
                    List<TargetChoice> listAssistRestoreApp$lambda$19 = AssistRestoreApp$lambda$18(i1Var5);
                    boolean zAssistRestoreApp$lambda$16 = AssistRestoreApp$lambda$15(i1Var4);
                    zH = vVar.h(settingsStore) | vVar.f(aVar);
                    objK = vVar.K();
                    if (zH) {
                        objK = new c(settingsStore, aVar, i1Var4, 1);
                        vVar.f0(objK);
                    } else {
                        objK = new c(settingsStore, aVar, i1Var4, 1);
                        vVar.f0(objK);
                    }
                    a4.c cVar7 = (a4.c) objK;
                    objK2 = vVar.K();
                    if (objK2 == hVar) {
                        final int i10 = 0;
                        objK2 = new a4.c() { // from class: io.github.andrea_lyz.assistrestore.ui.d
                            @Override // a4.c
                            public final Object c(Object obj) {
                                switch (i10) {
                                    case 0:
                                        return AssistRestoreAppKt.AssistRestoreApp$lambda$59$0((h1) h1Var, (i1) i1Var2, ((Integer) obj).intValue());
                                    default:
                                        return AssistRestoreAppKt.AssistRestoreApp$lambda$42$0((i4.v) h1Var, (c3) i1Var2, (String) obj);
                                }
                            }
                        };
                        vVar.f0(objK2);
                    }
                    EntriesScreen(assistantSnapshotAssistRestoreApp$lambda$3, listAssistRestoreApp$lambda$19, zAssistRestoreApp$lambda$16, cVar7, (a4.c) objK2, aVar2, AssistRestoreApp$lambda$12(i1Var3), c3Var, 0, cVar3, vVar, 918577152);
                    vVar.p(z2);
                }
            } else if (iHashCode == -718837726 && strAssistRestoreApp$lambda$6.equals(ROUTE_ADVANCED)) {
                vVar.V(-1257591256);
                boolean zAssistRestoreApp$lambda$21 = AssistRestoreApp$lambda$21(i1Var6);
                boolean zH10 = vVar.h(settingsStore);
                Object objK32 = vVar.K();
                if (zH10 || objK32 == hVar) {
                    final int i11 = 2;
                    objK32 = new a4.c() { // from class: io.github.andrea_lyz.assistrestore.ui.b
                        @Override // a4.c
                        public final Object c(Object obj) {
                            int i12 = i11;
                            boolean zBooleanValue = ((Boolean) obj).booleanValue();
                            switch (i12) {
                                case 0:
                                    return AssistRestoreAppKt.AssistRestoreApp$lambda$55$0(settingsStore, i1Var6, zBooleanValue);
                                case 1:
                                    return AssistRestoreAppKt.AssistRestoreApp$lambda$56$0(settingsStore, i1Var6, zBooleanValue);
                                case 2:
                                    return AssistRestoreAppKt.AssistRestoreApp$lambda$52$0(settingsStore, i1Var6, zBooleanValue);
                                case 3:
                                    return AssistRestoreAppKt.AssistRestoreApp$lambda$53$0(settingsStore, i1Var6, zBooleanValue);
                                default:
                                    return AssistRestoreAppKt.AssistRestoreApp$lambda$54$0(settingsStore, i1Var6, zBooleanValue);
                            }
                        }
                    };
                    vVar.f0(objK32);
                }
                a4.c cVar8 = (a4.c) objK32;
                boolean zAssistRestoreApp$lambda$24 = AssistRestoreApp$lambda$24(i1Var7);
                boolean zH11 = vVar.h(settingsStore);
                Object objK33 = vVar.K();
                if (zH11 || objK33 == hVar) {
                    final int i12 = 3;
                    objK33 = new a4.c() { // from class: io.github.andrea_lyz.assistrestore.ui.b
                        @Override // a4.c
                        public final Object c(Object obj) {
                            int i13 = i12;
                            boolean zBooleanValue = ((Boolean) obj).booleanValue();
                            switch (i13) {
                                case 0:
                                    return AssistRestoreAppKt.AssistRestoreApp$lambda$55$0(settingsStore, i1Var7, zBooleanValue);
                                case 1:
                                    return AssistRestoreAppKt.AssistRestoreApp$lambda$56$0(settingsStore, i1Var7, zBooleanValue);
                                case 2:
                                    return AssistRestoreAppKt.AssistRestoreApp$lambda$52$0(settingsStore, i1Var7, zBooleanValue);
                                case 3:
                                    return AssistRestoreAppKt.AssistRestoreApp$lambda$53$0(settingsStore, i1Var7, zBooleanValue);
                                default:
                                    return AssistRestoreAppKt.AssistRestoreApp$lambda$54$0(settingsStore, i1Var7, zBooleanValue);
                            }
                        }
                    };
                    vVar.f0(objK33);
                }
                a4.c cVar9 = (a4.c) objK33;
                boolean zAssistRestoreApp$lambda$27 = AssistRestoreApp$lambda$27(i1Var8);
                boolean zH12 = vVar.h(settingsStore);
                Object objK34 = vVar.K();
                if (zH12 || objK34 == hVar) {
                    final int i13 = 4;
                    objK34 = new a4.c() { // from class: io.github.andrea_lyz.assistrestore.ui.b
                        @Override // a4.c
                        public final Object c(Object obj) {
                            int i14 = i13;
                            boolean zBooleanValue = ((Boolean) obj).booleanValue();
                            switch (i14) {
                                case 0:
                                    return AssistRestoreAppKt.AssistRestoreApp$lambda$55$0(settingsStore, i1Var8, zBooleanValue);
                                case 1:
                                    return AssistRestoreAppKt.AssistRestoreApp$lambda$56$0(settingsStore, i1Var8, zBooleanValue);
                                case 2:
                                    return AssistRestoreAppKt.AssistRestoreApp$lambda$52$0(settingsStore, i1Var8, zBooleanValue);
                                case 3:
                                    return AssistRestoreAppKt.AssistRestoreApp$lambda$53$0(settingsStore, i1Var8, zBooleanValue);
                                default:
                                    return AssistRestoreAppKt.AssistRestoreApp$lambda$54$0(settingsStore, i1Var8, zBooleanValue);
                            }
                        }
                    };
                    vVar.f0(objK34);
                }
                a4.c cVar10 = (a4.c) objK34;
                boolean zAssistRestoreApp$lambda$30 = AssistRestoreApp$lambda$30(i1Var9);
                boolean zH13 = vVar.h(settingsStore);
                Object objK35 = vVar.K();
                if (zH13 || objK35 == hVar) {
                    final int i14 = 0;
                    objK35 = new a4.c() { // from class: io.github.andrea_lyz.assistrestore.ui.b
                        @Override // a4.c
                        public final Object c(Object obj) {
                            int i15 = i14;
                            boolean zBooleanValue = ((Boolean) obj).booleanValue();
                            switch (i15) {
                                case 0:
                                    return AssistRestoreAppKt.AssistRestoreApp$lambda$55$0(settingsStore, i1Var9, zBooleanValue);
                                case 1:
                                    return AssistRestoreAppKt.AssistRestoreApp$lambda$56$0(settingsStore, i1Var9, zBooleanValue);
                                case 2:
                                    return AssistRestoreAppKt.AssistRestoreApp$lambda$52$0(settingsStore, i1Var9, zBooleanValue);
                                case 3:
                                    return AssistRestoreAppKt.AssistRestoreApp$lambda$53$0(settingsStore, i1Var9, zBooleanValue);
                                default:
                                    return AssistRestoreAppKt.AssistRestoreApp$lambda$54$0(settingsStore, i1Var9, zBooleanValue);
                            }
                        }
                    };
                    vVar.f0(objK35);
                }
                a4.c cVar11 = (a4.c) objK35;
                boolean zAssistRestoreApp$lambda$33 = AssistRestoreApp$lambda$33(i1Var10);
                boolean zH14 = vVar.h(settingsStore);
                Object objK36 = vVar.K();
                if (zH14 || objK36 == hVar) {
                    final int i15 = 1;
                    objK36 = new a4.c() { // from class: io.github.andrea_lyz.assistrestore.ui.b
                        @Override // a4.c
                        public final Object c(Object obj) {
                            int i16 = i15;
                            boolean zBooleanValue = ((Boolean) obj).booleanValue();
                            switch (i16) {
                                case 0:
                                    return AssistRestoreAppKt.AssistRestoreApp$lambda$55$0(settingsStore, i1Var10, zBooleanValue);
                                case 1:
                                    return AssistRestoreAppKt.AssistRestoreApp$lambda$56$0(settingsStore, i1Var10, zBooleanValue);
                                case 2:
                                    return AssistRestoreAppKt.AssistRestoreApp$lambda$52$0(settingsStore, i1Var10, zBooleanValue);
                                case 3:
                                    return AssistRestoreAppKt.AssistRestoreApp$lambda$53$0(settingsStore, i1Var10, zBooleanValue);
                                default:
                                    return AssistRestoreAppKt.AssistRestoreApp$lambda$54$0(settingsStore, i1Var10, zBooleanValue);
                            }
                        }
                    };
                    vVar.f0(objK36);
                }
                a4.c cVar12 = (a4.c) objK36;
                boolean zAssistRestoreApp$lambda$36 = AssistRestoreApp$lambda$36(i1Var11);
                boolean zH15 = vVar.h(context) | vVar.f(cVar);
                Object objK37 = vVar.K();
                if (zH15 || objK37 == hVar) {
                    objK37 = new c(context, cVar, i1Var11, 0);
                    vVar.f0(objK37);
                }
                AdvancedScreen(zAssistRestoreApp$lambda$21, cVar8, zAssistRestoreApp$lambda$24, cVar9, zAssistRestoreApp$lambda$27, cVar10, zAssistRestoreApp$lambda$30, cVar11, zAssistRestoreApp$lambda$33, cVar12, zAssistRestoreApp$lambda$36, (a4.c) objK37, 1, cVar3, vVar, 0, 3456);
                vVar.p(false);
            } else {
                c3Var = c3Var2;
                z2 = false;
                vVar.V(-1257554861);
                AssistantSnapshot assistantSnapshotAssistRestoreApp$lambda$4 = AssistRestoreApp$lambda$1(i1Var);
                List<TargetChoice> listAssistRestoreApp$lambda$110 = AssistRestoreApp$lambda$18(i1Var5);
                boolean zAssistRestoreApp$lambda$17 = AssistRestoreApp$lambda$15(i1Var4);
                zH = vVar.h(settingsStore) | vVar.f(aVar);
                objK = vVar.K();
                if (zH || objK == hVar) {
                    objK = new c(settingsStore, aVar, i1Var4, 1);
                    vVar.f0(objK);
                }
                a4.c cVar13 = (a4.c) objK;
                objK2 = vVar.K();
                if (objK2 == hVar) {
                    final int i16 = 0;
                    objK2 = new a4.c() { // from class: io.github.andrea_lyz.assistrestore.ui.d
                        @Override // a4.c
                        public final Object c(Object obj) {
                            switch (i16) {
                                case 0:
                                    return AssistRestoreAppKt.AssistRestoreApp$lambda$59$0((h1) h1Var, (i1) i1Var2, ((Integer) obj).intValue());
                                default:
                                    return AssistRestoreAppKt.AssistRestoreApp$lambda$42$0((i4.v) h1Var, (c3) i1Var2, (String) obj);
                            }
                        }
                    };
                    vVar.f0(objK2);
                }
                EntriesScreen(assistantSnapshotAssistRestoreApp$lambda$4, listAssistRestoreApp$lambda$110, zAssistRestoreApp$lambda$17, cVar13, (a4.c) objK2, aVar2, AssistRestoreApp$lambda$12(i1Var3), c3Var, 0, cVar3, vVar, 918577152);
                vVar.p(z2);
            }
        } else {
            vVar.Q();
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new e(i5, 0);
        }
    }

    private static final AssistantSnapshot AssistRestoreApp$lambda$1(i1 i1Var) {
        return (AssistantSnapshot) i1Var.getValue();
    }

    private static final void AssistRestoreApp$lambda$10(h1 h1Var, int i5) {
        ((p1) h1Var).h(i5);
    }

    private static final boolean AssistRestoreApp$lambda$12(i1 i1Var) {
        return ((Boolean) i1Var.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void AssistRestoreApp$lambda$13(i1 i1Var, boolean z2) {
        i1Var.setValue(Boolean.valueOf(z2));
    }

    private static final boolean AssistRestoreApp$lambda$15(i1 i1Var) {
        return ((Boolean) i1Var.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void AssistRestoreApp$lambda$16(i1 i1Var, boolean z2) {
        i1Var.setValue(Boolean.valueOf(z2));
    }

    private static final List<TargetChoice> AssistRestoreApp$lambda$18(i1 i1Var) {
        return (List) i1Var.getValue();
    }

    private static final boolean AssistRestoreApp$lambda$21(i1 i1Var) {
        return ((Boolean) i1Var.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void AssistRestoreApp$lambda$22(i1 i1Var, boolean z2) {
        i1Var.setValue(Boolean.valueOf(z2));
    }

    private static final boolean AssistRestoreApp$lambda$24(i1 i1Var) {
        return ((Boolean) i1Var.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void AssistRestoreApp$lambda$25(i1 i1Var, boolean z2) {
        i1Var.setValue(Boolean.valueOf(z2));
    }

    private static final boolean AssistRestoreApp$lambda$27(i1 i1Var) {
        return ((Boolean) i1Var.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void AssistRestoreApp$lambda$28(i1 i1Var, boolean z2) {
        i1Var.setValue(Boolean.valueOf(z2));
    }

    private static final boolean AssistRestoreApp$lambda$30(i1 i1Var) {
        return ((Boolean) i1Var.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void AssistRestoreApp$lambda$31(i1 i1Var, boolean z2) {
        i1Var.setValue(Boolean.valueOf(z2));
    }

    private static final boolean AssistRestoreApp$lambda$33(i1 i1Var) {
        return ((Boolean) i1Var.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void AssistRestoreApp$lambda$34(i1 i1Var, boolean z2) {
        i1Var.setValue(Boolean.valueOf(z2));
    }

    private static final boolean AssistRestoreApp$lambda$36(i1 i1Var) {
        return ((Boolean) i1Var.getValue()).booleanValue();
    }

    private static final void AssistRestoreApp$lambda$37(i1 i1Var, boolean z2) {
        i1Var.setValue(Boolean.valueOf(z2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final f0.p0 AssistRestoreApp$lambda$38$0(final androidx.lifecycle.v vVar, final Context context, final i1 i1Var, f0.q0 q0Var) {
        b4.l.e("$this$DisposableEffect", q0Var);
        final androidx.lifecycle.t tVar = new androidx.lifecycle.t() { // from class: io.github.andrea_lyz.assistrestore.ui.n0
            @Override // androidx.lifecycle.t
            public final void b(androidx.lifecycle.v vVar2, androidx.lifecycle.o oVar) {
                AssistRestoreAppKt.AssistRestoreApp$lambda$38$0$0(context, i1Var, vVar2, oVar);
            }
        };
        vVar.getLifecycle().a(tVar);
        return new f0.p0() { // from class: io.github.andrea_lyz.assistrestore.ui.AssistRestoreAppKt$AssistRestoreApp$lambda$38$0$$inlined$onDispose$1
            @Override // f0.p0
            public void dispose() {
                vVar.getLifecycle().b(tVar);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void AssistRestoreApp$lambda$38$0$0(Context context, i1 i1Var, androidx.lifecycle.v vVar, androidx.lifecycle.o oVar) {
        b4.l.e("<unused var>", vVar);
        b4.l.e("event", oVar);
        if (oVar == androidx.lifecycle.o.ON_RESUME) {
            i1Var.setValue(AssistantSnapshot.Companion.load$default(AssistantSnapshot.Companion, context, 0, 2, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [io.github.andrea_lyz.assistrestore.ui.App$ServiceStateListener, io.github.andrea_lyz.assistrestore.ui.AssistRestoreAppKt$AssistRestoreApp$2$1$listener$1] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public static final f0.p0 AssistRestoreApp$lambda$39$0(final SettingsStore settingsStore, final i1 i1Var, final i1 i1Var2, final i1 i1Var3, final i1 i1Var4, final i1 i1Var5, final i1 i1Var6, final i1 i1Var7, final i1 i1Var8, f0.q0 q0Var) {
        b4.l.e("$this$DisposableEffect", q0Var);
        final ?? r1 = new App.ServiceStateListener() { // from class: io.github.andrea_lyz.assistrestore.ui.AssistRestoreAppKt$AssistRestoreApp$2$1$listener$1
            @Override // io.github.andrea_lyz.assistrestore.ui.App.ServiceStateListener
            public void onServiceStateChanged(XposedService xposedService) {
                settingsStore.bind(xposedService);
                AssistRestoreAppKt.AssistRestoreApp$lambda$13(i1Var, xposedService != null);
                AssistRestoreAppKt.AssistRestoreApp$lambda$16(i1Var2, settingsStore.isEnabled());
                i1Var3.setValue(AssistRestoreAppKt.loadChoices(settingsStore));
                AssistRestoreAppKt.AssistRestoreApp$lambda$22(i1Var4, settingsStore.skipOcrPreload());
                AssistRestoreAppKt.AssistRestoreApp$lambda$25(i1Var5, settingsStore.unblockPageFlags());
                AssistRestoreAppKt.AssistRestoreApp$lambda$28(i1Var6, settingsStore.spoofGoogleBuild());
                AssistRestoreAppKt.AssistRestoreApp$lambda$31(i1Var7, settingsStore.handleWhenBarHidden());
                AssistRestoreAppKt.AssistRestoreApp$lambda$34(i1Var8, settingsStore.doublePressWallet());
            }
        };
        App.Companion.addServiceStateListener(r1, true);
        return new f0.p0() { // from class: io.github.andrea_lyz.assistrestore.ui.AssistRestoreAppKt$AssistRestoreApp$lambda$39$0$$inlined$onDispose$1
            @Override // f0.p0
            public void dispose() {
                App.Companion.removeServiceStateListener(r1);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AssistRestoreApp$lambda$40$0(Context context) {
        Toast.makeText(context, "已保存：底角那项要重启手机后桌面才会重新判断，之前可能仍保留手势动画", 1).show();
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AssistRestoreApp$lambda$41$0(Context context, boolean z2) {
        Toast.makeText(context, z2 ? "已隐藏桌面图标：仍可从 LSPosed 模块页或系统设置的应用详情打开本应用" : "已重新显示桌面图标", 1).show();
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AssistRestoreApp$lambda$42$0(i4.v vVar, c3 c3Var, String str) {
        b4.l.e("message", str);
        i4.y.n(vVar, null, new AssistRestoreAppKt$AssistRestoreApp$notify$1$1$1(c3Var, str, null), 3);
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AssistRestoreApp$lambda$43$0(Context context) {
        Object objR;
        List listH = p3.j.H(new Intent("android.intent.action.MANAGE_DEFAULT_APP").putExtra("android.intent.extra.ROLE_NAME", "android.app.role.ASSISTANT"), new Intent("android.settings.VOICE_INPUT_SETTINGS"), new Intent("android.settings.MANAGE_DEFAULT_APPS_SETTINGS"));
        boolean zIsEmpty = listH.isEmpty();
        p3.n nVar = p3.n.f5026a;
        if (zIsEmpty) {
            Toast.makeText(context, "系统里没有可打开的助理设置页", 0).show();
        } else {
            Iterator it = listH.iterator();
            while (it.hasNext()) {
                try {
                    context.startActivity((Intent) it.next());
                    objR = nVar;
                } catch (Throwable th) {
                    objR = p3.j.r(th);
                }
                if (!(objR instanceof p3.h)) {
                }
            }
            Toast.makeText(context, "系统里没有可打开的助理设置页", 0).show();
        }
        return nVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AssistRestoreApp$lambda$44$0(i1 i1Var, int i5) {
        String str = ROUTE_ENTRIES;
        if (i5 != 0 && i5 == 1) {
            str = ROUTE_ADVANCED;
        }
        i1Var.setValue(str);
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AssistRestoreApp$lambda$45$0(i1 i1Var) {
        i1Var.setValue(ROUTE_ENTRIES);
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AssistRestoreApp$lambda$46$0(h1 h1Var, int i5) {
        AssistRestoreApp$lambda$10(h1Var, i5);
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AssistRestoreApp$lambda$47$0(i1 i1Var, h1 h1Var, SettingsStore settingsStore, a4.a aVar, TargetChoice targetChoice) {
        b4.l.e("picked", targetChoice);
        ArrayList arrayListG0 = q3.l.g0(AssistRestoreApp$lambda$18(i1Var));
        arrayListG0.set(AssistRestoreApp$lambda$9(h1Var), targetChoice);
        i1Var.setValue(arrayListG0);
        AssistRestoreApp$persistChoice(settingsStore, aVar, AssistRestoreApp$lambda$9(h1Var), targetChoice);
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AssistRestoreApp$lambda$48$0(i1 i1Var) {
        i1Var.setValue(ROUTE_ENTRIES);
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AssistRestoreApp$lambda$49$0(i1 i1Var) {
        i1Var.setValue("custom");
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AssistRestoreApp$lambda$50$0(i1 i1Var) {
        i1Var.setValue(ROUTE_TARGET);
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AssistRestoreApp$lambda$51$0(SettingsStore settingsStore, i1 i1Var, i1 i1Var2) {
        i1Var.setValue(ROUTE_TARGET);
        i1Var2.setValue(loadChoices(settingsStore));
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AssistRestoreApp$lambda$52$0(SettingsStore settingsStore, i1 i1Var, boolean z2) {
        AssistRestoreApp$lambda$22(i1Var, z2);
        settingsStore.setSkipOcrPreload(z2);
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AssistRestoreApp$lambda$53$0(SettingsStore settingsStore, i1 i1Var, boolean z2) {
        AssistRestoreApp$lambda$25(i1Var, z2);
        settingsStore.setUnblockPageFlags(z2);
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AssistRestoreApp$lambda$54$0(SettingsStore settingsStore, i1 i1Var, boolean z2) {
        AssistRestoreApp$lambda$28(i1Var, z2);
        settingsStore.setSpoofGoogleBuild(z2);
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AssistRestoreApp$lambda$55$0(SettingsStore settingsStore, i1 i1Var, boolean z2) {
        AssistRestoreApp$lambda$31(i1Var, z2);
        settingsStore.setHandleWhenBarHidden(z2);
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AssistRestoreApp$lambda$56$0(SettingsStore settingsStore, i1 i1Var, boolean z2) {
        AssistRestoreApp$lambda$34(i1Var, z2);
        settingsStore.setDoublePressWallet(z2);
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AssistRestoreApp$lambda$57$0(Context context, a4.c cVar, i1 i1Var, boolean z2) {
        AssistRestoreApp$lambda$37(i1Var, z2);
        LauncherIcon.INSTANCE.setHidden(context, z2);
        cVar.c(Boolean.valueOf(z2));
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AssistRestoreApp$lambda$58$0(SettingsStore settingsStore, a4.a aVar, i1 i1Var, boolean z2) {
        AssistRestoreApp$lambda$16(i1Var, z2);
        settingsStore.setEnabled(z2);
        aVar.a();
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AssistRestoreApp$lambda$59$0(h1 h1Var, i1 i1Var, int i5) {
        AssistRestoreApp$lambda$10(h1Var, i5);
        i1Var.setValue(ROUTE_TARGET);
        return p3.n.f5026a;
    }

    private static final String AssistRestoreApp$lambda$6(i1 i1Var) {
        return (String) i1Var.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AssistRestoreApp$lambda$60(int i5, f0.p pVar, int i6) {
        AssistRestoreApp(pVar, f0.l.z(i5 | 1));
        return p3.n.f5026a;
    }

    private static final int AssistRestoreApp$lambda$9(h1 h1Var) {
        return ((p1) h1Var).g();
    }

    private static final void AssistRestoreApp$persistChoice(SettingsStore settingsStore, a4.a aVar, int i5, TargetChoice targetChoice) {
        String str = ENTRY_IDS.get(i5);
        boolean zEquals = AssistConfig.MODE_NONE.equals(settingsStore.mode(str));
        if (b4.l.a(targetChoice, TargetChoice.FollowDefault.INSTANCE)) {
            SettingsStore.setTarget$default(settingsStore, str, AssistConfig.MODE_DEFAULT, null, null, null, null, 60, null);
        } else if (b4.l.a(targetChoice, TargetChoice.CircleToSearch.INSTANCE)) {
            SettingsStore.setTarget$default(settingsStore, str, AssistConfig.MODE_CTS, null, null, null, null, 60, null);
        } else if (targetChoice instanceof TargetChoice.App) {
            SettingsStore.setTarget$default(settingsStore, str, AssistConfig.MODE_APP, ((TargetChoice.App) targetChoice).getPackageName(), null, null, null, 56, null);
        } else if (targetChoice instanceof TargetChoice.Custom) {
            SettingsStore.setTarget$default(settingsStore, str, "custom", null, null, null, null, 60, null);
        } else if (b4.l.a(targetChoice, TargetChoice.Oem.INSTANCE)) {
            SettingsStore.setTarget$default(settingsStore, str, AssistConfig.MODE_OEM, null, null, null, null, 60, null);
        } else {
            if (!b4.l.a(targetChoice, TargetChoice.None.INSTANCE)) {
                throw new i4.p();
            }
            SettingsStore.setTarget$default(settingsStore, str, AssistConfig.MODE_NONE, null, null, null, null, 60, null);
        }
        if (!b4.l.a(str, AssistConfig.ENTRY_CORNER) || zEquals == (targetChoice instanceof TargetChoice.None)) {
            return;
        }
        aVar.a();
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0053  */
    /* JADX WARN: Code duplicated, block: B:31:0x0055  */
    /* JADX WARN: Code duplicated, block: B:34:0x005d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:35:0x005f  */
    /* JADX WARN: Code duplicated, block: B:36:0x0062  */
    /* JADX WARN: Code duplicated, block: B:38:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:41:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:43:? A[RETURN, SYNTHETIC] */
    private static final void AssistantCard(final AssistantSnapshot assistantSnapshot, final a4.a aVar, r0.p pVar, f0.p pVar2, final int i5, final int i6) {
        int i7;
        r0.p pVar3;
        boolean z2;
        final r0.p pVar4;
        b2 b2VarR;
        r0.p pVar5;
        f0.v vVar = (f0.v) pVar2;
        vVar.X(1125744406);
        if ((i5 & 6) == 0) {
            i7 = (vVar.h(assistantSnapshot) ? 4 : 2) | i5;
        } else {
            i7 = i5;
        }
        if ((i5 & 48) == 0) {
            i7 |= vVar.h(aVar) ? 32 : 16;
        }
        int i8 = i6 & 4;
        if (i8 == 0) {
            if ((i5 & 384) == 0) {
                pVar3 = pVar;
                i7 |= vVar.f(pVar3) ? 256 : 128;
            }
            if ((i7 & 147) != 146) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (vVar.N(i7 & 1, z2)) {
                if (i8 != 0) {
                    pVar5 = r0.m.f5680a;
                } else {
                    pVar5 = pVar3;
                }
                h3 h3Var = c0.u.f1329a;
                final long j5 = ((c0.s) vVar.j(h3Var)).f1246c;
                final long j6 = ((c0.s) vVar.j(h3Var)).f1247d;
                h3 h3Var2 = s2.f1277a;
                o3.a(androidx.compose.foundation.a.e(q.f.j(pVar5, ((r2) vVar.j(h3Var2)).f1229e), false, null, aVar, 7), ((r2) vVar.j(h3Var2)).f1229e, j5, 0L, 0.0f, 0.0f, n0.i.c(-1693328677, new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.o0
                    @Override // a4.e
                    public final Object invoke(Object obj, Object obj2) {
                        int iIntValue = ((Integer) obj2).intValue();
                        return AssistRestoreAppKt.AssistantCard$lambda$0(assistantSnapshot, j6, j5, (f0.p) obj, iIntValue);
                    }
                }, vVar), vVar, 12582912, 120);
                pVar4 = pVar5;
            } else {
                vVar.Q();
                pVar4 = pVar3;
            }
            b2VarR = vVar.r();
            if (b2VarR != null) {
                b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.p0
                    @Override // a4.e
                    public final Object invoke(Object obj, Object obj2) {
                        int iIntValue = ((Integer) obj2).intValue();
                        return AssistRestoreAppKt.AssistantCard$lambda$1(assistantSnapshot, aVar, pVar4, i5, i6, (f0.p) obj, iIntValue);
                    }
                };
            }
        }
        i7 |= 384;
        pVar3 = pVar;
        if ((i7 & 147) != 146) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (vVar.N(i7 & 1, z2)) {
            if (i8 != 0) {
                pVar5 = r0.m.f5680a;
            } else {
                pVar5 = pVar3;
            }
            h3 h3Var3 = c0.u.f1329a;
            final long j7 = ((c0.s) vVar.j(h3Var3)).f1246c;
            final long j8 = ((c0.s) vVar.j(h3Var3)).f1247d;
            h3 h3Var4 = s2.f1277a;
            o3.a(androidx.compose.foundation.a.e(q.f.j(pVar5, ((r2) vVar.j(h3Var4)).f1229e), false, null, aVar, 7), ((r2) vVar.j(h3Var4)).f1229e, j7, 0L, 0.0f, 0.0f, n0.i.c(-1693328677, new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.o0
                @Override // a4.e
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return AssistRestoreAppKt.AssistantCard$lambda$0(assistantSnapshot, j8, j7, (f0.p) obj, iIntValue);
                }
            }, vVar), vVar, 12582912, 120);
            pVar4 = pVar5;
        } else {
            vVar.Q();
            pVar4 = pVar3;
        }
        b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.p0
                @Override // a4.e
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return AssistRestoreAppKt.AssistantCard$lambda$1(assistantSnapshot, aVar, pVar4, i5, i6, (f0.p) obj, iIntValue);
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AssistantCard$lambda$0(AssistantSnapshot assistantSnapshot, long j5, long j6, f0.p pVar, int i5) {
        p1.h hVar;
        androidx.compose.foundation.layout.b bVar;
        p1.h hVar2;
        r0.h hVar3 = r0.b.f5657h;
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 3) != 2)) {
            r0.m mVar = r0.m.f5680a;
            r0.p pVarC = androidx.compose.foundation.layout.c.c(mVar, 1.0f);
            r0.h hVar4 = r0.b.f5653d;
            n1.f0 f0VarE = r.o.e(hVar4, false);
            int iHashCode = Long.hashCode(vVar.S);
            w1 w1VarL = vVar.l();
            r0.p pVarC2 = r0.a.c(vVar, pVarC);
            p1.j.f4892c.getClass();
            p1.n nVar = p1.i.f4867b;
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            p1.h hVar5 = p1.i.f4870e;
            f0.l.x(hVar5, vVar, f0VarE);
            p1.h hVar6 = p1.i.f4869d;
            f0.l.x(hVar6, vVar, w1VarL);
            p1.h hVar7 = p1.i.f4871f;
            if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode))) {
                b.b.i(iHashCode, vVar, iHashCode, hVar7);
            }
            p1.h hVar8 = p1.i.f4868c;
            f0.l.x(hVar8, vVar, pVarC2);
            y0.c0 defaultIcon = assistantSnapshot.getDefaultIcon();
            androidx.compose.foundation.layout.b bVar2 = androidx.compose.foundation.layout.b.f112a;
            if (defaultIcon != null) {
                vVar.V(-1054301508);
                d4.a.c(defaultIcon, androidx.compose.foundation.layout.c.c(bVar2.a(hVar3), 0.72f), vVar, 24624);
                vVar.p(false);
                hVar = hVar6;
                hVar2 = hVar5;
                bVar = bVar2;
            } else {
                vVar.V(-1053972071);
                hVar = hVar6;
                bVar = bVar2;
                hVar2 = hVar5;
                c0.q0.b(h1.c.w(), null, androidx.compose.foundation.layout.c.c(bVar2.a(hVar3), 0.72f), y0.u.b(0.22f, j5), vVar, 48, 0);
                vVar.p(false);
            }
            m0ReadabilityScrimRPmYEkk(bVar, j6, vVar, 6);
            r0.p pVarF = androidx.compose.foundation.layout.a.f(bVar.a(hVar4), 20);
            r.v vVarA = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, 0);
            int iHashCode2 = Long.hashCode(vVar.S);
            w1 w1VarL2 = vVar.l();
            r0.p pVarC3 = r0.a.c(vVar, pVarF);
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            f0.l.x(hVar2, vVar, vVarA);
            f0.l.x(hVar, vVar, w1VarL2);
            if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode2))) {
                b.b.i(iHashCode2, vVar, iHashCode2, hVar7);
            }
            f0.l.x(hVar8, vVar, pVarC3);
            String defaultLabel = assistantSnapshot.getDefaultLabel();
            h3 h3Var = t4.f1328a;
            n4.b(defaultLabel, null, j5, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(h3Var)).f1287h, vVar, 0, 3120, 55290);
            r.c.a(vVar, androidx.compose.foundation.layout.c.d(mVar, 2));
            n4.b("当前默认助理 · 点按打开设置", null, y0.u.b(0.8f, j5), 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(h3Var)).f1291l, vVar, 6, 0, 65530);
            vVar.p(true);
            vVar.p(true);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n AssistantCard$lambda$1(AssistantSnapshot assistantSnapshot, a4.a aVar, r0.p pVar, int i5, int i6, f0.p pVar2, int i7) {
        AssistantCard(assistantSnapshot, aVar, pVar, pVar2, f0.l.z(i5 | 1), i6);
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void Chevron(f0.p pVar, int i5) {
        f0.v vVar = (f0.v) pVar;
        vVar.X(686141396);
        if (vVar.N(i5 & 1, i5 != 0)) {
            e1.e eVarB = a.a.f13g;
            if (eVarB == null) {
                e1.d dVar = new e1.d("Rounded.ChevronRight");
                int i6 = e1.a0.f1864a;
                y0.s0 s0Var = new y0.s0(y0.u.f6689b);
                e1.f fVar = new e1.f();
                fVar.h(9.29f, 6.71f);
                fVar.c(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
                fVar.f(13.17f, 12.0f);
                fVar.g(-3.88f, 3.88f);
                fVar.c(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
                fVar.c(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
                fVar.g(4.59f, -4.59f);
                fVar.c(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
                fVar.f(10.7f, 6.7f);
                fVar.c(-0.38f, -0.38f, -1.02f, -0.38f, -1.41f, 0.01f);
                fVar.a();
                e1.d.a(dVar, fVar.f1910d, s0Var);
                eVarB = dVar.b();
                a.a.f13g = eVarB;
            }
            c0.q0.b(eVarB, null, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, vVar, 48, 4);
        } else {
            vVar.Q();
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new e(i5, 2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n Chevron$lambda$0(int i5, f0.p pVar, int i6) {
        Chevron(pVar, f0.l.z(i5 | 1));
        return p3.n.f5026a;
    }

    private static final void CustomTargetScreen(final String str, final SettingsStore settingsStore, final a4.a aVar, final c3 c3Var, final a4.c cVar, f0.p pVar, final int i5) {
        int i6;
        a4.a aVar2;
        f0.v vVar;
        f0.v vVar2 = (f0.v) pVar;
        vVar2.X(1468104537);
        if ((i5 & 6) == 0) {
            i6 = (vVar2.f(str) ? 4 : 2) | i5;
        } else {
            i6 = i5;
        }
        if ((i5 & 48) == 0) {
            i6 |= vVar2.h(settingsStore) ? 32 : 16;
        }
        if ((i5 & 384) == 0) {
            aVar2 = aVar;
            i6 |= vVar2.h(aVar2) ? 256 : 128;
        } else {
            aVar2 = aVar;
        }
        if ((i5 & 3072) == 0) {
            i6 |= vVar2.f(c3Var) ? 2048 : 1024;
        }
        if ((i5 & 24576) == 0) {
            i6 |= vVar2.h(cVar) ? 16384 : 8192;
        }
        if (vVar2.N(i6 & 1, (i6 & 9363) != 9362)) {
            Object objK = vVar2.K();
            f0.h hVar = f0.o.f2281a;
            if (objK == hVar) {
                String strTargetPackage = settingsStore.targetPackage(str);
                if (strTargetPackage.length() == 0) {
                    strTargetPackage = BREENO_PACKAGE;
                }
                objK = f0.l.s(strTargetPackage);
                vVar2.f0(objK);
            }
            final i1 i1Var = (i1) objK;
            Object objK2 = vVar2.K();
            if (objK2 == hVar) {
                objK2 = f0.l.s(settingsStore.targetComponent(str));
                vVar2.f0(objK2);
            }
            final i1 i1Var2 = (i1) objK2;
            Object objK3 = vVar2.K();
            if (objK3 == hVar) {
                String strTargetArgs = settingsStore.targetArgs(str);
                if (strTargetArgs.length() == 0) {
                    strTargetArgs = "action=heytap.intent.action.ACTIVATE_SPEECH_ASSIST&start_type=91";
                }
                objK3 = f0.l.s(strTargetArgs);
                vVar2.f0(objK3);
            }
            final i1 i1Var3 = (i1) objK3;
            final List listH = p3.j.H(AssistConfig.METHOD_AUTO, AssistConfig.METHOD_ASSIST, AssistConfig.METHOD_INTENT);
            final List listH2 = p3.j.H("自动", "ACTION_ASSIST", "显式 Intent");
            Object objK4 = vVar2.K();
            if (objK4 == hVar) {
                int iIndexOf = listH.indexOf(settingsStore.targetMethod(str));
                if (iIndexOf < 0) {
                    iIndexOf = 0;
                }
                p1 p1Var = new p1(iIndexOf);
                vVar2.f0(p1Var);
                objK4 = p1Var;
            }
            final h1 h1Var = (h1) objK4;
            Object objK5 = vVar2.K();
            if (objK5 == hVar) {
                objK5 = f0.l.s("");
                vVar2.f0(objK5);
            }
            final i1 i1Var4 = (i1) objK5;
            int i7 = i6;
            Object objK6 = vVar2.K();
            if (objK6 == hVar) {
                objK6 = f0.l.s(Boolean.FALSE);
                vVar2.f0(objK6);
            }
            final i1 i1Var5 = (i1) objK6;
            final a4.a aVar3 = aVar2;
            vVar = vVar2;
            AppScreen("自定义目标", c3Var, aVar, null, null, n0.i.c(2015205037, new a4.f() { // from class: io.github.andrea_lyz.assistrestore.ui.m
                @Override // a4.f
                public final Object b(Object obj, Object obj2, Object obj3) {
                    int iIntValue = ((Integer) obj3).intValue();
                    return AssistRestoreAppKt.CustomTargetScreen$lambda$18(cVar, settingsStore, str, aVar3, i1Var4, listH, i1Var, i1Var2, h1Var, i1Var3, i1Var5, listH2, (r0.p) obj, (f0.p) obj2, iIntValue);
                }
            }, vVar2), vVar, ((i7 >> 6) & 112) | 196614 | (i7 & 896), 24);
        } else {
            vVar = vVar2;
            vVar.Q();
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.n
                @Override // a4.e
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return AssistRestoreAppKt.CustomTargetScreen$lambda$19(str, settingsStore, aVar, c3Var, cVar, i5, (f0.p) obj, iIntValue);
                }
            };
        }
    }

    private static final String CustomTargetScreen$lambda$1(i1 i1Var) {
        return (String) i1Var.getValue();
    }

    private static final int CustomTargetScreen$lambda$10(h1 h1Var) {
        return ((p1) h1Var).g();
    }

    private static final void CustomTargetScreen$lambda$11(h1 h1Var, int i5) {
        ((p1) h1Var).h(i5);
    }

    private static final String CustomTargetScreen$lambda$13(i1 i1Var) {
        return (String) i1Var.getValue();
    }

    private static final boolean CustomTargetScreen$lambda$16(i1 i1Var) {
        return ((Boolean) i1Var.getValue()).booleanValue();
    }

    private static final void CustomTargetScreen$lambda$17(i1 i1Var, boolean z2) {
        i1Var.setValue(Boolean.valueOf(z2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n CustomTargetScreen$lambda$18(final a4.c cVar, final SettingsStore settingsStore, final String str, final a4.a aVar, i1 i1Var, final List list, final i1 i1Var2, final i1 i1Var3, final h1 h1Var, final i1 i1Var4, i1 i1Var5, List list2, r0.p pVar, f0.p pVar2, int i5) {
        int i6;
        i1 i1Var6;
        p1.h hVar;
        i1 i1Var7;
        int i7;
        i1 i1Var8;
        b4.l.e("modifier", pVar);
        if ((i5 & 6) == 0) {
            i6 = i5 | (((f0.v) pVar2).f(pVar) ? 4 : 2);
        } else {
            i6 = i5;
        }
        f0.v vVar = (f0.v) pVar2;
        if (vVar.N(i6 & 1, (i6 & 19) != 18)) {
            float f5 = 16;
            r0.p pVarH = androidx.compose.foundation.layout.a.h(pVar, f5, 0.0f, 2);
            r.g gVar = r.j.f5572b;
            r0.f fVar = r0.b.f5665p;
            r.v vVarA = r.t.a(gVar, fVar, vVar, 0);
            int iHashCode = Long.hashCode(vVar.S);
            w1 w1VarL = vVar.l();
            r0.p pVarC = r0.a.c(vVar, pVarH);
            p1.j.f4892c.getClass();
            p1.n nVar = p1.i.f4867b;
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            p1.h hVar2 = p1.i.f4870e;
            f0.l.x(hVar2, vVar, vVarA);
            p1.h hVar3 = p1.i.f4869d;
            f0.l.x(hVar3, vVar, w1VarL);
            p1.h hVar4 = p1.i.f4871f;
            if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode))) {
                b.b.i(iHashCode, vVar, iHashCode, hVar4);
            }
            p1.h hVar5 = p1.i.f4868c;
            f0.l.x(hVar5, vVar, pVarC);
            float f6 = 8;
            r0.m mVar = r0.m.f5680a;
            r.c.a(vVar, androidx.compose.foundation.layout.c.d(mVar, f6));
            String strCustomTargetScreen$lambda$13 = CustomTargetScreen$lambda$13(i1Var);
            FillElement fillElement = androidx.compose.foundation.layout.c.f113a;
            Object objK = vVar.K();
            f0.h hVar6 = f0.o.f2281a;
            if (objK == hVar6) {
                i1Var6 = i1Var;
                objK = new h(i1Var6, 1);
                vVar.f0(objK);
            } else {
                i1Var6 = i1Var;
            }
            ComposableSingletons$AssistRestoreAppKt composableSingletons$AssistRestoreAppKt = ComposableSingletons$AssistRestoreAppKt.INSTANCE;
            d2.a(strCustomTargetScreen$lambda$13, (a4.c) objK, fillElement, false, null, composableSingletons$AssistRestoreAppKt.m1getLambda$11935203$app_release(), composableSingletons$AssistRestoreAppKt.m6getLambda$1855570913$app_release(), composableSingletons$AssistRestoreAppKt.getLambda$1577810288$app_release(), null, null, null, false, 0, 3, null, null, vVar, 102236592, 805306752, 7859896);
            r.c.a(vVar, androidx.compose.foundation.layout.c.d(mVar, f6));
            boolean zF = vVar.f(cVar);
            Object objK2 = vVar.K();
            if (zF || objK2 == hVar6) {
                final i1 i1Var9 = i1Var6;
                a4.a aVar2 = new a4.a() { // from class: io.github.andrea_lyz.assistrestore.ui.u
                    @Override // a4.a
                    public final Object a() {
                        return AssistRestoreAppKt.CustomTargetScreen$lambda$18$0$1$0(cVar, list, i1Var9, i1Var2, i1Var3, h1Var, i1Var4);
                    }
                };
                vVar.f0(aVar2);
                objK2 = aVar2;
            }
            float f7 = 56;
            h2.a((a4.a) objK2, androidx.compose.foundation.layout.c.d(fillElement, f7), false, null, null, null, null, composableSingletons$AssistRestoreAppKt.m8getLambda$314331257$app_release(), vVar, 805306416, 508);
            r.c.a(vVar, androidx.compose.foundation.layout.c.d(mVar, f5));
            String strCustomTargetScreen$lambda$1 = CustomTargetScreen$lambda$1(i1Var2);
            Object objK3 = vVar.K();
            if (objK3 == hVar6) {
                objK3 = new h(i1Var2, 2);
                vVar.f0(objK3);
            }
            d2.a(strCustomTargetScreen$lambda$1, (a4.c) objK3, fillElement, false, null, composableSingletons$AssistRestoreAppKt.m10getLambda$782868474$app_release(), composableSingletons$AssistRestoreAppKt.getLambda$1304707464$app_release(), composableSingletons$AssistRestoreAppKt.m7getLambda$2045808999$app_release(), null, null, null, true, 0, 0, null, null, vVar, 102236592, 12583296, 8253112);
            float f8 = 12;
            r.c.a(vVar, androidx.compose.foundation.layout.c.d(mVar, f8));
            String strCustomTargetScreen$lambda$4 = CustomTargetScreen$lambda$4(i1Var3);
            Object objK4 = vVar.K();
            int i8 = 3;
            if (objK4 == hVar6) {
                objK4 = new h(i1Var3, i8);
                vVar.f0(objK4);
            }
            d2.a(strCustomTargetScreen$lambda$4, (a4.c) objK4, fillElement, false, null, composableSingletons$AssistRestoreAppKt.m2getLambda$124485595$app_release(), composableSingletons$AssistRestoreAppKt.getLambda$1963090343$app_release(), composableSingletons$AssistRestoreAppKt.m3getLambda$1387426120$app_release(), null, null, null, true, 0, 0, null, null, vVar, 102236592, 12583296, 8253112);
            r.c.a(vVar, androidx.compose.foundation.layout.c.d(mVar, f8));
            r.v vVarA2 = r.t.a(gVar, fVar, vVar, 0);
            int iHashCode2 = Long.hashCode(vVar.S);
            w1 w1VarL2 = vVar.l();
            r0.p pVarC2 = r0.a.c(vVar, mVar);
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            f0.l.x(hVar2, vVar, vVarA2);
            f0.l.x(hVar3, vVar, w1VarL2);
            if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode2))) {
                hVar = r13;
                b.b.i(iHashCode2, vVar, iHashCode2, hVar);
            } else {
                hVar = hVar4;
            }
            f0.l.x(hVar5, vVar, pVarC2);
            n1.f0 f0VarE = r.o.e(r0.b.f5653d, false);
            int iHashCode3 = Long.hashCode(vVar.S);
            w1 w1VarL3 = vVar.l();
            r0.p pVarC3 = r0.a.c(vVar, mVar);
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            f0.l.x(hVar2, vVar, f0VarE);
            f0.l.x(hVar3, vVar, w1VarL3);
            if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode3))) {
                b.b.i(iHashCode3, vVar, iHashCode3, hVar);
            }
            f0.l.x(hVar5, vVar, pVarC3);
            r0.p pVarD = androidx.compose.foundation.layout.c.d(fillElement, f7);
            h3 h3Var = s2.f1277a;
            r0.p pVarJ = q.f.j(pVarD, ((r2) vVar.j(h3Var)).f1225a);
            h3 h3Var2 = c0.u.f1329a;
            r0.p pVarE = pVarJ.e(new BorderModifierNodeElement(1, new y0.s0(((c0.s) vVar.j(h3Var2)).A), ((r2) vVar.j(h3Var)).f1225a));
            Object objK5 = vVar.K();
            if (objK5 == hVar6) {
                i1Var7 = i1Var5;
                i7 = 2;
                objK5 = new i(i1Var7, i7);
                vVar.f0(objK5);
            } else {
                i1Var7 = i1Var5;
                i7 = 2;
            }
            r0.p pVarH2 = androidx.compose.foundation.layout.a.h(androidx.compose.foundation.a.e(pVarE, false, null, (a4.a) objK5, 7), f5, 0.0f, i7);
            r.s0 s0VarA = r.q0.a(r.j.f5571a, r0.b.f5663n, vVar, 48);
            int iHashCode4 = Long.hashCode(vVar.S);
            w1 w1VarL4 = vVar.l();
            r0.p pVarC4 = r0.a.c(vVar, pVarH2);
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            f0.l.x(hVar2, vVar, s0VarA);
            f0.l.x(hVar3, vVar, w1VarL4);
            if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode4))) {
                b.b.i(iHashCode4, vVar, iHashCode4, hVar);
            }
            f0.l.x(hVar5, vVar, pVarC4);
            e1.e eVarB = a.a.f12f;
            if (eVarB == null) {
                e1.d dVar = new e1.d("Rounded.CallSplit");
                int i9 = e1.a0.f1864a;
                y0.s0 s0Var = new y0.s0(y0.u.f6689b);
                e1.f fVar2 = new e1.f();
                fVar2.h(14.85f, 4.85f);
                fVar2.g(1.44f, 1.44f);
                fVar2.g(-2.88f, 2.88f);
                fVar2.g(1.42f, 1.42f);
                fVar2.g(2.88f, -2.88f);
                fVar2.g(1.44f, 1.44f);
                fVar2.c(0.31f, 0.31f, 0.85f, 0.09f, 0.85f, -0.36f);
                fVar2.k(4.5f);
                fVar2.c(0.0f, -0.28f, -0.22f, -0.5f, -0.5f, -0.5f);
                fVar2.e(-4.29f);
                fVar2.c(-0.45f, 0.0f, -0.67f, 0.54f, -0.36f, 0.85f);
                fVar2.a();
                fVar2.h(8.79f, 4.0f);
                fVar2.d(4.5f);
                fVar2.c(-0.28f, 0.0f, -0.5f, 0.22f, -0.5f, 0.5f);
                fVar2.l(4.29f);
                fVar2.c(0.0f, 0.45f, 0.54f, 0.67f, 0.85f, 0.35f);
                fVar2.f(6.29f, 7.7f);
                fVar2.f(11.0f, 12.4f);
                fVar2.k(19.0f);
                fVar2.c(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
                fVar2.j(1.0f, -0.45f, 1.0f, -1.0f);
                fVar2.l(-7.0f);
                fVar2.c(0.0f, -0.26f, -0.11f, -0.52f, -0.29f, -0.71f);
                fVar2.g(-5.0f, -5.01f);
                fVar2.g(1.44f, -1.44f);
                fVar2.c(0.31f, -0.3f, 0.09f, -0.84f, -0.36f, -0.84f);
                fVar2.a();
                e1.d.a(dVar, fVar2.f1910d, s0Var);
                eVarB = dVar.b();
                a.a.f12f = eVarB;
            }
            c0.q0.b(eVarB, null, null, ((c0.s) vVar.j(h3Var2)).f1262s, vVar, 48, 4);
            r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, f5));
            String str2 = (String) list2.get(CustomTargetScreen$lambda$10(h1Var));
            h3 h3Var3 = t4.f1328a;
            y1.h0 h0Var = ((s4) vVar.j(h3Var3)).f1289j;
            if (1.0f <= 0.0d) {
                throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
            }
            n4.b(str2, new LayoutWeightElement(1.0f), 0L, 0L, 0L, 0L, 0, false, 0, 0, h0Var, vVar, 0, 0, 65532);
            e1.e eVarB2 = d4.a.f1631b;
            if (eVarB2 == null) {
                e1.d dVar2 = new e1.d("Rounded.ArrowDropDown");
                int i10 = e1.a0.f1864a;
                y0.s0 s0Var2 = new y0.s0(y0.u.f6689b);
                ArrayList arrayList = new ArrayList(32);
                arrayList.add(new e1.m(8.71f, 11.71f));
                arrayList.add(new e1.q(2.59f, 2.59f));
                arrayList.add(new e1.o(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f));
                arrayList.add(new e1.q(2.59f, -2.59f));
                arrayList.add(new e1.o(0.63f, -0.63f, 0.18f, -1.71f, -0.71f, -1.71f));
                arrayList.add(new e1.k(9.41f));
                arrayList.add(new e1.o(-0.89f, 0.0f, -1.33f, 1.08f, -0.7f, 1.71f));
                arrayList.add(e1.i.f1922b);
                e1.d.a(dVar2, arrayList, s0Var2);
                eVarB2 = dVar2.b();
                d4.a.f1631b = eVarB2;
            }
            c0.q0.b(eVarB2, null, null, ((c0.s) vVar.j(h3Var2)).f1262s, vVar, 48, 4);
            vVar.p(true);
            boolean zCustomTargetScreen$lambda$16 = CustomTargetScreen$lambda$16(i1Var7);
            Object objK6 = vVar.K();
            if (objK6 == hVar6) {
                objK6 = new i(i1Var7, 3);
                vVar.f0(objK6);
            }
            c0.e.a(zCustomTargetScreen$lambda$16, (a4.a) objK6, null, 0L, null, null, null, 0L, 0.0f, 0.0f, n0.i.c(1936993526, new v(list2, h1Var, i1Var7, 0), vVar), vVar, 48);
            vVar.p(true);
            r.c.a(vVar, androidx.compose.foundation.layout.c.d(mVar, 4));
            n4.b("自动：优先该应用声明的助理活动；显式 Intent：按下面的组件与参数启动", androidx.compose.foundation.layout.a.i(mVar, f5, 0.0f, 0.0f, 14), ((c0.s) vVar.j(h3Var2)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(h3Var3)).f1291l, vVar, 54, 0, 65528);
            vVar.p(true);
            r.c.a(vVar, androidx.compose.foundation.layout.c.d(mVar, f8));
            String strCustomTargetScreen$lambda$7 = CustomTargetScreen$lambda$7(i1Var4);
            Object objK7 = vVar.K();
            if (objK7 == hVar6) {
                i1Var8 = i1Var4;
                objK7 = new h(i1Var8, 4);
                vVar.f0(objK7);
            } else {
                i1Var8 = i1Var4;
            }
            d2.a(strCustomTargetScreen$lambda$7, (a4.c) objK7, fillElement, false, null, composableSingletons$AssistRestoreAppKt.getLambda$533897284$app_release(), composableSingletons$AssistRestoreAppKt.m4getLambda$1673494074$app_release(), composableSingletons$AssistRestoreAppKt.m9getLambda$729043241$app_release(), null, null, null, true, 0, 0, null, null, vVar, 102236592, 12583296, 8253112);
            float f9 = 24;
            r.c.a(vVar, androidx.compose.foundation.layout.c.d(mVar, f9));
            boolean zH = vVar.h(settingsStore) | vVar.f(str) | vVar.f(cVar) | vVar.f(aVar);
            Object objK8 = vVar.K();
            if (zH || objK8 == hVar6) {
                final i1 i1Var10 = i1Var8;
                a4.a aVar3 = new a4.a() { // from class: io.github.andrea_lyz.assistrestore.ui.w
                    @Override // a4.a
                    public final Object a() {
                        return AssistRestoreAppKt.CustomTargetScreen$lambda$18$0$6$0(settingsStore, str, list, cVar, aVar, i1Var2, i1Var3, h1Var, i1Var10);
                    }
                };
                vVar.f0(aVar3);
                objK8 = aVar3;
            }
            h2.a((a4.a) objK8, androidx.compose.foundation.layout.c.d(fillElement, f7), false, null, null, null, null, composableSingletons$AssistRestoreAppKt.getLambda$672299760$app_release(), vVar, 805306416, 508);
            r.c.a(vVar, androidx.compose.foundation.layout.c.d(mVar, f9));
            vVar.p(true);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n CustomTargetScreen$lambda$18$0$0$0(i1 i1Var, String str) {
        b4.l.e("it", str);
        i1Var.setValue(str);
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n CustomTargetScreen$lambda$18$0$1$0(a4.c cVar, List list, i1 i1Var, i1 i1Var2, i1 i1Var3, h1 h1Var, i1 i1Var4) {
        PastedIntent intentJson = parseIntentJson(CustomTargetScreen$lambda$13(i1Var));
        if (intentJson == null) {
            cVar.c("没认出 JSON，检查一下格式");
        } else {
            if (intentJson.getPackageName().length() > 0) {
                i1Var2.setValue(intentJson.getPackageName());
            }
            if (intentJson.getClassName().length() > 0) {
                i1Var3.setValue(intentJson.getClassName());
            }
            int iIndexOf = list.indexOf(AssistConfig.METHOD_INTENT);
            if (iIndexOf < 0) {
                iIndexOf = 0;
            }
            CustomTargetScreen$lambda$11(h1Var, iIndexOf);
            StringBuilder sb = new StringBuilder();
            if (intentJson.getAction().length() > 0) {
                sb.append("action=");
                sb.append(intentJson.getAction());
            }
            if (intentJson.getCategory().length() > 0) {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append("category=");
                sb.append(intentJson.getCategory());
            }
            if (intentJson.getExtra().length() > 0) {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(intentJson.getExtra());
            }
            String string = sb.toString();
            b4.l.d("toString(...)", string);
            i1Var4.setValue(string);
            cVar.c("已识别并填入，确认后按「保存目标」");
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n CustomTargetScreen$lambda$18$0$2$0(i1 i1Var, String str) {
        b4.l.e("it", str);
        i1Var.setValue(str);
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n CustomTargetScreen$lambda$18$0$3$0(i1 i1Var, String str) {
        b4.l.e("it", str);
        i1Var.setValue(str);
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n CustomTargetScreen$lambda$18$0$4$0$0$0(i1 i1Var) {
        CustomTargetScreen$lambda$17(i1Var, true);
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n CustomTargetScreen$lambda$18$0$4$0$2$0(i1 i1Var) {
        CustomTargetScreen$lambda$17(i1Var, false);
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n CustomTargetScreen$lambda$18$0$4$0$3(List list, final h1 h1Var, final i1 i1Var, r.w wVar, f0.p pVar, int i5) {
        b4.l.e("$this$DropdownMenu", wVar);
        final int i6 = 0;
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 17) != 16)) {
            for (Object obj : list) {
                int i7 = i6 + 1;
                if (i6 < 0) {
                    p3.j.N();
                    throw null;
                }
                n0.d dVarC = n0.i.c(-773523668, new t((String) obj, 1), vVar);
                boolean zD = vVar.d(i6);
                Object objK = vVar.K();
                if (zD || objK == f0.o.f2281a) {
                    objK = new a4.a() { // from class: io.github.andrea_lyz.assistrestore.ui.d0
                        @Override // a4.a
                        public final Object a() {
                            return AssistRestoreAppKt.CustomTargetScreen$lambda$18$0$4$0$3$0$1$0(i6, h1Var, i1Var);
                        }
                    };
                    vVar.f0(objK);
                }
                c0.e.b(dVarC, (a4.a) objK, null, false, null, null, vVar, 6);
                i6 = i7;
            }
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n CustomTargetScreen$lambda$18$0$4$0$3$0$0(String str, f0.p pVar, int i5) {
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 3) != 2)) {
            n4.b(str, null, 0L, 0L, 0L, 0L, 0, false, 0, 0, null, vVar, 0, 0, 131070);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n CustomTargetScreen$lambda$18$0$4$0$3$0$1$0(int i5, h1 h1Var, i1 i1Var) {
        CustomTargetScreen$lambda$11(h1Var, i5);
        CustomTargetScreen$lambda$17(i1Var, false);
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n CustomTargetScreen$lambda$18$0$5$0(i1 i1Var, String str) {
        b4.l.e("it", str);
        i1Var.setValue(str);
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n CustomTargetScreen$lambda$18$0$6$0(SettingsStore settingsStore, String str, List list, a4.c cVar, a4.a aVar, i1 i1Var, i1 i1Var2, h1 h1Var, i1 i1Var3) {
        settingsStore.setTarget(str, "custom", h4.g.W(CustomTargetScreen$lambda$1(i1Var)).toString(), h4.g.W(CustomTargetScreen$lambda$4(i1Var2)).toString(), (String) list.get(CustomTargetScreen$lambda$10(h1Var)), h4.g.W(CustomTargetScreen$lambda$7(i1Var3)).toString());
        cVar.c("已保存：该入口改为自定义目标");
        cVar.c("已保存：" + settingsStore.mode(str) + " · " + settingsStore.targetPackage(str) + " · " + settingsStore.targetComponent(str) + " · " + settingsStore.targetMethod(str));
        aVar.a();
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n CustomTargetScreen$lambda$19(String str, SettingsStore settingsStore, a4.a aVar, c3 c3Var, a4.c cVar, int i5, f0.p pVar, int i6) {
        CustomTargetScreen(str, settingsStore, aVar, c3Var, cVar, pVar, f0.l.z(i5 | 1));
        return p3.n.f5026a;
    }

    private static final String CustomTargetScreen$lambda$4(i1 i1Var) {
        return (String) i1Var.getValue();
    }

    private static final String CustomTargetScreen$lambda$7(i1 i1Var) {
        return (String) i1Var.getValue();
    }

    private static final void EntriesScreen(final AssistantSnapshot assistantSnapshot, final List<? extends TargetChoice> list, final boolean z2, final a4.c cVar, final a4.c cVar2, final a4.a aVar, final boolean z3, final c3 c3Var, final int i5, final a4.c cVar3, f0.p pVar, final int i6) {
        int i7;
        f0.v vVar = (f0.v) pVar;
        vVar.X(2106951457);
        if ((i6 & 6) == 0) {
            i7 = (vVar.h(assistantSnapshot) ? 4 : 2) | i6;
        } else {
            i7 = i6;
        }
        if ((i6 & 48) == 0) {
            i7 |= vVar.h(list) ? 32 : 16;
        }
        if ((i6 & 384) == 0) {
            i7 |= vVar.g(z2) ? 256 : 128;
        }
        if ((i6 & 3072) == 0) {
            i7 |= vVar.h(cVar) ? 2048 : 1024;
        }
        if ((i6 & 24576) == 0) {
            i7 |= vVar.h(cVar2) ? 16384 : 8192;
        }
        if ((196608 & i6) == 0) {
            i7 |= vVar.h(aVar) ? 131072 : 65536;
        }
        if ((1572864 & i6) == 0) {
            i7 |= vVar.g(z3) ? 1048576 : 524288;
        }
        if ((12582912 & i6) == 0) {
            i7 |= vVar.f(c3Var) ? 8388608 : 4194304;
        }
        if ((100663296 & i6) == 0) {
            i7 |= vVar.d(i5) ? 67108864 : 33554432;
        }
        if ((805306368 & i6) == 0) {
            i7 |= vVar.h(cVar3) ? 536870912 : 268435456;
        }
        if (vVar.N(i7 & 1, (306783379 & i7) != 306783378)) {
            AppScreen("ColorOS 唤语", c3Var, null, null, n0.i.c(-1297825245, new o(i5, 2, cVar3), vVar), n0.i.c(698823757, new a4.f() { // from class: io.github.andrea_lyz.assistrestore.ui.x
                @Override // a4.f
                public final Object b(Object obj, Object obj2, Object obj3) {
                    int iIntValue = ((Integer) obj3).intValue();
                    return AssistRestoreAppKt.EntriesScreen$lambda$1(z3, assistantSnapshot, aVar, list, cVar2, z2, cVar, (r0.p) obj, (f0.p) obj2, iIntValue);
                }
            }, vVar), vVar, ((i7 >> 18) & 112) | 221190, 12);
        } else {
            vVar.Q();
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.y
                @Override // a4.e
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return AssistRestoreAppKt.EntriesScreen$lambda$2(assistantSnapshot, list, z2, cVar, cVar2, aVar, z3, c3Var, i5, cVar3, i6, (f0.p) obj, iIntValue);
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n EntriesScreen$lambda$0(int i5, a4.c cVar, f0.p pVar, int i6) {
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i6 & 1, (i6 & 3) != 2)) {
            AppBottomBar(i5, cVar, vVar, 0);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n EntriesScreen$lambda$1(boolean z2, AssistantSnapshot assistantSnapshot, a4.a aVar, List list, a4.c cVar, final boolean z3, final a4.c cVar2, r0.p pVar, f0.p pVar2, int i5) {
        int i6;
        b4.l.e("modifier", pVar);
        if ((i5 & 6) == 0) {
            i6 = i5 | (((f0.v) pVar2).f(pVar) ? 4 : 2);
        } else {
            i6 = i5;
        }
        f0.v vVar = (f0.v) pVar2;
        if (vVar.N(i6 & 1, (i6 & 19) != 18)) {
            float f5 = 16;
            r0.p pVarH = androidx.compose.foundation.layout.a.h(pVar, f5, 0.0f, 2);
            r.v vVarA = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, 0);
            int iHashCode = Long.hashCode(vVar.S);
            w1 w1VarL = vVar.l();
            r0.p pVarC = r0.a.c(vVar, pVarH);
            p1.j.f4892c.getClass();
            p1.n nVar = p1.i.f4867b;
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            p1.h hVar = p1.i.f4870e;
            f0.l.x(hVar, vVar, vVarA);
            p1.h hVar2 = p1.i.f4869d;
            f0.l.x(hVar2, vVar, w1VarL);
            p1.h hVar3 = p1.i.f4871f;
            if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode))) {
                b.b.i(iHashCode, vVar, iHashCode, hVar3);
            }
            p1.h hVar4 = p1.i.f4868c;
            f0.l.x(hVar4, vVar, pVarC);
            r0.m mVar = r0.m.f5680a;
            r.c.a(vVar, androidx.compose.foundation.layout.c.d(mVar, f5));
            r.s0 s0VarA = r.q0.a(new r.f(f5), r0.b.f5662m, vVar, 6);
            int iHashCode2 = Long.hashCode(vVar.S);
            w1 w1VarL2 = vVar.l();
            r0.p pVarC2 = r0.a.c(vVar, mVar);
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            f0.l.x(hVar, vVar, s0VarA);
            f0.l.x(hVar2, vVar, w1VarL2);
            if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode2))) {
                b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
            }
            f0.l.x(hVar4, vVar, pVarC2);
            if (1.0f <= 0.0d) {
                throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
            }
            ModuleStatusCard(z2, null, androidx.compose.foundation.layout.a.b(new LayoutWeightElement(1.0f)), vVar, 0, 2);
            if (1.0f <= 0.0d) {
                throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
            }
            AssistantCard(assistantSnapshot, aVar, androidx.compose.foundation.layout.a.b(new LayoutWeightElement(1.0f)), vVar, 0, 0);
            vVar.p(true);
            float f6 = 24;
            r.c.a(vVar, androidx.compose.foundation.layout.c.d(mVar, f6));
            n4.b("唤醒入口", null, 0L, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(t4.f1328a)).f1287h, vVar, 6, 0, 65534);
            r.c.a(vVar, androidx.compose.foundation.layout.c.d(mVar, 8));
            SectionCard(null, n0.i.c(-556069892, new v(list, assistantSnapshot, cVar, 1), vVar), vVar, 48, 1);
            r.c.a(vVar, androidx.compose.foundation.layout.c.d(mVar, f5));
            SectionCard(null, n0.i.c(-1145147405, new a4.f() { // from class: io.github.andrea_lyz.assistrestore.ui.h0
                @Override // a4.f
                public final Object b(Object obj, Object obj2, Object obj3) {
                    int iIntValue = ((Integer) obj3).intValue();
                    return AssistRestoreAppKt.EntriesScreen$lambda$1$0$2(z3, cVar2, (r.w) obj, (f0.p) obj2, iIntValue);
                }
            }, vVar), vVar, 48, 1);
            r.c.a(vVar, androidx.compose.foundation.layout.c.d(mVar, f6));
            vVar.p(true);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n EntriesScreen$lambda$1$0$1(List list, AssistantSnapshot assistantSnapshot, a4.c cVar, r.w wVar, f0.p pVar, int i5) {
        b4.l.e("$this$SectionCard", wVar);
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 17) != 16)) {
            int i6 = 0;
            for (Object obj : ENTRY_NAMES) {
                int i7 = i6 + 1;
                if (i6 < 0) {
                    p3.j.N();
                    throw null;
                }
                String str = (String) obj;
                String strDescribeChoice = describeChoice((TargetChoice) list.get(i6), assistantSnapshot);
                e1.e eVar = ENTRY_ICONS.get(i6);
                a4.e eVarM5getLambda$1855166647$app_release = ComposableSingletons$AssistRestoreAppKt.INSTANCE.m5getLambda$1855166647$app_release();
                boolean zF = vVar.f(cVar) | vVar.d(i6);
                Object objK = vVar.K();
                if (zF || objK == f0.o.f2281a) {
                    objK = new s(i6, 1, cVar);
                    vVar.f0(objK);
                }
                ListRow(str, strDescribeChoice, eVar, null, eVarM5getLambda$1855166647$app_release, (a4.a) objK, false, vVar, 24576, 72);
                if (i6 != p3.j.z(ENTRY_NAMES)) {
                    vVar.V(-1579563326);
                    RowDivider(vVar, 0);
                } else {
                    vVar.V(-1721811316);
                }
                vVar.p(false);
                i6 = i7;
            }
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n EntriesScreen$lambda$1$0$1$0$0$0(a4.c cVar, int i5) {
        cVar.c(Integer.valueOf(i5));
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n EntriesScreen$lambda$1$0$2(boolean z2, a4.c cVar, r.w wVar, f0.p pVar, int i5) {
        b4.l.e("$this$SectionCard", wVar);
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 17) != 16)) {
            ListRow("模块接管全部入口", "关闭后三个入口都回到 ColorOS 原始行为", a.a.C(), null, n0.i.c(-1953465882, new g0(z2, cVar, 0), vVar), null, false, vVar, 24630, 104);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n EntriesScreen$lambda$1$0$2$0(boolean z2, a4.c cVar, f0.p pVar, int i5) {
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 3) != 2)) {
            Toggle(z2, cVar, false, vVar, 0, 4);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n EntriesScreen$lambda$2(AssistantSnapshot assistantSnapshot, List list, boolean z2, a4.c cVar, a4.c cVar2, a4.a aVar, boolean z3, c3 c3Var, int i5, a4.c cVar3, int i6, f0.p pVar, int i7) {
        EntriesScreen(assistantSnapshot, list, z2, cVar, cVar2, aVar, z3, c3Var, i5, cVar3, pVar, f0.l.z(i6 | 1));
        return p3.n.f5026a;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x010f A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:103:0x0117  */
    /* JADX WARN: Code duplicated, block: B:106:0x0151  */
    /* JADX WARN: Code duplicated, block: B:107:0x0155  */
    /* JADX WARN: Code duplicated, block: B:110:0x0168  */
    /* JADX WARN: Code duplicated, block: B:112:0x0176  */
    /* JADX WARN: Code duplicated, block: B:115:0x0180  */
    /* JADX WARN: Code duplicated, block: B:116:0x0183  */
    /* JADX WARN: Code duplicated, block: B:119:0x018a  */
    /* JADX WARN: Code duplicated, block: B:121:0x01b2 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:122:0x01b4  */
    /* JADX WARN: Code duplicated, block: B:124:0x01fb  */
    /* JADX WARN: Code duplicated, block: B:125:0x01ff  */
    /* JADX WARN: Code duplicated, block: B:128:0x020c  */
    /* JADX WARN: Code duplicated, block: B:130:0x021a  */
    /* JADX WARN: Code duplicated, block: B:132:0x0253  */
    /* JADX WARN: Code duplicated, block: B:135:0x0275  */
    /* JADX WARN: Code duplicated, block: B:137:0x029b  */
    /* JADX WARN: Code duplicated, block: B:138:0x029f  */
    /* JADX WARN: Code duplicated, block: B:141:0x02ac  */
    /* JADX WARN: Code duplicated, block: B:143:0x02ba  */
    /* JADX WARN: Code duplicated, block: B:146:0x02f3  */
    /* JADX WARN: Code duplicated, block: B:147:0x0330  */
    /* JADX WARN: Code duplicated, block: B:150:0x0340  */
    /* JADX WARN: Code duplicated, block: B:152:0x034a  */
    /* JADX WARN: Code duplicated, block: B:155:0x036a  */
    /* JADX WARN: Code duplicated, block: B:157:0x0372  */
    /* JADX WARN: Code duplicated, block: B:160:0x0383  */
    /* JADX WARN: Code duplicated, block: B:162:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:23:0x003d  */
    /* JADX WARN: Code duplicated, block: B:25:0x0042  */
    /* JADX WARN: Code duplicated, block: B:27:0x0046  */
    /* JADX WARN: Code duplicated, block: B:29:0x004e  */
    /* JADX WARN: Code duplicated, block: B:30:0x0051  */
    /* JADX WARN: Code duplicated, block: B:34:0x0058  */
    /* JADX WARN: Code duplicated, block: B:36:0x005d  */
    /* JADX WARN: Code duplicated, block: B:38:0x0061  */
    /* JADX WARN: Code duplicated, block: B:40:0x0069  */
    /* JADX WARN: Code duplicated, block: B:41:0x006c  */
    /* JADX WARN: Code duplicated, block: B:45:0x0073  */
    /* JADX WARN: Code duplicated, block: B:47:0x0078  */
    /* JADX WARN: Code duplicated, block: B:49:0x007c  */
    /* JADX WARN: Code duplicated, block: B:51:0x0084  */
    /* JADX WARN: Code duplicated, block: B:52:0x0087  */
    /* JADX WARN: Code duplicated, block: B:56:0x0090  */
    /* JADX WARN: Code duplicated, block: B:58:0x0094  */
    /* JADX WARN: Code duplicated, block: B:60:0x0097  */
    /* JADX WARN: Code duplicated, block: B:62:0x009f  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:67:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:69:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:71:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:73:0x00be  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:78:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:79:0x00d7  */
    /* JADX WARN: Code duplicated, block: B:82:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:84:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:85:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:87:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:89:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:90:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:92:0x00f3  */
    /* JADX WARN: Code duplicated, block: B:94:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:95:0x00f8  */
    /* JADX WARN: Code duplicated, block: B:97:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:98:0x00fe  */
    /* JADX WARN: Multi-variable type inference failed */
    private static final void ListRow(final String str, String str2, e1.e eVar, y0.c0 c0Var, a4.e eVar2, a4.a aVar, boolean z2, f0.p pVar, final int i5, final int i6) {
        int i7;
        String str3;
        int i8;
        e1.e eVar3;
        int i9;
        int i10;
        int i11;
        int i12;
        a4.e eVar4;
        int i13;
        int i14;
        a4.a aVar2;
        int i15;
        int i16;
        boolean z3;
        int i17;
        int i18;
        boolean z4;
        final boolean z5;
        final String str4;
        final e1.e eVar5;
        final a4.e eVar6;
        final a4.a aVar3;
        final y0.c0 c0Var2;
        b2 b2VarR;
        String str5;
        a4.a aVar4;
        boolean z6;
        r0.m mVar;
        r0.p pVarE;
        int iHashCode;
        a4.a aVar5;
        p1.h hVar;
        p1.h hVar2;
        p1.h hVar3;
        p1.h hVar4;
        float f5;
        float f6;
        p1.h hVar5;
        e1.e eVar7;
        p1.h hVar6;
        boolean z7;
        int i19;
        int iHashCode2;
        int iHashCode3;
        y1 y1Var;
        a4.e eVar8;
        boolean z8;
        String str6;
        f0.v vVar = (f0.v) pVar;
        vVar.X(1957988642);
        if ((i5 & 6) == 0) {
            i7 = (vVar.f(str) ? 4 : 2) | i5;
        } else {
            i7 = i5;
        }
        int i20 = i6 & 2;
        if (i20 == 0) {
            if ((i5 & 48) == 0) {
                str3 = str2;
                i7 |= vVar.f(str3) ? 32 : 16;
            }
            i8 = i6 & 4;
            if (i8 != 0) {
                if ((i5 & 384) == 0) {
                    eVar3 = eVar;
                    if (vVar.f(eVar3)) {
                        i9 = 256;
                    } else {
                        i9 = 128;
                    }
                    i7 |= i9;
                }
                i10 = i6 & 8;
                if (i10 != 0) {
                    if ((i5 & 3072) == 0) {
                        if (vVar.h(c0Var)) {
                            i11 = 2048;
                        } else {
                            i11 = 1024;
                        }
                        i7 |= i11;
                    }
                    i12 = i6 & 16;
                    if (i12 != 0) {
                        if ((i5 & 24576) == 0) {
                            eVar4 = eVar2;
                            if (vVar.h(eVar4)) {
                                i13 = 16384;
                            } else {
                                i13 = 8192;
                            }
                            i7 |= i13;
                        }
                        i14 = i6 & 32;
                        if (i14 != 0) {
                            if ((196608 & i5) == 0) {
                                aVar2 = aVar;
                                if (vVar.h(aVar2)) {
                                    i15 = 131072;
                                } else {
                                    i15 = 65536;
                                }
                                i7 |= i15;
                            }
                            i16 = i6 & 64;
                            if (i16 != 0) {
                                i7 |= 1572864;
                                z3 = z2;
                            } else {
                                z3 = z2;
                                if ((i5 & 1572864) == 0) {
                                    if (vVar.g(z3)) {
                                        i17 = 1048576;
                                    } else {
                                        i17 = 524288;
                                    }
                                    i7 |= i17;
                                }
                            }
                            i18 = i7;
                            if ((i18 & 599187) != 599186) {
                                z4 = true;
                            } else {
                                z4 = false;
                            }
                            if (vVar.N(i18 & 1, z4)) {
                                if (i20 != 0) {
                                    str5 = null;
                                } else {
                                    str5 = str3;
                                }
                                if (i8 != 0) {
                                    eVar3 = null;
                                }
                                if (i10 != 0) {
                                    c0Var = null;
                                } else {
                                    c0Var = c0Var;
                                }
                                if (i12 != 0) {
                                    eVar4 = null;
                                }
                                if (i14 != 0) {
                                    aVar4 = null;
                                } else {
                                    aVar4 = aVar2;
                                }
                                if (i16 != 0) {
                                    z6 = true;
                                } else {
                                    z6 = z3;
                                }
                                r0.p pVarE2 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                                mVar = r0.m.f5680a;
                                if (z6 || aVar4 == null) {
                                    pVarE = mVar;
                                } else {
                                    pVarE = androidx.compose.foundation.a.e(mVar, false, null, aVar4, 7);
                                }
                                r0.p pVarE3 = pVarE2.e(pVarE);
                                float f7 = 16;
                                r0.p pVarG = androidx.compose.foundation.layout.a.g(pVarE3, f7, 12);
                                r0.g gVar = r0.b.f5663n;
                                r.b bVar = r.j.f5571a;
                                r.s0 s0VarA = r.q0.a(new r.f(f7), gVar, vVar, 54);
                                iHashCode = Long.hashCode(vVar.S);
                                w1 w1VarL = vVar.l();
                                r0.p pVarC = r0.a.c(vVar, pVarG);
                                p1.j.f4892c.getClass();
                                aVar5 = p1.i.f4867b;
                                vVar.Y();
                                if (vVar.R) {
                                    vVar.k(aVar5);
                                } else {
                                    vVar.i0();
                                }
                                hVar = p1.i.f4870e;
                                f0.l.x(hVar, vVar, s0VarA);
                                hVar2 = p1.i.f4869d;
                                f0.l.x(hVar2, vVar, w1VarL);
                                hVar3 = p1.i.f4871f;
                                if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode))) {
                                    b.b.i(iHashCode, vVar, iHashCode, hVar3);
                                }
                                hVar4 = p1.i.f4868c;
                                f0.l.x(hVar4, vVar, pVarC);
                                if (z6) {
                                    f5 = 1.0f;
                                } else {
                                    f5 = 0.38f;
                                }
                                if (c0Var != null) {
                                    vVar.V(-285980136);
                                    d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                                    vVar.p(false);
                                    f6 = f5;
                                    hVar5 = hVar2;
                                    i19 = 0;
                                    eVar7 = eVar3;
                                    hVar6 = hVar3;
                                    z7 = true;
                                } else if (eVar3 != null) {
                                    vVar.V(-286310244);
                                    r0.p pVarJ = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                                    y1 y1Var2 = c0.u.f1329a;
                                    r0.p pVarG2 = p3.p.g(androidx.compose.foundation.a.b(pVarJ, ((c0.s) vVar.j(y1Var2)).f1246c, y0.l0.f6642a), f5);
                                    n1.f0 f0VarE = r.o.e(r0.b.f5657h, false);
                                    iHashCode2 = Long.hashCode(vVar.S);
                                    w1 w1VarL2 = vVar.l();
                                    r0.p pVarC2 = r0.a.c(vVar, pVarG2);
                                    vVar.Y();
                                    float f8 = f5;
                                    if (vVar.R) {
                                        vVar.k(aVar5);
                                    } else {
                                        vVar.i0();
                                    }
                                    f0.l.x(hVar, vVar, f0VarE);
                                    f0.l.x(hVar2, vVar, w1VarL2);
                                    if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode2))) {
                                        b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                                    }
                                    f0.l.x(hVar4, vVar, pVarC2);
                                    long j5 = ((c0.s) vVar.j(y1Var2)).f1247d;
                                    hVar5 = hVar2;
                                    eVar7 = eVar3;
                                    hVar6 = hVar3;
                                    f6 = f8;
                                    c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j5, vVar, ((i18 >> 6) & 14) | 432, 0);
                                    z7 = true;
                                    vVar.p(true);
                                    i19 = 0;
                                    vVar.p(false);
                                } else {
                                    f6 = f5;
                                    hVar5 = hVar2;
                                    eVar7 = eVar3;
                                    hVar6 = hVar3;
                                    z7 = true;
                                    i19 = 0;
                                    vVar.V(-286291965);
                                    r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                                    vVar.p(false);
                                }
                                if (1.0f > 0.0d) {
                                    throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                                }
                                r0.p pVarG3 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                                r.v vVarA = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                                iHashCode3 = Long.hashCode(vVar.S);
                                w1 w1VarL3 = vVar.l();
                                r0.p pVarC3 = r0.a.c(vVar, pVarG3);
                                vVar.Y();
                                if (vVar.R) {
                                    vVar.k(aVar5);
                                } else {
                                    vVar.i0();
                                }
                                f0.l.x(hVar, vVar, vVarA);
                                f0.l.x(hVar5, vVar, w1VarL3);
                                if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode3))) {
                                    b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                                }
                                f0.l.x(hVar4, vVar, pVarC3);
                                y1Var = t4.f1328a;
                                eVar8 = eVar4;
                                a4.a aVar6 = aVar4;
                                z8 = i19;
                                boolean z9 = z7;
                                n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                                if (str5 != null) {
                                    vVar.V(1662633282);
                                    str6 = str5;
                                    n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                                    vVar.p(z8);
                                } else {
                                    str6 = str5;
                                    vVar.V(1662835526);
                                    vVar.p(z8);
                                }
                                vVar.p(z9);
                                if (eVar8 == null) {
                                    vVar.V(-284517805);
                                } else {
                                    vVar.V(-286272658);
                                    eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                                }
                                vVar.p(z8);
                                vVar.p(z9);
                                eVar5 = eVar7;
                                eVar6 = eVar8;
                                aVar3 = aVar6;
                                str4 = str6;
                                z5 = z6;
                            } else {
                                vVar.Q();
                                z5 = z3;
                                str4 = str3;
                                eVar5 = eVar3;
                                eVar6 = eVar4;
                                aVar3 = aVar2;
                            }
                            c0Var2 = c0Var;
                            b2VarR = vVar.r();
                            if (b2VarR != null) {
                                b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                                    @Override // a4.e
                                    public final Object invoke(Object obj, Object obj2) {
                                        int iIntValue = ((Integer) obj2).intValue();
                                        return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                                    }
                                };
                            }
                        }
                        i7 |= 196608;
                        aVar2 = aVar;
                        i16 = i6 & 64;
                        if (i16 != 0) {
                            i7 |= 1572864;
                            z3 = z2;
                        } else {
                            z3 = z2;
                            if ((i5 & 1572864) == 0) {
                                if (vVar.g(z3)) {
                                    i17 = 1048576;
                                } else {
                                    i17 = 524288;
                                }
                                i7 |= i17;
                            }
                        }
                        i18 = i7;
                        if ((i18 & 599187) != 599186) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        if (vVar.N(i18 & 1, z4)) {
                            if (i20 != 0) {
                                str5 = null;
                            } else {
                                str5 = str3;
                            }
                            if (i8 != 0) {
                                eVar3 = null;
                            }
                            if (i10 != 0) {
                                c0Var = null;
                            } else {
                                c0Var = c0Var;
                            }
                            if (i12 != 0) {
                                eVar4 = null;
                            }
                            if (i14 != 0) {
                                aVar4 = null;
                            } else {
                                aVar4 = aVar2;
                            }
                            if (i16 != 0) {
                                z6 = true;
                            } else {
                                z6 = z3;
                            }
                            r0.p pVarE4 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                            mVar = r0.m.f5680a;
                            if (z6) {
                                pVarE = mVar;
                            } else {
                                pVarE = mVar;
                            }
                            r0.p pVarE5 = pVarE4.e(pVarE);
                            float f9 = 16;
                            r0.p pVarG4 = androidx.compose.foundation.layout.a.g(pVarE5, f9, 12);
                            r0.g gVar2 = r0.b.f5663n;
                            r.b bVar2 = r.j.f5571a;
                            r.s0 s0VarA2 = r.q0.a(new r.f(f9), gVar2, vVar, 54);
                            iHashCode = Long.hashCode(vVar.S);
                            w1 w1VarL4 = vVar.l();
                            r0.p pVarC4 = r0.a.c(vVar, pVarG4);
                            p1.j.f4892c.getClass();
                            aVar5 = p1.i.f4867b;
                            vVar.Y();
                            if (vVar.R) {
                                vVar.k(aVar5);
                            } else {
                                vVar.i0();
                            }
                            hVar = p1.i.f4870e;
                            f0.l.x(hVar, vVar, s0VarA2);
                            hVar2 = p1.i.f4869d;
                            f0.l.x(hVar2, vVar, w1VarL4);
                            hVar3 = p1.i.f4871f;
                            if (vVar.R) {
                                b.b.i(iHashCode, vVar, iHashCode, hVar3);
                            } else {
                                b.b.i(iHashCode, vVar, iHashCode, hVar3);
                            }
                            hVar4 = p1.i.f4868c;
                            f0.l.x(hVar4, vVar, pVarC4);
                            if (z6) {
                                f5 = 1.0f;
                            } else {
                                f5 = 0.38f;
                            }
                            if (c0Var != null) {
                                vVar.V(-285980136);
                                d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                                vVar.p(false);
                                f6 = f5;
                                hVar5 = hVar2;
                                i19 = 0;
                                eVar7 = eVar3;
                                hVar6 = hVar3;
                                z7 = true;
                            } else if (eVar3 != null) {
                                vVar.V(-286310244);
                                r0.p pVarJ2 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                                y1 y1Var3 = c0.u.f1329a;
                                r0.p pVarG5 = p3.p.g(androidx.compose.foundation.a.b(pVarJ2, ((c0.s) vVar.j(y1Var3)).f1246c, y0.l0.f6642a), f5);
                                n1.f0 f0VarE2 = r.o.e(r0.b.f5657h, false);
                                iHashCode2 = Long.hashCode(vVar.S);
                                w1 w1VarL5 = vVar.l();
                                r0.p pVarC5 = r0.a.c(vVar, pVarG5);
                                vVar.Y();
                                float f10 = f5;
                                if (vVar.R) {
                                    vVar.k(aVar5);
                                } else {
                                    vVar.i0();
                                }
                                f0.l.x(hVar, vVar, f0VarE2);
                                f0.l.x(hVar2, vVar, w1VarL5);
                                if (vVar.R) {
                                    b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                                } else {
                                    b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                                }
                                f0.l.x(hVar4, vVar, pVarC5);
                                long j6 = ((c0.s) vVar.j(y1Var3)).f1247d;
                                hVar5 = hVar2;
                                eVar7 = eVar3;
                                hVar6 = hVar3;
                                f6 = f10;
                                c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j6, vVar, ((i18 >> 6) & 14) | 432, 0);
                                z7 = true;
                                vVar.p(true);
                                i19 = 0;
                                vVar.p(false);
                            } else {
                                f6 = f5;
                                hVar5 = hVar2;
                                eVar7 = eVar3;
                                hVar6 = hVar3;
                                z7 = true;
                                i19 = 0;
                                vVar.V(-286291965);
                                r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                                vVar.p(false);
                            }
                            if (1.0f > 0.0d) {
                                throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                            }
                            r0.p pVarG6 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                            r.v vVarA2 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                            iHashCode3 = Long.hashCode(vVar.S);
                            w1 w1VarL6 = vVar.l();
                            r0.p pVarC6 = r0.a.c(vVar, pVarG6);
                            vVar.Y();
                            if (vVar.R) {
                                vVar.k(aVar5);
                            } else {
                                vVar.i0();
                            }
                            f0.l.x(hVar, vVar, vVarA2);
                            f0.l.x(hVar5, vVar, w1VarL6);
                            if (vVar.R) {
                                b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                            } else {
                                b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                            }
                            f0.l.x(hVar4, vVar, pVarC6);
                            y1Var = t4.f1328a;
                            eVar8 = eVar4;
                            a4.a aVar7 = aVar4;
                            z8 = i19;
                            boolean z10 = z7;
                            n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                            if (str5 != null) {
                                vVar.V(1662633282);
                                str6 = str5;
                                n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                                vVar.p(z8);
                            } else {
                                str6 = str5;
                                vVar.V(1662835526);
                                vVar.p(z8);
                            }
                            vVar.p(z10);
                            if (eVar8 == null) {
                                vVar.V(-284517805);
                            } else {
                                vVar.V(-286272658);
                                eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                            }
                            vVar.p(z8);
                            vVar.p(z10);
                            eVar5 = eVar7;
                            eVar6 = eVar8;
                            aVar3 = aVar7;
                            str4 = str6;
                            z5 = z6;
                        } else {
                            vVar.Q();
                            z5 = z3;
                            str4 = str3;
                            eVar5 = eVar3;
                            eVar6 = eVar4;
                            aVar3 = aVar2;
                        }
                        c0Var2 = c0Var;
                        b2VarR = vVar.r();
                        if (b2VarR != null) {
                            b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                                @Override // a4.e
                                public final Object invoke(Object obj, Object obj2) {
                                    int iIntValue = ((Integer) obj2).intValue();
                                    return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                                }
                            };
                        }
                    }
                    i7 |= 24576;
                    eVar4 = eVar2;
                    i14 = i6 & 32;
                    if (i14 != 0) {
                        if ((196608 & i5) == 0) {
                            aVar2 = aVar;
                            if (vVar.h(aVar2)) {
                                i15 = 131072;
                            } else {
                                i15 = 65536;
                            }
                            i7 |= i15;
                        }
                        i16 = i6 & 64;
                        if (i16 != 0) {
                            i7 |= 1572864;
                            z3 = z2;
                        } else {
                            z3 = z2;
                            if ((i5 & 1572864) == 0) {
                                if (vVar.g(z3)) {
                                    i17 = 1048576;
                                } else {
                                    i17 = 524288;
                                }
                                i7 |= i17;
                            }
                        }
                        i18 = i7;
                        if ((i18 & 599187) != 599186) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        if (vVar.N(i18 & 1, z4)) {
                            if (i20 != 0) {
                                str5 = null;
                            } else {
                                str5 = str3;
                            }
                            if (i8 != 0) {
                                eVar3 = null;
                            }
                            if (i10 != 0) {
                                c0Var = null;
                            } else {
                                c0Var = c0Var;
                            }
                            if (i12 != 0) {
                                eVar4 = null;
                            }
                            if (i14 != 0) {
                                aVar4 = null;
                            } else {
                                aVar4 = aVar2;
                            }
                            if (i16 != 0) {
                                z6 = true;
                            } else {
                                z6 = z3;
                            }
                            r0.p pVarE6 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                            mVar = r0.m.f5680a;
                            if (z6) {
                                pVarE = mVar;
                            } else {
                                pVarE = mVar;
                            }
                            r0.p pVarE7 = pVarE6.e(pVarE);
                            float f11 = 16;
                            r0.p pVarG7 = androidx.compose.foundation.layout.a.g(pVarE7, f11, 12);
                            r0.g gVar3 = r0.b.f5663n;
                            r.b bVar3 = r.j.f5571a;
                            r.s0 s0VarA3 = r.q0.a(new r.f(f11), gVar3, vVar, 54);
                            iHashCode = Long.hashCode(vVar.S);
                            w1 w1VarL7 = vVar.l();
                            r0.p pVarC7 = r0.a.c(vVar, pVarG7);
                            p1.j.f4892c.getClass();
                            aVar5 = p1.i.f4867b;
                            vVar.Y();
                            if (vVar.R) {
                                vVar.k(aVar5);
                            } else {
                                vVar.i0();
                            }
                            hVar = p1.i.f4870e;
                            f0.l.x(hVar, vVar, s0VarA3);
                            hVar2 = p1.i.f4869d;
                            f0.l.x(hVar2, vVar, w1VarL7);
                            hVar3 = p1.i.f4871f;
                            if (vVar.R) {
                                b.b.i(iHashCode, vVar, iHashCode, hVar3);
                            } else {
                                b.b.i(iHashCode, vVar, iHashCode, hVar3);
                            }
                            hVar4 = p1.i.f4868c;
                            f0.l.x(hVar4, vVar, pVarC7);
                            if (z6) {
                                f5 = 1.0f;
                            } else {
                                f5 = 0.38f;
                            }
                            if (c0Var != null) {
                                vVar.V(-285980136);
                                d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                                vVar.p(false);
                                f6 = f5;
                                hVar5 = hVar2;
                                i19 = 0;
                                eVar7 = eVar3;
                                hVar6 = hVar3;
                                z7 = true;
                            } else if (eVar3 != null) {
                                vVar.V(-286310244);
                                r0.p pVarJ3 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                                y1 y1Var4 = c0.u.f1329a;
                                r0.p pVarG8 = p3.p.g(androidx.compose.foundation.a.b(pVarJ3, ((c0.s) vVar.j(y1Var4)).f1246c, y0.l0.f6642a), f5);
                                n1.f0 f0VarE3 = r.o.e(r0.b.f5657h, false);
                                iHashCode2 = Long.hashCode(vVar.S);
                                w1 w1VarL8 = vVar.l();
                                r0.p pVarC8 = r0.a.c(vVar, pVarG8);
                                vVar.Y();
                                float f12 = f5;
                                if (vVar.R) {
                                    vVar.k(aVar5);
                                } else {
                                    vVar.i0();
                                }
                                f0.l.x(hVar, vVar, f0VarE3);
                                f0.l.x(hVar2, vVar, w1VarL8);
                                if (vVar.R) {
                                    b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                                } else {
                                    b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                                }
                                f0.l.x(hVar4, vVar, pVarC8);
                                long j7 = ((c0.s) vVar.j(y1Var4)).f1247d;
                                hVar5 = hVar2;
                                eVar7 = eVar3;
                                hVar6 = hVar3;
                                f6 = f12;
                                c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j7, vVar, ((i18 >> 6) & 14) | 432, 0);
                                z7 = true;
                                vVar.p(true);
                                i19 = 0;
                                vVar.p(false);
                            } else {
                                f6 = f5;
                                hVar5 = hVar2;
                                eVar7 = eVar3;
                                hVar6 = hVar3;
                                z7 = true;
                                i19 = 0;
                                vVar.V(-286291965);
                                r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                                vVar.p(false);
                            }
                            if (1.0f > 0.0d) {
                                throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                            }
                            r0.p pVarG9 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                            r.v vVarA3 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                            iHashCode3 = Long.hashCode(vVar.S);
                            w1 w1VarL9 = vVar.l();
                            r0.p pVarC9 = r0.a.c(vVar, pVarG9);
                            vVar.Y();
                            if (vVar.R) {
                                vVar.k(aVar5);
                            } else {
                                vVar.i0();
                            }
                            f0.l.x(hVar, vVar, vVarA3);
                            f0.l.x(hVar5, vVar, w1VarL9);
                            if (vVar.R) {
                                b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                            } else {
                                b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                            }
                            f0.l.x(hVar4, vVar, pVarC9);
                            y1Var = t4.f1328a;
                            eVar8 = eVar4;
                            a4.a aVar8 = aVar4;
                            z8 = i19;
                            boolean z11 = z7;
                            n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                            if (str5 != null) {
                                vVar.V(1662633282);
                                str6 = str5;
                                n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                                vVar.p(z8);
                            } else {
                                str6 = str5;
                                vVar.V(1662835526);
                                vVar.p(z8);
                            }
                            vVar.p(z11);
                            if (eVar8 == null) {
                                vVar.V(-284517805);
                            } else {
                                vVar.V(-286272658);
                                eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                            }
                            vVar.p(z8);
                            vVar.p(z11);
                            eVar5 = eVar7;
                            eVar6 = eVar8;
                            aVar3 = aVar8;
                            str4 = str6;
                            z5 = z6;
                        } else {
                            vVar.Q();
                            z5 = z3;
                            str4 = str3;
                            eVar5 = eVar3;
                            eVar6 = eVar4;
                            aVar3 = aVar2;
                        }
                        c0Var2 = c0Var;
                        b2VarR = vVar.r();
                        if (b2VarR != null) {
                            b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                                @Override // a4.e
                                public final Object invoke(Object obj, Object obj2) {
                                    int iIntValue = ((Integer) obj2).intValue();
                                    return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                                }
                            };
                        }
                    }
                    i7 |= 196608;
                    aVar2 = aVar;
                    i16 = i6 & 64;
                    if (i16 != 0) {
                        i7 |= 1572864;
                        z3 = z2;
                    } else {
                        z3 = z2;
                        if ((i5 & 1572864) == 0) {
                            if (vVar.g(z3)) {
                                i17 = 1048576;
                            } else {
                                i17 = 524288;
                            }
                            i7 |= i17;
                        }
                    }
                    i18 = i7;
                    if ((i18 & 599187) != 599186) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (vVar.N(i18 & 1, z4)) {
                        if (i20 != 0) {
                            str5 = null;
                        } else {
                            str5 = str3;
                        }
                        if (i8 != 0) {
                            eVar3 = null;
                        }
                        if (i10 != 0) {
                            c0Var = null;
                        } else {
                            c0Var = c0Var;
                        }
                        if (i12 != 0) {
                            eVar4 = null;
                        }
                        if (i14 != 0) {
                            aVar4 = null;
                        } else {
                            aVar4 = aVar2;
                        }
                        if (i16 != 0) {
                            z6 = true;
                        } else {
                            z6 = z3;
                        }
                        r0.p pVarE8 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                        mVar = r0.m.f5680a;
                        if (z6) {
                            pVarE = mVar;
                        } else {
                            pVarE = mVar;
                        }
                        r0.p pVarE9 = pVarE8.e(pVarE);
                        float f13 = 16;
                        r0.p pVarG10 = androidx.compose.foundation.layout.a.g(pVarE9, f13, 12);
                        r0.g gVar4 = r0.b.f5663n;
                        r.b bVar4 = r.j.f5571a;
                        r.s0 s0VarA4 = r.q0.a(new r.f(f13), gVar4, vVar, 54);
                        iHashCode = Long.hashCode(vVar.S);
                        w1 w1VarL10 = vVar.l();
                        r0.p pVarC10 = r0.a.c(vVar, pVarG10);
                        p1.j.f4892c.getClass();
                        aVar5 = p1.i.f4867b;
                        vVar.Y();
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        hVar = p1.i.f4870e;
                        f0.l.x(hVar, vVar, s0VarA4);
                        hVar2 = p1.i.f4869d;
                        f0.l.x(hVar2, vVar, w1VarL10);
                        hVar3 = p1.i.f4871f;
                        if (vVar.R) {
                            b.b.i(iHashCode, vVar, iHashCode, hVar3);
                        } else {
                            b.b.i(iHashCode, vVar, iHashCode, hVar3);
                        }
                        hVar4 = p1.i.f4868c;
                        f0.l.x(hVar4, vVar, pVarC10);
                        if (z6) {
                            f5 = 1.0f;
                        } else {
                            f5 = 0.38f;
                        }
                        if (c0Var != null) {
                            vVar.V(-285980136);
                            d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                            vVar.p(false);
                            f6 = f5;
                            hVar5 = hVar2;
                            i19 = 0;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            z7 = true;
                        } else if (eVar3 != null) {
                            vVar.V(-286310244);
                            r0.p pVarJ4 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                            y1 y1Var5 = c0.u.f1329a;
                            r0.p pVarG11 = p3.p.g(androidx.compose.foundation.a.b(pVarJ4, ((c0.s) vVar.j(y1Var5)).f1246c, y0.l0.f6642a), f5);
                            n1.f0 f0VarE4 = r.o.e(r0.b.f5657h, false);
                            iHashCode2 = Long.hashCode(vVar.S);
                            w1 w1VarL11 = vVar.l();
                            r0.p pVarC11 = r0.a.c(vVar, pVarG11);
                            vVar.Y();
                            float f14 = f5;
                            if (vVar.R) {
                                vVar.k(aVar5);
                            } else {
                                vVar.i0();
                            }
                            f0.l.x(hVar, vVar, f0VarE4);
                            f0.l.x(hVar2, vVar, w1VarL11);
                            if (vVar.R) {
                                b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                            } else {
                                b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                            }
                            f0.l.x(hVar4, vVar, pVarC11);
                            long j8 = ((c0.s) vVar.j(y1Var5)).f1247d;
                            hVar5 = hVar2;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            f6 = f14;
                            c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j8, vVar, ((i18 >> 6) & 14) | 432, 0);
                            z7 = true;
                            vVar.p(true);
                            i19 = 0;
                            vVar.p(false);
                        } else {
                            f6 = f5;
                            hVar5 = hVar2;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            z7 = true;
                            i19 = 0;
                            vVar.V(-286291965);
                            r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                            vVar.p(false);
                        }
                        if (1.0f > 0.0d) {
                            throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                        }
                        r0.p pVarG12 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                        r.v vVarA4 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                        iHashCode3 = Long.hashCode(vVar.S);
                        w1 w1VarL12 = vVar.l();
                        r0.p pVarC12 = r0.a.c(vVar, pVarG12);
                        vVar.Y();
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        f0.l.x(hVar, vVar, vVarA4);
                        f0.l.x(hVar5, vVar, w1VarL12);
                        if (vVar.R) {
                            b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                        } else {
                            b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                        }
                        f0.l.x(hVar4, vVar, pVarC12);
                        y1Var = t4.f1328a;
                        eVar8 = eVar4;
                        a4.a aVar9 = aVar4;
                        z8 = i19;
                        boolean z12 = z7;
                        n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                        if (str5 != null) {
                            vVar.V(1662633282);
                            str6 = str5;
                            n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                            vVar.p(z8);
                        } else {
                            str6 = str5;
                            vVar.V(1662835526);
                            vVar.p(z8);
                        }
                        vVar.p(z12);
                        if (eVar8 == null) {
                            vVar.V(-284517805);
                        } else {
                            vVar.V(-286272658);
                            eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                        }
                        vVar.p(z8);
                        vVar.p(z12);
                        eVar5 = eVar7;
                        eVar6 = eVar8;
                        aVar3 = aVar9;
                        str4 = str6;
                        z5 = z6;
                    } else {
                        vVar.Q();
                        z5 = z3;
                        str4 = str3;
                        eVar5 = eVar3;
                        eVar6 = eVar4;
                        aVar3 = aVar2;
                    }
                    c0Var2 = c0Var;
                    b2VarR = vVar.r();
                    if (b2VarR != null) {
                        b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                            @Override // a4.e
                            public final Object invoke(Object obj, Object obj2) {
                                int iIntValue = ((Integer) obj2).intValue();
                                return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                            }
                        };
                    }
                }
                i7 |= 3072;
                i12 = i6 & 16;
                if (i12 != 0) {
                    if ((i5 & 24576) == 0) {
                        eVar4 = eVar2;
                        if (vVar.h(eVar4)) {
                            i13 = 16384;
                        } else {
                            i13 = 8192;
                        }
                        i7 |= i13;
                    }
                    i14 = i6 & 32;
                    if (i14 != 0) {
                        if ((196608 & i5) == 0) {
                            aVar2 = aVar;
                            if (vVar.h(aVar2)) {
                                i15 = 131072;
                            } else {
                                i15 = 65536;
                            }
                            i7 |= i15;
                        }
                        i16 = i6 & 64;
                        if (i16 != 0) {
                            i7 |= 1572864;
                            z3 = z2;
                        } else {
                            z3 = z2;
                            if ((i5 & 1572864) == 0) {
                                if (vVar.g(z3)) {
                                    i17 = 1048576;
                                } else {
                                    i17 = 524288;
                                }
                                i7 |= i17;
                            }
                        }
                        i18 = i7;
                        if ((i18 & 599187) != 599186) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        if (vVar.N(i18 & 1, z4)) {
                            if (i20 != 0) {
                                str5 = null;
                            } else {
                                str5 = str3;
                            }
                            if (i8 != 0) {
                                eVar3 = null;
                            }
                            if (i10 != 0) {
                                c0Var = null;
                            } else {
                                c0Var = c0Var;
                            }
                            if (i12 != 0) {
                                eVar4 = null;
                            }
                            if (i14 != 0) {
                                aVar4 = null;
                            } else {
                                aVar4 = aVar2;
                            }
                            if (i16 != 0) {
                                z6 = true;
                            } else {
                                z6 = z3;
                            }
                            r0.p pVarE10 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                            mVar = r0.m.f5680a;
                            if (z6) {
                                pVarE = mVar;
                            } else {
                                pVarE = mVar;
                            }
                            r0.p pVarE11 = pVarE10.e(pVarE);
                            float f15 = 16;
                            r0.p pVarG13 = androidx.compose.foundation.layout.a.g(pVarE11, f15, 12);
                            r0.g gVar5 = r0.b.f5663n;
                            r.b bVar5 = r.j.f5571a;
                            r.s0 s0VarA5 = r.q0.a(new r.f(f15), gVar5, vVar, 54);
                            iHashCode = Long.hashCode(vVar.S);
                            w1 w1VarL13 = vVar.l();
                            r0.p pVarC13 = r0.a.c(vVar, pVarG13);
                            p1.j.f4892c.getClass();
                            aVar5 = p1.i.f4867b;
                            vVar.Y();
                            if (vVar.R) {
                                vVar.k(aVar5);
                            } else {
                                vVar.i0();
                            }
                            hVar = p1.i.f4870e;
                            f0.l.x(hVar, vVar, s0VarA5);
                            hVar2 = p1.i.f4869d;
                            f0.l.x(hVar2, vVar, w1VarL13);
                            hVar3 = p1.i.f4871f;
                            if (vVar.R) {
                                b.b.i(iHashCode, vVar, iHashCode, hVar3);
                            } else {
                                b.b.i(iHashCode, vVar, iHashCode, hVar3);
                            }
                            hVar4 = p1.i.f4868c;
                            f0.l.x(hVar4, vVar, pVarC13);
                            if (z6) {
                                f5 = 1.0f;
                            } else {
                                f5 = 0.38f;
                            }
                            if (c0Var != null) {
                                vVar.V(-285980136);
                                d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                                vVar.p(false);
                                f6 = f5;
                                hVar5 = hVar2;
                                i19 = 0;
                                eVar7 = eVar3;
                                hVar6 = hVar3;
                                z7 = true;
                            } else if (eVar3 != null) {
                                vVar.V(-286310244);
                                r0.p pVarJ5 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                                y1 y1Var6 = c0.u.f1329a;
                                r0.p pVarG14 = p3.p.g(androidx.compose.foundation.a.b(pVarJ5, ((c0.s) vVar.j(y1Var6)).f1246c, y0.l0.f6642a), f5);
                                n1.f0 f0VarE5 = r.o.e(r0.b.f5657h, false);
                                iHashCode2 = Long.hashCode(vVar.S);
                                w1 w1VarL14 = vVar.l();
                                r0.p pVarC14 = r0.a.c(vVar, pVarG14);
                                vVar.Y();
                                float f16 = f5;
                                if (vVar.R) {
                                    vVar.k(aVar5);
                                } else {
                                    vVar.i0();
                                }
                                f0.l.x(hVar, vVar, f0VarE5);
                                f0.l.x(hVar2, vVar, w1VarL14);
                                if (vVar.R) {
                                    b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                                } else {
                                    b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                                }
                                f0.l.x(hVar4, vVar, pVarC14);
                                long j9 = ((c0.s) vVar.j(y1Var6)).f1247d;
                                hVar5 = hVar2;
                                eVar7 = eVar3;
                                hVar6 = hVar3;
                                f6 = f16;
                                c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j9, vVar, ((i18 >> 6) & 14) | 432, 0);
                                z7 = true;
                                vVar.p(true);
                                i19 = 0;
                                vVar.p(false);
                            } else {
                                f6 = f5;
                                hVar5 = hVar2;
                                eVar7 = eVar3;
                                hVar6 = hVar3;
                                z7 = true;
                                i19 = 0;
                                vVar.V(-286291965);
                                r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                                vVar.p(false);
                            }
                            if (1.0f > 0.0d) {
                                throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                            }
                            r0.p pVarG15 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                            r.v vVarA5 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                            iHashCode3 = Long.hashCode(vVar.S);
                            w1 w1VarL15 = vVar.l();
                            r0.p pVarC15 = r0.a.c(vVar, pVarG15);
                            vVar.Y();
                            if (vVar.R) {
                                vVar.k(aVar5);
                            } else {
                                vVar.i0();
                            }
                            f0.l.x(hVar, vVar, vVarA5);
                            f0.l.x(hVar5, vVar, w1VarL15);
                            if (vVar.R) {
                                b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                            } else {
                                b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                            }
                            f0.l.x(hVar4, vVar, pVarC15);
                            y1Var = t4.f1328a;
                            eVar8 = eVar4;
                            a4.a aVar10 = aVar4;
                            z8 = i19;
                            boolean z13 = z7;
                            n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                            if (str5 != null) {
                                vVar.V(1662633282);
                                str6 = str5;
                                n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                                vVar.p(z8);
                            } else {
                                str6 = str5;
                                vVar.V(1662835526);
                                vVar.p(z8);
                            }
                            vVar.p(z13);
                            if (eVar8 == null) {
                                vVar.V(-284517805);
                            } else {
                                vVar.V(-286272658);
                                eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                            }
                            vVar.p(z8);
                            vVar.p(z13);
                            eVar5 = eVar7;
                            eVar6 = eVar8;
                            aVar3 = aVar10;
                            str4 = str6;
                            z5 = z6;
                        } else {
                            vVar.Q();
                            z5 = z3;
                            str4 = str3;
                            eVar5 = eVar3;
                            eVar6 = eVar4;
                            aVar3 = aVar2;
                        }
                        c0Var2 = c0Var;
                        b2VarR = vVar.r();
                        if (b2VarR != null) {
                            b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                                @Override // a4.e
                                public final Object invoke(Object obj, Object obj2) {
                                    int iIntValue = ((Integer) obj2).intValue();
                                    return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                                }
                            };
                        }
                    }
                    i7 |= 196608;
                    aVar2 = aVar;
                    i16 = i6 & 64;
                    if (i16 != 0) {
                        i7 |= 1572864;
                        z3 = z2;
                    } else {
                        z3 = z2;
                        if ((i5 & 1572864) == 0) {
                            if (vVar.g(z3)) {
                                i17 = 1048576;
                            } else {
                                i17 = 524288;
                            }
                            i7 |= i17;
                        }
                    }
                    i18 = i7;
                    if ((i18 & 599187) != 599186) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (vVar.N(i18 & 1, z4)) {
                        if (i20 != 0) {
                            str5 = null;
                        } else {
                            str5 = str3;
                        }
                        if (i8 != 0) {
                            eVar3 = null;
                        }
                        if (i10 != 0) {
                            c0Var = null;
                        } else {
                            c0Var = c0Var;
                        }
                        if (i12 != 0) {
                            eVar4 = null;
                        }
                        if (i14 != 0) {
                            aVar4 = null;
                        } else {
                            aVar4 = aVar2;
                        }
                        if (i16 != 0) {
                            z6 = true;
                        } else {
                            z6 = z3;
                        }
                        r0.p pVarE12 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                        mVar = r0.m.f5680a;
                        if (z6) {
                            pVarE = mVar;
                        } else {
                            pVarE = mVar;
                        }
                        r0.p pVarE13 = pVarE12.e(pVarE);
                        float f17 = 16;
                        r0.p pVarG16 = androidx.compose.foundation.layout.a.g(pVarE13, f17, 12);
                        r0.g gVar6 = r0.b.f5663n;
                        r.b bVar6 = r.j.f5571a;
                        r.s0 s0VarA6 = r.q0.a(new r.f(f17), gVar6, vVar, 54);
                        iHashCode = Long.hashCode(vVar.S);
                        w1 w1VarL16 = vVar.l();
                        r0.p pVarC16 = r0.a.c(vVar, pVarG16);
                        p1.j.f4892c.getClass();
                        aVar5 = p1.i.f4867b;
                        vVar.Y();
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        hVar = p1.i.f4870e;
                        f0.l.x(hVar, vVar, s0VarA6);
                        hVar2 = p1.i.f4869d;
                        f0.l.x(hVar2, vVar, w1VarL16);
                        hVar3 = p1.i.f4871f;
                        if (vVar.R) {
                            b.b.i(iHashCode, vVar, iHashCode, hVar3);
                        } else {
                            b.b.i(iHashCode, vVar, iHashCode, hVar3);
                        }
                        hVar4 = p1.i.f4868c;
                        f0.l.x(hVar4, vVar, pVarC16);
                        if (z6) {
                            f5 = 1.0f;
                        } else {
                            f5 = 0.38f;
                        }
                        if (c0Var != null) {
                            vVar.V(-285980136);
                            d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                            vVar.p(false);
                            f6 = f5;
                            hVar5 = hVar2;
                            i19 = 0;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            z7 = true;
                        } else if (eVar3 != null) {
                            vVar.V(-286310244);
                            r0.p pVarJ6 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                            y1 y1Var7 = c0.u.f1329a;
                            r0.p pVarG17 = p3.p.g(androidx.compose.foundation.a.b(pVarJ6, ((c0.s) vVar.j(y1Var7)).f1246c, y0.l0.f6642a), f5);
                            n1.f0 f0VarE6 = r.o.e(r0.b.f5657h, false);
                            iHashCode2 = Long.hashCode(vVar.S);
                            w1 w1VarL17 = vVar.l();
                            r0.p pVarC17 = r0.a.c(vVar, pVarG17);
                            vVar.Y();
                            float f18 = f5;
                            if (vVar.R) {
                                vVar.k(aVar5);
                            } else {
                                vVar.i0();
                            }
                            f0.l.x(hVar, vVar, f0VarE6);
                            f0.l.x(hVar2, vVar, w1VarL17);
                            if (vVar.R) {
                                b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                            } else {
                                b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                            }
                            f0.l.x(hVar4, vVar, pVarC17);
                            long j10 = ((c0.s) vVar.j(y1Var7)).f1247d;
                            hVar5 = hVar2;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            f6 = f18;
                            c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j10, vVar, ((i18 >> 6) & 14) | 432, 0);
                            z7 = true;
                            vVar.p(true);
                            i19 = 0;
                            vVar.p(false);
                        } else {
                            f6 = f5;
                            hVar5 = hVar2;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            z7 = true;
                            i19 = 0;
                            vVar.V(-286291965);
                            r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                            vVar.p(false);
                        }
                        if (1.0f > 0.0d) {
                            throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                        }
                        r0.p pVarG18 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                        r.v vVarA6 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                        iHashCode3 = Long.hashCode(vVar.S);
                        w1 w1VarL18 = vVar.l();
                        r0.p pVarC18 = r0.a.c(vVar, pVarG18);
                        vVar.Y();
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        f0.l.x(hVar, vVar, vVarA6);
                        f0.l.x(hVar5, vVar, w1VarL18);
                        if (vVar.R) {
                            b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                        } else {
                            b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                        }
                        f0.l.x(hVar4, vVar, pVarC18);
                        y1Var = t4.f1328a;
                        eVar8 = eVar4;
                        a4.a aVar11 = aVar4;
                        z8 = i19;
                        boolean z14 = z7;
                        n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                        if (str5 != null) {
                            vVar.V(1662633282);
                            str6 = str5;
                            n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                            vVar.p(z8);
                        } else {
                            str6 = str5;
                            vVar.V(1662835526);
                            vVar.p(z8);
                        }
                        vVar.p(z14);
                        if (eVar8 == null) {
                            vVar.V(-284517805);
                        } else {
                            vVar.V(-286272658);
                            eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                        }
                        vVar.p(z8);
                        vVar.p(z14);
                        eVar5 = eVar7;
                        eVar6 = eVar8;
                        aVar3 = aVar11;
                        str4 = str6;
                        z5 = z6;
                    } else {
                        vVar.Q();
                        z5 = z3;
                        str4 = str3;
                        eVar5 = eVar3;
                        eVar6 = eVar4;
                        aVar3 = aVar2;
                    }
                    c0Var2 = c0Var;
                    b2VarR = vVar.r();
                    if (b2VarR != null) {
                        b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                            @Override // a4.e
                            public final Object invoke(Object obj, Object obj2) {
                                int iIntValue = ((Integer) obj2).intValue();
                                return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                            }
                        };
                    }
                }
                i7 |= 24576;
                eVar4 = eVar2;
                i14 = i6 & 32;
                if (i14 != 0) {
                    if ((196608 & i5) == 0) {
                        aVar2 = aVar;
                        if (vVar.h(aVar2)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i7 |= i15;
                    }
                    i16 = i6 & 64;
                    if (i16 != 0) {
                        i7 |= 1572864;
                        z3 = z2;
                    } else {
                        z3 = z2;
                        if ((i5 & 1572864) == 0) {
                            if (vVar.g(z3)) {
                                i17 = 1048576;
                            } else {
                                i17 = 524288;
                            }
                            i7 |= i17;
                        }
                    }
                    i18 = i7;
                    if ((i18 & 599187) != 599186) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (vVar.N(i18 & 1, z4)) {
                        if (i20 != 0) {
                            str5 = null;
                        } else {
                            str5 = str3;
                        }
                        if (i8 != 0) {
                            eVar3 = null;
                        }
                        if (i10 != 0) {
                            c0Var = null;
                        } else {
                            c0Var = c0Var;
                        }
                        if (i12 != 0) {
                            eVar4 = null;
                        }
                        if (i14 != 0) {
                            aVar4 = null;
                        } else {
                            aVar4 = aVar2;
                        }
                        if (i16 != 0) {
                            z6 = true;
                        } else {
                            z6 = z3;
                        }
                        r0.p pVarE14 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                        mVar = r0.m.f5680a;
                        if (z6) {
                            pVarE = mVar;
                        } else {
                            pVarE = mVar;
                        }
                        r0.p pVarE15 = pVarE14.e(pVarE);
                        float f19 = 16;
                        r0.p pVarG19 = androidx.compose.foundation.layout.a.g(pVarE15, f19, 12);
                        r0.g gVar7 = r0.b.f5663n;
                        r.b bVar7 = r.j.f5571a;
                        r.s0 s0VarA7 = r.q0.a(new r.f(f19), gVar7, vVar, 54);
                        iHashCode = Long.hashCode(vVar.S);
                        w1 w1VarL19 = vVar.l();
                        r0.p pVarC19 = r0.a.c(vVar, pVarG19);
                        p1.j.f4892c.getClass();
                        aVar5 = p1.i.f4867b;
                        vVar.Y();
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        hVar = p1.i.f4870e;
                        f0.l.x(hVar, vVar, s0VarA7);
                        hVar2 = p1.i.f4869d;
                        f0.l.x(hVar2, vVar, w1VarL19);
                        hVar3 = p1.i.f4871f;
                        if (vVar.R) {
                            b.b.i(iHashCode, vVar, iHashCode, hVar3);
                        } else {
                            b.b.i(iHashCode, vVar, iHashCode, hVar3);
                        }
                        hVar4 = p1.i.f4868c;
                        f0.l.x(hVar4, vVar, pVarC19);
                        if (z6) {
                            f5 = 1.0f;
                        } else {
                            f5 = 0.38f;
                        }
                        if (c0Var != null) {
                            vVar.V(-285980136);
                            d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                            vVar.p(false);
                            f6 = f5;
                            hVar5 = hVar2;
                            i19 = 0;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            z7 = true;
                        } else if (eVar3 != null) {
                            vVar.V(-286310244);
                            r0.p pVarJ7 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                            y1 y1Var8 = c0.u.f1329a;
                            r0.p pVarG110 = p3.p.g(androidx.compose.foundation.a.b(pVarJ7, ((c0.s) vVar.j(y1Var8)).f1246c, y0.l0.f6642a), f5);
                            n1.f0 f0VarE7 = r.o.e(r0.b.f5657h, false);
                            iHashCode2 = Long.hashCode(vVar.S);
                            w1 w1VarL110 = vVar.l();
                            r0.p pVarC110 = r0.a.c(vVar, pVarG110);
                            vVar.Y();
                            float f110 = f5;
                            if (vVar.R) {
                                vVar.k(aVar5);
                            } else {
                                vVar.i0();
                            }
                            f0.l.x(hVar, vVar, f0VarE7);
                            f0.l.x(hVar2, vVar, w1VarL110);
                            if (vVar.R) {
                                b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                            } else {
                                b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                            }
                            f0.l.x(hVar4, vVar, pVarC110);
                            long j11 = ((c0.s) vVar.j(y1Var8)).f1247d;
                            hVar5 = hVar2;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            f6 = f110;
                            c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j11, vVar, ((i18 >> 6) & 14) | 432, 0);
                            z7 = true;
                            vVar.p(true);
                            i19 = 0;
                            vVar.p(false);
                        } else {
                            f6 = f5;
                            hVar5 = hVar2;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            z7 = true;
                            i19 = 0;
                            vVar.V(-286291965);
                            r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                            vVar.p(false);
                        }
                        if (1.0f > 0.0d) {
                            throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                        }
                        r0.p pVarG111 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                        r.v vVarA7 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                        iHashCode3 = Long.hashCode(vVar.S);
                        w1 w1VarL111 = vVar.l();
                        r0.p pVarC111 = r0.a.c(vVar, pVarG111);
                        vVar.Y();
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        f0.l.x(hVar, vVar, vVarA7);
                        f0.l.x(hVar5, vVar, w1VarL111);
                        if (vVar.R) {
                            b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                        } else {
                            b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                        }
                        f0.l.x(hVar4, vVar, pVarC111);
                        y1Var = t4.f1328a;
                        eVar8 = eVar4;
                        a4.a aVar12 = aVar4;
                        z8 = i19;
                        boolean z15 = z7;
                        n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                        if (str5 != null) {
                            vVar.V(1662633282);
                            str6 = str5;
                            n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                            vVar.p(z8);
                        } else {
                            str6 = str5;
                            vVar.V(1662835526);
                            vVar.p(z8);
                        }
                        vVar.p(z15);
                        if (eVar8 == null) {
                            vVar.V(-284517805);
                        } else {
                            vVar.V(-286272658);
                            eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                        }
                        vVar.p(z8);
                        vVar.p(z15);
                        eVar5 = eVar7;
                        eVar6 = eVar8;
                        aVar3 = aVar12;
                        str4 = str6;
                        z5 = z6;
                    } else {
                        vVar.Q();
                        z5 = z3;
                        str4 = str3;
                        eVar5 = eVar3;
                        eVar6 = eVar4;
                        aVar3 = aVar2;
                    }
                    c0Var2 = c0Var;
                    b2VarR = vVar.r();
                    if (b2VarR != null) {
                        b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                            @Override // a4.e
                            public final Object invoke(Object obj, Object obj2) {
                                int iIntValue = ((Integer) obj2).intValue();
                                return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                            }
                        };
                    }
                }
                i7 |= 196608;
                aVar2 = aVar;
                i16 = i6 & 64;
                if (i16 != 0) {
                    i7 |= 1572864;
                    z3 = z2;
                } else {
                    z3 = z2;
                    if ((i5 & 1572864) == 0) {
                        if (vVar.g(z3)) {
                            i17 = 1048576;
                        } else {
                            i17 = 524288;
                        }
                        i7 |= i17;
                    }
                }
                i18 = i7;
                if ((i18 & 599187) != 599186) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (vVar.N(i18 & 1, z4)) {
                    if (i20 != 0) {
                        str5 = null;
                    } else {
                        str5 = str3;
                    }
                    if (i8 != 0) {
                        eVar3 = null;
                    }
                    if (i10 != 0) {
                        c0Var = null;
                    } else {
                        c0Var = c0Var;
                    }
                    if (i12 != 0) {
                        eVar4 = null;
                    }
                    if (i14 != 0) {
                        aVar4 = null;
                    } else {
                        aVar4 = aVar2;
                    }
                    if (i16 != 0) {
                        z6 = true;
                    } else {
                        z6 = z3;
                    }
                    r0.p pVarE16 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                    mVar = r0.m.f5680a;
                    if (z6) {
                        pVarE = mVar;
                    } else {
                        pVarE = mVar;
                    }
                    r0.p pVarE17 = pVarE16.e(pVarE);
                    float f111 = 16;
                    r0.p pVarG112 = androidx.compose.foundation.layout.a.g(pVarE17, f111, 12);
                    r0.g gVar8 = r0.b.f5663n;
                    r.b bVar8 = r.j.f5571a;
                    r.s0 s0VarA8 = r.q0.a(new r.f(f111), gVar8, vVar, 54);
                    iHashCode = Long.hashCode(vVar.S);
                    w1 w1VarL112 = vVar.l();
                    r0.p pVarC112 = r0.a.c(vVar, pVarG112);
                    p1.j.f4892c.getClass();
                    aVar5 = p1.i.f4867b;
                    vVar.Y();
                    if (vVar.R) {
                        vVar.k(aVar5);
                    } else {
                        vVar.i0();
                    }
                    hVar = p1.i.f4870e;
                    f0.l.x(hVar, vVar, s0VarA8);
                    hVar2 = p1.i.f4869d;
                    f0.l.x(hVar2, vVar, w1VarL112);
                    hVar3 = p1.i.f4871f;
                    if (vVar.R) {
                        b.b.i(iHashCode, vVar, iHashCode, hVar3);
                    } else {
                        b.b.i(iHashCode, vVar, iHashCode, hVar3);
                    }
                    hVar4 = p1.i.f4868c;
                    f0.l.x(hVar4, vVar, pVarC112);
                    if (z6) {
                        f5 = 1.0f;
                    } else {
                        f5 = 0.38f;
                    }
                    if (c0Var != null) {
                        vVar.V(-285980136);
                        d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                        vVar.p(false);
                        f6 = f5;
                        hVar5 = hVar2;
                        i19 = 0;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        z7 = true;
                    } else if (eVar3 != null) {
                        vVar.V(-286310244);
                        r0.p pVarJ8 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                        y1 y1Var9 = c0.u.f1329a;
                        r0.p pVarG113 = p3.p.g(androidx.compose.foundation.a.b(pVarJ8, ((c0.s) vVar.j(y1Var9)).f1246c, y0.l0.f6642a), f5);
                        n1.f0 f0VarE8 = r.o.e(r0.b.f5657h, false);
                        iHashCode2 = Long.hashCode(vVar.S);
                        w1 w1VarL113 = vVar.l();
                        r0.p pVarC113 = r0.a.c(vVar, pVarG113);
                        vVar.Y();
                        float f112 = f5;
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        f0.l.x(hVar, vVar, f0VarE8);
                        f0.l.x(hVar2, vVar, w1VarL113);
                        if (vVar.R) {
                            b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                        } else {
                            b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                        }
                        f0.l.x(hVar4, vVar, pVarC113);
                        long j12 = ((c0.s) vVar.j(y1Var9)).f1247d;
                        hVar5 = hVar2;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        f6 = f112;
                        c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j12, vVar, ((i18 >> 6) & 14) | 432, 0);
                        z7 = true;
                        vVar.p(true);
                        i19 = 0;
                        vVar.p(false);
                    } else {
                        f6 = f5;
                        hVar5 = hVar2;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        z7 = true;
                        i19 = 0;
                        vVar.V(-286291965);
                        r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                        vVar.p(false);
                    }
                    if (1.0f > 0.0d) {
                        throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                    }
                    r0.p pVarG114 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                    r.v vVarA8 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                    iHashCode3 = Long.hashCode(vVar.S);
                    w1 w1VarL114 = vVar.l();
                    r0.p pVarC114 = r0.a.c(vVar, pVarG114);
                    vVar.Y();
                    if (vVar.R) {
                        vVar.k(aVar5);
                    } else {
                        vVar.i0();
                    }
                    f0.l.x(hVar, vVar, vVarA8);
                    f0.l.x(hVar5, vVar, w1VarL114);
                    if (vVar.R) {
                        b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                    } else {
                        b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                    }
                    f0.l.x(hVar4, vVar, pVarC114);
                    y1Var = t4.f1328a;
                    eVar8 = eVar4;
                    a4.a aVar13 = aVar4;
                    z8 = i19;
                    boolean z16 = z7;
                    n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                    if (str5 != null) {
                        vVar.V(1662633282);
                        str6 = str5;
                        n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                        vVar.p(z8);
                    } else {
                        str6 = str5;
                        vVar.V(1662835526);
                        vVar.p(z8);
                    }
                    vVar.p(z16);
                    if (eVar8 == null) {
                        vVar.V(-284517805);
                    } else {
                        vVar.V(-286272658);
                        eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                    }
                    vVar.p(z8);
                    vVar.p(z16);
                    eVar5 = eVar7;
                    eVar6 = eVar8;
                    aVar3 = aVar13;
                    str4 = str6;
                    z5 = z6;
                } else {
                    vVar.Q();
                    z5 = z3;
                    str4 = str3;
                    eVar5 = eVar3;
                    eVar6 = eVar4;
                    aVar3 = aVar2;
                }
                c0Var2 = c0Var;
                b2VarR = vVar.r();
                if (b2VarR != null) {
                    b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                        @Override // a4.e
                        public final Object invoke(Object obj, Object obj2) {
                            int iIntValue = ((Integer) obj2).intValue();
                            return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                        }
                    };
                }
            }
            i7 |= 384;
            eVar3 = eVar;
            i10 = i6 & 8;
            if (i10 != 0) {
                if ((i5 & 3072) == 0) {
                    if (vVar.h(c0Var)) {
                        i11 = 2048;
                    } else {
                        i11 = 1024;
                    }
                    i7 |= i11;
                }
                i12 = i6 & 16;
                if (i12 != 0) {
                    if ((i5 & 24576) == 0) {
                        eVar4 = eVar2;
                        if (vVar.h(eVar4)) {
                            i13 = 16384;
                        } else {
                            i13 = 8192;
                        }
                        i7 |= i13;
                    }
                    i14 = i6 & 32;
                    if (i14 != 0) {
                        if ((196608 & i5) == 0) {
                            aVar2 = aVar;
                            if (vVar.h(aVar2)) {
                                i15 = 131072;
                            } else {
                                i15 = 65536;
                            }
                            i7 |= i15;
                        }
                        i16 = i6 & 64;
                        if (i16 != 0) {
                            i7 |= 1572864;
                            z3 = z2;
                        } else {
                            z3 = z2;
                            if ((i5 & 1572864) == 0) {
                                if (vVar.g(z3)) {
                                    i17 = 1048576;
                                } else {
                                    i17 = 524288;
                                }
                                i7 |= i17;
                            }
                        }
                        i18 = i7;
                        if ((i18 & 599187) != 599186) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        if (vVar.N(i18 & 1, z4)) {
                            if (i20 != 0) {
                                str5 = null;
                            } else {
                                str5 = str3;
                            }
                            if (i8 != 0) {
                                eVar3 = null;
                            }
                            if (i10 != 0) {
                                c0Var = null;
                            } else {
                                c0Var = c0Var;
                            }
                            if (i12 != 0) {
                                eVar4 = null;
                            }
                            if (i14 != 0) {
                                aVar4 = null;
                            } else {
                                aVar4 = aVar2;
                            }
                            if (i16 != 0) {
                                z6 = true;
                            } else {
                                z6 = z3;
                            }
                            r0.p pVarE18 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                            mVar = r0.m.f5680a;
                            if (z6) {
                                pVarE = mVar;
                            } else {
                                pVarE = mVar;
                            }
                            r0.p pVarE19 = pVarE18.e(pVarE);
                            float f113 = 16;
                            r0.p pVarG115 = androidx.compose.foundation.layout.a.g(pVarE19, f113, 12);
                            r0.g gVar9 = r0.b.f5663n;
                            r.b bVar9 = r.j.f5571a;
                            r.s0 s0VarA9 = r.q0.a(new r.f(f113), gVar9, vVar, 54);
                            iHashCode = Long.hashCode(vVar.S);
                            w1 w1VarL115 = vVar.l();
                            r0.p pVarC115 = r0.a.c(vVar, pVarG115);
                            p1.j.f4892c.getClass();
                            aVar5 = p1.i.f4867b;
                            vVar.Y();
                            if (vVar.R) {
                                vVar.k(aVar5);
                            } else {
                                vVar.i0();
                            }
                            hVar = p1.i.f4870e;
                            f0.l.x(hVar, vVar, s0VarA9);
                            hVar2 = p1.i.f4869d;
                            f0.l.x(hVar2, vVar, w1VarL115);
                            hVar3 = p1.i.f4871f;
                            if (vVar.R) {
                                b.b.i(iHashCode, vVar, iHashCode, hVar3);
                            } else {
                                b.b.i(iHashCode, vVar, iHashCode, hVar3);
                            }
                            hVar4 = p1.i.f4868c;
                            f0.l.x(hVar4, vVar, pVarC115);
                            if (z6) {
                                f5 = 1.0f;
                            } else {
                                f5 = 0.38f;
                            }
                            if (c0Var != null) {
                                vVar.V(-285980136);
                                d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                                vVar.p(false);
                                f6 = f5;
                                hVar5 = hVar2;
                                i19 = 0;
                                eVar7 = eVar3;
                                hVar6 = hVar3;
                                z7 = true;
                            } else if (eVar3 != null) {
                                vVar.V(-286310244);
                                r0.p pVarJ9 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                                y1 y1Var10 = c0.u.f1329a;
                                r0.p pVarG116 = p3.p.g(androidx.compose.foundation.a.b(pVarJ9, ((c0.s) vVar.j(y1Var10)).f1246c, y0.l0.f6642a), f5);
                                n1.f0 f0VarE9 = r.o.e(r0.b.f5657h, false);
                                iHashCode2 = Long.hashCode(vVar.S);
                                w1 w1VarL116 = vVar.l();
                                r0.p pVarC116 = r0.a.c(vVar, pVarG116);
                                vVar.Y();
                                float f114 = f5;
                                if (vVar.R) {
                                    vVar.k(aVar5);
                                } else {
                                    vVar.i0();
                                }
                                f0.l.x(hVar, vVar, f0VarE9);
                                f0.l.x(hVar2, vVar, w1VarL116);
                                if (vVar.R) {
                                    b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                                } else {
                                    b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                                }
                                f0.l.x(hVar4, vVar, pVarC116);
                                long j13 = ((c0.s) vVar.j(y1Var10)).f1247d;
                                hVar5 = hVar2;
                                eVar7 = eVar3;
                                hVar6 = hVar3;
                                f6 = f114;
                                c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j13, vVar, ((i18 >> 6) & 14) | 432, 0);
                                z7 = true;
                                vVar.p(true);
                                i19 = 0;
                                vVar.p(false);
                            } else {
                                f6 = f5;
                                hVar5 = hVar2;
                                eVar7 = eVar3;
                                hVar6 = hVar3;
                                z7 = true;
                                i19 = 0;
                                vVar.V(-286291965);
                                r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                                vVar.p(false);
                            }
                            if (1.0f > 0.0d) {
                                throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                            }
                            r0.p pVarG117 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                            r.v vVarA9 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                            iHashCode3 = Long.hashCode(vVar.S);
                            w1 w1VarL117 = vVar.l();
                            r0.p pVarC117 = r0.a.c(vVar, pVarG117);
                            vVar.Y();
                            if (vVar.R) {
                                vVar.k(aVar5);
                            } else {
                                vVar.i0();
                            }
                            f0.l.x(hVar, vVar, vVarA9);
                            f0.l.x(hVar5, vVar, w1VarL117);
                            if (vVar.R) {
                                b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                            } else {
                                b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                            }
                            f0.l.x(hVar4, vVar, pVarC117);
                            y1Var = t4.f1328a;
                            eVar8 = eVar4;
                            a4.a aVar14 = aVar4;
                            z8 = i19;
                            boolean z17 = z7;
                            n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                            if (str5 != null) {
                                vVar.V(1662633282);
                                str6 = str5;
                                n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                                vVar.p(z8);
                            } else {
                                str6 = str5;
                                vVar.V(1662835526);
                                vVar.p(z8);
                            }
                            vVar.p(z17);
                            if (eVar8 == null) {
                                vVar.V(-284517805);
                            } else {
                                vVar.V(-286272658);
                                eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                            }
                            vVar.p(z8);
                            vVar.p(z17);
                            eVar5 = eVar7;
                            eVar6 = eVar8;
                            aVar3 = aVar14;
                            str4 = str6;
                            z5 = z6;
                        } else {
                            vVar.Q();
                            z5 = z3;
                            str4 = str3;
                            eVar5 = eVar3;
                            eVar6 = eVar4;
                            aVar3 = aVar2;
                        }
                        c0Var2 = c0Var;
                        b2VarR = vVar.r();
                        if (b2VarR != null) {
                            b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                                @Override // a4.e
                                public final Object invoke(Object obj, Object obj2) {
                                    int iIntValue = ((Integer) obj2).intValue();
                                    return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                                }
                            };
                        }
                    }
                    i7 |= 196608;
                    aVar2 = aVar;
                    i16 = i6 & 64;
                    if (i16 != 0) {
                        i7 |= 1572864;
                        z3 = z2;
                    } else {
                        z3 = z2;
                        if ((i5 & 1572864) == 0) {
                            if (vVar.g(z3)) {
                                i17 = 1048576;
                            } else {
                                i17 = 524288;
                            }
                            i7 |= i17;
                        }
                    }
                    i18 = i7;
                    if ((i18 & 599187) != 599186) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (vVar.N(i18 & 1, z4)) {
                        if (i20 != 0) {
                            str5 = null;
                        } else {
                            str5 = str3;
                        }
                        if (i8 != 0) {
                            eVar3 = null;
                        }
                        if (i10 != 0) {
                            c0Var = null;
                        } else {
                            c0Var = c0Var;
                        }
                        if (i12 != 0) {
                            eVar4 = null;
                        }
                        if (i14 != 0) {
                            aVar4 = null;
                        } else {
                            aVar4 = aVar2;
                        }
                        if (i16 != 0) {
                            z6 = true;
                        } else {
                            z6 = z3;
                        }
                        r0.p pVarE110 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                        mVar = r0.m.f5680a;
                        if (z6) {
                            pVarE = mVar;
                        } else {
                            pVarE = mVar;
                        }
                        r0.p pVarE111 = pVarE110.e(pVarE);
                        float f115 = 16;
                        r0.p pVarG118 = androidx.compose.foundation.layout.a.g(pVarE111, f115, 12);
                        r0.g gVar10 = r0.b.f5663n;
                        r.b bVar10 = r.j.f5571a;
                        r.s0 s0VarA10 = r.q0.a(new r.f(f115), gVar10, vVar, 54);
                        iHashCode = Long.hashCode(vVar.S);
                        w1 w1VarL118 = vVar.l();
                        r0.p pVarC118 = r0.a.c(vVar, pVarG118);
                        p1.j.f4892c.getClass();
                        aVar5 = p1.i.f4867b;
                        vVar.Y();
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        hVar = p1.i.f4870e;
                        f0.l.x(hVar, vVar, s0VarA10);
                        hVar2 = p1.i.f4869d;
                        f0.l.x(hVar2, vVar, w1VarL118);
                        hVar3 = p1.i.f4871f;
                        if (vVar.R) {
                            b.b.i(iHashCode, vVar, iHashCode, hVar3);
                        } else {
                            b.b.i(iHashCode, vVar, iHashCode, hVar3);
                        }
                        hVar4 = p1.i.f4868c;
                        f0.l.x(hVar4, vVar, pVarC118);
                        if (z6) {
                            f5 = 1.0f;
                        } else {
                            f5 = 0.38f;
                        }
                        if (c0Var != null) {
                            vVar.V(-285980136);
                            d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                            vVar.p(false);
                            f6 = f5;
                            hVar5 = hVar2;
                            i19 = 0;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            z7 = true;
                        } else if (eVar3 != null) {
                            vVar.V(-286310244);
                            r0.p pVarJ10 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                            y1 y1Var11 = c0.u.f1329a;
                            r0.p pVarG119 = p3.p.g(androidx.compose.foundation.a.b(pVarJ10, ((c0.s) vVar.j(y1Var11)).f1246c, y0.l0.f6642a), f5);
                            n1.f0 f0VarE10 = r.o.e(r0.b.f5657h, false);
                            iHashCode2 = Long.hashCode(vVar.S);
                            w1 w1VarL119 = vVar.l();
                            r0.p pVarC119 = r0.a.c(vVar, pVarG119);
                            vVar.Y();
                            float f116 = f5;
                            if (vVar.R) {
                                vVar.k(aVar5);
                            } else {
                                vVar.i0();
                            }
                            f0.l.x(hVar, vVar, f0VarE10);
                            f0.l.x(hVar2, vVar, w1VarL119);
                            if (vVar.R) {
                                b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                            } else {
                                b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                            }
                            f0.l.x(hVar4, vVar, pVarC119);
                            long j14 = ((c0.s) vVar.j(y1Var11)).f1247d;
                            hVar5 = hVar2;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            f6 = f116;
                            c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j14, vVar, ((i18 >> 6) & 14) | 432, 0);
                            z7 = true;
                            vVar.p(true);
                            i19 = 0;
                            vVar.p(false);
                        } else {
                            f6 = f5;
                            hVar5 = hVar2;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            z7 = true;
                            i19 = 0;
                            vVar.V(-286291965);
                            r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                            vVar.p(false);
                        }
                        if (1.0f > 0.0d) {
                            throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                        }
                        r0.p pVarG1110 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                        r.v vVarA10 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                        iHashCode3 = Long.hashCode(vVar.S);
                        w1 w1VarL1110 = vVar.l();
                        r0.p pVarC1110 = r0.a.c(vVar, pVarG1110);
                        vVar.Y();
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        f0.l.x(hVar, vVar, vVarA10);
                        f0.l.x(hVar5, vVar, w1VarL1110);
                        if (vVar.R) {
                            b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                        } else {
                            b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                        }
                        f0.l.x(hVar4, vVar, pVarC1110);
                        y1Var = t4.f1328a;
                        eVar8 = eVar4;
                        a4.a aVar15 = aVar4;
                        z8 = i19;
                        boolean z18 = z7;
                        n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                        if (str5 != null) {
                            vVar.V(1662633282);
                            str6 = str5;
                            n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                            vVar.p(z8);
                        } else {
                            str6 = str5;
                            vVar.V(1662835526);
                            vVar.p(z8);
                        }
                        vVar.p(z18);
                        if (eVar8 == null) {
                            vVar.V(-284517805);
                        } else {
                            vVar.V(-286272658);
                            eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                        }
                        vVar.p(z8);
                        vVar.p(z18);
                        eVar5 = eVar7;
                        eVar6 = eVar8;
                        aVar3 = aVar15;
                        str4 = str6;
                        z5 = z6;
                    } else {
                        vVar.Q();
                        z5 = z3;
                        str4 = str3;
                        eVar5 = eVar3;
                        eVar6 = eVar4;
                        aVar3 = aVar2;
                    }
                    c0Var2 = c0Var;
                    b2VarR = vVar.r();
                    if (b2VarR != null) {
                        b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                            @Override // a4.e
                            public final Object invoke(Object obj, Object obj2) {
                                int iIntValue = ((Integer) obj2).intValue();
                                return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                            }
                        };
                    }
                }
                i7 |= 24576;
                eVar4 = eVar2;
                i14 = i6 & 32;
                if (i14 != 0) {
                    if ((196608 & i5) == 0) {
                        aVar2 = aVar;
                        if (vVar.h(aVar2)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i7 |= i15;
                    }
                    i16 = i6 & 64;
                    if (i16 != 0) {
                        i7 |= 1572864;
                        z3 = z2;
                    } else {
                        z3 = z2;
                        if ((i5 & 1572864) == 0) {
                            if (vVar.g(z3)) {
                                i17 = 1048576;
                            } else {
                                i17 = 524288;
                            }
                            i7 |= i17;
                        }
                    }
                    i18 = i7;
                    if ((i18 & 599187) != 599186) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (vVar.N(i18 & 1, z4)) {
                        if (i20 != 0) {
                            str5 = null;
                        } else {
                            str5 = str3;
                        }
                        if (i8 != 0) {
                            eVar3 = null;
                        }
                        if (i10 != 0) {
                            c0Var = null;
                        } else {
                            c0Var = c0Var;
                        }
                        if (i12 != 0) {
                            eVar4 = null;
                        }
                        if (i14 != 0) {
                            aVar4 = null;
                        } else {
                            aVar4 = aVar2;
                        }
                        if (i16 != 0) {
                            z6 = true;
                        } else {
                            z6 = z3;
                        }
                        r0.p pVarE112 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                        mVar = r0.m.f5680a;
                        if (z6) {
                            pVarE = mVar;
                        } else {
                            pVarE = mVar;
                        }
                        r0.p pVarE113 = pVarE112.e(pVarE);
                        float f117 = 16;
                        r0.p pVarG1111 = androidx.compose.foundation.layout.a.g(pVarE113, f117, 12);
                        r0.g gVar11 = r0.b.f5663n;
                        r.b bVar11 = r.j.f5571a;
                        r.s0 s0VarA11 = r.q0.a(new r.f(f117), gVar11, vVar, 54);
                        iHashCode = Long.hashCode(vVar.S);
                        w1 w1VarL1111 = vVar.l();
                        r0.p pVarC1111 = r0.a.c(vVar, pVarG1111);
                        p1.j.f4892c.getClass();
                        aVar5 = p1.i.f4867b;
                        vVar.Y();
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        hVar = p1.i.f4870e;
                        f0.l.x(hVar, vVar, s0VarA11);
                        hVar2 = p1.i.f4869d;
                        f0.l.x(hVar2, vVar, w1VarL1111);
                        hVar3 = p1.i.f4871f;
                        if (vVar.R) {
                            b.b.i(iHashCode, vVar, iHashCode, hVar3);
                        } else {
                            b.b.i(iHashCode, vVar, iHashCode, hVar3);
                        }
                        hVar4 = p1.i.f4868c;
                        f0.l.x(hVar4, vVar, pVarC1111);
                        if (z6) {
                            f5 = 1.0f;
                        } else {
                            f5 = 0.38f;
                        }
                        if (c0Var != null) {
                            vVar.V(-285980136);
                            d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                            vVar.p(false);
                            f6 = f5;
                            hVar5 = hVar2;
                            i19 = 0;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            z7 = true;
                        } else if (eVar3 != null) {
                            vVar.V(-286310244);
                            r0.p pVarJ11 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                            y1 y1Var12 = c0.u.f1329a;
                            r0.p pVarG1112 = p3.p.g(androidx.compose.foundation.a.b(pVarJ11, ((c0.s) vVar.j(y1Var12)).f1246c, y0.l0.f6642a), f5);
                            n1.f0 f0VarE11 = r.o.e(r0.b.f5657h, false);
                            iHashCode2 = Long.hashCode(vVar.S);
                            w1 w1VarL1112 = vVar.l();
                            r0.p pVarC1112 = r0.a.c(vVar, pVarG1112);
                            vVar.Y();
                            float f118 = f5;
                            if (vVar.R) {
                                vVar.k(aVar5);
                            } else {
                                vVar.i0();
                            }
                            f0.l.x(hVar, vVar, f0VarE11);
                            f0.l.x(hVar2, vVar, w1VarL1112);
                            if (vVar.R) {
                                b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                            } else {
                                b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                            }
                            f0.l.x(hVar4, vVar, pVarC1112);
                            long j15 = ((c0.s) vVar.j(y1Var12)).f1247d;
                            hVar5 = hVar2;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            f6 = f118;
                            c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j15, vVar, ((i18 >> 6) & 14) | 432, 0);
                            z7 = true;
                            vVar.p(true);
                            i19 = 0;
                            vVar.p(false);
                        } else {
                            f6 = f5;
                            hVar5 = hVar2;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            z7 = true;
                            i19 = 0;
                            vVar.V(-286291965);
                            r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                            vVar.p(false);
                        }
                        if (1.0f > 0.0d) {
                            throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                        }
                        r0.p pVarG1113 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                        r.v vVarA11 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                        iHashCode3 = Long.hashCode(vVar.S);
                        w1 w1VarL1113 = vVar.l();
                        r0.p pVarC1113 = r0.a.c(vVar, pVarG1113);
                        vVar.Y();
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        f0.l.x(hVar, vVar, vVarA11);
                        f0.l.x(hVar5, vVar, w1VarL1113);
                        if (vVar.R) {
                            b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                        } else {
                            b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                        }
                        f0.l.x(hVar4, vVar, pVarC1113);
                        y1Var = t4.f1328a;
                        eVar8 = eVar4;
                        a4.a aVar16 = aVar4;
                        z8 = i19;
                        boolean z19 = z7;
                        n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                        if (str5 != null) {
                            vVar.V(1662633282);
                            str6 = str5;
                            n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                            vVar.p(z8);
                        } else {
                            str6 = str5;
                            vVar.V(1662835526);
                            vVar.p(z8);
                        }
                        vVar.p(z19);
                        if (eVar8 == null) {
                            vVar.V(-284517805);
                        } else {
                            vVar.V(-286272658);
                            eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                        }
                        vVar.p(z8);
                        vVar.p(z19);
                        eVar5 = eVar7;
                        eVar6 = eVar8;
                        aVar3 = aVar16;
                        str4 = str6;
                        z5 = z6;
                    } else {
                        vVar.Q();
                        z5 = z3;
                        str4 = str3;
                        eVar5 = eVar3;
                        eVar6 = eVar4;
                        aVar3 = aVar2;
                    }
                    c0Var2 = c0Var;
                    b2VarR = vVar.r();
                    if (b2VarR != null) {
                        b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                            @Override // a4.e
                            public final Object invoke(Object obj, Object obj2) {
                                int iIntValue = ((Integer) obj2).intValue();
                                return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                            }
                        };
                    }
                }
                i7 |= 196608;
                aVar2 = aVar;
                i16 = i6 & 64;
                if (i16 != 0) {
                    i7 |= 1572864;
                    z3 = z2;
                } else {
                    z3 = z2;
                    if ((i5 & 1572864) == 0) {
                        if (vVar.g(z3)) {
                            i17 = 1048576;
                        } else {
                            i17 = 524288;
                        }
                        i7 |= i17;
                    }
                }
                i18 = i7;
                if ((i18 & 599187) != 599186) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (vVar.N(i18 & 1, z4)) {
                    if (i20 != 0) {
                        str5 = null;
                    } else {
                        str5 = str3;
                    }
                    if (i8 != 0) {
                        eVar3 = null;
                    }
                    if (i10 != 0) {
                        c0Var = null;
                    } else {
                        c0Var = c0Var;
                    }
                    if (i12 != 0) {
                        eVar4 = null;
                    }
                    if (i14 != 0) {
                        aVar4 = null;
                    } else {
                        aVar4 = aVar2;
                    }
                    if (i16 != 0) {
                        z6 = true;
                    } else {
                        z6 = z3;
                    }
                    r0.p pVarE114 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                    mVar = r0.m.f5680a;
                    if (z6) {
                        pVarE = mVar;
                    } else {
                        pVarE = mVar;
                    }
                    r0.p pVarE115 = pVarE114.e(pVarE);
                    float f119 = 16;
                    r0.p pVarG1114 = androidx.compose.foundation.layout.a.g(pVarE115, f119, 12);
                    r0.g gVar12 = r0.b.f5663n;
                    r.b bVar12 = r.j.f5571a;
                    r.s0 s0VarA12 = r.q0.a(new r.f(f119), gVar12, vVar, 54);
                    iHashCode = Long.hashCode(vVar.S);
                    w1 w1VarL1114 = vVar.l();
                    r0.p pVarC1114 = r0.a.c(vVar, pVarG1114);
                    p1.j.f4892c.getClass();
                    aVar5 = p1.i.f4867b;
                    vVar.Y();
                    if (vVar.R) {
                        vVar.k(aVar5);
                    } else {
                        vVar.i0();
                    }
                    hVar = p1.i.f4870e;
                    f0.l.x(hVar, vVar, s0VarA12);
                    hVar2 = p1.i.f4869d;
                    f0.l.x(hVar2, vVar, w1VarL1114);
                    hVar3 = p1.i.f4871f;
                    if (vVar.R) {
                        b.b.i(iHashCode, vVar, iHashCode, hVar3);
                    } else {
                        b.b.i(iHashCode, vVar, iHashCode, hVar3);
                    }
                    hVar4 = p1.i.f4868c;
                    f0.l.x(hVar4, vVar, pVarC1114);
                    if (z6) {
                        f5 = 1.0f;
                    } else {
                        f5 = 0.38f;
                    }
                    if (c0Var != null) {
                        vVar.V(-285980136);
                        d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                        vVar.p(false);
                        f6 = f5;
                        hVar5 = hVar2;
                        i19 = 0;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        z7 = true;
                    } else if (eVar3 != null) {
                        vVar.V(-286310244);
                        r0.p pVarJ12 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                        y1 y1Var13 = c0.u.f1329a;
                        r0.p pVarG1115 = p3.p.g(androidx.compose.foundation.a.b(pVarJ12, ((c0.s) vVar.j(y1Var13)).f1246c, y0.l0.f6642a), f5);
                        n1.f0 f0VarE12 = r.o.e(r0.b.f5657h, false);
                        iHashCode2 = Long.hashCode(vVar.S);
                        w1 w1VarL1115 = vVar.l();
                        r0.p pVarC1115 = r0.a.c(vVar, pVarG1115);
                        vVar.Y();
                        float f1110 = f5;
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        f0.l.x(hVar, vVar, f0VarE12);
                        f0.l.x(hVar2, vVar, w1VarL1115);
                        if (vVar.R) {
                            b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                        } else {
                            b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                        }
                        f0.l.x(hVar4, vVar, pVarC1115);
                        long j16 = ((c0.s) vVar.j(y1Var13)).f1247d;
                        hVar5 = hVar2;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        f6 = f1110;
                        c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j16, vVar, ((i18 >> 6) & 14) | 432, 0);
                        z7 = true;
                        vVar.p(true);
                        i19 = 0;
                        vVar.p(false);
                    } else {
                        f6 = f5;
                        hVar5 = hVar2;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        z7 = true;
                        i19 = 0;
                        vVar.V(-286291965);
                        r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                        vVar.p(false);
                    }
                    if (1.0f > 0.0d) {
                        throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                    }
                    r0.p pVarG1116 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                    r.v vVarA12 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                    iHashCode3 = Long.hashCode(vVar.S);
                    w1 w1VarL1116 = vVar.l();
                    r0.p pVarC1116 = r0.a.c(vVar, pVarG1116);
                    vVar.Y();
                    if (vVar.R) {
                        vVar.k(aVar5);
                    } else {
                        vVar.i0();
                    }
                    f0.l.x(hVar, vVar, vVarA12);
                    f0.l.x(hVar5, vVar, w1VarL1116);
                    if (vVar.R) {
                        b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                    } else {
                        b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                    }
                    f0.l.x(hVar4, vVar, pVarC1116);
                    y1Var = t4.f1328a;
                    eVar8 = eVar4;
                    a4.a aVar17 = aVar4;
                    z8 = i19;
                    boolean z110 = z7;
                    n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                    if (str5 != null) {
                        vVar.V(1662633282);
                        str6 = str5;
                        n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                        vVar.p(z8);
                    } else {
                        str6 = str5;
                        vVar.V(1662835526);
                        vVar.p(z8);
                    }
                    vVar.p(z110);
                    if (eVar8 == null) {
                        vVar.V(-284517805);
                    } else {
                        vVar.V(-286272658);
                        eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                    }
                    vVar.p(z8);
                    vVar.p(z110);
                    eVar5 = eVar7;
                    eVar6 = eVar8;
                    aVar3 = aVar17;
                    str4 = str6;
                    z5 = z6;
                } else {
                    vVar.Q();
                    z5 = z3;
                    str4 = str3;
                    eVar5 = eVar3;
                    eVar6 = eVar4;
                    aVar3 = aVar2;
                }
                c0Var2 = c0Var;
                b2VarR = vVar.r();
                if (b2VarR != null) {
                    b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                        @Override // a4.e
                        public final Object invoke(Object obj, Object obj2) {
                            int iIntValue = ((Integer) obj2).intValue();
                            return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                        }
                    };
                }
            }
            i7 |= 3072;
            i12 = i6 & 16;
            if (i12 != 0) {
                if ((i5 & 24576) == 0) {
                    eVar4 = eVar2;
                    if (vVar.h(eVar4)) {
                        i13 = 16384;
                    } else {
                        i13 = 8192;
                    }
                    i7 |= i13;
                }
                i14 = i6 & 32;
                if (i14 != 0) {
                    if ((196608 & i5) == 0) {
                        aVar2 = aVar;
                        if (vVar.h(aVar2)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i7 |= i15;
                    }
                    i16 = i6 & 64;
                    if (i16 != 0) {
                        i7 |= 1572864;
                        z3 = z2;
                    } else {
                        z3 = z2;
                        if ((i5 & 1572864) == 0) {
                            if (vVar.g(z3)) {
                                i17 = 1048576;
                            } else {
                                i17 = 524288;
                            }
                            i7 |= i17;
                        }
                    }
                    i18 = i7;
                    if ((i18 & 599187) != 599186) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (vVar.N(i18 & 1, z4)) {
                        if (i20 != 0) {
                            str5 = null;
                        } else {
                            str5 = str3;
                        }
                        if (i8 != 0) {
                            eVar3 = null;
                        }
                        if (i10 != 0) {
                            c0Var = null;
                        } else {
                            c0Var = c0Var;
                        }
                        if (i12 != 0) {
                            eVar4 = null;
                        }
                        if (i14 != 0) {
                            aVar4 = null;
                        } else {
                            aVar4 = aVar2;
                        }
                        if (i16 != 0) {
                            z6 = true;
                        } else {
                            z6 = z3;
                        }
                        r0.p pVarE116 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                        mVar = r0.m.f5680a;
                        if (z6) {
                            pVarE = mVar;
                        } else {
                            pVarE = mVar;
                        }
                        r0.p pVarE117 = pVarE116.e(pVarE);
                        float f1111 = 16;
                        r0.p pVarG1117 = androidx.compose.foundation.layout.a.g(pVarE117, f1111, 12);
                        r0.g gVar13 = r0.b.f5663n;
                        r.b bVar13 = r.j.f5571a;
                        r.s0 s0VarA13 = r.q0.a(new r.f(f1111), gVar13, vVar, 54);
                        iHashCode = Long.hashCode(vVar.S);
                        w1 w1VarL1117 = vVar.l();
                        r0.p pVarC1117 = r0.a.c(vVar, pVarG1117);
                        p1.j.f4892c.getClass();
                        aVar5 = p1.i.f4867b;
                        vVar.Y();
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        hVar = p1.i.f4870e;
                        f0.l.x(hVar, vVar, s0VarA13);
                        hVar2 = p1.i.f4869d;
                        f0.l.x(hVar2, vVar, w1VarL1117);
                        hVar3 = p1.i.f4871f;
                        if (vVar.R) {
                            b.b.i(iHashCode, vVar, iHashCode, hVar3);
                        } else {
                            b.b.i(iHashCode, vVar, iHashCode, hVar3);
                        }
                        hVar4 = p1.i.f4868c;
                        f0.l.x(hVar4, vVar, pVarC1117);
                        if (z6) {
                            f5 = 1.0f;
                        } else {
                            f5 = 0.38f;
                        }
                        if (c0Var != null) {
                            vVar.V(-285980136);
                            d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                            vVar.p(false);
                            f6 = f5;
                            hVar5 = hVar2;
                            i19 = 0;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            z7 = true;
                        } else if (eVar3 != null) {
                            vVar.V(-286310244);
                            r0.p pVarJ13 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                            y1 y1Var14 = c0.u.f1329a;
                            r0.p pVarG1118 = p3.p.g(androidx.compose.foundation.a.b(pVarJ13, ((c0.s) vVar.j(y1Var14)).f1246c, y0.l0.f6642a), f5);
                            n1.f0 f0VarE13 = r.o.e(r0.b.f5657h, false);
                            iHashCode2 = Long.hashCode(vVar.S);
                            w1 w1VarL1118 = vVar.l();
                            r0.p pVarC1118 = r0.a.c(vVar, pVarG1118);
                            vVar.Y();
                            float f1112 = f5;
                            if (vVar.R) {
                                vVar.k(aVar5);
                            } else {
                                vVar.i0();
                            }
                            f0.l.x(hVar, vVar, f0VarE13);
                            f0.l.x(hVar2, vVar, w1VarL1118);
                            if (vVar.R) {
                                b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                            } else {
                                b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                            }
                            f0.l.x(hVar4, vVar, pVarC1118);
                            long j17 = ((c0.s) vVar.j(y1Var14)).f1247d;
                            hVar5 = hVar2;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            f6 = f1112;
                            c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j17, vVar, ((i18 >> 6) & 14) | 432, 0);
                            z7 = true;
                            vVar.p(true);
                            i19 = 0;
                            vVar.p(false);
                        } else {
                            f6 = f5;
                            hVar5 = hVar2;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            z7 = true;
                            i19 = 0;
                            vVar.V(-286291965);
                            r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                            vVar.p(false);
                        }
                        if (1.0f > 0.0d) {
                            throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                        }
                        r0.p pVarG1119 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                        r.v vVarA13 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                        iHashCode3 = Long.hashCode(vVar.S);
                        w1 w1VarL1119 = vVar.l();
                        r0.p pVarC1119 = r0.a.c(vVar, pVarG1119);
                        vVar.Y();
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        f0.l.x(hVar, vVar, vVarA13);
                        f0.l.x(hVar5, vVar, w1VarL1119);
                        if (vVar.R) {
                            b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                        } else {
                            b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                        }
                        f0.l.x(hVar4, vVar, pVarC1119);
                        y1Var = t4.f1328a;
                        eVar8 = eVar4;
                        a4.a aVar18 = aVar4;
                        z8 = i19;
                        boolean z111 = z7;
                        n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                        if (str5 != null) {
                            vVar.V(1662633282);
                            str6 = str5;
                            n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                            vVar.p(z8);
                        } else {
                            str6 = str5;
                            vVar.V(1662835526);
                            vVar.p(z8);
                        }
                        vVar.p(z111);
                        if (eVar8 == null) {
                            vVar.V(-284517805);
                        } else {
                            vVar.V(-286272658);
                            eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                        }
                        vVar.p(z8);
                        vVar.p(z111);
                        eVar5 = eVar7;
                        eVar6 = eVar8;
                        aVar3 = aVar18;
                        str4 = str6;
                        z5 = z6;
                    } else {
                        vVar.Q();
                        z5 = z3;
                        str4 = str3;
                        eVar5 = eVar3;
                        eVar6 = eVar4;
                        aVar3 = aVar2;
                    }
                    c0Var2 = c0Var;
                    b2VarR = vVar.r();
                    if (b2VarR != null) {
                        b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                            @Override // a4.e
                            public final Object invoke(Object obj, Object obj2) {
                                int iIntValue = ((Integer) obj2).intValue();
                                return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                            }
                        };
                    }
                }
                i7 |= 196608;
                aVar2 = aVar;
                i16 = i6 & 64;
                if (i16 != 0) {
                    i7 |= 1572864;
                    z3 = z2;
                } else {
                    z3 = z2;
                    if ((i5 & 1572864) == 0) {
                        if (vVar.g(z3)) {
                            i17 = 1048576;
                        } else {
                            i17 = 524288;
                        }
                        i7 |= i17;
                    }
                }
                i18 = i7;
                if ((i18 & 599187) != 599186) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (vVar.N(i18 & 1, z4)) {
                    if (i20 != 0) {
                        str5 = null;
                    } else {
                        str5 = str3;
                    }
                    if (i8 != 0) {
                        eVar3 = null;
                    }
                    if (i10 != 0) {
                        c0Var = null;
                    } else {
                        c0Var = c0Var;
                    }
                    if (i12 != 0) {
                        eVar4 = null;
                    }
                    if (i14 != 0) {
                        aVar4 = null;
                    } else {
                        aVar4 = aVar2;
                    }
                    if (i16 != 0) {
                        z6 = true;
                    } else {
                        z6 = z3;
                    }
                    r0.p pVarE118 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                    mVar = r0.m.f5680a;
                    if (z6) {
                        pVarE = mVar;
                    } else {
                        pVarE = mVar;
                    }
                    r0.p pVarE119 = pVarE118.e(pVarE);
                    float f1113 = 16;
                    r0.p pVarG11110 = androidx.compose.foundation.layout.a.g(pVarE119, f1113, 12);
                    r0.g gVar14 = r0.b.f5663n;
                    r.b bVar14 = r.j.f5571a;
                    r.s0 s0VarA14 = r.q0.a(new r.f(f1113), gVar14, vVar, 54);
                    iHashCode = Long.hashCode(vVar.S);
                    w1 w1VarL11110 = vVar.l();
                    r0.p pVarC11110 = r0.a.c(vVar, pVarG11110);
                    p1.j.f4892c.getClass();
                    aVar5 = p1.i.f4867b;
                    vVar.Y();
                    if (vVar.R) {
                        vVar.k(aVar5);
                    } else {
                        vVar.i0();
                    }
                    hVar = p1.i.f4870e;
                    f0.l.x(hVar, vVar, s0VarA14);
                    hVar2 = p1.i.f4869d;
                    f0.l.x(hVar2, vVar, w1VarL11110);
                    hVar3 = p1.i.f4871f;
                    if (vVar.R) {
                        b.b.i(iHashCode, vVar, iHashCode, hVar3);
                    } else {
                        b.b.i(iHashCode, vVar, iHashCode, hVar3);
                    }
                    hVar4 = p1.i.f4868c;
                    f0.l.x(hVar4, vVar, pVarC11110);
                    if (z6) {
                        f5 = 1.0f;
                    } else {
                        f5 = 0.38f;
                    }
                    if (c0Var != null) {
                        vVar.V(-285980136);
                        d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                        vVar.p(false);
                        f6 = f5;
                        hVar5 = hVar2;
                        i19 = 0;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        z7 = true;
                    } else if (eVar3 != null) {
                        vVar.V(-286310244);
                        r0.p pVarJ14 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                        y1 y1Var15 = c0.u.f1329a;
                        r0.p pVarG11111 = p3.p.g(androidx.compose.foundation.a.b(pVarJ14, ((c0.s) vVar.j(y1Var15)).f1246c, y0.l0.f6642a), f5);
                        n1.f0 f0VarE14 = r.o.e(r0.b.f5657h, false);
                        iHashCode2 = Long.hashCode(vVar.S);
                        w1 w1VarL11111 = vVar.l();
                        r0.p pVarC11111 = r0.a.c(vVar, pVarG11111);
                        vVar.Y();
                        float f1114 = f5;
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        f0.l.x(hVar, vVar, f0VarE14);
                        f0.l.x(hVar2, vVar, w1VarL11111);
                        if (vVar.R) {
                            b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                        } else {
                            b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                        }
                        f0.l.x(hVar4, vVar, pVarC11111);
                        long j18 = ((c0.s) vVar.j(y1Var15)).f1247d;
                        hVar5 = hVar2;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        f6 = f1114;
                        c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j18, vVar, ((i18 >> 6) & 14) | 432, 0);
                        z7 = true;
                        vVar.p(true);
                        i19 = 0;
                        vVar.p(false);
                    } else {
                        f6 = f5;
                        hVar5 = hVar2;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        z7 = true;
                        i19 = 0;
                        vVar.V(-286291965);
                        r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                        vVar.p(false);
                    }
                    if (1.0f > 0.0d) {
                        throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                    }
                    r0.p pVarG11112 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                    r.v vVarA14 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                    iHashCode3 = Long.hashCode(vVar.S);
                    w1 w1VarL11112 = vVar.l();
                    r0.p pVarC11112 = r0.a.c(vVar, pVarG11112);
                    vVar.Y();
                    if (vVar.R) {
                        vVar.k(aVar5);
                    } else {
                        vVar.i0();
                    }
                    f0.l.x(hVar, vVar, vVarA14);
                    f0.l.x(hVar5, vVar, w1VarL11112);
                    if (vVar.R) {
                        b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                    } else {
                        b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                    }
                    f0.l.x(hVar4, vVar, pVarC11112);
                    y1Var = t4.f1328a;
                    eVar8 = eVar4;
                    a4.a aVar19 = aVar4;
                    z8 = i19;
                    boolean z112 = z7;
                    n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                    if (str5 != null) {
                        vVar.V(1662633282);
                        str6 = str5;
                        n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                        vVar.p(z8);
                    } else {
                        str6 = str5;
                        vVar.V(1662835526);
                        vVar.p(z8);
                    }
                    vVar.p(z112);
                    if (eVar8 == null) {
                        vVar.V(-284517805);
                    } else {
                        vVar.V(-286272658);
                        eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                    }
                    vVar.p(z8);
                    vVar.p(z112);
                    eVar5 = eVar7;
                    eVar6 = eVar8;
                    aVar3 = aVar19;
                    str4 = str6;
                    z5 = z6;
                } else {
                    vVar.Q();
                    z5 = z3;
                    str4 = str3;
                    eVar5 = eVar3;
                    eVar6 = eVar4;
                    aVar3 = aVar2;
                }
                c0Var2 = c0Var;
                b2VarR = vVar.r();
                if (b2VarR != null) {
                    b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                        @Override // a4.e
                        public final Object invoke(Object obj, Object obj2) {
                            int iIntValue = ((Integer) obj2).intValue();
                            return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                        }
                    };
                }
            }
            i7 |= 24576;
            eVar4 = eVar2;
            i14 = i6 & 32;
            if (i14 != 0) {
                if ((196608 & i5) == 0) {
                    aVar2 = aVar;
                    if (vVar.h(aVar2)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i7 |= i15;
                }
                i16 = i6 & 64;
                if (i16 != 0) {
                    i7 |= 1572864;
                    z3 = z2;
                } else {
                    z3 = z2;
                    if ((i5 & 1572864) == 0) {
                        if (vVar.g(z3)) {
                            i17 = 1048576;
                        } else {
                            i17 = 524288;
                        }
                        i7 |= i17;
                    }
                }
                i18 = i7;
                if ((i18 & 599187) != 599186) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (vVar.N(i18 & 1, z4)) {
                    if (i20 != 0) {
                        str5 = null;
                    } else {
                        str5 = str3;
                    }
                    if (i8 != 0) {
                        eVar3 = null;
                    }
                    if (i10 != 0) {
                        c0Var = null;
                    } else {
                        c0Var = c0Var;
                    }
                    if (i12 != 0) {
                        eVar4 = null;
                    }
                    if (i14 != 0) {
                        aVar4 = null;
                    } else {
                        aVar4 = aVar2;
                    }
                    if (i16 != 0) {
                        z6 = true;
                    } else {
                        z6 = z3;
                    }
                    r0.p pVarE1110 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                    mVar = r0.m.f5680a;
                    if (z6) {
                        pVarE = mVar;
                    } else {
                        pVarE = mVar;
                    }
                    r0.p pVarE1111 = pVarE1110.e(pVarE);
                    float f1115 = 16;
                    r0.p pVarG11113 = androidx.compose.foundation.layout.a.g(pVarE1111, f1115, 12);
                    r0.g gVar15 = r0.b.f5663n;
                    r.b bVar15 = r.j.f5571a;
                    r.s0 s0VarA15 = r.q0.a(new r.f(f1115), gVar15, vVar, 54);
                    iHashCode = Long.hashCode(vVar.S);
                    w1 w1VarL11113 = vVar.l();
                    r0.p pVarC11113 = r0.a.c(vVar, pVarG11113);
                    p1.j.f4892c.getClass();
                    aVar5 = p1.i.f4867b;
                    vVar.Y();
                    if (vVar.R) {
                        vVar.k(aVar5);
                    } else {
                        vVar.i0();
                    }
                    hVar = p1.i.f4870e;
                    f0.l.x(hVar, vVar, s0VarA15);
                    hVar2 = p1.i.f4869d;
                    f0.l.x(hVar2, vVar, w1VarL11113);
                    hVar3 = p1.i.f4871f;
                    if (vVar.R) {
                        b.b.i(iHashCode, vVar, iHashCode, hVar3);
                    } else {
                        b.b.i(iHashCode, vVar, iHashCode, hVar3);
                    }
                    hVar4 = p1.i.f4868c;
                    f0.l.x(hVar4, vVar, pVarC11113);
                    if (z6) {
                        f5 = 1.0f;
                    } else {
                        f5 = 0.38f;
                    }
                    if (c0Var != null) {
                        vVar.V(-285980136);
                        d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                        vVar.p(false);
                        f6 = f5;
                        hVar5 = hVar2;
                        i19 = 0;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        z7 = true;
                    } else if (eVar3 != null) {
                        vVar.V(-286310244);
                        r0.p pVarJ15 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                        y1 y1Var16 = c0.u.f1329a;
                        r0.p pVarG11114 = p3.p.g(androidx.compose.foundation.a.b(pVarJ15, ((c0.s) vVar.j(y1Var16)).f1246c, y0.l0.f6642a), f5);
                        n1.f0 f0VarE15 = r.o.e(r0.b.f5657h, false);
                        iHashCode2 = Long.hashCode(vVar.S);
                        w1 w1VarL11114 = vVar.l();
                        r0.p pVarC11114 = r0.a.c(vVar, pVarG11114);
                        vVar.Y();
                        float f1116 = f5;
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        f0.l.x(hVar, vVar, f0VarE15);
                        f0.l.x(hVar2, vVar, w1VarL11114);
                        if (vVar.R) {
                            b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                        } else {
                            b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                        }
                        f0.l.x(hVar4, vVar, pVarC11114);
                        long j19 = ((c0.s) vVar.j(y1Var16)).f1247d;
                        hVar5 = hVar2;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        f6 = f1116;
                        c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j19, vVar, ((i18 >> 6) & 14) | 432, 0);
                        z7 = true;
                        vVar.p(true);
                        i19 = 0;
                        vVar.p(false);
                    } else {
                        f6 = f5;
                        hVar5 = hVar2;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        z7 = true;
                        i19 = 0;
                        vVar.V(-286291965);
                        r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                        vVar.p(false);
                    }
                    if (1.0f > 0.0d) {
                        throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                    }
                    r0.p pVarG11115 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                    r.v vVarA15 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                    iHashCode3 = Long.hashCode(vVar.S);
                    w1 w1VarL11115 = vVar.l();
                    r0.p pVarC11115 = r0.a.c(vVar, pVarG11115);
                    vVar.Y();
                    if (vVar.R) {
                        vVar.k(aVar5);
                    } else {
                        vVar.i0();
                    }
                    f0.l.x(hVar, vVar, vVarA15);
                    f0.l.x(hVar5, vVar, w1VarL11115);
                    if (vVar.R) {
                        b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                    } else {
                        b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                    }
                    f0.l.x(hVar4, vVar, pVarC11115);
                    y1Var = t4.f1328a;
                    eVar8 = eVar4;
                    a4.a aVar110 = aVar4;
                    z8 = i19;
                    boolean z113 = z7;
                    n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                    if (str5 != null) {
                        vVar.V(1662633282);
                        str6 = str5;
                        n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                        vVar.p(z8);
                    } else {
                        str6 = str5;
                        vVar.V(1662835526);
                        vVar.p(z8);
                    }
                    vVar.p(z113);
                    if (eVar8 == null) {
                        vVar.V(-284517805);
                    } else {
                        vVar.V(-286272658);
                        eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                    }
                    vVar.p(z8);
                    vVar.p(z113);
                    eVar5 = eVar7;
                    eVar6 = eVar8;
                    aVar3 = aVar110;
                    str4 = str6;
                    z5 = z6;
                } else {
                    vVar.Q();
                    z5 = z3;
                    str4 = str3;
                    eVar5 = eVar3;
                    eVar6 = eVar4;
                    aVar3 = aVar2;
                }
                c0Var2 = c0Var;
                b2VarR = vVar.r();
                if (b2VarR != null) {
                    b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                        @Override // a4.e
                        public final Object invoke(Object obj, Object obj2) {
                            int iIntValue = ((Integer) obj2).intValue();
                            return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                        }
                    };
                }
            }
            i7 |= 196608;
            aVar2 = aVar;
            i16 = i6 & 64;
            if (i16 != 0) {
                i7 |= 1572864;
                z3 = z2;
            } else {
                z3 = z2;
                if ((i5 & 1572864) == 0) {
                    if (vVar.g(z3)) {
                        i17 = 1048576;
                    } else {
                        i17 = 524288;
                    }
                    i7 |= i17;
                }
            }
            i18 = i7;
            if ((i18 & 599187) != 599186) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (vVar.N(i18 & 1, z4)) {
                if (i20 != 0) {
                    str5 = null;
                } else {
                    str5 = str3;
                }
                if (i8 != 0) {
                    eVar3 = null;
                }
                if (i10 != 0) {
                    c0Var = null;
                } else {
                    c0Var = c0Var;
                }
                if (i12 != 0) {
                    eVar4 = null;
                }
                if (i14 != 0) {
                    aVar4 = null;
                } else {
                    aVar4 = aVar2;
                }
                if (i16 != 0) {
                    z6 = true;
                } else {
                    z6 = z3;
                }
                r0.p pVarE1112 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                mVar = r0.m.f5680a;
                if (z6) {
                    pVarE = mVar;
                } else {
                    pVarE = mVar;
                }
                r0.p pVarE1113 = pVarE1112.e(pVarE);
                float f1117 = 16;
                r0.p pVarG11116 = androidx.compose.foundation.layout.a.g(pVarE1113, f1117, 12);
                r0.g gVar16 = r0.b.f5663n;
                r.b bVar16 = r.j.f5571a;
                r.s0 s0VarA16 = r.q0.a(new r.f(f1117), gVar16, vVar, 54);
                iHashCode = Long.hashCode(vVar.S);
                w1 w1VarL11116 = vVar.l();
                r0.p pVarC11116 = r0.a.c(vVar, pVarG11116);
                p1.j.f4892c.getClass();
                aVar5 = p1.i.f4867b;
                vVar.Y();
                if (vVar.R) {
                    vVar.k(aVar5);
                } else {
                    vVar.i0();
                }
                hVar = p1.i.f4870e;
                f0.l.x(hVar, vVar, s0VarA16);
                hVar2 = p1.i.f4869d;
                f0.l.x(hVar2, vVar, w1VarL11116);
                hVar3 = p1.i.f4871f;
                if (vVar.R) {
                    b.b.i(iHashCode, vVar, iHashCode, hVar3);
                } else {
                    b.b.i(iHashCode, vVar, iHashCode, hVar3);
                }
                hVar4 = p1.i.f4868c;
                f0.l.x(hVar4, vVar, pVarC11116);
                if (z6) {
                    f5 = 1.0f;
                } else {
                    f5 = 0.38f;
                }
                if (c0Var != null) {
                    vVar.V(-285980136);
                    d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                    vVar.p(false);
                    f6 = f5;
                    hVar5 = hVar2;
                    i19 = 0;
                    eVar7 = eVar3;
                    hVar6 = hVar3;
                    z7 = true;
                } else if (eVar3 != null) {
                    vVar.V(-286310244);
                    r0.p pVarJ16 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                    y1 y1Var17 = c0.u.f1329a;
                    r0.p pVarG11117 = p3.p.g(androidx.compose.foundation.a.b(pVarJ16, ((c0.s) vVar.j(y1Var17)).f1246c, y0.l0.f6642a), f5);
                    n1.f0 f0VarE16 = r.o.e(r0.b.f5657h, false);
                    iHashCode2 = Long.hashCode(vVar.S);
                    w1 w1VarL11117 = vVar.l();
                    r0.p pVarC11117 = r0.a.c(vVar, pVarG11117);
                    vVar.Y();
                    float f1118 = f5;
                    if (vVar.R) {
                        vVar.k(aVar5);
                    } else {
                        vVar.i0();
                    }
                    f0.l.x(hVar, vVar, f0VarE16);
                    f0.l.x(hVar2, vVar, w1VarL11117);
                    if (vVar.R) {
                        b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                    } else {
                        b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                    }
                    f0.l.x(hVar4, vVar, pVarC11117);
                    long j110 = ((c0.s) vVar.j(y1Var17)).f1247d;
                    hVar5 = hVar2;
                    eVar7 = eVar3;
                    hVar6 = hVar3;
                    f6 = f1118;
                    c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j110, vVar, ((i18 >> 6) & 14) | 432, 0);
                    z7 = true;
                    vVar.p(true);
                    i19 = 0;
                    vVar.p(false);
                } else {
                    f6 = f5;
                    hVar5 = hVar2;
                    eVar7 = eVar3;
                    hVar6 = hVar3;
                    z7 = true;
                    i19 = 0;
                    vVar.V(-286291965);
                    r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                    vVar.p(false);
                }
                if (1.0f > 0.0d) {
                    throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                }
                r0.p pVarG11118 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                r.v vVarA16 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                iHashCode3 = Long.hashCode(vVar.S);
                w1 w1VarL11118 = vVar.l();
                r0.p pVarC11118 = r0.a.c(vVar, pVarG11118);
                vVar.Y();
                if (vVar.R) {
                    vVar.k(aVar5);
                } else {
                    vVar.i0();
                }
                f0.l.x(hVar, vVar, vVarA16);
                f0.l.x(hVar5, vVar, w1VarL11118);
                if (vVar.R) {
                    b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                } else {
                    b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                }
                f0.l.x(hVar4, vVar, pVarC11118);
                y1Var = t4.f1328a;
                eVar8 = eVar4;
                a4.a aVar111 = aVar4;
                z8 = i19;
                boolean z114 = z7;
                n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                if (str5 != null) {
                    vVar.V(1662633282);
                    str6 = str5;
                    n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                    vVar.p(z8);
                } else {
                    str6 = str5;
                    vVar.V(1662835526);
                    vVar.p(z8);
                }
                vVar.p(z114);
                if (eVar8 == null) {
                    vVar.V(-284517805);
                } else {
                    vVar.V(-286272658);
                    eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                }
                vVar.p(z8);
                vVar.p(z114);
                eVar5 = eVar7;
                eVar6 = eVar8;
                aVar3 = aVar111;
                str4 = str6;
                z5 = z6;
            } else {
                vVar.Q();
                z5 = z3;
                str4 = str3;
                eVar5 = eVar3;
                eVar6 = eVar4;
                aVar3 = aVar2;
            }
            c0Var2 = c0Var;
            b2VarR = vVar.r();
            if (b2VarR != null) {
                b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                    @Override // a4.e
                    public final Object invoke(Object obj, Object obj2) {
                        int iIntValue = ((Integer) obj2).intValue();
                        return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                    }
                };
            }
        }
        i7 |= 48;
        str3 = str2;
        i8 = i6 & 4;
        if (i8 != 0) {
            if ((i5 & 384) == 0) {
                eVar3 = eVar;
                if (vVar.f(eVar3)) {
                    i9 = 256;
                } else {
                    i9 = 128;
                }
                i7 |= i9;
            }
            i10 = i6 & 8;
            if (i10 != 0) {
                if ((i5 & 3072) == 0) {
                    if (vVar.h(c0Var)) {
                        i11 = 2048;
                    } else {
                        i11 = 1024;
                    }
                    i7 |= i11;
                }
                i12 = i6 & 16;
                if (i12 != 0) {
                    if ((i5 & 24576) == 0) {
                        eVar4 = eVar2;
                        if (vVar.h(eVar4)) {
                            i13 = 16384;
                        } else {
                            i13 = 8192;
                        }
                        i7 |= i13;
                    }
                    i14 = i6 & 32;
                    if (i14 != 0) {
                        if ((196608 & i5) == 0) {
                            aVar2 = aVar;
                            if (vVar.h(aVar2)) {
                                i15 = 131072;
                            } else {
                                i15 = 65536;
                            }
                            i7 |= i15;
                        }
                        i16 = i6 & 64;
                        if (i16 != 0) {
                            i7 |= 1572864;
                            z3 = z2;
                        } else {
                            z3 = z2;
                            if ((i5 & 1572864) == 0) {
                                if (vVar.g(z3)) {
                                    i17 = 1048576;
                                } else {
                                    i17 = 524288;
                                }
                                i7 |= i17;
                            }
                        }
                        i18 = i7;
                        if ((i18 & 599187) != 599186) {
                            z4 = true;
                        } else {
                            z4 = false;
                        }
                        if (vVar.N(i18 & 1, z4)) {
                            if (i20 != 0) {
                                str5 = null;
                            } else {
                                str5 = str3;
                            }
                            if (i8 != 0) {
                                eVar3 = null;
                            }
                            if (i10 != 0) {
                                c0Var = null;
                            } else {
                                c0Var = c0Var;
                            }
                            if (i12 != 0) {
                                eVar4 = null;
                            }
                            if (i14 != 0) {
                                aVar4 = null;
                            } else {
                                aVar4 = aVar2;
                            }
                            if (i16 != 0) {
                                z6 = true;
                            } else {
                                z6 = z3;
                            }
                            r0.p pVarE1114 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                            mVar = r0.m.f5680a;
                            if (z6) {
                                pVarE = mVar;
                            } else {
                                pVarE = mVar;
                            }
                            r0.p pVarE1115 = pVarE1114.e(pVarE);
                            float f1119 = 16;
                            r0.p pVarG11119 = androidx.compose.foundation.layout.a.g(pVarE1115, f1119, 12);
                            r0.g gVar17 = r0.b.f5663n;
                            r.b bVar17 = r.j.f5571a;
                            r.s0 s0VarA17 = r.q0.a(new r.f(f1119), gVar17, vVar, 54);
                            iHashCode = Long.hashCode(vVar.S);
                            w1 w1VarL11119 = vVar.l();
                            r0.p pVarC11119 = r0.a.c(vVar, pVarG11119);
                            p1.j.f4892c.getClass();
                            aVar5 = p1.i.f4867b;
                            vVar.Y();
                            if (vVar.R) {
                                vVar.k(aVar5);
                            } else {
                                vVar.i0();
                            }
                            hVar = p1.i.f4870e;
                            f0.l.x(hVar, vVar, s0VarA17);
                            hVar2 = p1.i.f4869d;
                            f0.l.x(hVar2, vVar, w1VarL11119);
                            hVar3 = p1.i.f4871f;
                            if (vVar.R) {
                                b.b.i(iHashCode, vVar, iHashCode, hVar3);
                            } else {
                                b.b.i(iHashCode, vVar, iHashCode, hVar3);
                            }
                            hVar4 = p1.i.f4868c;
                            f0.l.x(hVar4, vVar, pVarC11119);
                            if (z6) {
                                f5 = 1.0f;
                            } else {
                                f5 = 0.38f;
                            }
                            if (c0Var != null) {
                                vVar.V(-285980136);
                                d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                                vVar.p(false);
                                f6 = f5;
                                hVar5 = hVar2;
                                i19 = 0;
                                eVar7 = eVar3;
                                hVar6 = hVar3;
                                z7 = true;
                            } else if (eVar3 != null) {
                                vVar.V(-286310244);
                                r0.p pVarJ17 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                                y1 y1Var18 = c0.u.f1329a;
                                r0.p pVarG111110 = p3.p.g(androidx.compose.foundation.a.b(pVarJ17, ((c0.s) vVar.j(y1Var18)).f1246c, y0.l0.f6642a), f5);
                                n1.f0 f0VarE17 = r.o.e(r0.b.f5657h, false);
                                iHashCode2 = Long.hashCode(vVar.S);
                                w1 w1VarL111110 = vVar.l();
                                r0.p pVarC111110 = r0.a.c(vVar, pVarG111110);
                                vVar.Y();
                                float f11110 = f5;
                                if (vVar.R) {
                                    vVar.k(aVar5);
                                } else {
                                    vVar.i0();
                                }
                                f0.l.x(hVar, vVar, f0VarE17);
                                f0.l.x(hVar2, vVar, w1VarL111110);
                                if (vVar.R) {
                                    b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                                } else {
                                    b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                                }
                                f0.l.x(hVar4, vVar, pVarC111110);
                                long j111 = ((c0.s) vVar.j(y1Var18)).f1247d;
                                hVar5 = hVar2;
                                eVar7 = eVar3;
                                hVar6 = hVar3;
                                f6 = f11110;
                                c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j111, vVar, ((i18 >> 6) & 14) | 432, 0);
                                z7 = true;
                                vVar.p(true);
                                i19 = 0;
                                vVar.p(false);
                            } else {
                                f6 = f5;
                                hVar5 = hVar2;
                                eVar7 = eVar3;
                                hVar6 = hVar3;
                                z7 = true;
                                i19 = 0;
                                vVar.V(-286291965);
                                r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                                vVar.p(false);
                            }
                            if (1.0f > 0.0d) {
                                throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                            }
                            r0.p pVarG111111 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                            r.v vVarA17 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                            iHashCode3 = Long.hashCode(vVar.S);
                            w1 w1VarL111111 = vVar.l();
                            r0.p pVarC111111 = r0.a.c(vVar, pVarG111111);
                            vVar.Y();
                            if (vVar.R) {
                                vVar.k(aVar5);
                            } else {
                                vVar.i0();
                            }
                            f0.l.x(hVar, vVar, vVarA17);
                            f0.l.x(hVar5, vVar, w1VarL111111);
                            if (vVar.R) {
                                b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                            } else {
                                b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                            }
                            f0.l.x(hVar4, vVar, pVarC111111);
                            y1Var = t4.f1328a;
                            eVar8 = eVar4;
                            a4.a aVar112 = aVar4;
                            z8 = i19;
                            boolean z115 = z7;
                            n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                            if (str5 != null) {
                                vVar.V(1662633282);
                                str6 = str5;
                                n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                                vVar.p(z8);
                            } else {
                                str6 = str5;
                                vVar.V(1662835526);
                                vVar.p(z8);
                            }
                            vVar.p(z115);
                            if (eVar8 == null) {
                                vVar.V(-284517805);
                            } else {
                                vVar.V(-286272658);
                                eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                            }
                            vVar.p(z8);
                            vVar.p(z115);
                            eVar5 = eVar7;
                            eVar6 = eVar8;
                            aVar3 = aVar112;
                            str4 = str6;
                            z5 = z6;
                        } else {
                            vVar.Q();
                            z5 = z3;
                            str4 = str3;
                            eVar5 = eVar3;
                            eVar6 = eVar4;
                            aVar3 = aVar2;
                        }
                        c0Var2 = c0Var;
                        b2VarR = vVar.r();
                        if (b2VarR != null) {
                            b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                                @Override // a4.e
                                public final Object invoke(Object obj, Object obj2) {
                                    int iIntValue = ((Integer) obj2).intValue();
                                    return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                                }
                            };
                        }
                    }
                    i7 |= 196608;
                    aVar2 = aVar;
                    i16 = i6 & 64;
                    if (i16 != 0) {
                        i7 |= 1572864;
                        z3 = z2;
                    } else {
                        z3 = z2;
                        if ((i5 & 1572864) == 0) {
                            if (vVar.g(z3)) {
                                i17 = 1048576;
                            } else {
                                i17 = 524288;
                            }
                            i7 |= i17;
                        }
                    }
                    i18 = i7;
                    if ((i18 & 599187) != 599186) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (vVar.N(i18 & 1, z4)) {
                        if (i20 != 0) {
                            str5 = null;
                        } else {
                            str5 = str3;
                        }
                        if (i8 != 0) {
                            eVar3 = null;
                        }
                        if (i10 != 0) {
                            c0Var = null;
                        } else {
                            c0Var = c0Var;
                        }
                        if (i12 != 0) {
                            eVar4 = null;
                        }
                        if (i14 != 0) {
                            aVar4 = null;
                        } else {
                            aVar4 = aVar2;
                        }
                        if (i16 != 0) {
                            z6 = true;
                        } else {
                            z6 = z3;
                        }
                        r0.p pVarE1116 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                        mVar = r0.m.f5680a;
                        if (z6) {
                            pVarE = mVar;
                        } else {
                            pVarE = mVar;
                        }
                        r0.p pVarE1117 = pVarE1116.e(pVarE);
                        float f11111 = 16;
                        r0.p pVarG111112 = androidx.compose.foundation.layout.a.g(pVarE1117, f11111, 12);
                        r0.g gVar18 = r0.b.f5663n;
                        r.b bVar18 = r.j.f5571a;
                        r.s0 s0VarA18 = r.q0.a(new r.f(f11111), gVar18, vVar, 54);
                        iHashCode = Long.hashCode(vVar.S);
                        w1 w1VarL111112 = vVar.l();
                        r0.p pVarC111112 = r0.a.c(vVar, pVarG111112);
                        p1.j.f4892c.getClass();
                        aVar5 = p1.i.f4867b;
                        vVar.Y();
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        hVar = p1.i.f4870e;
                        f0.l.x(hVar, vVar, s0VarA18);
                        hVar2 = p1.i.f4869d;
                        f0.l.x(hVar2, vVar, w1VarL111112);
                        hVar3 = p1.i.f4871f;
                        if (vVar.R) {
                            b.b.i(iHashCode, vVar, iHashCode, hVar3);
                        } else {
                            b.b.i(iHashCode, vVar, iHashCode, hVar3);
                        }
                        hVar4 = p1.i.f4868c;
                        f0.l.x(hVar4, vVar, pVarC111112);
                        if (z6) {
                            f5 = 1.0f;
                        } else {
                            f5 = 0.38f;
                        }
                        if (c0Var != null) {
                            vVar.V(-285980136);
                            d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                            vVar.p(false);
                            f6 = f5;
                            hVar5 = hVar2;
                            i19 = 0;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            z7 = true;
                        } else if (eVar3 != null) {
                            vVar.V(-286310244);
                            r0.p pVarJ18 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                            y1 y1Var19 = c0.u.f1329a;
                            r0.p pVarG111113 = p3.p.g(androidx.compose.foundation.a.b(pVarJ18, ((c0.s) vVar.j(y1Var19)).f1246c, y0.l0.f6642a), f5);
                            n1.f0 f0VarE18 = r.o.e(r0.b.f5657h, false);
                            iHashCode2 = Long.hashCode(vVar.S);
                            w1 w1VarL111113 = vVar.l();
                            r0.p pVarC111113 = r0.a.c(vVar, pVarG111113);
                            vVar.Y();
                            float f11112 = f5;
                            if (vVar.R) {
                                vVar.k(aVar5);
                            } else {
                                vVar.i0();
                            }
                            f0.l.x(hVar, vVar, f0VarE18);
                            f0.l.x(hVar2, vVar, w1VarL111113);
                            if (vVar.R) {
                                b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                            } else {
                                b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                            }
                            f0.l.x(hVar4, vVar, pVarC111113);
                            long j112 = ((c0.s) vVar.j(y1Var19)).f1247d;
                            hVar5 = hVar2;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            f6 = f11112;
                            c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j112, vVar, ((i18 >> 6) & 14) | 432, 0);
                            z7 = true;
                            vVar.p(true);
                            i19 = 0;
                            vVar.p(false);
                        } else {
                            f6 = f5;
                            hVar5 = hVar2;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            z7 = true;
                            i19 = 0;
                            vVar.V(-286291965);
                            r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                            vVar.p(false);
                        }
                        if (1.0f > 0.0d) {
                            throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                        }
                        r0.p pVarG111114 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                        r.v vVarA18 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                        iHashCode3 = Long.hashCode(vVar.S);
                        w1 w1VarL111114 = vVar.l();
                        r0.p pVarC111114 = r0.a.c(vVar, pVarG111114);
                        vVar.Y();
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        f0.l.x(hVar, vVar, vVarA18);
                        f0.l.x(hVar5, vVar, w1VarL111114);
                        if (vVar.R) {
                            b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                        } else {
                            b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                        }
                        f0.l.x(hVar4, vVar, pVarC111114);
                        y1Var = t4.f1328a;
                        eVar8 = eVar4;
                        a4.a aVar113 = aVar4;
                        z8 = i19;
                        boolean z116 = z7;
                        n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                        if (str5 != null) {
                            vVar.V(1662633282);
                            str6 = str5;
                            n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                            vVar.p(z8);
                        } else {
                            str6 = str5;
                            vVar.V(1662835526);
                            vVar.p(z8);
                        }
                        vVar.p(z116);
                        if (eVar8 == null) {
                            vVar.V(-284517805);
                        } else {
                            vVar.V(-286272658);
                            eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                        }
                        vVar.p(z8);
                        vVar.p(z116);
                        eVar5 = eVar7;
                        eVar6 = eVar8;
                        aVar3 = aVar113;
                        str4 = str6;
                        z5 = z6;
                    } else {
                        vVar.Q();
                        z5 = z3;
                        str4 = str3;
                        eVar5 = eVar3;
                        eVar6 = eVar4;
                        aVar3 = aVar2;
                    }
                    c0Var2 = c0Var;
                    b2VarR = vVar.r();
                    if (b2VarR != null) {
                        b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                            @Override // a4.e
                            public final Object invoke(Object obj, Object obj2) {
                                int iIntValue = ((Integer) obj2).intValue();
                                return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                            }
                        };
                    }
                }
                i7 |= 24576;
                eVar4 = eVar2;
                i14 = i6 & 32;
                if (i14 != 0) {
                    if ((196608 & i5) == 0) {
                        aVar2 = aVar;
                        if (vVar.h(aVar2)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i7 |= i15;
                    }
                    i16 = i6 & 64;
                    if (i16 != 0) {
                        i7 |= 1572864;
                        z3 = z2;
                    } else {
                        z3 = z2;
                        if ((i5 & 1572864) == 0) {
                            if (vVar.g(z3)) {
                                i17 = 1048576;
                            } else {
                                i17 = 524288;
                            }
                            i7 |= i17;
                        }
                    }
                    i18 = i7;
                    if ((i18 & 599187) != 599186) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (vVar.N(i18 & 1, z4)) {
                        if (i20 != 0) {
                            str5 = null;
                        } else {
                            str5 = str3;
                        }
                        if (i8 != 0) {
                            eVar3 = null;
                        }
                        if (i10 != 0) {
                            c0Var = null;
                        } else {
                            c0Var = c0Var;
                        }
                        if (i12 != 0) {
                            eVar4 = null;
                        }
                        if (i14 != 0) {
                            aVar4 = null;
                        } else {
                            aVar4 = aVar2;
                        }
                        if (i16 != 0) {
                            z6 = true;
                        } else {
                            z6 = z3;
                        }
                        r0.p pVarE1118 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                        mVar = r0.m.f5680a;
                        if (z6) {
                            pVarE = mVar;
                        } else {
                            pVarE = mVar;
                        }
                        r0.p pVarE1119 = pVarE1118.e(pVarE);
                        float f11113 = 16;
                        r0.p pVarG111115 = androidx.compose.foundation.layout.a.g(pVarE1119, f11113, 12);
                        r0.g gVar19 = r0.b.f5663n;
                        r.b bVar19 = r.j.f5571a;
                        r.s0 s0VarA19 = r.q0.a(new r.f(f11113), gVar19, vVar, 54);
                        iHashCode = Long.hashCode(vVar.S);
                        w1 w1VarL111115 = vVar.l();
                        r0.p pVarC111115 = r0.a.c(vVar, pVarG111115);
                        p1.j.f4892c.getClass();
                        aVar5 = p1.i.f4867b;
                        vVar.Y();
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        hVar = p1.i.f4870e;
                        f0.l.x(hVar, vVar, s0VarA19);
                        hVar2 = p1.i.f4869d;
                        f0.l.x(hVar2, vVar, w1VarL111115);
                        hVar3 = p1.i.f4871f;
                        if (vVar.R) {
                            b.b.i(iHashCode, vVar, iHashCode, hVar3);
                        } else {
                            b.b.i(iHashCode, vVar, iHashCode, hVar3);
                        }
                        hVar4 = p1.i.f4868c;
                        f0.l.x(hVar4, vVar, pVarC111115);
                        if (z6) {
                            f5 = 1.0f;
                        } else {
                            f5 = 0.38f;
                        }
                        if (c0Var != null) {
                            vVar.V(-285980136);
                            d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                            vVar.p(false);
                            f6 = f5;
                            hVar5 = hVar2;
                            i19 = 0;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            z7 = true;
                        } else if (eVar3 != null) {
                            vVar.V(-286310244);
                            r0.p pVarJ19 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                            y1 y1Var110 = c0.u.f1329a;
                            r0.p pVarG111116 = p3.p.g(androidx.compose.foundation.a.b(pVarJ19, ((c0.s) vVar.j(y1Var110)).f1246c, y0.l0.f6642a), f5);
                            n1.f0 f0VarE19 = r.o.e(r0.b.f5657h, false);
                            iHashCode2 = Long.hashCode(vVar.S);
                            w1 w1VarL111116 = vVar.l();
                            r0.p pVarC111116 = r0.a.c(vVar, pVarG111116);
                            vVar.Y();
                            float f11114 = f5;
                            if (vVar.R) {
                                vVar.k(aVar5);
                            } else {
                                vVar.i0();
                            }
                            f0.l.x(hVar, vVar, f0VarE19);
                            f0.l.x(hVar2, vVar, w1VarL111116);
                            if (vVar.R) {
                                b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                            } else {
                                b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                            }
                            f0.l.x(hVar4, vVar, pVarC111116);
                            long j113 = ((c0.s) vVar.j(y1Var110)).f1247d;
                            hVar5 = hVar2;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            f6 = f11114;
                            c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j113, vVar, ((i18 >> 6) & 14) | 432, 0);
                            z7 = true;
                            vVar.p(true);
                            i19 = 0;
                            vVar.p(false);
                        } else {
                            f6 = f5;
                            hVar5 = hVar2;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            z7 = true;
                            i19 = 0;
                            vVar.V(-286291965);
                            r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                            vVar.p(false);
                        }
                        if (1.0f > 0.0d) {
                            throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                        }
                        r0.p pVarG111117 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                        r.v vVarA19 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                        iHashCode3 = Long.hashCode(vVar.S);
                        w1 w1VarL111117 = vVar.l();
                        r0.p pVarC111117 = r0.a.c(vVar, pVarG111117);
                        vVar.Y();
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        f0.l.x(hVar, vVar, vVarA19);
                        f0.l.x(hVar5, vVar, w1VarL111117);
                        if (vVar.R) {
                            b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                        } else {
                            b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                        }
                        f0.l.x(hVar4, vVar, pVarC111117);
                        y1Var = t4.f1328a;
                        eVar8 = eVar4;
                        a4.a aVar114 = aVar4;
                        z8 = i19;
                        boolean z117 = z7;
                        n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                        if (str5 != null) {
                            vVar.V(1662633282);
                            str6 = str5;
                            n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                            vVar.p(z8);
                        } else {
                            str6 = str5;
                            vVar.V(1662835526);
                            vVar.p(z8);
                        }
                        vVar.p(z117);
                        if (eVar8 == null) {
                            vVar.V(-284517805);
                        } else {
                            vVar.V(-286272658);
                            eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                        }
                        vVar.p(z8);
                        vVar.p(z117);
                        eVar5 = eVar7;
                        eVar6 = eVar8;
                        aVar3 = aVar114;
                        str4 = str6;
                        z5 = z6;
                    } else {
                        vVar.Q();
                        z5 = z3;
                        str4 = str3;
                        eVar5 = eVar3;
                        eVar6 = eVar4;
                        aVar3 = aVar2;
                    }
                    c0Var2 = c0Var;
                    b2VarR = vVar.r();
                    if (b2VarR != null) {
                        b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                            @Override // a4.e
                            public final Object invoke(Object obj, Object obj2) {
                                int iIntValue = ((Integer) obj2).intValue();
                                return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                            }
                        };
                    }
                }
                i7 |= 196608;
                aVar2 = aVar;
                i16 = i6 & 64;
                if (i16 != 0) {
                    i7 |= 1572864;
                    z3 = z2;
                } else {
                    z3 = z2;
                    if ((i5 & 1572864) == 0) {
                        if (vVar.g(z3)) {
                            i17 = 1048576;
                        } else {
                            i17 = 524288;
                        }
                        i7 |= i17;
                    }
                }
                i18 = i7;
                if ((i18 & 599187) != 599186) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (vVar.N(i18 & 1, z4)) {
                    if (i20 != 0) {
                        str5 = null;
                    } else {
                        str5 = str3;
                    }
                    if (i8 != 0) {
                        eVar3 = null;
                    }
                    if (i10 != 0) {
                        c0Var = null;
                    } else {
                        c0Var = c0Var;
                    }
                    if (i12 != 0) {
                        eVar4 = null;
                    }
                    if (i14 != 0) {
                        aVar4 = null;
                    } else {
                        aVar4 = aVar2;
                    }
                    if (i16 != 0) {
                        z6 = true;
                    } else {
                        z6 = z3;
                    }
                    r0.p pVarE11110 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                    mVar = r0.m.f5680a;
                    if (z6) {
                        pVarE = mVar;
                    } else {
                        pVarE = mVar;
                    }
                    r0.p pVarE11111 = pVarE11110.e(pVarE);
                    float f11115 = 16;
                    r0.p pVarG111118 = androidx.compose.foundation.layout.a.g(pVarE11111, f11115, 12);
                    r0.g gVar110 = r0.b.f5663n;
                    r.b bVar110 = r.j.f5571a;
                    r.s0 s0VarA110 = r.q0.a(new r.f(f11115), gVar110, vVar, 54);
                    iHashCode = Long.hashCode(vVar.S);
                    w1 w1VarL111118 = vVar.l();
                    r0.p pVarC111118 = r0.a.c(vVar, pVarG111118);
                    p1.j.f4892c.getClass();
                    aVar5 = p1.i.f4867b;
                    vVar.Y();
                    if (vVar.R) {
                        vVar.k(aVar5);
                    } else {
                        vVar.i0();
                    }
                    hVar = p1.i.f4870e;
                    f0.l.x(hVar, vVar, s0VarA110);
                    hVar2 = p1.i.f4869d;
                    f0.l.x(hVar2, vVar, w1VarL111118);
                    hVar3 = p1.i.f4871f;
                    if (vVar.R) {
                        b.b.i(iHashCode, vVar, iHashCode, hVar3);
                    } else {
                        b.b.i(iHashCode, vVar, iHashCode, hVar3);
                    }
                    hVar4 = p1.i.f4868c;
                    f0.l.x(hVar4, vVar, pVarC111118);
                    if (z6) {
                        f5 = 1.0f;
                    } else {
                        f5 = 0.38f;
                    }
                    if (c0Var != null) {
                        vVar.V(-285980136);
                        d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                        vVar.p(false);
                        f6 = f5;
                        hVar5 = hVar2;
                        i19 = 0;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        z7 = true;
                    } else if (eVar3 != null) {
                        vVar.V(-286310244);
                        r0.p pVarJ110 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                        y1 y1Var111 = c0.u.f1329a;
                        r0.p pVarG111119 = p3.p.g(androidx.compose.foundation.a.b(pVarJ110, ((c0.s) vVar.j(y1Var111)).f1246c, y0.l0.f6642a), f5);
                        n1.f0 f0VarE110 = r.o.e(r0.b.f5657h, false);
                        iHashCode2 = Long.hashCode(vVar.S);
                        w1 w1VarL111119 = vVar.l();
                        r0.p pVarC111119 = r0.a.c(vVar, pVarG111119);
                        vVar.Y();
                        float f11116 = f5;
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        f0.l.x(hVar, vVar, f0VarE110);
                        f0.l.x(hVar2, vVar, w1VarL111119);
                        if (vVar.R) {
                            b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                        } else {
                            b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                        }
                        f0.l.x(hVar4, vVar, pVarC111119);
                        long j114 = ((c0.s) vVar.j(y1Var111)).f1247d;
                        hVar5 = hVar2;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        f6 = f11116;
                        c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j114, vVar, ((i18 >> 6) & 14) | 432, 0);
                        z7 = true;
                        vVar.p(true);
                        i19 = 0;
                        vVar.p(false);
                    } else {
                        f6 = f5;
                        hVar5 = hVar2;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        z7 = true;
                        i19 = 0;
                        vVar.V(-286291965);
                        r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                        vVar.p(false);
                    }
                    if (1.0f > 0.0d) {
                        throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                    }
                    r0.p pVarG1111110 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                    r.v vVarA110 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                    iHashCode3 = Long.hashCode(vVar.S);
                    w1 w1VarL1111110 = vVar.l();
                    r0.p pVarC1111110 = r0.a.c(vVar, pVarG1111110);
                    vVar.Y();
                    if (vVar.R) {
                        vVar.k(aVar5);
                    } else {
                        vVar.i0();
                    }
                    f0.l.x(hVar, vVar, vVarA110);
                    f0.l.x(hVar5, vVar, w1VarL1111110);
                    if (vVar.R) {
                        b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                    } else {
                        b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                    }
                    f0.l.x(hVar4, vVar, pVarC1111110);
                    y1Var = t4.f1328a;
                    eVar8 = eVar4;
                    a4.a aVar115 = aVar4;
                    z8 = i19;
                    boolean z118 = z7;
                    n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                    if (str5 != null) {
                        vVar.V(1662633282);
                        str6 = str5;
                        n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                        vVar.p(z8);
                    } else {
                        str6 = str5;
                        vVar.V(1662835526);
                        vVar.p(z8);
                    }
                    vVar.p(z118);
                    if (eVar8 == null) {
                        vVar.V(-284517805);
                    } else {
                        vVar.V(-286272658);
                        eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                    }
                    vVar.p(z8);
                    vVar.p(z118);
                    eVar5 = eVar7;
                    eVar6 = eVar8;
                    aVar3 = aVar115;
                    str4 = str6;
                    z5 = z6;
                } else {
                    vVar.Q();
                    z5 = z3;
                    str4 = str3;
                    eVar5 = eVar3;
                    eVar6 = eVar4;
                    aVar3 = aVar2;
                }
                c0Var2 = c0Var;
                b2VarR = vVar.r();
                if (b2VarR != null) {
                    b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                        @Override // a4.e
                        public final Object invoke(Object obj, Object obj2) {
                            int iIntValue = ((Integer) obj2).intValue();
                            return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                        }
                    };
                }
            }
            i7 |= 3072;
            i12 = i6 & 16;
            if (i12 != 0) {
                if ((i5 & 24576) == 0) {
                    eVar4 = eVar2;
                    if (vVar.h(eVar4)) {
                        i13 = 16384;
                    } else {
                        i13 = 8192;
                    }
                    i7 |= i13;
                }
                i14 = i6 & 32;
                if (i14 != 0) {
                    if ((196608 & i5) == 0) {
                        aVar2 = aVar;
                        if (vVar.h(aVar2)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i7 |= i15;
                    }
                    i16 = i6 & 64;
                    if (i16 != 0) {
                        i7 |= 1572864;
                        z3 = z2;
                    } else {
                        z3 = z2;
                        if ((i5 & 1572864) == 0) {
                            if (vVar.g(z3)) {
                                i17 = 1048576;
                            } else {
                                i17 = 524288;
                            }
                            i7 |= i17;
                        }
                    }
                    i18 = i7;
                    if ((i18 & 599187) != 599186) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (vVar.N(i18 & 1, z4)) {
                        if (i20 != 0) {
                            str5 = null;
                        } else {
                            str5 = str3;
                        }
                        if (i8 != 0) {
                            eVar3 = null;
                        }
                        if (i10 != 0) {
                            c0Var = null;
                        } else {
                            c0Var = c0Var;
                        }
                        if (i12 != 0) {
                            eVar4 = null;
                        }
                        if (i14 != 0) {
                            aVar4 = null;
                        } else {
                            aVar4 = aVar2;
                        }
                        if (i16 != 0) {
                            z6 = true;
                        } else {
                            z6 = z3;
                        }
                        r0.p pVarE11112 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                        mVar = r0.m.f5680a;
                        if (z6) {
                            pVarE = mVar;
                        } else {
                            pVarE = mVar;
                        }
                        r0.p pVarE11113 = pVarE11112.e(pVarE);
                        float f11117 = 16;
                        r0.p pVarG1111111 = androidx.compose.foundation.layout.a.g(pVarE11113, f11117, 12);
                        r0.g gVar111 = r0.b.f5663n;
                        r.b bVar111 = r.j.f5571a;
                        r.s0 s0VarA111 = r.q0.a(new r.f(f11117), gVar111, vVar, 54);
                        iHashCode = Long.hashCode(vVar.S);
                        w1 w1VarL1111111 = vVar.l();
                        r0.p pVarC1111111 = r0.a.c(vVar, pVarG1111111);
                        p1.j.f4892c.getClass();
                        aVar5 = p1.i.f4867b;
                        vVar.Y();
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        hVar = p1.i.f4870e;
                        f0.l.x(hVar, vVar, s0VarA111);
                        hVar2 = p1.i.f4869d;
                        f0.l.x(hVar2, vVar, w1VarL1111111);
                        hVar3 = p1.i.f4871f;
                        if (vVar.R) {
                            b.b.i(iHashCode, vVar, iHashCode, hVar3);
                        } else {
                            b.b.i(iHashCode, vVar, iHashCode, hVar3);
                        }
                        hVar4 = p1.i.f4868c;
                        f0.l.x(hVar4, vVar, pVarC1111111);
                        if (z6) {
                            f5 = 1.0f;
                        } else {
                            f5 = 0.38f;
                        }
                        if (c0Var != null) {
                            vVar.V(-285980136);
                            d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                            vVar.p(false);
                            f6 = f5;
                            hVar5 = hVar2;
                            i19 = 0;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            z7 = true;
                        } else if (eVar3 != null) {
                            vVar.V(-286310244);
                            r0.p pVarJ111 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                            y1 y1Var112 = c0.u.f1329a;
                            r0.p pVarG1111112 = p3.p.g(androidx.compose.foundation.a.b(pVarJ111, ((c0.s) vVar.j(y1Var112)).f1246c, y0.l0.f6642a), f5);
                            n1.f0 f0VarE111 = r.o.e(r0.b.f5657h, false);
                            iHashCode2 = Long.hashCode(vVar.S);
                            w1 w1VarL1111112 = vVar.l();
                            r0.p pVarC1111112 = r0.a.c(vVar, pVarG1111112);
                            vVar.Y();
                            float f11118 = f5;
                            if (vVar.R) {
                                vVar.k(aVar5);
                            } else {
                                vVar.i0();
                            }
                            f0.l.x(hVar, vVar, f0VarE111);
                            f0.l.x(hVar2, vVar, w1VarL1111112);
                            if (vVar.R) {
                                b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                            } else {
                                b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                            }
                            f0.l.x(hVar4, vVar, pVarC1111112);
                            long j115 = ((c0.s) vVar.j(y1Var112)).f1247d;
                            hVar5 = hVar2;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            f6 = f11118;
                            c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j115, vVar, ((i18 >> 6) & 14) | 432, 0);
                            z7 = true;
                            vVar.p(true);
                            i19 = 0;
                            vVar.p(false);
                        } else {
                            f6 = f5;
                            hVar5 = hVar2;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            z7 = true;
                            i19 = 0;
                            vVar.V(-286291965);
                            r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                            vVar.p(false);
                        }
                        if (1.0f > 0.0d) {
                            throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                        }
                        r0.p pVarG1111113 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                        r.v vVarA111 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                        iHashCode3 = Long.hashCode(vVar.S);
                        w1 w1VarL1111113 = vVar.l();
                        r0.p pVarC1111113 = r0.a.c(vVar, pVarG1111113);
                        vVar.Y();
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        f0.l.x(hVar, vVar, vVarA111);
                        f0.l.x(hVar5, vVar, w1VarL1111113);
                        if (vVar.R) {
                            b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                        } else {
                            b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                        }
                        f0.l.x(hVar4, vVar, pVarC1111113);
                        y1Var = t4.f1328a;
                        eVar8 = eVar4;
                        a4.a aVar116 = aVar4;
                        z8 = i19;
                        boolean z119 = z7;
                        n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                        if (str5 != null) {
                            vVar.V(1662633282);
                            str6 = str5;
                            n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                            vVar.p(z8);
                        } else {
                            str6 = str5;
                            vVar.V(1662835526);
                            vVar.p(z8);
                        }
                        vVar.p(z119);
                        if (eVar8 == null) {
                            vVar.V(-284517805);
                        } else {
                            vVar.V(-286272658);
                            eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                        }
                        vVar.p(z8);
                        vVar.p(z119);
                        eVar5 = eVar7;
                        eVar6 = eVar8;
                        aVar3 = aVar116;
                        str4 = str6;
                        z5 = z6;
                    } else {
                        vVar.Q();
                        z5 = z3;
                        str4 = str3;
                        eVar5 = eVar3;
                        eVar6 = eVar4;
                        aVar3 = aVar2;
                    }
                    c0Var2 = c0Var;
                    b2VarR = vVar.r();
                    if (b2VarR != null) {
                        b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                            @Override // a4.e
                            public final Object invoke(Object obj, Object obj2) {
                                int iIntValue = ((Integer) obj2).intValue();
                                return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                            }
                        };
                    }
                }
                i7 |= 196608;
                aVar2 = aVar;
                i16 = i6 & 64;
                if (i16 != 0) {
                    i7 |= 1572864;
                    z3 = z2;
                } else {
                    z3 = z2;
                    if ((i5 & 1572864) == 0) {
                        if (vVar.g(z3)) {
                            i17 = 1048576;
                        } else {
                            i17 = 524288;
                        }
                        i7 |= i17;
                    }
                }
                i18 = i7;
                if ((i18 & 599187) != 599186) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (vVar.N(i18 & 1, z4)) {
                    if (i20 != 0) {
                        str5 = null;
                    } else {
                        str5 = str3;
                    }
                    if (i8 != 0) {
                        eVar3 = null;
                    }
                    if (i10 != 0) {
                        c0Var = null;
                    } else {
                        c0Var = c0Var;
                    }
                    if (i12 != 0) {
                        eVar4 = null;
                    }
                    if (i14 != 0) {
                        aVar4 = null;
                    } else {
                        aVar4 = aVar2;
                    }
                    if (i16 != 0) {
                        z6 = true;
                    } else {
                        z6 = z3;
                    }
                    r0.p pVarE11114 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                    mVar = r0.m.f5680a;
                    if (z6) {
                        pVarE = mVar;
                    } else {
                        pVarE = mVar;
                    }
                    r0.p pVarE11115 = pVarE11114.e(pVarE);
                    float f11119 = 16;
                    r0.p pVarG1111114 = androidx.compose.foundation.layout.a.g(pVarE11115, f11119, 12);
                    r0.g gVar112 = r0.b.f5663n;
                    r.b bVar112 = r.j.f5571a;
                    r.s0 s0VarA112 = r.q0.a(new r.f(f11119), gVar112, vVar, 54);
                    iHashCode = Long.hashCode(vVar.S);
                    w1 w1VarL1111114 = vVar.l();
                    r0.p pVarC1111114 = r0.a.c(vVar, pVarG1111114);
                    p1.j.f4892c.getClass();
                    aVar5 = p1.i.f4867b;
                    vVar.Y();
                    if (vVar.R) {
                        vVar.k(aVar5);
                    } else {
                        vVar.i0();
                    }
                    hVar = p1.i.f4870e;
                    f0.l.x(hVar, vVar, s0VarA112);
                    hVar2 = p1.i.f4869d;
                    f0.l.x(hVar2, vVar, w1VarL1111114);
                    hVar3 = p1.i.f4871f;
                    if (vVar.R) {
                        b.b.i(iHashCode, vVar, iHashCode, hVar3);
                    } else {
                        b.b.i(iHashCode, vVar, iHashCode, hVar3);
                    }
                    hVar4 = p1.i.f4868c;
                    f0.l.x(hVar4, vVar, pVarC1111114);
                    if (z6) {
                        f5 = 1.0f;
                    } else {
                        f5 = 0.38f;
                    }
                    if (c0Var != null) {
                        vVar.V(-285980136);
                        d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                        vVar.p(false);
                        f6 = f5;
                        hVar5 = hVar2;
                        i19 = 0;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        z7 = true;
                    } else if (eVar3 != null) {
                        vVar.V(-286310244);
                        r0.p pVarJ112 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                        y1 y1Var113 = c0.u.f1329a;
                        r0.p pVarG1111115 = p3.p.g(androidx.compose.foundation.a.b(pVarJ112, ((c0.s) vVar.j(y1Var113)).f1246c, y0.l0.f6642a), f5);
                        n1.f0 f0VarE112 = r.o.e(r0.b.f5657h, false);
                        iHashCode2 = Long.hashCode(vVar.S);
                        w1 w1VarL1111115 = vVar.l();
                        r0.p pVarC1111115 = r0.a.c(vVar, pVarG1111115);
                        vVar.Y();
                        float f111110 = f5;
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        f0.l.x(hVar, vVar, f0VarE112);
                        f0.l.x(hVar2, vVar, w1VarL1111115);
                        if (vVar.R) {
                            b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                        } else {
                            b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                        }
                        f0.l.x(hVar4, vVar, pVarC1111115);
                        long j116 = ((c0.s) vVar.j(y1Var113)).f1247d;
                        hVar5 = hVar2;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        f6 = f111110;
                        c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j116, vVar, ((i18 >> 6) & 14) | 432, 0);
                        z7 = true;
                        vVar.p(true);
                        i19 = 0;
                        vVar.p(false);
                    } else {
                        f6 = f5;
                        hVar5 = hVar2;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        z7 = true;
                        i19 = 0;
                        vVar.V(-286291965);
                        r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                        vVar.p(false);
                    }
                    if (1.0f > 0.0d) {
                        throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                    }
                    r0.p pVarG1111116 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                    r.v vVarA112 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                    iHashCode3 = Long.hashCode(vVar.S);
                    w1 w1VarL1111116 = vVar.l();
                    r0.p pVarC1111116 = r0.a.c(vVar, pVarG1111116);
                    vVar.Y();
                    if (vVar.R) {
                        vVar.k(aVar5);
                    } else {
                        vVar.i0();
                    }
                    f0.l.x(hVar, vVar, vVarA112);
                    f0.l.x(hVar5, vVar, w1VarL1111116);
                    if (vVar.R) {
                        b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                    } else {
                        b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                    }
                    f0.l.x(hVar4, vVar, pVarC1111116);
                    y1Var = t4.f1328a;
                    eVar8 = eVar4;
                    a4.a aVar117 = aVar4;
                    z8 = i19;
                    boolean z1110 = z7;
                    n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                    if (str5 != null) {
                        vVar.V(1662633282);
                        str6 = str5;
                        n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                        vVar.p(z8);
                    } else {
                        str6 = str5;
                        vVar.V(1662835526);
                        vVar.p(z8);
                    }
                    vVar.p(z1110);
                    if (eVar8 == null) {
                        vVar.V(-284517805);
                    } else {
                        vVar.V(-286272658);
                        eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                    }
                    vVar.p(z8);
                    vVar.p(z1110);
                    eVar5 = eVar7;
                    eVar6 = eVar8;
                    aVar3 = aVar117;
                    str4 = str6;
                    z5 = z6;
                } else {
                    vVar.Q();
                    z5 = z3;
                    str4 = str3;
                    eVar5 = eVar3;
                    eVar6 = eVar4;
                    aVar3 = aVar2;
                }
                c0Var2 = c0Var;
                b2VarR = vVar.r();
                if (b2VarR != null) {
                    b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                        @Override // a4.e
                        public final Object invoke(Object obj, Object obj2) {
                            int iIntValue = ((Integer) obj2).intValue();
                            return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                        }
                    };
                }
            }
            i7 |= 24576;
            eVar4 = eVar2;
            i14 = i6 & 32;
            if (i14 != 0) {
                if ((196608 & i5) == 0) {
                    aVar2 = aVar;
                    if (vVar.h(aVar2)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i7 |= i15;
                }
                i16 = i6 & 64;
                if (i16 != 0) {
                    i7 |= 1572864;
                    z3 = z2;
                } else {
                    z3 = z2;
                    if ((i5 & 1572864) == 0) {
                        if (vVar.g(z3)) {
                            i17 = 1048576;
                        } else {
                            i17 = 524288;
                        }
                        i7 |= i17;
                    }
                }
                i18 = i7;
                if ((i18 & 599187) != 599186) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (vVar.N(i18 & 1, z4)) {
                    if (i20 != 0) {
                        str5 = null;
                    } else {
                        str5 = str3;
                    }
                    if (i8 != 0) {
                        eVar3 = null;
                    }
                    if (i10 != 0) {
                        c0Var = null;
                    } else {
                        c0Var = c0Var;
                    }
                    if (i12 != 0) {
                        eVar4 = null;
                    }
                    if (i14 != 0) {
                        aVar4 = null;
                    } else {
                        aVar4 = aVar2;
                    }
                    if (i16 != 0) {
                        z6 = true;
                    } else {
                        z6 = z3;
                    }
                    r0.p pVarE11116 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                    mVar = r0.m.f5680a;
                    if (z6) {
                        pVarE = mVar;
                    } else {
                        pVarE = mVar;
                    }
                    r0.p pVarE11117 = pVarE11116.e(pVarE);
                    float f111111 = 16;
                    r0.p pVarG1111117 = androidx.compose.foundation.layout.a.g(pVarE11117, f111111, 12);
                    r0.g gVar113 = r0.b.f5663n;
                    r.b bVar113 = r.j.f5571a;
                    r.s0 s0VarA113 = r.q0.a(new r.f(f111111), gVar113, vVar, 54);
                    iHashCode = Long.hashCode(vVar.S);
                    w1 w1VarL1111117 = vVar.l();
                    r0.p pVarC1111117 = r0.a.c(vVar, pVarG1111117);
                    p1.j.f4892c.getClass();
                    aVar5 = p1.i.f4867b;
                    vVar.Y();
                    if (vVar.R) {
                        vVar.k(aVar5);
                    } else {
                        vVar.i0();
                    }
                    hVar = p1.i.f4870e;
                    f0.l.x(hVar, vVar, s0VarA113);
                    hVar2 = p1.i.f4869d;
                    f0.l.x(hVar2, vVar, w1VarL1111117);
                    hVar3 = p1.i.f4871f;
                    if (vVar.R) {
                        b.b.i(iHashCode, vVar, iHashCode, hVar3);
                    } else {
                        b.b.i(iHashCode, vVar, iHashCode, hVar3);
                    }
                    hVar4 = p1.i.f4868c;
                    f0.l.x(hVar4, vVar, pVarC1111117);
                    if (z6) {
                        f5 = 1.0f;
                    } else {
                        f5 = 0.38f;
                    }
                    if (c0Var != null) {
                        vVar.V(-285980136);
                        d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                        vVar.p(false);
                        f6 = f5;
                        hVar5 = hVar2;
                        i19 = 0;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        z7 = true;
                    } else if (eVar3 != null) {
                        vVar.V(-286310244);
                        r0.p pVarJ113 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                        y1 y1Var114 = c0.u.f1329a;
                        r0.p pVarG1111118 = p3.p.g(androidx.compose.foundation.a.b(pVarJ113, ((c0.s) vVar.j(y1Var114)).f1246c, y0.l0.f6642a), f5);
                        n1.f0 f0VarE113 = r.o.e(r0.b.f5657h, false);
                        iHashCode2 = Long.hashCode(vVar.S);
                        w1 w1VarL1111118 = vVar.l();
                        r0.p pVarC1111118 = r0.a.c(vVar, pVarG1111118);
                        vVar.Y();
                        float f111112 = f5;
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        f0.l.x(hVar, vVar, f0VarE113);
                        f0.l.x(hVar2, vVar, w1VarL1111118);
                        if (vVar.R) {
                            b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                        } else {
                            b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                        }
                        f0.l.x(hVar4, vVar, pVarC1111118);
                        long j117 = ((c0.s) vVar.j(y1Var114)).f1247d;
                        hVar5 = hVar2;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        f6 = f111112;
                        c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j117, vVar, ((i18 >> 6) & 14) | 432, 0);
                        z7 = true;
                        vVar.p(true);
                        i19 = 0;
                        vVar.p(false);
                    } else {
                        f6 = f5;
                        hVar5 = hVar2;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        z7 = true;
                        i19 = 0;
                        vVar.V(-286291965);
                        r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                        vVar.p(false);
                    }
                    if (1.0f > 0.0d) {
                        throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                    }
                    r0.p pVarG1111119 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                    r.v vVarA113 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                    iHashCode3 = Long.hashCode(vVar.S);
                    w1 w1VarL1111119 = vVar.l();
                    r0.p pVarC1111119 = r0.a.c(vVar, pVarG1111119);
                    vVar.Y();
                    if (vVar.R) {
                        vVar.k(aVar5);
                    } else {
                        vVar.i0();
                    }
                    f0.l.x(hVar, vVar, vVarA113);
                    f0.l.x(hVar5, vVar, w1VarL1111119);
                    if (vVar.R) {
                        b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                    } else {
                        b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                    }
                    f0.l.x(hVar4, vVar, pVarC1111119);
                    y1Var = t4.f1328a;
                    eVar8 = eVar4;
                    a4.a aVar118 = aVar4;
                    z8 = i19;
                    boolean z1111 = z7;
                    n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                    if (str5 != null) {
                        vVar.V(1662633282);
                        str6 = str5;
                        n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                        vVar.p(z8);
                    } else {
                        str6 = str5;
                        vVar.V(1662835526);
                        vVar.p(z8);
                    }
                    vVar.p(z1111);
                    if (eVar8 == null) {
                        vVar.V(-284517805);
                    } else {
                        vVar.V(-286272658);
                        eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                    }
                    vVar.p(z8);
                    vVar.p(z1111);
                    eVar5 = eVar7;
                    eVar6 = eVar8;
                    aVar3 = aVar118;
                    str4 = str6;
                    z5 = z6;
                } else {
                    vVar.Q();
                    z5 = z3;
                    str4 = str3;
                    eVar5 = eVar3;
                    eVar6 = eVar4;
                    aVar3 = aVar2;
                }
                c0Var2 = c0Var;
                b2VarR = vVar.r();
                if (b2VarR != null) {
                    b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                        @Override // a4.e
                        public final Object invoke(Object obj, Object obj2) {
                            int iIntValue = ((Integer) obj2).intValue();
                            return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                        }
                    };
                }
            }
            i7 |= 196608;
            aVar2 = aVar;
            i16 = i6 & 64;
            if (i16 != 0) {
                i7 |= 1572864;
                z3 = z2;
            } else {
                z3 = z2;
                if ((i5 & 1572864) == 0) {
                    if (vVar.g(z3)) {
                        i17 = 1048576;
                    } else {
                        i17 = 524288;
                    }
                    i7 |= i17;
                }
            }
            i18 = i7;
            if ((i18 & 599187) != 599186) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (vVar.N(i18 & 1, z4)) {
                if (i20 != 0) {
                    str5 = null;
                } else {
                    str5 = str3;
                }
                if (i8 != 0) {
                    eVar3 = null;
                }
                if (i10 != 0) {
                    c0Var = null;
                } else {
                    c0Var = c0Var;
                }
                if (i12 != 0) {
                    eVar4 = null;
                }
                if (i14 != 0) {
                    aVar4 = null;
                } else {
                    aVar4 = aVar2;
                }
                if (i16 != 0) {
                    z6 = true;
                } else {
                    z6 = z3;
                }
                r0.p pVarE11118 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                mVar = r0.m.f5680a;
                if (z6) {
                    pVarE = mVar;
                } else {
                    pVarE = mVar;
                }
                r0.p pVarE11119 = pVarE11118.e(pVarE);
                float f111113 = 16;
                r0.p pVarG11111110 = androidx.compose.foundation.layout.a.g(pVarE11119, f111113, 12);
                r0.g gVar114 = r0.b.f5663n;
                r.b bVar114 = r.j.f5571a;
                r.s0 s0VarA114 = r.q0.a(new r.f(f111113), gVar114, vVar, 54);
                iHashCode = Long.hashCode(vVar.S);
                w1 w1VarL11111110 = vVar.l();
                r0.p pVarC11111110 = r0.a.c(vVar, pVarG11111110);
                p1.j.f4892c.getClass();
                aVar5 = p1.i.f4867b;
                vVar.Y();
                if (vVar.R) {
                    vVar.k(aVar5);
                } else {
                    vVar.i0();
                }
                hVar = p1.i.f4870e;
                f0.l.x(hVar, vVar, s0VarA114);
                hVar2 = p1.i.f4869d;
                f0.l.x(hVar2, vVar, w1VarL11111110);
                hVar3 = p1.i.f4871f;
                if (vVar.R) {
                    b.b.i(iHashCode, vVar, iHashCode, hVar3);
                } else {
                    b.b.i(iHashCode, vVar, iHashCode, hVar3);
                }
                hVar4 = p1.i.f4868c;
                f0.l.x(hVar4, vVar, pVarC11111110);
                if (z6) {
                    f5 = 1.0f;
                } else {
                    f5 = 0.38f;
                }
                if (c0Var != null) {
                    vVar.V(-285980136);
                    d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                    vVar.p(false);
                    f6 = f5;
                    hVar5 = hVar2;
                    i19 = 0;
                    eVar7 = eVar3;
                    hVar6 = hVar3;
                    z7 = true;
                } else if (eVar3 != null) {
                    vVar.V(-286310244);
                    r0.p pVarJ114 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                    y1 y1Var115 = c0.u.f1329a;
                    r0.p pVarG11111111 = p3.p.g(androidx.compose.foundation.a.b(pVarJ114, ((c0.s) vVar.j(y1Var115)).f1246c, y0.l0.f6642a), f5);
                    n1.f0 f0VarE114 = r.o.e(r0.b.f5657h, false);
                    iHashCode2 = Long.hashCode(vVar.S);
                    w1 w1VarL11111111 = vVar.l();
                    r0.p pVarC11111111 = r0.a.c(vVar, pVarG11111111);
                    vVar.Y();
                    float f111114 = f5;
                    if (vVar.R) {
                        vVar.k(aVar5);
                    } else {
                        vVar.i0();
                    }
                    f0.l.x(hVar, vVar, f0VarE114);
                    f0.l.x(hVar2, vVar, w1VarL11111111);
                    if (vVar.R) {
                        b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                    } else {
                        b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                    }
                    f0.l.x(hVar4, vVar, pVarC11111111);
                    long j118 = ((c0.s) vVar.j(y1Var115)).f1247d;
                    hVar5 = hVar2;
                    eVar7 = eVar3;
                    hVar6 = hVar3;
                    f6 = f111114;
                    c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j118, vVar, ((i18 >> 6) & 14) | 432, 0);
                    z7 = true;
                    vVar.p(true);
                    i19 = 0;
                    vVar.p(false);
                } else {
                    f6 = f5;
                    hVar5 = hVar2;
                    eVar7 = eVar3;
                    hVar6 = hVar3;
                    z7 = true;
                    i19 = 0;
                    vVar.V(-286291965);
                    r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                    vVar.p(false);
                }
                if (1.0f > 0.0d) {
                    throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                }
                r0.p pVarG11111112 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                r.v vVarA114 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                iHashCode3 = Long.hashCode(vVar.S);
                w1 w1VarL11111112 = vVar.l();
                r0.p pVarC11111112 = r0.a.c(vVar, pVarG11111112);
                vVar.Y();
                if (vVar.R) {
                    vVar.k(aVar5);
                } else {
                    vVar.i0();
                }
                f0.l.x(hVar, vVar, vVarA114);
                f0.l.x(hVar5, vVar, w1VarL11111112);
                if (vVar.R) {
                    b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                } else {
                    b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                }
                f0.l.x(hVar4, vVar, pVarC11111112);
                y1Var = t4.f1328a;
                eVar8 = eVar4;
                a4.a aVar119 = aVar4;
                z8 = i19;
                boolean z1112 = z7;
                n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                if (str5 != null) {
                    vVar.V(1662633282);
                    str6 = str5;
                    n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                    vVar.p(z8);
                } else {
                    str6 = str5;
                    vVar.V(1662835526);
                    vVar.p(z8);
                }
                vVar.p(z1112);
                if (eVar8 == null) {
                    vVar.V(-284517805);
                } else {
                    vVar.V(-286272658);
                    eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                }
                vVar.p(z8);
                vVar.p(z1112);
                eVar5 = eVar7;
                eVar6 = eVar8;
                aVar3 = aVar119;
                str4 = str6;
                z5 = z6;
            } else {
                vVar.Q();
                z5 = z3;
                str4 = str3;
                eVar5 = eVar3;
                eVar6 = eVar4;
                aVar3 = aVar2;
            }
            c0Var2 = c0Var;
            b2VarR = vVar.r();
            if (b2VarR != null) {
                b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                    @Override // a4.e
                    public final Object invoke(Object obj, Object obj2) {
                        int iIntValue = ((Integer) obj2).intValue();
                        return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                    }
                };
            }
        }
        i7 |= 384;
        eVar3 = eVar;
        i10 = i6 & 8;
        if (i10 != 0) {
            if ((i5 & 3072) == 0) {
                if (vVar.h(c0Var)) {
                    i11 = 2048;
                } else {
                    i11 = 1024;
                }
                i7 |= i11;
            }
            i12 = i6 & 16;
            if (i12 != 0) {
                if ((i5 & 24576) == 0) {
                    eVar4 = eVar2;
                    if (vVar.h(eVar4)) {
                        i13 = 16384;
                    } else {
                        i13 = 8192;
                    }
                    i7 |= i13;
                }
                i14 = i6 & 32;
                if (i14 != 0) {
                    if ((196608 & i5) == 0) {
                        aVar2 = aVar;
                        if (vVar.h(aVar2)) {
                            i15 = 131072;
                        } else {
                            i15 = 65536;
                        }
                        i7 |= i15;
                    }
                    i16 = i6 & 64;
                    if (i16 != 0) {
                        i7 |= 1572864;
                        z3 = z2;
                    } else {
                        z3 = z2;
                        if ((i5 & 1572864) == 0) {
                            if (vVar.g(z3)) {
                                i17 = 1048576;
                            } else {
                                i17 = 524288;
                            }
                            i7 |= i17;
                        }
                    }
                    i18 = i7;
                    if ((i18 & 599187) != 599186) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (vVar.N(i18 & 1, z4)) {
                        if (i20 != 0) {
                            str5 = null;
                        } else {
                            str5 = str3;
                        }
                        if (i8 != 0) {
                            eVar3 = null;
                        }
                        if (i10 != 0) {
                            c0Var = null;
                        } else {
                            c0Var = c0Var;
                        }
                        if (i12 != 0) {
                            eVar4 = null;
                        }
                        if (i14 != 0) {
                            aVar4 = null;
                        } else {
                            aVar4 = aVar2;
                        }
                        if (i16 != 0) {
                            z6 = true;
                        } else {
                            z6 = z3;
                        }
                        r0.p pVarE111110 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                        mVar = r0.m.f5680a;
                        if (z6) {
                            pVarE = mVar;
                        } else {
                            pVarE = mVar;
                        }
                        r0.p pVarE111111 = pVarE111110.e(pVarE);
                        float f111115 = 16;
                        r0.p pVarG11111113 = androidx.compose.foundation.layout.a.g(pVarE111111, f111115, 12);
                        r0.g gVar115 = r0.b.f5663n;
                        r.b bVar115 = r.j.f5571a;
                        r.s0 s0VarA115 = r.q0.a(new r.f(f111115), gVar115, vVar, 54);
                        iHashCode = Long.hashCode(vVar.S);
                        w1 w1VarL11111113 = vVar.l();
                        r0.p pVarC11111113 = r0.a.c(vVar, pVarG11111113);
                        p1.j.f4892c.getClass();
                        aVar5 = p1.i.f4867b;
                        vVar.Y();
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        hVar = p1.i.f4870e;
                        f0.l.x(hVar, vVar, s0VarA115);
                        hVar2 = p1.i.f4869d;
                        f0.l.x(hVar2, vVar, w1VarL11111113);
                        hVar3 = p1.i.f4871f;
                        if (vVar.R) {
                            b.b.i(iHashCode, vVar, iHashCode, hVar3);
                        } else {
                            b.b.i(iHashCode, vVar, iHashCode, hVar3);
                        }
                        hVar4 = p1.i.f4868c;
                        f0.l.x(hVar4, vVar, pVarC11111113);
                        if (z6) {
                            f5 = 1.0f;
                        } else {
                            f5 = 0.38f;
                        }
                        if (c0Var != null) {
                            vVar.V(-285980136);
                            d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                            vVar.p(false);
                            f6 = f5;
                            hVar5 = hVar2;
                            i19 = 0;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            z7 = true;
                        } else if (eVar3 != null) {
                            vVar.V(-286310244);
                            r0.p pVarJ115 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                            y1 y1Var116 = c0.u.f1329a;
                            r0.p pVarG11111114 = p3.p.g(androidx.compose.foundation.a.b(pVarJ115, ((c0.s) vVar.j(y1Var116)).f1246c, y0.l0.f6642a), f5);
                            n1.f0 f0VarE115 = r.o.e(r0.b.f5657h, false);
                            iHashCode2 = Long.hashCode(vVar.S);
                            w1 w1VarL11111114 = vVar.l();
                            r0.p pVarC11111114 = r0.a.c(vVar, pVarG11111114);
                            vVar.Y();
                            float f111116 = f5;
                            if (vVar.R) {
                                vVar.k(aVar5);
                            } else {
                                vVar.i0();
                            }
                            f0.l.x(hVar, vVar, f0VarE115);
                            f0.l.x(hVar2, vVar, w1VarL11111114);
                            if (vVar.R) {
                                b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                            } else {
                                b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                            }
                            f0.l.x(hVar4, vVar, pVarC11111114);
                            long j119 = ((c0.s) vVar.j(y1Var116)).f1247d;
                            hVar5 = hVar2;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            f6 = f111116;
                            c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j119, vVar, ((i18 >> 6) & 14) | 432, 0);
                            z7 = true;
                            vVar.p(true);
                            i19 = 0;
                            vVar.p(false);
                        } else {
                            f6 = f5;
                            hVar5 = hVar2;
                            eVar7 = eVar3;
                            hVar6 = hVar3;
                            z7 = true;
                            i19 = 0;
                            vVar.V(-286291965);
                            r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                            vVar.p(false);
                        }
                        if (1.0f > 0.0d) {
                            throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                        }
                        r0.p pVarG11111115 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                        r.v vVarA115 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                        iHashCode3 = Long.hashCode(vVar.S);
                        w1 w1VarL11111115 = vVar.l();
                        r0.p pVarC11111115 = r0.a.c(vVar, pVarG11111115);
                        vVar.Y();
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        f0.l.x(hVar, vVar, vVarA115);
                        f0.l.x(hVar5, vVar, w1VarL11111115);
                        if (vVar.R) {
                            b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                        } else {
                            b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                        }
                        f0.l.x(hVar4, vVar, pVarC11111115);
                        y1Var = t4.f1328a;
                        eVar8 = eVar4;
                        a4.a aVar1110 = aVar4;
                        z8 = i19;
                        boolean z1113 = z7;
                        n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                        if (str5 != null) {
                            vVar.V(1662633282);
                            str6 = str5;
                            n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                            vVar.p(z8);
                        } else {
                            str6 = str5;
                            vVar.V(1662835526);
                            vVar.p(z8);
                        }
                        vVar.p(z1113);
                        if (eVar8 == null) {
                            vVar.V(-284517805);
                        } else {
                            vVar.V(-286272658);
                            eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                        }
                        vVar.p(z8);
                        vVar.p(z1113);
                        eVar5 = eVar7;
                        eVar6 = eVar8;
                        aVar3 = aVar1110;
                        str4 = str6;
                        z5 = z6;
                    } else {
                        vVar.Q();
                        z5 = z3;
                        str4 = str3;
                        eVar5 = eVar3;
                        eVar6 = eVar4;
                        aVar3 = aVar2;
                    }
                    c0Var2 = c0Var;
                    b2VarR = vVar.r();
                    if (b2VarR != null) {
                        b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                            @Override // a4.e
                            public final Object invoke(Object obj, Object obj2) {
                                int iIntValue = ((Integer) obj2).intValue();
                                return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                            }
                        };
                    }
                }
                i7 |= 196608;
                aVar2 = aVar;
                i16 = i6 & 64;
                if (i16 != 0) {
                    i7 |= 1572864;
                    z3 = z2;
                } else {
                    z3 = z2;
                    if ((i5 & 1572864) == 0) {
                        if (vVar.g(z3)) {
                            i17 = 1048576;
                        } else {
                            i17 = 524288;
                        }
                        i7 |= i17;
                    }
                }
                i18 = i7;
                if ((i18 & 599187) != 599186) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (vVar.N(i18 & 1, z4)) {
                    if (i20 != 0) {
                        str5 = null;
                    } else {
                        str5 = str3;
                    }
                    if (i8 != 0) {
                        eVar3 = null;
                    }
                    if (i10 != 0) {
                        c0Var = null;
                    } else {
                        c0Var = c0Var;
                    }
                    if (i12 != 0) {
                        eVar4 = null;
                    }
                    if (i14 != 0) {
                        aVar4 = null;
                    } else {
                        aVar4 = aVar2;
                    }
                    if (i16 != 0) {
                        z6 = true;
                    } else {
                        z6 = z3;
                    }
                    r0.p pVarE111112 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                    mVar = r0.m.f5680a;
                    if (z6) {
                        pVarE = mVar;
                    } else {
                        pVarE = mVar;
                    }
                    r0.p pVarE111113 = pVarE111112.e(pVarE);
                    float f111117 = 16;
                    r0.p pVarG11111116 = androidx.compose.foundation.layout.a.g(pVarE111113, f111117, 12);
                    r0.g gVar116 = r0.b.f5663n;
                    r.b bVar116 = r.j.f5571a;
                    r.s0 s0VarA116 = r.q0.a(new r.f(f111117), gVar116, vVar, 54);
                    iHashCode = Long.hashCode(vVar.S);
                    w1 w1VarL11111116 = vVar.l();
                    r0.p pVarC11111116 = r0.a.c(vVar, pVarG11111116);
                    p1.j.f4892c.getClass();
                    aVar5 = p1.i.f4867b;
                    vVar.Y();
                    if (vVar.R) {
                        vVar.k(aVar5);
                    } else {
                        vVar.i0();
                    }
                    hVar = p1.i.f4870e;
                    f0.l.x(hVar, vVar, s0VarA116);
                    hVar2 = p1.i.f4869d;
                    f0.l.x(hVar2, vVar, w1VarL11111116);
                    hVar3 = p1.i.f4871f;
                    if (vVar.R) {
                        b.b.i(iHashCode, vVar, iHashCode, hVar3);
                    } else {
                        b.b.i(iHashCode, vVar, iHashCode, hVar3);
                    }
                    hVar4 = p1.i.f4868c;
                    f0.l.x(hVar4, vVar, pVarC11111116);
                    if (z6) {
                        f5 = 1.0f;
                    } else {
                        f5 = 0.38f;
                    }
                    if (c0Var != null) {
                        vVar.V(-285980136);
                        d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                        vVar.p(false);
                        f6 = f5;
                        hVar5 = hVar2;
                        i19 = 0;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        z7 = true;
                    } else if (eVar3 != null) {
                        vVar.V(-286310244);
                        r0.p pVarJ116 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                        y1 y1Var117 = c0.u.f1329a;
                        r0.p pVarG11111117 = p3.p.g(androidx.compose.foundation.a.b(pVarJ116, ((c0.s) vVar.j(y1Var117)).f1246c, y0.l0.f6642a), f5);
                        n1.f0 f0VarE116 = r.o.e(r0.b.f5657h, false);
                        iHashCode2 = Long.hashCode(vVar.S);
                        w1 w1VarL11111117 = vVar.l();
                        r0.p pVarC11111117 = r0.a.c(vVar, pVarG11111117);
                        vVar.Y();
                        float f111118 = f5;
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        f0.l.x(hVar, vVar, f0VarE116);
                        f0.l.x(hVar2, vVar, w1VarL11111117);
                        if (vVar.R) {
                            b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                        } else {
                            b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                        }
                        f0.l.x(hVar4, vVar, pVarC11111117);
                        long j1110 = ((c0.s) vVar.j(y1Var117)).f1247d;
                        hVar5 = hVar2;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        f6 = f111118;
                        c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j1110, vVar, ((i18 >> 6) & 14) | 432, 0);
                        z7 = true;
                        vVar.p(true);
                        i19 = 0;
                        vVar.p(false);
                    } else {
                        f6 = f5;
                        hVar5 = hVar2;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        z7 = true;
                        i19 = 0;
                        vVar.V(-286291965);
                        r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                        vVar.p(false);
                    }
                    if (1.0f > 0.0d) {
                        throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                    }
                    r0.p pVarG11111118 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                    r.v vVarA116 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                    iHashCode3 = Long.hashCode(vVar.S);
                    w1 w1VarL11111118 = vVar.l();
                    r0.p pVarC11111118 = r0.a.c(vVar, pVarG11111118);
                    vVar.Y();
                    if (vVar.R) {
                        vVar.k(aVar5);
                    } else {
                        vVar.i0();
                    }
                    f0.l.x(hVar, vVar, vVarA116);
                    f0.l.x(hVar5, vVar, w1VarL11111118);
                    if (vVar.R) {
                        b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                    } else {
                        b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                    }
                    f0.l.x(hVar4, vVar, pVarC11111118);
                    y1Var = t4.f1328a;
                    eVar8 = eVar4;
                    a4.a aVar1111 = aVar4;
                    z8 = i19;
                    boolean z1114 = z7;
                    n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                    if (str5 != null) {
                        vVar.V(1662633282);
                        str6 = str5;
                        n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                        vVar.p(z8);
                    } else {
                        str6 = str5;
                        vVar.V(1662835526);
                        vVar.p(z8);
                    }
                    vVar.p(z1114);
                    if (eVar8 == null) {
                        vVar.V(-284517805);
                    } else {
                        vVar.V(-286272658);
                        eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                    }
                    vVar.p(z8);
                    vVar.p(z1114);
                    eVar5 = eVar7;
                    eVar6 = eVar8;
                    aVar3 = aVar1111;
                    str4 = str6;
                    z5 = z6;
                } else {
                    vVar.Q();
                    z5 = z3;
                    str4 = str3;
                    eVar5 = eVar3;
                    eVar6 = eVar4;
                    aVar3 = aVar2;
                }
                c0Var2 = c0Var;
                b2VarR = vVar.r();
                if (b2VarR != null) {
                    b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                        @Override // a4.e
                        public final Object invoke(Object obj, Object obj2) {
                            int iIntValue = ((Integer) obj2).intValue();
                            return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                        }
                    };
                }
            }
            i7 |= 24576;
            eVar4 = eVar2;
            i14 = i6 & 32;
            if (i14 != 0) {
                if ((196608 & i5) == 0) {
                    aVar2 = aVar;
                    if (vVar.h(aVar2)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i7 |= i15;
                }
                i16 = i6 & 64;
                if (i16 != 0) {
                    i7 |= 1572864;
                    z3 = z2;
                } else {
                    z3 = z2;
                    if ((i5 & 1572864) == 0) {
                        if (vVar.g(z3)) {
                            i17 = 1048576;
                        } else {
                            i17 = 524288;
                        }
                        i7 |= i17;
                    }
                }
                i18 = i7;
                if ((i18 & 599187) != 599186) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (vVar.N(i18 & 1, z4)) {
                    if (i20 != 0) {
                        str5 = null;
                    } else {
                        str5 = str3;
                    }
                    if (i8 != 0) {
                        eVar3 = null;
                    }
                    if (i10 != 0) {
                        c0Var = null;
                    } else {
                        c0Var = c0Var;
                    }
                    if (i12 != 0) {
                        eVar4 = null;
                    }
                    if (i14 != 0) {
                        aVar4 = null;
                    } else {
                        aVar4 = aVar2;
                    }
                    if (i16 != 0) {
                        z6 = true;
                    } else {
                        z6 = z3;
                    }
                    r0.p pVarE111114 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                    mVar = r0.m.f5680a;
                    if (z6) {
                        pVarE = mVar;
                    } else {
                        pVarE = mVar;
                    }
                    r0.p pVarE111115 = pVarE111114.e(pVarE);
                    float f111119 = 16;
                    r0.p pVarG11111119 = androidx.compose.foundation.layout.a.g(pVarE111115, f111119, 12);
                    r0.g gVar117 = r0.b.f5663n;
                    r.b bVar117 = r.j.f5571a;
                    r.s0 s0VarA117 = r.q0.a(new r.f(f111119), gVar117, vVar, 54);
                    iHashCode = Long.hashCode(vVar.S);
                    w1 w1VarL11111119 = vVar.l();
                    r0.p pVarC11111119 = r0.a.c(vVar, pVarG11111119);
                    p1.j.f4892c.getClass();
                    aVar5 = p1.i.f4867b;
                    vVar.Y();
                    if (vVar.R) {
                        vVar.k(aVar5);
                    } else {
                        vVar.i0();
                    }
                    hVar = p1.i.f4870e;
                    f0.l.x(hVar, vVar, s0VarA117);
                    hVar2 = p1.i.f4869d;
                    f0.l.x(hVar2, vVar, w1VarL11111119);
                    hVar3 = p1.i.f4871f;
                    if (vVar.R) {
                        b.b.i(iHashCode, vVar, iHashCode, hVar3);
                    } else {
                        b.b.i(iHashCode, vVar, iHashCode, hVar3);
                    }
                    hVar4 = p1.i.f4868c;
                    f0.l.x(hVar4, vVar, pVarC11111119);
                    if (z6) {
                        f5 = 1.0f;
                    } else {
                        f5 = 0.38f;
                    }
                    if (c0Var != null) {
                        vVar.V(-285980136);
                        d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                        vVar.p(false);
                        f6 = f5;
                        hVar5 = hVar2;
                        i19 = 0;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        z7 = true;
                    } else if (eVar3 != null) {
                        vVar.V(-286310244);
                        r0.p pVarJ117 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                        y1 y1Var118 = c0.u.f1329a;
                        r0.p pVarG111111110 = p3.p.g(androidx.compose.foundation.a.b(pVarJ117, ((c0.s) vVar.j(y1Var118)).f1246c, y0.l0.f6642a), f5);
                        n1.f0 f0VarE117 = r.o.e(r0.b.f5657h, false);
                        iHashCode2 = Long.hashCode(vVar.S);
                        w1 w1VarL111111110 = vVar.l();
                        r0.p pVarC111111110 = r0.a.c(vVar, pVarG111111110);
                        vVar.Y();
                        float f1111110 = f5;
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        f0.l.x(hVar, vVar, f0VarE117);
                        f0.l.x(hVar2, vVar, w1VarL111111110);
                        if (vVar.R) {
                            b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                        } else {
                            b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                        }
                        f0.l.x(hVar4, vVar, pVarC111111110);
                        long j1111 = ((c0.s) vVar.j(y1Var118)).f1247d;
                        hVar5 = hVar2;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        f6 = f1111110;
                        c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j1111, vVar, ((i18 >> 6) & 14) | 432, 0);
                        z7 = true;
                        vVar.p(true);
                        i19 = 0;
                        vVar.p(false);
                    } else {
                        f6 = f5;
                        hVar5 = hVar2;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        z7 = true;
                        i19 = 0;
                        vVar.V(-286291965);
                        r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                        vVar.p(false);
                    }
                    if (1.0f > 0.0d) {
                        throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                    }
                    r0.p pVarG111111111 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                    r.v vVarA117 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                    iHashCode3 = Long.hashCode(vVar.S);
                    w1 w1VarL111111111 = vVar.l();
                    r0.p pVarC111111111 = r0.a.c(vVar, pVarG111111111);
                    vVar.Y();
                    if (vVar.R) {
                        vVar.k(aVar5);
                    } else {
                        vVar.i0();
                    }
                    f0.l.x(hVar, vVar, vVarA117);
                    f0.l.x(hVar5, vVar, w1VarL111111111);
                    if (vVar.R) {
                        b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                    } else {
                        b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                    }
                    f0.l.x(hVar4, vVar, pVarC111111111);
                    y1Var = t4.f1328a;
                    eVar8 = eVar4;
                    a4.a aVar1112 = aVar4;
                    z8 = i19;
                    boolean z1115 = z7;
                    n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                    if (str5 != null) {
                        vVar.V(1662633282);
                        str6 = str5;
                        n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                        vVar.p(z8);
                    } else {
                        str6 = str5;
                        vVar.V(1662835526);
                        vVar.p(z8);
                    }
                    vVar.p(z1115);
                    if (eVar8 == null) {
                        vVar.V(-284517805);
                    } else {
                        vVar.V(-286272658);
                        eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                    }
                    vVar.p(z8);
                    vVar.p(z1115);
                    eVar5 = eVar7;
                    eVar6 = eVar8;
                    aVar3 = aVar1112;
                    str4 = str6;
                    z5 = z6;
                } else {
                    vVar.Q();
                    z5 = z3;
                    str4 = str3;
                    eVar5 = eVar3;
                    eVar6 = eVar4;
                    aVar3 = aVar2;
                }
                c0Var2 = c0Var;
                b2VarR = vVar.r();
                if (b2VarR != null) {
                    b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                        @Override // a4.e
                        public final Object invoke(Object obj, Object obj2) {
                            int iIntValue = ((Integer) obj2).intValue();
                            return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                        }
                    };
                }
            }
            i7 |= 196608;
            aVar2 = aVar;
            i16 = i6 & 64;
            if (i16 != 0) {
                i7 |= 1572864;
                z3 = z2;
            } else {
                z3 = z2;
                if ((i5 & 1572864) == 0) {
                    if (vVar.g(z3)) {
                        i17 = 1048576;
                    } else {
                        i17 = 524288;
                    }
                    i7 |= i17;
                }
            }
            i18 = i7;
            if ((i18 & 599187) != 599186) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (vVar.N(i18 & 1, z4)) {
                if (i20 != 0) {
                    str5 = null;
                } else {
                    str5 = str3;
                }
                if (i8 != 0) {
                    eVar3 = null;
                }
                if (i10 != 0) {
                    c0Var = null;
                } else {
                    c0Var = c0Var;
                }
                if (i12 != 0) {
                    eVar4 = null;
                }
                if (i14 != 0) {
                    aVar4 = null;
                } else {
                    aVar4 = aVar2;
                }
                if (i16 != 0) {
                    z6 = true;
                } else {
                    z6 = z3;
                }
                r0.p pVarE111116 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                mVar = r0.m.f5680a;
                if (z6) {
                    pVarE = mVar;
                } else {
                    pVarE = mVar;
                }
                r0.p pVarE111117 = pVarE111116.e(pVarE);
                float f1111111 = 16;
                r0.p pVarG111111112 = androidx.compose.foundation.layout.a.g(pVarE111117, f1111111, 12);
                r0.g gVar118 = r0.b.f5663n;
                r.b bVar118 = r.j.f5571a;
                r.s0 s0VarA118 = r.q0.a(new r.f(f1111111), gVar118, vVar, 54);
                iHashCode = Long.hashCode(vVar.S);
                w1 w1VarL111111112 = vVar.l();
                r0.p pVarC111111112 = r0.a.c(vVar, pVarG111111112);
                p1.j.f4892c.getClass();
                aVar5 = p1.i.f4867b;
                vVar.Y();
                if (vVar.R) {
                    vVar.k(aVar5);
                } else {
                    vVar.i0();
                }
                hVar = p1.i.f4870e;
                f0.l.x(hVar, vVar, s0VarA118);
                hVar2 = p1.i.f4869d;
                f0.l.x(hVar2, vVar, w1VarL111111112);
                hVar3 = p1.i.f4871f;
                if (vVar.R) {
                    b.b.i(iHashCode, vVar, iHashCode, hVar3);
                } else {
                    b.b.i(iHashCode, vVar, iHashCode, hVar3);
                }
                hVar4 = p1.i.f4868c;
                f0.l.x(hVar4, vVar, pVarC111111112);
                if (z6) {
                    f5 = 1.0f;
                } else {
                    f5 = 0.38f;
                }
                if (c0Var != null) {
                    vVar.V(-285980136);
                    d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                    vVar.p(false);
                    f6 = f5;
                    hVar5 = hVar2;
                    i19 = 0;
                    eVar7 = eVar3;
                    hVar6 = hVar3;
                    z7 = true;
                } else if (eVar3 != null) {
                    vVar.V(-286310244);
                    r0.p pVarJ118 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                    y1 y1Var119 = c0.u.f1329a;
                    r0.p pVarG111111113 = p3.p.g(androidx.compose.foundation.a.b(pVarJ118, ((c0.s) vVar.j(y1Var119)).f1246c, y0.l0.f6642a), f5);
                    n1.f0 f0VarE118 = r.o.e(r0.b.f5657h, false);
                    iHashCode2 = Long.hashCode(vVar.S);
                    w1 w1VarL111111113 = vVar.l();
                    r0.p pVarC111111113 = r0.a.c(vVar, pVarG111111113);
                    vVar.Y();
                    float f1111112 = f5;
                    if (vVar.R) {
                        vVar.k(aVar5);
                    } else {
                        vVar.i0();
                    }
                    f0.l.x(hVar, vVar, f0VarE118);
                    f0.l.x(hVar2, vVar, w1VarL111111113);
                    if (vVar.R) {
                        b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                    } else {
                        b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                    }
                    f0.l.x(hVar4, vVar, pVarC111111113);
                    long j1112 = ((c0.s) vVar.j(y1Var119)).f1247d;
                    hVar5 = hVar2;
                    eVar7 = eVar3;
                    hVar6 = hVar3;
                    f6 = f1111112;
                    c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j1112, vVar, ((i18 >> 6) & 14) | 432, 0);
                    z7 = true;
                    vVar.p(true);
                    i19 = 0;
                    vVar.p(false);
                } else {
                    f6 = f5;
                    hVar5 = hVar2;
                    eVar7 = eVar3;
                    hVar6 = hVar3;
                    z7 = true;
                    i19 = 0;
                    vVar.V(-286291965);
                    r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                    vVar.p(false);
                }
                if (1.0f > 0.0d) {
                    throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                }
                r0.p pVarG111111114 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                r.v vVarA118 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                iHashCode3 = Long.hashCode(vVar.S);
                w1 w1VarL111111114 = vVar.l();
                r0.p pVarC111111114 = r0.a.c(vVar, pVarG111111114);
                vVar.Y();
                if (vVar.R) {
                    vVar.k(aVar5);
                } else {
                    vVar.i0();
                }
                f0.l.x(hVar, vVar, vVarA118);
                f0.l.x(hVar5, vVar, w1VarL111111114);
                if (vVar.R) {
                    b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                } else {
                    b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                }
                f0.l.x(hVar4, vVar, pVarC111111114);
                y1Var = t4.f1328a;
                eVar8 = eVar4;
                a4.a aVar1113 = aVar4;
                z8 = i19;
                boolean z1116 = z7;
                n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                if (str5 != null) {
                    vVar.V(1662633282);
                    str6 = str5;
                    n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                    vVar.p(z8);
                } else {
                    str6 = str5;
                    vVar.V(1662835526);
                    vVar.p(z8);
                }
                vVar.p(z1116);
                if (eVar8 == null) {
                    vVar.V(-284517805);
                } else {
                    vVar.V(-286272658);
                    eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                }
                vVar.p(z8);
                vVar.p(z1116);
                eVar5 = eVar7;
                eVar6 = eVar8;
                aVar3 = aVar1113;
                str4 = str6;
                z5 = z6;
            } else {
                vVar.Q();
                z5 = z3;
                str4 = str3;
                eVar5 = eVar3;
                eVar6 = eVar4;
                aVar3 = aVar2;
            }
            c0Var2 = c0Var;
            b2VarR = vVar.r();
            if (b2VarR != null) {
                b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                    @Override // a4.e
                    public final Object invoke(Object obj, Object obj2) {
                        int iIntValue = ((Integer) obj2).intValue();
                        return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                    }
                };
            }
        }
        i7 |= 3072;
        i12 = i6 & 16;
        if (i12 != 0) {
            if ((i5 & 24576) == 0) {
                eVar4 = eVar2;
                if (vVar.h(eVar4)) {
                    i13 = 16384;
                } else {
                    i13 = 8192;
                }
                i7 |= i13;
            }
            i14 = i6 & 32;
            if (i14 != 0) {
                if ((196608 & i5) == 0) {
                    aVar2 = aVar;
                    if (vVar.h(aVar2)) {
                        i15 = 131072;
                    } else {
                        i15 = 65536;
                    }
                    i7 |= i15;
                }
                i16 = i6 & 64;
                if (i16 != 0) {
                    i7 |= 1572864;
                    z3 = z2;
                } else {
                    z3 = z2;
                    if ((i5 & 1572864) == 0) {
                        if (vVar.g(z3)) {
                            i17 = 1048576;
                        } else {
                            i17 = 524288;
                        }
                        i7 |= i17;
                    }
                }
                i18 = i7;
                if ((i18 & 599187) != 599186) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (vVar.N(i18 & 1, z4)) {
                    if (i20 != 0) {
                        str5 = null;
                    } else {
                        str5 = str3;
                    }
                    if (i8 != 0) {
                        eVar3 = null;
                    }
                    if (i10 != 0) {
                        c0Var = null;
                    } else {
                        c0Var = c0Var;
                    }
                    if (i12 != 0) {
                        eVar4 = null;
                    }
                    if (i14 != 0) {
                        aVar4 = null;
                    } else {
                        aVar4 = aVar2;
                    }
                    if (i16 != 0) {
                        z6 = true;
                    } else {
                        z6 = z3;
                    }
                    r0.p pVarE111118 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                    mVar = r0.m.f5680a;
                    if (z6) {
                        pVarE = mVar;
                    } else {
                        pVarE = mVar;
                    }
                    r0.p pVarE111119 = pVarE111118.e(pVarE);
                    float f1111113 = 16;
                    r0.p pVarG111111115 = androidx.compose.foundation.layout.a.g(pVarE111119, f1111113, 12);
                    r0.g gVar119 = r0.b.f5663n;
                    r.b bVar119 = r.j.f5571a;
                    r.s0 s0VarA119 = r.q0.a(new r.f(f1111113), gVar119, vVar, 54);
                    iHashCode = Long.hashCode(vVar.S);
                    w1 w1VarL111111115 = vVar.l();
                    r0.p pVarC111111115 = r0.a.c(vVar, pVarG111111115);
                    p1.j.f4892c.getClass();
                    aVar5 = p1.i.f4867b;
                    vVar.Y();
                    if (vVar.R) {
                        vVar.k(aVar5);
                    } else {
                        vVar.i0();
                    }
                    hVar = p1.i.f4870e;
                    f0.l.x(hVar, vVar, s0VarA119);
                    hVar2 = p1.i.f4869d;
                    f0.l.x(hVar2, vVar, w1VarL111111115);
                    hVar3 = p1.i.f4871f;
                    if (vVar.R) {
                        b.b.i(iHashCode, vVar, iHashCode, hVar3);
                    } else {
                        b.b.i(iHashCode, vVar, iHashCode, hVar3);
                    }
                    hVar4 = p1.i.f4868c;
                    f0.l.x(hVar4, vVar, pVarC111111115);
                    if (z6) {
                        f5 = 1.0f;
                    } else {
                        f5 = 0.38f;
                    }
                    if (c0Var != null) {
                        vVar.V(-285980136);
                        d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                        vVar.p(false);
                        f6 = f5;
                        hVar5 = hVar2;
                        i19 = 0;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        z7 = true;
                    } else if (eVar3 != null) {
                        vVar.V(-286310244);
                        r0.p pVarJ119 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                        y1 y1Var1110 = c0.u.f1329a;
                        r0.p pVarG111111116 = p3.p.g(androidx.compose.foundation.a.b(pVarJ119, ((c0.s) vVar.j(y1Var1110)).f1246c, y0.l0.f6642a), f5);
                        n1.f0 f0VarE119 = r.o.e(r0.b.f5657h, false);
                        iHashCode2 = Long.hashCode(vVar.S);
                        w1 w1VarL111111116 = vVar.l();
                        r0.p pVarC111111116 = r0.a.c(vVar, pVarG111111116);
                        vVar.Y();
                        float f1111114 = f5;
                        if (vVar.R) {
                            vVar.k(aVar5);
                        } else {
                            vVar.i0();
                        }
                        f0.l.x(hVar, vVar, f0VarE119);
                        f0.l.x(hVar2, vVar, w1VarL111111116);
                        if (vVar.R) {
                            b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                        } else {
                            b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                        }
                        f0.l.x(hVar4, vVar, pVarC111111116);
                        long j1113 = ((c0.s) vVar.j(y1Var1110)).f1247d;
                        hVar5 = hVar2;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        f6 = f1111114;
                        c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j1113, vVar, ((i18 >> 6) & 14) | 432, 0);
                        z7 = true;
                        vVar.p(true);
                        i19 = 0;
                        vVar.p(false);
                    } else {
                        f6 = f5;
                        hVar5 = hVar2;
                        eVar7 = eVar3;
                        hVar6 = hVar3;
                        z7 = true;
                        i19 = 0;
                        vVar.V(-286291965);
                        r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                        vVar.p(false);
                    }
                    if (1.0f > 0.0d) {
                        throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                    }
                    r0.p pVarG111111117 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                    r.v vVarA119 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                    iHashCode3 = Long.hashCode(vVar.S);
                    w1 w1VarL111111117 = vVar.l();
                    r0.p pVarC111111117 = r0.a.c(vVar, pVarG111111117);
                    vVar.Y();
                    if (vVar.R) {
                        vVar.k(aVar5);
                    } else {
                        vVar.i0();
                    }
                    f0.l.x(hVar, vVar, vVarA119);
                    f0.l.x(hVar5, vVar, w1VarL111111117);
                    if (vVar.R) {
                        b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                    } else {
                        b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                    }
                    f0.l.x(hVar4, vVar, pVarC111111117);
                    y1Var = t4.f1328a;
                    eVar8 = eVar4;
                    a4.a aVar1114 = aVar4;
                    z8 = i19;
                    boolean z1117 = z7;
                    n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                    if (str5 != null) {
                        vVar.V(1662633282);
                        str6 = str5;
                        n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                        vVar.p(z8);
                    } else {
                        str6 = str5;
                        vVar.V(1662835526);
                        vVar.p(z8);
                    }
                    vVar.p(z1117);
                    if (eVar8 == null) {
                        vVar.V(-284517805);
                    } else {
                        vVar.V(-286272658);
                        eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                    }
                    vVar.p(z8);
                    vVar.p(z1117);
                    eVar5 = eVar7;
                    eVar6 = eVar8;
                    aVar3 = aVar1114;
                    str4 = str6;
                    z5 = z6;
                } else {
                    vVar.Q();
                    z5 = z3;
                    str4 = str3;
                    eVar5 = eVar3;
                    eVar6 = eVar4;
                    aVar3 = aVar2;
                }
                c0Var2 = c0Var;
                b2VarR = vVar.r();
                if (b2VarR != null) {
                    b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                        @Override // a4.e
                        public final Object invoke(Object obj, Object obj2) {
                            int iIntValue = ((Integer) obj2).intValue();
                            return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                        }
                    };
                }
            }
            i7 |= 196608;
            aVar2 = aVar;
            i16 = i6 & 64;
            if (i16 != 0) {
                i7 |= 1572864;
                z3 = z2;
            } else {
                z3 = z2;
                if ((i5 & 1572864) == 0) {
                    if (vVar.g(z3)) {
                        i17 = 1048576;
                    } else {
                        i17 = 524288;
                    }
                    i7 |= i17;
                }
            }
            i18 = i7;
            if ((i18 & 599187) != 599186) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (vVar.N(i18 & 1, z4)) {
                if (i20 != 0) {
                    str5 = null;
                } else {
                    str5 = str3;
                }
                if (i8 != 0) {
                    eVar3 = null;
                }
                if (i10 != 0) {
                    c0Var = null;
                } else {
                    c0Var = c0Var;
                }
                if (i12 != 0) {
                    eVar4 = null;
                }
                if (i14 != 0) {
                    aVar4 = null;
                } else {
                    aVar4 = aVar2;
                }
                if (i16 != 0) {
                    z6 = true;
                } else {
                    z6 = z3;
                }
                r0.p pVarE1111110 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                mVar = r0.m.f5680a;
                if (z6) {
                    pVarE = mVar;
                } else {
                    pVarE = mVar;
                }
                r0.p pVarE1111111 = pVarE1111110.e(pVarE);
                float f1111115 = 16;
                r0.p pVarG111111118 = androidx.compose.foundation.layout.a.g(pVarE1111111, f1111115, 12);
                r0.g gVar1110 = r0.b.f5663n;
                r.b bVar1110 = r.j.f5571a;
                r.s0 s0VarA1110 = r.q0.a(new r.f(f1111115), gVar1110, vVar, 54);
                iHashCode = Long.hashCode(vVar.S);
                w1 w1VarL111111118 = vVar.l();
                r0.p pVarC111111118 = r0.a.c(vVar, pVarG111111118);
                p1.j.f4892c.getClass();
                aVar5 = p1.i.f4867b;
                vVar.Y();
                if (vVar.R) {
                    vVar.k(aVar5);
                } else {
                    vVar.i0();
                }
                hVar = p1.i.f4870e;
                f0.l.x(hVar, vVar, s0VarA1110);
                hVar2 = p1.i.f4869d;
                f0.l.x(hVar2, vVar, w1VarL111111118);
                hVar3 = p1.i.f4871f;
                if (vVar.R) {
                    b.b.i(iHashCode, vVar, iHashCode, hVar3);
                } else {
                    b.b.i(iHashCode, vVar, iHashCode, hVar3);
                }
                hVar4 = p1.i.f4868c;
                f0.l.x(hVar4, vVar, pVarC111111118);
                if (z6) {
                    f5 = 1.0f;
                } else {
                    f5 = 0.38f;
                }
                if (c0Var != null) {
                    vVar.V(-285980136);
                    d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                    vVar.p(false);
                    f6 = f5;
                    hVar5 = hVar2;
                    i19 = 0;
                    eVar7 = eVar3;
                    hVar6 = hVar3;
                    z7 = true;
                } else if (eVar3 != null) {
                    vVar.V(-286310244);
                    r0.p pVarJ1110 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                    y1 y1Var1111 = c0.u.f1329a;
                    r0.p pVarG111111119 = p3.p.g(androidx.compose.foundation.a.b(pVarJ1110, ((c0.s) vVar.j(y1Var1111)).f1246c, y0.l0.f6642a), f5);
                    n1.f0 f0VarE1110 = r.o.e(r0.b.f5657h, false);
                    iHashCode2 = Long.hashCode(vVar.S);
                    w1 w1VarL111111119 = vVar.l();
                    r0.p pVarC111111119 = r0.a.c(vVar, pVarG111111119);
                    vVar.Y();
                    float f1111116 = f5;
                    if (vVar.R) {
                        vVar.k(aVar5);
                    } else {
                        vVar.i0();
                    }
                    f0.l.x(hVar, vVar, f0VarE1110);
                    f0.l.x(hVar2, vVar, w1VarL111111119);
                    if (vVar.R) {
                        b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                    } else {
                        b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                    }
                    f0.l.x(hVar4, vVar, pVarC111111119);
                    long j1114 = ((c0.s) vVar.j(y1Var1111)).f1247d;
                    hVar5 = hVar2;
                    eVar7 = eVar3;
                    hVar6 = hVar3;
                    f6 = f1111116;
                    c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j1114, vVar, ((i18 >> 6) & 14) | 432, 0);
                    z7 = true;
                    vVar.p(true);
                    i19 = 0;
                    vVar.p(false);
                } else {
                    f6 = f5;
                    hVar5 = hVar2;
                    eVar7 = eVar3;
                    hVar6 = hVar3;
                    z7 = true;
                    i19 = 0;
                    vVar.V(-286291965);
                    r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                    vVar.p(false);
                }
                if (1.0f > 0.0d) {
                    throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                }
                r0.p pVarG1111111110 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                r.v vVarA1110 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                iHashCode3 = Long.hashCode(vVar.S);
                w1 w1VarL1111111110 = vVar.l();
                r0.p pVarC1111111110 = r0.a.c(vVar, pVarG1111111110);
                vVar.Y();
                if (vVar.R) {
                    vVar.k(aVar5);
                } else {
                    vVar.i0();
                }
                f0.l.x(hVar, vVar, vVarA1110);
                f0.l.x(hVar5, vVar, w1VarL1111111110);
                if (vVar.R) {
                    b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                } else {
                    b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                }
                f0.l.x(hVar4, vVar, pVarC1111111110);
                y1Var = t4.f1328a;
                eVar8 = eVar4;
                a4.a aVar1115 = aVar4;
                z8 = i19;
                boolean z1118 = z7;
                n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                if (str5 != null) {
                    vVar.V(1662633282);
                    str6 = str5;
                    n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                    vVar.p(z8);
                } else {
                    str6 = str5;
                    vVar.V(1662835526);
                    vVar.p(z8);
                }
                vVar.p(z1118);
                if (eVar8 == null) {
                    vVar.V(-284517805);
                } else {
                    vVar.V(-286272658);
                    eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                }
                vVar.p(z8);
                vVar.p(z1118);
                eVar5 = eVar7;
                eVar6 = eVar8;
                aVar3 = aVar1115;
                str4 = str6;
                z5 = z6;
            } else {
                vVar.Q();
                z5 = z3;
                str4 = str3;
                eVar5 = eVar3;
                eVar6 = eVar4;
                aVar3 = aVar2;
            }
            c0Var2 = c0Var;
            b2VarR = vVar.r();
            if (b2VarR != null) {
                b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                    @Override // a4.e
                    public final Object invoke(Object obj, Object obj2) {
                        int iIntValue = ((Integer) obj2).intValue();
                        return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                    }
                };
            }
        }
        i7 |= 24576;
        eVar4 = eVar2;
        i14 = i6 & 32;
        if (i14 != 0) {
            if ((196608 & i5) == 0) {
                aVar2 = aVar;
                if (vVar.h(aVar2)) {
                    i15 = 131072;
                } else {
                    i15 = 65536;
                }
                i7 |= i15;
            }
            i16 = i6 & 64;
            if (i16 != 0) {
                i7 |= 1572864;
                z3 = z2;
            } else {
                z3 = z2;
                if ((i5 & 1572864) == 0) {
                    if (vVar.g(z3)) {
                        i17 = 1048576;
                    } else {
                        i17 = 524288;
                    }
                    i7 |= i17;
                }
            }
            i18 = i7;
            if ((i18 & 599187) != 599186) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (vVar.N(i18 & 1, z4)) {
                if (i20 != 0) {
                    str5 = null;
                } else {
                    str5 = str3;
                }
                if (i8 != 0) {
                    eVar3 = null;
                }
                if (i10 != 0) {
                    c0Var = null;
                } else {
                    c0Var = c0Var;
                }
                if (i12 != 0) {
                    eVar4 = null;
                }
                if (i14 != 0) {
                    aVar4 = null;
                } else {
                    aVar4 = aVar2;
                }
                if (i16 != 0) {
                    z6 = true;
                } else {
                    z6 = z3;
                }
                r0.p pVarE1111112 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
                mVar = r0.m.f5680a;
                if (z6) {
                    pVarE = mVar;
                } else {
                    pVarE = mVar;
                }
                r0.p pVarE1111113 = pVarE1111112.e(pVarE);
                float f1111117 = 16;
                r0.p pVarG1111111111 = androidx.compose.foundation.layout.a.g(pVarE1111113, f1111117, 12);
                r0.g gVar1111 = r0.b.f5663n;
                r.b bVar1111 = r.j.f5571a;
                r.s0 s0VarA1111 = r.q0.a(new r.f(f1111117), gVar1111, vVar, 54);
                iHashCode = Long.hashCode(vVar.S);
                w1 w1VarL1111111111 = vVar.l();
                r0.p pVarC1111111111 = r0.a.c(vVar, pVarG1111111111);
                p1.j.f4892c.getClass();
                aVar5 = p1.i.f4867b;
                vVar.Y();
                if (vVar.R) {
                    vVar.k(aVar5);
                } else {
                    vVar.i0();
                }
                hVar = p1.i.f4870e;
                f0.l.x(hVar, vVar, s0VarA1111);
                hVar2 = p1.i.f4869d;
                f0.l.x(hVar2, vVar, w1VarL1111111111);
                hVar3 = p1.i.f4871f;
                if (vVar.R) {
                    b.b.i(iHashCode, vVar, iHashCode, hVar3);
                } else {
                    b.b.i(iHashCode, vVar, iHashCode, hVar3);
                }
                hVar4 = p1.i.f4868c;
                f0.l.x(hVar4, vVar, pVarC1111111111);
                if (z6) {
                    f5 = 1.0f;
                } else {
                    f5 = 0.38f;
                }
                if (c0Var != null) {
                    vVar.V(-285980136);
                    d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                    vVar.p(false);
                    f6 = f5;
                    hVar5 = hVar2;
                    i19 = 0;
                    eVar7 = eVar3;
                    hVar6 = hVar3;
                    z7 = true;
                } else if (eVar3 != null) {
                    vVar.V(-286310244);
                    r0.p pVarJ1111 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                    y1 y1Var1112 = c0.u.f1329a;
                    r0.p pVarG1111111112 = p3.p.g(androidx.compose.foundation.a.b(pVarJ1111, ((c0.s) vVar.j(y1Var1112)).f1246c, y0.l0.f6642a), f5);
                    n1.f0 f0VarE1111 = r.o.e(r0.b.f5657h, false);
                    iHashCode2 = Long.hashCode(vVar.S);
                    w1 w1VarL1111111112 = vVar.l();
                    r0.p pVarC1111111112 = r0.a.c(vVar, pVarG1111111112);
                    vVar.Y();
                    float f1111118 = f5;
                    if (vVar.R) {
                        vVar.k(aVar5);
                    } else {
                        vVar.i0();
                    }
                    f0.l.x(hVar, vVar, f0VarE1111);
                    f0.l.x(hVar2, vVar, w1VarL1111111112);
                    if (vVar.R) {
                        b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                    } else {
                        b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                    }
                    f0.l.x(hVar4, vVar, pVarC1111111112);
                    long j1115 = ((c0.s) vVar.j(y1Var1112)).f1247d;
                    hVar5 = hVar2;
                    eVar7 = eVar3;
                    hVar6 = hVar3;
                    f6 = f1111118;
                    c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j1115, vVar, ((i18 >> 6) & 14) | 432, 0);
                    z7 = true;
                    vVar.p(true);
                    i19 = 0;
                    vVar.p(false);
                } else {
                    f6 = f5;
                    hVar5 = hVar2;
                    eVar7 = eVar3;
                    hVar6 = hVar3;
                    z7 = true;
                    i19 = 0;
                    vVar.V(-286291965);
                    r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                    vVar.p(false);
                }
                if (1.0f > 0.0d) {
                    throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
                }
                r0.p pVarG1111111113 = p3.p.g(new LayoutWeightElement(1.0f), f6);
                r.v vVarA1111 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
                iHashCode3 = Long.hashCode(vVar.S);
                w1 w1VarL1111111113 = vVar.l();
                r0.p pVarC1111111113 = r0.a.c(vVar, pVarG1111111113);
                vVar.Y();
                if (vVar.R) {
                    vVar.k(aVar5);
                } else {
                    vVar.i0();
                }
                f0.l.x(hVar, vVar, vVarA1111);
                f0.l.x(hVar5, vVar, w1VarL1111111113);
                if (vVar.R) {
                    b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                } else {
                    b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
                }
                f0.l.x(hVar4, vVar, pVarC1111111113);
                y1Var = t4.f1328a;
                eVar8 = eVar4;
                a4.a aVar1116 = aVar4;
                z8 = i19;
                boolean z1119 = z7;
                n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
                if (str5 != null) {
                    vVar.V(1662633282);
                    str6 = str5;
                    n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                    vVar.p(z8);
                } else {
                    str6 = str5;
                    vVar.V(1662835526);
                    vVar.p(z8);
                }
                vVar.p(z1119);
                if (eVar8 == null) {
                    vVar.V(-284517805);
                } else {
                    vVar.V(-286272658);
                    eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
                }
                vVar.p(z8);
                vVar.p(z1119);
                eVar5 = eVar7;
                eVar6 = eVar8;
                aVar3 = aVar1116;
                str4 = str6;
                z5 = z6;
            } else {
                vVar.Q();
                z5 = z3;
                str4 = str3;
                eVar5 = eVar3;
                eVar6 = eVar4;
                aVar3 = aVar2;
            }
            c0Var2 = c0Var;
            b2VarR = vVar.r();
            if (b2VarR != null) {
                b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                    @Override // a4.e
                    public final Object invoke(Object obj, Object obj2) {
                        int iIntValue = ((Integer) obj2).intValue();
                        return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                    }
                };
            }
        }
        i7 |= 196608;
        aVar2 = aVar;
        i16 = i6 & 64;
        if (i16 != 0) {
            i7 |= 1572864;
            z3 = z2;
        } else {
            z3 = z2;
            if ((i5 & 1572864) == 0) {
                if (vVar.g(z3)) {
                    i17 = 1048576;
                } else {
                    i17 = 524288;
                }
                i7 |= i17;
            }
        }
        i18 = i7;
        if ((i18 & 599187) != 599186) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (vVar.N(i18 & 1, z4)) {
            if (i20 != 0) {
                str5 = null;
            } else {
                str5 = str3;
            }
            if (i8 != 0) {
                eVar3 = null;
            }
            if (i10 != 0) {
                c0Var = null;
            } else {
                c0Var = c0Var;
            }
            if (i12 != 0) {
                eVar4 = null;
            }
            if (i14 != 0) {
                aVar4 = null;
            } else {
                aVar4 = aVar2;
            }
            if (i16 != 0) {
                z6 = true;
            } else {
                z6 = z3;
            }
            r0.p pVarE1111114 = androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.f113a, 72, Float.NaN);
            mVar = r0.m.f5680a;
            if (z6) {
                pVarE = mVar;
            } else {
                pVarE = mVar;
            }
            r0.p pVarE1111115 = pVarE1111114.e(pVarE);
            float f1111119 = 16;
            r0.p pVarG1111111114 = androidx.compose.foundation.layout.a.g(pVarE1111115, f1111119, 12);
            r0.g gVar1112 = r0.b.f5663n;
            r.b bVar1112 = r.j.f5571a;
            r.s0 s0VarA1112 = r.q0.a(new r.f(f1111119), gVar1112, vVar, 54);
            iHashCode = Long.hashCode(vVar.S);
            w1 w1VarL1111111114 = vVar.l();
            r0.p pVarC1111111114 = r0.a.c(vVar, pVarG1111111114);
            p1.j.f4892c.getClass();
            aVar5 = p1.i.f4867b;
            vVar.Y();
            if (vVar.R) {
                vVar.k(aVar5);
            } else {
                vVar.i0();
            }
            hVar = p1.i.f4870e;
            f0.l.x(hVar, vVar, s0VarA1112);
            hVar2 = p1.i.f4869d;
            f0.l.x(hVar2, vVar, w1VarL1111111114);
            hVar3 = p1.i.f4871f;
            if (vVar.R) {
                b.b.i(iHashCode, vVar, iHashCode, hVar3);
            } else {
                b.b.i(iHashCode, vVar, iHashCode, hVar3);
            }
            hVar4 = p1.i.f4868c;
            f0.l.x(hVar4, vVar, pVarC1111111114);
            if (z6) {
                f5 = 1.0f;
            } else {
                f5 = 0.38f;
            }
            if (c0Var != null) {
                vVar.V(-285980136);
                d4.a.c(c0Var, p3.p.g(androidx.compose.foundation.layout.c.h(mVar, 40), f5), vVar, ((i18 >> 9) & 14) | 24624);
                vVar.p(false);
                f6 = f5;
                hVar5 = hVar2;
                i19 = 0;
                eVar7 = eVar3;
                hVar6 = hVar3;
                z7 = true;
            } else if (eVar3 != null) {
                vVar.V(-286310244);
                r0.p pVarJ1112 = q.f.j(androidx.compose.foundation.layout.c.h(mVar, 40), u.e.f5816a);
                y1 y1Var1113 = c0.u.f1329a;
                r0.p pVarG1111111115 = p3.p.g(androidx.compose.foundation.a.b(pVarJ1112, ((c0.s) vVar.j(y1Var1113)).f1246c, y0.l0.f6642a), f5);
                n1.f0 f0VarE1112 = r.o.e(r0.b.f5657h, false);
                iHashCode2 = Long.hashCode(vVar.S);
                w1 w1VarL1111111115 = vVar.l();
                r0.p pVarC1111111115 = r0.a.c(vVar, pVarG1111111115);
                vVar.Y();
                float f11111110 = f5;
                if (vVar.R) {
                    vVar.k(aVar5);
                } else {
                    vVar.i0();
                }
                f0.l.x(hVar, vVar, f0VarE1112);
                f0.l.x(hVar2, vVar, w1VarL1111111115);
                if (vVar.R) {
                    b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                } else {
                    b.b.i(iHashCode2, vVar, iHashCode2, hVar3);
                }
                f0.l.x(hVar4, vVar, pVarC1111111115);
                long j1116 = ((c0.s) vVar.j(y1Var1113)).f1247d;
                hVar5 = hVar2;
                eVar7 = eVar3;
                hVar6 = hVar3;
                f6 = f11111110;
                c0.q0.b(eVar7, null, androidx.compose.foundation.layout.c.h(mVar, 22), j1116, vVar, ((i18 >> 6) & 14) | 432, 0);
                z7 = true;
                vVar.p(true);
                i19 = 0;
                vVar.p(false);
            } else {
                f6 = f5;
                hVar5 = hVar2;
                eVar7 = eVar3;
                hVar6 = hVar3;
                z7 = true;
                i19 = 0;
                vVar.V(-286291965);
                r.c.a(vVar, androidx.compose.foundation.layout.c.k(mVar, 40));
                vVar.p(false);
            }
            if (1.0f > 0.0d) {
                throw new IllegalArgumentException("invalid weight 1.0; must be greater than zero");
            }
            r0.p pVarG1111111116 = p3.p.g(new LayoutWeightElement(1.0f), f6);
            r.v vVarA1112 = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, i19);
            iHashCode3 = Long.hashCode(vVar.S);
            w1 w1VarL1111111116 = vVar.l();
            r0.p pVarC1111111116 = r0.a.c(vVar, pVarG1111111116);
            vVar.Y();
            if (vVar.R) {
                vVar.k(aVar5);
            } else {
                vVar.i0();
            }
            f0.l.x(hVar, vVar, vVarA1112);
            f0.l.x(hVar5, vVar, w1VarL1111111116);
            if (vVar.R) {
                b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
            } else {
                b.b.i(iHashCode3, vVar, iHashCode3, hVar6);
            }
            f0.l.x(hVar4, vVar, pVarC1111111116);
            y1Var = t4.f1328a;
            eVar8 = eVar4;
            a4.a aVar1117 = aVar4;
            z8 = i19;
            boolean z11110 = z7;
            n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(y1Var)).f1289j, vVar, i18 & 14, 3120, 55294);
            if (str5 != null) {
                vVar.V(1662633282);
                str6 = str5;
                n4.b(str6, null, ((c0.s) vVar.j(c0.u.f1329a)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(y1Var)).f1291l, vVar, (i18 >> 3) & 14, 0, 65530);
                vVar.p(z8);
            } else {
                str6 = str5;
                vVar.V(1662835526);
                vVar.p(z8);
            }
            vVar.p(z11110);
            if (eVar8 == null) {
                vVar.V(-284517805);
            } else {
                vVar.V(-286272658);
                eVar8.invoke(vVar, Integer.valueOf((i18 >> 12) & 14));
            }
            vVar.p(z8);
            vVar.p(z11110);
            eVar5 = eVar7;
            eVar6 = eVar8;
            aVar3 = aVar1117;
            str4 = str6;
            z5 = z6;
        } else {
            vVar.Q();
            z5 = z3;
            str4 = str3;
            eVar5 = eVar3;
            eVar6 = eVar4;
            aVar3 = aVar2;
        }
        c0Var2 = c0Var;
        b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.z
                @Override // a4.e
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return AssistRestoreAppKt.ListRow$lambda$1(str, str4, eVar5, c0Var2, eVar6, aVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n ListRow$lambda$1(String str, String str2, e1.e eVar, y0.c0 c0Var, a4.e eVar2, a4.a aVar, boolean z2, int i5, int i6, f0.p pVar, int i7) {
        ListRow(str, str2, eVar, c0Var, eVar2, aVar, z2, pVar, f0.l.z(i5 | 1), i6);
        return p3.n.f5026a;
    }

    /* JADX WARN: Code duplicated, block: B:23:0x003d  */
    /* JADX WARN: Code duplicated, block: B:25:0x0042  */
    /* JADX WARN: Code duplicated, block: B:27:0x0046  */
    /* JADX WARN: Code duplicated, block: B:29:0x004e  */
    /* JADX WARN: Code duplicated, block: B:30:0x0051  */
    /* JADX WARN: Code duplicated, block: B:34:0x005c  */
    /* JADX WARN: Code duplicated, block: B:35:0x005e  */
    /* JADX WARN: Code duplicated, block: B:38:0x0066  */
    /* JADX WARN: Code duplicated, block: B:40:0x0069  */
    /* JADX WARN: Code duplicated, block: B:43:0x006e  */
    /* JADX WARN: Code duplicated, block: B:44:0x0070  */
    /* JADX WARN: Code duplicated, block: B:46:0x0073  */
    /* JADX WARN: Code duplicated, block: B:48:0x0088  */
    /* JADX WARN: Code duplicated, block: B:50:0x009e  */
    /* JADX WARN: Code duplicated, block: B:51:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:54:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:56:0x0106  */
    /* JADX WARN: Code duplicated, block: B:59:0x0111  */
    /* JADX WARN: Code duplicated, block: B:61:? A[RETURN, SYNTHETIC] */
    private static final void ModuleStatusCard(final boolean z2, a4.a aVar, r0.p pVar, f0.p pVar2, final int i5, final int i6) {
        int i7;
        a4.a aVar2;
        int i8;
        r0.p pVar3;
        int i9;
        boolean z3;
        final a4.a aVar3;
        final r0.p pVar4;
        b2 b2VarR;
        r0.p pVarE;
        r0.p pVar5;
        long j5;
        final long j6;
        f0.v vVar = (f0.v) pVar2;
        vVar.X(-1572387532);
        if ((i5 & 6) == 0) {
            i7 = (vVar.g(z2) ? 4 : 2) | i5;
        } else {
            i7 = i5;
        }
        int i10 = i6 & 2;
        if (i10 == 0) {
            if ((i5 & 48) == 0) {
                aVar2 = aVar;
                i7 |= vVar.h(aVar2) ? 32 : 16;
            }
            i8 = i6 & 4;
            if (i8 != 0) {
                if ((i5 & 384) == 0) {
                    pVar3 = pVar;
                    if (vVar.f(pVar3)) {
                        i9 = 256;
                    } else {
                        i9 = 128;
                    }
                    i7 |= i9;
                }
                if ((i7 & 147) != 146) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (vVar.N(i7 & 1, z3)) {
                    if (i10 != 0) {
                        aVar2 = null;
                    }
                    pVarE = r0.m.f5680a;
                    if (i8 != 0) {
                        pVar5 = pVarE;
                    } else {
                        pVar5 = pVar3;
                    }
                    if (z2) {
                        vVar.V(-2083481068);
                        j5 = ((c0.s) vVar.j(c0.u.f1329a)).f1246c;
                        vVar.p(false);
                    } else {
                        vVar.V(-2083417642);
                        j5 = ((c0.s) vVar.j(c0.u.f1329a)).f1268y;
                        vVar.p(false);
                    }
                    long j7 = j5;
                    if (z2) {
                        vVar.V(-2083326254);
                        j6 = ((c0.s) vVar.j(c0.u.f1329a)).f1247d;
                        vVar.p(false);
                    } else {
                        vVar.V(-2083260844);
                        j6 = ((c0.s) vVar.j(c0.u.f1329a)).f1269z;
                        vVar.p(false);
                    }
                    h3 h3Var = s2.f1277a;
                    u.d dVar = ((r2) vVar.j(h3Var)).f1229e;
                    r0.p pVarJ = q.f.j(pVar5, ((r2) vVar.j(h3Var)).f1229e);
                    if (aVar2 != null) {
                        pVarE = androidx.compose.foundation.a.e(pVarE, false, null, aVar2, 7);
                    }
                    o3.a(pVarJ.e(pVarE), dVar, j7, 0L, 0.0f, 0.0f, n0.i.c(350645519, new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.l0
                        @Override // a4.e
                        public final Object invoke(Object obj, Object obj2) {
                            int iIntValue = ((Integer) obj2).intValue();
                            return AssistRestoreAppKt.ModuleStatusCard$lambda$0(z2, j6, (f0.p) obj, iIntValue);
                        }
                    }, vVar), vVar, 12582912, 120);
                    a4.a aVar4 = aVar2;
                    pVar4 = pVar5;
                    aVar3 = aVar4;
                } else {
                    vVar.Q();
                    aVar3 = aVar2;
                    pVar4 = pVar3;
                }
                b2VarR = vVar.r();
                if (b2VarR != null) {
                    b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.m0
                        @Override // a4.e
                        public final Object invoke(Object obj, Object obj2) {
                            int iIntValue = ((Integer) obj2).intValue();
                            return AssistRestoreAppKt.ModuleStatusCard$lambda$1(z2, aVar3, pVar4, i5, i6, (f0.p) obj, iIntValue);
                        }
                    };
                }
            }
            i7 |= 384;
            pVar3 = pVar;
            if ((i7 & 147) != 146) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (vVar.N(i7 & 1, z3)) {
                if (i10 != 0) {
                    aVar2 = null;
                }
                pVarE = r0.m.f5680a;
                if (i8 != 0) {
                    pVar5 = pVarE;
                } else {
                    pVar5 = pVar3;
                }
                if (z2) {
                    vVar.V(-2083481068);
                    j5 = ((c0.s) vVar.j(c0.u.f1329a)).f1246c;
                    vVar.p(false);
                } else {
                    vVar.V(-2083417642);
                    j5 = ((c0.s) vVar.j(c0.u.f1329a)).f1268y;
                    vVar.p(false);
                }
                long j8 = j5;
                if (z2) {
                    vVar.V(-2083326254);
                    j6 = ((c0.s) vVar.j(c0.u.f1329a)).f1247d;
                    vVar.p(false);
                } else {
                    vVar.V(-2083260844);
                    j6 = ((c0.s) vVar.j(c0.u.f1329a)).f1269z;
                    vVar.p(false);
                }
                h3 h3Var2 = s2.f1277a;
                u.d dVar2 = ((r2) vVar.j(h3Var2)).f1229e;
                r0.p pVarJ2 = q.f.j(pVar5, ((r2) vVar.j(h3Var2)).f1229e);
                if (aVar2 != null) {
                    pVarE = androidx.compose.foundation.a.e(pVarE, false, null, aVar2, 7);
                }
                o3.a(pVarJ2.e(pVarE), dVar2, j8, 0L, 0.0f, 0.0f, n0.i.c(350645519, new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.l0
                    @Override // a4.e
                    public final Object invoke(Object obj, Object obj2) {
                        int iIntValue = ((Integer) obj2).intValue();
                        return AssistRestoreAppKt.ModuleStatusCard$lambda$0(z2, j6, (f0.p) obj, iIntValue);
                    }
                }, vVar), vVar, 12582912, 120);
                a4.a aVar5 = aVar2;
                pVar4 = pVar5;
                aVar3 = aVar5;
            } else {
                vVar.Q();
                aVar3 = aVar2;
                pVar4 = pVar3;
            }
            b2VarR = vVar.r();
            if (b2VarR != null) {
                b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.m0
                    @Override // a4.e
                    public final Object invoke(Object obj, Object obj2) {
                        int iIntValue = ((Integer) obj2).intValue();
                        return AssistRestoreAppKt.ModuleStatusCard$lambda$1(z2, aVar3, pVar4, i5, i6, (f0.p) obj, iIntValue);
                    }
                };
            }
        }
        i7 |= 48;
        aVar2 = aVar;
        i8 = i6 & 4;
        if (i8 != 0) {
            if ((i5 & 384) == 0) {
                pVar3 = pVar;
                if (vVar.f(pVar3)) {
                    i9 = 256;
                } else {
                    i9 = 128;
                }
                i7 |= i9;
            }
            if ((i7 & 147) != 146) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (vVar.N(i7 & 1, z3)) {
                if (i10 != 0) {
                    aVar2 = null;
                }
                pVarE = r0.m.f5680a;
                if (i8 != 0) {
                    pVar5 = pVarE;
                } else {
                    pVar5 = pVar3;
                }
                if (z2) {
                    vVar.V(-2083481068);
                    j5 = ((c0.s) vVar.j(c0.u.f1329a)).f1246c;
                    vVar.p(false);
                } else {
                    vVar.V(-2083417642);
                    j5 = ((c0.s) vVar.j(c0.u.f1329a)).f1268y;
                    vVar.p(false);
                }
                long j9 = j5;
                if (z2) {
                    vVar.V(-2083326254);
                    j6 = ((c0.s) vVar.j(c0.u.f1329a)).f1247d;
                    vVar.p(false);
                } else {
                    vVar.V(-2083260844);
                    j6 = ((c0.s) vVar.j(c0.u.f1329a)).f1269z;
                    vVar.p(false);
                }
                h3 h3Var3 = s2.f1277a;
                u.d dVar3 = ((r2) vVar.j(h3Var3)).f1229e;
                r0.p pVarJ3 = q.f.j(pVar5, ((r2) vVar.j(h3Var3)).f1229e);
                if (aVar2 != null) {
                    pVarE = androidx.compose.foundation.a.e(pVarE, false, null, aVar2, 7);
                }
                o3.a(pVarJ3.e(pVarE), dVar3, j9, 0L, 0.0f, 0.0f, n0.i.c(350645519, new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.l0
                    @Override // a4.e
                    public final Object invoke(Object obj, Object obj2) {
                        int iIntValue = ((Integer) obj2).intValue();
                        return AssistRestoreAppKt.ModuleStatusCard$lambda$0(z2, j6, (f0.p) obj, iIntValue);
                    }
                }, vVar), vVar, 12582912, 120);
                a4.a aVar6 = aVar2;
                pVar4 = pVar5;
                aVar3 = aVar6;
            } else {
                vVar.Q();
                aVar3 = aVar2;
                pVar4 = pVar3;
            }
            b2VarR = vVar.r();
            if (b2VarR != null) {
                b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.m0
                    @Override // a4.e
                    public final Object invoke(Object obj, Object obj2) {
                        int iIntValue = ((Integer) obj2).intValue();
                        return AssistRestoreAppKt.ModuleStatusCard$lambda$1(z2, aVar3, pVar4, i5, i6, (f0.p) obj, iIntValue);
                    }
                };
            }
        }
        i7 |= 384;
        pVar3 = pVar;
        if ((i7 & 147) != 146) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (vVar.N(i7 & 1, z3)) {
            if (i10 != 0) {
                aVar2 = null;
            }
            pVarE = r0.m.f5680a;
            if (i8 != 0) {
                pVar5 = pVarE;
            } else {
                pVar5 = pVar3;
            }
            if (z2) {
                vVar.V(-2083481068);
                j5 = ((c0.s) vVar.j(c0.u.f1329a)).f1246c;
                vVar.p(false);
            } else {
                vVar.V(-2083417642);
                j5 = ((c0.s) vVar.j(c0.u.f1329a)).f1268y;
                vVar.p(false);
            }
            long j10 = j5;
            if (z2) {
                vVar.V(-2083326254);
                j6 = ((c0.s) vVar.j(c0.u.f1329a)).f1247d;
                vVar.p(false);
            } else {
                vVar.V(-2083260844);
                j6 = ((c0.s) vVar.j(c0.u.f1329a)).f1269z;
                vVar.p(false);
            }
            h3 h3Var4 = s2.f1277a;
            u.d dVar4 = ((r2) vVar.j(h3Var4)).f1229e;
            r0.p pVarJ4 = q.f.j(pVar5, ((r2) vVar.j(h3Var4)).f1229e);
            if (aVar2 != null) {
                pVarE = androidx.compose.foundation.a.e(pVarE, false, null, aVar2, 7);
            }
            o3.a(pVarJ4.e(pVarE), dVar4, j10, 0L, 0.0f, 0.0f, n0.i.c(350645519, new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.l0
                @Override // a4.e
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return AssistRestoreAppKt.ModuleStatusCard$lambda$0(z2, j6, (f0.p) obj, iIntValue);
                }
            }, vVar), vVar, 12582912, 120);
            a4.a aVar7 = aVar2;
            pVar4 = pVar5;
            aVar3 = aVar7;
        } else {
            vVar.Q();
            aVar3 = aVar2;
            pVar4 = pVar3;
        }
        b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.m0
                @Override // a4.e
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return AssistRestoreAppKt.ModuleStatusCard$lambda$1(z2, aVar3, pVar4, i5, i6, (f0.p) obj, iIntValue);
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n ModuleStatusCard$lambda$0(boolean z2, long j5, f0.p pVar, int i5) {
        e1.e eVar;
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 3) != 2)) {
            r0.m mVar = r0.m.f5680a;
            r0.p pVarC = androidx.compose.foundation.layout.c.c(mVar, 1.0f);
            r0.h hVar = r0.b.f5653d;
            n1.f0 f0VarE = r.o.e(hVar, false);
            int iHashCode = Long.hashCode(vVar.S);
            w1 w1VarL = vVar.l();
            r0.p pVarC2 = r0.a.c(vVar, pVarC);
            p1.j.f4892c.getClass();
            p1.n nVar = p1.i.f4867b;
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            p1.h hVar2 = p1.i.f4870e;
            f0.l.x(hVar2, vVar, f0VarE);
            p1.h hVar3 = p1.i.f4869d;
            f0.l.x(hVar3, vVar, w1VarL);
            p1.h hVar4 = p1.i.f4871f;
            if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode))) {
                b.b.i(iHashCode, vVar, iHashCode, hVar4);
            }
            p1.h hVar5 = p1.i.f4868c;
            f0.l.x(hVar5, vVar, pVarC2);
            if (z2) {
                e1.e eVarB = d4.a.f1632c;
                if (eVarB == null) {
                    e1.d dVar = new e1.d("Rounded.CheckCircle");
                    int i6 = e1.a0.f1864a;
                    y0.s0 s0Var = new y0.s0(y0.u.f6689b);
                    e1.f fVar = new e1.f();
                    fVar.h(12.0f, 2.0f);
                    fVar.b(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
                    fVar.j(4.48f, 10.0f, 10.0f, 10.0f);
                    fVar.j(10.0f, -4.48f, 10.0f, -10.0f);
                    fVar.i(17.52f, 2.0f, 12.0f, 2.0f);
                    fVar.a();
                    fVar.h(9.29f, 16.29f);
                    fVar.f(5.7f, 12.7f);
                    fVar.c(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
                    fVar.c(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
                    fVar.f(10.0f, 14.17f);
                    fVar.g(6.88f, -6.88f);
                    fVar.c(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
                    fVar.c(0.39f, 0.39f, 0.39f, 1.02f, 0.0f, 1.41f);
                    fVar.g(-7.59f, 7.59f);
                    fVar.c(-0.38f, 0.39f, -1.02f, 0.39f, -1.41f, 0.0f);
                    fVar.a();
                    e1.d.a(dVar, fVar.f1910d, s0Var);
                    eVarB = dVar.b();
                    d4.a.f1632c = eVarB;
                }
                eVar = eVarB;
                hVar3 = hVar3;
            } else {
                mVar = mVar;
                e1.e eVarB2 = h1.c.f2608d;
                if (eVarB2 == null) {
                    e1.d dVar2 = new e1.d("Rounded.Error");
                    int i7 = e1.a0.f1864a;
                    y0.s0 s0Var2 = new y0.s0(y0.u.f6689b);
                    e1.f fVar2 = new e1.f();
                    fVar2.h(12.0f, 2.0f);
                    fVar2.b(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
                    fVar2.j(4.48f, 10.0f, 10.0f, 10.0f);
                    fVar2.j(10.0f, -4.48f, 10.0f, -10.0f);
                    fVar2.i(17.52f, 2.0f, 12.0f, 2.0f);
                    fVar2.a();
                    fVar2.h(12.0f, 13.0f);
                    fVar2.c(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
                    fVar2.f(11.0f, 8.0f);
                    fVar2.c(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
                    fVar2.j(1.0f, 0.45f, 1.0f, 1.0f);
                    fVar2.l(4.0f);
                    fVar2.c(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
                    fVar2.a();
                    fVar2.h(13.0f, 17.0f);
                    fVar2.e(-2.0f);
                    fVar2.l(-2.0f);
                    fVar2.e(2.0f);
                    fVar2.l(2.0f);
                    fVar2.a();
                    e1.d.a(dVar2, fVar2.f1910d, s0Var2);
                    eVarB2 = dVar2.b();
                    h1.c.f2608d = eVarB2;
                }
                eVar = eVarB2;
            }
            long jB = y0.u.b(0.3f, j5);
            r0.h hVar6 = r0.b.f5661l;
            androidx.compose.foundation.layout.b bVar = androidx.compose.foundation.layout.b.f112a;
            float f5 = 16;
            p1.h hVar7 = hVar3;
            c0.q0.b(eVar, null, androidx.compose.foundation.layout.a.d(androidx.compose.foundation.layout.c.c(bVar.a(hVar6), 0.92f), f5, f5), jB, vVar, 48, 0);
            r0.p pVarF = androidx.compose.foundation.layout.a.f(bVar.a(hVar), 20);
            r.v vVarA = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, 0);
            int iHashCode2 = Long.hashCode(vVar.S);
            w1 w1VarL2 = vVar.l();
            r0.p pVarC3 = r0.a.c(vVar, pVarF);
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            f0.l.x(hVar2, vVar, vVarA);
            f0.l.x(hVar7, vVar, w1VarL2);
            if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode2))) {
                b.b.i(iHashCode2, vVar, iHashCode2, hVar4);
            }
            f0.l.x(hVar5, vVar, pVarC3);
            String str = z2 ? "模块已生效" : "模块未生效";
            h3 h3Var = t4.f1328a;
            n4.b(str, null, j5, 0L, 0L, 0L, 2, false, 2, 0, ((s4) vVar.j(h3Var)).f1287h, vVar, 0, 3120, 55290);
            r.c.a(vVar, androidx.compose.foundation.layout.c.d(mVar, 2));
            n4.b(z2 ? "4 个作用域已挂载" : "未连接 Xposed 服务", null, y0.u.b(0.8f, j5), 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(h3Var)).f1291l, vVar, 0, 0, 65530);
            vVar.p(true);
            vVar.p(true);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n ModuleStatusCard$lambda$1(boolean z2, a4.a aVar, r0.p pVar, int i5, int i6, f0.p pVar2, int i7) {
        ModuleStatusCard(z2, aVar, pVar, pVar2, f0.l.z(i5 | 1), i6);
        return p3.n.f5026a;
    }

    /* JADX INFO: renamed from: ReadabilityScrim-RPmYEkk, reason: not valid java name */
    private static final void m0ReadabilityScrimRPmYEkk(final r.s sVar, final long j5, f0.p pVar, final int i5) {
        f0.v vVar = (f0.v) pVar;
        vVar.X(-1565424195);
        int i6 = (i5 & 6) == 0 ? (vVar.f(sVar) ? 4 : 2) | i5 : i5;
        if ((i5 & 48) == 0) {
            i6 |= vVar.e(j5) ? 32 : 16;
        }
        if (vVar.N(i6 & 1, (i6 & 19) != 18)) {
            r0.p pVarB = ((androidx.compose.foundation.layout.b) sVar).b();
            p3.f[] fVarArr = (p3.f[]) Arrays.copyOf(new p3.f[]{new p3.f(Float.valueOf(0.0f), new y0.u(j5)), new p3.f(Float.valueOf(0.52f), new y0.u(y0.u.b(0.94f, j5))), new p3.f(Float.valueOf(1.0f), new y0.u(y0.u.b(0.3f, j5)))}, 3);
            long jB = p3.j.b(0.0f, 0.0f);
            long jB2 = p3.j.b(0.0f, Float.POSITIVE_INFINITY);
            ArrayList arrayList = new ArrayList(fVarArr.length);
            for (p3.f fVar : fVarArr) {
                arrayList.add(new y0.u(((y0.u) fVar.f5015e).f6696a));
            }
            ArrayList arrayList2 = new ArrayList(fVarArr.length);
            for (p3.f fVar2 : fVarArr) {
                arrayList2.add(Float.valueOf(((Number) fVar2.f5014d).floatValue()));
            }
            r.o.a(androidx.compose.foundation.a.a(pVarB, new y0.e0(arrayList, arrayList2, jB, jB2)), vVar, 0);
        } else {
            vVar.Q();
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.q0
                @Override // a4.e
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return AssistRestoreAppKt.ReadabilityScrim_RPmYEkk$lambda$0(sVar, j5, i5, (f0.p) obj, iIntValue);
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n ReadabilityScrim_RPmYEkk$lambda$0(r.s sVar, long j5, int i5, f0.p pVar, int i6) {
        m0ReadabilityScrimRPmYEkk(sVar, j5, pVar, f0.l.z(i5 | 1));
        return p3.n.f5026a;
    }

    private static final void RowDivider(f0.p pVar, int i5) {
        f0.v vVar = (f0.v) pVar;
        vVar.X(2086834836);
        if (vVar.N(i5 & 1, i5 != 0)) {
            h2.d(androidx.compose.foundation.layout.a.i(r0.m.f5680a, 72, 0.0f, 0.0f, 14), 0.0f, ((c0.s) vVar.j(c0.u.f1329a)).B, vVar, 6, 2);
        } else {
            vVar.Q();
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new e(i5, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n RowDivider$lambda$0(int i5, f0.p pVar, int i6) {
        RowDivider(pVar, f0.l.z(i5 | 1));
        return p3.n.f5026a;
    }

    private static final void SectionCard(r0.p pVar, final a4.f fVar, f0.p pVar2, final int i5, final int i6) {
        final r0.p pVar3;
        int i7;
        f0.v vVar = (f0.v) pVar2;
        vVar.X(-1781555023);
        int i8 = i6 & 1;
        if (i8 != 0) {
            i7 = i5 | 6;
            pVar3 = pVar;
        } else if ((i5 & 6) == 0) {
            pVar3 = pVar;
            i7 = (vVar.f(pVar3) ? 4 : 2) | i5;
        } else {
            pVar3 = pVar;
            i7 = i5;
        }
        if ((i5 & 48) == 0) {
            i7 |= vVar.h(fVar) ? 32 : 16;
        }
        if (vVar.N(i7 & 1, (i7 & 19) != 18)) {
            r0.p pVar4 = i8 != 0 ? r0.m.f5680a : pVar3;
            r0.p pVarE = pVar4.e(androidx.compose.foundation.layout.c.f113a);
            u.d dVar = ((r2) vVar.j(s2.f1277a)).f1229e;
            h3 h3Var = c0.u.f1329a;
            long j5 = ((c0.s) vVar.j(h3Var)).F;
            long jB = c0.u.b(j5, vVar);
            long j6 = y0.u.f6694g;
            long jB2 = y0.u.b(0.38f, jB);
            c0.s sVar = (c0.s) vVar.j(h3Var);
            c0.o oVar = sVar.L;
            if (oVar == null) {
                float f5 = e0.f.f1715a;
                oVar = new c0.o(c0.u.c(sVar, 39), c0.u.a(sVar, c0.u.c(sVar, 39)), y0.l0.l(y0.u.b(e0.f.f1718d, c0.u.c(sVar, e0.f.f1716b)), c0.u.c(sVar, 39)), y0.u.b(0.38f, c0.u.a(sVar, c0.u.c(sVar, 39))));
                sVar.L = oVar;
            }
            long j7 = j5 != 16 ? j5 : oVar.f1121a;
            if (jB == 16) {
                jB = oVar.f1122b;
            }
            long j8 = jB;
            if (j6 == 16) {
                j6 = oVar.f1123c;
            }
            h2.b(pVarE, dVar, new c0.o(j7, j8, j6, jB2 != 16 ? jB2 : oVar.f1124d), null, n0.i.c(-1294682625, new e0(fVar, 0), vVar), vVar, 196608);
            pVar3 = pVar4;
        } else {
            vVar.Q();
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.f0
                @Override // a4.e
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return AssistRestoreAppKt.SectionCard$lambda$1(pVar3, fVar, i5, i6, (f0.p) obj, iIntValue);
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n SectionCard$lambda$0(a4.f fVar, r.w wVar, f0.p pVar, int i5) {
        b4.l.e("$this$Card", wVar);
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 17) != 16)) {
            r.v vVarA = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, 0);
            int iHashCode = Long.hashCode(vVar.S);
            w1 w1VarL = vVar.l();
            r0.p pVarC = r0.a.c(vVar, r0.m.f5680a);
            p1.j.f4892c.getClass();
            p1.n nVar = p1.i.f4867b;
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            f0.l.x(p1.i.f4870e, vVar, vVarA);
            f0.l.x(p1.i.f4869d, vVar, w1VarL);
            p1.h hVar = p1.i.f4871f;
            if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode))) {
                b.b.i(iHashCode, vVar, iHashCode, hVar);
            }
            f0.l.x(p1.i.f4868c, vVar, pVarC);
            fVar.b(r.x.f5639a, vVar, 6);
            vVar.p(true);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n SectionCard$lambda$1(r0.p pVar, a4.f fVar, int i5, int i6, f0.p pVar2, int i7) {
        SectionCard(pVar, fVar, pVar2, f0.l.z(i5 | 1), i6);
        return p3.n.f5026a;
    }

    private static final void TargetScreen(final int i5, final a4.c cVar, final TargetChoice targetChoice, final a4.c cVar2, final AssistantSnapshot assistantSnapshot, final a4.a aVar, final a4.a aVar2, final boolean z2, final boolean z3, final boolean z4, final c3 c3Var, f0.p pVar, final int i6, final int i7) {
        int i8;
        a4.c cVar3;
        AssistantSnapshot assistantSnapshot2;
        boolean z5;
        boolean z6;
        boolean z7;
        int i9;
        f0.v vVar;
        f0.v vVar2 = (f0.v) pVar;
        vVar2.X(-621975873);
        if ((i6 & 6) == 0) {
            i8 = (vVar2.d(i5) ? 4 : 2) | i6;
        } else {
            i8 = i6;
        }
        if ((i6 & 48) == 0) {
            cVar3 = cVar;
            i8 |= vVar2.h(cVar3) ? 32 : 16;
        } else {
            cVar3 = cVar;
        }
        if ((i6 & 384) == 0) {
            i8 |= (i6 & 512) == 0 ? vVar2.f(targetChoice) : vVar2.h(targetChoice) ? 256 : 128;
        }
        if ((i6 & 3072) == 0) {
            i8 |= vVar2.h(cVar2) ? 2048 : 1024;
        }
        if ((i6 & 24576) == 0) {
            assistantSnapshot2 = assistantSnapshot;
            i8 |= vVar2.h(assistantSnapshot2) ? 16384 : 8192;
        } else {
            assistantSnapshot2 = assistantSnapshot;
        }
        if ((196608 & i6) == 0) {
            i8 |= vVar2.h(aVar) ? 131072 : 65536;
        }
        if ((1572864 & i6) == 0) {
            i8 |= vVar2.h(aVar2) ? 1048576 : 524288;
        }
        if ((12582912 & i6) == 0) {
            z5 = z2;
            i8 |= vVar2.g(z5) ? 8388608 : 4194304;
        } else {
            z5 = z2;
        }
        if ((100663296 & i6) == 0) {
            z6 = z3;
            i8 |= vVar2.g(z6) ? 67108864 : 33554432;
        } else {
            z6 = z3;
        }
        if ((805306368 & i6) == 0) {
            z7 = z4;
            i8 |= vVar2.g(z7) ? 536870912 : 268435456;
        } else {
            z7 = z4;
        }
        int i10 = i8;
        if ((i7 & 6) == 0) {
            i9 = i7 | (vVar2.f(c3Var) ? 4 : 2);
        } else {
            i9 = i7;
        }
        if (vVar2.N(i10 & 1, ((306783379 & i10) == 306783378 && (i9 & 3) == 2) ? false : true)) {
            final AssistantSnapshot assistantSnapshot3 = assistantSnapshot2;
            final boolean z8 = z7;
            final a4.c cVar4 = cVar3;
            final boolean z9 = z5;
            final boolean z10 = z6;
            vVar = vVar2;
            AppScreen("唤醒目标", c3Var, aVar, null, null, n0.i.c(-1027720173, new a4.f() { // from class: io.github.andrea_lyz.assistrestore.ui.a0
                @Override // a4.f
                public final Object b(Object obj, Object obj2, Object obj3) {
                    int iIntValue = ((Integer) obj3).intValue();
                    return AssistRestoreAppKt.TargetScreen$lambda$0(i5, z8, cVar4, assistantSnapshot3, z9, targetChoice, cVar2, z10, aVar2, (r0.p) obj, (f0.p) obj2, iIntValue);
                }
            }, vVar2), vVar, ((i9 << 3) & 112) | 196614 | ((i10 >> 9) & 896), 24);
        } else {
            vVar = vVar2;
            vVar.Q();
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.b0
                @Override // a4.e
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return AssistRestoreAppKt.TargetScreen$lambda$1(i5, cVar, targetChoice, cVar2, assistantSnapshot, aVar, aVar2, z2, z3, z4, c3Var, i6, i7, (f0.p) obj, iIntValue);
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n TargetScreen$lambda$0(int i5, boolean z2, a4.c cVar, AssistantSnapshot assistantSnapshot, boolean z3, TargetChoice targetChoice, a4.c cVar2, boolean z4, a4.a aVar, r0.p pVar, f0.p pVar2, int i6) {
        int i7;
        b4.l.e("modifier", pVar);
        if ((i6 & 6) == 0) {
            i7 = i6 | (((f0.v) pVar2).f(pVar) ? 4 : 2);
        } else {
            i7 = i6;
        }
        f0.v vVar = (f0.v) pVar2;
        if (vVar.N(i7 & 1, (i7 & 19) != 18)) {
            float f5 = 16;
            r0.p pVarH = androidx.compose.foundation.layout.a.h(pVar, f5, 0.0f, 2);
            r.v vVarA = r.t.a(r.j.f5572b, r0.b.f5665p, vVar, 0);
            int iHashCode = Long.hashCode(vVar.S);
            w1 w1VarL = vVar.l();
            r0.p pVarC = r0.a.c(vVar, pVarH);
            p1.j.f4892c.getClass();
            p1.n nVar = p1.i.f4867b;
            vVar.Y();
            if (vVar.R) {
                vVar.k(nVar);
            } else {
                vVar.i0();
            }
            f0.l.x(p1.i.f4870e, vVar, vVarA);
            f0.l.x(p1.i.f4869d, vVar, w1VarL);
            p1.h hVar = p1.i.f4871f;
            if (vVar.R || !b4.l.a(vVar.K(), Integer.valueOf(iHashCode))) {
                b.b.i(iHashCode, vVar, iHashCode, hVar);
            }
            f0.l.x(p1.i.f4868c, vVar, pVarC);
            float f6 = 8;
            r0.m mVar = r0.m.f5680a;
            r.c.a(vVar, androidx.compose.foundation.layout.c.d(mVar, f6));
            i4.a(i5, null, 0L, 0L, null, null, n0.i.c(-1715220155, new o(i5, 0, cVar), vVar), vVar, 1572864);
            r.c.a(vVar, androidx.compose.foundation.layout.c.d(mVar, f5));
            h3 h3Var = t4.f1328a;
            n4.b("选择这个入口要唤醒谁", null, 0L, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(h3Var)).f1287h, vVar, 6, 0, 65534);
            r.c.a(vVar, androidx.compose.foundation.layout.c.d(mVar, f6));
            SectionCard(null, n0.i.c(1429745796, new p(assistantSnapshot, z3, targetChoice, cVar2), vVar), vVar, 48, 1);
            r.c.a(vVar, androidx.compose.foundation.layout.c.d(mVar, 12));
            SectionCard(null, n0.i.c(-1031831635, new p(z4, aVar, targetChoice, cVar2), vVar), vVar, 48, 1);
            r.c.a(vVar, androidx.compose.foundation.layout.c.d(mVar, f5));
            y1.h0 h0Var = ((s4) vVar.j(h3Var)).f1291l;
            h3 h3Var2 = c0.u.f1329a;
            n4.b("三个入口各存一份目标；全部关掉 = 这个入口不唤醒任何助理（ColorOS 原生调用也一并关掉）。", null, ((c0.s) vVar.j(h3Var2)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, h0Var, vVar, 6, 0, 65530);
            if (z2) {
                vVar.V(106259063);
                r.c.a(vVar, androidx.compose.foundation.layout.c.d(mVar, 4));
                n4.b("底角这项改动要重启手机后桌面才会重新判断，重启前可能仍保留手势动画。", null, ((c0.s) vVar.j(h3Var2)).f1262s, 0L, 0L, 0L, 0, false, 0, 0, ((s4) vVar.j(h3Var)).f1291l, vVar, 6, 0, 65530);
                vVar.p(false);
            } else {
                vVar.V(106549285);
                vVar.p(false);
            }
            r.c.a(vVar, androidx.compose.foundation.layout.c.d(mVar, 24));
            vVar.p(true);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n TargetScreen$lambda$0$0$0(int i5, a4.c cVar, f0.p pVar, int i6) {
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i6 & 1, (i6 & 3) != 2)) {
            int i7 = 0;
            for (Object obj : ENTRY_NAMES) {
                int i8 = i7 + 1;
                if (i7 < 0) {
                    p3.j.N();
                    throw null;
                }
                String str = (String) obj;
                boolean z2 = i7 == i5;
                boolean zF = vVar.f(cVar) | vVar.d(i7);
                Object objK = vVar.K();
                if (zF || objK == f0.o.f2281a) {
                    objK = new s(i7, 2, cVar);
                    vVar.f0(objK);
                }
                a4.b(z2, (a4.a) objK, null, false, n0.i.c(-1333612423, new t(str, 2), vVar), 0L, 0L, vVar, 24576);
                i7 = i8;
            }
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n TargetScreen$lambda$0$0$0$0$0$0(a4.c cVar, int i5) {
        cVar.c(Integer.valueOf(i5));
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n TargetScreen$lambda$0$0$0$0$1(String str, f0.p pVar, int i5) {
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 3) != 2)) {
            n4.b(str, null, 0L, 0L, 0L, 0L, 2, false, 1, 0, null, vVar, 0, 3120, 120830);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n TargetScreen$lambda$0$0$1(AssistantSnapshot assistantSnapshot, boolean z2, TargetChoice targetChoice, a4.c cVar, r.w wVar, f0.p pVar, int i5) {
        b4.l.e("$this$SectionCard", wVar);
        int i6 = 1;
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 17) != 16)) {
            ListRow("跟随系统默认助理", "当前系统默认：" + assistantSnapshot.getDefaultLabel(), h1.c.w(), null, n0.i.c(2084776305, new c0(targetChoice, i6, cVar), vVar), null, false, vVar, 24582, 104);
            RowDivider(vVar, 0);
            e1.e eVarB = a.a.f15i;
            if (eVarB == null) {
                e1.d dVar = new e1.d("Rounded.Search");
                int i7 = e1.a0.f1864a;
                y0.s0 s0Var = new y0.s0(y0.u.f6689b);
                e1.f fVar = new e1.f();
                fVar.h(15.5f, 14.0f);
                fVar.e(-0.79f);
                fVar.g(-0.28f, -0.27f);
                fVar.c(1.2f, -1.4f, 1.82f, -3.31f, 1.48f, -5.34f);
                fVar.c(-0.47f, -2.78f, -2.79f, -5.0f, -5.59f, -5.34f);
                fVar.c(-4.23f, -0.52f, -7.79f, 3.04f, -7.27f, 7.27f);
                fVar.c(0.34f, 2.8f, 2.56f, 5.12f, 5.34f, 5.59f);
                fVar.c(2.03f, 0.34f, 3.94f, -0.28f, 5.34f, -1.48f);
                fVar.g(0.27f, 0.28f);
                fVar.l(0.79f);
                fVar.g(4.25f, 4.25f);
                fVar.c(0.41f, 0.41f, 1.08f, 0.41f, 1.49f, 0.0f);
                fVar.c(0.41f, -0.41f, 0.41f, -1.08f, 0.0f, -1.49f);
                fVar.f(15.5f, 14.0f);
                fVar.a();
                fVar.h(9.5f, 14.0f);
                fVar.b(7.01f, 14.0f, 5.0f, 11.99f, 5.0f, 9.5f);
                fVar.i(7.01f, 5.0f, 9.5f, 5.0f);
                fVar.i(14.0f, 7.01f, 14.0f, 9.5f);
                fVar.i(11.99f, 14.0f, 9.5f, 14.0f);
                fVar.a();
                e1.d.a(dVar, fVar.f1910d, s0Var);
                eVarB = dVar.b();
                a.a.f15i = eVarB;
            }
            ListRow("一圈即搜", "走系统 CTS 服务，不跟随默认助理设置", eVarB, null, n0.i.c(-1006120600, new c0(targetChoice, 2, cVar), vVar), null, false, vVar, 24630, 104);
            if (z2) {
                vVar.V(367392050);
                RowDivider(vVar, 0);
                ListRow("小布识屏", "不接管：长按手势条仍由 ColorOS 自己处理", a.a.C(), null, n0.i.c(302608524, new c0(targetChoice, 3, cVar), vVar), null, false, vVar, 24630, 104);
                vVar.p(false);
            } else {
                vVar.V(367926366);
                vVar.p(false);
            }
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n TargetScreen$lambda$0$0$1$0(TargetChoice targetChoice, a4.c cVar, f0.p pVar, int i5) {
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 3) != 2)) {
            boolean z2 = targetChoice instanceof TargetChoice.FollowDefault;
            boolean zF = vVar.f(cVar);
            Object objK = vVar.K();
            if (zF || objK == f0.o.f2281a) {
                objK = new a(cVar, 1);
                vVar.f0(objK);
            }
            Toggle(z2, (a4.c) objK, false, vVar, 0, 4);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n TargetScreen$lambda$0$0$1$0$0$0(a4.c cVar, boolean z2) {
        toggleChoice(z2, TargetChoice.FollowDefault.INSTANCE, cVar);
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n TargetScreen$lambda$0$0$1$1(TargetChoice targetChoice, a4.c cVar, f0.p pVar, int i5) {
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 3) != 2)) {
            boolean z2 = targetChoice instanceof TargetChoice.CircleToSearch;
            boolean zF = vVar.f(cVar);
            Object objK = vVar.K();
            if (zF || objK == f0.o.f2281a) {
                objK = new a(cVar, 3);
                vVar.f0(objK);
            }
            Toggle(z2, (a4.c) objK, false, vVar, 0, 4);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n TargetScreen$lambda$0$0$1$1$0$0(a4.c cVar, boolean z2) {
        toggleChoice(z2, TargetChoice.CircleToSearch.INSTANCE, cVar);
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n TargetScreen$lambda$0$0$1$2(TargetChoice targetChoice, a4.c cVar, f0.p pVar, int i5) {
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 3) != 2)) {
            boolean z2 = targetChoice instanceof TargetChoice.Oem;
            boolean zF = vVar.f(cVar);
            Object objK = vVar.K();
            if (zF || objK == f0.o.f2281a) {
                objK = new a(cVar, 0);
                vVar.f0(objK);
            }
            Toggle(z2, (a4.c) objK, false, vVar, 0, 4);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n TargetScreen$lambda$0$0$1$2$0$0(a4.c cVar, boolean z2) {
        toggleChoice(z2, TargetChoice.Oem.INSTANCE, cVar);
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n TargetScreen$lambda$0$0$2(boolean z2, a4.a aVar, TargetChoice targetChoice, a4.c cVar, r.w wVar, f0.p pVar, int i5) {
        b4.l.e("$this$SectionCard", wVar);
        int i6 = 0;
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 17) != 16)) {
            if (z2) {
                vVar.V(-215230565);
                ListRow("小布助手", "只有 ACTION_ASSIST 活动，直接唤起小布助手本体", h1.c.w(), null, n0.i.c(-1878991435, new c0(targetChoice, i6, cVar), vVar), null, false, vVar, 24630, 104);
                RowDivider(vVar, 0);
                vVar.p(false);
            } else {
                vVar.V(-214375275);
                vVar.p(false);
            }
            e1.e eVarB = d4.a.f1630a;
            if (eVarB == null) {
                e1.d dVar = new e1.d("Rounded.Add");
                int i7 = e1.a0.f1864a;
                y0.s0 s0Var = new y0.s0(y0.u.f6689b);
                e1.f fVar = new e1.f();
                fVar.h(18.0f, 13.0f);
                fVar.e(-5.0f);
                fVar.l(5.0f);
                fVar.c(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
                fVar.j(-1.0f, -0.45f, -1.0f, -1.0f);
                fVar.l(-5.0f);
                fVar.d(6.0f);
                fVar.c(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
                fVar.j(0.45f, -1.0f, 1.0f, -1.0f);
                fVar.e(5.0f);
                fVar.k(6.0f);
                fVar.c(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
                fVar.j(1.0f, 0.45f, 1.0f, 1.0f);
                fVar.l(5.0f);
                fVar.e(5.0f);
                fVar.c(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
                fVar.j(-0.45f, 1.0f, -1.0f, 1.0f);
                fVar.a();
                e1.d.a(dVar, fVar.f1910d, s0Var);
                eVarB = dVar.b();
                d4.a.f1630a = eVarB;
            }
            ListRow("其他应用…", "手动填包名或服务组件，用该应用自己的助理入口", eVarB, null, ComposableSingletons$AssistRestoreAppKt.INSTANCE.getLambda$1387262298$app_release(), aVar, false, vVar, 24630, 72);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n TargetScreen$lambda$0$0$2$0(TargetChoice targetChoice, a4.c cVar, f0.p pVar, int i5) {
        f0.v vVar = (f0.v) pVar;
        if (vVar.N(i5 & 1, (i5 & 3) != 2)) {
            boolean z2 = (targetChoice instanceof TargetChoice.App) && b4.l.a(((TargetChoice.App) targetChoice).getPackageName(), BREENO_PACKAGE);
            boolean zF = vVar.f(cVar);
            Object objK = vVar.K();
            if (zF || objK == f0.o.f2281a) {
                objK = new a(cVar, 2);
                vVar.f0(objK);
            }
            Toggle(z2, (a4.c) objK, false, vVar, 0, 4);
        } else {
            vVar.Q();
        }
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n TargetScreen$lambda$0$0$2$0$0$0(a4.c cVar, boolean z2) {
        toggleChoice(z2, new TargetChoice.App(BREENO_PACKAGE), cVar);
        return p3.n.f5026a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n TargetScreen$lambda$1(int i5, a4.c cVar, TargetChoice targetChoice, a4.c cVar2, AssistantSnapshot assistantSnapshot, a4.a aVar, a4.a aVar2, boolean z2, boolean z3, boolean z4, c3 c3Var, int i6, int i7, f0.p pVar, int i8) {
        TargetScreen(i5, cVar, targetChoice, cVar2, assistantSnapshot, aVar, aVar2, z2, z3, z4, c3Var, pVar, f0.l.z(i6 | 1), f0.l.z(i7));
        return p3.n.f5026a;
    }

    private static final void Toggle(boolean z2, a4.c cVar, boolean z3, f0.p pVar, final int i5, final int i6) {
        int i7;
        a4.c cVar2;
        final boolean z4;
        final boolean z5;
        f0.v vVar = (f0.v) pVar;
        vVar.X(786004900);
        if ((i5 & 6) == 0) {
            i7 = (vVar.g(z2) ? 4 : 2) | i5;
        } else {
            i7 = i5;
        }
        if ((i5 & 48) == 0) {
            i7 |= vVar.h(cVar) ? 32 : 16;
        }
        int i8 = i6 & 4;
        if (i8 != 0) {
            i7 |= 384;
        } else if ((i5 & 384) == 0) {
            i7 |= vVar.g(z3) ? 256 : 128;
        }
        if (vVar.N(i7 & 1, (i7 & 147) != 146)) {
            boolean z6 = i8 == 0 ? z3 : true;
            cVar2 = cVar;
            androidx.compose.material3.a.a(z2, cVar2, null, z6, null, vVar, (i7 & 126) | ((i7 << 6) & 57344));
            z4 = z2;
            z5 = z6;
        } else {
            cVar2 = cVar;
            z4 = z2;
            vVar.Q();
            z5 = z3;
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            final a4.c cVar3 = cVar2;
            b2VarR.f2098d = new a4.e() { // from class: io.github.andrea_lyz.assistrestore.ui.q
                @Override // a4.e
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return AssistRestoreAppKt.Toggle$lambda$0(z4, cVar3, z5, i5, i6, (f0.p) obj, iIntValue);
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p3.n Toggle$lambda$0(boolean z2, a4.c cVar, boolean z3, int i5, int i6, f0.p pVar, int i7) {
        Toggle(z2, cVar, z3, pVar, f0.l.z(i5 | 1), i6);
        return p3.n.f5026a;
    }

    private static final String describeChoice(TargetChoice targetChoice, AssistantSnapshot assistantSnapshot) {
        Object next;
        String packageName;
        if (b4.l.a(targetChoice, TargetChoice.FollowDefault.INSTANCE)) {
            return "当前：跟随系统默认助理 · " + assistantSnapshot.getDefaultLabel();
        }
        if (b4.l.a(targetChoice, TargetChoice.CircleToSearch.INSTANCE)) {
            return "当前：一圈即搜";
        }
        if (!(targetChoice instanceof TargetChoice.App)) {
            if (targetChoice instanceof TargetChoice.Custom) {
                return "当前：自定义 · " + ((TargetChoice.Custom) targetChoice).getPackageName();
            }
            if (b4.l.a(targetChoice, TargetChoice.Oem.INSTANCE)) {
                return "当前：小布识屏（ColorOS 原生）";
            }
            if (b4.l.a(targetChoice, TargetChoice.None.INSTANCE)) {
                return "当前：不唤醒助理";
            }
            throw new i4.p();
        }
        Iterator<T> it = assistantSnapshot.getApps().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!b4.l.a(((AssistantApp) next).getPackageName(), ((TargetChoice.App) targetChoice).getPackageName()));
        AssistantApp assistantApp = (AssistantApp) next;
        if (assistantApp == null || (packageName = assistantApp.getLabel()) == null) {
            packageName = ((TargetChoice.App) targetChoice).getPackageName();
        }
        return "当前：" + packageName;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:27:0x0067  */
    public static final List<TargetChoice> loadChoices(SettingsStore settingsStore) {
        TargetChoice targetChoice;
        TargetChoice custom;
        List<String> list = ENTRY_IDS;
        ArrayList arrayList = new ArrayList(q3.m.S(list));
        for (String str : list) {
            switch (settingsStore.mode(str)) {
                case "custom":
                    custom = new TargetChoice.Custom(settingsStore.targetPackage(str));
                    targetChoice = custom;
                    break;
                case "app":
                    custom = new TargetChoice.App(settingsStore.targetPackage(str));
                    targetChoice = custom;
                    break;
                case "cts":
                    targetChoice = TargetChoice.CircleToSearch.INSTANCE;
                    break;
                case "oem":
                    targetChoice = TargetChoice.Oem.INSTANCE;
                    break;
                case "none":
                    targetChoice = TargetChoice.None.INSTANCE;
                    break;
                default:
                    targetChoice = TargetChoice.FollowDefault.INSTANCE;
                    break;
            }
            arrayList.add(targetChoice);
        }
        return arrayList;
    }

    private static final PastedIntent parseIntentJson(String str) {
        String str2;
        String str3;
        int i5;
        int iS;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i6 = 0;
        while (true) {
            int iS2 = h4.g.S(str, '\"', i6, 4);
            if (iS2 >= 0 && (iS = h4.g.S(str, '\"', (i5 = iS2 + 1), 4)) >= 0) {
                String strSubstring = str.substring(i5, iS);
                b4.l.d("substring(...)", strSubstring);
                int iS3 = h4.g.S(str, ':', iS, 4);
                if (iS3 < 0) {
                    break;
                }
                do {
                    iS3++;
                    if (iS3 >= str.length()) {
                        break;
                    }
                } while (str.charAt(iS3) == ' ');
                if (iS3 < str.length() && str.charAt(iS3) == '\"') {
                    int i7 = iS3 + 1;
                    int iS4 = h4.g.S(str, '\"', i7, 4);
                    if (iS4 < 0) {
                        break;
                    }
                    String strSubstring2 = str.substring(i7, iS4);
                    b4.l.d("substring(...)", strSubstring2);
                    linkedHashMap.put(strSubstring, strSubstring2);
                    i6 = iS4 + 1;
                } else {
                    int iS5 = h4.g.S(str, ',', iS3, 4);
                    if (iS5 < 0) {
                        iS5 = str.length();
                    }
                    String strSubstring3 = str.substring(iS3, iS5);
                    b4.l.d("substring(...)", strSubstring3);
                    linkedHashMap.put(strSubstring, h4.g.W(strSubstring3).toString());
                    i6 = iS5;
                }
            } else {
                break;
            }
        }
        if (linkedHashMap.isEmpty()) {
            return null;
        }
        String str4 = (String) linkedHashMap.get("action");
        if (str4 == null) {
            str4 = "";
        }
        String str5 = (String) linkedHashMap.get("packageName");
        if (str5 == null) {
            str5 = "";
        }
        String str6 = (String) linkedHashMap.get("className");
        if (str6 == null) {
            str6 = "";
        }
        String str7 = (String) linkedHashMap.get("category");
        if (str7 == null) {
            str7 = "";
        }
        String str8 = (String) linkedHashMap.get("extra");
        if (str8 == null) {
            String str9 = str7;
            str3 = "";
            str2 = str9;
        } else {
            str2 = str7;
            str3 = str8;
        }
        return new PastedIntent(str4, str5, str6, str2, str3);
    }

    private static final void toggleChoice(boolean z2, TargetChoice targetChoice, a4.c cVar) {
        if (!z2) {
            targetChoice = TargetChoice.None.INSTANCE;
        }
        cVar.c(targetChoice);
    }
}
