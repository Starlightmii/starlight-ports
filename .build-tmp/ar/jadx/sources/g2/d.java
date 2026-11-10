package g2;

import android.text.Layout;
import android.text.TextPaint;
import f0.g3;
import f0.w;
import java.text.BreakIterator;
import java.util.List;
import java.util.PriorityQueue;
import y1.h0;
import y1.q;
import y1.v;
import z1.n;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class d implements q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f2516a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final h0 f2517b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final List f2518c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List f2519d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d2.d f2520e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final k2.b f2521f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final f f2522g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final CharSequence f2523h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final n f2524i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public a1.b f2525j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f2526k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f2527l;

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 24371. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
        */
    public d(java.lang.String r42, y1.h0 r43, java.util.List r44, java.util.List r45, d2.d r46, k2.b r47) {
        /*
            Method dump skipped, instruction units count: 2437
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: g2.d.<init>(java.lang.String, y1.h0, java.util.List, java.util.List, d2.d, k2.b):void");
    }

    @Override // y1.q
    public final float a() {
        n nVar = this.f2524i;
        if (!Float.isNaN(nVar.f7129e)) {
            return nVar.f7129e;
        }
        CharSequence charSequence = nVar.f7125a;
        TextPaint textPaint = nVar.f7126b;
        BreakIterator lineInstance = BreakIterator.getLineInstance(textPaint.getTextLocale());
        lineInstance.setText(new z1.k(charSequence, charSequence.length()));
        PriorityQueue<p3.f> priorityQueue = new PriorityQueue(10, new w(4));
        int i5 = 0;
        for (int next = lineInstance.next(); next != -1; next = lineInstance.next()) {
            if (priorityQueue.size() < 10) {
                priorityQueue.add(new p3.f(Integer.valueOf(i5), Integer.valueOf(next)));
            } else {
                p3.f fVar = (p3.f) priorityQueue.peek();
                if (fVar != null && ((Number) fVar.f5015e).intValue() - ((Number) fVar.f5014d).intValue() < next - i5) {
                    priorityQueue.poll();
                    priorityQueue.add(new p3.f(Integer.valueOf(i5), Integer.valueOf(next)));
                }
            }
            i5 = next;
        }
        float fMax = 0.0f;
        for (p3.f fVar2 : priorityQueue) {
            fMax = Math.max(fMax, Layout.getDesiredWidth(charSequence, ((Number) fVar2.f5014d).intValue(), ((Number) fVar2.f5015e).intValue(), textPaint));
        }
        nVar.f7129e = fMax;
        return fMax;
    }

    @Override // y1.q
    public final boolean b() {
        a1.b bVar = this.f2525j;
        if (bVar != null ? bVar.v() : false) {
            return true;
        }
        if (!this.f2526k) {
            v vVar = this.f2517b.f6782c;
            a1.d dVar = j.f2543a;
            a1.d dVar2 = j.f2543a;
            g3 g3VarQ = (g3) dVar2.f31e;
            if (g3VarQ == null) {
                if (b3.j.c()) {
                    g3VarQ = dVar2.q();
                    dVar2.f31e = g3VarQ;
                } else {
                    g3VarQ = k.f2544a;
                }
            }
            if (((Boolean) g3VarQ.getValue()).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @Override // y1.q
    public final float c() {
        return this.f2524i.b();
    }
}
