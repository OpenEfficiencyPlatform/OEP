package tecintegration;

import javax.jws.WebParam;
import javax.jws.WebService;

import tecintegration.transformers.MeterDetails;
import tecintegration.transformers.UpdateResult;


@WebService
public interface FetchAndUpdateMeter {
	UpdateResult FetchMeterData(@WebParam(name="Meter")MeterDetails meter);
}
