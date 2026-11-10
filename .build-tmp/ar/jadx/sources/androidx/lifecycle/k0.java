package androidx.lifecycle;

import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Typeface;
import android.util.Log;
import io.github.libxposed.service.IXposedService;
import java.util.Arrays;
import m.d1;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public class k0 implements d2.o, i3.e, d1, t3.g {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static k0 f260e;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f261d;

    public k0() {
        this.f261d = 12;
        new b3.s();
    }

    public /* synthetic */ k0(int i5) {
        this.f261d = i5;
    }

    public static final float c(float f5, float[] fArr, float[] fArr2) {
        float f6;
        float f7;
        float f8;
        float f9;
        float fAbs = Math.abs(f5);
        float fSignum = Math.signum(f5);
        int iBinarySearch = Arrays.binarySearch(fArr, fAbs);
        if (iBinarySearch >= 0) {
            return fSignum * fArr2[iBinarySearch];
        }
        int i5 = -(iBinarySearch + 1);
        int i6 = i5 - 1;
        if (i6 >= fArr.length - 1) {
            float f10 = fArr[fArr.length - 1];
            float f11 = fArr2[fArr.length - 1];
            if (f10 == 0.0f) {
                return 0.0f;
            }
            return (f11 / f10) * f5;
        }
        if (i6 == -1) {
            float f12 = fArr[0];
            f8 = fArr2[0];
            f9 = f12;
            f7 = 0.0f;
            f6 = 0.0f;
        } else {
            float f13 = fArr[i6];
            float f14 = fArr[i5];
            f6 = fArr2[i6];
            f7 = f13;
            f8 = fArr2[i5];
            f9 = f14;
        }
        return (((f8 - f6) * Math.max(0.0f, Math.min(1.0f, f7 == f9 ? 0.0f : (fAbs - f7) / (f9 - f7)))) + f6) * fSignum;
    }

    public static Typeface e(String str, d2.k kVar, int i5) {
        if (i5 == 0 && b4.l.a(kVar, d2.k.f1613f) && (str == null || str.length() == 0)) {
            return Typeface.DEFAULT;
        }
        return Typeface.create(str == null ? Typeface.DEFAULT : Typeface.create(str, 0), kVar.f1615d, i5 == 1);
    }

    public static Typeface g(String str, d2.k kVar, int i5) {
        if (i5 == 0 && b4.l.a(kVar, d2.k.f1613f) && (str == null || str.length() == 0)) {
            return Typeface.DEFAULT;
        }
        int iU = a.a.u(kVar, i5);
        return (str == null || str.length() == 0) ? Typeface.defaultFromStyle(iU) : Typeface.create(str, iU);
    }

    private final void p() {
    }

    private final void q(int i5, Object obj) {
    }

    @Override // m.c1
    public m.q d(long j5, m.q qVar, m.q qVar2, m.q qVar3) {
        return qVar3;
    }

    @Override // m.c1
    public m.q f(long j5, m.q qVar, m.q qVar2, m.q qVar3) {
        return j5 < ((long) 0) * 1000000 ? qVar : qVar2;
    }

    @Override // i3.e
    public void h() {
        switch (this.f261d) {
            case 19:
                break;
            default:
                Log.d("ProfileInstaller", "DIAGNOSTIC_PROFILE_IS_COMPRESSED");
                break;
        }
    }

    @Override // i3.e
    public void i(int i5, Object obj) {
        String str;
        switch (this.f261d) {
            case 19:
                break;
            default:
                switch (i5) {
                    case 1:
                        str = "RESULT_INSTALL_SUCCESS";
                        break;
                    case 2:
                        str = "RESULT_ALREADY_INSTALLED";
                        break;
                    case 3:
                        str = "RESULT_UNSUPPORTED_ART_VERSION";
                        break;
                    case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
                        str = "RESULT_NOT_WRITABLE";
                        break;
                    case 5:
                        str = "RESULT_DESIRED_FORMAT_UNSUPPORTED";
                        break;
                    case 6:
                        str = "RESULT_BASELINE_PROFILE_NOT_FOUND";
                        break;
                    case 7:
                        str = "RESULT_IO_EXCEPTION";
                        break;
                    case 8:
                        str = "RESULT_PARSE_EXCEPTION";
                        break;
                    case 9:
                    default:
                        str = "";
                        break;
                    case 10:
                        str = "RESULT_INSTALL_SKIP_FILE_SUCCESS";
                        break;
                    case 11:
                        str = "RESULT_DELETE_SKIP_FILE_SUCCESS";
                        break;
                }
                if (i5 == 6 || i5 == 7 || i5 == 8) {
                    Log.e("ProfileInstaller", str, (Throwable) obj);
                } else {
                    Log.d("ProfileInstaller", str);
                }
                break;
        }
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0020  */
    public Typeface j(d2.m mVar, d2.k kVar, int i5) {
        String str;
        switch (this.f261d) {
            case 14:
                mVar.getClass();
                return e("sans-serif", kVar, i5);
            default:
                mVar.getClass();
                int i6 = kVar.f1615d / 100;
                if (i6 >= 0 && i6 < 2) {
                    str = "sans-serif-thin";
                } else if (2 <= i6 && i6 < 4) {
                    str = "sans-serif-light";
                } else if (i6 == 4) {
                    str = "sans-serif";
                } else if (i6 == 5) {
                    str = "sans-serif-medium";
                } else if ((6 > i6 || i6 >= 8) && 8 <= i6 && i6 < 11) {
                    str = "sans-serif-black";
                } else {
                    str = "sans-serif";
                }
                Typeface typeface = null;
                if (str.length() != 0) {
                    Typeface typefaceG = g(str, kVar, i5);
                    if (!b4.l.a(typefaceG, Typeface.create(Typeface.DEFAULT, a.a.u(kVar, i5))) && !b4.l.a(typefaceG, g(null, kVar, i5))) {
                        typeface = typefaceG;
                    }
                }
                return typeface == null ? g("sans-serif", kVar, i5) : typeface;
        }
    }

    public Signature[] k(PackageManager packageManager, String str) {
        return packageManager.getPackageInfo(str, 64).signatures;
    }

    @Override // m.d1
    public int l() {
        return 0;
    }

    public boolean m(CharSequence charSequence) {
        return false;
    }

    @Override // m.d1
    public int n() {
        return 0;
    }

    public String toString() {
        switch (this.f261d) {
            case 29:
                return "CompositionErrorContext";
            default:
                return super.toString();
        }
    }
}
