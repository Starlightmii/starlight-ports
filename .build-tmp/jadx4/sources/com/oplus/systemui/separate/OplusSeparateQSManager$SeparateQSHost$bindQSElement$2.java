package com.oplus.systemui.separate;

import android.content.Context;
import android.view.View;
import com.android.systemui.plugins.qs.QSElement;
import com.android.systemui.plugins.qs.QSSpecialModeUpdateMonitor;
import com.android.systemui.plugins.qs.SpanSizeInPlugin;
import com.android.systemui.util.ViewController;
import com.oplus.systemui.qs.media.OplusQsBaseMediaPanelView;
import com.oplus.systemui.qs.media.OplusQsMediaPanelViewController;
import java.util.Map;
import kotlin.Lazy;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusSeparateQSManager$SeparateQSHost$bindQSElement$2 implements QSElement {
    public /* synthetic */ Context $context;
    public OplusQsMediaPanelViewController curOplusQsMediaPanelViewController;
    public Lazy qsMediaPanelViewControllerCache$delegate;
    public /* synthetic */ OplusSeparateQSManager this$0;

    private final Map<SpanSizeInPlugin, OplusQsMediaPanelViewController> getQsMediaPanelViewControllerCache() {
        return (Map) this.qsMediaPanelViewControllerCache$delegate.getValue();
    }

    public final View createView(SpanSizeInPlugin spanSizeInPlugin, boolean z, boolean z2) {
        OplusQsMediaPanelViewController oplusQsMediaPanelViewControllerCreateQSMediaPanelView = getQsMediaPanelViewControllerCache().get(spanSizeInPlugin);
        if (oplusQsMediaPanelViewControllerCreateQSMediaPanelView == null) {
            oplusQsMediaPanelViewControllerCreateQSMediaPanelView = ((OplusSeparateQSManager.QSMediaFactory) this.this$0.qsMediaFactory.get()).createQSMediaPanelView(this.$context, spanSizeInPlugin);
            oplusQsMediaPanelViewControllerCreateQSMediaPanelView.init();
            getQsMediaPanelViewControllerCache().put(spanSizeInPlugin, oplusQsMediaPanelViewControllerCreateQSMediaPanelView);
        }
        if (z2) {
            this.curOplusQsMediaPanelViewController = oplusQsMediaPanelViewControllerCreateQSMediaPanelView;
            ((OplusQsBaseMediaPanelView) ((ViewController) oplusQsMediaPanelViewControllerCreateQSMediaPanelView).mView).updateColor();
        }
        if (z) {
            OplusQsMediaPanelViewController oplusQsMediaPanelViewController = this.curOplusQsMediaPanelViewController;
            oplusQsMediaPanelViewControllerCreateQSMediaPanelView.setMediaPanelData(oplusQsMediaPanelViewController != null ? oplusQsMediaPanelViewController.getMediaData() : null);
            ((OplusQsBaseMediaPanelView) ((ViewController) oplusQsMediaPanelViewControllerCreateQSMediaPanelView).mView).updateColor();
        }
        return oplusQsMediaPanelViewControllerCreateQSMediaPanelView.getView();
    }

    public final void onThemeApplied(boolean z, SpanSizeInPlugin spanSizeInPlugin) {
        OplusQsMediaPanelViewController oplusQsMediaPanelViewController = getQsMediaPanelViewControllerCache().get(spanSizeInPlugin);
        if (oplusQsMediaPanelViewController != null) {
            ((OplusQsBaseMediaPanelView) ((ViewController) oplusQsMediaPanelViewController).mView).updateBgVisibility();
        }
    }

    public final void recycle(SpanSizeInPlugin spanSizeInPlugin, View view) {
        getQsMediaPanelViewControllerCache().remove(spanSizeInPlugin);
    }

    public void setIsBeingDragged(boolean z) {
        OplusQsMediaPanelViewController oplusQsMediaPanelViewController = this.curOplusQsMediaPanelViewController;
        if (oplusQsMediaPanelViewController != null) {
            oplusQsMediaPanelViewController.setBeingDragged(z);
        }
    }

    public final void updateColor(QSSpecialModeUpdateMonitor.ColorType colorType, SpanSizeInPlugin spanSizeInPlugin) {
        OplusQsMediaPanelViewController oplusQsMediaPanelViewController;
        if (colorType != QSSpecialModeUpdateMonitor.ColorType.UI_MODE || (oplusQsMediaPanelViewController = getQsMediaPanelViewControllerCache().get(spanSizeInPlugin)) == null) {
            return;
        }
        ((OplusQsBaseMediaPanelView) ((ViewController) oplusQsMediaPanelViewController).mView).updateColor();
    }

    public final void updateDrawable(boolean z, SpanSizeInPlugin spanSizeInPlugin) {
        OplusQsMediaPanelViewController oplusQsMediaPanelViewController = getQsMediaPanelViewControllerCache().get(spanSizeInPlugin);
        if (oplusQsMediaPanelViewController != null) {
            ((OplusQsBaseMediaPanelView) ((ViewController) oplusQsMediaPanelViewController).mView).onDrawableUpdate(z);
        }
    }

    public final void updateOutline(SpanSizeInPlugin spanSizeInPlugin) {
        OplusQsMediaPanelViewController oplusQsMediaPanelViewController = getQsMediaPanelViewControllerCache().get(spanSizeInPlugin);
        if (oplusQsMediaPanelViewController != null) {
            ((OplusQsBaseMediaPanelView) ((ViewController) oplusQsMediaPanelViewController).mView).onOutlineUpdate();
        }
    }
}
