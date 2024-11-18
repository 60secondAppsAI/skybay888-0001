import http from "../http-common"; 

class FlightStatusService {
  getAllFlightStatuss(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/flightStatus/flightStatuss`, searchDTO);
  }

  get(flightStatusId) {
    return this.getRequest(`/flightStatus/${flightStatusId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/flightStatus?field=${matchData}`, null);
  }

  addFlightStatus(data) {
    return http.post("/flightStatus/addFlightStatus", data);
  }

  update(data) {
  	return http.post("/flightStatus/updateFlightStatus", data);
  }
  
  uploadImage(data,flightStatusId) {
  	return http.postForm("/flightStatus/uploadImage/"+flightStatusId, data);
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

export default new FlightStatusService();
