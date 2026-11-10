package com.oplus.systemui.shared.clocks;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.widget.FrameLayout;
import com.android.systemui.plugins.keyguard.ui.clocks.ClockConfig;
import com.android.systemui.plugins.keyguard.ui.clocks.ClockController;
import com.android.systemui.plugins.keyguard.ui.clocks.ClockEvents;
import java.io.PrintWriter;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class OplusClockController implements ClockController {
    private final Context context;
    private final OplusClockFaceController largeClock = new OplusClockFaceController() { // from class: com.oplus.systemui.shared.clocks.OplusClockController$largeClock$1
        @Override // com.oplus.systemui.shared.clocks.OplusClockFaceController
        public OplusClockFaceEvents getEvents() {
            return new OplusClockController$largeClock$1$events$1();
        }

        @Override // com.oplus.systemui.shared.clocks.OplusClockFaceController
        public View getView() {
            return new FrameLayout(this.this$0.getContext());
        }
    };

    public OplusClockController(Context context) {
        this.context = context;
    }

    public void dump(PrintWriter printWriter) {
    }

    public int getClockOffsetY() {
        return 0;
    }

    public abstract ClockConfig getConfig();

    public final Context getContext() {
        return this.context;
    }

    public abstract ClockEvents getEvents();

    /* JADX INFO: renamed from: getSmallClock */
    public abstract OplusClockFaceController mo368getSmallClock();

    public void initialize(boolean z, float f, float f2) {
    }

    public void updateContext(Configuration configuration) {
    }

    public OplusClockFaceController getLargeClock() {
        return this.largeClock;
    }
}
