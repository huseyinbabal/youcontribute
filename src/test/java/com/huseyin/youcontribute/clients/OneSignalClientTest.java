package com.huseyin.youcontribute.clients;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import com.currencyfair.onesignal.OneSignal;
import com.currencyfair.onesignal.model.notification.Button;
import com.currencyfair.onesignal.model.notification.NotificationRequest;
import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.com.google.common.collect.ImmutableList;

import static org.junit.jupiter.api.Assertions.*;

class OneSignalClientTest {

    @Test
    public void it_should_send_notification() {
        //given

        NotificationRequest request = new NotificationRequest();
        request.setTemplateId("4f90f941-cdd2-43a4-b932-6ecff434b355");
        request.setAppId("651f0ad7-99cb-4370-a196-38a21a6994b7");
        request.setAnyWeb(true);
        request.setContents(new HashMap<>() {{
            put("base_url", "http://localhost:4200");
            put("issue_title", "Production kubernetes cluster is deleted accidentally!");
            put("challenge_id", "123123");
        }});
        request.setContents(new HashMap<>() {{
            put("en", "<b>fasdfasfasfasdfsafsadf</b>");
        }});
        Button acceptButton = new Button();
        acceptButton.setId("accept");
        acceptButton.setText("Accept");
        acceptButton.setUrl("http://localhost:4200/challenges/5/accept");
        Button rejectButton = new Button();
        rejectButton.setId("reject");
        rejectButton.setText("Reject");
        rejectButton.setUrl("http://localhost:4200/challenges/5/reject");
        request.setWebButtons(Arrays.asList(acceptButton, rejectButton));
        request.setIncludedSegments(new ArrayList<>(){{add("Subscribed Users");}});
        OneSignal.createNotification("ZWRmMzAyYmEtZGU2NC00NjliLTg5MjMtOTM2MTA2NDg2OTk0", request);
        //when

        //then

    }

}
