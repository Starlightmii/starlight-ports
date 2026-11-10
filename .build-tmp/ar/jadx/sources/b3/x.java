package b3;

import java.nio.ByteBuffer;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class x {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final ThreadLocal f660d = new ThreadLocal();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f661a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final w f662b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile int f663c = 0;

    public x(w wVar, int i5) {
        this.f662b = wVar;
        this.f661a = i5;
    }

    public final int a(int i5) {
        c3.a aVarB = b();
        int iA = aVarB.a(16);
        if (iA == 0) {
            return 0;
        }
        ByteBuffer byteBuffer = (ByteBuffer) aVarB.f1504g;
        int i6 = iA + aVarB.f1501d;
        return byteBuffer.getInt((i5 * 4) + byteBuffer.getInt(i6) + i6 + 4);
    }

    public final c3.a b() {
        ThreadLocal threadLocal = f660d;
        c3.a aVar = (c3.a) threadLocal.get();
        if (aVar == null) {
            aVar = new c3.a();
            threadLocal.set(aVar);
        }
        c3.b bVar = (c3.b) this.f662b.f656d;
        int iA = bVar.a(6);
        if (iA != 0) {
            int i5 = iA + bVar.f1501d;
            int i6 = (this.f661a * 4) + ((ByteBuffer) bVar.f1504g).getInt(i5) + i5 + 4;
            int i7 = ((ByteBuffer) bVar.f1504g).getInt(i6) + i6;
            ByteBuffer byteBuffer = (ByteBuffer) bVar.f1504g;
            aVar.f1504g = byteBuffer;
            if (byteBuffer != null) {
                aVar.f1501d = i7;
                int i8 = i7 - byteBuffer.getInt(i7);
                aVar.f1502e = i8;
                aVar.f1503f = ((ByteBuffer) aVar.f1504g).getShort(i8);
                return aVar;
            }
            aVar.f1501d = 0;
            aVar.f1502e = 0;
            aVar.f1503f = 0;
        }
        return aVar;
    }

    public final String toString() {
        int i5;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", id:");
        c3.a aVarB = b();
        int iA = aVarB.a(4);
        sb.append(Integer.toHexString(iA != 0 ? ((ByteBuffer) aVarB.f1504g).getInt(iA + aVarB.f1501d) : 0));
        sb.append(", codepoints:");
        c3.a aVarB2 = b();
        int iA2 = aVarB2.a(16);
        if (iA2 != 0) {
            int i6 = iA2 + aVarB2.f1501d;
            i5 = ((ByteBuffer) aVarB2.f1504g).getInt(((ByteBuffer) aVarB2.f1504g).getInt(i6) + i6);
        } else {
            i5 = 0;
        }
        for (int i7 = 0; i7 < i5; i7++) {
            sb.append(Integer.toHexString(a(i7)));
            sb.append(" ");
        }
        return sb.toString();
    }
}
