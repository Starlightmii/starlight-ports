package com.oplusos.systemui.common.rendercache;

import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewRootImpl;
import android.view.ViewTreeObserver;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RenderBooster$Companion$applyRenderBoosterOnPreDraw$onPreDrawListener$1 implements ViewTreeObserver.OnPreDrawListener {
    public /* synthetic */ ViewRootImpl $this_applyRenderBoosterOnPreDraw;

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        long jUptimeNanos = SystemClock.uptimeNanos();
        this.$this_applyRenderBoosterOnPreDraw.getView().setTag(2131365583, null);
        this.$this_applyRenderBoosterOnPreDraw.getView().getViewTreeObserver().removeOnPreDrawListener(this);
        ArrayList<WeakReference<RenderBooster>> arrayListBoosterViews = RenderBooster.Companion.boosterViews(this.$this_applyRenderBoosterOnPreDraw);
        ArrayList arrayList = new ArrayList();
        int size = arrayListBoosterViews.size();
        int i = 0;
        while (i < size) {
            WeakReference<RenderBooster> weakReference = arrayListBoosterViews.get(i);
            i++;
            RenderBooster renderBooster = weakReference.get();
            if (renderBooster != null && renderBooster.invalidateType != 0) {
                arrayList.add(weakReference);
            }
        }
        ArrayList arrayList2 = RenderBooster.boosterApplyOrder;
        int size2 = arrayList.size();
        int i2 = 0;
        while (i2 < size2) {
            Object obj = arrayList.get(i2);
            i2++;
            RenderBooster renderBooster2 = (RenderBooster) ((WeakReference) obj).get();
            if (renderBooster2 != null) {
                arrayList2.add(renderBooster2);
            }
        }
        if (!RenderBooster.boosterApplyOrder.isEmpty()) {
            RenderBooster.Companion companion = RenderBooster.Companion;
            ArrayList arrayList3 = RenderBooster.boosterApplyOrder;
            companion.getClass();
            int size3 = arrayList3.size();
            if (size3 > 1) {
                if (RenderBooster.boosterDepthScratch.length < size3) {
                    int length = RenderBooster.boosterDepthScratch.length * 2;
                    if (size3 >= length) {
                        length = size3;
                    }
                    RenderBooster.boosterDepthScratch = new int[length];
                }
                for (int i3 = 0; i3 < size3; i3++) {
                    int[] iArr = RenderBooster.boosterDepthScratch;
                    int i4 = 0;
                    for (Object parent = ((RenderBooster) arrayList3.get(i3)).getTargetView().getParent(); parent instanceof View; parent = ((View) parent).getParent()) {
                        i4++;
                    }
                    iArr[i3] = i4;
                }
                for (int i5 = 1; i5 < size3; i5++) {
                    int i6 = RenderBooster.boosterDepthScratch[i5];
                    RenderBooster renderBooster3 = (RenderBooster) arrayList3.get(i5);
                    int i7 = i5 - 1;
                    while (i7 >= 0 && RenderBooster.boosterDepthScratch[i7] < i6) {
                        int i8 = i7 + 1;
                        arrayList3.set(i8, arrayList3.get(i7));
                        RenderBooster.boosterDepthScratch[i8] = RenderBooster.boosterDepthScratch[i7];
                        i7--;
                    }
                    int i9 = i7 + 1;
                    arrayList3.set(i9, renderBooster3);
                    RenderBooster.boosterDepthScratch[i9] = i6;
                }
            }
            ArrayList arrayList4 = RenderBooster.boosterApplyOrder;
            int size4 = arrayList4.size();
            int i10 = 0;
            while (i10 < size4) {
                Object obj2 = arrayList4.get(i10);
                i10++;
                ((RenderBooster) obj2).apply$vendor__oplus__packages__apps__systemui__oplus_common__android_common__OplusCommon();
            }
            RenderBooster.boosterApplyOrder.clear();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
                Log.d("SystemUi--Common", "RenderBooster-->".concat("[onPreDraw-apply] cost=" + (SystemClock.uptimeNanos() - jUptimeNanos) + "ns"));
            }
        } else if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_COMMON)) {
            Log.d("SystemUi--Common", "RenderBooster-->".concat("[onPreDraw-apply] cost=" + (SystemClock.uptimeNanos() - jUptimeNanos) + "ns"));
            return true;
        }
        return true;
    }
}
