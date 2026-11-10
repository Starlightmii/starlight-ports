package y0;

import android.graphics.ColorSpace;
import java.util.function.DoubleUnaryOperator;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class y {
    public static final ColorSpace a(z0.c cVar) {
        if (b4.l.a(cVar, z0.d.f7042c)) {
            return ColorSpace.get(ColorSpace.Named.SRGB);
        }
        if (b4.l.a(cVar, z0.d.f7054o)) {
            return ColorSpace.get(ColorSpace.Named.ACES);
        }
        if (b4.l.a(cVar, z0.d.f7055p)) {
            return ColorSpace.get(ColorSpace.Named.ACESCG);
        }
        if (b4.l.a(cVar, z0.d.f7052m)) {
            return ColorSpace.get(ColorSpace.Named.ADOBE_RGB);
        }
        if (b4.l.a(cVar, z0.d.f7047h)) {
            return ColorSpace.get(ColorSpace.Named.BT2020);
        }
        if (b4.l.a(cVar, z0.d.f7046g)) {
            return ColorSpace.get(ColorSpace.Named.BT709);
        }
        if (b4.l.a(cVar, z0.d.f7057r)) {
            return ColorSpace.get(ColorSpace.Named.CIE_LAB);
        }
        if (b4.l.a(cVar, z0.d.f7056q)) {
            return ColorSpace.get(ColorSpace.Named.CIE_XYZ);
        }
        if (b4.l.a(cVar, z0.d.f7048i)) {
            return ColorSpace.get(ColorSpace.Named.DCI_P3);
        }
        if (b4.l.a(cVar, z0.d.f7049j)) {
            return ColorSpace.get(ColorSpace.Named.DISPLAY_P3);
        }
        if (b4.l.a(cVar, z0.d.f7044e)) {
            return ColorSpace.get(ColorSpace.Named.EXTENDED_SRGB);
        }
        if (b4.l.a(cVar, z0.d.f7045f)) {
            return ColorSpace.get(ColorSpace.Named.LINEAR_EXTENDED_SRGB);
        }
        if (b4.l.a(cVar, z0.d.f7043d)) {
            return ColorSpace.get(ColorSpace.Named.LINEAR_SRGB);
        }
        if (b4.l.a(cVar, z0.d.f7050k)) {
            return ColorSpace.get(ColorSpace.Named.NTSC_1953);
        }
        if (b4.l.a(cVar, z0.d.f7053n)) {
            return ColorSpace.get(ColorSpace.Named.PRO_PHOTO_RGB);
        }
        if (b4.l.a(cVar, z0.d.f7051l)) {
            return ColorSpace.get(ColorSpace.Named.SMPTE_C);
        }
        if (!(cVar instanceof z0.q)) {
            return ColorSpace.get(ColorSpace.Named.SRGB);
        }
        z0.q qVar = (z0.q) cVar;
        float[] fArrA = qVar.f7088d.a();
        z0.r rVar = qVar.f7091g;
        ColorSpace.Rgb.TransferParameters transferParameters = rVar != null ? new ColorSpace.Rgb.TransferParameters(rVar.f7103b, rVar.f7104c, rVar.f7105d, rVar.f7106e, rVar.f7107f, rVar.f7108g, rVar.f7102a) : null;
        if (transferParameters != null) {
            return new ColorSpace.Rgb(cVar.f7037a, qVar.f7092h, fArrA, transferParameters);
        }
        String str = cVar.f7037a;
        float[] fArr = qVar.f7092h;
        final z0.p pVar = qVar.f7096l;
        final int i5 = 0;
        DoubleUnaryOperator doubleUnaryOperator = new DoubleUnaryOperator() { // from class: y0.w
            @Override // java.util.function.DoubleUnaryOperator
            public final double applyAsDouble(double d5) {
                switch (i5) {
                    case 0:
                        break;
                }
                return ((Number) pVar.c(Double.valueOf(d5))).doubleValue();
            }
        };
        final z0.p pVar2 = qVar.f7099o;
        final int i6 = 1;
        return new ColorSpace.Rgb(str, fArr, fArrA, doubleUnaryOperator, new DoubleUnaryOperator() { // from class: y0.w
            @Override // java.util.function.DoubleUnaryOperator
            public final double applyAsDouble(double d5) {
                switch (i6) {
                    case 0:
                        break;
                }
                return ((Number) pVar2.c(Double.valueOf(d5))).doubleValue();
            }
        }, qVar.f7089e, qVar.f7090f);
    }

    public static final z0.c b(ColorSpace colorSpace) {
        z0.s sVar;
        int id = colorSpace.getId();
        if (id == ColorSpace.Named.SRGB.ordinal()) {
            return z0.d.f7042c;
        }
        if (id == ColorSpace.Named.ACES.ordinal()) {
            return z0.d.f7054o;
        }
        if (id == ColorSpace.Named.ACESCG.ordinal()) {
            return z0.d.f7055p;
        }
        if (id == ColorSpace.Named.ADOBE_RGB.ordinal()) {
            return z0.d.f7052m;
        }
        if (id == ColorSpace.Named.BT2020.ordinal()) {
            return z0.d.f7047h;
        }
        if (id == ColorSpace.Named.BT709.ordinal()) {
            return z0.d.f7046g;
        }
        if (id == ColorSpace.Named.CIE_LAB.ordinal()) {
            return z0.d.f7057r;
        }
        if (id == ColorSpace.Named.CIE_XYZ.ordinal()) {
            return z0.d.f7056q;
        }
        if (id == ColorSpace.Named.DCI_P3.ordinal()) {
            return z0.d.f7048i;
        }
        if (id == ColorSpace.Named.DISPLAY_P3.ordinal()) {
            return z0.d.f7049j;
        }
        if (id == ColorSpace.Named.EXTENDED_SRGB.ordinal()) {
            return z0.d.f7044e;
        }
        if (id == ColorSpace.Named.LINEAR_EXTENDED_SRGB.ordinal()) {
            return z0.d.f7045f;
        }
        if (id == ColorSpace.Named.LINEAR_SRGB.ordinal()) {
            return z0.d.f7043d;
        }
        if (id == ColorSpace.Named.NTSC_1953.ordinal()) {
            return z0.d.f7050k;
        }
        if (id == ColorSpace.Named.PRO_PHOTO_RGB.ordinal()) {
            return z0.d.f7053n;
        }
        if (id == ColorSpace.Named.SMPTE_C.ordinal()) {
            return z0.d.f7051l;
        }
        if (!(colorSpace instanceof ColorSpace.Rgb)) {
            return z0.d.f7042c;
        }
        ColorSpace.Rgb rgb = (ColorSpace.Rgb) colorSpace;
        ColorSpace.Rgb.TransferParameters transferParameters = rgb.getTransferParameters();
        if (rgb.getWhitePoint().length == 3) {
            float f5 = rgb.getWhitePoint()[0];
            float f6 = rgb.getWhitePoint()[1];
            float f7 = f5 + f6 + rgb.getWhitePoint()[2];
            sVar = new z0.s(f5 / f7, f6 / f7);
        } else {
            sVar = new z0.s(rgb.getWhitePoint()[0], rgb.getWhitePoint()[1]);
        }
        return new z0.q(rgb.getName(), rgb.getPrimaries(), sVar, rgb.getTransform(), new x(0, colorSpace), new x(1, colorSpace), colorSpace.getMinValue(0), colorSpace.getMaxValue(0), transferParameters != null ? new z0.r(transferParameters.g, transferParameters.a, transferParameters.b, transferParameters.c, transferParameters.d, transferParameters.e, transferParameters.f) : null, rgb.getId());
    }
}
