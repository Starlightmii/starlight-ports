package b3;

import android.graphics.Typeface;
import java.nio.ByteBuffer;
import java.util.Iterator;
import m.e1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class w implements e1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f656d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f657e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f658f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object f659g;

    public w(Typeface typeface, c3.b bVar) {
        int i5;
        int i6;
        int i7;
        int i8;
        this.f659g = typeface;
        this.f656d = bVar;
        this.f658f = new v(1024);
        int iA = bVar.a(6);
        if (iA != 0) {
            int i9 = iA + bVar.f1501d;
            i5 = ((ByteBuffer) bVar.f1504g).getInt(((ByteBuffer) bVar.f1504g).getInt(i9) + i9);
        } else {
            i5 = 0;
        }
        this.f657e = new char[i5 * 2];
        int iA2 = bVar.a(6);
        if (iA2 != 0) {
            int i10 = iA2 + bVar.f1501d;
            i6 = ((ByteBuffer) bVar.f1504g).getInt(((ByteBuffer) bVar.f1504g).getInt(i10) + i10);
        } else {
            i6 = 0;
        }
        for (int i11 = 0; i11 < i6; i11++) {
            x xVar = new x(this, i11);
            c3.a aVarB = xVar.b();
            int iA3 = aVarB.a(4);
            Character.toChars(iA3 != 0 ? ((ByteBuffer) aVarB.f1504g).getInt(iA3 + aVarB.f1501d) : 0, (char[]) this.f657e, i11 * 2);
            c3.a aVarB2 = xVar.b();
            int iA4 = aVarB2.a(16);
            if (iA4 != 0) {
                int i12 = iA4 + aVarB2.f1501d;
                i7 = ((ByteBuffer) aVarB2.f1504g).getInt(((ByteBuffer) aVarB2.f1504g).getInt(i12) + i12);
            } else {
                i7 = 0;
            }
            if (!(i7 > 0)) {
                throw new IllegalArgumentException("invalid metadata codepoint length");
            }
            v vVar = (v) this.f658f;
            c3.a aVarB3 = xVar.b();
            int iA5 = aVarB3.a(16);
            if (iA5 != 0) {
                int i13 = iA5 + aVarB3.f1501d;
                i8 = ((ByteBuffer) aVarB3.f1504g).getInt(((ByteBuffer) aVarB3.f1504g).getInt(i13) + i13);
            } else {
                i8 = 0;
            }
            vVar.a(xVar, 0, i8 - 1);
        }
    }

    public /* synthetic */ w(Object obj) {
        this.f656d = obj;
    }

    public w(m.y yVar) {
        this(new a1.d(15, yVar));
    }

    @Override // m.c1
    public long b(m.q qVar, m.q qVar2, m.q qVar3) {
        Iterator it = d4.a.N(0, qVar.b()).iterator();
        long jMax = 0;
        while (true) {
            e4.c cVar = (e4.c) it;
            if (!cVar.f2079f) {
                return jMax;
            }
            int iNextInt = cVar.nextInt();
            jMax = Math.max(jMax, ((m.r) this.f656d).get(iNextInt).d(qVar.a(iNextInt), qVar2.a(iNextInt), qVar3.a(iNextInt)));
        }
    }

    public m.q c(long j5, m.q qVar, m.q qVar2) {
        if (((m.q) this.f658f) == null) {
            this.f658f = qVar.c();
        }
        m.q qVar3 = (m.q) this.f658f;
        if (qVar3 == null) {
            b4.l.j("velocityVector");
            throw null;
        }
        int iB = qVar3.b();
        for (int i5 = 0; i5 < iB; i5++) {
            m.q qVar4 = (m.q) this.f658f;
            if (qVar4 == null) {
                b4.l.j("velocityVector");
                throw null;
            }
            a1.d dVar = (a1.d) this.f656d;
            qVar.getClass();
            long j6 = j5 / 1000000;
            l.d dVarA = ((l.e) dVar.f31e).a(qVar2.a(i5));
            long j7 = dVarA.f3488c;
            qVar4.e((((Math.signum(dVarA.f3486a) * l.b.a(j7 > 0 ? j6 / j7 : 1.0f).f3483b) * dVarA.f3487b) / j7) * 1000.0f, i5);
        }
        m.q qVar5 = (m.q) this.f658f;
        if (qVar5 != null) {
            return qVar5;
        }
        b4.l.j("velocityVector");
        throw null;
    }

    @Override // m.c1
    public m.q d(long j5, m.q qVar, m.q qVar2, m.q qVar3) {
        if (((m.q) this.f658f) == null) {
            this.f658f = qVar3.c();
        }
        m.q qVar4 = (m.q) this.f658f;
        if (qVar4 == null) {
            b4.l.j("velocityVector");
            throw null;
        }
        int iB = qVar4.b();
        for (int i5 = 0; i5 < iB; i5++) {
            m.q qVar5 = (m.q) this.f658f;
            if (qVar5 == null) {
                b4.l.j("velocityVector");
                throw null;
            }
            qVar5.e(((m.r) this.f656d).get(i5).c(j5, qVar.a(i5), qVar2.a(i5), qVar3.a(i5)), i5);
        }
        m.q qVar6 = (m.q) this.f658f;
        if (qVar6 != null) {
            return qVar6;
        }
        b4.l.j("velocityVector");
        throw null;
    }

    @Override // m.c1
    public m.q f(long j5, m.q qVar, m.q qVar2, m.q qVar3) {
        if (((m.q) this.f657e) == null) {
            this.f657e = qVar.c();
        }
        m.q qVar4 = (m.q) this.f657e;
        if (qVar4 == null) {
            b4.l.j("valueVector");
            throw null;
        }
        int iB = qVar4.b();
        for (int i5 = 0; i5 < iB; i5++) {
            m.q qVar5 = (m.q) this.f657e;
            if (qVar5 == null) {
                b4.l.j("valueVector");
                throw null;
            }
            qVar5.e(((m.r) this.f656d).get(i5).b(j5, qVar.a(i5), qVar2.a(i5), qVar3.a(i5)), i5);
        }
        m.q qVar6 = (m.q) this.f657e;
        if (qVar6 != null) {
            return qVar6;
        }
        b4.l.j("valueVector");
        throw null;
    }

    @Override // m.c1
    public m.q o(m.q qVar, m.q qVar2, m.q qVar3) {
        if (((m.q) this.f659g) == null) {
            this.f659g = qVar3.c();
        }
        m.q qVar4 = (m.q) this.f659g;
        if (qVar4 == null) {
            b4.l.j("endVelocityVector");
            throw null;
        }
        int iB = qVar4.b();
        for (int i5 = 0; i5 < iB; i5++) {
            m.q qVar5 = (m.q) this.f659g;
            if (qVar5 == null) {
                b4.l.j("endVelocityVector");
                throw null;
            }
            qVar5.e(((m.r) this.f656d).get(i5).e(qVar.a(i5), qVar2.a(i5), qVar3.a(i5)), i5);
        }
        m.q qVar6 = (m.q) this.f659g;
        if (qVar6 != null) {
            return qVar6;
        }
        b4.l.j("endVelocityVector");
        throw null;
    }
}
