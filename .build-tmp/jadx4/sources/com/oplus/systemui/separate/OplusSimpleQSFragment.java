package com.oplus.systemui.separate;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.systemui.DependencyEx;
import com.android.systemui.plugins.qs.QS;
import com.android.systemui.plugins.qs.QSContainerController;
import com.android.systemui.qs.deprecated.BaseQuickStatusBarHeader;
import com.android.systemui.separate.OplusSimpleQSManager;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl$2$;
import com.android.systemui.util.LifecycleFragment;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSimpleQSFragment extends LifecycleFragment implements QS {
    public static final Companion Companion = new Companion();
    public static final String TAG = "OplusSimpleQSFragment";
    private BaseQuickStatusBarHeader header;
    private OplusQSSimpleHeader simpleQsHeader;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    private final View getHeader() {
        return this.header;
    }

    public final void animateHeaderSlidingOut() {
    }

    public final void closeCustomizer() {
    }

    public final void closeDetail() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int getDesiredHeight() {
        View view = getView();
        if (view != null) {
            return view.getMeasuredHeight();
        }
        return 0;
    }

    public int getHeaderBottom() {
        return getHeader().getBottom();
    }

    public void getHeaderBoundsOnScreen(Rect rect) {
        BaseQuickStatusBarHeader baseQuickStatusBarHeader = this.header;
        if (baseQuickStatusBarHeader != null) {
            baseQuickStatusBarHeader.getBoundsOnScreen(rect);
        } else if (rect != null) {
            rect.setEmpty();
        }
    }

    public int getHeaderLeft() {
        return getHeader().getLeft();
    }

    public int getHeaderTop() {
        return getHeader().getTop();
    }

    public int getQsMinExpansionHeight() {
        OplusQSSimpleHeader oplusQSSimpleHeader = this.simpleQsHeader;
        if (oplusQSSimpleHeader != null) {
            return oplusQSSimpleHeader.getSimpleQSHeight();
        }
        return 0;
    }

    public final void hideImmediately() {
    }

    public final boolean isCustomizing() {
        return false;
    }

    public final boolean isHeaderShown() {
        BaseQuickStatusBarHeader baseQuickStatusBarHeader = this.header;
        if (baseQuickStatusBarHeader != null) {
            return baseQuickStatusBarHeader.isShown();
        }
        return false;
    }

    public final boolean isShowingDetail() {
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(2131559373, viewGroup, false);
    }

    public final void onDestroy() {
        super.onDestroy();
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "onDestroy", null, 8);
        ((OplusSimpleQSManager) DependencyEx.sDependency.getDependency(OplusSimpleQSManager.class)).onDestroy();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onViewCreated(View view, Bundle bundle) {
        super/*android.app.Fragment*/.onViewCreated(view, bundle);
        this.simpleQsHeader = (OplusQSSimpleHeader) view.requireViewById(2131365229);
        this.header = view.requireViewById(2131363334);
        LogUtil.i$default(LogUtil.TAG_NOTIFICATION, TAG, "onViewCreated", null, 8);
        ((OplusSimpleQSManager) DependencyEx.sDependency.getDependency(OplusSimpleQSManager.class)).onViewCreated(view);
    }

    public void setCollapseExpandAction(Runnable runnable) {
    }

    public void setCollapsedMediaVisibilityChangedListener(Consumer<Boolean> consumer) {
    }

    public void setContainerController(QSContainerController qSContainerController) {
    }

    public void setExpanded(boolean z) {
    }

    public final void setFancyClipping(int i, int i2, int i3, int i4, int i5, boolean z, boolean z2) {
    }

    public void setHeaderClickable(boolean z) {
    }

    public void setHeightOverride(int i) {
    }

    public void setInSplitShade(boolean z) {
    }

    public void setIsNotificationPanelFullWidth(boolean z) {
    }

    public void setListening(boolean z) {
        ((OplusSimpleQSManager) DependencyEx.sDependency.getDependency(OplusSimpleQSManager.class)).setListening(z);
    }

    public void setOverscrolling(boolean z) {
    }

    public void setPanelView(QS.HeightListener heightListener) {
    }

    public final void setQsExpansion(float f, float f2, float f3, float f4) {
        if (f > 0.0f && LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NOTIFICATION)) {
            Log.d("SystemUi--Notification", FlashlightControllerImpl$2$.ExternalSyntheticOutline0.m("setQsExpansion qsExpansionFraction: ", f, ",,panelExpansionFraction: ", f2, "OplusSimpleQSFragment-->"));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setQsVisible(boolean z) {
        View view;
        if (z && ((view = getView()) == null || view.getVisibility() != 0)) {
            ((OplusSimpleQSManager) DependencyEx.sDependency.getDependency(OplusSimpleQSManager.class)).updateFakeLocation();
        }
        View view2 = getView();
        if (view2 != null) {
            view2.setVisibility(z ? 0 : 4);
        }
        ((OplusSimpleQSManager) DependencyEx.sDependency.getDependency(OplusSimpleQSManager.class)).setQsVisible(z);
    }
}
