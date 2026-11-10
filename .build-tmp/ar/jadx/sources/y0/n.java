package y0;

import android.graphics.BlendModeColorFilter;
import io.github.libxposed.service.IXposedService;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final n f6649a = new n();

    public final BlendModeColorFilter a(long j5, int i5) {
        b.f();
        return b.e(l0.w(j5), l0.s(i5));
    }

    public final m b(BlendModeColorFilter blendModeColorFilter) {
        int i5;
        long jC = l0.c(blendModeColorFilter.getColor());
        switch (c.f6616a[blendModeColorFilter.getMode().ordinal()]) {
            case 1:
                i5 = 0;
                break;
            case 2:
                i5 = 1;
                break;
            case 3:
                i5 = 2;
                break;
            case IXposedService.HOT_RELOAD_PROCESS_DIED /* 4 */:
            default:
                i5 = 3;
                break;
            case 5:
                i5 = 4;
                break;
            case 6:
                i5 = 5;
                break;
            case 7:
                i5 = 6;
                break;
            case 8:
                i5 = 7;
                break;
            case 9:
                i5 = 8;
                break;
            case 10:
                i5 = 9;
                break;
            case 11:
                i5 = 10;
                break;
            case 12:
                i5 = 11;
                break;
            case 13:
                i5 = 12;
                break;
            case 14:
                i5 = 13;
                break;
            case 15:
                i5 = 14;
                break;
            case 16:
                i5 = 15;
                break;
            case 17:
                i5 = 16;
                break;
            case 18:
                i5 = 17;
                break;
            case 19:
                i5 = 18;
                break;
            case 20:
                i5 = 19;
                break;
            case 21:
                i5 = 20;
                break;
            case 22:
                i5 = 21;
                break;
            case 23:
                i5 = 22;
                break;
            case 24:
                i5 = 23;
                break;
            case 25:
                i5 = 24;
                break;
            case 26:
                i5 = 25;
                break;
            case 27:
                i5 = 26;
                break;
            case 28:
                i5 = 27;
                break;
            case 29:
                i5 = 28;
                break;
        }
        return new m(jC, i5, blendModeColorFilter);
    }
}
