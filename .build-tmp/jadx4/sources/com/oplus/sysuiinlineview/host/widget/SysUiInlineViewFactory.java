package com.oplus.sysuiinlineview.host.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.oplus.sysuiinlineview.host.view.SysUiInlineHostView;
import com.oplus.sysuiinlineview.host.view.SysUiInlineSurfaceView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface SysUiInlineViewFactory {

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Default implements SysUiInlineViewFactory {
        public static final Default INSTANCE = new Default();

        @Override // com.oplus.sysuiinlineview.host.widget.SysUiInlineViewFactory
        public SysUiInlineHostView makeHostView(Context context, ViewGroup viewGroup, boolean z) {
            View viewInflate = LayoutInflater.from(context).inflate(2131559944, viewGroup, z);
            if (viewInflate instanceof SysUiInlineHostView) {
                return (SysUiInlineHostView) viewInflate;
            }
            return null;
        }

        @Override // com.oplus.sysuiinlineview.host.widget.SysUiInlineViewFactory
        public View makePreviewView(Context context, ViewGroup viewGroup) {
            return LayoutInflater.from(context).inflate(2131559943, viewGroup, false);
        }

        @Override // com.oplus.sysuiinlineview.host.widget.SysUiInlineViewFactory
        public SysUiInlineSurfaceView makeSurfaceView(Context context, ViewGroup viewGroup) {
            View viewInflate = LayoutInflater.from(context).inflate(2131559945, viewGroup, false);
            if (viewInflate instanceof SysUiInlineSurfaceView) {
                return (SysUiInlineSurfaceView) viewInflate;
            }
            return null;
        }
    }

    SysUiInlineHostView makeHostView(Context context, ViewGroup viewGroup, boolean z);

    View makePreviewView(Context context, ViewGroup viewGroup);

    SysUiInlineSurfaceView makeSurfaceView(Context context, ViewGroup viewGroup);
}
