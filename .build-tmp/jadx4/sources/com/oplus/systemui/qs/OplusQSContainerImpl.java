package com.oplus.systemui.qs;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Handler;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.IndentingPrintWriter;
import android.view.View;
import android.view.ViewRootImpl;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import com.android.systemui.Dependency;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$;
import com.android.systemui.qs.IQSContainerImpl;
import com.android.systemui.qs.deprecated.QSPanel;
import com.android.systemui.util.ViewController;
import com.oplus.systemui.qs.base.temporarilyinfo.TemporarilyInfoManager;
import com.oplus.systemui.qs.customize.OplusQSCustomizer;
import com.oplus.util.OplusDarkModeUtil;
import com.oplusos.systemui.common.util.LogUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusQSContainerImpl extends FrameLayout implements IQSContainerImpl {
    public static final /* synthetic */ int $r8$clinit = 0;
    public View mBackground;
    public OplusQSContainerImplController mController;
    public final OplusQSContainerImpl$$ExternalSyntheticLambda0 mDispatchConfigRunnable;
    public final Path mFancyClippingPath;
    public boolean mHasDispatchConfig;
    public final Configuration mInflateConfiguration;
    public boolean mIsMediaModeChangeInDiffConfig;
    public OplusQSCustomizer mQSCustomizer;
    public QSPanel mQSPanel;
    public OplusQSPanelContainer mQSPanelContainer;
    public boolean mQsDisabled;

    public OplusQSContainerImpl(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mFancyClippingPath = new Path();
        this.mInflateConfiguration = new Configuration();
        OplusQSContainerImpl$$ExternalSyntheticLambda0 oplusQSContainerImpl$$ExternalSyntheticLambda0 = new OplusQSContainerImpl$$ExternalSyntheticLambda0();
        oplusQSContainerImpl$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.mDispatchConfigRunnable = oplusQSContainerImpl$$ExternalSyntheticLambda0;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        if (!this.mFancyClippingPath.isEmpty()) {
            canvas.translate(0.0f, -getTranslationY());
            canvas.clipOutPath(this.mFancyClippingPath);
            canvas.translate(0.0f, getTranslationY());
        }
        super.dispatchDraw(canvas);
    }

    public final void dump(PrintWriter printWriter, String[] strArr) {
        IndentingPrintWriter indentingPrintWriter = new IndentingPrintWriter(printWriter, "  ");
        indentingPrintWriter.println("OplusQSContainerImpl:");
        indentingPrintWriter.println("translationY: " + getTranslationY());
    }

    public OplusQSContainerImplController getController() {
        return this.mController;
    }

    public int getQqsHeight() {
        return super.getQqsHeight();
    }

    public int getQsHeight() {
        return super.getQsHeight();
    }

    public /* bridge */ /* synthetic */ int getSquishedQqsHeight() {
        return super.getSquishedQqsHeight();
    }

    public /* bridge */ /* synthetic */ int getSquishedQsHeight() {
        return super.getSquishedQsHeight();
    }

    @Override // android.view.View
    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        updateResources$8();
        return super.onApplyWindowInsets(windowInsets);
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        StringBuilder sb = new StringBuilder("onConfigurationChanged getResources() orientation = ");
        sb.append(getResources().getConfiguration().orientation);
        sb.append(", configuration = ");
        EdgeBackGestureHandler$.ExternalSyntheticOutline0.m(configuration.orientation, LogUtil.TAG_QS, "OplusQSContainerImpl", sb);
        this.mInflateConfiguration.setTo(configuration);
        this.mIsMediaModeChangeInDiffConfig = false;
        OplusQSContainerImplController oplusQSContainerImplController = this.mController;
        if (oplusQSContainerImplController != null) {
            oplusQSContainerImplController.getClass();
            TemporarilyInfoManager.Companion companion = TemporarilyInfoManager.Companion;
            if (Intrinsics.areEqual(companion.getInstance().getQsRootView(), ((ViewController) oplusQSContainerImplController).mView)) {
                companion.getInstance().clearViewPool();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ((Handler) Dependency.sDependency.getDependencyInner(Dependency.MAIN_HANDLER)).removeCallbacks(this.mDispatchConfigRunnable);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mQSPanelContainer = (OplusQSPanelContainer) findViewById(2131363092);
        this.mQSPanel = findViewById(2131364821);
        this.mQSCustomizer = (OplusQSCustomizer) findViewById(2131364724);
        View viewFindViewById = findViewById(2131364819);
        this.mBackground = viewFindViewById;
        viewFindViewById.setBackgroundColor(0);
        setImportantForAccessibility(2);
        updateResources$8();
        this.mInflateConfiguration.setTo(getResources().getConfiguration());
        LogUtil.d(LogUtil.TAG_QS, "OplusQSContainerImpl", "onFinishInflate end -- orientation = " + getResources().getConfiguration().orientation + ", dark mode = " + OplusDarkModeUtil.isNightMode(((FrameLayout) this).mContext));
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        OplusQSContainerImpl oplusQSContainerImpl;
        Trace.traceBegin(4096L, "OplusQSContainerImpl#onMeasure");
        int size = View.MeasureSpec.getSize(i2);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i), 1073741824);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(size, 1073741824);
        int childCount = getChildCount();
        int i3 = 0;
        while (i3 < childCount) {
            View childAt = this.getChildAt(i3);
            if (childAt.getVisibility() == 8 || childAt == this.mQSPanelContainer) {
                oplusQSContainerImpl = this;
            } else {
                oplusQSContainerImpl = this;
                oplusQSContainerImpl.measureChildWithMargins(childAt, iMakeMeasureSpec, 0, iMakeMeasureSpec2, 0);
            }
            i3++;
            iMakeMeasureSpec2 = iMakeMeasureSpec2;
            this = oplusQSContainerImpl;
        }
        OplusQSContainerImpl oplusQSContainerImpl2 = this;
        int i4 = iMakeMeasureSpec2;
        OplusQSContainerImplController oplusQSContainerImplController = oplusQSContainerImpl2.mController;
        oplusQSContainerImpl2.measureChildWithMargins(oplusQSContainerImpl2.mQSPanelContainer, iMakeMeasureSpec, 0, View.MeasureSpec.makeMeasureSpec(Math.max(size - Math.max(1, oplusQSContainerImplController != null ? oplusQSContainerImplController.calcExpandMeasureHeightExceptQsPanel() : 0), oplusQSContainerImpl2.mQSPanel.getMinimumHeight()), Integer.MIN_VALUE), 0);
        oplusQSContainerImpl2.setMeasuredDimension(i, i2);
        oplusQSContainerImpl2.mQSCustomizer.measure(i, i4);
        Trace.traceEnd(4096L);
    }

    @Override // android.view.View
    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        ViewRootImpl viewRootImpl = getViewRootImpl();
        if (i == 0 && viewRootImpl != null && viewRootImpl.getView() == view) {
            Handler handler = (Handler) Dependency.sDependency.getDependencyInner(Dependency.MAIN_HANDLER);
            if (handler.hasCallbacks(this.mDispatchConfigRunnable)) {
                return;
            }
            handler.post(this.mDispatchConfigRunnable);
        }
    }

    @Override // android.view.View
    public final boolean performClick() {
        return true;
    }

    public void setController(OplusQSContainerImplController oplusQSContainerImplController) {
        this.mController = oplusQSContainerImplController;
    }

    public /* bridge */ /* synthetic */ void setExpansion(float f) {
        super.setExpansion(f);
    }

    public void setHeightOverride(int i) {
    }

    public void setKeyguardShow(boolean z) {
    }

    public void setQsMediaPanelShown(boolean z) {
        this.mIsMediaModeChangeInDiffConfig = this.mInflateConfiguration.orientation != getResources().getConfiguration().orientation;
        updateResources$8();
    }

    public final void updateResources$8() {
        Resources resources = ((FrameLayout) this).mContext.getResources();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mBackground.getLayoutParams();
        int dimensionPixelSize = resources.getDimensionPixelSize(2131171527);
        layoutParams.setMarginStart(dimensionPixelSize);
        layoutParams.setMarginEnd(dimensionPixelSize);
        this.mBackground.setLayoutParams(layoutParams);
    }
}
