package n;

import android.text.Layout;
import java.io.Serializable;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n extends b4.m implements a4.c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f3964e = 1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ long f3965f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f3966g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Serializable f3967h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Object f3968i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(long j5, float[] fArr, b4.u uVar, b4.t tVar) {
        super(1);
        this.f3965f = j5;
        this.f3966g = fArr;
        this.f3967h = uVar;
        this.f3968i = tVar;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(x0.d dVar, b4.w wVar, long j5, y0.m mVar) {
        super(1);
        this.f3966g = dVar;
        this.f3967h = wVar;
        this.f3965f = j5;
        this.f3968i = mVar;
    }

    @Override // a4.c
    public final Object c(Object obj) {
        float[] fArr;
        y1.a aVar;
        int i5;
        boolean z2;
        float fA;
        float fA2;
        switch (this.f3964e) {
            case 0:
                p1.f0 f0Var = (p1.f0) obj;
                f0Var.c();
                x0.d dVar = (x0.d) this.f3966g;
                float f5 = dVar.f6534a;
                float f6 = dVar.f6535b;
                b4.w wVar = (b4.w) this.f3967h;
                long j5 = this.f3965f;
                y0.m mVar = (y0.m) this.f3968i;
                a1.c cVar = f0Var.f4828d;
                ((a1.d) cVar.f27e.f23d).x(f5, f6);
                try {
                    a1.f.Q(f0Var, (y0.c0) wVar.f697d, j5, 0L, 0.0f, mVar, 890);
                    return p3.n.f5026a;
                } finally {
                    ((a1.d) cVar.f27e.f23d).x(-f5, -f6);
                }
            default:
                y1.o oVar = (y1.o) obj;
                float[] fArr2 = (float[]) this.f3966g;
                b4.u uVar = (b4.u) this.f3967h;
                b4.t tVar = (b4.t) this.f3968i;
                int i6 = oVar.f6800b;
                y1.a aVar2 = oVar.f6799a;
                int iD = oVar.f6801c;
                long j6 = this.f3965f;
                int iE = i6 > y1.g0.e(j6) ? oVar.f6800b : y1.g0.e(j6);
                if (iD >= y1.g0.d(j6)) {
                    iD = y1.g0.d(j6);
                }
                long jG = p3.j.g(oVar.b(iE), oVar.b(iD));
                int i7 = uVar.f695d;
                z1.y yVar = aVar2.f6706d;
                int iE2 = y1.g0.e(jG);
                int iD2 = y1.g0.d(jG);
                Layout layout = yVar.f7155e;
                int length = layout.getText().length();
                if (iE2 < 0) {
                    throw new IllegalArgumentException("startOffset must be > 0");
                }
                if (iE2 >= length) {
                    throw new IllegalArgumentException("startOffset must be less than text length");
                }
                if (iD2 <= iE2) {
                    throw new IllegalArgumentException("endOffset must be greater than startOffset");
                }
                if (iD2 > length) {
                    throw new IllegalArgumentException("endOffset must be smaller or equal to text length");
                }
                if (fArr2.length - i7 < (iD2 - iE2) * 4) {
                    throw new IllegalArgumentException("array.size - arrayStart must be greater or equal than (endOffset - startOffset) * 4");
                }
                int lineForOffset = layout.getLineForOffset(iE2);
                int lineForOffset2 = layout.getLineForOffset(iD2 - 1);
                v1.g gVar = new v1.g(yVar);
                if (lineForOffset <= lineForOffset2) {
                    while (true) {
                        int lineStart = layout.getLineStart(lineForOffset);
                        int i8 = i7;
                        int iF = yVar.f(lineForOffset);
                        int iMax = Math.max(iE2, lineStart);
                        int iMin = Math.min(iD2, iF);
                        float fG = yVar.g(lineForOffset);
                        float fE = yVar.e(lineForOffset);
                        fArr = fArr2;
                        z1.y yVar2 = yVar;
                        aVar = aVar2;
                        boolean z3 = false;
                        boolean z4 = layout.getParagraphDirection(lineForOffset) == 1;
                        int i9 = iMax;
                        int i10 = i8;
                        while (i9 < iMin) {
                            boolean zIsRtlCharAt = layout.isRtlCharAt(i9);
                            if (!z4 || zIsRtlCharAt) {
                                i5 = iMin;
                                if (z4 && zIsRtlCharAt) {
                                    z3 = false;
                                    float fA3 = gVar.a(i9, false, false, false);
                                    z2 = z4;
                                    fA = gVar.a(i9 + 1, true, true, false);
                                    fA2 = fA3;
                                } else {
                                    z2 = z4;
                                    z3 = false;
                                    if (z2 || !zIsRtlCharAt) {
                                        fA = gVar.a(i9, false, false, false);
                                        fA2 = gVar.a(i9 + 1, true, true, false);
                                    } else {
                                        fA2 = gVar.a(i9, false, false, true);
                                        fA = gVar.a(i9 + 1, true, true, true);
                                    }
                                }
                                fArr[i10] = fA;
                                fArr[i10 + 1] = fG;
                                fArr[i10 + 2] = fA2;
                                fArr[i10 + 3] = fE;
                                i10 += 4;
                                i9++;
                                z4 = z2;
                                iMin = i5;
                            } else {
                                i5 = iMin;
                                fA = gVar.a(i9, z3, z3, true);
                                z2 = z4;
                                fA2 = gVar.a(i9 + 1, true, true, true);
                            }
                            z3 = false;
                            fArr[i10] = fA;
                            fArr[i10 + 1] = fG;
                            fArr[i10 + 2] = fA2;
                            fArr[i10 + 3] = fE;
                            i10 += 4;
                            i9++;
                            z4 = z2;
                            iMin = i5;
                        }
                        if (lineForOffset != lineForOffset2) {
                            lineForOffset++;
                            i7 = i10;
                            fArr2 = fArr;
                            yVar = yVar2;
                            aVar2 = aVar;
                        }
                    }
                } else {
                    fArr = fArr2;
                    aVar = aVar2;
                }
                int iC = (y1.g0.c(jG) * 4) + uVar.f695d;
                for (int i11 = uVar.f695d; i11 < iC; i11 += 4) {
                    int i12 = i11 + 1;
                    float f7 = fArr[i12];
                    float f8 = tVar.f694d;
                    fArr[i12] = f7 + f8;
                    int i13 = i11 + 3;
                    fArr[i13] = fArr[i13] + f8;
                }
                uVar.f695d = iC;
                tVar.f694d = aVar.b() + tVar.f694d;
                return p3.n.f5026a;
        }
    }
}
