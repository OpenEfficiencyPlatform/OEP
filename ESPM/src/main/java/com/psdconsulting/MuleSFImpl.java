package com.psdconsulting;

import javax.jws.WebService;

@WebService(endpointInterface = "com.psdconsulting.MuleSalesForce")
public class MuleSFImpl implements MuleSalesforce
{
	@Override
	public String UpdateProperty(String JobID)
	{
		return JobID;
	}
}