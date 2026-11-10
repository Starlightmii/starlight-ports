package com.oplus.systemui.statusbar.seeding.anim;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public interface IStatusBarViewProvider {
    default List<View> endAreaIcons() {
        ViewGroup endAreaContent = getEndAreaContent();
        endAreaIcons.1 r0 = endAreaIcons.1.INSTANCE;
        ArrayList arrayList = new ArrayList();
        StatusBarViewProviderKt.dfsTraverse(endAreaContent, r0, arrayList);
        return arrayList;
    }

    ViewGroup getEndAreaContent();

    ViewGroup getStartAreaContent();

    ViewGroup getStatusBarContent();

    default List<View> startAreaIcons() {
        ViewGroup startAreaContent = getStartAreaContent();
        startAreaIcons.1 r0 = startAreaIcons.1.INSTANCE;
        ArrayList arrayList = new ArrayList();
        StatusBarViewProviderKt.dfsTraverse(startAreaContent, r0, arrayList);
        return arrayList;
    }
}
