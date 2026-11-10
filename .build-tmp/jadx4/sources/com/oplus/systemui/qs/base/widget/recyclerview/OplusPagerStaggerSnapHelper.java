package com.oplus.systemui.qs.base.widget.recyclerview;

import android.util.Log;
import androidx.collection.FloatList$;
import androidx.compose.runtime.collection.MutableVectorKt$;
import androidx.recyclerview.widget.COUIRecyclerView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.coui.appcompat.animation.dynamicanimation.COUIDynamicAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringAnimation;
import com.coui.appcompat.animation.dynamicanimation.COUISpringForce;
import com.oplus.keyguard.security.view.OplusKeyguardSimInputViewController$;
import com.oplus.systemui.qs.base.util.DeviceUtil;
import com.oplusos.systemui.common.olivelink.Spring;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ReflectionUtils;
import java.lang.invoke.VarHandle;
import java.util.Arrays;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusPagerStaggerSnapHelper extends RecyclerView.OnFlingListener implements IPagerSnap {
    private static final String TAG = "OplusPagerStaggerSnapHelper";
    private RecyclerView attachedRecyclerView;
    public int offsetX;
    private final Lazy offsetXSpringAnimation$delegate;
    public int offsetY;
    private final Lazy offsetYSpringAnimation$delegate;
    private final RecyclerView.OnScrollListener scrollListener;
    public static final Companion Companion = new Companion();
    private static final Lazy springConfig$delegate = LazyKt__LazyJVMKt.lazy(new OplusPagerStaggerSnapHelper$$ExternalSyntheticLambda2());

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        /* JADX INFO: Access modifiers changed from: private */
        public final Spring getSpringConfig() {
            return (Spring) OplusPagerStaggerSnapHelper.springConfig$delegate.getValue();
        }
    }

    public OplusPagerStaggerSnapHelper() {
        OplusPagerStaggerSnapHelper$$ExternalSyntheticLambda0 oplusPagerStaggerSnapHelper$$ExternalSyntheticLambda0 = new OplusPagerStaggerSnapHelper$$ExternalSyntheticLambda0(0);
        oplusPagerStaggerSnapHelper$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.offsetXSpringAnimation$delegate = LazyKt__LazyJVMKt.lazy(oplusPagerStaggerSnapHelper$$ExternalSyntheticLambda0);
        OplusPagerStaggerSnapHelper$$ExternalSyntheticLambda0 oplusPagerStaggerSnapHelper$$ExternalSyntheticLambda1 = new OplusPagerStaggerSnapHelper$$ExternalSyntheticLambda0(1);
        oplusPagerStaggerSnapHelper$$ExternalSyntheticLambda1.f$0 = this;
        VarHandle.storeStoreFence();
        this.offsetYSpringAnimation$delegate = LazyKt__LazyJVMKt.lazy(oplusPagerStaggerSnapHelper$$ExternalSyntheticLambda1);
        OplusPagerStaggerSnapHelper$scrollListener$1 oplusPagerStaggerSnapHelper$scrollListener$1 = new OplusPagerStaggerSnapHelper$scrollListener$1();
        oplusPagerStaggerSnapHelper$scrollListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.scrollListener = oplusPagerStaggerSnapHelper$scrollListener$1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final COUISpringAnimation getOffsetXSpringAnimation() {
        return (COUISpringAnimation) this.offsetXSpringAnimation$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final COUISpringAnimation getOffsetYSpringAnimation() {
        return (COUISpringAnimation) this.offsetYSpringAnimation$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setScrollState(int i) {
        RecyclerView recyclerView = this.attachedRecyclerView;
        if (recyclerView == null) {
            return;
        }
        ReflectionUtils.methodInvoke(recyclerView, COUIRecyclerView.class, "setScrollState", new Class[]{Integer.TYPE}, Integer.valueOf(i));
    }

    public final void attachToRecyclerView(RecyclerView recyclerView) {
        if (Intrinsics.areEqual(this.attachedRecyclerView, recyclerView)) {
            return;
        }
        RecyclerView recyclerView2 = this.attachedRecyclerView;
        if (recyclerView2 != null) {
            recyclerView2.removeOnScrollListener(this.scrollListener);
            recyclerView2.setOnFlingListener((RecyclerView.OnFlingListener) null);
        }
        this.attachedRecyclerView = recyclerView;
        if (recyclerView != null) {
            if (recyclerView.getOnFlingListener() != null) {
                Path$.ExternalSyntheticBUOutline0.m$1("An instance of OnFlingListener already set.");
                return;
            } else {
                recyclerView.addOnScrollListener(this.scrollListener);
                recyclerView.setOnFlingListener(this);
            }
        }
        scrollToCurrPageCenter();
    }

    public final void cancelFlingAnimation() {
        getOffsetXSpringAnimation().cancel();
        getOffsetYSpringAnimation().cancel();
    }

    public final void endFlingAnimation() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("[endFlingAnimation] skipToEnd isRunning=", "OplusPagerStaggerSnapHelper-->", "SystemUi--QuickSettings", isFlingAnimationRunning());
        }
        if (isFlingAnimationRunning()) {
            getOffsetXSpringAnimation().skipToEnd();
            getOffsetYSpringAnimation().skipToEnd();
        }
    }

    @Override // com.oplus.systemui.qs.base.widget.recyclerview.IPagerSnap
    public boolean flingByOffset(RecyclerView.LayoutManager layoutManager, int i, int i2, int[] iArr) {
        this.offsetX = 0;
        this.offsetY = 0;
        if (layoutManager instanceof IPagerSnapCallback) {
            if (iArr[0] == 0 && iArr[1] == 0) {
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "OplusPagerStaggerSnapHelper-->[flingToPosition] return, offset = 0");
                }
                setScrollState(0);
                return false;
            }
            ((IPagerSnapCallback) layoutManager).setCurrPageIndex(iArr);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "OplusPagerStaggerSnapHelper-->".concat(MutableVectorKt$.ExternalSyntheticOutline0.m("[flingToPosition] offset = [", iArr[0], iArr[1], ", ", "]")));
            }
            if (layoutManager.canScrollHorizontally()) {
                COUISpringAnimation startValue = getOffsetXSpringAnimation().setStartVelocity(RangesKt___RangesKt.coerceIn(i, -8000, 8000)).setStartValue(0.0f);
                COUISpringForce spring = startValue.getSpring();
                Companion companion = Companion;
                spring.setBounce(companion.getSpringConfig().bounce());
                startValue.getSpring().setResponse(companion.getSpringConfig().response());
                startValue.animateToFinalPosition(iArr[0]);
                setScrollState(2);
                return true;
            }
            if (layoutManager.canScrollVertically()) {
                COUISpringAnimation startValue2 = getOffsetYSpringAnimation().setStartVelocity(RangesKt___RangesKt.coerceIn(i2, -8000, 8000)).setStartValue(0.0f);
                COUISpringForce spring2 = startValue2.getSpring();
                Companion companion2 = Companion;
                spring2.setBounce(companion2.getSpringConfig().bounce());
                startValue2.getSpring().setResponse(companion2.getSpringConfig().response());
                startValue2.animateToFinalPosition(iArr[1]);
                setScrollState(2);
                return true;
            }
        }
        return false;
    }

    public final boolean isFlingAnimationRunning() {
        return ((COUIDynamicAnimation) getOffsetXSpringAnimation()).mRunning || ((COUIDynamicAnimation) getOffsetYSpringAnimation()).mRunning;
    }

    public final boolean isSlowScrolling() {
        if (isFlingAnimationRunning()) {
            float fFloatValue = ((Float) ReflectionUtils.getValue(COUIDynamicAnimation.class, getOffsetXSpringAnimation(), "mVelocity")).floatValue();
            float fFloatValue2 = ((Float) ReflectionUtils.getValue(COUIDynamicAnimation.class, getOffsetYSpringAnimation(), "mVelocity")).floatValue();
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("[isSlowScrolling] currVelX=", fFloatValue, ", currVelY=", fFloatValue2, "OplusPagerStaggerSnapHelper-->"));
            }
            if (Math.abs(fFloatValue) > 0.0f && Math.abs(fFloatValue) < 2500.0f) {
                return true;
            }
            if (Math.abs(fFloatValue2) > 0.0f && Math.abs(fFloatValue2) < 2500.0f) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:36:0x0082  */
    public final boolean onFling(int i, int i2) {
        IPagerSnapCallback layoutManager;
        int[] iArrFindPrePageOffset;
        int[] iArrCurrPageOffset;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", OplusKeyguardSimInputViewController$.ExternalSyntheticOutline0.m("[onFling] velocityX=", i, i2, ", velocityY=", "OplusPagerStaggerSnapHelper-->"));
        }
        RecyclerView recyclerView = this.attachedRecyclerView;
        if (recyclerView == null || (layoutManager = recyclerView.getLayoutManager()) == null || recyclerView.getAdapter() == null) {
            return false;
        }
        int iDp2px = DeviceUtil.dp2px(recyclerView.getContext(), 50.0f);
        int i3 = layoutManager.getLayoutDirection() == 1 ? -i : i;
        int[] iArr = new int[2];
        boolean z = layoutManager instanceof IPagerSnapCallback;
        if (!z) {
            iArrFindPrePageOffset = iArr;
        } else if (layoutManager.canScrollHorizontally()) {
            if (i3 > iDp2px) {
                iArrFindPrePageOffset = layoutManager.findNextPageOffset();
            } else if (i3 < (-iDp2px)) {
                iArrFindPrePageOffset = layoutManager.findPrePageOffset();
            } else {
                iArrFindPrePageOffset = iArr;
            }
        } else if (!layoutManager.canScrollVertically()) {
            iArrFindPrePageOffset = iArr;
        } else if (i2 > iDp2px) {
            iArrFindPrePageOffset = layoutManager.findNextPageOffset();
        } else if (i2 < (-iDp2px)) {
            iArrFindPrePageOffset = layoutManager.findPrePageOffset();
        } else {
            iArrFindPrePageOffset = iArr;
        }
        if (Arrays.equals(iArrFindPrePageOffset, iArr)) {
            IPagerSnapCallback iPagerSnapCallback = z ? layoutManager : null;
            if (iPagerSnapCallback != null && (iArrCurrPageOffset = iPagerSnapCallback.currPageOffset()) != null) {
                iArrFindPrePageOffset = iArrCurrPageOffset;
            }
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", NotificationPanelViewController$.ExternalSyntheticOutline0.m(i, i2, ", velocityY=", "OplusPagerStaggerSnapHelper-->", FloatList$.ExternalSyntheticOutline0.m("[onFling] targetOffset=[", iArrFindPrePageOffset[0], iArrFindPrePageOffset[1], ", ", "], velocityX=")));
        }
        flingByOffset(layoutManager, i, i2, iArrFindPrePageOffset);
        return true;
    }

    public final void scrollToCurrPageCenter() {
        IPagerSnapCallback layoutManager;
        int[] iArrCurrPageOffset;
        RecyclerView recyclerView;
        RecyclerView recyclerView2 = this.attachedRecyclerView;
        if (recyclerView2 == null || (layoutManager = recyclerView2.getLayoutManager()) == null) {
            return;
        }
        cancelFlingAnimation();
        boolean z = layoutManager instanceof IPagerSnapCallback;
        IPagerSnapCallback iPagerSnapCallback = z ? layoutManager : null;
        if (iPagerSnapCallback == null || (iArrCurrPageOffset = iPagerSnapCallback.currPageOffset()) == null) {
            iArrCurrPageOffset = new int[2];
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "OplusPagerStaggerSnapHelper-->".concat(MutableVectorKt$.ExternalSyntheticOutline0.m("[scrollToCurrPageCenter],offset = [", iArrCurrPageOffset[0], iArrCurrPageOffset[1], ", ", "]")));
        }
        IPagerSnapCallback iPagerSnapCallback2 = z ? layoutManager : null;
        if (iPagerSnapCallback2 != null) {
            iPagerSnapCallback2.setCurrPageIndex(iArrCurrPageOffset);
        }
        int i = iArrCurrPageOffset[0];
        if ((i == 0 && iArrCurrPageOffset[1] == 0) || (recyclerView = this.attachedRecyclerView) == null) {
            return;
        }
        recyclerView.smoothScrollBy(i, iArrCurrPageOffset[1]);
    }
}
