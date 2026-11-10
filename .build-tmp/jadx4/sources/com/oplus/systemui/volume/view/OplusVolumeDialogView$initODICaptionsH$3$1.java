package com.oplus.systemui.volume.view;

import android.util.Log;
import android.view.View;
import com.android.systemui.volume.OplusVolumeControllerEx;
import com.oplus.systemui.volume.OplusVolumeDialogImpl;
import com.oplus.systemui.volume.VolumeListAdapter;
import com.oplus.systemui.volume.eximpl.OplusVolumeControllerExImpl;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusVolumeDialogView$initODICaptionsH$3$1 implements View.OnClickListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusVolumeDialogView this$0;

    public /* synthetic */ OplusVolumeDialogView$initODICaptionsH$3$1(int i) {
        this.$r8$classId = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Collection collectionEmptyList;
        ArrayList<OplusVolumeRow> panelShowList;
        int i = 0;
        switch (this.$r8$classId) {
            case 0:
                this.this$0.getVolumeInteractor().rescheduleTimeoutH();
                OplusVolumeDialogImpl volumeInteractor = this.this$0.getVolumeInteractor();
                OplusVolumeControllerEx oplusVolumeControllerEx = volumeInteractor.mOplusVolumeController;
                oplusVolumeControllerEx.setCaptionsEnabled(true ^ ((OplusVolumeControllerExImpl) oplusVolumeControllerEx).areCaptionsEnabled());
                volumeInteractor.mOplusVolumeDialogView.updateCaptionsIcon(0);
                break;
            case 1:
                this.this$0.onAppVolumeAdjustClick();
                break;
            default:
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_VOLUME)) {
                    Log.d("SystemUi--Volume", "OplusVolumeDialogView-->systemVolumeClickAfter");
                }
                if (!this.this$0.getVolumeInteractor().mExpanded) {
                    VolumeListAdapter volumeListAdapter = this.this$0.getVolumeListAdapter();
                    if (volumeListAdapter != null) {
                        volumeListAdapter.setPanelType(true);
                    }
                    this.this$0.getVolumeInteractor().expandPanel();
                    VolumeListAdapter volumeListAdapter2 = this.this$0.getVolumeListAdapter();
                    if (volumeListAdapter2 == null || (panelShowList = volumeListAdapter2.getPanelShowList()) == null) {
                        collectionEmptyList = CollectionsKt__CollectionsKt.emptyList();
                    } else {
                        collectionEmptyList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(panelShowList, 10));
                        int size = panelShowList.size();
                        while (i < size) {
                            OplusVolumeRow oplusVolumeRow = panelShowList.get(i);
                            i++;
                            collectionEmptyList.add(Integer.valueOf(oplusVolumeRow.getStream()));
                        }
                    }
                    Collection collection = collectionEmptyList;
                    if (!collection.isEmpty()) {
                        this.this$0.getVolumeInteractor().mOplusVolumeController.updateAudibleStreamVolume(collection);
                    }
                    OplusVolumeSideAccessoryButton oplusVolumeSideAccessoryButton = this.this$0.mMoreRowStreamLl;
                    if (oplusVolumeSideAccessoryButton != null) {
                        OplusVolumeDialogView oplusVolumeDialogView = this.this$0;
                        OplusVolumeDialogView.flushRowAndIconParent.3 r0 = new OplusVolumeDialogView.flushRowAndIconParent.3(4);
                        r0.this$0 = oplusVolumeDialogView;
                        VarHandle.storeStoreFence();
                        oplusVolumeSideAccessoryButton.postDelayed(r0, 400L);
                    }
                    break;
                }
                break;
        }
    }
}
