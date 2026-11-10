package com.oplus.systemui.qs.base.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity$;
import com.coui.appcompat.indicator.COUIPageIndicator;
import com.oplus.systemui.qs.OplusQSBottomImpl;
import com.oplus.systemui.qs.base.util.SeparateQSStatisicUtil;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusQSPageIndicator extends COUIPageIndicator {
    public final ArrayList mCallbacks;
    public boolean mUseOriginalRTL;

    public OplusQSPageIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCallbacks = new ArrayList();
        this.mUseOriginalRTL = false;
    }

    public final boolean isLayoutRtl() {
        if (this.mUseOriginalRTL) {
            return super.isLayoutRtl();
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setDotsCount(int i) {
        int dotsCount = getDotsCount();
        super.setDotsCount(i);
        if (dotsCount == 0 && i > 0) {
            setCurrentPosition(0);
        }
        MediaProjectionPermissionActivity$.ExternalSyntheticOutline0.m(i, "setDotsCount: ", LogUtil.TAG_QS, "OplusQSPageIndicator");
        setNumPages(i);
        SeparateQSStatisicUtil.getSeparateTileInfoData().setQsTilesDotsCount(i);
        setVisibility(i <= 1 ? 4 : 0);
    }

    public void setNumPages(int i) {
        ArrayList arrayList = this.mCallbacks;
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayList.get(i2);
            i2++;
            OplusQSBottomImpl.AnonymousClass1 anonymousClass1 = (OplusQSBottomImpl.AnonymousClass1) obj;
            boolean z = i > 1;
            OplusQSBottomImpl oplusQSBottomImpl = anonymousClass1.this$0;
            if (oplusQSBottomImpl.mShowPageIndicator != z) {
                oplusQSBottomImpl.mShowPageIndicator = z;
                oplusQSBottomImpl.updateResources();
            }
        }
    }

    public void setUseOriginalRTL(boolean z) {
        this.mUseOriginalRTL = z;
    }

    public OplusQSPageIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public OplusQSPageIndicator(Context context) {
        this(context, null);
    }
}
