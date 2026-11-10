package com.oplusos.systemui.common.inline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.oplus.sysuiinlineview.host.view.SysUiInlineHostView;
import com.oplus.sysuiinlineview.host.view.SysUiInlineSurfaceView;
import com.oplus.sysuiinlineview.host.widget.SysUiInlineViewFactory;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SystemUIInlineViewHelper$defaultViewFactory$1 implements SysUiInlineViewFactory {
    @Override // com.oplus.sysuiinlineview.host.widget.SysUiInlineViewFactory
    public final SysUiInlineHostView makeHostView(Context context, ViewGroup viewGroup, boolean z) {
        View viewInflate = LayoutInflater.from(context).inflate(2131559946, viewGroup, z);
        if (viewInflate instanceof SysUiInlineHostView) {
            return (SysUiInlineHostView) viewInflate;
        }
        return null;
    }

    @Override // com.oplus.sysuiinlineview.host.widget.SysUiInlineViewFactory
    public final View makePreviewView(Context context, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(2131559947, viewGroup, false);
    }

    @Override // com.oplus.sysuiinlineview.host.widget.SysUiInlineViewFactory
    public final SysUiInlineSurfaceView makeSurfaceView(Context context, ViewGroup viewGroup) {
        SysUiInlineSurfaceView sysUiInlineSurfaceViewMakeSurfaceView = SysUiInlineViewFactory.Default.INSTANCE.makeSurfaceView(context, viewGroup);
        if (sysUiInlineSurfaceViewMakeSurfaceView != null) {
            sysUiInlineSurfaceViewMakeSurfaceView.setId(2131365536);
        }
        return sysUiInlineSurfaceViewMakeSurfaceView;
    }
}
