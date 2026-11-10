package com.oplus.systemui.qs.res.domain.interactor;

import android.content.Intent;
import androidx.compose.material3.internal.TextFieldImplKt$;
import com.android.systemui.fragments.FragmentHostManager;
import com.oplus.systemui.qs.helper.QSFragmentHelper;
import com.oplus.systemui.qs.res.data.repository.QSThemeConfigRepository;
import com.oplusos.systemui.common.receiver.ThemeReceiver;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class StdQSResInteractor$startThemeChangeCollection$2 implements FlowCollector {
    public /* synthetic */ StdQSResInteractor this$0;

    public final Object emit(Object obj, Continuation continuation) {
        FragmentHostManager fragmentHostManager;
        Intent intent = (Intent) obj;
        boolean booleanExtra = intent.getBooleanExtra(ThemeReceiver.THEME_SKIN_CHANGED, false);
        QSThemeConfigRepository.Companion companion = QSThemeConfigRepository.Companion;
        StdQSResInteractor stdQSResInteractor = this.this$0;
        boolean zIsGlobalThemeSupportOutline = companion.isGlobalThemeSupportOutline(stdQSResInteractor.context);
        if (booleanExtra && !zIsGlobalThemeSupportOutline) {
            stdQSResInteractor.personalityRepository.resetQSShapeType();
        }
        boolean booleanExtra2 = intent.getBooleanExtra(ThemeReceiver.MATERIAL_COLOR_MODE, false);
        StringBuilder sbM = TextFieldImplKt$.ExternalSyntheticOutline0.m("themeChangeEvent: skinChanged=", ", supportOutline=", ", colorMode=", booleanExtra, zIsGlobalThemeSupportOutline);
        sbM.append(booleanExtra2);
        LogUtil.i$default(LogUtil.TAG_QS, "StdQSResInteractor", sbM.toString(), null, 8);
        if (!booleanExtra2 && (fragmentHostManager = FragmentHostManager.get(QSFragmentHelper.getInstance().getQsContainer())) != null) {
            fragmentHostManager.reloadFragments();
        }
        return Unit.INSTANCE;
    }
}
