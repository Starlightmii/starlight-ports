package q1;

import java.util.Comparator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class a0 implements Comparator {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a0 f5105b = new a0(0);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final a0 f5106c = new a0(1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a0 f5107d = new a0(2);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5108a;

    public /* synthetic */ a0(int i5) {
        this.f5108a = i5;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        switch (this.f5108a) {
            case 0:
                x0.d dVarF = ((w1.n) obj).f();
                x0.d dVarF2 = ((w1.n) obj2).f();
                int iCompare = Float.compare(dVarF.f6534a, dVarF2.f6534a);
                if (iCompare != 0) {
                    return iCompare;
                }
                int iCompare2 = Float.compare(dVarF.f6535b, dVarF2.f6535b);
                if (iCompare2 != 0) {
                    return iCompare2;
                }
                int iCompare3 = Float.compare(dVarF.f6537d, dVarF2.f6537d);
                return iCompare3 != 0 ? iCompare3 : Float.compare(dVarF.f6536c, dVarF2.f6536c);
            case 1:
                x0.d dVarF3 = ((w1.n) obj).f();
                x0.d dVarF4 = ((w1.n) obj2).f();
                int iCompare4 = Float.compare(dVarF4.f6536c, dVarF3.f6536c);
                if (iCompare4 != 0) {
                    return iCompare4;
                }
                int iCompare5 = Float.compare(dVarF3.f6535b, dVarF4.f6535b);
                if (iCompare5 != 0) {
                    return iCompare5;
                }
                int iCompare6 = Float.compare(dVarF3.f6537d, dVarF4.f6537d);
                return iCompare6 != 0 ? iCompare6 : Float.compare(dVarF4.f6534a, dVarF3.f6534a);
            default:
                p3.f fVar = (p3.f) obj;
                p3.f fVar2 = (p3.f) obj2;
                int iCompare7 = Float.compare(((x0.d) fVar.f5014d).f6535b, ((x0.d) fVar2.f5014d).f6535b);
                return iCompare7 != 0 ? iCompare7 : Float.compare(((x0.d) fVar.f5014d).f6537d, ((x0.d) fVar2.f5014d).f6537d);
        }
    }
}
