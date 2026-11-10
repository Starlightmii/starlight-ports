package com.oplus.systemui.plugins.qs.customize.view.viewholder;

import android.view.View;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.oplus.systemui.plugins.qs.OplusSeparateQSPluginImpl;
import com.oplus.systemui.plugins.qs.widget.OplusQsToggleSliderLayout;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EditableBrightnessViewHolder$sliderLongClickListener$1 implements View.OnLongClickListener {
    public /* synthetic */ EditableBrightnessViewHolder this$0;

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        OplusQsToggleSliderLayout oplusQsToggleSliderLayout = this.this$0.toggleSliderLayout;
        OplusSeparateQSPlugin.IStub proxy = OplusSeparateQSPluginImpl.Companion.getProxy();
        if (proxy != null) {
            proxy.showBrightnessDialog(this.this$0.getQsBrightness(), oplusQsToggleSliderLayout, true);
        }
        return true;
    }
}
