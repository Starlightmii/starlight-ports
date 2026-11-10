package com.oplus.systemui.notification.lockscreen.capsule;

import android.view.View;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface CapsuleListContainer {
    void addContainerView(View view);

    void addContainerViewAt(View view, int i);

    void changeViewPosition(View view, int i);

    View getContainerChildAt(int i);

    int getContainerChildCount();

    void reTopContainerView(View view, boolean z);

    void removeContainerView(View view);
}
