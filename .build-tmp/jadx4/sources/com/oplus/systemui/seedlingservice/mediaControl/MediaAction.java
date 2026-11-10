package com.oplus.systemui.seedlingservice.mediaControl;

import android.graphics.drawable.Icon;
import android.net.Uri;
import androidx.compose.animation.ChangeSize$;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import com.android.compose.gesture.NestedDraggableNode$detectDrags$2$;
import java.util.HashMap;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt___StringsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MediaAction {
    public static final String ACTION_NAME_MEDIA_DEVICE = "mediaDevice";
    public static final String CUSTOM_ACTION_PREFIX = "customAction_";
    public static final Companion Companion = new Companion(null);
    public static final String ICON_DEFAULT = "Default";
    public static final String ICON_SECOND_HOME = "SecondHome";
    public static final String OPLUS_ACTION_PREFIX = "oplusAction_";
    public static final String STANDARD_ACTION_PREFIX = "standardAction_";
    private MediaActionRunnable action;
    private String animRes;
    private final String buttonName;
    private final Icon icon;
    private final HashMap<String, Pair<String, Uri>> iconMap;
    private boolean isStandardAction;
    private String voiceLabel;

    public /* synthetic */ MediaAction(String str, HashMap map, String str2, String str3, boolean z, Icon icon, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, map, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? "" : str3, (i & 16) != 0 ? false : z, (i & 32) != 0 ? null : icon);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MediaAction copy$default(MediaAction mediaAction, String str, HashMap map, String str2, String str3, boolean z, Icon icon, int i, Object obj) {
        if ((i & 1) != 0) {
            str = mediaAction.buttonName;
        }
        if ((i & 2) != 0) {
            map = mediaAction.iconMap;
        }
        if ((i & 4) != 0) {
            str2 = mediaAction.animRes;
        }
        if ((i & 8) != 0) {
            str3 = mediaAction.voiceLabel;
        }
        if ((i & 16) != 0) {
            z = mediaAction.isStandardAction;
        }
        if ((i & 32) != 0) {
            icon = mediaAction.icon;
        }
        boolean z2 = z;
        Icon icon2 = icon;
        return mediaAction.copy(str, map, str2, str3, z2, icon2);
    }

    public final String component1() {
        return this.buttonName;
    }

    public final HashMap<String, Pair<String, Uri>> component2() {
        return this.iconMap;
    }

    public final String component3() {
        return this.animRes;
    }

    public final String component4() {
        return this.voiceLabel;
    }

    public final boolean component5() {
        return this.isStandardAction;
    }

    public final Icon component6() {
        return this.icon;
    }

    public final MediaAction copy(String str, HashMap<String, Pair<String, Uri>> map, String str2, String str3, boolean z, Icon icon) {
        return new MediaAction(str, map, str2, str3, z, icon);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaAction)) {
            return false;
        }
        MediaAction mediaAction = (MediaAction) obj;
        return Intrinsics.areEqual(this.buttonName, mediaAction.buttonName) && Intrinsics.areEqual(this.iconMap, mediaAction.iconMap) && Intrinsics.areEqual(this.animRes, mediaAction.animRes) && Intrinsics.areEqual(this.voiceLabel, mediaAction.voiceLabel) && this.isStandardAction == mediaAction.isStandardAction && Intrinsics.areEqual(this.icon, mediaAction.icon);
    }

    public final MediaActionRunnable getAction() {
        return this.action;
    }

    public final String getAnimRes() {
        return this.animRes;
    }

    public final String getButtonName() {
        return this.buttonName;
    }

    public final Object getIcon(String str) {
        String str2;
        String str3;
        Pair<String, Uri> pair = this.iconMap.get(str);
        if (pair != null && (str3 = (String) pair.getFirst()) != null) {
            return str3;
        }
        Pair<String, Uri> pair2 = this.iconMap.get(str);
        Uri uri = pair2 != null ? (Uri) pair2.getSecond() : null;
        if (uri != null) {
            return uri;
        }
        if (!Intrinsics.areEqual(str, ICON_DEFAULT)) {
            Pair<String, Uri> pair3 = this.iconMap.get(ICON_DEFAULT);
            if (pair3 != null && (str2 = (String) pair3.getFirst()) != null) {
                return str2;
            }
            Pair<String, Uri> pair4 = this.iconMap.get(ICON_DEFAULT);
            if (pair4 != null) {
                return (Uri) pair4.getSecond();
            }
        }
        return null;
    }

    public final HashMap<String, Pair<String, Uri>> getIconMap() {
        return this.iconMap;
    }

    public final String getVoiceLabel() {
        return this.voiceLabel;
    }

    public int hashCode() {
        int iHashCode = (this.iconMap.hashCode() + (this.buttonName.hashCode() * 31)) * 31;
        String str = this.animRes;
        int iM = ChangeSize$.ExternalSyntheticOutline0.m(PropertyValuesHolder2D$.ExternalSyntheticOutline0.m((iHashCode + (str == null ? 0 : str.hashCode())) * 31, 31, this.voiceLabel), 31, this.isStandardAction);
        Icon icon = this.icon;
        return iM + (icon != null ? icon.hashCode() : 0);
    }

    public final boolean isStandardAction() {
        return this.isStandardAction;
    }

    public final void setAction(MediaActionRunnable mediaActionRunnable) {
        this.action = mediaActionRunnable;
    }

    public final void setAnimRes(String str) {
        this.animRes = str;
    }

    public final void setStandardAction(boolean z) {
        this.isStandardAction = z;
    }

    public final void setVoiceLabel(String str) {
        this.voiceLabel = str;
    }

    public final String toSimpleInfo(CharSequence charSequence) {
        if (charSequence == null || charSequence.length() == 0) {
            return "EMPTY";
        }
        return StringsKt___StringsKt.first(charSequence) + "***";
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("MediaAction(");
        NestedDraggableNode$detectDrags$2$.ExternalSyntheticOutline0.m("buttonName=", this.buttonName, ", ", sb);
        sb.append("iconMap=" + this.iconMap + ", ");
        NestedDraggableNode$detectDrags$2$.ExternalSyntheticOutline0.m("animRes=", this.animRes, ", ", sb);
        String str = this.voiceLabel;
        NestedDraggableNode$detectDrags$2$.ExternalSyntheticOutline0.m("voiceLabel=", str != null ? toSimpleInfo(str) : null, ", ", sb);
        sb.append("isStandardAction=" + this.isStandardAction + ", ");
        sb.append("icon=" + this.icon);
        sb.append(")");
        return sb.toString();
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public MediaAction(String str, HashMap<String, Pair<String, Uri>> map, String str2, String str3, boolean z, Icon icon) {
        this.buttonName = str;
        this.iconMap = map;
        this.animRes = str2;
        this.voiceLabel = str3;
        this.isStandardAction = z;
        this.icon = icon;
    }

    public final Icon getIcon() {
        return this.icon;
    }
}
