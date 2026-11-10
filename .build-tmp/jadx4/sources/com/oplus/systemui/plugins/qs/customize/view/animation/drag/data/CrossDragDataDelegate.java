package com.oplus.systemui.plugins.qs.customize.view.animation.drag.data;

import com.oplus.systemui.plugins.qs.customize.viewmodel.IntentTarget;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CrossDragDataDelegate {
    public static final /* synthetic */ KProperty[] $$delegatedProperties;
    public static final Companion Companion;
    private static final String TAG = "CrossDragDataDelegate";
    private final ReadWriteProperty dragData$delegate = new CrossDragDataDelegate$special$$inlined$observable$1(new CrossRegionDragData(false, IntentTarget.NON, null, null, null, null, null));

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    static {
        MutablePropertyReference1Impl mutablePropertyReference1Impl = new MutablePropertyReference1Impl(CrossDragDataDelegate.class, "dragData", "getDragData()Lcom/oplus/systemui/plugins/qs/customize/view/animation/drag/data/CrossRegionDragData;", 0);
        Reflection.factory.getClass();
        $$delegatedProperties = new KProperty[]{mutablePropertyReference1Impl};
        Companion = new Companion();
    }

    private final CrossRegionDragData getDragData() {
        return (CrossRegionDragData) this.dragData$delegate.getValue(this, $$delegatedProperties[0]);
    }

    private final void setDragData(CrossRegionDragData crossRegionDragData) {
        this.dragData$delegate.setValue(this, $$delegatedProperties[0], crossRegionDragData);
    }

    public final CrossRegionDragData getCrossDragData() {
        return getDragData();
    }

    public final void updateDragData(Function1 function1) {
        CrossRegionDragData dragData = getDragData();
        CrossRegionDragData crossRegionDragDataCopy = dragData.copy(dragData.isRegionFirstCrossed, dragData.currentDragIntent, dragData.originLoc, dragData.locInPersonalArea, dragData.swapDataInPersonalArea, dragData.insertSpecInOtherArea, dragData.copyViewProxy);
        function1.invoke(crossRegionDragDataCopy);
        setDragData(crossRegionDragDataCopy);
    }
}
