package com.oplus.systemui.qs;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.DependencyEx;
import com.android.systemui.qs.OplusQSContainerEx;
import com.android.systemui.separate.OplusSimpleQSManager;
import com.android.systemui.util.SeparateModeHelper;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQSContainerExImpl extends OplusQSContainerEx {
    private final Rect mQsContainerBound = new Rect();

    public final boolean isEventInView(View view, MotionEvent motionEvent, int i, boolean z, boolean z2) {
        view.getBoundsOnScreen(this.mQsContainerBound);
        int iRoundToInt = z2 ? this.mQsContainerBound.left : MathKt.roundToInt((motionEvent.getRawX() - motionEvent.getX()) + view.getLeft());
        int iRoundToInt2 = MathKt.roundToInt(motionEvent.getRawY() - motionEvent.getY());
        Rect rect = this.mQsContainerBound;
        if (z) {
            rect.offsetTo(0, iRoundToInt2);
        } else {
            rect.bottom = rect.top + i;
            rect.offsetTo(iRoundToInt, iRoundToInt2);
        }
        return this.mQsContainerBound.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
    }

    public boolean isInsideQsContainer(MotionEvent motionEvent, int i, boolean z) {
        OplusQSContainerImpl qsContainer;
        if (SeparateModeHelper.INSTANCE.getSeparateMode()) {
            ViewGroup qSContainer = ((OplusSimpleQSManager) DependencyEx.sDependency.getDependency(OplusSimpleQSManager.class)).getQSContainer();
            if (qSContainer == null) {
                return false;
            }
            return isEventInView(qSContainer, motionEvent, i, false, true);
        }
        if (QSFragmentHelper.getInstance().isCoveringOrCoveredQs() || (qsContainer = QSFragmentHelper.getInstance().getQsContainer()) == null) {
            return false;
        }
        return isEventInView(qsContainer, motionEvent, i, z, false);
    }
}
