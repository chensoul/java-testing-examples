package cc.chensoul.jgiven.then;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import cc.chensoul.zendesk.ZendeskApiClient;
import cc.chensoul.user.User;
import org.mockito.Mockito;

@JGivenStage
public class ThenZendeskClientMock extends Stage<ThenZendeskClientMock> {

    @ExpectedScenarioState
    ZendeskApiClient zendeskApiClientMock;

    @ExpectedScenarioState
    User user;

    public ThenZendeskClientMock a_call_is_made_to_zendesk_to_create_the_ticket() {
        Mockito.verify(zendeskApiClientMock).createZendeskTicketForUser(user.getId());
        return this;
    }
}
