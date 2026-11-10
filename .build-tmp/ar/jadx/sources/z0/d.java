package z0;

import e2.f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float[] f7040a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final float[] f7041b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final q f7042c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final q f7043d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final q f7044e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final q f7045f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final q f7046g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final q f7047h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final q f7048i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final q f7049j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final q f7050k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final q f7051l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final q f7052m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final q f7053n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final q f7054o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final q f7055p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final k f7056q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final k f7057r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final q f7058s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final l f7059t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final c[] f7060u;

    static {
        float[] fArr = {0.64f, 0.33f, 0.3f, 0.6f, 0.15f, 0.06f};
        f7040a = fArr;
        float[] fArr2 = {0.67f, 0.33f, 0.21f, 0.71f, 0.14f, 0.08f};
        f7041b = fArr2;
        r rVar = new r(2.4d, 0.9478672985781991d, 0.05213270142180095d, 0.07739938080495357d, 0.04045d);
        r rVar2 = new r(2.2d, 0.9478672985781991d, 0.05213270142180095d, 0.07739938080495357d, 0.04045d);
        s sVar = j.f7072d;
        q qVar = new q("sRGB IEC61966-2.1", fArr, sVar, rVar, 0);
        f7042c = qVar;
        q qVar2 = new q("sRGB IEC61966-2.1 (Linear)", fArr, sVar, 1.0d, 0.0f, 1.0f, 1);
        f7043d = qVar2;
        q qVar3 = new q("scRGB-nl IEC 61966-2-2:2003", fArr, sVar, null, new f0(10), new f0(11), -0.799f, 2.399f, rVar, 2);
        f7044e = qVar3;
        q qVar4 = new q("scRGB IEC 61966-2-2:2003", fArr, sVar, 1.0d, -0.5f, 7.499f, 3);
        f7045f = qVar4;
        q qVar5 = new q("Rec. ITU-R BT.709-5", new float[]{0.64f, 0.33f, 0.3f, 0.6f, 0.15f, 0.06f}, sVar, new r(2.2222222222222223d, 0.9099181073703367d, 0.09008189262966333d, 0.2222222222222222d, 0.081d), 4);
        f7046g = qVar5;
        q qVar6 = new q("Rec. ITU-R BT.2020-1", new float[]{0.708f, 0.292f, 0.17f, 0.797f, 0.131f, 0.046f}, sVar, new r(2.2222222222222223d, 0.9096697898662786d, 0.09033021013372146d, 0.2222222222222222d, 0.08145d), 5);
        f7047h = qVar6;
        q qVar7 = new q("SMPTE RP 431-2-2007 DCI (P3)", new float[]{0.68f, 0.32f, 0.265f, 0.69f, 0.15f, 0.06f}, new s(0.314f, 0.351f), 2.6d, 0.0f, 1.0f, 6);
        f7048i = qVar7;
        q qVar8 = new q("Display P3", new float[]{0.68f, 0.32f, 0.265f, 0.69f, 0.15f, 0.06f}, sVar, rVar, 7);
        f7049j = qVar8;
        q qVar9 = new q("NTSC (1953)", fArr2, j.f7069a, new r(2.2222222222222223d, 0.9099181073703367d, 0.09008189262966333d, 0.2222222222222222d, 0.081d), 8);
        f7050k = qVar9;
        q qVar10 = new q("SMPTE-C RGB", new float[]{0.63f, 0.34f, 0.31f, 0.595f, 0.155f, 0.07f}, sVar, new r(2.2222222222222223d, 0.9099181073703367d, 0.09008189262966333d, 0.2222222222222222d, 0.081d), 9);
        f7051l = qVar10;
        q qVar11 = new q("Adobe RGB (1998)", new float[]{0.64f, 0.33f, 0.21f, 0.71f, 0.15f, 0.06f}, sVar, 2.2d, 0.0f, 1.0f, 10);
        f7052m = qVar11;
        q qVar12 = new q("ROMM RGB ISO 22028-2:2013", new float[]{0.7347f, 0.2653f, 0.1596f, 0.8404f, 0.0366f, 1.0E-4f}, j.f7070b, new r(1.8d, 1.0d, 0.0d, 0.0625d, 0.031248d), 11);
        f7053n = qVar12;
        s sVar2 = j.f7071c;
        q qVar13 = new q("SMPTE ST 2065-1:2012 ACES", new float[]{0.7347f, 0.2653f, 0.0f, 1.0f, 1.0E-4f, -0.077f}, sVar2, 1.0d, -65504.0f, 65504.0f, 12);
        f7054o = qVar13;
        q qVar14 = new q("Academy S-2014-004 ACEScg", new float[]{0.713f, 0.293f, 0.165f, 0.83f, 0.128f, 0.044f}, sVar2, 1.0d, -65504.0f, 65504.0f, 13);
        f7055p = qVar14;
        k kVar = new k(14, 1, b.f7033b, "Generic XYZ");
        f7056q = kVar;
        long j5 = b.f7034c;
        k kVar2 = new k(15, 0, j5, "Generic L*a*b*");
        f7057r = kVar2;
        q qVar15 = new q("None", fArr, sVar, rVar2, 16);
        f7058s = qVar15;
        l lVar = new l("Oklab", j5, 17);
        f7059t = lVar;
        f7060u = new c[]{qVar, qVar2, qVar3, qVar4, qVar5, qVar6, qVar7, qVar8, qVar9, qVar10, qVar11, qVar12, qVar13, qVar14, kVar, kVar2, qVar15, lVar};
    }
}
