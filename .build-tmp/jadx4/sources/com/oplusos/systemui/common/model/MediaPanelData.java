package com.oplusos.systemui.common.model;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class MediaPanelData {
    private CharSequence artist;
    private String currentTime;
    private Integer len;
    private Integer position;
    private CharSequence song;
    private String totalTime;

    public MediaPanelData(CharSequence charSequence, CharSequence charSequence2, String str, String str2, Integer num, Integer num2) {
        this.artist = charSequence;
        this.song = charSequence2;
        this.currentTime = str;
        this.totalTime = str2;
        this.position = num;
        this.len = num2;
    }

    public final CharSequence component1() {
        return this.artist;
    }

    public final CharSequence component2() {
        return this.song;
    }

    public final String component3() {
        return this.currentTime;
    }

    public final String component4() {
        return this.totalTime;
    }

    public final Integer component5() {
        return this.position;
    }

    public final Integer component6() {
        return this.len;
    }

    public final MediaPanelData copy(CharSequence charSequence, CharSequence charSequence2, String str, String str2, Integer num, Integer num2) {
        return new MediaPanelData(charSequence, charSequence2, str, str2, num, num2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaPanelData)) {
            return false;
        }
        MediaPanelData mediaPanelData = (MediaPanelData) obj;
        return Intrinsics.areEqual(this.artist, mediaPanelData.artist) && Intrinsics.areEqual(this.song, mediaPanelData.song) && Intrinsics.areEqual(this.currentTime, mediaPanelData.currentTime) && Intrinsics.areEqual(this.totalTime, mediaPanelData.totalTime) && Intrinsics.areEqual(this.position, mediaPanelData.position) && Intrinsics.areEqual(this.len, mediaPanelData.len);
    }

    public final CharSequence getArtist() {
        return this.artist;
    }

    public final String getCurrentTime() {
        return this.currentTime;
    }

    public final Integer getLen() {
        return this.len;
    }

    public final Integer getPosition() {
        return this.position;
    }

    public final CharSequence getSong() {
        return this.song;
    }

    public final String getTotalTime() {
        return this.totalTime;
    }

    public final int hashCode() {
        CharSequence charSequence = this.artist;
        int iHashCode = (charSequence == null ? 0 : charSequence.hashCode()) * 31;
        CharSequence charSequence2 = this.song;
        int iHashCode2 = (iHashCode + (charSequence2 == null ? 0 : charSequence2.hashCode())) * 31;
        String str = this.currentTime;
        int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.totalTime;
        int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer num = this.position;
        int iHashCode5 = (iHashCode4 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.len;
        return iHashCode5 + (num2 != null ? num2.hashCode() : 0);
    }

    public final void setArtist(CharSequence charSequence) {
        this.artist = charSequence;
    }

    public final void setCurrentTime(String str) {
        this.currentTime = str;
    }

    public final void setLen(Integer num) {
        this.len = num;
    }

    public final void setPosition(Integer num) {
        this.position = num;
    }

    public final void setSong(CharSequence charSequence) {
        this.song = charSequence;
    }

    public final void setTotalTime(String str) {
        this.totalTime = str;
    }

    public String toString() {
        return "MediaPanelData(artist=" + ((Object) this.artist) + ", song=" + ((Object) this.song) + ", currentTime=" + this.currentTime + ", totalTime=" + this.totalTime + ", position=" + this.position + ", len=" + this.len + ')';
    }
}
