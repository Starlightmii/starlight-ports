package q1;

import android.content.ClipDescription;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Trace;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TtsSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import io.github.andrea_lyz.assistrestore.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class f0 extends w2.b {
    public static final j.q N;
    public j.r A;
    public final j.s B;
    public final j.p C;
    public final j.p D;
    public final String E;
    public final String F;
    public final a1.b G;
    public final j.r H;
    public i2 I;
    public boolean J;
    public final androidx.lifecycle.a0 K;
    public final ArrayList L;
    public final d0 M;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final u f5178d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5179e = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final d0 f5180f = new d0(this, 0);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final AccessibilityManager f5181g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f5182h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final v f5183i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final w f5184j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public List f5185k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Handler f5186l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final a2.h f5187m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f5188n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public x2.h f5189o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f5190p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final j.r f5191q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final j.r f5192r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final j.i0 f5193s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final j.i0 f5194t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f5195u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Integer f5196v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final j.g f5197w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final k4.c f5198x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f5199y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public b0 f5200z;

    static {
        int[] iArr = {R.id.accessibility_custom_action_0, R.id.accessibility_custom_action_1, R.id.accessibility_custom_action_2, R.id.accessibility_custom_action_3, R.id.accessibility_custom_action_4, R.id.accessibility_custom_action_5, R.id.accessibility_custom_action_6, R.id.accessibility_custom_action_7, R.id.accessibility_custom_action_8, R.id.accessibility_custom_action_9, R.id.accessibility_custom_action_10, R.id.accessibility_custom_action_11, R.id.accessibility_custom_action_12, R.id.accessibility_custom_action_13, R.id.accessibility_custom_action_14, R.id.accessibility_custom_action_15, R.id.accessibility_custom_action_16, R.id.accessibility_custom_action_17, R.id.accessibility_custom_action_18, R.id.accessibility_custom_action_19, R.id.accessibility_custom_action_20, R.id.accessibility_custom_action_21, R.id.accessibility_custom_action_22, R.id.accessibility_custom_action_23, R.id.accessibility_custom_action_24, R.id.accessibility_custom_action_25, R.id.accessibility_custom_action_26, R.id.accessibility_custom_action_27, R.id.accessibility_custom_action_28, R.id.accessibility_custom_action_29, R.id.accessibility_custom_action_30, R.id.accessibility_custom_action_31};
        int i5 = j.i.f3111a;
        j.q qVar = new j.q(32);
        int i6 = qVar.f3137b;
        if (i6 < 0) {
            k.a.d("");
            throw null;
        }
        int i7 = i6 + 32;
        qVar.b(i7);
        int[] iArr2 = qVar.f3136a;
        int i8 = qVar.f3137b;
        if (i6 != i8) {
            q3.k.y(iArr2, iArr2, i7, i6, i8);
        }
        q3.k.B(iArr, iArr2, i6, 0, 12);
        qVar.f3137b += 32;
        N = qVar;
    }

    /* JADX WARN: Type inference failed for: r2v4, types: [q1.v] */
    /* JADX WARN: Type inference failed for: r2v5, types: [q1.w] */
    public f0(u uVar) {
        this.f5178d = uVar;
        Object systemService = uVar.getContext().getSystemService("accessibility");
        b4.l.c("null cannot be cast to non-null type android.view.accessibility.AccessibilityManager", systemService);
        AccessibilityManager accessibilityManager = (AccessibilityManager) systemService;
        this.f5181g = accessibilityManager;
        this.f5182h = 100L;
        this.f5183i = new AccessibilityManager.AccessibilityStateChangeListener() { // from class: q1.v
            @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
            public final void onAccessibilityStateChanged(boolean z2) {
                f0 f0Var = this.f5423a;
                f0Var.f5185k = z2 ? f0Var.f5181g.getEnabledAccessibilityServiceList(-1) : q3.s.f5477d;
            }
        };
        this.f5184j = new AccessibilityManager.TouchExplorationStateChangeListener() { // from class: q1.w
            @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
            public final void onTouchExplorationStateChanged(boolean z2) {
                f0 f0Var = this.f5439a;
                f0Var.f5185k = f0Var.f5181g.getEnabledAccessibilityServiceList(-1);
            }
        };
        this.f5185k = accessibilityManager.getEnabledAccessibilityServiceList(-1);
        this.f5186l = new Handler(Looper.getMainLooper());
        this.f5187m = new a2.h(this);
        this.f5188n = Integer.MIN_VALUE;
        this.f5191q = new j.r();
        this.f5192r = new j.r();
        this.f5193s = new j.i0();
        this.f5194t = new j.i0();
        this.f5195u = -1;
        this.f5197w = new j.g();
        this.f5198x = k4.j.a(1, 6, null);
        this.f5199y = true;
        j.r rVar = j.j.f3115a;
        b4.l.c("null cannot be cast to non-null type androidx.collection.IntObjectMap<V of androidx.collection.IntObjectMapKt.intObjectMapOf>", rVar);
        this.A = rVar;
        this.B = new j.s();
        this.C = new j.p();
        this.D = new j.p();
        this.E = "android.view.accessibility.extra.EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL";
        this.F = "android.view.accessibility.extra.EXTRA_DATA_TEST_TRAVERSALAFTER_VAL";
        this.G = new a1.b(5);
        this.H = new j.r();
        w1.n nVarA = uVar.getSemanticsOwner().a();
        b4.l.c("null cannot be cast to non-null type androidx.collection.IntObjectMap<V of androidx.collection.IntObjectMapKt.intObjectMapOf>", rVar);
        this.I = new i2(nVarA, rVar);
        uVar.addOnAttachStateChangeListener(new x(0, this));
        this.K = new androidx.lifecycle.a0(6, this);
        this.L = new ArrayList();
        this.M = new d0(this, 1);
    }

    public static /* synthetic */ void B(f0 f0Var, int i5, int i6, Integer num, int i7) {
        if ((i7 & 4) != 0) {
            num = null;
        }
        f0Var.A(i5, i6, num, null);
    }

    public static CharSequence K(CharSequence charSequence) {
        if (charSequence.length() != 0) {
            int i5 = 100000;
            if (charSequence.length() > 100000) {
                if (Character.isHighSurrogate(charSequence.charAt(99999)) && Character.isLowSurrogate(charSequence.charAt(100000))) {
                    i5 = 99999;
                }
                CharSequence charSequenceSubSequence = charSequence.subSequence(0, i5);
                b4.l.c("null cannot be cast to non-null type T of androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.trimToSize", charSequenceSubSequence);
                return charSequenceSubSequence;
            }
        }
        return charSequence;
    }

    public static final x2.h b(f0 f0Var, int i5) {
        androidx.lifecycle.q lifecycle;
        u uVar = f0Var.f5178d;
        Trace.beginSection("checkIfDestroyed");
        try {
            m viewTreeOwners = uVar.getViewTreeOwners();
            if (((viewTreeOwners == null || (lifecycle = viewTreeOwners.f5259a.getLifecycle()) == null) ? null : ((androidx.lifecycle.x) lifecycle).f298c) == androidx.lifecycle.p.f274d) {
                Trace.endSection();
                return null;
            }
            Trace.endSection();
            Trace.beginSection("createAccessibilityNodeInfoObject");
            try {
                AccessibilityNodeInfo accessibilityNodeInfoObtain = AccessibilityNodeInfo.obtain();
                x2.h hVar = new x2.h(accessibilityNodeInfoObtain);
                Trace.endSection();
                Trace.beginSection("calculateNodeWithAdjustedBounds");
                try {
                    j2 j2Var = (j2) f0Var.m().d(i5);
                    Trace.endSection();
                    if (j2Var == null) {
                        return null;
                    }
                    w1.n nVar = j2Var.f5252a;
                    Trace.beginSection("setParentForAccessibility");
                    int i6 = -1;
                    try {
                        if (i5 == -1) {
                            Object parentForAccessibility = uVar.getParentForAccessibility();
                            View view = parentForAccessibility instanceof View ? (View) parentForAccessibility : null;
                            hVar.f6564b = -1;
                            accessibilityNodeInfoObtain.setParent(view);
                        } else {
                            w1.n nVarJ = nVar.j();
                            Integer numValueOf = nVarJ != null ? Integer.valueOf(nVarJ.f6321g) : null;
                            if (numValueOf == null) {
                                d4.a.M("semanticsNode " + i5 + " has null parent");
                                throw null;
                            }
                            int iIntValue = numValueOf.intValue();
                            if (iIntValue != uVar.getSemanticsOwner().a().f6321g) {
                                i6 = iIntValue;
                            }
                            hVar.f6564b = i6;
                            accessibilityNodeInfoObtain.setParent(uVar, i6);
                        }
                        Trace.endSection();
                        hVar.f6565c = i5;
                        accessibilityNodeInfoObtain.setSource(uVar, i5);
                        Trace.beginSection("setBoundsInScreen");
                        try {
                            accessibilityNodeInfoObtain.setBoundsInScreen(f0Var.d(j2Var));
                            Trace.endSection();
                            Trace.beginSection("populateAccessibilityNodeInfoProperties");
                            try {
                                f0Var.u(i5, hVar, nVar);
                                return hVar;
                            } finally {
                                Trace.endSection();
                            }
                        } catch (Throwable th) {
                            Trace.endSection();
                            throw th;
                        }
                    } catch (Throwable th2) {
                        Trace.endSection();
                        throw th2;
                    }
                } catch (Throwable th3) {
                    Trace.endSection();
                    throw th3;
                }
            } catch (Throwable th4) {
                Trace.endSection();
                throw th4;
            }
        } catch (Throwable th5) {
            Trace.endSection();
            throw th5;
        }
    }

    public static boolean n(w1.n nVar) {
        Object obj = nVar.f6318d.f6308d.get(w1.q.A);
        if (obj == null) {
            obj = null;
        }
        x1.a aVar = (x1.a) obj;
        LinkedHashMap linkedHashMap = nVar.f6318d.f6308d;
        Object obj2 = linkedHashMap.get(w1.q.f6353r);
        if (obj2 == null) {
            obj2 = null;
        }
        w1.f fVar = (w1.f) obj2;
        boolean z2 = aVar != null;
        Object obj3 = linkedHashMap.get(w1.q.f6361z);
        if (((Boolean) (obj3 != null ? obj3 : null)) == null || (fVar != null && fVar.f6279a == 4)) {
            return z2;
        }
        return true;
    }

    public static String p(w1.n nVar) {
        y1.f fVar;
        if (nVar != null) {
            w1.i iVar = nVar.f6318d;
            LinkedHashMap linkedHashMap = iVar.f6308d;
            w1.t tVar = w1.q.f6336a;
            if (linkedHashMap.containsKey(tVar)) {
                return h1.c.s((List) iVar.b(tVar), ",");
            }
            w1.t tVar2 = w1.q.f6358w;
            if (linkedHashMap.containsKey(tVar2)) {
                Object obj = linkedHashMap.get(tVar2);
                if (obj == null) {
                    obj = null;
                }
                y1.f fVar2 = (y1.f) obj;
                if (fVar2 != null) {
                    return fVar2.f6759d;
                }
            } else {
                Object obj2 = linkedHashMap.get(w1.q.f6355t);
                if (obj2 == null) {
                    obj2 = null;
                }
                List list = (List) obj2;
                if (list != null && (fVar = (y1.f) q3.l.X(list)) != null) {
                    return fVar.f6759d;
                }
            }
        }
        return null;
    }

    public static final boolean t(w1.g gVar, float f5) {
        n.d1 d1Var = gVar.f6280a;
        if (f5 >= 0.0f || ((Number) d1Var.a()).floatValue() <= 0.0f) {
            return f5 > 0.0f && ((Number) d1Var.a()).floatValue() < ((Number) gVar.f6281b.a()).floatValue();
        }
        return true;
    }

    public static final boolean v(w1.g gVar) {
        n.d1 d1Var = gVar.f6280a;
        if (((Number) d1Var.a()).floatValue() > 0.0f) {
            return true;
        }
        ((Number) d1Var.a()).floatValue();
        ((Number) gVar.f6281b.a()).floatValue();
        return false;
    }

    public static final boolean w(w1.g gVar) {
        n.d1 d1Var = gVar.f6280a;
        if (((Number) d1Var.a()).floatValue() < ((Number) gVar.f6281b.a()).floatValue()) {
            return true;
        }
        ((Number) d1Var.a()).floatValue();
        return false;
    }

    public final boolean A(int i5, int i6, Integer num, List list) {
        if (i5 == Integer.MIN_VALUE || !q()) {
            return false;
        }
        AccessibilityEvent accessibilityEventH = h(i5, i6);
        if (num != null) {
            accessibilityEventH.setContentChangeTypes(num.intValue());
        }
        if (list != null) {
            accessibilityEventH.setContentDescription(h1.c.s(list, ","));
        }
        Trace.beginSection("sendEvent");
        try {
            return z(accessibilityEventH);
        } finally {
            Trace.endSection();
        }
    }

    public final void C(int i5, int i6, String str) {
        AccessibilityEvent accessibilityEventH = h(x(i5), 32);
        accessibilityEventH.setContentChangeTypes(i6);
        if (str != null) {
            accessibilityEventH.getText().add(str);
        }
        z(accessibilityEventH);
    }

    public final void D(int i5) {
        b0 b0Var = this.f5200z;
        if (b0Var != null) {
            w1.n nVar = b0Var.f5118a;
            if (i5 != nVar.f6321g) {
                return;
            }
            if (SystemClock.uptimeMillis() - b0Var.f5123f <= 1000) {
                AccessibilityEvent accessibilityEventH = h(x(nVar.f6321g), 131072);
                accessibilityEventH.setFromIndex(b0Var.f5121d);
                accessibilityEventH.setToIndex(b0Var.f5122e);
                accessibilityEventH.setAction(b0Var.f5119b);
                accessibilityEventH.setMovementGranularity(b0Var.f5120c);
                accessibilityEventH.getText().add(p(nVar));
                z(accessibilityEventH);
            }
        }
        this.f5200z = null;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x020e  */
    /* JADX WARN: Code duplicated, block: B:101:0x021c  */
    /* JADX WARN: Code duplicated, block: B:102:0x0231  */
    /* JADX WARN: Code duplicated, block: B:105:0x0254  */
    /* JADX WARN: Code duplicated, block: B:109:0x026b  */
    /* JADX WARN: Code duplicated, block: B:111:0x0271  */
    /* JADX WARN: Code duplicated, block: B:115:0x0278  */
    /* JADX WARN: Code duplicated, block: B:118:0x0280  */
    /* JADX WARN: Code duplicated, block: B:122:0x0287  */
    /* JADX WARN: Code duplicated, block: B:125:0x029b  */
    /* JADX WARN: Code duplicated, block: B:126:0x029d  */
    /* JADX WARN: Code duplicated, block: B:130:0x02a5  */
    /* JADX WARN: Code duplicated, block: B:133:0x02b2 A[LOOP:4: B:128:0x02a1->B:133:0x02b2, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:138:0x02c0  */
    /* JADX WARN: Code duplicated, block: B:141:0x02d4 A[LOOP:5: B:136:0x02bc->B:141:0x02d4, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:145:0x02f3 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:148:0x02fa  */
    /* JADX WARN: Code duplicated, block: B:150:0x02fe A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:153:0x0305  */
    /* JADX WARN: Code duplicated, block: B:155:0x0309 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:158:0x032e  */
    /* JADX WARN: Code duplicated, block: B:161:0x034d A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:162:0x034f  */
    /* JADX WARN: Code duplicated, block: B:163:0x036c  */
    /* JADX WARN: Code duplicated, block: B:164:0x0382  */
    /* JADX WARN: Code duplicated, block: B:168:0x0397  */
    /* JADX WARN: Code duplicated, block: B:171:0x039d  */
    /* JADX WARN: Code duplicated, block: B:176:0x03dc  */
    /* JADX WARN: Code duplicated, block: B:178:0x03e2  */
    /* JADX WARN: Code duplicated, block: B:179:0x03e4  */
    /* JADX WARN: Code duplicated, block: B:183:0x03f6  */
    /* JADX WARN: Code duplicated, block: B:186:0x0407 A[LOOP:3: B:182:0x03f4->B:186:0x0407, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:190:0x0415  */
    /* JADX WARN: Code duplicated, block: B:193:0x0423  */
    /* JADX WARN: Code duplicated, block: B:197:0x0433  */
    /* JADX WARN: Code duplicated, block: B:198:0x0446  */
    /* JADX WARN: Code duplicated, block: B:202:0x045f  */
    /* JADX WARN: Code duplicated, block: B:203:0x046d  */
    /* JADX WARN: Code duplicated, block: B:205:0x047a  */
    /* JADX WARN: Code duplicated, block: B:207:0x0482  */
    /* JADX WARN: Code duplicated, block: B:209:0x048e  */
    /* JADX WARN: Code duplicated, block: B:214:0x049f  */
    /* JADX WARN: Code duplicated, block: B:216:0x04aa  */
    /* JADX WARN: Code duplicated, block: B:218:0x04b0  */
    /* JADX WARN: Code duplicated, block: B:222:0x04ba  */
    /* JADX WARN: Code duplicated, block: B:228:0x04e9  */
    /* JADX WARN: Code duplicated, block: B:233:0x050c  */
    /* JADX WARN: Code duplicated, block: B:235:0x0523  */
    /* JADX WARN: Code duplicated, block: B:238:0x0528  */
    /* JADX WARN: Code duplicated, block: B:241:0x052d  */
    /* JADX WARN: Code duplicated, block: B:244:0x053c  */
    /* JADX WARN: Code duplicated, block: B:251:0x0549  */
    /* JADX WARN: Code duplicated, block: B:254:0x054e  */
    /* JADX WARN: Code duplicated, block: B:269:0x059f  */
    /* JADX WARN: Code duplicated, block: B:279:0x04db A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:280:0x04cd A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:288:0x04f0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:289:0x0391 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:290:0x0104 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:291:0x011e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:292:0x03ec A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:293:0x0147 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:294:0x0166 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:295:0x0185 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:296:0x044e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:297:0x0503 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:298:0x0494 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:299:0x04bc A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:300:0x023d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:301:0x0263 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:305:0x040a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:306:0x0400 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:307:0x02b9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:308:0x02bb A[EDGE_INSN: B:308:0x02bb->B:135:0x02bb BREAK  A[LOOP:4: B:128:0x02a1->B:133:0x02b2], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:309:0x02d7 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:310:0x02d9 A[EDGE_INSN: B:310:0x02d9->B:143:0x02d9 BREAK  A[LOOP:5: B:136:0x02bc->B:141:0x02d4], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:41:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:43:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:49:0x0110  */
    /* JADX WARN: Code duplicated, block: B:53:0x012f  */
    /* JADX WARN: Code duplicated, block: B:54:0x0135  */
    /* JADX WARN: Code duplicated, block: B:56:0x013d  */
    /* JADX WARN: Code duplicated, block: B:57:0x013f  */
    /* JADX WARN: Code duplicated, block: B:60:0x015a  */
    /* JADX WARN: Code duplicated, block: B:63:0x017a  */
    /* JADX WARN: Code duplicated, block: B:67:0x018d  */
    /* JADX WARN: Code duplicated, block: B:70:0x0193  */
    /* JADX WARN: Code duplicated, block: B:71:0x0195  */
    /* JADX WARN: Code duplicated, block: B:75:0x019c  */
    /* JADX WARN: Code duplicated, block: B:77:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:80:0x01ac  */
    /* JADX WARN: Code duplicated, block: B:82:0x01ca  */
    /* JADX WARN: Code duplicated, block: B:85:0x01d2  */
    /* JADX WARN: Code duplicated, block: B:86:0x01d7  */
    /* JADX WARN: Code duplicated, block: B:89:0x01e9  */
    /* JADX WARN: Code duplicated, block: B:92:0x01ef  */
    /* JADX WARN: Code duplicated, block: B:93:0x01f4  */
    /* JADX WARN: Code duplicated, block: B:95:0x01f8  */
    /* JADX WARN: Code duplicated, block: B:97:0x01fd  */
    public final void E(j.r rVar) {
        Integer num;
        ArrayList arrayList;
        int[] iArr;
        long[] jArr;
        int i5;
        int i6;
        Integer num2;
        Integer num3;
        ArrayList arrayList2;
        int[] iArr2;
        long[] jArr2;
        int i7;
        int i8;
        int i9;
        Integer num4;
        h2 h2Var;
        boolean z2;
        w1.t tVar;
        w1.t tVar2;
        String str;
        boolean zA;
        ArrayList arrayList3;
        w1.t tVar3;
        Object obj;
        w1.f fVar;
        boolean z3;
        w1.n nVar;
        Object obj2;
        AccessibilityEvent accessibilityEventH;
        Object obj3;
        List list;
        String strS;
        Object obj4;
        List list2;
        String strS2;
        int i10;
        w1.t tVar4;
        String str2;
        Object obj5;
        y1.f fVar2;
        Object obj6;
        CharSequence charSequence;
        CharSequence charSequenceK;
        int length;
        int length2;
        Integer num5;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        boolean zContainsKey;
        boolean z4;
        boolean z5;
        AccessibilityEvent accessibilityEventI;
        w1.t tVar5;
        Object obj7;
        y1.f fVar3;
        String str3;
        boolean zA2;
        int size;
        int i17;
        h2 h2Var2;
        Object obj8;
        Object obj9;
        Object value;
        int i18;
        w1.t tVar6;
        w1.a aVar;
        Object obj10;
        boolean z6;
        String str4;
        w1.a aVar2;
        p3.c cVar;
        List list3;
        Object obj11;
        List list4;
        LinkedHashSet linkedHashSet;
        LinkedHashSet linkedHashSet2;
        Object value2;
        Object obj12;
        j.r rVar2 = rVar;
        Integer num6 = 64;
        ArrayList arrayList4 = this.L;
        ArrayList arrayList5 = new ArrayList(arrayList4);
        arrayList4.clear();
        int[] iArr3 = rVar2.f3139b;
        long[] jArr3 = rVar2.f3138a;
        int i19 = 2;
        int length3 = jArr3.length - 2;
        int i20 = 0;
        Integer num7 = 0;
        if (length3 < 0) {
            return;
        }
        int i21 = 0;
        while (true) {
            long j5 = jArr3[i21];
            int i22 = i19;
            int i23 = length3;
            if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i24 = 8;
                int i25 = 8 - ((~(i21 - i23)) >>> 31);
                long j6 = j5;
                int i26 = i20;
                while (i26 < i25) {
                    if ((j6 & 255) < 128) {
                        int i27 = iArr3[(i21 << 3) + i26];
                        i2 i2Var = (i2) this.H.d(i27);
                        if (i2Var == null) {
                            i26 = i26;
                            num3 = num6;
                            arrayList2 = arrayList5;
                            iArr2 = iArr3;
                            jArr2 = jArr3;
                            i7 = i24;
                            i8 = i20;
                            i9 = i21;
                            num4 = num7;
                        } else {
                            w1.i iVar = i2Var.f5228a;
                            LinkedHashMap linkedHashMap = iVar.f6308d;
                            j2 j2Var = (j2) rVar2.d(i27);
                            w1.n nVar2 = j2Var != null ? j2Var.f5252a : null;
                            if (nVar2 == null) {
                                d4.a.M("no value for specified key");
                                throw null;
                            }
                            p1.d0 d0Var = nVar2.f6317c;
                            w1.i iVar2 = nVar2.f6318d;
                            iArr2 = iArr3;
                            int i28 = nVar2.f6321g;
                            jArr2 = jArr3;
                            LinkedHashMap linkedHashMap2 = iVar2.f6308d;
                            Iterator it = iVar2.iterator();
                            boolean z7 = false;
                            while (true) {
                                i26 = i26;
                                if (!it.hasNext()) {
                                    w1.i iVar3 = iVar;
                                    num3 = num6;
                                    arrayList2 = arrayList5;
                                    w1.n nVar3 = nVar2;
                                    i9 = i21;
                                    num4 = num7;
                                    int i29 = i27;
                                    i8 = 0;
                                    if (!z7) {
                                        Iterator it2 = iVar3.iterator();
                                        while (true) {
                                            if (!it2.hasNext()) {
                                                z7 = false;
                                                break;
                                            }
                                            if (!nVar3.i().f6308d.containsKey((w1.t) ((Map.Entry) it2.next()).getKey())) {
                                                z7 = true;
                                                break;
                                            }
                                        }
                                    }
                                    if (!z7) {
                                        i7 = 8;
                                        break;
                                    } else {
                                        i7 = 8;
                                        B(this, x(i29), 2048, num4, 8);
                                        break;
                                    }
                                }
                                Map.Entry entry = (Map.Entry) it.next();
                                Object key = entry.getKey();
                                iVar = iVar;
                                w1.t tVar7 = w1.q.f6350o;
                                if (b4.l.a(key, tVar7)) {
                                    i21 = i21;
                                } else {
                                    i21 = i21;
                                    if (!b4.l.a(entry.getKey(), w1.q.f6351p)) {
                                        z2 = false;
                                    }
                                    if (z2) {
                                        tVar = (w1.t) entry.getKey();
                                        tVar2 = w1.q.f6339d;
                                        if (b4.l.a(tVar, tVar2)) {
                                            Object value3 = entry.getValue();
                                            b4.l.c("null cannot be cast to non-null type kotlin.String", value3);
                                            str = (String) value3;
                                            if (linkedHashMap.containsKey(tVar2)) {
                                                C(i27, 8, str);
                                            }
                                        } else {
                                            if (b4.l.a(tVar, w1.q.f6337b)) {
                                                zA = true;
                                            } else {
                                                zA = b4.l.a(tVar, w1.q.A);
                                            }
                                            if (zA) {
                                                B(this, x(i27), 2048, num6, 8);
                                                B(this, x(i27), 2048, num7, 8);
                                            } else {
                                                arrayList3 = arrayList5;
                                                if (b4.l.a(tVar, w1.q.f6338c)) {
                                                    B(this, x(i27), 2048, num6, 8);
                                                    B(this, x(i27), 2048, num7, 8);
                                                    nVar = nVar2;
                                                    i10 = i27;
                                                    linkedHashMap = linkedHashMap;
                                                    i27 = i10;
                                                    linkedHashMap = linkedHashMap;
                                                    num7 = num7;
                                                    nVar2 = nVar;
                                                    arrayList5 = arrayList3;
                                                    num6 = num6;
                                                } else {
                                                    tVar3 = w1.q.f6361z;
                                                    if (b4.l.a(tVar, tVar3)) {
                                                        obj = linkedHashMap2.get(w1.q.f6353r);
                                                        if (obj == null) {
                                                            obj = null;
                                                        }
                                                        fVar = (w1.f) obj;
                                                        if (fVar == null) {
                                                            z3 = false;
                                                        } else {
                                                            z3 = true;
                                                        }
                                                        if (z3) {
                                                            obj2 = linkedHashMap2.get(tVar3);
                                                            if (obj2 == null) {
                                                                obj2 = null;
                                                            }
                                                            if (b4.l.a(obj2, Boolean.TRUE)) {
                                                                accessibilityEventH = h(x(i27), 4);
                                                                w1.n nVar4 = new w1.n(nVar2.f6315a, true, d0Var, iVar2);
                                                                obj3 = nVar4.i().f6308d.get(w1.q.f6336a);
                                                                if (obj3 == null) {
                                                                    obj3 = null;
                                                                }
                                                                list = (List) obj3;
                                                                if (list != null) {
                                                                    strS = h1.c.s(list, ",");
                                                                } else {
                                                                    strS = null;
                                                                }
                                                                nVar = nVar2;
                                                                obj4 = nVar4.i().f6308d.get(w1.q.f6355t);
                                                                if (obj4 == null) {
                                                                    obj4 = null;
                                                                }
                                                                list2 = (List) obj4;
                                                                if (list2 != null) {
                                                                    strS2 = h1.c.s(list2, ",");
                                                                } else {
                                                                    strS2 = null;
                                                                }
                                                                if (strS != null) {
                                                                    accessibilityEventH.setContentDescription(strS);
                                                                }
                                                                if (strS2 != null) {
                                                                    accessibilityEventH.getText().add(strS2);
                                                                }
                                                                z(accessibilityEventH);
                                                            } else {
                                                                nVar = nVar2;
                                                                B(this, x(i27), 2048, num7, 8);
                                                            }
                                                        } else {
                                                            nVar = nVar2;
                                                            B(this, x(i27), 2048, num6, 8);
                                                            B(this, x(i27), 2048, num7, 8);
                                                        }
                                                    } else {
                                                        nVar = nVar2;
                                                        if (b4.l.a(tVar, w1.q.f6336a)) {
                                                            int iX = x(i27);
                                                            Object value4 = entry.getValue();
                                                            b4.l.c("null cannot be cast to non-null type kotlin.collections.List<kotlin.String>", value4);
                                                            A(iX, 2048, 4, (List) value4);
                                                        } else {
                                                            tVar4 = w1.q.f6358w;
                                                            str2 = "";
                                                            if (b4.l.a(tVar, tVar4)) {
                                                                num7 = num7;
                                                                linkedHashMap = linkedHashMap;
                                                                num6 = num6;
                                                                i10 = i27;
                                                                tVar5 = w1.q.f6359x;
                                                                if (b4.l.a(tVar, tVar5)) {
                                                                    obj7 = linkedHashMap2.get(tVar4);
                                                                    if (obj7 == null) {
                                                                        obj7 = null;
                                                                    }
                                                                    fVar3 = (y1.f) obj7;
                                                                    if (fVar3 != null) {
                                                                        str2 = str3;
                                                                    }
                                                                    long j7 = ((y1.g0) iVar2.b(tVar5)).f6770a;
                                                                    z(i(x(i10), Integer.valueOf((int) (j7 >> 32)), Integer.valueOf((int) (j7 & 4294967295L)), Integer.valueOf(str2.length()), K(str2)));
                                                                    D(i28);
                                                                } else {
                                                                    if (b4.l.a(tVar, tVar7)) {
                                                                        zA2 = true;
                                                                    } else {
                                                                        zA2 = b4.l.a(tVar, w1.q.f6351p);
                                                                    }
                                                                    if (zA2) {
                                                                        s(d0Var);
                                                                        size = arrayList4.size();
                                                                        i17 = 0;
                                                                        while (true) {
                                                                            if (i17 < size) {
                                                                                h2Var2 = null;
                                                                                break;
                                                                            } else {
                                                                                if (((h2) arrayList4.get(i17)).f5218d == i10) {
                                                                                    h2Var2 = (h2) arrayList4.get(i17);
                                                                                    break;
                                                                                }
                                                                                i17++;
                                                                            }
                                                                        }
                                                                        b4.l.b(h2Var2);
                                                                        obj8 = linkedHashMap2.get(tVar7);
                                                                        if (obj8 == null) {
                                                                            obj8 = null;
                                                                        }
                                                                        h2Var2.f5222h = (w1.g) obj8;
                                                                        obj9 = linkedHashMap2.get(w1.q.f6351p);
                                                                        if (obj9 == null) {
                                                                            obj9 = null;
                                                                        }
                                                                        h2Var2.f5223i = (w1.g) obj9;
                                                                        if (h2Var2.f5219e.contains(h2Var2)) {
                                                                            this.f5178d.getSnapshotObserver().a(h2Var2, this.M, new c0.t2(h2Var2, 7, this));
                                                                        }
                                                                    } else if (b4.l.a(tVar, w1.q.f6346k)) {
                                                                        value = entry.getValue();
                                                                        b4.l.c("null cannot be cast to non-null type kotlin.Boolean", value);
                                                                        if (((Boolean) value).booleanValue()) {
                                                                            i18 = 8;
                                                                            z(h(x(i28), 8));
                                                                        } else {
                                                                            i18 = 8;
                                                                        }
                                                                        B(this, x(i28), 2048, num7, i18);
                                                                    } else {
                                                                        tVar6 = w1.h.f6302u;
                                                                        if (b4.l.a(tVar, tVar6)) {
                                                                            list3 = (List) iVar2.b(tVar6);
                                                                            obj11 = linkedHashMap.get(tVar6);
                                                                            if (obj11 == null) {
                                                                                obj11 = null;
                                                                            }
                                                                            list4 = (List) obj11;
                                                                            if (list4 != null) {
                                                                                linkedHashSet = new LinkedHashSet();
                                                                                if (list3.size() <= 0) {
                                                                                    list3.get(0).getClass();
                                                                                    throw new ClassCastException();
                                                                                }
                                                                                linkedHashSet2 = new LinkedHashSet();
                                                                                if (list4.size() <= 0) {
                                                                                    list4.get(0).getClass();
                                                                                    throw new ClassCastException();
                                                                                }
                                                                                if (linkedHashSet.containsAll(linkedHashSet2)) {
                                                                                    z7 = true;
                                                                                } else {
                                                                                    z7 = true;
                                                                                }
                                                                            } else if (!list3.isEmpty()) {
                                                                                i27 = i10;
                                                                                linkedHashMap = linkedHashMap;
                                                                                num7 = num7;
                                                                                nVar2 = nVar;
                                                                                arrayList5 = arrayList3;
                                                                                num6 = num6;
                                                                                z7 = true;
                                                                            }
                                                                            i27 = i10;
                                                                            linkedHashMap = linkedHashMap;
                                                                            num7 = num7;
                                                                            nVar2 = nVar;
                                                                            arrayList5 = arrayList3;
                                                                            num6 = num6;
                                                                        } else {
                                                                            if (entry.getValue() instanceof w1.a) {
                                                                                Object value5 = entry.getValue();
                                                                                b4.l.c("null cannot be cast to non-null type androidx.compose.ui.semantics.AccessibilityAction<*>", value5);
                                                                                aVar = (w1.a) value5;
                                                                                obj10 = linkedHashMap.get((w1.t) entry.getKey());
                                                                                if (obj10 == null) {
                                                                                    obj10 = null;
                                                                                }
                                                                                if (aVar != obj10) {
                                                                                    if (obj10 instanceof w1.a) {
                                                                                        str4 = aVar.f6272a;
                                                                                        aVar2 = (w1.a) obj10;
                                                                                        p3.c cVar2 = aVar2.f6273b;
                                                                                        if (b4.l.a(str4, aVar2.f6272a)) {
                                                                                            z6 = true;
                                                                                        }
                                                                                    }
                                                                                    z6 = false;
                                                                                } else {
                                                                                    z6 = true;
                                                                                }
                                                                                if (z6) {
                                                                                    z7 = false;
                                                                                } else {
                                                                                    z7 = true;
                                                                                }
                                                                            } else {
                                                                                z7 = true;
                                                                            }
                                                                            i27 = i10;
                                                                            linkedHashMap = linkedHashMap;
                                                                            num7 = num7;
                                                                            nVar2 = nVar;
                                                                            arrayList5 = arrayList3;
                                                                            num6 = num6;
                                                                        }
                                                                    }
                                                                }
                                                            } else if (linkedHashMap2.containsKey(w1.h.f6289h)) {
                                                                obj5 = linkedHashMap.get(tVar4);
                                                                if (obj5 == null) {
                                                                    obj5 = null;
                                                                }
                                                                fVar2 = (y1.f) obj5;
                                                                if (fVar2 == null) {
                                                                    fVar2 = "";
                                                                }
                                                                obj6 = linkedHashMap2.get(tVar4);
                                                                if (obj6 == null) {
                                                                    obj6 = null;
                                                                }
                                                                charSequence = (y1.f) obj6;
                                                                if (charSequence == null) {
                                                                    charSequence = "";
                                                                }
                                                                charSequenceK = K(charSequence);
                                                                length = fVar2.length();
                                                                length2 = charSequence.length();
                                                                num5 = num7;
                                                                if (length > length2) {
                                                                    i11 = length2;
                                                                } else {
                                                                    i11 = length;
                                                                }
                                                                num6 = num6;
                                                                i12 = 0;
                                                                while (true) {
                                                                    i13 = i11;
                                                                    if (i12 < i11) {
                                                                        i14 = length;
                                                                        break;
                                                                    }
                                                                    i14 = length;
                                                                    if (fVar2.charAt(i12) != charSequence.charAt(i12)) {
                                                                        break;
                                                                        break;
                                                                    } else {
                                                                        i12++;
                                                                        i11 = i13;
                                                                        length = i14;
                                                                    }
                                                                }
                                                                i15 = 0;
                                                                while (true) {
                                                                    if (i15 < i13 - i12) {
                                                                        i16 = i15;
                                                                        break;
                                                                    }
                                                                    i16 = i15;
                                                                    if (fVar2.charAt((i14 - 1) - i15) != charSequence.charAt((length2 - 1) - i16)) {
                                                                        break;
                                                                        break;
                                                                    }
                                                                    i15 = i16 + 1;
                                                                }
                                                                int i30 = (i14 - i16) - i12;
                                                                int i31 = (length2 - i16) - i12;
                                                                w1.t tVar8 = w1.q.B;
                                                                boolean zContainsKey2 = linkedHashMap.containsKey(tVar8);
                                                                boolean zContainsKey3 = linkedHashMap2.containsKey(tVar8);
                                                                zContainsKey = linkedHashMap.containsKey(w1.q.f6358w);
                                                                if (zContainsKey) {
                                                                    z4 = false;
                                                                } else {
                                                                    z4 = false;
                                                                }
                                                                if (zContainsKey) {
                                                                    z5 = false;
                                                                } else {
                                                                    z5 = false;
                                                                }
                                                                if (z4) {
                                                                    i10 = i27;
                                                                    accessibilityEventI = i(x(i27), num5, num5, Integer.valueOf(length2), charSequenceK);
                                                                    num7 = num5;
                                                                } else {
                                                                    i10 = i27;
                                                                    accessibilityEventI = i(x(i27), num5, num5, Integer.valueOf(length2), charSequenceK);
                                                                    num7 = num5;
                                                                }
                                                                accessibilityEventI.setClassName("android.widget.EditText");
                                                                z(accessibilityEventI);
                                                                if (z4) {
                                                                    long j8 = ((y1.g0) iVar2.b(w1.q.f6359x)).f6770a;
                                                                    accessibilityEventI.setFromIndex((int) (j8 >> 32));
                                                                    accessibilityEventI.setToIndex((int) (j8 & 4294967295L));
                                                                    z(accessibilityEventI);
                                                                } else {
                                                                    long j9 = ((y1.g0) iVar2.b(w1.q.f6359x)).f6770a;
                                                                    accessibilityEventI.setFromIndex((int) (j9 >> 32));
                                                                    accessibilityEventI.setToIndex((int) (j9 & 4294967295L));
                                                                    z(accessibilityEventI);
                                                                }
                                                            } else {
                                                                num7 = num7;
                                                                linkedHashMap = linkedHashMap;
                                                                num6 = num6;
                                                                i10 = i27;
                                                                B(this, x(i10), 2048, Integer.valueOf(i22), 8);
                                                            }
                                                            i27 = i10;
                                                            linkedHashMap = linkedHashMap;
                                                            num7 = num7;
                                                            nVar2 = nVar;
                                                            arrayList5 = arrayList3;
                                                            num6 = num6;
                                                        }
                                                    }
                                                    num7 = num7;
                                                    linkedHashMap = linkedHashMap;
                                                    num6 = num6;
                                                    i10 = i27;
                                                    i27 = i10;
                                                    linkedHashMap = linkedHashMap;
                                                    num7 = num7;
                                                    nVar2 = nVar;
                                                    arrayList5 = arrayList3;
                                                    num6 = num6;
                                                }
                                            }
                                        }
                                        arrayList3 = arrayList5;
                                        nVar = nVar2;
                                        i10 = i27;
                                        linkedHashMap = linkedHashMap;
                                        i27 = i10;
                                        linkedHashMap = linkedHashMap;
                                        num7 = num7;
                                        nVar2 = nVar;
                                        arrayList5 = arrayList3;
                                        num6 = num6;
                                    } else {
                                        value2 = entry.getValue();
                                        obj12 = linkedHashMap.get((w1.t) entry.getKey());
                                        if (obj12 == null) {
                                            obj12 = null;
                                        }
                                        if (b4.l.a(value2, obj12)) {
                                            tVar = (w1.t) entry.getKey();
                                            tVar2 = w1.q.f6339d;
                                            if (b4.l.a(tVar, tVar2)) {
                                                Object value6 = entry.getValue();
                                                b4.l.c("null cannot be cast to non-null type kotlin.String", value6);
                                                str = (String) value6;
                                                if (linkedHashMap.containsKey(tVar2)) {
                                                    C(i27, 8, str);
                                                }
                                            } else {
                                                if (b4.l.a(tVar, w1.q.f6337b)) {
                                                    zA = true;
                                                } else {
                                                    zA = b4.l.a(tVar, w1.q.A);
                                                }
                                                if (zA) {
                                                    B(this, x(i27), 2048, num6, 8);
                                                    B(this, x(i27), 2048, num7, 8);
                                                } else {
                                                    arrayList3 = arrayList5;
                                                    if (b4.l.a(tVar, w1.q.f6338c)) {
                                                        B(this, x(i27), 2048, num6, 8);
                                                        B(this, x(i27), 2048, num7, 8);
                                                        nVar = nVar2;
                                                        i10 = i27;
                                                        linkedHashMap = linkedHashMap;
                                                        i27 = i10;
                                                        linkedHashMap = linkedHashMap;
                                                        num7 = num7;
                                                        nVar2 = nVar;
                                                        arrayList5 = arrayList3;
                                                        num6 = num6;
                                                    } else {
                                                        tVar3 = w1.q.f6361z;
                                                        if (b4.l.a(tVar, tVar3)) {
                                                            obj = linkedHashMap2.get(w1.q.f6353r);
                                                            if (obj == null) {
                                                                obj = null;
                                                            }
                                                            fVar = (w1.f) obj;
                                                            if (fVar == null && fVar.f6279a == 4) {
                                                                z3 = true;
                                                            } else {
                                                                z3 = false;
                                                            }
                                                            if (z3) {
                                                                obj2 = linkedHashMap2.get(tVar3);
                                                                if (obj2 == null) {
                                                                    obj2 = null;
                                                                }
                                                                if (b4.l.a(obj2, Boolean.TRUE)) {
                                                                    accessibilityEventH = h(x(i27), 4);
                                                                    w1.n nVar5 = new w1.n(nVar2.f6315a, true, d0Var, iVar2);
                                                                    obj3 = nVar5.i().f6308d.get(w1.q.f6336a);
                                                                    if (obj3 == null) {
                                                                        obj3 = null;
                                                                    }
                                                                    list = (List) obj3;
                                                                    if (list != null) {
                                                                        strS = h1.c.s(list, ",");
                                                                    } else {
                                                                        strS = null;
                                                                    }
                                                                    nVar = nVar2;
                                                                    obj4 = nVar5.i().f6308d.get(w1.q.f6355t);
                                                                    if (obj4 == null) {
                                                                        obj4 = null;
                                                                    }
                                                                    list2 = (List) obj4;
                                                                    if (list2 != null) {
                                                                        strS2 = h1.c.s(list2, ",");
                                                                    } else {
                                                                        strS2 = null;
                                                                    }
                                                                    if (strS != null) {
                                                                        accessibilityEventH.setContentDescription(strS);
                                                                    }
                                                                    if (strS2 != null) {
                                                                        accessibilityEventH.getText().add(strS2);
                                                                    }
                                                                    z(accessibilityEventH);
                                                                } else {
                                                                    nVar = nVar2;
                                                                    B(this, x(i27), 2048, num7, 8);
                                                                }
                                                            } else {
                                                                nVar = nVar2;
                                                                B(this, x(i27), 2048, num6, 8);
                                                                B(this, x(i27), 2048, num7, 8);
                                                            }
                                                        } else {
                                                            nVar = nVar2;
                                                            if (b4.l.a(tVar, w1.q.f6336a)) {
                                                                int iX2 = x(i27);
                                                                Object value7 = entry.getValue();
                                                                b4.l.c("null cannot be cast to non-null type kotlin.collections.List<kotlin.String>", value7);
                                                                A(iX2, 2048, 4, (List) value7);
                                                            } else {
                                                                tVar4 = w1.q.f6358w;
                                                                str2 = "";
                                                                if (b4.l.a(tVar, tVar4)) {
                                                                    num7 = num7;
                                                                    linkedHashMap = linkedHashMap;
                                                                    num6 = num6;
                                                                    i10 = i27;
                                                                    tVar5 = w1.q.f6359x;
                                                                    if (b4.l.a(tVar, tVar5)) {
                                                                        obj7 = linkedHashMap2.get(tVar4);
                                                                        if (obj7 == null) {
                                                                            obj7 = null;
                                                                        }
                                                                        fVar3 = (y1.f) obj7;
                                                                        if (fVar3 != null && (str3 = fVar3.f6759d) != null) {
                                                                            str2 = str3;
                                                                        }
                                                                        long j10 = ((y1.g0) iVar2.b(tVar5)).f6770a;
                                                                        z(i(x(i10), Integer.valueOf((int) (j10 >> 32)), Integer.valueOf((int) (j10 & 4294967295L)), Integer.valueOf(str2.length()), K(str2)));
                                                                        D(i28);
                                                                    } else {
                                                                        if (b4.l.a(tVar, tVar7)) {
                                                                            zA2 = true;
                                                                        } else {
                                                                            zA2 = b4.l.a(tVar, w1.q.f6351p);
                                                                        }
                                                                        if (zA2) {
                                                                            s(d0Var);
                                                                            size = arrayList4.size();
                                                                            i17 = 0;
                                                                            while (true) {
                                                                                if (i17 < size) {
                                                                                    h2Var2 = null;
                                                                                    break;
                                                                                } else {
                                                                                    if (((h2) arrayList4.get(i17)).f5218d == i10) {
                                                                                        h2Var2 = (h2) arrayList4.get(i17);
                                                                                        break;
                                                                                    }
                                                                                    i17++;
                                                                                }
                                                                            }
                                                                            b4.l.b(h2Var2);
                                                                            obj8 = linkedHashMap2.get(tVar7);
                                                                            if (obj8 == null) {
                                                                                obj8 = null;
                                                                            }
                                                                            h2Var2.f5222h = (w1.g) obj8;
                                                                            obj9 = linkedHashMap2.get(w1.q.f6351p);
                                                                            if (obj9 == null) {
                                                                                obj9 = null;
                                                                            }
                                                                            h2Var2.f5223i = (w1.g) obj9;
                                                                            if (h2Var2.f5219e.contains(h2Var2)) {
                                                                                this.f5178d.getSnapshotObserver().a(h2Var2, this.M, new c0.t2(h2Var2, 7, this));
                                                                            }
                                                                        } else if (b4.l.a(tVar, w1.q.f6346k)) {
                                                                            value = entry.getValue();
                                                                            b4.l.c("null cannot be cast to non-null type kotlin.Boolean", value);
                                                                            if (((Boolean) value).booleanValue()) {
                                                                                i18 = 8;
                                                                                z(h(x(i28), 8));
                                                                            } else {
                                                                                i18 = 8;
                                                                            }
                                                                            B(this, x(i28), 2048, num7, i18);
                                                                        } else {
                                                                            tVar6 = w1.h.f6302u;
                                                                            if (b4.l.a(tVar, tVar6)) {
                                                                                list3 = (List) iVar2.b(tVar6);
                                                                                obj11 = linkedHashMap.get(tVar6);
                                                                                if (obj11 == null) {
                                                                                    obj11 = null;
                                                                                }
                                                                                list4 = (List) obj11;
                                                                                if (list4 != null) {
                                                                                    linkedHashSet = new LinkedHashSet();
                                                                                    if (list3.size() <= 0) {
                                                                                        list3.get(0).getClass();
                                                                                        throw new ClassCastException();
                                                                                    }
                                                                                    linkedHashSet2 = new LinkedHashSet();
                                                                                    if (list4.size() <= 0) {
                                                                                        list4.get(0).getClass();
                                                                                        throw new ClassCastException();
                                                                                    }
                                                                                    if (linkedHashSet.containsAll(linkedHashSet2) || !linkedHashSet2.containsAll(linkedHashSet)) {
                                                                                        z7 = true;
                                                                                    } else {
                                                                                        z7 = false;
                                                                                    }
                                                                                } else if (!list3.isEmpty()) {
                                                                                    i27 = i10;
                                                                                    linkedHashMap = linkedHashMap;
                                                                                    num7 = num7;
                                                                                    nVar2 = nVar;
                                                                                    arrayList5 = arrayList3;
                                                                                    num6 = num6;
                                                                                    z7 = true;
                                                                                }
                                                                                i27 = i10;
                                                                                linkedHashMap = linkedHashMap;
                                                                                num7 = num7;
                                                                                nVar2 = nVar;
                                                                                arrayList5 = arrayList3;
                                                                                num6 = num6;
                                                                            } else {
                                                                                if (entry.getValue() instanceof w1.a) {
                                                                                    Object value8 = entry.getValue();
                                                                                    b4.l.c("null cannot be cast to non-null type androidx.compose.ui.semantics.AccessibilityAction<*>", value8);
                                                                                    aVar = (w1.a) value8;
                                                                                    obj10 = linkedHashMap.get((w1.t) entry.getKey());
                                                                                    if (obj10 == null) {
                                                                                        obj10 = null;
                                                                                    }
                                                                                    if (aVar != obj10) {
                                                                                        if (obj10 instanceof w1.a) {
                                                                                            str4 = aVar.f6272a;
                                                                                            aVar2 = (w1.a) obj10;
                                                                                            p3.c cVar3 = aVar2.f6273b;
                                                                                            if (b4.l.a(str4, aVar2.f6272a) && (((cVar = aVar.f6273b) != null || cVar3 == null) && (cVar == null || cVar3 != null))) {
                                                                                                z6 = true;
                                                                                            }
                                                                                        }
                                                                                        z6 = false;
                                                                                    } else {
                                                                                        z6 = true;
                                                                                    }
                                                                                    if (z6) {
                                                                                        z7 = true;
                                                                                    } else {
                                                                                        z7 = false;
                                                                                    }
                                                                                } else {
                                                                                    z7 = true;
                                                                                }
                                                                                i27 = i10;
                                                                                linkedHashMap = linkedHashMap;
                                                                                num7 = num7;
                                                                                nVar2 = nVar;
                                                                                arrayList5 = arrayList3;
                                                                                num6 = num6;
                                                                            }
                                                                        }
                                                                    }
                                                                } else if (linkedHashMap2.containsKey(w1.h.f6289h)) {
                                                                    obj5 = linkedHashMap.get(tVar4);
                                                                    if (obj5 == null) {
                                                                        obj5 = null;
                                                                    }
                                                                    fVar2 = (y1.f) obj5;
                                                                    if (fVar2 == null) {
                                                                        fVar2 = "";
                                                                    }
                                                                    obj6 = linkedHashMap2.get(tVar4);
                                                                    if (obj6 == null) {
                                                                        obj6 = null;
                                                                    }
                                                                    charSequence = (y1.f) obj6;
                                                                    if (charSequence == null) {
                                                                        charSequence = "";
                                                                    }
                                                                    charSequenceK = K(charSequence);
                                                                    length = fVar2.length();
                                                                    length2 = charSequence.length();
                                                                    num5 = num7;
                                                                    if (length > length2) {
                                                                        i11 = length2;
                                                                    } else {
                                                                        i11 = length;
                                                                    }
                                                                    num6 = num6;
                                                                    i12 = 0;
                                                                    while (true) {
                                                                        i13 = i11;
                                                                        if (i12 < i11) {
                                                                            i14 = length;
                                                                            break;
                                                                        }
                                                                        i14 = length;
                                                                        if (fVar2.charAt(i12) != charSequence.charAt(i12)) {
                                                                            break;
                                                                        }
                                                                        i12++;
                                                                        i11 = i13;
                                                                        length = i14;
                                                                    }
                                                                    i15 = 0;
                                                                    while (true) {
                                                                        if (i15 < i13 - i12) {
                                                                            i16 = i15;
                                                                            break;
                                                                        }
                                                                        i16 = i15;
                                                                        if (fVar2.charAt((i14 - 1) - i15) != charSequence.charAt((length2 - 1) - i16)) {
                                                                            break;
                                                                        } else {
                                                                            i15 = i16 + 1;
                                                                        }
                                                                    }
                                                                    int i32 = (i14 - i16) - i12;
                                                                    int i33 = (length2 - i16) - i12;
                                                                    w1.t tVar9 = w1.q.B;
                                                                    boolean zContainsKey4 = linkedHashMap.containsKey(tVar9);
                                                                    boolean zContainsKey5 = linkedHashMap2.containsKey(tVar9);
                                                                    zContainsKey = linkedHashMap.containsKey(w1.q.f6358w);
                                                                    if (zContainsKey || zContainsKey4 || !zContainsKey5) {
                                                                        z4 = false;
                                                                    } else {
                                                                        z4 = true;
                                                                    }
                                                                    if (zContainsKey || !zContainsKey4 || zContainsKey5) {
                                                                        z5 = false;
                                                                    } else {
                                                                        z5 = true;
                                                                    }
                                                                    if (z4 || z5) {
                                                                        i10 = i27;
                                                                        accessibilityEventI = i(x(i27), num5, num5, Integer.valueOf(length2), charSequenceK);
                                                                        num7 = num5;
                                                                    } else {
                                                                        accessibilityEventI = h(x(i27), 16);
                                                                        accessibilityEventI.setFromIndex(i12);
                                                                        accessibilityEventI.setRemovedCount(i32);
                                                                        accessibilityEventI.setAddedCount(i33);
                                                                        accessibilityEventI.setBeforeText(fVar2);
                                                                        accessibilityEventI.getText().add(charSequenceK);
                                                                        i10 = i27;
                                                                        num7 = num5;
                                                                    }
                                                                    accessibilityEventI.setClassName("android.widget.EditText");
                                                                    z(accessibilityEventI);
                                                                    if (z4 || z5) {
                                                                        long j11 = ((y1.g0) iVar2.b(w1.q.f6359x)).f6770a;
                                                                        accessibilityEventI.setFromIndex((int) (j11 >> 32));
                                                                        accessibilityEventI.setToIndex((int) (j11 & 4294967295L));
                                                                        z(accessibilityEventI);
                                                                    }
                                                                } else {
                                                                    num7 = num7;
                                                                    linkedHashMap = linkedHashMap;
                                                                    num6 = num6;
                                                                    i10 = i27;
                                                                    B(this, x(i10), 2048, Integer.valueOf(i22), 8);
                                                                }
                                                                i27 = i10;
                                                                linkedHashMap = linkedHashMap;
                                                                num7 = num7;
                                                                nVar2 = nVar;
                                                                arrayList5 = arrayList3;
                                                                num6 = num6;
                                                            }
                                                        }
                                                        num7 = num7;
                                                        linkedHashMap = linkedHashMap;
                                                        num6 = num6;
                                                        i10 = i27;
                                                        i27 = i10;
                                                        linkedHashMap = linkedHashMap;
                                                        num7 = num7;
                                                        nVar2 = nVar;
                                                        arrayList5 = arrayList3;
                                                        num6 = num6;
                                                    }
                                                }
                                            }
                                        }
                                        arrayList3 = arrayList5;
                                        nVar = nVar2;
                                        i10 = i27;
                                        linkedHashMap = linkedHashMap;
                                        i27 = i10;
                                        linkedHashMap = linkedHashMap;
                                        num7 = num7;
                                        nVar2 = nVar;
                                        arrayList5 = arrayList3;
                                        num6 = num6;
                                    }
                                }
                                int size2 = arrayList5.size();
                                int i34 = 0;
                                while (true) {
                                    if (i34 >= size2) {
                                        h2Var = null;
                                        break;
                                    }
                                    int i35 = size2;
                                    if (((h2) arrayList5.get(i34)).f5218d == i27) {
                                        h2Var = (h2) arrayList5.get(i34);
                                        break;
                                    } else {
                                        i34++;
                                        size2 = i35;
                                    }
                                }
                                if (h2Var != null) {
                                    z2 = false;
                                } else {
                                    h2Var = new h2(i27, arrayList4);
                                    z2 = true;
                                }
                                arrayList4.add(h2Var);
                                if (z2) {
                                    value2 = entry.getValue();
                                    obj12 = linkedHashMap.get((w1.t) entry.getKey());
                                    if (obj12 == null) {
                                        obj12 = null;
                                    }
                                    if (b4.l.a(value2, obj12)) {
                                        tVar = (w1.t) entry.getKey();
                                        tVar2 = w1.q.f6339d;
                                        if (b4.l.a(tVar, tVar2)) {
                                            Object value9 = entry.getValue();
                                            b4.l.c("null cannot be cast to non-null type kotlin.String", value9);
                                            str = (String) value9;
                                            if (linkedHashMap.containsKey(tVar2)) {
                                                C(i27, 8, str);
                                            }
                                        } else {
                                            if (b4.l.a(tVar, w1.q.f6337b)) {
                                                zA = true;
                                            } else {
                                                zA = b4.l.a(tVar, w1.q.A);
                                            }
                                            if (zA) {
                                                B(this, x(i27), 2048, num6, 8);
                                                B(this, x(i27), 2048, num7, 8);
                                            } else {
                                                arrayList3 = arrayList5;
                                                if (b4.l.a(tVar, w1.q.f6338c)) {
                                                    B(this, x(i27), 2048, num6, 8);
                                                    B(this, x(i27), 2048, num7, 8);
                                                    nVar = nVar2;
                                                    i10 = i27;
                                                    linkedHashMap = linkedHashMap;
                                                    i27 = i10;
                                                    linkedHashMap = linkedHashMap;
                                                    num7 = num7;
                                                    nVar2 = nVar;
                                                    arrayList5 = arrayList3;
                                                    num6 = num6;
                                                } else {
                                                    tVar3 = w1.q.f6361z;
                                                    if (b4.l.a(tVar, tVar3)) {
                                                        obj = linkedHashMap2.get(w1.q.f6353r);
                                                        if (obj == null) {
                                                            obj = null;
                                                        }
                                                        fVar = (w1.f) obj;
                                                        if (fVar == null) {
                                                            z3 = false;
                                                        } else {
                                                            z3 = true;
                                                        }
                                                        if (z3) {
                                                            obj2 = linkedHashMap2.get(tVar3);
                                                            if (obj2 == null) {
                                                                obj2 = null;
                                                            }
                                                            if (b4.l.a(obj2, Boolean.TRUE)) {
                                                                accessibilityEventH = h(x(i27), 4);
                                                                w1.n nVar6 = new w1.n(nVar2.f6315a, true, d0Var, iVar2);
                                                                obj3 = nVar6.i().f6308d.get(w1.q.f6336a);
                                                                if (obj3 == null) {
                                                                    obj3 = null;
                                                                }
                                                                list = (List) obj3;
                                                                if (list != null) {
                                                                    strS = h1.c.s(list, ",");
                                                                } else {
                                                                    strS = null;
                                                                }
                                                                nVar = nVar2;
                                                                obj4 = nVar6.i().f6308d.get(w1.q.f6355t);
                                                                if (obj4 == null) {
                                                                    obj4 = null;
                                                                }
                                                                list2 = (List) obj4;
                                                                if (list2 != null) {
                                                                    strS2 = h1.c.s(list2, ",");
                                                                } else {
                                                                    strS2 = null;
                                                                }
                                                                if (strS != null) {
                                                                    accessibilityEventH.setContentDescription(strS);
                                                                }
                                                                if (strS2 != null) {
                                                                    accessibilityEventH.getText().add(strS2);
                                                                }
                                                                z(accessibilityEventH);
                                                            } else {
                                                                nVar = nVar2;
                                                                B(this, x(i27), 2048, num7, 8);
                                                            }
                                                        } else {
                                                            nVar = nVar2;
                                                            B(this, x(i27), 2048, num6, 8);
                                                            B(this, x(i27), 2048, num7, 8);
                                                        }
                                                    } else {
                                                        nVar = nVar2;
                                                        if (b4.l.a(tVar, w1.q.f6336a)) {
                                                            int iX3 = x(i27);
                                                            Object value10 = entry.getValue();
                                                            b4.l.c("null cannot be cast to non-null type kotlin.collections.List<kotlin.String>", value10);
                                                            A(iX3, 2048, 4, (List) value10);
                                                        } else {
                                                            tVar4 = w1.q.f6358w;
                                                            str2 = "";
                                                            if (b4.l.a(tVar, tVar4)) {
                                                                num7 = num7;
                                                                linkedHashMap = linkedHashMap;
                                                                num6 = num6;
                                                                i10 = i27;
                                                                tVar5 = w1.q.f6359x;
                                                                if (b4.l.a(tVar, tVar5)) {
                                                                    obj7 = linkedHashMap2.get(tVar4);
                                                                    if (obj7 == null) {
                                                                        obj7 = null;
                                                                    }
                                                                    fVar3 = (y1.f) obj7;
                                                                    if (fVar3 != null) {
                                                                        str2 = str3;
                                                                    }
                                                                    long j12 = ((y1.g0) iVar2.b(tVar5)).f6770a;
                                                                    z(i(x(i10), Integer.valueOf((int) (j12 >> 32)), Integer.valueOf((int) (j12 & 4294967295L)), Integer.valueOf(str2.length()), K(str2)));
                                                                    D(i28);
                                                                } else {
                                                                    if (b4.l.a(tVar, tVar7)) {
                                                                        zA2 = true;
                                                                    } else {
                                                                        zA2 = b4.l.a(tVar, w1.q.f6351p);
                                                                    }
                                                                    if (zA2) {
                                                                        s(d0Var);
                                                                        size = arrayList4.size();
                                                                        i17 = 0;
                                                                        while (true) {
                                                                            if (i17 < size) {
                                                                                h2Var2 = null;
                                                                                break;
                                                                            } else {
                                                                                if (((h2) arrayList4.get(i17)).f5218d == i10) {
                                                                                    h2Var2 = (h2) arrayList4.get(i17);
                                                                                    break;
                                                                                }
                                                                                i17++;
                                                                            }
                                                                        }
                                                                        b4.l.b(h2Var2);
                                                                        obj8 = linkedHashMap2.get(tVar7);
                                                                        if (obj8 == null) {
                                                                            obj8 = null;
                                                                        }
                                                                        h2Var2.f5222h = (w1.g) obj8;
                                                                        obj9 = linkedHashMap2.get(w1.q.f6351p);
                                                                        if (obj9 == null) {
                                                                            obj9 = null;
                                                                        }
                                                                        h2Var2.f5223i = (w1.g) obj9;
                                                                        if (h2Var2.f5219e.contains(h2Var2)) {
                                                                            this.f5178d.getSnapshotObserver().a(h2Var2, this.M, new c0.t2(h2Var2, 7, this));
                                                                        }
                                                                    } else if (b4.l.a(tVar, w1.q.f6346k)) {
                                                                        value = entry.getValue();
                                                                        b4.l.c("null cannot be cast to non-null type kotlin.Boolean", value);
                                                                        if (((Boolean) value).booleanValue()) {
                                                                            i18 = 8;
                                                                            z(h(x(i28), 8));
                                                                        } else {
                                                                            i18 = 8;
                                                                        }
                                                                        B(this, x(i28), 2048, num7, i18);
                                                                    } else {
                                                                        tVar6 = w1.h.f6302u;
                                                                        if (b4.l.a(tVar, tVar6)) {
                                                                            list3 = (List) iVar2.b(tVar6);
                                                                            obj11 = linkedHashMap.get(tVar6);
                                                                            if (obj11 == null) {
                                                                                obj11 = null;
                                                                            }
                                                                            list4 = (List) obj11;
                                                                            if (list4 != null) {
                                                                                linkedHashSet = new LinkedHashSet();
                                                                                if (list3.size() <= 0) {
                                                                                    list3.get(0).getClass();
                                                                                    throw new ClassCastException();
                                                                                }
                                                                                linkedHashSet2 = new LinkedHashSet();
                                                                                if (list4.size() <= 0) {
                                                                                    list4.get(0).getClass();
                                                                                    throw new ClassCastException();
                                                                                }
                                                                                if (linkedHashSet.containsAll(linkedHashSet2)) {
                                                                                    z7 = true;
                                                                                } else {
                                                                                    z7 = true;
                                                                                }
                                                                            } else if (!list3.isEmpty()) {
                                                                                i27 = i10;
                                                                                linkedHashMap = linkedHashMap;
                                                                                num7 = num7;
                                                                                nVar2 = nVar;
                                                                                arrayList5 = arrayList3;
                                                                                num6 = num6;
                                                                                z7 = true;
                                                                            }
                                                                            i27 = i10;
                                                                            linkedHashMap = linkedHashMap;
                                                                            num7 = num7;
                                                                            nVar2 = nVar;
                                                                            arrayList5 = arrayList3;
                                                                            num6 = num6;
                                                                        } else {
                                                                            if (entry.getValue() instanceof w1.a) {
                                                                                Object value11 = entry.getValue();
                                                                                b4.l.c("null cannot be cast to non-null type androidx.compose.ui.semantics.AccessibilityAction<*>", value11);
                                                                                aVar = (w1.a) value11;
                                                                                obj10 = linkedHashMap.get((w1.t) entry.getKey());
                                                                                if (obj10 == null) {
                                                                                    obj10 = null;
                                                                                }
                                                                                if (aVar != obj10) {
                                                                                    if (obj10 instanceof w1.a) {
                                                                                        str4 = aVar.f6272a;
                                                                                        aVar2 = (w1.a) obj10;
                                                                                        p3.c cVar4 = aVar2.f6273b;
                                                                                        if (b4.l.a(str4, aVar2.f6272a)) {
                                                                                            z6 = true;
                                                                                        }
                                                                                    }
                                                                                    z6 = false;
                                                                                } else {
                                                                                    z6 = true;
                                                                                }
                                                                                if (z6) {
                                                                                    z7 = true;
                                                                                } else {
                                                                                    z7 = false;
                                                                                }
                                                                            } else {
                                                                                z7 = true;
                                                                            }
                                                                            i27 = i10;
                                                                            linkedHashMap = linkedHashMap;
                                                                            num7 = num7;
                                                                            nVar2 = nVar;
                                                                            arrayList5 = arrayList3;
                                                                            num6 = num6;
                                                                        }
                                                                    }
                                                                }
                                                            } else if (linkedHashMap2.containsKey(w1.h.f6289h)) {
                                                                obj5 = linkedHashMap.get(tVar4);
                                                                if (obj5 == null) {
                                                                    obj5 = null;
                                                                }
                                                                fVar2 = (y1.f) obj5;
                                                                if (fVar2 == null) {
                                                                    fVar2 = "";
                                                                }
                                                                obj6 = linkedHashMap2.get(tVar4);
                                                                if (obj6 == null) {
                                                                    obj6 = null;
                                                                }
                                                                charSequence = (y1.f) obj6;
                                                                if (charSequence == null) {
                                                                    charSequence = "";
                                                                }
                                                                charSequenceK = K(charSequence);
                                                                length = fVar2.length();
                                                                length2 = charSequence.length();
                                                                num5 = num7;
                                                                if (length > length2) {
                                                                    i11 = length2;
                                                                } else {
                                                                    i11 = length;
                                                                }
                                                                num6 = num6;
                                                                i12 = 0;
                                                                while (true) {
                                                                    i13 = i11;
                                                                    if (i12 < i11) {
                                                                        i14 = length;
                                                                        break;
                                                                    }
                                                                    i14 = length;
                                                                    if (fVar2.charAt(i12) != charSequence.charAt(i12)) {
                                                                        break;
                                                                        break;
                                                                    } else {
                                                                        i12++;
                                                                        i11 = i13;
                                                                        length = i14;
                                                                    }
                                                                }
                                                                i15 = 0;
                                                                while (true) {
                                                                    if (i15 < i13 - i12) {
                                                                        i16 = i15;
                                                                        break;
                                                                    }
                                                                    i16 = i15;
                                                                    if (fVar2.charAt((i14 - 1) - i15) != charSequence.charAt((length2 - 1) - i16)) {
                                                                        break;
                                                                        break;
                                                                    }
                                                                    i15 = i16 + 1;
                                                                }
                                                                int i36 = (i14 - i16) - i12;
                                                                int i37 = (length2 - i16) - i12;
                                                                w1.t tVar10 = w1.q.B;
                                                                boolean zContainsKey6 = linkedHashMap.containsKey(tVar10);
                                                                boolean zContainsKey7 = linkedHashMap2.containsKey(tVar10);
                                                                zContainsKey = linkedHashMap.containsKey(w1.q.f6358w);
                                                                if (zContainsKey) {
                                                                    z4 = false;
                                                                } else {
                                                                    z4 = false;
                                                                }
                                                                if (zContainsKey) {
                                                                    z5 = false;
                                                                } else {
                                                                    z5 = false;
                                                                }
                                                                if (z4) {
                                                                    i10 = i27;
                                                                    accessibilityEventI = i(x(i27), num5, num5, Integer.valueOf(length2), charSequenceK);
                                                                    num7 = num5;
                                                                } else {
                                                                    i10 = i27;
                                                                    accessibilityEventI = i(x(i27), num5, num5, Integer.valueOf(length2), charSequenceK);
                                                                    num7 = num5;
                                                                }
                                                                accessibilityEventI.setClassName("android.widget.EditText");
                                                                z(accessibilityEventI);
                                                                if (z4) {
                                                                    long j13 = ((y1.g0) iVar2.b(w1.q.f6359x)).f6770a;
                                                                    accessibilityEventI.setFromIndex((int) (j13 >> 32));
                                                                    accessibilityEventI.setToIndex((int) (j13 & 4294967295L));
                                                                    z(accessibilityEventI);
                                                                } else {
                                                                    long j14 = ((y1.g0) iVar2.b(w1.q.f6359x)).f6770a;
                                                                    accessibilityEventI.setFromIndex((int) (j14 >> 32));
                                                                    accessibilityEventI.setToIndex((int) (j14 & 4294967295L));
                                                                    z(accessibilityEventI);
                                                                }
                                                            } else {
                                                                num7 = num7;
                                                                linkedHashMap = linkedHashMap;
                                                                num6 = num6;
                                                                i10 = i27;
                                                                B(this, x(i10), 2048, Integer.valueOf(i22), 8);
                                                            }
                                                            i27 = i10;
                                                            linkedHashMap = linkedHashMap;
                                                            num7 = num7;
                                                            nVar2 = nVar;
                                                            arrayList5 = arrayList3;
                                                            num6 = num6;
                                                        }
                                                    }
                                                    num7 = num7;
                                                    linkedHashMap = linkedHashMap;
                                                    num6 = num6;
                                                    i10 = i27;
                                                    i27 = i10;
                                                    linkedHashMap = linkedHashMap;
                                                    num7 = num7;
                                                    nVar2 = nVar;
                                                    arrayList5 = arrayList3;
                                                    num6 = num6;
                                                }
                                            }
                                        }
                                    }
                                    arrayList3 = arrayList5;
                                    nVar = nVar2;
                                    i10 = i27;
                                    linkedHashMap = linkedHashMap;
                                    i27 = i10;
                                    linkedHashMap = linkedHashMap;
                                    num7 = num7;
                                    nVar2 = nVar;
                                    arrayList5 = arrayList3;
                                    num6 = num6;
                                } else {
                                    tVar = (w1.t) entry.getKey();
                                    tVar2 = w1.q.f6339d;
                                    if (b4.l.a(tVar, tVar2)) {
                                        Object value12 = entry.getValue();
                                        b4.l.c("null cannot be cast to non-null type kotlin.String", value12);
                                        str = (String) value12;
                                        if (linkedHashMap.containsKey(tVar2)) {
                                            C(i27, 8, str);
                                        }
                                    } else {
                                        if (b4.l.a(tVar, w1.q.f6337b)) {
                                            zA = true;
                                        } else {
                                            zA = b4.l.a(tVar, w1.q.A);
                                        }
                                        if (zA) {
                                            B(this, x(i27), 2048, num6, 8);
                                            B(this, x(i27), 2048, num7, 8);
                                        } else {
                                            arrayList3 = arrayList5;
                                            if (b4.l.a(tVar, w1.q.f6338c)) {
                                                B(this, x(i27), 2048, num6, 8);
                                                B(this, x(i27), 2048, num7, 8);
                                                nVar = nVar2;
                                                i10 = i27;
                                                linkedHashMap = linkedHashMap;
                                                i27 = i10;
                                                linkedHashMap = linkedHashMap;
                                                num7 = num7;
                                                nVar2 = nVar;
                                                arrayList5 = arrayList3;
                                                num6 = num6;
                                            } else {
                                                tVar3 = w1.q.f6361z;
                                                if (b4.l.a(tVar, tVar3)) {
                                                    obj = linkedHashMap2.get(w1.q.f6353r);
                                                    if (obj == null) {
                                                        obj = null;
                                                    }
                                                    fVar = (w1.f) obj;
                                                    if (fVar == null) {
                                                        z3 = false;
                                                    } else {
                                                        z3 = true;
                                                    }
                                                    if (z3) {
                                                        obj2 = linkedHashMap2.get(tVar3);
                                                        if (obj2 == null) {
                                                            obj2 = null;
                                                        }
                                                        if (b4.l.a(obj2, Boolean.TRUE)) {
                                                            accessibilityEventH = h(x(i27), 4);
                                                            w1.n nVar7 = new w1.n(nVar2.f6315a, true, d0Var, iVar2);
                                                            obj3 = nVar7.i().f6308d.get(w1.q.f6336a);
                                                            if (obj3 == null) {
                                                                obj3 = null;
                                                            }
                                                            list = (List) obj3;
                                                            if (list != null) {
                                                                strS = h1.c.s(list, ",");
                                                            } else {
                                                                strS = null;
                                                            }
                                                            nVar = nVar2;
                                                            obj4 = nVar7.i().f6308d.get(w1.q.f6355t);
                                                            if (obj4 == null) {
                                                                obj4 = null;
                                                            }
                                                            list2 = (List) obj4;
                                                            if (list2 != null) {
                                                                strS2 = h1.c.s(list2, ",");
                                                            } else {
                                                                strS2 = null;
                                                            }
                                                            if (strS != null) {
                                                                accessibilityEventH.setContentDescription(strS);
                                                            }
                                                            if (strS2 != null) {
                                                                accessibilityEventH.getText().add(strS2);
                                                            }
                                                            z(accessibilityEventH);
                                                        } else {
                                                            nVar = nVar2;
                                                            B(this, x(i27), 2048, num7, 8);
                                                        }
                                                    } else {
                                                        nVar = nVar2;
                                                        B(this, x(i27), 2048, num6, 8);
                                                        B(this, x(i27), 2048, num7, 8);
                                                    }
                                                } else {
                                                    nVar = nVar2;
                                                    if (b4.l.a(tVar, w1.q.f6336a)) {
                                                        int iX4 = x(i27);
                                                        Object value13 = entry.getValue();
                                                        b4.l.c("null cannot be cast to non-null type kotlin.collections.List<kotlin.String>", value13);
                                                        A(iX4, 2048, 4, (List) value13);
                                                    } else {
                                                        tVar4 = w1.q.f6358w;
                                                        str2 = "";
                                                        if (b4.l.a(tVar, tVar4)) {
                                                            num7 = num7;
                                                            linkedHashMap = linkedHashMap;
                                                            num6 = num6;
                                                            i10 = i27;
                                                            tVar5 = w1.q.f6359x;
                                                            if (b4.l.a(tVar, tVar5)) {
                                                                obj7 = linkedHashMap2.get(tVar4);
                                                                if (obj7 == null) {
                                                                    obj7 = null;
                                                                }
                                                                fVar3 = (y1.f) obj7;
                                                                if (fVar3 != null) {
                                                                    str2 = str3;
                                                                }
                                                                long j15 = ((y1.g0) iVar2.b(tVar5)).f6770a;
                                                                z(i(x(i10), Integer.valueOf((int) (j15 >> 32)), Integer.valueOf((int) (j15 & 4294967295L)), Integer.valueOf(str2.length()), K(str2)));
                                                                D(i28);
                                                            } else {
                                                                if (b4.l.a(tVar, tVar7)) {
                                                                    zA2 = true;
                                                                } else {
                                                                    zA2 = b4.l.a(tVar, w1.q.f6351p);
                                                                }
                                                                if (zA2) {
                                                                    s(d0Var);
                                                                    size = arrayList4.size();
                                                                    i17 = 0;
                                                                    while (true) {
                                                                        if (i17 < size) {
                                                                            h2Var2 = null;
                                                                            break;
                                                                        } else {
                                                                            if (((h2) arrayList4.get(i17)).f5218d == i10) {
                                                                                h2Var2 = (h2) arrayList4.get(i17);
                                                                                break;
                                                                            }
                                                                            i17++;
                                                                        }
                                                                    }
                                                                    b4.l.b(h2Var2);
                                                                    obj8 = linkedHashMap2.get(tVar7);
                                                                    if (obj8 == null) {
                                                                        obj8 = null;
                                                                    }
                                                                    h2Var2.f5222h = (w1.g) obj8;
                                                                    obj9 = linkedHashMap2.get(w1.q.f6351p);
                                                                    if (obj9 == null) {
                                                                        obj9 = null;
                                                                    }
                                                                    h2Var2.f5223i = (w1.g) obj9;
                                                                    if (h2Var2.f5219e.contains(h2Var2)) {
                                                                        this.f5178d.getSnapshotObserver().a(h2Var2, this.M, new c0.t2(h2Var2, 7, this));
                                                                    }
                                                                } else if (b4.l.a(tVar, w1.q.f6346k)) {
                                                                    value = entry.getValue();
                                                                    b4.l.c("null cannot be cast to non-null type kotlin.Boolean", value);
                                                                    if (((Boolean) value).booleanValue()) {
                                                                        i18 = 8;
                                                                        z(h(x(i28), 8));
                                                                    } else {
                                                                        i18 = 8;
                                                                    }
                                                                    B(this, x(i28), 2048, num7, i18);
                                                                } else {
                                                                    tVar6 = w1.h.f6302u;
                                                                    if (b4.l.a(tVar, tVar6)) {
                                                                        list3 = (List) iVar2.b(tVar6);
                                                                        obj11 = linkedHashMap.get(tVar6);
                                                                        if (obj11 == null) {
                                                                            obj11 = null;
                                                                        }
                                                                        list4 = (List) obj11;
                                                                        if (list4 != null) {
                                                                            linkedHashSet = new LinkedHashSet();
                                                                            if (list3.size() <= 0) {
                                                                                list3.get(0).getClass();
                                                                                throw new ClassCastException();
                                                                            }
                                                                            linkedHashSet2 = new LinkedHashSet();
                                                                            if (list4.size() <= 0) {
                                                                                list4.get(0).getClass();
                                                                                throw new ClassCastException();
                                                                            }
                                                                            if (linkedHashSet.containsAll(linkedHashSet2)) {
                                                                                z7 = true;
                                                                            } else {
                                                                                z7 = true;
                                                                            }
                                                                        } else if (!list3.isEmpty()) {
                                                                            i27 = i10;
                                                                            linkedHashMap = linkedHashMap;
                                                                            num7 = num7;
                                                                            nVar2 = nVar;
                                                                            arrayList5 = arrayList3;
                                                                            num6 = num6;
                                                                            z7 = true;
                                                                        }
                                                                        i27 = i10;
                                                                        linkedHashMap = linkedHashMap;
                                                                        num7 = num7;
                                                                        nVar2 = nVar;
                                                                        arrayList5 = arrayList3;
                                                                        num6 = num6;
                                                                    } else {
                                                                        if (entry.getValue() instanceof w1.a) {
                                                                            Object value14 = entry.getValue();
                                                                            b4.l.c("null cannot be cast to non-null type androidx.compose.ui.semantics.AccessibilityAction<*>", value14);
                                                                            aVar = (w1.a) value14;
                                                                            obj10 = linkedHashMap.get((w1.t) entry.getKey());
                                                                            if (obj10 == null) {
                                                                                obj10 = null;
                                                                            }
                                                                            if (aVar != obj10) {
                                                                                if (obj10 instanceof w1.a) {
                                                                                    str4 = aVar.f6272a;
                                                                                    aVar2 = (w1.a) obj10;
                                                                                    p3.c cVar5 = aVar2.f6273b;
                                                                                    if (b4.l.a(str4, aVar2.f6272a)) {
                                                                                        z6 = true;
                                                                                    }
                                                                                }
                                                                                z6 = false;
                                                                            } else {
                                                                                z6 = true;
                                                                            }
                                                                            if (z6) {
                                                                                z7 = true;
                                                                            } else {
                                                                                z7 = false;
                                                                            }
                                                                        } else {
                                                                            z7 = true;
                                                                        }
                                                                        i27 = i10;
                                                                        linkedHashMap = linkedHashMap;
                                                                        num7 = num7;
                                                                        nVar2 = nVar;
                                                                        arrayList5 = arrayList3;
                                                                        num6 = num6;
                                                                    }
                                                                }
                                                            }
                                                        } else if (linkedHashMap2.containsKey(w1.h.f6289h)) {
                                                            obj5 = linkedHashMap.get(tVar4);
                                                            if (obj5 == null) {
                                                                obj5 = null;
                                                            }
                                                            fVar2 = (y1.f) obj5;
                                                            if (fVar2 == null) {
                                                                fVar2 = "";
                                                            }
                                                            obj6 = linkedHashMap2.get(tVar4);
                                                            if (obj6 == null) {
                                                                obj6 = null;
                                                            }
                                                            charSequence = (y1.f) obj6;
                                                            if (charSequence == null) {
                                                                charSequence = "";
                                                            }
                                                            charSequenceK = K(charSequence);
                                                            length = fVar2.length();
                                                            length2 = charSequence.length();
                                                            num5 = num7;
                                                            if (length > length2) {
                                                                i11 = length2;
                                                            } else {
                                                                i11 = length;
                                                            }
                                                            num6 = num6;
                                                            i12 = 0;
                                                            while (true) {
                                                                i13 = i11;
                                                                if (i12 < i11) {
                                                                    i14 = length;
                                                                    break;
                                                                }
                                                                i14 = length;
                                                                if (fVar2.charAt(i12) != charSequence.charAt(i12)) {
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    i12++;
                                                                    i11 = i13;
                                                                    length = i14;
                                                                }
                                                            }
                                                            i15 = 0;
                                                            while (true) {
                                                                if (i15 < i13 - i12) {
                                                                    i16 = i15;
                                                                    break;
                                                                }
                                                                i16 = i15;
                                                                if (fVar2.charAt((i14 - 1) - i15) != charSequence.charAt((length2 - 1) - i16)) {
                                                                    break;
                                                                    break;
                                                                }
                                                                i15 = i16 + 1;
                                                            }
                                                            int i38 = (i14 - i16) - i12;
                                                            int i39 = (length2 - i16) - i12;
                                                            w1.t tVar11 = w1.q.B;
                                                            boolean zContainsKey8 = linkedHashMap.containsKey(tVar11);
                                                            boolean zContainsKey9 = linkedHashMap2.containsKey(tVar11);
                                                            zContainsKey = linkedHashMap.containsKey(w1.q.f6358w);
                                                            if (zContainsKey) {
                                                                z4 = false;
                                                            } else {
                                                                z4 = false;
                                                            }
                                                            if (zContainsKey) {
                                                                z5 = false;
                                                            } else {
                                                                z5 = false;
                                                            }
                                                            if (z4) {
                                                                i10 = i27;
                                                                accessibilityEventI = i(x(i27), num5, num5, Integer.valueOf(length2), charSequenceK);
                                                                num7 = num5;
                                                            } else {
                                                                i10 = i27;
                                                                accessibilityEventI = i(x(i27), num5, num5, Integer.valueOf(length2), charSequenceK);
                                                                num7 = num5;
                                                            }
                                                            accessibilityEventI.setClassName("android.widget.EditText");
                                                            z(accessibilityEventI);
                                                            if (z4) {
                                                                long j16 = ((y1.g0) iVar2.b(w1.q.f6359x)).f6770a;
                                                                accessibilityEventI.setFromIndex((int) (j16 >> 32));
                                                                accessibilityEventI.setToIndex((int) (j16 & 4294967295L));
                                                                z(accessibilityEventI);
                                                            } else {
                                                                long j17 = ((y1.g0) iVar2.b(w1.q.f6359x)).f6770a;
                                                                accessibilityEventI.setFromIndex((int) (j17 >> 32));
                                                                accessibilityEventI.setToIndex((int) (j17 & 4294967295L));
                                                                z(accessibilityEventI);
                                                            }
                                                        } else {
                                                            num7 = num7;
                                                            linkedHashMap = linkedHashMap;
                                                            num6 = num6;
                                                            i10 = i27;
                                                            B(this, x(i10), 2048, Integer.valueOf(i22), 8);
                                                        }
                                                        i27 = i10;
                                                        linkedHashMap = linkedHashMap;
                                                        num7 = num7;
                                                        nVar2 = nVar;
                                                        arrayList5 = arrayList3;
                                                        num6 = num6;
                                                    }
                                                }
                                                num7 = num7;
                                                linkedHashMap = linkedHashMap;
                                                num6 = num6;
                                                i10 = i27;
                                                i27 = i10;
                                                linkedHashMap = linkedHashMap;
                                                num7 = num7;
                                                nVar2 = nVar;
                                                arrayList5 = arrayList3;
                                                num6 = num6;
                                            }
                                        }
                                    }
                                    arrayList3 = arrayList5;
                                    nVar = nVar2;
                                    i10 = i27;
                                    linkedHashMap = linkedHashMap;
                                    i27 = i10;
                                    linkedHashMap = linkedHashMap;
                                    num7 = num7;
                                    nVar2 = nVar;
                                    arrayList5 = arrayList3;
                                    num6 = num6;
                                }
                            }
                        }
                    } else {
                        i26 = i26;
                        num3 = num6;
                        arrayList2 = arrayList5;
                        iArr2 = iArr3;
                        jArr2 = jArr3;
                        i7 = i24;
                        i8 = i20;
                        i9 = i21;
                        num4 = num7;
                    }
                    j6 >>= i7;
                    i26++;
                    rVar2 = rVar;
                    i20 = i8;
                    i24 = i7;
                    num7 = num4;
                    iArr3 = iArr2;
                    jArr3 = jArr2;
                    i21 = i9;
                    arrayList5 = arrayList2;
                    num6 = num3;
                }
                num = num6;
                arrayList = arrayList5;
                iArr = iArr3;
                jArr = jArr3;
                i5 = i20;
                i6 = i21;
                num2 = num7;
                if (i25 != i24) {
                    return;
                }
            } else {
                num = num6;
                arrayList = arrayList5;
                iArr = iArr3;
                jArr = jArr3;
                i5 = i20;
                i6 = i21;
                num2 = num7;
            }
            int i40 = i6;
            if (i40 == i23) {
                return;
            }
            Integer num8 = num2;
            i21 = i40 + 1;
            num7 = num8;
            rVar2 = rVar;
            length3 = i23;
            i20 = i5;
            i19 = i22;
            iArr3 = iArr;
            jArr3 = jArr;
            arrayList5 = arrayList;
            num6 = num;
        }
    }

    public final void F(p1.d0 d0Var, j.s sVar) {
        w1.i iVarN;
        if (d0Var.D() && !this.f5178d.getAndroidViewsHandler$ui_release().getLayoutNodeToHolder().containsKey(d0Var)) {
            j.g gVar = this.f5197w;
            int i5 = gVar.f3105f;
            for (int i6 = 0; i6 < i5; i6++) {
                if (m0.p((p1.d0) gVar.f3104e[i6], d0Var)) {
                    return;
                }
            }
            Trace.beginSection("GetSemanticsNode");
            try {
                p1.d0 d0Var2 = null;
                if (!d0Var.f4801y.d(8)) {
                    d0Var = d0Var.r();
                    while (true) {
                        if (d0Var == null) {
                            d0Var = null;
                            break;
                        } else if (d0Var.f4801y.d(8)) {
                            break;
                        } else {
                            d0Var = d0Var.r();
                        }
                    }
                }
                if (d0Var != null && (iVarN = d0Var.n()) != null) {
                    if (!iVarN.f6309e) {
                        for (p1.d0 d0VarR = d0Var.r(); d0VarR != null; d0VarR = d0VarR.r()) {
                            w1.i iVarN2 = d0VarR.n();
                            if (iVarN2 != null && iVarN2.f6309e) {
                                d0Var2 = d0VarR;
                                break;
                            }
                        }
                        if (d0Var2 != null) {
                            d0Var = d0Var2;
                        }
                    }
                    int i7 = d0Var.f4781e;
                    Trace.endSection();
                    if (sVar.a(i7)) {
                        B(this, x(i7), 2048, 1, 8);
                        return;
                    }
                    return;
                }
                Trace.endSection();
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }
    }

    public final void G(p1.d0 d0Var) {
        if (d0Var.D() && !this.f5178d.getAndroidViewsHandler$ui_release().getLayoutNodeToHolder().containsKey(d0Var)) {
            int i5 = d0Var.f4781e;
            w1.g gVar = (w1.g) this.f5191q.d(i5);
            w1.g gVar2 = (w1.g) this.f5192r.d(i5);
            if (gVar == null && gVar2 == null) {
                return;
            }
            AccessibilityEvent accessibilityEventH = h(i5, 4096);
            if (gVar != null) {
                accessibilityEventH.setScrollX((int) ((Number) gVar.f6280a.a()).floatValue());
                accessibilityEventH.setMaxScrollX((int) ((Number) gVar.f6281b.a()).floatValue());
            }
            if (gVar2 != null) {
                accessibilityEventH.setScrollY((int) ((Number) gVar2.f6280a.a()).floatValue());
                accessibilityEventH.setMaxScrollY((int) ((Number) gVar2.f6281b.a()).floatValue());
            }
            z(accessibilityEventH);
        }
    }

    public final boolean H(w1.n nVar, int i5, int i6, boolean z2) {
        String strP;
        w1.i iVar = nVar.f6318d;
        int i7 = nVar.f6321g;
        w1.t tVar = w1.h.f6288g;
        if (iVar.f6308d.containsKey(tVar) && m0.h(nVar)) {
            a4.f fVar = (a4.f) ((w1.a) nVar.f6318d.b(tVar)).f6273b;
            if (fVar != null) {
                return ((Boolean) fVar.b(Integer.valueOf(i5), Integer.valueOf(i6), Boolean.valueOf(z2))).booleanValue();
            }
        } else if ((i5 != i6 || i6 != this.f5195u) && (strP = p(nVar)) != null) {
            if (i5 < 0 || i5 != i6 || i6 > strP.length()) {
                i5 = -1;
            }
            this.f5195u = i5;
            boolean z3 = strP.length() > 0;
            z(i(x(i7), z3 ? Integer.valueOf(this.f5195u) : null, z3 ? Integer.valueOf(this.f5195u) : null, z3 ? Integer.valueOf(strP.length()) : null, strP));
            D(i7);
            return true;
        }
        return false;
    }

    public final void I() {
        j.p pVar = this.C;
        pVar.a();
        j.p pVar2 = this.D;
        pVar2.a();
        j2 j2Var = (j2) m().d(-1);
        w1.n nVar = j2Var != null ? j2Var.f5252a : null;
        b4.l.b(nVar);
        ArrayList arrayListJ = J(m0.i(nVar), p3.j.I(nVar));
        int iZ = p3.j.z(arrayListJ);
        int i5 = 1;
        if (1 > iZ) {
            return;
        }
        while (true) {
            int i6 = ((w1.n) arrayListJ.get(i5 - 1)).f6321g;
            int i7 = ((w1.n) arrayListJ.get(i5)).f6321g;
            pVar.e(i6, i7);
            pVar2.e(i7, i6);
            if (i5 == iZ) {
                return;
            } else {
                i5++;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:28:0x00ba A[EDGE_INSN: B:28:0x00ba->B:29:0x00ce BREAK  A[LOOP:2: B:17:0x0052->B:27:0x00b7]] */
    public final ArrayList J(boolean z2, ArrayList arrayList) {
        j.r rVar = j.j.f3115a;
        j.r rVar2 = new j.r();
        ArrayList arrayList2 = new ArrayList();
        int size = arrayList.size();
        for (int i5 = 0; i5 < size; i5++) {
            j((w1.n) arrayList.get(i5), arrayList2, rVar2);
        }
        ArrayList arrayList3 = new ArrayList();
        int iZ = p3.j.z(arrayList2);
        if (iZ >= 0) {
            int i6 = 0;
            while (true) {
                w1.n nVar = (w1.n) arrayList2.get(i6);
                if (i6 == 0) {
                    arrayList3.add(new p3.f(nVar.f(), p3.j.I(nVar)));
                    break;
                }
                float f5 = nVar.f().f6535b;
                float f6 = nVar.f().f6537d;
                boolean z3 = f5 >= f6;
                int iZ2 = p3.j.z(arrayList3);
                if (iZ2 < 0) {
                    arrayList3.add(new p3.f(nVar.f(), p3.j.I(nVar)));
                    break;
                }
                int i7 = 0;
                while (true) {
                    x0.d dVar = (x0.d) ((p3.f) arrayList3.get(i7)).f5014d;
                    float f7 = dVar.f6535b;
                    float f8 = dVar.f6537d;
                    boolean z4 = f7 >= f8;
                    if (!z3 && !z4 && Math.max(f5, f7) < Math.min(f6, f8)) {
                        arrayList3.set(i7, new p3.f(new x0.d(Math.max(dVar.f6534a, 0.0f), Math.max(dVar.f6535b, f5), Math.min(dVar.f6536c, Float.POSITIVE_INFINITY), Math.min(f8, f6)), ((p3.f) arrayList3.get(i7)).f5015e));
                        ((List) ((p3.f) arrayList3.get(i7)).f5015e).add(nVar);
                        break;
                    }
                    if (i7 == iZ2) {
                        arrayList3.add(new p3.f(nVar.f(), p3.j.I(nVar)));
                        break;
                    }
                    i7++;
                }
                if (i6 == iZ) {
                    break;
                }
                i6++;
            }
        }
        q3.p.T(arrayList3, a0.f5107d);
        ArrayList arrayList4 = new ArrayList();
        int size2 = arrayList3.size();
        for (int i8 = 0; i8 < size2; i8++) {
            p3.f fVar = (p3.f) arrayList3.get(i8);
            q3.p.T((List) fVar.f5015e, new e0(new e0(z2 ? a0.f5106c : a0.f5105b)));
            arrayList4.addAll((Collection) fVar.f5015e);
        }
        q3.p.T(arrayList4, new f0.w(2));
        int size3 = 0;
        while (size3 <= p3.j.z(arrayList4)) {
            List list = (List) rVar2.d(((w1.n) arrayList4.get(size3)).f6321g);
            if (list != null) {
                if (r((w1.n) arrayList4.get(size3))) {
                    size3++;
                } else {
                    arrayList4.remove(size3);
                }
                arrayList4.addAll(size3, list);
                size3 += list.size();
            } else {
                size3++;
            }
        }
        return arrayList4;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0064  */
    /* JADX WARN: Code duplicated, block: B:20:0x006f  */
    /* JADX WARN: Code duplicated, block: B:23:0x007c  */
    /* JADX WARN: Multi-variable type inference failed */
    public final void L() {
        long j5;
        long j6;
        long j7;
        char c5;
        long[] jArr;
        long[] jArr2;
        long j8;
        int i5;
        int iNumberOfTrailingZeros;
        char c6;
        i2 i2Var;
        j.s sVar = new j.s();
        j.s sVar2 = this.B;
        int[] iArr = sVar2.f3145b;
        long[] jArr3 = sVar2.f3144a;
        int length = jArr3.length - 2;
        j.r rVar = this.H;
        int i6 = 8;
        if (length >= 0) {
            int i7 = 0;
            j5 = 128;
            j6 = 255;
            while (true) {
                long j9 = jArr3[i7];
                char c7 = 7;
                j7 = -9187201950435737472L;
                if ((((~j9) << 7) & j9 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i8 = 8 - ((~(i7 - length)) >>> 31);
                    int i9 = 0;
                    while (i9 < i8) {
                        if ((j9 & 255) < 128) {
                            int i10 = iArr[(i7 << 3) + i9];
                            c6 = c7;
                            j2 j2Var = (j2) m().d(i10);
                            Object obj = null;
                            w1.n nVar = j2Var != null ? j2Var.f5252a : null;
                            if (nVar != null) {
                                if (!nVar.f6318d.f6308d.containsKey(w1.q.f6339d)) {
                                    sVar.a(i10);
                                    i2Var = (i2) rVar.d(i10);
                                    if (i2Var != null) {
                                        Object obj2 = i2Var.f5228a.f6308d.get(w1.q.f6339d);
                                        obj = (String) (obj2 != null ? obj2 : null);
                                    }
                                    C(i10, 32, obj);
                                }
                            } else {
                                sVar.a(i10);
                                i2Var = (i2) rVar.d(i10);
                                if (i2Var != null) {
                                    Object obj3 = i2Var.f5228a.f6308d.get(w1.q.f6339d);
                                    obj = (String) (obj3 != null ? obj3 : null);
                                }
                                C(i10, 32, obj);
                            }
                        } else {
                            c6 = c7;
                        }
                        j9 >>= 8;
                        i9++;
                        c7 = c6;
                    }
                    c5 = c7;
                    if (i8 != 8) {
                        break;
                    }
                } else {
                    c5 = 7;
                }
                if (i7 == length) {
                    break;
                } else {
                    i7++;
                }
            }
        } else {
            j5 = 128;
            j6 = 255;
            j7 = -9187201950435737472L;
            c5 = 7;
        }
        int[] iArr2 = sVar.f3145b;
        long[] jArr4 = sVar.f3144a;
        int length2 = jArr4.length - 2;
        if (length2 >= 0) {
            int i11 = 0;
            while (true) {
                long j10 = jArr4[i11];
                if ((((~j10) << c5) & j10 & j7) != j7) {
                    int i12 = 8 - ((~(i11 - length2)) >>> 31);
                    int i13 = 0;
                    while (i13 < i12) {
                        if ((j10 & j6) < j5) {
                            int i14 = iArr2[(i11 << 3) + i13];
                            int iHashCode = Integer.hashCode(i14) * (-862048943);
                            int i15 = iHashCode ^ (iHashCode << 16);
                            int i16 = i15 & 127;
                            int i17 = sVar2.f3146c;
                            int i18 = (i15 >>> 7) & i17;
                            i5 = i6;
                            int i19 = 0;
                            while (true) {
                                long[] jArr5 = sVar2.f3144a;
                                int i20 = i18 >> 3;
                                jArr2 = jArr4;
                                int i21 = (i18 & 7) << 3;
                                j8 = j10;
                                long j11 = (jArr5[i20] >>> i21) | ((jArr5[i20 + 1] << (64 - i21)) & ((-i21) >> 63));
                                int i22 = i17;
                                long j12 = (((long) i16) * 72340172838076673L) ^ j11;
                                long j13 = (j12 - 72340172838076673L) & (~j12) & j7;
                                while (j13 != 0) {
                                    iNumberOfTrailingZeros = (i18 + (Long.numberOfTrailingZeros(j13) >> 3)) & i22;
                                    int i23 = i22;
                                    if (sVar2.f3145b[iNumberOfTrailingZeros] == i14) {
                                        break;
                                    }
                                    j13 &= j13 - 1;
                                    i22 = i23;
                                }
                                int i24 = i22;
                                if ((j11 & ((~j11) << 6) & j7) != 0) {
                                    iNumberOfTrailingZeros = -1;
                                    break;
                                }
                                i19 += 8;
                                i18 = (i18 + i19) & i24;
                                jArr4 = jArr2;
                                i17 = i24;
                                j10 = j8;
                            }
                            int i25 = iNumberOfTrailingZeros;
                            if (i25 >= 0) {
                                sVar2.g(i25);
                            }
                        } else {
                            jArr2 = jArr4;
                            j8 = j10;
                            i5 = i6;
                        }
                        j10 = j8 >> i5;
                        i13++;
                        i6 = i5;
                        jArr4 = jArr2;
                    }
                    jArr = jArr4;
                    if (i12 != i6) {
                        break;
                    }
                } else {
                    jArr = jArr4;
                }
                if (i11 == length2) {
                    break;
                }
                i11++;
                jArr4 = jArr;
                i6 = 8;
            }
        }
        rVar.a();
        j.r rVarM = m();
        int[] iArr3 = rVarM.f3139b;
        Object[] objArr = rVarM.f3140c;
        long[] jArr6 = rVarM.f3138a;
        int length3 = jArr6.length - 2;
        if (length3 >= 0) {
            int i26 = 0;
            while (true) {
                long j14 = jArr6[i26];
                if ((((~j14) << c5) & j14 & j7) != j7) {
                    int i27 = 8 - ((~(i26 - length3)) >>> 31);
                    for (int i28 = 0; i28 < i27; i28++) {
                        if ((j14 & j6) < j5) {
                            int i29 = (i26 << 3) + i28;
                            int i30 = iArr3[i29];
                            w1.n nVar2 = ((j2) objArr[i29]).f5252a;
                            w1.i iVar = nVar2.f6318d;
                            w1.t tVar = w1.q.f6339d;
                            if (iVar.f6308d.containsKey(tVar) && sVar2.a(i30)) {
                                C(i30, 16, (String) nVar2.f6318d.b(tVar));
                            }
                            rVar.g(i30, new i2(nVar2, m()));
                        }
                        j14 >>= 8;
                    }
                    if (i27 != 8) {
                        break;
                    }
                }
                if (i26 == length3) {
                    break;
                } else {
                    i26++;
                }
            }
        }
        this.I = new i2(this.f5178d.getSemanticsOwner().a(), m());
    }

    @Override // w2.b
    public final a2.h a(View view) {
        return this.f5187m;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void c(int i5, x2.h hVar, String str, Bundle bundle) {
        w1.n nVar;
        x0.d dVar;
        RectF rectF;
        x0.d dVarD;
        AccessibilityNodeInfo accessibilityNodeInfo = hVar.f6563a;
        j2 j2Var = (j2) m().d(i5);
        if (j2Var == null || (nVar = j2Var.f5252a) == null) {
            return;
        }
        w1.i iVar = nVar.f6318d;
        LinkedHashMap linkedHashMap = iVar.f6308d;
        String strP = p(nVar);
        if (b4.l.a(str, this.E)) {
            j.p pVar = this.C;
            int iC = pVar.c(i5);
            int i6 = iC >= 0 ? pVar.f3132c[iC] : -1;
            if (i6 != -1) {
                accessibilityNodeInfo.getExtras().putInt(str, i6);
                return;
            }
            return;
        }
        if (b4.l.a(str, this.F)) {
            j.p pVar2 = this.D;
            int iC2 = pVar2.c(i5);
            int i7 = iC2 >= 0 ? pVar2.f3132c[iC2] : -1;
            if (i7 != -1) {
                accessibilityNodeInfo.getExtras().putInt(str, i7);
                return;
            }
            return;
        }
        p1.y0 y0Var = null;
        if (!linkedHashMap.containsKey(w1.h.f6282a) || bundle == null || !b4.l.a(str, "android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY")) {
            w1.t tVar = w1.q.f6354s;
            if (!linkedHashMap.containsKey(tVar) || bundle == null || !b4.l.a(str, "androidx.compose.ui.semantics.testTag")) {
                if (b4.l.a(str, "androidx.compose.ui.semantics.id")) {
                    accessibilityNodeInfo.getExtras().putInt(str, nVar.f6321g);
                    return;
                }
                return;
            } else {
                Object obj = linkedHashMap.get(tVar);
                String str2 = (String) (obj == null ? null : obj);
                if (str2 != null) {
                    accessibilityNodeInfo.getExtras().putCharSequence(str, str2);
                    return;
                }
                return;
            }
        }
        int i8 = bundle.getInt("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX", -1);
        int i9 = bundle.getInt("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH", -1);
        if (i9 > 0 && i8 >= 0) {
            if (i8 < (strP != null ? strP.length() : Integer.MAX_VALUE)) {
                y1.e0 e0VarN = m0.n(iVar);
                if (e0VarN == null) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                int i10 = 0;
                while (i10 < i9) {
                    int i11 = i8 + i10;
                    if (i11 >= e0VarN.f6753a.f6743a.f6759d.length()) {
                        arrayList.add(y0Var);
                        i10 = i10;
                    } else {
                        x0.d dVarB = e0VarN.b(i11);
                        p1.y0 y0VarC = nVar.c();
                        long jZ = 0;
                        if (y0VarC != null) {
                            if (!y0VarC.L0().f5693p) {
                                y0VarC = y0Var;
                            }
                            if (y0VarC != null) {
                                jZ = y0VarC.Z(0L);
                            }
                        }
                        x0.d dVarH = dVarB.h(jZ);
                        x0.d dVarE = nVar.e();
                        if (dVarH.f(dVarE)) {
                            dVarD = dVarH.d(dVarE);
                        } else {
                            dVar = y0Var;
                        }
                        if (dVar != 0) {
                            dVar = dVarD;
                            long jB = p3.j.b(dVar.f6534a, dVar.f6535b);
                            u uVar = this.f5178d;
                            long jQ = uVar.q(jB);
                            long jQ2 = uVar.q(p3.j.b(dVar.f6536c, dVar.f6537d));
                            rectF = new RectF(x0.c.d(jQ), x0.c.e(jQ), x0.c.d(jQ2), x0.c.e(jQ2));
                        } else {
                            dVar = dVarD;
                            rectF = null;
                        }
                        arrayList.add(rectF);
                    }
                    i10++;
                    y0Var = null;
                }
                accessibilityNodeInfo.getExtras().putParcelableArray(str, (Parcelable[]) arrayList.toArray(new RectF[0]));
                return;
            }
        }
        Log.e("AccessibilityDelegate", "Invalid arguments for accessibility character locations");
    }

    public final Rect d(j2 j2Var) {
        Rect rect = j2Var.f5253b;
        long jB = p3.j.b(rect.left, rect.top);
        u uVar = this.f5178d;
        long jQ = uVar.q(jB);
        long jQ2 = uVar.q(p3.j.b(rect.right, rect.bottom));
        return new Rect((int) Math.floor(x0.c.d(jQ)), (int) Math.floor(x0.c.e(jQ)), (int) Math.ceil(x0.c.d(jQ2)), (int) Math.ceil(x0.c.e(jQ2)));
    }

    /* JADX WARN: Code duplicated, block: B:29:0x006c  */
    /* JADX WARN: Code duplicated, block: B:30:0x006e  */
    /* JADX WARN: Code duplicated, block: B:33:0x007a A[Catch: all -> 0x0048, TRY_LEAVE, TryCatch #2 {all -> 0x0048, blocks: (B:56:0x00e6, B:27:0x005e, B:31:0x0072, B:33:0x007a, B:57:0x00eb, B:58:0x00ee, B:20:0x0044, B:13:0x002c, B:34:0x0082, B:37:0x008a, B:39:0x008f, B:41:0x009d, B:43:0x00a8, B:45:0x00af, B:46:0x00b2, B:48:0x00b4, B:49:0x00b7, B:50:0x00b8, B:52:0x00bf, B:53:0x00c8, B:40:0x009a, B:42:0x00a5), top: B:69:0x0020, inners: #1 }] */
    /* JADX WARN: Code duplicated, block: B:37:0x008a A[Catch: all -> 0x0033, TRY_ENTER, TryCatch #1 {all -> 0x0033, blocks: (B:13:0x002c, B:34:0x0082, B:37:0x008a, B:39:0x008f, B:41:0x009d, B:43:0x00a8, B:45:0x00af, B:46:0x00b2, B:48:0x00b4, B:49:0x00b7, B:50:0x00b8, B:52:0x00bf, B:53:0x00c8, B:40:0x009a, B:42:0x00a5), top: B:68:0x002c, outer: #2, inners: #0, #4 }] */
    /* JADX WARN: Code duplicated, block: B:39:0x008f A[Catch: all -> 0x0033, TRY_LEAVE, TryCatch #1 {all -> 0x0033, blocks: (B:13:0x002c, B:34:0x0082, B:37:0x008a, B:39:0x008f, B:41:0x009d, B:43:0x00a8, B:45:0x00af, B:46:0x00b2, B:48:0x00b4, B:49:0x00b7, B:50:0x00b8, B:52:0x00bf, B:53:0x00c8, B:40:0x009a, B:42:0x00a5), top: B:68:0x002c, outer: #2, inners: #0, #4 }] */
    /* JADX WARN: Code duplicated, block: B:52:0x00bf A[Catch: all -> 0x0033, TryCatch #1 {all -> 0x0033, blocks: (B:13:0x002c, B:34:0x0082, B:37:0x008a, B:39:0x008f, B:41:0x009d, B:43:0x00a8, B:45:0x00af, B:46:0x00b2, B:48:0x00b4, B:49:0x00b7, B:50:0x00b8, B:52:0x00bf, B:53:0x00c8, B:40:0x009a, B:42:0x00a5), top: B:68:0x002c, outer: #2, inners: #0, #4 }] */
    /* JADX WARN: Code duplicated, block: B:59:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00e3, code lost:
    
        if (i4.y.d(r7, r0) == r4) goto L55;
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:54:0x00e3 -> B:14:0x002f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object e(v3.c r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 257
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: q1.f0.e(v3.c):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:46:0x00d1  */
    public final boolean f(boolean z2, int i5, long j5) {
        w1.t tVar;
        long[] jArr;
        long[] jArr2;
        int i6;
        if (!b4.l.a(Looper.getMainLooper().getThread(), Thread.currentThread())) {
            return false;
        }
        j.r rVarM = m();
        if (x0.c.b(j5, 9205357640488583168L) || !x0.c.f(j5)) {
            return false;
        }
        if (z2) {
            tVar = w1.q.f6351p;
        } else {
            if (z2) {
                throw new i4.p();
            }
            tVar = w1.q.f6350o;
        }
        Object[] objArr = rVarM.f3140c;
        long[] jArr3 = rVarM.f3138a;
        int length = jArr3.length - 2;
        if (length < 0) {
            return false;
        }
        int i7 = 0;
        boolean z3 = false;
        while (true) {
            long j6 = jArr3[i7];
            if ((((~j6) << 7) & j6 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i8 = 8;
                int i9 = 8 - ((~(i7 - length)) >>> 31);
                int i10 = 0;
                while (i10 < i9) {
                    if ((255 & j6) < 128) {
                        j2 j2Var = (j2) objArr[(i7 << 3) + i10];
                        Rect rect = j2Var.f5253b;
                        i6 = i8;
                        jArr2 = jArr3;
                        if (x0.c.d(j5) >= ((float) rect.left) && x0.c.d(j5) < ((float) rect.right) && x0.c.e(j5) >= ((float) rect.top) && x0.c.e(j5) < ((float) rect.bottom)) {
                            Object obj = j2Var.f5252a.f6318d.f6308d.get(tVar);
                            if (obj == null) {
                                obj = null;
                            }
                            w1.g gVar = (w1.g) obj;
                            if (gVar != null) {
                                n.d1 d1Var = gVar.f6280a;
                                if (i5 < 0) {
                                    if (((Number) d1Var.a()).floatValue() > 0.0f) {
                                        z3 = true;
                                    }
                                } else if (((Number) d1Var.a()).floatValue() < ((Number) gVar.f6281b.a()).floatValue()) {
                                    z3 = true;
                                }
                            }
                        }
                    } else {
                        jArr2 = jArr3;
                        i6 = i8;
                    }
                    j6 >>= i6;
                    i10++;
                    i8 = i6;
                    jArr3 = jArr2;
                }
                jArr = jArr3;
                if (i9 != i8) {
                    return z3;
                }
            } else {
                jArr = jArr3;
            }
            if (i7 == length) {
                return z3;
            }
            i7++;
            jArr3 = jArr;
        }
    }

    public final void g() {
        Trace.beginSection("sendAccessibilitySemanticsStructureChangeEvents");
        try {
            if (q()) {
                y(this.f5178d.getSemanticsOwner().a(), this.I);
            }
            Trace.endSection();
            Trace.beginSection("sendSemanticsPropertyChangeEvents");
            try {
                E(m());
                Trace.endSection();
                Trace.beginSection("updateSemanticsNodesCopyAndPanes");
                try {
                    L();
                } finally {
                    Trace.endSection();
                }
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        } catch (Throwable th2) {
            Trace.endSection();
            throw th2;
        }
    }

    public final AccessibilityEvent h(int i5, int i6) {
        j2 j2Var;
        u uVar = this.f5178d;
        Trace.beginSection("obtainAccessibilityEvent");
        try {
            AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i6);
            Trace.endSection();
            accessibilityEventObtain.setEnabled(true);
            accessibilityEventObtain.setClassName("android.view.View");
            Trace.beginSection("event.packageName");
            try {
                accessibilityEventObtain.setPackageName(uVar.getContext().getPackageName());
                Trace.endSection();
                Trace.beginSection("event.setSource");
                try {
                    accessibilityEventObtain.setSource(uVar, i5);
                    Trace.endSection();
                    if (q() && (j2Var = (j2) m().d(i5)) != null) {
                        accessibilityEventObtain.setPassword(j2Var.f5252a.f6318d.f6308d.containsKey(w1.q.B));
                    }
                    return accessibilityEventObtain;
                } catch (Throwable th) {
                    Trace.endSection();
                    throw th;
                }
            } catch (Throwable th2) {
                Trace.endSection();
                throw th2;
            }
        } catch (Throwable th3) {
            Trace.endSection();
            throw th3;
        }
    }

    public final AccessibilityEvent i(int i5, Integer num, Integer num2, Integer num3, CharSequence charSequence) {
        AccessibilityEvent accessibilityEventH = h(i5, 8192);
        if (num != null) {
            accessibilityEventH.setFromIndex(num.intValue());
        }
        if (num2 != null) {
            accessibilityEventH.setToIndex(num2.intValue());
        }
        if (num3 != null) {
            accessibilityEventH.setItemCount(num3.intValue());
        }
        if (charSequence != null) {
            accessibilityEventH.getText().add(charSequence);
        }
        return accessibilityEventH;
    }

    public final void j(w1.n nVar, ArrayList arrayList, j.r rVar) {
        boolean zI = m0.i(nVar);
        int i5 = nVar.f6321g;
        w1.i iVar = nVar.f6318d;
        Object obj = iVar.f6308d.get(w1.q.f6347l);
        if (obj == null) {
            obj = Boolean.FALSE;
        }
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        if ((zBooleanValue || r(nVar)) && m().b(i5)) {
            arrayList.add(nVar);
        }
        if (zBooleanValue) {
            rVar.g(i5, J(zI, q3.l.g0(w1.n.h(nVar, 7))));
            return;
        }
        List listH = w1.n.h(nVar, 7);
        int size = listH.size();
        for (int i6 = 0; i6 < size; i6++) {
            j((w1.n) listH.get(i6), arrayList, rVar);
        }
    }

    public final int k(w1.n nVar) {
        w1.i iVar = nVar.f6318d;
        if (!iVar.f6308d.containsKey(w1.q.f6336a)) {
            w1.t tVar = w1.q.f6359x;
            if (iVar.f6308d.containsKey(tVar)) {
                return (int) (((y1.g0) iVar.b(tVar)).f6770a & 4294967295L);
            }
        }
        return this.f5195u;
    }

    public final int l(w1.n nVar) {
        w1.i iVar = nVar.f6318d;
        if (!iVar.f6308d.containsKey(w1.q.f6336a)) {
            w1.t tVar = w1.q.f6359x;
            if (iVar.f6308d.containsKey(tVar)) {
                return (int) (((y1.g0) iVar.b(tVar)).f6770a >> 32);
            }
        }
        return this.f5195u;
    }

    public final j.r m() {
        if (this.f5199y) {
            this.f5199y = false;
            Trace.beginSection("generateCurrentSemanticsNodes");
            try {
                j.r rVarL = m0.l(this.f5178d.getSemanticsOwner());
                Trace.endSection();
                this.A = rVarL;
                if (q()) {
                    Trace.beginSection("setTraversalValues");
                    try {
                        I();
                    } finally {
                        Trace.endSection();
                    }
                }
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }
        return this.A;
    }

    public final String o(w1.n nVar) {
        w1.i iVar = nVar.f6318d;
        w1.i iVar2 = nVar.f6318d;
        Object string = iVar.f6308d.get(w1.q.f6337b);
        String string2 = null;
        if (string == null) {
            string = null;
        }
        LinkedHashMap linkedHashMap = iVar2.f6308d;
        Object obj = linkedHashMap.get(w1.q.A);
        if (obj == null) {
            obj = null;
        }
        x1.a aVar = (x1.a) obj;
        Object obj2 = linkedHashMap.get(w1.q.f6353r);
        if (obj2 == null) {
            obj2 = null;
        }
        w1.f fVar = (w1.f) obj2;
        u uVar = this.f5178d;
        if (aVar != null) {
            int iOrdinal = aVar.ordinal();
            if (iOrdinal != 0) {
                if (iOrdinal != 1) {
                    if (iOrdinal == 2 && string == null) {
                        string = uVar.getContext().getResources().getString(R.string.indeterminate);
                    }
                } else if (fVar != null && fVar.f6279a == 2 && string == null) {
                    string = uVar.getContext().getResources().getString(R.string.state_off);
                }
            } else if (fVar != null && fVar.f6279a == 2 && string == null) {
                string = uVar.getContext().getResources().getString(R.string.state_on);
            }
        }
        Object obj3 = linkedHashMap.get(w1.q.f6361z);
        if (obj3 == null) {
            obj3 = null;
        }
        Boolean bool = (Boolean) obj3;
        if (bool != null) {
            boolean zBooleanValue = bool.booleanValue();
            if ((fVar == null || fVar.f6279a != 4) && string == null) {
                string = zBooleanValue ? uVar.getContext().getResources().getString(R.string.selected) : uVar.getContext().getResources().getString(R.string.not_selected);
            }
        }
        Object obj4 = linkedHashMap.get(w1.q.f6338c);
        if (obj4 == null) {
            obj4 = null;
        }
        w1.e eVar = (w1.e) obj4;
        if (eVar != null) {
            if (eVar != w1.e.f6277b) {
                if (string == null) {
                    string = uVar.getContext().getResources().getString(R.string.template_percent, 0);
                }
            } else if (string == null) {
                string = uVar.getContext().getResources().getString(R.string.in_progress);
            }
        }
        w1.t tVar = w1.q.f6358w;
        if (linkedHashMap.containsKey(tVar)) {
            LinkedHashMap linkedHashMap2 = new w1.n(nVar.f6315a, true, nVar.f6317c, iVar2).i().f6308d;
            Object obj5 = linkedHashMap2.get(w1.q.f6336a);
            if (obj5 == null) {
                obj5 = null;
            }
            Collection collection = (Collection) obj5;
            if (collection == null || collection.isEmpty()) {
                Object obj6 = linkedHashMap2.get(w1.q.f6355t);
                if (obj6 == null) {
                    obj6 = null;
                }
                Collection collection2 = (Collection) obj6;
                if (collection2 == null || collection2.isEmpty()) {
                    Object obj7 = linkedHashMap2.get(tVar);
                    if (obj7 == null) {
                        obj7 = null;
                    }
                    CharSequence charSequence = (CharSequence) obj7;
                    if (charSequence == null || charSequence.length() == 0) {
                        string2 = uVar.getContext().getResources().getString(R.string.state_empty);
                    }
                }
            }
            string = string2;
        }
        return (String) string;
    }

    public final boolean q() {
        return this.f5181g.isEnabled() && !this.f5185k.isEmpty();
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0056  */
    public final boolean r(w1.n nVar) {
        boolean z2;
        w1.i iVar = nVar.f6318d;
        w1.i iVar2 = nVar.f6318d;
        Object obj = iVar.f6308d.get(w1.q.f6336a);
        if (obj == null) {
            obj = null;
        }
        List list = (List) obj;
        if ((list != null ? (String) q3.l.X(list) : null) == null) {
            Object obj2 = iVar2.f6308d.get(w1.q.f6358w);
            if (obj2 == null) {
                obj2 = null;
            }
            y1.f fVar = (y1.f) obj2;
            Object obj3 = iVar2.f6308d.get(w1.q.f6355t);
            if (obj3 == null) {
                obj3 = null;
            }
            List list2 = (List) obj3;
            y1.f fVar2 = list2 != null ? (y1.f) q3.l.X(list2) : null;
            if (fVar == null) {
                fVar = fVar2;
            }
            if (fVar == null && o(nVar) == null && !n(nVar)) {
                z2 = false;
            } else {
                z2 = true;
            }
        } else {
            z2 = true;
        }
        return iVar2.f6309e || (nVar.m() && z2);
    }

    public final void s(p1.d0 d0Var) {
        if (this.f5197w.add(d0Var)) {
            this.f5198x.o(p3.n.f5026a);
        }
    }

    /* JADX WARN: Code duplicated, block: B:264:0x056f  */
    /* JADX WARN: Code duplicated, block: B:267:0x0574  */
    /* JADX WARN: Code duplicated, block: B:270:0x0586  */
    /* JADX WARN: Code duplicated, block: B:273:0x058e  */
    /* JADX WARN: Code duplicated, block: B:275:0x0598  */
    /* JADX WARN: Code duplicated, block: B:278:0x05a1  */
    /* JADX WARN: Code duplicated, block: B:283:0x05ab  */
    /* JADX WARN: Code duplicated, block: B:284:0x05ad  */
    /* JADX WARN: Code duplicated, block: B:290:0x05b7  */
    /* JADX WARN: Code duplicated, block: B:291:0x05b9  */
    /* JADX WARN: Code duplicated, block: B:297:0x05c3  */
    /* JADX WARN: Code duplicated, block: B:304:0x05e2  */
    /* JADX WARN: Code duplicated, block: B:307:0x05f2  */
    /* JADX WARN: Code duplicated, block: B:310:0x05f7  */
    /* JADX WARN: Code duplicated, block: B:312:0x0601  */
    /* JADX WARN: Code duplicated, block: B:315:0x0618  */
    /* JADX WARN: Code duplicated, block: B:318:0x061d  */
    /* JADX WARN: Code duplicated, block: B:321:0x0630  */
    /* JADX WARN: Code duplicated, block: B:323:0x063a  */
    /* JADX WARN: Code duplicated, block: B:326:0x063f  */
    /* JADX WARN: Code duplicated, block: B:329:0x0656  */
    /* JADX WARN: Code duplicated, block: B:332:0x065b  */
    /* JADX WARN: Code duplicated, block: B:335:0x0673  */
    /* JADX WARN: Code duplicated, block: B:338:0x0678  */
    /* JADX WARN: Code duplicated, block: B:341:0x068f  */
    /* JADX WARN: Code duplicated, block: B:348:0x06a6  */
    /* JADX WARN: Code duplicated, block: B:349:0x06ad  */
    /* JADX WARN: Code duplicated, block: B:351:0x06b0  */
    /* JADX WARN: Code duplicated, block: B:358:0x06cd  */
    /* JADX WARN: Code duplicated, block: B:360:0x06d0  */
    /* JADX WARN: Code duplicated, block: B:362:0x06e5  */
    /* JADX WARN: Code duplicated, block: B:365:0x06ec  */
    /* JADX WARN: Code duplicated, block: B:366:0x06ef  */
    /* JADX WARN: Code duplicated, block: B:369:0x0712  */
    /* JADX WARN: Code duplicated, block: B:376:0x0720  */
    /* JADX WARN: Code duplicated, block: B:378:0x0723  */
    /* JADX WARN: Code duplicated, block: B:380:0x072d  */
    /* JADX WARN: Code duplicated, block: B:382:0x0737  */
    /* JADX WARN: Code duplicated, block: B:384:0x073f  */
    /* JADX WARN: Code duplicated, block: B:388:0x0749  */
    /* JADX WARN: Code duplicated, block: B:390:0x0751  */
    /* JADX WARN: Code duplicated, block: B:392:0x0757  */
    /* JADX WARN: Code duplicated, block: B:400:0x076f  */
    /* JADX WARN: Code duplicated, block: B:402:0x0775  */
    /* JADX WARN: Code duplicated, block: B:404:0x077f  */
    /* JADX WARN: Code duplicated, block: B:406:0x0787  */
    /* JADX WARN: Code duplicated, block: B:408:0x078a  */
    /* JADX WARN: Code duplicated, block: B:411:0x078f  */
    /* JADX WARN: Code duplicated, block: B:418:0x07b3  */
    /* JADX WARN: Code duplicated, block: B:420:0x07b6  */
    /* JADX WARN: Code duplicated, block: B:422:0x07c0  */
    /* JADX WARN: Code duplicated, block: B:425:0x07cf  */
    /* JADX WARN: Code duplicated, block: B:428:0x07e1  */
    /* JADX WARN: Code duplicated, block: B:430:0x07e9  */
    /* JADX WARN: Code duplicated, block: B:431:0x07ef  */
    /* JADX WARN: Code duplicated, block: B:434:0x07fa  */
    /* JADX WARN: Code duplicated, block: B:441:0x084e  */
    /* JADX WARN: Code duplicated, block: B:444:0x0855  */
    /* JADX WARN: Code duplicated, block: B:447:0x0874  */
    /* JADX WARN: Code duplicated, block: B:450:0x087b  */
    /* JADX WARN: Code duplicated, block: B:453:0x0891  */
    /* JADX WARN: Code duplicated, block: B:455:0x0894  */
    /* JADX WARN: Code duplicated, block: B:457:0x08a7  */
    /* JADX WARN: Code duplicated, block: B:459:0x08aa  */
    /* JADX WARN: Code duplicated, block: B:461:0x08b6  */
    /* JADX WARN: Code duplicated, block: B:463:0x08ca  */
    /* JADX WARN: Code duplicated, block: B:467:0x08d6  */
    /* JADX WARN: Code duplicated, block: B:469:0x08dc  */
    /* JADX WARN: Code duplicated, block: B:470:0x08de  */
    /* JADX WARN: Code duplicated, block: B:472:0x08e4  */
    /* JADX WARN: Code duplicated, block: B:474:0x08ea  */
    /* JADX WARN: Code duplicated, block: B:478:0x0903  */
    /* JADX WARN: Code duplicated, block: B:480:0x0906  */
    /* JADX WARN: Code duplicated, block: B:482:0x090c  */
    /* JADX WARN: Code duplicated, block: B:483:0x0910  */
    /* JADX WARN: Code duplicated, block: B:485:0x091e  */
    /* JADX WARN: Code duplicated, block: B:487:0x0921  */
    /* JADX WARN: Code duplicated, block: B:489:0x092f  */
    /* JADX WARN: Code duplicated, block: B:491:0x0932  */
    /* JADX WARN: Code duplicated, block: B:494:0x0941  */
    /* JADX WARN: Code duplicated, block: B:496:0x0953  */
    /* JADX WARN: Code duplicated, block: B:498:0x0969  */
    /* JADX WARN: Code duplicated, block: B:500:0x097a  */
    /* JADX WARN: Code duplicated, block: B:504:0x0989  */
    /* JADX WARN: Code duplicated, block: B:506:0x098f  */
    /* JADX WARN: Code duplicated, block: B:507:0x0992  */
    /* JADX WARN: Code duplicated, block: B:509:0x0996  */
    /* JADX WARN: Code duplicated, block: B:510:0x0999  */
    /* JADX WARN: Code duplicated, block: B:513:0x09a9  */
    /* JADX WARN: Code duplicated, block: B:515:0x09c1  */
    /* JADX WARN: Code duplicated, block: B:522:0x09f1  */
    /* JADX WARN: Code duplicated, block: B:524:0x09f4  */
    /* JADX WARN: Code duplicated, block: B:526:0x0a02  */
    /* JADX WARN: Code duplicated, block: B:529:0x0a06  */
    /* JADX WARN: Code duplicated, block: B:530:0x0a08  */
    /* JADX WARN: Code duplicated, block: B:532:0x0a0b  */
    /* JADX WARN: Code duplicated, block: B:535:0x0a22  */
    /* JADX WARN: Code duplicated, block: B:538:0x0a2c  */
    /* JADX WARN: Code duplicated, block: B:540:0x0a32  */
    /* JADX WARN: Code duplicated, block: B:542:0x0a3d  */
    /* JADX WARN: Code duplicated, block: B:543:0x0a40  */
    /* JADX WARN: Code duplicated, block: B:547:0x0a4b  */
    /* JADX WARN: Code duplicated, block: B:549:0x0a56  */
    /* JADX WARN: Code duplicated, block: B:550:0x0a59  */
    /* JADX WARN: Code duplicated, block: B:557:0x0a7c  */
    /* JADX WARN: Code duplicated, block: B:559:0x0a7f  */
    /* JADX WARN: Code duplicated, block: B:561:0x0a8d  */
    /* JADX WARN: Code duplicated, block: B:564:0x0a91  */
    /* JADX WARN: Code duplicated, block: B:565:0x0a93  */
    /* JADX WARN: Code duplicated, block: B:567:0x0a96  */
    /* JADX WARN: Code duplicated, block: B:570:0x0aad  */
    /* JADX WARN: Code duplicated, block: B:573:0x0ab7  */
    /* JADX WARN: Code duplicated, block: B:575:0x0abd  */
    /* JADX WARN: Code duplicated, block: B:578:0x0acd  */
    /* JADX WARN: Code duplicated, block: B:581:0x0adb  */
    /* JADX WARN: Code duplicated, block: B:584:0x0aec  */
    /* JADX WARN: Code duplicated, block: B:585:0x0af0  */
    /* JADX WARN: Code duplicated, block: B:588:0x0aff  */
    /* JADX WARN: Code duplicated, block: B:590:0x0b0d  */
    /* JADX WARN: Code duplicated, block: B:593:0x0b29  */
    /* JADX WARN: Code duplicated, block: B:596:0x0b45  */
    /* JADX WARN: Code duplicated, block: B:599:0x0b5f  */
    /* JADX WARN: Code duplicated, block: B:601:0x0b73  */
    /* JADX WARN: Code duplicated, block: B:603:0x0b89  */
    /* JADX WARN: Code duplicated, block: B:605:0x0b9a  */
    /* JADX WARN: Code duplicated, block: B:607:0x0ba3  */
    /* JADX WARN: Code duplicated, block: B:608:0x0bb9  */
    /* JADX WARN: Code duplicated, block: B:612:0x0bd1  */
    /* JADX WARN: Code duplicated, block: B:614:0x0bd7  */
    /* JADX WARN: Code duplicated, block: B:615:0x0bdb  */
    /* JADX WARN: Code duplicated, block: B:617:0x0be5  */
    /* JADX WARN: Code duplicated, block: B:619:0x0bea  */
    /* JADX WARN: Code duplicated, block: B:621:0x0bf2  */
    /* JADX WARN: Code duplicated, block: B:623:0x0c00  */
    /* JADX WARN: Code duplicated, block: B:626:0x0c12  */
    /* JADX WARN: Code duplicated, block: B:628:0x0c1d  */
    /* JADX WARN: Code duplicated, block: B:630:0x0c38  */
    /* JADX WARN: Code duplicated, block: B:633:0x0c44  */
    /* JADX WARN: Code duplicated, block: B:634:0x0c48  */
    /* JADX WARN: Code duplicated, block: B:636:0x0c4e  */
    /* JADX WARN: Code duplicated, block: B:639:0x0c61  */
    /* JADX WARN: Code duplicated, block: B:641:0x0c68  */
    /* JADX WARN: Code duplicated, block: B:643:0x0c6c  */
    /* JADX WARN: Code duplicated, block: B:644:0x0c7f  */
    /* JADX WARN: Code duplicated, block: B:647:0x0c89  */
    /* JADX WARN: Code duplicated, block: B:649:0x0c8f  */
    /* JADX WARN: Code duplicated, block: B:651:0x0c93  */
    /* JADX WARN: Code duplicated, block: B:653:0x0c9b  */
    /* JADX WARN: Code duplicated, block: B:655:0x0ca1  */
    /* JADX WARN: Code duplicated, block: B:687:0x076c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:689:0x0767 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:694:0x08cd A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:700:0x097c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:701:0x097c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:702:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:628:0x0c1d, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v14, types: [q3.s] */
    /* JADX WARN: Type inference failed for: r2v15, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r2v17, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r41v0, types: [q1.f0] */
    /* JADX WARN: Type inference failed for: r5v19 */
    /* JADX WARN: Type inference failed for: r5v20, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r5v21 */
    /* JADX WARN: Type inference failed for: r5v22, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r5v28, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r5v29, types: [java.util.ArrayList] */
    public final void u(int i5, x2.h hVar, w1.n nVar) {
        Float f5;
        w1.i iVar;
        w1.i iVar2;
        w1.f fVar;
        u uVar;
        AccessibilityNodeInfo accessibilityNodeInfo;
        SpannableString spannableString;
        int i6;
        w1.i iVar3;
        Object obj;
        Object obj2;
        w1.a aVar;
        char c5;
        x2.h hVar2;
        Object obj3;
        w1.a aVar2;
        Object obj4;
        w1.a aVar3;
        String strP;
        boolean z2;
        int i7;
        ArrayList arrayList;
        CharSequence charSequenceE;
        boolean z3;
        w1.e eVar;
        Object obj5;
        ArrayList arrayList2;
        Object obj6;
        Object obj7;
        w1.n nVarJ;
        Object obj8;
        Object obj9;
        ArrayList arrayList3;
        List listH;
        int size;
        int i8;
        int i9;
        boolean zH;
        int i10;
        int i11;
        Object obj10;
        Object obj11;
        w1.n nVar2;
        w1.g gVar;
        w1.g gVar2;
        CharSequence charSequence;
        int i12;
        boolean zR;
        Bundle extras;
        j.p pVar;
        int iC;
        int i13;
        u uVar2;
        j.p pVar2;
        int iC2;
        int i14;
        w1.a aVar4;
        w1.a aVar5;
        w1.a aVar6;
        w1.i iVarK;
        w1.t tVar;
        List list;
        int size2;
        j.q qVar;
        j.i0 i0Var;
        j.x xVar;
        int[] iArr;
        int[] iArr2;
        int i15;
        int i16;
        int i17;
        ArrayList arrayList4;
        int i18;
        Object obj12;
        boolean z4;
        Object obj13;
        Object obj14;
        boolean z5;
        x2.c cVar;
        x2.c cVar2;
        Object obj15;
        boolean zH2;
        int size3;
        int size4;
        List listH2;
        int size5;
        int i19;
        w1.n nVar3;
        w1.t tVar2;
        float fFloatValue;
        float fFloatValue2;
        float fFloatValue3;
        float fFloatValue4;
        Object obj16;
        w1.a aVar7;
        String str;
        Object obj17;
        List list2;
        boolean z6;
        p1.d0 d0VarR;
        w1.i iVarN;
        boolean zA;
        Object obj18;
        w1.i iVarN2;
        boolean z7;
        Object obj19;
        Object obj20;
        w1.a aVar8;
        Object obj21;
        w1.a aVar9;
        Object obj22;
        w1.a aVar10;
        Object obj23;
        w1.a aVar11;
        ClipDescription primaryClipDescription;
        boolean zHasMimeType;
        Object obj24;
        boolean z8;
        boolean z9;
        boolean z10;
        boolean z11;
        boolean zBooleanValue;
        ?? arrayList5;
        ?? arrayList6;
        a1.b bVar;
        int i20;
        Float fValueOf = Float.valueOf(0.0f);
        hVar.f("android.view.View");
        AccessibilityNodeInfo accessibilityNodeInfo2 = hVar.f6563a;
        w1.i iVar4 = nVar.f6318d;
        w1.i iVar5 = nVar.f6318d;
        LinkedHashMap linkedHashMap = iVar4.f6308d;
        if (iVar4.f6308d.containsKey(w1.q.f6358w)) {
            hVar.f("android.widget.EditText");
        }
        w1.t tVar3 = w1.q.f6355t;
        if (linkedHashMap.containsKey(tVar3)) {
            hVar.f("android.widget.TextView");
        }
        Object obj25 = linkedHashMap.get(w1.q.f6353r);
        if (obj25 == null) {
            obj25 = null;
        }
        w1.f fVar2 = (w1.f) obj25;
        u uVar3 = this.f5178d;
        if (fVar2 != null) {
            int i21 = fVar2.f6279a;
            f5 = fValueOf;
            if (nVar.f6319e || w1.n.h(nVar, 4).isEmpty()) {
                if (i21 == 4) {
                    accessibilityNodeInfo2.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", uVar3.getContext().getResources().getString(R.string.tab));
                } else if (i21 == 2) {
                    accessibilityNodeInfo2.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", uVar3.getContext().getResources().getString(R.string.switch_role));
                } else {
                    String strX = m0.x(i21);
                    if (i21 != 5 || nVar.m() || iVar4.f6309e) {
                        hVar.f(strX);
                    }
                }
            }
        } else {
            f5 = fValueOf;
        }
        if (linkedHashMap.containsKey(w1.h.f6289h)) {
            hVar.f("android.widget.EditText");
        }
        if (linkedHashMap.containsKey(tVar3)) {
            hVar.f("android.widget.TextView");
        }
        accessibilityNodeInfo2.setPackageName(uVar3.getContext().getPackageName());
        accessibilityNodeInfo2.setImportantForAccessibility(m0.q(nVar));
        List listH3 = w1.n.h(nVar, 4);
        int size6 = listH3.size();
        for (int i22 = 0; i22 < size6; i22++) {
            w1.n nVar4 = (w1.n) listH3.get(i22);
            if (m().b(nVar4.f6321g)) {
                if (uVar3.getAndroidViewsHandler$ui_release().getLayoutNodeToHolder().get(nVar4.f6317c) != null) {
                    throw new ClassCastException();
                }
                int i23 = nVar4.f6321g;
                if (i23 != -1) {
                    accessibilityNodeInfo2.addChild(uVar3, i23);
                }
            }
        }
        if (i5 == this.f5188n) {
            accessibilityNodeInfo2.setAccessibilityFocused(true);
            hVar.a(x2.c.f6554d);
        } else {
            accessibilityNodeInfo2.setAccessibilityFocused(false);
            hVar.a(x2.c.f6553c);
        }
        Object obj26 = iVar5.f6308d.get(w1.q.f6358w);
        if (obj26 == null) {
            obj26 = null;
        }
        y1.f fVar3 = (y1.f) obj26;
        Object obj27 = iVar5.f6308d.get(w1.q.f6355t);
        if (obj27 == null) {
            obj27 = null;
        }
        List list3 = (List) obj27;
        y1.f fVar4 = list3 != null ? (y1.f) q3.l.X(list3) : null;
        if (fVar3 == null) {
            fVar3 = fVar4;
        }
        if (fVar3 != null) {
            uVar3.getFontFamilyResolver();
            k2.b density = uVar3.getDensity();
            String str2 = fVar3.f6759d;
            List list4 = fVar3.f6762g;
            SpannableString spannableString2 = new SpannableString(str2);
            List list5 = fVar3.f6760e;
            if (list5 != null) {
                int size7 = list5.size();
                int i24 = 0;
                while (i24 < size7) {
                    List list6 = list5;
                    y1.d dVar = (y1.d) list5.get(i24);
                    int i25 = size7;
                    y1.a0 a0Var = (y1.a0) dVar.f6739a;
                    String str3 = str2;
                    int i26 = dVar.f6740b;
                    int i27 = dVar.f6741c;
                    int i28 = i24;
                    u uVar4 = uVar3;
                    long jB = a0Var.f6709a.b();
                    long j5 = a0Var.f6710b;
                    d2.k kVar = a0Var.f6711c;
                    d2.i iVar6 = a0Var.f6712d;
                    j2.n nVar5 = a0Var.f6718j;
                    w1.i iVar7 = iVar5;
                    f2.b bVar2 = a0Var.f6719k;
                    AccessibilityNodeInfo accessibilityNodeInfo3 = accessibilityNodeInfo2;
                    w1.i iVar8 = iVar4;
                    long j6 = a0Var.f6720l;
                    j2.j jVar = a0Var.f6721m;
                    j2.m cVar3 = a0Var.f6709a;
                    w1.f fVar5 = fVar2;
                    List list7 = list4;
                    if (!y0.u.c(jB, cVar3.b())) {
                        cVar3 = jB != 16 ? new j2.c(jB) : j2.l.f3327a;
                    }
                    a.a.J(spannableString2, cVar3.b(), i26, i27);
                    SpannableString spannableString3 = spannableString2;
                    a.a.K(spannableString3, j5, density, i26, i27);
                    if (kVar == null && iVar6 == null) {
                        i20 = 33;
                    } else {
                        StyleSpan styleSpan = new StyleSpan(a.a.u(kVar == null ? d2.k.f1613f : kVar, iVar6 != null ? iVar6.f1610a : 0));
                        i20 = 33;
                        spannableString3.setSpan(styleSpan, i26, i27, 33);
                    }
                    if (jVar != null) {
                        int i29 = jVar.f3325a;
                        if ((i29 | 1) == i29) {
                            spannableString3.setSpan(new UnderlineSpan(), i26, i27, i20);
                        }
                        if ((i29 | 2) == i29) {
                            spannableString3.setSpan(new StrikethroughSpan(), i26, i27, i20);
                        }
                    }
                    if (nVar5 != null) {
                        spannableString3.setSpan(new ScaleXSpan(nVar5.f3329a), i26, i27, i20);
                    }
                    if (bVar2 != null) {
                        spannableString3.setSpan(h2.a.f2615a.a(bVar2), i26, i27, i20);
                    }
                    if (j6 != 16) {
                        spannableString3.setSpan(new BackgroundColorSpan(y0.l0.w(j6)), i26, i27, i20);
                    }
                    i24 = i28 + 1;
                    spannableString2 = spannableString3;
                    fVar2 = fVar5;
                    list5 = list6;
                    size7 = i25;
                    str2 = str3;
                    uVar3 = uVar4;
                    iVar5 = iVar7;
                    accessibilityNodeInfo2 = accessibilityNodeInfo3;
                    iVar4 = iVar8;
                    list4 = list7;
                }
            }
            AccessibilityNodeInfo accessibilityNodeInfo4 = accessibilityNodeInfo2;
            iVar = iVar4;
            iVar2 = iVar5;
            SpannableString spannableString4 = spannableString2;
            String str4 = str2;
            fVar = fVar2;
            List list8 = list4;
            uVar = uVar3;
            int length = str4.length();
            ?? arrayList7 = q3.s.f5477d;
            if (list8 != null) {
                arrayList5 = new ArrayList(list8.size());
                int size8 = list8.size();
                int i30 = 0;
                while (i30 < size8) {
                    List list9 = list8;
                    Object obj28 = list9.get(i30);
                    y1.d dVar2 = (y1.d) obj28;
                    if ((dVar2.f6739a instanceof y1.j0) && y1.g.c(0, length, dVar2.f6740b, dVar2.f6741c)) {
                        arrayList5.add(obj28);
                    }
                    i30++;
                    list8 = list9;
                }
            } else {
                arrayList5 = arrayList7;
            }
            List list10 = list8;
            int size9 = arrayList5.size();
            for (int i31 = 0; i31 < size9; i31++) {
                y1.d dVar3 = (y1.d) arrayList5.get(i31);
                y1.j0 j0Var = (y1.j0) dVar3.f6739a;
                int i32 = dVar3.f6740b;
                int i33 = dVar3.f6741c;
                if (!(j0Var instanceof y1.j0)) {
                    throw new i4.p();
                }
                spannableString4.setSpan(new TtsSpan.VerbatimBuilder(j0Var.f6786a).build(), i32, i33, 33);
            }
            int length2 = str4.length();
            if (list10 != null) {
                arrayList6 = new ArrayList(list10.size());
                int size10 = list10.size();
                for (int i34 = 0; i34 < size10; i34++) {
                    Object obj29 = list10.get(i34);
                    y1.d dVar4 = (y1.d) obj29;
                    if ((dVar4.f6739a instanceof y1.i0) && y1.g.c(0, length2, dVar4.f6740b, dVar4.f6741c)) {
                        arrayList6.add(obj29);
                    }
                }
            } else {
                arrayList6 = arrayList7;
            }
            int size11 = arrayList6.size();
            int i35 = 0;
            while (true) {
                bVar = this.G;
                if (i35 >= size11) {
                    break;
                }
                y1.d dVar5 = (y1.d) arrayList6.get(i35);
                y1.i0 i0Var2 = (y1.i0) dVar5.f6739a;
                int i36 = dVar5.f6740b;
                int i37 = dVar5.f6741c;
                WeakHashMap weakHashMap = (WeakHashMap) bVar.f23d;
                Object uRLSpan = weakHashMap.get(i0Var2);
                if (uRLSpan == null) {
                    uRLSpan = new URLSpan(i0Var2.f6783a);
                    weakHashMap.put(i0Var2, uRLSpan);
                }
                spannableString4.setSpan((URLSpan) uRLSpan, i36, i37, 33);
                i35++;
            }
            int length3 = str4.length();
            if (list10 != null) {
                arrayList7 = new ArrayList(list10.size());
                int size12 = list10.size();
                for (int i38 = 0; i38 < size12; i38++) {
                    Object obj30 = list10.get(i38);
                    y1.d dVar6 = (y1.d) obj30;
                    if ((dVar6.f6739a instanceof y1.l) && y1.g.c(0, length3, dVar6.f6740b, dVar6.f6741c)) {
                        arrayList7.add(obj30);
                    }
                }
            }
            int size13 = arrayList7.size();
            for (int i39 = 0; i39 < size13; i39++) {
                y1.d dVar7 = (y1.d) arrayList7.get(i39);
                Object obj31 = dVar7.f6739a;
                int i40 = dVar7.f6741c;
                int i41 = dVar7.f6740b;
                if (((y1.l) obj31) instanceof y1.k) {
                    b4.l.c("null cannot be cast to non-null type androidx.compose.ui.text.LinkAnnotation.Url", obj31);
                    y1.k kVar2 = (y1.k) obj31;
                    y1.d dVar8 = new y1.d(i41, i40, kVar2);
                    WeakHashMap weakHashMap2 = (WeakHashMap) bVar.f24e;
                    Object uRLSpan2 = weakHashMap2.get(dVar8);
                    if (uRLSpan2 == null) {
                        uRLSpan2 = new URLSpan(kVar2.f6787a);
                        weakHashMap2.put(dVar8, uRLSpan2);
                    }
                    spannableString4.setSpan((URLSpan) uRLSpan2, i41, i40, 33);
                } else {
                    WeakHashMap weakHashMap3 = (WeakHashMap) bVar.f25f;
                    Object gVar3 = weakHashMap3.get(dVar7);
                    if (gVar3 == null) {
                        gVar3 = new g2.g((y1.l) dVar7.f6739a);
                        weakHashMap3.put(dVar7, gVar3);
                    }
                    spannableString4.setSpan((ClickableSpan) gVar3, i41, i40, 33);
                }
            }
            spannableString = (SpannableString) K(spannableString4);
            accessibilityNodeInfo = accessibilityNodeInfo4;
        } else {
            iVar = iVar4;
            iVar2 = iVar5;
            fVar = fVar2;
            uVar = uVar3;
            accessibilityNodeInfo = accessibilityNodeInfo2;
            spannableString = null;
        }
        accessibilityNodeInfo.setText(spannableString);
        w1.t tVar4 = w1.q.C;
        if (linkedHashMap.containsKey(tVar4)) {
            accessibilityNodeInfo.setContentInvalid(true);
            Object obj32 = linkedHashMap.get(tVar4);
            if (obj32 == null) {
                obj32 = null;
            }
            accessibilityNodeInfo.setError((CharSequence) obj32);
        }
        String strO = o(nVar);
        if (Build.VERSION.SDK_INT >= 30) {
            t2.b.g(accessibilityNodeInfo, strO);
        } else {
            accessibilityNodeInfo.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.STATE_DESCRIPTION_KEY", strO);
        }
        accessibilityNodeInfo.setCheckable(n(nVar));
        Object obj33 = linkedHashMap.get(w1.q.A);
        if (obj33 == null) {
            obj33 = null;
        }
        x1.a aVar12 = (x1.a) obj33;
        if (aVar12 != null) {
            if (aVar12 == x1.a.f6547d) {
                accessibilityNodeInfo.setChecked(true);
            } else if (aVar12 == x1.a.f6548e) {
                accessibilityNodeInfo.setChecked(false);
            }
        }
        Object obj34 = linkedHashMap.get(w1.q.f6361z);
        if (obj34 == null) {
            obj34 = null;
        }
        Boolean bool = (Boolean) obj34;
        if (bool != null) {
            boolean zBooleanValue2 = bool.booleanValue();
            if (fVar == null) {
                i6 = 4;
            } else {
                i6 = 4;
                if (fVar.f6279a == 4) {
                    accessibilityNodeInfo.setSelected(zBooleanValue2);
                }
            }
            accessibilityNodeInfo.setChecked(zBooleanValue2);
        } else {
            i6 = 4;
        }
        w1.i iVar9 = iVar;
        if (!iVar9.f6309e || w1.n.h(nVar, i6).isEmpty()) {
            Object obj35 = linkedHashMap.get(w1.q.f6336a);
            if (obj35 == null) {
                obj35 = null;
            }
            List list11 = (List) obj35;
            accessibilityNodeInfo.setContentDescription(list11 != null ? (String) q3.l.X(list11) : null);
        }
        Object obj36 = linkedHashMap.get(w1.q.f6354s);
        if (obj36 == null) {
            obj36 = null;
        }
        String str5 = (String) obj36;
        if (str5 != null) {
            w1.n nVarJ2 = nVar;
            while (true) {
                if (nVarJ2 == null) {
                    zBooleanValue = false;
                    break;
                }
                w1.i iVar10 = nVarJ2.f6318d;
                w1.t tVar5 = w1.r.f6362a;
                if (iVar10.f6308d.containsKey(tVar5)) {
                    zBooleanValue = ((Boolean) iVar10.b(tVar5)).booleanValue();
                    break;
                }
                nVarJ2 = nVarJ2.j();
            }
            if (zBooleanValue) {
                accessibilityNodeInfo.setViewIdResourceName(str5);
            }
        }
        w1.t tVar6 = w1.q.f6336a;
        Object obj37 = linkedHashMap.get(w1.q.f6343h);
        if (obj37 == null) {
            obj37 = null;
        }
        if (((p3.n) obj37) != null) {
            if (Build.VERSION.SDK_INT >= 28) {
                accessibilityNodeInfo.setHeading(true);
            } else {
                Bundle extras2 = accessibilityNodeInfo.getExtras();
                if (extras2 != null) {
                    extras2.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", (extras2.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & (-3)) | 2);
                }
            }
        }
        accessibilityNodeInfo.setPassword(linkedHashMap.containsKey(w1.q.B));
        accessibilityNodeInfo.setEditable(linkedHashMap.containsKey(w1.q.D));
        Object obj38 = linkedHashMap.get(w1.q.E);
        if (obj38 == null) {
            obj38 = null;
        }
        Integer num = (Integer) obj38;
        accessibilityNodeInfo.setMaxTextLength(num != null ? num.intValue() : -1);
        accessibilityNodeInfo.setEnabled(m0.h(nVar));
        w1.t tVar7 = w1.q.f6346k;
        accessibilityNodeInfo.setFocusable(linkedHashMap.containsKey(tVar7));
        if (accessibilityNodeInfo.isFocusable()) {
            accessibilityNodeInfo.setFocused(((Boolean) iVar9.b(tVar7)).booleanValue());
            if (accessibilityNodeInfo.isFocused()) {
                accessibilityNodeInfo.addAction(2);
            } else {
                accessibilityNodeInfo.addAction(1);
            }
        }
        p1.y0 y0VarC = nVar.c();
        if (!(y0VarC != null ? y0VarC.S0() : false)) {
            iVar3 = iVar2;
            boolean z12 = iVar3.f6308d.containsKey(w1.q.f6348m) ? false : true;
            accessibilityNodeInfo.setVisibleToUser(z12);
            obj = linkedHashMap.get(w1.q.f6345j);
            if (obj == null) {
                obj = null;
            }
            if (((w1.d) obj) != null) {
                accessibilityNodeInfo.setLiveRegion(1);
            }
            accessibilityNodeInfo.setClickable(false);
            obj2 = iVar3.f6308d.get(w1.h.f6283b);
            if (obj2 == null) {
                obj2 = null;
            }
            aVar = (w1.a) obj2;
            c5 = 3;
            if (aVar != null) {
                obj24 = iVar3.f6308d.get(w1.q.f6361z);
                if (obj24 == null) {
                    obj24 = null;
                }
                boolean zA2 = b4.l.a(obj24, Boolean.TRUE);
                if (fVar == null && fVar.f6279a == 4) {
                    z8 = true;
                } else {
                    z8 = false;
                }
                if (z8) {
                    z9 = true;
                } else {
                    if (fVar == null && fVar.f6279a == 3) {
                        z11 = true;
                    } else {
                        z11 = false;
                    }
                    if (z11) {
                        z9 = true;
                    } else {
                        z9 = false;
                    }
                }
                if (z9 || (z9 && !zA2)) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                accessibilityNodeInfo.setClickable(z10);
                if (m0.h(nVar) || !accessibilityNodeInfo.isClickable()) {
                    hVar2 = hVar;
                } else {
                    x2.c cVar4 = new x2.c(null, 16, aVar.f6272a, null);
                    hVar2 = hVar;
                    hVar2.a(cVar4);
                }
            } else {
                hVar2 = hVar;
            }
            accessibilityNodeInfo.setLongClickable(false);
            obj3 = iVar3.f6308d.get(w1.h.f6284c);
            if (obj3 == null) {
                obj3 = null;
            }
            aVar2 = (w1.a) obj3;
            if (aVar2 != null) {
                accessibilityNodeInfo.setLongClickable(true);
                if (m0.h(nVar)) {
                    hVar2.a(new x2.c(null, 32, aVar2.f6272a, null));
                }
            }
            obj4 = iVar3.f6308d.get(w1.h.f6295n);
            if (obj4 == null) {
                obj4 = null;
            }
            aVar3 = (w1.a) obj4;
            if (aVar3 != null) {
                hVar2.a(new x2.c(null, 16384, aVar3.f6272a, null));
            }
            if (m0.h(nVar)) {
                obj20 = iVar3.f6308d.get(w1.h.f6289h);
                if (obj20 == null) {
                    obj20 = null;
                }
                aVar8 = (w1.a) obj20;
                if (aVar8 != null) {
                    hVar2.a(new x2.c(null, 2097152, aVar8.f6272a, null));
                }
                obj21 = iVar3.f6308d.get(w1.h.f6294m);
                if (obj21 == null) {
                    obj21 = null;
                }
                aVar9 = (w1.a) obj21;
                if (aVar9 != null) {
                    hVar2.a(new x2.c(null, android.R.id.accessibilityActionImeEnter, aVar9.f6272a, null));
                }
                obj22 = iVar3.f6308d.get(w1.h.f6296o);
                if (obj22 == null) {
                    obj22 = null;
                }
                aVar10 = (w1.a) obj22;
                if (aVar10 != null) {
                    hVar2.a(new x2.c(null, 65536, aVar10.f6272a, null));
                }
                obj23 = iVar3.f6308d.get(w1.h.f6297p);
                if (obj23 == null) {
                    obj23 = null;
                }
                aVar11 = (w1.a) obj23;
                if (aVar11 != null && accessibilityNodeInfo.isFocused()) {
                    primaryClipDescription = uVar.getClipboardManager().f5215a.getPrimaryClipDescription();
                    if (primaryClipDescription != null) {
                        zHasMimeType = primaryClipDescription.hasMimeType("text/*");
                    } else {
                        zHasMimeType = false;
                    }
                    if (zHasMimeType) {
                        hVar2.a(new x2.c(null, 32768, aVar11.f6272a, null));
                    }
                }
            }
            strP = p(nVar);
            if (strP != null || strP.length() == 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (!z2) {
                accessibilityNodeInfo.setTextSelection(l(nVar), k(nVar));
                obj16 = iVar3.f6308d.get(w1.h.f6288g);
                if (obj16 == null) {
                    obj16 = null;
                }
                aVar7 = (w1.a) obj16;
                if (aVar7 != null) {
                    str = aVar7.f6272a;
                } else {
                    str = null;
                }
                hVar2.a(new x2.c(null, 131072, str, null));
                accessibilityNodeInfo.addAction(256);
                accessibilityNodeInfo.addAction(512);
                accessibilityNodeInfo.setMovementGranularities(11);
                obj17 = iVar3.f6308d.get(w1.q.f6336a);
                if (obj17 == null) {
                    obj17 = null;
                }
                list2 = (List) obj17;
                if (list2 != null || list2.isEmpty()) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (z6) {
                    if (iVar3.f6308d.containsKey(w1.h.f6282a)) {
                        if (iVar3.f6308d.containsKey(w1.q.f6358w)) {
                            obj19 = iVar3.f6308d.get(tVar7);
                            if (obj19 == null) {
                                obj19 = null;
                            }
                            if (!b4.l.a(obj19, Boolean.TRUE)) {
                                d0VarR = nVar.f6317c.r();
                                while (true) {
                                    if (d0VarR == null) {
                                        d0VarR = null;
                                        break;
                                    } else {
                                        iVarN2 = d0VarR.n();
                                        if (iVarN2 == null) {
                                        }
                                        d0VarR = d0VarR.r();
                                    }
                                }
                                if (d0VarR != null) {
                                    iVarN = d0VarR.n();
                                    if (iVarN != null) {
                                        obj18 = iVarN.f6308d.get(w1.q.f6346k);
                                        if (obj18 == null) {
                                            obj18 = null;
                                        }
                                        zA = b4.l.a(obj18, Boolean.TRUE);
                                    } else {
                                        zA = false;
                                    }
                                    if (zA) {
                                    }
                                }
                            }
                        } else {
                            d0VarR = nVar.f6317c.r();
                            while (true) {
                                if (d0VarR == null) {
                                    d0VarR = null;
                                    break;
                                }
                                iVarN2 = d0VarR.n();
                                if (iVarN2 == null && iVarN2.f6309e) {
                                    if (iVarN2.f6308d.containsKey(w1.q.f6358w)) {
                                        break;
                                    }
                                }
                                d0VarR = d0VarR.r();
                            }
                            if (d0VarR != null) {
                                iVarN = d0VarR.n();
                                if (iVarN != null) {
                                    obj18 = iVarN.f6308d.get(w1.q.f6346k);
                                    if (obj18 == null) {
                                        obj18 = null;
                                    }
                                    zA = b4.l.a(obj18, Boolean.TRUE);
                                } else {
                                    zA = false;
                                }
                                z7 = zA ? false : true;
                            }
                        }
                        if (!z7) {
                            accessibilityNodeInfo.setMovementGranularities(accessibilityNodeInfo.getMovementGranularities() | 20);
                        }
                    }
                }
            }
            i7 = Build.VERSION.SDK_INT;
            arrayList = new ArrayList();
            arrayList.add("androidx.compose.ui.semantics.id");
            charSequenceE = hVar2.e();
            if (charSequenceE != null || charSequenceE.length() == 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (!z3) {
                if (iVar3.f6308d.containsKey(w1.h.f6282a)) {
                    arrayList.add("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY");
                }
            }
            if (iVar3.f6308d.containsKey(w1.q.f6354s)) {
                arrayList.add("androidx.compose.ui.semantics.testTag");
            }
            accessibilityNodeInfo.setAvailableExtraData(arrayList);
            eVar = (w1.e) p3.j.A(iVar3, w1.q.f6338c);
            if (eVar != null) {
                tVar2 = w1.h.f6287f;
                if (iVar3.a(tVar2)) {
                    hVar2.f("android.widget.SeekBar");
                } else {
                    hVar2.f("android.widget.ProgressBar");
                }
                w1.e eVar2 = w1.e.f6277b;
                if (eVar != w1.e.f6277b) {
                    eVar.a().getClass();
                    float fFloatValue5 = f5.floatValue();
                    eVar.a().getClass();
                    hVar2.f6563a.setRangeInfo((AccessibilityNodeInfo.RangeInfo) new j0.e(AccessibilityNodeInfo.RangeInfo.obtain(1, fFloatValue5, f5.floatValue(), 0.0f)).f3188a);
                }
                if (nVar.k().a(tVar2) && m0.h(nVar)) {
                    eVar.a().getClass();
                    fFloatValue = f5.floatValue();
                    eVar.a().getClass();
                    fFloatValue2 = f5.floatValue();
                    if (fFloatValue < fFloatValue2) {
                        fFloatValue = fFloatValue2;
                    }
                    if (0.0f < fFloatValue) {
                        hVar2.a(x2.c.f6555e);
                    }
                    eVar.a().getClass();
                    fFloatValue3 = f5.floatValue();
                    eVar.a().getClass();
                    fFloatValue4 = f5.floatValue();
                    if (fFloatValue3 > fFloatValue4) {
                        fFloatValue3 = fFloatValue4;
                    }
                    if (0.0f > fFloatValue3) {
                        hVar2.a(x2.c.f6556f);
                    }
                }
            }
            y.a(hVar, nVar);
            obj5 = nVar.i().f6308d.get(w1.q.f6341f);
            if (obj5 == null) {
                obj5 = null;
            }
            if (obj5 == null) {
                throw new ClassCastException();
            }
            arrayList2 = new ArrayList();
            obj6 = nVar.i().f6308d.get(w1.q.f6340e);
            if (obj6 == null) {
                obj6 = null;
            }
            if (obj6 != null) {
                listH2 = w1.n.h(nVar, 4);
                size5 = listH2.size();
                for (i19 = 0; i19 < size5; i19++) {
                    nVar3 = (w1.n) listH2.get(i19);
                    if (nVar3.i().f6308d.containsKey(w1.q.f6361z)) {
                        arrayList2.add(nVar3);
                    }
                }
            }
            if (!arrayList2.isEmpty()) {
                zH2 = q.f.h(arrayList2);
                if (zH2) {
                    size3 = 1;
                } else {
                    size3 = arrayList2.size();
                }
                if (zH2) {
                    size4 = arrayList2.size();
                } else {
                    size4 = 1;
                }
                hVar2.f6563a.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(size3, size4, false, 0));
            }
            obj7 = nVar.i().f6308d.get(w1.q.f6342g);
            if (obj7 == null) {
                obj7 = null;
            }
            if (obj7 == null) {
                throw new ClassCastException();
            }
            nVarJ = nVar.j();
            if (nVarJ == null) {
                obj8 = nVarJ.i().f6308d.get(w1.q.f6340e);
                if (obj8 == null) {
                    obj8 = null;
                }
                if (obj8 != null) {
                    obj9 = nVarJ.i().f6308d.get(w1.q.f6341f);
                    if (obj9 == null) {
                        obj9 = null;
                    }
                    if (obj9 == null) {
                        throw new ClassCastException();
                    }
                    if (!nVar.i().f6308d.containsKey(w1.q.f6361z)) {
                        arrayList3 = new ArrayList();
                        listH = w1.n.h(nVarJ, 4);
                        size = listH.size();
                        i8 = 0;
                        i9 = 0;
                        while (i8 < size) {
                            nVar2 = (w1.n) listH.get(i8);
                            char c6 = c5;
                            if (nVar2.i().f6308d.containsKey(w1.q.f6361z)) {
                                arrayList3.add(nVar2);
                                if (nVar2.f6317c.s() < nVar.f6317c.s()) {
                                    i9++;
                                }
                            }
                            i8++;
                            c5 = c6;
                        }
                        if (!arrayList3.isEmpty()) {
                            zH = q.f.h(arrayList3);
                            if (zH) {
                                i10 = 0;
                            } else {
                                i10 = i9;
                            }
                            if (zH) {
                                i11 = i9;
                            } else {
                                i11 = 0;
                            }
                            obj10 = nVar.i().f6308d.get(w1.q.f6361z);
                            obj11 = obj10;
                            if (obj10 == null) {
                                obj11 = Boolean.FALSE;
                            }
                            hVar2.f6563a.setCollectionItemInfo(AccessibilityNodeInfo.CollectionItemInfo.obtain(i10, 1, i11, 1, false, ((Boolean) obj11).booleanValue()));
                        }
                    }
                }
            }
            gVar = (w1.g) p3.j.A(nVar.k(), w1.q.f6350o);
            w1.a aVar13 = (w1.a) p3.j.A(nVar.k(), w1.h.f6285d);
            if (gVar != null && aVar13 != null) {
                obj14 = nVar.i().f6308d.get(w1.q.f6341f);
                if (obj14 == null) {
                    obj14 = null;
                }
                if (obj14 == null) {
                    obj15 = nVar.i().f6308d.get(w1.q.f6340e);
                    if (obj15 == null) {
                        obj15 = null;
                    }
                    if (obj15 != null) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                } else {
                    z5 = true;
                }
                if (!z5) {
                    hVar2.f("android.widget.HorizontalScrollView");
                }
                if (((Number) gVar.f6281b.a()).floatValue() > 0.0f) {
                    accessibilityNodeInfo.setScrollable(true);
                }
                if (m0.h(nVar)) {
                    if (w(gVar)) {
                        hVar2.a(x2.c.f6555e);
                        if (m0.i(nVar)) {
                            cVar2 = x2.c.f6558h;
                        } else {
                            cVar2 = x2.c.f6560j;
                        }
                        hVar2.a(cVar2);
                    }
                    if (v(gVar)) {
                        hVar2.a(x2.c.f6556f);
                        if (m0.i(nVar)) {
                            cVar = x2.c.f6560j;
                        } else {
                            cVar = x2.c.f6558h;
                        }
                        hVar2.a(cVar);
                    }
                }
            }
            gVar2 = (w1.g) p3.j.A(nVar.k(), w1.q.f6351p);
            if (gVar2 != null && aVar13 != null) {
                obj12 = nVar.i().f6308d.get(w1.q.f6341f);
                if (obj12 == null) {
                    obj12 = null;
                }
                if (obj12 == null) {
                    obj13 = nVar.i().f6308d.get(w1.q.f6340e);
                    if (obj13 == null) {
                        obj13 = null;
                    }
                    if (obj13 != null) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                } else {
                    z4 = true;
                }
                if (!z4) {
                    hVar2.f("android.widget.ScrollView");
                }
                if (((Number) gVar2.f6281b.a()).floatValue() > 0.0f) {
                    accessibilityNodeInfo.setScrollable(true);
                }
                if (m0.h(nVar)) {
                    if (w(gVar2)) {
                        hVar2.a(x2.c.f6555e);
                        hVar2.a(x2.c.f6559i);
                    }
                    if (v(gVar2)) {
                        hVar2.a(x2.c.f6556f);
                        hVar2.a(x2.c.f6557g);
                    }
                }
            }
            if (i7 >= 29) {
                z.a(hVar, nVar);
            }
            charSequence = (CharSequence) p3.j.A(nVar.k(), w1.q.f6339d);
            if (i7 >= 28) {
                accessibilityNodeInfo.setPaneTitle(charSequence);
            } else {
                accessibilityNodeInfo.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY", charSequence);
            }
            if (m0.h(nVar)) {
                aVar4 = (w1.a) p3.j.A(nVar.k(), w1.h.f6298q);
                if (aVar4 != null) {
                    hVar2.a(new x2.c(262144, aVar4.a()));
                }
                aVar5 = (w1.a) p3.j.A(nVar.k(), w1.h.f6299r);
                if (aVar5 != null) {
                    hVar2.a(new x2.c(524288, aVar5.a()));
                }
                aVar6 = (w1.a) p3.j.A(nVar.k(), w1.h.f6300s);
                if (aVar6 != null) {
                    hVar2.a(new x2.c(1048576, aVar6.a()));
                }
                iVarK = nVar.k();
                tVar = w1.h.f6302u;
                if (iVarK.a(tVar)) {
                    list = (List) nVar.k().b(tVar);
                    size2 = list.size();
                    qVar = N;
                    if (size2 < qVar.f3137b) {
                        throw new IllegalStateException("Can't have more than " + qVar.f3137b + " custom actions for one widget");
                    }
                    j.i0 i0Var3 = new j.i0();
                    j.x xVar2 = j.d0.f3095a;
                    j.x xVar3 = new j.x();
                    i0Var = this.f5194t;
                    i12 = i5;
                    if (i0Var.b(i12)) {
                        xVar = (j.x) i0Var.c(i12);
                        iArr = new int[16];
                        iArr2 = qVar.f3136a;
                        i15 = qVar.f3137b;
                        i16 = 0;
                        i17 = 0;
                        while (i16 < i15) {
                            int i42 = iArr2[i16];
                            i18 = i17 + 1;
                            int i43 = i15;
                            if (iArr.length < i18) {
                                int[] iArrCopyOf = Arrays.copyOf(iArr, Math.max(i18, (iArr.length * 3) / 2));
                                b4.l.d("copyOf(...)", iArrCopyOf);
                                iArr = iArrCopyOf;
                            }
                            iArr[i17] = i42;
                            i16++;
                            i17 = i18;
                            i15 = i43;
                        }
                        arrayList4 = new ArrayList();
                        if (list.size() <= 0) {
                            b.b.l(list.get(0));
                            b4.l.b(xVar);
                            throw null;
                        }
                        if (arrayList4.size() <= 0) {
                            b.b.l(arrayList4.get(0));
                            if (i17 > 0) {
                                int i44 = iArr[0];
                                throw null;
                            }
                            k.a.d("Index must be between 0 and size");
                            throw null;
                        }
                    } else if (list.size() > 0) {
                        b.b.l(list.get(0));
                        qVar.c(0);
                        throw null;
                    }
                    this.f5193s.d(i12, i0Var3);
                    i0Var.d(i12, xVar3);
                } else {
                    i12 = i5;
                }
            } else {
                i12 = i5;
            }
            zR = r(nVar);
            if (Build.VERSION.SDK_INT >= 28) {
                accessibilityNodeInfo.setScreenReaderFocusable(zR);
            } else {
                extras = accessibilityNodeInfo.getExtras();
                if (extras != null) {
                    extras.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", (zR ? 1 : 0) | (extras.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & (-2)));
                }
            }
            pVar = this.C;
            iC = pVar.c(i12);
            if (iC >= 0) {
                i13 = pVar.f3132c[iC];
            } else {
                i13 = -1;
            }
            if (i13 != -1) {
                m0.v(uVar.getAndroidViewsHandler$ui_release(), i13);
                uVar2 = uVar;
                hVar2.g(uVar2, i13);
                c(i12, hVar2, this.E, null);
            } else {
                uVar2 = uVar;
            }
            pVar2 = this.D;
            iC2 = pVar2.c(i12);
            if (iC2 >= 0) {
                i14 = pVar2.f3132c[iC2];
            } else {
                i14 = -1;
            }
            if (i14 != -1) {
                m0.v(uVar2.getAndroidViewsHandler$ui_release(), i14);
            }
        }
        iVar3 = iVar2;
        accessibilityNodeInfo.setVisibleToUser(z12);
        obj = linkedHashMap.get(w1.q.f6345j);
        if (obj == null) {
            obj = null;
        }
        if (((w1.d) obj) != null) {
            accessibilityNodeInfo.setLiveRegion(1);
        }
        accessibilityNodeInfo.setClickable(false);
        obj2 = iVar3.f6308d.get(w1.h.f6283b);
        if (obj2 == null) {
            obj2 = null;
        }
        aVar = (w1.a) obj2;
        c5 = 3;
        if (aVar != null) {
            obj24 = iVar3.f6308d.get(w1.q.f6361z);
            if (obj24 == null) {
                obj24 = null;
            }
            boolean zA3 = b4.l.a(obj24, Boolean.TRUE);
            if (fVar == null) {
                z8 = false;
            } else {
                z8 = true;
            }
            if (z8) {
                z9 = true;
            } else {
                if (fVar == null) {
                    z11 = false;
                } else {
                    z11 = true;
                }
                if (z11) {
                    z9 = true;
                } else {
                    z9 = false;
                }
            }
            if (z9) {
                z10 = true;
            } else {
                z10 = true;
            }
            accessibilityNodeInfo.setClickable(z10);
            if (m0.h(nVar)) {
                hVar2 = hVar;
            } else {
                hVar2 = hVar;
            }
        } else {
            hVar2 = hVar;
        }
        accessibilityNodeInfo.setLongClickable(false);
        obj3 = iVar3.f6308d.get(w1.h.f6284c);
        if (obj3 == null) {
            obj3 = null;
        }
        aVar2 = (w1.a) obj3;
        if (aVar2 != null) {
            accessibilityNodeInfo.setLongClickable(true);
            if (m0.h(nVar)) {
                hVar2.a(new x2.c(null, 32, aVar2.f6272a, null));
            }
        }
        obj4 = iVar3.f6308d.get(w1.h.f6295n);
        if (obj4 == null) {
            obj4 = null;
        }
        aVar3 = (w1.a) obj4;
        if (aVar3 != null) {
            hVar2.a(new x2.c(null, 16384, aVar3.f6272a, null));
        }
        if (m0.h(nVar)) {
            obj20 = iVar3.f6308d.get(w1.h.f6289h);
            if (obj20 == null) {
                obj20 = null;
            }
            aVar8 = (w1.a) obj20;
            if (aVar8 != null) {
                hVar2.a(new x2.c(null, 2097152, aVar8.f6272a, null));
            }
            obj21 = iVar3.f6308d.get(w1.h.f6294m);
            if (obj21 == null) {
                obj21 = null;
            }
            aVar9 = (w1.a) obj21;
            if (aVar9 != null) {
                hVar2.a(new x2.c(null, android.R.id.accessibilityActionImeEnter, aVar9.f6272a, null));
            }
            obj22 = iVar3.f6308d.get(w1.h.f6296o);
            if (obj22 == null) {
                obj22 = null;
            }
            aVar10 = (w1.a) obj22;
            if (aVar10 != null) {
                hVar2.a(new x2.c(null, 65536, aVar10.f6272a, null));
            }
            obj23 = iVar3.f6308d.get(w1.h.f6297p);
            if (obj23 == null) {
                obj23 = null;
            }
            aVar11 = (w1.a) obj23;
            if (aVar11 != null) {
                primaryClipDescription = uVar.getClipboardManager().f5215a.getPrimaryClipDescription();
                if (primaryClipDescription != null) {
                    zHasMimeType = primaryClipDescription.hasMimeType("text/*");
                } else {
                    zHasMimeType = false;
                }
                if (zHasMimeType) {
                    hVar2.a(new x2.c(null, 32768, aVar11.f6272a, null));
                }
            }
        }
        strP = p(nVar);
        if (strP != null) {
            z2 = true;
        } else {
            z2 = true;
        }
        if (!z2) {
            accessibilityNodeInfo.setTextSelection(l(nVar), k(nVar));
            obj16 = iVar3.f6308d.get(w1.h.f6288g);
            if (obj16 == null) {
                obj16 = null;
            }
            aVar7 = (w1.a) obj16;
            if (aVar7 != null) {
                str = aVar7.f6272a;
            } else {
                str = null;
            }
            hVar2.a(new x2.c(null, 131072, str, null));
            accessibilityNodeInfo.addAction(256);
            accessibilityNodeInfo.addAction(512);
            accessibilityNodeInfo.setMovementGranularities(11);
            obj17 = iVar3.f6308d.get(w1.q.f6336a);
            if (obj17 == null) {
                obj17 = null;
            }
            list2 = (List) obj17;
            if (list2 != null) {
                z6 = true;
            } else {
                z6 = true;
            }
            if (z6) {
                if (iVar3.f6308d.containsKey(w1.h.f6282a)) {
                    if (iVar3.f6308d.containsKey(w1.q.f6358w)) {
                        d0VarR = nVar.f6317c.r();
                        while (true) {
                            if (d0VarR == null) {
                                d0VarR = null;
                                break;
                            } else {
                                iVarN2 = d0VarR.n();
                                if (iVarN2 == null) {
                                }
                                d0VarR = d0VarR.r();
                            }
                        }
                        if (d0VarR != null) {
                            iVarN = d0VarR.n();
                            if (iVarN != null) {
                                obj18 = iVarN.f6308d.get(w1.q.f6346k);
                                if (obj18 == null) {
                                    obj18 = null;
                                }
                                zA = b4.l.a(obj18, Boolean.TRUE);
                            } else {
                                zA = false;
                            }
                            if (zA) {
                            }
                        }
                    } else {
                        obj19 = iVar3.f6308d.get(tVar7);
                        if (obj19 == null) {
                            obj19 = null;
                        }
                        if (!b4.l.a(obj19, Boolean.TRUE)) {
                            d0VarR = nVar.f6317c.r();
                            while (true) {
                                if (d0VarR == null) {
                                    d0VarR = null;
                                    break;
                                } else {
                                    iVarN2 = d0VarR.n();
                                    if (iVarN2 == null) {
                                    }
                                    d0VarR = d0VarR.r();
                                }
                            }
                            if (d0VarR != null) {
                                iVarN = d0VarR.n();
                                if (iVarN != null) {
                                    obj18 = iVarN.f6308d.get(w1.q.f6346k);
                                    if (obj18 == null) {
                                        obj18 = null;
                                    }
                                    zA = b4.l.a(obj18, Boolean.TRUE);
                                } else {
                                    zA = false;
                                }
                                if (zA) {
                                }
                            }
                        }
                    }
                    if (!z7) {
                        accessibilityNodeInfo.setMovementGranularities(accessibilityNodeInfo.getMovementGranularities() | 20);
                    }
                }
            }
        }
        i7 = Build.VERSION.SDK_INT;
        arrayList = new ArrayList();
        arrayList.add("androidx.compose.ui.semantics.id");
        charSequenceE = hVar2.e();
        if (charSequenceE != null) {
            z3 = true;
        } else {
            z3 = true;
        }
        if (!z3) {
            if (iVar3.f6308d.containsKey(w1.h.f6282a)) {
                arrayList.add("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY");
            }
        }
        if (iVar3.f6308d.containsKey(w1.q.f6354s)) {
            arrayList.add("androidx.compose.ui.semantics.testTag");
        }
        accessibilityNodeInfo.setAvailableExtraData(arrayList);
        eVar = (w1.e) p3.j.A(iVar3, w1.q.f6338c);
        if (eVar != null) {
            tVar2 = w1.h.f6287f;
            if (iVar3.a(tVar2)) {
                hVar2.f("android.widget.SeekBar");
            } else {
                hVar2.f("android.widget.ProgressBar");
            }
            w1.e eVar3 = w1.e.f6277b;
            if (eVar != w1.e.f6277b) {
                eVar.a().getClass();
                float fFloatValue6 = f5.floatValue();
                eVar.a().getClass();
                hVar2.f6563a.setRangeInfo((AccessibilityNodeInfo.RangeInfo) new j0.e(AccessibilityNodeInfo.RangeInfo.obtain(1, fFloatValue6, f5.floatValue(), 0.0f)).f3188a);
            }
            if (nVar.k().a(tVar2)) {
                eVar.a().getClass();
                fFloatValue = f5.floatValue();
                eVar.a().getClass();
                fFloatValue2 = f5.floatValue();
                if (fFloatValue < fFloatValue2) {
                    fFloatValue = fFloatValue2;
                }
                if (0.0f < fFloatValue) {
                    hVar2.a(x2.c.f6555e);
                }
                eVar.a().getClass();
                fFloatValue3 = f5.floatValue();
                eVar.a().getClass();
                fFloatValue4 = f5.floatValue();
                if (fFloatValue3 > fFloatValue4) {
                    fFloatValue3 = fFloatValue4;
                }
                if (0.0f > fFloatValue3) {
                    hVar2.a(x2.c.f6556f);
                }
            }
        }
        y.a(hVar, nVar);
        obj5 = nVar.i().f6308d.get(w1.q.f6341f);
        if (obj5 == null) {
            obj5 = null;
        }
        if (obj5 == null) {
            throw new ClassCastException();
        }
        arrayList2 = new ArrayList();
        obj6 = nVar.i().f6308d.get(w1.q.f6340e);
        if (obj6 == null) {
            obj6 = null;
        }
        if (obj6 != null) {
            listH2 = w1.n.h(nVar, 4);
            size5 = listH2.size();
            while (i19 < size5) {
                nVar3 = (w1.n) listH2.get(i19);
                if (nVar3.i().f6308d.containsKey(w1.q.f6361z)) {
                    arrayList2.add(nVar3);
                }
            }
        }
        if (!arrayList2.isEmpty()) {
            zH2 = q.f.h(arrayList2);
            if (zH2) {
                size3 = 1;
            } else {
                size3 = arrayList2.size();
            }
            if (zH2) {
                size4 = arrayList2.size();
            } else {
                size4 = 1;
            }
            hVar2.f6563a.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(size3, size4, false, 0));
        }
        obj7 = nVar.i().f6308d.get(w1.q.f6342g);
        if (obj7 == null) {
            obj7 = null;
        }
        if (obj7 == null) {
            throw new ClassCastException();
        }
        nVarJ = nVar.j();
        if (nVarJ == null) {
            obj8 = nVarJ.i().f6308d.get(w1.q.f6340e);
            if (obj8 == null) {
                obj8 = null;
            }
            if (obj8 != null) {
                obj9 = nVarJ.i().f6308d.get(w1.q.f6341f);
                if (obj9 == null) {
                    obj9 = null;
                }
                if (obj9 == null) {
                    throw new ClassCastException();
                }
                if (!nVar.i().f6308d.containsKey(w1.q.f6361z)) {
                    arrayList3 = new ArrayList();
                    listH = w1.n.h(nVarJ, 4);
                    size = listH.size();
                    i8 = 0;
                    i9 = 0;
                    while (i8 < size) {
                        nVar2 = (w1.n) listH.get(i8);
                        char c7 = c5;
                        if (nVar2.i().f6308d.containsKey(w1.q.f6361z)) {
                            arrayList3.add(nVar2);
                            if (nVar2.f6317c.s() < nVar.f6317c.s()) {
                                i9++;
                            }
                        }
                        i8++;
                        c5 = c7;
                    }
                    if (!arrayList3.isEmpty()) {
                        zH = q.f.h(arrayList3);
                        if (zH) {
                            i10 = 0;
                        } else {
                            i10 = i9;
                        }
                        if (zH) {
                            i11 = i9;
                        } else {
                            i11 = 0;
                        }
                        obj10 = nVar.i().f6308d.get(w1.q.f6361z);
                        obj11 = obj10;
                        if (obj10 == null) {
                            obj11 = Boolean.FALSE;
                        }
                        hVar2.f6563a.setCollectionItemInfo(AccessibilityNodeInfo.CollectionItemInfo.obtain(i10, 1, i11, 1, false, ((Boolean) obj11).booleanValue()));
                    }
                }
            }
        }
        gVar = (w1.g) p3.j.A(nVar.k(), w1.q.f6350o);
        w1.a aVar14 = (w1.a) p3.j.A(nVar.k(), w1.h.f6285d);
        if (gVar != null) {
            obj14 = nVar.i().f6308d.get(w1.q.f6341f);
            if (obj14 == null) {
                obj14 = null;
            }
            if (obj14 == null) {
                obj15 = nVar.i().f6308d.get(w1.q.f6340e);
                if (obj15 == null) {
                    obj15 = null;
                }
                if (obj15 != null) {
                    z5 = true;
                } else {
                    z5 = false;
                }
            } else {
                z5 = true;
            }
            if (!z5) {
                hVar2.f("android.widget.HorizontalScrollView");
            }
            if (((Number) gVar.f6281b.a()).floatValue() > 0.0f) {
                accessibilityNodeInfo.setScrollable(true);
            }
            if (m0.h(nVar)) {
                if (w(gVar)) {
                    hVar2.a(x2.c.f6555e);
                    if (m0.i(nVar)) {
                        cVar2 = x2.c.f6560j;
                    } else {
                        cVar2 = x2.c.f6558h;
                    }
                    hVar2.a(cVar2);
                }
                if (v(gVar)) {
                    hVar2.a(x2.c.f6556f);
                    if (m0.i(nVar)) {
                        cVar = x2.c.f6558h;
                    } else {
                        cVar = x2.c.f6560j;
                    }
                    hVar2.a(cVar);
                }
            }
        }
        gVar2 = (w1.g) p3.j.A(nVar.k(), w1.q.f6351p);
        if (gVar2 != null) {
            obj12 = nVar.i().f6308d.get(w1.q.f6341f);
            if (obj12 == null) {
                obj12 = null;
            }
            if (obj12 == null) {
                obj13 = nVar.i().f6308d.get(w1.q.f6340e);
                if (obj13 == null) {
                    obj13 = null;
                }
                if (obj13 != null) {
                    z4 = true;
                } else {
                    z4 = false;
                }
            } else {
                z4 = true;
            }
            if (!z4) {
                hVar2.f("android.widget.ScrollView");
            }
            if (((Number) gVar2.f6281b.a()).floatValue() > 0.0f) {
                accessibilityNodeInfo.setScrollable(true);
            }
            if (m0.h(nVar)) {
                if (w(gVar2)) {
                    hVar2.a(x2.c.f6555e);
                    hVar2.a(x2.c.f6559i);
                }
                if (v(gVar2)) {
                    hVar2.a(x2.c.f6556f);
                    hVar2.a(x2.c.f6557g);
                }
            }
        }
        if (i7 >= 29) {
            z.a(hVar, nVar);
        }
        charSequence = (CharSequence) p3.j.A(nVar.k(), w1.q.f6339d);
        if (i7 >= 28) {
            accessibilityNodeInfo.setPaneTitle(charSequence);
        } else {
            accessibilityNodeInfo.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY", charSequence);
        }
        if (m0.h(nVar)) {
            i12 = i5;
        } else {
            aVar4 = (w1.a) p3.j.A(nVar.k(), w1.h.f6298q);
            if (aVar4 != null) {
                hVar2.a(new x2.c(262144, aVar4.a()));
            }
            aVar5 = (w1.a) p3.j.A(nVar.k(), w1.h.f6299r);
            if (aVar5 != null) {
                hVar2.a(new x2.c(524288, aVar5.a()));
            }
            aVar6 = (w1.a) p3.j.A(nVar.k(), w1.h.f6300s);
            if (aVar6 != null) {
                hVar2.a(new x2.c(1048576, aVar6.a()));
            }
            iVarK = nVar.k();
            tVar = w1.h.f6302u;
            if (iVarK.a(tVar)) {
                list = (List) nVar.k().b(tVar);
                size2 = list.size();
                qVar = N;
                if (size2 < qVar.f3137b) {
                    throw new IllegalStateException("Can't have more than " + qVar.f3137b + " custom actions for one widget");
                }
                j.i0 i0Var4 = new j.i0();
                j.x xVar4 = j.d0.f3095a;
                j.x xVar5 = new j.x();
                i0Var = this.f5194t;
                i12 = i5;
                if (i0Var.b(i12)) {
                    xVar = (j.x) i0Var.c(i12);
                    iArr = new int[16];
                    iArr2 = qVar.f3136a;
                    i15 = qVar.f3137b;
                    i16 = 0;
                    i17 = 0;
                    while (i16 < i15) {
                        int i45 = iArr2[i16];
                        i18 = i17 + 1;
                        int i46 = i15;
                        if (iArr.length < i18) {
                            int[] iArrCopyOf2 = Arrays.copyOf(iArr, Math.max(i18, (iArr.length * 3) / 2));
                            b4.l.d("copyOf(...)", iArrCopyOf2);
                            iArr = iArrCopyOf2;
                        }
                        iArr[i17] = i45;
                        i16++;
                        i17 = i18;
                        i15 = i46;
                    }
                    arrayList4 = new ArrayList();
                    if (list.size() <= 0) {
                        b.b.l(list.get(0));
                        b4.l.b(xVar);
                        throw null;
                    }
                    if (arrayList4.size() <= 0) {
                        b.b.l(arrayList4.get(0));
                        if (i17 > 0) {
                            int i47 = iArr[0];
                            throw null;
                        }
                        k.a.d("Index must be between 0 and size");
                        throw null;
                    }
                } else if (list.size() > 0) {
                    b.b.l(list.get(0));
                    qVar.c(0);
                    throw null;
                }
                this.f5193s.d(i12, i0Var4);
                i0Var.d(i12, xVar5);
            } else {
                i12 = i5;
            }
        }
        zR = r(nVar);
        if (Build.VERSION.SDK_INT >= 28) {
            accessibilityNodeInfo.setScreenReaderFocusable(zR);
        } else {
            extras = accessibilityNodeInfo.getExtras();
            if (extras != null) {
                extras.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", (zR ? 1 : 0) | (extras.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & (-2)));
            }
        }
        pVar = this.C;
        iC = pVar.c(i12);
        if (iC >= 0) {
            i13 = pVar.f3132c[iC];
        } else {
            i13 = -1;
        }
        if (i13 != -1) {
            m0.v(uVar.getAndroidViewsHandler$ui_release(), i13);
            uVar2 = uVar;
            hVar2.g(uVar2, i13);
            c(i12, hVar2, this.E, null);
        } else {
            uVar2 = uVar;
        }
        pVar2 = this.D;
        iC2 = pVar2.c(i12);
        if (iC2 >= 0) {
            i14 = pVar2.f3132c[iC2];
        } else {
            i14 = -1;
        }
        if (i14 != -1) {
            m0.v(uVar2.getAndroidViewsHandler$ui_release(), i14);
        }
    }

    public final int x(int i5) {
        if (i5 == this.f5178d.getSemanticsOwner().a().f6321g) {
            return -1;
        }
        return i5;
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0086 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:28:0x0088 A[LOOP:1: B:15:0x004c->B:28:0x0088, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:42:0x008b A[EDGE_INSN: B:42:0x008b->B:29:0x008b BREAK  A[LOOP:1: B:15:0x004c->B:28:0x0088], SYNTHETIC] */
    public final void y(w1.n nVar, i2 i2Var) {
        int[] iArr = j.k.f3116a;
        j.s sVar = new j.s();
        List listH = w1.n.h(nVar, 4);
        p1.d0 d0Var = nVar.f6317c;
        int size = listH.size();
        for (int i5 = 0; i5 < size; i5++) {
            w1.n nVar2 = (w1.n) listH.get(i5);
            j.r rVarM = m();
            int i6 = nVar2.f6321g;
            if (rVarM.b(i6)) {
                if (!i2Var.f5229b.c(i6)) {
                    s(d0Var);
                    return;
                }
                sVar.a(i6);
            }
        }
        j.s sVar2 = i2Var.f5229b;
        int[] iArr2 = sVar2.f3145b;
        long[] jArr = sVar2.f3144a;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i7 = 0;
            while (true) {
                long j5 = jArr[i7];
                if ((((~j5) << 7) & j5 & (-9187201950435737472L)) == -9187201950435737472L) {
                    if (i7 != length) {
                        break;
                        break;
                    }
                    i7++;
                } else {
                    int i8 = 8 - ((~(i7 - length)) >>> 31);
                    for (int i9 = 0; i9 < i8; i9++) {
                        if ((255 & j5) < 128 && !sVar.c(iArr2[(i7 << 3) + i9])) {
                            s(d0Var);
                            return;
                        }
                        j5 >>= 8;
                    }
                    if (i8 != 8) {
                        break;
                    } else if (i7 != length) {
                        break;
                    } else {
                        i7++;
                    }
                }
            }
        }
        List listH2 = w1.n.h(nVar, 4);
        int size2 = listH2.size();
        for (int i10 = 0; i10 < size2; i10++) {
            w1.n nVar3 = (w1.n) listH2.get(i10);
            if (m().b(nVar3.f6321g)) {
                Object objD = this.H.d(nVar3.f6321g);
                b4.l.b(objD);
                y(nVar3, (i2) objD);
            }
        }
    }

    public final boolean z(AccessibilityEvent accessibilityEvent) {
        if (!q()) {
            return false;
        }
        if (accessibilityEvent.getEventType() == 2048 || accessibilityEvent.getEventType() == 32768) {
            this.f5190p = true;
        }
        try {
            return ((Boolean) this.f5180f.c(accessibilityEvent)).booleanValue();
        } finally {
            this.f5190p = false;
        }
    }
}
