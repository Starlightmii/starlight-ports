package com.oplus.systemui.volume.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.oplus.systemui.volume.view.OplusVolumeDialogView;
import com.oplusos.systemui.common.feature.FeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class VolumeLayoutUtil {
    public static final VolumeLayoutUtil INSTANCE = new VolumeLayoutUtil();
    public static final String TAG = "VolumeLayoutUtil";

    /* JADX INFO: renamed from: com.oplus.systemui.volume.utils.VolumeLayoutUtil$prepareSideVolumeProgressOverflow$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ FrameLayout $host;
        public /* synthetic */ int $overflowTop;
        public /* synthetic */ View $row;
        public /* synthetic */ FrameLayout.LayoutParams $rowLp;

        @Override // java.lang.Runnable
        public final void run() {
            VolumeLayoutUtil.prepareSideVolumeProgressOverflow$applyLayout(this.$host, this.$rowLp, this.$overflowTop, this.$row);
        }
    }

    public static final Rect computePanelAccessibilityBoundsInScreen(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i = iArr[0];
        return new Rect(i, iArr[1], view.getWidth() + i, view.getHeight() + iArr[1]);
    }

    public static final boolean containsPanelAccessibilityBounds(View view, int i, int i2) {
        if (view.getVisibility() != 0) {
            return false;
        }
        return computePanelAccessibilityBoundsInScreen(view).contains(i, i2);
    }

    public static final int dp2Px(float f, Context context) {
        if (context == null) {
            return (int) f;
        }
        OplusVolumeDialogView.Companion companion = OplusVolumeDialogView.Companion;
        if (companion.getSScale() <= 0.0f) {
            companion.setSScale(context.getResources().getDisplayMetrics().density);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            Log.d("SystemUi--Volume", FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("dp2Px: density1: ", companion.getSScale(), ",density2: ", context.getResources().getDisplayMetrics().density, "VolumeLayoutUtil-->"));
        }
        return (int) ((companion.getSScale() * f) + 0.5f);
    }

    public static final View findItemViewAtTransformedCoords(RecyclerView recyclerView, float f, float f2) {
        int childCount = recyclerView.getChildCount();
        while (true) {
            childCount--;
            if (-1 >= childCount) {
                return null;
            }
            View childAt = recyclerView.getChildAt(childCount);
            if (childAt != null) {
                float translationX = childAt.getTranslationX() + childAt.getLeft();
                float translationY = childAt.getTranslationY() + childAt.getTop();
                float width = childAt.getWidth() + translationX;
                float height = childAt.getHeight() + translationY;
                if (f >= translationX && f <= width && f2 >= translationY && f2 <= height) {
                    return childAt;
                }
            }
        }
    }

    public static final int getVolumePanelHeight(RecyclerView.ViewHolder viewHolder) {
        return ((WindowManager) viewHolder.itemView.getContext().getSystemService("window")).getCurrentWindowMetrics().getBounds().height();
    }

    public static final int getVolumePanelWidth(RecyclerView.ViewHolder viewHolder) {
        FeatureOption.INSTANCE.getClass();
        return FeatureOption.isTablet() ? dp2Px(360.0f, viewHolder.itemView.getContext()) : ((WindowManager) viewHolder.itemView.getContext().getSystemService("window")).getCurrentWindowMetrics().getBounds().width();
    }

    public static final int getYLocationOnScreen(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return iArr[1];
    }

    public static final boolean isLandscape(Context context) {
        boolean z = context.getResources().getConfiguration().orientation == 2;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("isLandscape=", "OplusVolumeDialogView-->", "SystemUi--Volume", z);
        }
        return z;
    }

    public static final boolean isPointInAnyItem(RecyclerView recyclerView, float f, float f2) {
        return findItemViewAtTransformedCoords(recyclerView, f, f2) != null;
    }

    public static final void prepareSideVolumeProgressOverflow(View view, FrameLayout frameLayout) {
        if (view == null || frameLayout == null) {
            return;
        }
        if (frameLayout.getPaddingTop() != 0) {
            frameLayout.setPadding(frameLayout.getPaddingLeft(), 0, frameLayout.getPaddingRight(), frameLayout.getPaddingBottom());
        }
        Resources resources = view.getContext().getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(2131171238) + resources.getDimensionPixelSize(2131173968);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        FrameLayout.LayoutParams layoutParams2 = layoutParams instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams : null;
        if (layoutParams2 == null) {
            layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
            view.setLayoutParams(layoutParams2);
        }
        layoutParams2.topMargin = -dimensionPixelSize;
        if (frameLayout.getHeight() > 0) {
            prepareSideVolumeProgressOverflow$applyLayout(frameLayout, layoutParams2, dimensionPixelSize, view);
            return;
        }
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.$host = frameLayout;
        anonymousClass1.$rowLp = layoutParams2;
        anonymousClass1.$overflowTop = dimensionPixelSize;
        anonymousClass1.$row = view;
        VarHandle.storeStoreFence();
        frameLayout.post(anonymousClass1);
    }

    public static final void prepareSideVolumeProgressOverflow$applyLayout(FrameLayout frameLayout, FrameLayout.LayoutParams layoutParams, int i, View view) {
        int height = frameLayout.getHeight();
        if (height <= 0) {
            return;
        }
        layoutParams.width = -1;
        layoutParams.height = height + i;
        view.setLayoutParams(layoutParams);
        View viewFindViewById = view.findViewById(2131364972);
        if (viewFindViewById == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams2 = viewFindViewById.getLayoutParams();
        ConstraintLayout.LayoutParams layoutParams3 = layoutParams2 instanceof ConstraintLayout.LayoutParams ? (ConstraintLayout.LayoutParams) layoutParams2 : null;
        if (layoutParams3 == null || ((ViewGroup.MarginLayoutParams) layoutParams3).topMargin == i) {
            return;
        }
        ((ViewGroup.MarginLayoutParams) layoutParams3).topMargin = i;
        viewFindViewById.setLayoutParams(layoutParams3);
    }

    public static final void resetSideVolumeProgressRevealLayout(View view, FrameLayout frameLayout) {
        if (view == null || frameLayout == null) {
            return;
        }
        if (frameLayout.getPaddingTop() != 0) {
            frameLayout.setPadding(frameLayout.getPaddingLeft(), 0, frameLayout.getPaddingRight(), frameLayout.getPaddingBottom());
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        FrameLayout.LayoutParams layoutParams2 = layoutParams instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams : null;
        if (layoutParams2 != null) {
            layoutParams2.topMargin = 0;
            layoutParams2.height = -1;
            view.setLayoutParams(layoutParams2);
        }
        View viewFindViewById = view.findViewById(2131364972);
        if (viewFindViewById != null) {
            ConstraintLayout.LayoutParams layoutParams3 = viewFindViewById.getLayoutParams();
            ConstraintLayout.LayoutParams layoutParams4 = layoutParams3 instanceof ConstraintLayout.LayoutParams ? layoutParams3 : null;
            if (layoutParams4 != null) {
                ((ViewGroup.MarginLayoutParams) layoutParams4).topMargin = 0;
                viewFindViewById.setLayoutParams(layoutParams4);
            }
        }
    }

    public static final void setHeight(View view, int i) {
        ViewGroup.LayoutParams layoutParams;
        if (view == null || (layoutParams = view.getLayoutParams()) == null) {
            return;
        }
        layoutParams.height = i;
        view.setLayoutParams(layoutParams);
    }
}
