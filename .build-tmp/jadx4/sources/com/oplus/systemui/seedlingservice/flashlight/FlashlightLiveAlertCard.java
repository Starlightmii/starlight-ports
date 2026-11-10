package com.oplus.systemui.seedlingservice.flashlight;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FlashlightLiveAlertCard {
    public static final String COLOR_00000000 = "colorInt/00000000";
    public static final String COLOR_26FFFFFF = "colorInt/26FFFFFF";
    public static final String COLOR_8AFFFFFF = "colorInt/8AFFFFFF";
    public static final String COLOR_E6FFFFFF = "colorInt/E6FFFFFF";
    public static final String COLOR_FFE7D38C = "colorInt/FFE7D38C";
    public static final String COLOR_FFFFFFFF = "colorInt/FFFFFFFF";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String ICON_LABEL = "ICON_LABEL";
    public static final String SHAPE_CIRCLE = "CIRCLE";
    public static final String SHAPE_RECTANGLE = "RECTANGLE";
    public static final String SIZE_REGULAR = "REGULAR";
    public static final String SIZE_SMALL = "SMALL";
    public static final String STATE_OFF = "OFF";
    public static final String STATE_ON = "ON";
    private final SymActionLayout criticalData;
    private final StandardLayout primaryData;
    private final ActionsLayout subData;

    public FlashlightLiveAlertCard(StandardLayout standardLayout, ActionsLayout actionsLayout, SymActionLayout symActionLayout) {
        this.primaryData = standardLayout;
        this.subData = actionsLayout;
        this.criticalData = symActionLayout;
    }

    public static /* synthetic */ FlashlightLiveAlertCard copy$default(FlashlightLiveAlertCard flashlightLiveAlertCard, StandardLayout standardLayout, ActionsLayout actionsLayout, SymActionLayout symActionLayout, int i, Object obj) {
        if ((i & 1) != 0) {
            standardLayout = flashlightLiveAlertCard.primaryData;
        }
        if ((i & 2) != 0) {
            actionsLayout = flashlightLiveAlertCard.subData;
        }
        if ((i & 4) != 0) {
            symActionLayout = flashlightLiveAlertCard.criticalData;
        }
        return flashlightLiveAlertCard.copy(standardLayout, actionsLayout, symActionLayout);
    }

    public final StandardLayout component1() {
        return this.primaryData;
    }

    public final ActionsLayout component2() {
        return this.subData;
    }

    public final SymActionLayout component3() {
        return this.criticalData;
    }

    public final FlashlightLiveAlertCard copy(StandardLayout standardLayout, ActionsLayout actionsLayout, SymActionLayout symActionLayout) {
        return new FlashlightLiveAlertCard(standardLayout, actionsLayout, symActionLayout);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FlashlightLiveAlertCard)) {
            return false;
        }
        FlashlightLiveAlertCard flashlightLiveAlertCard = (FlashlightLiveAlertCard) obj;
        return Intrinsics.areEqual(this.primaryData, flashlightLiveAlertCard.primaryData) && Intrinsics.areEqual(this.subData, flashlightLiveAlertCard.subData) && Intrinsics.areEqual(this.criticalData, flashlightLiveAlertCard.criticalData);
    }

    public final SymActionLayout getCriticalData() {
        return this.criticalData;
    }

    public final StandardLayout getPrimaryData() {
        return this.primaryData;
    }

    public final ActionsLayout getSubData() {
        return this.subData;
    }

    public int hashCode() {
        return this.criticalData.hashCode() + ((this.subData.hashCode() + (this.primaryData.hashCode() * 31)) * 31);
    }

    public String toString() {
        return "FlashlightLiveAlertCard(primaryData=" + this.primaryData + ", subData=" + this.subData + ", criticalData=" + this.criticalData + ")";
    }
}
