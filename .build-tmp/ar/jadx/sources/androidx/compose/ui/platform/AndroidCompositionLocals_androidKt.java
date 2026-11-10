package androidx.compose.ui.platform;

import a4.c;
import a4.e;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import c0.c2;
import c0.g1;
import f0.b2;
import f0.h;
import f0.h3;
import f0.i1;
import f0.j0;
import f0.l;
import f0.o;
import f0.p;
import f0.v;
import f0.y1;
import f0.z1;
import io.github.andrea_lyz.assistrestore.R;
import j3.g;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import n0.i;
import o0.d;
import p.c1;
import p3.n;
import q1.j1;
import q1.m;
import q1.m1;
import q1.n0;
import q1.n1;
import q1.o0;
import q1.p0;
import q1.u;
import q1.x0;
import u1.a;
import u1.b;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class AndroidCompositionLocals_androidKt {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j0 f203a = new j0(n0.f5270f);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final h3 f204b = new h3(n0.f5271g);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final h3 f205c = new h3(n0.f5272h);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final h3 f206d = new h3(n0.f5273i);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final h3 f207e = new h3(n0.f5274j);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final h3 f208f = new h3(n0.f5275k);

    public static final void a(u uVar, e eVar, p pVar, int i5) {
        i1 i1Var;
        LinkedHashMap linkedHashMap;
        boolean z2;
        v vVar = (v) pVar;
        vVar.X(1396852028);
        if ((((vVar.h(uVar) ? 4 : 2) | i5 | (vVar.h(eVar) ? 32 : 16)) & 19) == 18 && vVar.z()) {
            vVar.Q();
        } else {
            Context context = uVar.getContext();
            Object objK = vVar.K();
            h hVar = o.f2281a;
            if (objK == hVar) {
                objK = l.s(new Configuration(context.getResources().getConfiguration()));
                vVar.f0(objK);
            }
            i1 i1Var2 = (i1) objK;
            Object objK2 = vVar.K();
            if (objK2 == hVar) {
                objK2 = new c1(i1Var2, 1);
                vVar.f0(objK2);
            }
            uVar.setConfigurationChangeObserver((c) objK2);
            Object objK3 = vVar.K();
            if (objK3 == hVar) {
                objK3 = new x0();
                vVar.f0(objK3);
            }
            x0 x0Var = (x0) objK3;
            m viewTreeOwners = uVar.getViewTreeOwners();
            if (viewTreeOwners == null) {
                throw new IllegalStateException("Called when the ViewTreeOwnersAvailability is not yet in Available state");
            }
            g gVar = viewTreeOwners.f5260b;
            Object objK4 = vVar.K();
            if (objK4 == hVar) {
                Object parent = uVar.getParent();
                b4.l.c("null cannot be cast to non-null type android.view.View", parent);
                View view = (View) parent;
                Object tag = view.getTag(R.id.compose_view_saveable_id_tag);
                String strValueOf = tag instanceof String ? (String) tag : null;
                if (strValueOf == null) {
                    strValueOf = String.valueOf(view.getId());
                }
                String str = o0.c.class.getSimpleName() + ':' + strValueOf;
                j3.e savedStateRegistry = gVar.getSavedStateRegistry();
                Bundle bundleA = savedStateRegistry.a(str);
                if (bundleA != null) {
                    linkedHashMap = new LinkedHashMap();
                    for (String str2 : bundleA.keySet()) {
                        ArrayList parcelableArrayList = bundleA.getParcelableArrayList(str2);
                        b4.l.c("null cannot be cast to non-null type java.util.ArrayList<kotlin.Any?>{ kotlin.collections.TypeAliasesKt.ArrayList<kotlin.Any?> }", parcelableArrayList);
                        linkedHashMap.put(str2, parcelableArrayList);
                        i1Var2 = i1Var2;
                    }
                } else {
                    linkedHashMap = null;
                }
                i1Var = i1Var2;
                h3 h3Var = o0.e.f4366a;
                d dVar = new d(linkedHashMap);
                try {
                    savedStateRegistry.b(str, new b.g(2, dVar));
                    z2 = true;
                } catch (IllegalArgumentException unused) {
                    z2 = false;
                }
                m1 m1Var = new m1(dVar, new n1(z2, savedStateRegistry, str));
                vVar.f0(m1Var);
                objK4 = m1Var;
            } else {
                i1Var = i1Var2;
            }
            m1 m1Var2 = (m1) objK4;
            boolean zH = vVar.h(m1Var2);
            Object objK5 = vVar.K();
            if (zH || objK5 == hVar) {
                objK5 = new g1(19, m1Var2);
                vVar.f0(objK5);
            }
            l.c(n.f5026a, (c) objK5, vVar);
            Configuration configuration = (Configuration) i1Var.getValue();
            Object objK6 = vVar.K();
            if (objK6 == hVar) {
                objK6 = new a();
                vVar.f0(objK6);
            }
            a aVar = (a) objK6;
            Object objK7 = vVar.K();
            Object obj = objK7;
            if (objK7 == hVar) {
                Configuration configuration2 = new Configuration();
                if (configuration != null) {
                    configuration2.setTo(configuration);
                }
                vVar.f0(configuration2);
                obj = configuration2;
            }
            Configuration configuration3 = (Configuration) obj;
            Object objK8 = vVar.K();
            if (objK8 == hVar) {
                objK8 = new o0(configuration3, aVar);
                vVar.f0(objK8);
            }
            o0 o0Var = (o0) objK8;
            boolean zH2 = vVar.h(context);
            Object objK9 = vVar.K();
            if (zH2 || objK9 == hVar) {
                objK9 = new c2(context, 14, o0Var);
                vVar.f0(objK9);
            }
            l.c(aVar, (c) objK9, vVar);
            Object objK10 = vVar.K();
            if (objK10 == hVar) {
                objK10 = new b();
                vVar.f0(objK10);
            }
            b bVar = (b) objK10;
            Object objK11 = vVar.K();
            if (objK11 == hVar) {
                objK11 = new p0(bVar);
                vVar.f0(objK11);
            }
            p0 p0Var = (p0) objK11;
            boolean zH3 = vVar.h(context);
            Object objK12 = vVar.K();
            if (zH3 || objK12 == hVar) {
                objK12 = new c2(context, 15, p0Var);
                vVar.f0(objK12);
            }
            l.c(bVar, (c) objK12, vVar);
            j0 j0Var = j1.f5251t;
            l.b(new z1[]{f203a.a((Configuration) i1Var.getValue()), f204b.a(context), e3.b.f2073a.a(viewTreeOwners.f5259a), f207e.a(gVar), o0.e.f4366a.a(m1Var2), f208f.a(uVar.getView()), f205c.a(aVar), f206d.a(bVar), j0Var.a(Boolean.valueOf(((Boolean) vVar.j(j0Var)).booleanValue() | uVar.getScrollCaptureInProgress$ui_release()))}, i.c(1471621628, new c0.x0(uVar, x0Var, eVar, 4), vVar), vVar, 56);
        }
        b2 b2VarR = vVar.r();
        if (b2VarR != null) {
            b2VarR.f2098d = new c0.l(i5, 12, uVar, eVar);
        }
    }

    public static final void b(String str) {
        throw new IllegalStateException(("CompositionLocal " + str + " not present").toString());
    }

    public static final y1 getLocalLifecycleOwner() {
        return e3.b.f2073a;
    }
}
