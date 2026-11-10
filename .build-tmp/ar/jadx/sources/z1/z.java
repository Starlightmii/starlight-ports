package z1;

import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final x f7167a = new x();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final long f7168b;

    static {
        long j5 = 0;
        f7168b = (j5 & 4294967295L) | (j5 << 32);
    }

    public static final TextDirectionHeuristic a(int i5) {
        if (i5 == 0) {
            return TextDirectionHeuristics.LTR;
        }
        if (i5 == 1) {
            return TextDirectionHeuristics.RTL;
        }
        if (i5 == 2) {
            return TextDirectionHeuristics.FIRSTSTRONG_LTR;
        }
        if (i5 == 3) {
            return TextDirectionHeuristics.FIRSTSTRONG_RTL;
        }
        if (i5 != 4) {
            return i5 != 5 ? TextDirectionHeuristics.FIRSTSTRONG_LTR : TextDirectionHeuristics.LOCALE;
        }
        return TextDirectionHeuristics.ANYRTL_LTR;
    }
}
