import http from "../http-common"; 

class TicketPriceService {
  getAllTicketPrices(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/ticketPrice/ticketPrices`, searchDTO);
  }

  get(ticketPriceId) {
    return this.getRequest(`/ticketPrice/${ticketPriceId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/ticketPrice?field=${matchData}`, null);
  }

  addTicketPrice(data) {
    return http.post("/ticketPrice/addTicketPrice", data);
  }

  update(data) {
  	return http.post("/ticketPrice/updateTicketPrice", data);
  }
  
  uploadImage(data,ticketPriceId) {
  	return http.postForm("/ticketPrice/uploadImage/"+ticketPriceId, data);
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

export default new TicketPriceService();
