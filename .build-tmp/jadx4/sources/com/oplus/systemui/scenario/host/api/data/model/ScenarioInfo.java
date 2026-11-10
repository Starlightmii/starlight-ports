package com.oplus.systemui.scenario.host.api.data.model;

import android.graphics.drawable.Drawable;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$;
import androidx.constraintlayout.core.widgets.ConstraintWidget$;
import androidx.exifinterface.media.ExifInterface$;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class ScenarioInfo {
    private final String scenarioAnim;
    private final Drawable scenarioAnimBg;
    public final int scenarioIconRes;
    private final String scenarioName;
    private final String scenarioNameAndStatic;
    private final String scenarioStatic;
    private final List<ScenarioTileInfo> scenarioTiles;
    private final String scenarioType;

    public ScenarioInfo(String str, String str2, String str3, String str4, Drawable drawable, String str5, List<ScenarioTileInfo> list, int i) {
        this.scenarioName = str;
        this.scenarioType = str2;
        this.scenarioNameAndStatic = str3;
        this.scenarioStatic = str4;
        this.scenarioAnimBg = drawable;
        this.scenarioAnim = str5;
        this.scenarioTiles = list;
        this.scenarioIconRes = i;
    }

    public final String component1() {
        return this.scenarioName;
    }

    public final String component2() {
        return this.scenarioType;
    }

    public final String component3() {
        return this.scenarioNameAndStatic;
    }

    public final String component4() {
        return this.scenarioStatic;
    }

    public final Drawable component5() {
        return this.scenarioAnimBg;
    }

    public final String component6() {
        return this.scenarioAnim;
    }

    public final List<ScenarioTileInfo> component7() {
        return this.scenarioTiles;
    }

    public final ScenarioInfo copy(String str, String str2, String str3, String str4, Drawable drawable, String str5, List<ScenarioTileInfo> list, int i) {
        return new ScenarioInfo(str, str2, str3, str4, drawable, str5, list, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ScenarioInfo)) {
            return false;
        }
        ScenarioInfo scenarioInfo = (ScenarioInfo) obj;
        return Intrinsics.areEqual(this.scenarioName, scenarioInfo.scenarioName) && Intrinsics.areEqual(this.scenarioType, scenarioInfo.scenarioType) && Intrinsics.areEqual(this.scenarioNameAndStatic, scenarioInfo.scenarioNameAndStatic) && Intrinsics.areEqual(this.scenarioStatic, scenarioInfo.scenarioStatic) && Intrinsics.areEqual(this.scenarioAnimBg, scenarioInfo.scenarioAnimBg) && Intrinsics.areEqual(this.scenarioAnim, scenarioInfo.scenarioAnim) && Intrinsics.areEqual(this.scenarioTiles, scenarioInfo.scenarioTiles) && this.scenarioIconRes == scenarioInfo.scenarioIconRes;
    }

    public final String getScenarioAnim() {
        return this.scenarioAnim;
    }

    public final Drawable getScenarioAnimBg() {
        return this.scenarioAnimBg;
    }

    public final int getScenarioIconRes() {
        return this.scenarioIconRes;
    }

    public final String getScenarioName() {
        return this.scenarioName;
    }

    public final String getScenarioNameAndStatic() {
        return this.scenarioNameAndStatic;
    }

    public final String getScenarioStatic() {
        return this.scenarioStatic;
    }

    public final List<ScenarioTileInfo> getScenarioTiles() {
        return this.scenarioTiles;
    }

    public final String getScenarioType() {
        return this.scenarioType;
    }

    public final int hashCode() {
        int iM = PropertyValuesHolder2D$.ExternalSyntheticOutline0.m(this.scenarioName.hashCode() * 31, 31, this.scenarioType);
        String str = this.scenarioNameAndStatic;
        int iHashCode = (iM + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.scenarioStatic;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Drawable drawable = this.scenarioAnimBg;
        int iHashCode3 = (iHashCode2 + (drawable == null ? 0 : drawable.hashCode())) * 31;
        String str3 = this.scenarioAnim;
        return Integer.hashCode(this.scenarioIconRes) + PropertyValuesHolder2D$.ExternalSyntheticOutline0.m((iHashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31, 31, this.scenarioTiles);
    }

    public String toString() {
        String str = this.scenarioName;
        String str2 = this.scenarioType;
        String str3 = this.scenarioNameAndStatic;
        String str4 = this.scenarioStatic;
        Drawable drawable = this.scenarioAnimBg;
        String str5 = this.scenarioAnim;
        List<ScenarioTileInfo> list = this.scenarioTiles;
        StringBuilder sbM = ExifInterface$.ExternalSyntheticOutline1.m("ScenarioInfo(scenarioName=", str, ", scenarioType=", str2, ", scenarioNameAndStatic=");
        ConstraintWidget$.ExternalSyntheticOutline0.m(sbM, str3, ", scenarioStatic=", str4, ", scenarioAnimBg=");
        sbM.append(drawable);
        sbM.append(", scenarioAnim=");
        sbM.append(str5);
        sbM.append(", scenarioTiles=");
        sbM.append(list);
        sbM.append(", scenarioIconRes=");
        sbM.append(this.scenarioIconRes);
        sbM.append(")");
        return sbM.toString();
    }
}
