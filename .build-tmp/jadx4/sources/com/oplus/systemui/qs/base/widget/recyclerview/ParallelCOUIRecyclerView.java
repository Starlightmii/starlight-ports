package com.oplus.systemui.qs.base.widget.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.COUIRecyclerView;
import androidx.recyclerview.widget.RecyclerView;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.ReflectionUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ParallelCOUIRecyclerView extends COUIRecyclerView {
    public static final Companion Companion = new Companion();
    private static final String TAG = "ParallelCOUIRecyclerView";
    private final Lazy drawMethod$delegate;
    private StaggeredPagerLayoutManager staggeredLayoutManager;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public final void handleClipSplitParentBounds(RecyclerView recyclerView, boolean z) {
            recyclerView.setTag(2131365589, Boolean.valueOf(z));
        }

        public final void handleIgnoreParentBounds(RecyclerView recyclerView, boolean z) {
            recyclerView.setTag(2131365590, Boolean.valueOf(z));
        }

        public final void handleIgnoreParentBoundsForScroll(RecyclerView recyclerView, boolean z) {
            recyclerView.setTag(2131365591, Boolean.valueOf(z));
        }

        public final void ignoreParentBounds(View view, boolean z) {
            view.setTag(2131365602, Boolean.valueOf(z));
        }

        public final boolean shouldClipSplitParentBounds$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(RecyclerView recyclerView) {
            Object tag = recyclerView.getTag(2131365589);
            Boolean bool = tag instanceof Boolean ? (Boolean) tag : null;
            if (bool != null) {
                return bool.booleanValue();
            }
            return false;
        }

        public final boolean shouldHandleIgnoreParentBounds$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(RecyclerView recyclerView) {
            Object tag = recyclerView.getTag(2131365590);
            Boolean bool = tag instanceof Boolean ? (Boolean) tag : null;
            if (bool != null) {
                return bool.booleanValue();
            }
            return false;
        }

        public final boolean shouldIgnoreParentBounds$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(View view) {
            Object tag = view.getTag(2131365602);
            Boolean bool = tag instanceof Boolean ? (Boolean) tag : null;
            if (bool != null) {
                return bool.booleanValue();
            }
            return false;
        }

        public final boolean shouldIgnoreParentBoundsForScroll(RecyclerView recyclerView) {
            Object tag = recyclerView.getTag(2131365591);
            Boolean bool = tag instanceof Boolean ? (Boolean) tag : null;
            if (bool != null) {
                return bool.booleanValue();
            }
            return false;
        }
    }

    public ParallelCOUIRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.drawMethod$delegate = LazyKt__LazyJVMKt.lazy(new ParallelCOUIRecyclerView$$ExternalSyntheticLambda0());
    }

    private final Method getDrawMethod() {
        return (Method) this.drawMethod$delegate.getValue();
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0050  */
    /* JADX WARN: Code duplicated, block: B:33:0x0065  */
    /* JADX WARN: Code duplicated, block: B:6:0x001c  */
    /* JADX WARN: Multi-variable type inference failed */
    public void dispatchDraw(Canvas canvas) throws IllegalAccessException, InvocationTargetException {
        boolean z;
        List<View> listFindVisibleChildren;
        Method drawMethod;
        boolean z2;
        Companion companion = Companion;
        boolean zShouldHandleIgnoreParentBounds$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib = companion.shouldHandleIgnoreParentBounds$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(this);
        boolean zShouldIgnoreParentBoundsForScroll = companion.shouldIgnoreParentBoundsForScroll(this);
        boolean zShouldClipSplitParentBounds$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib = companion.shouldClipSplitParentBounds$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib(this);
        if (zShouldClipSplitParentBounds$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib) {
            StaggeredPagerLayoutManager staggeredPagerLayoutManager = this.staggeredLayoutManager;
            if (staggeredPagerLayoutManager == null || (getScrollState() == 2 && staggeredPagerLayoutManager.getCurrentPageIndex() == staggeredPagerLayoutManager.getPageCount() - 1)) {
                z2 = true;
            } else {
                int[] iArrCurrScrollOffset = staggeredPagerLayoutManager.currScrollOffset();
                if (staggeredPagerLayoutManager.canScrollHorizontally()) {
                    z2 = !(iArrCurrScrollOffset[0] == getMeasuredWidth() * staggeredPagerLayoutManager.getCurrentPageIndex());
                } else {
                    z2 = true;
                }
            }
            if (z2) {
                z = true;
            } else {
                z = false;
            }
        } else {
            z = false;
        }
        boolean z3 = zShouldIgnoreParentBoundsForScroll && !z;
        int i = ((ViewGroup) this).mPaddingTop;
        if (zShouldHandleIgnoreParentBounds$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib) {
            ((ViewGroup) this).mPaddingTop = -200;
        }
        Object obj = null;
        if (zShouldHandleIgnoreParentBounds$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib || z3) {
            StaggeredPagerLayoutManager staggeredPagerLayoutManager2 = this.staggeredLayoutManager;
            if (zShouldClipSplitParentBounds$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib) {
                if (staggeredPagerLayoutManager2 != null) {
                    listFindVisibleChildren = staggeredPagerLayoutManager2.findCurrentPageChildren();
                } else {
                    listFindVisibleChildren = null;
                }
            } else if (staggeredPagerLayoutManager2 != null) {
                listFindVisibleChildren = staggeredPagerLayoutManager2.findVisibleChildren();
            } else {
                listFindVisibleChildren = null;
            }
        } else {
            listFindVisibleChildren = null;
        }
        if (z3 && listFindVisibleChildren != null) {
            Iterator<T> it = listFindVisibleChildren.iterator();
            while (it.hasNext()) {
                ((View) it.next()).setTransitionVisibility(4);
            }
        }
        super.dispatchDraw(canvas);
        if (z3 && listFindVisibleChildren != null) {
            Iterator<T> it2 = listFindVisibleChildren.iterator();
            while (it2.hasNext()) {
                ((View) it2.next()).setTransitionVisibility(0);
            }
        }
        if (zShouldHandleIgnoreParentBounds$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib) {
            ((ViewGroup) this).mPaddingTop = i;
        }
        if (zShouldHandleIgnoreParentBounds$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib || z3) {
            if (z3 && listFindVisibleChildren != null) {
                for (View view : listFindVisibleChildren) {
                    Method drawMethod2 = getDrawMethod();
                    if (drawMethod2 != null) {
                        drawMethod2.invoke(view, canvas, this, 0L);
                    }
                }
            }
            if (listFindVisibleChildren != null) {
                for (Object obj2 : listFindVisibleChildren) {
                    if (Companion.shouldIgnoreParentBounds$vendor__oplus__packages__apps__systemui__oplus_qs_base__android_common__OplusSystemUIQSBaseLib((View) obj2)) {
                        obj = obj2;
                        break;
                    }
                }
                View view2 = (View) obj;
                if (view2 == null || (drawMethod = getDrawMethod()) == null) {
                    return;
                }
                drawMethod.invoke(view2, canvas, this, 0L);
            }
        }
    }

    public final boolean hasOverlappingRendering() {
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0020  */
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        Boolean boolValueOf;
        if (motionEvent.getActionMasked() == 0) {
            IPagerSnap onFlingListener = getOnFlingListener();
            IPagerSnap iPagerSnap = onFlingListener instanceof IPagerSnap ? onFlingListener : null;
            if (iPagerSnap != null) {
                boolValueOf = Boolean.valueOf(((OplusPagerStaggerSnapHelper) iPagerSnap).isSlowScrolling());
            } else {
                boolValueOf = null;
            }
        } else {
            boolValueOf = null;
        }
        boolean zOnInterceptTouchEvent = super.onInterceptTouchEvent(motionEvent);
        if (getScrollState() == 1 && motionEvent.getActionMasked() == 0) {
            RecyclerView.OnFlingListener onFlingListener2 = getOnFlingListener();
            if ((onFlingListener2 instanceof IPagerSnap ? (IPagerSnap) onFlingListener2 : null) != null) {
                Boolean bool = Boolean.TRUE;
                ReflectionUtils.setValue(COUIRecyclerView.class, this, "mIsTouchDownWhileSlowScrolling", Boolean.valueOf(Intrinsics.areEqual(boolValueOf, bool)));
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                    Log.d("SystemUi--QuickSettings", "ParallelCOUIRecyclerView-->".concat("[onInterceptTouchEvent] set mIsTouchDownWhileSlowScrolling " + Intrinsics.areEqual(boolValueOf, bool) + ", " + motionEvent));
                }
            }
        }
        return zOnInterceptTouchEvent;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.staggeredLayoutManager = layoutManager instanceof StaggeredPagerLayoutManager ? (StaggeredPagerLayoutManager) layoutManager : null;
        super.setLayoutManager(layoutManager);
    }

    public ParallelCOUIRecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public /* synthetic */ ParallelCOUIRecyclerView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public ParallelCOUIRecyclerView(Context context) {
        this(context, null, 0, 6, null);
    }
}
