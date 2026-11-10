package o0;

import f0.d2;
import j.f0;
import j.z;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import q1.m0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z f4364a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public z f4365b;

    public d(LinkedHashMap linkedHashMap) {
        z zVar;
        if (linkedHashMap == null || linkedHashMap.isEmpty()) {
            zVar = null;
        } else {
            zVar = new z(linkedHashMap.size());
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                zVar.i(entry.getKey(), entry.getValue());
            }
        }
        this.f4364a = zVar;
    }

    @Override // o0.c
    public final boolean a(Object obj) {
        return m0.j(obj);
    }

    @Override // o0.c
    public final Object b(String str) {
        z zVar = this.f4364a;
        List list = zVar != null ? (List) zVar.g(str) : null;
        if (list == null || list.isEmpty()) {
            return null;
        }
        if (list.size() > 1 && zVar != null) {
            List listSubList = list.subList(1, list.size());
            int iD = zVar.d(str);
            if (iD < 0) {
                iD = ~iD;
            }
            Object[] objArr = zVar.f3178c;
            Object obj = objArr[iD];
            zVar.f3177b[iD] = str;
            objArr[iD] = listSubList;
        }
        return list.get(0);
    }

    @Override // o0.c
    public final a1.b c(String str, d2 d2Var) {
        int length = str.length();
        for (int i5 = 0; i5 < length; i5++) {
            if (!h1.c.B(str.charAt(i5))) {
                z zVar = this.f4365b;
                if (zVar == null) {
                    long[] jArr = f0.f3102a;
                    zVar = new z();
                    this.f4365b = zVar;
                }
                Object objE = zVar.e(str);
                if (objE == null) {
                    objE = new ArrayList();
                    zVar.i(str, objE);
                }
                ((List) objE).add(d2Var);
                return new a1.b(zVar, str, d2Var);
            }
        }
        throw new IllegalArgumentException("Registered key is empty or blank");
    }
}
