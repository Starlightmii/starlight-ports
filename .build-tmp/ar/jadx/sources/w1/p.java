package w1;

import io.github.libxposed.service.IXposedService;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class p extends b4.m implements a4.e {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final p f6324f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final p f6325g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final p f6326h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final p f6327i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final p f6328j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final p f6329k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final p f6330l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final p f6331m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final p f6332n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final p f6333o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final p f6334p;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f6335e;

    static {
        int i5 = 2;
        f6324f = new p(i5, 0);
        f6325g = new p(i5, 1);
        f6326h = new p(i5, 2);
        f6327i = new p(i5, 3);
        f6328j = new p(i5, 4);
        f6329k = new p(i5, 5);
        f6330l = new p(i5, 6);
        f6331m = new p(i5, 7);
        f6332n = new p(i5, 8);
        f6333o = new p(i5, 9);
        f6334p = new p(i5, 10);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ p(int i5, int i6) {
        super(i5);
        this.f6335e = i6;
    }

    @Override // a4.e
    public final Object invoke(Object obj, Object obj2) {
        String str;
        p3.c cVar;
        switch (this.f6335e) {
            case 0:
                List list = (List) obj;
                List list2 = (List) obj2;
                if (list == null) {
                    return list2;
                }
                ArrayList arrayListG0 = q3.l.g0(list);
                arrayListG0.addAll(list2);
                return arrayListG0;
            case 1:
                return (p3.n) obj;
            case 2:
                throw new IllegalStateException("merge function called on unmergeable property IsPopup. A popup should not be a child of a clickable/focusable node.");
            case 3:
                throw new IllegalStateException("merge function called on unmergeable property PaneTitle.");
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                f fVar = (f) obj;
                int i5 = ((f) obj2).f6279a;
                return fVar;
            case 5:
                return (String) obj;
            case 6:
                List list3 = (List) obj;
                List list4 = (List) obj2;
                if (list3 == null) {
                    return list4;
                }
                ArrayList arrayListG1 = q3.l.g0(list3);
                arrayListG1.addAll(list4);
                return arrayListG1;
            case 7:
                Float f5 = (Float) obj;
                ((Number) obj2).floatValue();
                return f5;
            case 8:
                Boolean bool = (Boolean) obj;
                ((Boolean) obj2).booleanValue();
                return bool;
            case 9:
                a aVar = (a) obj;
                a aVar2 = (a) obj2;
                if (aVar == null || (str = aVar.f6272a) == null) {
                    str = aVar2.f6272a;
                }
                if (aVar == null || (cVar = aVar.f6273b) == null) {
                    cVar = aVar2.f6273b;
                }
                return new a(str, cVar);
            default:
                return obj == null ? obj2 : obj;
        }
    }
}
