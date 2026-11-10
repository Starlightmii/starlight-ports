package j1;

import android.os.Build;
import android.util.SparseBooleanArray;
import android.util.SparseLongArray;
import android.view.MotionEvent;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f3241a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final SparseLongArray f3242b = new SparseLongArray();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final SparseBooleanArray f3243c = new SparseBooleanArray();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayList f3244d = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3245e = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3246f = -1;

    /* JADX WARN: Code duplicated, block: B:19:0x0044  */
    /* JADX WARN: Code duplicated, block: B:72:0x013d  */
    /* JADX WARN: Code duplicated, block: B:74:0x0140  */
    /* JADX WARN: Code duplicated, block: B:76:0x0143 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:77:0x0145 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:78:0x0147  */
    /* JADX WARN: Code duplicated, block: B:79:0x014a  */
    /* JADX WARN: Code duplicated, block: B:80:0x014d  */
    /* JADX WARN: Code duplicated, block: B:81:0x0150  */
    /* JADX WARN: Code duplicated, block: B:82:0x0153  */
    /* JADX WARN: Code duplicated, block: B:85:0x0165  */
    /* JADX WARN: Code duplicated, block: B:97:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:99:0x01ba  */
    public final a2.h a(MotionEvent motionEvent, q1.u uVar) {
        long j5;
        int i5;
        int i6;
        long jValueAt;
        long j6;
        long jQ;
        long jA;
        long jC;
        int toolType;
        int i7;
        int historySize;
        int i8;
        long jB;
        float historicalX;
        int actionMasked = motionEvent.getActionMasked();
        SparseLongArray sparseLongArray = this.f3242b;
        SparseBooleanArray sparseBooleanArray = this.f3243c;
        int i9 = 3;
        if (actionMasked == 3 || actionMasked == 4) {
            sparseLongArray.clear();
            sparseBooleanArray.clear();
            return null;
        }
        if (motionEvent.getPointerCount() == 1) {
            int toolType2 = motionEvent.getToolType(0);
            int source = motionEvent.getSource();
            if (toolType2 != this.f3245e || source != this.f3246f) {
                this.f3245e = toolType2;
                this.f3246f = source;
                sparseBooleanArray.clear();
                sparseLongArray.clear();
            }
        }
        int actionMasked2 = motionEvent.getActionMasked();
        if (actionMasked2 == 0 || actionMasked2 == 5) {
            j5 = 1;
            int actionIndex = motionEvent.getActionIndex();
            int pointerId = motionEvent.getPointerId(actionIndex);
            if (sparseLongArray.indexOfKey(pointerId) < 0) {
                long j7 = this.f3241a;
                this.f3241a = j7 + 1;
                sparseLongArray.put(pointerId, j7);
                if (motionEvent.getToolType(actionIndex) == 3) {
                    sparseBooleanArray.put(pointerId, true);
                }
            }
        } else if (actionMasked2 != 9) {
            j5 = 1;
        } else {
            int pointerId2 = motionEvent.getPointerId(0);
            if (sparseLongArray.indexOfKey(pointerId2) < 0) {
                long j8 = this.f3241a;
                j5 = 1;
                this.f3241a = j8 + 1;
                sparseLongArray.put(pointerId2, j8);
            } else {
                j5 = 1;
            }
        }
        boolean z2 = actionMasked == 9 || actionMasked == 7 || actionMasked == 10;
        boolean z3 = actionMasked == 8;
        if (z2) {
            i5 = 1;
            sparseBooleanArray.put(motionEvent.getPointerId(motionEvent.getActionIndex()), true);
        } else {
            i5 = 1;
        }
        int actionIndex2 = actionMasked != i5 ? actionMasked != 6 ? -1 : motionEvent.getActionIndex() : 0;
        ArrayList arrayList = this.f3244d;
        arrayList.clear();
        int pointerCount = motionEvent.getPointerCount();
        int i10 = 0;
        while (i10 < pointerCount) {
            boolean z4 = (z2 || i10 == actionIndex2 || (z3 && motionEvent.getButtonState() == 0)) ? false : true;
            int pointerId3 = motionEvent.getPointerId(i10);
            int iIndexOfKey = sparseLongArray.indexOfKey(pointerId3);
            if (iIndexOfKey >= 0) {
                jValueAt = sparseLongArray.valueAt(iIndexOfKey);
            } else {
                long j9 = this.f3241a;
                this.f3241a = j9 + j5;
                sparseLongArray.put(pointerId3, j9);
                jValueAt = j9;
            }
            float pressure = motionEvent.getPressure(i10);
            long jB2 = p3.j.b(motionEvent.getX(i10), motionEvent.getY(i10));
            long jA2 = x0.c.a(jB2, 0.0f, i9);
            if (i10 == 0) {
                jA = p3.j.b(motionEvent.getRawX(), motionEvent.getRawY());
                jC = uVar.C(jA);
            } else {
                if (Build.VERSION.SDK_INT >= 29) {
                    jA = f.f3247a.a(motionEvent, i10);
                    jC = uVar.C(jA);
                } else {
                    j6 = jB2;
                    jQ = uVar.q(jB2);
                }
                toolType = motionEvent.getToolType(i10);
                if (toolType == 0) {
                    i7 = 0;
                } else if (toolType != 1) {
                    i7 = 1;
                } else if (toolType != 2) {
                    i7 = i9;
                } else if (toolType != i9) {
                    i7 = 2;
                } else if (toolType != 4) {
                    i7 = 0;
                } else {
                    i7 = 4;
                }
                ArrayList arrayList2 = new ArrayList(motionEvent.getHistorySize());
                historySize = motionEvent.getHistorySize();
                for (i8 = 0; i8 < historySize; i8++) {
                    historicalX = motionEvent.getHistoricalX(i10, i8);
                    float historicalY = motionEvent.getHistoricalY(i10, i8);
                    if (Float.isInfinite(historicalX) && !Float.isNaN(historicalX) && !Float.isInfinite(historicalY) && !Float.isNaN(historicalY)) {
                        long jB3 = p3.j.b(historicalX, historicalY);
                        arrayList2.add(new d(motionEvent.getHistoricalEventTime(i8), jB3, jB3));
                    }
                }
                if (motionEvent.getActionMasked() == 8) {
                    jB = p3.j.b(motionEvent.getAxisValue(10), (-motionEvent.getAxisValue(9)) + 0.0f);
                } else {
                    jB = 0;
                }
                arrayList.add(new w(jValueAt, motionEvent.getEventTime(), jQ, j6, z4, pressure, i7, sparseBooleanArray.get(motionEvent.getPointerId(i10), false), arrayList2, jB, jA2));
                i10++;
                z3 = z3;
                z2 = z2;
                i9 = 3;
            }
            jQ = jA;
            j6 = jC;
            toolType = motionEvent.getToolType(i10);
            if (toolType == 0) {
                i7 = 0;
            } else if (toolType != 1) {
                i7 = 1;
            } else if (toolType != 2) {
                i7 = i9;
            } else if (toolType != i9) {
                i7 = 2;
            } else if (toolType != 4) {
                i7 = 0;
            } else {
                i7 = 4;
            }
            ArrayList arrayList3 = new ArrayList(motionEvent.getHistorySize());
            historySize = motionEvent.getHistorySize();
            while (i8 < historySize) {
                historicalX = motionEvent.getHistoricalX(i10, i8);
                float historicalY2 = motionEvent.getHistoricalY(i10, i8);
                if (Float.isInfinite(historicalX)) {
                }
            }
            if (motionEvent.getActionMasked() == 8) {
                jB = p3.j.b(motionEvent.getAxisValue(10), (-motionEvent.getAxisValue(9)) + 0.0f);
            } else {
                jB = 0;
            }
            arrayList.add(new w(jValueAt, motionEvent.getEventTime(), jQ, j6, z4, pressure, i7, sparseBooleanArray.get(motionEvent.getPointerId(i10), false), arrayList3, jB, jA2));
            i10++;
            z3 = z3;
            z2 = z2;
            i9 = 3;
        }
        int actionMasked3 = motionEvent.getActionMasked();
        if (actionMasked3 == 1 || actionMasked3 == 6) {
            int pointerId4 = motionEvent.getPointerId(motionEvent.getActionIndex());
            i6 = 0;
            if (!sparseBooleanArray.get(pointerId4, false)) {
                sparseLongArray.delete(pointerId4);
                sparseBooleanArray.delete(pointerId4);
            }
        } else {
            i6 = 0;
        }
        if (sparseLongArray.size() > motionEvent.getPointerCount()) {
            for (int size = sparseLongArray.size() - 1; -1 < size; size--) {
                int iKeyAt = sparseLongArray.keyAt(size);
                int pointerCount2 = motionEvent.getPointerCount();
                int i11 = i6;
                while (true) {
                    if (i11 >= pointerCount2) {
                        sparseLongArray.removeAt(size);
                        sparseBooleanArray.delete(iKeyAt);
                        break;
                    }
                    if (motionEvent.getPointerId(i11) == iKeyAt) {
                        break;
                    }
                    i11++;
                }
            }
        }
        motionEvent.getEventTime();
        return new a2.h(arrayList, 12, motionEvent);
    }
}
