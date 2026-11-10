package com.oplus.systemui.qs.base.res.model;

import androidx.car.app.serialization.Bundler$;
import com.oplus.utrace.lib.SdkConfigData;
import java.util.Arrays;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DrawableState {
    private final int[] stateSpec;

    public DrawableState(int[] iArr) {
        this.stateSpec = iArr;
    }

    public final int[] component1() {
        return this.stateSpec;
    }

    public final DrawableState copy(int[] iArr) {
        return new DrawableState(iArr);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DrawableState) {
            return Arrays.equals(this.stateSpec, ((DrawableState) obj).stateSpec);
        }
        return false;
    }

    public final int[] getStateSpec() {
        return this.stateSpec;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.stateSpec);
    }

    public String toString() {
        String string;
        String string2;
        int[] iArr = this.stateSpec;
        int length = iArr.length - 1;
        if (length == -1) {
            string2 = SdkConfigData.EMPTY_JSON_ARRAY;
        } else {
            StringBuilder sb = new StringBuilder("[");
            int i = 0;
            while (true) {
                int i2 = iArr[i];
                TileViewFlag tileViewFlag = TileViewFlag.Active;
                if (i2 == tileViewFlag.getAttr()) {
                    string = tileViewFlag.toString();
                } else {
                    TileViewFlag tileViewFlag2 = TileViewFlag.Inactive;
                    if (i2 == tileViewFlag2.getAttr()) {
                        string = tileViewFlag2.toString();
                    } else {
                        TileViewFlag tileViewFlag3 = TileViewFlag.Inoperable;
                        if (i2 == tileViewFlag3.getAttr()) {
                            string = tileViewFlag3.toString();
                        } else {
                            TileViewFlag tileViewFlag4 = TileViewFlag.Unavailable;
                            if (i2 == tileViewFlag4.getAttr()) {
                                string = tileViewFlag4.toString();
                            } else {
                                TileViewFlag tileViewFlag5 = TileViewFlag.Dragged;
                                if (i2 == tileViewFlag5.getAttr()) {
                                    string = tileViewFlag5.toString();
                                } else {
                                    TileViewFlag tileViewFlag6 = TileViewFlag.Toggleable;
                                    if (i2 == tileViewFlag6.getAttr()) {
                                        string = tileViewFlag6.toString();
                                    } else {
                                        TileViewFlag tileViewFlag7 = TileViewFlag.Deform;
                                        string = i2 == tileViewFlag7.getAttr() ? tileViewFlag7.toString() : String.valueOf(i2);
                                    }
                                }
                            }
                        }
                    }
                }
                sb.append(string);
                if (i == length) {
                    break;
                }
                sb.append(", ");
                i++;
            }
            sb.append(']');
            string2 = sb.toString();
        }
        return Bundler$.ExternalSyntheticOutline0.m("DrawableState(stateSpec=", string2, ")");
    }
}
