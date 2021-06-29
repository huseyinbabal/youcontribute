package com.huseyin.youcontribute.clients;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.currencyfair.onesignal.OneSignal;
import com.currencyfair.onesignal.model.notification.Button;
import com.currencyfair.onesignal.model.notification.NotificationRequest;
import com.huseyin.youcontribute.config.ApplicationProperties;
import org.springframework.stereotype.Service;

@Service
public class OneSignalClient {

    private final ApplicationProperties applicationProperties;

    public OneSignalClient(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    public void sendNotification(Integer challengeId, String issueTitle) {
        NotificationRequest request = new NotificationRequest();
        ApplicationProperties.OneSignalProperties oneSignal = this.applicationProperties.getOneSignal();
        request.setTemplateId(oneSignal.getNewChallengeTemplateId());
        request.setAppId(oneSignal.getAppId());
        request.setAnyWeb(true);
        request.setContents(new HashMap<>() {{
            put("en", String.format("Would you like to solve following challenge?\n%s", issueTitle));
        }});
        Button acceptButton = new Button();
        acceptButton.setId("accept");
        acceptButton.setText("Accept");
        acceptButton.setUrl(String.format("http://localhost:4200/challenge/%d/accept", challengeId));
        Button rejectButton = new Button();
        rejectButton.setId("reject");
        rejectButton.setText("Reject");
        rejectButton.setUrl(String.format("http://localhost:4200/challenge/%d/reject", challengeId));
        request.setWebButtons(Arrays.asList(acceptButton, rejectButton));
        request.setIncludedSegments(new ArrayList<>(){{add("Subscribed Users");}});
        OneSignal.createNotification(oneSignal.getApiAuthKey(), request);
    }

}
