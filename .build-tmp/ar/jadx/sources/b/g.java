package b;

import android.os.Bundle;
import io.github.andrea_lyz.assistrestore.ui.MainActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import q1.m0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class g implements j3.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f331a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f332b;

    public /* synthetic */ g(int i5, Object obj) {
        this.f331a = i5;
        this.f332b = obj;
    }

    /* JADX WARN: Code duplicated, block: B:37:0x008e  */
    @Override // j3.d
    public final Bundle a() {
        Map mapSingletonMap;
        p3.f[] fVarArr;
        long j5;
        char c5;
        long j6;
        long j7;
        Map map;
        long[] jArr;
        int i5;
        long[] jArr2;
        int i6;
        switch (this.f331a) {
            case 0:
                return r.c((MainActivity) this.f332b);
            case 1:
                f3.a aVar = (f3.a) this.f332b;
                LinkedHashMap linkedHashMap = (LinkedHashMap) aVar.f2452d;
                b4.l.e("<this>", linkedHashMap);
                int size = linkedHashMap.size();
                Map mapSingletonMap2 = q3.t.f5478d;
                if (size == 0) {
                    mapSingletonMap = mapSingletonMap2;
                } else if (size != 1) {
                    mapSingletonMap = new LinkedHashMap(linkedHashMap);
                } else {
                    Map.Entry entry = (Map.Entry) linkedHashMap.entrySet().iterator().next();
                    mapSingletonMap = Collections.singletonMap(entry.getKey(), entry.getValue());
                    b4.l.d("with(...)", mapSingletonMap);
                }
                for (Map.Entry entry2 : mapSingletonMap.entrySet()) {
                    aVar.k((String) entry2.getKey(), ((l4.d0) ((l4.p) entry2.getValue())).getValue());
                }
                LinkedHashMap linkedHashMap2 = (LinkedHashMap) aVar.f2450b;
                b4.l.e("<this>", linkedHashMap2);
                int size2 = linkedHashMap2.size();
                if (size2 != 0) {
                    if (size2 != 1) {
                        mapSingletonMap2 = new LinkedHashMap(linkedHashMap2);
                    } else {
                        Map.Entry entry3 = (Map.Entry) linkedHashMap2.entrySet().iterator().next();
                        mapSingletonMap2 = Collections.singletonMap(entry3.getKey(), entry3.getValue());
                        b4.l.d("with(...)", mapSingletonMap2);
                    }
                }
                for (Map.Entry entry4 : mapSingletonMap2.entrySet()) {
                    aVar.k((String) entry4.getKey(), ((j3.d) entry4.getValue()).a());
                }
                LinkedHashMap linkedHashMap3 = (LinkedHashMap) aVar.f2449a;
                if (linkedHashMap3.isEmpty()) {
                    fVarArr = new p3.f[0];
                } else {
                    ArrayList arrayList = new ArrayList(linkedHashMap3.size());
                    for (Map.Entry entry5 : linkedHashMap3.entrySet()) {
                        arrayList.add(new p3.f((String) entry5.getKey(), entry5.getValue()));
                    }
                    fVarArr = (p3.f[]) arrayList.toArray(new p3.f[0]);
                }
                return q.f.g((p3.f[]) Arrays.copyOf(fVarArr, fVarArr.length));
            default:
                o0.d dVar = (o0.d) this.f332b;
                j.z zVar = dVar.f4364a;
                if (zVar == null && dVar.f4365b == null) {
                    map = q3.t.f5478d;
                } else {
                    int i7 = 0;
                    int i8 = zVar != null ? zVar.f3180e : 0;
                    j.z zVar2 = dVar.f4365b;
                    HashMap map2 = new HashMap(i8 + (zVar2 != null ? zVar2.f3180e : 0));
                    long j8 = -9187201950435737472L;
                    int i9 = 8;
                    if (zVar != null) {
                        Object[] objArr = zVar.f3177b;
                        Object[] objArr2 = zVar.f3178c;
                        long[] jArr3 = zVar.f3176a;
                        int length = jArr3.length - 2;
                        if (length >= 0) {
                            int i10 = 0;
                            c5 = 7;
                            j6 = 128;
                            while (true) {
                                long j9 = jArr3[i10];
                                j7 = 255;
                                if ((((~j9) << 7) & j9 & j8) != j8) {
                                    int i11 = 8 - ((~(i10 - length)) >>> 31);
                                    int i12 = 0;
                                    while (i12 < i11) {
                                        if ((j9 & 255) < 128) {
                                            int i13 = (i10 << 3) + i12;
                                            map2.put((String) objArr[i13], (List) objArr2[i13]);
                                        }
                                        j9 >>= 8;
                                        i12++;
                                        j8 = j8;
                                    }
                                    j5 = j8;
                                    if (i11 == 8) {
                                    }
                                } else {
                                    j5 = j8;
                                }
                                if (i10 != length) {
                                    i10++;
                                    j8 = j5;
                                }
                            }
                        } else {
                            j5 = -9187201950435737472L;
                            c5 = 7;
                            j6 = 128;
                            j7 = 255;
                        }
                    } else {
                        j5 = -9187201950435737472L;
                        c5 = 7;
                        j6 = 128;
                        j7 = 255;
                    }
                    j.z zVar3 = dVar.f4365b;
                    if (zVar3 != null) {
                        Object[] objArr3 = zVar3.f3177b;
                        Object[] objArr4 = zVar3.f3178c;
                        long[] jArr4 = zVar3.f3176a;
                        int length2 = jArr4.length - 2;
                        if (length2 >= 0) {
                            int i14 = 0;
                            while (true) {
                                long j10 = jArr4[i14];
                                if ((((~j10) << c5) & j10 & j5) != j5) {
                                    int i15 = 8 - ((~(i14 - length2)) >>> 31);
                                    int i16 = i7;
                                    while (i16 < i15) {
                                        if ((j10 & j7) < j6) {
                                            int i17 = (i14 << 3) + i16;
                                            Object obj = objArr3[i17];
                                            List list = (List) objArr4[i17];
                                            String str = (String) obj;
                                            i6 = i9;
                                            if (list.size() == 1) {
                                                Object objA = ((a4.a) list.get(i7)).a();
                                                if (objA != null) {
                                                    if (!m0.j(objA)) {
                                                        throw new IllegalStateException(o0.g.a(objA).toString());
                                                    }
                                                    map2.put(str, p3.j.l(objA));
                                                }
                                                jArr2 = jArr4;
                                            } else {
                                                int size3 = list.size();
                                                ArrayList arrayList2 = new ArrayList(size3);
                                                while (i7 < size3) {
                                                    long[] jArr5 = jArr4;
                                                    Object objA2 = ((a4.a) list.get(i7)).a();
                                                    if (objA2 != null && !m0.j(objA2)) {
                                                        throw new IllegalStateException(o0.g.a(objA2).toString());
                                                    }
                                                    arrayList2.add(objA2);
                                                    i7++;
                                                    jArr4 = jArr5;
                                                }
                                                jArr2 = jArr4;
                                                map2.put(str, arrayList2);
                                            }
                                        } else {
                                            jArr2 = jArr4;
                                            i6 = i9;
                                        }
                                        j10 >>= i6;
                                        i16++;
                                        i9 = i6;
                                        jArr4 = jArr2;
                                        i7 = 0;
                                    }
                                    jArr = jArr4;
                                    i5 = i9;
                                    if (i15 == i5) {
                                    }
                                } else {
                                    jArr = jArr4;
                                    i5 = i9;
                                }
                                if (i14 != length2) {
                                    i14++;
                                    i9 = i5;
                                    jArr4 = jArr;
                                    i7 = 0;
                                }
                            }
                        }
                    }
                    map = map2;
                }
                Bundle bundle = new Bundle();
                for (Map.Entry entry6 : map.entrySet()) {
                    String str2 = (String) entry6.getKey();
                    List list2 = (List) entry6.getValue();
                    bundle.putParcelableArrayList(str2, list2 instanceof ArrayList ? (ArrayList) list2 : new ArrayList<>(list2));
                }
                return bundle;
        }
    }
}
