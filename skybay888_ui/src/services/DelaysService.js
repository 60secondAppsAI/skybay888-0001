import http from "../http-common"; 

class DelaysService {
  getAllDelayss(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/delays/delayss`, searchDTO);
  }

  get(delaysId) {
    return this.getRequest(`/delays/${delaysId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/delays?field=${matchData}`, null);
  }

  addDelays(data) {
    return http.post("/delays/addDelays", data);
  }

  update(data) {
  	return http.post("/delays/updateDelays", data);
  }
  
  uploadImage(data,delaysId) {
  	return http.postForm("/delays/uploadImage/"+delaysId, data);
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

export default new DelaysService();
