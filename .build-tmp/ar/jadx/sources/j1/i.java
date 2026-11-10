package j1;

import android.view.MotionEvent;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f3257a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f3258b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3259c;

    /* JADX WARN: Code duplicated, block: B:29:0x0052  */
    /* JADX WARN: Code duplicated, block: B:30:0x0054  */
    /* JADX WARN: Code duplicated, block: B:31:0x0056  */
    public i(List list, a2.h hVar) {
        this.f3257a = list;
        MotionEvent motionEvent = hVar != null ? (MotionEvent) ((a2.h) hVar.f50f).f50f : null;
        int i5 = 0;
        this.f3258b = motionEvent != null ? motionEvent.getButtonState() : 0;
        MotionEvent motionEvent2 = hVar != null ? (MotionEvent) ((a2.h) hVar.f50f).f50f : null;
        if (motionEvent2 != null) {
            motionEvent2.getMetaState();
        }
        MotionEvent motionEvent3 = hVar != null ? (MotionEvent) ((a2.h) hVar.f50f).f50f : null;
        if (motionEvent3 != null) {
            int actionMasked = motionEvent3.getActionMasked();
            if (actionMasked == 0) {
                i5 = 1;
            } else if (actionMasked == 1) {
                i5 = 2;
            } else if (actionMasked != 2) {
                switch (actionMasked) {
                    case 5:
                        i5 = 1;
                        break;
                    case 6:
                        i5 = 2;
                        break;
                    case 7:
                        i5 = 3;
                        break;
                    case 8:
                        i5 = 6;
                        break;
                    case 9:
                        i5 = 4;
                        break;
                    case 10:
                        i5 = 5;
                        break;
                }
            } else {
                i5 = 3;
            }
        } else {
            int size = list.size();
            while (true) {
                if (i5 < size) {
                    u uVar = (u) list.get(i5);
                    if (s.c(uVar)) {
                        i5 = 2;
                    } else if (s.a(uVar)) {
                        i5 = 1;
                    } else {
                        i5++;
                    }
                } else {
                    i5 = 3;
                }
            }
        }
        this.f3259c = i5;
    }
}
