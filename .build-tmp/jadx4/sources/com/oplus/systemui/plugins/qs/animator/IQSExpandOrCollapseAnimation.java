package com.oplus.systemui.plugins.qs.animator;

import android.view.View;
import com.oplusos.systemui.common.model.Loc;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IQSExpandOrCollapseAnimation {

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface QSNodeChangeListener {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface QSNodePageSelectedListener {
    }

    default View getAnimatorView(int i) {
        return null;
    }

    default int getSubSize() {
        return 1;
    }

    boolean isNeedAddResponse();

    void resetNonCurrPageToExpanded();

    default void saveLatestNoEditModeLocList(List<Loc> list) {
    }

    void setCusTranslationY(float f, int i, int i2);

    default void setNodeNumChange(QSNodeChangeListener qSNodeChangeListener) {
    }

    default void setPageSelectedListener(QSNodePageSelectedListener qSNodePageSelectedListener) {
    }

    void updatePivotXY(int i, int i2, boolean z);

    void updateState(int i, int i2, float f, float f2, float f3);
}
