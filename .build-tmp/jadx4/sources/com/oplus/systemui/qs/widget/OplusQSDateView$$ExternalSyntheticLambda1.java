package com.oplus.systemui.qs.widget;

import com.oplus.systemui.keyguard.clock.WeatherInfoParseHelper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.function.Consumer;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final /* synthetic */ class OplusQSDateView$$ExternalSyntheticLambda1 implements Consumer {
    public /* synthetic */ OplusQSDateView f$0;

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        OplusQSDateView oplusQSDateView = this.f$0;
        WeatherInfoParseHelper.TimeInfo timeInfo = (WeatherInfoParseHelper.TimeInfo) obj;
        if (timeInfo != null) {
            int i = OplusQSDateView.$r8$clinit;
            oplusQSDateView.setText(timeInfo.getDateInfo());
            return;
        }
        if (oplusQSDateView.mDateFormat == null) {
            oplusQSDateView.mDateFormat = DateTimeFormatter.ofPattern(oplusQSDateView.mDatePattern, Locale.getDefault());
        }
        String str = LocalDateTime.now().format(oplusQSDateView.mDateFormat);
        if (str.equals(oplusQSDateView.mLastText)) {
            return;
        }
        oplusQSDateView.setText(str);
        oplusQSDateView.mLastText = str;
    }
}
