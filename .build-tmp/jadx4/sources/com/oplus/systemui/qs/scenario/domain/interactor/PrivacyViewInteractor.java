package com.oplus.systemui.qs.scenario.domain.interactor;

import android.content.Context;
import android.view.View;
import com.oplus.systemui.plugins.scenario.ScenarioAssistantNavigation;
import com.oplus.systemui.privacy.dialog.QSPrivacyDialogElement;
import com.oplus.systemui.qs.scenario.data.repository.OplusQSPrivacyRepository;
import com.oplus.systemui.qs.scenario.domain.adapter.PrivacyDialogHomePageAdapter$Factory;
import com.oplus.systemui.qs.scenario.domain.model.PrivacyViewDialogNavigation$Factory;
import java.util.List;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PrivacyViewInteractor {
    private final PrivacyDialogHomePageAdapter$Factory homePageAdapterFactory;
    private final PrivacyViewDialogNavigation$Factory navigationFactory;
    private final StateFlow privacyElementList;
    private final OplusQSPrivacyRepository privacyRepository;

    public PrivacyViewInteractor(OplusQSPrivacyRepository oplusQSPrivacyRepository, PrivacyViewDialogNavigation$Factory privacyViewDialogNavigation$Factory, PrivacyDialogHomePageAdapter$Factory privacyDialogHomePageAdapter$Factory) {
        this.privacyRepository = oplusQSPrivacyRepository;
        this.navigationFactory = privacyViewDialogNavigation$Factory;
        this.homePageAdapterFactory = privacyDialogHomePageAdapter$Factory;
        this.privacyElementList = oplusQSPrivacyRepository.getPrivacyElementList();
    }

    public final View createSepQSDialogView(Context context, ScenarioAssistantNavigation scenarioAssistantNavigation) {
        List<QSPrivacyDialogElement> list = (List) this.privacyRepository.getPrivacyElementList().getValue();
        if (list.isEmpty()) {
            return null;
        }
        return this.homePageAdapterFactory.create(this.navigationFactory.create(scenarioAssistantNavigation), list).createPageView(context);
    }

    public final StateFlow getPrivacyElementList() {
        return this.privacyElementList;
    }
}
