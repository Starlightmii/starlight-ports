package x2;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.List;
import q1.f0;

/* JADX INFO: compiled from: r8-map-id-864ffde9d59ed35bc4209251fe295afa616a17016f049d977cb0fda4927bbf7c */
/* JADX INFO: loaded from: classes.dex */
public final class i extends AccessibilityNodeProvider {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a2.h f6566a;

    public i(a2.h hVar) {
        this.f6566a = hVar;
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public final void addExtraDataToAccessibilityNodeInfo(int i5, AccessibilityNodeInfo accessibilityNodeInfo, String str, Bundle bundle) {
        ((f0) this.f6566a.f50f).c(i5, new h(accessibilityNodeInfo), str, bundle);
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public final AccessibilityNodeInfo createAccessibilityNodeInfo(int i5) {
        h hVarQ = this.f6566a.q(i5);
        if (hVarQ == null) {
            return null;
        }
        return hVarQ.f6563a;
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public final List findAccessibilityNodeInfosByText(String str, int i5) {
        this.f6566a.getClass();
        return null;
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public final AccessibilityNodeInfo findFocus(int i5) {
        a2.h hVar = this.f6566a;
        h hVarQ = hVar.q(((f0) hVar.f50f).f5188n);
        if (hVarQ == null) {
            return null;
        }
        return hVarQ.f6563a;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:131:0x01e5  */
    /* JADX WARN: Code duplicated, block: B:222:0x0359  */
    /* JADX WARN: Code duplicated, block: B:224:0x035d  */
    /* JADX WARN: Code duplicated, block: B:225:0x035f  */
    /* JADX WARN: Code duplicated, block: B:228:0x0364  */
    /* JADX WARN: Code duplicated, block: B:229:0x0366  */
    /* JADX WARN: Code duplicated, block: B:232:0x036c  */
    /* JADX WARN: Code duplicated, block: B:233:0x036e  */
    /* JADX WARN: Code duplicated, block: B:236:0x0374  */
    /* JADX WARN: Code duplicated, block: B:237:0x0376  */
    /* JADX WARN: Code duplicated, block: B:240:0x037c  */
    /* JADX WARN: Code duplicated, block: B:241:0x037e  */
    /* JADX WARN: Code duplicated, block: B:244:0x0384  */
    /* JADX WARN: Code duplicated, block: B:245:0x0386  */
    /* JADX WARN: Code duplicated, block: B:252:0x0392  */
    /* JADX WARN: Code duplicated, block: B:261:0x03a1  */
    /* JADX WARN: Code duplicated, block: B:263:0x03a9  */
    /* JADX WARN: Code duplicated, block: B:266:0x03b4  */
    /* JADX WARN: Code duplicated, block: B:269:0x03b9 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:276:0x03db  */
    /* JADX WARN: Code duplicated, block: B:278:0x03fe  */
    /* JADX WARN: Code duplicated, block: B:281:0x0403  */
    /* JADX WARN: Code duplicated, block: B:286:0x041d  */
    /* JADX WARN: Code duplicated, block: B:289:0x0426  */
    /* JADX WARN: Code duplicated, block: B:293:0x042d  */
    /* JADX WARN: Code duplicated, block: B:295:0x0437  */
    /* JADX WARN: Code duplicated, block: B:298:0x043c A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:336:0x04b5  */
    /* JADX WARN: Code duplicated, block: B:338:0x04bd  */
    /* JADX WARN: Code duplicated, block: B:341:0x04c2 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:343:0x04c6  */
    /* JADX WARN: Code duplicated, block: B:344:0x04cb  */
    /* JADX WARN: Code duplicated, block: B:346:0x04d1 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:347:0x04d3  */
    /* JADX WARN: Code duplicated, block: B:350:0x04da  */
    /* JADX WARN: Code duplicated, block: B:352:0x04e2  */
    /* JADX WARN: Code duplicated, block: B:358:0x04ff  */
    /* JADX WARN: Code duplicated, block: B:360:0x0503  */
    /* JADX WARN: Code duplicated, block: B:362:0x050b  */
    /* JADX WARN: Code duplicated, block: B:363:0x050d  */
    /* JADX WARN: Code duplicated, block: B:365:0x0511  */
    /* JADX WARN: Code duplicated, block: B:367:0x0517  */
    /* JADX WARN: Code duplicated, block: B:368:0x0519  */
    /* JADX WARN: Code duplicated, block: B:371:0x051e  */
    /* JADX WARN: Code duplicated, block: B:460:0x0658  */
    /* JADX WARN: Code duplicated, block: B:465:0x0671  */
    /* JADX WARN: Code duplicated, block: B:472:0x0683  */
    /* JADX WARN: Code duplicated, block: B:474:0x0687  */
    /* JADX WARN: Code duplicated, block: B:476:0x069b  */
    /* JADX WARN: Code duplicated, block: B:478:0x069f  */
    /* JADX WARN: Code restructure failed: missing block: B:540:0x0189, code lost:
    
        r1 = null;
     */
    /* JADX WARN: Switch 'out' block B:6:0x0025 for B:24:0x005a already processed. Defaulting to fallback option. */
    /* JADX WARN: Switch 'out' block B:6:0x0025 for B:25:0x005d already processed. Defaulting to fallback option. */
    @Override // android.view.accessibility.AccessibilityNodeProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean performAction(int r21, int r22, android.os.Bundle r23) {
        /*
            Method dump skipped, instruction units count: 2068
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: x2.i.performAction(int, int, android.os.Bundle):boolean");
    }
}
