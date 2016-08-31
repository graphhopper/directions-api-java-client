
# Activity

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**type** | [**TypeEnum**](#TypeEnum) | type of activity |  [optional]
**id** | **String** | id referring to the underlying service or shipment, i.e. the shipment or service this activity belongs to |  [optional]
**locationId** | **String** | id that refers to address |  [optional]
**arrTime** | **Long** | arrival time at this activity in ms |  [optional]
**endTime** | **Long** | end time of and thus departure time at this activity |  [optional]


<a name="TypeEnum"></a>
## Enum: TypeEnum
Name | Value
---- | -----
START | &quot;start&quot;
END | &quot;end&quot;
SERVICE | &quot;service&quot;
PICKUPSHIPMENT | &quot;pickupShipment&quot;
DELIVERSHIPMENT | &quot;deliverShipment&quot;



