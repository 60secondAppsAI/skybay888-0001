import http from "../http-common"; 

class CustomerServiceRequestService {
  getAllCustomerServiceRequests(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/customerServiceRequest/customerServiceRequests`, searchDTO);
  }

  get(customerServiceRequestId) {
    return this.getRequest(`/customerServiceRequest/${customerServiceRequestId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/customerServiceRequest?field=${matchData}`, null);
  }

  addCustomerServiceRequest(data) {
    return http.post("/customerServiceRequest/addCustomerServiceRequest", data);
  }

  update(data) {
  	return http.post("/customerServiceRequest/updateCustomerServiceRequest", data);
  }
  
  uploadImage(data,customerServiceRequestId) {
  	return http.postForm("/customerServiceRequest/uploadImage/"+customerServiceRequestId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new CustomerServiceRequestService();
