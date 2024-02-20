package emailTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;

import com.mailosaur.MailosaurClient;
import com.mailosaur.MailosaurException;
import com.mailosaur.models.Link;
import com.mailosaur.models.Message;
import com.mailosaur.models.MessageSearchParams;
import com.mailosaur.models.SearchCriteria;

public class TestMail {

	public class AppTest {

		@Test
		public void emailTestExample() throws IOException, MailosaurException {
			// Available in the API tab of a server
			String apiKey = "QPiICmtjoQZW816xoNKckr9xzOyHbfZ5";
			String serverId = "qnzuhgdx";
			String serverDomain = "qnzuhgdx.mailosaur.net";

			MailosaurClient mailosaur = new MailosaurClient(apiKey);

			MessageSearchParams params = new MessageSearchParams();
			params.withServer(serverId);

			SearchCriteria criteria = new SearchCriteria();
			criteria.withSentTo("anything@" + serverDomain);

			Message message;
			message = mailosaur.messages().get(params, criteria);

			//subject, cc, bcc, to, from
			System.out.println(message.subject());
			System.out.println(message.cc());
			System.out.println(message.bcc());
			System.out.println(message.to().get(0).email());
			System.out.println(message.from().get(0).email());
			//body
			System.out.println(message.text().body());
			
			//How many links available in email body:
			System.out.println(message.html().links().size());
			
			List<Link> links = message.html().links();
			for(Link link : links) {
				System.out.println(link.text());
				System.out.println(link.href());
			}
			assertNotNull(message);
			assertEquals("Fwd: Activate your Mailosaur account", message.subject());
			assertTrue(message.text().body().contains("The Mailosaur Team"));
		}
	}
}
