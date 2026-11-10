package com.oplus.systemui.statusbar.notification.collection;

import android.content.Context;
import com.android.systemui.statusbar.notification.collection.OplusListEntryEx;
import com.android.systemui.statusbar.notification.collection.PipelineEntry;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusListEntryExImpl implements OplusListEntryEx {
    private PipelineEntry base;
    private final Context context;
    public boolean hasBeShowed;

    public OplusListEntryExImpl(Context context) {
        this.context = context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: bind, reason: merged with bridge method [inline-methods] */
    public void bind$1(PipelineEntry pipelineEntry) {
        setBase((Object) pipelineEntry);
    }

    public /* bridge */ /* synthetic */ String getEncryptionKey() {
        return super.getEncryptionKey();
    }

    public boolean getHasBeShowed() {
        return this.hasBeShowed;
    }

    public /* bridge */ /* synthetic */ long getUpdateTime() {
        return super.getUpdateTime();
    }

    public void setHasBeShowed(boolean z) {
        this.hasBeShowed = z;
    }

    /* JADX INFO: renamed from: getBase, reason: merged with bridge method [inline-methods] */
    public PipelineEntry m384getBase() {
        return this.base;
    }

    public void setBase(PipelineEntry pipelineEntry) {
        this.base = pipelineEntry;
    }
}
