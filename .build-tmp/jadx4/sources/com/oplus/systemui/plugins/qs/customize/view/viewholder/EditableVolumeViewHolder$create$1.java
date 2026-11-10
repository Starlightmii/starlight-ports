package com.oplus.systemui.plugins.qs.customize.view.viewholder;

import android.content.Context;
import com.oplus.systemui.plugins.qs.OplusQSSpecialModeProvider;
import com.oplus.systemui.qs.base.util.QsUserContextProvider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EditableVolumeViewHolder$create$1 implements QsUserContextProvider {
    public /* synthetic */ EditableVolumeViewHolder this$0;

    @Override // com.oplus.systemui.qs.base.util.QsUserContextProvider
    public Context getCurrentUserContext() {
        Context userContext = OplusQSSpecialModeProvider.Companion.getInstance().getUserContext();
        if (userContext != null) {
            return userContext;
        }
        EditableVolumeViewHolder editableVolumeViewHolder = this.this$0;
        EditableVolumeViewHolder.Companion companion = EditableVolumeViewHolder.Companion;
        return editableVolumeViewHolder.getContext();
    }
}
