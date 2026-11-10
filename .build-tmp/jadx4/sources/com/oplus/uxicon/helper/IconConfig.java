package com.oplus.uxicon.helper;

import androidx.appcompat.app.AppCompatDelegateImpl$;
import java.io.Serializable;
import java.util.Arrays;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class IconConfig implements Serializable {
    private int artPlusOn;
    private int darkModeIcon;
    private int foregroundSize;
    private boolean foreign;
    private int iconRadius;
    private int iconShape;
    private int iconSize;
    private boolean mIconThemeEnable;
    private boolean mIsLocalSpecial;
    private boolean mMonoFollowWallpaper;
    private boolean mSingleColorInternalChange;
    private int theme;

    public int getArtPlusOn() {
        return this.artPlusOn;
    }

    public int getDarkModeIcon() {
        return this.darkModeIcon;
    }

    public int getForegroundSize() {
        return this.foregroundSize;
    }

    public boolean getForeign() {
        return this.foreign;
    }

    public int getIconRadius() {
        return this.iconRadius;
    }

    public int getIconShape() {
        return this.iconShape;
    }

    public int getIconSize() {
        return this.iconSize;
    }

    public int getRadiusType() {
        if (Arrays.asList(2, 3, 6, 7, 8).contains(Integer.valueOf(this.theme)) && this.iconShape == 0) {
            int i = this.iconRadius;
            if ((i & 3840) == 256) {
                return 1;
            }
            if ((i & 255) >= 75) {
                return 2;
            }
        }
        return 0;
    }

    public int getTheme() {
        return this.theme;
    }

    public final boolean isMonoFollowWallpaper() {
        return this.mMonoFollowWallpaper;
    }

    public void setArtPlusOn(int i) {
        this.artPlusOn = i;
    }

    public void setDarkModeIcon(int i) {
        this.darkModeIcon = i;
    }

    public void setForegroundSize(int i) {
        this.foregroundSize = i;
    }

    public void setForeign(boolean z) {
        this.foreign = z;
    }

    public void setIconRadius(int i) {
        this.iconRadius = i;
    }

    public void setIconShape(int i) {
        this.iconShape = i;
    }

    public void setIconSize(int i) {
        this.iconSize = i;
    }

    public void setIconThemeEnable(boolean z) {
        this.mIconThemeEnable = z;
    }

    public void setLocalSpecial(boolean z) {
        this.mIsLocalSpecial = z;
    }

    public void setMIsLocalSpecial(boolean z) {
        this.mIsLocalSpecial = z;
    }

    public void setMonoFollowWallpaper(boolean z) {
        this.mMonoFollowWallpaper = z;
    }

    public void setPropertyTo(IconConfig iconConfig) {
        if (iconConfig == null) {
            return;
        }
        if (this.theme != iconConfig.theme) {
            setTheme(iconConfig.getTheme());
        }
        if (this.artPlusOn != iconConfig.artPlusOn) {
            setArtPlusOn(iconConfig.getArtPlusOn());
        }
        if (this.iconShape != iconConfig.iconShape) {
            setIconShape(iconConfig.getIconShape());
        }
        if (this.iconRadius != iconConfig.iconRadius) {
            setIconRadius(iconConfig.getIconRadius());
        }
        if (this.foregroundSize != iconConfig.foregroundSize) {
            setForegroundSize(iconConfig.getForegroundSize());
        }
        if (this.iconSize != iconConfig.iconSize) {
            setIconSize(iconConfig.getIconSize());
        }
        if (this.darkModeIcon != iconConfig.darkModeIcon) {
            setDarkModeIcon(iconConfig.getDarkModeIcon());
        }
        boolean z = this.mIsLocalSpecial;
        boolean z2 = iconConfig.mIsLocalSpecial;
        if (z != z2) {
            setLocalSpecial(z2);
        }
        boolean z3 = this.mIconThemeEnable;
        boolean z4 = iconConfig.mIconThemeEnable;
        if (z3 != z4) {
            setIconThemeEnable(z4);
        }
        boolean z5 = this.mMonoFollowWallpaper;
        boolean z6 = iconConfig.mMonoFollowWallpaper;
        if (z5 != z6) {
            setMonoFollowWallpaper(z6);
        }
    }

    public void setSingleColorInternalChange(boolean z) {
        this.mSingleColorInternalChange = z;
    }

    public void setTheme(int i) {
        this.theme = i;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("IconConfig = [ isForeign : ");
        sb.append(this.foreign);
        sb.append(",theme : ");
        sb.append(this.theme);
        sb.append(",iconSize : ");
        sb.append(this.iconSize);
        sb.append(",iconShape : ");
        sb.append(this.iconShape);
        sb.append(",foregroundSize : ");
        sb.append(this.foregroundSize);
        sb.append(",artPlusOn : ");
        sb.append(this.artPlusOn);
        sb.append(",iconRadius ：");
        sb.append(this.iconRadius);
        sb.append(",darkModeIcon:");
        sb.append(this.darkModeIcon);
        sb.append(",localSpecial:");
        sb.append(this.mIsLocalSpecial);
        sb.append(",iconThemeEnable:");
        sb.append(this.mIconThemeEnable);
        sb.append(",mMonoFollowWallpaper:");
        return AppCompatDelegateImpl$.ExternalSyntheticOutline0.m(" ]", sb, this.mMonoFollowWallpaper);
    }
}
