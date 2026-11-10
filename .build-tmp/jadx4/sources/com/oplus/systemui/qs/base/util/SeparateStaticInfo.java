package com.oplus.systemui.qs.base.util;

import android.view.MotionEvent;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class SeparateStaticInfo {
    public boolean disuse;
    private MotionEvent downEvent;
    public boolean downOnHotArea;
    private SeparateQSStatisicUtil.EXPANDED from;
    public boolean keyGuardShow;
    public long lastTrackTime;
    private SeparateQSStatisicUtil.EXPANDED lastTrackTo;
    private String opType;
    public boolean separateQSEnabled;
    private SeparateQSStatisicUtil.EXPANDED to;

    public SeparateStaticInfo() {
        SeparateQSStatisicUtil.EXPANDED expanded = SeparateQSStatisicUtil.EXPANDED.NONE;
        this.to = expanded;
        this.from = expanded;
        this.opType = "";
        this.disuse = true;
        this.lastTrackTo = expanded;
        this.lastTrackTime = -1L;
    }

    public final boolean getDisuse() {
        return this.disuse;
    }

    public final MotionEvent getDownEvent() {
        return this.downEvent;
    }

    public final boolean getDownOnHotArea() {
        return this.downOnHotArea;
    }

    public final SeparateQSStatisicUtil.EXPANDED getFrom() {
        return this.from;
    }

    public final boolean getKeyGuardShow() {
        return this.keyGuardShow;
    }

    public final long getLastTrackTime() {
        return this.lastTrackTime;
    }

    public final SeparateQSStatisicUtil.EXPANDED getLastTrackTo() {
        return this.lastTrackTo;
    }

    public final String getOpType() {
        return this.opType;
    }

    public final boolean getSeparateQSEnabled() {
        return this.separateQSEnabled;
    }

    public final SeparateQSStatisicUtil.EXPANDED getTo() {
        return this.to;
    }

    public final void reset() {
        SeparateQSStatisicUtil.EXPANDED expanded = SeparateQSStatisicUtil.EXPANDED.NONE;
        this.to = expanded;
        this.from = expanded;
        this.opType = "";
        this.keyGuardShow = false;
        this.separateQSEnabled = false;
        this.downEvent = null;
        this.downOnHotArea = false;
        this.disuse = true;
    }

    public final void setDisuse(boolean z) {
        this.disuse = z;
    }

    public final void setDownEvent(MotionEvent motionEvent) {
        this.downEvent = motionEvent;
    }

    public final void setDownOnHotArea(boolean z) {
        this.downOnHotArea = z;
    }

    public final void setFrom(SeparateQSStatisicUtil.EXPANDED expanded) {
        this.from = expanded;
    }

    public final void setKeyGuardShow(boolean z) {
        this.keyGuardShow = z;
    }

    public final void setLastTrackTime(long j) {
        this.lastTrackTime = j;
    }

    public final void setLastTrackTo(SeparateQSStatisicUtil.EXPANDED expanded) {
        this.lastTrackTo = expanded;
    }

    public final void setOpType(String str) {
        this.opType = str;
    }

    public final void setSeparateQSEnabled(boolean z) {
        this.separateQSEnabled = z;
    }

    public final void setTo(SeparateQSStatisicUtil.EXPANDED expanded) {
        this.to = expanded;
    }
}
