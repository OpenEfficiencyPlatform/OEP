import javax.xml.namespace.QName;
import com.sforce.soap.partner.sobject.SObject;

def account = new SObject();
account.setType("Account");
account.setField("Id", flowVars.SFAccountId);
account.setName(new QName("Account"));

Contact=
[
	AccountId: flowVars.SFAccountId,
	LastName: flowVars.ContactName,
	Email: flowVars.contactEmail
]

return [Contact]