package com.oplus.systemui.statusbar.icon.data;

import android.util.Log;
import com.oplusos.systemui.common.observer.ChildrenModeObserver;
import com.oplusos.systemui.common.observer.FocusModeSettingsObserver;
import com.oplusos.systemui.common.observer.IObserverListener;
import com.oplusos.systemui.common.util.LogUtil;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusNotificationIconAreaRepository$childModeListener$1 implements IObserverListener {
    public final /* synthetic */ int $r8$classId;
    public /* synthetic */ OplusNotificationIconAreaRepository this$0;

    public /* synthetic */ OplusNotificationIconAreaRepository$childModeListener$1(int i) {
        this.$r8$classId = i;
    }

    @Override // com.oplusos.systemui.common.observer.IObserverListener
    public final void onChange(boolean z) {
        switch (this.$r8$classId) {
            case 0:
                OplusNotificationIconAreaRepository oplusNotificationIconAreaRepository = this.this$0;
                oplusNotificationIconAreaRepository.isChildrenMode.setValue(Boolean.valueOf(ChildrenModeObserver.getInstance().mIsChildrenMode));
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    Log.d("SystemUi--Statusbar", "OplusNotificationIconAreaRepository-->".concat("updateStatusBarIconByChildrenMode :" + oplusNotificationIconAreaRepository.isChildrenMode.getValue() + ", selfChange: " + z));
                    break;
                }
                break;
            default:
                this.this$0.isFocusMode.setValue(Boolean.valueOf(FocusModeSettingsObserver.getsInstance().mIsFocusMode));
                OplusNotificationIconAreaRepository oplusNotificationIconAreaRepository2 = this.this$0;
                if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
                    Log.d("SystemUi--Statusbar", "OplusNotificationIconAreaRepository-->".concat("mIsFocusMode = " + oplusNotificationIconAreaRepository2.isFocusMode));
                    break;
                }
                break;
        }
    }
}
