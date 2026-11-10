package b;

import android.content.res.TypedArray;
import android.media.MediaDrm;
import android.media.MediaMetadataRetriever;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class b {
    public static int a(float f5, int i5, int i6) {
        return (Float.hashCode(f5) + i5) * i6;
    }

    public static int b(int i5, int i6, long j5) {
        return (Long.hashCode(j5) + i5) * i6;
    }

    public static int c(int i5, int i6, boolean z2) {
        return (Boolean.hashCode(z2) + i5) * i6;
    }

    public static String d(String str, int i5, String str2, int i6) {
        return str + i5 + str2 + i6;
    }

    public static String e(StringBuilder sb, float f5, char c5) {
        sb.append(f5);
        sb.append(c5);
        return sb.toString();
    }

    public static String f(StringBuilder sb, int i5, char c5) {
        sb.append(i5);
        sb.append(c5);
        return sb.toString();
    }

    public static StringBuilder g(String str, int i5, String str2) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(i5);
        sb.append(str2);
        return sb;
    }

    public static void h(int i5, int i6, int i7, int i8, int i9) {
        h1.c.e(i5);
        h1.c.e(i6);
        h1.c.e(i7);
        h1.c.e(i8);
        h1.c.e(i9);
    }

    public static void i(int i5, f0.v vVar, int i6, p1.h hVar) {
        vVar.f0(Integer.valueOf(i5));
        vVar.b(hVar, Integer.valueOf(i6));
    }

    public static void j(long j5, StringBuilder sb, String str) {
        sb.append((Object) y0.u.i(j5));
        sb.append(str);
    }

    public static /* synthetic */ void k(AutoCloseable autoCloseable) throws Exception {
        boolean zIsTerminated;
        if (autoCloseable instanceof AutoCloseable) {
            autoCloseable.close();
            return;
        }
        if (!(autoCloseable instanceof ExecutorService)) {
            if (autoCloseable instanceof TypedArray) {
                ((TypedArray) autoCloseable).recycle();
                return;
            } else if (autoCloseable instanceof MediaMetadataRetriever) {
                ((MediaMetadataRetriever) autoCloseable).release();
                return;
            } else {
                if (!(autoCloseable instanceof MediaDrm)) {
                    throw new IllegalArgumentException();
                }
                ((MediaDrm) autoCloseable).release();
                return;
            }
        }
        ExecutorService executorService = (ExecutorService) autoCloseable;
        if (executorService == ForkJoinPool.commonPool() || (zIsTerminated = executorService.isTerminated())) {
            return;
        }
        executorService.shutdown();
        boolean z2 = false;
        while (!zIsTerminated) {
            try {
                zIsTerminated = executorService.awaitTermination(1L, TimeUnit.DAYS);
            } catch (InterruptedException unused) {
                if (!z2) {
                    executorService.shutdownNow();
                    z2 = true;
                }
            }
        }
        if (z2) {
            Thread.currentThread().interrupt();
        }
    }

    public static /* synthetic */ void l(Object obj) {
        if (obj != null) {
            throw new ClassCastException();
        }
    }

    public static /* synthetic */ String m(int i5) {
        if (i5 == 1) {
            return "Measuring";
        }
        if (i5 == 2) {
            return "LookaheadMeasuring";
        }
        if (i5 == 3) {
            return "LayingOut";
        }
        if (i5 != 4) {
            return i5 != 5 ? "null" : "Idle";
        }
        return "LookaheadLayingOut";
    }
}
