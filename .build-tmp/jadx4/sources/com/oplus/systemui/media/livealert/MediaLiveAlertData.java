package com.oplus.systemui.media.livealert;

import androidx.compose.animation.ChangeSize$;
import com.android.keyguard.logging.KeyguardLogger$;
import com.android.settingslib.media.DynamicRouteAttributes$;
import com.oplus.systemui.seedlingservice.mediaControl.SeedlingMediaData;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MediaLiveAlertData {
    public boolean allowCreateCard;
    public boolean isFold;
    public boolean isMediaSeedlingShowOnStatusBar;
    public boolean isMilestone;
    public boolean isOccluded;
    public boolean isPanoramicAod;
    public boolean isScreenOn;
    public boolean isShowKeyguard;
    public boolean isUmsSeedlingShowOnLockScreen;
    public boolean isUpdateMedia;
    private List<SeedlingMediaData> mediaData;
    public boolean notificationSortedAllowed;
    public boolean shouldNotificationSeedlingCardHidden;
    public boolean shouldPlayAnim;
    public boolean shouldSecondHomeSeedlingCardHidden;
    public boolean staticVoicePrintShow;

    public MediaLiveAlertData(List<SeedlingMediaData> list, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15) {
        this.mediaData = list;
        this.staticVoicePrintShow = z;
        this.isMediaSeedlingShowOnStatusBar = z2;
        this.allowCreateCard = z3;
        this.shouldNotificationSeedlingCardHidden = z4;
        this.shouldSecondHomeSeedlingCardHidden = z5;
        this.shouldPlayAnim = z6;
        this.isShowKeyguard = z7;
        this.isOccluded = z8;
        this.isUmsSeedlingShowOnLockScreen = z9;
        this.isFold = z10;
        this.isScreenOn = z11;
        this.isPanoramicAod = z12;
        this.notificationSortedAllowed = z13;
        this.isMilestone = z14;
        this.isUpdateMedia = z15;
    }

    public final List<SeedlingMediaData> component1() {
        return this.mediaData;
    }

    public final MediaLiveAlertData copy(List<SeedlingMediaData> list, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15) {
        return new MediaLiveAlertData(list, z, z2, z3, z4, z5, z6, z7, z8, z9, z10, z11, z12, z13, z14, z15);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaLiveAlertData)) {
            return false;
        }
        MediaLiveAlertData mediaLiveAlertData = (MediaLiveAlertData) obj;
        return Intrinsics.areEqual(this.mediaData, mediaLiveAlertData.mediaData) && this.staticVoicePrintShow == mediaLiveAlertData.staticVoicePrintShow && this.isMediaSeedlingShowOnStatusBar == mediaLiveAlertData.isMediaSeedlingShowOnStatusBar && this.allowCreateCard == mediaLiveAlertData.allowCreateCard && this.shouldNotificationSeedlingCardHidden == mediaLiveAlertData.shouldNotificationSeedlingCardHidden && this.shouldSecondHomeSeedlingCardHidden == mediaLiveAlertData.shouldSecondHomeSeedlingCardHidden && this.shouldPlayAnim == mediaLiveAlertData.shouldPlayAnim && this.isShowKeyguard == mediaLiveAlertData.isShowKeyguard && this.isOccluded == mediaLiveAlertData.isOccluded && this.isUmsSeedlingShowOnLockScreen == mediaLiveAlertData.isUmsSeedlingShowOnLockScreen && this.isFold == mediaLiveAlertData.isFold && this.isScreenOn == mediaLiveAlertData.isScreenOn && this.isPanoramicAod == mediaLiveAlertData.isPanoramicAod && this.notificationSortedAllowed == mediaLiveAlertData.notificationSortedAllowed && this.isMilestone == mediaLiveAlertData.isMilestone && this.isUpdateMedia == mediaLiveAlertData.isUpdateMedia;
    }

    public final boolean getAllowCreateCard() {
        return this.allowCreateCard;
    }

    public final List<SeedlingMediaData> getMediaData() {
        return this.mediaData;
    }

    public final boolean getNotificationSortedAllowed() {
        return this.notificationSortedAllowed;
    }

    public final boolean getShouldNotificationSeedlingCardHidden() {
        return this.shouldNotificationSeedlingCardHidden;
    }

    public final boolean getShouldPlayAnim() {
        return this.shouldPlayAnim;
    }

    public final boolean getShouldSecondHomeSeedlingCardHidden() {
        return this.shouldSecondHomeSeedlingCardHidden;
    }

    public final boolean getStaticVoicePrintShow() {
        return this.staticVoicePrintShow;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.isUpdateMedia) + ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(ChangeSize$.ExternalSyntheticOutline0.m(this.mediaData.hashCode() * 31, 31, this.staticVoicePrintShow), 31, this.isMediaSeedlingShowOnStatusBar), 31, this.allowCreateCard), 31, this.shouldNotificationSeedlingCardHidden), 31, this.shouldSecondHomeSeedlingCardHidden), 31, this.shouldPlayAnim), 31, this.isShowKeyguard), 31, this.isOccluded), 31, this.isUmsSeedlingShowOnLockScreen), 31, this.isFold), 31, this.isScreenOn), 31, this.isPanoramicAod), 31, this.notificationSortedAllowed), 31, this.isMilestone);
    }

    public final void setAllowCreateCard(boolean z) {
        this.allowCreateCard = z;
    }

    public final void setFold(boolean z) {
        this.isFold = z;
    }

    public final void setMediaData(List<SeedlingMediaData> list) {
        this.mediaData = list;
    }

    public final void setMediaSeedlingShowOnStatusBar(boolean z) {
        this.isMediaSeedlingShowOnStatusBar = z;
    }

    public final void setMilestone(boolean z) {
        this.isMilestone = z;
    }

    public final void setNotificationSortedAllowed(boolean z) {
        this.notificationSortedAllowed = z;
    }

    public final void setOccluded(boolean z) {
        this.isOccluded = z;
    }

    public final void setPanoramicAod(boolean z) {
        this.isPanoramicAod = z;
    }

    public final void setScreenOn(boolean z) {
        this.isScreenOn = z;
    }

    public final void setShouldNotificationSeedlingCardHidden(boolean z) {
        this.shouldNotificationSeedlingCardHidden = z;
    }

    public final void setShouldPlayAnim(boolean z) {
        this.shouldPlayAnim = z;
    }

    public final void setShouldSecondHomeSeedlingCardHidden(boolean z) {
        this.shouldSecondHomeSeedlingCardHidden = z;
    }

    public final void setShowKeyguard(boolean z) {
        this.isShowKeyguard = z;
    }

    public final void setStaticVoicePrintShow(boolean z) {
        this.staticVoicePrintShow = z;
    }

    public final void setUmsSeedlingShowOnLockScreen(boolean z) {
        this.isUmsSeedlingShowOnLockScreen = z;
    }

    public final void setUpdateMedia(boolean z) {
        this.isUpdateMedia = z;
    }

    public String toString() {
        List<SeedlingMediaData> list = this.mediaData;
        boolean z = this.staticVoicePrintShow;
        boolean z2 = this.isMediaSeedlingShowOnStatusBar;
        boolean z3 = this.allowCreateCard;
        boolean z4 = this.shouldNotificationSeedlingCardHidden;
        boolean z5 = this.shouldSecondHomeSeedlingCardHidden;
        boolean z6 = this.shouldPlayAnim;
        boolean z7 = this.isShowKeyguard;
        boolean z8 = this.isOccluded;
        boolean z9 = this.isUmsSeedlingShowOnLockScreen;
        boolean z10 = this.isFold;
        boolean z11 = this.isScreenOn;
        boolean z12 = this.isPanoramicAod;
        boolean z13 = this.notificationSortedAllowed;
        boolean z14 = this.isMilestone;
        boolean z15 = this.isUpdateMedia;
        StringBuilder sb = new StringBuilder("MediaLiveAlertData(mediaData=");
        sb.append(list);
        sb.append(", staticVoicePrintShow=");
        sb.append(z);
        sb.append(", isMediaSeedlingShowOnStatusBar=");
        KeyguardLogger$.ExternalSyntheticOutline0.m(", allowCreateCard=", ", shouldNotificationSeedlingCardHidden=", sb, z2, z3);
        KeyguardLogger$.ExternalSyntheticOutline0.m(", shouldSecondHomeSeedlingCardHidden=", ", shouldPlayAnim=", sb, z4, z5);
        KeyguardLogger$.ExternalSyntheticOutline0.m(", isShowKeyguard=", ", isOccluded=", sb, z6, z7);
        KeyguardLogger$.ExternalSyntheticOutline0.m(", isUmsSeedlingShowOnLockScreen=", ", isFold=", sb, z8, z9);
        KeyguardLogger$.ExternalSyntheticOutline0.m(", isScreenOn=", ", isPanoramicAod=", sb, z10, z11);
        KeyguardLogger$.ExternalSyntheticOutline0.m(", notificationSortedAllowed=", ", isMilestone=", sb, z12, z13);
        return DynamicRouteAttributes$.ExternalSyntheticOutline0.m(", isUpdateMedia=", ")", sb, z14, z15);
    }
}
